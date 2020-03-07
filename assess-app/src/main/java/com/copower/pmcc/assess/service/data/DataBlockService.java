package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.*;
import com.copower.pmcc.assess.dal.basis.dao.data.DataBlockDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingVo;
import com.copower.pmcc.assess.dto.output.cases.CaseUnitHuxingVo;
import com.copower.pmcc.assess.dto.output.data.DataBlockVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 10:01
 * @Description:基础版块维护
 */
@Service
public class DataBlockService {
    @Autowired
    private DataBlockDao dataBlockDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateLandStateDao basicEstateLandStateDao;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private CaseEstateParkingService caseEstateParkingService;
    @Autowired
    private BasicEstateParkingService basicEstateParkingService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private CaseBuildingService caseBuildingService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private CaseUnitService caseUnitService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private CaseUnitHuxingService caseUnitHuxingService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private CaseHouseService caseHouseService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private CaseHouseTradingService caseHouseTradingService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private CaseHouseRoomService caseHouseRoomService;
    @Autowired
    private CaseHouseCorollaryEquipmentService caseHouseCorollaryEquipmentService;
    @Autowired
    private CaseHouseDamagedDegreeService caseHouseDamagedDegreeService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private BasicHouseCorollaryEquipmentService basicHouseCorollaryEquipmentService;
    @Autowired
    private CaseHouseRoomDecorateService caseHouseRoomDecorateService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private BasicHouseCaseSummaryService basicHouseCaseSummaryService;
    @Autowired
    private CaseEstateService caseEstateService;
    @Autowired
    private CaseEstateLandStateService caseEstateLandStateService;
    @Autowired
    private BasicEstateDao basicEstateDao;
    @Autowired
    private BasicBuildingDao basicBuildingDao;
    @Autowired
    private BasicUnitDao basicUnitDao;
    @Autowired
    private BasicHouseDao basicHouseDao;
    @Autowired
    private BasicEstateParkingDao basicEstateParkingDao;
    @Autowired
    private BasicUnitHuxingDao basicUnitHuxingDao;
    @Autowired
    private BasicHouseTradingDao basicHouseTradingDao;
    @Autowired
    private BasicHouseRoomDecorateDao basicHouseRoomDecorateDao;
    @Autowired
    private BasicHouseCorollaryEquipmentDao basicHouseCorollaryEquipmentDao;
    @Autowired
    private BasicHouseDamagedDegreeDao basicHouseDamagedDegreeDao;
    @Autowired
    private BasicHouseDamagedDegreeDetailDao basicHouseDamagedDegreeDetailDao;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateDataBlock(DataBlock dataBlock) {
        if (dataBlock.getId() == null || dataBlock.getId().intValue() == 0) {
            dataBlock.setCreator(commonService.thisUserAccount());
            try {
                return dataBlockDao.addDataBlock(dataBlock);
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
                return null;
            }
        } else {
            dataBlockDao.updateDataBlock(dataBlock);
            return null;
        }
    }

    public DataBlock getDataBlockById(Integer id) {
        if (id == null) {
            logger.error("null point");
            return null;
        }
        return dataBlockDao.getDataBlockById(id);
    }

