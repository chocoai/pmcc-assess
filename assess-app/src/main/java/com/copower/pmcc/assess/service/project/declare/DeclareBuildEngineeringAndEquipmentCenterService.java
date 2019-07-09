package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareBuildEngineeringAndEquipmentCenterDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/22 10:27
 * @Description:申报中间表
 */
@Service
public class DeclareBuildEngineeringAndEquipmentCenterService {
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
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    @Autowired
    private TaskExecutor executor;

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
                    if (CollectionUtils.isNotEmpty(declareBuildEconomicIndicatorsService.getEntityListByPid(indicatorId))) {
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

    public void deleteIds(String id) {
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        List<String> types = Lists.newArrayList();
        types.add(DeclareBuildEconomicIndicatorsCenter.class.getSimpleName());
        types.add(DeclareBuildingConstructionPermit.class.getSimpleName());
        types.add(DeclareBuildingPermit.class.getSimpleName());
        types.add(DeclareLandUsePermit.class.getSimpleName());
        types.add(DeclarePreSalePermit.class.getSimpleName());
        types.add(DeclareRealtyLandCert.class.getSimpleName());
        types.add(DeclareRealtyRealEstateCert.class.getSimpleName());
        types.add(DeclareEconomicIndicatorsHead.class.getSimpleName());
        ids.stream().forEachOrdered(integer -> {
            DeclareBuildEngineeringAndEquipmentCenter center = getDeclareBuildEngineeringAndEquipmentCenterById(integer);
            if (center != null) {
                executor.execute(() -> {
                    deleteByType2(types, center);
                });
            }
        });
        executor.execute(() -> {
            declareBuildEngineeringAndEquipmentCenterDao.deleteIds(ids);
        });

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
    @Deprecated
    public void copy(String ids, Integer copyId, String type) {
        if (StringUtils.isEmpty(ids)) {
            return;
        }
        if (StringUtils.isEmpty(type)) {
            return;
        }
        if (copyId == null) {
            return;
        }
        List<Integer> integerList2 = FormatUtils.transformString2Integer(ids);
        List<DeclareBuildEngineeringAndEquipmentCenter> centerList = Lists.newArrayList();
        integerList2.forEach(integer -> {
            DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
            query.setType(type);
            if (Objects.equal(DeclareBuildEngineering.class.getSimpleName(), type)) {
                DeclareBuildEngineering declareBuildEngineering = declareBuildEngineeringService.getDeclareBuildEngineeringById(integer);
                query.setBuildEngineeringId(declareBuildEngineering.getId());
                List<DeclareBuildEngineeringAndEquipmentCenter> centerList2 = declareBuildEngineeringAndEquipmentCenterList(query);
                if (CollectionUtils.isNotEmpty(centerList2)) {
                    centerList.addAll(centerList2);
                }
            }
            if (Objects.equal(DeclareBuildEquipmentInstall.class.getSimpleName(), type)) {
                DeclareBuildEquipmentInstall declareBuildEquipmentInstall = declareBuildEquipmentInstallService.getDeclareBuildEquipmentInstallById(integer);
                query.setBuildEquipmentId(declareBuildEquipmentInstall.getId());
                List<DeclareBuildEngineeringAndEquipmentCenter> centerList2 = declareBuildEngineeringAndEquipmentCenterList(query);
                if (CollectionUtils.isNotEmpty(centerList2)) {
                    centerList.addAll(centerList2);
                }
            }
            if (Objects.equal(DeclareRealtyHouseCert.class.getSimpleName(), type)) {
                DeclareRealtyHouseCert declareRealtyHouseCert = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(integer);
                query.setHouseId(declareRealtyHouseCert.getId());
                List<DeclareBuildEngineeringAndEquipmentCenter> centerList2 = declareBuildEngineeringAndEquipmentCenterList(query);
                if (CollectionUtils.isNotEmpty(centerList2)) {
                    centerList.addAll(centerList2);
                }
            }
        });
        List<Integer> integerList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(centerList)) {
            centerList.forEach(oo -> integerList.add(oo.getId()));
        }
        if (CollectionUtils.isNotEmpty(integerList)) {
            copy(StringUtils.join(integerList, ","), copyId);
        }
    }

    public void copy(String ids, Integer copyId) {
        if (StringUtils.isEmpty(ids)) {
            return;
        }
        if (copyId == null) {
            return;
        }
        DeclareBuildEngineeringAndEquipmentCenter copyData = getDeclareBuildEngineeringAndEquipmentCenterById(copyId);
        if (copyData == null) {
            return;
        }
        List<Integer> integerList = FormatUtils.transformString2Integer(ids);
        List<DeclareBuildEngineeringAndEquipmentCenter> centerList = Lists.newArrayList();
        integerList.forEach(integer -> {
            DeclareBuildEngineeringAndEquipmentCenter equipmentCenter = getDeclareBuildEngineeringAndEquipmentCenterById(integer);
            if (equipmentCenter != null) {
                centerList.add(equipmentCenter);
            }
        });
        if (CollectionUtils.isNotEmpty(centerList)) {
            centerList.forEach(equipmentCenter -> {
                executor.execute(() -> {
                    Integer idA = equipmentCenter.getId();
                    BeanUtils.copyProperties(copyData, equipmentCenter);
                    equipmentCenter.setId(idA);
                    //把不属于从数据的设置为null
                    equipmentCenter.setBuildEngineeringId(null);
                    equipmentCenter.setBuildEquipmentId(null);
                    equipmentCenter.setHouseId(null);
                    declareBuildEngineeringAndEquipmentCenterDao.updateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                });
            });
        }
    }

    public void deleteByType(String type, Integer centerId) {
        if (StringUtils.isEmpty(type) || centerId == null) {
            return;
        }
        DeclareBuildEngineeringAndEquipmentCenter equipmentCenter = getDeclareBuildEngineeringAndEquipmentCenterById(centerId);
        if (equipmentCenter == null) {
            return;
        }
        deleteByType2(Lists.newArrayList(type), equipmentCenter);
    }

    /**
     * 根据类型删除子从表
     *
     * @param types
     * @param equipmentCenter
     * @return
     */
    private boolean deleteByType2(List<String> types, DeclareBuildEngineeringAndEquipmentCenter equipmentCenter) {
        if (CollectionUtils.isEmpty(types) || equipmentCenter == null) {
            return false;
        }
        //新经济指标
        if (types.contains(DeclareEconomicIndicatorsHead.class.getSimpleName())) {
            DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
            query.setPlanDetailsId(equipmentCenter.getPlanDetailsId());
            query.setType(equipmentCenter.getType());
            query.setIndicatorId(equipmentCenter.getIndicatorId());
            executor.execute(() -> {
                List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterList(query);
                if (CollectionUtils.isNotEmpty(centerList)) {
                    //当只存在一个的时候，把中间表包含经济指标的id设为null,并且删除其原表的数据
                    //当有多个关联关系的时候则把中间表包含经济指标的id设为null
                    if (centerList.size() == 1) {
//                        declareEconomicIndicatorsHeadService.removeDeclareEconomicIndicatorsHeadById(equipmentCenter.getIndicatorId());
                    }
                    equipmentCenter.setIndicatorId(0);
                    saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                }
            });
        }
        //经济指标
        if (types.contains(DeclareBuildEconomicIndicatorsCenter.class.getSimpleName())) {
            DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
            query.setPlanDetailsId(equipmentCenter.getPlanDetailsId());
            query.setType(equipmentCenter.getType());
            query.setIndicatorId(equipmentCenter.getIndicatorId());
            executor.execute(() -> {
                List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterList(query);
                if (CollectionUtils.isNotEmpty(centerList)) {
                    //当只存在一个的时候，把中间表包含经济指标的id设为null,并且删除其原表的数据
                    //当有多个关联关系的时候则把中间表包含经济指标的id设为null
                    if (centerList.size() == 1) {
                        DeclareBuildEconomicIndicatorsCenter deleteIndicatorsCenter = new DeclareBuildEconomicIndicatorsCenter();
                        deleteIndicatorsCenter.setId(equipmentCenter.getIndicatorId());
//                        declareBuildEconomicIndicatorsCenterService.removeDeclareBuildEconomicIndicatorsCenter(deleteIndicatorsCenter);
                    }
                    equipmentCenter.setIndicatorId(0);
                    saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                }
            });
        }
        //建筑工程施工许可证
        if (types.contains(DeclareBuildingConstructionPermit.class.getSimpleName())) {
            DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
            query.setPlanDetailsId(equipmentCenter.getPlanDetailsId());
            query.setType(equipmentCenter.getType());
            query.setBuildingConstructionPermitId(equipmentCenter.getBuildingConstructionPermitId());
            executor.execute(() -> {
                List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterList(query);
                if (CollectionUtils.isNotEmpty(centerList)) {
                    if (centerList.size() == 1) {
                        DeclareBuildingConstructionPermit delete = new DeclareBuildingConstructionPermit();
                        delete.setId(equipmentCenter.getBuildingConstructionPermitId());
//                        declareBuildingConstructionPermitService.removeDeclareBuildingConstructionPermit(delete);
                    }
                    equipmentCenter.setBuildingConstructionPermitId(0);
                    saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                }
            });
        }
        //建设工程规划许可证
        if (types.contains(DeclareBuildingPermit.class.getSimpleName())) {
            DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
            query.setPlanDetailsId(equipmentCenter.getPlanDetailsId());
            query.setType(equipmentCenter.getType());
            query.setBuildingPermitId(equipmentCenter.getBuildingPermitId());
            executor.execute(() -> {
                List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterList(query);
                if (CollectionUtils.isNotEmpty(centerList)) {
                    if (centerList.size() == 1) {
                        DeclareBuildingPermit delete = new DeclareBuildingPermit();
                        delete.setId(equipmentCenter.getBuildingPermitId());
//                        declareBuildingPermitService.removeDeclareBuildingPermit(delete);
                    }
                    equipmentCenter.setBuildingPermitId(0);
                    saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                }
            });
        }
        //建设用地规划许可证
        if (types.contains(DeclareLandUsePermit.class.getSimpleName())) {
            DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
            query.setPlanDetailsId(equipmentCenter.getPlanDetailsId());
            query.setType(equipmentCenter.getType());
            query.setLandUsePermitId(equipmentCenter.getLandUsePermitId());
            executor.execute(() -> {
                List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterList(query);
                if (CollectionUtils.isNotEmpty(centerList)) {
                    if (centerList.size() == 1) {
                        DeclareLandUsePermit delete = new DeclareLandUsePermit();
                        delete.setId(equipmentCenter.getLandUsePermitId());
//                        declareLandUsePermitService.removeDeclareLandUsePermit(delete);
                    }
                    equipmentCenter.setLandUsePermitId(0);
                    saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                }
            });
        }
        //商品房预售许可证
        if (types.contains(DeclarePreSalePermit.class.getSimpleName())) {
            DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
            query.setPlanDetailsId(equipmentCenter.getPlanDetailsId());
            query.setType(equipmentCenter.getType());
            query.setPreSalePermitId(equipmentCenter.getPreSalePermitId());
            executor.execute(() -> {
                List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterList(query);
                if (CollectionUtils.isNotEmpty(centerList)) {
                    if (centerList.size() == 1) {
                        DeclarePreSalePermit delete = new DeclarePreSalePermit();
                        delete.setId(equipmentCenter.getPreSalePermitId());
//                        declarePreSalePermitService.removeDeclarePreSalePermit(delete);
                    }
                    equipmentCenter.setPreSalePermitId(0);
                    saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                }
            });
        }
        //土地证
        if (types.contains(DeclareRealtyLandCert.class.getSimpleName())) {
            DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
            query.setPlanDetailsId(equipmentCenter.getPlanDetailsId());
            query.setType(equipmentCenter.getType());
            query.setLandId(equipmentCenter.getLandId());
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterList(query);
                    if (CollectionUtils.isNotEmpty(centerList)) {
                        if (centerList.size() == 1) {
                            DeclareRealtyLandCert delete = new DeclareRealtyLandCert();
                            delete.setId(equipmentCenter.getLandId());
//                            declareRealtyLandCertService.removeDeclareRealtyLandCert(delete);
                        }
                        equipmentCenter.setLandId(0);
                        saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                    }
                }
            });
        }
        //不动产
        if (types.contains(DeclareRealtyRealEstateCert.class.getSimpleName())) {
            DeclareBuildEngineeringAndEquipmentCenter query = new DeclareBuildEngineeringAndEquipmentCenter();
            query.setPlanDetailsId(equipmentCenter.getPlanDetailsId());
            query.setType(equipmentCenter.getType());
            query.setRealEstateId(equipmentCenter.getRealEstateId());
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterList(query);
                    if (CollectionUtils.isNotEmpty(centerList)) {
                        if (centerList.size() == 1) {
                            DeclareRealtyRealEstateCert delete = new DeclareRealtyRealEstateCert();
                            delete.setId(equipmentCenter.getRealEstateId());
//                            declareRealtyRealEstateCertService.removeDeclareRealtyRealEstateCert(delete);
                        }
                        equipmentCenter.setRealEstateId(0);
                        saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                    }
                }
            });
        }
        return true;
    }

}
