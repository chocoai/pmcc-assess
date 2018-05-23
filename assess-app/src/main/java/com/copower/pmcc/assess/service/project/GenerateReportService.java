package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.GenerateReportDao;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.entity.GenerateReport;
import com.copower.pmcc.assess.dal.entity.GenerateReportRecord;
import com.copower.pmcc.assess.dto.output.project.GenerateReportRecordVo;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kings on 2018-5-23.
 */
@Service
public class GenerateReportService {
    @Autowired
    private GenerateReportDao generateReportDao;
    @Autowired
    private DeclareRecordService declareRecordService;

    /**
     * 初始化用于生成报告记录信息
     *
     * @param projectId
     * @param planId
     */
    @Transactional
    public void initGenerateReportRecord(Integer projectId, Integer planId) {
        int count = generateReportDao.getGenerateReportRecordCount(projectId, planId);
        if (count > 0) return;

        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordByProjectId(projectId);
        if (CollectionUtils.isNotEmpty(declareRecords)) {
            GenerateReportRecord generateReportRecord = null;
            for (DeclareRecord declareRecord : declareRecords) {
                generateReportRecord = new GenerateReportRecord();
                BeanUtils.copyProperties(declareRecord,generateReportRecord);
                generateReportRecord.setProjectId(projectId);
                generateReportRecord.setPlanId(planId);
                generateReportDao.addGenerateReportRecord(generateReportRecord);
            }
        }
    }

    /**
     * 获取报告记录信息
     * @param projectId
     * @param planId
     * @return
     */
    public List<GenerateReportRecordVo> getGenerateReportRecordList(Integer projectId, Integer planId){
        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordByProjectId(projectId);
        List<GenerateReportRecordVo> voList= Lists.newArrayList();
        return LangUtils.transform(declareRecords, p -> {
            GenerateReportRecordVo generateReportRecordVo = new GenerateReportRecordVo();
            BeanUtils.copyProperties(p,generateReportRecordVo);
            return generateReportRecordVo;
        });
    }

    /**
     * 生成报告
     */
    public void generate(){

    }
}
