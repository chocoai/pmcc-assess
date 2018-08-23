package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTrading;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingExample;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTrading;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseTradingMapper;
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
public class ExamineHouseTradingDao {
    @Autowired
    private ExamineHouseTradingMapper examineHouseTradingMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineHouseTrading getHouseTradingById(Integer id) {
        return examineHouseTradingMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据信息
     * @param declareId
     * @return
     */
    public ExamineHouseTrading getHouseTradingByDeclareId(Integer declareId,Integer planDetailsId,Integer examineType) {
        ExamineHouseTradingExample example = new ExamineHouseTradingExample();
        example.createCriteria().andDeclareIdEqualTo(declareId).andPlanDetailsIdEqualTo(planDetailsId).andExamineTypeEqualTo(examineType);
        List<ExamineHouseTrading> blockList = examineHouseTradingMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(blockList)) return blockList.get(0);
        return null;
    }

    /**
     * 获取数据列表
     * @param examineHouseTrading
     * @return
     */
    public List<ExamineHouseTrading> getHouseTradingList(ExamineHouseTrading examineHouseTrading) {
        ExamineHouseTradingExample example = new ExamineHouseTradingExample();
        MybatisUtils.convertObj2Example(examineHouseTrading, example);
        return examineHouseTradingMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineHouseTrading
     * @return
     */
    public boolean addHouseTrading(ExamineHouseTrading examineHouseTrading) {
        return examineHouseTradingMapper.insertSelective(examineHouseTrading) > 0;
    }

    /**
     * 编辑
     * @param examineHouseTrading
     * @return
     */
    public boolean updateHouseTrading(ExamineHouseTrading examineHouseTrading) {
        return examineHouseTradingMapper.updateByPrimaryKeySelective(examineHouseTrading) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseTrading(Integer id){
        return examineHouseTradingMapper.deleteByPrimaryKey(id) > 0;
    }

}