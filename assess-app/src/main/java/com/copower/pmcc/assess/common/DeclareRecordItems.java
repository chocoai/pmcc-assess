package com.copower.pmcc.assess.common;

import com.copower.pmcc.assess.dal.entity.DeclareRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13426 on 2018/5/15.
 */
public class DeclareRecordItems {
    private List<List<DeclareRecord>> items = new ArrayList<>();

    public List<List<DeclareRecord>> getItems() {
        return items;
    }

    public void setItems(List<List<DeclareRecord>> items) {
        this.items = items;
    }
}
