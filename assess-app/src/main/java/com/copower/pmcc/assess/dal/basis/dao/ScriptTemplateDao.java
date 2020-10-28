package com.copower.pmcc.assess.dal.basis.dao;

import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplate;
import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplateExample;
import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplateWithBLOBs;
import com.copower.pmcc.assess.dal.basis.mapper.ScriptTemplateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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

    public boolean addScriptTemplate(ScriptTemplateWithBLOBs scriptTemplate) {
        return scriptTemplateMapper.insertSelective(scriptTemplate) >= 1;
    }

    public ScriptTemplateWithBLOBs getScriptTemplateById(Integer id) {
        return scriptTemplateMapper.selectByPrimaryKey(id);
    }

    public ScriptTemplateWithBLOBs getScriptTemplateByKey(String key) {
        ScriptTemplateExample example = new ScriptTemplateExample();
        ScriptTemplateExample.Criteria criteria = example.createCriteria();
        criteria.andTemplateKeyEqualTo(key);
        List<ScriptTemplateWithBLOBs> list = scriptTemplateMapper.selectByExampleWithBLOBs(example);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0);
    }

    public boolean updateScriptTemplate(ScriptTemplateWithBLOBs scriptTemplate) {
        return scriptTemplateMapper.updateByPrimaryKeySelective(scriptTemplate) == 1;
    }

    public void removeScriptTemplate(ScriptTemplateWithBLOBs scriptTemplate) {
        ScriptTemplateExample example = new ScriptTemplateExample();
        MybatisUtils.convertObj2Example(scriptTemplate, example);
        scriptTemplateMapper.deleteByExample(example);
    }

    public void deleteScriptTemplateByIds(List<Integer> ids) {
        ScriptTemplateExample example = new ScriptTemplateExample();
        ScriptTemplateExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        scriptTemplateMapper.deleteByExample(example);
    }

    public List<ScriptTemplateWithBLOBs> getScriptTemplateList(ScriptTemplateWithBLOBs scriptTemplate) {
        ScriptTemplateExample example = new ScriptTemplateExample();
        MybatisUtils.convertObj2Example(scriptTemplate, example);
        return scriptTemplateMapper.selectByExampleWithBLOBs(example);
    }

    public List<ScriptTemplateWithBLOBs> getScriptTemplateList(String templateName, String templateKey) {
        ScriptTemplateExample example = new ScriptTemplateExample();
        ScriptTemplateExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(templateKey)) {
            criteria.andTemplateKeyEqualTo(templateKey);
        }
        if (StringUtils.isNotBlank(templateName)){
            criteria.andTemplateNameLike(String.format("%%%s%%",templateName));
        }
        return scriptTemplateMapper.selectByExampleWithBLOBs(example);
    }
}
