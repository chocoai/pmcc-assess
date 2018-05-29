package com.copower.pmcc.assess.dal.mapper;

import com.copower.pmcc.assess.dal.entity.BaseReportBatchConclusion;
import com.copower.pmcc.assess.dal.entity.BaseReportBatchConclusionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseReportBatchConclusionMapper {
    int countByExample(BaseReportBatchConclusionExample example);

    int deleteByExample(BaseReportBatchConclusionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseReportBatchConclusion record);

    int insertSelective(BaseReportBatchConclusion record);

    List<BaseReportBatchConclusion> selectByExample(BaseReportBatchConclusionExample example);

    BaseReportBatchConclusion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseReportBatchConclusion record, @Param("example") BaseReportBatchConclusionExample example);

    int updateByExample(@Param("record") BaseReportBatchConclusion record, @Param("example") BaseReportBatchConclusionExample example);

    int updateByPrimaryKeySelective(BaseReportBatchConclusion record);

    int updateByPrimaryKey(BaseReportBatchConclusion record);
}