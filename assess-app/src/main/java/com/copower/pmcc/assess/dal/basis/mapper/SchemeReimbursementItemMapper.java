package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursementItem;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursementItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchemeReimbursementItemMapper {
    long countByExample(SchemeReimbursementItemExample example);

    int deleteByExample(SchemeReimbursementItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SchemeReimbursementItem record);

    int insertSelective(SchemeReimbursementItem record);

    List<SchemeReimbursementItem> selectByExample(SchemeReimbursementItemExample example);

    SchemeReimbursementItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SchemeReimbursementItem record, @Param("example") SchemeReimbursementItemExample example);

    int updateByExample(@Param("record") SchemeReimbursementItem record, @Param("example") SchemeReimbursementItemExample example);

    int updateByPrimaryKeySelective(SchemeReimbursementItem record);

    int updateByPrimaryKey(SchemeReimbursementItem record);
}