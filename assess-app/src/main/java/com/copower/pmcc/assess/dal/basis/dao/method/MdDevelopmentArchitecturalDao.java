package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentArchitectural;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentArchitecturalExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdDevelopmentArchitecturalMapper;
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
public class MdDevelopmentArchitecturalDao {
    
    @Autowired
    private MdDevelopmentArchitecturalMapper mdDevelopmentArchitecturalMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdDevelopmentArchitectural getIncomeById(Integer id) {
        return mdDevelopmentArchitecturalMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param mdDevelopmentArchitectural
     * @return
     */
    public List<MdDevelopmentArchitectural> getIncomeList(MdDevelopmentArchitectural mdDevelopmentArchitectural) {
        MdDevelopmentArchitecturalExample example = new MdDevelopmentArchitecturalExample();
        MybatisUtils.convertObj2Example(mdDevelopmentArchitectural, example);
        return mdDevelopmentArchitecturalMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param mdDevelopmentArchitectural
     * @return
     */
    public int addIncome(MdDevelopmentArchitectural mdDevelopmentArchitectural) {
        mdDevelopmentArchitecturalMapper.insertSelective(mdDevelopmentArchitectural);
        return mdDevelopmentArchitectural.getId();
    }

    /**
     * 编辑
     * @param mdDevelopmentArchitectural
     * @return
     */
    public boolean updateIncome(MdDevelopmentArchitectural mdDevelopmentArchitectural) {
        return mdDevelopmentArchitecturalMapper.updateByPrimaryKeySelective(mdDevelopmentArchitectural) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteIncome(Integer id){
        return mdDevelopmentArchitecturalMapper.deleteByPrimaryKey(id) > 0;
    }
}
