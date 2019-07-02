package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.DataCustomerField;
import com.copower.pmcc.assess.dal.basis.entity.DataCustomerFieldExample;
import com.copower.pmcc.assess.dal.basis.mapper.DataCustomerFieldMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/12
 * @time: 15:04
 */
@Repository
public class DataCustomerFieldDao {
    @Autowired
    private DataCustomerFieldMapper dataCustomerFieldMapper;

    public DataCustomerField getDataCustomerField(Integer id) {
        return dataCustomerFieldMapper.selectByPrimaryKey(id);
    }

    public List<DataCustomerField> getDataCustomerFieldList(String name) {
        DataCustomerFieldExample example = new DataCustomerFieldExample();
        DataCustomerFieldExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.andCustomerNameLike("%" + name + "%");
        }
        List<DataCustomerField> dataCustomerFields = dataCustomerFieldMapper.selectByExample(example);
        return dataCustomerFields;
    }

    public List<DataCustomerField> getObjectList(DataCustomerField dataCustomerField) {
        DataCustomerFieldExample example = new DataCustomerFieldExample();
        MybatisUtils.convertObj2Example(dataCustomerField, example);
        return dataCustomerFieldMapper.selectByExample(example);
    }

    public List<DataCustomerField> getObjectList(Integer customerId) {
        DataCustomerFieldExample example = new DataCustomerFieldExample();
        example.createCriteria().andCustomerIdEqualTo(customerId);
        return dataCustomerFieldMapper.selectByExample(example);
    }

    public boolean addDataCustomerField(DataCustomerField dataCustomerField) {
        int i = dataCustomerFieldMapper.insert(dataCustomerField);
        return i > 0;
    }

    public boolean editDataCustomerField(DataCustomerField dataCustomerField) {
        int i = dataCustomerFieldMapper.updateByPrimaryKeySelective(dataCustomerField);
        return i > 0;
    }

    public boolean deleteDataCustomerField(Integer id) {
        int i = dataCustomerFieldMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
