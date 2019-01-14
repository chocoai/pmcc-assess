package com.copower.pmcc.assess.dal.basis.dao.data;

import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatio;
import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatioExample;
import com.copower.pmcc.assess.dal.basis.mapper.ToolResidueRatioMapper;
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
public class ToolResidueRatioDao {
    @Autowired
    private ToolResidueRatioMapper toolResidueRatioMapper;

    public ToolResidueRatio getToolResidueRatio(Integer id) {
        return toolResidueRatioMapper.selectByPrimaryKey(id);
    }


    public List<ToolResidueRatio> getObjectList(ToolResidueRatio toolResidueRatio) {
        ToolResidueRatioExample example = new ToolResidueRatioExample();
        MybatisUtils.convertObj2Example(toolResidueRatio, example);
        return toolResidueRatioMapper.selectByExample(example);
    }

    public boolean addToolResidueRatio(ToolResidueRatio toolResidueRatio) {
        int i = toolResidueRatioMapper.insert(toolResidueRatio);
        return i > 0;
    }

    public boolean editToolResidueRatio(ToolResidueRatio toolResidueRatio) {
        int i = toolResidueRatioMapper.updateByPrimaryKeySelective(toolResidueRatio);
        return i > 0;
    }

    public boolean deleteToolResidueRatio(Integer id) {
        int i = toolResidueRatioMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
