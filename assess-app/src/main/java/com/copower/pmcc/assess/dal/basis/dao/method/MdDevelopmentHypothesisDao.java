package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentHypothesis;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentHypothesisExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdDevelopmentHypothesisMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/24 18:50
 * @Description:
 */
@Repository
public class MdDevelopmentHypothesisDao {
    @Autowired
    private MdDevelopmentHypothesisMapper mdDevelopmentHypothesisMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdDevelopmentHypothesis getMdDevelopmentHypothesisById(Integer id) {
        return mdDevelopmentHypothesisMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param mdDevelopmentHypothesis
     * @return
     */
    public List<MdDevelopmentHypothesis> getMdDevelopmentHypothesisList(MdDevelopmentHypothesis mdDevelopmentHypothesis) {
        MdDevelopmentHypothesisExample example = new MdDevelopmentHypothesisExample();
        MybatisUtils.convertObj2Example(mdDevelopmentHypothesis, example);
        return mdDevelopmentHypothesisMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param mdDevelopmentHypothesis
     * @return
     */
    public int addMdDevelopmentHypothesis(MdDevelopmentHypothesis mdDevelopmentHypothesis) {
        mdDevelopmentHypothesisMapper.insertSelective(mdDevelopmentHypothesis);
        return mdDevelopmentHypothesis.getId();
    }

    /**
     * 编辑
     * @param mdDevelopmentHypothesis
     * @return
     */
    public boolean updateMdDevelopmentHypothesis(MdDevelopmentHypothesis mdDevelopmentHypothesis) {
        return mdDevelopmentHypothesisMapper.updateByPrimaryKeySelective(mdDevelopmentHypothesis) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMdDevelopmentHypothesis(Integer id){
        return mdDevelopmentHypothesisMapper.deleteByPrimaryKey(id) > 0;
    }
}
