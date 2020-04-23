package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BaseQrcode;
import com.copower.pmcc.assess.dal.basis.entity.BaseQrcodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseQrcodeMapper {
    int countByExample(BaseQrcodeExample example);

    int deleteByExample(BaseQrcodeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseQrcode record);

    int insertSelective(BaseQrcode record);

    List<BaseQrcode> selectByExampleWithBLOBs(BaseQrcodeExample example);

    List<BaseQrcode> selectByExample(BaseQrcodeExample example);

    BaseQrcode selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseQrcode record, @Param("example") BaseQrcodeExample example);

    int updateByExampleWithBLOBs(@Param("record") BaseQrcode record, @Param("example") BaseQrcodeExample example);

    int updateByExample(@Param("record") BaseQrcode record, @Param("example") BaseQrcodeExample example);

    int updateByPrimaryKeySelective(BaseQrcode record);

    int updateByPrimaryKeyWithBLOBs(BaseQrcode record);

    int updateByPrimaryKey(BaseQrcode record);
}