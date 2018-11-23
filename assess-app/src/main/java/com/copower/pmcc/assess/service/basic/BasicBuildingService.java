package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basic.dao.BasicBuildingDao;
import com.copower.pmcc.assess.dal.basic.dao.BasicBuildingMainDao;
import com.copower.pmcc.assess.dal.basic.entity.*;
import com.copower.pmcc.assess.dal.basis.entity.DataBuilder;
import com.copower.pmcc.assess.dal.basis.entity.DataProperty;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.assess.service.data.DataBuilderService;
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
import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicBuildingService {
    @Autowired
    private DataPropertyService dataPropertyService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicBuildingDao basicBuildingDao;
    @Autowired
    private DataBuilderService dataBuilderService;
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
    private BasicBuildingMainDao basicBuildingMainDao;
    @Autowired
    private CaseBuildingService caseBuildingService;
    @Autowired
    private CaseBuildingMainService caseBuildingMainService;
    @Autowired
    private CaseBuildingOutfitService caseBuildingOutfitService;
    @Autowired
    private CaseBuildingMaintenanceService caseBuildingMaintenanceService;
    @Autowired
    private CaseBuildingSurfaceService caseBuildingSurfaceService;
    @Autowired
    private CaseBuildingFunctionService caseBuildingFunctionService;

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

    public Integer upgradeVersion(BasicBuilding basicBuilding) throws Exception {
        if (basicBuilding.getId() == null || basicBuilding.getId().intValue() == 0) {
            basicBuilding.setCreator(commonService.thisUserAccount());
            Integer id = basicBuildingDao.saveBasicBuilding(basicBuilding);
        } else {
            basicBuildingDao.updateBasicBuilding(basicBuilding);
        }
        return basicBuilding.getId();
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
            Integer id = basicBuildingDao.saveBasicBuilding(basicBuilding);
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
        return basicBuildingDao.basicBuildingList(basicBuilding);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicBuilding basicBuilding) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicBuilding> basicBuildingList = basicBuildingDao.basicBuildingList(basicBuilding);
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
        if (basicBuilding.getPropertyType() != null) {
            vo.setPropertyTypeName(baseDataDicService.getNameById(basicBuilding.getPropertyType()));
        }
        if (basicBuilding.getBuildingStructure() != null) {
            vo.setBuildingStructureName(baseDataDicService.getNameById(basicBuilding.getBuildingStructure()));
        }
        if (basicBuilding.getBuildingStructureLower() != null) {
            vo.setBuildingStructureLowerName(baseDataDicService.getNameById(basicBuilding.getBuildingStructureLower()));
        }
        if (basicBuilding.getBuildingCategory() != null) {
            vo.setBuildingCategoryName(baseDataDicService.getNameById(basicBuilding.getBuildingCategory()));
        }
        if (basicBuilding.getOpenTime() != null) {
            vo.setOpenTimeName(DateUtils.format(basicBuilding.getOpenTime()));
        }
        if (basicBuilding.getRoomTime() != null) {
            vo.setRoomTimeName(DateUtils.format(basicBuilding.getRoomTime()));
        }
        if (basicBuilding.getPropertyId() != null) {
            DataProperty dataProperty = dataPropertyService.getByDataPropertyId(basicBuilding.getPropertyId());
            if (dataProperty != null) {
                vo.setPropertyName(dataProperty.getName());
            }
        }
        if (basicBuilding.getBuilderId() != null) {
            DataBuilder dataBuilder = dataBuilderService.getByDataBuilderId(basicBuilding.getBuilderId());
            if (dataBuilder != null) {
                vo.setDataBuildingName(dataBuilder.getName());
            }
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
    @Transactional
    public BasicBuildingMain addBuildingMainAndBuilding(String buildingNumber) throws Exception {
        this.clearInvalidData();

        BasicBuildingMain basicBuildingMain = new BasicBuildingMain();
        basicBuildingMain.setBuildingNumber(buildingNumber);
        basicBuildingMain.setBuildingName(buildingNumber+'栋');
        basicBuildingMain.setApplyId(0);
        basicBuildingMain.setCreator(commonService.thisUserAccount());
        basicBuildingMainDao.saveBasicBuildingMain(basicBuildingMain);

        BasicBuilding basicBuilding = new BasicBuilding();
        basicBuilding.setBasicBuildingMainId(basicBuildingMain.getId());
        basicBuilding.setCreator(commonService.thisUserAccount());
        basicBuildingDao.saveBasicBuilding(basicBuilding);
        return basicBuildingMain;
    }

    /**
     * 根据主表id获取所有楼栋部分信息
     * @param basicBuildingMainId
     * @return
     */
    public List<BasicBuilding> getBasicBuildingListByMainId(Integer basicBuildingMainId) {
        BasicBuilding basicBuildingWhere = new BasicBuilding();
        basicBuildingWhere.setBasicBuildingMainId(basicBuildingMainId);
        List<BasicBuilding> buildingList = basicBuildingDao.basicBuildingList(basicBuildingWhere);
        return buildingList;
    }

    /**
     * 清理无效数据
     *
     * @throws Exception
     */
    @Transactional
    public void clearInvalidData() throws Exception {
        BasicBuildingMain where = new BasicBuildingMain();
        where.setApplyId(0);
        where.setCreator(commonService.thisUserAccount());
        List<BasicBuildingMain> buildingMainList = basicBuildingMainDao.basicBuildingMainList(where);
        BasicBuildingMain basicBuildingMain = null;
        if (CollectionUtils.isEmpty(buildingMainList)) return;
        basicBuildingMain = buildingMainList.get(0);

        BasicBuilding basicBuildingWhere = new BasicBuilding();
        basicBuildingWhere.setBasicBuildingMainId(basicBuildingMain.getId());
        List<BasicBuilding> buildingList = basicBuildingDao.basicBuildingList(basicBuildingWhere);
        if (CollectionUtils.isEmpty(buildingList)) return;
        for (BasicBuilding basicBuilding : buildingList) {
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
        basicBuildingMainDao.deleteBasicBuildingMain(basicBuildingMain.getId());
    }

    /**
     * 将CaseBuilding下的子类 转移到 BasicBuilding下的子类中去 (用做过程数据)
     *
     * @param caseMainBuildId
     * @throws Exception
     */
    @Transactional(rollbackFor = {Exception.class})
    public BasicBuildingMain appWriteBuilding(Integer caseMainBuildId) throws Exception {
        if (caseMainBuildId == null) {
            throw new Exception("null point");
        }
        Map<String, Object> map = Maps.newHashMap();
        this.clearInvalidData();//清理数据
        CaseBuildingMain caseBuildingMain = caseBuildingMainService.getCaseBuildingMainById(caseMainBuildId);
        if (caseBuildingMain == null) return null;
        BasicBuildingMain basicBuildingMain = new BasicBuildingMain();
        BeanUtils.copyProperties(caseBuildingMain, basicBuildingMain);
        basicBuildingMain.setApplyId(0);
        basicBuildingMain.setCreator(commonService.thisUserAccount());
        basicBuildingMain.setGmtCreated(null);
        basicBuildingMain.setGmtModified(null);
        basicBuildingMainDao.saveBasicBuildingMain(basicBuildingMain);

        CaseBuilding where = new CaseBuilding();
        where.setCaseBuildingMainId(caseMainBuildId);
        List<CaseBuilding> caseBuildingList = caseBuildingService.getCaseBuildingList(where);
        if (CollectionUtils.isEmpty(caseBuildingList)) return null;
        List<CaseBuildingOutfit> buildingOutfitList = null;
        List<CaseBuildingMaintenance> maintenanceList = null;
        List<CaseBuildingSurface> surfaceList = null;
        List<CaseBuildingFunction> functionList = null;
        for (CaseBuilding caseBuilding : caseBuildingList) {
            BasicBuilding basicBuilding = new BasicBuilding();
            BeanUtils.copyProperties(caseBuilding, basicBuilding);
            basicBuilding.setBasicBuildingMainId(basicBuildingMain.getId());
            basicBuilding.setCreator(commonService.thisUserAccount());
            basicBuilding.setGmtCreated(null);
            basicBuilding.setGmtModified(null);
            basicBuildingDao.saveBasicBuilding(basicBuilding);

            List<SysAttachmentDto> sysAttachmentDtoList = null;
            SysAttachmentDto queryFile = new SysAttachmentDto();
            queryFile.setTableId(caseBuilding.getId());
            queryFile.setTableName(FormatUtils.entityNameConvertToTableName(CaseBuilding.class));
            sysAttachmentDtoList = baseAttachmentService.getAttachmentList(queryFile);
            //复制 临时附件
            if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                for (SysAttachmentDto attachmentDto : sysAttachmentDtoList) {
                    SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
                    sysAttachmentDto.setTableId(0);
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


            buildingOutfitList = caseBuildingOutfitService.getCaseBuildingOutfitList(queryOutfit);
            maintenanceList = caseBuildingMaintenanceService.getCaseBuildingMaintenanceList(queryMaintenance);
            surfaceList = caseBuildingSurfaceService.getCaseBuildingSurfaceList(querySurface);
            functionList = caseBuildingFunctionService.getCaseBuildingFunctionListO(queryFunction);

            if (!ObjectUtils.isEmpty(buildingOutfitList)) {
                BasicBuildingOutfit basicBuildingOutfit = new BasicBuildingOutfit();
                for (CaseBuildingOutfit oo : buildingOutfitList) {
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
                BasicBuildingMaintenance basicBuildingMaintenance = new BasicBuildingMaintenance();
                for (CaseBuildingMaintenance oo : maintenanceList) {
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
                BasicBuildingSurface basicBuildingSurface = new BasicBuildingSurface();
                for (CaseBuildingSurface oo : surfaceList) {
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
                BasicBuildingFunction basicBuildingFunction = new BasicBuildingFunction();
                for (CaseBuildingFunction oo : functionList) {
                    BeanCopyHelp.copyPropertiesIgnoreNull(oo, basicBuildingFunction);
                    basicBuildingFunction.setBuildingId(basicBuilding.getId());
                    basicBuildingFunction.setId(null);
                    basicBuildingFunction.setCreator(commonService.thisUserAccount());
                    basicBuildingFunction.setGmtCreated(null);
                    basicBuildingFunction.setGmtModified(null);
                    basicBuildingFunctionService.saveAndUpdateBasicBuildingFunction(basicBuildingFunction);
                }
            }
        }
        return basicBuildingMain;
    }


}
