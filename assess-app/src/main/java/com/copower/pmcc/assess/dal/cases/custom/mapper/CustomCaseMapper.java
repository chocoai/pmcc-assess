package com.copower.pmcc.assess.dal.cases.custom.mapper;

import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.entity.*;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by kings on 2018-11-21.
 */
public interface CustomCaseMapper {

    /**
     * 获取案例最新版本楼盘信息
     *
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
     *
     * @param estateId
     * @return
     */
    public List<CustomCaseEntity> getCaseBuildingList(@Param("buildingNumber") String buildingNumber, @Param("estateId") Integer estateId);

    /**
     * 根据楼栋id获取单元信息
     *
     * @param buildingId
     * @return
     */
    public List<CustomCaseEntity> getCaseUnitList(@Param("unitNumber") String unitNumber, @Param("buildingId") Integer buildingId);

    /**
     * 根据单元id获取房屋信息
     *
     * @param unitId
     * @return
     */
    public List<CustomCaseEntity> getCaseHouseList(@Param("houseNumber") String houseNumber, @Param("unitId") Integer unitId);

    /**
     * 根据条件查出 CaseBaseHouse 实体列表
     *
     * @param tradingUnitPriceStart
     * @param tradingUnitPriceEnd
     * @param tradingTimeStart
     * @param tradingTimeEnd
     * @param caseBaseHouse
     * @return
     * @throws SQLException
     */
    public List<CaseBaseHouse> findCaseBaseHouseList(@Param("areaStart") BigDecimal tradingUnitPriceStart, @Param("areaEnd") BigDecimal tradingUnitPriceEnd, @Param("tradingTimeStart") Date tradingTimeStart, @Param("tradingTimeEnd") Date tradingTimeEnd, @Param("caseBaseHouse") CaseBaseHouse caseBaseHouse) throws SQLException;

    /**
     * 筛选版本最新并且筛选版本不重复的楼栋号码
     *
     * @param estateId
     * @return List<CaseBuildingMain>
     * @throws Exception
     */
    public List<CaseBuilding> screenBuildList(@Param("estateId") Integer estateId) throws Exception;

    /**
     * 筛选版本最新并且筛选版本不重复的单元号码
     *
     * @param buildingId
     * @return CaseEstateTagging
     * @throws SQLException
     */
    public List<CaseUnit> screenUnitList(@Param("buildingId") Integer buildingId) throws SQLException;

    /**
     * 筛选版本最新并且筛选版本不重复的房屋号码
     *
     * @param unitId
     * @return CaseEstateTagging
     * @throws SQLException
     */
    public List<CaseHouse> screenHouseList(@Param("unitId") Integer unitId) throws SQLException;


}
