package com.copower.pmcc.assess.common.enums;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2019/1/15 14:30
 * @Description:报告模板字段
 */
public enum BaseReportFieldEnum {
    REPORTNUMBER("", "文号", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    PRINCIPAL("", "委托人", BaseReportFieldReplaceEnum.TEXT.getKey()),
    Location("", "区位", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    powerPerson("", "权利人", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),//(区位)
    notPowerPerson("", "非权利人", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),//(区位)
    ValueType("", "价值类型", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    DefinitionValue("", "价值定义", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    ValueImplication("", "价值含义", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    AssessArea("", "评估面积", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    UseRightType("", "使用权类型", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    LandPracticalUse("", "土地实际用途", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    StatementPurposeEntrustment("", "委托目的表述", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    SetUse("", "设定用途", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    HouseType("", "房产类型", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    judgeObjectAreaStatusSheet("", "估价对象区位状况表", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    judgeObjectLandStateSheet("", "估价土地实体状况表", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    judgeBuildLandStateSheet("", "估价建筑物实体状况表", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    inventoryRight("", "土地他项权利情况", BaseReportFieldReplaceEnum.BOOKMARK.getKey()),
    EvaluationMethod("", "评估方法", BaseReportFieldReplaceEnum.BOOKMARK.getKey());
    private String key;

    private String name;
    private String describe;

    private BaseReportFieldEnum(String key, String name, String describe) {
        this.name = name;
        this.key = key;
        this.describe = describe;
    }

    public static BaseReportFieldEnum getEnumByName(String id) {
        for (BaseReportFieldEnum e : BaseReportFieldEnum.values()) {
            if (e.getKey().equals(id)) {
                return e;
            }
        }
        return null;
    }

    public static List<KeyValueDto> getBaseReportFieldEnumList() {
        List<KeyValueDto> keyValueDtos = new ArrayList<>();
        for (BaseReportFieldEnum e : BaseReportFieldEnum.values()) {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(String.valueOf(e.getKey()));
            keyValueDto.setValue(e.getName());
            keyValueDtos.add(keyValueDto);
        }
        return keyValueDtos;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
