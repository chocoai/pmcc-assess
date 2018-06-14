package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dal.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dto.input.data.CaseComparisonDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.data.CaseComparisonFieldService;
import com.copower.pmcc.assess.service.data.CaseComparisonService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
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

/**
 * Created by 13426 on 2018/5/3.
 */
@RequestMapping(value = "/caseComparison",name = "案例对比配置")
@Controller
public class CaseComparisonController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Autowired
    private BaseDataDicService baseDataDicService;

    @Autowired
    private CaseComparisonService service;

    @Autowired
    private CaseComparisonFieldService fieldService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    @RequestMapping(value = "/view", name = "转到index页面")
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/caseComparisonView");
        modelAndView.addObject("typeMap",service.getTypeMap());
        modelAndView.addObject("userList",fieldService.getTableList());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", name = "显示列表", method = RequestMethod.GET)
    public BootstrapTableVo list(Integer exploreFormType) {
        BootstrapTableVo vo = service.getDataByExploreFormType(exploreFormType);
//        vo = service.listVos(methodStr);//关键字查询
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/get", name = "获取",method = {RequestMethod.GET})
    public Object get(@RequestParam(value = "id") Integer id) {
        CaseComparisonDto dto = null;
        try {
//            dto = service.get(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return dto;
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.GET}, name = "增加与修改")
    public HttpResult add(CaseComparisonDto dto) {
        try {
            if (dto.getId() != null && dto.getId() != 0) {//不再使用专门的 update controller
                service.update(dto);
            } else {
                service.add(dto);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除",method = RequestMethod.POST)
    public HttpResult delete(@RequestParam(value = "id") Integer id) {
        try {
            service.remove(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

}
