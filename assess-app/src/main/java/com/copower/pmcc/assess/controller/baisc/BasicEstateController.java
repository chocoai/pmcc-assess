package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicAlternativeCaseDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicAlternativeCase;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandState;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVo;
import com.copower.pmcc.assess.service.basic.BasicEstateLandStateService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:56
 * @Description:楼盘
 */
@RequestMapping(value = "/basicEstate")
@Controller
public class BasicEstateController {
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicAlternativeCaseDao basicAlternativeCaseDao;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicEstateById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateById(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.getBasicEstateById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicEstate", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicEstate(BasicEstate basicEstate) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.saveAndUpdateBasicEstate(basicEstate, true));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicEstate", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicEstate(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.deleteBasicEstate(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicEstate basicEstate) {
        try {
            return basicEstateService.getBootstrapTableVo(basicEstate);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicEstateList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicEstateList(BasicEstate basicEstate) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.basicEstateList(basicEstate));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getBasicEstateByApplyId", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateByApplyId(Integer applyId) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.getBasicEstateMapByApplyId(applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getDataFromProject", name = "引用项目中的数据", method = {RequestMethod.GET})
    public HttpResult getDataFromProject(Integer applyId) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.getBasicEstateMapFromProject(applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/quoteEstateData", name = "引用项目中的数据批量时", method = {RequestMethod.GET})
    public HttpResult quoteEstateData(Integer id, Integer tableId) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.quoteEstateData(id, tableId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/quoteFromAlternative", name = "引用项目中的数据批量时", method = {RequestMethod.GET})
    public HttpResult quoteFromAlternative(Integer id, Integer tableId) {
        try {
            BasicAlternativeCase alternativeCase = basicAlternativeCaseDao.getBasicAlternativeCaseById(id);
            BasicApplyBatchDetail batchDetail = basicApplyBatchDetailDao.getInfoById(alternativeCase.getBusinessId());
            return HttpResult.newCorrectResult(basicEstateService.quoteEstateData(batchDetail.getTableId(), tableId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addEstateAndLandstate", name = "添加楼盘及土地基本信息", method = {RequestMethod.POST})
    public HttpResult addEstateAndLandstate(String estateName, String province, String city) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.addEstateAndLandstate(estateName, province, city));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult("添加楼盘及土地基本信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/appWriteEstate", name = "过程数据转移", method = {RequestMethod.POST})
    public HttpResult appWriteEstate(Integer caseEstateId, String estatePartInMode, Integer applyId) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.appWriteEstate(caseEstateId, estatePartInMode, applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicEstateMapById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateMapById(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicEstateService.getBasicEstateMapById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/detailView", name = "转到详情页面 ", method = RequestMethod.GET)
    public ModelAndView detailView(Integer id) throws Exception {
        String view = "project/stageSurvey/house/detail/estate";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        BasicEstateVo basicEstateVo = publicBasicService.getBasicEstateById(id);
        modelAndView.addObject(StringUtils.uncapitalize(BasicEstate.class.getSimpleName()), basicEstateVo);
        BasicEstateLandStateVo landStateVo = basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateService.getLandStateByEstateId(basicEstateVo.getId()));
        modelAndView.addObject(StringUtils.uncapitalize(BasicEstateLandState.class.getSimpleName()), landStateVo);
        if (basicEstateVo.getType() != null) {
            modelAndView.addObject("formType", BasicApplyTypeEnum.getEnumById(basicEstateVo.getType()).getKey());
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseEstateVos", method = {RequestMethod.GET}, name = "获取案例 楼盘列表")
    public BootstrapTableVo getCaseEstateVos(String name, String province, String city, String district) {
        BasicEstate basicEstate = new BasicEstate();
        if (!StringUtils.isEmpty(name)) {
            basicEstate.setName(name);
        }
        if (!StringUtils.isEmpty(province)) {
            basicEstate.setProvince(province);
        }
        if (!StringUtils.isEmpty(city)) {
            basicEstate.setCity(city);
        }
        if (!StringUtils.isEmpty(district)) {
            basicEstate.setDistrict(district);
        }
        basicEstate.setBisCase(true);
        return basicEstateService.getBootstrapTableVo(basicEstate);
    }
}
