package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateTagging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zch
 * @date: 2019/5/16 18:56
 * @description:
 */
public class BasicEstateTaggingVo  extends BasicEstateTagging implements Serializable {

    private List<BasicEstateTaggingGaoDe> gaoDeList = new ArrayList<>();

    public List<BasicEstateTaggingGaoDe> getGaoDeList() {
        return gaoDeList;
    }

    public void setGaoDeList(List<BasicEstateTaggingGaoDe> gaoDeList) {
        this.gaoDeList = gaoDeList;
    }
}
