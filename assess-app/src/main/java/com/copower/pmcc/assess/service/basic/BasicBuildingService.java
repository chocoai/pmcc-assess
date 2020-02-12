package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.basic.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicBuildingDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBuilderService;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.assess.service.data.DataPropertyService;
import com.copower.pmcc.crm.api.provider.CrmRpcBaseDataDicService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicBuildingService {
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


    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<BasicBuilding> getBasicBuildingIds(List<Integer> ids) {
        return basicBuildingDao.getBasicBuildingIds(ids);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicBuildingVo getBasicBuildingById(Integer id) {
        BasicBuilding basicBuilding = basicBuildingDao.getBasicBuildingById(id);
        return getBasicBuildingVo(basicBuilding);
    }

    /**
     * 新增或者修改
     *
     * @param basicBuilding
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicBuilding(BasicBuilding basicBuilding, boolean updateNull) throws Exception {
        if (basicBuilding.getId() == null || basicBuilding.getId().intValue() == 0) {
            basicBuilding.setCreator(commonService.thisUserAccount());
            Integer id = basicBuildingDao.addBasicBuilding(basicBuilding);
            return id;
        } else {
            if (updateNull) {
                BasicBuilding building = basicBuildingDao.getBasicBuildingById(basicBuilding.getId());
                if (building != null) {
                    basicBuilding.setCreator(building.getCreator());
                    basicBuilding.setGmtCreated(building.getGmtCreated());
                    basicBuilding.setGmtModified(DateUtils.now());
                }
            }
            basicBuildingDao.updateBasicBuilding(basicBuilding, updateNull);
            return basicBuilding.getId();
        }
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
     * @param applyId
     * @return
     * @throws Exception
     */
    public BasicBuildingVo getBasicBuildingByApplyId(Integer applyId) {
        if (applyId == null) return null;
        BasicBuilding where = new BasicBuilding();
        where.setApplyId(applyId);
        where.setCreator(commonService.thisUserAccount());
        List<BasicBuilding> basicBuildings = basicBuildingDao.getBasicBuildingList(where);
        if (!CollectionUtils.isEmpty(basicBuildings)) {
            return getBasicBuildingVo(basicBuildings.get(0));
        } else {
            BasicApply basicApply = basicApplyService.getByBasicApplyId(applyId);
            return getBasicBuildingById(basicApply.getBasicBuildingId());
        }
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
            vo.setPropertyCompanyNatureName(crmRpcBaseDataDicService.getBaseDataDic(basicBuilding.getPropertyCompanyNature()).getName());
        }
        vo.setPropertySocialPrestigeName(baseDataDicService.getNameById(basicBuilding.getPropertySocialPrestige()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicBuilding.getCreator()));
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
        basicBuilding.setBuildingName(buildingNumber + '栋');
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
    public void clearInvalidData(Integer buildingId) throws Exception {
        if (buildingId == null) return;
        clearInvalidChildData(buildingId);//清理从表数据

        //清除标记
        BasicEstateTagging where = new BasicEstateTagging();
        where.setTableId(buildingId);
        where.setType(EstateTaggingTypeEnum.BUILDING.getKey());
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
    public void clearInvalidChildData(Integer buildingId) throws Exception {
        StringBuilder sqlBulder = new StringBuilder();
        String baseSql = "update %s set bis_delete=1 where building_id=%s;";
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class), buildingId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class), buildingId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class), buildingId));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class), buildingId));
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());
    }

    @Transactional(rollbackFor = Exception.class)
    public BasicBuilding copyBasicBuilding(Integer sourceBuildingId, Integer targetBuildingId, Boolean containChild) throws Exception {
        return copyBasicBuildingIgnore(sourceBuildingId, targetBuildingId, containChild);
    }

    /**
     * 拷贝查勘楼栋数据
     *
     * @param sourceBuildingId
     * @param targetBuildingId
     * @param containChild     是否包含从表数据
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicBuilding copyBasicBuildingIgnore(Integer sourceBuildingId, Integer targetBuildingId, Boolean containChild, String... ignoreProperties) throws Exception {
        if (sourceBuildingId == null) return null;
        BasicBuilding sourceBasicBuilding = getBasicBuildingById(sourceBuildingId);
        if (sourceBasicBuilding == null) return null;
        BasicBuilding targetBasicBuilding = getBasicBuildingById(targetBuildingId);
        if (targetBasicBuilding == null) {
            targetBasicBuilding = new BasicBuilding();
            BeanUtils.copyProperties(sourceBasicBuilding, targetBasicBuilding, ignoreProperties);
            targetBasicBuilding.setId(null);
            targetBasicBuilding.setCreator(commonService.thisUserAccount());
            targetBasicBuilding.setGmtCreated(null);
            targetBasicBuilding.setGmtModified(null);
        } else {
            BeanUtils.copyProperties(sourceBasicBuilding, targetBasicBuilding, "id");
        }
        this.saveAndUpdateBasicBuilding(targetBasicBuilding, true);

        if (targetBuildingId != null && targetBuildingId > 0) {//目标数据已存在，先清理目标数据的从表数据
            clearInvalidChildData(targetBuildingId);

            SysAttachmentDto where=new SysAttachmentDto();
            where.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
            where.setTableId(targetBuildingId);
            baseAttachmentService.deleteAttachmentByDto(where);
        }
        baseAttachmentService.copyFtpAttachments(FormatUtils.entityNameConvertToTableName(BasicBuilding.class), sourceBuildingId, targetBasicBuilding.getId());//附件拷贝
        basicEstateTaggingService.copyTagging(EstateTaggingTypeEnum.BUILDING, sourceBuildingId, targetBasicBuilding.getId());//标记tagging

        if (containChild) { //处理从表数据
            StringBuilder sqlBuilder = new StringBuilder();
            SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
            HashMap<String, String> map = Maps.newHashMap();
            map.put("building_id", String.valueOf(targetBasicBuilding.getId()));
            map.put("creator", commonService.thisUserAccount());
            synchronousDataDto.setFieldDefaultValue(map);
            synchronousDataDto.setWhereSql("building_id=" + sourceBuildingId);
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
}
