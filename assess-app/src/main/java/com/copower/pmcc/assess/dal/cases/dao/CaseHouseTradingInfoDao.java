package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingInfo;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTradingInfoExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseHouseTradingInfoMapper;
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
public class CaseHouseTradingInfoDao {
    @Autowired
    private CaseHouseTradingInfoMapper caseHouseTradingInfoMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseHouseTradingInfo getHouseTradingInfoById(Integer id) {
        return caseHouseTradingInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseHouseTradingInfo
     * @return
     */
    public List<CaseHouseTradingInfo> getHouseTradingInfoList(CaseHouseTradingInfo caseHouseTradingInfo) {
        CaseHouseTradingInfoExample example = new CaseHouseTradingInfoExample();
        MybatisUtils.convertObj2Example(caseHouseTradingInfo, example);
        return caseHouseTradingInfoMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseHouseTradingInfo
     * @return
     */
    public boolean addHouseTradingInfo(CaseHouseTradingInfo caseHouseTradingInfo) {
        return caseHouseTradingInfoMapper.insertSelective(caseHouseTradingInfo) > 0;
    }

    /**
     * 编辑
     * @param caseHouseTradingInfo
     * @return
     */
    public boolean updateHouseTradingInfo(CaseHouseTradingInfo caseHouseTradingInfo) {
        return caseHouseTradingInfoMapper.updateByPrimaryKeySelective(caseHouseTradingInfo) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseTradingInfo(Integer id){
        return caseHouseTradingInfoMapper.deleteByPrimaryKey(id) > 0;
    }

}