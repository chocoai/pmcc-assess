package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataBlock;
import com.copower.pmcc.assess.dal.basis.entity.DataBlockExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataBlockMapper;
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
public class DataBlockDao {
    @Autowired
    private DataBlockMapper dataBlockMapper;

    public Integer addDataBlock(DataBlock dataBlock) {
        try {
            dataBlockMapper.insertSelective(dataBlock);
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        return dataBlock.getId();
    }

    public DataBlock getDataBlockById(Integer id) {
        if (id == null) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        return dataBlockMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataBlock(DataBlock dataBlock) {
        try {
            return dataBlockMapper.updateByPrimaryKeySelective(dataBlock) == 1;
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        return false;
    }

    public void removeDataBlock(DataBlock dataBlock) {
        DataBlockExample example = new DataBlockExample();
        MybatisUtils.convertObj2Example(dataBlock, example);
        try {
            dataBlockMapper.deleteByExample(example);
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
    }

    public List<DataBlock> getDataBlockList(DataBlock dataBlock) {
        DataBlockExample example = new DataBlockExample();
        if (dataBlock == null) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        MybatisUtils.convertObj2Example(dataBlock, example);
        return dataBlockMapper.selectByExample(example);
    }

    public List<DataBlock> getDataBlockList(String name) {
        DataBlockExample example = new DataBlockExample();
        example.createCriteria().andNameLike(String.format("%%%s%%",name));
        return dataBlockMapper.selectByExample(example);
    }
}
