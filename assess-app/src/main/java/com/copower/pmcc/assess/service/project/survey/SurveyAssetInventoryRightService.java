package com.copower.pmcc.assess.service.project.survey;


import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryRightDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRight;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryRightVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: zch
 * @date: 2019/3/18 17:29
 * @description:他项权力普通类 (他项权利主类 > 他项权力申报类 > 他项权力普通类)
 */
@Service
public class SurveyAssetInventoryRightService {

    @Autowired
    private SurveyAssetInventoryRightDao surveyAssetInventoryRightDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    public SurveyAssetInventoryRight getSurveyAssetInventoryRightById(Integer id) {
        return surveyAssetInventoryRightDao.getSurveyAssetInventoryRightById(id);
    }

    public List<SurveyAssetInventoryRight> getSurveyAssetInventoryRightBy(Integer inventoryRightRecordId){
        SurveyAssetInventoryRight query = new SurveyAssetInventoryRight();
        query.setInventoryRightRecordId(inventoryRightRecordId);
        return surveyAssetInventoryRightDao.getSurveyAssetInventoryRightList(query);
    }

    public List<SurveyAssetInventoryRight> surveyAssetInventoryRights(Integer planDetailsId) {
        SurveyAssetInventoryRight surveyAssetInventoryRight = new SurveyAssetInventoryRight();
        surveyAssetInventoryRight.setPlanDetailsId(planDetailsId);
        return surveyAssetInventoryRightDao.getSurveyAssetInventoryRightList(surveyAssetInventoryRight);
    }

    public List<SurveyAssetInventoryRight> getSurveyAssetInventoryRightList(SurveyAssetInventoryRight surveyAssetInventoryRight) {
        return surveyAssetInventoryRightDao.getSurveyAssetInventoryRightList(surveyAssetInventoryRight);
    }

