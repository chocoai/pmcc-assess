package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
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
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
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
    @Autowired
    private BasicApplyDao basicApplyDao;
    @Autowired
    private BasicUnitService basicUnitService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicHouse getBasicHouseById(Integer id) {
        return basicHouseDao.getBasicHouseById(id);
    }

    public List<BasicHouse> getBasicHouseIds(List<Integer> ids) {
        return basicHouseDao.getBasicHouseIds(ids);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouse
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouse(BasicHouse basicHouse, boolean updateNull) throws Exception {
        if (basicHouse.getId() == null || basicHouse.getId() == 0) {
            basicHouse.setCreator(commonService.thisUserAccount());
            return basicHouseDao.addBasicHouse(basicHouse);
        } else {
            if (updateNull) {
                BasicHouse house = basicHouseDao.getBasicHouseById(basicHouse.getId());
                if (house != null) {
                    basicHouse.setCreator(house.getCreator());
                    basicHouse.setGmtCreated(house.getGmtCreated());
                    basicHouse.setGmtModified(DateUtils.now());
                }
            }
            basicHouseDao.updateBasicHouse(basicHouse, updateNull);
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
     * 删除同一单元下的房屋数据
     *
     * @param houseId
     * @return
     * @throws Exception
     */
    public void deleteHousesAndBasicApply(Integer houseId) throws Exception {
        this.deleteBasicHouse(houseId);
        this.clearInvalidChildData(houseId);
        //删除标准时，删除原来basicApply的数据
        BasicApply basicApply = new BasicApply();
        basicApply.setBasicHouseId(houseId);
        BasicApply basicApplyOnly = basicApplyService.getBasicApply(basicApply);
        if (basicApplyOnly != null) {
            basicApplyDao.deleteBasicApply(basicApplyOnly.getId());
        }

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

    public List<BasicHouse> getHousesByUnitId(Integer unitId) throws Exception {
        if (unitId == null) return null;
        BasicHouse basicHouse = new BasicHouse();
        basicHouse.setUnitId(unitId);
        return basicHouseDao.basicHouseList(basicHouse);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouse basicHouse) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouse> basicHouseList = basicHouseDao.basicHouseList(basicHouse);
        List<BasicHouseVo> transform = LangUtils.transform(basicHouseList, o -> getBasicHouseVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(transform) ? new ArrayList<BasicHouseVo>(10) : transform);
        return vo;
    }

    public List<CustomCaseEntity> autoCompleteCaseHouse(String houseNumber, Integer unitId) throws Exception {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CustomCaseEntity> houseList = basicHouseDao.getLatestVersionHouseList(houseNumber, unitId);
        return houseList;
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
        vo.setCreatorName(publicService.getUserNameByAccount(basicHouse.getCreator()));
        return vo;
    }


    /**
     * 清理无效数据
     *
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void clearInvalidData(Integer houseId) throws Exception {
        if (houseId == null) return;
        clearInvalidChildData(houseId);//清理从表数据

        //清除标记
        BasicEstateTagging where = new BasicEstateTagging();
        where.setTableId(houseId);
        where.setType(EstateTaggingTypeEnum.HOUSE.getKey());
        basicEstateTaggingDao.removeBasicEstateTagging(where);

        StringBuilder sqlBulder = new StringBuilder();
        String baseSql = "update %s set bis_delete=1 where house_id=%s;";
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseTrading.class), houseId));
        sqlBulder.append(String.format("update %s set bis_delete=1 where id=%s;", FormatUtils.entityNameConvertToTableName(BasicHouse.class), houseId));
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());
    }

    /**
     * 清理从表数据
     *
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void clearInvalidChildData(Integer houseId) throws Exception {


        StringBuilder sqlBulder = new StringBuilder();
        String baseSql = "update %s set bis_delete=1 where house_id=%s;";
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseTradingSell.class), houseId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseTradingLease.class), houseId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseRoom.class), houseId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseWater.class), houseId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class), houseId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class), houseId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class), houseId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class), houseId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseDamagedDegree.class), houseId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicHouseDamagedDegreeDetail.class), houseId));
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
        if (applyId == null) return null;
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
        basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicHouseTrading, false);
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
                            basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(basicHouseDamagedDegree, false);
                        }
                    }
                }
            }
        }
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

    @Transactional(rollbackFor = Exception.class)
    public BasicHouse copyBasicHouse(Integer sourceHouseId, Integer targetHouseId, Boolean containChild) throws Exception {
        return copyBasicHouseIgnore(sourceHouseId,targetHouseId,containChild);
    }

    /**
     * 拷贝房屋数据
     *
     * @param sourceHouseId 源数据Id
     * @param targetHouseId 目标数据Id
     * @param containChild  是否拷贝从表数据
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicHouse copyBasicHouseIgnore(Integer sourceHouseId, Integer targetHouseId, Boolean containChild, String... ignoreProperties) throws Exception {
        BasicHouse sourceBasicHouse = getBasicHouseById(sourceHouseId);
        if (sourceBasicHouse == null) return null;
        BasicHouse targetBasicHouse = getBasicHouseById(targetHouseId);
        if (targetBasicHouse == null) {
            targetBasicHouse = new BasicHouse();
            BeanUtils.copyProperties(sourceBasicHouse, targetBasicHouse,ignoreProperties);
            targetBasicHouse.setId(null);
            targetBasicHouse.setCreator(commonService.thisUserAccount());
            targetBasicHouse.setGmtCreated(null);
            targetBasicHouse.setGmtModified(null);
        } else {
            BeanUtils.copyProperties(sourceBasicHouse, targetBasicHouse, "id");
        }
        this.saveAndUpdateBasicHouse(targetBasicHouse, true);
        BasicHouseTrading sourceBasicHouseTrading = basicHouseTradingService.getTradingByHouseId(sourceHouseId);
        if (sourceBasicHouseTrading != null) {
            BasicHouseTrading targetBasicHouseTrading = basicHouseTradingService.getTradingByHouseId(targetHouseId);
            if (targetBasicHouseTrading == null) {
                targetBasicHouseTrading = new BasicHouseTrading();
                BeanUtils.copyProperties(sourceBasicHouseTrading, targetBasicHouseTrading);
                targetBasicHouseTrading.setId(null);
                targetBasicHouseTrading.setHouseId(targetBasicHouse.getId());
                targetBasicHouseTrading.setCreator(commonService.thisUserAccount());
                targetBasicHouseTrading.setGmtCreated(null);
                targetBasicHouseTrading.setGmtModified(null);
            } else {
                BeanUtils.copyProperties(sourceBasicHouseTrading, targetBasicHouseTrading, "id");
                targetBasicHouseTrading.setHouseId(targetBasicHouse.getId());
            }
            basicHouseTradingService.saveAndUpdateBasicHouseTrading(targetBasicHouseTrading, true);
        }
        if (targetHouseId != null && targetHouseId > 0) {//目标数据已存在，先清理目标数据的从表数据
            clearInvalidChildData(targetHouseId);

            SysAttachmentDto where=new SysAttachmentDto();
            where.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
            where.setTableId(targetHouseId);
            baseAttachmentService.deleteAttachmentByDto(where);
        }
        baseAttachmentService.copyFtpAttachments(FormatUtils.entityNameConvertToTableName(BasicHouse.class), sourceHouseId, targetBasicHouse.getId());
        if (containChild) {
            StringBuilder sqlBuilder = new StringBuilder();
            SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
            HashMap<String, String> map = Maps.newHashMap();
            map.put("house_id", String.valueOf(targetBasicHouse.getId()));
            map.put("creator", commonService.thisUserAccount());
            synchronousDataDto.setFieldDefaultValue(map);
            synchronousDataDto.setWhereSql("house_id=" + sourceHouseId);
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
            basicHouseRoom.setHouseId(sourceHouseId);
            BasicHouseCorollaryEquipment caseHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
            caseHouseCorollaryEquipment.setHouseId(sourceHouseId);
            List<BasicHouseRoom> basicHouseRooms = basicHouseRoomService.basicHouseRoomList(basicHouseRoom);
            List<BasicHouseCorollaryEquipment> caseHouseCorollaryEquipments = basicHouseCorollaryEquipmentService.basicHouseCorollaryEquipmentList(caseHouseCorollaryEquipment);
            List<BasicHouseDamagedDegree> damagedDegreeList = basicHouseDamagedDegreeService.getDamagedDegreeList(sourceHouseId);


            if (!ObjectUtils.isEmpty(basicHouseRooms)) {
                try {
                    for (BasicHouseRoom oo : basicHouseRooms) {
                        BasicHouseRoom room = new BasicHouseRoom();
                        BeanUtils.copyProperties(oo, room);
                        room.setId(null);
                        room.setHouseId(targetBasicHouse.getId());
                        room.setCreator(commonService.thisUserAccount());
                        room.setGmtCreated(null);
                        room.setGmtModified(null);
                        basicHouseRoomService.saveAndUpdateBasicHouseRoom(room, true);
                        BasicHouseRoomDecorate oldBasicHouseRoomDecorate = new BasicHouseRoomDecorate();
                        oldBasicHouseRoomDecorate.setRoomId(oo.getId());
                        List<BasicHouseRoomDecorate> oldBasicHouseRoomDecorateList = basicHouseRoomDecorateService.basicHouseRoomDecorateList(oldBasicHouseRoomDecorate);
                        if (!ObjectUtils.isEmpty(oldBasicHouseRoomDecorateList)) {
                            for (BasicHouseRoomDecorate po : oldBasicHouseRoomDecorateList) {
                                BasicHouseRoomDecorate basicHouseRoomDecorate = new BasicHouseRoomDecorate();
                                BeanUtils.copyProperties(po, basicHouseRoomDecorate);
                                basicHouseRoomDecorate.setRoomId(room.getId());
                                basicHouseRoomDecorate.setId(null);
                                basicHouseRoomDecorate.setCreator(commonService.thisUserAccount());
                                basicHouseRoomDecorate.setGmtCreated(null);
                                basicHouseRoomDecorate.setGmtModified(null);
                                basicHouseRoomDecorateService.saveAndUpdateBasicHouseRoomDecorate(basicHouseRoomDecorate, true);
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
                        BasicHouseCorollaryEquipment equipment = new BasicHouseCorollaryEquipment();
                        BeanUtils.copyProperties(oo, equipment);
                        equipment.setId(null);
                        equipment.setHouseId(targetBasicHouse.getId());
                        equipment.setCreator(commonService.thisUserAccount());
                        equipment.setGmtCreated(null);
                        equipment.setGmtModified(null);
                        basicHouseCorollaryEquipmentService.saveAndUpdateBasicHouseCorollaryEquipment(equipment, true);
                        baseAttachmentService.copyFtpAttachments(FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class), oo.getId(), equipment.getId());
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
                        basicHouseDamagedDegree.setHouseId(targetBasicHouse.getId());
                        basicHouseDamagedDegree.setCreator(commonService.thisUserAccount());
                        basicHouseDamagedDegree.setGmtCreated(null);
                        basicHouseDamagedDegree.setGmtModified(null);
                        basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(basicHouseDamagedDegree, true);
                        List<BasicHouseDamagedDegreeDetail> damagedDegreeDetailList = basicHouseDamagedDegreeService.getDamagedDegreeDetails(caseHouseDamagedDegree.getId());
                        if (CollectionUtils.isNotEmpty(damagedDegreeDetailList)) {
                            for (BasicHouseDamagedDegreeDetail caseHouseDamagedDegreeDetail : damagedDegreeDetailList) {
                                BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail = new BasicHouseDamagedDegreeDetail();
                                BeanUtils.copyProperties(caseHouseDamagedDegreeDetail, basicHouseDamagedDegreeDetail);
                                basicHouseDamagedDegreeDetail.setDamagedDegreeId(basicHouseDamagedDegree.getId());
                                basicHouseDamagedDegreeDetail.setId(null);
                                basicHouseDamagedDegreeDetail.setHouseId(targetBasicHouse.getId());
                                basicHouseDamagedDegreeDetail.setCreator(commonService.thisUserAccount());
                                basicHouseDamagedDegreeDetail.setGmtCreated(null);
                                basicHouseDamagedDegreeDetail.setGmtModified(null);
                                basicHouseDamagedDegreeService.saveAndUpdateDamagedDegreeDetail(basicHouseDamagedDegreeDetail, true);
                            }
                        }
                    }
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        }
        return targetBasicHouse;
    }

    /**
     * 获取当前案例版本号
     *
     * @param estateId
     * @param buildingId
     * @param unitId
     * @param houseNumber
     * @return
     */
    public Integer getCurrVersion(Integer estateId, Integer buildingId, Integer unitId, String houseNumber) {
        BasicHouse where = new BasicHouse();
        where.setEstateId(estateId);
        where.setBuildingId(buildingId);
        where.setUnitId(unitId);
        where.setHouseNumber(houseNumber);
        return basicHouseDao.getCountByBasicHouse(where) + 1;
    }

    /**
     * 数据转换为案例数据
     *
     * @param houseId
     */
    @Transactional(rollbackFor = Exception.class)
    public void dataToCase(Integer houseId) {
        BasicHouse basicHouse = getBasicHouseById(houseId);
        if (basicHouse == null) return;
        Integer currVersion = getCurrVersion(basicHouse.getEstateId(), basicHouse.getBuildingId(), basicHouse.getUnitId(), basicHouse.getHouseNumber());
        //
        if (currVersion > 1) {//1.将上个版本数据复制一份 2.将数据更新为最新数据 3.更新版本号

        } else {//直接复制数据写入

        }
    }
}
