package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
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

    @RequestMapping(value = "/basicBatchApplyIndex", name = "申请首页", method = RequestMethod.GET)
    public ModelAndView basicApplyIndex() {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyIndex", "0", 0, "0", "");
        return modelAndView;
    }

    /**
     * 获取树
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getBatchApplyTree", method = RequestMethod.GET)
    public List<ZtreeDto> getZtreeDto(Integer estateId) throws Exception {
        return basicApplyBatchService.getZtreeDto(estateId);
    }

    @ResponseBody
    @RequestMapping(value = "/saveApplyInfo", name = "保存", method = {RequestMethod.POST})
    public HttpResult saveApplyInfo(BasicApplyBatch basicApplyBatch) {
        try {
            basicApplyBatchService.saveApplyInfo(basicApplyBatch);
            return HttpResult.newCorrectResult(basicApplyBatch);
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveItemData", name = "保存一条明细", method = {RequestMethod.POST})
    public HttpResult saveItemData(String formData, Integer planDetailsId) {
        try {
            BasicApplyBatchDetail basicApplyBatchDetail = JSON.parseObject(formData, BasicApplyBatchDetail.class);
            if (basicApplyBatchDetail.getBisStandard() == null) {
                basicApplyBatchDetail.setBisStandard(false);
            }
            return HttpResult.newCorrectResult(basicApplyBatchDetailService.addBasicApplyBatchDetail(basicApplyBatchDetail, planDetailsId));
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
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
        return HttpResult.newCorrectResult(basicApplyBatchDetailService.getDataById(id));
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
    public ModelAndView fillInfo(Integer formClassify, Integer formType, Integer tbId, String tbType, Integer planDetailsId) throws Exception {
        String view = "/project/stageSurvey";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        //根据类型取得所需的数据
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
                break;
            case UNIT:
                modelAndView.addObject("basicUnit", basicUnitService.getBasicUnitById(tbId));
                break;
            case HOUSE:
                Map<String, Object> basicHouseMap = basicHouseService.getBasicHouseMapById(tbId);
                basicHouse = (BasicHouse) basicHouseMap.get(FormatUtils.toLowerCaseFirstChar(BasicHouse.class.getSimpleName()));
                basicHouseTrading = (BasicHouseTrading) basicHouseMap.get(FormatUtils.toLowerCaseFirstChar(BasicHouseTrading.class.getSimpleName()));
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
        modelAndView.addObject("formType", BasicApplyTypeEnum.getEnumById(formType).getKey());
        return modelAndView;
    }


    @RequestMapping(value = "/informationDetail", name = "信息详情页面", method = RequestMethod.GET)
    public ModelAndView informationDetail(Integer type, Integer id, Integer buildingType, Integer estateId) throws Exception {
        String view = "/basic/informationDetail";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        if (id == 0) {
            modelAndView.addObject("tableId", estateId);
            BasicEstateVo basicEstateVo = publicBasicService.getBasicEstateById(estateId);
            BasicEstateLandStateVo basicEstateLandStateVo = publicBasicService.getEstateLandStateByEstateId(estateId);
            modelAndView.addObject("basicEstate", basicEstateVo);
            modelAndView.addObject("basicEstateLandState", basicEstateLandStateVo);
        } else {
            BasicApplyBatchDetail detailData = basicApplyBatchDetailService.getDataById(id);
            switch (detailData.getTableName()) {
                case "tb_basic_building":
                    BasicBuildingVo basicBuilding = publicBasicService.getBasicBuildingById(detailData.getTableId());
                    modelAndView.addObject("basicBuilding", basicBuilding);
                    break;
                case "tb_basic_unit":
                    BasicUnit basicUnit = publicBasicService.getBasicUnitById(detailData.getTableId());
                    modelAndView.addObject("basicUnit", basicUnit);
                    break;
                case "tb_basic_house":
                    BasicHouseVo basicHouseVo = publicBasicService.getBasicHouseVoById(detailData.getTableId());
                    modelAndView.addObject("basicHouse", basicHouseVo);
                    BasicHouseTradingVo basicHouseTradingVo = publicBasicService.getBasicHouseTradingByHouseId(detailData.getTableId());
                    modelAndView.addObject("basicHouseTrading", basicHouseTradingVo);
                    break;
            }
            modelAndView.addObject("tableId", detailData.getTableId());
        }
        this.setViewParam(type, id, buildingType, estateId, modelAndView);
        return modelAndView;

    }

    @ResponseBody
    @RequestMapping(value = "/saveDraft", name = "保存楼盘等")
    public HttpResult saveDraft(String formData, Integer planDetailsId) {
        try {
            basicApplyBatchService.saveDraft(formData, planDetailsId);
            return HttpResult.newCorrectResult();
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
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
            log.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return HttpResult.newErrorResult("案例申请提交异常");
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/approval", name = "审批页面", method = RequestMethod.GET)
    public ModelAndView basicApplyApproval(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyApproval", processInsId, boxId, taskId, agentUserAccount);
        try {
            BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processInsId);
            modelAndView.addObject("applyBatch", applyBatch);
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
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
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult(e1);
        }
    }


    @RequestMapping(value = "/applyEdit", name = "返回修改页面", method = RequestMethod.GET)
    public ModelAndView basicApplyEdit(String processInsId, String taskId, Integer boxId, String agentUserAccount) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyIndex", processInsId, boxId, taskId, agentUserAccount);
        try {
            BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchByProcessInsId(processInsId);
            modelAndView.addObject("applyBatch", applyBatch);
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
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
            log.error(e1.getMessage(), e1);
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
            log.error(e.getMessage(), e);
            return HttpResult.newErrorResult("删除草稿数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicAppBatchDraftList", name = "获取草稿数据列表", method = {RequestMethod.GET})
    public BootstrapTableVo getBasicAppBatchDraftList(String estateName) throws Exception {
        return basicApplyBatchService.getBootstrapTableVo(estateName, true);
    }

    @ResponseBody
    @RequestMapping(value = "/saveApplyDraftInfo", name = "保存", method = {RequestMethod.POST})
    public HttpResult saveApplyDraftInfo(BasicApplyBatch basicApplyBatch) {
        try {
            basicApplyBatch.setDraftFlag(true);
            basicApplyBatchService.saveApplyInfo(basicApplyBatch);
            return HttpResult.newCorrectResult(basicApplyBatch);
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @RequestMapping(value = "/applyAgain", name = "重新申请", method = RequestMethod.GET)
    public ModelAndView applyAgain(Integer id) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/basic/basicBatchApplyIndex", "0", 0, "0", "");
        try {
            BasicApplyBatch applyBatch = basicApplyBatchService.getInfoById(id);
            modelAndView.addObject("applyBatch", applyBatch);
        } catch (Exception e1) {
            log.error(e1.getMessage(), e1);
        }
        return modelAndView;
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
            modelAndView.addObject("tableId", singleData.getEstateId());
            //显示引用项目还是案列按钮
            modelAndView.addObject("showTab", singleData.getShowTab());
        } else {
            BasicApplyBatchDetail basicApplyBatchDetail = new BasicApplyBatchDetail();
            basicApplyBatchDetail.setId(id);
            BasicApplyBatchDetail detailData = basicApplyBatchDetailService.getSingleData(basicApplyBatchDetail);
            modelAndView.addObject("tableId", detailData.getTableId());
            BasicApplyBatch applyBatch = basicApplyBatchService.getInfoById(detailData.getApplyBatchId());
            modelAndView.addObject("showTab", applyBatch.getShowTab());
            //上级引用数据Id
            modelAndView.addObject("parentQuoteId", basicApplyBatchDetailService.getParentQuoteId(detailData));
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
            log.error(e1.getMessage(), e1);
            return HttpResult.newErrorResult("保存数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveBasicApplyBatch", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveBasicApplyBatch(String formData) {
        try {
            BasicApplyBatch applyBatch = JSON.parseObject(formData, BasicApplyBatch.class);
            basicApplyBatchService.saveApplyInfo(applyBatch);
            return HttpResult.newCorrectResult(applyBatch);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/initBasicApplyBatchInfo", method = {RequestMethod.POST}, name = "初始化")
    public HttpResult initBasicApplyBatchInfo(Integer planDetailsId,Integer classify) {
        try {
            BasicApplyBatch applyBatch = new BasicApplyBatch();
            applyBatch.setPlanDetailsId(planDetailsId);
            applyBatch.setClassify(classify);
            basicApplyBatchService.initBasicApplyBatchInfo(applyBatch);
            return HttpResult.newCorrectResult(applyBatch);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("初始化异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveApplyInfo", method = {RequestMethod.POST}, name = "保存")
    public HttpResult save(String formData, Integer planDetailsId) {
        try {
            Map<String, Object> objectMap = Maps.newHashMap();
            BasicApplyBatch applyBatch = JSON.parseObject(formData, BasicApplyBatch.class);
            applyBatch.setPlanDetailsId(planDetailsId);
            applyBatch.setShowTab(true);//显示引用案列按钮
            basicApplyBatchService.saveApplyInfo(applyBatch);
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicApplyBatch.class.getSimpleName()), applyBatch);
            return HttpResult.newCorrectResult(objectMap);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getStandardCount", name = "获取标准对象数量", method = RequestMethod.POST)
    public HttpResult getStandardCount(Integer planDetailsId) {
        try {
            List<BasicApply> basicApplyList = basicApplyService.getBasicApplyListByPlanDetailsId(planDetailsId);
            return HttpResult.newCorrectResult(CollectionUtils.isEmpty(basicApplyList) ? 0 : basicApplyList.size());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("获取标准对象数量");
        }
    }

    @RequestMapping(value = "/informationDetail", name = "信息详情页面", method = RequestMethod.GET)
    public ModelAndView informationDetail(Integer id, String buildingType, Integer estateId) throws Exception {
        String view = "/basic/informationDetail";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        Integer type = 0;
        if (id == 0) {
            modelAndView.addObject("tableId", estateId);
            BasicEstateVo basicEstateVo = publicBasicService.getBasicEstateById(estateId);
            BasicEstateLandStateVo basicEstateLandStateVo = publicBasicService.getEstateLandStateByEstateId(estateId);
            modelAndView.addObject("basicEstate", basicEstateVo);
            modelAndView.addObject("basicEstateLandState", basicEstateLandStateVo);
            type = basicApplyBatchService.getBasicApplyBatchByEstateId(estateId).getType();
        } else {
            BasicApplyBatchDetail batchDetail = null;
            switch (buildingType) {
                case "building":
                    BasicBuildingVo basicBuilding = publicBasicService.getBasicBuildingById(id);
                    modelAndView.addObject("basicBuilding", basicBuilding);
                    batchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_building", id);
                    type = basicApplyBatchService.getInfoById(batchDetail.getApplyBatchId()).getType();
                    break;
                case "unit":
                    BasicUnit basicUnit = publicBasicService.getBasicUnitById(id);
                    modelAndView.addObject("basicUnit", basicUnit);
                    batchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_unit", id);
                    type = basicApplyBatchService.getInfoById(batchDetail.getApplyBatchId()).getType();
                    break;
                case "house":
                    BasicHouseVo basicHouseVo = publicBasicService.getBasicHouseVoById(id);
                    modelAndView.addObject("basicHouse", basicHouseVo);
                    BasicHouseTradingVo basicHouseTradingVo = publicBasicService.getBasicHouseTradingByHouseId(id);
                    modelAndView.addObject("basicHouseTrading", basicHouseTradingVo);
                    batchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_house", id);
                    type = basicApplyBatchService.getInfoById(batchDetail.getApplyBatchId()).getType();
                    break;
            }
            modelAndView.addObject("tableId", id);
        }
        modelAndView.addObject("buildingType", buildingType);
        modelAndView.addObject("type", type);
        BasicApply basicApply = new BasicApply();
        basicApply.setType(type);
        modelAndView.addObject("basicApply", basicApply);
        return modelAndView;

    }
}