    public BootstrapTableVo getBootstrapTableVo(SurveyAssetInventoryRight surveyAssetInventoryRight) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = this.getSurveyAssetInventoryRightList(surveyAssetInventoryRight);
        List<SurveyAssetInventoryRightVo> surveyAssetInventoryRightVoList = getVoList(surveyAssetInventoryRightList);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(surveyAssetInventoryRightVoList) ? new ArrayList<SurveyAssetInventoryRight>() : surveyAssetInventoryRightVoList);
        return vo;
    }

    public void removeSurveyAssetInventoryRight(SurveyAssetInventoryRight surveyAssetInventoryRight) {
        surveyAssetInventoryRightDao.removeSurveyAssetInventoryRight(surveyAssetInventoryRight);
    }

    public List<SurveyAssetInventoryRightVo> getVoList(List<SurveyAssetInventoryRight> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return LangUtils.transform(list, p -> {
            return getSurveyAssetInventoryRightVo(p);
        });
    }

    public SurveyAssetInventoryRightVo getSurveyAssetInventoryRightVo(SurveyAssetInventoryRight right){
        SurveyAssetInventoryRightVo surveyAssetInventoryRightVo = new SurveyAssetInventoryRightVo();
        BeanUtils.copyProperties(right, surveyAssetInventoryRightVo);
        if (right.getCategory() != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(right.getCategory());
            if (projectClassify != null) {
                surveyAssetInventoryRightVo.setCategoryName(projectClassify.getName());
            }
        }
        if (right.getType() != null) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(right.getType());
            if (projectClassify != null) {
                surveyAssetInventoryRightVo.setTypeName(projectClassify.getName());
            }
        }
        return surveyAssetInventoryRightVo;
    }

    /**
     * 更新数据
     *
     * @param surveyAssetInventoryRight
     * @throws BusinessException
     */
    public void save(SurveyAssetInventoryRight surveyAssetInventoryRight) throws BusinessException {
        if (surveyAssetInventoryRight == null) {
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        }
        if (surveyAssetInventoryRight.getId() != null && surveyAssetInventoryRight.getId().intValue() > 0) {
            surveyAssetInventoryRightDao.update(surveyAssetInventoryRight);
        } else {
            surveyAssetInventoryRight.setCreator(commonService.thisUserAccount());
            surveyAssetInventoryRightDao.add(surveyAssetInventoryRight);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInventoryRight.class), surveyAssetInventoryRight.getId());
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    public boolean delete(Integer id) throws BusinessException {
        if (id == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        return surveyAssetInventoryRightDao.delete(id);
    }

    /**
     * 导入数据
     *
     * @return
     */
    public String importData(SurveyAssetInventoryRight right, MultipartFile multipartFile) throws BusinessException, IOException {
        if (right == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook hssfWorkbook = PoiUtils.isExcel2003(filePath) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
        Sheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
        int startRowNumber = 1;//读取数据的起始行
        int rowCount = sheet.getLastRowNum() + 1 - startRowNumber;//数据总行数
        StringBuilder errorMsg = new StringBuilder();
        int successCount = 0;//导入成功数据条数
        List<BaseDataDic> typeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.INVENTORY_RIGHT_TYPE);
        for (int i = startRowNumber; i <= sheet.getLastRowNum(); i++) {
            try {
                Row row = sheet.getRow(i);
                SurveyAssetInventoryRight surveyAssetInventoryRight = new SurveyAssetInventoryRight();
                surveyAssetInventoryRight.setProjectId(right.getProjectId());
                surveyAssetInventoryRight.setPlanDetailsId(right.getPlanDetailsId());
                surveyAssetInventoryRight.setCertName(right.getCertName());
                surveyAssetInventoryRight.setCreator(commonService.thisUserAccount());
                BaseDataDic typeDic = baseDataDicService.getDataDicByName(typeList, PoiUtils.getCellValue(row.getCell(1)));
                if (typeDic == null) {
                    errorMsg.append(String.format("\n第%s行异常：类型与系统配置的名称不一致", i + 1));
                    continue;
                }
                surveyAssetInventoryRight.setType(typeDic.getId());
                List<BaseDataDic> categoryList = baseDataDicService.getCacheDataDicListByPid(typeDic.getId());
                BaseDataDic categoryDic = baseDataDicService.getDataDicByName(categoryList, PoiUtils.getCellValue(row.getCell(2)));
                if (categoryDic == null) {
                    errorMsg.append(String.format("\n第%s行异常：类别与系统配置的名称不一致", i + 1));
                    continue;
                }
                surveyAssetInventoryRight.setCategory(categoryDic.getId());
                surveyAssetInventoryRight.setNumber(PoiUtils.getCellValue(row.getCell(3)));
                surveyAssetInventoryRight.setRegisterDate(DateUtils.convertDate(PoiUtils.getCellValue(row.getCell(4))));
                surveyAssetInventoryRight.setObligor(PoiUtils.getCellValue(row.getCell(5)));
                surveyAssetInventoryRight.setObligee(PoiUtils.getCellValue(row.getCell(6)));
                surveyAssetInventoryRight.setRegisterAmount(PoiUtils.getCellValue(row.getCell(7)));
                surveyAssetInventoryRight.setActualAmount(PoiUtils.getCellValue(row.getCell(8)));
                surveyAssetInventoryRight.setRegisterArea(PoiUtils.getCellValue(row.getCell(9)));
                surveyAssetInventoryRight.setRightRank(PoiUtils.getCellValue(row.getCell(10)));
                surveyAssetInventoryRight.setBeginDate(DateUtils.convertDate(PoiUtils.getCellValue(row.getCell(11))));
                surveyAssetInventoryRight.setEndDate(DateUtils.convertDate(PoiUtils.getCellValue(row.getCell(12))));
                surveyAssetInventoryRightDao.add(surveyAssetInventoryRight);
                successCount++;
            } catch (Exception e) {
                errorMsg.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
        }
        inputStream.close();
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowCount, successCount, rowCount - successCount, errorMsg.toString());
    }
}
