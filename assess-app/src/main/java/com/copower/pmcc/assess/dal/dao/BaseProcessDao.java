package com.copower.pmcc.assess.dal.dao;

import com.copower.pmcc.assess.dal.entity.BaseProcess;
import com.copower.pmcc.assess.dal.entity.BaseProcessExample;
import com.copower.pmcc.assess.dal.entity.BaseProcessForm;
import com.copower.pmcc.assess.dal.entity.BaseProcessFormExample;
import com.copower.pmcc.assess.dal.mapper.BaseProcessFormMapper;
import com.copower.pmcc.assess.dal.mapper.BaseProcessMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/23
 * @time: 14:43
 */
@Repository
public class BaseProcessDao {
    @Autowired
    private BaseProcessMapper hrProcessMapper;
    @Autowired
    private BaseProcessFormMapper hrProcessFormMapper;

    //Process==================================================================

    public BaseProcess getProcessById(Integer id) {
        return hrProcessMapper.selectByPrimaryKey(id);
    }

    public BaseProcess getProcessByName(String name) {
        BaseProcessExample example = new BaseProcessExample();
        example.createCriteria().andBisEnableEqualTo(true).andNameEqualTo(name);
        List<BaseProcess> hrProcesses = hrProcessMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(hrProcesses)) {
            return hrProcesses.get(0);
        }
        return null;
    }

    public List<BaseProcessForm> getProcessForms(Integer processId,String boxName) {
        BaseProcessFormExample example = new BaseProcessFormExample();
        BaseProcessFormExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        criteria.andProcessIdEqualTo(processId);
        if(StringUtils.isNotBlank(boxName)){
            criteria.andBoxReActivityNameEqualTo(boxName);
        }
        example.setOrderByClause("sorting");
        return hrProcessFormMapper.selectByExample(example);
    }

    public BaseProcess getProcessByBoxName(String boxName) {
        BaseProcessExample example = new BaseProcessExample();
        example.createCriteria().andBisEnableEqualTo(true).andBoxNameEqualTo(boxName);
        List<BaseProcess> hrProcesses = hrProcessMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(hrProcesses)) {
            return hrProcesses.get(0);
        }
        return null;
    }

    public Boolean saveBaseProcess(BaseProcess hrBaseProcess) {
        return hrProcessMapper.insertSelective(hrBaseProcess) == 1;
    }

    public Boolean updateBaseProcess(BaseProcess hrBaseProcess) {
        return hrProcessMapper.updateByPrimaryKeySelective(hrBaseProcess) >= 0;
    }
    public Boolean deleteBaseProcess(Integer id) {
        return hrProcessMapper.deleteByPrimaryKey(id)==1;
    }
    public List<BaseProcess> getBaseProcessList(String search) {
        BaseProcessExample example = new BaseProcessExample();
        BaseProcessExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(search)) {
            criteria.andCnNameLike(String.format("%s%s%s", "%", search, "%"));
        }
        return hrProcessMapper.selectByExample(example);
    }

    //ProcessForm==================================================================

    public List<BaseProcessForm> getProcessFormByProcess(Integer processId) {
        BaseProcessFormExample example = new BaseProcessFormExample();
        example.createCriteria().andBisEnableEqualTo(true).andProcessIdEqualTo(processId);
        example.setOrderByClause("sorting");
        return hrProcessFormMapper.selectByExample(example);
    }


    public List<BaseProcessForm> getProcessFormList(BaseProcessForm hrBaseProcessForm) {
        BaseProcessFormExample example = new BaseProcessFormExample();
        MybatisUtils.convertObj2Example(hrBaseProcessForm,example);
        example.createCriteria().andBisEnableEqualTo(true);
        example.setOrderByClause("sorting");
        return hrProcessFormMapper.selectByExample(example);
    }

    public List<BaseProcessForm> getBaseProcessFormList(String search, Integer procesId) {
        BaseProcessFormExample example = new BaseProcessFormExample();
        BaseProcessFormExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true).andProcessIdEqualTo(procesId);
        if (StringUtils.isNotBlank(search)) {
            criteria.andFormModuleNameLike(String.format("%s%s%s", "%", search, "%"));
        }
        example.setOrderByClause(" sorting");
        return hrProcessFormMapper.selectByExample(example);
    }


    public Boolean saveBaseProcessForm(BaseProcessForm hrBaseProcess) {
        return hrProcessFormMapper.insertSelective(hrBaseProcess) == 1;
    }

    public Boolean updateBaseProcessForm(BaseProcessForm hrBaseProcess) {
        return hrProcessFormMapper.updateByPrimaryKeySelective(hrBaseProcess) >= 0;
    }

    public Boolean deleteBaseProcessForm(Integer id) {
        return hrProcessFormMapper.deleteByPrimaryKey(id)==1;
    }
}
