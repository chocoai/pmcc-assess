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
        dataDispatchRegisterMapper.insertSelective(dataDispatchRegister);
        return dataDispatchRegister.getId();
    }

    public DataDispatchRegister getDataDispatchRegisterById(Integer id) {
        return dataDispatchRegisterMapper.selectByPrimaryKey(id);
    }

    public boolean updateDataDispatchRegister(DataDispatchRegister dataDispatchRegister) {
        return dataDispatchRegisterMapper.updateByPrimaryKeySelective(dataDispatchRegister) == 1;
    }

    public void removeDataDispatchRegister(DataDispatchRegister dataDispatchRegister) {
        DataDispatchRegisterExample example = new DataDispatchRegisterExample();
        MybatisUtils.convertObj2Example(dataDispatchRegister, example);
        dataDispatchRegisterMapper.deleteByExample(example);
    }

    public List<DataDispatchRegister> getDataDispatchRegisterList(DataDispatchRegister dataDispatchRegister) {
        DataDispatchRegisterExample example = new DataDispatchRegisterExample();
        MybatisUtils.convertObj2Example(dataDispatchRegister, example);
        return dataDispatchRegisterMapper.selectByExample(example);
    }

}
