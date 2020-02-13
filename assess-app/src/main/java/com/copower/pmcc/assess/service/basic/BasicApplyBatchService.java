package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyFormNameEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.ExamineFileUpLoadFieldEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomBasicAppBatchMapper;
import com.copower.pmcc.assess.dal.basis.dao.basic.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.output.basic.BasicApplyBatchVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.assist.ResidueRatioService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.cases.*;
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
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private ResidueRatioService residueRatioService;
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
    private CaseEstateService caseEstateService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CaseBuildingService caseBuildingService;
    @Autowired
    private CaseUnitService caseUnitService;
    @Autowired
    private CaseHouseService caseHouseService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicBuildingDao basicBuildingDao;
    @Autowired
    private BasicUnitDao basicUnitDao;
    @Autowired
    private BasicHouseDao basicHouseDao;
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
            ztreeDto.setType(getZtreeDtoType(item.getTableName()));
            ztreeDto.setCreator(item.getCreator());
            ztreeDto.setExecutor(item.getExecutor());
            ztreeDto.setCreatorName(publicService.getUserNameByAccount(item.getCreator()));
            ztreeDto.setBisStructure(item.getBisStructure());
            treeDtos.add(ztreeDto);
        }
        return treeDtos;
    }

    public String getZtreeDtoType(String tableName) {
        if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicEstate.class))) {
            return EstateTaggingTypeEnum.ESTATE.getKey();
        }
        if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicHouse.class))) {
            return EstateTaggingTypeEnum.HOUSE.getKey();
        }
        if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicBuilding.class))) {
            return EstateTaggingTypeEnum.BUILDING.getKey();
        }
        if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicUnit.class))) {
            return EstateTaggingTypeEnum.UNIT.getKey();
        }
        return null;
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

    /**
     * 获取楼盘下案例结构
     *
     * @param estateId
     * @return
     */
    public List<ZtreeDto> getCaseEstateZtreeDtos(Integer estateId) {
        if (estateId == null) return null;
        //先清除已生成的数据
        BasicApplyBatch where = new BasicApplyBatch();
        where.setEstateId(estateId);
        where.setBisCase(true);
        BasicApplyBatch basicApplyBatch = basicApplyBatchDao.getBasicApplyBatch(where);
        if (basicApplyBatch != null) {
            List<BasicApplyBatchDetail> applyBatchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(basicApplyBatch.getId());
            if (CollectionUtils.isNotEmpty(applyBatchDetailList))
                applyBatchDetailList.forEach(o -> basicApplyBatchDetailDao.deleteInfo(o.getId()));
            basicApplyBatchDao.deleteInfo(basicApplyBatch.getId());
        }

        BasicApplyBatch applyBatch = new BasicApplyBatch();
        applyBatch.setEstateId(estateId);
        applyBatch.setBisCase(true);
        applyBatch.setCreator(commonService.thisUserAccount());
        basicApplyBatchDao.addBasicApplyBatch(applyBatch);

        BasicApplyBatchDetail estateApplyBatchDetail = new BasicApplyBatchDetail();
        estateApplyBatchDetail.setPid(0);
        estateApplyBatchDetail.setApplyBatchId(applyBatch.getId());
        estateApplyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        estateApplyBatchDetail.setTableId(estateId);
        BasicEstate basicEstate = basicEstateService.getBasicEstateById(estateId);
        estateApplyBatchDetail.setName(basicEstate.getName());
        estateApplyBatchDetail.setDisplayName(basicEstate.getName());
        estateApplyBatchDetail.setCreator(commonService.thisUserAccount());
        basicApplyBatchDetailDao.addInfo(estateApplyBatchDetail);

        BasicBuilding whereBasicBuilding = new BasicBuilding();
        whereBasicBuilding.setEstateId(estateId);
        whereBasicBuilding.setBisCase(true);
        List<BasicBuilding> buildingList = basicBuildingService.getBasicBuildingList(whereBasicBuilding);
        if (CollectionUtils.isNotEmpty(buildingList)) {
            for (BasicBuilding basicBuilding : buildingList) {
                BasicApplyBatchDetail buildingApplyBatchDetail = new BasicApplyBatchDetail();
                buildingApplyBatchDetail.setPid(estateApplyBatchDetail.getId());
                buildingApplyBatchDetail.setApplyBatchId(applyBatch.getId());
                buildingApplyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                buildingApplyBatchDetail.setTableId(basicBuilding.getId());
                buildingApplyBatchDetail.setName(basicBuilding.getBuildingNumber());
                buildingApplyBatchDetail.setDisplayName(basicBuilding.getBuildingName());
                buildingApplyBatchDetail.setCreator(commonService.thisUserAccount());
                basicApplyBatchDetailDao.addInfo(buildingApplyBatchDetail);

                BasicUnit whereBasicUnit = new BasicUnit();
                whereBasicUnit.setBuildingId(basicBuilding.getId());
                whereBasicUnit.setBisCase(true);
                List<BasicUnit> basicUnitList = basicUnitService.getBasicUnitList(whereBasicUnit);
                if (CollectionUtils.isNotEmpty(basicUnitList)) {
                    for (BasicUnit basicUnit : basicUnitList) {
                        BasicApplyBatchDetail unitApplyBatchDetail = new BasicApplyBatchDetail();
                        unitApplyBatchDetail.setPid(buildingApplyBatchDetail.getId());
                        unitApplyBatchDetail.setApplyBatchId(applyBatch.getId());
                        unitApplyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                        unitApplyBatchDetail.setTableId(basicUnit.getId());
                        unitApplyBatchDetail.setName(basicUnit.getUnitNumber());
                        unitApplyBatchDetail.setDisplayName(basicUnit.getUnitNumber() + "单元");
                        unitApplyBatchDetail.setCreator(commonService.thisUserAccount());
                        basicApplyBatchDetailDao.addInfo(unitApplyBatchDetail);

                        BasicHouse whereBasicHouse = new BasicHouse();
                        whereBasicHouse.setUnitId(basicUnit.getId());
                        whereBasicHouse.setBisCase(true);
                        List<BasicHouse> basicHouseList = basicHouseService.getBasicHouseList(whereBasicHouse);
                        if (CollectionUtils.isNotEmpty(basicHouseList)) {
                            for (BasicHouse basicHouse : basicHouseList) {
                                BasicApplyBatchDetail houseApplyBatchDetail = new BasicApplyBatchDetail();
                                houseApplyBatchDetail.setPid(unitApplyBatchDetail.getId());
                                houseApplyBatchDetail.setApplyBatchId(applyBatch.getId());
                                houseApplyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                                houseApplyBatchDetail.setTableId(basicHouse.getId());
                                houseApplyBatchDetail.setName(basicHouse.getHouseNumber());
                                houseApplyBatchDetail.setDisplayName(basicHouse.getHouseNumber());
                                houseApplyBatchDetail.setCreator(commonService.thisUserAccount());
                                basicApplyBatchDetailDao.addInfo(houseApplyBatchDetail);
                            }
                        }
                    }
                }
            }
        }
        List<ZtreeDto> list = Lists.newArrayList();
        List<BasicApplyBatchDetail> applyBatchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(applyBatch.getId());
        if (CollectionUtils.isNotEmpty(applyBatchDetailList)) {
            for (BasicApplyBatchDetail applyBatchDetail : applyBatchDetailList) {
                ZtreeDto ztreeDto = new ZtreeDto();
                ztreeDto.setId(applyBatchDetail.getId());
                ztreeDto.setName(applyBatchDetail.getName());
                ztreeDto.setDisplayName(applyBatchDetail.getDisplayName());
                ztreeDto.setPid(applyBatchDetail.getPid());
                ztreeDto.setTableName(applyBatchDetail.getTableName());
                ztreeDto.setTableId(applyBatchDetail.getTableId());
                ztreeDto.setType(getZtreeDtoType(applyBatchDetail.getTableName()));
                ztreeDto.setCreator(applyBatchDetail.getCreator());
                ztreeDto.setExecutor(applyBatchDetail.getExecutor());
                list.add(ztreeDto);
            }
        }
        return list;
    }

    //验证楼盘是否已在案列库
    public Integer verification(BasicApplyBatch basicApplyBatch) throws Exception {
        BasicEstate basicEstate = new BasicEstate();
        basicEstate.setProvince(basicApplyBatch.getProvince());
        basicEstate.setCity(basicApplyBatch.getCity());
        basicEstate.setName(basicApplyBatch.getEstateName());
        basicEstate.setBisCase(true);
        List<BasicEstate> caseEstateList = basicEstateService.getBasicEstateList(basicEstate);
        if (CollectionUtils.isNotEmpty(caseEstateList)) {
            Collections.sort(caseEstateList, (a, b) -> {
                return b.getVersion().compareTo(a.getVersion());
            });
            return caseEstateList.get(0).getId();
        }
        return 0;
    }


    public List<BaseDataDic> getFormClassifyList() {
        List<BaseDataDic> dataDicList = baseDataDicService.getCacheDataDicList
                (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY);
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
                    basicApplyService.deleteBasicApply(o.getId());
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            });
        }
        if (applyBatch != null) {
            List<BasicApplyBatchDetail> batchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(applyBatch.getId());
            if (CollectionUtils.isNotEmpty(batchDetailList)) {
                for (BasicApplyBatchDetail basicApplyBatchDetail : batchDetailList) {
                    if (FormatUtils.entityNameConvertToTableName(BasicBuilding.class).equals(basicApplyBatchDetail.getTableName())) {
                        basicBuildingService.clearInvalidData(basicApplyBatchDetail.getTableId());
                    }
                    if (FormatUtils.entityNameConvertToTableName(BasicUnit.class).equals(basicApplyBatchDetail.getTableName())) {
                        basicUnitService.clearInvalidData(basicApplyBatchDetail.getTableId());
                    }
                    if (FormatUtils.entityNameConvertToTableName(BasicHouse.class).equals(basicApplyBatchDetail.getTableName())) {
                        basicHouseService.clearInvalidData(basicApplyBatchDetail.getTableId());
                    }
                    basicApplyBatchDetailDao.deleteInfo(basicApplyBatchDetail.getId());
                }
            }
            basicEstateService.clearInvalidData(applyBatch.getEstateId());
            basicApplyBatchDao.deleteInfo(applyBatch.getId());
        }
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
        //1.根据不同情况初始化不同的信息结构 2.初始化之前需先将原初始化信息删除
        deleteBatchByPlanDetailsId(basicApplyBatch.getPlanDetailsId());
        if (basicApplyBatch.getClassify() == null) return basicApplyBatch;
        BaseDataDic classifyDataDic = baseDataDicService.getCacheDataDicById(basicApplyBatch.getClassify());
        //楼盘
        BasicEstate basicEstate = new BasicEstate();
        //申报表代入的信息
        DeclareRecord declareRecord = null;
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(basicApplyBatch.getPlanDetailsId());
        if (projectPlanDetails != null && projectPlanDetails.getDeclareRecordId() != null) {
            declareRecord = declareRecordService.getDeclareRecordById(projectPlanDetails.getDeclareRecordId());
        }
        if (declareRecord != null) {
            basicEstate.setProvince(declareRecord.getProvince());
            basicEstate.setCity(declareRecord.getCity());
            basicEstate.setDistrict(declareRecord.getDistrict());
            basicEstate.setStreetNumber(declareRecord.getStreetNumber());
            basicEstate.setAttachNumber(declareRecord.getAttachedNumber());
        }
        basicEstateService.saveAndUpdateBasicEstate(basicEstate, false);
        BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
        if (declareRecord != null) {
            basicEstateLandState.setLandUseType(declareRecord.getLandCertUse());
            basicEstateLandState.setLandUseCategory(declareRecord.getLandCertUseCategory());
        }
        basicEstateLandState.setEstateId(basicEstate.getId());
        basicEstateLandState.setCreator(commonService.thisUserAccount());
        basicEstateLandStateService.saveAndUpdateBasicEstateLandState(basicEstateLandState, false);
        String estateName = "楼盘信息";
        if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND.equals(classifyDataDic.getFieldName())
                || AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND_ONLY.equals(classifyDataDic.getFieldName())) {
            estateName = "地块信息";
        }
        basicApplyBatch.setEstateId(basicEstate.getId());
        basicApplyBatch.setEstateName(estateName);
        saveBasicApplyBatch(basicApplyBatch);

        BasicApplyBatchDetail estateApplyBatchDetail = new BasicApplyBatchDetail();
        estateApplyBatchDetail.setPid(0);
        estateApplyBatchDetail.setBisStandard(false);
        estateApplyBatchDetail.setApplyBatchId(basicApplyBatch.getId());
        estateApplyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        estateApplyBatchDetail.setTableId(basicEstate.getId());
        estateApplyBatchDetail.setName(estateName);
        estateApplyBatchDetail.setDisplayName(estateName);
        estateApplyBatchDetail.setExecutor(commonService.thisUserAccount());
        basicApplyBatchDetailService.saveBasicApplyBatchDetail(estateApplyBatchDetail);

        if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_SINGEL.equals(classifyDataDic.getFieldName())) {
            //1.添加applyBatch 2.添加applyBatchDetail 3.添加楼盘、楼栋、单元、房屋主表 4.添加basicApply表
            BasicBuilding basicBuilding = new BasicBuilding();
            basicBuilding.setEstateId(basicEstate.getId());
            basicBuilding.setCreator(commonService.thisUserAccount());
            basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding, false);
            BasicApplyBatchDetail buildingApplyBatchDetail = new BasicApplyBatchDetail();
            buildingApplyBatchDetail.setPid(estateApplyBatchDetail.getId());
            buildingApplyBatchDetail.setBisStandard(false);
            buildingApplyBatchDetail.setApplyBatchId(basicApplyBatch.getId());
            buildingApplyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
            buildingApplyBatchDetail.setTableId(basicBuilding.getId());
            buildingApplyBatchDetail.setName("楼栋信息");
            buildingApplyBatchDetail.setDisplayName("楼栋信息");
            buildingApplyBatchDetail.setExecutor(commonService.thisUserAccount());
            basicApplyBatchDetailService.saveBasicApplyBatchDetail(buildingApplyBatchDetail);
            //单元
            BasicUnit basicUnit = new BasicUnit();
            basicUnit.setEstateId(basicBuilding.getEstateId());
            basicUnit.setBuildingId(basicBuilding.getId());
            basicUnit.setCreator(commonService.thisUserAccount());
            basicUnitService.saveAndUpdateBasicUnit(basicUnit, false);
            BasicApplyBatchDetail unitApplyBatchDetail = new BasicApplyBatchDetail();
            unitApplyBatchDetail.setBisStandard(false);
            unitApplyBatchDetail.setPid(buildingApplyBatchDetail.getId());
            unitApplyBatchDetail.setApplyBatchId(basicApplyBatch.getId());
            unitApplyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
            unitApplyBatchDetail.setTableId(basicUnit.getId());
            unitApplyBatchDetail.setName("单元信息");
            unitApplyBatchDetail.setDisplayName("单元信息");
            unitApplyBatchDetail.setExecutor(commonService.thisUserAccount());
            basicApplyBatchDetailService.saveBasicApplyBatchDetail(unitApplyBatchDetail);
            //房屋
            BasicHouse basicHouse = new BasicHouse();
            basicHouse.setEstateId(basicUnit.getEstateId());
            basicHouse.setBuildingId(basicUnit.getBuildingId());
            basicHouse.setUnitId(basicUnit.getId());
            basicHouse.setCreator(commonService.thisUserAccount());
            basicHouseService.saveAndUpdateBasicHouse(basicHouse, false);
            BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
            basicHouseTrading.setHouseId(basicHouse.getId());
            basicHouseTrading.setCreator(commonService.thisUserAccount());
            basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading, false);
            BasicApplyBatchDetail houseApplyBatchDetail = new BasicApplyBatchDetail();
            houseApplyBatchDetail.setBisStandard(true);
            houseApplyBatchDetail.setPid(unitApplyBatchDetail.getId());
            houseApplyBatchDetail.setApplyBatchId(basicApplyBatch.getId());
            houseApplyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
            houseApplyBatchDetail.setTableId(basicHouse.getId());
            houseApplyBatchDetail.setName("房屋信息");
            houseApplyBatchDetail.setDisplayName("房屋信息");
            houseApplyBatchDetail.setExecutor(commonService.thisUserAccount());
            basicApplyBatchDetailService.saveBasicApplyBatchDetail(houseApplyBatchDetail);

            BasicApply basicApply = new BasicApply();
            basicApply.setPlanDetailsId(basicApplyBatch.getPlanDetailsId());
            basicApply.setBasicEstateId(basicEstate.getId());
            basicApply.setBasicBuildingId(basicBuilding.getId());
            basicApply.setBasicUnitId(basicUnit.getId());
            basicApply.setBasicHouseId(basicHouse.getId());
            basicApply.setCreator(commonService.thisUserAccount());
            basicApplyService.saveBasicApply(basicApply);
        } else if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND_ONLY.equals(classifyDataDic.getFieldName())) {
            //纯土地中地块包一部分房屋相关信息
            BasicHouse basicHouse = new BasicHouse();
            basicHouse.setHouseNumber("0");
            basicHouse.setCreator(commonService.thisUserAccount());
            basicHouseService.saveAndUpdateBasicHouse(basicHouse, false);
            BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
            basicHouseTrading.setHouseId(basicHouse.getId());
            basicHouseTrading.setCreator(commonService.thisUserAccount());
            basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading, false);

            BasicApply basicApply = new BasicApply();
            basicApply.setBasicEstateId(basicEstate.getId());
            basicApply.setBasicHouseId(basicHouse.getId());
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
            basicEstateService.saveAndUpdateBasicEstate(basicEstate, false);
        }
    }

    /**
     * 保存数据
     *
     * @param formData
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveDraft(String formData, Integer applyBatchId, Integer planDetailsId) throws Exception {
        String jsonContent = null;
        JSONObject jsonObject = JSON.parseObject(formData);

        //楼盘过程数据
        BasicEstate basicEstate = null;
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATE.getVar());
        if (StringUtils.isNotBlank(jsonContent)) {
            basicEstate = JSONObject.parseObject(jsonContent, BasicEstate.class);
            //原来数据做记录,将老数据复制一条
            BasicEstate oldBasicEstate = basicEstateService.getBasicEstateById(basicEstate.getId());
            BasicEstate version = basicEstateService.copyBasicEstate(oldBasicEstate.getId(), null, false);
            version.setRelevanceId(oldBasicEstate.getId());
            basicEstateService.saveAndUpdateBasicEstate(version, false);

            if (basicEstate != null) {
                basicEstate.setClassify(oldBasicEstate.getClassify());
                basicEstate.setType(oldBasicEstate.getType());
                basicEstateService.saveAndUpdateBasicEstate(basicEstate, true);
                BasicApplyBatch basicApplyBatch = getBasicApplyBatchByEstateId(basicEstate.getId());
                if (basicApplyBatch != null) {
                    basicApplyBatch.setEstateName(basicEstate.getName());
                    basicApplyBatchDao.updateInfo(basicApplyBatch);
                }
                if (basicEstate.getId() != null) {
                    BasicEstateLandState basicEstateLandState = null;
                    String string = jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATELAND_STATE.getVar());
                    basicEstateLandState = JSONObject.parseObject(string, BasicEstateLandState.class);
                    if (basicEstateLandState != null) {
                        basicEstateLandState.setLandLevelContent(StringUtils.isNotEmpty(basicEstateLandState.getLandLevelContent()) ? basicEstateLandState.getLandLevelContent() : null);
                        basicEstateLandState.setEstateId(basicEstate.getId());
                        basicEstateLandStateService.saveAndUpdateBasicEstateLandState(basicEstateLandState, true);
                    }
                }
                BasicApplyBatchDetail estateDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicEstate.class), basicEstate.getId());
                if (estateDetail != null) {
                    estateDetail.setName(basicEstate.getName());
                    estateDetail.setDisplayName(basicEstate.getName());
                    basicApplyBatchDetailDao.updateInfo(estateDetail);
                }
                BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchById(applyBatchId);
                if (applyBatch != null) {
                    applyBatch.setEstateId(basicEstate.getId());
                    applyBatch.setEstateName(basicEstate.getName());
                    basicApplyBatchService.saveBasicApplyBatch(applyBatch);
                }
            }
        }

        //楼栋主数据
        BasicBuilding basicBuilding = null;
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_BUILDING.getVar());
        if (StringUtils.isNotBlank(jsonContent)) {
            basicBuilding = JSONObject.parseObject(jsonContent, BasicBuilding.class);
            //原来数据做记录,将老数据复制一条
            BasicBuilding oldBasicBuilding = basicBuildingService.getBasicBuildingById(basicBuilding.getId());
            BasicBuilding version = basicBuildingService.copyBasicBuilding(oldBasicBuilding.getId(), null, false);
            version.setRelevanceId(oldBasicBuilding.getId());
            version.setEstateId(0);
            basicBuildingService.saveAndUpdateBasicBuilding(version, false);

            if (basicBuilding != null) {
                basicBuildingService.saveAndUpdateBasicBuilding(basicBuilding, true);
                BasicApplyBatchDetail buildingDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicBuilding.class), basicBuilding.getId());
                if (buildingDetail != null) {
                    buildingDetail.setName(basicBuilding.getBuildingNumber());
                    buildingDetail.setDisplayName(basicBuilding.getBuildingName());
                    basicApplyBatchDetailDao.updateInfo(buildingDetail);
                }
            }
        }

        //单元过程数据
        BasicUnit basicUnit = null;
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_UNIT.getVar());
        if (StringUtils.isNotEmpty(jsonContent)) {
            basicUnit = JSONObject.parseObject(jsonContent, BasicUnit.class);
            //原来数据做记录,将老数据复制一条
            BasicUnit oldBasicUnit = basicUnitService.getBasicUnitById(basicUnit.getId());
            BasicUnit version = basicUnitService.copyBasicUnit(oldBasicUnit.getId(), null, false);
            version.setRelevanceId(oldBasicUnit.getId());
            version.setEstateId(0);
            version.setBuildingId(0);
            basicUnitService.saveAndUpdateBasicUnit(version, false);
            if (basicUnit != null) {
                basicUnitService.saveAndUpdateBasicUnit(basicUnit, true);
                BasicApplyBatchDetail unitDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicUnit.class), basicUnit.getId());
                if (unitDetail != null) {
                    unitDetail.setName(basicUnit.getUnitNumber());
                    unitDetail.setDisplayName(String.format("%s%s", basicUnit.getUnitNumber(), "单元"));
                    basicApplyBatchDetailDao.updateInfo(unitDetail);
                }
            }
        }

        //处理房屋数据
        BasicHouse basicHouse = null;
        jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_HOUSE.getVar());
        if (StringUtils.isNotEmpty(jsonContent)) {
            basicHouse = JSONObject.parseObject(jsonContent, BasicHouse.class);
            //原来数据做记录,将老数据复制一条
            BasicHouse oldBasicHouse = basicHouseDao.getBasicHouseById(basicHouse.getId());
            BasicHouse version = basicHouseService.copyBasicHouse(oldBasicHouse.getId(), null, false);
            version.setRelevanceId(oldBasicHouse.getId());
            version.setEstateId(0);
            version.setBuildingId(0);
            version.setUnitId(0);
            basicHouseService.saveAndUpdateBasicHouse(version, false);
            if (basicHouse != null) {
                Integer houseId = basicHouseService.saveAndUpdateBasicHouse(basicHouse, true);
                BasicApplyBatchDetail houseDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicHouse.class), basicHouse.getId());
                if (houseDetail != null) {
                    houseDetail.setName(basicHouse.getHouseNumber());
                    houseDetail.setDisplayName(basicHouse.getHouseNumber());
                    basicApplyBatchDetailDao.updateInfo(houseDetail);

                    BasicApply where = new BasicApply();
                    where.setPlanDetailsId(planDetailsId);
                    where.setBasicHouseId(houseDetail.getTableId());
                    BasicApply basicApply = basicApplyService.getBasicApply(where);
                    Map<EstateTaggingTypeEnum, BasicApplyBatchDetail> map = basicApplyBatchDetailService.getApplyBatchDetailMap(houseDetail);
                    basicApply.setArea(basicHouse.getArea());
                    if (houseDetail.getCaseTablePid() == null) {
                        //单元
                        BasicApplyBatchDetail unitBatchDetail = map.get(EstateTaggingTypeEnum.UNIT);
                        //楼栋
                        BasicApplyBatchDetail buildBatchDetail = map.get(EstateTaggingTypeEnum.BUILDING);
                        //楼盘
                        BasicApplyBatchDetail estateBatchDetail = map.get(EstateTaggingTypeEnum.ESTATE);
                        basicApply.setName(basicApplyService.getFullName(estateBatchDetail.getName(), buildBatchDetail.getName(), unitBatchDetail.getName(), houseDetail.getName()));
                    } else {
                        CaseUnit caseUnit = caseUnitService.getCaseUnitById(houseDetail.getCaseTablePid());
                        CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(caseUnit.getBuildingId());
                        CaseEstate caseEstate = caseEstateService.getCaseEstateById(caseBuilding.getEstateId());
                        basicApply.setName(basicApplyService.getFullName(caseEstate.getName(), caseBuilding.getBuildingNumber(), caseUnit.getUnitNumber(), houseDetail.getName()));
                    }
                    basicApplyService.saveBasicApply(basicApply);
                }
                //交易信息
                jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_TRADING.getVar());
                BasicHouseTrading basicTrading = JSONObject.parseObject(jsonContent, BasicHouseTrading.class);
                if (basicTrading != null) {
                    BasicHouseTrading houseTradingOld = basicHouseTradingService.getTradingByHouseId(basicHouse.getId());
                    basicTrading.setId(houseTradingOld.getId());
                    basicTrading.setHouseId(houseId);
                    basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicTrading, true);
                }
                //完损度
                jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_DAMAGED_DEGREE.getVar());
                List<BasicHouseDamagedDegree> damagedDegreeList = JSONObject.parseArray(jsonContent, BasicHouseDamagedDegree.class);
                if (!org.springframework.util.CollectionUtils.isEmpty(damagedDegreeList)) {
                    for (BasicHouseDamagedDegree degree : damagedDegreeList) {
                        Integer category = degree.getCategory();
                        BasicHouseDamagedDegree damagedDegree = basicHouseDamagedDegreeService.getDamagedDegreeByHouseIdAndCategory(houseId, category);
                        damagedDegree.setEntityCondition(degree.getEntityCondition());
                        damagedDegree.setEntityConditionContent(degree.getEntityConditionContent());
                        damagedDegree.setScore(degree.getScore());
                        basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(damagedDegree, false);
                    }
                    //写入成新率
                    String newDegree = residueRatioService.getObservationalRatio(basicHouse.getId());
                    basicHouse.setNewDegree(newDegree);
                    basicHouseService.saveAndUpdateBasicHouse(basicHouse, false);
                }
            }
        }
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
        //验证是否已在审批
        this.verifyApproval(id);

        BasicApplyBatch applyBatch = basicApplyBatchDao.getBasicApplyBatchById(id);
        ProcessUserDto processUserDto = null;
        ProcessInfo processInfo = new ProcessInfo();
        //流程描述
        processInfo.setFolio(String.format("%s%s", "批量申请_", applyBatch.getEstateName()));
        final String boxName = baseParameterService.getParameterValues(BaseParameterEnum.CASE_BASE_INFO_BATCH_APPLY_KEY.getParameterKey());
        BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
        processInfo.setTableName(FormatUtils.entityNameConvertToTableName(BasicApply.class));
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


    //验证楼盘是否已发起审批
    public void verifyApproval(Integer id) throws Exception {
        BasicApplyBatch applyBatch = basicApplyBatchDao.getBasicApplyBatchById(id);
        //需要验证的数据
        BasicEstate basicEstate = basicEstateDao.getBasicEstateById(applyBatch.getEstateId());
        BasicApplyBatch verifyData = new BasicApplyBatch();
        //验证升级数据
        if (applyBatch.getCaseEstateId() != null) {
            verifyData.setEstateName(applyBatch.getEstateName());
            verifyData.setStatus(ProjectStatusEnum.RUNING.getKey());
            verifyData.setCaseEstateId(applyBatch.getCaseEstateId());
            List<BasicApplyBatch> infoList = basicApplyBatchDao.getInfoList(verifyData);
            if (CollectionUtils.isNotEmpty(infoList)) {
                throw new BusinessException("升级楼盘正在审批中，请不要重复申请");
            }
        } else {
            //获取所有审批中的数据
            BasicApplyBatch approvalData = new BasicApplyBatch();
            approvalData.setStatus(ProjectStatusEnum.RUNING.getKey());
            List<BasicApplyBatch> infoList = basicApplyBatchDao.getInfoList(approvalData);
            if (CollectionUtils.isNotEmpty(infoList)) {
                List<Integer> estateIds = LangUtils.transform(infoList, o -> o.getEstateId());
                List<BasicEstate> basicEstateList = LangUtils.transform(estateIds, p -> basicEstateDao.getBasicEstateById(p));
                for (BasicEstate item : basicEstateList) {
                    if (StringUtils.equals(item.getName(), basicEstate.getName()) && StringUtils.equals(item.getProvince()
                            , basicEstate.getProvince()) && StringUtils.equals(item.getCity(), basicEstate.getCity())) {
                        throw new BusinessException("该楼盘正在审批中，请不要重复申请");
                    }
                }
            }
        }


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
     * 获取申请完整名称
     *
     * @param estateName
     * @param buildingNumber
     * @param unitNumber
     * @param houseNumber
     * @return
     */
    public String getFullName(String estateName, String buildingNumber, String unitNumber, String houseNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(estateName))
            stringBuilder.append(estateName);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(buildingNumber))
            stringBuilder.append(buildingNumber).append("栋");
        if (org.apache.commons.lang3.StringUtils.isNotBlank(unitNumber))
            stringBuilder.append(unitNumber).append("单元");
        if (org.apache.commons.lang3.StringUtils.isNotBlank(houseNumber))
            stringBuilder.append(houseNumber).append("号");
        return stringBuilder.toString();
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

        //复制楼栋
        if (sourceBasicApplyBatchDetail.getTableName().equals("tb_basic_building")) {
            basicBuildingService.copyBasicBuilding(sourceBasicApplyBatchDetail.getTableId(), targetBasicApplyBatchDetail.getTableId(), true);
        }
        //复制单元
        if (sourceBasicApplyBatchDetail.getTableName().equals("tb_basic_unit")) {
            basicUnitService.copyBasicUnit(sourceBasicApplyBatchDetail.getTableId(), targetBasicApplyBatchDetail.getTableId(), true);
        }
        //复制房屋
        if (sourceBasicApplyBatchDetail.getTableName().equals("tb_basic_house")) {
            basicHouseService.copyBasicHouse(sourceBasicApplyBatchDetail.getTableId(), targetBasicApplyBatchDetail.getTableId(), true);
        }
    }

    /**
     * 深复制
     *
     * @param sourceBatchDetailId
     * @throws Exception
     */
    public void deepCopy(Integer sourceBatchDetailId) throws Exception {
        //被复制数据
        BasicApplyBatchDetail sourceBasicApplyBatchDetail = basicApplyBatchDetailDao.getInfoById(sourceBatchDetailId);

        //复制楼栋
        if (sourceBasicApplyBatchDetail.getTableName().equals("tb_basic_building")) {
            BasicBuilding sourceBuilding = basicBuildingDao.getBasicBuildingById(sourceBasicApplyBatchDetail.getTableId());
            deepCopyBasicBuilding(sourceBuilding, sourceBasicApplyBatchDetail);
        }
        //复制单元
        if (sourceBasicApplyBatchDetail.getTableName().equals("tb_basic_unit")) {
            BasicUnit sourceUnit = basicUnitDao.getBasicUnitById(sourceBasicApplyBatchDetail.getTableId());
            deepCopyBasicUnit(sourceUnit, sourceBasicApplyBatchDetail, null);
        }
    }

    /**
     * 楼栋深复制（basic->basic）
     *
     * @return
     * @throws Exception
     */
    private void deepCopyBasicBuilding(BasicBuilding sourceBuilding, BasicApplyBatchDetail source) throws Exception {
        BasicBuilding targetBuilding = basicBuildingService.copyBasicBuilding(sourceBuilding.getId(), null, true);
        //拷贝完后更新
        BasicApplyBatchDetail newBatchDetail = new BasicApplyBatchDetail();
        BeanUtils.copyProperties(source, newBatchDetail, "id", "gmtCreated", "gmtModified");
        newBatchDetail.setTableId(targetBuilding.getId());
        newBatchDetail.setCreator(commonService.thisUserAccount());
        basicApplyBatchDetailDao.addInfo(newBatchDetail);

        //复制单元
        List<BasicApplyBatchDetail> unitBatchDetailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByPid(source.getId(), source.getApplyBatchId());
        if (CollectionUtils.isNotEmpty(unitBatchDetailList)) {
            for (BasicApplyBatchDetail batchDetail : unitBatchDetailList) {
                deepCopyBasicUnit(basicUnitDao.getBasicUnitById(batchDetail.getTableId()), batchDetail, newBatchDetail.getId());
            }
        }
    }


    /**
     * 单元深复制(Basic->Basic)
     *
     * @param source
     * @return
     * @throws Exception
     */
    private void deepCopyBasicUnit(BasicUnit sourceUnit, BasicApplyBatchDetail source, Integer buildingApplyBatchDetailId) throws Exception {
        BasicUnit targetUnit = basicUnitService.copyBasicUnit(sourceUnit.getId(), null, true);

        BasicApplyBatchDetail newBatchDetail = new BasicApplyBatchDetail();
        BeanUtils.copyProperties(source, newBatchDetail, "id", "gmtCreated", "gmtModified");
        if (buildingApplyBatchDetailId != null && buildingApplyBatchDetailId > 0)
            newBatchDetail.setPid(buildingApplyBatchDetailId);
        newBatchDetail.setTableId(targetUnit.getId());
        newBatchDetail.setExecutor(commonService.thisUserAccount());
        newBatchDetail.setCreator(commonService.thisUserAccount());
        basicApplyBatchDetailDao.addInfo(newBatchDetail);

        List<BasicHouse> houseList = basicHouseService.getHousesByUnitId(sourceUnit.getId());
        if (CollectionUtils.isNotEmpty(houseList)) {
            for (BasicHouse basicHouse : houseList) {
                BasicHouse copyBasicHouse = basicHouseService.copyBasicHouse(basicHouse.getId(), null, true);
                copyBasicHouse.setUnitId(targetUnit.getId());
                basicHouseService.saveAndUpdateBasicHouse(copyBasicHouse, false);

                BasicApplyBatchDetail batchDetail = new BasicApplyBatchDetail();
                batchDetail.setPid(newBatchDetail.getId());
                batchDetail.setApplyBatchId(source.getApplyBatchId());
                batchDetail.setName(copyBasicHouse.getHouseNumber());
                batchDetail.setDisplayName(copyBasicHouse.getHouseNumber());
                batchDetail.setTableId(copyBasicHouse.getId());
                batchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                batchDetail.setExecutor(commonService.thisUserAccount());
                batchDetail.setCreator(commonService.thisUserAccount());
                basicApplyBatchDetailDao.addInfo(batchDetail);
            }
        }
    }

    /**
     * 初始化支撑新增获取升级案例结构
     *
     * @param basicApplyBatchId
     * @param caseBatchDetailId
     */
    public BasicApplyBatchDetail initCaseEstateZtree(Integer basicApplyBatchId, Integer caseBatchDetailId, Boolean containThis) {
        //1.根据选择的数据，找出需要支撑该数据的结构，再到现有结构数据表中查看是否已有相关结构数据
        //如果没有则需将结构数据添加进去，处理结构需从上到下
        List<BasicApplyBatchDetail> applyBatchDetails = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(basicApplyBatchId);
        List<BasicApplyBatchDetail> needList = Lists.newArrayList();
        if (containThis) {
            basicApplyBatchDetailService.collectionParentBatchDetails(caseBatchDetailId, needList);
        } else {
            BasicApplyBatchDetail thisDetail = basicApplyBatchDetailService.getDataById(caseBatchDetailId);
            basicApplyBatchDetailService.collectionParentBatchDetails(thisDetail.getPid(), needList);
        }
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
    private BasicApplyBatchDetail listContainCaseBatchDetail(List<BasicApplyBatchDetail> list, BasicApplyBatchDetail applyBatchDetail) {
        if (CollectionUtils.isEmpty(list) || applyBatchDetail == null) return null;
        for (BasicApplyBatchDetail batchDetail : list) {
            Boolean tableNameSame = StringUtils.isNotBlank(batchDetail.getTableName())
                    && StringUtils.isNotBlank(applyBatchDetail.getTableName())
                    && batchDetail.getTableName().equalsIgnoreCase(applyBatchDetail.getTableName());

            Boolean tableIdSame = batchDetail.getTableId() != null && applyBatchDetail.getTableId() != null
                    && batchDetail.getTableId() != 0 && applyBatchDetail.getTableId() != 0
                    && batchDetail.getTableId().equals(applyBatchDetail.getTableId());

            Boolean upgradeTableIdSame = batchDetail.getUpgradeTableId() != null && applyBatchDetail.getUpgradeTableId() != null
                    && batchDetail.getUpgradeTableId() != 0 && applyBatchDetail.getUpgradeTableId() != 0
                    && batchDetail.getUpgradeTableId().equals(applyBatchDetail.getUpgradeTableId());

            if (tableNameSame && (tableIdSame || upgradeTableIdSame)) return batchDetail;
        }
        return null;
    }

    /**
     * 升级案例数据
     *
     * @param applyBatchId
     * @param pid
     * @param caseBatchDetailId
     * @throws Exception
     */
    public BasicApplyBatchDetail upgradeCase(Integer applyBatchId, Integer pid, Integer caseBatchDetailId) throws Exception {
        //1.先将案例数据拷贝一份，用于升级的基础编辑数据
        //2.将拷贝的数据挂到申请的结构下
        BasicApplyBatchDetail applyBatchDetail = new BasicApplyBatchDetail();
        applyBatchDetail.setPid(pid);
        applyBatchDetail.setApplyBatchId(applyBatchId);
        applyBatchDetail.setCreator(commonService.thisUserAccount());
        applyBatchDetail.setExecutor(commonService.thisUserAccount());
        BasicApplyBatchDetail batchDetail = basicApplyBatchDetailService.getDataById(caseBatchDetailId);
        if (batchDetail != null) {
            applyBatchDetail.setUpgradeTableId(batchDetail.getTableId());
            if (FormatUtils.entityNameConvertToTableName(BasicEstate.class).equalsIgnoreCase(batchDetail.getTableName())) {
                BasicEstate basicEstate = basicEstateService.copyBasicEstateIgnore(batchDetail.getTableId(), null, true, "bisCase");
                applyBatchDetail.setName(basicEstate.getName());
                applyBatchDetail.setDisplayName(basicEstate.getName());
                applyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
                applyBatchDetail.setTableId(basicEstate.getId());
            } else if (FormatUtils.entityNameConvertToTableName(BasicBuilding.class).equalsIgnoreCase(batchDetail.getTableName())) {
                BasicBuilding basicBuilding = basicBuildingService.copyBasicBuildingIgnore(batchDetail.getTableId(), null, true, "bisCase");
                applyBatchDetail.setName(basicBuilding.getBuilderName());
                applyBatchDetail.setDisplayName(basicBuilding.getBuilderName());
                applyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                applyBatchDetail.setTableId(basicBuilding.getId());
            } else if (FormatUtils.entityNameConvertToTableName(BasicUnit.class).equalsIgnoreCase(batchDetail.getTableName())) {
                BasicUnit basicUnit = basicUnitService.copyBasicUnitIgnore(batchDetail.getTableId(), null, true, "bisCase");
                applyBatchDetail.setName(basicUnit.getUnitNumber());
                applyBatchDetail.setDisplayName(basicUnit.getUnitNumber());
                applyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                applyBatchDetail.setTableId(basicUnit.getId());
            } else if (FormatUtils.entityNameConvertToTableName(BasicHouse.class).equalsIgnoreCase(batchDetail.getTableName())) {
                BasicHouse basicHouse = basicHouseService.copyBasicHouseIgnore(batchDetail.getTableId(), null, true, "bisCase");
                applyBatchDetail.setName(basicHouse.getHouseNumber());
                applyBatchDetail.setDisplayName(basicHouse.getHouseNumber());
                applyBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                applyBatchDetail.setTableId(basicHouse.getId());
            }
        }
        basicApplyBatchDetailDao.addInfo(applyBatchDetail);
        return applyBatchDetail;
    }


}
