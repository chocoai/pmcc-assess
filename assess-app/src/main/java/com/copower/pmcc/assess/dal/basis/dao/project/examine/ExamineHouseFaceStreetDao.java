package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseFaceStreet;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseFaceStreetExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseFaceStreetMapper;
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
public class ExamineHouseFaceStreetDao {
    @Autowired
    private ExamineHouseFaceStreetMapper examineHouseFaceStreetMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineHouseFaceStreet getHouseFaceStreetById(Integer id) {
        return examineHouseFaceStreetMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineHouseFaceStreet
     * @return
     */
    public List<ExamineHouseFaceStreet> getHouseFaceStreetList(ExamineHouseFaceStreet examineHouseFaceStreet) {
        ExamineHouseFaceStreetExample example = new ExamineHouseFaceStreetExample();
        MybatisUtils.convertObj2Example(examineHouseFaceStreet, example);
        return examineHouseFaceStreetMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineHouseFaceStreet
     * @return
     */
    public boolean addHouseFaceStreet(ExamineHouseFaceStreet examineHouseFaceStreet) {
        return examineHouseFaceStreetMapper.insertSelective(examineHouseFaceStreet) > 0;
    }

    /**
     * 编辑
     * @param examineHouseFaceStreet
     * @return
     */
    public boolean updateHouseFaceStreet(ExamineHouseFaceStreet examineHouseFaceStreet) {
        return examineHouseFaceStreetMapper.updateByPrimaryKeySelective(examineHouseFaceStreet) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseFaceStreet(Integer id){
        return examineHouseFaceStreetMapper.deleteByPrimaryKey(id) > 0;
    }

}