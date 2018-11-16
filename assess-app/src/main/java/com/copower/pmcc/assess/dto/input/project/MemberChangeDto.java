package com.copower.pmcc.assess.dto.input.project;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2018/09/06 10:35
 */
public class MemberChangeDto {

    private String originalManagerAccount; //原始项目经理账户

    private String originalManagerName; //名称

    private String originalMemberAccount; //原始成员账户

    private String originalMemberName;

    private String newManagerAccount; //新的项目经理账户

    private String newManagerName; //名称

    private String newMemberAccount; //新的成员账户

    private String newMemberName;

    public String getOriginalManagerAccount() {
        return originalManagerAccount;
    }

    public void setOriginalManagerAccount(String originalManagerAccount) {
        this.originalManagerAccount = originalManagerAccount;
    }

    public String getOriginalManagerName() {
        return originalManagerName;
    }

    public void setOriginalManagerName(String originalManagerName) {
        this.originalManagerName = originalManagerName;
    }

    public String getOriginalMemberAccount() {
        return originalMemberAccount;
    }

    public void setOriginalMemberAccount(String originalMemberAccount) {
        this.originalMemberAccount = originalMemberAccount;
    }

    public String getOriginalMemberName() {
        return originalMemberName;
    }

    public void setOriginalMemberName(String originalMemberName) {
        this.originalMemberName = originalMemberName;
    }

    public String getNewManagerAccount() {
        return newManagerAccount;
    }

    public void setNewManagerAccount(String newManagerAccount) {
        this.newManagerAccount = newManagerAccount;
    }

    public String getNewManagerName() {
        return newManagerName;
    }

    public void setNewManagerName(String newManagerName) {
        this.newManagerName = newManagerName;
    }

    public String getNewMemberAccount() {
        return newMemberAccount;
    }

    public void setNewMemberAccount(String newMemberAccount) {
        this.newMemberAccount = newMemberAccount;
    }

    public String getNewMemberName() {
        return newMemberName;
    }

    public void setNewMemberName(String newMemberName) {
        this.newMemberName = newMemberName;
    }
}
