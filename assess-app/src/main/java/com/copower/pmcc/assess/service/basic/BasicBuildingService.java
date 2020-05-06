package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyFormNameEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicBuildingDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.input.basic.BasicFormClassifyParamDto;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingVo;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBuilderService;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.assess.service.data.DataPropertyService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.crm.api.provider.CrmRpcBaseDataDicService;
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
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicBuildingService extends BasicEntityAbstract {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicBuildingDao basicBuildingDao;
    @Autowired
    private PublicService publicService;
    @Autowired
    private DataBuildingNewRateService dataBuildingNewRateService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private DataPropertyService dataPropertyService;
    @Autowired
    private DataBuilderService dataBuilderService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private CrmRpcBaseDataDicService crmRpcBaseDataDicService;
    @Autowired
    private BasicEstateTaggingDao basicEstateTaggingDao;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<BasicBuilding> getBasicBuildingIds(List<Integer> ids) {
        return basicBuildingDao.getBasicBuildingIds(ids);
    }

    public List<BasicBuilding> getBasicBuildingByEstateId(Integer estateId) {
        BasicBuilding building = new BasicBuilding();
        building.setEstateId(estateId);
        building.setBisDelete(false);
        return basicBuildingDao.getBasicBuildingList(building);
    }


    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicBuilding getBasicBuildingById(Integer id) {
        BasicBuilding basicBuilding = basicBuildingDao.getBasicBuildingById(id);
        return basicBuilding;
    }

    public BasicBuildingVo getBasicBuildingVoById(Integer id) {
        BasicBuilding basicBuilding = basicBuildingDao.getBasicBuildingById(id);
        return getBasicBuildingVo(basicBuilding);
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicBuilding(Integer id) throws Exception {
        return basicBuildingDao.deleteBasicBuilding(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicBuilding
     * @return
     * @throws Exception
     */
    public List<BasicBuilding> getBasicBuildingList(BasicBuilding basicBuilding) {
        return basicBuildingDao.getBasicBuildingList(basicBuilding);
    }


    /**
     * 获取数据
     *
     * @param basicApply
     * @return
     * @throws Exception
     */
    public BasicBuildingVo getBasicBuildingByBasicApply(BasicApply basicApply) {
        if (basicApply == null) return null;
        String structuralInfo = basicApply.getStructuralInfo();
        List<KeyValueDto> keyValueDtos = JSON.parseArray(structuralInfo, KeyValueDto.class);
        BasicBuildingVo vo = new BasicBuildingVo();
        //检查是否存在差异的楼栋数据，有则需将基础部分与差异部分写入vo中
        BasicBuilding currBuildingDifference = null;
        for (KeyValueDto keyValueDto : keyValueDtos) {
            Boolean isBuilding = BasicFormClassifyEnum.BUILDING.getKey().equals(keyValueDto.getKey());
            Boolean isBuildingBase = BasicFormClassifyEnum.BUILDING_BASE.getKey().equals(keyValueDto.getKey());
            Boolean isBuildingMonolayer = BasicFormClassifyEnum.BUILDING_MONOLAYER.getKey().equals(keyValueDto.getKey());
            if (isBuilding || isBuildingBase || isBuildingMonolayer) {
                vo = getBasicBuildingVo(getBasicBuildingById(Integer.valueOf(keyValueDto.getValue())));
            }
            if (BasicFormClassifyEnum.BUILDING_DIFFERENCE.getKey().equals(keyValueDto.getKey())) {
                vo.setCurrBuildingDifference(getBasicBuildingVo(getBasicBuildingById(Integer.valueOf(keyValueDto.getValue()))));
                List<BasicApplyBatchDetail> list=Lists.newArrayList();
                basicApplyBatchDetailService.collectionParentBatchDetails(basicApply.getBatchDetailId(),list);
                List<BasicApplyBatchDetail> filter = LangUtils.filter(list, o -> o.getType().equals(keyValueDto.getKey()));
                if(CollectionUtils.isNotEmpty(filter)){
                    List<BasicBuildingVo> basicBuildingVos=Lists.newArrayList();
                    for (BasicApplyBatchDetail basicApplyBatchDetail : filter) {
                        basicBuildingVos.add(getBasicBuildingVo(getBasicBuildingById(basicApplyBatchDetail.getTableId())));
                    }
                    vo.setBasicBuildingDifferences(basicBuildingVos);
                }
            }
        }
        return vo;
    }

    public BasicBuildingVo getBasicBuildingByApplyId(Integer applyId) {
        if (applyId == null) return null;
        return getBasicBuildingByBasicApply(basicApplyService.getByBasicApplyId(applyId));
    }

    public BootstrapTableVo getBootstrapTableVo(BasicBuilding basicBuilding) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicBuilding> basicBuildingList = basicBuildingDao.getBasicBuildingList(basicBuilding);
        List<BasicBuildingVo> transform = LangUtils.transform(basicBuildingList, o -> getBasicBuildingVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(transform) ? new ArrayList<BasicBuildingVo>(10) : transform);
        return vo;
    }

    public List<CustomCaseEntity> autoCompleteCaseBuilding(String buildingNumber, Integer estateId) {
        if (StringUtils.isBlank(buildingNumber) || estateId == null) return null;
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CustomCaseEntity> entities = basicBuildingDao.getLatestVersionBuildingList(buildingNumber, estateId);
        return entities;
    }

    public BasicBuildingVo getBasicBuildingVo(BasicBuilding basicBuilding) {
        if (basicBuilding == null) {
            return null;
        }
        BasicBuildingVo vo = new BasicBuildingVo();
        BeanUtils.copyProperties(basicBuilding, vo);
        vo.setPropertyTypeName(baseDataDicService.getNameById(basicBuilding.getPropertyType()));
        vo.setPropertyCategoryName(baseDataDicService.getNameById(basicBuilding.getPropertyCategory()));
        vo.setBuildingStructureTypeName(baseDataDicService.getNameById(basicBuilding.getBuildingStructureType()));
        vo.setBuildingStructureCategoryName(baseDataDicService.getNameById(basicBuilding.getBuildingStructureCategory()));
        vo.setResidenceUseYearName(baseDataDicService.getNameById(basicBuilding.getResidenceUseYear()));
        vo.setBetweenDistanceName(baseDataDicService.getNameById(basicBuilding.getBetweenDistance()));
        if (basicBuilding.getCompletedTimeType() != null) {
            vo.setCompletedTimeTypeName(baseDataDicService.getDataDicById(basicBuilding.getCompletedTimeType()).getName());
        }
        if (basicBuilding.getIndustryUseYear() != null) {
            if (dataBuildingNewRateService.getByiDdataBuildingNewRate(basicBuilding.getIndustryUseYear()) != null) {
                vo.setIndustryUseYearName(dataBuildingNewRateService.getByiDdataBuildingNewRate(basicBuilding.getIndustryUseYear()).getBuildingStructure());
            }
        }
        if (basicBuilding.getOpenTime() != null) {
            vo.setOpenTimeName(DateUtils.format(basicBuilding.getOpenTime()));
        }
        if (basicBuilding.getRoomTime() != null) {
            vo.setRoomTimeName(DateUtils.format(basicBuilding.getRoomTime()));
        }
        if (basicBuilding.getBeCompletedTime() != null) {
            vo.setBeCompletedTimeName(DateUtils.format(basicBuilding.getBeCompletedTime()));
        }
        if (basicBuilding.getProperty() != null) {
            DataProperty dataProperty = dataPropertyService.getByDataPropertyId(basicBuilding.getProperty());
            if (dataProperty != null) {
                vo.setPropertyName(dataProperty.getName());
                vo.setDataProperty(dataPropertyService.getDataPropertyVo(dataProperty));
            }
        }
        if (basicBuilding.getBuilder() != null) {
            DataBuilder dataBuilder = dataBuilderService.getByDataBuilderId(basicBuilding.getBuilder());
            if (dataBuilder != null) {
                vo.setBuildingName(dataBuilder.getName());
                vo.setDataBuilder(dataBuilderService.getDataBuilderVo(dataBuilder));
            }
        }
        vo.setConstructionQualityName(baseDataDicService.getNameById(basicBuilding.getConstructionQuality()));
        vo.setAppearanceStyleName(baseDataDicService.getNameById(basicBuilding.getAppearanceStyle()));
        vo.setAppearanceNewAndOldName(baseDataDicService.getNameById(basicBuilding.getAppearanceNewAndOld()));
        if (basicBuilding.getPropertyCompanyNature() != null) {
            try {
                //crm 未知错误  暂时这样处理
                vo.setPropertyCompanyNatureName(crmRpcBaseDataDicService.getBaseDataDic(basicBuilding.getPropertyCompanyNature()).getName());
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
        }
        vo.setPropertySocialPrestigeName(baseDataDicService.getNameById(basicBuilding.getPropertySocialPrestige()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicBuilding.getCreator()));
        if (org.apache.commons.lang.math.NumberUtils.isNumber(basicBuilding.getMinimumFloorDistance())) {
            String nameById = baseDataDicService.getNameById(basicBuilding.getMinimumFloorDistance());
            if (StringUtils.isNotBlank(nameById)) {
                vo.setMinimumFloorDistance(nameById);
            }
        }
        return vo;
    }


    /**
     * 同时添加楼栋主数据及楼栋信息，方便业务后期操作数据
     *
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicBuilding addBuilding(String buildingNumber) throws Exception {
        this.clearInvalidData(0);

        BasicBuilding basicBuilding = new BasicBuilding();
        basicBuilding.setBuildingNumber(buildingNumber);
        basicBuilding.setBuildingName(buildingNumber);
        basicBuilding.setApplyId(0);
        basicBuilding.setCreator(commonService.thisUserAccount());
        basicBuildingDao.addBasicBuilding(basicBuilding);
        return basicBuilding;
    }

    /**
     * 清理无效数据
     *
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void clearInvalidData(Integer buildingId) throws Exception {
        if (buildingId == null) return;
        clearInvalidChildData(buildingId);//清理从表数据

        //清除标记
        BasicEstateTagging where = new BasicEstateTagging();
        where.setTableId(buildingId);
        where.setType(BasicFormClassifyEnum.BUILDING.getKey());
        basicEstateTaggingDao.removeBasicEstateTagging(where);

        StringBuilder sqlBulder = new StringBuilder();
        sqlBulder.append(String.format("update %s set bis_delete=1 where id=%s;", FormatUtils.entityNameConvertToTableName(BasicBuilding.class), buildingId));
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());
    }

    /**
     * 只清理从表数据
     *
     * @param buildingId
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void clearInvalidChildData(Integer buildingId) throws Exception {
        StringBuilder sqlBulder = new StringBuilder();
        String baseSql = "update %s set bis_delete=1 where building_id=%s;";
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class), buildingId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class), buildingId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class), buildingId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class), buildingId));
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());
    }

    @Override
    public Integer saveAndUpdate(Object o, Boolean updateNull) {
        if (o == null) return null;
        BasicBuilding basicBuilding = (BasicBuilding) o;
        if (basicBuilding.getId() == null || basicBuilding.getId().intValue() == 0) {
            basicBuilding.setCreator(commonService.thisUserAccount());
            Integer id = basicBuildingDao.addBasicBuilding(basicBuilding);
            return id;
        } else {
            if (updateNull) {
                BasicBuilding building = basicBuildingDao.getBasicBuildingById(basicBuilding.getId());
                if (building != null) {
                    basicBuilding.setBisDelete(building.getBisDelete());
                    basicBuilding.setCreator(building.getCreator());
                    basicBuilding.setGmtCreated(building.getGmtCreated());
                    basicBuilding.setGmtModified(DateUtils.now());
                }
            }
            basicBuildingDao.updateBasicBuilding(basicBuilding, updateNull);
            return basicBuilding.getId();
        }
    }

    @Override
    public Integer saveAndUpdateByFormData(String formData, Integer planDetailsId) throws Exception {
        if (StringUtils.isBlank(formData)) return null;
        JSONObject jsonObject = JSON.parseObject(formData);
        BasicBuilding basicBuilding = null;
        String jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_BUILDING.getVar());
        if (StringUtils.isNotBlank(jsonContent)) {
            basicBuilding = JSONObject.parseObject(jsonContent, BasicBuilding.class);
            //原来数据做记录,将老数据复制一条
            BasicBuilding oldBasicBuilding = getBasicBuildingById(basicBuilding.getId());
            if (oldBasicBuilding != null && StringUtils.isNotBlank(oldBasicBuilding.getBuildingNumber())) {
                BasicBuilding version = (BasicBuilding) copyBasicEntity(oldBasicBuilding.getId(), null, false);
                version.setRelevanceId(oldBasicBuilding.getId());
                version.setEstateId(0);
                saveAndUpdate(version, false);
            }

            if (basicBuilding != null) {
                BasicEstate basicEstate = basicEstateService.getBasicEstateById(basicBuilding.getEstateId());
                if (basicEstate != null)
                    basicBuilding.setFullName(basicEstate.getName() + basicBuilding.getBuildingNumber());
                saveAndUpdate(basicBuilding, true);
                BasicApplyBatchDetail buildingDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(FormatUtils.entityNameConvertToTableName(BasicBuilding.class), basicBuilding.getId());
                if (buildingDetail != null) {
                    buildingDetail.setName(basicBuilding.getBuildingNumber());
                    buildingDetail.setDisplayName(basicBuilding.getBuildingNumber());
                    basicApplyBatchDetailService.saveBasicApplyBatchDetail(buildingDetail);
                }
                return basicBuilding.getId();
            }
        }
        return null;
    }

    @Override
    public Object getBasicEntityById(Integer id) {
        return getBasicBuildingById(id);
    }

    @Override
    public Object copyBasicEntity(Integer sourceId, Integer targetId, Boolean containChild) throws Exception {
        return copyBasicEntityIgnore(sourceId, targetId, containChild, null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object copyBasicEntityIgnore(Integer sourceId, Integer targetId, Boolean containChild, List<String> ignoreList) throws Exception {
        if (sourceId == null) return null;
        BasicBuilding sourceBasicBuilding = getBasicBuildingById(sourceId);
        if (sourceBasicBuilding == null) return null;
        BasicBuilding targetBasicBuilding = (targetId == null || targetId <= 0) ? null : getBasicBuildingById(targetId);
        if (CollectionUtils.isEmpty(ignoreList)) ignoreList = Lists.newArrayList();
        ignoreList.addAll(Lists.newArrayList(BaseConstant.ASSESS_IGNORE_ARRAY));
        if (targetBasicBuilding == null) {
            targetBasicBuilding = new BasicBuilding();
            BeanUtils.copyProperties(sourceBasicBuilding, targetBasicBuilding, ignoreList.toArray(new String[ignoreList.size()]));
            targetBasicBuilding.setCreator(commonService.thisUserAccount());
        } else {
            BeanUtils.copyProperties(sourceBasicBuilding, targetBasicBuilding, ignoreList.toArray(new String[ignoreList.size()]));
        }
        this.saveAndUpdate(targetBasicBuilding, true);

        if (targetId != null && targetId > 0) {//目标数据已存在，先清理目标数据的从表数据
            clearInvalidChildData(targetId);

            SysAttachmentDto where = new SysAttachmentDto();
            where.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
            where.setTableId(targetId);
            baseAttachmentService.deleteAttachmentByDto(where);
        }
        baseAttachmentService.copyFtpAttachments(FormatUtils.entityNameConvertToTableName(BasicBuilding.class), sourceId, targetBasicBuilding.getId());//附件拷贝
        basicEstateTaggingService.copyTagging(BasicFormClassifyEnum.BUILDING, sourceId, targetBasicBuilding.getId());//标记tagging

        if (containChild) { //处理从表数据
            StringBuilder sqlBuilder = new StringBuilder();
            SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
            HashMap<String, String> map = Maps.newHashMap();
            map.put("building_id", String.valueOf(targetBasicBuilding.getId()));
            map.put("creator", commonService.thisUserAccount());
            synchronousDataDto.setFieldDefaultValue(map);
            synchronousDataDto.setWhereSql("building_id=" + sourceId);
            synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS);
            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋外装sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//维护结构sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//层面结构sql

            synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class));
            synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class));
            sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//建筑功能sql

            ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        }
        return targetBasicBuilding;
    }

    @Override
    public List<BasicFormClassifyEnum> getLowerFormClassifyList() {
        List<BasicFormClassifyEnum> list = Lists.newArrayList();
        list.add(BasicFormClassifyEnum.UNIT_RESIDENCE);
        list.add(BasicFormClassifyEnum.UNIT);
        return list;
    }

    @Override
    public ModelAndView getEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/realEstate/building");
        BasicBuildingVo buildingVo = getBasicBuildingVoById(basicFormClassifyParamDto.getTbId());
        modelAndView.addObject("basicBuilding", buildingVo);
        List<CrmBaseDataDicDto> unitPropertiesList = projectInfoService.getUnitPropertiesList();
        modelAndView.addObject("unitPropertiesList", unitPropertiesList);
        Integer applyBatchId = basicFormClassifyParamDto.getApplyBatchId();
        Integer tbId = basicFormClassifyParamDto.getTbId();
        BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(applyBatchId, FormatUtils.entityNameConvertToTableName(BasicBuilding.class), tbId);
        if (basicApplyBatchDetail != null) {//获取引用id
            basicApplyBatchDetail = basicApplyBatchDetailService.getDataById(basicApplyBatchDetail.getPid());
            if (basicApplyBatchDetail != null) {
                BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByTableName(basicApplyBatchDetail.getTableName());
                Object entity = entityAbstract.getBasicEntityById(basicApplyBatchDetail.getTableId());
                if (entity != null) {
                    modelAndView.addObject("quoteId", entityAbstract.getProperty(entity, "quoteId"));
                }
            }
        }
        return modelAndView;
    }

    @Override
    public ModelAndView getDetailModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/realEstate/detail/building");
        BasicBuildingVo buildingVo = getBasicBuildingVoById(basicFormClassifyParamDto.getTbId());
        modelAndView.addObject("basicBuilding", buildingVo);
        return modelAndView;
    }

    @Override
    public ModelAndView getPhoneEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/realEstate/photo/building");
        BasicBuildingVo buildingVo = getBasicBuildingVoById(basicFormClassifyParamDto.getTbId());
        modelAndView.addObject("basicBuilding", buildingVo);
        return modelAndView;
    }
}
