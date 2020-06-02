package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.MyEntry;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareBuildEngineeringAndEquipmentCenterDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
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
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

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
    @Autowired
    private DeclareBuildingConstructionPermitService declareBuildingConstructionPermitService;
    @Autowired
    private DeclareBuildingPermitService declareBuildingPermitService;
    @Autowired
    private DeclareLandUsePermitService declareLandUsePermitService;
    @Autowired
    private DeclarePreSalePermitService declarePreSalePermitService;
    @Autowired
    private DeclareRealtyCheckListService declareRealtyCheckListService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private DeclareRecordService declareRecordService;

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
                    copyLicense(type, idA, copyId);
                });
            });
        }
    }

    /**
     * 许可证 拷贝
     *
     * @param masterType
     * @param masterId
     * @param copyId
     */
    private void copyLicense(String masterType, Integer masterId, Integer copyId) {
        //附件拷贝 提取的公共方法
        BiConsumer<String, MyEntry<Integer, Integer>> biConsumer = ((tableName, entryBiConsumer) -> {
            SysAttachmentDto example = new SysAttachmentDto();
            example.setTableName(tableName);
            example.setTableId(entryBiConsumer.getKey());
            SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
            sysAttachmentDto.setTableName(tableName);
            sysAttachmentDto.setTableId(entryBiConsumer.getValue());
            try {
                baseAttachmentService.copyFtpAttachments(example, sysAttachmentDto);
            } catch (Exception e) {
                baseService.writeExceptionInfo(e);
            }
        });
        List<DeclareBuildingConstructionPermit> declareBuildingConstructionPermits = declareBuildingConstructionPermitService.getDeclareBuildingConstructionPermitByMasterId(copyId);
        List<DeclareBuildingPermit> declareBuildingPermits = declareBuildingPermitService.getDeclareBuildingPermitByMasterId(copyId);
        List<DeclareLandUsePermit> declareLandUsePermits = declareLandUsePermitService.getDeclareLandUsePermitByMasterId(copyId);
        List<DeclarePreSalePermit> declarePreSalePermits = declarePreSalePermitService.getDeclarePreSalePermitByMasterId(copyId);
        List<DeclareRealtyCheckList> realtyCheckLists = declareRealtyCheckListService.getDeclareRealtyCheckLists(copyId);
        if (CollectionUtils.isNotEmpty(declareBuildingConstructionPermits)) {
            Iterator<DeclareBuildingConstructionPermit> iterator = declareBuildingConstructionPermits.iterator();
            while (iterator.hasNext()) {
                DeclareBuildingConstructionPermit permit = iterator.next();
                MyEntry<Integer, Integer> myEntry = new MyEntry<>(permit.getId(), 0);
                permit.setId(null);
                permit.setMasterId(masterId);
                permit.setMasterType(masterType);
                declareBuildingConstructionPermitService.saveAndUpdateDeclareBuildingConstructionPermit(permit);
                myEntry.setValue(permit.getId());
                biConsumer.accept(FormatUtils.entityNameConvertToTableName(DeclareBuildingConstructionPermit.class), myEntry);

            }
        }
        if (CollectionUtils.isNotEmpty(declareBuildingPermits)) {
            Iterator<DeclareBuildingPermit> iterator = declareBuildingPermits.iterator();
            while (iterator.hasNext()) {
                DeclareBuildingPermit buildingPermit = iterator.next();
                MyEntry<Integer, Integer> myEntry = new MyEntry<>(buildingPermit.getId(), 0);
                buildingPermit.setId(null);
                buildingPermit.setMasterId(masterId);
                buildingPermit.setMasterType(masterType);
                declareBuildingPermitService.saveAndUpdateDeclareBuildingPermit(buildingPermit);
                myEntry.setValue(buildingPermit.getId());
                biConsumer.accept(FormatUtils.entityNameConvertToTableName(DeclareBuildingPermit.class), myEntry);
            }
        }
        if (CollectionUtils.isNotEmpty(declareLandUsePermits)) {
            Iterator<DeclareLandUsePermit> iterator = declareLandUsePermits.iterator();
            while (iterator.hasNext()) {
                DeclareLandUsePermit usePermit = iterator.next();
                MyEntry<Integer, Integer> myEntry = new MyEntry<>(usePermit.getId(), 0);
                usePermit.setId(null);
                usePermit.setMasterId(masterId);
                usePermit.setMasterType(masterType);
                declareLandUsePermitService.saveAndUpdateDeclareLandUsePermit(usePermit);
                myEntry.setValue(usePermit.getId());
                biConsumer.accept(FormatUtils.entityNameConvertToTableName(DeclareLandUsePermit.class), myEntry);
            }
        }
        if (CollectionUtils.isNotEmpty(declarePreSalePermits)) {
            Iterator<DeclarePreSalePermit> iterator = declarePreSalePermits.iterator();
            while (iterator.hasNext()) {
                DeclarePreSalePermit salePermit = iterator.next();
                MyEntry<Integer, Integer> myEntry = new MyEntry<>(salePermit.getId(), 0);
                salePermit.setId(null);
                salePermit.setMasterId(masterId);
                salePermit.setMasterType(masterType);
                declarePreSalePermitService.saveAndUpdateDeclarePreSalePermit(salePermit);
                myEntry.setValue(salePermit.getId());
                biConsumer.accept(FormatUtils.entityNameConvertToTableName(DeclarePreSalePermit.class), myEntry);
            }
        }
        if (CollectionUtils.isNotEmpty(realtyCheckLists)) {
            Iterator<DeclareRealtyCheckList> iterator = realtyCheckLists.iterator();
            while (iterator.hasNext()) {
                DeclareRealtyCheckList checkList = iterator.next();
                MyEntry<Integer, Integer> myEntry = new MyEntry<>(checkList.getId(), 0);
                checkList.setId(null);
                checkList.setMarsterId(masterId);
                declareRealtyCheckListService.saveDeclareRealtyCheckList(checkList);
                myEntry.setValue(checkList.getId());
                biConsumer.accept(FormatUtils.entityNameConvertToTableName(DeclareRealtyCheckList.class), myEntry);
            }
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


    public List<Integer> getDataIds(DeclareRecord declareRecord, Class<?> c) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        DeclareBuildEngineeringAndEquipmentCenter center = new DeclareBuildEngineeringAndEquipmentCenter();
        if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
            center.setType(DeclareRealtyHouseCert.class.getSimpleName());
            center.setHouseId(declareRecord.getDataTableId());
        }
        if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
            center.setType(DeclareRealtyRealEstateCert.class.getSimpleName());
            center.setRealEstateId(declareRecord.getDataTableId());
        }
        if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
            center.setType(DeclareRealtyLandCert.class.getSimpleName());
            center.setLandId(declareRecord.getDataTableId());
        }
        if (StringUtils.isBlank(center.getType())) {
            return arrayList;
        }

        List<DeclareBuildEngineeringAndEquipmentCenter> declareBuildEngineeringAndEquipmentCenterList = declareBuildEngineeringAndEquipmentCenterDao.getDeclareBuildEngineeringAndEquipmentCenterList(center);
        if (CollectionUtils.isEmpty(declareBuildEngineeringAndEquipmentCenterList)) {
            return arrayList;
        }
        Iterator<DeclareBuildEngineeringAndEquipmentCenter> iterator = declareBuildEngineeringAndEquipmentCenterList.iterator();
        while (iterator.hasNext()) {
            DeclareBuildEngineeringAndEquipmentCenter equipmentCenter = iterator.next();
            if (c.getSimpleName().equalsIgnoreCase(DeclareBuildingConstructionPermit.class.getSimpleName())) {
                List<DeclareBuildingConstructionPermit> permitByMasterId = declareBuildingConstructionPermitService.getDeclareBuildingConstructionPermitByMasterId(equipmentCenter.getId());
                if (CollectionUtils.isNotEmpty(permitByMasterId)) {
                    List<Integer> collect = permitByMasterId.stream().map(oo -> oo.getId()).collect(Collectors.toList());
                    arrayList.addAll(collect);
                }
            }
            if (c.getSimpleName().equalsIgnoreCase(DeclareBuildingPermit.class.getSimpleName())) {
                List<DeclareBuildingPermit> permitByMasterId = declareBuildingPermitService.getDeclareBuildingPermitByMasterId(equipmentCenter.getId());
                if (CollectionUtils.isNotEmpty(permitByMasterId)) {
                    List<Integer> collect = permitByMasterId.stream().map(oo -> oo.getId()).collect(Collectors.toList());
                    arrayList.addAll(collect);
                }
            }
            if (c.getSimpleName().equalsIgnoreCase(DeclareLandUsePermit.class.getSimpleName())) {
                List<DeclareLandUsePermit> declareLandUsePermitByMasterId = declareLandUsePermitService.getDeclareLandUsePermitByMasterId(equipmentCenter.getId());
                if (CollectionUtils.isNotEmpty(declareLandUsePermitByMasterId)) {
                    List<Integer> collect = declareLandUsePermitByMasterId.stream().map(oo -> oo.getId()).collect(Collectors.toList());
                    arrayList.addAll(collect);
                }
            }
            if (c.getSimpleName().equalsIgnoreCase(DeclarePreSalePermit.class.getSimpleName())) {
                List<DeclarePreSalePermit> permitByMasterId = declarePreSalePermitService.getDeclarePreSalePermitByMasterId(equipmentCenter.getId());
                if (CollectionUtils.isNotEmpty(permitByMasterId)) {
                    List<Integer> collect = permitByMasterId.stream().map(oo -> oo.getId()).collect(Collectors.toList());
                    arrayList.addAll(collect);
                }
            }
            if (c.getSimpleName().equalsIgnoreCase(DeclareRealtyCheckList.class.getSimpleName())) {
                List<DeclareRealtyCheckList> declareRealtyCheckListList = declareRealtyCheckListService.getDeclareRealtyCheckLists(equipmentCenter.getId());
                if (CollectionUtils.isNotEmpty(declareRealtyCheckListList)) {
                    List<Integer> collect = declareRealtyCheckListList.stream().map(oo -> oo.getId()).collect(Collectors.toList());
                    arrayList.addAll(collect);
                }
            }
            if (c.getSimpleName().equalsIgnoreCase(DeclareRealtyLandCert.class.getSimpleName())) {
                if (equipmentCenter.getLandId() != null && equipmentCenter.getLandId() != 0) {
                    arrayList.add(equipmentCenter.getLandId());
                }
            }
            if (c.getSimpleName().equalsIgnoreCase(DeclareRealtyHouseCert.class.getSimpleName())) {
                if (equipmentCenter.getHouseId() != null && equipmentCenter.getHouseId() != 0) {
                    arrayList.add(equipmentCenter.getHouseId());
                }
            }
        }
        return arrayList;
    }


    public DeclareBuildEngineeringAndEquipmentCenter getDataByDeclareRecord(Integer declareRecordId) {
        DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(declareRecordId);
        DeclareBuildEngineeringAndEquipmentCenter center = new DeclareBuildEngineeringAndEquipmentCenter();
        if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
            center.setType(DeclareRealtyHouseCert.class.getSimpleName());
            center.setHouseId(declareRecord.getDataTableId());
        }
        if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
            center.setType(DeclareRealtyRealEstateCert.class.getSimpleName());
            center.setRealEstateId(declareRecord.getDataTableId());
        }
        if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
            center.setType(DeclareRealtyLandCert.class.getSimpleName());
            center.setLandId(declareRecord.getDataTableId());
        }

        List<DeclareBuildEngineeringAndEquipmentCenter> declareBuildEngineeringAndEquipmentCenterList = declareBuildEngineeringAndEquipmentCenterDao.getDeclareBuildEngineeringAndEquipmentCenterList(center);
        if (CollectionUtils.isNotEmpty(declareBuildEngineeringAndEquipmentCenterList)) {
            return declareBuildEngineeringAndEquipmentCenterList.get(0);
        }
        return center;

    }
}