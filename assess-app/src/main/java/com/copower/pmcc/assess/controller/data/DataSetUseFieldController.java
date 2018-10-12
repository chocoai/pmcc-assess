package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataSetUseField;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */
@Controller
@RequestMapping("/setUseField")
public class DataSetUseFieldController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSetUseFieldController.class);
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataSetUseFieldService dataSetUseFieldService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    //region 基础数据字典配置页面视图

    /**
     * 基础数据字典配置页面视图
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataSetUseField");
        return modelAndView;
    }
    //endregion

    //region 获取字典列表数据

    /**
     * 获取字典列表数据
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/getSetUseFieldList")
    public BootstrapTableVo getSetUseFieldList(String name) {
        return dataSetUseFieldService.getSetUseFieldList(name);
    }

    /**
     * 获取字典列表数据
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/getSetUseFieldListByPid")
    public BootstrapTableVo getSetUseFieldListByPid(Integer pid) {
        return dataSetUseFieldService.getSetUseFieldListByPid(pid);
    }
    //endregion

    /**
     * 获取字典列表数据
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/getCacheSetUseFieldListByPid")
    public HttpResult getCacheSetUseFieldListByPid(Integer pid) {
        try {
            List<DataSetUseField> sysSetUseFields = dataSetUseFieldService.getCacheSetUseFieldListByPid(pid);
            return HttpResult.newCorrectResult(sysSetUseFields);
        } catch (Exception e) {
            LOGGER.error("获取数据字典异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
    //endregion

    /**
     * 获取字典列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getSetUseFieldListByFieldName")
    public HttpResult getSetUseFieldListByFieldName(String fieldName) {
        try {
            List<DataSetUseField> sysSetUseFields = dataSetUseFieldService.getCacheSetUseFieldList(fieldName);
            return HttpResult.newCorrectResult(sysSetUseFields);
        } catch (Exception e) {
            LOGGER.error("获取数据字典异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    //region 保存字典数据

    /**
     * 保存字典数据
     *
     * @param sysSetUseField
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveSetUseField", method = RequestMethod.POST)
    public HttpResult saveSetUseField(DataSetUseField sysSetUseField) {
        try {
            dataSetUseFieldService.saveSetUseField(sysSetUseField);
        } catch (Exception e) {
            LOGGER.error("保存数据字典异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
    //endregion

    /**
     * 获取字典列表by pid
     *
     * @param fieldName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getListByFieldName", method = RequestMethod.GET)
    public HttpResult getListByFieldName(String fieldName) {
        List<DataSetUseField> dataDicList = dataSetUseFieldService.getCacheSetUseFieldList(fieldName);
        return HttpResult.newCorrectResult(dataDicList);
    }

    /**
     * 获取字典详细信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSetUseFieldInfo", method = RequestMethod.GET)
    public HttpResult getSetUseFieldInfo(Integer id) {
        DataSetUseField dataDicInfo = dataSetUseFieldService.getSetUseFieldById(id);
        if (dataDicInfo == null)
            dataDicInfo = new DataSetUseField();
        return HttpResult.newCorrectResult(dataDicInfo);
    }

    /**
     * 删除字典数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delSetUseField", method = RequestMethod.POST)
    public HttpResult delSetUseField(Integer id) {
        try {
            dataSetUseFieldService.delSetUseField(id);
        } catch (Exception e) {
            LOGGER.error("删除数据字典异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 删除字典数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSetUseFieldLevel", method = RequestMethod.POST)
    public HttpResult getSetUseFieldLevel(Integer id) {
        try {
            return HttpResult.newCorrectResult(dataSetUseFieldService.getSetUseFieldLevel(id));
        } catch (Exception e) {
            LOGGER.error("获取字典层级异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
