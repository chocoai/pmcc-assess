package com.copower.pmcc.assess.dal.cases.custom.mapper;

import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kings on 2018-11-21.
 */
public interface CustomCaseMapper {

    /**
     * 获取案例最新版本楼盘信息
     * @param name
     * @param province
     * @param city
     * @param district
     * @return
     */
    public List<CustomCaseEntity> getCaseEstateList(@Param("name") String name,
                                                          @Param("province") String province,
                                                          @Param("city") String city,
                                                          @Param("district") String district);

    /**
     * 根据楼盘id获取楼栋信息
     * @param estateId
     * @return
     */
    public List<CustomCaseEntity> getCaseBuildingMainList(@Param("buildingNumber") String buildingNumber,@Param("estateId") Integer estateId);

    /**
     * 根据楼栋id获取单元信息
     * @param buildingMainId
     * @return
     */
    public List<CustomCaseEntity> getCaseUnitList(@Param("unitNumber") String unitNumber,@Param("buildingMainId") Integer buildingMainId);

    /**
     * 根据单元id获取房屋信息
     * @param unitId
     * @return
     */
    public List<CustomCaseEntity> getCaseHouseList(@Param("houseNumber") String houseNumber,@Param("unitId") Integer unitId);
}
