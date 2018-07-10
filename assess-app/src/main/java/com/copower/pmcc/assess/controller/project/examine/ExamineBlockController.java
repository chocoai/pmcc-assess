package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBlock;
import com.copower.pmcc.assess.service.project.examine.ExamineBlockService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kings on 2018-7-6.
 */
@Controller
@RequestMapping("/examineBlock")
public class ExamineBlockController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineBlockService examineBlockService;

    @ResponseBody
    @GetMapping(value = "/getBlockById", name = "获取版块")
    public HttpResult getBlockById(Integer id) {
        ExamineBlock examineBlock = examineBlockService.getBlockById(id);
        return HttpResult.newCorrectResult(examineBlock);
    }

    @ResponseBody
    @PostMapping(value = "/saveBlock", name = "保存版块")
    public HttpResult saveBlock(ExamineBlock examineBlock){
        try {
            return HttpResult.newCorrectResult(examineBlockService.saveBlock(examineBlock));
        } catch (BusinessException e) {
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}
