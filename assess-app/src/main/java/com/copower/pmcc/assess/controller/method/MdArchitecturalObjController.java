package com.copower.pmcc.assess.controller.method;

import com.copower.pmcc.assess.constant.AssessMarketCostConstant;
import com.copower.pmcc.assess.dal.basis.entity.MdArchitecturalObj;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.method.MdArchitecturalObjService;
import com.copower.pmcc.assess.service.method.MdMarketCostService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zch on 2019/7/8.
 * 建筑安装工程费
 */
@RequestMapping(value = "/mdArchitecturalObj")
@RestController
public class MdArchitecturalObjController {
    @Autowired
    private MdArchitecturalObjService mdArchitecturalObjService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private MdMarketCostService mdMarketCostService;

    @Autowired
    private BaseDataDicService baseDataDicService;

    @PostMapping(value = "/saveMdArchitecturalObj")
    public HttpResult saveMdArchitecturalObj(String forData, String type, String databaseName, Integer pid, Integer planDetailsId) {
        try {
            MdArchitecturalObj mdArchitecturalObj = new MdArchitecturalObj();
            saveMdArchitecturalObj2(forData, type, databaseName, pid, planDetailsId, mdArchitecturalObj);
            return HttpResult.newCorrectResult(200, mdArchitecturalObj);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/removeMdArchitecturalObj")
    public HttpResult removeMdArchitecturalObj(String type, String databaseName, Integer pid, Integer planDetailsId) {
        try {
            mdArchitecturalObjService.removeMdArchitecturalObj(type, pid, databaseName, planDetailsId);
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getMdArchitecturalObjList")
    public HttpResult getMdArchitecturalObjList(MdArchitecturalObj mdArchitecturalObj) {
        try {
            List<MdArchitecturalObj> objs = mdArchitecturalObjService.getMdArchitecturalObjListByExample(mdArchitecturalObj);
            return HttpResult.newCorrectResult(200, objs);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e);
        }
    }

    @RequestMapping(value = "/getBaseDicTree", name = "获取", method = RequestMethod.GET)
    public BootstrapTableVo getBaseDicTree() {
        BootstrapTableVo vo = mdMarketCostService.getBaseDicTree();
        return vo;
    }

    @RequestMapping(value = "/getTreeView", name = "获取建安工程费树形结构", method = RequestMethod.GET)
    public HttpResult getTreeView() {
        return HttpResult.newCorrectResult(baseDataDicService.getTreeViewByKey(AssessMarketCostConstant.BUILD_SECURITY_ENGINEERING_PROJECT));
    }

    private void saveMdArchitecturalObj2(String forData, String type, String databaseName, Integer pid, Integer planDetailsId, MdArchitecturalObj mdArchitecturalObj) {
        mdArchitecturalObjService.removeMdArchitecturalObj(type, pid, databaseName, planDetailsId);
        mdArchitecturalObj.setDatabaseName(databaseName);
        mdArchitecturalObj.setPid(pid);
        mdArchitecturalObj.setType(type);
        mdArchitecturalObj.setJsonContent(forData);
        mdArchitecturalObj.setPlanDetailsId(planDetailsId);
        mdArchitecturalObjService.saveMdArchitecturalObj(mdArchitecturalObj);
    }

}
