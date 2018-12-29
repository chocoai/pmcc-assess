package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareBuildEngineeringAndEquipmentCenterDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/22 10:27
 * @Description:在建工程中间表
 */
@Service
public class DeclareBuildEngineeringAndEquipmentCenterService {
    @Autowired
    private DeclareBuildEconomicIndicatorsCenterService declareBuildEconomicIndicatorsCenterService;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterDao declareBuildEngineeringAndEquipmentCenterDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private DeclareBuildEngineeringService declareBuildEngineeringService;
    @Autowired
    private DeclareBuildEquipmentInstallService declareBuildEquipmentInstallService;
    @Autowired
    private DeclareBuildEconomicIndicatorsService declareBuildEconomicIndicatorsService;
    @Autowired
    private DeclareBuildingConstructionPermitService declareBuildingConstructionPermitService;
    @Autowired
    private DeclareBuildingPermitService declareBuildingPermitService;
    @Autowired
    private DeclareLandUsePermitService declareLandUsePermitService;
    @Autowired
    private DeclarePreSalePermitService declarePreSalePermitService;
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    @Autowired
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter) {
        if (declareBuildEngineeringAndEquipmentCenter.getId() == null || declareBuildEngineeringAndEquipmentCenter.getId().intValue() == 0) {
            declareBuildEngineeringAndEquipmentCenter.setCreator(commonService.thisUserAccount());
            Integer id = declareBuildEngineeringAndEquipmentCenterDao.addDeclareBuildEngineeringAndEquipmentCenter(declareBuildEngineeringAndEquipmentCenter);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareBuildEngineeringAndEquipmentCenter.class), id);
            return id;
        } else {
            declareBuildEngineeringAndEquipmentCenterDao.updateDeclareBuildEngineeringAndEquipmentCenter(declareBuildEngineeringAndEquipmentCenter);
            return null;
        }
    }


    public List<DeclareBuildEconomicIndicators> getDeclareBuildEconomicIndicatorsList(String type, Integer buildEngineeringId, Integer buildEquipmentId) {
        List<DeclareBuildEngineeringAndEquipmentCenter> declareBuildEngineeringAndEquipmentCenterList = declareBuildEngineeringAndEquipmentCenterDao.getDeclareBuildEngineeringAndEquipmentCenterList(type, buildEngineeringId, buildEquipmentId);
        List<DeclareBuildEconomicIndicators> economicIndicators = new ArrayList<DeclareBuildEconomicIndicators>(10);
        if (!ObjectUtils.isEmpty(declareBuildEngineeringAndEquipmentCenterList)) {
            for (DeclareBuildEngineeringAndEquipmentCenter buildEngineeringAndEquipmentCenter : declareBuildEngineeringAndEquipmentCenterList) {
                Integer indicatorId = buildEngineeringAndEquipmentCenter.getIndicatorId();
                if (indicatorId != null) {
                    if (!ObjectUtils.isEmpty(declareBuildEconomicIndicatorsService.getEntityListByPid(indicatorId))) {
                        economicIndicators.addAll(declareBuildEconomicIndicatorsService.getEntityListByPid(indicatorId));
                    }
                }
            }
        }
        return economicIndicators;
    }

    public List<DeclareBuildEngineeringAndEquipmentCenter> declareBuildEngineeringAndEquipmentCenterList(DeclareBuildEngineeringAndEquipmentCenter oo) {
        return declareBuildEngineeringAndEquipmentCenterDao.getDeclareBuildEngineeringAndEquipmentCenterList(oo);
    }

    public DeclareBuildEngineeringAndEquipmentCenter getDeclareBuildEngineeringAndEquipmentCenterById(Integer id) {
        return declareBuildEngineeringAndEquipmentCenterDao.getDeclareBuildEngineeringAndEquipmentCenterById(id);
    }

    public void removeDeclareBuildEngineeringAndEquipmentCenter(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter) {
        declareBuildEngineeringAndEquipmentCenterDao.removeDeclareBuildEngineeringAndEquipmentCenter(declareBuildEngineeringAndEquipmentCenter);
    }

    /**
     * 处理copy 数据
     *
     * @param ids
     * @param copyId
     * @param type
     */
    public void copy(String ids, Integer copyId, String type) {
        if (StringUtils.isEmpty(ids)) {
            return;
        }
        for (String id : ids.split(",")) {
            if (StringUtils.isNotBlank(type)) {
                //在建工程（土建）
                DeclareBuildEngineeringAndEquipmentCenter centerA = getDeclareBuildEngineeringAndEquipmentCenterById(copyId);
                List<DeclareBuildEngineeringAndEquipmentCenter> centerList = null;
                if (Objects.equal(DeclareBuildEngineering.class.getSimpleName(), type)) {
                    DeclareBuildEngineering declareBuildEngineering = declareBuildEngineeringService.getDeclareBuildEngineeringById(Integer.parseInt(id));
                    DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
                    query.setBuildEngineeringId(declareBuildEngineering.getId());
                    query.setType(DeclareBuildEngineering.class.getSimpleName());
                    centerList = declareBuildEngineeringAndEquipmentCenterList(query);
                }
                if (Objects.equal(DeclareBuildEquipmentInstall.class.getSimpleName(), type)) {
                    DeclareBuildEquipmentInstall declareBuildEquipmentInstall = declareBuildEquipmentInstallService.getDeclareBuildEquipmentInstallById(Integer.parseInt(id));
                    DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
                    query.setBuildEquipmentId(declareBuildEquipmentInstall.getId());
                    query.setType(DeclareBuildEquipmentInstall.class.getSimpleName());
                    centerList = declareBuildEngineeringAndEquipmentCenterList(query);
                }
                //在建工程 (设备安装)
                if (!ObjectUtils.isEmpty(centerList)) {
                    DeclareBuildEngineeringAndEquipmentCenter centerB = centerList.get(0);
                    if (centerB != null) {
                        int cid = centerB.getId();
                        centerA.setBuildEngineeringId(null);
                        centerA.setBuildEquipmentId(null);
                        BeanUtils.copyProperties(centerA, centerB);
                        centerB.setId(cid);
                        declareBuildEngineeringAndEquipmentCenterDao.updateDeclareBuildEngineeringAndEquipmentCenter(centerB);
                    }
                }
            }
        }
    }

    public boolean deleteByType(String type, Integer dataId, Integer centerId) throws Exception {
        if (dataId == null || StringUtils.isEmpty(type) || centerId == null) {
            throw new Exception("null 参数");
        }
        DeclareBuildEngineeringAndEquipmentCenter equipmentCenter = getDeclareBuildEngineeringAndEquipmentCenterById(centerId);
        if (equipmentCenter == null) {
            return false;
        }
        DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
        query.setPlanDetailsId(equipmentCenter.getPlanDetailsId());
        query.setType(equipmentCenter.getType());
        List<DeclareBuildEngineeringAndEquipmentCenter> centerList = null;


        //经济指标
        if (DeclareBuildEconomicIndicatorsCenter.class.getSimpleName().equals(type)) {
            query.setIndicatorId(dataId);
            centerList = declareBuildEngineeringAndEquipmentCenterList(query);
            if (ObjectUtils.isEmpty(centerList)) {
                return false;
            }
            //当只存在一个的时候，把中间表包含经济指标的id设为null,并且删除其原表的数据
            //当有多个关联关系的时候则把中间表包含经济指标的id设为null
            if (centerList.size() == 1) {
                DeclareBuildEconomicIndicatorsCenter deleteIndicatorsCenter = new DeclareBuildEconomicIndicatorsCenter();
                deleteIndicatorsCenter.setId(centerList.get(0).getIndicatorId());
                declareBuildEconomicIndicatorsCenterService.removeDeclareBuildEconomicIndicatorsCenter(deleteIndicatorsCenter);
            }
            for (DeclareBuildEngineeringAndEquipmentCenter center : centerList) {
                center.setIndicatorId(0);
                saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(center);
            }
        }
        //建筑工程施工许可证
        if (DeclareBuildingConstructionPermit.class.getSimpleName().equals(type)) {
            query.setBuildingConstructionPermitId(dataId);
            centerList = declareBuildEngineeringAndEquipmentCenterList(query);
            if (ObjectUtils.isEmpty(centerList)) {
                return false;
            }
            if (centerList.size() == 1) {
                DeclareBuildingConstructionPermit delete = new DeclareBuildingConstructionPermit();
                delete.setId(centerList.get(0).getBuildingConstructionPermitId());
                declareBuildingConstructionPermitService.removeDeclareBuildingConstructionPermit(delete);
            }
            for (DeclareBuildEngineeringAndEquipmentCenter center : centerList) {
                center.setBuildingConstructionPermitId(0);
                saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(center);
            }
        }
        //建设工程规划许可证
        if (DeclareBuildingPermit.class.getSimpleName().equals(type)) {
            query.setBuildingPermitId(dataId);
            centerList = declareBuildEngineeringAndEquipmentCenterList(query);
            if (ObjectUtils.isEmpty(centerList)) {
                return false;
            }
            if (centerList.size() == 1) {
                DeclareBuildingPermit delete = new DeclareBuildingPermit();
                delete.setId(centerList.get(0).getBuildingPermitId());
                declareBuildingPermitService.removeDeclareBuildingPermit(delete);
            }
            for (DeclareBuildEngineeringAndEquipmentCenter center : centerList) {
                center.setBuildingPermitId(0);
                saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(center);
            }
        }
        //建设用地规划许可证
        if (DeclareLandUsePermit.class.getSimpleName().equals(type)) {
            query.setLandUsePermitId(dataId);
            centerList = declareBuildEngineeringAndEquipmentCenterList(query);
            if (ObjectUtils.isEmpty(centerList)) {
                return false;
            }
            if (centerList.size() == 1) {
                DeclareLandUsePermit delete = new DeclareLandUsePermit();
                delete.setId(centerList.get(0).getLandUsePermitId());
                declareLandUsePermitService.removeDeclareLandUsePermit(delete);
            }
            for (DeclareBuildEngineeringAndEquipmentCenter center : centerList) {
                center.setLandUsePermitId(0);
                saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(center);
            }
        }
        //商品房预售许可证
        if (DeclarePreSalePermit.class.getSimpleName().equals(type)) {
            query.setPreSalePermitId(dataId);
            centerList = declareBuildEngineeringAndEquipmentCenterList(query);
            if (ObjectUtils.isEmpty(centerList)) {
                return false;
            }
            if (centerList.size() == 1) {
                DeclarePreSalePermit delete = new DeclarePreSalePermit();
                delete.setId(centerList.get(0).getPreSalePermitId());
                declarePreSalePermitService.removeDeclarePreSalePermit(delete);
            }
            for (DeclareBuildEngineeringAndEquipmentCenter center : centerList) {
                center.setPreSalePermitId(0);
                saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(center);
            }
        }
        //土地证
        if (DeclareRealtyLandCert.class.getSimpleName().equals(type)){
            query.setLandId(dataId);
            centerList = declareBuildEngineeringAndEquipmentCenterList(query);
            if (ObjectUtils.isEmpty(centerList)) {
                return false;
            }
            if (centerList.size() == 1){
                DeclareRealtyLandCert delete = new DeclareRealtyLandCert();
                delete.setId(centerList.get(0).getLandId());
                declareRealtyLandCertService.removeDeclareRealtyLandCert(delete);
            }
            for (DeclareBuildEngineeringAndEquipmentCenter center : centerList) {
                center.setLandId(0);
                saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(center);
            }
        }
        //不动产
        if (DeclareRealtyRealEstateCert.class.getSimpleName().equals(type)){
            query.setRealEstateId(dataId);
            centerList = declareBuildEngineeringAndEquipmentCenterList(query);
            if (ObjectUtils.isEmpty(centerList)) {
                return false;
            }
            if (centerList.size() == 1){
                DeclareRealtyRealEstateCert delete = new DeclareRealtyRealEstateCert();
                delete.setId(centerList.get(0).getRealEstateId());
                declareRealtyRealEstateCertService.removeDeclareRealtyRealEstateCert(delete);
            }
            for (DeclareBuildEngineeringAndEquipmentCenter center : centerList) {
                center.setRealEstateId(0);
                saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(center);
            }
        }
        return true;
    }

}
