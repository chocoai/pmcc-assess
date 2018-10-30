package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basic.entity.BasicApply;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingMain;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstate;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMain;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingVo;
import com.copower.pmcc.assess.service.cases.CaseBuildingMainService;
import com.copower.pmcc.assess.service.cases.CaseBuildingService;
import com.copower.pmcc.assess.service.cases.CaseEstateService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/25 10:31
 * @Description:
 */
@Service
public class PublicBasicService {
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicBuildingMainService basicBuildingMainService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CaseBuildingMainService caseBuildingMainService;
    @Autowired
    private CaseEstateService caseEstateService;
    @Autowired
    private CaseBuildingService caseBuildingService;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 审批之后 回写数据并且升级版本
     *
     * @param approvalModelDto
     * @throws Exception
     */
    public void approvalAndWrite(ApprovalModelDto approvalModelDto) throws Exception {
        try {
            processControllerComponent.processSubmitLoopTaskNodeArg(approvalModelDto, false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        String processInsId = approvalModelDto.getProcessInsId();
        BasicApply basicApply = basicApplyService.getBasicApplyByProcessInsId(processInsId);
        if (basicApply != null) {
            BasicEstate basicEstate = this.getByAppIdBasicEstate(basicApply.getId());
            BasicBuildingMain basicBuildingMain = this.getByAppIdBasicBuildingMain(basicApply.getId());
            //-----------------------------------||---------------------------
            if (basicEstate != null) {
                CaseEstate caseEstate = null;
                if (basicEstate.getCaseEstateId() != null) {
                    caseEstate = caseEstateService.getCaseEstateById(basicEstate.getCaseEstateId());
                    //虽然有 案例id但是无法找到具体数据的情况(一般不会发生)
                    if (caseEstate == null) {
                        caseEstate = new CaseEstate();
                        BeanCopyHelp.copyPropertiesIgnoreNull(basicEstate, caseEstate);
                        caseEstate.setId(null);
                        caseEstate.setGmtCreated(null);
                        caseEstate.setGmtModified(null);
                    }
                    //有案例 id 并且找到
                    if (caseEstate != null) {
                        BeanCopyHelp.copyPropertiesIgnoreNull(basicEstate, caseEstate);
                        caseEstate.setId(basicEstate.getCaseEstateId());
                    }
                }
                //第一次情况
                if (basicEstate.getCaseEstateId() == null) {
                    caseEstate = new CaseEstate();
                    BeanCopyHelp.copyPropertiesIgnoreNull(basicEstate, caseEstate);
                    caseEstate.setId(null);
                    caseEstate.setGmtCreated(null);
                    caseEstate.setGmtModified(null);
                }
                //升级版本 以及更改某些数据  或者 新添数据
                if (caseEstate != null) {
                    caseEstateService.upgradeVersion(caseEstate);
                }
            }
            //-----------------------------------||---------------------------
            Integer caseBuildingMainId = null;
            if (basicBuildingMain != null) {
                CaseBuildingMain caseBuildingMain = null;
                if (basicBuildingMain.getCaseBuildingMain() != null) {
                    caseBuildingMain = caseBuildingMainService.getCaseBuildingMainById(basicBuildingMain.getCaseBuildingMain());
                    if (caseBuildingMain != null) {
                        BeanCopyHelp.copyPropertiesIgnoreNull(basicBuildingMain, caseBuildingMain);
                        caseBuildingMain.setId(basicBuildingMain.getCaseBuildingMain());
                    }
                    if (caseBuildingMain == null) {
                        caseBuildingMain = new CaseBuildingMain();
                        BeanCopyHelp.copyPropertiesIgnoreNull(basicBuildingMain, caseBuildingMain);
                        caseBuildingMain.setId(null);
                        caseBuildingMain.setGmtCreated(null);
                        caseBuildingMain.setGmtModified(null);
                    }
                }
                if (basicBuildingMain.getCaseBuildingMain() == null) {
                    caseBuildingMain = new CaseBuildingMain();
                    BeanCopyHelp.copyPropertiesIgnoreNull(basicBuildingMain, caseBuildingMain);
                    caseBuildingMain.setId(null);
                    caseBuildingMain.setGmtCreated(null);
                    caseBuildingMain.setGmtModified(null);
                }
                //升级版本 以及更改某些数据  或者 新添数据
                if (caseBuildingMain != null) {
                    caseBuildingMainId = caseBuildingMainService.upgradeVersion(caseBuildingMain);
                }
                BasicBuilding basicBuildingQuery = new BasicBuilding();
                basicBuildingQuery.setBasicBuildingMainId(basicBuildingMain.getId());
                List<BasicBuilding> basicBuildingList = basicBuildingService.basicBuildingList(basicBuildingQuery);
                if (!ObjectUtils.isEmpty(basicBuildingList)) {
                    for (BasicBuilding basicBuilding : basicBuildingList) {
                        CaseBuilding caseBuilding = null;
                        if (basicBuilding.getCaseBuildingId() != null) {
                            caseBuilding = caseBuildingService.getCaseBuildingById(basicBuilding.getCaseBuildingId());
                            if (caseBuilding != null) {
                                BeanCopyHelp.copyPropertiesIgnoreNull(basicBuilding, caseBuilding);
                                caseBuilding.setId(basicBuilding.getCaseBuildingId());
                                caseBuilding.setCaseBuildingMainId(caseBuildingMainId);
                            }
                            if (caseBuilding == null) {
                                caseBuilding = new CaseBuilding();
                                BeanCopyHelp.copyPropertiesIgnoreNull(basicBuilding, caseBuilding);
                                caseBuilding.setCaseBuildingMainId(caseBuildingMainId);
                                caseBuilding.setId(null);
                                caseBuilding.setGmtCreated(null);
                                caseBuilding.setGmtModified(null);
                            }
                        }
                        if (basicBuilding.getCaseBuildingId() == null) {
                            caseBuilding = new CaseBuilding();
                            BeanCopyHelp.copyPropertiesIgnoreNull(basicBuilding, caseBuilding);
                            caseBuilding.setCaseBuildingMainId(caseBuildingMainId);
                            caseBuilding.setId(null);
                            caseBuilding.setGmtCreated(null);
                            caseBuilding.setGmtModified(null);
                        }
                        caseBuildingService.upgradeVersion(caseBuilding);
                    }
                }
            }
            //-----------------------------------||---------------------------
        }
    }


    /**
     * 保存数据
     *
     * @param formData
     * @param bisNextUser
     * @throws Exception
     */
    public void saveBasic(String formData, Boolean bisNextUser) throws Exception {
        if (StringUtils.isEmpty(formData)) {
            return;
        }
        BasicApply basicApply = new BasicApply();
        basicApplyService.saveBasicApply(basicApply);

        JSONObject jsonObject = JSON.parseObject(formData);
        String jsonContent = null;
        BasicEstate basicEstate = null;
        jsonContent = jsonObject.getString("basicEstate");
        if (StringUtils.isNotBlank(jsonContent)) {
            basicEstate = JSONObject.parseObject(jsonContent, BasicEstate.class);
            basicEstate.setApplyId(basicApply.getId());
            if (basicEstate.getId() != null) {
                basicEstate.setCaseEstateId(basicEstate.getId());
                basicEstate.setId(null);
            }
            try {
                basicEstateService.upgradeVersion(basicEstate);
            } catch (Exception e1) {

            }
        }
        jsonContent = null;
        jsonContent = jsonObject.getString("basicBuildingMain");
        BasicBuildingMain basicBuildingMain = null;
        if (StringUtils.isNotBlank(jsonContent)) {
            basicBuildingMain = JSONObject.parseObject(jsonContent, BasicBuildingMain.class);
            basicBuildingMain.setApplyId(basicApply.getId());
            if (basicBuildingMain.getId() != null) {
                basicBuildingMain.setCaseBuildingMain(basicBuildingMain.getId());
                basicBuildingMain.setId(null);
            }
            if (basicBuildingMain.getEstateId() == null){
                basicBuildingMain.setEstateId(basicEstate.getId());
                //当楼栋数据未包含楼盘 那么楼盘必须是新增的情况
                if (basicEstate.getId() == null){
                    throw new Exception();
                }
            }
            try {
               basicBuildingMainService.upgradeVersion(basicBuildingMain);
            } catch (Exception e1) {

            }
            jsonContent = null;
            jsonContent = jsonObject.getString("basicBuildings");
            List<BasicBuilding> basicBuildingList = null;
            try {
                basicBuildingList = JSONObject.parseArray(jsonContent, BasicBuilding.class);
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
            }
            if (!ObjectUtils.isEmpty(basicBuildingList)) {
                for (BasicBuilding basicBuilding : basicBuildingList) {
                    if (basicBuilding.getId() != null) {
                        basicBuilding.setCaseBuildingId(basicBuilding.getId());
                        basicBuilding.setId(null);
                    }
                    if (basicBuildingMain.getId() != null) {
                        basicBuilding.setBasicBuildingMainId(basicBuildingMain.getId());
                        try {
                            basicBuildingService.upgradeVersion(basicBuilding);
                        } catch (Exception e1) {

                        }
                    }
                }
            }
        }
        //发起流程
        basicApplyService.sumTask(basicApply, FormatUtils.entityNameConvertToTableName(BasicApply.class));
    }

    public BasicEstate getByAppIdBasicEstate(Integer appId) throws Exception {
        BasicEstate basicEstate = new BasicEstate();
        basicEstate.setApplyId(appId);
        List<BasicEstate> basicEstates = basicEstateService.basicEstateList(basicEstate);
        if (!ObjectUtils.isEmpty(basicEstates)) {
            return basicEstateService.getBasicEstateVo(basicEstates.get(0));
        } else {
            return null;
        }
    }

    public BasicBuildingMain getByAppIdBasicBuildingMain(Integer appId) throws Exception {
        BasicBuildingMain basicBuildingMain = new BasicBuildingMain();
        basicBuildingMain.setApplyId(appId);
        List<BasicBuildingMain> basicBuildingMains = basicBuildingMainService.basicBuildingMainList(basicBuildingMain);
        if (!ObjectUtils.isEmpty(basicBuildingMains)) {
            return basicBuildingMains.get(0);
        } else {
            return null;
        }
    }

    public List<BasicBuilding> getMainById(BasicBuildingMain buildingMain)throws Exception{
        BasicBuilding basicBuilding = new BasicBuilding();
        basicBuilding.setBasicBuildingMainId(buildingMain.getId());
        return basicBuildingService.basicBuildingList(basicBuilding);
    }

    public BasicBuildingVo getBasicBuildingVo(BasicBuilding basicBuilding){
        return basicBuildingService.getBasicBuildingVo(basicBuilding);
    }
}
