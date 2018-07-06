package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBlockArea;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBlockAreaExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineBlockAreaMapper;
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
public class ExamineBlockAreaDao {
    @Autowired
    private ExamineBlockAreaMapper examineBlockAreaMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineBlockArea getBlockAreaById(Integer id) {
        return examineBlockAreaMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineBlockArea
     * @return
     */
    public List<ExamineBlockArea> getBlockAreaList(ExamineBlockArea examineBlockArea) {
        ExamineBlockAreaExample example = new ExamineBlockAreaExample();
        MybatisUtils.convertObj2Example(examineBlockArea, example);
        return examineBlockAreaMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineBlockArea
     * @return
     */
    public boolean addBlockArea(ExamineBlockArea examineBlockArea) {
        return examineBlockAreaMapper.insertSelective(examineBlockArea) > 0;
    }

    /**
     * 编辑
     * @param examineBlockArea
     * @return
     */
    public boolean updateBlockArea(ExamineBlockArea examineBlockArea) {
        return examineBlockAreaMapper.updateByPrimaryKeySelective(examineBlockArea) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBlockArea(Integer id){
        return examineBlockAreaMapper.deleteByPrimaryKey(id) > 0;
    }

}