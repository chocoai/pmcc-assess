package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingInfo;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseTradingInfoExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseTradingInfoMapper;
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
public class ExamineHouseTradingInfoDao {
    @Autowired
    private ExamineHouseTradingInfoMapper examineHouseTradingInfoMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineHouseTradingInfo getHouseTradingInfoById(Integer id) {
        return examineHouseTradingInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineHouseTradingInfo
     * @return
     */
    public List<ExamineHouseTradingInfo> getHouseTradingInfoList(ExamineHouseTradingInfo examineHouseTradingInfo) {
        ExamineHouseTradingInfoExample example = new ExamineHouseTradingInfoExample();
        MybatisUtils.convertObj2Example(examineHouseTradingInfo, example);
        return examineHouseTradingInfoMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineHouseTradingInfo
     * @return
     */
    public boolean addHouseTradingInfo(ExamineHouseTradingInfo examineHouseTradingInfo) {
        return examineHouseTradingInfoMapper.insertSelective(examineHouseTradingInfo) > 0;
    }

    /**
     * 编辑
     * @param examineHouseTradingInfo
     * @return
     */
    public boolean updateHouseTradingInfo(ExamineHouseTradingInfo examineHouseTradingInfo) {
        return examineHouseTradingInfoMapper.updateByPrimaryKeySelective(examineHouseTradingInfo) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseTradingInfo(Integer id){
        return examineHouseTradingInfoMapper.deleteByPrimaryKey(id) > 0;
    }

}