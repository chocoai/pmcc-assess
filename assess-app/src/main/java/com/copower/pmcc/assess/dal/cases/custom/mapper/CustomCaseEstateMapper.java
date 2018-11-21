package com.copower.pmcc.assess.dal.cases.custom.mapper;

import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEstate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kings on 2018-11-21.
 */
public interface CustomCaseEstateMapper {

    /**
     * 获取案例最新版本楼盘信息
     * @param name
     * @param province
     * @param city
     * @param district
     * @return
     */
    public List<CustomCaseEstate> getCustomCaseEstateList(@Param("name") String name,
                                                          @Param("province") String province,
                                                          @Param("city") String city,
                                                          @Param("district") String district);
}
