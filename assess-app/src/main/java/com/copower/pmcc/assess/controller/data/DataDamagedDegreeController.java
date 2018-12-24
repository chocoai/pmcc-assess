package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.basis.entity.DataDamagedDegree;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.service.data.DataDamagedDegreeService;
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
@RequestMapping("/dataDamagedDegree")
public class DataDamagedDegreeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataDamagedDegreeController.class);
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataDamagedDegreeService dataDamagedDegreeService;

    //region 基础数据完整度配置页面视图

    /**
     * 基础数据完整度配置页面视图
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/dataDamagedDegree");
        return modelAndView;
    }
    //endregion

    //region 获取完整度列表数据

    /**
     * 获取完整度列表数据
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/getDamagedDegreeList")
    public BootstrapTableVo getDamagedDegreeList(String fieldName, String name) {
        return dataDamagedDegreeService.getDamagedDegreeList(fieldName, name);
    }

    /**
     * 获取完整度列表数据
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/getDamagedDegreeListByPid")
    public BootstrapTableVo getDamagedDegreeListByPid(Integer pid) {
        return dataDamagedDegreeService.getDamagedDegreeListByPid(pid);
    }
    //endregion

    /**
     * 获取完整度列表数据
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/getCacheDamagedDegreeListByPid")
    public HttpResult getCacheDamagedDegreeListByPid(Integer pid) {
        try {
            List<DataDamagedDegree> sysDamagedDegrees = dataDamagedDegreeService.getCacheDamagedDegreeListByPid(pid);
            return HttpResult.newCorrectResult(sysDamagedDegrees);
        } catch (Exception e) {
            LOGGER.error("获取数据完整度异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
    //endregion

    /**
     * 获取完整度列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDamagedDegreeListByFieldName")
    public HttpResult getDamagedDegreeListByFieldName(String fieldName) {
        try {
            List<DataDamagedDegree> sysDamagedDegrees = dataDamagedDegreeService.getCacheDamagedDegreeList(fieldName);
            return HttpResult.newCorrectResult(sysDamagedDegrees);
        } catch (Exception e) {
            LOGGER.error("获取数据完整度异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    //region 保存完整度数据

    /**
     * 保存完整度数据
     *
     * @param sysDamagedDegree
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveDamagedDegree", method = RequestMethod.POST)
    public HttpResult saveDamagedDegree(DataDamagedDegree sysDamagedDegree) {
        try {
            dataDamagedDegreeService.saveDamagedDegree(sysDamagedDegree);
        } catch (Exception e) {
            LOGGER.error("保存数据完整度异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
    //endregion

    /**
     * 获取完整度列表by pid
     *
     * @param fieldName
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getListByFieldName", method = RequestMethod.GET)
    public HttpResult getListByFieldName(String fieldName) {
        List<DataDamagedDegree> DamagedDegreeList = dataDamagedDegreeService.getCacheDamagedDegreeList(fieldName);
        return HttpResult.newCorrectResult(DamagedDegreeList);
    }

    /**
     * 获取完整度详细信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDamagedDegreeInfo", method = RequestMethod.GET)
    public HttpResult getDamagedDegreeInfo(Integer id) {
        DataDamagedDegree DamagedDegreeInfo = dataDamagedDegreeService.getDamagedDegreeById(id);
        if (DamagedDegreeInfo == null)
            DamagedDegreeInfo = new DataDamagedDegree();
        return HttpResult.newCorrectResult(DamagedDegreeInfo);
    }

    /**
     * 删除完整度数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delDamagedDegree", method = RequestMethod.POST)
    public HttpResult delDamagedDegree(Integer id) {
        try {
            dataDamagedDegreeService.delDamagedDegree(id);
        } catch (Exception e) {
            LOGGER.error("删除数据完整度异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 删除完整度数据
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDamagedDegreeLevel", method = RequestMethod.POST)
    public HttpResult getDamagedDegreeLevel(Integer id) {
        try {
            return HttpResult.newCorrectResult(dataDamagedDegreeService.getDamagedDegreeLevel(id));
        } catch (Exception e) {
            LOGGER.error("获取完整度层级异常", e);
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
    @RequestMapping(value = "/queryDamagedDegreeTree", method = RequestMethod.POST)
    public List<ZtreeDto> queryDamagedDegreeTree(String name) {
        return dataDamagedDegreeService.queryDamagedDegreeTree(name);
    }

    /**
     * 获取数据信息树
     *
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDamagedDegreeTree", method = RequestMethod.POST)
    public List<ZtreeDto> getDamagedDegreeTree(Integer pid) {
        return dataDamagedDegreeService.getDamagedDegreeTree(pid);
    }

    /**
     * 获取数据信息树
     *
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getDamagedDegreeByKey", method = RequestMethod.POST)
    public List<ZtreeDto> getDamagedDegreeByKey(String key) {
        return dataDamagedDegreeService.getDamagedDegreeByKey(key);
    }

}
