package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyFormNameEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.common.enums.basic.ExamineCommonQuoteFieldEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateLandStateDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.input.basic.BasicFormClassifyParamDto;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVo;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.data.DataDeveloperService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicEstateService extends BasicEntityAbstract {
    @Autowired
    private PublicService publicService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicEstateDao basicEstateDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateLandStateDao basicEstateLandStateDao;
    @Autowired
    private BasicEstateParkingService basicEstateParkingService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private DataDeveloperService dataDeveloperService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private BasicEstateTaggingDao basicEstateTaggingDao;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;
    @Autowired
    private BasicCommonQuoteFieldInfoService basicCommonQuoteFieldInfoService;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicEstate getBasicEstateById(Integer id) {
        return basicEstateDao.getBasicEstateById(id);
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicEstate(Integer id) throws Exception {
        return basicEstateDao.deleteBasicEstate(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicEstate
     * @return
     * @throws Exception
     */
    public List<BasicEstate> getBasicEstateList(BasicEstate basicEstate) throws Exception {
        return basicEstateDao.getBasicEstateList(basicEstate);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicEstate basicEstate) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstate> basicEstateList = basicEstateDao.getBasicEstateList(basicEstate);
        List<BasicEstateVo> transform = LangUtils.transform(basicEstateList, o -> getBasicEstateVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(transform) ? new ArrayList<BasicEstateVo>(10) : transform);
        return vo;
    }

    public BootstrapTableVo getCaseEstateList(String name, String province, String city, String district) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstate> basicEstateList = basicEstateDao.getCaseEstateList(name, province, city, district);
        List<BasicEstateVo> transform = LangUtils.transform(basicEstateList, o -> getBasicEstateVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(transform) ? new ArrayList<BasicEstateVo>(10) : transform);
        return vo;
    }

    public List<CustomCaseEntity> autoCompleteCaseEstate(String name, String province, String city) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(province) || StringUtils.isBlank(city)) return null;
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CustomCaseEntity> caseEstateList = basicEstateDao.getLatestVersionEstateList(name, province, city, null);
        return caseEstateList;
    }

    public BasicEstateVo getBasicEstateVo(BasicEstate basicEstate) {
        if (basicEstate == null) {
            return null;
        }
        BasicEstateVo vo = new BasicEstateVo();
        BeanUtils.copyProperties(basicEstate, vo);
        if (StringUtils.isNotBlank(basicEstate.getProvince())) {
            //省
            vo.setProvinceName(erpAreaService.getSysAreaName(basicEstate.getProvince()));
        }
        if (StringUtils.isNotBlank(basicEstate.getCity())) {
            //市或者县
            vo.setCityName(erpAreaService.getSysAreaName(basicEstate.getCity()));
        }
        if (StringUtils.isNotBlank(basicEstate.getDistrict())) {
            //县
            vo.setDistrictName(erpAreaService.getSysAreaName(basicEstate.getDistrict()));
        }
        vo.setPositionName(baseDataDicService.getNameById(basicEstate.getPosition()));
        if (basicEstate.getBlockId() != null) {
            DataBlock dataBlock = dataBlockService.getDataBlockById(basicEstate.getBlockId());
            if (dataBlock != null) {
                vo.setBlockName(dataBlock.getName());
            }
        } else {
            vo.setBlockName(basicEstate.getBlockName());
        }
        vo.setSupplyGasName(baseDataDicService.getNameById(basicEstate.getSupplyGas()));
        vo.setSupplyPowerName(baseDataDicService.getNameById(basicEstate.getSupplyPower()));
        vo.setSupplyWaterName(baseDataDicService.getNameById(basicEstate.getSupplyWater()));
        vo.setDrainWaterName(baseDataDicService.getNameById(basicEstate.getDrainWater()));
        vo.setSupplyHeatingName(baseDataDicService.getNameById(basicEstate.getSupplyHeating()));
        vo.setSupplyCommunicationName(baseDataDicService.getNameById(basicEstate.getSupplyCommunication()));
        vo.setSupplyRoadName(baseDataDicService.getNameById(basicEstate.getSupplyRoad()));
        if (NumberUtils.isNumber(basicEstate.getDeveloper())) {
            DataDeveloper dataDeveloper = dataDeveloperService.getByDataDeveloperId(Integer.parseInt(basicEstate.getDeveloper()));
            if (dataDeveloper != null) {
                vo.setDeveloper(dataDeveloper.getName());
                vo.setDeveloperName(dataDeveloper.getName());
                vo.setDataDeveloper(dataDeveloperService.getDataDeveloperVo(dataDeveloper));
            }
        }
        if (StringUtils.isNotBlank(basicEstate.getInfrastructure())) {
            List<Integer> ids = FormatUtils.transformString2Integer(basicEstate.getInfrastructure());
            if (org.apache.commons.collections.CollectionUtils.isNotEmpty(ids)) {
                List<String> stringList = Lists.newArrayList();
                ids.forEach(o -> stringList.add(baseDataDicService.getNameById(o)));
                vo.setInfrastructureName(StringUtils.join(stringList, "，"));
            }
        }
        vo.setInfrastructureCompletenessName(baseDataDicService.getNameById(basicEstate.getInfrastructureCompleteness()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicEstate.getCreator()));
        return vo;
    }


    /**
     * 清理无效数据
     *
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void clearInvalidData(Integer estateId) throws Exception {
        if (estateId == null) return;
        clearInvalidChildData(estateId);

        //清除标记
        BasicEstateTagging where = new BasicEstateTagging();
        where.setTableId(estateId);
        where.setType(BasicFormClassifyEnum.ESTATE.getKey());
        basicEstateTaggingDao.removeBasicEstateTagging(where);

        StringBuilder sqlBulder = new StringBuilder();
        String baseSql = "update %s set bis_delete=1 where estate_id=%s;";
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicEstateLandState.class), estateId));
        sqlBulder.append(String.format("update %s set bis_delete=1 where id=%s;", FormatUtils.entityNameConvertToTableName(BasicEstate.class), estateId));
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void clearInvalidChildData(Integer estateId) throws Exception {
        BasicEstateLandState estateLandState = basicEstateLandStateService.getLandStateByEstateId(estateId);

        StringBuilder sqlBulder = new StringBuilder();
        String baseSql = "update %s set bis_delete=1 where estate_id=%s;";

        String baseLandSql = "update %s set bis_delete=1 where land_id=%s;";

        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class), estateId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicEstateParking.class), estateId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class), estateId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class), estateId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class), estateId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class), estateId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class), estateId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class), estateId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class), estateId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class), estateId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicEstateStreetInfo.class), estateId));
        if (estateLandState != null) {
            sqlBulder.append(String.format(baseLandSql, FormatUtils.entityNameConvertToTableName(BasicEstateLandCategoryInfo.class), estateLandState.getId()));
        }
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());
    }

    /**
     * 获取数据
     *
     * @param applyId
     * @return
     * @throws Exception
     */
    public Map<String, Object> getBasicEstateMapByApplyId(Integer applyId) throws Exception {
        Map<String, Object> objectMap = Maps.newHashMap();
        BasicEstate where = new BasicEstate();
        where.setApplyId(applyId);
        if (applyId == null || applyId == 0)
            where.setCreator(commonService.thisUserAccount());
        List<BasicEstate> basicEstates = basicEstateDao.getBasicEstateList(where);
        if (CollectionUtils.isEmpty(basicEstates)) return null;
        BasicEstate basicEstate = basicEstates.get(0);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstate.class.getSimpleName()), getBasicEstateVo(basicEstate));

        BasicEstateLandState estateLandState = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
        BasicEstateLandStateVo basicEstateLandStateVo = basicEstateLandStateService.getBasicEstateLandStateVo(estateLandState);
        if (basicEstateLandStateVo == null) {
            basicEstateLandStateVo = new BasicEstateLandStateVo();
            basicEstateLandStateVo.setEstateId(estateLandState.getId());
        }
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstateLandState.class.getSimpleName()), basicEstateLandStateVo);
        return objectMap;
    }

    public BasicEstate getBasicEstateByApplyId(Integer applyId) {
        if (applyId == null) return null;
        return getBasicEstateByBasicApply(basicApplyService.getByBasicApplyId(applyId));
    }

    public BasicEstate getBasicEstateByBasicApply(BasicApply basicApply) {
        if (basicApply == null) return null;
        String structuralInfo = basicApply.getStructuralInfo();
        List<KeyValueDto> keyValueDtos = JSON.parseArray(structuralInfo, KeyValueDto.class);
        for (KeyValueDto keyValueDto : keyValueDtos) {
            if (BasicFormClassifyEnum.ESTATE.getKey().equals(keyValueDto.getKey())) {
                return getBasicEstateById(Integer.valueOf(keyValueDto.getValue()));
            }
        }
        return null;
    }


    /**
     * 添加楼盘及土地基本信息
     *
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> addEstateAndLandstate(String estateName, String province, String city) throws Exception {
        this.clearInvalidData(0);
        Map<String, Object> objectMap = Maps.newHashMap();

        BasicEstate basicEstate = new BasicEstate();
        basicEstate.setName(estateName);
        basicEstate.setProvince(province);
        basicEstate.setCity(city);
        basicEstate.setApplyId(0);
        basicEstate.setCreator(commonService.thisUserAccount());
        basicEstateDao.addBasicEstate(basicEstate);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstate.class.getSimpleName()), getBasicEstateVo(basicEstate));

        BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
        basicEstateLandState.setName(estateName);
        basicEstateLandState.setEstateId(basicEstate.getId());
        basicEstateLandState.setCreator(commonService.thisUserAccount());
        basicEstateLandStateDao.addBasicEstateLandState(basicEstateLandState);
        objectMap.put(FormatUtils.toLowerCaseFirstChar(BasicEstateLandState.class.getSimpleName()), basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandState));
        return objectMap;
    }


    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Map<String, Object> getBasicEstateMapById(Integer id) throws Exception {
        Map<String, Object> objectMap = Maps.newHashMap();

        BasicEstate basicEstate = this.getBasicEstateById(id);
        objectMap.put(StringUtils.uncapitalize(BasicEstate.class.getSimpleName()), getBasicEstateVo(basicEstate));
        BasicEstateLandState estateLandState = basicEstateLandStateService.getLandStateByEstateId(basicEstate.getId());
        objectMap.put(StringUtils.uncapitalize(BasicEstateLandState.class.getSimpleName()), basicEstateLandStateService.getBasicEstateLandStateVo(estateLandState));
        return objectMap;
    }

    /**
     * 案例库中是否已存在该楼盘
     *
     * @param province
     * @param city
     * @param name
     * @return
     */
    public Boolean isExistEstateCase(String province, String city, String name) {
        return basicEstateDao.getBasicEstateCount(province, city, name) > 0;
    }

    @Override
    public Integer saveAndUpdate(Object o, Boolean updateNull) {
        if (o == null) return null;
        BasicEstate basicEstate = (BasicEstate) o;
        if (basicEstate.getId() == null || basicEstate.getId().intValue() == 0) {
            basicEstate.setCreator(commonService.thisUserAccount());
            Integer id = basicEstateDao.addBasicEstate(basicEstate);
            return id;
        } else {
            if (updateNull) {
                BasicEstate estate = basicEstateDao.getBasicEstateById(basicEstate.getId());
                if (estate != null) {
                    basicEstate.setBisDelete(estate.getBisDelete());
                    basicEstate.setCreator(estate.getCreator());
                    basicEstate.setGmtCreated(estate.getGmtCreated());
                    basicEstate.setGmtModified(DateUtils.now());
                }
            }
            basicEstateDao.updateBasicEstate(basicEstate, updateNull);
            return basicEstate.getId();
        }
    }

    @Override
    public Integer saveAndUpdateByFormData(String formData, Integer planDetailsId) throws Exception {
        if (StringUtils.isBlank(formData)) return null;
        JSONObject jsonObject = JSON.parseObject(formData);
        String jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATE.getVar());
        BasicEstate basicEstate = JSONObject.parseObject(jsonContent, BasicEstate.class);
        //原来数据做记录,将老数据复制一条
        BasicEstate oldBasicEstate = getBasicEstateById(basicEstate.getId());
        if (oldBasicEstate != null && StringUtils.isNotBlank(oldBasicEstate.getName())) {
            BasicEstate version = (BasicEstate) copyBasicEntity(oldBasicEstate.getId(), null, false);
            version.setRelevanceId(oldBasicEstate.getId());
            saveAndUpdate(version, false);
        }

        if (basicEstate != null) {
            basicEstate.setClassify(oldBasicEstate.getClassify());
            basicEstate.setType(oldBasicEstate.getType());
            saveAndUpdate(basicEstate, true);

            if (basicEstate.getId() != null) {
                BasicEstateLandState basicEstateLandState = null;
                String string = jsonObject.getString(BasicApplyFormNameEnum.BASIC_ESTATELAND_STATE.getVar());
                basicEstateLandState = JSONObject.parseObject(string, BasicEstateLandState.class);
                if (basicEstateLandState != null) {
                    basicEstateLandState.setLandLevelContent(org.apache.commons.lang.StringUtils.isNotEmpty(basicEstateLandState.getLandLevelContent()) ? basicEstateLandState.getLandLevelContent() : null);
                    basicEstateLandState.setEstateId(basicEstate.getId());
                    basicEstateLandStateService.saveAndUpdateBasicEstateLandState(basicEstateLandState, true);
                }
            }
            BasicApplyBatchDetail estateDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicEstate.class), basicEstate.getId());
            if (estateDetail != null) {
                estateDetail.setName(basicEstate.getName());
                estateDetail.setDisplayName(basicEstate.getName());
                basicApplyBatchDetailService.saveBasicApplyBatchDetail(estateDetail);
                BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(estateDetail.getApplyBatchId());
                if (basicApplyBatch != null) {
                    basicApplyBatch.setEstateId(basicEstate.getId());
                    basicApplyBatch.setEstateName(basicEstate.getName());
                    basicApplyBatchService.saveBasicApplyBatch(basicApplyBatch);
                }

                //添加公共引用
                basicCommonQuoteFieldInfoService.setValue(estateDetail.getApplyBatchId(), estateDetail.getType(), ExamineCommonQuoteFieldEnum.OPEN_TIME_ENUM, DateUtils.formatDate(basicEstate.getOpenTime()));
                basicCommonQuoteFieldInfoService.setValue(estateDetail.getApplyBatchId(), estateDetail.getType(), ExamineCommonQuoteFieldEnum.COVER_AN_AREA, String.valueOf(basicEstate.getCoverAnArea()));
                List<BasicEstateLandCategoryInfo> basicEstateLandCategoryInfos = basicEstateLandCategoryInfoService.getListByEstateId(basicEstate.getId());
                if (!CollectionUtils.isEmpty(basicEstateLandCategoryInfos)) {
                    BigDecimal landUseYear = basicEstateLandCategoryInfos.get(0).getLandUseYear();
                    basicCommonQuoteFieldInfoService.setValue(estateDetail.getApplyBatchId(), estateDetail.getType(), ExamineCommonQuoteFieldEnum.LAND_USE_YEAR_ENUM, String.valueOf(landUseYear));
                }
            }
        }
        return basicEstate.getId();
    }

    @Override
    public Object getBasicEntityById(Integer id) {
        return basicEstateDao.getBasicEstateById(id);
    }

    @Override
    public Object copyBasicEntity(Integer sourceId, Integer targetId, Boolean containChild) throws Exception {
        return copyBasicEntityIgnore(sourceId, targetId, containChild, null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object copyBasicEntityIgnore(Integer sourceId, Integer targetId, Boolean containChild, List<String> ignoreList) throws Exception {
        if (sourceId == null) return null;
        BasicEstate sourceBasicEstate = getBasicEstateById(sourceId);
        if (sourceBasicEstate == null) return null;
        BasicEstate targetBasicEstate = (targetId == null || targetId <= 0) ? null : getBasicEstateById(targetId);
        if (CollectionUtils.isEmpty(ignoreList)) ignoreList = Lists.newArrayList();
        ignoreList.addAll(Lists.newArrayList(BaseConstant.ASSESS_IGNORE_ARRAY));
        if (targetBasicEstate == null) {
            targetBasicEstate = new BasicEstate();
            BeanUtils.copyProperties(sourceBasicEstate, targetBasicEstate, ignoreList.toArray(new String[ignoreList.size()]));
            targetBasicEstate.setCreator(commonService.thisUserAccount());
        } else {
            BeanUtils.copyProperties(sourceBasicEstate, targetBasicEstate, ignoreList.toArray(new String[ignoreList.size()]));
        }
        this.saveAndUpdate(targetBasicEstate, true);

        //土地信息
        BasicEstateLandState sourceEstateLandState = basicEstateLandStateService.getLandStateByEstateId(sourceId);
        if (sourceEstateLandState != null) {
            BasicEstateLandState targeEstateLandState = basicEstateLandStateService.getLandStateByEstateId(targetBasicEstate.getId());
            if (targeEstateLandState == null) {
                targeEstateLandState = new BasicEstateLandState();
                BeanUtils.copyProperties(sourceEstateLandState, targeEstateLandState);
                targeEstateLandState.setEstateId(targetBasicEstate.getId());
                targeEstateLandState.setId(null);
                targeEstateLandState.setCreator(commonService.thisUserAccount());
                targeEstateLandState.setGmtCreated(null);
                targeEstateLandState.setGmtModified(null);
            } else {
                BeanUtils.copyProperties(sourceEstateLandState, targeEstateLandState, "id");
                targeEstateLandState.setEstateId(targetBasicEstate.getId());
            }
            basicEstateLandStateService.saveAndUpdateBasicEstateLandState(targeEstateLandState, true);
            basicEstateLandCategoryInfoService.copy(sourceEstateLandState.getId(), targeEstateLandState.getId());
        }
        if (targetId != null && targetId > 0) {//目标数据已存在，先清理目标数据的从表数据
            clearInvalidChildData(targetId);

            SysAttachmentDto where = new SysAttachmentDto();
            where.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
            where.setTableId(targetId);
            baseAttachmentService.deleteAttachmentByDto(where);
        }
        //附件拷贝
        baseAttachmentService.copyFtpAttachments(FormatUtils.entityNameConvertToTableName(BasicEstate.class), sourceId, targetBasicEstate.getId());
        //标记tagging
        basicEstateTaggingService.copyTagging(BasicFormClassifyEnum.ESTATE, sourceId, targetBasicEstate.getId());
        if (containChild) { //处理从表数据
            try {  //停车场数据
                BasicEstateParking estateParking = new BasicEstateParking();
                estateParking.setEstateId(sourceId);
                List<BasicEstateParking> oldBasicEstateParkings = basicEstateParkingService.basicEstateParkingList(estateParking);
                if (!ObjectUtils.isEmpty(oldBasicEstateParkings)) {
                    for (BasicEstateParking oldBasicEstateParking : oldBasicEstateParkings) {
                        BasicEstateParking queryBasicEstateParking = new BasicEstateParking();
                        BeanCopyHelp.copyPropertiesIgnoreNull(oldBasicEstateParking, queryBasicEstateParking);
                        queryBasicEstateParking.setEstateId(targetBasicEstate.getId());
                        queryBasicEstateParking.setId(null);
                        queryBasicEstateParking.setGmtCreated(null);
                        queryBasicEstateParking.setGmtModified(null);
                        queryBasicEstateParking.setCreator(commonService.thisUserAccount());
                        Integer parkingId = basicEstateParkingService.saveAndUpdateBasicEstateParking(queryBasicEstateParking, false);

                        baseAttachmentService.copyFtpAttachments(FormatUtils.entityNameConvertToTableName(BasicEstateParking.class), oldBasicEstateParking.getId(), parkingId);
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

            StringBuilder sqlBuilder = new StringBuilder();
            SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
            HashMap<String, String> map = Maps.newHashMap();
            map.put("estate_id", String.valueOf(targetBasicEstate.getId()));
            map.put("creator", commonService.thisUserAccount());
            synchronousDataDto.setFieldDefaultValue(map);
            synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
            synchronousDataDto.setWhereSql("estate_id=" + sourceId);
            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//通信网络sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateSupply.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//供应信息sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingTraffic.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//交通信息sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMedical.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//医养信息sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//原材料信息sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingLeisurePlace.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//休闲场所信息sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//金融服务信息sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//环境因素信息sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicMatchingEducation.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//教育信息sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicEstateStreetInfo.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicEstateStreetInfo.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼盘 街道号sql

            ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        }
        return targetBasicEstate;
    }

    @Override
    public List<BasicFormClassifyEnum> getLowerFormClassifyList() {
        List<BasicFormClassifyEnum> list = Lists.newArrayList();
        list.add(BasicFormClassifyEnum.BUILDING);
        list.add(BasicFormClassifyEnum.BUILDING_MONOLAYER);
        list.add(BasicFormClassifyEnum.BUILDING_BASE);
        return list;
    }

    @Override
    public ModelAndView getEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/realEstate/estate");
        modelAndView.addObject("basicEstate", getBasicEstateById(basicFormClassifyParamDto.getTbId()));
        modelAndView.addObject("basicEstateLandState", basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateService.getLandStateByEstateId(basicFormClassifyParamDto.getTbId())));
        List<CrmBaseDataDicDto> unitPropertiesList = projectInfoService.getUnitPropertiesList();
        modelAndView.addObject("unitPropertiesList", unitPropertiesList);
        return modelAndView;
    }

    @Override
    public ModelAndView getDetailModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/realEstate/detail/estate");
        modelAndView.addObject("basicEstate", getBasicEstateVo(getBasicEstateById(basicFormClassifyParamDto.getTbId())));
        modelAndView.addObject("basicEstateLandState", basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateService.getLandStateByEstateId(basicFormClassifyParamDto.getTbId())));
        return modelAndView;
    }

    @Override
    public ModelAndView getPhoneEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/realEstate/photo/estate");
        modelAndView.addObject("basicEstate", getBasicEstateById(basicFormClassifyParamDto.getTbId()));
        modelAndView.addObject("basicEstateLandState", basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateService.getLandStateByEstateId(basicFormClassifyParamDto.getTbId())));
        return modelAndView;
    }
}