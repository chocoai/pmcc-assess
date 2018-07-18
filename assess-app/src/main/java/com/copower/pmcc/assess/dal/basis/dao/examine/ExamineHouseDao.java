package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouse;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseExample;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouse;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
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
public class ExamineHouseDao {
    @Autowired
    private ExamineHouseMapper examineHouseMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineHouse getHouseById(Integer id) {
        return examineHouseMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据信息
     * @param declareId
     * @return
     */
    public ExamineHouse getHouseByDeclareId(Integer declareId) {
        ExamineHouseExample example = new ExamineHouseExample();
        example.createCriteria().andDeclareIdEqualTo(declareId);
        List<ExamineHouse> blockList = examineHouseMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(blockList)) return blockList.get(0);
        return null;
    }

    /**
     * 获取数据列表
     * @param examineHouse
     * @return
     */
    public List<ExamineHouse> getHouseList(ExamineHouse examineHouse) {
        ExamineHouseExample example = new ExamineHouseExample();
        MybatisUtils.convertObj2Example(examineHouse, example);
        return examineHouseMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineHouse
     * @return
     */
    public boolean addHouse(ExamineHouse examineHouse) {
        return examineHouseMapper.insertSelective(examineHouse) > 0;
    }

    /**
     * 编辑
     * @param examineHouse
     * @return
     */
    public boolean updateHouse(ExamineHouse examineHouse) {
        return examineHouseMapper.updateByPrimaryKeySelective(examineHouse) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouse(Integer id){
        return examineHouseMapper.deleteByPrimaryKey(id) > 0;
    }

}