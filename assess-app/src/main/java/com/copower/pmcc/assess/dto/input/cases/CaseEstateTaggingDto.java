package com.copower.pmcc.assess.dto.input.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateTagging;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: zch
 * @Date: 2018/12/10 10:15
 * @Description:
 */
public class CaseEstateTaggingDto extends CaseEstateTagging {
    private Set<CaseEstateTaggingDto> children = new HashSet<CaseEstateTaggingDto>();
    private String state = "open";
    private boolean checked = false;
    private String huxingImg;

    public Set<CaseEstateTaggingDto> getChildren() {
        return children;
    }

    public void setChildren(Set<CaseEstateTaggingDto> children) {
        this.children = children;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getHuxingImg() {
        return huxingImg;
    }

    public void setHuxingImg(String huxingImg) {
        this.huxingImg = huxingImg;
    }
}
