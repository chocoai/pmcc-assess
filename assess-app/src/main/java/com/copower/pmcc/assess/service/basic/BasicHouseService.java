package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.BasicApplyPartInModeEnum;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseDamagedDegreeVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseVo;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingLeaseVo;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseTradingSellVo;
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
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
    private CaseEstateTaggingService caseEstateTaggingService;
    @Autowired
    private BasicHouseTradingLeaseService basicHouseTradingLeaseService;
    @Autowired
    private BasicHouseTradingSellService basicHouseTradingSellService;
    @Autowired
    private BasicHouseWaterService basicHouseWaterService;
    @Autowired
    private BasicHouseIntelligentService basicHouseIntelligentService;
    @Autowired
    private BasicHouseFaceStreetService basicHouseFaceStreetService;
    @Autowired
    private BasicHouseEquipmentService basicHouseEquipmentService;
    @Autowired
    private BasicHouseCorollaryEquipmentService basicHouseCorollaryEquipmentService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseDao basicHouseDao;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private CaseHouseTradingSellService caseHouseTradingSellService;
    @Autowired
    private CaseHouseTradingLeaseService caseHouseTradingLeaseService;
    @Autowired
    private CaseHouseRoomService caseHouseRoomService;
    @Autowired
    private CaseHouseEquipmentService caseHouseEquipmentService;
    @Autowired
    private CaseHouseFaceStreetService caseHouseFaceStreetService;
    @Autowired
    private CaseHouseIntelligentService caseHouseIntelligentService;
    @Autowired
    private CaseHouseWaterService caseHouseWaterService;
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
    private BasicHouseWaterDrainService basicHouseWaterDrainService;
    @Autowired
    private CaseHouseWaterDrainService caseHouseWaterDrainService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private DataDamagedDegreeService dataDamagedDegreeService;
    @Autowired
    private CaseHouseDamagedDegreeService caseHouseDamagedDegreeService;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

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
        if (basicHouse.getId() == null || basicHouse.getId().intValue() == 0) {
            basicHouse.setCreator(commonService.thisUserAccount());
            return basicHouseDao.addBasicHouse(basicHouse);
        } else {
            basicHouseDao.updateBasicHouse(basicHouse);
            return null;
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
        if (basicHouse.getPracticalUse() != null) {
            vo.setPracticalUseName(baseDataDicService.getNameById(basicHouse.getPracticalUse()));
        }
        if (basicHouse.getOrientation() != null) {
            vo.setOrientationName(baseDataDicService.getNameById(basicHouse.getOrientation()));
        }
        return vo;
    }


    /**
     * 清理无效数据
     *
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void clearInvalidData(Integer applyId) throws Exception {
        BasicHouse where = new BasicHouse();
        where.setApplyId(applyId);
        if (applyId == 0) {
            where.setCreator(commonService.thisUserAccount());
        }
        List<BasicHouse> houseList = basicHouseDao.basicHouseList(where);
        if (CollectionUtils.isEmpty(houseList)) {
            return;
        }
        BasicHouse house = houseList.get(0);
        BasicHouseTrading houseTrading = basicHouseTradingService.getTradingByHouseId(house.getId());

        List<SysAttachmentDto> sysAttachmentDtoList = null;

        BasicHouseTradingSell querySell = new BasicHouseTradingSell();
        BasicHouseTradingLease queryLease = new BasicHouseTradingLease();
        BasicHouseRoom queryRoom = new BasicHouseRoom();
        BasicHouseWater queryBasicHouseWater = new BasicHouseWater();
        BasicHouseIntelligent queryBasicHouseIntelligent = new BasicHouseIntelligent();
        BasicHouseFaceStreet queryBasicHouseFaceStreet = new BasicHouseFaceStreet();
        BasicHouseEquipment queryBasicHouseEquipment = new BasicHouseEquipment();
        BasicHouseCorollaryEquipment queryBasicHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
        BasicHouseWaterDrain queryBasicHouseWaterDrain = new BasicHouseWaterDrain();
        BasicHouseDamagedDegree queryBasicHouseDamagedDegree = new BasicHouseDamagedDegree();
        BasicHouseDamagedDegreeDetail queryBasicHouseDamagedDegreeDetail = new BasicHouseDamagedDegreeDetail();

        queryLease.setHouseId(house.getId());
        querySell.setHouseId(house.getId());
        queryRoom.setHouseId(house.getId());
        queryBasicHouseWater.setHouseId(house.getId());
        queryBasicHouseIntelligent.setHouseId(house.getId());
        queryBasicHouseFaceStreet.setHouseId(house.getId());
        queryBasicHouseEquipment.setHouseId(house.getId());
        queryBasicHouseCorollaryEquipment.setHouseId(house.getId());
        queryBasicHouseWaterDrain.setHouseId(house.getId());
        queryBasicHouseDamagedDegree.setHouseId(house.getId());
        queryBasicHouseDamagedDegreeDetail.setHouseId(house.getId());

        basicHouseTradingSellService.deleteBasicHouseTradingSell(querySell);
        basicHouseTradingLeaseService.deleteBasicHouseTradingLease(queryLease);
        List<BasicHouseRoom> basicHouseRoomList = basicHouseRoomService.basicHouseRoomList(queryRoom);
        basicHouseWaterService.deleteBasicHouseWater(queryBasicHouseWater);
        basicHouseIntelligentService.deleteBasicHouseIntelligent(queryBasicHouseIntelligent);
        basicHouseFaceStreetService.deleteBasicHouseFaceStreet(queryBasicHouseFaceStreet);
        basicHouseEquipmentService.deleteBasicHouseEquipment(queryBasicHouseEquipment);
        List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList = basicHouseCorollaryEquipmentService.basicHouseCorollaryEquipmentList(queryBasicHouseCorollaryEquipment);
        basicHouseWaterDrainService.deleteBasicHouseWaterDrain(queryBasicHouseWaterDrain);
        basicHouseDamagedDegreeService.deleteDamagedDegree(queryBasicHouseDamagedDegree);
        basicHouseDamagedDegreeService.deleteDamagedDegreeDetail(queryBasicHouseDamagedDegreeDetail);

        if (!ObjectUtils.isEmpty(basicHouseRoomList)) {
            basicHouseRoomList.forEach(oo -> {
                try {
                    basicHouseRoomService.deleteBasicHouseRoom(oo.getId());
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }

        if (!ObjectUtils.isEmpty(basicHouseCorollaryEquipmentList)) {
            basicHouseCorollaryEquipmentList.forEach(oo -> {
                try {
                    basicHouseCorollaryEquipmentService.deleteBasicHouseCorollaryEquipment(oo.getId());
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            });
        }

        //清理附件
        SysAttachmentDto queryFile = new SysAttachmentDto();
        queryFile.setTableId(house.getId());
        queryFile.setCreater(commonService.thisUserAccount());
        queryFile.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        sysAttachmentDtoList = baseAttachmentService.getAttachmentList(queryFile);
        if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                baseAttachmentService.deleteAttachment(sysAttachmentDto.getId());
            }
        }

        if (houseTrading != null) {
            //删除交易信息
            basicHouseTradingService.deleteBasicHouseTrading(houseTrading.getId());
        }
        //删除房屋信息
        basicHouseDao.deleteBasicHouse(house.getId());
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
        BasicHouse where = new BasicHouse();
        where.setApplyId(applyId);
        if (applyId == null || applyId == 0) {
            where.setCreator(commonService.thisUserAccount());
        }
        List<BasicHouse> basicHouses = basicHouseDao.basicHouseList(where);
        if (CollectionUtils.isEmpty(basicHouses)) {
            return null;
        }
        BasicHouse basicHouse = basicHouses.get(0);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouse.class.getSimpleName()), getBasicHouseVo(basicHouse));

        BasicHouseTrading houseTrading = basicHouseTradingService.getTradingByHouseId(basicHouse.getId());
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicHouseTrading.class.getSimpleName()), basicHouseTradingService.getBasicHouseTradingVo(houseTrading));
        return objectMap;
    }

    public BasicHouse getHouseByApplyId(Integer applyId)  {
        BasicHouse where = new BasicHouse();
        where.setApplyId(applyId);
        if (applyId == null || applyId == 0) {
            where.setCreator(commonService.thisUserAccount());
        }
        List<BasicHouse> basicHouses = basicHouseDao.basicHouseList(where);
        if (CollectionUtils.isEmpty(basicHouses)) {
            return null;
        }
        BasicHouse basicHouse = basicHouses.get(0);
        return basicHouse;
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

        //添加房屋完损度
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
        return objectMap;
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
        this.clearInvalidData(0);
        if (applyId != null) {
            this.clearInvalidData(applyId);
        }
        Map<String, Object> objectMap = new HashMap<String, Object>(2);
        CaseHouse caseHouse = caseHouseService.getCaseHouseById(caseHouseId);
        if (caseHouse == null) {
            return objectMap;
        }
        BasicHouse basicHouse = new BasicHouse();
        BeanUtils.copyProperties(caseHouse, basicHouse);
        basicHouse.setApplyId(applyId == null ? 0 : applyId);
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
        }

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    CaseEstateTagging caseEstateTagging = new CaseEstateTagging();
                    caseEstateTagging.setDataId(caseHouseId);
                    caseEstateTagging.setType(EstateTaggingTypeEnum.HOUSE.getKey());
                    List<CaseEstateTagging> caseEstateTaggings = caseEstateTaggingService.getCaseEstateTaggingList(caseEstateTagging);
                    basicEstateService.copyTaggingFromCase(caseEstateTaggings, applyId);
                } catch (Exception e1) {
                    logger.info("", e1);
                }
            }
        });

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                //附件拷贝
                SysAttachmentDto queryFile = new SysAttachmentDto();
                queryFile.setTableId(caseHouseId);
                queryFile.setTableName(FormatUtils.entityNameConvertToTableName(CaseHouse.class));
                try {
                    List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(queryFile);
                    if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                        for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                            SysAttachmentDto attachmentDto = new SysAttachmentDto();
                            attachmentDto.setTableId(basicHouse.getId());
                            attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
                            baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                        }
                    }
                } catch (Exception e1) {
                    logger.error(e1.getMessage(),e1);
                }
            }
        });


        CaseHouseTradingLease caseHouseTradingLease = new CaseHouseTradingLease();
        caseHouseTradingLease.setHouseId(caseHouseId);
        CaseHouseTradingSell caseHouseTradingSell = new CaseHouseTradingSell();
        caseHouseTradingSell.setHouseId(caseHouseId);
        CaseHouseRoom caseHouseRoom = new CaseHouseRoom();
        caseHouseRoom.setHouseId(caseHouseId);

        CaseHouseEquipment caseHouseEquipment = new CaseHouseEquipment();
        caseHouseEquipment.setHouseId(caseHouseId);
        CaseHouseFaceStreet caseHouseFaceStreet = new CaseHouseFaceStreet();
        caseHouseFaceStreet.setHouseId(caseHouseId);
        CaseHouseIntelligent caseHouseIntelligent = new CaseHouseIntelligent();
        caseHouseIntelligent.setHouseId(caseHouseId);
        CaseHouseWater caseHouseWater = new CaseHouseWater();
        caseHouseWater.setHouseId(caseHouseId);
        CaseHouseCorollaryEquipment caseHouseCorollaryEquipment = new CaseHouseCorollaryEquipment();
        caseHouseCorollaryEquipment.setHouseId(caseHouseId);
        CaseHouseWaterDrain caseHouseWaterDrain = new CaseHouseWaterDrain();
        caseHouseWaterDrain.setHouseId(caseHouseId);

        List<CaseHouseTradingSellVo> caseHouseTradingSellVos = caseHouseTradingSellService.caseHouseTradingSellList(caseHouseTradingSell, null);
        List<CaseHouseTradingLeaseVo> caseHouseTradingLeaseVos = caseHouseTradingLeaseService.caseHouseTradingLeaseList(caseHouseTradingLease, null);
        List<CaseHouseRoom> caseHouseRooms = caseHouseRoomService.getCaseHouseRoomList(caseHouseRoom);
        List<CaseHouseEquipment> caseHouseEquipments = caseHouseEquipmentService.getCaseHouseEquipmentList(caseHouseEquipment);
        List<CaseHouseFaceStreet> caseHouseFaceStreets = caseHouseFaceStreetService.getCaseHouseFaceStreetList(caseHouseFaceStreet);
        List<CaseHouseIntelligent> caseHouseIntelligents = caseHouseIntelligentService.getCaseHouseIntelligentList(caseHouseIntelligent);
        List<CaseHouseWater> caseHouseWaters = caseHouseWaterService.getCaseHouseWaterList(caseHouseWater);
        List<CaseHouseCorollaryEquipment> caseHouseCorollaryEquipments = caseHouseCorollaryEquipmentService.getCaseHouseCorollaryEquipmentList(caseHouseCorollaryEquipment);
        List<CaseHouseWaterDrain> caseHouseWaterDrainList = caseHouseWaterDrainService.getCaseHouseWaterDrainList(caseHouseWaterDrain);
        List<CaseHouseDamagedDegree> damagedDegreeList = caseHouseDamagedDegreeService.getDamagedDegreeListByHouseId(caseHouseId);

        if (!ObjectUtils.isEmpty(caseHouseTradingSellVos)) {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (CaseHouseTradingSellVo oo : caseHouseTradingSellVos) {
                            BasicHouseTradingSell sell = new BasicHouseTradingSell();
                            BeanUtils.copyProperties(oo, sell);
                            sell.setId(null);
                            sell.setHouseId(basicHouse.getId());
                            sell.setCreator(commonService.thisUserAccount());
                            sell.setGmtCreated(null);
                            sell.setGmtModified(null);
                            basicHouseTradingSellService.saveAndUpdateBasicHouseTradingSell(sell);
                        }
                    } catch (Exception e1) {

                    }
                }
            });

        }
        if (!ObjectUtils.isEmpty(caseHouseTradingLeaseVos)) {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (CaseHouseTradingLeaseVo oo : caseHouseTradingLeaseVos) {
                            BasicHouseTradingLease lease = new BasicHouseTradingLease();
                            BeanUtils.copyProperties(oo, lease);
                            lease.setId(null);
                            lease.setHouseId(basicHouse.getId());
                            lease.setCreator(commonService.thisUserAccount());
                            lease.setGmtCreated(null);
                            lease.setGmtModified(null);
                            basicHouseTradingLeaseService.saveAndUpdateBasicHouseTradingLease(lease);
                        }
                    } catch (Exception e1) {

                    }
                }
            });

        }
        if (!ObjectUtils.isEmpty(caseHouseRooms)) {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
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
                    } catch (Exception e1) {

                    }
                }
            });

        }
        if (!ObjectUtils.isEmpty(caseHouseEquipments)) {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (CaseHouseEquipment oo : caseHouseEquipments) {
                            BasicHouseEquipment po = new BasicHouseEquipment();
                            BeanUtils.copyProperties(oo, po);
                            po.setId(null);
                            po.setHouseId(basicHouse.getId());
                            po.setCreator(commonService.thisUserAccount());
                            po.setGmtCreated(null);
                            po.setGmtModified(null);
                            basicHouseEquipmentService.saveAndUpdateBasicHouseEquipment(po);
                        }
                    } catch (Exception e1) {

                    }
                }
            });

        }
        if (!ObjectUtils.isEmpty(caseHouseFaceStreets)) {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (CaseHouseFaceStreet oo : caseHouseFaceStreets) {
                            BasicHouseFaceStreet po = new BasicHouseFaceStreet();
                            BeanUtils.copyProperties(oo, po);
                            po.setId(null);
                            po.setHouseId(basicHouse.getId());
                            po.setCreator(commonService.thisUserAccount());
                            po.setGmtCreated(null);
                            po.setGmtModified(null);
                            basicHouseFaceStreetService.saveAndUpdateBasicHouseFaceStreet(po);
                        }
                    } catch (Exception e1) {

                    }
                }
            });

        }
        if (!ObjectUtils.isEmpty(caseHouseIntelligents)) {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (CaseHouseIntelligent oo : caseHouseIntelligents) {
                            BasicHouseIntelligent po = new BasicHouseIntelligent();
                            BeanUtils.copyProperties(oo, po);
                            po.setId(null);
                            po.setHouseId(basicHouse.getId());
                            po.setCreator(commonService.thisUserAccount());
                            po.setGmtCreated(null);
                            po.setGmtModified(null);
                            basicHouseIntelligentService.saveAndUpdateBasicHouseIntelligent(po);
                        }
                    } catch (Exception e1) {

                    }
                }
            });

        }
        if (!ObjectUtils.isEmpty(caseHouseWaters)) {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (CaseHouseWater oo : caseHouseWaters) {
                            BasicHouseWater po = new BasicHouseWater();
                            BeanUtils.copyProperties(oo, po);
                            po.setId(null);
                            po.setHouseId(basicHouse.getId());
                            po.setCreator(commonService.thisUserAccount());
                            po.setGmtCreated(null);
                            po.setGmtModified(null);
                            basicHouseWaterService.saveAndUpdateBasicHouseWater(po);
                        }
                    } catch (Exception e1) {

                    }
                }
            });

        }
        if (!ObjectUtils.isEmpty(caseHouseCorollaryEquipments)) {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
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
                                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                                    attachmentDto.setTableId(id);
                                    attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
                                    baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                                }
                            }
                        }
                    } catch (Exception e1) {

                    }
                }
            });

        }
        if (!ObjectUtils.isEmpty(caseHouseWaterDrainList)) {
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    caseHouseWaterDrainList.parallelStream().forEach(oo -> {
                        try {
                            BasicHouseWaterDrain basicHouseWaterDrain = new BasicHouseWaterDrain();
                            BeanUtils.copyProperties(oo, basicHouseWaterDrain);
                            basicHouseWaterDrain.setId(null);
                            basicHouseWaterDrain.setHouseId(basicHouse.getId());
                            basicHouseWaterDrain.setCreator(commonService.thisUserAccount());
                            basicHouseWaterDrain.setGmtCreated(null);
                            basicHouseWaterDrain.setGmtModified(null);
                            basicHouseWaterDrainService.saveAndUpdateBasicHouseWaterDrain(basicHouseWaterDrain);
                        } catch (Exception e1) {
                            logger.error("", e1);
                        }
                    });
                }
            });

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
                logger.error(e1.getMessage(),e1);
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
     * 添加房屋
     *
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addHouse(String houseNumber, Integer applyId) throws Exception {
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

        //添加房屋完损度
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
        return objectMap;
    }
}
