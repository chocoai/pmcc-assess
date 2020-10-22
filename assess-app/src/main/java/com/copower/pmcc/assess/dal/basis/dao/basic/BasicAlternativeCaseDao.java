package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicAlternativeCase;
import com.copower.pmcc.assess.dal.basis.entity.BasicAlternativeCaseExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicAlternativeCaseMapper;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:52
 * @Description:
 */
@Repository
public class BasicAlternativeCaseDao {
    @Autowired
    private BasicAlternativeCaseMapper bBasicAlternativeCaseMapper;

    public Integer addBasicAlternativeCase(BasicAlternativeCase bBasicAlternativeCase) {
        bBasicAlternativeCaseMapper.insertSelective(bBasicAlternativeCase);
        return bBasicAlternativeCase.getId();
    }

    public BasicAlternativeCase getBasicAlternativeCaseById(Integer id) {
        return bBasicAlternativeCaseMapper.selectByPrimaryKey(id);
    }

    public boolean updateBasicAlternativeCase(BasicAlternativeCase bBasicAlternativeCase) {
        return bBasicAlternativeCaseMapper.updateByPrimaryKeySelective(bBasicAlternativeCase) == 1;
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteInfo(Integer id) {
        return bBasicAlternativeCaseMapper.deleteByPrimaryKey(id) > 0;
    }

    public Boolean deleteDataByBatchDetailId(Integer batchDetailId) {
        BasicAlternativeCaseExample example = new BasicAlternativeCaseExample();
        example.createCriteria().andBatchDetailIdEqualTo(batchDetailId);
        return bBasicAlternativeCaseMapper.deleteByExample(example) > 0;
    }

    public List<BasicAlternativeCase> getBasicAlternativeCaseList(String name,String type, String businessKey, String creator) {
        BasicAlternativeCaseExample example = new BasicAlternativeCaseExample();
        BasicAlternativeCaseExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%s%s%s", "%", name, "%"));
        }
        if (StringUtils.isNotBlank(type)) {
            criteria.andTypeEqualTo(type);
        }
        if (StringUtils.isNotBlank(businessKey)) {
            criteria.andBusinessKeyLike(String.format("%s%s%s", "%", businessKey, "%"));
        }
        if (StringUtils.isNotBlank(creator)) {
            criteria.andCreatorEqualTo(creator);
        }
        example.setOrderByClause("id desc");
        return bBasicAlternativeCaseMapper.selectByExample(example);
    }

}
