package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import com.mysql.jdbc.Statement;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-3-21.
 */
@Repository
public class FormConfigureDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 根据数据库名称获取数据库所有表数据
     *
     * @param databaseName
     * @return
     */
    public List<KeyValueDto> getDbTableList(String databaseName) {
        String sql = "select TABLE_NAME,TABLE_COMMENT from information_schema.tables where table_schema=?";
        List<KeyValueDto> keyValueDtos = jdbcTemplate.query(sql, new Object[]{databaseName}, (rs, num) -> {
            KeyValueDto dto = new KeyValueDto();
            dto.setKey(rs.getNString("TABLE_NAME"));
            dto.setValue(rs.getNString("TABLE_COMMENT"));
            return dto;
        });
        return keyValueDtos;
    }

    /**
     * 跟进数据库名称获取数据库所有表数据
     *
     * @param tableName
     * @return
     */
    public List<KeyValueDto> getTableFieldList(String databaseName, String tableName) {
        String sql = "select COLUMN_NAME,COLUMN_COMMENT,DATA_TYPE from INFORMATION_SCHEMA.Columns where table_name=? and table_schema=?";
        List<KeyValueDto> keyValueDtos = jdbcTemplate.query(sql, new Object[]{tableName, databaseName}, (rs, num) -> {
            KeyValueDto dto = new KeyValueDto();
            dto.setKey(rs.getNString("COLUMN_NAME"));
            dto.setValue(rs.getNString("COLUMN_COMMENT"));
            dto.setExplain(rs.getNString("DATA_TYPE"));
            return dto;
        });
        return keyValueDtos;
    }

    /**
     * 获取单条数据对象
     *
     * @param tableName
     * @param tableId
     * @return
     */
    public Map<String, Object> getObjectSingle(String tableName, Integer tableId) {
        String sql = String.format("select * from %s where id=?", tableName);
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, new Object[]{tableId});
        return map;
    }

    public Map<String, Object> getObjectSingle(String sql, Object[] args) {
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, args);
        return map;
    }

    public Map<String, Object> getObjectSingle(String tableName, String processInsId) {
        String sql = String.format("select * from %s where process_ins_id=?", tableName);
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, new Object[]{processInsId});
        return map;
    }

    public String getText(String sql) {
        return jdbcTemplate.queryForObject(sql, new Object[]{}, String.class);
    }

    /**
     * 新增数据
     *
     * @param tableName
     * @param map
     * @return
     */
    public Integer addObject(String tableName, Map<String, Object> map) {
        if (map == null || map.size() <= 0) return null;
        removeInvalidField(tableName, map);
        StringBuilder keyString = new StringBuilder();
        StringBuilder valueString = new StringBuilder();
        List<Object> objects = Lists.newArrayList();
        for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
            keyString.append(stringObjectEntry.getKey()).append(",");
            valueString.append("?,");
            objects.add(stringObjectEntry.getValue());
        }
        String sql = String.format("insert into %s(%s) values(%s)", tableName,
                StringUtils.stripEnd(keyString.toString(), ","), StringUtils.stripEnd(valueString.toString(), ","));

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
                                @Override
                                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                    for (int i = 1; i <= objects.size(); i++) {
                                        Object o = objects.get(i - 1);
                                        ps.setObject(i, o);
                                    }
                                    return ps;
                                }
                            },
                keyHolder);

        return keyHolder.getKey().intValue();

    }

    /**
     * 更新数据
     *
     * @param sql
     * @return
     */
    public Boolean updateObject(String sql) throws BusinessException {
        if (StringUtils.isBlank(sql))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        int i = jdbcTemplate.update(sql, new Object[]{});
        return i > 0;
    }

    /**
     * 删除数据
     *
     * @param tableName
     * @param tableId
     * @return
     */
    public Boolean deleteObject(String tableName, Integer tableId) {
        String sql = String.format("delete from %s where id=?", tableName);
        int i = jdbcTemplate.update(sql, new Object[]{tableId});
        return i > 0;
    }

    /**
     * 分页查询数据
     *
     * @param sql
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<Map<String, Object>> getObjectList(String sql, Integer pageIndex, Integer pageSize) {
        String querySql = String.format("%s limit %s,%s", sql, (pageIndex - 1) * pageSize, pageSize);
        return jdbcTemplate.queryForList(querySql);
    }

    public List<Map<String, Object>> getObjectList(String sql) {
        return jdbcTemplate.queryForList(sql);
    }

    /**
     * 获取数据总条数
     *
     * @param sql
     * @return
     */
    public Long getObjectCount(String sql) {
        Long aLong = jdbcTemplate.queryForObject(String.format("select count(*) from (%s) as T", sql), new Object[]{}, Long.class);
        return aLong == null ? 0 : aLong;
    }

    public List<KeyValueDto> getDdlDataSource(String sql) {
        return jdbcTemplate.query(sql, (rs, number) -> {
            KeyValueDto keyValueDto = new KeyValueDto();
            keyValueDto.setKey(rs.getString("id"));
            keyValueDto.setValue(rs.getString("text"));
            return keyValueDto;
        });
    }

    /**
     * 去除无效字段
     *
     * @param tableName
     * @param map
     */
    public void removeInvalidField(String tableName, Map<String, Object> map) {
        List<KeyValueDto> tableFieldList = getTableFieldList(BaseConstant.CURRENT_DATABASE_NAME, tableName);
        if (CollectionUtils.isNotEmpty(tableFieldList)) {
            //去除多余字段
            List<String> list = LangUtils.transform(tableFieldList, p -> p.getKey());
            List<String> removeKeys = Lists.newArrayList();
            for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
                if (!list.contains(stringObjectEntry.getKey()))
                    removeKeys.add(stringObjectEntry.getKey());
            }
            if (CollectionUtils.isNotEmpty(removeKeys)) {
                for (String removeKey : removeKeys) {
                    map.remove(removeKey);
                }
            }
            //无效值处理
            for (KeyValueDto keyValueDto : tableFieldList) {
                if (map.containsKey(keyValueDto.getKey())) {
                    switch (keyValueDto.getExplain()) {
                        case "int":
                        case "decimal":
                        case "bit":
                        case "timestamp":
                        case "date":
                        case "time":
                        case "datetime":
                            if (StringUtils.isBlank(map.get(keyValueDto.getKey())+"")) {
                                map.put(keyValueDto.getKey(), null);
                            }
                            break;
                    }
                }
            }
        }
    }
}
