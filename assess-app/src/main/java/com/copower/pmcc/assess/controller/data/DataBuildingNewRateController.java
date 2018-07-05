package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataBuildingNewRate;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @RequestMapping(value = "/getArchitectureList", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    BootstrapTableVo list(@RequestParam(value = "buildingStructureA") String buildingStructureA) {
        BootstrapTableVo vo = dataBuildingNewRateService.getDataBuildingNewRateList(buildingStructureA);
        return vo;
    }


    /**
     * remove
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/removeDataBuildingNewRate", method = RequestMethod.POST)
    public @ResponseBody
    HttpResult removeDataBuildingNewRate(@RequestParam(value = "id") Integer id) {
        try {
            dataBuildingNewRateService.deleteDataBuildingNewRate(id);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/addDataBuildingNewRate", method = RequestMethod.POST)
    public @ResponseBody
    HttpResult addDataBuildingNewRate(DataBuildingNewRate dataBuildingNewRate) {
        try {
            if (dataBuildingNewRate.getId() != null && dataBuildingNewRate.getId() != 0) {//不再使用专门的 update controller
                dataBuildingNewRateService.editDataBuildingNewRate(dataBuildingNewRate);
            } else {
                dataBuildingNewRateService.addDataBuildingNewRate(dataBuildingNewRate);
            }
        } catch (BusinessException e) {
            logger.info("" + e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * update
     *
     * @param dataBuildingNewRate
     * @return
     */
    @RequestMapping(value = "/updateDataBuildingNewRate", method = RequestMethod.POST)
    public @ResponseBody
    HttpResult updateDataBuildingNewRate(@RequestParam(value = "dataBuildingNewRate") DataBuildingNewRate dataBuildingNewRate) {
        try {
            dataBuildingNewRateService.editDataBuildingNewRate(dataBuildingNewRate);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * get
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getDataBuildingNewRate", method = {RequestMethod.POST, RequestMethod.GET})
    public @ResponseBody
    Object getDataBuildingNewRate(@RequestParam(value = "id") Integer id) {
        DataBuildingNewRate dataBuildingNewRate = null;
        if (id != null) {
            dataBuildingNewRate = dataBuildingNewRateService.getByiDdataBuildingNewRate(id);
        }
        return dataBuildingNewRateService.getDataBuildingNewRateVo(dataBuildingNewRate);
    }
}
