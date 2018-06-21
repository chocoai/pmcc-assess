package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.dal.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */
@Controller
@RequestMapping("/baseProjectClassify")
public class BaseProjectClassifyController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    //region 基础数据项目分类配置页面视图

    /**
     * 基础数据项目分类配置页面视图
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/projectClassify");
        return modelAndView;
    }
    //endregion

    //region 获取项目分类列表数据

    /**
     * 获取项目分类列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getProjectClassifyList")
    public BootstrapTableVo getProjectClassifyList(String fieldName, String name) {
        return baseProjectClassifyService.getProjectClassifyList(fieldName, name);
    }

    /**
     * 获取项目分类列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getProjectClassifyListByPid")
    public BootstrapTableVo getProjectClassifyListByPid(Integer pid) {
        return baseProjectClassifyService.getProjectClassifyListByPid(pid);
    }
    //endregion

    /**
     * 获取项目分类列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCacheProjectClassifyListByPid")
    public HttpResult getCacheProjectClassifyListByPid(Integer pid) {
        try {
            List<BaseProjectClassify> sysProjectClassifys = baseProjectClassifyService.getCacheProjectClassifyListByPid(pid);
            return HttpResult.newCorrectResult(sysProjectClassifys);
        } catch (Exception e) {
            LOGGER.error("获取数据项目分类异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
    //endregion

    /**
     * 获取项目分类列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getProjectClassifyListByFieldName")
    public HttpResult getProjectClassifyListByFieldName(String fieldName) {
        try {
            List<BaseProjectClassify> sysProjectClassifys = baseProjectClassifyService.getCacheProjectClassifyListByKey(fieldName);
            return HttpResult.newCorrectResult(sysProjectClassifys);
        } catch (Exception e) {
            LOGGER.error("获取数据项目分类异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    //region 保存项目分类数据

    /**
     * 保存项目分类数据
     *
     * @param sysProjectClassify
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveProjectClassify", method = RequestMethod.POST)
    public HttpResult saveProjectClassify(BaseProjectClassify sysProjectClassify) {
        try {
            baseProjectClassifyService.saveProjectClassify(sysProjectClassify);
        } catch (Exception e) {
            LOGGER.error("保存数据项目分类异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
    //endregion

    /**
     * 获取项目分类列表by pid
     *
     * @param fieldName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getListByFieldName", method = RequestMethod.GET)
    public HttpResult getListByFieldName(String fieldName) {
        List<BaseProjectClassify> dataDicList = baseProjectClassifyService.getCacheProjectClassifyListByKey(fieldName);
        return HttpResult.newCorrectResult(dataDicList);
    }

    /**
     * 获取项目分类详细信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getProjectClassifyInfo", method = RequestMethod.GET)
    public HttpResult getProjectClassifyInfo(Integer id) {
        BaseProjectClassify dataDicInfo = baseProjectClassifyService.getProjectClassifyById(id);
        if (dataDicInfo == null)
            dataDicInfo = new BaseProjectClassify();
        return HttpResult.newCorrectResult(dataDicInfo);
    }

    /**
     * 删除项目分类数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delProjectClassify", method = RequestMethod.POST)
    public HttpResult delProjectClassify(Integer id) {
        try {
            baseProjectClassifyService.delProjectClassify(id);
        } catch (Exception e) {
            LOGGER.error("删除数据项目分类异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 删除项目分类数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getProjectClassifyLevel", method = RequestMethod.POST)
    public HttpResult getProjectClassifyLevel(Integer id) {
        try {
            return HttpResult.newCorrectResult(baseProjectClassifyService.getProjectClassifyLevel(id));
        } catch (Exception e) {
            LOGGER.error("获取项目分类层级异常", e);
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
    @RequestMapping(value = "/queryProjectClassifyTree", method = RequestMethod.POST)
    public List<ZtreeDto> queryProjectClassifyTree(String name, Integer pid, String key, String filterKey) {
        return baseProjectClassifyService.queryProjectClassifyTree(name, pid, key, filterKey);
    }

    /**
     * 获取数据信息树
     *
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getProjectClassifyTreeByPid", method = RequestMethod.POST)
    public List<ZtreeDto> getProjectClassifyTreeByPid(Integer pid, String filterKey) {
        return baseProjectClassifyService.getProjectClassifyTreeByPid(pid, filterKey);
    }

    /**
     * 获取数据信息树
     *
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getProjectClassifyTreeByKey", method = RequestMethod.POST)
    public List<ZtreeDto> getProjectClassifyTreeByKey(String key, String filterKey) {
        return baseProjectClassifyService.getProjectClassifyTreeByKey(key, filterKey);
    }


}
