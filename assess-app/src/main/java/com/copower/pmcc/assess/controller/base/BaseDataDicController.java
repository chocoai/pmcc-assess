package com.copower.pmcc.assess.controller.base;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
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
@RequestMapping("/baseDataDic")
public class BaseDataDicController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDataDicController.class);
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;

    //region 基础数据字典配置页面视图

    /**
     * 基础数据字典配置页面视图
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/dataDic");
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
    @GetMapping("/getDataDicList")
    public BootstrapTableVo getDataDicList(String fieldName, String name) {
        return baseDataDicService.getDataDicList(fieldName, name);
    }

    /**
     * 获取字典列表数据
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/getDataDicListByPid")
    public BootstrapTableVo getDataDicListByPid(Integer pid) {
        return baseDataDicService.getDataDicListByPid(pid);
    }
    //endregion

    /**
     * 获取字典列表数据
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/getCacheDataDicListByPid")
    public HttpResult getCacheDataDicListByPid(Integer pid) {
        try {
            List<BaseDataDic> sysDataDics = baseDataDicService.getCacheDataDicListByPid(pid);
            return HttpResult.newCorrectResult(sysDataDics);
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
    @RequestMapping("/getDataDicListByFieldName")
    public HttpResult getDataDicListByFieldName(String fieldName) {
        try {
            List<BaseDataDic> sysDataDics = baseDataDicService.getCacheDataDicList(fieldName);
            return HttpResult.newCorrectResult(sysDataDics);
        } catch (Exception e) {
            LOGGER.error("获取数据字典异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    //region 保存字典数据

    /**
     * 保存字典数据
     *
     * @param sysDataDic
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveDataDic", method = RequestMethod.POST)
    public HttpResult saveDataDic(BaseDataDic sysDataDic) {
        try {
            baseDataDicService.saveDataDic(sysDataDic);
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
        List<BaseDataDic> dataDicList = baseDataDicService.getCacheDataDicList(fieldName);
        return HttpResult.newCorrectResult(dataDicList);
    }

    /**
     * 获取字典详细信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDataDicInfo", method = RequestMethod.GET)
    public HttpResult getDataDicInfo(Integer id) {
        BaseDataDic dataDicInfo = baseDataDicService.getDataDicById(id);
        if (dataDicInfo == null)
            dataDicInfo = new BaseDataDic();
        return HttpResult.newCorrectResult(dataDicInfo);
    }

    /**
     * 删除字典数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delDataDic", method = RequestMethod.POST)
    public HttpResult delDataDic(Integer id) {
        try {
            baseDataDicService.delDataDic(id);
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
    @RequestMapping(value = "/getDataDicLevel", method = RequestMethod.POST)
    public HttpResult getDataDicLevel(Integer id) {
        try {
            return HttpResult.newCorrectResult(baseDataDicService.getDataDicLevel(id));
        } catch (Exception e) {
            LOGGER.error("获取字典层级异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


    /**
     * 查询数据信息树
     *
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryBaseDicTree", method = RequestMethod.POST)
    public List<ZtreeDto> queryBaseDicTree(String name) {
        return baseDataDicService.queryBaseDicTree(name);
    }

    /**
     * 获取数据信息树
     *
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getBaseDicTree", method = RequestMethod.POST)
    public List<ZtreeDto> getBaseDicTree(Integer pid) {
        return baseDataDicService.getBaseDicTree(pid);
    }

    /**
     * 获取数据信息树
     *
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getBaseDicByKey", method = RequestMethod.POST)
    public List<ZtreeDto> getBaseDicByKey(String key) {
        return baseDataDicService.getBaseDicByKey(key);
    }



    /**
     * 保存keyValue
     *
     * @param formData
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveKeyValue", method = RequestMethod.POST)
    public HttpResult saveKeyValue(String formData) {
        try {
            BaseDataDic sysDataDic = JSON.parseObject(formData, BaseDataDic.class);
            baseDataDicService.saveDataDic(sysDataDic);
        } catch (Exception e) {
            LOGGER.error("保存数据字典异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }


    /**
     * 从现有集合中根据名称找出对应数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDataDicByName")
    public HttpResult getDataDicByName(String fieldName,String name) {
        try {
            List<BaseDataDic> sysDataDics = baseDataDicService.getCacheDataDicList(fieldName);
            BaseDataDic dataDic = baseDataDicService.getDataDicByName(sysDataDics, name);
            return HttpResult.newCorrectResult(dataDic);
        } catch (Exception e) {
            LOGGER.error("获取数据字典异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
