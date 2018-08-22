package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataBuildingNewRate;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 功能描述: 建筑成新率修改
 *
 * @param:
 * @return:
 * @auther: zch
 * @date: 2018/8/22 15:53
 */

@RequestMapping(value = "/architecture")
@Controller
public class DataBuildingNewRateController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataBuildingNewRateService dataBuildingNewRateService;

    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;


    @RequestMapping(value = "/Index")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataArchitectureDic");
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.BUILDING_NEW_RATE_USE);
        modelAndView.addObject("useList", baseDataDics);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getArchitectureList", method = {RequestMethod.GET})
    public BootstrapTableVo list(String buildingStructure) {
        DataBuildingNewRate dataBuildingNewRate = new DataBuildingNewRate();
        if (!StringUtils.isEmpty(buildingStructure)) {
            dataBuildingNewRate.setBuildingStructure(buildingStructure);
        }
        BootstrapTableVo vo = dataBuildingNewRateService.getDataBuildingNewRateList(dataBuildingNewRate);
        return vo;
    }


    /**
     * remove
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/removeDataBuildingNewRate", method = RequestMethod.POST)
    public HttpResult removeDataBuildingNewRate(Integer id) {
        try {
            dataBuildingNewRateService.deleteDataBuildingNewRate(id);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/addAndUpdateNewRate", method = RequestMethod.POST)
    public HttpResult addAndUpdateNewRate(DataBuildingNewRate dataBuildingNewRate) {
        try {
            if (dataBuildingNewRate.getId() != null && !Objects.equal(dataBuildingNewRate.getId(), 0)) {//不再使用专门的 update controller
                dataBuildingNewRateService.editDataBuildingNewRate(dataBuildingNewRate);
            } else {
                dataBuildingNewRateService.addDataBuildingNewRate(dataBuildingNewRate);
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }


    @ResponseBody
    @RequestMapping(value = "/getByDataBuildingNewRateId", method = RequestMethod.GET)
    public HttpResult getByDataBuildingNewRateId(Integer id) {
        DataBuildingNewRate dataBuildingNewRate = null;
        try {
            dataBuildingNewRate = dataBuildingNewRateService.getByiDdataBuildingNewRate(id);
        } catch (Exception e1) {
            return HttpResult.newErrorResult(e1.getMessage());
        }
        return HttpResult.newCorrectResult(dataBuildingNewRate);
    }
}
