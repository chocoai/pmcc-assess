package com.copower.pmcc.assess.dal.basic.custom;

import org.apache.ibatis.annotations.Param;


public interface CustomDataCopyMapper {
    //数据拷贝
    void copy(@Param("tableName") String tableName,
              @Param("fileName") String fileName,
              @Param("source") Object source,
              @Param("target") Object target);
}
