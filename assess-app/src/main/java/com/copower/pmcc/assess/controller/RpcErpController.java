package com.copower.pmcc.assess.controller;

import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysDepartmentDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.SysWorkLogDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcDepartmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/29
 * @time: 11:50
 */
@Controller
@RequestMapping(value = "/RpcErpService", name = "ERP接口")
public class RpcErpController {

    @Autowired
    private ErpRpcDepartmentService erpRpcDepartmentService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/getUserInfoByDepartmentId", method = RequestMethod.GET)
    public BootstrapTableVo getUserInfoByDepartmentId(Integer departmentId) {

        List<Integer> departmentIdListByPid = erpRpcDepartmentService.getDepartmentChildenIds(departmentId);
        departmentIdListByPid.add(departmentId);
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo userInfoByDepartmentId = erpRpcUserService.getUserInfoByDepartmentId(departmentIdListByPid, requestBaseParam.getOffset(), requestBaseParam.getLimit(), requestBaseParam
                .getSearch());
        return userInfoByDepartmentId;
    }

    @ResponseBody
    @RequestMapping(value = "/getOrgUserByUserAccounts", method = RequestMethod.GET)
    public BootstrapTableVo getOrgUserByUserAccounts(String userList) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        if (StringUtils.isBlank(userList)) {
            return bootstrapTableVo;
        }
        List<SysUserDto> orgUserListByUserAccounts = null;
        List<String> list = FormatUtils.transformString2List(userList);
        HashSet<String> hashSet= Sets.newHashSet();
        if(CollectionUtils.isNotEmpty(list)){
            for (String s : list) {
                hashSet.add(s);
            }
        }
        orgUserListByUserAccounts = erpRpcUserService.getSysUserList(Lists.newArrayList(hashSet));
        bootstrapTableVo.setTotal(new Long(orgUserListByUserAccounts.size()));
        bootstrapTableVo.setRows(orgUserListByUserAccounts);
        return bootstrapTableVo;
    }

    @ResponseBody
    @RequestMapping(value = "/getDepartmentByUserAccount", method = RequestMethod.GET)
    public HttpResult getDepartmentByUserAccount(String userAccount) {
        SysUserDto sysUser = erpRpcUserService.getSysUser(userAccount);
        if (sysUser != null) {
            SysDepartmentDto departmentDto = erpRpcDepartmentService.getDepartmentById(sysUser.getDepartmentId());
            return HttpResult.newCorrectResult(departmentDto);
        }
        return HttpResult.newCorrectResult();
    }

}
