package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.dal.basis.dao.base.DatabaseJdbcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by kings on 2018-3-21.
 */
@Service
public class DatabaseJdbcService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DatabaseJdbcDao databaseJdbcDao;

    /**
     * 获取单条数据
     *
     * @param tableName
     * @param tableId
     * @return
     */
    public Map<String, Object> getObjectSingle(String tableName, Integer tableId) {
        return databaseJdbcDao.getObjectSingle(tableName, tableId);
    }

    public Map<String, Object> getObjectSingle(String sql, Object[] args) {
        return databaseJdbcDao.getObjectSingle(sql, args);
    }

    public Map<String, Object> getObjectSingle(String tableName, String processInsId) {
        return databaseJdbcDao.getObjectSingle(tableName, processInsId);
    }



}
