package com.copower.pmcc.assess.dal.basis.custom.entity;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 13426 on 2018/5/15.
 */
public class DeclareRecordRowMapper implements RowMapper<DeclareRecord> {
    @Override
    public DeclareRecord mapRow(ResultSet rs, int i) throws SQLException {
        DeclareRecord declareRecord = new DeclareRecord();
        declareRecord.setId(rs.getInt("id"));
//        declareRecord.setProjectId(rs.getInt("projectId"));
        declareRecord.setProjectId(rs.getInt("project_id"));
        declareRecord.setName(rs.getString("name"));
        declareRecord.setProvince(rs.getString("province"));
        declareRecord.setCity(rs.getString("city"));
        declareRecord.setDistrict(rs.getString("district"));
        declareRecord.setFloorArea(rs.getBigDecimal("floor_area"));
        declareRecord.setCreator(rs.getString("creator"));
        declareRecord.setGmtCreated(rs.getDate("gmt_created"));
        return declareRecord;//
    }
}
