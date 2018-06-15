package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.constant.AssessFieldNameConstant;
import com.copower.pmcc.assess.dal.dao.DeclareRecordDao;
import com.copower.pmcc.assess.dal.dao.FormConfigureDao;
import com.copower.pmcc.assess.dal.entity.DeclareInfo;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.service.project.DeclareInfoService;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.apache.commons.collections.CollectionUtils;
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
    private ProcessControllerComponent processControllerComponent;
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
                if (map.containsKey(AssessFieldNameConstant.DECLARE_RECORD_NAME)) {
                    declareRecord.setName(String.valueOf(map.get(AssessFieldNameConstant.DECLARE_RECORD_NAME)));
                }
                if (map.containsKey(AssessFieldNameConstant.DECLARE_RECORD_PROVINCE)) {
                    declareRecord.setProvince(String.valueOf(map.get(AssessFieldNameConstant.DECLARE_RECORD_PROVINCE)));
                }
                if (map.containsKey(AssessFieldNameConstant.DECLARE_RECORD_CITY)) {
                    declareRecord.setCity(String.valueOf(map.get(AssessFieldNameConstant.DECLARE_RECORD_CITY)));
                }
                if (map.containsKey(AssessFieldNameConstant.DECLARE_RECORD_DISTRICT)) {
                    declareRecord.setDistrict(String.valueOf(map.get(AssessFieldNameConstant.DECLARE_RECORD_DISTRICT)));
                }
                if (map.containsKey(AssessFieldNameConstant.DECLARE_RECORD_FLOOR_AREA)) {
                    declareRecord.setFloorArea(new BigDecimal(String.valueOf(map.get(AssessFieldNameConstant.DECLARE_RECORD_FLOOR_AREA))));
                }
                if (map.containsKey(AssessFieldNameConstant.DECLARE_RECORD_OWNERSHIP)) {
                    declareRecord.setOwnership(String.valueOf(map.get(AssessFieldNameConstant.DECLARE_RECORD_OWNERSHIP)));
                }
                declareRecord.setCreator(processControllerComponent.getThisUser());
                declareRecordDao.addDeclareRecord(declareRecord);
            }
        }
    }
}
