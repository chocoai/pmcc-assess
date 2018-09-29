package com.copower.pmcc.assess.service.event.project;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.project.declare.*;
import com.copower.pmcc.bpm.api.dto.model.ProcessExecution;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by kings on 2018-9-29
 * 监听 在建工程和设备安装以及房产证 土地证 以及 不动产证
 */
@Component
public class DeclareRealtyEstateCertEvent extends ProjectTaskEvent {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DeclareInfoService declareInfoService;
    @Autowired
    private DeclareBuildEngineeringService declareBuildEngineeringService;
    @Autowired
    private DeclareBuildEquipmentInstallService declareBuildEquipmentInstallService;
    @Autowired
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    @Autowired
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService;

    @Override
    public void processFinishExecute(ProcessExecution processExecution) {
        super.processFinishExecute(processExecution);
        DeclareInfo declareInfo = declareInfoService.getDeclareInfoByProcessInsId(processExecution.getProcessInstanceId());
        if (declareInfo == null) {
            return;
        }
        declareBuildEngineeringService.eventWriteDeclareInfo(declareInfo);
        declareBuildEquipmentInstallService.eventWriteDeclareInfo(declareInfo);
        declareRealtyHouseCertService.eventWriteDeclareInfo(declareInfo);
        declareRealtyRealEstateCertService.eventWriteDeclareInfo(declareInfo);
        declareRealtyLandCertService.eventWriteDeclareInfo(declareInfo);

    }

    private String getValueFormMap(Map map, String key) {
        Object o = map.get(key);
        if (o == null) {
            return "";
        }
        return String.valueOf(o);
    }
}
