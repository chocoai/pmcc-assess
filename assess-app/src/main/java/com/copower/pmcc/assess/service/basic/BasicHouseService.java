package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.AssessUploadEnum;
import com.copower.pmcc.assess.common.enums.BasicApplyPartInModeEnum;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseDamagedDegreeVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseTradingVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.assess.service.data.DataDamagedDegreeService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicHouseService {
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BasicHouseCorollaryEquipmentService basicHouseCorollaryEquipmentService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseDao basicHouseDao;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private CaseHouseRoomService caseHouseRoomService;
    @Autowired
    private CaseHouseCorollaryEquipmentService caseHouseCorollaryEquipmentService;
    @Autowired
    private CaseHouseRoomDecorateService caseHouseRoomDecorateService;
    @Autowired
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    @Autowired
    private CaseHouseService caseHouseService;
    @Autowired
    private CaseHouseTradingService caseHouseTradingService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private DataDamagedDegreeService dataDamagedDegreeService;
    @Autowired
    private CaseHouseDamagedDegreeService caseHouseDamagedDegreeService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
    @Autowired
    private BasicEstateTaggingDao basicEstateTaggingDao;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicHouse getBasicHouseById(Integer id) throws Exception {
        return basicHouseDao.getBasicHouseById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouse
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouse(BasicHouse basicHouse) throws Exception {
        if (basicHouse.getId() == null || basicHouse.getId() == 0) {
            basicHouse.setCreator(commonService.thisUserAccount());
            return basicHouseDao.addBasicHouse(basicHouse);
        } else {
            basicHouseDao.updateBasicHouse(basicHouse);
            return basicHouse.getId();
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicHouse(Integer id) throws Exception {
        return basicHouseDao.deleteBasicHouse(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicHouse
     * @return
     * @throws Exception
     */
    public List<BasicHouse> basicHouseList(BasicHouse basicHouse) throws Exception {
        return basicHouseDao.basicHouseList(basicHouse);
    }


    public BootstrapTableVo getBootstrapTableVo(BasicHouse basicHouse) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouse> basicHouseList = basicHouseDao.basicHouseList(basicHouse);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicHouseList) ? new ArrayList<BasicHouse>(10) : basicHouseList);
        return vo;
    }

    public BasicHouseVo getBasicHouseVo(BasicHouse basicHouse) {
        if (basicHouse == null) {
            return null;
        }
        BasicHouseVo vo = new BasicHouseVo();
        BeanUtils.copyProperties(basicHouse, vo);
        if (basicHouse.getUseEnvironment() != null) {
            vo.setUseEnvironmentName(baseDataDicService.getNameById(basicHouse.getUseEnvironment()));
        }
        if (basicHouse.getCertUse() != null) {
            vo.setCertUseName(baseDataDicService.getNameById(basicHouse.getCertUse()));
        }
        if (basicHouse.getOrientation() != null) {
            vo.setOrientationName(baseDataDicService.getNameById(basicHouse.getOrientation()));
        }
        if (basicHouse.getResearchType() != null) {
            vo.setResearchTypeName(baseDataDicService.getNameById(basicHouse.getResearchType()));
        }
        if (basicHouse.getPracticalUse() != null) {
            vo.setPracticalUseName(baseDataDicService.getNameById(basicHouse.getPracticalUse()));
        }
        if (basicHouse.getUseCondition() != null) {
            vo.setUseConditionName(baseDataDicService.getNameById(basicHouse.getUseCondition()));
        }
        if (basicHouse.getDecorateSituation() != null) {
            vo.setDecorateSituationName(baseDataDicService.getNameById(basicHouse.getDecorateSituation()));
        }
        vo.setSpatialDistributionName(baseDataDicService.getNameById(basicHouse.getSpatialDistribution()));
        return vo;
    }


    /**
     * 清理无效数据
     *
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void clearInvalidData(Integer applyId) throws Exception {
        BasicHouse house = null;
        if (applyId == 0) {
            BasicHouse where = new BasicHouse();
            where.setApplyId(applyId);
            where.setCreator(commonService.thisUserAccount());
            List<BasicHouse> houseList = basicHouseDao.basicHouseList(where);
            if (CollectionUtils.isNotEmpty(houseList)) {
                house = houseList.get(0);
            }
        } else {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(applyId);
            house = basicHouseDao.getBasicHouseById(basicApply.getBasicHouseId());
        }
        if (house == null) return;
        //清除标记
        if(house!=null) {
            BasicEstateTagging where = new BasicEstateTagging();
            where.setTableId(house.getId());
            where.setType("house");
            basicEstateTaggingDao.removeBasicEstateTagging(where);
        }
        StringBuilder sqlBulder = new StringBuilder();
        String baseSql = "delete from %s where house_id=%s;";
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class), house.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class), house.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseRoom.class), house.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseWater.class), house.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class), house.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class), house.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class), house.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class), house.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseDamagedDegree.class), house.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseDamagedDegreeDetail.class), house.getId()));

        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseTrading.class), house.getId()));
        sqlBulder.append(String.format("delete from %s where id=%s;", FormatUtils.entityNameConvertToTableName(BasicHouse.class), house.getId()));
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());
    }

    /**
     * 获取数据
     *
     * @param applyId
     * @return
     * @throws Exception
     */
    public Map<String, Object> getBasicHouseByApplyId(Integer applyId) throws Exception {
        Map<String, Object> objectMap = Maps.newHashMap();
        BasicHouse basicHouse = getHouseByApplyId(applyId);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouse.class.getSimpleName()), getBasicHouseVo(basicHouse));

        BasicHouseTrading houseTrading = basicHouseTradingService.getTradingByHouseId(basicHouse.getId());
        BasicHouseTradingVo basicHouseTradingVo = basicHouseTradingService.getBasicHouseTradingVo(houseTrading);
        if (basicHouseTradingVo == null) {
            basicHouseTradingVo = new BasicHouseTradingVo();
            basicHouseTradingVo.setHouseId(basicHouse.getId());
        }
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouseTrading.class.getSimpleName()), basicHouseTradingVo);
        return objectMap;
    }

    public BasicHouse getHouseByApplyId(Integer applyId) {
        if(applyId==null) return null;
        BasicHouse where = new BasicHouse();
        where.setApplyId(applyId);
        where.setCreator(commonService.thisUserAccount());
        List<BasicHouse> basicHouses = basicHouseDao.basicHouseList(where);
        if (!CollectionUtils.isEmpty(basicHouses)) {
            return basicHouses.get(0);
        } else {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(applyId);
            return basicHouseDao.getBasicHouseById(basicApply.getBasicHouseId());
        }
    }

    /**
     * 添加房屋及交易情况
     *
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addHouseAndTrading(String houseNumber, Integer applyId) throws Exception {
        this.clearInvalidData(0);
        Map<String, Object> objectMap = Maps.newHashMap();

        BasicHouse basicHouse = null;
        if (applyId == null || applyId.equals(0)) {
            basicHouse = new BasicHouse();
            basicHouse.setHouseNumber(houseNumber);

            basicHouse.setApplyId(0);
            basicHouse.setCreator(commonService.thisUserAccount());
            basicHouseDao.addBasicHouse(basicHouse);
        } else {
            basicHouse = getHouseByApplyId(applyId);
        }
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouse.class.getSimpleName()), getBasicHouseVo(basicHouse));

        BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
        basicHouseTrading.setHouseId(basicHouse.getId());
        basicHouseTrading.setApplyId(applyId == null ? 0 : applyId);
        basicHouseTrading.setCreator(commonService.thisUserAccount());
        basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouseTrading.class.getSimpleName()), basicHouseTradingService.getBasicHouseTradingVo(basicHouseTrading));

        initDemagedDegree(basicHouse);
        return objectMap;
    }

    /**
     * 初始化房屋完损度
     *
     * @param basicHouse
     * @throws Exception
     */
    public void initDemagedDegree(BasicHouse basicHouse) throws Exception {
        List<DataDamagedDegree> degreeList = dataDamagedDegreeService.getCacheDamagedDegreeListByPid(0);
        List<BasicHouseDamagedDegree> basicHouseDamagedDegrees = basicHouseDamagedDegreeService.getDamagedDegreeList(basicHouse.getId());
        if (CollectionUtils.isEmpty(basicHouseDamagedDegrees)) {
            if (!CollectionUtils.isEmpty(degreeList)) {
                for (DataDamagedDegree degree : degreeList) {
                    List<DataDamagedDegree> damagedDegreeList = dataDamagedDegreeService.getCacheDamagedDegreeListByPid(degree.getId());
                    if (!CollectionUtils.isEmpty(damagedDegreeList)) {
                        for (DataDamagedDegree damagedDegree : damagedDegreeList) {
                            BasicHouseDamagedDegree basicHouseDamagedDegree = new BasicHouseDamagedDegreeVo();
                            basicHouseDamagedDegree.setHouseId(basicHouse.getId());
                            basicHouseDamagedDegree.setType(degree.getId());
                            basicHouseDamagedDegree.setCategory(damagedDegree.getId());
                            basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(basicHouseDamagedDegree);
                        }
                    }
                }
            }
        }
    }

    /**
     * 将 CaseHouse 下的子类 转移到 BasicHouse下的子类中去 (用做过程数据)
     *
     * @param caseHouseId
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> appWriteHouse(Integer caseHouseId, String housePartInMode, Integer applyId) throws Exception {
        if (caseHouseId == null) {
            throw new Exception("null ponit");
        }
        applyId = applyId == null ? 0 : applyId;
        this.clearInvalidData(applyId);
        Map<String, Object> objectMap = new HashMap<String, Object>(2);
        CaseHouse caseHouse = caseHouseService.getCaseHouseById(caseHouseId);
        if (caseHouse == null) {
            return objectMap;
        }
        BasicHouse basicHouse = new BasicHouse();
        BeanUtils.copyProperties(caseHouse, basicHouse);
        basicHouse.setApplyId(applyId);
        basicHouse.setCreator(commonService.thisUserAccount());
        basicHouse.setId(null);
        basicHouse.setGmtCreated(null);
        basicHouse.setGmtModified(null);
        if (StringUtils.equals(housePartInMode, BasicApplyPartInModeEnum.REFERENCE.getKey())) {
            basicHouse.setHouseNumber(null);
        }
        basicHouseDao.addBasicHouse(basicHouse);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouse.class.getSimpleName()), getBasicHouseVo(basicHouse));

        CaseHouseTrading queryTrading = new CaseHouseTrading();
        queryTrading.setHouseId(caseHouseId);
        List<CaseHouseTrading> caseHouseTradings = caseHouseTradingService.caseHouseTradingList(queryTrading);
        if (!ObjectUtils.isEmpty(caseHouseTradings)) {
            BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
            BeanUtils.copyProperties(caseHouseTradings.get(0), basicHouseTrading);
            basicHouseTrading.setApplyId(applyId == null ? 0 : applyId);
            basicHouseTrading.setHouseId(basicHouse.getId());
            basicHouseTrading.setCreator(commonService.thisUserAccount());
            basicHouseTrading.setId(null);
            basicHouseTrading.setGmtCreated(null);
            basicHouseTrading.setGmtModified(null);
            basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading);
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouseTrading.class.getSimpleName()), basicHouseTradingService.getBasicHouseTradingVo(basicHouseTrading));
        } else {
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouseTrading.class.getSimpleName()), new BasicHouseTradingVo());
        }

        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(caseHouse.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(CaseHouse.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(basicHouse.getId());
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        //标注拷贝
        CaseEstateTagging caseEstateTagging = new CaseEstateTagging();
        caseEstateTagging.setDataId(caseHouseId);
        caseEstateTagging.setType(EstateTaggingTypeEnum.HOUSE.getKey());
        List<CaseEstateTagging> oldCaseEstateTaggingList = caseEstateTaggingService.getCaseEstateTaggingList(caseEstateTagging);
        if (!ObjectUtils.isEmpty(oldCaseEstateTaggingList)) {
            BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
            BeanUtils.copyProperties(oldCaseEstateTaggingList.get(0), basicEstateTagging);
            basicEstateTagging.setCreator(commonService.thisUserAccount());
            basicEstateTagging.setName(null);
            basicEstateTagging.setTableId(basicHouse.getId());
            basicEstateTagging.setGmtCreated(null);
            basicEstateTagging.setGmtModified(null);
            //获取新户型图id
            SysAttachmentDto newHuXing = new SysAttachmentDto();
            newHuXing.setTableId(basicHouse.getId());
            newHuXing.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
            newHuXing.setFieldsName(AssessUploadEnum.HOUSE_NEW_HUXING_PLAN.getKey());
            List<SysAttachmentDto> newHuXingList = baseAttachmentService.getAttachmentList(newHuXing);
            if(CollectionUtils.isNotEmpty(newHuXingList))
                basicEstateTagging.setAttachmentId(newHuXingList.get(0).getId());
            basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
        }
        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("house_id", String.valueOf(basicHouse.getId()));
        map.put("creator", commonService.thisUserAccount());
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
        caseHouseRoom.setHouseId(caseHouseId);
        CaseHouseCorollaryEquipment caseHouseCorollaryEquipment = new CaseHouseCorollaryEquipment();
        caseHouseCorollaryEquipment.setHouseId(caseHouseId);
        List<CaseHouseRoom> caseHouseRooms = caseHouseRoomService.getCaseHouseRoomList(caseHouseRoom);
        List<CaseHouseCorollaryEquipment> caseHouseCorollaryEquipments = caseHouseCorollaryEquipmentService.getCaseHouseCorollaryEquipmentList(caseHouseCorollaryEquipment);
        List<CaseHouseDamagedDegree> damagedDegreeList = caseHouseDamagedDegreeService.getDamagedDegreeListByHouseId(caseHouseId);


        if (!ObjectUtils.isEmpty(caseHouseRooms)) {
            try {
                for (CaseHouseRoom oo : caseHouseRooms) {
                    BasicHouseRoom room = new BasicHouseRoom();
                    BeanUtils.copyProperties(oo, room);
                    room.setId(null);
                    room.setHouseId(basicHouse.getId());
                    room.setCreator(commonService.thisUserAccount());
                    room.setGmtCreated(null);
                    room.setGmtModified(null);
                    basicHouseRoomService.saveAndUpdateBasicHouseRoom(room);
                    CaseHouseRoomDecorate caseHouseRoomDecorate = new CaseHouseRoomDecorate();
                    caseHouseRoomDecorate.setRoomId(oo.getId());
                    List<CaseHouseRoomDecorate> caseHouseRoomDecorateList = caseHouseRoomDecorateService.getCaseHouseRoomDecorateList(caseHouseRoomDecorate);
                    if (!ObjectUtils.isEmpty(caseHouseRoomDecorateList)) {
                        for (CaseHouseRoomDecorate po : caseHouseRoomDecorateList) {
                            BasicHouseRoomDecorate basicHouseRoomDecorate = new BasicHouseRoomDecorate();
                            BeanUtils.copyProperties(po, basicHouseRoomDecorate);
                            basicHouseRoomDecorate.setRoomId(room.getId());
                            basicHouseRoomDecorate.setCreator(commonService.thisUserAccount());
                            basicHouseRoomDecorate.setGmtCreated(null);
                            basicHouseRoomDecorate.setGmtModified(null);
                            basicHouseRoomDecorate.setId(null);
                            basicHouseRoomDecorateService.saveAndUpdateBasicHouseRoomDecorate(basicHouseRoomDecorate);
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
                    po.setCreator(commonService.thisUserAccount());
                    po.setGmtCreated(null);
                    po.setGmtModified(null);
                    Integer id = basicHouseCorollaryEquipmentService.saveAndUpdateBasicHouseCorollaryEquipment(po);
                    if (!ObjectUtils.isEmpty(sysAttachmentDtoList1)) {
                        for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList1) {
                            attachmentDto = new SysAttachmentDto();
                            attachmentDto.setTableId(id);
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
                    basicHouseDamagedDegree.setCreator(commonService.thisUserAccount());
                    basicHouseDamagedDegree.setGmtCreated(null);
                    basicHouseDamagedDegree.setGmtModified(null);
                    basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(basicHouseDamagedDegree);
                    List<CaseHouseDamagedDegreeDetail> damagedDegreeDetailList = caseHouseDamagedDegreeService.getDamagedDegreeDetails(caseHouseDamagedDegree.getId());
                    if (CollectionUtils.isNotEmpty(damagedDegreeDetailList)) {
                        for (CaseHouseDamagedDegreeDetail caseHouseDamagedDegreeDetail : damagedDegreeDetailList) {
                            BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail = new BasicHouseDamagedDegreeDetail();
                            BeanUtils.copyProperties(caseHouseDamagedDegreeDetail, basicHouseDamagedDegreeDetail);
                            basicHouseDamagedDegreeDetail.setDamagedDegreeId(basicHouseDamagedDegree.getId());
                            basicHouseDamagedDegreeDetail.setId(null);
                            basicHouseDamagedDegreeDetail.setHouseId(basicHouse.getId());
                            basicHouseDamagedDegreeDetail.setCreator(commonService.thisUserAccount());
                            basicHouseDamagedDegreeDetail.setGmtCreated(null);
                            basicHouseDamagedDegreeDetail.setGmtModified(null);
                            basicHouseDamagedDegreeService.saveAndUpdateDamagedDegreeDetail(basicHouseDamagedDegreeDetail);
                        }
                    }
                }
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
            }
        }
        return objectMap;
    }

    //引用项目中数据
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> getBasicHouseFromProject(Integer applyId) throws Exception {
        if (applyId == null) {
            throw new Exception("null ponit");
        }
        this.clearInvalidData(0);
        Map<String, Object> objectMap = new HashMap<String, Object>(2);
        BasicHouse oldBasicHouse = this.getHouseByApplyId(applyId);
        if (oldBasicHouse == null) {
            return objectMap;
        }
        BasicHouse basicHouse = new BasicHouse();
        BeanUtils.copyProperties(oldBasicHouse, basicHouse);
        basicHouse.setApplyId(0);
        basicHouse.setCreator(commonService.thisUserAccount());
        basicHouse.setGmtCreated(null);
        basicHouse.setGmtModified(null);
        basicHouse.setId(null);

        this.saveAndUpdateBasicHouse(basicHouse);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouse.class.getSimpleName()), getBasicHouseVo(basicHouse));

        BasicHouseTrading queryTrading = new BasicHouseTrading();
        queryTrading.setHouseId(oldBasicHouse.getId());
        List<BasicHouseTrading> oldBasicHouseTradings = basicHouseTradingService.basicHouseTradingList(queryTrading);
        if (!ObjectUtils.isEmpty(oldBasicHouseTradings)) {
            BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
            BeanUtils.copyProperties(oldBasicHouseTradings.get(0), basicHouseTrading);
            basicHouseTrading.setApplyId(0);
            basicHouseTrading.setHouseId(basicHouse.getId());
            basicHouseTrading.setCreator(commonService.thisUserAccount());
            basicHouseTrading.setId(null);
            basicHouseTrading.setGmtCreated(null);
            basicHouseTrading.setGmtModified(null);
            basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading);
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouseTrading.class.getSimpleName()), basicHouseTradingService.getBasicHouseTradingVo(basicHouseTrading));
        } else {
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouseTrading.class.getSimpleName()), new BasicHouseTradingVo());
        }


        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(oldBasicHouse.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(basicHouse.getId());
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        //标注拷贝
        BasicEstateTagging basicEstateTaggingQuery = new BasicEstateTagging();
        basicEstateTaggingQuery.setTableId(oldBasicHouse.getId());
        basicEstateTaggingQuery.setType(EstateTaggingTypeEnum.HOUSE.getKey());
        List<BasicEstateTagging> oldBasicEstateTaggingList = basicEstateTaggingService.getBasicEstateTaggingList(basicEstateTaggingQuery);
        if (!ObjectUtils.isEmpty(oldBasicEstateTaggingList)) {
            BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
            BeanUtils.copyProperties(oldBasicEstateTaggingList.get(0), basicEstateTagging);
            basicEstateTagging.setCreator(commonService.thisUserAccount());
            basicEstateTagging.setName(null);
            basicEstateTagging.setTableId(basicHouse.getId());
            basicEstateTagging.setGmtCreated(null);
            basicEstateTagging.setGmtModified(null);
            //获取新户型图id
            SysAttachmentDto newHuXing = new SysAttachmentDto();
            newHuXing.setTableId(basicHouse.getId());
            newHuXing.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
            newHuXing.setFieldsName(AssessUploadEnum.HOUSE_NEW_HUXING_PLAN.getKey());
            List<SysAttachmentDto> newHuXingList = baseAttachmentService.getAttachmentList(newHuXing);
            if(CollectionUtils.isNotEmpty(newHuXingList))
                basicEstateTagging.setAttachmentId(newHuXingList.get(0).getId());
            basicEstateTaggingService.addBasicEstateTagging(basicEstateTagging);
        }

        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("house_id", String.valueOf(basicHouse.getId()));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        synchronousDataDto.setWhereSql("house_id=" + oldBasicHouse.getId());
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出售sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出租sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供水sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//排水sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//电力通讯网络sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//临路状况sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//设备sql

        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql

        BasicHouseRoom basicHouseRoom = new BasicHouseRoom();
        basicHouseRoom.setHouseId(oldBasicHouse.getId());
        BasicHouseCorollaryEquipment caseHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
        caseHouseCorollaryEquipment.setHouseId(oldBasicHouse.getId());
        List<BasicHouseRoom> basicHouseRooms = basicHouseRoomService.basicHouseRoomList(basicHouseRoom);
        List<BasicHouseCorollaryEquipment> caseHouseCorollaryEquipments = basicHouseCorollaryEquipmentService.basicHouseCorollaryEquipmentList(caseHouseCorollaryEquipment);
        List<BasicHouseDamagedDegree> damagedDegreeList = basicHouseDamagedDegreeService.getDamagedDegreeList(oldBasicHouse.getId());


        if (!ObjectUtils.isEmpty(basicHouseRooms)) {
            try {
                for (BasicHouseRoom oo : basicHouseRooms) {
                    BasicHouseRoom room = new BasicHouseRoom();
                    BeanUtils.copyProperties(oo, room);
                    room.setId(null);
                    room.setHouseId(basicHouse.getId());
                    room.setCreator(commonService.thisUserAccount());
                    room.setGmtCreated(null);
                    room.setGmtModified(null);
                    basicHouseRoomService.saveAndUpdateBasicHouseRoom(room);
                    BasicHouseRoomDecorate oldBasicHouseRoomDecorate = new BasicHouseRoomDecorate();
                    oldBasicHouseRoomDecorate.setRoomId(oo.getId());
                    List<BasicHouseRoomDecorate> oldBasicHouseRoomDecorateList = basicHouseRoomDecorateService.basicHouseRoomDecorateList(oldBasicHouseRoomDecorate);
                    if (!ObjectUtils.isEmpty(oldBasicHouseRoomDecorateList)) {
                        for (BasicHouseRoomDecorate po : oldBasicHouseRoomDecorateList) {
                            BasicHouseRoomDecorate basicHouseRoomDecorate = new BasicHouseRoomDecorate();
                            BeanUtils.copyProperties(po, basicHouseRoomDecorate);
                            basicHouseRoomDecorate.setRoomId(room.getId());
                            basicHouseRoomDecorate.setCreator(commonService.thisUserAccount());
                            basicHouseRoomDecorate.setGmtCreated(null);
                            basicHouseRoomDecorate.setGmtModified(null);
                            basicHouseRoomDecorate.setId(null);
                            basicHouseRoomDecorateService.saveAndUpdateBasicHouseRoomDecorate(basicHouseRoomDecorate);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }


        if (!ObjectUtils.isEmpty(caseHouseCorollaryEquipments)) {
            try {
                for (BasicHouseCorollaryEquipment oo : caseHouseCorollaryEquipments) {
                    SysAttachmentDto query = new SysAttachmentDto();
                    query.setTableId(oo.getId());
                    query.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
                    List<SysAttachmentDto> sysAttachmentDtoList1 = baseAttachmentService.getAttachmentList(query);
                    BasicHouseCorollaryEquipment po = new BasicHouseCorollaryEquipment();
                    BeanUtils.copyProperties(oo, po);
                    po.setId(null);
                    po.setHouseId(basicHouse.getId());
                    po.setCreator(commonService.thisUserAccount());
                    po.setGmtCreated(null);
                    po.setGmtModified(null);
                    Integer id = basicHouseCorollaryEquipmentService.saveAndUpdateBasicHouseCorollaryEquipment(po);
                    if (!ObjectUtils.isEmpty(sysAttachmentDtoList1)) {
                        for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList1) {
                            attachmentDto = new SysAttachmentDto();
                            attachmentDto.setTableId(id);
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
                for (BasicHouseDamagedDegree caseHouseDamagedDegree : damagedDegreeList) {
                    BasicHouseDamagedDegree basicHouseDamagedDegree = new BasicHouseDamagedDegree();
                    BeanUtils.copyProperties(caseHouseDamagedDegree, basicHouseDamagedDegree);
                    basicHouseDamagedDegree.setId(null);
                    basicHouseDamagedDegree.setHouseId(basicHouse.getId());
                    basicHouseDamagedDegree.setCreator(commonService.thisUserAccount());
                    basicHouseDamagedDegree.setGmtCreated(null);
                    basicHouseDamagedDegree.setGmtModified(null);
                    basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(basicHouseDamagedDegree);
                    List<BasicHouseDamagedDegreeDetail> damagedDegreeDetailList = basicHouseDamagedDegreeService.getDamagedDegreeDetails(caseHouseDamagedDegree.getId());
                    if (CollectionUtils.isNotEmpty(damagedDegreeDetailList)) {
                        for (BasicHouseDamagedDegreeDetail caseHouseDamagedDegreeDetail : damagedDegreeDetailList) {
                            BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail = new BasicHouseDamagedDegreeDetail();
                            BeanUtils.copyProperties(caseHouseDamagedDegreeDetail, basicHouseDamagedDegreeDetail);
                            basicHouseDamagedDegreeDetail.setDamagedDegreeId(basicHouseDamagedDegree.getId());
                            basicHouseDamagedDegreeDetail.setId(null);
                            basicHouseDamagedDegreeDetail.setHouseId(basicHouse.getId());
                            basicHouseDamagedDegreeDetail.setCreator(commonService.thisUserAccount());
                            basicHouseDamagedDegreeDetail.setGmtCreated(null);
                            basicHouseDamagedDegreeDetail.setGmtModified(null);
                            basicHouseDamagedDegreeService.saveAndUpdateDamagedDegreeDetail(basicHouseDamagedDegreeDetail);
                        }
                    }
                }
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
            }
        }
        return objectMap;
    }


    /**
     * 引用案列中的数据
     *
     * @param id      老数据对应id
     * @param tableId basicHouse对应id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> quoteHouseData(Integer id, Integer tableId) throws Exception {
        if (id == null || tableId == null) {
            throw new Exception("null ponit");
        }
        this.clearInvalidData2(tableId);
        //更新批量申请表信息
        BasicApplyBatchDetail batchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_house", tableId);
        batchDetail.setQuoteId(id);
        batchDetail.setBaseType(BaseConstant.DATABASE_PMCC_ASSESS);
        basicApplyBatchDetailDao.updateInfo(batchDetail);
        Integer parentTableId = basicApplyBatchDetailService.getParentTableId(batchDetail);

        Map<String, Object> objectMap = new HashMap<String, Object>(2);
        BasicHouse oldBasicHouse = this.getBasicHouseById(id);
        BasicHouse basicHouse = new BasicHouse();
        BeanUtils.copyProperties(oldBasicHouse, basicHouse);
        basicHouse.setCreator(commonService.thisUserAccount());
        basicHouse.setGmtCreated(null);
        basicHouse.setGmtModified(null);
        basicHouse.setId(tableId);
        basicHouse.setApplyId(null);
        basicHouse.setUnitId(parentTableId);

        this.saveAndUpdateBasicHouse(basicHouse);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouse.class.getSimpleName()), getBasicHouseVo(basicHouse));

        BasicHouseTrading queryTrading = new BasicHouseTrading();
        queryTrading.setHouseId(id);
        List<BasicHouseTrading> oldBasicHouseTradings = basicHouseTradingService.basicHouseTradingList(queryTrading);
        if (!ObjectUtils.isEmpty(oldBasicHouseTradings)) {
            BasicHouseTrading basicHouseTrading = new BasicHouseTrading();
            BeanUtils.copyProperties(oldBasicHouseTradings.get(0), basicHouseTrading);
            basicHouseTrading.setApplyId(null);
            basicHouseTrading.setHouseId(tableId);
            basicHouseTrading.setCreator(commonService.thisUserAccount());
            basicHouseTrading.setId(null);
            basicHouseTrading.setGmtCreated(null);
            basicHouseTrading.setGmtModified(null);
            basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading);
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouseTrading.class.getSimpleName()), basicHouseTradingService.getBasicHouseTradingVo(basicHouseTrading));
        } else {
            objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouseTrading.class.getSimpleName()), new BasicHouseTradingVo());
        }

        //删除原有的附件
        SysAttachmentDto deleteExample = new SysAttachmentDto();
        deleteExample.setTableId(tableId);
        deleteExample.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(deleteExample);
        if (!org.springframework.util.CollectionUtils.isEmpty(attachmentList)) {
            for (SysAttachmentDto item : attachmentList) {
                baseAttachmentService.deleteAttachment(item.getId());
            }
        }


        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(id);
        example.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(tableId);
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("house_id", String.valueOf(tableId));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        synchronousDataDto.setWhereSql("house_id=" + id);
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出售sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//房屋出租sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供水sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//排水sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//电力通讯网络sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//临路状况sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//设备sql

        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql

        BasicHouseRoom basicHouseRoom = new BasicHouseRoom();
        basicHouseRoom.setHouseId(id);
        BasicHouseCorollaryEquipment caseHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
        caseHouseCorollaryEquipment.setHouseId(id);
        List<BasicHouseRoom> basicHouseRooms = basicHouseRoomService.basicHouseRoomList(basicHouseRoom);
        List<BasicHouseCorollaryEquipment> caseHouseCorollaryEquipments = basicHouseCorollaryEquipmentService.basicHouseCorollaryEquipmentList(caseHouseCorollaryEquipment);
        List<BasicHouseDamagedDegree> damagedDegreeList = basicHouseDamagedDegreeService.getDamagedDegreeList(id);


        if (!ObjectUtils.isEmpty(basicHouseRooms)) {
            try {
                for (BasicHouseRoom oo : basicHouseRooms) {
                    BasicHouseRoom room = new BasicHouseRoom();
                    BeanUtils.copyProperties(oo, room);
                    room.setId(null);
                    room.setHouseId(tableId);
                    room.setCreator(commonService.thisUserAccount());
                    room.setGmtCreated(null);
                    room.setGmtModified(null);
                    basicHouseRoomService.saveAndUpdateBasicHouseRoom(room);
                    BasicHouseRoomDecorate oldBasicHouseRoomDecorate = new BasicHouseRoomDecorate();
                    oldBasicHouseRoomDecorate.setRoomId(oo.getId());
                    List<BasicHouseRoomDecorate> oldBasicHouseRoomDecorateList = basicHouseRoomDecorateService.basicHouseRoomDecorateList(oldBasicHouseRoomDecorate);
                    if (!ObjectUtils.isEmpty(oldBasicHouseRoomDecorateList)) {
                        for (BasicHouseRoomDecorate po : oldBasicHouseRoomDecorateList) {
                            BasicHouseRoomDecorate basicHouseRoomDecorate = new BasicHouseRoomDecorate();
                            BeanUtils.copyProperties(po, basicHouseRoomDecorate);
                            basicHouseRoomDecorate.setRoomId(room.getId());
                            basicHouseRoomDecorate.setCreator(commonService.thisUserAccount());
                            basicHouseRoomDecorate.setGmtCreated(null);
                            basicHouseRoomDecorate.setGmtModified(null);
                            basicHouseRoomDecorate.setId(null);
                            basicHouseRoomDecorateService.saveAndUpdateBasicHouseRoomDecorate(basicHouseRoomDecorate);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }


        if (!ObjectUtils.isEmpty(caseHouseCorollaryEquipments)) {
            try {
                for (BasicHouseCorollaryEquipment oo : caseHouseCorollaryEquipments) {
                    SysAttachmentDto query = new SysAttachmentDto();
                    query.setTableId(oo.getId());
                    query.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
                    List<SysAttachmentDto> sysAttachmentDtoList1 = baseAttachmentService.getAttachmentList(query);
                    BasicHouseCorollaryEquipment po = new BasicHouseCorollaryEquipment();
                    BeanUtils.copyProperties(oo, po);
                    po.setId(null);
                    po.setHouseId(tableId);
                    po.setCreator(commonService.thisUserAccount());
                    po.setGmtCreated(null);
                    po.setGmtModified(null);
                    Integer EquipmentId = basicHouseCorollaryEquipmentService.saveAndUpdateBasicHouseCorollaryEquipment(po);
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
                for (BasicHouseDamagedDegree caseHouseDamagedDegree : damagedDegreeList) {
                    BasicHouseDamagedDegree basicHouseDamagedDegree = new BasicHouseDamagedDegree();
                    BeanUtils.copyProperties(caseHouseDamagedDegree, basicHouseDamagedDegree);
                    basicHouseDamagedDegree.setId(null);
                    basicHouseDamagedDegree.setHouseId(tableId);
                    basicHouseDamagedDegree.setCreator(commonService.thisUserAccount());
                    basicHouseDamagedDegree.setGmtCreated(null);
                    basicHouseDamagedDegree.setGmtModified(null);
                    basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(basicHouseDamagedDegree);
                    List<BasicHouseDamagedDegreeDetail> damagedDegreeDetailList = basicHouseDamagedDegreeService.getDamagedDegreeDetails(caseHouseDamagedDegree.getId());
                    if (CollectionUtils.isNotEmpty(damagedDegreeDetailList)) {
                        for (BasicHouseDamagedDegreeDetail caseHouseDamagedDegreeDetail : damagedDegreeDetailList) {
                            BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail = new BasicHouseDamagedDegreeDetail();
                            BeanUtils.copyProperties(caseHouseDamagedDegreeDetail, basicHouseDamagedDegreeDetail);
                            basicHouseDamagedDegreeDetail.setDamagedDegreeId(basicHouseDamagedDegree.getId());
                            basicHouseDamagedDegreeDetail.setId(null);
                            basicHouseDamagedDegreeDetail.setHouseId(tableId);
                            basicHouseDamagedDegreeDetail.setCreator(commonService.thisUserAccount());
                            basicHouseDamagedDegreeDetail.setGmtCreated(null);
                            basicHouseDamagedDegreeDetail.setGmtModified(null);
                            basicHouseDamagedDegreeService.saveAndUpdateDamagedDegreeDetail(basicHouseDamagedDegreeDetail);
                        }
                    }
                }
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
            }
        }
        return objectMap;
    }

    /**
     * 拷贝户型图
     *
     * @param sourceTableId
     * @param sourceTableName
     * @param targetTableId
     * @param fieldsName
     */
    public void copyHuxingPlan(Integer sourceTableId, String sourceTableName, Integer targetTableId, String fieldsName) throws Exception {
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(targetTableId);
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        attachmentDto.setFieldsName(fieldsName);
        //清除原关联的附件
        baseAttachmentService.deleteAttachmentByDto(attachmentDto);

        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableId(sourceTableId);
        where.setTableName(sourceTableName);
        List<SysAttachmentDto> attachmentList = baseAttachmentService.getAttachmentList(where);
        if (CollectionUtils.isEmpty(attachmentList)) {
            return;
        }
        for (SysAttachmentDto sysAttachmentDto : attachmentList) {
            baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
        }
    }

    /**
     * 清理无效数据
     *
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void clearInvalidData2(Integer tableId) throws Exception {
        StringBuilder sqlBulder = new StringBuilder();
        String baseSql = "delete from %s where house_id=%s;";
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseRoom.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseWater.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseDamagedDegree.class), tableId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseDamagedDegreeDetail.class), tableId));

        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseTrading.class), tableId));
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Map<String, Object> getBasicHouseMapById(Integer id) throws Exception {
        Map<String, Object> objectMap = Maps.newHashMap();
        BasicHouse basicHouse = this.getBasicHouseById(id);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouse.class.getSimpleName()), getBasicHouseVo(basicHouse));

        BasicHouseTrading houseTrading = basicHouseTradingService.getTradingByHouseId(basicHouse.getId());
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouseTrading.class.getSimpleName()), basicHouseTradingService.getBasicHouseTradingVo(houseTrading) != null ? basicHouseTradingService.getBasicHouseTradingVo(houseTrading) : new BasicHouseTrading());
        initDemagedDegree(basicHouse);
        return objectMap;
    }
}
