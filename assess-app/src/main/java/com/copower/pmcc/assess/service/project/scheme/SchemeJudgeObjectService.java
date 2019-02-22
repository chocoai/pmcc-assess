package com.copower.pmcc.assess.service.project.scheme;


import com.copower.pmcc.assess.common.enums.AssessUploadEnum;
import com.copower.pmcc.assess.common.enums.ComputeDataTypeEnum;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.entity.BasicApply;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateTagging;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouse;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeSurePriceFactorDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeProgrammeDto;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicEstateTaggingService;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.data.DataBestUseDescriptionService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 估价对象
 * Created by 13426 on 2018/5/21.
 */
@Service
public class SchemeJudgeObjectService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SchemeSurePriceFactorDao schemeSurePriceFactorDao;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private DataBestUseDescriptionService dataBestUseDescriptionService;
    @Autowired
    private DataSetUseFieldService dataSetUseFieldService;
    @Autowired
    private DeclareRecordService declareRecordService;

    public boolean addSchemeJudgeObject(SchemeJudgeObject schemeJudgeObject) {
        return schemeJudgeObjectDao.addSchemeJudgeObject(schemeJudgeObject);
    }

    public boolean updateSchemeJudgeObject(SchemeJudgeObject schemeJudgeObject) {
        return schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
    }


    public SchemeJudgeObject getSchemeJudgeObject(Integer id) {
        return schemeJudgeObjectDao.getSchemeJudgeObject(id);
    }

    /**
     * 获取估价对象数据列表
     *
     * @param areaGroupId
     * @return
     */
    public List<SchemeJudgeObjectVo> getSchemeJudgeObjectList(Integer areaGroupId) {
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectDao.getSchemeJudgeObjectList(areaGroupId);
        return LangUtils.transform(schemeJudgeObjectList, o -> getSchemeJudgeObjectVo(o));
    }

    public List<SchemeJudgeObject> getJudgeObjectListByProjectId(Integer projectId) {
        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
        schemeJudgeObject.setProjectId(projectId);
        schemeJudgeObject.setBisEnable(true);
        return schemeJudgeObjectDao.getJudgeObjectList(schemeJudgeObject);
    }

    public List<SchemeJudgeObject> getJudgeObjectListByAreaGroupId(Integer areaGroupId) {
        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
        schemeJudgeObject.setAreaGroupId(areaGroupId);
        return schemeJudgeObjectDao.getJudgeObjectList(schemeJudgeObject);
    }

    /**
     * 获取启用的委估对象
     *
     * @param areaGroupId
     * @return
     */
    public List<SchemeJudgeObject> getJudgeObjectApplicableListByAreaGroupId(Integer areaGroupId) {
        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
        schemeJudgeObject.setAreaGroupId(areaGroupId);
        schemeJudgeObject.setBisEnable(true);
        return schemeJudgeObjectDao.getJudgeObjectList(schemeJudgeObject);
    }

    /**
     * 获取区域下完整的委估对象
     *
     * @return
     */
    public List<SchemeJudgeObject> getJudgeObjectFullListByAreaId(Integer areaGroupId) {
        SchemeJudgeObject where = new SchemeJudgeObject();
        where.setAreaGroupId(areaGroupId);
        where.setBisMerge(false);
        List<SchemeJudgeObject> judgeObjects = schemeJudgeObjectDao.getJudgeObjectList(where);
        return judgeObjects;
    }

    public List<SchemeJudgeObject> getChildrenJudgeObject(Integer id) {
        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
        schemeJudgeObject.setPid(id);
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getJudgeObjectList(schemeJudgeObject);
        return judgeObjectList;
    }

    public List<SchemeJudgeObjectVo> getListByPid(Integer pid) {
        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
        schemeJudgeObject.setPid(pid);
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getJudgeObjectList(schemeJudgeObject);
        if (CollectionUtils.isEmpty(judgeObjectList)) return null;
        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordListByIds(LangUtils.transform(judgeObjectList, o -> o.getDeclareRecordId()));
        List<SchemeJudgeObjectVo> judgeObjectVoList = Lists.newArrayList();
        for (SchemeJudgeObject judgeObject : judgeObjectList) {
            SchemeJudgeObjectVo schemeJudgeObjectVo = new SchemeJudgeObjectVo();
            BeanUtils.copyProperties(judgeObject, schemeJudgeObjectVo);
            schemeJudgeObjectVo.setCoefficient(getCoefficientByDeclareId(judgeObject.getDeclareRecordId()));
            for (DeclareRecord declareRecord : declareRecords) {
                if (declareRecord.getId().equals(judgeObject.getDeclareRecordId())) {
                    schemeJudgeObjectVo.setFloor(declareRecord.getFloor());
                    schemeJudgeObjectVo.setRoomNumber(declareRecord.getRoomNumber());
                }
            }
            judgeObjectVoList.add(schemeJudgeObjectVo);
        }
        if (CollectionUtils.isNotEmpty(judgeObjectVoList)) {
            //根据楼层编号和房号排序
            judgeObjectVoList.sort((o1, o2) -> {
                int result = 0;
                if (NumberUtils.isNumber(o1.getFloor()) && NumberUtils.isNumber(o2.getFloor())) {
                    result = Integer.compare(NumberUtils.toInt(o1.getFloor()), NumberUtils.toInt(o2.getFloor()));
                }
                if (result == 0) {//如果楼层相同再根据房号判断
                    if (NumberUtils.isNumber(o1.getRoomNumber()) && NumberUtils.isNumber(o2.getRoomNumber())) {
                        result = Integer.compare(NumberUtils.toInt(o1.getRoomNumber()), NumberUtils.toInt(o2.getRoomNumber()));
                    }
                }
                return result;
            });
        }
        return judgeObjectVoList;
    }

    public String getCoefficientByDeclareId(Integer declareId) {
        List<SchemeSurePriceFactor> factorList = schemeSurePriceFactorDao.getFactorListByDeclareId(declareId);
        if (CollectionUtils.isNotEmpty(factorList)) {
            StringBuilder coefficient = new StringBuilder();
            for (SchemeSurePriceFactor schemeSurePriceFactor : factorList) {
                coefficient.append(schemeSurePriceFactor.getFactor()).append(schemeSurePriceFactor.getRemark()).append(":");
                if (schemeSurePriceFactor.getType().equals(ComputeDataTypeEnum.ABSOLUTE.getId())) {
                    coefficient.append(schemeSurePriceFactor.getCoefficient().setScale(2));
                } else {
                    coefficient.append(schemeSurePriceFactor.getCoefficient().multiply(new BigDecimal("100")).setScale(2)).append("%");
                }
                coefficient.append(";\r\n");
            }
            return coefficient.toString();
        }
        return null;
    }

    /**
     * 获取估价对象数据列表
     *
     * @param pid
     * @return
     */
    public BootstrapTableVo getJudgeObjectListByPid(Integer pid) {
        BootstrapTableVo vo = new BootstrapTableVo();
        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
        schemeJudgeObject.setPid(pid);
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getJudgeObjectList(schemeJudgeObject);
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(judgeObjectList) ? new ArrayList<SchemeJudgeObject>() : judgeObjectList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public boolean removeSchemeJudgeObject(Integer id) {
        return schemeJudgeObjectDao.removeSchemeJudgeObject(id);
    }

    /**
     * 更新委估对象区域分组id
     *
     * @param oldAreaGroupId
     * @param newAreaGroupId
     * @return
     */
    public boolean updateSchemeJudgeObject(Integer oldAreaGroupId, Integer newAreaGroupId) {
        return schemeJudgeObjectDao.updateSchemeJudgeObject(oldAreaGroupId, newAreaGroupId);
    }

    /**
     * 权证所属区域还原
     *
     * @param originalAreaGroupId
     * @return
     */
    public void areaGroupReduction(Integer originalAreaGroupId) {
        SchemeJudgeObject where = new SchemeJudgeObject();
        where.setOriginalAreaGroupId(originalAreaGroupId);
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getJudgeObjectList(where);
        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
            for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
                schemeJudgeObject.setAreaGroupId(schemeJudgeObject.getOriginalAreaGroupId());
                schemeJudgeObject.setNumber(schemeJudgeObject.getOriginalNumber());
                schemeJudgeObject.setOriginalAreaGroupId(null);
                schemeJudgeObject.setOriginalNumber(null);
                schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
            }
        }
    }

    /**
     * 获取vo
     *
     * @param schemeJudgeObject
     * @return
     */
    public SchemeJudgeObjectVo getSchemeJudgeObjectVo(SchemeJudgeObject schemeJudgeObject) {
        SchemeJudgeObjectVo schemeJudgeObjectVo = new SchemeJudgeObjectVo();
        BeanUtils.copyProperties(schemeJudgeObject, schemeJudgeObjectVo);
        if (schemeJudgeObject.getSetUse() != null) {
            DataSetUseField setUseField = dataSetUseFieldService.getCacheSetUseFieldById(schemeJudgeObject.getSetUse());
            if (setUseField != null)
                schemeJudgeObjectVo.setSetUseName(setUseField.getName());
        }
        if (schemeJudgeObject.getBestUse() != null) {
            DataBestUseDescription bestUseDescription = dataBestUseDescriptionService.getCacheBestUseDescriptionById(schemeJudgeObject.getBestUse());
            if (bestUseDescription != null)
                schemeJudgeObjectVo.setBestUseName(bestUseDescription.getName());
        }
        return schemeJudgeObjectVo;
    }

    /**
     * 权证拆分
     *
     * @param id
     */
    @Transactional
    public void splitJudge(Integer projectId, Integer areaGroupId, Integer id) {
        //1.找出该权证已拆分的数据，以确定新权证的拆分序号
        //2.添加拆分数据,如果没有拆分数据，则需将主数据的拆分号设置为1
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(id);
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByNumber(projectId, areaGroupId, schemeJudgeObject.getNumber());
        if (judgeObjectList.size() == 1) {
            schemeJudgeObject.setSplitNumber(1);
            schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
        }
        schemeJudgeObject.setId(null);
        schemeJudgeObject.setSplitNumber(judgeObjectList.size() + 1);
        schemeJudgeObject.setName(String.format("%s-%s%s", schemeJudgeObject.getNumber(), schemeJudgeObject.getSplitNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
        schemeJudgeObject.setBisSplit(true);
        schemeJudgeObject.setBisSetFunction(false);
        schemeJudgeObjectDao.addSchemeJudgeObject(schemeJudgeObject);
    }

    /**
     * 删除拆分出来的权证
     *
     * @param id
     */
    @Transactional
    public void delSplitJudge(Integer projectId, Integer areaGroupId, Integer id) {
        //1.删除该条数据 2.将相同委估对象编号下的拆分数据的拆分号重新编号
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(id);
        String number = schemeJudgeObject.getNumber();
        Integer splitNumber = schemeJudgeObject.getSplitNumber();
        schemeJudgeObjectDao.removeSchemeJudgeObject(id);
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByNumber(projectId, areaGroupId, number);
        if (CollectionUtils.isEmpty(judgeObjectList)) return;
        SchemeJudgeObject judgeObject = null;
        if (judgeObjectList.size() == 1) {
            judgeObject = judgeObjectList.get(0);
            judgeObject.setSplitNumber(0);
            schemeJudgeObjectDao.updateSchemeJudgeObject(judgeObject);
        } else {
            for (SchemeJudgeObject object : judgeObjectList) {
                if (object.getSplitNumber() >= splitNumber) {
                    object.setSplitNumber(splitNumber);
                    schemeJudgeObjectDao.updateSchemeJudgeObject(object);
                    splitNumber++;
                }
            }
        }
    }

    /**
     * 委估对象合并
     *
     * @param ids
     */
    @Transactional
    public void mergeJudge(String ids) throws BusinessException {
        //1.循环需要合并的委估对象，将委估对象编号、权证号、所有权人、坐落、证载面积、评估面积等信息进行合并；其它信息取第一个权证信息
        //2.添加出合并委估对象，将参与合并的委估对象pid设置为新委估对象id，参与合并的委估对象设置为不可用

        //先验证如果不是同一区域的委估对象，则不允许合并

        List<Integer> judgeIds = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeIds);
        if (CollectionUtils.isEmpty(judgeObjectList) || judgeObjectList.size() <= 1)
            throw new BusinessException("参与合并的委估对象至少为两个");
        SchemeJudgeObject mergeJudgeObject = new SchemeJudgeObject();
        BeanUtils.copyProperties(judgeObjectList.get(0), mergeJudgeObject);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            if (!mergeJudgeObject.getAreaGroupId().equals(schemeJudgeObject.getAreaGroupId()))
                throw new BusinessException("参与合并的委估对象必须属于同一区域");
        }

        StringBuilder numberBuilder = new StringBuilder();
        BigDecimal floorAreaTotal = new BigDecimal("0");
        BigDecimal evaluationAreaTotal = new BigDecimal("0");
        StringBuilder rpdBuilder = new StringBuilder();
        StringBuilder collateralBuilder = new StringBuilder();
        List<String> ownershipList = Lists.newArrayList();
        List<String> seatList = Lists.newArrayList();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            //委估对象编号合并
            numberBuilder.append(schemeJudgeObject.getNumber());
            if (schemeJudgeObject.getSplitNumber() != null && schemeJudgeObject.getSplitNumber() > 0)
                numberBuilder.append("-").append(schemeJudgeObject.getSplitNumber());
            numberBuilder.append(",");
            if (schemeJudgeObject.getFloorArea() != null)
                floorAreaTotal = floorAreaTotal.add(schemeJudgeObject.getFloorArea());
            if (schemeJudgeObject.getEvaluationArea() != null)
                evaluationAreaTotal = evaluationAreaTotal.add(schemeJudgeObject.getEvaluationArea());
            if (StringUtils.isNotBlank(schemeJudgeObject.getRentalPossessionDesc()))
                rpdBuilder.append(schemeJudgeObject.getRentalPossessionDesc()).append("\r\n");
            if (StringUtils.isNotBlank(schemeJudgeObject.getCollateralFound()))
                collateralBuilder.append(schemeJudgeObject.getCollateralFound()).append("\r\n");
            ownershipList.add(schemeJudgeObject.getOwnership());
            seatList.add(schemeJudgeObject.getSeat());
        }
        mergeJudgeObject.setId(null);
        mergeJudgeObject.setPid(0);
        mergeJudgeObject.setSplitNumber(null);
        mergeJudgeObject.setNumber(StringUtils.strip(numberBuilder.toString(), ","));
        mergeJudgeObject.setName(String.format("%s%s", mergeJudgeObject.getNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
        mergeJudgeObject.setOwnership(publicService.districtString(ownershipList));
        mergeJudgeObject.setSeat(publicService.fusinString(seatList));
        mergeJudgeObject.setFloorArea(floorAreaTotal);
        mergeJudgeObject.setEvaluationArea(evaluationAreaTotal);
        mergeJudgeObject.setRentalPossessionDesc(rpdBuilder.toString());
        mergeJudgeObject.setCollateralFound(collateralBuilder.toString());
        mergeJudgeObject.setBisSplit(false);
        mergeJudgeObject.setBisEnable(true);
        mergeJudgeObject.setBisMerge(true);
        mergeJudgeObject.setBisSetFunction(false);
        mergeJudgeObject.setCreator(commonService.thisUserAccount());
        schemeJudgeObjectDao.addSchemeJudgeObject(mergeJudgeObject);

        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            schemeJudgeObject.setPid(mergeJudgeObject.getId());
            schemeJudgeObject.setBisEnable(false);
            schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
        }
    }

    /**
     * 委估对象合并取消
     *
     * @param id
     */
    @Transactional
    public void mergeJudgeCancel(Integer id) {
        //1.将原参与合并的委估对象设置为可用，删除该合并的委估对象
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(id);
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByPid(schemeJudgeObject.getId());
        for (SchemeJudgeObject judgeObject : judgeObjectList) {
            judgeObject.setBisEnable(true);
            schemeJudgeObjectDao.updateSchemeJudgeObject(judgeObject);
        }
        schemeJudgeObjectDao.removeSchemeJudgeObject(id);
    }

    /**
     * 保存区域下方案
     *
     * @param schemeProgrammeDto
     */
    @Transactional
    public void saveProgrammeArea(SchemeProgrammeDto schemeProgrammeDto) throws BusinessException {
        if (schemeProgrammeDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.get(schemeProgrammeDto.getAreaGroupId());
        schemeAreaGroup.setValueTimePoint(schemeProgrammeDto.getValueTimePoint());
        schemeAreaGroup.setTimePointExplain(schemeProgrammeDto.getTimePointExplain());
        schemeAreaGroup.setValueDefinition(schemeProgrammeDto.getValueDefinition());
        schemeAreaGroup.setValueDefinitionDesc(schemeProgrammeDto.getValueDefinitionDesc());
        schemeAreaGroup.setValueConnotation(schemeProgrammeDto.getValueConnotation());
        schemeAreaGroup.setValueConnotationDesc(schemeProgrammeDto.getValueConnotationDesc());
        schemeAreaGroup.setEntrustPurpose(schemeProgrammeDto.getEntrustmentPurpose());
        schemeAreaGroup.setRemarkEntrustPurpose(schemeProgrammeDto.getRemarkEntrustPurpose());
        schemeAreaGroupService.saveAreaGroup(schemeAreaGroup);

        List<SchemeJudgeObject> schemeJudgeObjects = schemeProgrammeDto.getSchemeJudgeObjects();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjects)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjects) {
                if (schemeJudgeObject.getId() != null && schemeJudgeObject.getId() > 0) {
                    schemeJudgeObject.setGmtModified(new Date());
                    schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
                }
            }
        }
    }

    /**
     * 保存方案所有内容
     *
     * @param schemeProgrammeDtos
     */
    @Transactional
    public void saveProgrammeAll(List<SchemeProgrammeDto> schemeProgrammeDtos) throws BusinessException {
        if (schemeProgrammeDtos == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        for (SchemeProgrammeDto schemeProgrammeDto : schemeProgrammeDtos) {
            saveProgrammeArea(schemeProgrammeDto);
        }
    }


    /**
     * 提交方案
     *
     * @param schemeProgrammeDtos
     */
    @Transactional
    public void submitProgramme(Integer projectId, Integer planId, List<SchemeProgrammeDto> schemeProgrammeDtos) throws BusinessException {
        //1.验证数据的完整性与准确性，查看评估方法是否都已设置
        //2.清除该计划下的所有任务，保存方案数据
        //3.生成计划任务
        int count = schemeJudgeObjectDao.getNotSetFunctionCount(projectId);
        if (count > 0)
            throw new BusinessException("还有委估对象未设置评估方法请检查");
        saveProgrammeAll(schemeProgrammeDtos);
        projectPlanDetailsService.deletePlanDetailsByPlanId(planId);
        List<SchemeAreaGroup> areaGroupList = schemeAreaGroupService.getAreaGroupList(projectId);


        if (CollectionUtils.isNotEmpty(areaGroupList)) {
            ProjectPlan projectPlan = projectPlanService.getProjectplanById(planId);
            ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
            ProjectPhase phaseSurePrice = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.SURE_PRICE, projectInfo.getProjectCategoryId());
            ProjectPhase phaseLiquidationAnalysis = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.LIQUIDATION_ANALYSIS, projectInfo.getProjectCategoryId());
            ProjectPhase phaseReimbursement = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.REIMBURSEMENT, projectInfo.getProjectCategoryId());
            ProjectPhase supportInfo = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.SUPPORT_INFO, projectInfo.getProjectCategoryId());
            ProjectPhase reportFile = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.REPORT_FILE, projectInfo.getProjectCategoryId());
            String mortgageKey = baseDataDicService.getCacheDataDicById(projectInfo.getEntrustPurpose()).getFieldName();
            int i = 0;
            Map<Integer, ProjectPhase> phaseMap = getProjectPhaseMap(projectInfo.getProjectCategoryId());
            List<ProjectPhase> areaProjectPhases = Lists.newArrayList(supportInfo, reportFile);
            List<ProjectPhase> judgeProjectPhases = Lists.newArrayList(phaseSurePrice);
            if (StringUtils.equals(mortgageKey, AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE)) {//如果是抵押评估还需添加事项，变现分析税费、法定优先受偿款
                judgeProjectPhases.add(phaseLiquidationAnalysis);
                judgeProjectPhases.add(phaseReimbursement);
            }
            for (SchemeAreaGroup schemeAreaGroup : areaGroupList) {
                ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
                projectPlanDetails.setProjectWorkStageId(projectPlan.getWorkStageId());
                projectPlanDetails.setPlanId(projectPlan.getId());
                projectPlanDetails.setProjectId(projectPlan.getProjectId());
                projectPlanDetails.setProjectPhaseName(schemeAreaGroup.getAreaName());
                projectPlanDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                projectPlanDetails.setBisLastLayer(false);
                projectPlanDetails.setSorting(i++);
                projectPlanDetailsDao.addProjectPlanDetails(projectPlanDetails);

                if (CollectionUtils.isNotEmpty(areaProjectPhases)) {
                    for (ProjectPhase projectPhase : areaProjectPhases) {
                        ProjectPlanDetails details = new ProjectPlanDetails();
                        details.setProjectWorkStageId(projectPlan.getWorkStageId());
                        details.setPlanId(projectPlan.getId());
                        details.setProjectId(projectPlan.getProjectId());
                        details.setProjectPhaseName(projectPhase.getProjectPhaseName());
                        details.setProjectPhaseId(projectPhase.getId());
                        details.setAreaId(schemeAreaGroup.getId());
                        details.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                        details.setPid(projectPlanDetails.getId());
                        details.setBisLastLayer(true);
                        details.setSorting(i++);
                        projectPlanDetailsDao.addProjectPlanDetails(details);
                    }
                }

                List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getSchemeJudgeObjectList(schemeAreaGroup.getId());
                if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                    for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
                        ProjectPlanDetails planDetails = new ProjectPlanDetails();
                        planDetails.setProjectWorkStageId(projectPlan.getWorkStageId());
                        planDetails.setPlanId(projectPlan.getId());
                        planDetails.setProjectId(projectPlan.getProjectId());
                        StringBuilder phaseName = new StringBuilder(schemeJudgeObject.getNumber());
                        if (schemeJudgeObject.getSplitNumber() != null && schemeJudgeObject.getSplitNumber() > 0) {
                            phaseName.append("-").append(schemeJudgeObject.getSplitNumber());
                        }
                        phaseName.append(BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME);
                        planDetails.setProjectPhaseName(phaseName.toString());
                        planDetails.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                        planDetails.setPid(projectPlanDetails.getId());
                        planDetails.setJudgeObjectId(schemeJudgeObject.getId());
                        planDetails.setBisLastLayer(false);
                        planDetails.setSorting(i++);
                        projectPlanDetailsDao.addProjectPlanDetails(planDetails);

                        List<SchemeJudgeFunction> judgeFunctions = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                        if (CollectionUtils.isNotEmpty(judgeFunctions)) {
                            for (SchemeJudgeFunction judgeFunction : judgeFunctions) {
                                ProjectPhase projectPhase = phaseMap.get(judgeFunction.getMethodType());
                                ProjectPlanDetails details = new ProjectPlanDetails();
                                details.setProjectWorkStageId(projectPlan.getWorkStageId());
                                details.setPlanId(projectPlan.getId());
                                details.setProjectId(projectPlan.getProjectId());
                                details.setProjectPhaseName(projectPhase.getProjectPhaseName());
                                details.setProjectPhaseId(projectPhase.getId());
                                details.setJudgeObjectId(schemeJudgeObject.getId());
                                details.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                                details.setPid(planDetails.getId());
                                details.setBisLastLayer(true);
                                details.setSorting(i++);
                                projectPlanDetailsDao.addProjectPlanDetails(details);
                            }
                        }

                        if (CollectionUtils.isNotEmpty(judgeProjectPhases)) {
                            for (ProjectPhase projectPhase : judgeProjectPhases) {
                                ProjectPlanDetails details = new ProjectPlanDetails();
                                details.setProjectWorkStageId(projectPlan.getWorkStageId());
                                details.setPlanId(projectPlan.getId());
                                details.setProjectId(projectPlan.getProjectId());
                                details.setProjectPhaseName(projectPhase.getProjectPhaseName());
                                details.setProjectPhaseId(projectPhase.getId());
                                details.setJudgeObjectId(schemeJudgeObject.getId());
                                details.setStatus(ProcessStatusEnum.NOPROCESS.getValue());
                                details.setPid(planDetails.getId());
                                details.setBisLastLayer(true);
                                details.setSorting(i++);
                                projectPlanDetailsDao.addProjectPlanDetails(details);
                            }
                        }
                        this.makeJudgeObjectPosition(Lists.newArrayList(schemeJudgeObject.getId()));
                    }
                }
            }
        }
    }

    private Map<Integer, ProjectPhase> getProjectPhaseMap(Integer categoryId) {
        //1.取得该阶段所有事项 2.取得方法下字典数据配置
        Map<Integer, ProjectPhase> map = Maps.newHashMap();
        List<BaseDataDic> methodList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_EVALUATION_METHOD);
        for (BaseDataDic method : methodList) {
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(method.getFieldName(), categoryId);
            if (projectPhase != null)
                map.put(method.getId(), projectPhase);
        }
        return map;
    }

    /**
     * 生成委估对象的位置示意图
     *
     * @param judgeObjectIds
     */
    public void makeJudgeObjectPosition(List<Integer> judgeObjectIds) {
        if (CollectionUtils.isEmpty(judgeObjectIds)) return;
        for (Integer judgeObjectId : judgeObjectIds) {
            SchemeJudgeObject judgeObject = this.getSchemeJudgeObject(judgeObjectId);
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            List<BasicEstateTagging> taggingList = basicEstateTaggingService.getEstateTaggingList(basicApply.getId(), EstateTaggingTypeEnum.UNIT.getKey());
            if (CollectionUtils.isNotEmpty(taggingList)) {
                BasicEstateTagging tagging = taggingList.get(0);
                SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
                sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SchemeJudgeObject.class));
                sysAttachmentDto.setTableId(judgeObject.getId());
                sysAttachmentDto.setProjectId(judgeObject.getProjectId());
                sysAttachmentDto.setFieldsName(AssessUploadEnum.JUDGE_OBJECT_POSITION.getKey());
                sysAttachmentDto.setFileName("位置示意图.jpg");
                publicService.downLoadLocationImage(tagging.getLng(), tagging.getLat(), sysAttachmentDto);
            }
        }
    }

    /**
     * 根据申报id获取查勘中房屋信息
     *
     * @param declareId
     * @return
     */
    public BasicHouse getBasicHouseByDeclareId(Integer declareId) {
        try {
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(AssessPhaseKeyConstant.SCENE_EXPLORE);
            ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetails(declareId, projectPhase.getId());
            BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(planDetails.getId());
            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
            return basicHouse;
        } catch (Exception e) {
            logger.error("获取房屋信息异常", e);
            return null;
        }
    }

    /**
     * 更新出租占用情况
     *
     * @param id
     * @param rentalPossessionDesc
     */
    public void updateRentalPossessionDesc(Integer id, String rentalPossessionDesc) throws BusinessException {
        SchemeJudgeObject judgeObject = this.getSchemeJudgeObject(id);
        if (judgeObject == null)
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
        judgeObject.setRentalPossessionDesc(rentalPossessionDesc);
        this.updateSchemeJudgeObject(judgeObject);
    }
}
