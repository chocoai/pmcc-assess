package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopment;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdDevelopmentMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/24 18:49
 * @Description:
 */
@Repository
public class MdDevelopmentDao {
    @Autowired
    private MdDevelopmentMapper mdDevelopmentMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdDevelopment getMdDevelopmentById(Integer id) {
        return mdDevelopmentMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param mdDevelopment
     * @return
     */
    public List<MdDevelopment> getMdDevelopmentList(MdDevelopment mdDevelopment) {
        MdDevelopmentExample example = new MdDevelopmentExample();
        MybatisUtils.convertObj2Example(mdDevelopment, example);
        return mdDevelopmentMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param mdDevelopment
     * @return
     */
    public int addMdDevelopment(MdDevelopment mdDevelopment) {
        mdDevelopmentMapper.insertSelective(mdDevelopment);
        return mdDevelopment.getId();
    }

    /**
     * 编辑
     * @param mdDevelopment
     * @return
     */
    public boolean updateMdDevelopment(MdDevelopment mdDevelopment) {
        return mdDevelopmentMapper.updateByPrimaryKeySelective(mdDevelopment) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMdDevelopment(Integer id){
        return mdDevelopmentMapper.deleteByPrimaryKey(id) > 0;
    }
}
