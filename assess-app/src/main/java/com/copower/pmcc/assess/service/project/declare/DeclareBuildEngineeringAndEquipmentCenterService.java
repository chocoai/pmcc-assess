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
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    @Autowired
    private TaskExecutor executor;

    public Integer saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter) {
        return saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(declareBuildEngineeringAndEquipmentCenter, false);
    }

    public Integer saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter, boolean updateNull) {
        if (declareBuildEngineeringAndEquipmentCenter.getId() == null || declareBuildEngineeringAndEquipmentCenter.getId().intValue() == 0) {
            declareBuildEngineeringAndEquipmentCenter.setCreator(commonService.thisUserAccount());
            declareBuildEngineeringAndEquipmentCenterDao.addDeclareBuildEngineeringAndEquipmentCenter(declareBuildEngineeringAndEquipmentCenter);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareBuildEngineeringAndEquipmentCenter.class), declareBuildEngineeringAndEquipmentCenter.getId());
            return declareBuildEngineeringAndEquipmentCenter.getId();
        } else {
            declareBuildEngineeringAndEquipmentCenterDao.updateDeclareBuildEngineeringAndEquipmentCenter(declareBuildEngineeringAndEquipmentCenter, updateNull);
            return declareBuildEngineeringAndEquipmentCenter.getId();
        }
    }


    public List<DeclareBuildEngineeringAndEquipmentCenter> declareBuildEngineeringAndEquipmentCenterList(DeclareBuildEngineeringAndEquipmentCenter oo) {
        return declareBuildEngineeringAndEquipmentCenterDao.getDeclareBuildEngineeringAndEquipmentCenterList(oo);
    }

    public void deleteIds(String id) {
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        declareBuildEngineeringAndEquipmentCenterDao.deleteIds(ids);
    }

    public DeclareBuildEngineeringAndEquipmentCenter getDeclareBuildEngineeringAndEquipmentCenterById(Integer id) {
        return declareBuildEngineeringAndEquipmentCenterDao.getDeclareBuildEngineeringAndEquipmentCenterById(id);
    }

    @Deprecated
    public void removeDeclareBuildEngineeringAndEquipmentCenter(DeclareBuildEngineeringAndEquipmentCenter declareBuildEngineeringAndEquipmentCenter) {

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
        String type = copyData.getType();
        if (CollectionUtils.isNotEmpty(centerList)) {
            centerList.forEach(equipmentCenter -> {
                executor.execute(() -> {
                    Integer idA = equipmentCenter.getId();
                    BeanUtils.copyProperties(copyData, equipmentCenter);
                    equipmentCenter.setId(idA);
                    //把不属于从数据的设置为null  暂时添加这些
                    if (Objects.equal(type, DeclareBuildEquipmentInstall.class.getSimpleName())) {
                        equipmentCenter.setBuildEquipmentId(null);
                    }
                    if (Objects.equal(type, DeclareBuildEngineering.class.getSimpleName())) {
                        equipmentCenter.setBuildEngineeringId(null);
                    }
                    if (Objects.equal(type, DeclareRealtyRealEstateCert.class.getSimpleName())) {
                        equipmentCenter.setRealEstateId(null);
                    }
                    if (Objects.equal(type, DeclareRealtyLandCert.class.getSimpleName())) {
                        equipmentCenter.setLandId(null);
                    }
                    if (Objects.equal(type, DeclareRealtyHouseCert.class.getSimpleName())) {
                        equipmentCenter.setHouseId(null);
                    }
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
        //新经济指标2
        if (types.contains(MdEconomicIndicators.class.getSimpleName())) {
            if (restrain(equipmentCenter.getPlanDetailsId(), equipmentCenter.getType(), equipmentCenter.getIndicatorId())) {
                executor.execute(() -> {
//                    mdEconomicIndicatorsService.deleteById(equipmentCenter.getIndicatorId());
                    equipmentCenter.setIndicatorId(0);
                    saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                });
            }
        }
        //建筑工程施工许可证
        if (types.contains(DeclareBuildingConstructionPermit.class.getSimpleName())) {
            if (restrain(equipmentCenter.getPlanDetailsId(), equipmentCenter.getType(), equipmentCenter.getBuildingConstructionPermitId())) {
                executor.execute(() -> {
                    equipmentCenter.setBuildingConstructionPermitId(0);
                    saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                });
            }
        }
        //建设工程规划许可证
        if (types.contains(DeclareBuildingPermit.class.getSimpleName())) {
            if (restrain(equipmentCenter.getPlanDetailsId(), equipmentCenter.getType(), equipmentCenter.getBuildingPermitId())) {
                executor.execute(() -> {
                    equipmentCenter.setBuildingPermitId(0);
                    saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                });
            }
        }
        //建设用地规划许可证
        if (types.contains(DeclareLandUsePermit.class.getSimpleName())) {
            if (restrain(equipmentCenter.getPlanDetailsId(), equipmentCenter.getType(), equipmentCenter.getLandUsePermitId())) {
                executor.execute(() -> {
                    equipmentCenter.setLandUsePermitId(0);
                    saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                });
            }
        }
        //商品房预售许可证
        if (types.contains(DeclarePreSalePermit.class.getSimpleName())) {
            if (restrain(equipmentCenter.getPlanDetailsId(), equipmentCenter.getType(), equipmentCenter.getPreSalePermitId())) {
                executor.execute(() -> {
                    equipmentCenter.setPreSalePermitId(0);
                    saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                });
            }
        }
        //土地证
        if (types.contains(DeclareRealtyLandCert.class.getSimpleName())) {
            if (restrain(equipmentCenter.getPlanDetailsId(), equipmentCenter.getType(), equipmentCenter.getLandId())) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        equipmentCenter.setLandId(0);
                        saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                    }
                });
            }
        }
        //不动产
        if (types.contains(DeclareRealtyRealEstateCert.class.getSimpleName())) {
            if (restrain(equipmentCenter.getPlanDetailsId(), equipmentCenter.getType(), equipmentCenter.getRealEstateId())) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        equipmentCenter.setRealEstateId(0);
                        saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                    }
                });
            }
        }
        //房产证
        if (types.contains(DeclareRealtyHouseCert.class.getSimpleName())) {
            if (restrain(equipmentCenter.getPlanDetailsId(), equipmentCenter.getType(), equipmentCenter.getHouseId())) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        equipmentCenter.setHouseId(0);
                        saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                    }
                });
            }
        }
        if (types.contains(DeclareBuildEquipmentInstall.class.getSimpleName())) {
            if (restrain(equipmentCenter.getPlanDetailsId(), equipmentCenter.getType(), equipmentCenter.getBuildEquipmentId())) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        equipmentCenter.setBuildEquipmentId(0);
                        saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                    }
                });
            }
        }
        if (types.contains(DeclareBuildEngineering.class.getSimpleName())) {
            if (restrain(equipmentCenter.getPlanDetailsId(), equipmentCenter.getType(), equipmentCenter.getBuildEngineeringId())) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        equipmentCenter.setBuildEngineeringId(0);
                        saveAndUpdateDeclareBuildEngineeringAndEquipmentCenter(equipmentCenter);
                    }
                });
            }
        }
        return true;
    }


    /**
     * 一个校验方法
     *
     * @param planDetailsId
     * @param type
     * @param target
     * @return
     */
    private boolean restrain(Integer planDetailsId, String type, Integer target) {
        if (planDetailsId == null) {
            return false;
        }
        if (target == null) {
            return false;
        }
        if (StringUtils.isEmpty(type)) {
            return false;
        }
        return true;
    }

}
