package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.constant.AssessFieldNameConstant;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.constant.AssessTableNameConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.dao.*;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.FormConfigureDetailDto;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.DeclareInfoService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.SurveyCaseStudyDetailService;
import com.copower.pmcc.assess.service.project.SurveyCaseStudyService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-5-21.
 */
@Component
public class DeclareRecordEvent extends ProjectTaskEvent {
    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private DeclareInfoService declareInfoService;
    @Autowired
    private FormConfigureDao formConfigureDao;
    @Autowired
    private DeclareRecordDao declareRecordDao;


    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);
        DeclareInfo declareInfo = declareInfoService.getDeclareInfoByProcessInsId(processExecution.getProcessInstanceId());
        if (declareInfo == null) return;
        String sql = String.format("select * from %s where main_id=%s", declareInfo.getDynamicTableName(), declareInfo.getId());
        List<Map<String, Object>> mapList = formConfigureDao.getObjectList(sql);
        if (CollectionUtils.isNotEmpty(mapList)) {
            for (Map<String, Object> map : mapList) {
                //记录申报数据
                DeclareRecord declareRecord = new DeclareRecord();
                declareRecord.setProjectId(declareInfo.getProjectId());
                //申报数据特定字段记录
                if (map.containsKey(AssessFieldNameConstant.PMCC_ASSESS_DECLARE_RECORD_NAME)) {
                    declareRecord.setName(String.valueOf(map.get(AssessFieldNameConstant.PMCC_ASSESS_DECLARE_RECORD_NAME)));
                }
                if (map.containsKey(AssessFieldNameConstant.PMCC_ASSESS_DECLARE_RECORD_PROVINCE)) {
                    declareRecord.setProvince(String.valueOf(map.get(AssessFieldNameConstant.PMCC_ASSESS_DECLARE_RECORD_PROVINCE)));
                }
                if (map.containsKey(AssessFieldNameConstant.PMCC_ASSESS_DECLARE_RECORD_CITY)) {
                    declareRecord.setCity(String.valueOf(map.get(AssessFieldNameConstant.PMCC_ASSESS_DECLARE_RECORD_CITY)));
                }
                if (map.containsKey(AssessFieldNameConstant.PMCC_ASSESS_DECLARE_RECORD_DISTRICT)) {
                    declareRecord.setDistrict(String.valueOf(map.get(AssessFieldNameConstant.PMCC_ASSESS_DECLARE_RECORD_DISTRICT)));
                }
                if (map.containsKey(AssessFieldNameConstant.PMCC_ASSESS_DECLARE_RECORD_FLOOR_AREA)) {
                    declareRecord.setFloorArea(new BigDecimal(String.valueOf(map.get(AssessFieldNameConstant.PMCC_ASSESS_DECLARE_RECORD_FLOOR_AREA))));
                }
                if (map.containsKey(AssessFieldNameConstant.PMCC_ASSESS_DECLARE_RECORD_OWNERSHIP)) {
                    declareRecord.setOwnership(String.valueOf(map.get(AssessFieldNameConstant.PMCC_ASSESS_DECLARE_RECORD_OWNERSHIP)));
                }
                declareRecord.setCreator(serviceComponent.getThisUser());
                declareRecordDao.addDeclareRecord(declareRecord);
            }
        }
    }
}
