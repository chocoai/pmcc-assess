package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitDecorate;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitDecorateExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineUnitDecorateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: wangpc
 * @data: 2018/07/06
 * @time: 14:36
 */
@Repository
public class ExamineUnitDecorateDao {
    @Autowired
    private ExamineUnitDecorateMapper examineUnitDecorateMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineUnitDecorate getUnitDecorateById(Integer id) {
        return examineUnitDecorateMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineUnitDecorate
     * @return
     */
    public List<ExamineUnitDecorate> getUnitDecorateList(ExamineUnitDecorate examineUnitDecorate) {
        ExamineUnitDecorateExample example = new ExamineUnitDecorateExample();
        MybatisUtils.convertObj2Example(examineUnitDecorate, example);
        return examineUnitDecorateMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineUnitDecorate
     * @return
     */
    public boolean addUnitDecorate(ExamineUnitDecorate examineUnitDecorate) {
        return examineUnitDecorateMapper.insertSelective(examineUnitDecorate) > 0;
    }

    /**
     * 编辑
     * @param examineUnitDecorate
     * @return
     */
    public boolean updateUnitDecorate(ExamineUnitDecorate examineUnitDecorate) {
        return examineUnitDecorateMapper.updateByPrimaryKeySelective(examineUnitDecorate) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteUnitDecorate(Integer id){
        return examineUnitDecorateMapper.deleteByPrimaryKey(id) > 0;
    }

}