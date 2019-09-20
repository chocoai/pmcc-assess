package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.dao.project.ProjectNumberRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.DataNumberRule;
import com.copower.pmcc.assess.dal.basis.entity.ProjectNumberRecord;
import com.copower.pmcc.assess.dal.basis.entity.ProjectTakeNumber;
import com.copower.pmcc.assess.service.ProjectTakeNumberService;
import com.copower.pmcc.assess.service.data.DataNumberRuleService;
import com.copower.pmcc.assess.service.event.BaseProcessEvent;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public class ProjectTakeNumberServiceEvent extends BaseProcessEvent {
    @Autowired
    private ProjectTakeNumberService projectTakeNumberService;
    @Autowired
    private ProjectNumberRecordDao projectNumberRecordDao;
    @Autowired
    private DataNumberRuleService dataNumberRuleService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;


    @Override
    public void processFinishExecute(ProcessExecution processExecution) throws Exception {
        ProjectTakeNumber data = projectTakeNumberService.getDataByProcessInsId(processExecution.getProcessInstanceId());
        data.setStatus(ProcessStatusEnum.FINISH.getValue());

        //获取到该报告类型的最大号
        //增加一条文号记录
        DataNumberRule numberRule = dataNumberRuleService.getDataNumberRule(data.getReportType());
        int number = 1;
        if (numberRule.getStartNumber() != null)
            number = numberRule.getStartNumber();
        int year = DateUtils.getYear(new Date());

        //一个分组下的reportType
        List<DataNumberRule> numberRuleGroup = dataNumberRuleService.getDataNumberRuleByGroup(numberRule.getGroupName());
        List<Integer> reportTypes = LangUtils.transform(numberRuleGroup, o -> o.getReportType());
        //去重
        List<Integer> reportTypeList = generateCommonMethod.removeDuplicate(reportTypes);
        List<ProjectNumberRecord> projectNumberRecords = Lists.newArrayList();
        for (Integer reportTypeValue : reportTypeList) {
            ProjectNumberRecord projectNumberRecord = new ProjectNumberRecord();
            projectNumberRecord.setReportType(reportTypeValue);
            List<ProjectNumberRecord> numberRecords = projectNumberRecordDao.getProjectNumberRecordList(projectNumberRecord);
            if (CollectionUtils.isNotEmpty(numberRecords))
                projectNumberRecords.addAll(numberRecords);
        }
        if (CollectionUtils.isNotEmpty(projectNumberRecords)) {
            if (projectNumberRecords.size() >= 2) {
                //排序
                Ordering<ProjectNumberRecord> ordering = Ordering.from((o1, o2) -> {
                    return (o2.getNumber().compareTo(o1.getNumber()));
                });
                projectNumberRecords.sort(ordering);
            }
            //最大号
            number = projectNumberRecords.get(0).getNumber();
        }

        String reportNumber = numberRule.getNumberRule().replaceAll("\\{prefix\\}", numberRule.getPrefix())
                .replaceAll("\\{year\\}", String.valueOf(year));
        reportNumber = reportNumber.replaceAll("\\{number\\}", StringUtils.leftPad(String.valueOf(++number), numberRule.getFigures(), '0'));

        ProjectNumberRecord projectNumberRecord = new ProjectNumberRecord();
        projectNumberRecord.setProjectId(data.getProjectId());
        projectNumberRecord.setAreaId(0);
        projectNumberRecord.setReportType(data.getReportType());
        projectNumberRecord.setYear(year);
        projectNumberRecord.setNumber(++number);
        projectNumberRecord.setNumberValue(reportNumber);
        projectNumberRecord.setCreator(data.getCreator());
        projectNumberRecordDao.addProjectNumberRecord(projectNumberRecord);
        data.setNumberRecordId(projectNumberRecord.getId());
        projectTakeNumberService.editData(data);
    }
}
