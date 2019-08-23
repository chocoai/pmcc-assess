package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataDispatchRegister;
import com.copower.pmcc.assess.dal.basis.entity.DataDispatchRegisterExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataDispatchRegisterMapper;
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
public class DataDispatchRegisterDao {
    @Autowired
    private DataDispatchRegisterMapper dataDispatchRegisterMapper;

    public Integer addDataDispatchRegister(DataDispatchRegister dataDispatchRegister) {
        try {
            dataDispatchRegisterMapper.insertSelective(dataDispatchRegister);
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        return dataDispatchRegister.getId();
    }

    public DataDispatchRegister getDataDispatchRegisterById(Integer id) {
        if (id == null) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        return dataDispatchRegisterMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataDispatchRegister(DataDispatchRegister dataDispatchRegister) {
        try {
            return dataDispatchRegisterMapper.updateByPrimaryKeySelective(dataDispatchRegister) == 1;
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        return false;
    }

    public void removeDataDispatchRegister(DataDispatchRegister dataDispatchRegister) {
        DataDispatchRegisterExample example = new DataDispatchRegisterExample();
        MybatisUtils.convertObj2Example(dataDispatchRegister, example);
        try {
            dataDispatchRegisterMapper.deleteByExample(example);
        } catch (Exception e1) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
    }

    public List<DataDispatchRegister> getDataDispatchRegisterList(DataDispatchRegister dataDispatchRegister) {
        DataDispatchRegisterExample example = new DataDispatchRegisterExample();
        if (dataDispatchRegister == null) {
            try {
                throw new SQLException("exception");
            } catch (SQLException e) {
            }
        }
        MybatisUtils.convertObj2Example(dataDispatchRegister, example);
        return dataDispatchRegisterMapper.selectByExample(example);
    }

}
