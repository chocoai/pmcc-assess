package com.copower.pmcc.assess.dto.output.project.csr;

import com.copower.pmcc.assess.dal.basis.entity.CsrBorrower;

public class CsrBorrowerVo extends CsrBorrower {
    private String attachmentHtml;

    public String getAttachmentHtml() {
        return attachmentHtml;
    }

    public void setAttachmentHtml(String attachmentHtml) {
        this.attachmentHtml = attachmentHtml;
    }
}
