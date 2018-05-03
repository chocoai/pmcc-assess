package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.DataBestUseDescription;
import com.copower.pmcc.assess.dal.entity.EarlyWarning;
import com.copower.pmcc.assess.service.data.DataEarlyWarningService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
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

@RequestMapping(value = "/earlyWarning")
@Controller
public class DataEarlyWarningController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ControllerComponent controllerComponent;

    @Autowired
    private DataEarlyWarningService dataEarlyWarningService;

    /**
     * 初始化页面
     * @return 视图页面
     */
    @RequestMapping(value = "/Index", name = "预警设置初始页面 ")
    public ModelAndView index() {
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/data/dataEarlyWarning");
        return modelAndView;
    }

    /**
     * 新增or修改一条预警设置信息
     * @param earlyWarning 预警设置entity
     * @return 请求结果消息
     */
    @ResponseBody
    @RequestMapping(value = "/editEarlyWarning", name = "新增or修改预警设置信息", method = RequestMethod.POST)
    public HttpResult editEarlyWarning(EarlyWarning earlyWarning) {
        try {
            if (earlyWarning.getId() != null && earlyWarning.getId() != 0){
                //修改
                dataEarlyWarningService.editEarlyWarning(earlyWarning);
            }else{
                //新增
                dataEarlyWarningService.addEarlyWarning(earlyWarning);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 查询预警设置信息列表
     * @param keyWord 委托目的
     * @return Bootstrap表格对象
     */
    @ResponseBody
    @RequestMapping(value = "/getEarlyWarningForList", name = "获取预警设置信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getEarlyWarningForList(String keyWord) {
        try {
            return dataEarlyWarningService.getEarlyWarningForList(keyWord);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 删除一条预警设置信息
     * @param id 编号
     * @return 请求结果消息
     */
    @ResponseBody
    @RequestMapping(value = "/deleteEarlyWarning", name = "删除预警设置信息", method = RequestMethod.POST)
    public HttpResult deleteEarlyWarningForList(@RequestParam(value = "id") Integer id) {
        try {
            dataEarlyWarningService.deleteEarlyWarning(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
