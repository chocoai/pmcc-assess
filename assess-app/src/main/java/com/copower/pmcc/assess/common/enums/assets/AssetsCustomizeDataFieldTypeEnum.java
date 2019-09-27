package com.copower.pmcc.assess.common.enums.assets;

/**
 * Created by zch on 2019-9-23.
 */
public enum AssetsCustomizeDataFieldTypeEnum {
    CustomizeDataField("自定义名称和附件",0),
    FixedDataField("固定名称",1),
    FixedDataFieldAndFile("固定名称和附件",2),
    OtherTypeData("其它类型数据",100),
    ;
    private int fileType;
    private String remark;

    AssetsCustomizeDataFieldTypeEnum(String remark, int fileType) {
        this.remark = remark;
        this.fileType = fileType;
    }

    public String getRemark() {
        return remark;
    }

    public int getFileType() {
        return fileType;
    }
}
