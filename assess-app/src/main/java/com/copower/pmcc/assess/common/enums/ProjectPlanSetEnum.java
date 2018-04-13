package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.enums.IEnum;
import com.copower.pmcc.erp.common.utils.EnumUtils;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/6
 * @time: 9:20
 */
public enum ProjectPlanSetEnum implements IEnum<Integer> {
    ALL(0, "全部设置"), NULL(1, "未设置");
    private Integer value;

    private String describe;

    ProjectPlanSetEnum(int value, String describe) {
        this.value = value;
        this.describe = describe;
    }

    public static ProjectPlanSetEnum create(int value) {
        return EnumUtils.getEnum(ProjectPlanSetEnum.values(), value);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
