package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.DataDeclareForm;
import com.copower.pmcc.assess.dal.entity.DataDeclareFormExample;
import com.copower.pmcc.assess.dal.mapper.DataDeclareFormMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/8/31
 * @time: 16:00
 */
@Repository
public class DataDeclareFormDao {
    @Autowired
    private DataDeclareFormMapper dataDeclareFormMapper;

    public DataDeclareForm getDataDeclareForm(Integer id){
        return dataDeclareFormMapper.selectByPrimaryKey(id);
    }

    public List<DataDeclareForm> getDataDeclareFormList(Integer assessClass, String name) {
        DataDeclareFormExample example = new DataDeclareFormExample();
        DataDeclareFormExample.Criteria criteria = example.createCriteria();
        if (assessClass != null) {
            criteria.andAssessClassEqualTo(assessClass);
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%s%s%s", "%", name, "%"));
        }
        example.setOrderByClause(" id desc");
        List<DataDeclareForm> DataDeclareForms = dataDeclareFormMapper.selectByExample(example);
        return DataDeclareForms;
    }


    public Boolean addDataDeclareForm(DataDeclareForm DataDeclareForm) {
        int i = dataDeclareFormMapper.insertSelective(DataDeclareForm);
        return i == 1;
    }

    public Boolean updateDataDeclareForm(DataDeclareForm DataDeclareForm) {
        int i = dataDeclareFormMapper.updateByPrimaryKeySelective(DataDeclareForm);
        return i >= 0;
    }

    public Boolean deleteDataDeclareForm(Integer id){
        return dataDeclareFormMapper.deleteByPrimaryKey(id) > 0;
    }

}
