package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.entity.DataReportAnalysisField;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataReportAnalysisFieldService;
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


@RequestMapping(value = "/reportAnalysisField")
@Controller
public class DataReportAnalysisFieldController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Autowired
    private BaseDataDicService baseDataDicService;

    @Autowired
    private DataReportAnalysisFieldService dataReportAnalysisFieldService;

    @ResponseBody
    @RequestMapping(value = "/addField", name = "新增一条报告分析子项", method = RequestMethod.POST)
    public HttpResult save(DataReportAnalysisField field){
        try {
            dataReportAnalysisFieldService.addField(field);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    /**
     * 查询报告信息子项列表
     * @param pid 父级id
     * @return BootstrapTableVo 表格对象
     */
    @ResponseBody
    @RequestMapping(value = "/getFieldList", name = "查询报告分析子项列表", method = RequestMethod.GET)
    public BootstrapTableVo getFieldList(Integer pid,String name) {
        try {
            return dataReportAnalysisFieldService.getFieldList(pid,name);
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
    @RequestMapping(value = "/deleteField", name = "删除一条报告分析子项", method = RequestMethod.POST)
    public HttpResult deleteField(Integer id) {
        try {
            dataReportAnalysisFieldService.deleteField(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }










}
