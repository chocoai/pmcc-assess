package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.basis.dao.project.ProjectNumberRecordDao;
import com.copower.pmcc.assess.service.data.DataNumberRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kings on 2019-1-14.
 */
@Service
public class ProjectNumberRecordService {
    @Autowired
    private ProjectNumberRecordDao projectNumberRecordDao;
    @Autowired
    private DataNumberRuleService dataNumberRuleService;

    /**
     * 获取报告文号
     * @param projectId 项目id
     * @param areaId 区域id
     * @param reportType 报告类型
     * @return
     */
    public String getReportNumber(Integer projectId,Integer areaId,Integer reportType){
        return "四川协和预评（2019）0001号";
    }

}
