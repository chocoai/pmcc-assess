package com.copower.pmcc.assess.dto.input.project;

import com.copower.pmcc.assess.dal.entity.SchemeInfo;

/**
 * Created by 13426 on 2018/5/25.
 */
public class SchemeInfoFormDataDto {

   private String Content;
   private String DataID;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDataID() {
        return DataID;
    }

    public void setDataID(String dataID) {
        DataID = dataID;
    }
}
