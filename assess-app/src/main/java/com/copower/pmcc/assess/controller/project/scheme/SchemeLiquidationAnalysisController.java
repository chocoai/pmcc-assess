package com.copower.pmcc.assess.controller.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeLiquidationAnalysisItemDao;
import com.copower.pmcc.assess.service.project.scheme.SchemeLiquidationAnalysisService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kings on 2018-10-25.
 */
@Controller
@RequestMapping("/schemeLiquidationAnalysis")
public class SchemeLiquidationAnalysisController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SchemeLiquidationAnalysisService projectTaskLiquidationAnalysisService;
    @Autowired
    private SchemeLiquidationAnalysisItemDao schemeLiquidationAnalysisItemDao;


    @ResponseBody
    @RequestMapping(value = "/getAnalysisItemList", name = "获取税率明细", method = RequestMethod.POST)
    public HttpResult getAnalysisItemList(Integer planDetailsId) {
        return HttpResult.newCorrectResult(projectTaskLiquidationAnalysisService.getAnalysisItemList(planDetailsId));
    }

    @ResponseBody
    @RequestMapping(value = "/deleteItem", name = "删除一条明细", method = RequestMethod.POST)
    public HttpResult deleteCsrCalculation(Integer id) {
        try {
            schemeLiquidationAnalysisItemDao.deleteSchemeLiquidationAnalysisItem(id);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
