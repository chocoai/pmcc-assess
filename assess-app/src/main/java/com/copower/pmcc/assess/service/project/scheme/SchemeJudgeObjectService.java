package com.copower.pmcc.assess.service.project.scheme;


import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.ComputeDataTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.ResponsibileModelEnum;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectHistoryDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeSurePriceFactorDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeSurePriceItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.method.MdNewAndRemoveDto;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeJudgeObjectAppendTaskDto;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeProgrammeDto;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.basic.BasicHouseHuxingPriceService;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.basic.BasicUnitHuxingService;
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
import com.copower.pmcc.erp.constant.CacheConstant;
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
    private SchemeSurePriceItemDao schemeSurePriceItemDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
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
    private SchemeJudgeObjectHistoryDao schemeJudgeObjectHistoryDao;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private BasicHouseHuxingPriceService basicHouseHuxingPriceService;

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
    public List<SchemeJudgeObjectVo> getSchemeJudgeObjectList(Integer areaGroupId, String number, String ownership, String seat) {
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectDao.getSchemeJudgeObjectList(areaGroupId, number, ownership, seat);
        return LangUtils.transform(schemeJudgeObjectList, o -> getSchemeJudgeObjectVo(o));
    }

    public BootstrapTableVo getJudgeObjectListByQuery(String name, String certName, String seat, String ownership, Integer areaGroupId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectDao.getJudgeObjectListByQuery(name, certName, seat, ownership, areaGroupId, null);
        List<SchemeJudgeObjectVo> objectVoList = LangUtils.transform(schemeJudgeObjectList, o -> getSchemeJudgeObjectVo(o));
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(objectVoList) ? new ArrayList<SchemeJudgeObject>() : objectVoList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public BootstrapTableVo getSchemeJudgeObjectListAll(String name, String certName, String seat, String ownership, Integer areaGroupId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SchemeJudgeObject> schemeJudgeObjectList = schemeJudgeObjectDao.getSchemeJudgeObjectListAll(name, certName, seat, ownership, areaGroupId, null);
        List<SchemeJudgeObjectVo> objectVoList = LangUtils.transform(schemeJudgeObjectList, o -> getSchemeJudgeObjectVo(o));
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(objectVoList) ? new ArrayList<SchemeJudgeObject>() : objectVoList);
        vo.setTotal(page.getTotal());
        return vo;
    }


    public List<SchemeJudgeObject> getJudgeObjectListByProjectId(Integer projectId) {
        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
        schemeJudgeObject.setProjectId(projectId);
        schemeJudgeObject.setBisEnable(true);
        return schemeJudgeObjectDao.getJudgeObjectList(schemeJudgeObject);
    }

    public List<SchemeJudgeObject> getJudgeObjectListAllByAreaGroupId(Integer areaGroupId) {
        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
        schemeJudgeObject.setAreaGroupId(areaGroupId);
        return schemeJudgeObjectDao.getJudgeObjectList(schemeJudgeObject);
    }

    public List<SchemeJudgeObject> getJudgeObjectList(SchemeJudgeObject schemeJudgeObject) {
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

    public List<SchemeJudgeObject> getJudgeObjectListByIds(List<Integer> judgeObjectIds) {
        if (CollectionUtils.isEmpty(judgeObjectIds)) return null;
        return schemeJudgeObjectDao.getListByIds(judgeObjectIds);
    }

    public List<SchemeJudgeObject> getChildrenJudgeObject(Integer id) {
        return schemeJudgeObjectDao.getListByPid(id);
    }

    /**
     * 获取拆分的估价对象
     *
     * @param splitFromId
     * @return
     */
    public List<SchemeJudgeObject> getJudgeObjectListBySplitFrom(Integer splitFromId) {
        SchemeJudgeObject where = new SchemeJudgeObject();
        where.setSplitFrom(splitFromId);
        return schemeJudgeObjectDao.getJudgeObjectList(where);
    }

    /**
     * 获取估价对象数据列表
     *
     * @param pid
     * @return
     */
    public BootstrapTableVo getBootstrapTableVoByPid(Integer pid) {
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

    public List<SchemeJudgeObjectVo> getVoListByPid(Integer pid) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByPid(pid);
        if (CollectionUtils.isEmpty(judgeObjectList)) {
            judgeObjectList = Lists.newArrayList(schemeJudgeObjectDao.getSchemeJudgeObject(pid));
        }
        List<BasicApply> basicApplyList = basicApplyService.getBasicApplyListByIds(LangUtils.transform(judgeObjectList, o -> o.getBasicApplyId()));
        List<SchemeJudgeObjectVo> judgeObjectVoList = Lists.newArrayList();
        for (SchemeJudgeObject judgeObject : judgeObjectList) {
            SchemeJudgeObjectVo schemeJudgeObjectVo = new SchemeJudgeObjectVo();
            BeanUtils.copyProperties(judgeObject, schemeJudgeObjectVo);
            for (BasicApply basicApply : basicApplyList) {
                if (basicApply.getId().equals(judgeObject.getBasicApplyId()) && basicApply.getBasicHouseId() != null) {
                    BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
                    if (basicHouse != null) {
                        schemeJudgeObjectVo.setFloor(basicHouse.getFloor());
                        schemeJudgeObjectVo.setRoomNumber(basicHouse.getHouseNumber());
                        BasicUnitHuxing unitHuxing = basicUnitHuxingService.getHuxingByHouseId(basicHouse.getId());
                        if (unitHuxing != null) {
                            schemeJudgeObjectVo.setTenementType(unitHuxing.getTenementType());
                        }
                        List<BasicHouseHuxingPrice> huxingPrices = basicHouseHuxingPriceService.getBasicHouseHuxingPriceList(basicHouse.getId());
                        if (CollectionUtils.isNotEmpty(huxingPrices)) {
                            schemeJudgeObjectVo.setHasPriceAdjust(true);
                        } else {
                            schemeJudgeObjectVo.setHasPriceAdjust(false);
                        }
                    }
                }
            }
            judgeObjectVoList.add(schemeJudgeObjectVo);
        }
        return judgeObjectVoList;
    }

    public List<SchemeJudgeObject> getListByPid(Integer pid) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByPid(pid);
        if (CollectionUtils.isEmpty(judgeObjectList)) return null;
        return judgeObjectList;
    }

    /**
     * 获取待调整价格的估价对象
     *
     * @param judgeObjectId
     * @return
     */
    public List<SchemeJudgeObjectVo> getAdjustObjectListByPid(Integer judgeObjectId) {
        SchemeJudgeObject judgeObject = getSchemeJudgeObject(judgeObjectId);
        List<SchemeJudgeObjectVo> vos = getVoListByPid(judgeObjectId);
        if (CollectionUtils.isEmpty(vos)) return null;
        return vos.stream().filter(o -> !o.getId().equals(judgeObject.getStandardJudgeId())).collect(Collectors.toList());
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
                schemeJudgeObject.setOriginalAreaGroupId(null);
                schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
            }
        }
        reNumberJudgeObject(originalAreaGroupId);
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
        if (schemeJudgeObject.getStandardJudgeId() != null) {
            SchemeJudgeObject judgeObject = getSchemeJudgeObject(schemeJudgeObject.getStandardJudgeId());
            if (judgeObject != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(judgeObject.getNumber());
                if (judgeObject.getSplitNumber() != null)
                    stringBuilder.append("-").append(judgeObject.getSplitNumber());
                schemeJudgeObjectVo.setStandardNumber(stringBuilder.toString());
            }
        }
        if (schemeJudgeObject.getBasicApplyId() != null) {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(schemeJudgeObject.getBasicApplyId());
            if (basicApply != null)
                schemeJudgeObjectVo.setSurveyObjectName(basicApply.getName());
        }
        if (StringUtils.isNotEmpty(schemeJudgeObject.getParcelInnerDevelop())) {
            List<String> stringList = Lists.newArrayList(schemeJudgeObject.getParcelInnerDevelop().split(",")).stream().map(s -> baseDataDicService.getNameById(s)).collect(Collectors.toList());
            schemeJudgeObjectVo.setParcelInnerDevelopName(org.apache.commons.lang3.StringUtils.join(stringList, "、"));
        }
        if (StringUtils.isNotEmpty(schemeJudgeObject.getParcelOuterDevelop())) {
            List<String> stringList = Lists.newArrayList(schemeJudgeObject.getParcelOuterDevelop().split(",")).stream().map(s -> baseDataDicService.getNameById(s)).collect(Collectors.toList());
            schemeJudgeObjectVo.setParcelOuterDevelopName(org.apache.commons.lang3.StringUtils.join(stringList, "、"));
        }
        if (StringUtils.isNotEmpty(schemeJudgeObject.getParcelSettingInnerDevelop())) {
            List<String> stringList = Lists.newArrayList(schemeJudgeObject.getParcelSettingInnerDevelop().split(",")).stream().map(s -> baseDataDicService.getNameById(s)).collect(Collectors.toList());
            schemeJudgeObjectVo.setParcelSettingInnerDevelopName(org.apache.commons.lang3.StringUtils.join(stringList, "、"));
        }
        return schemeJudgeObjectVo;
    }

    /**
     * 权证拆分
     *
     * @param judgeObjectId
     */
    @Transactional(rollbackFor = Exception.class)
    public void splitJudge(Integer projectId, Integer areaGroupId, Integer judgeObjectId, Integer splitCount) {
        //1.找出该权证已拆分的数据，以确定新权证的拆分序号
        //2.添加拆分数据,如果没有拆分数据，则需将主数据的拆分号设置为1
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(judgeObjectId);
        for (int i = 0; i < splitCount; i++) {
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByNumber(projectId, areaGroupId, schemeJudgeObject.getNumber());
            if (judgeObjectList.size() == 1) {
                schemeJudgeObject.setSplitNumber(1);
                schemeJudgeObject.setName(String.format("%s-%s%s", schemeJudgeObject.getNumber(), schemeJudgeObject.getSplitNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
                schemeJudgeObject.setEvaluationArea(new BigDecimal("0"));
                schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
                i++;
            }
            splitJudge(schemeJudgeObject, judgeObjectList.size() + 1, null);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void splitJudge(SchemeJudgeObject schemeJudgeObject, Integer splitNumber, Integer basicApplyId) {
        SchemeJudgeObject splitJudgeObject = new SchemeJudgeObject();
        splitJudgeObject.setPid(0);
        splitJudgeObject.setSplitFrom(schemeJudgeObject.getId());
        splitJudgeObject.setProjectId(schemeJudgeObject.getProjectId());
        splitJudgeObject.setAreaGroupId(schemeJudgeObject.getAreaGroupId());
        splitJudgeObject.setOriginalAreaGroupId(schemeJudgeObject.getOriginalAreaGroupId());
        splitJudgeObject.setDeclareRecordId(schemeJudgeObject.getDeclareRecordId());
        splitJudgeObject.setBasicApplyId(basicApplyId);
        splitJudgeObject.setNumber(schemeJudgeObject.getNumber());
        splitJudgeObject.setSplitNumber(splitNumber);
        splitJudgeObject.setName(String.format("%s-%s%s", schemeJudgeObject.getNumber(), splitJudgeObject.getSplitNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
        splitJudgeObject.setCertName(schemeJudgeObject.getCertName());
        splitJudgeObject.setOwnership(schemeJudgeObject.getOwnership());
        splitJudgeObject.setSeat(schemeJudgeObject.getSeat());
        BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApplyId);
        if (basicHouse != null) {
            splitJudgeObject.setCertUse(basicHouse.getCertUse());
            splitJudgeObject.setPracticalUse(basicHouse.getPracticalUse());
        } else {
            splitJudgeObject.setCertUse(schemeJudgeObject.getCertUse());
            splitJudgeObject.setPracticalUse(schemeJudgeObject.getPracticalUse());
        }
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
            if (schemeJudgeObject.getEvaluationArea() != null)
                evaluationAreaTotal = evaluationAreaTotal.add(schemeJudgeObject.getEvaluationArea());
            ownershipList.add(schemeJudgeObject.getOwnership());
            seatList.add(schemeJudgeObject.getSeat());
        }
        Map<Integer, BigDecimal> mappingSingleEntity = FormatUtils.mappingSingleEntity(judgeObjectList, o -> o.getDeclareRecordId(), o -> o.getFloorArea());
        if (mappingSingleEntity != null && !mappingSingleEntity.isEmpty()) {//证载面积的合计应取权证上的
            for (Map.Entry<Integer, BigDecimal> entry : mappingSingleEntity.entrySet()) {
                floorAreaTotal = floorAreaTotal.add(entry.getValue());
            }
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
    @Transactional(rollbackFor = Exception.class)
    public void mergeJudgeCancel(Integer id) {
        //1.将原参与合并的委估对象设置为可用，删除该合并的委估对象
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByPid(id);
        for (SchemeJudgeObject judgeObject : judgeObjectList) {
            judgeObject.setBisEnable(true);
            judgeObject.setPid(0);
            schemeJudgeObjectDao.updateSchemeJudgeObject(judgeObject);
        }
        schemeJudgeObjectDao.removeSchemeJudgeObject(id);
    }

    /**
     * 委估对象合并取消(部分)
     *
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void mergeJudgeAdjust(Integer id, String removeIds, String addIds) {
        //1.将原参与合并的委估对象设置为可用，更该估价对象号及名称
        //2.标准估价对象无法移除
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(id);
        if (StringUtils.isNotBlank(removeIds)) {
            List<Integer> list = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(removeIds));
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(list);
            String number = "," + schemeJudgeObject.getNumber();
            String name = "," + schemeJudgeObject.getName();
            BigDecimal floorArea = schemeJudgeObject.getFloorArea();
            BigDecimal evaluationArea = schemeJudgeObject.getEvaluationArea();
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    if (judgeObject.getId().equals(schemeJudgeObject.getStandardJudgeId())) continue;
                    judgeObject.setBisEnable(true);
                    judgeObject.setPid(0);
                    schemeJudgeObjectDao.updateSchemeJudgeObject(judgeObject);
                    String fullNumber = "," + judgeObject.getNumber() + (judgeObject.getSplitNumber() == null ? "" : "-" + judgeObject.getSplitNumber());
                    number = number.replace(fullNumber, "");
                    name = name.replace(fullNumber, "");
                    floorArea = floorArea.subtract(judgeObject.getFloorArea());
                    evaluationArea = evaluationArea.subtract(judgeObject.getEvaluationArea());
                }
            }
            schemeJudgeObject.setNumber(StringUtils.strip(number.replaceAll(",+", ","), ","));
            schemeJudgeObject.setName(schemeJudgeObject.getNumber() + BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME);
            schemeJudgeObject.setFloorArea(floorArea);
            schemeJudgeObject.setEvaluationArea(evaluationArea);
            schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
            judgeNameChangeEvent(schemeJudgeObject);
        }

        if (StringUtils.isNotBlank(addIds)) {
            List<Integer> list = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(addIds));
            List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(list);
            String number = schemeJudgeObject.getNumber();
            BigDecimal floorArea = schemeJudgeObject.getFloorArea();
            BigDecimal evaluationArea = schemeJudgeObject.getEvaluationArea();
            if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                for (SchemeJudgeObject judgeObject : judgeObjectList) {
                    judgeObject.setBisEnable(false);
                    judgeObject.setPid(id);
                    schemeJudgeObjectDao.updateSchemeJudgeObject(judgeObject);
                    String fullNumber = "," + judgeObject.getNumber() + (judgeObject.getSplitNumber() == null ? "" : "-" + judgeObject.getSplitNumber());
                    number = number + fullNumber;
                    floorArea = floorArea.add(judgeObject.getFloorArea());
                    evaluationArea = evaluationArea.add(judgeObject.getEvaluationArea());
                }
            }
            schemeJudgeObject.setNumber(number);
            schemeJudgeObject.setName(number + BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME);
            schemeJudgeObject.setFloorArea(floorArea);
            schemeJudgeObject.setEvaluationArea(evaluationArea);
            schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
            judgeNameChangeEvent(schemeJudgeObject);
        }

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
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(schemeProgrammeDto.getAreaGroupId());
        schemeAreaGroup.setValueTimePoint(schemeProgrammeDto.getValueTimePoint());
        schemeAreaGroup.setTimePointExplain(schemeProgrammeDto.getTimePointExplain());
        schemeAreaGroup.setEntrustPurpose(schemeProgrammeDto.getEntrustmentPurpose());
        schemeAreaGroup.setRemarkEntrustPurpose(schemeProgrammeDto.getRemarkEntrustPurpose());
        schemeAreaGroup.setValueDefinition(schemeProgrammeDto.getValueDefinition());
        schemeAreaGroup.setValueDefinitionDesc(schemeProgrammeDto.getValueDefinitionDesc());
        schemeAreaGroup.setPropertyScope(schemeProgrammeDto.getPropertyScope());
        schemeAreaGroup.setScopeInclude(schemeProgrammeDto.getScopeInclude());
        schemeAreaGroup.setScopeNotInclude(schemeProgrammeDto.getScopeNotInclude());
        schemeAreaGroup.setEntrustAimType(schemeProgrammeDto.getEntrustAimType());
        schemeAreaGroupService.saveAreaGroup(schemeAreaGroup);
    }

    /**
     * 保存方案所有内容
     *
     * @param schemeProgrammeDtos
     */
    @Transactional(rollbackFor = Exception.class)
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
     * @param mustUseBox          当为true发起复核审批流程,当为false直接发起该阶段下的所有任务
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
            String folio = String.join("", "【方案审批】", projectPlan.getPlanName(), DateUtils.format(new Date(), DateUtils.DATETIME_MILL_SECOND));
            //取得复核模型
            final String boxName = baseParameterService.getParameterValues(BaseParameterEnum.PROJECT__FORM__PROGRAMME__PROCESS__KEY.getParameterKey());
            BoxReDto boxReDto = bpmRpcBoxService.getBoxReByBoxName(boxName);
            ProcessInfo processInfo = new ProcessInfo();
            processInfo.setStartUser(processControllerComponent.getThisUser());
            processInfo.setProjectId(projectInfo.getId());
            processInfo.setProcessName(boxReDto.getProcessName());
            processInfo.setGroupName(boxReDto.getGroupName());
            processInfo.setFolio(folio);//流程描述
            processInfo.setTableName(FormatUtils.entityNameConvertToTableName(ProjectPlan.class));
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
            projectPlanService.updateProjectPlan(projectPlan);
        } else {
            this.submitProgrammeHandle(projectInfo, projectPlan, projectWorkStage);
        }
    }

    /**
     * 发起该阶段下的任务
     *
     * @param projectInfo
     * @param projectPlan
     * @param projectWorkStage
     * @throws BusinessException
     * @throws BpmException
     */
    public void submitProgrammeHandle(ProjectInfo projectInfo, ProjectPlan projectPlan, ProjectWorkStage projectWorkStage) throws BusinessException, BpmException {
        String projectManager = projectMemberService.getProjectManager(projectInfo.getId());
        List<SchemeAreaGroup> areaGroupList = schemeAreaGroupService.getAreaGroupEnableByProjectId(projectInfo.getId());
        if (CollectionUtils.isNotEmpty(areaGroupList)) {
            Boolean isNeedReNumber = false;
            List<MdNewAndRemoveDto> methodChangeJudges = Lists.newArrayList();//只是改变了评估方法的估价对象
            ProjectPhase phaseSurePrice = projectPhaseService.getCacheProjectPhaseByReferenceId(AssessPhaseKeyConstant.SURE_PRICE, projectInfo.getProjectCategoryId());
            Map<Integer, ProjectPhase> phaseMap = getProjectPhaseMap(projectInfo.getProjectCategoryId());

            SchemeJudgeObjectAppendTaskDto appendTaskDto = new SchemeJudgeObjectAppendTaskDto();
            appendTaskDto.setProjectInfo(projectInfo);
            appendTaskDto.setProjectWorkStage(projectWorkStage);
            appendTaskDto.setProjectPlan(projectPlan);
            appendTaskDto.setPhaseSurePrice(phaseSurePrice);
            appendTaskDto.setProjectManager(projectManager);
            appendTaskDto.setPhaseMap(phaseMap);
            for (SchemeAreaGroup schemeAreaGroup : areaGroupList) {
                appendTaskDto.setSorting(schemeAreaGroup.getId());
                appendTaskDto.setSchemeAreaGroup(schemeAreaGroup);
                if (schemeAreaGroup.getBisNew() == Boolean.TRUE) {
                    List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getSchemeJudgeObjectList(schemeAreaGroup.getId());
                    if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
                            appendTaskDto.setSchemeJudgeObject(schemeJudgeObject);
                            appendTaskForJudgeObject(appendTaskDto);
                        }
                    }
                    List<ProjectPhase> judgeProjectPhases = schemeAreaGroupService.getAreaProjectPhaseListByEntrustPurpose(projectInfo, schemeAreaGroup.getEntrustPurpose());
                    if (CollectionUtils.isNotEmpty(judgeProjectPhases)) {
                        for (ProjectPhase projectPhase : judgeProjectPhases) {
                            savePlanDetails(projectInfo, projectWorkStage, projectPlan, schemeAreaGroup.getId(), schemeAreaGroup, null, schemeAreaGroup.getAreaName(), projectPhase, projectManager);
                        }
                    }
                    schemeAreaGroup.setBisNew(false);
                    schemeAreaGroupService.saveAreaGroup(schemeAreaGroup);
                } else {
                    //先拿历史版本数据与当前数据进行比较，确定各种数据的处理方案
                    //1.先用所有关键信息进行比对，完全一致，这部分数据不做处理，注意有可能选择的方法会不一致
                    //2.不一致，其中新增或发生变化的先删除任务再为其添加新任务；已被删除的估价对象只将其任务删除掉
                    List<SchemeJudgeObject> judgeObjectList = getJudgeObjectApplicableListByAreaGroupId(schemeAreaGroup.getId());
                    List<SchemeJudgeObjectHistory> judgeObjectHistoryList = schemeJudgeObjectHistoryDao.getEnableListByAreaId(schemeAreaGroup.getId());
                    if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                        Iterator<SchemeJudgeObject> judgeObjectIterator = judgeObjectList.iterator();
                        while (judgeObjectIterator.hasNext()) {
                            SchemeJudgeObject judgeObject = (SchemeJudgeObject) judgeObjectIterator.next();
                            if (CollectionUtils.isNotEmpty(judgeObjectHistoryList)) {
                                Iterator<SchemeJudgeObjectHistory> judgeObjectHistoryIterator = judgeObjectHistoryList.iterator();
                                while (judgeObjectHistoryIterator.hasNext()) {
                                    SchemeJudgeObjectHistory judgeObjectHistory = (SchemeJudgeObjectHistory) judgeObjectHistoryIterator.next();
                                    boolean isSameAreaGroupId = publicService.equalsInteger(judgeObject.getAreaGroupId(), judgeObjectHistory.getAreaGroupId());
                                    boolean isSameDeclareRecordId = publicService.equalsInteger(judgeObject.getDeclareRecordId(), judgeObjectHistory.getDeclareRecordId());
                                    boolean isSameNumber = publicService.equalsString(judgeObject.getNumber(), judgeObjectHistory.getNumber());
                                    boolean isSameSplitNumber = publicService.equalsInteger(judgeObject.getSplitNumber(), judgeObjectHistory.getSplitNumber());

                                    //需做调整
                                    //1.应该使用估价对象id进行比较，如果估价对象id相同且不是合并对象，则只检查方法是否变更
                                    //2.如果估价对象id相同且为合并对象，则检查参与合并的对象是否变化，有变化则让确定单价事项重新完成
                                    if (judgeObject.getId().equals(judgeObjectHistory.getJudgeObjectId())) {
                                        judgeObjectIterator.remove();
                                        judgeObjectHistoryIterator.remove();
                                        if (judgeObject.getBisMerge() == Boolean.TRUE || judgeObjectHistory.getBisMerge() == Boolean.TRUE) {
                                            isNeedReNumber = !publicService.listIsConsistent(FormatUtils.transformString2List(judgeObject.getNumber()), FormatUtils.transformString2List(judgeObjectHistory.getNumber()));
                                            if (isNeedReNumber) {//确定单价事项需重新完成
                                                ProjectPlanDetails surePricePhase = projectPlanDetailsService.getProjectPlanDetailsByJudgeId(judgeObject.getId(), phaseSurePrice.getId());
                                                projectPlanDetailsService.replyProjectPlanDetails(surePricePhase.getId(), null);
                                            }
                                        }

                                        List<String> judgeObjectFunctions = FormatUtils.transformString2List(judgeObject.getJudgeFunction());
                                        List<String> judgeObjectHistoryFunctions = FormatUtils.transformString2List(judgeObjectHistory.getJudgeFunction());
                                        if (!publicService.listIsConsistent(judgeObjectFunctions, judgeObjectHistoryFunctions)) {
                                            MdNewAndRemoveDto mdNewAndRemoveDto = new MdNewAndRemoveDto();
                                            mdNewAndRemoveDto.setJudgeObjectId(judgeObject.getId());
                                            mdNewAndRemoveDto.setSchemeJudgeObject(judgeObject);
                                            mdNewAndRemoveDto.setSchemeAreaGroup(schemeAreaGroup);
                                            if (CollectionUtils.isNotEmpty(judgeObjectFunctions)) {
                                                mdNewAndRemoveDto.setNewMethodList(FormatUtils.ListStringToListInteger(judgeObjectFunctions));
                                            }
                                            if (CollectionUtils.isNotEmpty(judgeObjectHistoryFunctions)) {
                                                mdNewAndRemoveDto.setRemoveMethodList(FormatUtils.ListStringToListInteger(judgeObjectHistoryFunctions));
                                            }
                                            methodChangeJudges.add(mdNewAndRemoveDto);
                                        }
                                    }
                                }
                            }
                        }
                        //新添加的估价对象
                        if (CollectionUtils.isNotEmpty(judgeObjectList)) {
                            isNeedReNumber = true;
                            for (SchemeJudgeObject judgeObject : judgeObjectList) {
                                appendTaskDto.setSchemeJudgeObject(judgeObject);
                                appendTaskForJudgeObject(appendTaskDto);
                            }
                        }
                        //已经被移除的估价对象
                        if (CollectionUtils.isNotEmpty(judgeObjectHistoryList)) {
                            isNeedReNumber = true;
                            judgeObjectHistoryList.forEach(o -> {
                                clearJudgeObjectTask(projectInfo.getId(), o.getJudgeObjectId());
                            });
                        }
                        if (isNeedReNumber) {
                            reNumberJudgeObject(schemeAreaGroup.getId());
                        }
                    }
                }
            }

            //处理只改变了评估方法的估价对象
            if (CollectionUtils.isNotEmpty(methodChangeJudges)) {
                for (MdNewAndRemoveDto item : methodChangeJudges) {
                    schemeSurePriceItemDao.deleteSurePriceItemByJudgeId(item.getJudgeObjectId());
                    if (CollectionUtils.isNotEmpty(item.getRemoveMethodList())) {
                        for (Integer methodType : item.getRemoveMethodList()) {
                            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(methodType);
                            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(baseDataDic.getFieldName());
                            ProjectPlanDetails where = new ProjectPlanDetails();
                            where.setProjectId(projectInfo.getId());
                            where.setProjectPhaseId(projectPhase.getId());
                            where.setJudgeObjectId(item.getJudgeObjectId());
                            List<ProjectPlanDetails> projectDetails = projectPlanDetailsService.getProjectDetails(where);
                            if (CollectionUtils.isNotEmpty(projectDetails))
                                projectDetails.forEach(o -> projectPlanDetailsService.deleteProjectPlanDetails(o));
                        }
                    }
                    if (CollectionUtils.isNotEmpty(item.getNewMethodList())) {
                        String planRemark = generatePlanRemark(item.getSchemeJudgeObject());
                        for (Integer methodType : item.getNewMethodList()) {
                            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(methodType);
                            ProjectPhase projectPhase = projectPhaseService.getCacheProjectPhaseByKey(baseDataDic.getFieldName());
                            if (!hasPlanDetails(projectInfo.getId(), null, projectPhase.getId(), item.getJudgeObjectId()))
                                savePlanDetails(projectInfo, projectWorkStage, projectPlan, 0, item.getSchemeAreaGroup(), item.getJudgeObjectId(), planRemark, projectPhase, projectManager);
                        }
                    }
                }
            }
        }
        schemeAreaGroupService.clearAreaGroupTask(schemeAreaGroupService.getAreaGroupAllByProjectId(projectInfo.getId()));//清理掉空的区域
        recordToHistory(projectInfo.getId()); //当所有任务生成完成后，将当前方案版本数据存入历史数据表中
    }

    /**
     * 清除估价对象相关任务
     */
    public void clearJudgeObjectTask(Integer projectId, Integer judgeObjectId) {
        ProjectPlanDetails where = new ProjectPlanDetails();
        where.setProjectId(projectId);
        where.setJudgeObjectId(judgeObjectId);
        List<ProjectPlanDetails> projectDetails = projectPlanDetailsService.getProjectDetails(where);
        if (CollectionUtils.isNotEmpty(projectDetails)) {
            projectDetails.forEach(o -> projectPlanDetailsService.deleteProjectPlanDetails(o));
        } else {
            schemeJudgeObjectDao.removeSchemeJudgeObject(judgeObjectId);
        }
    }

    public void recordToHistory(Integer projectId) {
        //先清除上个版本历史数据
        schemeJudgeObjectHistoryDao.deleteHistoryListByProjectId(projectId);
        List<SchemeJudgeObject> judgeObjects = getJudgeObjectListByProjectId(projectId);
        if (CollectionUtils.isNotEmpty(judgeObjects)) {
            judgeObjects.forEach(o -> {
                SchemeJudgeObjectHistory judgeObjectHistory = new SchemeJudgeObjectHistory();
                judgeObjectHistory.setJudgeObjectId(o.getId());
                judgeObjectHistory.setProjectId(o.getProjectId());
                judgeObjectHistory.setAreaGroupId(o.getAreaGroupId());
                judgeObjectHistory.setDeclareRecordId(o.getDeclareRecordId());
                judgeObjectHistory.setNumber(o.getNumber());
                judgeObjectHistory.setSplitNumber(o.getSplitNumber());
                judgeObjectHistory.setName(o.getName());
                judgeObjectHistory.setJudgeFunction(o.getJudgeFunction());
                judgeObjectHistory.setSorting(o.getSorting());
                judgeObjectHistory.setBisEnable(o.getBisEnable());
                judgeObjectHistory.setBisSplit(o.getBisSplit());
                judgeObjectHistory.setBisMerge(o.getBisMerge());
                judgeObjectHistory.setCreator(commonService.thisUserAccount());
                schemeJudgeObjectHistoryDao.addSchemeJudgeObjectHistory(judgeObjectHistory);
            });
        }
    }

    private int appendTaskForJudgeObject(SchemeJudgeObjectAppendTaskDto schemeJudgeObjectAppendTaskDto) throws BpmException {
        ProjectInfo projectInfo = schemeJudgeObjectAppendTaskDto.getProjectInfo();
        ProjectWorkStage projectWorkStage = schemeJudgeObjectAppendTaskDto.getProjectWorkStage();
        ProjectPlan projectPlan = schemeJudgeObjectAppendTaskDto.getProjectPlan();
        Integer sorting = 0;
        SchemeAreaGroup schemeAreaGroup = schemeJudgeObjectAppendTaskDto.getSchemeAreaGroup();
        SchemeJudgeObject schemeJudgeObject = schemeJudgeObjectAppendTaskDto.getSchemeJudgeObject();
        ProjectPhase phaseSurePrice = schemeJudgeObjectAppendTaskDto.getPhaseSurePrice();
        String projectManager = schemeJudgeObjectAppendTaskDto.getProjectManager();
        Map<Integer, ProjectPhase> phaseMap = schemeJudgeObjectAppendTaskDto.getPhaseMap();

        String planRemark = generatePlanRemark(schemeJudgeObject);
        List<SchemeJudgeFunction> judgeFunctions = schemeJudgeFunctionService.getApplicableJudgeFunctions(schemeJudgeObject.getId());
        if (CollectionUtils.isNotEmpty(judgeFunctions)) {
            for (SchemeJudgeFunction judgeFunction : judgeFunctions) {
                ProjectPhase projectPhase = phaseMap.get(judgeFunction.getMethodType());
                if (projectPhase != null) {
                    sorting = schemeJudgeObjectAppendTaskDto.getSorting() + schemeJudgeObject.getSorting() * 100 + projectPhase.getPhaseSort();
                    if (!hasPlanDetails(projectInfo.getId(), null, projectPhase.getId(), schemeJudgeObject.getId()))
                        savePlanDetails(projectInfo, projectWorkStage, projectPlan, sorting, null, schemeJudgeObject.getId(), planRemark, projectPhase, projectManager);
                }
            }
        }
        sorting = schemeJudgeObjectAppendTaskDto.getSorting() + schemeJudgeObject.getSorting() * 100 + phaseSurePrice.getPhaseSort();
        if (!hasPlanDetails(projectInfo.getId(), null, phaseSurePrice.getId(), schemeJudgeObject.getId()))
            sorting = savePlanDetails(projectInfo, projectWorkStage, projectPlan, sorting, null, schemeJudgeObject.getId(), planRemark, phaseSurePrice, projectManager);//确定单价
        return sorting;
    }

    private String generatePlanRemark(SchemeJudgeObject schemeJudgeObject) {
        StringBuilder stringBuilder = new StringBuilder();
        if (schemeJudgeObject != null) {
            stringBuilder.append(schemeJudgeObject.getNumber());
            if (schemeJudgeObject.getSplitNumber() != null && schemeJudgeObject.getSplitNumber() > 0) {
                stringBuilder.append("-").append(schemeJudgeObject.getSplitNumber());
            }
            stringBuilder.append(BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME);
        }
        return stringBuilder.toString();
    }


    public int savePlanDetails(ProjectInfo projectInfo, ProjectWorkStage projectWorkStage, ProjectPlan projectPlan, int sorting, SchemeAreaGroup schemeAreaGroup, Integer judgeObjectId, String planRemarks, ProjectPhase projectPhase, String projectManager) throws BpmException {
        ProjectPlanDetails details = new ProjectPlanDetails();
        details.setProjectWorkStageId(projectPlan.getWorkStageId());
        details.setPlanId(projectPlan.getId());
        details.setProjectId(projectPlan.getProjectId());
        details.setProjectPhaseName(projectPhase.getProjectPhaseName());
        details.setProjectPhaseId(projectPhase.getId());
        if (schemeAreaGroup != null)
            details.setAreaId(schemeAreaGroup.getId());
        details.setJudgeObjectId(judgeObjectId);
        details.setStatus(ProcessStatusEnum.RUN.getValue());
        details.setExecuteUserAccount(projectManager);
        details.setPlanStartDate(new Date());
        details.setPlanEndDate(new Date());
        details.setPid(0);
        details.setPlanRemarks(planRemarks);
        details.setBisLastLayer(true);
        details.setSorting(sorting++);
        projectPlanDetailsDao.addProjectPlanDetails(details);
        //发起任务
        projectPlanService.saveProjectPlanDetailsResponsibility(details, projectInfo.getProjectName(), projectWorkStage.getWorkStageName(), ResponsibileModelEnum.TASK);
        return sorting;
    }

    public Boolean hasPlanDetails(Integer projectId, Integer areaId, Integer phaseId, Integer judgeId) {
        ProjectPlanDetails where = new ProjectPlanDetails();
        if (projectId != null)
            where.setProjectId(projectId);
        if (areaId != null)
            where.setAreaId(areaId);
        if (phaseId != null)
            where.setProjectPhaseId(phaseId);
        if (judgeId != null)
            where.setJudgeObjectId(judgeId);
        return CollectionUtils.isNotEmpty(projectPlanDetailsService.getProjectDetails(where));
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

    public List<Integer> getJudgeNumberByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) return null;
        List<SchemeJudgeObject> judgeObjects = schemeJudgeObjectDao.getListByIds(ids);
        List<Integer> list = Lists.newArrayList();
        for (SchemeJudgeObject judgeObject : judgeObjects) {
            List<String> string2List = FormatUtils.transformString2List(judgeObject.getNumber(), ",");
            if (CollectionUtils.isEmpty(string2List)) {
                string2List = FormatUtils.transformString2List(judgeObject.getNumber(), "-");
            }
            if (CollectionUtils.isEmpty(string2List)) {
                string2List = FormatUtils.transformString2List(judgeObject.getNumber(), "_");
            }
            if (CollectionUtils.isNotEmpty(string2List)) {
                for (String num : string2List) {
                    if (NumberUtils.isNumber(num)) {
                        list.add(NumberUtils.createInteger(num));
                    }
                }
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
            schemeJudgeObjectDao.updateSchemeJudgeObject(schemeJudgeObject);
        }
    }

    /**
     * 区域下的估价对象重新排号
     *
     * @param areaGroupId
     */
    public void reNumberJudgeObject(Integer areaGroupId) {
        //1.根据权证的编号确定估价对象的序号 2.将合并对象独立处理
        List<SchemeJudgeObject> judgeObjects = getJudgeObjectDeclareListByAreaId(areaGroupId);
        for (SchemeJudgeObject judgeObject : judgeObjects) {
            //处理该估价对象的拆分对象
            List<SchemeJudgeObject> splitJudgeObjectList = getJudgeObjectListBySplitFrom(judgeObject.getId());
            if (CollectionUtils.isNotEmpty(splitJudgeObjectList)) {
                splitJudgeObjectList.forEach(o -> {
                    o.setNumber(judgeObject.getNumber());
                    judgeObject.setName(String.format("%s-%s%s", o.getNumber(), o.getSplitNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
                    updateSchemeJudgeObject(o);
                    judgeNameChangeEvent(o);
                });
            }
        }
        List<SchemeJudgeObject> mergeJudgeObjectList = schemeJudgeObjectDao.getMergeListByAreaId(areaGroupId);
        if (CollectionUtils.isNotEmpty(mergeJudgeObjectList)) {
            for (SchemeJudgeObject mergeJudgeObject : mergeJudgeObjectList) {
                StringBuilder numberBuilder = new StringBuilder();
                List<SchemeJudgeObject> childrenJudgeObject = getChildrenJudgeObject(mergeJudgeObject.getId());
                if (CollectionUtils.isNotEmpty(childrenJudgeObject)) {
                    for (SchemeJudgeObject object : childrenJudgeObject) {
                        numberBuilder.append(object.getNumber());
                        if (object.getSplitNumber() != null && object.getSplitNumber() > 0)
                            numberBuilder.append("-").append(object.getSplitNumber());
                        numberBuilder.append(",");
                    }
                }
                mergeJudgeObject.setNumber(StringUtils.strip(numberBuilder.toString().replaceAll(",+", ","), ","));
                mergeJudgeObject.setName(String.format("%s%s", mergeJudgeObject.getNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
                updateSchemeJudgeObject(mergeJudgeObject);
                judgeNameChangeEvent(mergeJudgeObject);
            }
        }
        //该区域下变现与受偿款任务重新完成
        SchemeAreaGroup schemeAreaGroup = schemeAreaGroupService.getSchemeAreaGroup(areaGroupId);
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(schemeAreaGroup.getProjectId());
        List<ProjectPhase> projectPhases = schemeAreaGroupService.getAreaProjectPhaseListByEntrustPurpose(projectInfo, schemeAreaGroup.getEntrustPurpose());
        if (CollectionUtils.isNotEmpty(projectPhases)) {
            for (ProjectPhase projectPhase : projectPhases) {
                ProjectPlanDetails query = new ProjectPlanDetails();
                query.setAreaId(areaGroupId);
                query.setProjectPhaseId(projectPhase.getId());
                List<ProjectPlanDetails> planDetailsList = projectPlanDetailsService.getProjectDetails(query);
                if (CollectionUtils.isNotEmpty(planDetailsList)) {
                    planDetailsList.forEach(o -> {
                        try {
                            projectPlanDetailsService.replyProjectPlanDetails(o.getId(), null);
                        } catch (Exception e) {
                            logger.error(e.getMessage(), e);
                        }
                    });
                }
            }
        }
        clearCacheJudgeObjectByProjectId(projectInfo.getId());//排号完成清除缓存
    }

    /**
     * 清除项目下估价对象缓存数据
     *
     * @param projectId
     */
    public void clearCacheJudgeObjectByProjectId(Integer projectId) {
        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
        schemeJudgeObject.setProjectId(projectId);
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getJudgeObjectList(schemeJudgeObject);
        if (CollectionUtils.isNotEmpty(judgeObjectList))
            judgeObjectList.forEach(o -> processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_JUDGE_OBJECT_ID, String.valueOf(o.getId())));
    }

    /**
     * 当估价对象名称变化时，其他相关联的名称都发生变更
     *
     * @param judgeObject
     */
    public void judgeNameChangeEvent(SchemeJudgeObject judgeObject) {
        if (judgeObject == null) return;
        //1.工作事项备注名称change
        ProjectPlanDetails where = new ProjectPlanDetails();
        where.setJudgeObjectId(judgeObject.getId());
        List<ProjectPlanDetails> planDetailsList = projectPlanDetailsService.getProjectDetails(where);
        if (CollectionUtils.isNotEmpty(planDetailsList)) {
            for (ProjectPlanDetails item : planDetailsList) {
                item.setPlanRemarks(judgeObject.getName());
                projectPlanDetailsService.updateProjectPlanDetails(item);
            }
        }
    }

    /**
     * 根据applyId获取对应的估价对象
     *
     * @param applyId
     * @return
     */
    public SchemeJudgeObject getJudgeObjectByApplyId(Integer applyId) {
        if (applyId == null) return null;
        SchemeJudgeObject where = new SchemeJudgeObject();
        where.setBasicApplyId(applyId);
        where.setBisEnable(true);
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getJudgeObjectList(where);
        if (CollectionUtils.isEmpty(judgeObjectList)) return null;
        return judgeObjectList.get(0);
    }
}
