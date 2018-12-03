package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdCostAndDevelopmentOther;
import com.copower.pmcc.assess.dal.basis.entity.MdCostAndDevelopmentOtherExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdCostAndDevelopmentOtherMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/23 09:21
 * @Description:
 */
@Repository
public class MdCostAndDevelopmentOtherDao {
    @Autowired
    private MdCostAndDevelopmentOtherMapper mdCostAndDevelopmentOtherMapper;

    public void deletePid(int pid){
        MdCostAndDevelopmentOtherExample example = new MdCostAndDevelopmentOtherExample();
        MdCostAndDevelopmentOtherExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        criteria.andPidEqualTo(pid);
        mdCostAndDevelopmentOtherMapper.deleteByExample(example);
    }

    public List<MdCostAndDevelopmentOther> getMdCostAndDevelopmentOtherList(MdCostAndDevelopmentOther mdCostAndDevelopmentOther){
        MdCostAndDevelopmentOtherExample example = new MdCostAndDevelopmentOtherExample();
        MybatisUtils.convertObj2Example(mdCostAndDevelopmentOther, example);
        return mdCostAndDevelopmentOtherMapper.selectByExample(example);
    }

    public int addMdCostAndDevelopmentOther(MdCostAndDevelopmentOther mdCostAndDevelopmentOther){
        mdCostAndDevelopmentOtherMapper.insertSelective(mdCostAndDevelopmentOther);
        return mdCostAndDevelopmentOther.getId();
    }

    public List<MdCostAndDevelopmentOther> costAndDevelopmentOtherList(String type,Integer pid,String databaseName){
        MdCostAndDevelopmentOtherExample example = new MdCostAndDevelopmentOtherExample();
        MdCostAndDevelopmentOtherExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (StringUtils.isNotBlank(type)){
            criteria.andTypeEqualTo(type);
        }
        if (StringUtils.isNotBlank(databaseName)){
            criteria.andDatabaseNameEqualTo(databaseName);
        }
        if (pid != null){
            criteria.andPidEqualTo(pid);
        }
        //降序
        example.setOrderByClause("id desc");
        //去重复
        example.setDistinct(false);
        return mdCostAndDevelopmentOtherMapper.selectByExample(example);
    }

    public boolean updateMdCostAndDevelopmentOther(MdCostAndDevelopmentOther mdCostAndDevelopmentOther){
        return mdCostAndDevelopmentOtherMapper.updateByPrimaryKeySelective(mdCostAndDevelopmentOther)==1;
    }

    public MdCostAndDevelopmentOther getMdCostAndDevelopmentOther(Integer id){
        return mdCostAndDevelopmentOtherMapper.selectByPrimaryKey(id);
    }
}
