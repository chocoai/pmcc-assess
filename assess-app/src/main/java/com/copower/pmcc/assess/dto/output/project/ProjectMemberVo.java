package com.copower.pmcc.assess.dto.output.project;


import com.copower.pmcc.assess.dal.basis.entity.ProjectMember;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/9/29
 * @time: 17:31
 */
public class ProjectMemberVo extends ProjectMember {


    private String userAccountManagerName;

    private String userAccountMemberName;

    private String userAccountQualityName;

    public String getUserAccountManagerName() {
        return userAccountManagerName;
    }

    public void setUserAccountManagerName(String userAccountManagerName) {
        this.userAccountManagerName = userAccountManagerName;
    }

    public String getUserAccountMemberName() {
        return userAccountMemberName;
    }

    public void setUserAccountMemberName(String userAccountMemberName) {
        this.userAccountMemberName = userAccountMemberName;
    }

    public String getUserAccountQualityName() {
        return userAccountQualityName;
    }

    public void setUserAccountQualityName(String userAccountQualityName) {
        this.userAccountQualityName = userAccountQualityName;
    }
}
