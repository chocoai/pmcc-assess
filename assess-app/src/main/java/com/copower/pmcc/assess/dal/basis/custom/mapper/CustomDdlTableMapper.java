package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomDdlTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 描述:DDL
 *
 * @author: Calvin(qiudong@copowercpa.com)CustomDdlTableMapper
 * @data: 2017/8/17
 * @time: 10:56
 */
public interface CustomDdlTableMapper {

    void customTableDdl(@Param("ddlSql") String ddlSql);//直接用SQL对数据库中表进行新增或删除
    List<Map> customTableSelect(@Param("ddlSql") String ddlSql);//查询自定义表相关数据
    int customTableDdlInsert(CustomDdlTable customDdlTable);//查询自定义表相关数据
    List<Map> getFuniHousesTypeLpbh(@Param("ddlSql") String ddlSql);//查询tb_funi_houses_type表中的lpbh
}
