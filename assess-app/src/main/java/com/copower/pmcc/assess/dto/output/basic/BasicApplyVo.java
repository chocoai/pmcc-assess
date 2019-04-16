package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicApply;

/**
 * @Auther: zch
 * @Date: 2018/11/15 11:29
 * @Description:
 */
public class BasicApplyVo extends BasicApply {
    private String fullName;
    private String typeName;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
