package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDao;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

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


    public DataLandLevelVo getByProcessInsId(String processInsId){
        List<DataLandLevel> dataLandLevelList = dataLandLevelDao.getByProcessInsIdList(processInsId) ;
        if (org.apache.commons.collections.CollectionUtils.isNotEmpty(dataLandLevelList)){
            return getDataLandLevelVo(dataLandLevelList.stream().findFirst().get(),null);
        }else {
            return new DataLandLevelVo();
        }
    }

    /**
     * 审批提交
     * @param approvalModelDto
     * @param blockName
     * @param writeBackBlockFlag
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class})
    public void comeInLandLevelApprovalSubmit(ApprovalModelDto approvalModelDto, String blockName, Boolean writeBackBlockFlag)throws Exception{
        DataLandLevelVo dataLandLevelVo = getByProcessInsId(approvalModelDto.getProcessInsId()) ;
        if (dataLandLevelVo.getId() != null){
            dataLandLevelVo.setStatus(ProjectStatusEnum.FINISH.getKey());
            saveAndUpdateDataLandLevel(dataLandLevelVo) ;
        }
        processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
    }


    /**
     * 返回修改
     * @param approvalModelDto
     * @param formData
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class})
    public void comeInLandLevelEditSubmit(ApprovalModelDto approvalModelDto , String formData)throws Exception{
        DataLandLevel dataLandLevel = JSONObject.parseObject(formData,DataLandLevel.class) ;
        saveAndUpdateDataLandLevel(dataLandLevel);
        processControllerComponent.processSubmitLoopTaskNodeArg(publicService.getEditApprovalModel(approvalModelDto), false);

    }

    /**
     * 发起流程
     * @param dataLandLevel
     */
    @Transactional(rollbackFor = {Exception.class})
    public void submit(DataLandLevel dataLandLevel)throws Exception{
        saveAndUpdateDataLandLevel(dataLandLevel) ;
        ProcessUserDto processUserDto = null;
        ProcessInfo processInfo = new ProcessInfo();
        //流程描述
        processInfo.setFolio(String.join("","土地级别描述申请 时间:", DateUtils.format(new Date(),DateUtils.DATETIME_PATTERN)));
        final String boxName = baseParameterService.getParameterValues(BaseParameterEnum.DATA_LAND_LEVEL_APPLY_KEY.getParameterKey());
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(DataLandLevel.class));
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
//        processInfo.setProcessEventExecutor(DataLandLevelEvent.class);
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getKey());
//        processInfo.setProcessEventExecutorName(DataLandLevelEvent.class.getSimpleName());
        processInfo.setTableId(dataLandLevel.getId());
        processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, processControllerComponent.getThisUser(), false);
        dataLandLevel.setProcessInsId(processUserDto.getProcessInsId());
        dataLandLevel.setStatus(ProjectStatusEnum.DRAFT.getKey());
        saveAndUpdateDataLandLevel(dataLandLevel) ;
    }

    public void saveAndUpdateDataLandLevel(DataLandLevel dataLandLevel) {
        if (dataLandLevel.getId() == null) {
            dataLandLevel.setCreator(commonService.thisUserAccount());
            dataLandLevelDao.addDataLandLevel(dataLandLevel);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DataLandLevel.class), dataLandLevel.getId());
        } else {
            dataLandLevelDao.updateDataLandLevel(dataLandLevel);
        }
    }

    public DataLandLevel getDataLandLevelById(Integer id) {
        return dataLandLevelDao.getDataLandLevelById(id);
    }

    /**
     * 设置过滤条件的草稿会自动过滤
     * @param query
     * @return
     */
    public List<DataLandLevel> getDataLandLevelList(DataLandLevel query) {
        return dataLandLevelDao.getDataLandLevelList(query);
    }

    public BootstrapTableVo getDataLandLevelListVos(DataLandLevel dataLandLevel) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLandLevel> dataLandLevels = getDataLandLevelList(dataLandLevel);
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentListByTableName(FormatUtils.entityNameConvertToTableName(DataLandLevel.class), LangUtils.transform(dataLandLevels, o -> o.getId()));
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
        vo.setLandRightTypeName(baseDataDicService.getNameById(dataLandLevel.getLandRightType()));
        return vo;
    }
}
