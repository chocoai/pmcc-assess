package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
import com.copower.pmcc.assess.proxy.face.BasicFormStructureInterface;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.event.basic.BasicApplyBatchEvent;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class BasicApplyBatchService {
    protected final Logger log = LoggerFactory.getLogger(getClass());
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
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicHouseCaseSummaryService basicHouseCaseSummaryService;
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
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;
    @Autowired
    private BasicUnitDao basicUnitDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicEstateDao basicEstateDao;
    @Autowired
    private BasicHouseDao basicHouseDao;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Lazy
    @Autowired
    private CustomBasicAppBatchMapper customBasicAppBatchMapper;
    @Autowired
    private PublicBasicService publicBasicService;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 初始化树形结构
     *
     * @param basicApplyBatchId
     * @param showTag           是否显示新增或升级提示
     * @param bisDetail         查看明细
     * @return
     */
    public List<ZtreeDto> getZtreeDto(Integer basicApplyBatchId, Boolean showTag, Boolean bisDetail) throws Exception {
        List<ZtreeDto> treeDtos = new ArrayList<>();
        if (basicApplyBatchId == null) return treeDtos;
        BasicApplyBatch basicApplyBatch = getBasicApplyBatchById(basicApplyBatchId);
        if (basicApplyBatch == null) return treeDtos;
        List<BasicApplyBatchDetail> basicApplyBatchDetails = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(basicApplyBatch.getId());
        if (CollectionUtils.isEmpty(basicApplyBatchDetails)) return treeDtos;
        BasicApplyBatch caseBasicApplyBatch = getCaseBasicApplyBatch(basicApplyBatch.getProvince(), basicApplyBatch.getCity(), basicApplyBatch.getEstateName());
        for (BasicApplyBatchDetail item : basicApplyBatchDetails) {
            ZtreeDto ztreeDto = new ZtreeDto();
            ztreeDto.setId(item.getId());
            ztreeDto.setName(item.getName());
            ztreeDto.setBisFromCase(item.getBisFromCase());
            if (basicApplyBatch.getPlanDetailsId() == null) {
                ztreeDto.setDisplayName(item.getDisplayName());
            } else {
                ztreeDto.setDisplayName(String.format("%s(%s)", item.getDisplayName(), publicService.getUserNameByAccount(item.getExecutor())));
            }
            ztreeDto.setPid(item.getPid());
            ztreeDto.setTableName(item.getTableName());
            ztreeDto.setTableId(item.getTableId());
            if (showTag == true) {
                if (caseBasicApplyBatch == null) {
                    ztreeDto.setBisAdd(true);
                    ztreeDto.setDisplayName(String.format("%s(新增)", item.getDisplayName()));
                } else {
                    BasicApplyBatchDetail caseBasicApplyBatchDetail = getCaseBasicApplyBatchDetail(item, caseBasicApplyBatch.getId());
                    if (item.getBisFromCase() != true) {
                        if (caseBasicApplyBatchDetail == null) {
                            ztreeDto.setBisAdd(true);
                            ztreeDto.setDisplayName(String.format("%s(新增)", item.getDisplayName()));
                        } else {
                            ztreeDto.setBisUpgrade(true);
                            ztreeDto.setDisplayName(String.format("%s(升级)", item.getDisplayName()));
                        }
                    }
                }
            }
            ztreeDto.setType(item.getType());
            ztreeDto.setCreator(item.getCreator());
            ztreeDto.setExecutor(item.getExecutor());
            ztreeDto.setCreatorName(publicService.getUserNameByAccount(item.getCreator()));
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

    /**
     * 获取楼盘树形结构
     *
     * @param caseBatchApplyId
     * @return
     */
    public List<ZtreeDto> getEstateZtreeById(Integer caseBatchApplyId) {
        BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(caseBatchApplyId);
        List<ZtreeDto> treeDtos = new ArrayList<>();
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
                if (basicApplyBatch.getCaseApplyBatchId() != null && basicApplyBatch.getCaseApplyBatchId() > 0) {
                    if (item.getUpgradeTableId() != null && item.getUpgradeTableId() > 0) {
                        ztreeDto.setDisplayName(String.format("%s(升级)", item.getDisplayName()));
                    } else {
                        ztreeDto.setDisplayName(String.format("%s(新增)", item.getDisplayName()));
                    }
                }
            }
            if (StringUtils.isNotEmpty(item.getType())) {
                ztreeDto.setType(item.getType());
            } else {
                BasicFormClassifyEnum anEnum = BasicFormClassifyEnum.getEnumByTableName(item.getTableName());
                ztreeDto.setType(anEnum.getKey());
            }
            ztreeDto.setCreator(item.getCreator());
            ztreeDto.setExecutor(item.getExecutor());
            ztreeDto.setCreatorName(publicService.getUserNameByAccount(item.getCreator()));
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
        BasicApplyBatch caseBasicApplyBatch = getCaseBasicApplyBatch(province, city, estateName);
        if (caseBasicApplyBatch != null) {
            basicApplyBatch.setClassify(caseBasicApplyBatch.getClassify());
            basicApplyBatch.setType(caseBasicApplyBatch.getType());
            basicApplyBatch.setCaseApplyBatchId(caseBasicApplyBatch.getId());
            basicApplyBatch.setEstateId(caseBasicApplyBatch.getEstateId());
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
//                    || AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND_ONLY.equals(o.getFieldName())
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
        if (id == null) return null;
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

    public BasicApplyBatch getBasicApplyBatchByRefId(Integer refId) {
        BasicApplyBatch basicApplyBatch = new BasicApplyBatch();
        basicApplyBatch.setReferenceApplyBatchId(refId);
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
        BaseDataDic classifyDataDic = baseDataDicService.getCacheDataDicById(basicApplyBatch.getClassify());
        BasicFormStructureInterface serviceBean = publicBasicService.getStructureServiceBeanByKey(classifyDataDic.getFieldName());
        return serviceBean.initBasicApplyBatch(basicApplyBatch);
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

    /**
     * 查勘案例申请流程发起
     *
     * @param sourceApplyBatchId
     * @param zTreeData
     * @return
     * @throws Exception
     */
    public void processSurveySubmit(Integer sourceApplyBatchId, String zTreeData) throws Exception {
        if (sourceApplyBatchId == null || sourceApplyBatchId == 0)
            throw new BusinessException("请求参数为空");
        if (StringUtils.isBlank(zTreeData))
            throw new BusinessException("请勾选要处理数据");
        //1.首先将数据拷贝到案例申请这边，状态为草稿状态，并且处理好数据结构，为新增或升级的数据
        //2.首先确定楼盘是否为新增楼盘，当楼盘为新增数据时，楼盘下的数据都为新增数据
        //3.当楼盘为升级的楼盘，则只处理选择后的数据
        BasicApplyBatch sourceApplyBatch = basicApplyBatchDao.getBasicApplyBatchById(sourceApplyBatchId);
        if (sourceApplyBatch.getReferenceApplyBatchId() != null) {
            sourceApplyBatch = basicApplyBatchDao.getBasicApplyBatchById(sourceApplyBatch.getReferenceApplyBatchId());
        }
        BasicApplyBatch targetApplyBatch = new BasicApplyBatch();
        targetApplyBatch.setProvince(sourceApplyBatch.getProvince());
        targetApplyBatch.setCity(sourceApplyBatch.getCity());
        targetApplyBatch.setClassify(sourceApplyBatch.getClassify());
        targetApplyBatch.setType(sourceApplyBatch.getType());
        targetApplyBatch.setBuildingStatus(sourceApplyBatch.getBuildingStatus());
        targetApplyBatch.setDraftFlag(true);
        targetApplyBatch.setBisCase(false);
        targetApplyBatch.setBisDelete(false);
        targetApplyBatch.setCreator(commonService.thisUserAccount());
        BasicApplyBatch caseBasicApplyBatch = getCaseBasicApplyBatch(sourceApplyBatch.getProvince(), sourceApplyBatch.getCity(), sourceApplyBatch.getEstateName());
        if (caseBasicApplyBatch == null) {//新增
            targetApplyBatch.setEstateName(sourceApplyBatch.getEstateName());
        } else {//升级
            targetApplyBatch.setCaseApplyBatchId(caseBasicApplyBatch.getId());
        }
        basicApplyBatchDao.addBasicApplyBatch(targetApplyBatch);

        //1.根据提交到后台的数据，先找出pid为0的数据即为树形结构的根节点
        //2.再找出根节点下的节点数据，依次递归将节点添加新的结构下
        List<ZtreeDto> ztreeDtoList = JSON.parseArray(zTreeData, ZtreeDto.class);
        if (CollectionUtils.isEmpty(ztreeDtoList)) return;
        ZtreeDto rootNode = LangUtils.filter(ztreeDtoList, p -> p.getPid().equals(0)).get(0);//根节点
        BasicApplyBatchDetail applyBatchDetail = new BasicApplyBatchDetail();
        applyBatchDetail.setPid(0);
        applyBatchDetail.setApplyBatchId(targetApplyBatch.getId());
        applyBatchDetail.setType(rootNode.getType());
        setTableIdByNode(applyBatchDetail, rootNode);
        applyBatchDetail.setTableName(rootNode.getTableName());
        applyBatchDetail.setName(rootNode.getName());
        applyBatchDetail.setDisplayName(rootNode.getName());
        applyBatchDetail.setExecutor(commonService.thisUserAccount());
        if (rootNode.getChecked() == Boolean.TRUE) {
            applyBatchDetail.setBisFromCase(false);
        } else {
            applyBatchDetail.setBisFromCase(rootNode.getHalfCheck());
        }
        applyBatchDetail.setBisDelete(false);
        applyBatchDetail.setCreator(commonService.thisUserAccount());
        basicApplyBatchDetailService.saveBasicApplyBatchDetail(applyBatchDetail);
        targetApplyBatch.setEstateName(rootNode.getName());
        targetApplyBatch.setEstateId(rootNode.getTableId());
        basicApplyBatchDao.updateInfo(targetApplyBatch);

        createChildNodeRecursion(targetApplyBatch.getId(), ztreeDtoList, rootNode, applyBatchDetail.getId());
    }

    //递归创建子节点
    private void createChildNodeRecursion(Integer applyBatchId, List<ZtreeDto> ztreeDtoList, ZtreeDto parentNode, Integer newDetailId) {
        List<ZtreeDto> childNodes = LangUtils.filter(ztreeDtoList, p -> p.getPid().equals(parentNode.getId()));//根节点
        if (CollectionUtils.isNotEmpty(childNodes)) {
            for (ZtreeDto childNode : childNodes) {
                BasicApplyBatchDetail applyBatchDetail = new BasicApplyBatchDetail();
                applyBatchDetail.setPid(newDetailId);
                applyBatchDetail.setApplyBatchId(applyBatchId);
                applyBatchDetail.setType(childNode.getType());
                setTableIdByNode(applyBatchDetail, childNode);
                applyBatchDetail.setTableName(childNode.getTableName());
                applyBatchDetail.setName(childNode.getName());
                applyBatchDetail.setDisplayName(childNode.getName());
                applyBatchDetail.setExecutor(commonService.thisUserAccount());
                if (childNode.getChecked() == Boolean.TRUE) {
                    applyBatchDetail.setBisFromCase(false);
                } else {
                    applyBatchDetail.setBisFromCase(childNode.getHalfCheck());
                }
                applyBatchDetail.setBisDelete(false);
                applyBatchDetail.setCreator(commonService.thisUserAccount());
                basicApplyBatchDetailService.saveBasicApplyBatchDetail(applyBatchDetail);
                createChildNodeRecursion(applyBatchId, ztreeDtoList, childNode, applyBatchDetail.getId());
            }
        }
    }

    private void setTableIdByNode(BasicApplyBatchDetail applyBatchDetail, ZtreeDto node) {
        if (applyBatchDetail == null || node == null) return;
        if (node.getChecked() == Boolean.TRUE) {
            BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByKey(node.getType());
            try {
                Object entity = entityAbstract.copyBasicEntity(node.getTableId(), null, true);
                if (entity != null) {
                    applyBatchDetail.setTableId((Integer) entityAbstract.getProperty(entity, "id"));
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        } else {
            applyBatchDetail.setTableId(node.getTableId());
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
            batchDetail.setType(needList.get(i).getType());
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
            BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByKey(ztreeDto.getType());
            Object o = entityAbstract.copyBasicEntityIgnore(ztreeDto.getTableId(), null, true, ignoreList);
            applyBatchDetail.setName(ztreeDto.getName());
            applyBatchDetail.setType(ztreeDto.getType());
            applyBatchDetail.setDisplayName(ztreeDto.getName());
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

    /**
     * 获取楼盘案例数据
     *
     * @param province
     * @param city
     * @param search
     * @return
     */
    public BootstrapTableVo getCaseEstateListByName(String province, String city, String search) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicApplyBatch> basicAppBatchDraftList = basicApplyBatchDao.getCaseEstateListByName(province, city, search);
        List<BasicApplyBatchVo> voList = LangUtils.transform(basicAppBatchDraftList, o -> getBasicApplyBatchVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(voList) ? new ArrayList<BasicApplyBatchVo>() : voList);
        return vo;
    }

    /**
     * 根据区域+楼盘名称查询案例中的楼盘
     *
     * @param province
     * @param city
     * @param estateName
     * @return
     */
    public BasicApplyBatch getCaseBasicApplyBatch(String province, String city, String estateName) {
        BasicApplyBatch where = new BasicApplyBatch();
        where.setProvince(province);
        where.setCity(city);
        where.setEstateName(estateName);
        where.setBisDelete(false);
        where.setBisCase(true);
        BasicApplyBatch basicApplyBatch = basicApplyBatchDao.getBasicApplyBatch(where);
        return basicApplyBatch;
    }

    /**
     * 获取案例下同类型节点数据（确定该节点是否为升级数据）
     *
     * @param source
     * @param caseApplyBatchId
     * @return
     */
    public BasicApplyBatchDetail getCaseBasicApplyBatchDetail(BasicApplyBatchDetail source, Integer caseApplyBatchId) {
        if (source == null || caseApplyBatchId == null) return null;
        List<BasicApplyBatchDetail> list = basicApplyBatchDetailService.getBasicApplyBatchDetailListByType(source.getType(), caseApplyBatchId, null, true);
        if (CollectionUtils.isEmpty(list)) return null;
        for (BasicApplyBatchDetail basicApplyBatchDetail : list) {
            List<Boolean> booleanList = Lists.newArrayList();
            compareParentName(source, basicApplyBatchDetail, booleanList);
            if (LangUtils.filter(booleanList, o -> o.booleanValue() == Boolean.FALSE).size() <= 0) {
                return basicApplyBatchDetail;
            }
        }
        return null;
    }

    /**
     * 递归比较父级节点的名称是否完全一致
     *
     * @param source
     * @param caseTarget
     * @return
     */
    public void compareParentName(BasicApplyBatchDetail source, BasicApplyBatchDetail caseTarget, List<Boolean> booleanList) {
        if (source == null || caseTarget == null) {
            booleanList.add(false);
            return;
        }
        if (source.getName().equals(caseTarget.getName())) {
            booleanList.add(true);
            if (source.getPid() <= 0 && caseTarget.getPid() <= 0) return;
            BasicApplyBatchDetail sourceParent = basicApplyBatchDetailService.getCacheBasicApplyBatchDetailById(source.getPid());
            BasicApplyBatchDetail caseTargetParent = basicApplyBatchDetailService.getCacheBasicApplyBatchDetailById(caseTarget.getPid());
            compareParentName(sourceParent, caseTargetParent, booleanList);
        } else {
            booleanList.add(false);
        }
    }

    /**
     * 获取案例上级
     *
     * @param source
     * @param caseApplyBatchId
     * @return
     */
    public BasicApplyBatchDetail getCaseParentBatchDetail(BasicApplyBatchDetail source, Integer caseApplyBatchId) {
        if (source == null || caseApplyBatchId == null) return null;
        BasicApplyBatchDetail batchDetailParent = basicApplyBatchDetailService.getCacheBasicApplyBatchDetailById(source.getPid());
        List<BasicApplyBatchDetail> detailList = basicApplyBatchDetailService.getBasicApplyBatchDetailByName(batchDetailParent.getType(), batchDetailParent.getName(), caseApplyBatchId);
        if (CollectionUtils.isEmpty(detailList)) return null;
        if (detailList.size() == 1) return detailList.get(0);
        for (BasicApplyBatchDetail basicApplyBatchDetail : detailList) {
            List<Boolean> booleanList = Lists.newArrayList();
            compareParentName(batchDetailParent, basicApplyBatchDetail, booleanList);
            if (LangUtils.filter(booleanList, o -> o.booleanValue() == Boolean.FALSE).size() <= 0) {
                return basicApplyBatchDetail;
            }
        }
        return null;
    }

    /**
     * 通过备选案例引用数据
     *
     * @param basicApplyBatchDetailId
     * @return
     */
    public BasicApplyBatch referenceDataByDetailId(Integer basicApplyBatchDetailId, Integer projectId, Integer planDetailsId) {
        if (basicApplyBatchDetailId == null) return null;
        List<BasicApplyBatchDetail> list = Lists.newArrayList();
        basicApplyBatchDetailService.collectionParentBatchDetails(basicApplyBatchDetailId, list);
        if (CollectionUtils.isEmpty(list)) return null;
        BasicApplyBatchDetail topBatchDetai = list.get(list.size() - 1);
        BasicApplyBatch sourceApplyBatch = basicApplyBatchDao.getBasicApplyBatchById(topBatchDetai.getApplyBatchId());
        BasicApplyBatch newBasicApplyBatch = new BasicApplyBatch();
        BeanUtils.copyProperties(sourceApplyBatch, newBasicApplyBatch, BaseConstant.ASSESS_IGNORE_ARRAY);
        newBasicApplyBatch.setProjectId(projectId);
        newBasicApplyBatch.setPlanDetailsId(planDetailsId);
        newBasicApplyBatch.setDraftFlag(false);
        newBasicApplyBatch.setBisDelete(false);
        newBasicApplyBatch.setCreator(commonService.thisUserAccount());
        basicApplyBatchDao.addBasicApplyBatch(newBasicApplyBatch);
        Integer pid = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            BasicApplyBatchDetail sourceApplyBatchDetail = list.get(i);
            BasicApplyBatchDetail newApplyBatchDetail = new BasicApplyBatchDetail();
            newApplyBatchDetail.setPid(pid);
            newApplyBatchDetail.setApplyBatchId(newBasicApplyBatch.getId());
            newApplyBatchDetail.setTableName(sourceApplyBatchDetail.getTableName());
            BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByKey(sourceApplyBatchDetail.getType());
            try {
                Object entity = entityAbstract.copyBasicEntity(sourceApplyBatchDetail.getTableId(), null, true);
                Integer entityId = (Integer) entityAbstract.getProperty(entity, "id");
                newApplyBatchDetail.setTableId(entityId);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            newApplyBatchDetail.setPlanDetailsId(planDetailsId);
            newApplyBatchDetail.setType(sourceApplyBatchDetail.getType());
            newApplyBatchDetail.setName(sourceApplyBatchDetail.getName());
            newApplyBatchDetail.setDisplayName(sourceApplyBatchDetail.getDisplayName());
            newApplyBatchDetail.setExecutor(commonService.thisUserAccount());
            basicApplyBatchDetailService.saveBasicApplyBatchDetail(newApplyBatchDetail);
            try {
                basicApplyBatchDetailService.insertBasicApply(newApplyBatchDetail, planDetailsId);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
            pid = newApplyBatchDetail.getId();
        }
        return newBasicApplyBatch;
    }

    //写入案例数据
    @Transactional(rollbackFor = Exception.class)
    public void handleCaseData(BasicApplyBatch applyBatch) throws Exception {
        BasicApplyBatch caseBasicApplyBatch = basicApplyBatchService.getCaseBasicApplyBatch(applyBatch.getProvince(), applyBatch.getCity(), applyBatch.getEstateName());
        List<BasicApplyBatchDetail> sourceDetails = basicApplyBatchDetailService.getBasicApplyBatchDetailByApplyBatchId(applyBatch.getId());
        //新增
        if (caseBasicApplyBatch == null) {
            applyBatch.setBisCase(true);
            basicApplyBatchDao.updateInfo(applyBatch);
            //将从数据也设置为案例数据
            if (CollectionUtils.isNotEmpty(sourceDetails)) {
                for (BasicApplyBatchDetail source : sourceDetails) {
                    BasicEntityAbstract anAbstract = publicBasicService.getServiceBeanByKey(source.getType());
                    Object entity = anAbstract.getBasicEntityById(source.getTableId());
                    if (entity != null) {
                        anAbstract.setProperty(entity, "bisCase", true);
                        anAbstract.setProperty(entity, "version", 1);
                        anAbstract.setProperty(entity, "applyId", source.getId());
                        anAbstract.saveAndUpdate(entity, false);
                    }
                    //summary表处理
                    if (source.getType().startsWith(BasicFormClassifyEnum.HOUSE.getKey())) {
                        houseWriteToSummary(source, applyBatch);
                    }
                }
            }
        } else {
            //升级或新增节点
            if (CollectionUtils.isNotEmpty(sourceDetails)) {
                for (BasicApplyBatchDetail source : sourceDetails) {
                    if (source.getBisFromCase() != true) {
                        BasicApplyBatchDetail caseBasicApplyBatchDetail = basicApplyBatchService.getCaseBasicApplyBatchDetail(source, caseBasicApplyBatch.getId());
                        //升级
                        if (caseBasicApplyBatchDetail != null) {
                            //原数据对应表
                            BasicEntityAbstract anAbstract = publicBasicService.getServiceBeanByKey(caseBasicApplyBatchDetail.getType());
                            Object entity = anAbstract.getBasicEntityById(source.getTableId());
                            //获取版本号
                            Object caseEntity = anAbstract.getBasicEntityById(caseBasicApplyBatchDetail.getTableId());
                            Object version = anAbstract.getProperty(caseEntity, "version");

                            anAbstract.setProperty(entity, "version", (Integer) (version == null ? 0 : version) + 1);
                            anAbstract.setProperty(entity, "bisCase", true);
                            anAbstract.setProperty(entity, "applyId", caseBasicApplyBatchDetail.getId());
                            anAbstract.saveAndUpdate(entity, false);

                            caseBasicApplyBatchDetail.setTableId((Integer) anAbstract.getProperty(entity, "id"));
                            basicApplyBatchDetailService.saveBasicApplyBatchDetail(caseBasicApplyBatchDetail);
                        } else {
                            //新增节点
                            BasicApplyBatchDetail caseParentBasicApplyBatchDetail = basicApplyBatchService.getCaseParentBatchDetail(source, caseBasicApplyBatch.getId());
                            if (caseParentBasicApplyBatchDetail != null) {
                                BasicApplyBatchDetail newDetail = new BasicApplyBatchDetail();
                                BeanUtils.copyProperties(source, newDetail, "id");
                                newDetail.setApplyBatchId(caseBasicApplyBatch.getId());
                                newDetail.setPid(caseParentBasicApplyBatchDetail.getId());
                                basicApplyBatchDetailService.saveBasicApplyBatchDetail(newDetail);
                                BasicEntityAbstract anAbstract = publicBasicService.getServiceBeanByKey(newDetail.getType());
                                Object entity = anAbstract.getBasicEntityById(newDetail.getTableId());
                                if (entity != null) {
                                    anAbstract.setProperty(entity, "bisCase", true);
                                    anAbstract.setProperty(entity, "version", 1);
                                    anAbstract.setProperty(entity, "applyId", newDetail.getId());
                                    anAbstract.saveAndUpdate(entity, false);
                                }
                            }
                        }
                        //summary表处理
                        if (source.getType().startsWith(BasicFormClassifyEnum.HOUSE.getKey())) {
                            houseWriteToSummary(source, applyBatch);
                        }
                    }
                }
            }
        }
        applyBatch.setStatus(ProjectStatusEnum.FINISH.getKey());
        basicApplyBatchDao.updateInfo(applyBatch);
    }

    //写入summary
    public void houseWriteToSummary(BasicApplyBatchDetail house, BasicApplyBatch applyBatch) {
        //准备需要的数据
        BasicEstate basicEstate = basicEstateService.getBasicEstateById(applyBatch.getEstateId());
        BasicHouse basicHouse = basicHouseService.getBasicHouseById(house.getTableId());
        if (basicHouse == null) return;
        BasicEstateLandCategoryInfo landCategoryInfo = basicEstateLandCategoryInfoService.getBasicEstateLandCategoryInfoByHouseId(basicHouse.getId());
        cleanSummary(house.getId());

        List<BasicHouseTrading> tradingList = basicHouseTradingService.getTradingListByHouseId(basicHouse.getId());
        if (CollectionUtils.isNotEmpty(tradingList)) {
            for (BasicHouseTrading trading : tradingList) {
                BasicHouseCaseSummary summary = new BasicHouseCaseSummary();
                summary.setCaseHouseId(house.getId());
                summary.setProvince(basicEstate.getProvince());
                summary.setCity(basicEstate.getCity());
                summary.setDistrict(basicEstate.getDistrict());
                summary.setBlockName(basicEstate.getBlockName());
                summary.setFullName(basicApplyBatchDetailService.getFullNameByBatchDetailId(house.getId()));
                summary.setStreet(basicEstate.getStreet());
                summary.setTradingType(trading.getTradingType());
                summary.setTradingTime(trading.getTradingTime());
                summary.setTradingUnitPrice(trading.getTradingUnitPrice());
                if (landCategoryInfo != null) {
                    summary.setHouseType(landCategoryInfo.getLandUseType());
                    summary.setHouseCategory(landCategoryInfo.getLandUseCategory());
                }
                summary.setArea(basicHouse.getArea());
                summary.setEstateName(basicEstate.getName());
                summary.setVersion(basicHouse.getVersion());
                summary.setBisFromSelf(true);
                summary.setBisNewest(true);
                basicHouseCaseSummaryService.addBaseHouseSummary(summary);
            }
        }
    }

    /**
     * 清空汇总数据
     *
     * @param houseId
     */
    public void cleanSummary(Integer houseId) {
        if (houseId == null || houseId <= 0) return;
        BasicHouseCaseSummary where = new BasicHouseCaseSummary();
        where.setCaseHouseId(houseId);
        where.setBisFromSelf(true);
        List<BasicHouseCaseSummary> caseSummaryList = basicHouseCaseSummaryService.getBaseHouseSummaryList(where);
        if (CollectionUtils.isNotEmpty(caseSummaryList)) {
            for (BasicHouseCaseSummary item : caseSummaryList) {
                basicHouseCaseSummaryService.deleteBaseHouseSummaryById(item.getId());
            }
        }
    }

    /**
     * 根据项目id获取该项目下的楼盘名称数据
     *
     * @param projectId
     * @return
     */
    public List<String> getEstateNameListByProjectId(Integer projectId) {
        List<String> list = Lists.newArrayList();
        BasicApplyBatch where = new BasicApplyBatch();
        where.setProjectId(projectId);
        List<BasicApplyBatch> infoList = basicApplyBatchDao.getInfoList(where);
        if (CollectionUtils.isEmpty(infoList)) return list;
        for (BasicApplyBatch item : infoList) {
            StringBuilder stringBuilder = new StringBuilder();
            if (StringUtils.isNotBlank(item.getProvince()))
                stringBuilder.append(erpAreaService.getSysAreaName(item.getProvince())).append("/");
            if (StringUtils.isNotBlank(item.getCity()))
                stringBuilder.append(erpAreaService.getSysAreaName(item.getCity())).append("/");
            stringBuilder.append(item.getEstateName());
            list.add(stringBuilder.toString());
        }
        return list;
    }

    /**
     * 判断是否应该引用楼盘数据
     *
     * @param projectId
     * @param batchDetailId
     * @param province
     * @param city
     * @param estateName
     * @return
     */
    public Boolean isNeedReferenceEstate(Integer projectId, Integer batchDetailId, String province, String city, String estateName) {
        //1.先判断是否已引用
        BasicApplyBatchDetail applyBatchDetail = basicApplyBatchDetailService.getDataById(batchDetailId);
        if (applyBatchDetail == null) {
            return false;
        }
        //判断是否在同项目或案例库中存在相同楼盘
        return false;
    }
}
