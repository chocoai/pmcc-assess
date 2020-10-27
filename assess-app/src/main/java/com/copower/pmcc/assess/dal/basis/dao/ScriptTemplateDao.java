package com.copower.pmcc.assess.dal.basis.dao;

import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplate;
import com.copower.pmcc.assess.dal.basis.entity.ScriptTemplateExample;
import com.copower.pmcc.assess.dal.basis.mapper.ScriptTemplateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 09:55
 * @Description:
 */
@Repository
public class ScriptTemplateDao {
    @Autowired
    private ScriptTemplateMapper dataBlockMapper;

    public Integer addScriptTemplate(ScriptTemplate scriptTemplate) {
        dataBlockMapper.insertSelective(scriptTemplate);
        return scriptTemplate.getId();
    }

    public ScriptTemplate getScriptTemplateById(Integer id) {
        return dataBlockMapper.selectByPrimaryKey(id);
    }

    public boolean updateScriptTemplate(ScriptTemplate dataBlock) {
        return dataBlockMapper.updateByPrimaryKeySelective(dataBlock) == 1;
    }

    public void removeScriptTemplate(ScriptTemplate scriptTemplate) {
        ScriptTemplateExample example = new ScriptTemplateExample();
        MybatisUtils.convertObj2Example(scriptTemplate, example);
        dataBlockMapper.deleteByExample(example);
    }

    public List<ScriptTemplate> getScriptTemplateList(ScriptTemplate scriptTemplate) {
        ScriptTemplateExample example = new ScriptTemplateExample();
        MybatisUtils.convertObj2Example(scriptTemplate, example);
        return dataBlockMapper.selectByExample(example);
    }
}
