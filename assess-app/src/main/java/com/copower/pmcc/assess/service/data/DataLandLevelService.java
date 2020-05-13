package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.data.DataLandLevelEvent;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
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
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/9/4 18:24
 * @Description:土地级别维护
 */
@Service
public class DataLandLevelService {
    @Autowired
    private DataLandLevelDao dataLandLevelDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BaseService baseService;

    private boolean importDataLandLevel(DataLandLevel oo, StringBuilder builder, Row row, int i) throws Exception {
        Map<String, String> map = new HashMap<>();
        List<BaseDataDic> listLandRightTypes = baseDataDicService.getCacheDataDicList("project.declare.land.certificate.type");
        //验证(区域) 省市区
        if (erpAreaService.checkArea(PoiUtils.getCellValue(row.getCell(0)), PoiUtils.getCellValue(row.getCell(1)), PoiUtils.getCellValue(row.getCell(2)), builder, map)) {
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.PROVINCE))) {
                oo.setProvince(map.get(erpAreaService.PROVINCE));
            }
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.CITY))) {
                oo.setCity(map.get(erpAreaService.CITY));
            }
            if (!org.springframework.util.StringUtils.isEmpty(map.get(erpAreaService.DISTRICT))) {
                oo.setDistrict(map.get(erpAreaService.DISTRICT));
            }
        } else {
            builder.append(String.format("\n第%s行异常：区域类型与系统配置的名称不一致 ===>请检查省市区(县) ", i));
            return false;
        }

        for (int j = 0; j < 18; j++) {
            switch (j) {
                case 3: {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(j)))) {
                        oo.setTownShipName(PoiUtils.getCellValue(row.getCell(j)));
                    }
                    break;
                }
                case 4: {
                    //验证基础字典中数据
                    String value = PoiUtils.getCellValue(row.getCell(j));

                    if (StringUtils.isNotBlank(value)) {
                        BaseDataDic typeDic = baseDataDicService.getDataDicByName(listLandRightTypes, value);
                        if (typeDic == null) {
                            builder.append(String.format("\n第%s行异常：类型与系统配置的名称不一致(共有情况)", i));
                            return false;
                        } else {
                            //共有情况
                            oo.setLandRightType(typeDic.getId());
                        }
                    } else {
                        builder.append(String.format("\n第%s行异常：权利类型必须填写", i));
                        return false;
                    }
                    break;
                }
                case 5: {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(j)))) {
                        oo.setValuationDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(j))));
                    }
                    break;
                }
                case 6: {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(j)))) {
                        oo.setReleaseDate(DateUtils.parse(PoiUtils.getCellValue(row.getCell(j))));
                    }
                    break;
                }
                case 7: {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(j)))) {
                        oo.setExecutionTime(DateUtils.parse(PoiUtils.getCellValue(row.getCell(j))));
                    }
                    break;
                }
                case 8: {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(j)))) {
                        oo.setWordSymbol(PoiUtils.getCellValue(row.getCell(j)));
                    }
                    break;
                }
                case 9: {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(j)))) {
                        oo.setTitle(PoiUtils.getCellValue(row.getCell(j)));
                    }
                    break;
                }
                case 10: {
                    if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(j)))) {
                        oo.setLandDefinition(PoiUtils.getCellValue(row.getCell(j)));
                    }
                    break;
                }
                default:
                    break;
            }
        }


        return true;
    }

    /**
     * 土地级别区域(维护)
     *
     * @param dataLandLevel
     * @param multipartFile
     * @return
     * @throws Exception
     */
    public String importDataLandLevel(DataLandLevel dataLandLevel, MultipartFile multipartFile) throws Exception {
        StringBuilder builder = new StringBuilder(10);
        Workbook workbook = null;
        Row row = null;
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "土地级别区域(维护)");
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
            DataLandLevel target = null;
            //标识符
            boolean flag = true;
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                target = new DataLandLevel();
                BeanUtils.copyProperties(dataLandLevel, target);
                target.setId(null);
                //excel 处理
                if (!this.importDataLandLevel(target, builder, row, i)) {
                    continue;
                }
            } catch (Exception e) {
                flag = false;
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
            if (flag) {
                target.setStatus(ProjectStatusEnum.DRAFT.getKey());
                saveAndUpdateDataLandLevel(target);
                successCount++;
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }


    public List<DataLandLevelVo> getByProcessInsId(String processInsId) {
        List<DataLandLevelVo> dataLandLevelVoList = Lists.newArrayList();
        List<DataLandLevel> dataLandLevelList = dataLandLevelDao.getByProcessInsIdList(processInsId);
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(dataLandLevelList)) {
            dataLandLevelList.forEach(dataLandLevel -> dataLandLevelVoList.add(getDataLandLevelVo(dataLandLevel, null)));
        }
        return dataLandLevelVoList;
    }

    /**
     * 审批提交
     *
     * @param approvalModelDto
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class})
    public void comeInLandLevelApprovalSubmit(ApprovalModelDto approvalModelDto) throws Exception {
        processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
    }


    /**
     * 返回修改
     *
     * @param approvalModelDto
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class})
    public void comeInLandLevelEditSubmit(ApprovalModelDto approvalModelDto) throws Exception {
        processControllerComponent.processSubmitLoopTaskNodeArg(publicService.getEditApprovalModel(approvalModelDto), false);
    }

    /**
     * 发起流程
     */
    @Transactional(rollbackFor = {Exception.class})
    public void submitProcess() throws Exception {
        DataLandLevel where = new DataLandLevel();
        where.setStatus(ProjectStatusEnum.DRAFT.getKey());
        where.setCreator(commonService.thisUserAccount());
        List<DataLandLevel> dataLandLevelList = getDataLandLevelList(where);
        if (CollectionUtils.isEmpty(dataLandLevelList))
            throw new BusinessException("请填写相关基准地价信息");
        ProcessUserDto processUserDto = null;
        ProcessInfo processInfo = new ProcessInfo();
        String areaFullName = erpAreaService.getAreaFullName(dataLandLevelList.get(0).getProvince(), dataLandLevelList.get(0).getCity(), dataLandLevelList.get(0).getDistrict());
        processInfo.setFolio(String.format("%s%s基准地价", areaFullName, dataLandLevelList.get(0).getTownShipName()));//流程描述
        final String boxName = baseParameterService.getParameterValues(BaseParameterEnum.DATA_LAND_LEVEL_APPLY_KEY.getParameterKey());
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(DataLandLevel.class));
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getName());
        processInfo.setProcessEventExecutor(DataLandLevelEvent.class);
        processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, processControllerComponent.getThisUser(), false);
        if (CollectionUtils.isNotEmpty(dataLandLevelList)) {
            for (DataLandLevel dataLandLevel : dataLandLevelList) {
                dataLandLevel.setProcessInsId(processUserDto.getProcessInsId());
                dataLandLevel.setStatus(ProjectStatusEnum.RUNING.getKey());
                saveAndUpdateDataLandLevel(dataLandLevel);
            }
        }
    }

    public void saveAndUpdateDataLandLevel(DataLandLevel dataLandLevel) {
        if (dataLandLevel.getId() == null) {
            dataLandLevel.setCreator(commonService.thisUserAccount());
            dataLandLevel.setStatus(ProjectStatusEnum.DRAFT.getKey());
            dataLandLevelDao.addDataLandLevel(dataLandLevel);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DataLandLevel.class), dataLandLevel.getId());
        } else {
            DataLandLevel landLevel = getDataLandLevelById(dataLandLevel.getId()) ;
            if (StringUtils.isBlank(dataLandLevel.getCreator())) {
                dataLandLevel.setCreator(landLevel.getCreator());
            }
            if (dataLandLevel.getGmtCreated() == null){
                dataLandLevel.setGmtCreated(landLevel.getGmtCreated());
            }
            updateDataLandLevel(dataLandLevel ,true);
        }
    }

    public boolean updateDataLandLevel(DataLandLevel oo, boolean updateNull){
        return dataLandLevelDao.updateDataLandLevel(oo, updateNull) ;
    }

    public List<DataLandLevel> getByIds(String ids) {
        return dataLandLevelDao.getByIds(FormatUtils.transformString2Integer(ids));
    }

    public DataLandLevel getDataLandLevelById(Integer id) {
        return dataLandLevelDao.getDataLandLevelById(id);
    }

    /**
     * 设置过滤条件的草稿会自动过滤
     *
     * @param query
     * @return
     */
    public List<DataLandLevel> getDataLandLevelList(DataLandLevel query) {
        if (StringUtils.isNotEmpty(query.getProcessInsId())) {
            DataLandLevel where = new DataLandLevel();
            where.setProcessInsId(query.getProcessInsId());
            return dataLandLevelDao.getDataLandLevelList(where);
        } else {
            return dataLandLevelDao.getDataLandLevelList(query);
        }
    }

    public BootstrapTableVo getDataLandLevelListVos(DataLandLevel dataLandLevel) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLandLevel> dataLandLevels = getDataLandLevelList(dataLandLevel);
        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableName(FormatUtils.entityNameConvertToTableName(DataLandLevel.class));
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(dataLandLevels, o -> o.getId()), where);
        List<DataLandLevelVo> vos = LangUtils.transform(dataLandLevels, o -> getDataLandLevelVo(o, attachmentDtos));
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public void removeDataLandLevel(DataLandLevel dataLandLevel) {
        dataLandLevelDao.removeDataLandLevel(dataLandLevel);
    }

    public DataLandLevelVo getDataLandLevelVo(DataLandLevel dataLandLevel, List<SysAttachmentDto> attachmentDtos) {
        DataLandLevelVo vo = new DataLandLevelVo();
        BeanUtils.copyProperties(dataLandLevel, vo);
        if (StringUtils.isNotBlank(dataLandLevel.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(dataLandLevel.getProvince()));//省
        }
        if (StringUtils.isNotBlank(dataLandLevel.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(dataLandLevel.getCity()));//市或者县
        }
        if (StringUtils.isNotBlank(dataLandLevel.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(dataLandLevel.getDistrict()));//县
        }

        if (!CollectionUtils.isEmpty(attachmentDtos)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (SysAttachmentDto attachmentDto : attachmentDtos) {
                if (attachmentDto.getTableId().equals(dataLandLevel.getId())) {
                    stringBuilder.append(baseAttachmentService.getViewHtml(attachmentDto));
                }
            }
            vo.setFileViewName(stringBuilder.toString());
        }
        vo.setStatusName(ProjectStatusEnum.getNameByKey(dataLandLevel.getStatus()));
        vo.setCreatorName(publicService.getUserNameByAccount(dataLandLevel.getCreator()));
        vo.setLandRightTypeName(baseDataDicService.getNameById(dataLandLevel.getLandRightType()));
        return vo;
    }
}
