package com.copower.pmcc.assess.service.project.scheme;


import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ComputeDataTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeSurePriceFactorDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeProgrammeDto;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.data.DataBestUseDescriptionService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.event.project.ProgrammeProcessEvent;
import com.copower.pmcc.assess.service.project.*;
import com.copower.pmcc.assess.service.project.change.ProjectWorkStageService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.generate.GenerateReportInfoService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProjectTaskService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private ApplicationConstant applicationConstant;
    @Autowired
    private BpmRpcProjectTaskService bpmRpcProjectTaskService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private DataBestUseDescriptionService dataBestUseDescriptionService;
    @Autowired
    private DataSetUseFieldService dataSetUseFieldService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private SchemeInfoService schemeInfoService;
    @Autowired
    private SchemeSurePriceService schemeSurePriceService;
    @Autowired
    private GenerateReportInfoService generateReportInfoService;
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

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
     * 不会获取合并的对象
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

    /**
     * 获取区域下与权证对应的委估对象
     * 不会获取到合并与拆分的对象，只与权证1对1
     *
     * @return
     */
    public List<SchemeJudgeObject> getJudgeObjectDeclareListByAreaId(Integer areaGroupId) {
        SchemeJudgeObject where = new SchemeJudgeObject();
        where.setAreaGroupId(areaGroupId);
        where.setBisMerge(false);
        where.setBisSplit(false);
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
            schemeJudgeObjectVo.setCoefficient(getFactorListByJudgeObjectId(judgeObject.getId()));
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

    /**
     * 获取待调整价格的估价对象
     *
     * @param judgeObjectId
     * @return
     */
    public List<SchemeJudgeObjectVo> getAdjustObjectListByPid(Integer judgeObjectId) {
        SchemeJudgeObject judgeObject = getSchemeJudgeObject(judgeObjectId);
        List<SchemeJudgeObjectVo> vos = getListByPid(judgeObjectId);
        if (CollectionUtils.isEmpty(vos)) return null;
        return vos.stream().filter(o -> !o.getId().equals(judgeObject.getStandardJudgeId())).collect(Collectors.toList());
    }

    public String getFactorListByJudgeObjectId(Integer declareId) {
        List<SchemeSurePriceFactor> factorList = schemeSurePriceFactorDao.getFactorListByJudgeObjectId(declareId);
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
        String s = getFactorListByJudgeObjectId(schemeJudgeObjectVo.getId());
        if (StringUtils.isNotBlank(s)) {
            schemeJudgeObjectVo.setCoefficient(s);
        }
        return schemeJudgeObjectVo;
    }

    /**
     * 权证拆分
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void splitJudge(Integer projectId, Integer areaGroupId, Integer id) {
        //1.找出该权证已拆分的数据，以确定新权证的拆分序号
        //2.添加拆分数据,如果没有拆分数据，则需将主数据的拆分号设置为1
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(id);
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByNumber(projectId, areaGroupId, schemeJudgeObject.getNumber());
        if (judgeObjectList.size() == 1) {
            schemeJudgeObject.setSplitNumber(1);
            schemeJudgeObject.setName(String.format("%s-%s%s", schemeJudgeObject.getNumber(), schemeJudgeObject.getSplitNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
            schemeJudgeObject.setEvaluationArea(new BigDecimal("0"));
            schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
        }
        SchemeJudgeObject splitJudgeObject = new SchemeJudgeObject();
        splitJudgeObject.setPid(0);
        splitJudgeObject.setProjectId(schemeJudgeObject.getProjectId());
        splitJudgeObject.setAreaGroupId(schemeJudgeObject.getAreaGroupId());
        splitJudgeObject.setOriginalAreaGroupId(schemeJudgeObject.getOriginalAreaGroupId());
        splitJudgeObject.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
        splitJudgeObject.setNumber(schemeJudgeObject.getNumber());
        splitJudgeObject.setSplitNumber(judgeObjectList.size() + 1);
        splitJudgeObject.setName(String.format("%s-%s%s", schemeJudgeObject.getNumber(), splitJudgeObject.getSplitNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
        splitJudgeObject.setCertName(schemeJudgeObject.getCertName());
        splitJudgeObject.setOwnership(schemeJudgeObject.getOwnership());
        splitJudgeObject.setSeat(schemeJudgeObject.getSeat());
        splitJudgeObject.setCertUse(schemeJudgeObject.getCertUse());
        splitJudgeObject.setPracticalUse(schemeJudgeObject.getPracticalUse());
        splitJudgeObject.setLandCertUse(schemeJudgeObject.getLandCertUse());
        splitJudgeObject.setLandPracticalUse(schemeJudgeObject.getPracticalUse());
        splitJudgeObject.setLandUseEndDate(schemeJudgeObject.getLandUseEndDate());
        splitJudgeObject.setLandRemainingYear(schemeJudgeObject.getLandRemainingYear());
        splitJudgeObject.setSetUse(schemeJudgeObject.getSetUse());
        splitJudgeObject.setBestUse(schemeJudgeObject.getBestUse());
        splitJudgeObject.setFloorArea(schemeJudgeObject.getFloorArea());
        splitJudgeObject.setBisSplit(true);
        splitJudgeObject.setBisSetFunction(false);
        splitJudgeObject.setBisEnable(true);
        splitJudgeObject.setSorting(schemeJudgeObject.getSorting());
        splitJudgeObject.setCreator(commonService.thisUserAccount());
        schemeJudgeObjectDao.addSchemeJudgeObject(splitJudgeObject);
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
            judgeObject.setName(String.format("%s%s", schemeJudgeObject.getNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
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
    public void mergeJudge(String ids, Integer standardJudgeId) throws BusinessException {
        //1.循环需要合并的委估对象，将委估对象编号、权证号、所有权人、坐落、证载面积、评估面积等信息进行合并；其它信息取第一个权证信息
        //2.添加出合并委估对象，将参与合并的委估对象pid设置为新委估对象id，参与合并的委估对象设置为不可用
        //先验证如果不是同一区域的委估对象，则不允许合并

        List<Integer> judgeIds = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeIds);
        if (CollectionUtils.isEmpty(judgeObjectList) || judgeObjectList.size() <= 1)
            throw new BusinessException("参与合并的委估对象至少为两个");
        SchemeJudgeObject mergeJudgeObject = new SchemeJudgeObject();
        BeanUtils.copyProperties(schemeJudgeObjectDao.getSchemeJudgeObject(standardJudgeId), mergeJudgeObject);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            if (!mergeJudgeObject.getAreaGroupId().equals(schemeJudgeObject.getAreaGroupId()))
                throw new BusinessException("参与合并的委估对象必须属于同一区域");
        }

        StringBuilder numberBuilder = new StringBuilder();
        BigDecimal floorAreaTotal = new BigDecimal("0");
        BigDecimal evaluationAreaTotal = new BigDecimal("0");
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
            ownershipList.add(schemeJudgeObject.getOwnership());
            seatList.add(schemeJudgeObject.getSeat());
        }
        mergeJudgeObject.setId(null);
        mergeJudgeObject.setPid(0);
        mergeJudgeObject.setSplitNumber(null);
        mergeJudgeObject.setNumber(StringUtils.strip(numberBuilder.toString(), ","));
        mergeJudgeObject.setName(String.format("%s%s", mergeJudgeObject.getNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
        mergeJudgeObject.setOwnership(publicService.districtString(ownershipList));
        mergeJudgeObject.setSeat(publicService.fusinString(seatList, false));
        mergeJudgeObject.setFloorArea(floorAreaTotal);
        mergeJudgeObject.setEvaluationArea(evaluationAreaTotal);
        mergeJudgeObject.setStandardJudgeId(standardJudgeId);
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
            judgeObject.setPid(0);
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
        schemeAreaGroup.setEntrustPurpose(schemeProgrammeDto.getEntrustmentPurpose());
        schemeAreaGroup.setRemarkEntrustPurpose(schemeProgrammeDto.getRemarkEntrustPurpose());
        schemeAreaGroup.setValueDefinition(schemeProgrammeDto.getValueDefinition());
        schemeAreaGroup.setPropertyScope(schemeProgrammeDto.getPropertyScope());
        schemeAreaGroup.setScopeInclude(schemeProgrammeDto.getScopeInclude());
        schemeAreaGroup.setScopeNotInclude(schemeProgrammeDto.getScopeNotInclude());

        schemeAreaGroupService.saveAreaGroup(schemeAreaGroup);

        List<SchemeJudgeObject> schemeJudgeObjects = schemeProgrammeDto.getSchemeJudgeObjects();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjects)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjects) {
                if (schemeJudgeObject.getId() != null && schemeJudgeObject.getId() > 0) {
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
     * 回滚项目到方案阶段
     *
     * @param projectId
     * @param planId
     */
    public void rollBackToProgramme(Integer projectId, Integer planId) {
        //将整个项目的阶段任务回滚到方案阶段
        //根据情况来确定如何清理数据；
        // 1.当整个委估对象没有发生变化，数量合并拆分都没有发生变化，则只处理委估对象中发生变化的方法
        // 2.一旦委估对象都发生了变化,只是单纯添加了

        ProjectPlan projectPlan = projectPlanService.getProjectplanById(planId);
        List<ProjectPlan> planList = projectPlanService.getProjectplanByProjectId(projectId, null);
        for (ProjectPlan plan : planList) {
            if (plan.getStageSort().intValue() > projectPlan.getStageSort().intValue()) {
                //设置状态为等待 删除阶段下的任务
                plan.setProjectStatus(ProjectStatusEnum.WAIT.getKey());
                plan.setBisAutoComplete(false);
                projectPlanService.updateProjectPlan(plan);
                projectPlanDetailsService.deletePlanDetailsByPlanId(plan.getId());

                //清除task任务
                bpmRpcProjectTaskService.deleteProjectTaskByPlanId(applicationConstant.getAppKey(), plan.getId());
            }
        }
        projectPlanDetailsService.deletePlanDetailsByPlanId(planId);
        schemeInfoService.deleteSchemeInfoByProjectId(projectId);
        schemeSurePriceService.deleteSurePriceAll(projectId);
        generateReportInfoService.deleteGenerateReportInfoByProjectId(projectId);
    }

    /**
     * 提交方案
     *
     * @param projectId
     * @param planId
     * @param schemeProgrammeDtos
     * @param mustUseBox 当为true发起复核审批流程,当为false直接发起该阶段下的所有任务
     * @throws BusinessException
     * @throws BpmException
     */
    @Transactional
    public void submitProgramme(Integer projectId, Integer planId, List<SchemeProgrammeDto> schemeProgrammeDtos, boolean mustUseBox) throws BusinessException, BpmException {
        //1.验证数据的完整性与准确性，查看评估方法是否都已设置
        //2.清除该计划下的所有任务，保存方案数据
        //3.生成计划任务
        int count = schemeJudgeObjectDao.getNotSetFunctionCount(projectId);
        if (count > 0) {
            throw new BusinessException("还有委估对象未设置评估方法请检查");
        }
        this.saveProgrammeAll(schemeProgrammeDtos);
        ProjectPlan projectPlan = projectPlanService.getProjectplanById(planId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        ProjectWorkStage projectWorkStage = projectWorkStageService.cacheProjectWorkStage(projectPlan.getWorkStageId());
        if (mustUseBox) {
            //发起流程
            //这里根据参数获取复核模型来发起一个复核流程
            ProcessUserDto processUserDto = null;
            //发起相应的流程
            String folio = String.join("","【方案审批】",projectPlan.getPlanName(), DateUtils.format(new Date(),DateUtils.DATETIME_MILL_SECOND));
            //取得复核模型
            final String boxName = baseParameterService.getParameterValues(BaseParameterEnum.PROJECT__FORM__PROGRAMME__PROCESS__KEY.getParameterKey());
            BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
            ProcessInfo processInfo = new ProcessInfo();
            processInfo.setStartUser(processControllerComponent.getThisUser());
            processInfo.setProjectId(projectInfo.getId());
            processInfo.setProcessName(boxReDto.getProcessName());
            processInfo.setGroupName(boxReDto.getGroupName());
            processInfo.setFolio(folio);//流程描述
//            processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPlan.class));
            processInfo.setTableId(0);
            processInfo.setBoxId(boxReDto.getId());
            processInfo.setWorkStage(projectWorkStage.getWorkStageName());
            processInfo.setProcessEventExecutorName(ProgrammeProcessEvent.class.getSimpleName());//作用是当监听器审批通过后重新执行发起该阶段下的任务
            processInfo.setWorkStageId(projectWorkStage.getId());
            processInfo.setAppKey(applicationConstant.getAppKey());
            try {
                processUserDto = processControllerComponent.processStart(processControllerComponent.getThisUser(), processInfo, processControllerComponent.getThisUser(), false);
            } catch (BpmException e) {
                baseService.writeExceptionInfo(e, String.format("%s 失败!", folio));
                throw new BusinessException(e.getMessage());
            }
            projectPlan.setProcessInsId(processUserDto.getProcessInsId());
            projectPlanService.updateProjectPlan(projectPlan) ;
        }
        //原方法  这里会直接发起阶段任务我的理解 这里我只是把这个方法拆成2个而已，以前生成这个阶段下的任务的方法无任何修改,请以后的修改人注意
        if (!mustUseBox) {
            this.submitProgrammeHandle(projectInfo, projectPlan, projectWorkStage);
        }
    }

    /**
     * 发起该阶段下的任务
     * @param projectInfo
     * @param projectPlan
     * @param projectWorkStage
     * @throws BusinessException
     * @throws BpmException
     */
    public void submitProgrammeHandle(ProjectInfo projectInfo, ProjectPlan projectPlan, ProjectWorkStage projectWorkStage) throws BusinessException, BpmException {
        //循环所有新的委估对象，与上一次生成的数据做对比,首先找出未发生变化的委估对象，再处理新添加的委估对象，
        //1.当该委估对象为新添加的对象，则为该委估对象添加相关方法任务
        //2.当委估对象与上一次委估对象一致，方法未发生变更则不做处理，方法发生了变化则处理变化的内容
        //3.

        //1.首先找出没有发生变化的估价对象，及检查选择的方法是否发生变化，分隔出什么都变化的估价对象，方法变更的估价对象，拆分合并变化的估价对象
        //2.将没有发变化的事项与任务保留，其余的全部删除
        //3.对于只变化了方法，则只处理方法变更的事项，其他的委估对象事项都重新生成



        rollBackToProgramme(projectInfo.getId(), projectPlan.getId());
        String projectManager = projectMemberService.getProjectManager(projectInfo.getId());
        List<SchemeAreaGroup> areaGroupList = schemeAreaGroupService.getAreaGroupList(projectInfo.getId());
        if (CollectionUtils.isNotEmpty(areaGroupList)) {
            ProjectPhase phaseSurePrice = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.SURE_PRICE, projectInfo.getProjectCategoryId());
            ProjectPhase phaseLiquidationAnalysis = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.LIQUIDATION_ANALYSIS, projectInfo.getProjectCategoryId());
            ProjectPhase phaseReimbursement = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.REIMBURSEMENT, projectInfo.getProjectCategoryId());
            String mortgageKey = baseDataDicService.getCacheDataDicById(projectInfo.getEntrustPurpose()).getFieldName();
            int i = 0;
            Map<Integer, ProjectPhase> phaseMap = getProjectPhaseMap(projectInfo.getProjectCategoryId());
            List<ProjectPhase> judgeProjectPhases = Lists.newArrayList();
            if (StringUtils.equals(mortgageKey, AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE_MORTGAGE)) {//如果是抵押评估还需添加事项，变现分析税费、法定优先受偿款
                judgeProjectPhases.add(phaseLiquidationAnalysis);
                judgeProjectPhases.add(phaseReimbursement);
            }
            for (SchemeAreaGroup schemeAreaGroup : areaGroupList) {
                List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getSchemeJudgeObjectList(schemeAreaGroup.getId());
                if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                    for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
                        StringBuilder phaseName = new StringBuilder();
                        phaseName.append(schemeAreaGroup.getAreaName()).append("/").append(schemeJudgeObject.getNumber());
                        if (schemeJudgeObject.getSplitNumber() != null && schemeJudgeObject.getSplitNumber() > 0) {
                            phaseName.append("-").append(schemeJudgeObject.getSplitNumber());
                        }
                        phaseName.append(BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME);
                        List<SchemeJudgeFunction> judgeFunctions = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
                        if (CollectionUtils.isNotEmpty(judgeFunctions)) {
                            for (SchemeJudgeFunction judgeFunction : judgeFunctions) {
                                ProjectPhase projectPhase = phaseMap.get(judgeFunction.getMethodType());
                                if (projectPhase != null) {
                                    i = savePlanDetails(projectInfo, projectWorkStage, projectPlan, i, null, schemeJudgeObject, phaseName.toString(), projectPhase, projectManager);
                                    i++;
                                }
                            }
                        }
                        savePlanDetails(projectInfo, projectWorkStage, projectPlan, i, null, schemeJudgeObject, phaseName.toString(), phaseSurePrice, projectManager);//确定单价
                    }
                }
                if (CollectionUtils.isNotEmpty(judgeProjectPhases)) {
                    for (ProjectPhase projectPhase : judgeProjectPhases) {
                        savePlanDetails(projectInfo, projectWorkStage, projectPlan, i, schemeAreaGroup, null, schemeAreaGroup.getAreaName(), projectPhase, projectManager);
                    }
                }
            }
        }
    }

    private int savePlanDetails(ProjectInfo projectInfo, ProjectWorkStage projectWorkStage, ProjectPlan projectPlan, int i, SchemeAreaGroup schemeAreaGroup, SchemeJudgeObject schemeJudgeObject, String planRemarks, ProjectPhase projectPhase, String projectManager) throws BpmException {
        ProjectPlanDetails details = new ProjectPlanDetails();
        details.setProjectWorkStageId(projectPlan.getWorkStageId());
        details.setPlanId(projectPlan.getId());
        details.setProjectId(projectPlan.getProjectId());
        details.setProjectPhaseName(projectPhase.getProjectPhaseName());
        details.setProjectPhaseId(projectPhase.getId());
        if (schemeAreaGroup != null)
            details.setAreaId(schemeAreaGroup.getId());
        if (schemeJudgeObject != null)
            details.setJudgeObjectId(schemeJudgeObject.getId());
        details.setStatus(ProcessStatusEnum.RUN.getValue());
        details.setExecuteUserAccount(projectManager);
        details.setPlanStartDate(new Date());
        details.setPlanEndDate(new Date());
        details.setPid(0);
        details.setPlanRemarks(planRemarks);
        details.setBisLastLayer(true);
        details.setSorting(i++);
        projectPlanDetailsDao.addProjectPlanDetails(details);
        //发起任务
        projectPlanService.saveProjectPlanDetailsResponsibility(details, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
        return i;
    }

    private Map<Integer, ProjectPhase> getProjectPhaseMap(Integer categoryId) {
        //1.取得该阶段所有事项 2.取得方法下字典数据配置
        Map<Integer, ProjectPhase> map = Maps.newHashMap();
        List<BaseDataDic> methodList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_EVALUATION_METHOD);
        for (BaseDataDic method : methodList) {
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(method.getFieldName(), categoryId);
            if (projectPhase != null)
                map.put(method.getId(), projectPhase);
        }
        return map;
    }


    /**
     * 根据权证id获取估价对象编号
     *
     * @param declareRecordIds
     * @return
     */
    public List<Integer> getJudgeNumberByDeclareIds(List<Integer> declareRecordIds) {
        List<SchemeJudgeObject> judgeObjects = schemeJudgeObjectDao.getListByDeclareIds(declareRecordIds);
        if (CollectionUtils.isEmpty(judgeObjects)) return null;
        List<Integer> list = Lists.newArrayList();
        for (SchemeJudgeObject judgeObject : judgeObjects) {
            if (NumberUtils.isNumber(judgeObject.getNumber())) {
                list.add(NumberUtils.createInteger(judgeObject.getNumber()));
            }
        }
        return list;
    }

    /**
     * 根据申报信息获取对应估价对象
     *
     * @param declareRecordIds
     * @return
     */
    public List<SchemeJudgeObject> getListByDeclareIds(List<Integer> declareRecordIds) {
        return schemeJudgeObjectDao.getListByDeclareIds(declareRecordIds);
    }

    /**
     * 根据申报id获取查勘中房屋信息
     *
     * @param declareId
     * @return
     */
    public BasicHouse getBasicHouseByDeclareId(Integer declareId, Integer categoryId) {
        try {
            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.SCENE_EXPLORE, categoryId);
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
        this.updateSchemeJudgeObject(judgeObject);
    }

    public List<SchemeJudgeObject> getListByIds(List<Integer> judgeIds) {
        return schemeJudgeObjectDao.getListByIds(judgeIds);
    }

    /**
     * 重启申报阶段后根据申报记录id更新一下估价对象
     *
     * @param declareRecordIds
     * @param projectId
     */
    public void reStartDeclareApplyByDeclareRecordId(List<Integer> declareRecordIds, Integer projectId) {
        List<SchemeJudgeObject> schemeJudgeObjectList = getJudgeObjectListByProjectId(projectId);
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) {
            return;
        }
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            if (schemeJudgeObject.getDeclareRecordId() == null) {
                continue;
            }
            //确保属于更新的是重启后修改的申报数据
            if (!declareRecordIds.contains(schemeJudgeObject.getDeclareRecordId())) {
                continue;
            }
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord == null) {
                continue;
            }
            schemeJudgeObject.setFloorArea(declareRecord.getFloorArea());
            schemeJudgeObject.setCertName(declareRecord.getName());
            schemeJudgeObject.setOwnership(declareRecord.getOwnership());
            schemeJudgeObject.setSeat(declareRecord.getSeat());
            schemeJudgeObject.setCertUse(declareRecord.getCertUse());
            schemeJudgeObject.setPracticalUse(declareRecord.getPracticalUse());
            schemeJudgeObject.setEvaluationArea(declareRecord.getPracticalArea());
            schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
        }
    }
}
