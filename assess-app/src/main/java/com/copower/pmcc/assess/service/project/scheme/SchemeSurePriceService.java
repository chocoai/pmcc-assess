package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.method.MdCostDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdIncomeDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdMarketCompareDao;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeSurePriceApplyDto;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.event.project.SchemeSurePriceEvent;
import com.copower.pmcc.assess.service.method.MdCommonService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcActivitiProcessManageService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by kings on 2018-10-15.
 */
@Service
public class SchemeSurePriceService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SchemeSurePriceDao schemeSurePriceDao;
    @Autowired
    private SchemeSurePriceItemDao schemeSurePriceItemDao;
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;
    @Autowired
    private DeclareRecordDao declareRecordDao;
    @Autowired
    private SchemeInfoDao schemeInfoDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private MdMarketCompareDao mdMarketCompareDao;
    @Autowired
    private MdIncomeDao mdIncomeDao;
    @Autowired
    private MdCostDao mdCostDao;
    @Autowired
    private MdDevelopmentDao mdDevelopmentDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private SchemeJudgeFunctionDao schemeJudgeFunctionDao;
    @Autowired
    private MdCommonService mdCommonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SchemeSurePriceFactorService schemeSurePriceFactorService;
    @Autowired
    private BpmRpcActivitiProcessManageService bpmRpcActivitiProcessManageService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private SchemeSurePriceFactorDao schemeSurePriceFactorDao;

    /**
     * 保存确定单价信息
     *
     * @param schemeSurePrice
     */
    public void saveSchemeSurePrice(SchemeSurePrice schemeSurePrice) {
        if (schemeSurePrice.getId() == null || schemeSurePrice.getId() == 0) {
            schemeSurePrice.setCreator(commonService.thisUserAccount());
            schemeSurePriceDao.addSurePrice(schemeSurePrice);
        } else {
            schemeSurePriceDao.updateSurePrice(schemeSurePrice);
        }
    }

    /**
     * 获取数据by planDetailsId
     *
     * @param planDetailsId
     * @return
     */
    public SchemeSurePrice getSurePriceByPlanDetailsId(Integer planDetailsId) {
        SchemeSurePrice where = new SchemeSurePrice();
        where.setPlanDetailsId(planDetailsId);
        return schemeSurePriceDao.getSchemeSurePrice(where);
    }

    public SchemeSurePrice getSchemeSurePriceBySchemeJudgeObjectId(Integer schemeJudgeObjectId) {
        SchemeSurePrice where = new SchemeSurePrice();
        where.setJudgeObjectId(schemeJudgeObjectId);
        List<SchemeSurePrice> schemeSurePriceList = schemeSurePriceDao.getSurePriceList(where);
        if (CollectionUtils.isNotEmpty(schemeSurePriceList)) {
            return schemeSurePriceList.get(0);
        } else {
            return null;
        }
    }

    public void deleteSurePriceAll(Integer projectId) {
        SchemeSurePrice where = new SchemeSurePrice();
        where.setProjectId(projectId);
        List<SchemeSurePrice> schemeSurePriceList = schemeSurePriceDao.getSurePriceList(where);
        if (CollectionUtils.isNotEmpty(schemeSurePriceList)) {
            for (SchemeSurePrice schemeSurePrice : schemeSurePriceList) {
                SchemeSurePriceItem whereItem = new SchemeSurePriceItem();
                where.setJudgeObjectId(schemeSurePrice.getJudgeObjectId());
                List<SchemeSurePriceItem> surePriceItemList = schemeSurePriceItemDao.getSurePriceItemList(whereItem);
                if (CollectionUtils.isNotEmpty(surePriceItemList))
                    surePriceItemList.forEach(o -> schemeSurePriceItemDao.deleteSurePriceItem(o.getId()));
                schemeSurePriceDao.deleteSurePrice(schemeSurePrice.getId());
            }
        }
    }

    /**
     * 获取单价确定明细数据
     *
     * @param judgeObjectId
     * @param isUpdatePrice 确认是否允许调整
     * @return
     * @throws BusinessException
     */
    @Transactional
    public List<SchemeSurePriceItem> getSchemeSurePriceItemList(Integer judgeObjectId, boolean isUpdatePrice) throws BusinessException {
        List<SchemeSurePriceItem> surePriceItemList = new ArrayList<>();
        if (judgeObjectId == null) {
            return surePriceItemList;
        }
        SchemeSurePriceItem where = new SchemeSurePriceItem();
        where.setJudgeObjectId(judgeObjectId);
        List<SchemeSurePriceItem> surePriceItemList2 = schemeSurePriceItemDao.getSurePriceItemList(where);
        SchemeJudgeObject judgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(judgeObjectId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(judgeObject.getProjectId());
        if (CollectionUtils.isNotEmpty(surePriceItemList2)) {
            surePriceItemList.addAll(surePriceItemList2);
            if (isUpdatePrice) {//更新试算价格
                for (SchemeSurePriceItem schemeSurePriceItem : surePriceItemList) {
                    schemeSurePriceItem.setTrialPrice(getPrice(judgeObjectId, schemeSurePriceItem.getMethodType()));
                    schemeSurePriceItemDao.updateSurePriceItem(schemeSurePriceItem);
                }
            }
        }
        //当没有就初始化数据
        if (CollectionUtils.isEmpty(surePriceItemList)) {
            SchemeJudgeFunction functionWhere = new SchemeJudgeFunction();
            functionWhere.setBisApplicable(true);
            functionWhere.setJudgeObjectId(judgeObjectId);
            List<SchemeJudgeFunction> judgeFunctions = schemeJudgeFunctionDao.getSchemeJudgeFunction(functionWhere);
            if (CollectionUtils.isNotEmpty(judgeFunctions)) {
                List<BaseDataDic> baseMethodList = mdCommonService.getBaseMethodList(projectInfo.getProjectCategoryId());
                List<Integer> methodTypeList = LangUtils.transform(baseMethodList, o -> o.getId());
                List<SchemeJudgeFunction> filter = LangUtils.filter(judgeFunctions, o -> methodTypeList.contains(o.getMethodType()));
                for (SchemeJudgeFunction judgeFunction : filter) {
                    SchemeSurePriceItem schemeSurePriceItem = new SchemeSurePriceItem();
                    schemeSurePriceItem.setJudgeObjectId(judgeObjectId);
                    schemeSurePriceItem.setMethodType(judgeFunction.getMethodType());
                    schemeSurePriceItem.setMethodName(baseDataDicService.getNameById(judgeFunction.getMethodType()));
                    schemeSurePriceItem.setTrialPrice(getPrice(judgeObjectId, judgeFunction.getMethodType()));
                    schemeSurePriceItem.setCreator(commonService.thisUserAccount());
                    schemeSurePriceItemDao.addSurePriceItem(schemeSurePriceItem);
                    surePriceItemList.add(schemeSurePriceItem);
                }
            }
        }
        //确认调整才允许计算均值价格
        if (isUpdatePrice && CollectionUtils.isNotEmpty(surePriceItemList)) {
            //如果价格差异小于等于10% 自动设置对应权重 求取平均价
            List<BigDecimal> decimalList = LangUtils.transform(surePriceItemList, o -> o.getTrialPrice());
            if (CollectionUtils.isEmpty(decimalList)) {
                throw new BusinessException("未获取到方法试算价格");
            }
            Collections.sort(decimalList);
            int equ = publicService.computeDifference(decimalList.stream().findFirst().get(), decimalList.get(decimalList.size() - 1));
            if (equ <= 10) {
                BigDecimal averageWeight = new BigDecimal("1").divide(new BigDecimal(surePriceItemList.size()), 4, BigDecimal.ROUND_HALF_DOWN);
                for (int i = 0; i < surePriceItemList.size(); i++) {
                    if (i == surePriceItemList.size() - 1) {
                        surePriceItemList.get(i).setWeight(new BigDecimal("1").subtract(averageWeight.multiply(new BigDecimal(surePriceItemList.size() - 1))));
                    } else {
                        surePriceItemList.get(i).setWeight(averageWeight);
                    }
                    schemeSurePriceItemDao.updateSurePriceItem(surePriceItemList.get(i));
                }
                return schemeSurePriceItemDao.getSurePriceItemList(where);
            }
        }
        return surePriceItemList;
    }


    /**
     * 获取方法名称
     *
     * @param methodType
     * @return
     */
    private String getMethodName(String methodType) {
        if (StringUtils.isEmpty(methodType)) return null;
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(methodType);
        if (baseDataDic == null) return null;
        return baseDataDic.getName();
    }

    /**
     * 获取方法测算价格
     *
     * @param methodType
     * @param judgeObjectId
     * @return
     */
    private BigDecimal getPrice(Integer judgeObjectId, Integer methodType) {
        SchemeInfo where = new SchemeInfo();
        where.setJudgeObjectId(judgeObjectId);
        where.setMethodType(methodType);
        SchemeInfo schemeInfo = schemeInfoDao.getSchemeInfo(where);
        if (schemeInfo == null) return new BigDecimal("0");
        Integer methodDataId = schemeInfo.getMethodDataId();
        String methTypeKey = baseDataDicService.getDataDicById(methodType).getFieldName();
        BigDecimal price = null;
        switch (methTypeKey) {
            case AssessDataDicKeyConstant.MD_MARKET_COMPARE:
                MdMarketCompare marketCompare = mdMarketCompareDao.getMarketCompareById(methodDataId);
                if (marketCompare != null)
                    price = marketCompare.getPrice();
                break;
            case AssessDataDicKeyConstant.MD_INCOME:
                MdIncome mdIncome = mdIncomeDao.getIncomeById(methodDataId);
                if (mdIncome != null)
                    price = mdIncome.getPrice();
                break;
            case AssessDataDicKeyConstant.MD_COST:
                MdCost mdCost = mdCostDao.getMdCostById(methodDataId);
                if (mdCost != null)
                    price = mdCost.getPrice();
                break;
            case AssessDataDicKeyConstant.MD_DEVELOPMENT:
                MdDevelopment mdDevelopment = mdDevelopmentDao.getMdDevelopmentById(methodDataId);
                if (mdDevelopment != null)
                    price = mdDevelopment.getPrice();
                break;
        }
        return price == null ? new BigDecimal("0") : price;
    }

    /**
     * 提交对应单价
     *
     * @param schemeSurePriceApplyDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void submitSurePrice(SchemeSurePriceApplyDto schemeSurePriceApplyDto, ProjectPlanDetails projectPlanDetails, String processInsId) {
        SchemeSurePrice schemeSurePrice = schemeSurePriceDao.getSurePriceById(schemeSurePriceApplyDto.getId());
        schemeSurePrice.setProcessInsId(processInsId);
        schemeSurePrice.setWeightExplain(schemeSurePriceApplyDto.getWeightExplain());
        schemeSurePrice.setPrice(schemeSurePriceApplyDto.getPrice());
        schemeSurePriceDao.updateSurePrice(schemeSurePrice);

        List<SchemeSurePriceItem> surePriceItems = schemeSurePriceApplyDto.getSurePriceItemList();
        if (CollectionUtils.isNotEmpty(surePriceItems)) {
            for (SchemeSurePriceItem surePriceItem : surePriceItems) {
                schemeSurePriceItemDao.updateSurePriceItem(surePriceItem);
            }
        }
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(schemeSurePriceApplyDto.getJudgeObjectId());
        if (schemeJudgeObject != null) {
            schemeJudgeObject.setPrice(schemeSurePriceApplyDto.getPrice());
            schemeJudgeObject.setOriginalPrice(schemeSurePriceApplyDto.getPrice());
            schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
        }
        List<SchemeJudgeObject> childrenList = Lists.newArrayList();
        //如果是合并对象，则找出子项并更新子项的单价
        if (schemeJudgeObject.getBisMerge() == Boolean.TRUE) {
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByPid(schemeJudgeObject.getId());
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                childrenList.addAll(judgeObjectList);
            }
        } else {
            DeclareRecord declareRecord = declareRecordDao.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            declareRecord.setPrice(schemeSurePriceApplyDto.getPrice());
            declareRecordDao.updateDeclareRecord(declareRecord);
        }
        if (CollectionUtils.isNotEmpty(childrenList)) {
            for (SchemeJudgeObject judgeObject : childrenList) {
                judgeObject.setPrice(schemeSurePriceApplyDto.getPrice());
                judgeObject.setOriginalPrice(schemeSurePriceApplyDto.getPrice());
                schemeJudgeObjectDao.updateSchemeJudgeObject(judgeObject);
                //同步更新申报记录单价
                DeclareRecord declareRecord = declareRecordDao.getDeclareRecordById(judgeObject.getDeclareRecordId());
                declareRecord.setPrice(schemeSurePriceApplyDto.getPrice());
                declareRecordDao.updateDeclareRecord(declareRecord);
                final Integer one = 1;
                final Integer zero = 0;
                Map<Integer, List<BigDecimal>> integerBigDecimalMap = Maps.newHashMap();
                //不更新作为标准的哪个估价对象
                if (!Objects.equal(judgeObject.getId(), schemeJudgeObject.getId())) {
                    List<SchemeSurePriceFactor> schemeSurePriceFactorList = schemeSurePriceFactorService.getSurePriceFactors(judgeObject.getId());
                    if (CollectionUtils.isNotEmpty(schemeSurePriceFactorList)) {
                        for (SchemeSurePriceFactor priceFactor : schemeSurePriceFactorList) {
                            if (priceFactor.getType() == null || priceFactor.getCoefficient() == null) {
                                continue;
                            }
                            List<BigDecimal> bigDecimalList = integerBigDecimalMap.get(priceFactor.getType());
                            if (CollectionUtils.isEmpty(bigDecimalList)) {
                                bigDecimalList = Lists.newArrayList();
                            }
                            bigDecimalList.add(priceFactor.getCoefficient());
                            integerBigDecimalMap.put(priceFactor.getType(), bigDecimalList);
                        }
                    }
                }
                if (!integerBigDecimalMap.isEmpty()) {
                    List<BigDecimal> bigDecimalListA = Lists.newArrayList();
                    List<BigDecimal> bigDecimalListB = Lists.newArrayList();
                    for (Map.Entry<Integer, List<BigDecimal>> integerListEntry : integerBigDecimalMap.entrySet()) {
                        if (Objects.equal(zero, integerListEntry.getKey())) {
                            bigDecimalListA.addAll(integerListEntry.getValue());
                        }
                        if (Objects.equal(one, integerListEntry.getKey())) {
                            bigDecimalListB.addAll(integerListEntry.getValue());
                        }
                    }
                    BigDecimal temp = new BigDecimal(0);
                    if (CollectionUtils.isNotEmpty(bigDecimalListA)) {
                        double sum = bigDecimalListA.stream().mapToDouble(BigDecimal::doubleValue).sum();
                        temp = temp.add(new BigDecimal(sum));
                    }
                    if (CollectionUtils.isNotEmpty(bigDecimalListB)) {
                        double sum = bigDecimalListB.stream().mapToDouble(BigDecimal::doubleValue).sum();
                        temp = temp.add(new BigDecimal(sum).multiply(schemeSurePriceApplyDto.getPrice()));
                    }
                    temp = temp.add(schemeSurePriceApplyDto.getPrice());
                    if (temp.toBigInteger().intValue() != 0) {
                        judgeObject.setPrice(temp);
                        judgeObject.setOriginalPrice(temp);
                        schemeJudgeObjectDao.updateSchemeJudgeObject(judgeObject);
                    }
                }
            }
        }
        if (!org.apache.commons.lang.StringUtils.isBlank(processInsId)) {
            //修改监听器
            try {
                bpmRpcActivitiProcessManageService.setProcessEventExecutor(processInsId, SchemeSurePriceEvent.class.getSimpleName());
            } catch (BpmException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    /**
     * 导出
     *
     * @param response
     */
    public void generateAndExport(HttpServletResponse response, Integer pid) throws BusinessException, IOException {
        List<SchemeJudgeObjectVo> vos = schemeJudgeObjectService.getAdjustObjectListByPid(pid);
        if (CollectionUtils.isEmpty(vos)) {
            throw new BusinessException("没有获取到有效的数据");
        }
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        //创建Excel标题 估价对象名称、评估面积、楼层、房号、评估价格、因素
        String[] title = {"估价对象名称", "评估面积", "楼层", "房号", "评估价格", "因素"};

        for (int i = 0; i < title.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title[i]);
            sheet.setColumnWidth(i, 4000);
        }
        for (int i = 0; i < vos.size(); i++) {
            row = sheet.createRow(i + 1);
            SchemeJudgeObjectVo schemeJudgeObjectVo = vos.get(i);
            row.createCell(0).setCellValue(schemeJudgeObjectVo.getName()); // 估价对象名称
            row.createCell(1).setCellValue(String.valueOf(schemeJudgeObjectVo.getFloorArea() == null ? "" : schemeJudgeObjectVo.getFloorArea())); // 评估面积
            row.createCell(2).setCellValue(schemeJudgeObjectVo.getFloor()); // 楼层
            row.createCell(3).setCellValue(schemeJudgeObjectVo.getRoomNumber()); // 房号
            row.createCell(4).setCellValue(String.valueOf(schemeJudgeObjectVo.getPrice() == null ? "" : schemeJudgeObjectVo.getPrice())); // 评估价格
            row.createCell(5).setCellValue(schemeJudgeObjectVo.getCoefficient()); // 因素
        }

        OutputStream os = response.getOutputStream();
        try {
            this.setResponseHeader(response, "确定单价.XLS");
            wb.write(os);

        } catch (Exception e) {
            throw new BusinessException("导出Excel出错:" + e);
        } finally {
            os.flush();
            os.close();
        }
    }

    /**
     * 响应流
     *
     * @param response
     * @param fileName
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 功能描述: 导入
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/25 18:31
     */
    public String importData(MultipartFile multipartFile, Integer pid) throws Exception {
        Workbook workbook = null;
        Row row = null;
        StringBuilder builder = new StringBuilder();
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        //只取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //工作表的第一行
        row = sheet.getRow(0);
        //读取数据的起始行
        int startRowNumber = 1;
        //导入成功数据条数
        int successCount = 0;
        //总列数
        int colLength = row.getPhysicalNumberOfCells() != 0 ? row.getPhysicalNumberOfCells() : row.getLastCellNum();
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        rowLength = rowLength - startRowNumber;
        if (rowLength == 0) {
            builder.append("没有数据!");
            return builder.toString();
        }
        for (int i = startRowNumber; i < rowLength + startRowNumber; i++) {
            SchemeJudgeObject schemeJudgeObject = null;
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                schemeJudgeObject = new SchemeJudgeObject();
                if (!this.updateData(pid, schemeJudgeObject, builder, row, colLength, i)) {
                    continue;
                }

                successCount++;
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }

        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

    public boolean updateData(Integer pid, SchemeJudgeObject schemeJudgeObject, StringBuilder builder, Row row, int colLength, int i) throws Exception {
        //通过委估对象名称找到对应的委估对象
        String name = PoiUtils.getCellValue(row.getCell(0));
        if (StringUtils.isEmpty(name)) {
            builder.append(String.format("\n第%s行异常：委估对象名称不能为空", i));
            return false;
        }
        List<SchemeJudgeObjectVo> vos = schemeJudgeObjectService.getAdjustObjectListByPid(pid);
        if (CollectionUtils.isNotEmpty(vos)) {
            List<String> names = LangUtils.transform(vos, o -> o.getName());
            if (!names.contains(name)) {
                builder.append(String.format("\n第%s行异常：委估对象名称没有匹配", i));
                return false;
            }
            for (SchemeJudgeObjectVo item : vos) {
                if (item.getName().equals(name)) {
                    schemeJudgeObject = item;
                }
            }

        }


        //评估价格
        if (!StringUtils.isEmpty(PoiUtils.getCellValue(row.getCell(4)))) {
            if (this.isNumeric(PoiUtils.getCellValue(row.getCell(4)))) {
                schemeJudgeObject.setPrice(new BigDecimal(PoiUtils.getCellValue(row.getCell(4))));
            } else {
                builder.append(String.format("\n第%s行异常：评估价格应填写数字", i));
                return false;
            }
        }
        SchemeSurePriceFactor schemeSurePriceFactor = null;
        List<SchemeSurePriceFactor> factorList = schemeSurePriceFactorDao.getFactorListByJudgeObjectId(schemeJudgeObject.getId());
        if (CollectionUtils.isNotEmpty(factorList)) {
            //因素
            if (!StringUtils.isEmpty(PoiUtils.getCellValue(row.getCell(5)))) {
                String value = PoiUtils.getCellValue(row.getCell(5)).trim();
                //定义空格,回车,换行符,制表符
                String spaceRegex = "\\s*|\t|\r|\n";
                // 过滤空格等
                value = value.replaceAll(spaceRegex, "");
                String[] coefficients = value.split(";");
                List<String> coefficientList = Arrays.asList(coefficients);
                for (String coefficientItem : coefficientList) {
                    //通过类型匹配
                    String factor = coefficientItem.substring(0, coefficientItem.indexOf(":"));
                    for (SchemeSurePriceFactor schemeSurePriceFactorItem : factorList) {
                        StringBuilder temp = new StringBuilder();
                        temp.append(schemeSurePriceFactorItem.getFactor()).append(schemeSurePriceFactorItem.getRemark());
                        if (temp.toString().equals(factor)) {
                            schemeSurePriceFactor = schemeSurePriceFactorItem;
                            String coefficientData = coefficientItem.substring(coefficientItem.indexOf(":") + 1);
                            if (coefficientData.contains("%")) {
                                String coefficient = ArithmeticUtils.parseFormatString(coefficientData);
                                schemeSurePriceFactor.setCoefficient(new BigDecimal(coefficient));
                            }
                            if (this.isNumeric(coefficientData)) {
                                schemeSurePriceFactor.setCoefficient(new BigDecimal(coefficientData));
                            }
                            schemeSurePriceFactorDao.updateSurePriceFactor(schemeSurePriceFactor);
                        }
                    }

                }


            }

        }

        schemeJudgeObjectService.updateSchemeJudgeObject(schemeJudgeObject);
        return true;
    }

    //验证是否是数字或小数
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        if (str.indexOf(".") > 0) {//判断是否有小数点
            if (str.indexOf(".") == str.lastIndexOf(".") && str.split("\\.").length == 2) { //判断是否只有一个小数点
                return pattern.matcher(str.replace(".", "")).matches();
            } else {
                return false;
            }
        } else {
            return pattern.matcher(str).matches();
        }

    }

}
