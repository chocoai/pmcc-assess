package com.copower.pmcc.assess.controller.project.declare;

import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kings on 2018-5-8.
 */
@Controller
@RequestMapping("/declare")
public class DeclareController {
    @Autowired
    private BaseDataDicService baseDataDicService;

    @ResponseBody
    @RequestMapping(value = "/getDeclareTypeTree", name = "取得申报类型树", method = RequestMethod.GET)
    public HttpResult getQualificationTreeByKey(String key) {
        return HttpResult.newCorrectResult(baseDataDicService.getDataDicTree(key));
    }
}
