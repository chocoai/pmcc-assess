package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.GenerateReportItem;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.GenerateReportItemMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2020-1-19.
 */
@Repository
public class GenerateReportItemDao {

    @Autowired
    private GenerateReportItemMapper mapper;

    public boolean updateGenerateReportItem(GenerateReportItem oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteGenerateReportItemById(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void deleteGenerateReportItemByIds(List<Integer> ids) {
        GenerateReportItemExample example = new GenerateReportItemExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example);
    }

    public GenerateReportItem getGenerateReportItemById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean saveGenerateReportItem(GenerateReportItem oo) {
        return mapper.insertSelective(oo) == 1;
    }

    public List<GenerateReportItem> getGenerateReportItemByIds(List<Integer> ids) {
        GenerateReportItemExample example = new GenerateReportItemExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public List<GenerateReportItem> getGenerateReportItemListByExample(GenerateReportItem oo) {
        GenerateReportItemExample example = new GenerateReportItemExample();
        GenerateReportItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        MybatisUtils.convertObj2Criteria(oo, criteria);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }



}
