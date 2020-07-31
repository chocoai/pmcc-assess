package com.copower.pmcc.assess.dal.basis.custom.mapper;

import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomProjectPlanCount;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomProjectPlanDetailCount;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummary;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by kings on 2018-11-21.
 */
public interface CustomProjectMapper {

    /**
     * 按月统计完成的工作阶段数量
     *
     * @param startMonth
     * @param endMonth
     * @return
     */
    public List<CustomProjectPlanCount> getPlanCountByMonth(@Param("startMonth") String startMonth, @Param("endMonth") String endMonth);


    /**
     * 按月统计完成的工作事项数量
     *
     * @param startMonth
     * @param endMonth
     * @return
     */
    public List<CustomProjectPlanDetailCount> getPlanDetailsCountByMonth(@Param("startMonth") String startMonth,@Param("endMonth") String endMonth);


}
