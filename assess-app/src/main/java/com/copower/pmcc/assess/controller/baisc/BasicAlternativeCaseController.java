package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.BasicAlternativeCase;
import com.copower.pmcc.assess.dto.input.BasicAlternativeCaseDto;
import com.copower.pmcc.assess.service.basic.BasicAlternativeCaseService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping(value = "/basicAlternativeCase")
@Controller
public class BasicAlternativeCaseController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BasicAlternativeCaseService basicAlternativeCaseService;


    @ResponseBody
    @RequestMapping(value = "/getBasicAlternativeCaseList", name = "取得备选案列信息", method = RequestMethod.GET)
    public BootstrapTableVo getBasicAlternativeCaseList(String name, String tbType) {
        BootstrapTableVo vo = basicAlternativeCaseService.getBasicAlternativeCaseList(name, tbType);
        return vo;
    }


    @ResponseBody
    @RequestMapping(value = "/addToAlternative", method = {RequestMethod.POST}, name = "添加")
    public HttpResult addToAlternative(String formData) {
        BasicAlternativeCase basicAlternativeCase = JSON.parseObject(formData, BasicAlternativeCase.class);
        try {
            basicAlternativeCaseService.saveAndUpdateBasicAlternativeCase(basicAlternativeCase);
            return HttpResult.newCorrectResult("添加成功");
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            return HttpResult.newErrorResult("添加异常");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/infoDetail", method = {RequestMethod.POST})
    public HttpResult infoDetail(Integer id) {
        try {
            BasicAlternativeCaseDto dto = basicAlternativeCaseService.getBasicAlternativeCaseDto(id);
            return HttpResult.newCorrectResult(dto);
        } catch (Exception e) {
            return HttpResult.newErrorResult("操作异常");
        }
    }

}
