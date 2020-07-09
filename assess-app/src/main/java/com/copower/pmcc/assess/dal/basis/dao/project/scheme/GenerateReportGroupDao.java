package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGroup;
import com.copower.pmcc.assess.dal.basis.entity.GenerateReportGroupExample;
import com.copower.pmcc.assess.dal.basis.mapper.GenerateReportGroupMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zch on 2020-1-19.
 */
@Repository
public class GenerateReportGroupDao {

    @Autowired
    private GenerateReportGroupMapper mapper;

    public boolean updateGenerateReportGroup(GenerateReportGroup oo, boolean updateNull) {
        return updateNull ? mapper.updateByPrimaryKey(oo) == 1 : mapper.updateByPrimaryKeySelective(oo) == 1;
    }

    public boolean deleteGenerateReportGroupById(Integer id) {
        return mapper.deleteByPrimaryKey(id) == 1;
    }

    public void deleteGenerateReportGroupByIds(List<Integer> ids) {
        GenerateReportGroupExample example = new GenerateReportGroupExample();
        example.createCriteria().andIdIn(ids);
        mapper.deleteByExample(example);
    }

    public GenerateReportGroup getGenerateReportGroupById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean saveGenerateReportGroup(GenerateReportGroup oo) {
        return mapper.insertSelective(oo) == 1;
    }

    public List<GenerateReportGroup> getGenerateReportGroupByIds(List<Integer> ids) {
        GenerateReportGroupExample example = new GenerateReportGroupExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public List<GenerateReportGroup> getGenerateReportGroupListByExample(GenerateReportGroup oo) {
        GenerateReportGroupExample example = new GenerateReportGroupExample();
        GenerateReportGroupExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();

        MybatisUtils.convertObj2Criteria(oo, criteria);
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }

    public List<GenerateReportGroup> getGenerateReportGroupListByLike(GenerateReportGroup oo) {
        GenerateReportGroupExample example = new GenerateReportGroupExample();
        GenerateReportGroupExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (StringUtils.isNotBlank(oo.getName())) {
            criteria.andNameLike(String.format("%%%s%%", oo.getName()));
        }
        if (StringUtils.isNotBlank(oo.getFullName())) {
            criteria.andFullNameLike(String.format("%%%s%%", oo.getFullName()));
        }
        if (oo.getReportInfoId() != null){
            criteria.andReportInfoIdEqualTo(oo.getReportInfoId()) ;
        }
        example.setOrderByClause("id");
        return mapper.selectByExample(example);
    }


}
