package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataExamineTask;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.service.data.DataExamineTaskService;
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
@RequestMapping("/dataExamineTask")
public class DataExamineTaskController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataExamineTaskService dataExamineTaskService;

    //region 基础数据调查任务配置页面视图

    /**
     * 基础数据调查任务配置页面视图
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataExamineTask");
        return modelAndView;
    }
    //endregion

    //region 获取调查任务列表数据

    /**
     * 获取调查任务列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDataExamineTaskList")
    public BootstrapTableVo getDataExamineTaskList(String fieldName, String name) {
        return dataExamineTaskService.getDataExamineTaskList(fieldName, name);
    }

    /**
     * 获取调查任务列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDataExamineTaskListByPid")
    public BootstrapTableVo getDataExamineTaskListByPid(Integer pid) {
        return dataExamineTaskService.getDataExamineTaskListByPid(pid);
    }
    //endregion

    /**
     * 获取调查任务列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCacheDataExamineTaskListByPid")
    public HttpResult getCacheDataExamineTaskListByPid(Integer pid) {
        try {
            List<DataExamineTask> sysDataExamineTasks = dataExamineTaskService.getCacheDataExamineTaskListByPid(pid);
            return HttpResult.newCorrectResult(sysDataExamineTasks);
        } catch (Exception e) {
            LOGGER.error("获取数据调查任务异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
    //endregion

    /**
     * 获取调查任务列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDataExamineTaskListByFieldName")
    public HttpResult getDataExamineTaskListByFieldName(String fieldName) {
        try {
            List<DataExamineTask> sysDataExamineTasks = dataExamineTaskService.getCacheDataExamineTaskListByKey(fieldName);
            return HttpResult.newCorrectResult(sysDataExamineTasks);
        } catch (Exception e) {
            LOGGER.error("获取数据调查任务异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    //region 保存调查任务数据

    /**
     * 保存调查任务数据
     *
     * @param sysDataExamineTask
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveDataExamineTask", method = RequestMethod.POST)
    public HttpResult saveDataExamineTask(DataExamineTask sysDataExamineTask) {
        try {
            dataExamineTaskService.saveDataExamineTask(sysDataExamineTask);
        } catch (Exception e) {
            LOGGER.error("保存数据调查任务异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
    //endregion

    /**
     * 获取调查任务列表by pid
     *
     * @param fieldName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getListByFieldName", method = RequestMethod.GET)
    public HttpResult getListByFieldName(String fieldName) {
        List<DataExamineTask> dataDicList = dataExamineTaskService.getCacheDataExamineTaskListByKey(fieldName);
        return HttpResult.newCorrectResult(dataDicList);
    }

    /**
     * 获取调查任务详细信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDataExamineTaskInfo", method = RequestMethod.GET)
    public HttpResult getDataExamineTaskInfo(Integer id) {
        DataExamineTask dataDicInfo = dataExamineTaskService.getDataExamineTaskById(id);
        if (dataDicInfo == null)
            dataDicInfo = new DataExamineTask();
        return HttpResult.newCorrectResult(dataDicInfo);
    }

    /**
     * 删除调查任务数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delDataExamineTask", method = RequestMethod.POST)
    public HttpResult delDataExamineTask(Integer id) {
        try {
            dataExamineTaskService.delDataExamineTask(id);
        } catch (Exception e) {
            LOGGER.error("删除数据调查任务异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 删除调查任务数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDataExamineTaskLevel", method = RequestMethod.POST)
    public HttpResult getDataExamineTaskLevel(Integer id) {
        try {
            return HttpResult.newCorrectResult(dataExamineTaskService.getDataExamineTaskLevel(id));
        } catch (Exception e) {
            LOGGER.error("获取调查任务层级异常", e);
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
    @RequestMapping(value = "/queryDataExamineTaskTree", method = RequestMethod.POST)
    public List<ZtreeDto> queryDataExamineTaskTree(String name, Integer pid, String key, String filterKey) {
        return dataExamineTaskService.queryDataExamineTaskTree(name, pid, key, filterKey);
    }

    /**
     * 获取数据信息树
     *
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDataExamineTaskTreeByPid", method = RequestMethod.POST)
    public List<ZtreeDto> getDataExamineTaskTreeByPid(Integer pid, String filterKey) {
        return dataExamineTaskService.getDataExamineTaskTreeByPid(pid, filterKey);
    }

    /**
     * 获取数据信息树
     *
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDataExamineTaskTreeByKey", method = RequestMethod.POST)
    public List<ZtreeDto> getDataExamineTaskTreeByKey(String key, String filterKey) {
        return dataExamineTaskService.getDataExamineTaskTreeByKey(key, filterKey);
    }


}
