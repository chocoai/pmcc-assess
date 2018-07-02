package com.copower.pmcc.assess.controller.project.declare;

import com.copower.pmcc.assess.dto.output.base.BaseFormModuleVo;
import com.copower.pmcc.assess.service.project.declare.DeclareInfoService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kings on 2018-5-8.
 */
@Controller
@RequestMapping("/declare")
public class DeclareController {
    @Autowired
    private DeclareInfoService declareInfoService;

    @ResponseBody
    @RequestMapping(value = "/addDeclareUserClassify", name = "添加使用的项目分类", method = RequestMethod.POST)
    public HttpResult addDeclareUserClassify(Integer projectId,Integer planDetailsId,Integer projectClassifyId) {
        declareInfoService.addDeclareUserClassify(projectId,planDetailsId,projectClassifyId);
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delDeclareUserClassify", name = "删除使用的项目分类", method = RequestMethod.POST)
    public HttpResult delDeclareUserClassify(Integer projectId,Integer planDetailsId,Integer projectClassifyId) {
        try {
            declareInfoService.delDeclareUserClassify(projectId,planDetailsId,projectClassifyId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBaseFormModuleVos", name = "获取使用到的申报表数据", method = RequestMethod.POST)
    public HttpResult getBaseFormModuleVos(Integer projectId,Integer planDetailsId) {
        List<BaseFormModuleVo> baseFormModuleVos = declareInfoService.getBaseFormModuleVos(projectId, planDetailsId);
        return HttpResult.newCorrectResult(baseFormModuleVos);
    }
}
