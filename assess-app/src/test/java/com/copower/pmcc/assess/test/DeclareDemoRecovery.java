package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.dal.basis.dao.base.BaseDataDicDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPhaseDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.*;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by zch on 2019/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContextTest.xml"})
public class DeclareDemoRecovery {

    @Autowired
    private DeclareRecordDao declareRecordDao;
    @Autowired
    private DeclareRealtyHouseCertDao declareRealtyHouseCertDao;
    @Autowired
    private DeclareRealtyRealEstateCertDao declareRealtyRealEstateCertDao;
    @Autowired
    private DeclareBuildEngineeringAndEquipmentCenterDao declareBuildEngineeringAndEquipmentCenterDao;
    @Autowired
    private DeclareRealtyLandCertDao declareRealtyLandCertDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BaseDataDicDao baseDataDicDao;
    @Autowired
    private ProjectPhaseDao projectPhaseDao;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    private int projectPhaseId = 118;

    //不动产证
    @org.junit.Test
    public void testRecoveryRealEstate() {
        List<BaseDataDic> baseDataDicList = baseDataDicDao.getAllBaseDataDicList();
        String creator = "admin";
        List<DeclareRecord> declareRecordList = declareRecordDao.getDeclareRecordList(new DeclareRecord());
        Map<DeclareRecord, Integer> integerMap = Maps.newHashMap();
        List<DeclareRecord> list = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            for (DeclareRecord declareRecord : declareRecordList) {
                if (StringUtils.isNotEmpty(declareRecord.getDataTableName())) {
                    if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class), declareRecord.getDataTableName())) {
                        if (declareRecord.getDataTableId() != null) {
                            Integer id = declareRecord.getDataTableId();
                            DeclareRealtyRealEstateCert realtyRealEstateCertById = declareRealtyRealEstateCertDao.getDeclareRealtyRealEstateCertById(id);
                            if (realtyRealEstateCertById == null) {
                                list.add(declareRecord);
                            }
                        }

                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder(8);
        if (CollectionUtils.isNotEmpty(list)) {
            for (DeclareRecord declareRecord : list) {
                stringBuilder.append("INSERT into ").append(declareRecord.getDataTableName()).append("(id,creator)").append(" ");
                stringBuilder.append("values(").append(declareRecord.getDataTableId()).append(",").append("'" + creator + "'").append(")");
                try {
                    jdbcTemplate.execute(stringBuilder.toString());
                } catch (DataAccessException e) {
                    //异常不再抛出,因为里面有重复的主键
                }
                stringBuilder.delete(0, stringBuilder.toString().length());
                integerMap.put(declareRecord, declareRecord.getDataTableId());
            }
        }
        if (!integerMap.isEmpty()) {
            integerMap.forEach((declareRecord, id) -> {
                DeclareRealtyRealEstateCert oo = declareRealtyRealEstateCertDao.getDeclareRealtyRealEstateCertById(id);
                if (oo != null) {

                    oo.setUseEndDate(declareRecord.getLandUseEndDate());
                    oo.setHousingStructure(declareRecord.getHousingStructure());
                    oo.setEvidenceArea(declareRecord.getFloorArea());
                    if (CollectionUtils.isNotEmpty(baseDataDicList)) {
                        for (BaseDataDic baseDataDic : baseDataDicList) {
                            //
                            if (StringUtils.isNotEmpty(declareRecord.getPublicSituation())) {
                                if (Objects.equal(baseDataDic.getName(), declareRecord.getPublicSituation())) {
                                    oo.setPublicSituation(baseDataDic.getId());
                                }
                            }
                            if (StringUtils.isNotEmpty(declareRecord.getCertUse())) {
                                if (Objects.equal(baseDataDic.getName(), declareRecord.getCertUse())) {
                                    oo.setHouseCertUse(baseDataDic.getId());
                                }
                            }
                            if (StringUtils.isNotEmpty(declareRecord.getNature())) {
                                if (Objects.equal(baseDataDic.getName(), declareRecord.getNature())) {
                                    oo.setNature(baseDataDic.getId());
                                }
                            }
                            //
                            if (StringUtils.isNotEmpty(declareRecord.getLandRightType())) {
                                if (Objects.equal(baseDataDic.getName(), declareRecord.getLandRightType())) {
                                    oo.setLandRightType(baseDataDic.getId());
                                }
                            }
                            //
                            if (StringUtils.isNotEmpty(declareRecord.getLandRightNature())) {
                                if (Objects.equal(baseDataDic.getName(), declareRecord.getLandRightNature())) {
                                    oo.setLandRightNature(baseDataDic.getId());
                                }
                            }
                        }
                    }


                    oo.setRegistrationDate(declareRecord.getRegistrationDate());
                    oo.setCertName(declareRecord.getName());
                    oo.setOwnership(declareRecord.getOwnership());
                    oo.setBeLocated(declareRecord.getSeat());
                    oo.setStreetNumber(declareRecord.getStreetNumber());
                    oo.setAttachedNumber(declareRecord.getAttachedNumber());
                    oo.setBuildingNumber(declareRecord.getBuildingNumber());
                    oo.setUnit(declareRecord.getUnit());
                    oo.setFloor(declareRecord.getFloor());
                    oo.setRoomNumber(declareRecord.getRoomNumber());
                    oo.setUseRightArea(declareRecord.getLandUseRightArea());
                    oo.setTerminationDate(declareRecord.getLandUseEndDate());

                    oo.setDistrict(declareRecord.getDistrict());
                    oo.setCity(declareRecord.getCity());
                    oo.setProvince(declareRecord.getProvince());
                    oo.setEnable("master");
                    ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
                    projectPlanDetails.setProjectId(declareRecord.getProjectId());
                    projectPlanDetails.setProjectPhaseId(projectPhaseId);
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsDao.getListObject(projectPlanDetails);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        oo.setPlanDetailsId(projectPlanDetailsList.get(0).getId());
                    }
                    declareRealtyRealEstateCertDao.updateDeclareRealtyRealEstateCert(oo) ;
                }
            });
        }
    }

    //土地证
    @org.junit.Test
    public void testRecoveryLand() {
        List<BaseDataDic> baseDataDicList = baseDataDicDao.getAllBaseDataDicList();
        String creator = "admin";
        List<DeclareRecord> declareRecordList = declareRecordDao.getDeclareRecordList(new DeclareRecord());
        Map<DeclareRecord, Integer> integerMap = Maps.newHashMap();
        List<DeclareRecord> list = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            for (DeclareRecord declareRecord : declareRecordList) {
                if (StringUtils.isNotEmpty(declareRecord.getDataTableName())) {
                    if (Objects.equal(FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class), declareRecord.getDataTableName())) {
                        if (declareRecord.getDataTableId() != null) {
                            Integer id = declareRecord.getDataTableId();
                            DeclareRealtyLandCert declareRealtyLandCert = declareRealtyLandCertDao.getDeclareRealtyLandCertById(id);
                            if (declareRealtyLandCert == null) {
                                list.add(declareRecord);
                            }
                        }

                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder(8);
        if (CollectionUtils.isNotEmpty(list)) {
            for (DeclareRecord declareRecord : list) {
                stringBuilder.append("INSERT into ").append(declareRecord.getDataTableName()).append("(id,creator)").append(" ");
                stringBuilder.append("values(").append(declareRecord.getDataTableId()).append(",").append("'" + creator + "'").append(")");
                try {
                    jdbcTemplate.execute(stringBuilder.toString());
                } catch (DataAccessException e) {
                    //异常不再抛出,因为里面有重复的主键
                }
                stringBuilder.delete(0, stringBuilder.toString().length());
                integerMap.put(declareRecord, declareRecord.getDataTableId());
            }
        }
        if (!integerMap.isEmpty()) {
            integerMap.forEach((declareRecord, id) -> {
                DeclareRealtyLandCert oo = declareRealtyLandCertDao.getDeclareRealtyLandCertById(id);
                if (oo != null) {
                    oo.setRegistrationDate(declareRecord.getRegistrationDate());
                    oo.setLandCertName(declareRecord.getName());
                    oo.setOwnership(declareRecord.getOwnership());
                    if (CollectionUtils.isNotEmpty(baseDataDicList)) {
                        for (BaseDataDic baseDataDic : baseDataDicList) {
                            //
                            if (StringUtils.isNotEmpty(declareRecord.getPublicSituation())) {
                                if (Objects.equal(baseDataDic.getName(), declareRecord.getPublicSituation())) {
                                    oo.setPublicSituation(baseDataDic.getId());
                                }
                            }
                            //
                            if (StringUtils.isNotEmpty(declareRecord.getLandCertUse())) {
                                if (Objects.equal(baseDataDic.getName(), declareRecord.getLandCertUse())) {
                                    oo.setCertUse(baseDataDic.getId());
                                }
                            }
                            //
                            if (StringUtils.isNotEmpty(declareRecord.getLandRightType())) {
                                if (Objects.equal(baseDataDic.getName(), declareRecord.getLandRightType())) {
                                    oo.setLandRightType(baseDataDic.getId());
                                }
                            }
                            //
                            if (StringUtils.isNotEmpty(declareRecord.getLandRightNature())) {
                                if (Objects.equal(baseDataDic.getName(), declareRecord.getLandRightNature())) {
                                    oo.setLandRightNature(baseDataDic.getId());
                                }
                            }
                        }
                    }
                    oo.setBeLocated(declareRecord.getSeat());
                    oo.setStreetNumber(declareRecord.getStreetNumber());
                    oo.setAttachedNumber(declareRecord.getAttachedNumber());
                    oo.setBuildingNumber(declareRecord.getBuildingNumber());
                    oo.setUnit(declareRecord.getUnit());
                    oo.setFloor(declareRecord.getFloor());
                    oo.setRoomNumber(declareRecord.getRoomNumber());
                    oo.setUseRightArea(declareRecord.getFloorArea());
                    oo.setTerminationDate(declareRecord.getLandUseEndDate());

                    oo.setDistrict(declareRecord.getDistrict());
                    oo.setCity(declareRecord.getCity());
                    oo.setProvince(declareRecord.getProvince());

                    DeclareBuildEngineeringAndEquipmentCenter center = new DeclareBuildEngineeringAndEquipmentCenter();
                    center.setLandId(id);
                    List<DeclareBuildEngineeringAndEquipmentCenter> centerList = declareBuildEngineeringAndEquipmentCenterDao.getDeclareBuildEngineeringAndEquipmentCenterList(center);
                    if (CollectionUtils.isNotEmpty(centerList)) {
                        if (centerList.stream().anyMatch(po -> po.getHouseId() != null)) {
                            int houseId = centerList.stream().filter(po -> po.getHouseId() != null).findFirst().get().getHouseId();
                            DeclareRealtyHouseCert declareRealtyHouseCert = declareRealtyHouseCertDao.getDeclareRealtyHouseCertById(houseId);
                            if (declareRealtyHouseCert != null) {
                                oo.setPlanDetailsId(declareRealtyHouseCert.getPlanDetailsId());
                                if (StringUtils.isNotEmpty(declareRealtyHouseCert.getProvince())) {
                                    oo.setProvince(declareRealtyHouseCert.getProvince());
                                }
                                if (StringUtils.isNotEmpty(declareRealtyHouseCert.getCity())) {
                                    oo.setCity(declareRealtyHouseCert.getCity());
                                }
                                if (StringUtils.isNotEmpty(declareRealtyHouseCert.getDistrict())) {
                                    oo.setDistrict(declareRealtyHouseCert.getDistrict());
                                }
                            }
                        }
                    }
                    oo.setEnable("branch");
                    ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
                    projectPlanDetails.setProjectId(declareRecord.getProjectId());
                    projectPlanDetails.setProjectPhaseId(projectPhaseId);
                    List<ProjectPlanDetails> projectPlanDetailsList = projectPlanDetailsDao.getListObject(projectPlanDetails);
                    if (CollectionUtils.isNotEmpty(projectPlanDetailsList)) {
                        oo.setPlanDetailsId(projectPlanDetailsList.get(0).getId());
                    }
                    declareRealtyLandCertDao.updateDeclareRealtyLandCert(oo);
                }
            });
        }
    }

}
