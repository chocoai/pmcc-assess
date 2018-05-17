package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.DataReportAnalysis;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataReportAnalysisService;
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

import java.util.List;


@RequestMapping(value = "/reportAnalysis")
@Controller
public class DataReportAnalysisController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ControllerComponent controllerComponent;

    @Autowired
    private BaseDataDicService baseDataDicService;

    @Autowired
    private DataReportAnalysisService dataReportAnalysisService;

    /**
     * 初始化页面
     * @return 视图页面
     */
    @RequestMapping(value = "/Index", name = "报告分析初始页面 ")
    public ModelAndView index() {
        //获取报告分析类别名称
        List<BaseDataDic> categoryList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.REPORT_ANALYSIS_CATEGORY);
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/data/dataReportAnalysis");
        modelAndView.addObject("categoryList",categoryList);
        return modelAndView;
    }

    /**
     * 根据类别id获取子项
     * @return 数据列表
     */
    @ResponseBody
    @RequestMapping(value = "/getFieldByPid", name = "根据类别id获取子项")
    public HttpResult getFieldByPid(String pid){
        try {
            List<BaseDataDic> categoryFieldList = baseDataDicService.getCacheDataDicListByPid(Integer.parseInt(pid));
            return HttpResult.newCorrectResult(categoryFieldList);
        } catch (Exception e) {
            logger.error("获取数据字典异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }

    }
    @ResponseBody
    @RequestMapping(value = "/save", name = "新增一条报告分析", method = RequestMethod.POST)
    public HttpResult save(DataReportAnalysis dataReportAnalysis){
        try {
            dataReportAnalysisService.addDataReportAnalysis(dataReportAnalysis);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 查询报告信息列表
     * @param keyWord 查询条件
     * @return BootstrapTableVo 表格对象
     */
    @ResponseBody
    @RequestMapping(value = "/getList", name = "查询报告信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getReportAnalysisForList(String keyWord) {
        try {
            return dataReportAnalysisService.getList(keyWord);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 删除一条报告分析
     * @param id 编号
     * @return 请求结果消息
     */
    @ResponseBody
    @RequestMapping(value = "/deleteReportAnalysis", name = "删除一条报告分析", method = RequestMethod.POST)
    public HttpResult deleteReportAnalysis(Integer id) {
        try {
            dataReportAnalysisService.deleteReportAnalysis(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 获取一条报告分析
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get", name = "获取",method = {RequestMethod.GET})
    public Object get(Integer id) {
        DataReportAnalysis dataReportAnalysis = null;
        try {
            dataReportAnalysis = dataReportAnalysisService.getReportAnalysis(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return dataReportAnalysis;
    }









}
