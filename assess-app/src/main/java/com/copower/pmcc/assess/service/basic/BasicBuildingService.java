package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.common.enums.BasicApplyPartInModeEnum;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.dal.basic.dao.BasicBuildingDao;
import com.copower.pmcc.assess.dal.basic.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.assess.service.data.DataBuildingNewRateService;
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
    private BasicBuildingFunctionService basicBuildingFunctionService;
    @Autowired
    private BasicBuildingMaintenanceService basicBuildingMaintenanceService;
    @Autowired
    private BasicBuildingOutfitService basicBuildingOutfitService;
    @Autowired
    private BasicBuildingSurfaceService basicBuildingSurfaceService;
    @Autowired
    private BasicBuildingDao basicBuildingDao;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private CaseBuildingService caseBuildingService;
    @Autowired
    private CaseBuildingOutfitService caseBuildingOutfitService;
    @Autowired
    private CaseBuildingMaintenanceService caseBuildingMaintenanceService;
    @Autowired
    private CaseBuildingSurfaceService caseBuildingSurfaceService;
    @Autowired
    private CaseBuildingFunctionService caseBuildingFunctionService;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
    @Autowired
    private DataBuildingNewRateService dataBuildingNewRateService;


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
    public BasicBuilding getBasicBuildingByApplyId(Integer applyId)  {
        BasicBuilding where = new BasicBuilding();
        where.setApplyId(applyId);
        if (applyId == null || applyId == 0) {
            where.setCreator(commonService.thisUserAccount());
        }
        List<BasicBuilding> basicBuildings = basicBuildingDao.getBasicBuildingList(where);
        if (CollectionUtils.isEmpty(basicBuildings)) return null;
        return basicBuildings.get(0);
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
        if (basicBuilding.getIndustryUseYear() != null){
            if (dataBuildingNewRateService.getByiDdataBuildingNewRate(basicBuilding.getIndustryUseYear()) != null){
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
        return vo;
    }


    /**
     * 同时添加楼栋主数据及楼栋信息，方便业务后期操作数据
     *
     * @return
     * @throws Exception
     */
    @Transactional(value = "transactionManagerBasic", rollbackFor = Exception.class)
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
    @Transactional(value = "transactionManagerBasic", rollbackFor = Exception.class)
    public void clearInvalidData(Integer applyId) throws Exception {
        BasicBuilding where = new BasicBuilding();
        where.setApplyId(applyId);
        if (applyId.equals(0))
            where.setCreator(commonService.thisUserAccount());
        List<BasicBuilding> basicBuildingList = basicBuildingDao.getBasicBuildingList(where);
        BasicBuilding basicBuilding = null;
        if (CollectionUtils.isEmpty(basicBuildingList)) return;
        basicBuilding = basicBuildingList.get(0);

        List<BasicBuildingFunction> basicBuildingFunctionList = null;
        List<BasicBuildingMaintenance> basicBuildingMaintenanceList = null;
        List<BasicBuildingOutfit> basicBuildingOutfitList = null;
        List<BasicBuildingSurface> basicBuildingSurfaceList = null;
        BasicBuildingSurface querySurface = new BasicBuildingSurface();
        BasicBuildingFunction queryFunction = new BasicBuildingFunction();
        BasicBuildingOutfit queryOutfit = new BasicBuildingOutfit();
        BasicBuildingMaintenance queryMaintenance = new BasicBuildingMaintenance();
        querySurface.setBuildingId(basicBuilding.getId());
        queryFunction.setBuildingId(basicBuilding.getId());
        queryOutfit.setBuildingId(basicBuilding.getId());
        queryMaintenance.setBuildingId(basicBuilding.getId());

        basicBuildingSurfaceList = basicBuildingSurfaceService.basicBuildingSurfaceList(querySurface);
        basicBuildingFunctionList = basicBuildingFunctionService.basicBuildingFunctionList(queryFunction);
        basicBuildingOutfitList = basicBuildingOutfitService.basicBuildingOutfitList(queryOutfit);
        basicBuildingMaintenanceList = basicBuildingMaintenanceService.basicBuildingMaintenanceList(queryMaintenance);
        for (BasicBuildingSurface oo : basicBuildingSurfaceList) {
            basicBuildingSurfaceService.removeBasicBuildingSurface(oo);
        }
        for (BasicBuildingFunction oo : basicBuildingFunctionList) {
            basicBuildingFunctionService.removeBasicBuildingFunction(oo);
        }
        for (BasicBuildingOutfit oo : basicBuildingOutfitList) {
            basicBuildingOutfitService.removeBasicBuildingOutfit(oo);
        }
        for (BasicBuildingMaintenance oo : basicBuildingMaintenanceList) {
            basicBuildingMaintenanceService.removeBasicBuildingMaintenance(oo);
        }

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
        basicBuildingDao.deleteBasicBuilding(basicBuilding.getId());
    }

    /**
     * 将CaseBuilding下的子类 转移到 BasicBuilding下的子类中去 (用做过程数据)
     *
     * @param caseBuildingId
     * @throws Exception
     */
    @Transactional(value = "transactionManagerBasic", rollbackFor = Exception.class)
    public BasicBuilding appWriteBuilding(Integer caseBuildingId, String buildingPartInMode, Integer applyId) throws Exception {
        if (caseBuildingId == null) {
            throw new Exception("null point");
        }
        //清理数据
        this.clearInvalidData(0);
        if (applyId != null) {
            this.clearInvalidData(applyId);
        }
        CaseBuilding caseBuilding = caseBuildingService.getCaseBuildingById(caseBuildingId);
        if (caseBuilding == null) {
            return null;
        }
        BasicBuilding basicBuilding = new BasicBuilding();
        BeanUtils.copyProperties(caseBuilding, basicBuilding);
        basicBuilding.setApplyId(applyId == null ? 0 : applyId);
        basicBuilding.setCreator(commonService.thisUserAccount());
        basicBuilding.setGmtCreated(null);
        basicBuilding.setGmtModified(null);
        if (StringUtils.equals(buildingPartInMode, BasicApplyPartInModeEnum.REFERENCE.getKey())) {
            basicBuilding.setBuildingNumber(null);
            basicBuilding.setBuildingName(null);
        }
        basicBuildingDao.addBasicBuilding(basicBuilding);

        if (StringUtils.equals(buildingPartInMode, BasicApplyPartInModeEnum.UPGRADE.getKey())) {

        }
        CaseEstateTagging caseEstateTagging = new CaseEstateTagging();
        caseEstateTagging.setDataId(caseBuildingId);
        caseEstateTagging.setType(EstateTaggingTypeEnum.BUILDING.getKey());
        List<CaseEstateTagging> caseEstateTaggings = caseEstateTaggingService.getCaseEstateTaggingList(caseEstateTagging);
        basicEstateService.copyTaggingFromCase(caseEstateTaggings,applyId);

        List<SysAttachmentDto> sysAttachmentDtoList = null;
        SysAttachmentDto queryFile = new SysAttachmentDto();
        queryFile.setTableId(caseBuilding.getId());
        queryFile.setTableName(FormatUtils.entityNameConvertToTableName(CaseBuilding.class));
        sysAttachmentDtoList = baseAttachmentService.getAttachmentList(queryFile);
        //复制 临时附件
        if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
            for (SysAttachmentDto attachmentDto : sysAttachmentDtoList) {
                SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
                sysAttachmentDto.setTableId(basicBuilding.getId());
                sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
                baseAttachmentService.copyFtpAttachment(attachmentDto.getId(), sysAttachmentDto);
            }
        }

        CaseBuildingOutfit queryOutfit = new CaseBuildingOutfit();
        CaseBuildingMaintenance queryMaintenance = new CaseBuildingMaintenance();
        CaseBuildingSurface querySurface = new CaseBuildingSurface();
        CaseBuildingFunction queryFunction = new CaseBuildingFunction();

        queryOutfit.setBuildingId(caseBuilding.getId());
        queryMaintenance.setBuildingId(caseBuilding.getId());
        querySurface.setBuildingId(caseBuilding.getId());
        queryFunction.setBuildingId(caseBuilding.getId());


        List<CaseBuildingOutfit> buildingOutfitList = caseBuildingOutfitService.getCaseBuildingOutfitList(queryOutfit);
        List<CaseBuildingMaintenance> maintenanceList = caseBuildingMaintenanceService.getCaseBuildingMaintenanceList(queryMaintenance);
        List<CaseBuildingSurface> surfaceList = caseBuildingSurfaceService.getCaseBuildingSurfaceList(querySurface);
        List<CaseBuildingFunction> functionList = caseBuildingFunctionService.getCaseBuildingFunctionListO(queryFunction);


        if (!ObjectUtils.isEmpty(buildingOutfitList)) {
            for (CaseBuildingOutfit oo : buildingOutfitList) {
                BasicBuildingOutfit basicBuildingOutfit = new BasicBuildingOutfit();
                BeanCopyHelp.copyPropertiesIgnoreNull(oo, basicBuildingOutfit);
                basicBuildingOutfit.setBuildingId(basicBuilding.getId());
                basicBuildingOutfit.setId(null);
                basicBuildingOutfit.setCreator(commonService.thisUserAccount());
                basicBuildingOutfit.setGmtCreated(null);
                basicBuildingOutfit.setGmtModified(null);
                basicBuildingOutfitService.saveAndUpdateBasicBuildingOutfit(basicBuildingOutfit);
            }
        }
        if (!ObjectUtils.isEmpty(maintenanceList)) {
            for (CaseBuildingMaintenance oo : maintenanceList) {
                BasicBuildingMaintenance basicBuildingMaintenance = new BasicBuildingMaintenance();
                BeanCopyHelp.copyPropertiesIgnoreNull(oo, basicBuildingMaintenance);
                basicBuildingMaintenance.setBuildingId(basicBuilding.getId());
                basicBuildingMaintenance.setId(null);
                basicBuildingMaintenance.setCreator(commonService.thisUserAccount());
                basicBuildingMaintenance.setGmtCreated(null);
                basicBuildingMaintenance.setGmtModified(null);
                basicBuildingMaintenanceService.saveAndUpdateBasicBuildingMaintenance(basicBuildingMaintenance);
            }
        }
        if (!ObjectUtils.isEmpty(surfaceList)) {
            for (CaseBuildingSurface oo : surfaceList) {
                BasicBuildingSurface basicBuildingSurface = new BasicBuildingSurface();
                BeanCopyHelp.copyPropertiesIgnoreNull(oo, basicBuildingSurface);
                basicBuildingSurface.setBuildingId(basicBuilding.getId());
                basicBuildingSurface.setId(null);
                basicBuildingSurface.setCreator(commonService.thisUserAccount());
                basicBuildingSurface.setGmtCreated(null);
                basicBuildingSurface.setGmtModified(null);
                basicBuildingSurfaceService.saveAndUpdateBasicBuildingSurface(basicBuildingSurface);
            }
        }
        if (!ObjectUtils.isEmpty(functionList)) {
            for (CaseBuildingFunction oo : functionList) {
                BasicBuildingFunction basicBuildingFunction = new BasicBuildingFunction();
                BeanCopyHelp.copyPropertiesIgnoreNull(oo, basicBuildingFunction);
                basicBuildingFunction.setBuildingId(basicBuilding.getId());
                basicBuildingFunction.setId(null);
                basicBuildingFunction.setCreator(commonService.thisUserAccount());
                basicBuildingFunction.setGmtCreated(null);
                basicBuildingFunction.setGmtModified(null);
                basicBuildingFunctionService.saveAndUpdateBasicBuildingFunction(basicBuildingFunction);
            }
        }
        return basicBuilding;
    }


}
