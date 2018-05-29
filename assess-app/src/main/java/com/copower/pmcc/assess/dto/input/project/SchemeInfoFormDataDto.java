package com.copower.pmcc.assess.dto.input.project;

import java.io.Serializable;

/**
 * Created by 13426 on 2018/5/25.
 */
public class SchemeInfoFormDataDto implements Serializable{

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

    @Override
    public String toString() {
        return "SchemeInfoFormDataDto{" +
                "Content='" + Content + '\'' +
                ", DataID='" + DataID + '\'' +
                '}';
    }
}
