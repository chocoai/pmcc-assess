package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.DataBlock;
import com.copower.pmcc.assess.job.AssessmentBonusJob;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.NetInfoRecordService;
import com.copower.pmcc.assess.service.NetUrlConfigService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.MessageTypeEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.message.model.MessageResponse;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/9/11 10:08
 * @Description:基础版块维护
 */
@RequestMapping(value = "/dataBlock")
@Controller
public class DataBlockController extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private NetUrlConfigService netUrlConfigService;
    @Autowired
    private AssessmentBonusJob assessmentBonusJob;


    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataBlockView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());//所有省份

        //assessmentBonusJob.launchAssessmentBonusTask();
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataBlockById", method = {RequestMethod.GET}, name = "获取基础版块维护")
    public HttpResult getDataBlockById(Integer id) {
        DataBlock dataBlock = null;
        try {
            if (id != null) {
                dataBlock = dataBlockService.getDataBlockById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataBlock);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataBlockList", method = {RequestMethod.GET}, name = "获取基础版块维护列表")
    public BootstrapTableVo getDataBlockList(String province, String city, String district, String name) {
        DataBlock dataBlock = new DataBlock();
        BootstrapTableVo vo = null;
        try {
            vo = dataBlockService.getDataBlockListVos(province, city, district, name);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataBlockById", method = {RequestMethod.POST}, name = "删除基础版块维护")
    public HttpResult deleteDataBlockById(Integer id) {
        try {
            if (id != null) {
                DataBlock dataBlock = new DataBlock();
                dataBlock.setId(id);
                dataBlockService.removeDataBlock(dataBlock);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataBlock", method = {RequestMethod.POST}, name = "更新基础版块维护")
    public HttpResult saveAndUpdateDataBlock(DataBlock dataBlock) {
        try {
            dataBlockService.saveAndUpdateDataBlock(dataBlock);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getDataBlockListByArea", method = {RequestMethod.GET}, name = "获取版块信息by区域")
    public HttpResult getDataBlockListByArea(String province, String city, String district) {
        try {
            return HttpResult.newCorrectResult(dataBlockService.getDataBlockListByArea(province, city, district));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("获取版块信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/dataBlockList", method = {RequestMethod.GET}, name = "获取版块信息 list")
    public HttpResult dataBlockList(String province, String city, String district) {
        try {
            DataBlock dataBlock = new DataBlock();
            if (org.apache.commons.lang.StringUtils.isNotBlank(province)) {
                dataBlock.setProvince(province);
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(city)) {
                dataBlock.setCity(city);
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(district)) {
                dataBlock.setDistrict(district);
            }
            return HttpResult.newCorrectResult(dataBlockService.dataBlockVos(dataBlock));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("获取版块信息 list exception");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/isExistBlock", method = {RequestMethod.GET}, name = "验证版块是否已存在")
    public HttpResult isExistBlock(String province, String city, String district, String name) {
        try {
            return HttpResult.newCorrectResult(dataBlockService.isExistBlock(province, city, district, name));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("获取版块信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateOldData", method = {RequestMethod.GET}, name = "更新数据")
    public HttpResult updateOldData(Integer key) {
        try {
            dataBlockService.updateOldData(key);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("更新数据异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/climbingOldData", method = {RequestMethod.GET}, name = "抓取两年前老数据")
    public HttpResult climbingOldData(Integer id) {
        try {
            netUrlConfigService.climbingAll();
            netUrlConfigService.getBaZhongTradingCenter(1);
            netUrlConfigService.getLuZhouTradingCenter(1);
            netUrlConfigService.getZiGongTradingCenter(1);
            netUrlConfigService.getYiBinTradingCenter(1,"land");
            netUrlConfigService.getYiBinTradingCenter(1,"asset");
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("抓取两年前老数据异常");
        }
    }

}
