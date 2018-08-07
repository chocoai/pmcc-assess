package com.copower.pmcc.assess.service.assist;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomDdlTable;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomDdlTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/12/28
 * @time: 15:21
 */
@Component
public class DdlMySqlAssist {
    @Autowired
    private CustomDdlTableMapper customDdlTableMapper;

    public void customTableDdl(String sql) {
        customDdlTableMapper.customTableDdl(sql);
    }
    public List<Map> customTableSelect(String sql) {
        List<Map> maps = customDdlTableMapper.customTableSelect(sql);
        return maps;
    }

    public List<Map> getFuniHousesTypeLpbh(String sql){
        List<Map> maps = customDdlTableMapper.getFuniHousesTypeLpbh(sql);
        return maps;
    }

    public Integer customTableDdlInsert(String sql) {
        CustomDdlTable customDdlTable =new CustomDdlTable();
        customDdlTable.setDdlSql(sql);
        customDdlTableMapper.customTableDdlInsert(customDdlTable);
        return customDdlTable.getId();
    }
}
