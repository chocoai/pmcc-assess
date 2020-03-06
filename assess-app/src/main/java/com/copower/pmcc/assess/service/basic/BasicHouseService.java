package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyFormNameEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseDamagedDegreeVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseTradingVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseVo;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.assist.ResidueRatioService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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
import com.google.common.collect.Lists;
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
public class BasicHouseService extends BasicEntityAbstract {
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
    private BasicHouseRoomDecorateService basicHouseRoomDecorateService;
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private DataDamagedDegreeService dataDamagedDegreeService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateTaggingDao basicEstateTaggingDao;
    @Autowired
    private BasicApplyDao basicApplyDao;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private ResidueRatioService residueRatioService;
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
     * 删除房屋数据
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
    public List<BasicHouse> getBasicHouseList(BasicHouse basicHouse) {
        return basicHouseDao.getBasicHouseList(basicHouse);
    }

    public List<BasicHouse> getHousesByUnitId(Integer unitId) {
        if (unitId == null) return null;
        BasicHouse basicHouse = new BasicHouse();
        basicHouse.setUnitId(unitId);
        return basicHouseDao.getBasicHouseList(basicHouse);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouse basicHouse) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouse> basicHouseList = basicHouseDao.getBasicHouseList(basicHouse);
        List<BasicHouseVo> transform = LangUtils.transform(basicHouseList, o -> getBasicHouseVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(transform) ? new ArrayList<BasicHouseVo>(10) : transform);
        return vo;
    }

    public List<CustomCaseEntity> autoCompleteCaseHouse(String houseNumber, Integer unitId) throws Exception {
        if (StringUtils.isBlank(houseNumber) || unitId == null) return null;
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
        if (basicHouse.getOrientation() != null) {
            vo.setOrientationName(baseDataDicService.getNameById(basicHouse.getOrientation()));
        }
        if (basicHouse.getSpatialDistribution() != null) {
            vo.setSpatialDistributionName(baseDataDicService.getNameById(basicHouse.getSpatialDistribution()));
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
    @Override
    public void clearInvalidData(Integer houseId) throws Exception {
        if (houseId == null) return;
        clearInvalidChildData(houseId);//清理从表数据

        //清除标记
        BasicEstateTagging where = new BasicEstateTagging();
        where.setTableId(houseId);
        where.setType(BasicFormClassifyEnum.HOUSE.getKey());
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
    @Override
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
        List<BasicHouse> basicHouses = basicHouseDao.getBasicHouseList(where);
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

    @Override
    public Integer saveAndUpdate(Object o, Boolean updateNull) {
        if (o == null) return null;
        BasicHouse basicHouse = (BasicHouse) o;
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

    @Override
    public Integer saveAndUpdateByFormData(String formData, Integer planDetailsId) throws Exception {
        if (StringUtils.isBlank(formData)) return null;
        JSONObject jsonObject = JSON.parseObject(formData);
        BasicHouse basicHouse = null;
        String jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_HOUSE.getVar());
        if (StringUtils.isNotEmpty(jsonContent)) {
            basicHouse = JSONObject.parseObject(jsonContent, BasicHouse.class);
            //原来数据做记录,将老数据复制一条
            BasicHouse oldBasicHouse = basicHouseDao.getBasicHouseById(basicHouse.getId());
            if (oldBasicHouse != null && StringUtils.isNotBlank(oldBasicHouse.getHouseNumber())) {
                BasicHouse version = (BasicHouse) copyBasicEntity(oldBasicHouse.getId(), null, false);
                version.setRelevanceId(oldBasicHouse.getId());
                version.setEstateId(0);
                version.setBuildingId(0);
                version.setUnitId(0);
                saveAndUpdate(version, false);
            }

            if (basicHouse != null) {
                BasicUnit basicUnit = basicUnitService.getBasicUnitById(basicHouse.getUnitId());
                if (basicUnit != null)
                    basicHouse.setFullName(basicUnit.getFullName() + basicHouse.getHouseNumber());
                Integer houseId = saveAndUpdate(basicHouse, true);
                BasicApplyBatchDetail houseDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicHouse.class), basicHouse.getId());
                if (houseDetail != null) {
                    houseDetail.setName(basicHouse.getHouseNumber());
                    houseDetail.setDisplayName(basicHouse.getHouseNumber());
                    basicApplyBatchDetailService.saveBasicApplyBatchDetail(houseDetail);

                    BasicApply where = new BasicApply();
                    where.setPlanDetailsId(planDetailsId);
                    where.setBasicHouseId(houseDetail.getTableId());
                    BasicApply basicApply = basicApplyService.getBasicApply(where);
                    Map<BasicFormClassifyEnum, BasicApplyBatchDetail> map = basicApplyBatchDetailService.getApplyBatchDetailMap(houseDetail);
                    basicApply.setArea(basicHouse.getArea());
                    BasicApplyBatchDetail unitBatchDetail = map.get(BasicFormClassifyEnum.UNIT);//单元
                    BasicApplyBatchDetail buildBatchDetail = map.get(BasicFormClassifyEnum.BUILDING); //楼栋
                    BasicApplyBatchDetail estateBatchDetail = map.get(BasicFormClassifyEnum.ESTATE);//楼盘
                    basicApply.setName(basicApplyService.getFullName(estateBatchDetail.getName(), buildBatchDetail.getName(), unitBatchDetail.getName(), houseDetail.getName()));
                    basicApplyService.saveBasicApply(basicApply);
                }
                //交易信息
                jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_TRADING.getVar());
                BasicHouseTrading basicTrading = JSONObject.parseObject(jsonContent, BasicHouseTrading.class);
                if (basicTrading != null) {
                    BasicHouseTrading houseTradingOld = basicHouseTradingService.getTradingByHouseId(basicHouse.getId());
                    basicTrading.setId(houseTradingOld.getId());
                    basicTrading.setHouseId(houseId);
                    basicHouseTradingService.saveAndUpdateBasicHouseTrading(basicTrading, true);
                }
                //完损度
                jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_DAMAGED_DEGREE.getVar());
                List<BasicHouseDamagedDegree> damagedDegreeList = JSONObject.parseArray(jsonContent, BasicHouseDamagedDegree.class);
                if (!CollectionUtils.isEmpty(damagedDegreeList)) {
                    for (BasicHouseDamagedDegree degree : damagedDegreeList) {
                        Integer category = degree.getCategory();
                        BasicHouseDamagedDegree damagedDegree = basicHouseDamagedDegreeService.getDamagedDegreeByHouseIdAndCategory(houseId, category);
                        damagedDegree.setEntityCondition(degree.getEntityCondition());
                        damagedDegree.setEntityConditionContent(degree.getEntityConditionContent());
                        damagedDegree.setScore(degree.getScore());
                        basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(damagedDegree, false);
                    }
                    //写入成新率
                    String newDegree = residueRatioService.getObservationalRatio(basicHouse.getId());
                    basicHouse.setNewDegree(newDegree);
                    saveAndUpdate(basicHouse, false);
                }
                return basicHouse.getId();
            }
        }
        return null;
    }

    @Override
    public Object getBasicEntityById(Integer id) {
        return getBasicHouseById(id);
    }

    @Override
    public Object copyBasicEntity(Integer sourceId, Integer targetId, Boolean containChild) throws Exception {
        return copyBasicEntityIgnore(sourceId, targetId, containChild, null);
    }

    @Override
    public Object copyBasicEntityIgnore(Integer sourceId, Integer targetId, Boolean containChild, List<String> ignoreList) throws Exception {
        BasicHouse sourceBasicHouse = getBasicHouseById(sourceId);
        if (sourceBasicHouse == null) return null;
        BasicHouse targetBasicHouse = getBasicHouseById(targetId);
        if (CollectionUtils.isEmpty(ignoreList)) ignoreList = Lists.newArrayList();
        ignoreList.addAll(Lists.newArrayList(BaseConstant.ASSESS_IGNORE_ARRAY));
        if (targetBasicHouse == null) {
            targetBasicHouse = new BasicHouse();
            BeanUtils.copyProperties(sourceBasicHouse, targetBasicHouse, ignoreList.toArray(new String[ignoreList.size()]));
            targetBasicHouse.setCreator(commonService.thisUserAccount());
        } else {
            BeanUtils.copyProperties(sourceBasicHouse, targetBasicHouse, ignoreList.toArray(new String[ignoreList.size()]));
        }
        this.saveAndUpdate(targetBasicHouse, true);
        BasicHouseTrading sourceBasicHouseTrading = basicHouseTradingService.getTradingByHouseId(sourceId);
        if (sourceBasicHouseTrading != null) {
            BasicHouseTrading targetBasicHouseTrading = basicHouseTradingService.getTradingByHouseId(targetId);
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
        if (targetId != null && targetId > 0) {//目标数据已存在，先清理目标数据的从表数据
            clearInvalidChildData(targetId);

            SysAttachmentDto where = new SysAttachmentDto();
            where.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
            where.setTableId(targetId);
            baseAttachmentService.deleteAttachmentByDto(where);
        }
        baseAttachmentService.copyFtpAttachments(FormatUtils.entityNameConvertToTableName(BasicHouse.class), sourceId, targetBasicHouse.getId());
        if (containChild) {
            StringBuilder sqlBuilder = new StringBuilder();
            SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
            HashMap<String, String> map = Maps.newHashMap();
            map.put("house_id", String.valueOf(targetBasicHouse.getId()));
            map.put("creator", commonService.thisUserAccount());
            synchronousDataDto.setFieldDefaultValue(map);
            synchronousDataDto.setWhereSql("house_id=" + sourceId);
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
            basicHouseRoom.setHouseId(sourceId);
            BasicHouseCorollaryEquipment caseHouseCorollaryEquipment = new BasicHouseCorollaryEquipment();
            caseHouseCorollaryEquipment.setHouseId(sourceId);
            List<BasicHouseRoom> basicHouseRooms = basicHouseRoomService.basicHouseRoomList(basicHouseRoom);
            List<BasicHouseCorollaryEquipment> caseHouseCorollaryEquipments = basicHouseCorollaryEquipmentService.basicHouseCorollaryEquipmentList(caseHouseCorollaryEquipment);
            List<BasicHouseDamagedDegree> damagedDegreeList = basicHouseDamagedDegreeService.getDamagedDegreeList(sourceId);


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
}
