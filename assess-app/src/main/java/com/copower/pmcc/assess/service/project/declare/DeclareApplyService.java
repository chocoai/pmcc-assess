package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareApplyDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareApply;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kings on 2018-5-10.
 */
@Service
public class DeclareApplyService {
    @Autowired
    private DeclareApplyDao declareApplyDao;
    @Autowired
    private CommonService commonService;
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

    /**
     * 获取申报信息by processInsId
     *
     * @param processInsId
     * @return
     */
    public DeclareApply getDeclareApplyByProcessInsId(String processInsId) {
        if (StringUtils.isBlank(processInsId)) return null;
        return declareApplyDao.getDeclareApplyByProcessInsId(processInsId);
    }

    /**
     * 保存申报数据
     *
     * @param declareApply
     */
    public void saveDeclareApply(DeclareApply declareApply) throws BusinessException {
        if (declareApply.getId() != null && declareApply.getId() > 0) {
            declareApplyDao.updateDeclareApply(declareApply);
        } else {
            declareApply.setCreator(commonService.thisUserAccount());
            declareApplyDao.addDeclareApply(declareApply);
        }
    }

    /**
     * 数据写入到申报记录表中
     * @param declareApply
     */
    public void writeToDeclareRecord(DeclareApply declareApply){
        declareBuildEngineeringService.eventWriteDeclareApply(declareApply);
        declareBuildEquipmentInstallService.eventWriteDeclareApply(declareApply);
        declareRealtyHouseCertService.eventWriteDeclareApply(declareApply);
        declareRealtyRealEstateCertService.eventWriteDeclareApply(declareApply);
        declareRealtyLandCertService.eventWriteDeclareApply(declareApply);
    }

}
