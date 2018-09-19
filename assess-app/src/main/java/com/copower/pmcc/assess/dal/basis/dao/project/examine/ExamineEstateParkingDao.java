package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateParking;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateParkingExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineEstateParkingMapper;
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
public class ExamineEstateParkingDao {
    @Autowired
    private ExamineEstateParkingMapper examineEstateParkingMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineEstateParking getEstateParkingById(Integer id) {
        return examineEstateParkingMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineEstateParking
     * @return
     */
    public List<ExamineEstateParking> getEstateParkingList(ExamineEstateParking examineEstateParking) {
        ExamineEstateParkingExample example = new ExamineEstateParkingExample();
        MybatisUtils.convertObj2Example(examineEstateParking, example);
        return examineEstateParkingMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineEstateParking
     * @return
     */
    public int addEstateParking(ExamineEstateParking examineEstateParking) {
        examineEstateParkingMapper.insertSelective(examineEstateParking) ;
        return examineEstateParking.getId();
    }

    /**
     * 编辑
     * @param examineEstateParking
     * @return
     */
    public boolean updateEstateParking(ExamineEstateParking examineEstateParking) {
        return examineEstateParkingMapper.updateByPrimaryKeySelective(examineEstateParking) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateParking(Integer id){
        return examineEstateParkingMapper.deleteByPrimaryKey(id) > 0;
    }

}