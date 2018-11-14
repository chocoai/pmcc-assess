package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitHuxing;
import com.copower.pmcc.assess.dto.output.cases.CaseUnitHuxingVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseUnitHuxingService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 16:43
 * @Description:
 */
@RequestMapping(value = "/caseUnitHuxing")
@Controller
public class CaseUnitHuxingController {
    @Autowired
    private CaseUnitHuxingService caseUnitHuxingService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/getCaseUnitHuxingById", method = {RequestMethod.GET}, name = "获取户型")
    public HttpResult getById(Integer id) {
        CaseUnitHuxing caseUnitHuxing = null;
        try {
            if (id != null) {
                caseUnitHuxing = caseUnitHuxingService.getCaseUnitHuxingById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseUnitHuxingService.getCaseUnitHuxingVo(caseUnitHuxing));
    }

    @ResponseBody
    @RequestMapping(value = "/examineunithuxingSelect", method = {RequestMethod.GET}, name = "户型列表")
    public HttpResult examineunithuxingSelect(Integer unitId) {
        List<CaseUnitHuxingVo> caseUnitHuxings = Lists.newArrayList();
        try {
            CaseUnitHuxing caseUnitHuxing = new CaseUnitHuxing();
            if (!ObjectUtils.isEmpty(unitId)) {
                caseUnitHuxing.setUnitId(unitId);
            }
            caseUnitHuxings = caseUnitHuxingService.getCaseUnitHuxingList(caseUnitHuxing);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return HttpResult.newCorrectResult(caseUnitHuxings);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseUnitHuxingList", method = {RequestMethod.GET}, name = "户型列表")
    public BootstrapTableVo getCaseUnitHuxingList(Integer unitId) {
        BootstrapTableVo vo = null;
        try {
            CaseUnitHuxing caseUnitHuxing = new CaseUnitHuxing();
            if (!ObjectUtils.isEmpty(unitId)) {
                caseUnitHuxing.setUnitId(unitId);
            }
            vo = caseUnitHuxingService.getCaseUnitHuxingLists(caseUnitHuxing);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseUnitHuxingById", method = {RequestMethod.POST}, name = "删除户型")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(caseUnitHuxingService.deleteCaseUnitHuxing(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseUnitHuxing", method = {RequestMethod.POST}, name = "更新户型")
    public HttpResult save(CaseUnitHuxing caseUnitHuxing) {
        try {
            if (caseUnitHuxing.getId() == null || caseUnitHuxing.getId().equals(0)) {
                caseUnitHuxingService.addCaseUnitHuxing(caseUnitHuxing);
            } else {
                caseUnitHuxingService.updateCaseUnitHuxing(caseUnitHuxing);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


}
