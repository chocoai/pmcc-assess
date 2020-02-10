package com.copower.pmcc.assess.controller.cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandState;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateLandState;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 17:13
 * @Description:
 */
@RequestMapping(value = "/caseEstate")
@Controller
public class CaseEstateController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CaseEstateService caseEstateService;
    @Autowired
    private CaseEstateLandStateService caseEstateLandStateService;
    @Autowired
    private CaseBuildingService caseBuildingService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CaseEstateNetworkService caseEstateNetworkService;
    @Autowired
    private CaseEstateParkingService caseEstateParkingService;
    @Autowired
    private CaseEstateSupplyService caseEstateSupplyService;
    @Autowired
    private CaseMatchingEducationService caseMatchingEducationService;
    @Autowired
    private CaseMatchingEnvironmentService caseMatchingEnvironmentService;
    @Autowired
    private CaseMatchingFinanceService caseMatchingFinanceService;
    @Autowired
    private CaseMatchingLeisurePlaceService caseMatchingLeisurePlaceService;
    @Autowired
    private CaseMatchingMaterialService caseMatchingMaterialService;
    @Autowired
    private CaseMatchingMedicalService caseMatchingMedicalService;
    @Autowired
    private CaseMatchingTrafficService caseMatchingTrafficService;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
    @Autowired
    private BasicEstateDao basicEstateDao;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;


    @RequestMapping(value = "/detailView", name = "转到详情页面 ", method = RequestMethod.GET)
    public ModelAndView detailView(Integer id) throws Exception {
        String view = "project/stageSurvey/house/detail/estate";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        if (id != null && id.intValue() != 0) {
            //使用查勘html
            //复制到basic用来显示
            //已复制则更新
            BasicEstate estate = new BasicEstate();
            estate.setDisplayCaseId(id);
            List<BasicEstate> basicEstates = basicEstateDao.basicEstateList(estate);
            if (CollectionUtils.isNotEmpty(basicEstates)) {
                estate = basicEstates.get(0);
            } else {
                basicEstateDao.addBasicEstate(estate);
            }
            caseEstateService.quoteCaseEstateToBasic(id, estate.getId());
            estate.setDisplayCaseId(id);
            basicEstateDao.updateBasicEstate(estate, false);
            BasicEstateVo basicEstateVo = publicBasicService.getBasicEstateById(estate.getId());
            modelAndView.addObject(StringUtils.uncapitalize(BasicEstate.class.getSimpleName()), basicEstateVo);
            modelAndView.addObject(StringUtils.uncapitalize(BasicEstateLandState.class.getSimpleName()), basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateService.getLandStateByEstateId(basicEstateVo.getId())));
            CaseEstate caseEstate = caseEstateService.getCaseEstateById(id);
            if (caseEstate.getType() != null) {
                modelAndView.addObject("formType", BasicApplyTypeEnum.getEnumById(caseEstate.getType()).getKey());
            }

        }
        return modelAndView;
    }

    @RequestMapping(value = "/detail", name = "楼盘相关信息地图标注", method = {RequestMethod.GET})
    public ModelAndView index(Integer estateId) {
        ModelAndView modelAndView = processControllerComponent.baseFormModelAndView("/case/caseEstate/caseTaggingView", "0", 0, "0", "");
        CaseEstate caseEstate = caseEstateService.getCaseEstateById(estateId);
        modelAndView.addObject("estateId", estateId == null ? 0 : estateId);
        modelAndView.addObject("estateName", caseEstate.getName());
        modelAndView.addObject("thisTitle", "楼盘信息标注");
        return modelAndView;
    }

    @ResponseBody
    @GetMapping(value = "/getEstateTaggingList", name = "获取标注信息列表")
    public BootstrapTableVo getEstateTaggingList(Integer dataId, String type) {
        try {
            return caseEstateTaggingService.getEstateTaggingList(dataId, type);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseEstateById", method = {RequestMethod.GET}, name = "获取案例 楼盘")
    public HttpResult getCaseEstateById(Integer id) {
        CaseEstate caseEstate = null;
        try {
            if (id != null) {
                caseEstate = caseEstateService.getCaseEstateById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseEstate);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseEstateById", method = {RequestMethod.POST}, name = "删除案例 楼盘")
    public HttpResult deleteCaseEstateById(Integer id) {
        List<CaseBuilding> caseBuildingList = null;
        CaseEstate caseEstate = null;
        CaseBuilding caseBuilding = new CaseBuilding();
        try {
            if (id != null && id.intValue() != 0) {
                caseEstate = caseEstateService.getCaseEstateById(id);
                caseBuilding.setEstateId(id);
                caseBuildingList = caseBuildingService.getCaseBuildingList(caseBuilding);
                if (caseBuildingList.size() >= 1) {
                    return HttpResult.newErrorResult("请删除此楼盘下的楼栋之后在删除此楼盘! remove fail");
                }
                caseEstateService.deleteCaseEstate(id);
                return HttpResult.newCorrectResult(caseEstate.getBlockId());
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseEstate", method = {RequestMethod.POST}, name = "更新案例 楼盘")
    public HttpResult saveAndUpdateCaseEstate(String formData) {
        JSONObject jsonObject = JSON.parseObject(formData);
        CaseEstate caseEstate = null;
        CaseEstateLandState caseEstateLandState = null;
        String jsonContent = null;
        try {
            try {
                jsonContent = jsonObject.getString("estate");
                caseEstate = JSONObject.parseObject(jsonContent, CaseEstate.class);
                jsonContent = null;
                jsonContent = jsonObject.getString("landState");
                caseEstateLandState = JSONObject.parseObject(jsonContent, CaseEstateLandState.class);
            } catch (Exception e1) {
                logger.error(String.format("exception: %s", e1.getMessage()), e1);
                return HttpResult.newErrorResult("解析异常");
            }
            Integer estateId = null;
            if (caseEstate != null) {
                if (caseEstate.getId() == null) {
                    if (caseEstate.getBlockId() == null) {
                        caseEstate.setBlockId(0);
                    }
                    estateId = caseEstateService.saveAndUpdateCaseEstate(caseEstate);
                } else {
                    caseEstateService.saveAndUpdateCaseEstate(caseEstate);
                }
            }
            if (estateId != null && estateId.intValue() != 0) {
                caseEstateLandState.setEstateId(estateId);
                //更新附件
                baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseEstate.class), estateId);
            }
            caseEstateLandStateService.saveAndUpdateCaseEstateLandState(caseEstateLandState);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseEstateList", method = {RequestMethod.GET}, name = "获取案例 楼盘列表")
    public HttpResult caseEstateList(String name) {
        CaseEstate caseEstate = new CaseEstate();
        if (!StringUtils.isEmpty(name)) {
            caseEstate.setName(name);
        }
        List<CaseEstate> caseEstateList = caseEstateService.getCaseEstateList(caseEstate);
        return HttpResult.newCorrectResult(caseEstateList);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseEstateVos", method = {RequestMethod.GET}, name = "获取案例 楼盘列表")
    public BootstrapTableVo getCaseEstateVos(String name, String province, String city, String district) {
        CaseEstate caseEstate = new CaseEstate();
        if (!StringUtils.isEmpty(name)) {
            caseEstate.setName(name);
        }
        if (!StringUtils.isEmpty(province)) {
            caseEstate.setProvince(province);
        }
        if (!StringUtils.isEmpty(city)) {
            caseEstate.setCity(city);
        }
        if (!StringUtils.isEmpty(district)) {
            caseEstate.setDistrict(district);
        }
        return caseEstateService.getCaseEstateVos(caseEstate);
    }

    @ResponseBody
    @RequestMapping(value = "/autoCompleteCaseEstate", method = {RequestMethod.GET}, name = "楼盘 信息自动补全")
    public HttpResult autoCompleteCaseEstate(String name, String province, String city) {
        try {
            List<CustomCaseEntity> caseEstateList = caseEstateService.autoCompleteCaseEstate(name, province, city);
            return HttpResult.newCorrectResult(caseEstateList);
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/quoteCaseEstateToBasic", name = "引用案列数据", method = {RequestMethod.GET})
    public HttpResult quoteCaseEstateToBasic(Integer id, Integer tableId) {
        try {
            return HttpResult.newCorrectResult(caseEstateService.quoteCaseEstateToBasic(id, tableId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


}
