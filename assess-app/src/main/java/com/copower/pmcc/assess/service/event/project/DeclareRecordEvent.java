package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.constant.AssessFieldNameConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareUseClassifyDao;
import com.copower.pmcc.assess.dal.basis.dao.base.FormConfigureDao;
import com.copower.pmcc.assess.service.base.BaseFormService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.declare.*;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DeclareInfoService declareInfoService;
    @Autowired
    private FormConfigureDao formConfigureDao;
    @Autowired
    private DeclareRecordDao declareRecordDao;
    @Autowired
    private DeclareUseClassifyDao declareUseClassifyDao;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private BaseFormService baseFormService;
    @Autowired
    private DeclareBuildEngineeringService declareBuildEngineeringService;
    @Autowired
    private DeclareBuildEquipmentInstallService declareBuildEquipmentInstallService;
    @Autowired
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    @Autowired
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService ;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);
        DeclareInfo declareInfo = declareInfoService.getDeclareInfoByProcessInsId(processExecution.getProcessInstanceId());
        if (declareInfo == null) return;
        declareBuildEngineeringService.eventWriteDeclareInfo(declareInfo);
        declareBuildEquipmentInstallService.eventWriteDeclareInfo(declareInfo);
        declareRealtyHouseCertService.eventWriteDeclareInfo(declareInfo);
        declareRealtyRealEstateCertService.eventWriteDeclareInfo(declareInfo);
        declareRealtyLandCertService.eventWriteDeclareInfo(declareInfo);
        List<DeclareUseClassify> declareUseClassifyList = declareUseClassifyDao.getDeclareUseClassifyList(declareInfo.getProjectId(), declareInfo.getPlanDetailsId());
        if (CollectionUtils.isEmpty(declareUseClassifyList)) return;
        for (DeclareUseClassify declareUseClassify : declareUseClassifyList) {
            BaseProjectClassify projectClassify = baseProjectClassifyService.getCacheProjectClassifyById(declareUseClassify.getProjectClassifyId());
            if (projectClassify != null && projectClassify.getFormModuleId() != null) {
                BaseFormModule baseFormModule = baseFormService.getBaseFormModule(projectClassify.getFormModuleId());
                if (baseFormModule != null) {
                    String sql = String.format("select * from %s where %s=%s", baseFormModule.getTableName(), baseFormModule.getForeignKeyName(), declareInfo.getPlanDetailsId());
                    List<Map<String, Object>> mapList = formConfigureDao.getObjectList(sql);
                    if (CollectionUtils.isNotEmpty(mapList)) {
                        for (Map<String, Object> map : mapList) {
                            //记录申报数据
                            DeclareRecord declareRecord = new DeclareRecord();
                            declareRecord.setProjectClassifyId(declareUseClassify.getProjectClassifyId());
                            declareRecord.setProjectId(declareInfo.getProjectId());
                            //申报数据特定字段记录
                            if (map.containsKey(AssessFieldNameConstant.DECLARE_RECORD_NAME)) {
                                declareRecord.setName(getValueFormMap(map,AssessFieldNameConstant.DECLARE_RECORD_NAME));
                            }
                            if (map.containsKey(AssessFieldNameConstant.DECLARE_RECORD_PROVINCE)) {
                                declareRecord.setProvince(getValueFormMap(map,AssessFieldNameConstant.DECLARE_RECORD_PROVINCE));
                            }
                            if (map.containsKey(AssessFieldNameConstant.DECLARE_RECORD_CITY)) {
                                declareRecord.setCity(getValueFormMap(map,AssessFieldNameConstant.DECLARE_RECORD_CITY));
                            }
                            if (map.containsKey(AssessFieldNameConstant.DECLARE_RECORD_DISTRICT)) {
                                declareRecord.setDistrict(getValueFormMap(map,AssessFieldNameConstant.DECLARE_RECORD_DISTRICT));
                            }
                            if (map.containsKey(AssessFieldNameConstant.DECLARE_RECORD_FLOOR_AREA)) {
                                String floorAreaString = getValueFormMap(map,AssessFieldNameConstant.DECLARE_RECORD_FLOOR_AREA);
                                if (StringUtils.isNotBlank(floorAreaString))
                                    declareRecord.setFloorArea(new BigDecimal(floorAreaString));
                            }
                            if (map.containsKey(AssessFieldNameConstant.DECLARE_RECORD_OWNERSHIP)) {
                                declareRecord.setOwnership(getValueFormMap(map,AssessFieldNameConstant.DECLARE_RECORD_OWNERSHIP));
                            }
                            declareRecord.setInventoryContentKey(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT);
                            declareRecord.setCreator(processControllerComponent.getThisUser());
                            declareRecordDao.addDeclareRecord(declareRecord);
                        }
                    }
                }
            }
        }
    }

    private String getValueFormMap(Map map,String key){
        Object o = map.get(key);
        if(o==null) return "";
        return String.valueOf(o);
    }
}
