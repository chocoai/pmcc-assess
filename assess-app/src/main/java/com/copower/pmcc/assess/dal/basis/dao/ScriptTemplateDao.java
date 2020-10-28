package com.copower.pmcc.assess.dal.basis.dao;

import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplate;
import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplateExample;
import com.copower.pmcc.assess.dal.basis.mapper.ScriptTemplateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 09:55
 * @Description:
 */
@Repository
public class ScriptTemplateDao {
    @Autowired
    private ScriptTemplateMapper scriptTemplateMapper;

    public Integer addScriptTemplate(ScriptTemplate scriptTemplate) {
        scriptTemplateMapper.insertSelective(scriptTemplate);
        return scriptTemplate.getId();
    }

    public ScriptTemplate getScriptTemplateById(Integer id) {
        return scriptTemplateMapper.selectByPrimaryKey(id);
    }

    public ScriptTemplate getScriptTemplateByKey(String key) {
        ScriptTemplateExample example = new ScriptTemplateExample();
        ScriptTemplateExample.Criteria criteria = example.createCriteria();
        criteria.andTemplateKeyEqualTo(key);
        List<ScriptTemplate> list = scriptTemplateMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0);
    }

    public boolean updateScriptTemplate(ScriptTemplate dataBlock) {
        return scriptTemplateMapper.updateByPrimaryKeySelective(dataBlock) == 1;
    }

    public void removeScriptTemplate(ScriptTemplate scriptTemplate) {
        ScriptTemplateExample example = new ScriptTemplateExample();
        MybatisUtils.convertObj2Example(scriptTemplate, example);
        scriptTemplateMapper.deleteByExample(example);
    }

    public List<ScriptTemplate> getScriptTemplateList(ScriptTemplate scriptTemplate) {
        ScriptTemplateExample example = new ScriptTemplateExample();
        MybatisUtils.convertObj2Example(scriptTemplate, example);
        return scriptTemplateMapper.selectByExample(example);
    }
}
