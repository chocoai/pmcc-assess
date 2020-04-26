package com.copower.pmcc.assess.proxy.face;

import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;

/**
 * Created by wangpc on 2020/2/14.
 */
public interface BasicFormStructureInterface {
    public BasicApplyBatch initBasicApplyBatch(BasicApplyBatch basicApplyBatch) throws Exception;//根据表单大类初始化结构
}
