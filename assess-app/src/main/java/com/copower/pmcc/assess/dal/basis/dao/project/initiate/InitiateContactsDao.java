package com.copower.pmcc.assess.dal.basis.dao.project.initiate;

import com.copower.pmcc.assess.dal.basis.entity.InitiateContacts;
import com.copower.pmcc.assess.dal.basis.entity.InitiateContactsExample;
import com.copower.pmcc.assess.dal.basis.mapper.InitiateContactsMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 联系人
 * Created by 13426 on 2018/5/4.
 */
@Repository
public class InitiateContactsDao {

    @Autowired
    private InitiateContactsMapper mapper;

    public int save(InitiateContacts contacts) {
        mapper.insertSelective(contacts);
        return contacts.getId();
    }

    public boolean add(InitiateContacts contacts) {
        return mapper.insertSelective(contacts) == 1;
    }

    public boolean remove(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public boolean remove(Integer pid, Integer type) {
        InitiateContactsExample example = new InitiateContactsExample();
        InitiateContactsExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (pid != null) {
            criteria.andCPidEqualTo(pid);
        }
        if (type != null) {
            criteria.andCTypeEqualTo(type);
        }
        return mapper.deleteByExample(example) >= 1;
    }

    public void clear(InitiateContacts contacts){
        InitiateContactsExample example = new InitiateContactsExample();
        MybatisUtils.convertObj2Example(contacts, example);
        mapper.deleteByExample(example);
    }

    public boolean update(InitiateContacts contacts) {
        return mapper.updateByPrimaryKeySelective(contacts) == 1;
    }

    public List<InitiateContacts> initiateContactsList(InitiateContacts initiateContacts){
        InitiateContactsExample example = new InitiateContactsExample();
        MybatisUtils.convertObj2Example(initiateContacts, example);
        return mapper.selectByExample(example);
    }

    public List<InitiateContacts> getByIds(List<Integer> ids){
        InitiateContactsExample example = new InitiateContactsExample();
        example.createCriteria().andIdIn(ids) ;
        return mapper.selectByExample(example);
    }


    public InitiateContacts get(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<InitiateContacts> getList(Integer cPid, Integer cType, String createPeople, Integer customerId, Integer crmID) {
        InitiateContactsExample example = new InitiateContactsExample();
        InitiateContactsExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (!ObjectUtils.isEmpty(cPid)) {//主表(关联的主表,如:委托人id)
            criteria.andCPidEqualTo(cPid);
        }
        if (!ObjectUtils.isEmpty(cType)) {//类型如:委托人,资产占有人,报告使用单位
            criteria.andCTypeEqualTo(cType);
        }
        if (!StringUtils.isEmpty(createPeople)) {//当前用户
            criteria.andCreatorEqualTo(createPeople);
        }
        if (!ObjectUtils.isEmpty(customerId)) {
            criteria.andCustomerIdEqualTo(String.valueOf(customerId));
        }
        if (!ObjectUtils.isEmpty(crmID)) {
            criteria.andCrmIdEqualTo(String.valueOf(crmID));
        }
        List<InitiateContacts> contacts = mapper.selectByExample(example);
        return contacts;
    }

}
