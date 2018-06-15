package com.copower.pmcc.assess.common.enums;

public enum CsrBorrowerEnum {
//    CSR_BORROWER_ENUM("/template/temp/"),ZIP_NAME("债权评估标准化模板");
    CSR_BORROWER_ENUM("/template/"),ZIP_NAME("债权评估标准化模板");
    private String filePath;

    CsrBorrowerEnum(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}
