package com.copower.pmcc.assess.dto.output.document;

import com.copower.pmcc.assess.dal.basis.entity.DocumentSend;

/**
 * Created by 13426 on 2018/4/27.
 */
public class DocumentSendVo extends DocumentSend {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
