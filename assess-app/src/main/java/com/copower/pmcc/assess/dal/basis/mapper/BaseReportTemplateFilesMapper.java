package com.copower.pmcc.assess.dal.basis.mapper;

import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplateFiles;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportTemplateFilesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseReportTemplateFilesMapper {
    int countByExample(BaseReportTemplateFilesExample example);

    int deleteByExample(BaseReportTemplateFilesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseReportTemplateFiles record);

    int insertSelective(BaseReportTemplateFiles record);

    List<BaseReportTemplateFiles> selectByExample(BaseReportTemplateFilesExample example);

    BaseReportTemplateFiles selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseReportTemplateFiles record, @Param("example") BaseReportTemplateFilesExample example);

    int updateByExample(@Param("record") BaseReportTemplateFiles record, @Param("example") BaseReportTemplateFilesExample example);

    int updateByPrimaryKeySelective(BaseReportTemplateFiles record);

    int updateByPrimaryKey(BaseReportTemplateFiles record);
}