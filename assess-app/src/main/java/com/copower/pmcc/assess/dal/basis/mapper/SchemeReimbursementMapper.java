package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursement;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeReimbursementMapper {
    long countByExample(SchemeReimbursementExample example);

    int deleteByExample(SchemeReimbursementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeReimbursement record);

    int insertSelective(SchemeReimbursement record);

    List<SchemeReimbursement> selectByExample(SchemeReimbursementExample example);

    SchemeReimbursement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeReimbursement record, @Param("example") SchemeReimbursementExample example);

    int updateByExample(@Param("record") SchemeReimbursement record, @Param("example") SchemeReimbursementExample example);

    int updateByPrimaryKeySelective(SchemeReimbursement record);

    int updateByPrimaryKey(SchemeReimbursement record);
}