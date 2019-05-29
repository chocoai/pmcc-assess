package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.BasicApplyPartInModeEnum;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicBuildingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseBuildingService;
import com.copower.pmcc.assess.service.data.DataBuilderService;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
import com.copower.pmcc.assess.service.data.DataPropertyService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
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
    private BasicEstateService basicEstateService;
    @Autowired
    private CaseBuildingService caseBuildingService;
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


    private final Logger logger = LoggerFactory.getLogger(getClass());


    private void updateSysAttachmentDto(BasicBuilding basicBuilding, Integer id) {
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(0);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto dto : sysAttachmentDtoList) {
                if (org.apache.commons.lang3.StringUtils.isNotBlank(dto.getFieldsName())) {
                    dto.setTableId(id);
                    baseAttachmentService.updateAttachment(dto);
                }
            }
        }
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicBuildingVo getBasicBuildingById(Integer id) throws Exception {
        BasicBuilding basicBuilding = basicBuildingDao.getBasicBuildingById(id);
        return getBasicBuildingVo(basicBuilding);
    }

    public boolean update(BasicBuilding basicBuilding) throws Exception {
        return basicBuildingDao.updateBasicBuilding(basicBuilding);
    }

    /**
     * 新增或者修改
     *
     * @param basicBuilding
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicBuilding(BasicBuilding basicBuilding) throws Exception {
        if (basicBuilding.getId() == null || basicBuilding.getId().intValue() == 0) {
            basicBuilding.setCreator(commonService.thisUserAccount());
            Integer id = basicBuildingDao.addBasicBuilding(basicBuilding);
            this.updateSysAttachmentDto(basicBuilding, id);
            return id;
        } else {
            basicBuildingDao.updateBasicBuilding(basicBuilding);
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
    public List<BasicBuilding> basicBuildingList(BasicBuilding basicBuilding) throws Exception {
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
        BasicBuilding where = new BasicBuilding();
        where.setApplyId(applyId);
        if (applyId == null || applyId == 0) {
            where.setCreator(commonService.thisUserAccount());
        }
        List<BasicBuilding> basicBuildings = basicBuildingDao.getBasicBuildingList(where);
        if (CollectionUtils.isEmpty(basicBuildings)) return null;
        return getBasicBuildingVo(basicBuildings.stream().findFirst().get());
    }

    public BootstrapTableVo getBootstrapTableVo(BasicBuilding basicBuilding) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicBuilding> basicBuildingList = basicBuildingDao.getBasicBuildingList(basicBuilding);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicBuildingList) ? new ArrayList<BasicBuilding>(10) : basicBuildingList);
        return vo;
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
        if (basicBuilding.getProperty()!=null){
            DataProperty dataProperty = dataPropertyService.getByDataPropertyId(basicBuilding.getProperty());
            if (dataProperty != null){
                vo.setPropertyName(dataProperty.getName());
                vo.setDataProperty(dataPropertyService.getDataPropertyVo(dataProperty));
            }
        }
        if (basicBuilding.getBuilder()!=null){
            DataBuilder dataBuilder = dataBuilderService.getByDataBuilderId(basicBuilding.getBuilder());
            if (dataBuilder != null){
                vo.setBuildingName(dataBuilder.getName());
                vo.setDataBuilder(dataBuilderService.getDataBuilderVo(dataBuilder));
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
    public void clearInvalidData(Integer applyId) throws Exception {
        BasicBuilding where = new BasicBuilding();
        where.setApplyId(applyId);
        if (applyId.equals(0))
            where.setCreator(commonService.thisUserAccount());
        List<BasicBuilding> basicBuildingList = basicBuildingDao.getBasicBuildingList(where);
        BasicBuilding basicBuilding = null;
        if (CollectionUtils.isEmpty(basicBuildingList)) return;
        basicBuilding = basicBuildingList.get(0);

        StringBuilder sqlBulder = new StringBuilder();
        String baseSql = "delete from %s where building_id=%s;";
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class), basicBuilding.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class), basicBuilding.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class), basicBuilding.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicBuildingSurface.class), basicBuilding.getId()));

        sqlBulder.append(String.format("delete from %s where id=%s;", FormatUtils.entityNameConvertToTableName(BasicBuilding.class), basicBuilding.getId()));
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());

        //清理附件
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(0);
        sysAttachmentDto.setCreater(commonService.thisUserAccount());
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            for (SysAttachmentDto oo : sysAttachmentDtos) {
                baseAttachmentService.deleteAttachment(oo.getId());
            }
        }
    }

    /**
     * 将CaseBuilding下的子类 转移到 BasicBuilding下的子类中去 (用做过程数据)
     *
     * @param caseBuildingId
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicBuilding appWriteBuilding(Integer caseBuildingId, String buildingPartInMode, Integer applyId) throws Exception {
        if (caseBuildingId == null) {
            throw new Exception("null point");
        }
        applyId = applyId == null ? 0 : applyId;
        //清理数据
        this.clearInvalidData(applyId);
        CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(caseBuildingId);
        if (caseBuilding == null) {
            return null;
        }
        BasicBuilding basicBuilding = new BasicBuilding();
        BeanUtils.copyProperties(caseBuilding, basicBuilding);
        basicBuilding.setApplyId(applyId);
        basicBuilding.setCreator(commonService.thisUserAccount());
        basicBuilding.setGmtCreated(null);
        basicBuilding.setGmtModified(null);
        if (StringUtils.equals(buildingPartInMode, BasicApplyPartInModeEnum.REFERENCE.getKey())) {
            basicBuilding.setBuildingNumber(null);
            basicBuilding.setBuildingName(null);
        }
        basicBuildingDao.addBasicBuilding(basicBuilding);

        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(caseBuilding.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(CaseBuilding.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(basicBuilding.getId());
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
        baseAttachmentService.copyFtpAttachments(example, attachmentDto);

        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("building_id", String.valueOf(basicBuilding.getId()));
        map.put("creator", commonService.thisUserAccount());
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

        sqlBuilder.append(basicEstateService.copyTaggingFromCase(EstateTaggingTypeEnum.BUILDING, caseBuilding.getId(), applyId));
        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql
        return basicBuilding;
    }


}
