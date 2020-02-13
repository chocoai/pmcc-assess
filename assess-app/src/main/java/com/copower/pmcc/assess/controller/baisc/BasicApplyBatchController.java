package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseVo;
import com.copower.pmcc.assess.dto.output.project.survey.BasicApplyBatchDetailVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.chks.ChksAssessmentProjectPerformanceService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.chks.api.dto.AssessmentProjectPerformanceDto;
import com.copower.pmcc.chks.api.provider.ChksRpcAssessmentService;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-10-24.
 * 案例基础数据
 */
@Controller
@RequestMapping("/basicApplyBatch")
public class BasicApplyBatchController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ChksRpcAssessmentService chksRpcAssessmentService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private BaseParameterService baseParameterService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private ChksAssessmentProjectPerformanceService chksAssessmentProjectPerformanceService;

    //-----------------------------------------------案例批量申请
    @RequestMapping(value = "/basicBatchApplyIndex", name = "申请首页", method = RequestMethod.GET)
    public ModelAndView basicApplyIndex(Integer estateId, String estateName) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyIndex", "0", 0, "0", "");
        BasicApplyBatch basicApplyBatch = new BasicApplyBatch();
        basicApplyBatch.setDraftFlag(true);
        if (estateId != null && estateId != 0) {//大类与类型可以确定
            BasicEstate basicEstate = basicEstateService.getBasicEstateById(estateId);
            if (basicEstate != null) {
                basicApplyBatch.setClassify(basicEstate.getClassify());
                basicApplyBatch.setType(basicEstate.getType());
                basicApplyBatch.setEstateId(basicEstate.getId());
                basicApplyBatch.setCaseEstateId(basicEstate.getId());
            }
        }
        basicApplyBatch.setEstateName(estateName);
        basicApplyBatchService.addBasicApplyBatch(basicApplyBatch);
        modelAndView.addObject("basicApplyBatch", basicApplyBatch);
        modelAndView.addObject("formClassifyList", basicApplyBatchService.getFormClassifyList());
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/initBasicApplyBatchCase", method = RequestMethod.POST)
    public HttpResult initBasicApplyBatchCase(BasicApplyBatch basicApplyBatch) {
        try {
            basicApplyBatchService.initBasicApplyBatchInfo(basicApplyBatch);
            return HttpResult.newCorrectResult(basicApplyBatch);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("初始化异常");
        }
    }

    @RequestMapping(value = "/applyAgain", name = "重新申请", method = RequestMethod.GET)
    public ModelAndView applyAgain(Integer id) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyIndex", "0", 0, "0", "");
        try {
            BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(id);
            modelAndView.addObject("basicApplyBatch", basicApplyBatch);
            modelAndView.addObject("formClassifyList", basicApplyBatchService.getFormClassifyList());
            modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    /**
     * 新增楼盘时获取树
     * 初始化treeByPlanDetailsId
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getBatchApplyTree", method = RequestMethod.GET)
    public List<ZtreeDto> getBatchApplyTree(Integer basicApplyBatchId) throws Exception {
        return basicApplyBatchService.getZtreeDto(basicApplyBatchId);
    }

    /**
     * 获取楼盘下案例结构
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCaseEstateZtreeDtos", method = RequestMethod.GET)
    public List<ZtreeDto> getCaseEstateZtreeDtos(Integer estateId) {
        return basicApplyBatchService.getCaseEstateZtreeDtos(estateId);
    }

    @ResponseBody
    @RequestMapping(value = "/initCaseEstateZtree", name = "初始化支撑新增获取升级案例结构", method = RequestMethod.POST)
    public HttpResult initCaseEstateZtree(Integer applyBatchId, Integer caseBatchDetailId,Boolean containThis) {
        try {
            BasicApplyBatchDetail batchDetail = basicApplyBatchService.initCaseEstateZtree(applyBatchId, caseBatchDetailId,containThis);
            return HttpResult.newCorrectResult(batchDetail);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("初始化支撑新增获取升级案例结构异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/upgradeCase", name = "案例升级", method = RequestMethod.POST)
    public HttpResult upgradeCase(Integer applyBatchId, Integer pid, Integer caseBatchDetailId) {
        try {
            BasicApplyBatchDetail batchDetail = basicApplyBatchService.upgradeCase(applyBatchId, pid, caseBatchDetailId);
            return HttpResult.newCorrectResult(batchDetail);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("案例升级异常");
        }
    }

    //-----------------------------------------------案例批量申请


    /**
     * 初始化treeByPlanDetailsId
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTreeByPlanDetailsId", method = RequestMethod.GET)
    public List<ZtreeDto> getTreeByPlanDetailsId(Integer planDetailsId) throws Exception {
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(planDetailsId);
        return basicApplyBatchService.getZtreeDto(applyBatch.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicApplyBatchDetailList", method = RequestMethod.GET)
    public HttpResult getBasicApplyBatchDetailList(BasicApplyBatchDetail basicApplyBatchDetail) throws Exception {
        try {
            return HttpResult.newCorrectResult(basicApplyBatchDetailService.getBasicApplyBatchDetailList(basicApplyBatchDetail));
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveApplyInfo", name = "保存", method = {RequestMethod.POST})
    public HttpResult saveApplyInfo(BasicApplyBatch basicApplyBatch) {
        try {
            basicApplyBatchService.saveBasicApplyBatch(basicApplyBatch);
            return HttpResult.newCorrectResult(basicApplyBatch);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveItemData", name = "保存一条明细", method = {RequestMethod.POST})
    public HttpResult saveItemData(String formData, Integer planDetailsId) {
        try {
            BasicApplyBatchDetail basicApplyBatchDetail = JSON.parseObject(formData, BasicApplyBatchDetail.class);
            BasicApplyBatchDetailVo basicApplyBatchDetailVo = basicApplyBatchDetailService.getBasicApplyBatchDetailVo(basicApplyBatchDetailService.addBasicApplyBatchDetail(basicApplyBatchDetail, planDetailsId));
            return HttpResult.newCorrectResult(basicApplyBatchDetailVo);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    /**
     * 删除操作手册信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteDetail", name = "删除一条明细", method = RequestMethod.POST)
    public HttpResult deleteDetail(Integer id) throws Exception {
        try {
            basicApplyBatchDetailService.deleteBasicApplyBatchDetail(id);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    /**
     * 获取和编辑
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAndEditDetail", name = "获取和编辑", method = RequestMethod.GET)
    public HttpResult getAndEditDetail(Integer id) {
        return HttpResult.newCorrectResult(basicApplyBatchDetailService.getBasicApplyBatchDetailVo(basicApplyBatchDetailService.getDataById(id)));
    }

    @RequestMapping(value = "/fillInformation", name = "填写信息页面", method = RequestMethod.GET)
    public ModelAndView fillInformation(Integer type, Integer id, Integer buildingType, Integer estateId, Integer planDetailsId) throws Exception {
        String view = "/basic/fillInformation";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("planDetailsId", planDetailsId);
        this.setViewParam(type, id, buildingType, estateId, modelAndView);

        return modelAndView;
    }

    @RequestMapping(value = "/fillInfo", name = "填写信息页面", method = RequestMethod.GET)
    public ModelAndView fillInfo(Integer applyBatchId, Integer formClassify, Integer formType, Integer tbId, String tbType, Integer planDetailsId) throws Exception {
        String view = "/project/stageSurvey";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        //根据类型取得所需的数据
        Integer quoteId = 0;
        BasicApplyBatchDetail basicApplyBatchDetail = null;
        BasicEstate basicEstate = null;
        BasicHouse basicHouse = null;
        BasicHouseTrading basicHouseTrading = null;
        EstateTaggingTypeEnum estateTaggingTypeEnum = EstateTaggingTypeEnum.getEnumByKey(tbType);
        switch (estateTaggingTypeEnum) {
            case ESTATE:
                Map<String, Object> basicEstateMap = basicEstateService.getBasicEstateMapById(tbId);
                basicEstate = (BasicEstate) basicEstateMap.get(FormatUtils.toLowerCaseFirstChar(BasicEstate.class.getSimpleName()));
                modelAndView.addObject("basicEstate", basicEstate);
                modelAndView.addObject("basicEstateLandState", basicEstateMap.get(FormatUtils.toLowerCaseFirstChar(BasicEstateLandState.class.getSimpleName())));
                break;
            case BUILDING:
                modelAndView.addObject("basicBuilding", basicBuildingService.getBasicBuildingById(tbId));
                BasicApplyBatchDetail batchDetailBuild = basicApplyBatchDetailService.getBasicApplyBatchDetail(applyBatchId, FormatUtils.entityNameConvertToTableName(BasicBuilding.class), tbId);
                modelAndView.addObject("bisStructure", batchDetailBuild.getBisStructure());
                basicApplyBatchDetail = basicApplyBatchDetailService.getDataById(batchDetailBuild.getPid());
                if (basicApplyBatchDetail != null) {
                    quoteId = basicApplyBatchDetail.getQuoteId();
                }
                break;
            case UNIT:
                modelAndView.addObject("basicUnit", basicUnitService.getBasicUnitById(tbId));
                BasicApplyBatchDetail batchDetailUnit = basicApplyBatchDetailService.getBasicApplyBatchDetail(applyBatchId, FormatUtils.entityNameConvertToTableName(BasicUnit.class), tbId);
                basicApplyBatchDetail = basicApplyBatchDetailService.getDataById(batchDetailUnit.getPid());
                if (basicApplyBatchDetail != null) {
                    quoteId = basicApplyBatchDetail.getQuoteId();
                }
                break;
            case HOUSE:
                Map<String, Object> basicHouseMap = basicHouseService.getBasicHouseMapById(tbId);
                basicHouse = (BasicHouse) basicHouseMap.get(FormatUtils.toLowerCaseFirstChar(BasicHouse.class.getSimpleName()));
                basicHouseTrading = (BasicHouseTrading) basicHouseMap.get(FormatUtils.toLowerCaseFirstChar(BasicHouseTrading.class.getSimpleName()));
                BasicApplyBatchDetail batchDetailHouse = basicApplyBatchDetailService.getBasicApplyBatchDetail(applyBatchId, FormatUtils.entityNameConvertToTableName(BasicHouse.class), tbId);
                basicApplyBatchDetail = basicApplyBatchDetailService.getDataById(batchDetailHouse.getPid());
                if (basicApplyBatchDetail != null) {
                    quoteId = basicApplyBatchDetail.getQuoteId();
                }
                break;
        }

        //根据表单大类 类型可确定使用哪个view，
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(formClassify);
        if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND_ONLY.equals(baseDataDic.getFieldName())) {
            view = view + "/landOnly/index";
            BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(planDetailsId);
            if (basicApply != null && basicApply.getBasicHouseId() != null) {
                Map<String, Object> basicHouseMap = basicHouseService.getBasicHouseMapById(basicApply.getBasicHouseId());
                basicHouse = (BasicHouse) basicHouseMap.get(FormatUtils.toLowerCaseFirstChar(BasicHouse.class.getSimpleName()));
                basicHouseTrading = (BasicHouseTrading) basicHouseMap.get(FormatUtils.toLowerCaseFirstChar(BasicHouseTrading.class.getSimpleName()));
            }
        }
        if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND.equals(baseDataDic.getFieldName())) {
            view = view + "/land/index";
        }
        if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_SINGEL.equals(baseDataDic.getFieldName()) || AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_MULTIPLE.equals(baseDataDic.getFieldName())) {
            view = view + "/house/index";
        }
        modelAndView.setViewName(view);
        modelAndView.addObject("basicHouse", basicHouse);
        modelAndView.addObject("basicHouseTrading", basicHouseTrading);
        modelAndView.addObject("planDetailsId", planDetailsId);
        modelAndView.addObject("tbType", tbType);
        modelAndView.addObject("formClassify", formClassify);
        modelAndView.addObject("tbId", tbId);
        modelAndView.addObject("quoteId", quoteId);
        modelAndView.addObject("formType", BasicApplyTypeEnum.getEnumById(formType).getKey());
        List<CrmBaseDataDicDto> unitPropertiesList = projectInfoService.getUnitPropertiesList();
        modelAndView.addObject("unitPropertiesList", unitPropertiesList);
        if (applyBatchId != null) {
            BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(applyBatchId);
            modelAndView.addObject(StringUtils.uncapitalize(BasicApplyBatch.class.getSimpleName()), basicApplyBatch);
        }
        return modelAndView;
    }


    @RequestMapping(value = "/informationDetail", name = "信息详情页面", method = RequestMethod.GET)
    public ModelAndView informationDetail(Integer formClassify, Integer formType, Integer tableId, String tableName, String tbType, Integer planDetailsId, Integer applyBatchId, boolean isHistory, Integer assessmentPerformanceId) throws Exception {
        final StringBuffer stringBuffer = new StringBuffer("/project/stageSurvey");
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(stringBuffer.toString());
        if (formType != null)
            modelAndView.addObject("formType", BasicApplyTypeEnum.getEnumById(formType).getKey());
        setViewParam(modelAndView, tableName, tbType, tableId, applyBatchId);
        //查看历史记录标识
        if (isHistory) {
            modelAndView.addObject("isHistory", isHistory);
        }
        //根据表单大类 类型可确定使用哪个view，因为现在的查勘分为房屋和土地以及房屋带土地,其中这三者都有可能使用相同的表单,因此上面参数直接使用表单名称和表单id来获取参数，而这里会参照fillInfo()来设计表单view路径
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(formClassify);
        if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND_ONLY.equals(baseDataDic.getFieldName())) {
            stringBuffer.append("/landOnly/detail/index");
            BasicApply basicApply = basicApplyService.getBasicApplyByPlanDetailsId(planDetailsId);
            if (basicApply != null && basicApply.getBasicHouseId() != null) {
                setViewParam(modelAndView, FormatUtils.entityNameConvertToTableName(BasicHouse.class), EstateTaggingTypeEnum.HOUSE.getKey(), basicApply.getBasicHouseId(), applyBatchId);
                setViewParam(modelAndView, FormatUtils.entityNameConvertToTableName(BasicEstate.class), EstateTaggingTypeEnum.ESTATE.getKey(), basicApply.getBasicEstateId(), applyBatchId);
            }
        }
        if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_LAND.equals(baseDataDic.getFieldName())) {
            stringBuffer.append("/land/detail/");
            EstateTaggingTypeEnum estateTaggingTypeEnum = EstateTaggingTypeEnum.getEnumByKey(tbType);
            if (estateTaggingTypeEnum != null) {
                switch (estateTaggingTypeEnum) {
                    case ESTATE:
                        stringBuffer.append("estate");
                        break;
                    case BUILDING:
                        stringBuffer.append("building");
                        break;
                    case UNIT:
                        stringBuffer.append("unit");
                        break;
                    case HOUSE:
                        stringBuffer.append("house");
                        break;
                }
            }
            if (estateTaggingTypeEnum == null) {
                stringBuffer.append("index");
            }
            modelAndView.addObject("tbType", tbType);
        }
        if (AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_SINGEL.equals(baseDataDic.getFieldName()) || AssessDataDicKeyConstant.PROJECT_SURVEY_FORM_CLASSIFY_MULTIPLE.equals(baseDataDic.getFieldName())) {
            stringBuffer.append("/house/detail/");
            EstateTaggingTypeEnum estateTaggingTypeEnum = EstateTaggingTypeEnum.getEnumByKey(tbType);
            if (estateTaggingTypeEnum != null) {
                switch (estateTaggingTypeEnum) {
                    case ESTATE:
                        stringBuffer.append("estate");
                        break;
                    case BUILDING:
                        BasicApplyBatchDetail batchDetailBuild = basicApplyBatchDetailService.getBasicApplyBatchDetail(applyBatchId, FormatUtils.entityNameConvertToTableName(BasicBuilding.class), tableId);
                        //构筑物页面
                        if (batchDetailBuild.getBisStructure()) {
                            stringBuffer.append("structures");
                        } else {
                            stringBuffer.append("building");
                        }
                        break;
                    case UNIT:
                        stringBuffer.append("unit");
                        break;
                    case HOUSE:
                        stringBuffer.append("house");
                        break;
                }
            }
            if (estateTaggingTypeEnum == null) {
                stringBuffer.append("index");
            }
        }
        modelAndView.setViewName(stringBuffer.toString());
        try {
            chksParams(modelAndView, planDetailsId, assessmentPerformanceId);
        } catch (Exception e) {
            logger.error("考核参数异常");
        }
        return modelAndView;
    }

    /**
     * 考核参数
     *
     * @param modelAndView
     * @param planDetailsId
     */
    private void chksParams(ModelAndView modelAndView, Integer planDetailsId, Integer assessmentPerformanceId) {
        ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
        BoxReDto boxReDto = chksAssessmentProjectPerformanceService.getBoxReDto(projectPlanDetails.getProcessInsId());
        AssessmentProjectPerformanceDto assessmentProjectPerformanceDto = chksRpcAssessmentService.getAssessmentProjectPerformanceById(assessmentPerformanceId);
        modelAndView.addObject(StringUtils.uncapitalize(AssessmentProjectPerformanceDto.class.getSimpleName()), assessmentProjectPerformanceDto);
        modelAndView.addObject(StringUtils.uncapitalize(BoxReDto.class.getSimpleName()), boxReDto);
        modelAndView.addObject(StringUtils.uncapitalize(ProjectPlanDetails.class.getSimpleName()), projectPlanDetails);
        modelAndView.addObject(StringUtils.uncapitalize(SysUserDto.class.getSimpleName()), processControllerComponent.getThisUserInfo());
        //当前节点  可以查看的权限节点信息列表
        modelAndView.addObject("activityDtoList", chksAssessmentProjectPerformanceService.getAssessmentProjectPerformanceNext(assessmentProjectPerformanceDto.getBoxId(), assessmentProjectPerformanceDto.getActivityId(), null, chksAssessmentProjectPerformanceService.getSpotCheck(assessmentProjectPerformanceDto.getBoxId(), processControllerComponent.getThisUser())));
    }

    /**
     * 设置参数
     *
     * @param modelAndView
     * @param tableName
     * @param tbType
     * @param tableId
     * @param applyBatchId
     * @throws Exception
     */
    private void setViewParam(ModelAndView modelAndView, String tableName, String tbType, Integer tableId, Integer applyBatchId) throws Exception {
        //楼盘表单
        if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicEstate.class)) || Objects.equal(tbType, EstateTaggingTypeEnum.ESTATE.getKey())) {
            BasicEstateVo basicEstateVo = publicBasicService.getBasicEstateById(tableId);
            modelAndView.addObject(StringUtils.uncapitalize(BasicEstate.class.getSimpleName()), basicEstateVo);
            modelAndView.addObject(StringUtils.uncapitalize(BasicEstateLandState.class.getSimpleName()), basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateService.getLandStateByEstateId(basicEstateVo.getId())));
        }
        //楼栋表单
        if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicBuilding.class)) || Objects.equal(tbType, EstateTaggingTypeEnum.BUILDING.getKey())) {
            modelAndView.addObject(StringUtils.uncapitalize(BasicApplyBatchDetail.class.getSimpleName()), basicApplyBatchDetailService.getBasicApplyBatchDetail(tableName, tableId));
            modelAndView.addObject(StringUtils.uncapitalize(BasicBuilding.class.getSimpleName()), publicBasicService.getBasicBuildingById(tableId));
        }
        //单元表单
        if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicUnit.class)) || Objects.equal(tbType, EstateTaggingTypeEnum.UNIT.getKey())) {
            modelAndView.addObject(StringUtils.uncapitalize(BasicApplyBatchDetail.class.getSimpleName()), basicApplyBatchDetailService.getBasicApplyBatchDetail(tableName, tableId));
            modelAndView.addObject(StringUtils.uncapitalize(BasicUnit.class.getSimpleName()), publicBasicService.getBasicUnitById(tableId));
        }
        //房屋表单
        if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicHouse.class)) || Objects.equal(tbType, EstateTaggingTypeEnum.HOUSE.getKey())) {
            modelAndView.addObject(StringUtils.uncapitalize(BasicApplyBatchDetail.class.getSimpleName()), basicApplyBatchDetailService.getBasicApplyBatchDetail(tableName, tableId));
            BasicHouseVo basicHouseVo = publicBasicService.getBasicHouseVoById(tableId);
            modelAndView.addObject(StringUtils.uncapitalize(BasicHouse.class.getSimpleName()), basicHouseVo);
            modelAndView.addObject(StringUtils.uncapitalize(BasicHouseTrading.class.getSimpleName()), basicHouseTradingService.getBasicHouseTradingVo(basicHouseTradingService.getTradingByHouseId(basicHouseVo.getId())));
        }
        if (applyBatchId != null) {
            BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(applyBatchId);
            modelAndView.addObject(StringUtils.uncapitalize(BasicApplyBatch.class.getSimpleName()), basicApplyBatch);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveDraft", name = "保存楼盘等")
    public HttpResult saveDraft(String formData, Integer applyBatchId, Integer planDetailsId) {
        try {
            basicApplyBatchService.saveDraft(formData, applyBatchId, planDetailsId);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicApplyBatchSubmit", name = "数据申请 提交", method = RequestMethod.POST)
    public HttpResult basicApplyBatchSubmit(Integer id) {
        try {
            //发起流程
            basicApplyBatchService.processStartSubmit(id);
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("案例申请提交异常");
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/approval", name = "审批页面", method = RequestMethod.GET)
    public ModelAndView basicApplyApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyApproval", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject("formClassifyList", basicApplyBatchService.getFormClassifyList());
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        try {
            BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processInsId);
            modelAndView.addObject("applyBatch", applyBatch);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/detail", name = "详情页面", method = RequestMethod.GET)
    public ModelAndView basicApplyBatchDetail(String processInsId, Integer boxId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyDetail", processInsId, boxId, "-1", null);
        modelAndView.addObject("formClassifyList", basicApplyBatchService.getFormClassifyList());
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        try {
            BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processInsId);
            modelAndView.addObject("applyBatch", applyBatch);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/basicApprovalSubmit", name = "审批页面 提交")
    public HttpResult basicApprovalSubmit(ApprovalModelDto approvalModelDto) {
        try {
            basicApplyBatchService.processApprovalSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult(e1);
        }
    }


    @RequestMapping(value = "/applyEdit", name = "返回修改页面", method = RequestMethod.GET)
    public ModelAndView basicApplyEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyIndex", processInsId, boxId, taskId, agentUserAccount);
        modelAndView.addObject("formClassifyList", basicApplyBatchService.getFormClassifyList());
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        try {
            BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processInsId);
            modelAndView.addObject("applyBatch", applyBatch);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
        }
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/editSubmit", name = "返回修改 提交")
    public HttpResult basicEditSubmit(ApprovalModelDto approvalModelDto) {
        try {
            basicApplyBatchService.processEditSubmit(approvalModelDto);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("返回修改提交异常");
        }
    }

    @RequestMapping(value = "/basicBatchAppDraftListView", name = "草稿页面", method = {RequestMethod.GET})
    public ModelAndView basicAppListView() {
        String view = "/basic/basicBatchAppDraftListView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicBatchApply", name = "删除草稿数据")
    public HttpResult deleteBasicBatchApply(Integer id) {
        try {
            basicApplyBatchService.deleteBasicBatchApply(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("删除草稿数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicAppBatchDraftList", name = "获取草稿数据列表", method = {RequestMethod.GET})
    public BootstrapTableVo getBasicAppBatchDraftList(String estateName) throws Exception {
        return basicApplyBatchService.getBootstrapTableVo(estateName);
    }

    @RequestMapping(value = "/draftDetail", name = "草稿详情页面", method = RequestMethod.GET)
    public ModelAndView basicApplyBatchDetail(Integer id) {
        String boxName = baseParameterService.getParameterValues(BaseParameterEnum.CASE_BASE_INFO_BATCH_APPLY_KEY.getParameterKey());
        Integer boxId = bpmRpcBoxService.getBoxIdByBoxName(boxName);
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchById(id);
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyDetail", applyBatch.getProcessInsId(), boxId, "-1", null);
        modelAndView.addObject("applyBatch", applyBatch);
        modelAndView.addObject("formClassifyList", basicApplyBatchService.getFormClassifyList());
        modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/saveApplyDraftInfo", name = "保存", method = {RequestMethod.POST})
    public HttpResult saveApplyDraftInfo(BasicApplyBatch basicApplyBatch) {
        try {
            basicApplyBatch.setDraftFlag(true);
            basicApplyBatchService.saveBasicApplyBatch(basicApplyBatch);
            return HttpResult.newCorrectResult(basicApplyBatch);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/verification", name = "验证楼盘是否已在案列库", method = {RequestMethod.POST})
    public HttpResult verification(BasicApplyBatch basicApplyBatch) {
        try {
            Integer caseEstateId = basicApplyBatchService.verification(basicApplyBatch);
            return HttpResult.newCorrectResult(caseEstateId);
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }


    /**
     * 设置参数
     *
     * @param modelAndView
     * @throws Exception
     */
    private void setViewParam(Integer type, Integer id, Integer buildingType, Integer estateId, ModelAndView modelAndView) {
        if (buildingType == 0)
            modelAndView.addObject("buildingType", "estate");
        if (buildingType == 1)
            modelAndView.addObject("buildingType", "building");
        if (buildingType == 2)
            modelAndView.addObject("buildingType", "unit");
        if (buildingType == 3)
            modelAndView.addObject("buildingType", "house");

        if (buildingType == 0) {
            BasicApplyBatch applyBatch = new BasicApplyBatch();
            applyBatch.setEstateId(estateId);
            BasicApplyBatch singleData = basicApplyBatchService.getSingleData(applyBatch);
            if (singleData != null) {
                modelAndView.addObject("tableId", singleData.getEstateId());
                //显示引用项目还是案列按钮
                modelAndView.addObject("showTab", singleData.getShowTab());
            }
        } else {
            BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
            basicApplyBatchDetail.setId(id);
            BasicApplyBatchDetail detailData = basicApplyBatchDetailService.getSingleData(basicApplyBatchDetail);
            modelAndView.addObject("tableId", detailData.getTableId());
            BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchById(detailData.getApplyBatchId());
            modelAndView.addObject("showTab", applyBatch.getShowTab());
        }
        BasicApply basicApply = new BasicApply();
        basicApply.setType(type);
        modelAndView.addObject("type", type);
        modelAndView.addObject("basicApply", basicApply);
        modelAndView.addObject("isApplyBatch", "show");
        List<CrmBaseDataDicDto> unitPropertiesList = projectInfoService.getUnitPropertiesList();
        modelAndView.addObject("unitPropertiesList", unitPropertiesList);
    }


    @ResponseBody
    @RequestMapping(value = "/getEstateDataByPlanDetailsId", method = {RequestMethod.GET}, name = "获取查勘下楼盘列表")
    public BootstrapTableVo getEstateDataByPlanDetailsId(Integer planDetailsId) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstateVo> vos = Lists.newArrayList();
        BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(planDetailsId);
        if (applyBatch != null) {
            BasicEstate estate = basicEstateService.getBasicEstateById(applyBatch.getEstateId());
            vos.add(basicEstateService.getBasicEstateVo(estate));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicEstateVo>(10) : vos);
        return vo;
    }

    /**
     * 获取和编辑
     *
     * @param planDetailsId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDataByPlanDetailsId", name = "通过PlanDetailsId获取", method = RequestMethod.GET)
    public HttpResult getDataByPlanDetailsId(Integer planDetailsId) {
        return HttpResult.newCorrectResult(basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(planDetailsId));
    }

    @ResponseBody
    @RequestMapping(value = "/paste", name = "粘贴", method = {RequestMethod.POST})
    public HttpResult paste(Integer sourceBatchDetailId, Integer targeBatchDetailId) {
        try {
            basicApplyBatchService.pasteExamineInfo(sourceBatchDetailId, targeBatchDetailId);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deepCopy", name = "深复制", method = {RequestMethod.POST})
    public HttpResult deepCopy(Integer sourceBatchDetailId, Integer planDetailsId) {
        try {
            basicApplyBatchService.deepCopy(sourceBatchDetailId);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveBasicApplyBatch", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveBasicApplyBatch(String formData) {
        try {
            BasicApplyBatch applyBatch = JSON.parseObject(formData, BasicApplyBatch.class);
            basicApplyBatchService.saveBasicApplyBatch(applyBatch);
            return HttpResult.newCorrectResult(applyBatch);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/initBasicApplyBatchInfo", method = {RequestMethod.POST}, name = "初始化")
    public HttpResult initBasicApplyBatchInfo(Integer planDetailsId, Integer classify, Integer type, Integer buildingStatus) {
        try {
            BasicApplyBatch applyBatch = new BasicApplyBatch();
            applyBatch.setPlanDetailsId(planDetailsId);
            applyBatch.setClassify(classify);
            applyBatch.setType(type);
            applyBatch.setBuildingStatus(buildingStatus);
            basicApplyBatchService.initBasicApplyBatchInfo(applyBatch);
            return HttpResult.newCorrectResult(applyBatch);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("初始化异常");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/editBuildingStatus", method = {RequestMethod.POST}, name = "保存")
    public HttpResult editBuildingStatus(String formData) {
        try {
            BasicApplyBatch applyBatch = JSON.parseObject(formData, BasicApplyBatch.class);
            //修改权证建筑状态
            if (applyBatch.getBuildingStatus() != null) {
                ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(applyBatch.getPlanDetailsId());
                if (planDetails != null && planDetails.getDeclareRecordId() != null) {
                    DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(planDetails.getDeclareRecordId());
                    declareRecord.setBuildingStatus(applyBatch.getBuildingStatus());
                    declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
                }
            }
            basicApplyBatchService.saveBasicApplyBatch(applyBatch);
            return HttpResult.newCorrectResult(applyBatch);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


}
