package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseEstateParking;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateParkingExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseEstateParkingMapper;
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
public class CaseEstateParkingDao {
    @Autowired
    private CaseEstateParkingMapper caseEstateParkingMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseEstateParking getEstateParkingById(Integer id) {
        return caseEstateParkingMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseEstateParking
     * @return
     */
    public List<CaseEstateParking> getEstateParkingList(CaseEstateParking caseEstateParking) {
        CaseEstateParkingExample example = new CaseEstateParkingExample();
        MybatisUtils.convertObj2Example(caseEstateParking, example);
        return caseEstateParkingMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseEstateParking
     * @return
     */
    public int addEstateParking(CaseEstateParking caseEstateParking) {
       caseEstateParkingMapper.insertSelective(caseEstateParking);
       return caseEstateParking.getId();
    }

    /**
     * 编辑
     * @param caseEstateParking
     * @return
     */
    public boolean updateEstateParking(CaseEstateParking caseEstateParking) {
        return caseEstateParkingMapper.updateByPrimaryKeySelective(caseEstateParking) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateParking(Integer id){
        return caseEstateParkingMapper.deleteByPrimaryKey(id) > 0;
    }

}