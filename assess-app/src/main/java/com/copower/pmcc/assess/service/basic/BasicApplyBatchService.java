package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomBasicAppBatchMapper;
import com.copower.pmcc.assess.dal.basis.dao.basic.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.output.basic.BasicApplyBatchVo;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.ResidueRatioService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.basic.BasicApplyBatchEvent;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
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
import com.copower.pmcc.erp.constant.CacheConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BasicApplyBatchService {
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchDao basicApplyBatchDao;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicBuildingDao basicBuildingDao;
    @Autowired
    private BasicUnitDao basicUnitDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicEstateDao basicEstateDao;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Lazy
    @Autowired
    private CustomBasicAppBatchMapper customBasicAppBatchMapper;
    @Autowired
    private PublicBasicService publicBasicService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<ZtreeDto> getZtreeDto(Integer basicApplyBatchId) throws Exception {
        List<ZtreeDto> treeDtos = new ArrayList<>();
        if (basicApplyBatchId == null) return treeDtos;
        BasicApplyBatch basicApplyBatch = getBasicApplyBatchById(basicApplyBatchId);
        if (basicApplyBatch == null) return treeDtos;
        List<BasicApplyBatchDetail> basicApplyBatchDetails = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(basicApplyBatch.getId());
        if (CollectionUtils.isEmpty(basicApplyBatchDetails)) return treeDtos;
        for (BasicApplyBatchDetail item : basicApplyBatchDetails) {
            ZtreeDto ztreeDto = new ZtreeDto();
            ztreeDto.setId(item.getId());
            ztreeDto.setName(item.getName());
            ztreeDto.setTextName(item.getDisplayName());
            if (basicApplyBatch.getPlanDetailsId() == null) {
                ztreeDto.setDisplayName(item.getDisplayName());
            } else {
                ztreeDto.setDisplayName(String.format("%s(%s)", item.getDisplayName(), publicService.getUserNameByAccount(item.getExecutor())));
            }
            ztreeDto.setPid(item.getPid());
            ztreeDto.setTableName(item.getTableName());
            if (item.getBisFromCase() == Boolean.FALSE) {
                ztreeDto.setTableId(item.getTableId());
                if (basicApplyBatch.getCaseEstateId() != null && basicApplyBatch.getCaseEstateId() > 0) {
                    if (item.getUpgradeTableId() != null && item.getUpgradeTableId() > 0) {
                        ztreeDto.setDisplayName(String.format("%s(升级)", item.getDisplayName()));
                    } else {
                        ztreeDto.setDisplayName(String.format("%s(新增)", item.getDisplayName()));
                    }
                }
            }
            ztreeDto.setType(item.getType());
            ztreeDto.setCreator(item.getCreator());
            ztreeDto.setExecutor(item.getExecutor());
            ztreeDto.setCreatorName(publicService.getUserNameByAccount(item.getCreator()));
            ztreeDto.setBisStructure(item.getBisStructure());
            ztreeDto.setApplyBatchId(item.getApplyBatchId());
            ztreeDto.setDeclareRecordId(item.getDeclareRecordId());
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(item.getDeclareRecordId());
            if (declareRecord != null) {
                ztreeDto.setDeclareRecordName(declareRecord.getName());
            }
            treeDtos.add(ztreeDto);
        }
        return treeDtos;
    }

    public BasicApplyBatch getBasicApplyBatchByProcessInsId(String processInsId) {
        BasicApplyBatch basicApplyBatch = new BasicApplyBatch();
        basicApplyBatch.setProcessInsId(processInsId);
        List<BasicApplyBatch> basicApplyBatches = basicApplyBatchDao.getInfoList(basicApplyBatch);
        if (CollectionUtils.isNotEmpty(basicApplyBatches)) {
            return getBasicApplyBatchVo(basicApplyBatches.get(0));
        }
        return null;
    }

    public List<ZtreeDto> getCacheEstateZtreeById(Integer estateId) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_BASIC_BATCH_APPLY_ESTATE_ID, String.valueOf(estateId));
        try {
            List<ZtreeDto> sysProjectClassifys = LangUtils.listCache(rdsKey, estateId, ZtreeDto.class, input -> getEstateZtreeById(estateId));
            return sysProjectClassifys;
        } catch (Exception e) {
            return getEstateZtreeById(estateId);
        }

    }

    /**
     * 获取楼盘树形结构
     *
     * @param estateId
     * @return
     */
    public List<ZtreeDto> getEstateZtreeById(Integer estateId) {
        List<ZtreeDto> ztreeDtoList = Lists.newArrayList();
        ZtreeDto ztreeDtoEstate = new ZtreeDto();
        BasicEstate basicEstate = basicEstateService.getBasicEstateById(estateId);
        if (basicEstate == null) return ztreeDtoList;
        ztreeDtoEstate.setPid(0);
        ztreeDtoEstate.setId(estateId);
        ztreeDtoEstate.setName(basicEstate.getName());
        ztreeDtoEstate.setDisplayName(ztreeDtoEstate.getName());
        ztreeDtoEstate.setTableName(BasicFormClassifyEnum.ESTATE.getTableName());
        ztreeDtoEstate.setTableId(estateId);
        ztreeDtoEstate.setType(BasicFormClassifyEnum.ESTATE.getKey());
        ztreeDtoList.add(ztreeDtoEstate);

        BasicBuilding whereBasicBuilding = new BasicBuilding();
        whereBasicBuilding.setEstateId(estateId);
        whereBasicBuilding.setBisCase(true);
        List<BasicBuilding> buildingList = basicBuildingService.getBasicBuildingList(whereBasicBuilding);
        if (CollectionUtils.isEmpty(buildingList)) return ztreeDtoList;
        for (BasicBuilding basicBuilding : buildingList) {
            ZtreeDto ztreeDtoBuilding = new ZtreeDto();
            ztreeDtoBuilding.setPid(ztreeDtoEstate.getId());
            ztreeDtoBuilding.setId(basicBuilding.getId());
            ztreeDtoBuilding.setName(basicBuilding.getBuildingNumber());
            ztreeDtoBuilding.setDisplayName(ztreeDtoBuilding.getName());
            ztreeDtoBuilding.setTableName(BasicFormClassifyEnum.BUILDING.getTableName());
            ztreeDtoBuilding.setTableId(basicBuilding.getId());
            ztreeDtoBuilding.setType(BasicFormClassifyEnum.BUILDING.getKey());
            ztreeDtoList.add(ztreeDtoBuilding);

            BasicUnit whereBasicUnit = new BasicUnit();
            whereBasicUnit.setBuildingId(basicBuilding.getId());
            whereBasicUnit.setBisCase(true);
            List<BasicUnit> basicUnitList = basicUnitService.getBasicUnitList(whereBasicUnit);
            if (CollectionUtils.isEmpty(basicUnitList)) continue;
            for (BasicUnit basicUnit : basicUnitList) {
                ZtreeDto ztreeDtoUnit = new ZtreeDto();
                ztreeDtoUnit.setPid(ztreeDtoBuilding.getId());
                ztreeDtoUnit.setId(basicUnit.getId());
                ztreeDtoUnit.setName(basicUnit.getUnitNumber());
                ztreeDtoUnit.setDisplayName(ztreeDtoUnit.getName());
                ztreeDtoUnit.setTableName(BasicFormClassifyEnum.UNIT.getTableName());
                ztreeDtoUnit.setTableId(basicUnit.getId());
                ztreeDtoUnit.setType(BasicFormClassifyEnum.UNIT.getKey());
                ztreeDtoList.add(ztreeDtoUnit);

                BasicHouse whereBasicHouse = new BasicHouse();
                whereBasicHouse.setUnitId(basicUnit.getId());
                whereBasicHouse.setBisCase(true);
                List<BasicHouse> basicHouseList = basicHouseService.getBasicHouseList(whereBasicHouse);
                if (CollectionUtils.isEmpty(basicHouseList)) continue;
                for (BasicHouse basicHouse : basicHouseList) {
                    ZtreeDto ztreeDtoHouse = new ZtreeDto();
                    ztreeDtoHouse.setPid(ztreeDtoUnit.getId());
                    ztreeDtoHouse.setId(basicHouse.getId());
                    ztreeDtoHouse.setName(basicHouse.getHouseNumber());
                    ztreeDtoHouse.setDisplayName(ztreeDtoHouse.getName());
                    ztreeDtoHouse.setTableName(BasicFormClassifyEnum.HOUSE.getTableName());
                    ztreeDtoHouse.setTableId(basicHouse.getId());
                    ztreeDtoHouse.setType(BasicFormClassifyEnum.HOUSE.getKey());
                    ztreeDtoList.add(ztreeDtoHouse);
                }
            }
        }

        return ztreeDtoList;
    }

    /**
     * 记录批量申请
     *
     * @param province
     * @param city
     * @param estateName
     * @return
     */
    public Integer recordBatchApply(String province, String city, String estateName) throws Exception {
        BasicApplyBatch basicApplyBatch = new BasicApplyBatch();
        basicApplyBatch.setDraftFlag(true);
        basicApplyBatch.setStatus(ProjectStatusEnum.DRAFT.getKey());
        basicApplyBatch.setEstateName(estateName);
        basicApplyBatch.setBisDelete(false);
        Boolean existEstateCase = basicEstateService.isExistEstateCase(province, city, estateName);
        if (existEstateCase) {
            BasicEstate latestVersionEstate = basicEstateDao.getLatestVersionEstate(province, city, estateName);
            if (latestVersionEstate != null) {
                basicApplyBatch.setBisCase(true);
                basicApplyBatch.setClassify(latestVersionEstate.getClassify());
                basicApplyBatch.setType(latestVersionEstate.getType());
                basicApplyBatch.setCaseEstateId(latestVersionEstate.getId());
            }
        }
        saveBasicApplyBatch(basicApplyBatch);
        return basicApplyBatch.getId();
    }

    public List<BaseDataDic> getFormClassifyList() {
        List<BaseDataDic> dataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY);
        List<BaseDataDic> resultList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(dataDicList)) return resultList;
        resultList = LangUtils.filter(dataDicList, o -> {
            return AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_MULTIPLE.equals(o.getFieldName())
                    || AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND_ONLY.equals(o.getFieldName())
                    || AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND.equals(o.getFieldName());
        });

        return resultList;
    }


    public BasicApplyBatch getSingleData(BasicApplyBatch basicApplyBatch) {
        List<BasicApplyBatch> infoList = basicApplyBatchDao.getInfoList(basicApplyBatch);
        if (CollectionUtils.isNotEmpty(infoList)) return infoList.get(0);
        return null;
    }


    public BasicApplyBatch getBasicApplyBatchById(Integer id) {
        return basicApplyBatchDao.getBasicApplyBatchById(id);
    }

    //删除
    public void deleteBasicBatchApply(Integer id) {
        basicApplyBatchDao.deleteInfo(id);
    }

    //获取草稿数据
    public BootstrapTableVo getBootstrapTableVo(String estateName) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        String creator = commonService.thisUserAccount();
        List<BasicApplyBatch> basicAppBatchDraftList = customBasicAppBatchMapper.getCustomDraftList(estateName, creator);
        List<BasicApplyBatchVo> voList = LangUtils.transform(basicAppBatchDraftList, o -> getBasicApplyBatchVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(voList) ? new ArrayList<BasicApplyBatchVo>() : voList);
        return vo;
    }


    /**
     * 通过estateId获取
     *
     * @param estateId
     * @return
     */
    public BasicApplyBatch getBasicApplyBatchByEstateId(Integer estateId) {
        BasicApplyBatch basicApplyBatch = new BasicApplyBatch();
        basicApplyBatch.setEstateId(estateId);
        basicApplyBatch.setBisCase(false);
        List<BasicApplyBatch> basicApplyBatches = basicApplyBatchDao.getInfoList(basicApplyBatch);
        if (CollectionUtils.isNotEmpty(basicApplyBatches)) {
            return basicApplyBatches.get(0);
        }
        return null;
    }

    public BasicApplyBatch getBasicApplyBatchByPlanDetailsId(Integer planDetailsId) {
        BasicApplyBatch basicApplyBatch = new BasicApplyBatch();
        basicApplyBatch.setPlanDetailsId(planDetailsId);
        List<BasicApplyBatch> basicApplyBatches = basicApplyBatchDao.getInfoList(basicApplyBatch);
        if (CollectionUtils.isNotEmpty(basicApplyBatches)) {
            return basicApplyBatches.get(0);
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchByPlanDetailsId(Integer planDetailsId) throws Exception {
        if (planDetailsId == null) return;
        BasicApplyBatch applyBatch = getBasicApplyBatchByPlanDetailsId(planDetailsId);
        List<BasicApply> basicApplyList = basicApplyService.getBasicApplyListByPlanDetailsId(planDetailsId);
        if (CollectionUtils.isNotEmpty(basicApplyList)) {
            basicApplyList.forEach(o -> {
                try {
                    basicApplyService.deleteBasicApplyById(o.getId());
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            });
        }
        if (applyBatch != null) {
            deleteBatchAllById(applyBatch.getId());
            basicApplyBatchDao.deleteInfo(applyBatch.getId());
        }
    }

    /**
     * 删除批量树结构信息
     *
     * @param basicApplyBatchId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchAllById(Integer basicApplyBatchId) throws Exception {
        if (basicApplyBatchId == null) return;
        List<BasicApplyBatchDetail> batchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(basicApplyBatchId);
        if (CollectionUtils.isNotEmpty(batchDetailList)) {
            for (BasicApplyBatchDetail basicApplyBatchDetail : batchDetailList) {
                basicApplyBatchDetailService.deleteBasicApplyBatchDetail(basicApplyBatchDetail.getId());
            }
        }
        basicApplyBatchDao.deleteInfo(basicApplyBatchId);
    }

    public void addBasicApplyBatch(BasicApplyBatch applyBatch) {
        applyBatch.setCreator(commonService.thisUserAccount());
        basicApplyBatchDao.addBasicApplyBatch(applyBatch);
    }

    /**
     * 初始化
     *
     * @param basicApplyBatch
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicApplyBatch initBasicApplyBatchInfo(BasicApplyBatch basicApplyBatch) throws Exception {
        if (basicApplyBatch == null) return null;
        //1.根据不同情况初始化不同的信息结构 2.初始化之前需先将原初始化信息删除
        List<BasicApplyBatchDetail> batchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(basicApplyBatch.getId());
        if (CollectionUtils.isNotEmpty(batchDetailList)) {
            for (BasicApplyBatchDetail basicApplyBatchDetail : batchDetailList) {
                basicApplyBatchDetailService.deleteBasicApplyBatchDetail(basicApplyBatchDetail.getId());
            }
        }

        if (basicApplyBatch.getClassify() == null) return basicApplyBatch;
        BasicFormClassifyEnum formClassifyEnum = BasicFormClassifyEnum.ESTATE;
        BaseDataDic classifyDataDic = baseDataDicService.getCacheDataDicById(basicApplyBatch.getClassify());
        //楼盘
        BasicEstate basicEstate = new BasicEstate();
        //申报表代入的信息
        DeclareRecord declareRecord = null;
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(basicApplyBatch.getPlanDetailsId());
        if (projectPlanDetails != null && projectPlanDetails.getDeclareRecordId() != null) {
            basicApplyBatch.setProjectId(projectPlanDetails.getProjectId());
            declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        }
        if (declareRecord != null) {
            basicEstate.setProvince(declareRecord.getProvince());
            basicEstate.setCity(declareRecord.getCity());
            basicEstate.setDistrict(declareRecord.getDistrict());
            basicEstate.setStreetNumber(declareRecord.getStreetNumber());
            basicEstate.setAttachNumber(declareRecord.getAttachedNumber());
        }
        basicEstateService.saveAndUpdate(basicEstate, false);
        BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
        if (declareRecord != null) {
            basicEstateLandState.setLandUseType(declareRecord.getLandCertUse());
            basicEstateLandState.setLandUseCategory(declareRecord.getLandCertUseCategory());
        }
        basicEstateLandState.setEstateId(basicEstate.getId());
        basicEstateLandState.setCreator(commonService.thisUserAccount());
        basicEstateLandStateService.saveAndUpdateBasicEstateLandState(basicEstateLandState, false);
        if (StringUtils.isBlank(basicApplyBatch.getEstateName())) {
            String estateName = "楼盘信息";
            if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND.equals(classifyDataDic.getFieldName())) {
                estateName = "地块信息";
                formClassifyEnum=BasicFormClassifyEnum.ESTATE_LAND_INCLUD;
            }
            if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND_ONLY.equals(classifyDataDic.getFieldName())) {
                estateName = "地块信息";
                formClassifyEnum=BasicFormClassifyEnum.ESTATE_LAND;
            }
            basicApplyBatch.setEstateName(estateName);
        }
        basicApplyBatch.setEstateId(basicEstate.getId());
        saveBasicApplyBatch(basicApplyBatch);

        BasicApplyBatchDetail estateApplyBatchDetail = new BasicApplyBatchDetail();
        estateApplyBatchDetail.setPid(0);
        estateApplyBatchDetail.setApplyBatchId(basicApplyBatch.getId());
        estateApplyBatchDetail.setTableName(formClassifyEnum.getTableName());
        estateApplyBatchDetail.setTableId(basicEstate.getId());
        estateApplyBatchDetail.setName(basicApplyBatch.getEstateName());
        estateApplyBatchDetail.setDisplayName(basicApplyBatch.getEstateName());
        estateApplyBatchDetail.setExecutor(commonService.thisUserAccount());
        estateApplyBatchDetail.setType(formClassifyEnum.getKey());
        basicApplyBatchDetailService.saveBasicApplyBatchDetail(estateApplyBatchDetail);

        if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND_ONLY.equals(classifyDataDic.getFieldName())) {
            BasicApply basicApply = new BasicApply();
            basicApply.setBasicEstateId(basicEstate.getId());
            if(declareRecord!=null){
                basicApply.setDeclareRecordId(declareRecord.getId());
            }
            basicApply.setPlanDetailsId(basicApplyBatch.getPlanDetailsId());
            basicApplyService.saveBasicApply(basicApply);
        }
        return basicApplyBatch;
    }

    //保存
    public void saveBasicApplyBatch(BasicApplyBatch basicApplyBatch) throws Exception {
        if (basicApplyBatch.getId() != null && basicApplyBatch.getId() != 0) {
            basicApplyBatchDao.updateInfo(basicApplyBatch);
        } else {
            basicApplyBatch.setCreator(commonService.thisUserAccount());
            basicApplyBatchDao.addBasicApplyBatch(basicApplyBatch);
        }
        BasicEstate basicEstate = basicEstateService.getBasicEstateById(basicApplyBatch.getEstateId());
        if (basicEstate != null) {
            basicEstate.setClassify(basicApplyBatch.getClassify());
            basicEstate.setType(basicApplyBatch.getType());
            basicEstateService.saveAndUpdate(basicEstate, false);
        }
    }

    /**
     * 保存数据
     *
     * @param formData
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveDraft(String formData, String formClassify, Integer planDetailsId) throws Exception {
        BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByTableName(BasicFormClassifyEnum.getEnumByKey(formClassify).getTableName());
        entityAbstract.saveAndUpdateByFormData(formData, planDetailsId);
    }

    /**
     * 返回修改 提交
     *
     * @param approvalModelDto
     * @throws Exception
     */
    public void processEditSubmit(ApprovalModelDto approvalModelDto) throws Exception {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(publicService.getEditApprovalModel(approvalModelDto), false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }


    /**
     * 流程发起
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ProcessUserDto processStartSubmit(Integer id) throws Exception {
        BasicApplyBatch applyBatch = basicApplyBatchDao.getBasicApplyBatchById(id);
        ProcessUserDto processUserDto = null;
        ProcessInfo processInfo = new ProcessInfo();
        //流程描述
        processInfo.setFolio(String.format("%s%s", "批量申请_", applyBatch.getEstateName()));
        final String boxName = baseParameterService.getParameterValues(BaseParameterEnum.CASE_BASE_INFO_BATCH_APPLY_KEY.getParameterKey());
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(BasicApplyBatch.class));
        processInfo.setBoxId(boxReDto.getId());
        processInfo.setProcessName(boxReDto.getProcessName());
        processInfo.setGroupName(boxReDto.getGroupName());
        processInfo.setProcessEventExecutor(BasicApplyBatchEvent.class);
        processInfo.setRemarks(ProjectStatusEnum.STARTAPPLY.getKey());
        processInfo.setProcessEventExecutorName(BasicApplyBatchEvent.class.getSimpleName());
        processInfo.setTableId(id);
        try {
            processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, processControllerComponent.getThisUser(), false);
            applyBatch.setDraftFlag(false);
            applyBatch.setProcessInsId(processUserDto.getProcessInsId());
            applyBatch.setStatus(ProjectStatusEnum.RUNING.getKey());
            basicApplyBatchDao.updateInfo(applyBatch);
        } catch (Exception e) {
            logger.error(String.format("流程发起失败: %s", e.getMessage()), e);
            throw e;
        }
        return processUserDto;
    }

    public BasicApplyBatchVo getBasicApplyBatchVo(BasicApplyBatch basicApplyBatch) {
        if (basicApplyBatch == null) {
            return null;
        }
        BasicApplyBatchVo vo = new BasicApplyBatchVo();
        BeanUtils.copyProperties(basicApplyBatch, vo);
        if (StringUtils.isNotBlank(basicApplyBatch.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(basicApplyBatch.getProvince()));
        }
        if (StringUtils.isNotBlank(basicApplyBatch.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(basicApplyBatch.getCity()));
        }

        if (basicApplyBatch.getType() != null) {
            for (BasicApplyTypeEnum typeEnum : BasicApplyTypeEnum.values()) {
                if (basicApplyBatch.getType().intValue() == typeEnum.getId().intValue()) {
                    vo.setTypeName(typeEnum.getName());
                    break;
                }
            }
        }
        vo.setFullName(basicApplyBatch.getEstateName());
        return vo;
    }

    /**
     * 审批
     *
     * @param approvalModelDto
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void processApprovalSubmit(ApprovalModelDto approvalModelDto) throws Exception {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 粘贴调查信息
     *
     * @param sourceBatchDetailId
     * @param targetBatchDetailId
     * @throws Exception
     */
    public void pasteExamineInfo(Integer sourceBatchDetailId, Integer targetBatchDetailId) throws Exception {
        //被复制数据
        BasicApplyBatchDetail sourceBasicApplyBatchDetail = basicApplyBatchDetailDao.getInfoById(sourceBatchDetailId);
        BasicApplyBatchDetail targetBasicApplyBatchDetail = basicApplyBatchDetailDao.getInfoById(targetBatchDetailId);

        BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByTableName(sourceBasicApplyBatchDetail.getTableName());
        entityAbstract.copyBasicEntity(sourceBasicApplyBatchDetail.getTableId(), targetBasicApplyBatchDetail.getTableId(), true);
    }

    /**
     * 深复制
     *
     * @param sourceBatchDetailId
     * @throws Exception
     */
    public void deepCopy(Integer sourceBatchDetailId) throws Exception {
        BasicApplyBatchDetail sourceBasicApplyBatchDetail = basicApplyBatchDetailDao.getInfoById(sourceBatchDetailId);
        deepCopyRecursion(sourceBasicApplyBatchDetail, sourceBasicApplyBatchDetail.getPid());
    }

    /**
     * 递归复制
     *
     * @param basicApplyBatchDetail
     * @throws Exception
     */
    public void deepCopyRecursion(BasicApplyBatchDetail basicApplyBatchDetail, Integer pid) throws Exception {
        if (basicApplyBatchDetail == null) return;
        //1.先复制本身 2.检查是否有下级，如果有则继续复制下级
        BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByTableName(basicApplyBatchDetail.getTableName());
        Object object = entityAbstract.copyBasicEntity(basicApplyBatchDetail.getTableId(), null, true);
        BasicApplyBatchDetail newBatchDetail = new BasicApplyBatchDetail();
        if (object != null) {
            BeanUtils.copyProperties(basicApplyBatchDetail, newBatchDetail, BaseConstant.ASSESS_IGNORE_ARRAY);
            newBatchDetail.setPid(pid);
            newBatchDetail.setTableId((Integer) entityAbstract.getProperty(object, "id"));
            newBatchDetail.setExecutor(commonService.thisUserAccount());
            newBatchDetail.setCreator(commonService.thisUserAccount());
            basicApplyBatchDetailDao.addInfo(newBatchDetail);
        }
        List<BasicApplyBatchDetail> applyBatchDetails = basicApplyBatchDetailService.getBasicApplyBatchDetailByPid(basicApplyBatchDetail.getId(), basicApplyBatchDetail.getApplyBatchId());
        if (CollectionUtils.isEmpty(applyBatchDetails)) return;
        for (BasicApplyBatchDetail item : applyBatchDetails) {
            deepCopyRecursion(item, newBatchDetail.getId());
        }
    }

    /**
     * 初始化支撑新增获取升级案例结构
     *
     * @param basicApplyBatchId
     * @param nodesJson
     */
    public BasicApplyBatchDetail initCaseEstateZtree(Integer basicApplyBatchId, String nodesJson) {
        //1.根据选择的数据，找出需要支撑该数据的结构，再到现有结构数据表中查看是否已有相关结构数据
        //如果没有则需将结构数据添加进去，处理结构需从上到下
        List<BasicApplyBatchDetail> applyBatchDetails = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(basicApplyBatchId);
        List<ZtreeDto> needList = JSON.parseArray(nodesJson, ZtreeDto.class);
        if (CollectionUtils.isEmpty(needList)) return null;
        Integer pid = 0;
        BasicApplyBatchDetail lastNode = null;
        for (int i = needList.size() - 1; i >= 0; i--) {
            BasicApplyBatchDetail containBatchDetail = listContainCaseBatchDetail(applyBatchDetails, needList.get(i));
            if (containBatchDetail != null) {
                pid = containBatchDetail.getId();
                lastNode = containBatchDetail;
                continue;
            }
            BasicApplyBatchDetail batchDetail = new BasicApplyBatchDetail();
            batchDetail.setPid(pid);
            batchDetail.setApplyBatchId(basicApplyBatchId);
            batchDetail.setName(needList.get(i).getName());
            batchDetail.setDisplayName(needList.get(i).getDisplayName());
            batchDetail.setTableId(needList.get(i).getTableId());
            batchDetail.setTableName(needList.get(i).getTableName());
            batchDetail.setBisFromCase(true);
            batchDetail.setExecutor(commonService.thisUserAccount());
            batchDetail.setCreator(commonService.thisUserAccount());
            basicApplyBatchDetailDao.addInfo(batchDetail);
            pid = batchDetail.getId();
            lastNode = batchDetail;
        }
        return lastNode;
    }

    //检测数据在list是否已存在
    private BasicApplyBatchDetail listContainCaseBatchDetail(List<BasicApplyBatchDetail> list, ZtreeDto ztreeDto) {
        if (CollectionUtils.isEmpty(list) || ztreeDto == null) return null;
        for (BasicApplyBatchDetail batchDetail : list) {
            Boolean tableNameSame = StringUtils.isNotBlank(batchDetail.getTableName())
                    && StringUtils.isNotBlank(ztreeDto.getTableName())
                    && batchDetail.getTableName().equalsIgnoreCase(ztreeDto.getTableName());

            Boolean tableIdSame = batchDetail.getTableId() != null && ztreeDto.getTableId() != null
                    && batchDetail.getTableId().equals(ztreeDto.getTableId());

            Boolean upgradeTableIdSame = batchDetail.getUpgradeTableId() != null && ztreeDto.getTableId() != null
                    && batchDetail.getUpgradeTableId().equals(ztreeDto.getTableId());

            if (tableNameSame && (tableIdSame || upgradeTableIdSame)) return batchDetail;
        }
        return null;
    }

    /**
     * 升级案例数据
     *
     * @param applyBatchId
     * @param pid
     * @param nodeJson
     * @throws Exception
     */
    public BasicApplyBatchDetail upgradeCase(Integer applyBatchId, Integer pid, String nodeJson) throws Exception {
        //1.先将案例数据拷贝一份，用于升级的基础编辑数据
        //2.将拷贝的数据挂到申请的结构下
        BasicApplyBatchDetail applyBatchDetail = new BasicApplyBatchDetail();
        applyBatchDetail.setPid(pid);
        applyBatchDetail.setApplyBatchId(applyBatchId);
        applyBatchDetail.setCreator(commonService.thisUserAccount());
        applyBatchDetail.setExecutor(commonService.thisUserAccount());
        ZtreeDto ztreeDto = JSON.parseObject(nodeJson, ZtreeDto.class);
        ArrayList<String> ignoreList = Lists.newArrayList("bisCase");
        if (ztreeDto != null) {
            applyBatchDetail.setUpgradeTableId(ztreeDto.getTableId());
            BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByTableName(ztreeDto.getTableName());
            Object o = entityAbstract.copyBasicEntityIgnore(ztreeDto.getTableId(), null, true, ignoreList);
            Object name = entityAbstract.getProperty(o, "name");
            if (o instanceof BasicBuilding)
                name = entityAbstract.getProperty(o, "buildingNumber");
            if (o instanceof BasicUnit)
                name = entityAbstract.getProperty(o, "unitNumber");
            if (o instanceof BasicHouse)
                name = entityAbstract.getProperty(o, "houseNumber");
            applyBatchDetail.setName(String.valueOf(name));
            applyBatchDetail.setDisplayName(String.valueOf(name));
            applyBatchDetail.setTableName(ztreeDto.getTableName());
            applyBatchDetail.setTableId((Integer) entityAbstract.getProperty(o, "id"));
        }
        basicApplyBatchDetailService.saveBasicApplyBatchDetail(applyBatchDetail);
        return applyBatchDetail;
    }


    /**
     * 引用楼盘
     *
     * @param referenceId
     * @param basicApplyBatchId
     * @return
     */
    public void referenceEstate(Integer referenceId, Integer basicApplyBatchId, Integer planDetailsId) {
        if (basicApplyBatchId != null) {
            BasicApplyBatch basicApplyBatch = getBasicApplyBatchById(basicApplyBatchId);
            basicApplyBatch.setReferenceApplyBatchId(referenceId);
            basicApplyBatchDao.updateInfo(basicApplyBatch);
        } else {
            BasicApplyBatch sourceApplyBatch = basicApplyBatchDao.getBasicApplyBatchById(referenceId);
            BasicApplyBatch newBasicApplyBatch = new BasicApplyBatch();
            BeanUtils.copyProperties(sourceApplyBatch, newBasicApplyBatch, BaseConstant.ASSESS_IGNORE_ARRAY);
            newBasicApplyBatch.setProjectId(sourceApplyBatch.getProjectId());
            newBasicApplyBatch.setPlanDetailsId(planDetailsId);
            newBasicApplyBatch.setReferenceApplyBatchId(referenceId);
            newBasicApplyBatch.setDraftFlag(false);
            newBasicApplyBatch.setBisDelete(false);
            newBasicApplyBatch.setCreator(commonService.thisUserAccount());
            basicApplyBatchDao.addBasicApplyBatch(newBasicApplyBatch);
        }

    }

    /**
     * 获取批量信息 by planDetailsIds
     *
     * @param planDetailsIdList
     * @return
     */
    public List<BasicApplyBatch> getBasicApplyBatchsByPlanDetailsIds(List<Integer> planDetailsIdList) {
        if (CollectionUtils.isEmpty(planDetailsIdList)) return null;
        return basicApplyBatchDao.getBasicApplyBatchsByPlanDetailsIds(planDetailsIdList);
    }

    //案例生成树结构
    public BasicApplyBatch generateTree(Integer estateId) throws Exception {
        if (estateId == null) {
            throw new BusinessException("数据不存在");
        }
        //主表
        BasicEstate basicEstate = basicEstateService.getBasicEstateById(estateId);
        BasicApplyBatch basicApplyBatch = new BasicApplyBatch();
        basicApplyBatch.setEstateId(estateId);
        basicApplyBatch.setBisCase(true);
        basicApplyBatch.setType(basicEstate.getType());
        basicApplyBatch.setClassify(basicEstate.getClassify());
        basicApplyBatch.setEstateName(basicEstate.getName());
        basicApplyBatch.setCreator(basicEstate.getCreator());
        basicApplyBatchDao.addBasicApplyBatch(basicApplyBatch);
        //子表 楼盘
        BasicApplyBatchDetail estateBatchDetail = new BasicApplyBatchDetail();
        estateBatchDetail.setPid(0);
        estateBatchDetail.setApplyBatchId(basicApplyBatch.getId());
        estateBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        estateBatchDetail.setTableId(basicEstate.getId());
        estateBatchDetail.setType(BasicFormClassifyEnum.ESTATE.getKey());
        estateBatchDetail.setName(basicEstate.getName());
        estateBatchDetail.setDisplayName(basicEstate.getName());
        estateBatchDetail.setExecutor(basicEstate.getCreator());
        basicApplyBatchDetailService.saveBasicApplyBatchDetail(estateBatchDetail);
        //楼栋
        List<BasicBuilding> buildingList = basicBuildingService.getBasicBuildingByEstateId(estateId);
        if (CollectionUtils.isNotEmpty(buildingList)) {
            for (BasicBuilding building : buildingList) {
                BasicApplyBatchDetail buildingBatchDetail = new BasicApplyBatchDetail();
                buildingBatchDetail.setPid(estateBatchDetail.getId());
                buildingBatchDetail.setApplyBatchId(basicApplyBatch.getId());
                buildingBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                buildingBatchDetail.setTableId(building.getId());
                buildingBatchDetail.setType(BasicFormClassifyEnum.BUILDING.getKey());
                buildingBatchDetail.setName(building.getBuildingNumber());
                buildingBatchDetail.setDisplayName(String.format("%s栋", building.getBuildingNumber()));
                buildingBatchDetail.setExecutor(building.getCreator());
                basicApplyBatchDetailService.saveBasicApplyBatchDetail(buildingBatchDetail);
                //单元
                List<BasicUnit> unitList = basicUnitService.getBasicUnitByUnitId(building.getId());
                if (CollectionUtils.isNotEmpty(unitList)) {
                    for (BasicUnit unit : unitList) {
                        BasicApplyBatchDetail unitBatchDetail = new BasicApplyBatchDetail();
                        unitBatchDetail.setPid(buildingBatchDetail.getId());
                        unitBatchDetail.setApplyBatchId(basicApplyBatch.getId());
                        unitBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                        unitBatchDetail.setTableId(unit.getId());
                        unitBatchDetail.setType(BasicFormClassifyEnum.UNIT.getKey());
                        unitBatchDetail.setName(unit.getUnitNumber());
                        unitBatchDetail.setDisplayName(String.format("%s单元", unit.getUnitNumber()));
                        unitBatchDetail.setExecutor(unit.getCreator());
                        basicApplyBatchDetailService.saveBasicApplyBatchDetail(unitBatchDetail);
                        //房屋
                        List<BasicHouse> houseList = basicHouseService.getHousesByUnitId(unit.getId());
                        if (CollectionUtils.isNotEmpty(houseList)) {
                            for (BasicHouse house : houseList) {
                                BasicApplyBatchDetail houseBatchDetail = new BasicApplyBatchDetail();
                                houseBatchDetail.setPid(unitBatchDetail.getId());
                                houseBatchDetail.setApplyBatchId(basicApplyBatch.getId());
                                houseBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                                houseBatchDetail.setTableId(house.getId());
                                houseBatchDetail.setName(house.getHouseNumber());
                                houseBatchDetail.setType(BasicFormClassifyEnum.HOUSE.getKey());
                                houseBatchDetail.setDisplayName(house.getHouseNumber());
                                houseBatchDetail.setExecutor(house.getCreator());
                                basicApplyBatchDetailService.saveBasicApplyBatchDetail(houseBatchDetail);
                            }
                        }
                    }
                }

            }
        }

        return basicApplyBatch;
    }
}
