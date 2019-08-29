package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceRecordExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeSurePriceRecordMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 09:55
 * @Description:
 */
@Repository
public class SchemeSurePriceRecordDao {
    @Autowired
    private SchemeSurePriceRecordMapper schemeSurePriceRecordMapper;

    public Integer addSchemeSurePriceRecord(SchemeSurePriceRecord schemeSurePriceRecord) {
        try {
            schemeSurePriceRecordMapper.insertSelective(schemeSurePriceRecord);
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        return schemeSurePriceRecord.getId();
    }

    public SchemeSurePriceRecord getSchemeSurePriceRecordById(Integer id) {
        if (id == null) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        return schemeSurePriceRecordMapper.selectByPrimaryKey(id);
    }

    public boolean updateSchemeSurePriceRecord(SchemeSurePriceRecord schemeSurePriceRecord) {
        try {
            return schemeSurePriceRecordMapper.updateByPrimaryKeySelective(schemeSurePriceRecord) == 1;
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        return false;
    }

    public void removeSchemeSurePriceRecord(SchemeSurePriceRecord schemeSurePriceRecord) {
        SchemeSurePriceRecordExample example = new SchemeSurePriceRecordExample();
        MybatisUtils.convertObj2Example(schemeSurePriceRecord, example);
        try {
            schemeSurePriceRecordMapper.deleteByExample(example);
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
    }

    public List<SchemeSurePriceRecord> getSchemeSurePriceRecordList(SchemeSurePriceRecord schemeSurePriceRecord) {
        SchemeSurePriceRecordExample example = new SchemeSurePriceRecordExample();
        if (schemeSurePriceRecord == null) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        MybatisUtils.convertObj2Example(schemeSurePriceRecord, example);
        return schemeSurePriceRecordMapper.selectByExample(example);
    }

}
