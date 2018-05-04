package com.copower.pmcc.assess.controller;

import com.copower.pmcc.bpm.api.provider.BpmRpcBoxRoleUserService;
import com.copower.pmcc.bpm.api.provider.BpmRpcProcessInsManagerService;
import com.copower.pmcc.erp.api.dto.DepartmentTree;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/23
 * @time: 10:16
 */
@Controller
@RequestMapping(value = "/RpcBpmService", name = "BPM接口类")
public class RpcBpmController {
    @Autowired
    private BpmRpcBoxRoleUserService bpmRpcBoxRoleUserService;
    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Autowired
    private BpmRpcProcessInsManagerService bpmRpcProcessInsManagerService;

    @RequestMapping(value = "/getDepartmentRoleTree", name = "取得角色树", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult getDepartmentRoleTree() {
        DepartmentTree departmentRoleTree = bpmRpcBoxRoleUserService.getDepartmentRoleTree();
        return HttpResult.newCorrectResult(departmentRoleTree);
    }

    @RequestMapping(value = "/getDepartmentTree", name = "取得部门树", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult getDepartmentTree(Integer orgId) {
        DepartmentTree departmentTree = erpRpcDepartmentService.getDepartmentTree(orgId);
        return HttpResult.newCorrectResult(departmentTree);
    }

    @ResponseBody
    @RequestMapping(value = "/getApprovalLogByProject", method = RequestMethod.GET)
    public BootstrapTableVo getApprovalLogByProject(Integer projectId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        return bpmRpcProcessInsManagerService.getApprovalLogByProject(projectId, requestBaseParam.getLimit(), requestBaseParam.getOffset(), requestBaseParam.getSearch());
    }
}
