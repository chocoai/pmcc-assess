package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.hr.api.provider.HrRpcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/31
 * @time: 13:35
 */
@Component
public class ApprovalUserService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private HrRpcUserService hrRpcUserService;
    @Autowired
    private ProjectMemberService projectMemberService;

    public String getRoleUserAccountList(String roleName, String appointUserAccoount, Integer projectId) {
        String users = "";
        try {
            Method method = this.getClass().getMethod(roleName, String.class, Integer.class);
            users = (String) method.invoke(this, appointUserAccoount, projectId);
        } catch (Exception e) {
        }
        return users;
    }

    //申请人
    public String apply_user(String CurruserAccount, Integer projectId) {
        return CurruserAccount;
    }

    //申请人直接上级
    public String apply_user_superior(String CurruserAccount, Integer projectId) {
        return hrRpcUserService.getUserInfoSuperior(CurruserAccount);
    }

    //申请人所有部门部门领导
    public String apply_user_department(String CurruserAccount, Integer projectId) {
        return hrRpcUserService.getDepartmentLeader(CurruserAccount);
    }

    //操作人
    public String opation_user(String CurruserAccount, Integer projectId) {
        return apply_user(commonService.thisUserAccount(),projectId);
    }

    //操作人所有部门领导
    public String opation_user_department(String CurruserAccount, Integer projectId) {
        return apply_user_department(commonService.thisUserAccount(),projectId);
    }

    //操作人直接上级
    public String opation_user_superior(String CurruserAccount, Integer projectId) {
        return apply_user_superior(commonService.thisUserAccount(),projectId);
    }

    public String project_manager(String CurruserAccount, Integer projectId) {
       return projectMemberService.getProjectManagerMember(projectId);
    }
}
