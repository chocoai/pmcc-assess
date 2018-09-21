package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataBlock;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBlockService;
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
 * @Date: 2018/9/11 10:08
 * @Description:基础版块维护
 */
@RequestMapping(value = "/dataBlock")
@Controller
public class DataBlockController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private ErpAreaService erpAreaService;

    @RequestMapping(value = "/view", name = "转到index页面 ",method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataBlockView" ;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("ProvinceList", erpAreaService.getProvinceList());//所有省份
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataBlockById",method = {RequestMethod.GET},name = "获取基础版块维护")
    public HttpResult getDataBlockById(Integer id) {
        DataBlock dataBlock = null;
        try {
            if (id!=null){
                dataBlock = dataBlockService.getDataBlockById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataBlock);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataBlockList",method = {RequestMethod.GET},name = "获取基础版块维护列表")
    public BootstrapTableVo getDataBlockList(String name) {
        DataBlock dataBlock = new DataBlock();
        BootstrapTableVo vo = null;
        try {
            if (!StringUtils.isEmpty(name)){
                dataBlock.setName(name);
            }
            vo = dataBlockService.getDataBlockListVos(dataBlock);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataBlockById",method = {RequestMethod.POST},name = "删除基础版块维护")
    public HttpResult deleteDataBlockById(Integer id) {
        try {
            if (id!=null){
                DataBlock dataBlock = new DataBlock();
                dataBlock.setId(id);
                dataBlockService.removeDataBlock(dataBlock);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataBlock",method = {RequestMethod.POST},name = "更新基础版块维护")
    public HttpResult saveAndUpdateDataBlock(DataBlock dataBlock){
        try {
            dataBlockService.saveAndUpdateDataBlock(dataBlock);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getDataBlockListByArea",method = {RequestMethod.GET},name = "获取版块信息by区域")
    public HttpResult getDataBlockListByArea(String province,String city,String district){
        try {
            return HttpResult.newCorrectResult(dataBlockService.getDataBlockListByArea(province,city,district));
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("获取版块信息异常");
        }
    }
}