    public BootstrapTableVo getDataBlockListVos(String province, String city, String district, String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataBlockVo> vos = dataBlockVos(province, city, district, name);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DataBlockVo> dataBlockVos(DataBlock dataBlock) {
        List<DataBlock> dataBlocks = dataBlockDao.getDataBlockList(dataBlock);
        List<DataBlockVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataBlocks)) {
            for (DataBlock landLevel : dataBlocks) {
                vos.add(getDataBlockVo(landLevel));
            }
        }
        return vos;
    }

    public List<DataBlockVo> dataBlockVos(String province, String city, String district, String name) {
        List<DataBlock> dataBlocks = dataBlockDao.getDataBlockList(province, city, district, name);
        List<DataBlockVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataBlocks)) {
            for (DataBlock landLevel : dataBlocks) {
                vos.add(getDataBlockVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDataBlock(DataBlock dataBlock) {
        dataBlockDao.removeDataBlock(dataBlock);
    }

    public DataBlockVo getDataBlockVo(DataBlock dataBlock) {
        DataBlockVo vo = new DataBlockVo();
        BeanUtils.copyProperties(dataBlock, vo);
        if (StringUtils.isNotBlank(dataBlock.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(dataBlock.getProvince()));
        }
        if (StringUtils.isNotBlank(dataBlock.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(dataBlock.getCity()));
        }
        if (StringUtils.isNotBlank(dataBlock.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(dataBlock.getDistrict()));
        }
        return vo;
    }

    /**
     * 根据区域获取版块信息
     *
     * @param province
     * @param city
     * @param distric
     * @return
     */
    public List<DataBlock> getDataBlockListByArea(String province, String city, String distric) {
        if (StringUtils.isBlank(province) || StringUtils.isBlank(city))
            return null;
        DataBlock dataBlock = new DataBlock();
        dataBlock.setProvince(province);
        dataBlock.setCity(city);
        if (StringUtils.isNotBlank(distric))
            dataBlock.setDistrict(distric);
        return dataBlockDao.getDataBlockList(dataBlock);
    }

    /**
     * 版块是否已存在
     *
     * @param province
     * @param city
     * @param distric
     * @param blockName
     * @return
     */
    public Boolean isExistBlock(String province, String city, String distric, String blockName) {
        DataBlock dataBlock = new DataBlock();
        dataBlock.setProvince(province);
        dataBlock.setCity(city);
        if (StringUtils.isNotBlank(distric))
            dataBlock.setDistrict(distric);
        dataBlock.setName(blockName);
        List<DataBlock> blockList = dataBlockDao.getDataBlockList(dataBlock);
        return blockList.size() > 0;
    }

    //将案例库的数据拷贝到Basic，包含对应的附件
    @Transactional(rollbackFor = Exception.class)
    public void caseAllToBasic() throws Exception {
        //拷贝 楼盘 楼栋 单元 房屋 拷贝房屋时注意将 houseCaseSummary数据处理掉
        List<CaseEstate> caseEstateList = caseEstateService.getCaseEstateList(new CaseEstate());
        if(CollectionUtils.isNotEmpty(caseEstateList)){
            for (CaseEstate caseEstate : caseEstateList) {
                BasicEstate basicEstate = new BasicEstate();
                BeanUtils.copyProperties(caseEstate, basicEstate);
                basicEstate.setId(null);
                basicEstate.setBisCase(true);
                basicEstateDao.addBasicEstate(basicEstate);

                //附件拷贝
                SysAttachmentDto example = new SysAttachmentDto();
                example.setTableId(caseEstate.getId());
                example.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstate.class));
                SysAttachmentDto attachmentDto = new SysAttachmentDto();
                attachmentDto.setTableId(basicEstate.getId());
                attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
                baseAttachmentService.copyFtpAttachments(example, attachmentDto);

                CaseEstateLandState queryLandState = new CaseEstateLandState();
                queryLandState.setEstateId(caseEstate.getId());
                List<CaseEstateLandState> oldCaseEstateLandStateList = caseEstateLandStateService.getCaseEstateLandStateList(queryLandState);
                if (!ObjectUtils.isEmpty(oldCaseEstateLandStateList)) {
                    BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
                    BeanUtils.copyProperties(oldCaseEstateLandStateList.get(0), basicEstateLandState);
                    basicEstateLandState.setId(null);
                    basicEstateLandState.setEstateId(basicEstate.getId());
                    basicEstateLandStateDao.addBasicEstateLandState(basicEstateLandState);
                }

                CaseEstateTagging oldCaseEstateTagging = new CaseEstateTagging();
                oldCaseEstateTagging.setTableId(caseEstate.getId());
                oldCaseEstateTagging.setType(BasicFormClassifyEnum.ESTATE.getKey());
                List<CaseEstateTagging> oldCaseEstateTaggingList = caseEstateTaggingService.getCaseEstateTaggingList(oldCaseEstateTagging);
                if (!ObjectUtils.isEmpty(oldCaseEstateTaggingList)) {
                    BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
                    BeanUtils.copyProperties(oldCaseEstateTaggingList.get(0), basicEstateTagging);
                    basicEstateTagging.setId(null);
                    basicEstateTagging.setTableId(basicEstate.getId());
                    basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
                }

                try {
                    CaseEstateParking caseParking = new CaseEstateParking();
                    caseParking.setEstateId(caseEstate.getId());
                    List<CaseEstateParking> oldCaseEstateParkings = caseEstateParkingService.getEstateParkingList(caseParking);
                    if (!ObjectUtils.isEmpty(oldCaseEstateParkings)) {
                        for (CaseEstateParking oldCaseEstateParking : oldCaseEstateParkings) {
                            BasicEstateParking basicEstateParking = new BasicEstateParking();
                            BeanUtils.copyProperties(oldCaseEstateParking, basicEstateParking);
                            basicEstateParking.setId(null);
                            basicEstateParking.setEstateId(basicEstate.getId());
                            Integer estateParkingId = basicEstateParkingDao.saveBasicEstateParking(basicEstateParking);

                            example = new SysAttachmentDto();
                            example.setTableId(oldCaseEstateParking.getId());
                            example.setTableName(FormatUtils.entityNameConvertToTableName(CaseEstateParking.class));
                            attachmentDto = new SysAttachmentDto();
                            attachmentDto.setTableId(estateParkingId);
                            attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateParking.class));
                            baseAttachmentService.copyFtpAttachments(example, attachmentDto);
                        }
                    }
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }

                StringBuilder sqlBuilder = new StringBuilder();
                SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
                HashMap<String, String> map = Maps.newHashMap();
                map.put("estate_id", String.valueOf(basicEstate.getId()));
                map.put("creator", caseEstate.getCreator());
                synchronousDataDto.setFieldDefaultValue(map);
                synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
                synchronousDataDto.setWhereSql("estate_id=" + caseEstate.getId());
                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseEstateNetwork.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//通信网络sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseEstateSupply.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供应信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseMatchingTraffic.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//交通信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseMatchingMedical.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//医养信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseMatchingMaterial.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//原材料信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseMatchingLeisurePlace.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//休闲场所信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseMatchingFinance.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//金融服务信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseMatchingEnvironment.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//环境因素信息sql

                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseMatchingEducation.class));
                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class));
                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//教育信息sql

                ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql

                //-----------处理该楼盘下楼栋信息
                CaseBuilding whereCaseBuilding=new CaseBuilding();
                whereCaseBuilding.setEstateId(caseEstate.getId());
                List<CaseBuilding> caseBuildingList = caseBuildingService.getCaseBuildingList(whereCaseBuilding);
                if(CollectionUtils.isNotEmpty(caseBuildingList)){
                    for (CaseBuilding caseBuilding : caseBuildingList) {
                        BasicBuilding basicBuilding = new BasicBuilding();
                        BeanUtils.copyProperties(caseBuilding, basicBuilding);
                        basicBuilding.setId(null);
                        basicBuilding.setEstateId(basicEstate.getId());
                        basicBuilding.setBisCase(true);
                        basicBuilding.setFullName(basicEstate.getName()+basicBuilding.getBuildingNumber());
                        basicBuildingDao.addBasicBuilding(basicBuilding);

                        oldCaseEstateTagging = new CaseEstateTagging();
                        oldCaseEstateTagging.setTableId(caseBuilding.getId());
                        oldCaseEstateTagging.setType(BasicFormClassifyEnum.BUILDING.getKey());
                        oldCaseEstateTaggingList = caseEstateTaggingService.getCaseEstateTaggingList(oldCaseEstateTagging);
                        if (!ObjectUtils.isEmpty(oldCaseEstateTaggingList)) {
                            BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
                            BeanUtils.copyProperties(oldCaseEstateTaggingList.get(0), basicEstateTagging);
                            basicEstateTagging.setId(null);
                            basicEstateTagging.setTableId(basicBuilding.getId());
                            basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
                        }

                        sqlBuilder = new StringBuilder();
                        synchronousDataDto = new SynchronousDataDto();
                        map = Maps.newHashMap();
                        map.put("building_id", String.valueOf(basicBuilding.getId()));
                        map.put("creator", caseBuilding.getCreator());
                        synchronousDataDto.setFieldDefaultValue(map);
                        synchronousDataDto.setWhereSql("building_id=" + caseBuilding.getId());
                        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseBuildingOutfit.class));
                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class));
                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋外装sql

                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseBuildingMaintenance.class));
                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class));
                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//维护结构sql

                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseBuildingSurface.class));
                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class));
                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//层面结构sql

                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseBuildingFunction.class));
                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class));
                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//建筑功能sql

                        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql

                        //------------处理该楼栋下的单元信息
                        CaseUnit whereCaseUnit=new CaseUnit();
                        whereCaseUnit.setBuildingId(caseBuilding.getId());
                        List<CaseUnit> caseUnitList = caseUnitService.getCaseUnitList(whereCaseUnit);
                        if(CollectionUtils.isNotEmpty(caseUnitList)){
                            for (CaseUnit caseUnit : caseUnitList) {
                                BasicUnit basicUnit = new BasicUnit();
                                BeanUtils.copyProperties(caseUnit, basicUnit);
                                basicUnit.setId(null);
                                basicUnit.setEstateId(basicEstate.getId());
                                basicUnit.setBuildingId(basicBuilding.getId());
                                basicUnit.setBisCase(true);
                                basicUnit.setFullName(basicBuilding.getFullName()+basicUnit.getUnitNumber());
                                basicUnitDao.addBasicUnit(basicUnit);

                                //附件拷贝
                                example = new SysAttachmentDto();
                                example.setTableId(caseUnit.getId());
                                example.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnit.class));
                                attachmentDto = new SysAttachmentDto();
                                attachmentDto.setTableId(basicUnit.getId());
                                attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
                                baseAttachmentService.copyFtpAttachments(example, attachmentDto);

                                oldCaseEstateTagging = new CaseEstateTagging();
                                oldCaseEstateTagging.setTableId(caseUnit.getId());
                                oldCaseEstateTagging.setType(BasicFormClassifyEnum.UNIT.getKey());
                                List<CaseEstateTagging> oldCaseUnitTaggingList = caseEstateTaggingService.getCaseEstateTaggingList(oldCaseEstateTagging);
                                if (!ObjectUtils.isEmpty(oldCaseUnitTaggingList)) {
                                    BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
                                    BeanUtils.copyProperties(oldCaseUnitTaggingList.get(0), basicEstateTagging);
                                    basicEstateTagging.setId(null);
                                    basicEstateTagging.setTableId(basicUnit.getId());
                                    basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
                                }
                                try {
                                    List<CaseUnitHuxingVo> caseUnitHuxingList = null;
                                    CaseUnitHuxing query = new CaseUnitHuxing();
                                    query.setUnitId(caseUnit.getId());
                                    caseUnitHuxingList = caseUnitHuxingService.getCaseUnitHuxingList(query);
                                    if (!ObjectUtils.isEmpty(caseUnitHuxingList)) {
                                        for (CaseUnitHuxing oldCaseUnitHuxing : caseUnitHuxingList) {
                                            BasicUnitHuxing basicUnitHuxing = new BasicUnitHuxing();
                                            BeanUtils.copyProperties(oldCaseUnitHuxing, basicUnitHuxing);
                                            basicUnitHuxing.setId(null);
                                            basicUnitHuxing.setUnitId(basicUnit.getId());
                                            Integer huxingId = basicUnitHuxingDao.addBasicUnitHuxing(basicUnitHuxing);

                                            //附件拷贝
                                            example = new SysAttachmentDto();
                                            example.setTableId(oldCaseUnitHuxing.getId());
                                            example.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnitHuxing.class));
                                            attachmentDto = new SysAttachmentDto();
                                            attachmentDto.setTableId(huxingId);
                                            attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                                            baseAttachmentService.copyFtpAttachments(example, attachmentDto);
                                        }
                                    }
                                } catch (Exception e) {
                                    logger.info(e.getMessage(), e);
                                }

                                sqlBuilder = new StringBuilder();
                                synchronousDataDto = new SynchronousDataDto();
                                map = Maps.newHashMap();
                                map.put("unit_id", String.valueOf(basicUnit.getId()));
                                map.put("creator", commonService.thisUserAccount());
                                synchronousDataDto.setFieldDefaultValue(map);
                                synchronousDataDto.setWhereSql("unit_id=" + caseUnit.getId());
                                synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
                                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseUnitDecorate.class));
                                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
                                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋内装sql

                                synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseUnitElevator.class));
                                synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
                                sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//配备电梯sql

                                ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql

                                //-----------处理该单元下房屋数据
                                CaseHouse whereCaseHouse=new CaseHouse();
                                whereCaseHouse.setUnitId(caseUnit.getId());
                                List<CaseHouse> caseHouseList = caseHouseService.getCaseHouseList(whereCaseHouse);
                                if(CollectionUtils.isNotEmpty(caseHouseList)){
                                    for (CaseHouse caseHouse : caseHouseList) {
                                        BasicHouse basicHouse = new BasicHouse();
                                        BeanUtils.copyProperties(caseHouse, basicHouse);
                                        basicHouse.setId(null);
                                        basicHouse.setEstateId(basicEstate.getId());
                                        basicHouse.setBuildingId(basicBuilding.getId());
                                        basicHouse.setUnitId(basicUnit.getId());
                                        basicHouse.setBisCase(true);
                                        basicHouse.setFullName(basicUnit.getFullName()+basicHouse.getHouseNumber());
                                        basicHouseDao.addBasicHouse(basicHouse);

                                        CaseHouseTrading queryTrading = new CaseHouseTrading();
                                        queryTrading.setHouseId(caseHouse.getId());
                                        List<CaseHouseTradingVo> oldCaseHouseTradings = caseHouseTradingService.caseHouseTradingVoList(queryTrading);
                                        BasicHouseTrading basicHouseTrading=null;
                                        if (!ObjectUtils.isEmpty(oldCaseHouseTradings)) {
                                            basicHouseTrading = basicHouseTradingService.getTradingByHouseId(basicHouse.getId());
                                            if (basicHouseTrading != null) {
                                                BeanUtils.copyProperties(oldCaseHouseTradings.get(0), basicHouseTrading);
                                                basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading, false);
                                            }else{
                                                basicHouseTrading = new BasicHouseTrading();
                                                BeanUtils.copyProperties(oldCaseHouseTradings.get(0), basicHouseTrading);
                                                basicHouseTrading.setHouseId(basicHouse.getId());
                                                basicHouseTrading.setId(null);
                                                basicHouseTradingDao.addBasicHouseTrading(basicHouseTrading);
                                            }
                                        }


                                        //附件拷贝
                                        example = new SysAttachmentDto();
                                        example.setTableId(caseHouse.getId());
                                        example.setTableName(FormatUtils.entityNameConvertToTableName(CaseHouse.class));
                                        attachmentDto = new SysAttachmentDto();
                                        attachmentDto.setTableId(basicHouse.getId());
                                        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                                        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

                                        sqlBuilder = new StringBuilder();
                                        synchronousDataDto = new SynchronousDataDto();
                                        map = Maps.newHashMap();
                                        map.put("house_id", String.valueOf(basicHouse.getId()));
                                        map.put("creator", caseHouse.getCreator());
                                        synchronousDataDto.setFieldDefaultValue(map);
                                        synchronousDataDto.setWhereSql("house_id=" + caseHouse.getId());
                                        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
                                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseHouseTradingSell.class));
                                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class));
                                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出售sql

                                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseHouseTradingLease.class));
                                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class));
                                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出租sql

                                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseHouseWater.class));
                                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class));
                                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供水sql

                                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseHouseWaterDrain.class));
                                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class));
                                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//排水sql

                                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseHouseIntelligent.class));
                                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class));
                                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//电力通讯网络sql

                                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseHouseFaceStreet.class));
                                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class));
                                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//临路状况sql

                                        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseHouseEquipment.class));
                                        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
                                        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//设备sql

                                        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql

                                        CaseHouseRoom caseHouseRoom = new CaseHouseRoom();
                                        caseHouseRoom.setHouseId(caseHouse.getId());
                                        CaseHouseCorollaryEquipment caseHouseCorollaryEquipment = new CaseHouseCorollaryEquipment();
                                        caseHouseCorollaryEquipment.setHouseId(caseHouse.getId());
                                        List<CaseHouseRoom> caseHouseRooms = caseHouseRoomService.getCaseHouseRoomList(caseHouseRoom);
                                        List<CaseHouseCorollaryEquipment> caseHouseCorollaryEquipments = caseHouseCorollaryEquipmentService.getCaseHouseCorollaryEquipmentList(caseHouseCorollaryEquipment);
                                        List<CaseHouseDamagedDegree> damagedDegreeList = caseHouseDamagedDegreeService.getDamagedDegreeListByHouseId(caseHouse.getId());


                                        if (!ObjectUtils.isEmpty(caseHouseRooms)) {
                                            try {
                                                for (CaseHouseRoom oo : caseHouseRooms) {
                                                    BasicHouseRoom room = new BasicHouseRoom();
                                                    BeanUtils.copyProperties(oo, room);
                                                    room.setId(null);
                                                    room.setHouseId(basicHouse.getId());
                                                    basicHouseRoomService.saveAndUpdateBasicHouseRoom(room, false);
                                                    CaseHouseRoomDecorate caseHouseRoomDecorate = new CaseHouseRoomDecorate();
                                                    caseHouseRoomDecorate.setRoomId(oo.getId());
                                                    List<CaseHouseRoomDecorate> caseHouseRoomDecorateList = caseHouseRoomDecorateService.getCaseHouseRoomDecorateList(caseHouseRoomDecorate);
                                                    if (!ObjectUtils.isEmpty(caseHouseRoomDecorateList)) {
                                                        for (CaseHouseRoomDecorate po : caseHouseRoomDecorateList) {
                                                            BasicHouseRoomDecorate basicHouseRoomDecorate = new BasicHouseRoomDecorate();
                                                            BeanUtils.copyProperties(po, basicHouseRoomDecorate);
                                                            basicHouseRoomDecorate.setRoomId(room.getId());
                                                            basicHouseRoomDecorate.setId(null);
                                                            basicHouseRoomDecorateDao.addBasicHouseRoomDecorate(basicHouseRoomDecorate);
                                                        }
                                                    }
                                                }
                                            } catch (Exception e) {
                                                logger.error(e.getMessage(), e);
                                            }
                                        }

                                        if (!ObjectUtils.isEmpty(caseHouseCorollaryEquipments)) {
                                            try {
                                                for (CaseHouseCorollaryEquipment oo : caseHouseCorollaryEquipments) {
                                                    SysAttachmentDto query = new SysAttachmentDto();
                                                    query.setTableId(oo.getId());
                                                    query.setTableName(FormatUtils.entityNameConvertToTableName(CaseHouseCorollaryEquipment.class));
                                                    List<SysAttachmentDto> sysAttachmentDtoList1 = baseAttachmentService.getAttachmentList(query);
                                                    BasicHouseCorollaryEquipment po = new BasicHouseCorollaryEquipment();
                                                    BeanUtils.copyProperties(oo, po);
                                                    po.setId(null);
                                                    po.setHouseId(basicHouse.getId());
                                                    Integer EquipmentId = basicHouseCorollaryEquipmentDao.saveBasicHouseCorollaryEquipment(po);
                                                    if (!ObjectUtils.isEmpty(sysAttachmentDtoList1)) {
                                                        for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList1) {
                                                            attachmentDto = new SysAttachmentDto();
                                                            attachmentDto.setTableId(EquipmentId);
                                                            attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
                                                            baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                                                        }
                                                    }
                                                }
                                            } catch (Exception e) {
                                                logger.error(e.getMessage(), e);
                                            }
                                        }

                                        if (CollectionUtils.isNotEmpty(damagedDegreeList)) {
                                            try {
                                                for (CaseHouseDamagedDegree caseHouseDamagedDegree : damagedDegreeList) {
                                                    BasicHouseDamagedDegree basicHouseDamagedDegree = new BasicHouseDamagedDegree();
                                                    BeanUtils.copyProperties(caseHouseDamagedDegree, basicHouseDamagedDegree);
                                                    basicHouseDamagedDegree.setId(null);
                                                    basicHouseDamagedDegree.setHouseId(basicHouse.getId());
                                                    basicHouseDamagedDegreeDao.saveBasicHouseDamagedDegree(basicHouseDamagedDegree);
                                                    List<CaseHouseDamagedDegreeDetail> damagedDegreeDetailList = caseHouseDamagedDegreeService.getDamagedDegreeDetails(caseHouseDamagedDegree.getId());
                                                    if (CollectionUtils.isNotEmpty(damagedDegreeDetailList)) {
                                                        for (CaseHouseDamagedDegreeDetail caseHouseDamagedDegreeDetail : damagedDegreeDetailList) {
                                                            BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail = new BasicHouseDamagedDegreeDetail();
                                                            BeanUtils.copyProperties(caseHouseDamagedDegreeDetail, basicHouseDamagedDegreeDetail);
                                                            basicHouseDamagedDegreeDetail.setDamagedDegreeId(basicHouseDamagedDegree.getId());
                                                            basicHouseDamagedDegreeDetail.setId(null);
                                                            basicHouseDamagedDegreeDetail.setHouseId(basicHouse.getId());
                                                            basicHouseDamagedDegreeDetailDao.addBasicHouseDamagedDegreeDetail(basicHouseDamagedDegreeDetail);
                                                        }
                                                    }
                                                }
                                            } catch (Exception e1) {
                                                logger.error(e1.getMessage(), e1);
                                            }
                                        }

                                        //将房间数据写入到summary中
                                        String fullName = String.format("%s%s栋%s单元%s号", basicEstate.getName(), basicBuilding.getBuildingNumber(), basicUnit.getUnitNumber(), basicHouse.getHouseNumber());
                                        BasicHouseCaseSummary basicHouseCaseSummary = new BasicHouseCaseSummary();
                                        basicHouseCaseSummary.setCaseHouseId(basicHouse.getId());
                                        basicHouseCaseSummary.setType(basicEstate.getType());
                                        basicHouseCaseSummary.setProvince(basicEstate.getProvince());
                                        basicHouseCaseSummary.setCity(basicEstate.getCity());
                                        basicHouseCaseSummary.setDistrict(basicEstate.getDistrict());
                                        basicHouseCaseSummary.setBlockName(basicEstate.getBlockName());
                                        basicHouseCaseSummary.setFullName(fullName.replaceAll("号+?","号"));
                                        basicHouseCaseSummary.setStreet(basicEstate.getStreetNumber());
                                        basicHouseCaseSummary.setArea(basicHouse.getArea());
                                        basicHouseCaseSummary.setHouseType(basicHouse.getPracticalUse());
                                        basicHouseCaseSummary.setTradingType(basicHouseTrading.getTradingType());
                                        basicHouseCaseSummary.setTradingTime(basicHouseTrading.getTradingTime());
                                        basicHouseCaseSummary.setTradingUnitPrice(basicHouseTrading.getTradingUnitPrice());
                                        basicHouseCaseSummary.setVersion(basicHouse.getVersion());
                                        basicHouseCaseSummary.setCreator(basicHouse.getCreator());
                                        basicHouseCaseSummaryService.addBaseHouseSummary(basicHouseCaseSummary);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
