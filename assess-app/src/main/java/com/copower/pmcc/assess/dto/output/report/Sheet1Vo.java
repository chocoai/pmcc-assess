package com.copower.pmcc.assess.dto.output.report;

import com.copower.pmcc.assess.dal.entity.Sheet1;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/7
 * @time: 15:24
 */
public class Sheet1Vo extends Sheet1 {
    private String attachmentHtml;

    public String getAttachmentHtml() {
        return attachmentHtml;
    }

    public void setAttachmentHtml(String attachmentHtml) {
        this.attachmentHtml = attachmentHtml;
    }
}
