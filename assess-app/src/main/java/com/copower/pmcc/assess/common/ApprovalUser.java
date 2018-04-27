package com.copower.pmcc.assess.common;

import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.hr.api.provider.HrRpcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/24
 * @time: 14:27
 */
@Component
public class ApprovalUser {
    @Autowired
    private CommonService commonService;
    @Autowired
    private HrRpcUserService hrRpcUserService;

    public String getRoleUserAccountList(String roleName, String appointUserAccoount) {
        String users = "";
        try {
            Method method = this.getClass().getMethod(roleName, String.class);
            users = (String) method.invoke(this, appointUserAccoount);
        } catch (Exception e) {
        }
        return users;
    }

    //申请人
    public String apply_user(String CurruserAccount) {
        return CurruserAccount;
    }

    //申请人直接上级
    public String apply_user_superior(String CurruserAccount) {
        return hrRpcUserService.getUserInfoSuperior(CurruserAccount);
    }

    //申请人所有部门部门领导
    public String apply_user_department(String CurruserAccount) {
        return hrRpcUserService.getDepartmentLeader(CurruserAccount);
    }
    //申请人所有部门分管领导
    public String apply_user_department_leader(String CurruserAccount) {
        return hrRpcUserService.getDepartmentFirstLeader(CurruserAccount);
    }

    //操作人
    public String opation_user(String CurruserAccount) {
        return apply_user(commonService.thisUserAccount());
    }

    //操作人所有部门领导
    public String opation_user_department(String CurruserAccount) {
        return apply_user_department(commonService.thisUserAccount());
    }

    //所在部门分管领导
    public String opation_user_department_leader(String CurruserAccount) {
        return apply_user_department_leader(commonService.thisUserAccount());
    }
    //操作人直接上级
    public String opation_user_superior(String CurruserAccount) {
        return apply_user_superior(commonService.thisUserAccount());
    }

    public String project_manager(String CurruserAccount) {
        return CurruserAccount;//合同不存在项目经理，所以返回本身
    }

}
