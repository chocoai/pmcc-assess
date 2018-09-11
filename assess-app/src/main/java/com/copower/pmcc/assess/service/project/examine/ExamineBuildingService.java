package com.copower.pmcc.assess.service.project.examine;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.ExamineFileUpLoadFieldEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineBuildingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineBuildingFunctionVo;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineBuildingVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBuilderService;
import com.copower.pmcc.assess.service.data.DataDeveloperService;
import com.copower.pmcc.assess.service.data.DataPropertyService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Auther: zch
 * @Date: 2018/7/23 17:29
 * @Description:楼栋基础信息
 */
@Service
public class ExamineBuildingService {
    @Autowired
    private ExamineBuildingDao examineBuildingDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataBuilderService dataBuilderService;
    @Autowired
    private DataPropertyService dataPropertyService;
    @Autowired
    private DataDeveloperService dataDeveloperService;
    @Autowired
    private ExamineBuildingOutfitService examineBuildingOutfitService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ExamineBuildingMaintenanceService examineBuildingMaintenanceService;
    @Autowired
    private ExamineBuildingSurfaceService examineBuildingSurfaceService;
    @Autowired
    private ExamineBuildingFunctionService examineBuildingFunctionService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Map<String, Object> copyBuildFileData(String formData) {
        Map<String, Object> objectMap = Maps.newHashMap();
        JSONObject jsonObject = JSON.parseObject(formData);
        SysAttachmentDto sysAttachmentDto = null;
        StringBuilder builder = new StringBuilder();
        if (jsonObject != null) {
            String identifier = jsonObject.getString("identifier");
            if (!StringUtils.isEmpty(identifier)) {
                String floorPlanAttachmentId = jsonObject.getString("floorPlanAttachmentId");
                String figureOutsideAttachmentId = jsonObject.getString("figureOutsideAttachmentId");
                String floorAppearanceFigureAttachmentId = jsonObject.getString("floorAppearanceFigureAttachmentId");
                if (!StringUtils.isEmpty(floorPlanAttachmentId)) {
                    if (NumberUtils.isNumber(floorPlanAttachmentId)) {
                        sysAttachmentDto = baseAttachmentService.getSysAttachmentDto(Integer.parseInt(floorPlanAttachmentId));
                        if (sysAttachmentDto != null) {
                            builder.append(ExamineFileUpLoadFieldEnum.buildingFloorPlan.getName()).append(identifier);
                            sysAttachmentDto.setId(null);
                            sysAttachmentDto.setFieldsName(builder.toString());
                            baseAttachmentService.addAttachment(sysAttachmentDto);
                            objectMap.put("floorPlanAttachmentId", sysAttachmentDto);
                            sysAttachmentDto = null;
                            builder.setLength(0);
                        }
                    }
                }
                if (!StringUtils.isEmpty(figureOutsideAttachmentId)) {
                    if (NumberUtils.isNumber(figureOutsideAttachmentId)) {
                        sysAttachmentDto = baseAttachmentService.getSysAttachmentDto(Integer.parseInt(figureOutsideAttachmentId));
                        if (sysAttachmentDto != null) {
                            builder.append(ExamineFileUpLoadFieldEnum.buildingFigureOutside.getName()).append(identifier);
                            sysAttachmentDto.setId(null);
                            sysAttachmentDto.setFieldsName(builder.toString());
                            baseAttachmentService.addAttachment(sysAttachmentDto);
                            objectMap.put("figureOutsideAttachmentId", sysAttachmentDto);
                            sysAttachmentDto = null;
                            builder.setLength(0);
                        }
                    }
                }
                if (!StringUtils.isEmpty(floorAppearanceFigureAttachmentId)) {
                    if (NumberUtils.isNumber(floorAppearanceFigureAttachmentId)) {
                        sysAttachmentDto = baseAttachmentService.getSysAttachmentDto(Integer.parseInt(floorAppearanceFigureAttachmentId));
                        if (sysAttachmentDto != null) {
                            builder.append(ExamineFileUpLoadFieldEnum.buildingFloorAppearanceFigure.getName()).append(identifier);
                            sysAttachmentDto.setId(null);
                            sysAttachmentDto.setFieldsName(builder.toString());
                            baseAttachmentService.addAttachment(sysAttachmentDto);
                            objectMap.put("floorAppearanceFigureAttachmentId", sysAttachmentDto);
                        }
                    }
                }
            }

        }
        return objectMap;
    }

    public boolean copySonMainOutfitSurfaceFunction(Integer planDetailsId, Integer examineType, Integer declareId, String oldBuildNumber, String newBuildNumber) {
        ExamineBuildingOutfit buildingOutfit = new ExamineBuildingOutfit();
        if (planDetailsId != null) {
            buildingOutfit.setPlanDetailsId(planDetailsId);
        }
        if (examineType != null) {
            buildingOutfit.setExamineType(examineType);
        }
        if (declareId != null) {
            buildingOutfit.setDeclareId(declareId);
        }
        if (!StringUtils.isEmpty(oldBuildNumber)) {
            buildingOutfit.setBuildNumber(oldBuildNumber);
        }
        List<ExamineBuildingOutfit> examineBuildingOutfitList = examineBuildingOutfitService.getExamineBuildingOutfitList(buildingOutfit);
        //由于上面四个参数 子类都有,因此
        ExamineBuildingMaintenance maintenance = new ExamineBuildingMaintenance();
        BeanUtils.copyProperties(buildingOutfit, maintenance);
        List<ExamineBuildingMaintenance> examineBuildingMaintenances = examineBuildingMaintenanceService.getExamineBuildingMaintenanceList(maintenance);
        ExamineBuildingSurface buildingSurface = new ExamineBuildingSurface();
        BeanUtils.copyProperties(buildingOutfit, buildingSurface);
        List<ExamineBuildingSurface> buildingSurfaceList = examineBuildingSurfaceService.getExamineBuildingSurfaceList(buildingSurface);
        ExamineBuildingFunction function = new ExamineBuildingFunction();
        BeanUtils.copyProperties(buildingOutfit, function);
        List<ExamineBuildingFunctionVo> buildingFunctions = examineBuildingFunctionService.getExamineBuildingFunctionList(function);

        try {
            if (!ObjectUtils.isEmpty(examineBuildingOutfitList)) {
                for (ExamineBuildingOutfit oo : examineBuildingOutfitList) {
                    oo.setBuildNumber(newBuildNumber);
                    oo.setId(null);
                    examineBuildingOutfitService.addExamineBuildingOutfit(oo);
                }
            }
            if (!ObjectUtils.isEmpty(examineBuildingMaintenances)) {
                for (ExamineBuildingMaintenance oo : examineBuildingMaintenances) {
                    oo.setBuildNumber(newBuildNumber);
                    oo.setId(null);
                    examineBuildingMaintenanceService.addExamineBuildingMaintenance(oo);
                }
            }
            if (!ObjectUtils.isEmpty(buildingSurfaceList)) {
                for (ExamineBuildingSurface oo : buildingSurfaceList) {
                    oo.setBuildNumber(newBuildNumber);
                    oo.setId(null);
                    examineBuildingSurfaceService.addExamineBuildingSurface(oo);
                }
            }
            if (!ObjectUtils.isEmpty(buildingFunctions)) {
                for (ExamineBuildingFunctionVo oo : buildingFunctions) {
                    oo.setBuildNumber(newBuildNumber);
                    oo.setId(null);
                    examineBuildingFunctionService.saveAndUpdateExamineBuildingFunction(oo);
                }
            }
        } catch (Exception e1) {
            logger.error(String.format("%s%s", "异常! ==>", e1.getMessage()));
            return false;
        }
        return true;
    }

    public void initSonMainOutfitSurfaceFunction() {
        ExamineBuildingOutfit buildingOutfit = new ExamineBuildingOutfit();
        buildingOutfit.setBuildNumber("0");
        ExamineBuildingMaintenance maintenance = new ExamineBuildingMaintenance();
        maintenance.setBuildNumber("0");
        ExamineBuildingSurface buildingSurface = new ExamineBuildingSurface();
        buildingSurface.setBuildNumber("0");
        ExamineBuildingFunction function = new ExamineBuildingFunction();
        function.setBuildNumber("0");
        boolean f1 = examineBuildingOutfitService.removeExamineBuildingOutfit(buildingOutfit);
        boolean f2 = examineBuildingMaintenanceService.removeExamineBuildingMaintenance(maintenance);
        boolean f3 = examineBuildingSurfaceService.removeExamineBuildingSurface(buildingSurface);
        boolean f4 = examineBuildingFunctionService.removeExamineBuildingFunction(function);
        if (f1 && f2 && f3 && f4) {
        } else {
            try {
                throw new Exception("其中某一个删除数据出问题了");
            } catch (Exception e1) {

            }
        }
    }

    public void updateSonMainOutfitSurface(String buildNumber) {
        ExamineBuildingMaintenance maintenance = new ExamineBuildingMaintenance();
        maintenance.setBuildNumber("0");
        List<ExamineBuildingMaintenance> buildingMaintenanceList = examineBuildingMaintenanceService.getExamineBuildingMaintenanceList(maintenance);
        if (!ObjectUtils.isEmpty(buildingMaintenanceList)) {
            for (ExamineBuildingMaintenance buildingMaintenance : buildingMaintenanceList) {
                buildingMaintenance.setBuildNumber(buildNumber);
                examineBuildingMaintenanceService.updateExamineBuildingMaintenance(buildingMaintenance);
            }
        }
        ExamineBuildingOutfit buildingOutfit = new ExamineBuildingOutfit();
        buildingOutfit.setBuildNumber("0");
        List<ExamineBuildingOutfit> examineBuildingOutfitList = examineBuildingOutfitService.getExamineBuildingOutfitList(buildingOutfit);
        if (!ObjectUtils.isEmpty(examineBuildingOutfitList)) {
            for (ExamineBuildingOutfit examineBuildingOutfit : examineBuildingOutfitList) {
                examineBuildingOutfit.setBuildNumber(buildNumber);
                examineBuildingOutfitService.updateExamineBuildingOutfit(examineBuildingOutfit);
            }
        }
        ExamineBuildingSurface buildingSurface = new ExamineBuildingSurface();
        buildingSurface.setBuildNumber("0");
        List<ExamineBuildingSurface> examineBuildingSurfaceList = examineBuildingSurfaceService.getExamineBuildingSurfaceList(buildingSurface);
        if (!ObjectUtils.isEmpty(examineBuildingSurfaceList)) {
            for (ExamineBuildingSurface examineBuildingSurface : examineBuildingSurfaceList) {
                examineBuildingSurface.setBuildNumber(buildNumber);
                examineBuildingSurfaceService.updateExamineBuildingSurface(examineBuildingSurface);
            }
        }
        ExamineBuildingFunction function = new ExamineBuildingFunction();
        function.setBuildNumber("0");
        List<ExamineBuildingFunctionVo> functionList = examineBuildingFunctionService.getExamineBuildingFunctionList(function);
        if (!ObjectUtils.isEmpty(functionList)) {
            for (ExamineBuildingFunction oo : functionList) {
                oo.setBuildNumber(buildNumber);
                examineBuildingFunctionService.saveAndUpdateExamineBuildingFunction(oo);
            }
        }
    }

    public List<ExamineBuilding> getByDeclareIdAndExamineType(Integer declareId, Integer planDetailsId, Integer examineType) {
        return examineBuildingDao.getByDeclareIdAndExamineType(declareId, planDetailsId, examineType);
    }

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public ExamineBuildingVo getExamineBuildingById(Integer id) {
        ExamineBuildingVo examineBuildingVo = getExamineBuildingVo(examineBuildingDao.getBuildingById(id));
        return examineBuildingVo;
    }


    /**
     * 获取数据列表
     *
     * @param examineBuilding
     * @return
     */
    public List<ExamineBuilding> getExamineBuildingList(ExamineBuilding examineBuilding) {
        return examineBuildingDao.getBuildingList(examineBuilding);
    }

    public BootstrapTableVo getExamineBuildingLists(ExamineBuilding examineBuilding) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineBuildingVo> vos = Lists.newArrayList();
        getExamineBuildingList(examineBuilding).stream().forEach(oo -> vos.add(getExamineBuildingVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineBuildingVo>() : vos);
        return vo;
    }

    public ExamineBuildingVo getExamineBuildingVo(ExamineBuilding examineBuilding) {
        ExamineBuildingVo vo = new ExamineBuildingVo();
        if (examineBuilding != null) {
            BeanUtils.copyProperties(examineBuilding, vo);
            if (examineBuilding.getBuildingStructure() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getDataDicById(examineBuilding.getBuildingStructure());
                if (baseDataDic != null) {
                    vo.setBuildingStructureName(baseDataDic.getName());
                    vo.setBuildingStructurePid(baseDataDic.getPid());
                }
            }
            if (examineBuilding.getBuildingCategory() != null) {
                vo.setBuildingCategoryName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_PROPERTY_CATEGORY, examineBuilding.getBuildingCategory()));
            }
            if (examineBuilding.getBuilderId() != null) {
                DataBuilder dataBuilder = dataBuilderService.getByDataBuilderId(examineBuilding.getBuilderId());
                if (dataBuilder != null) {
                    vo.setBuilderName(dataBuilder.getName());
                }
            }
            if (examineBuilding.getPropertyId() != null) {
                DataProperty dataProperty = dataPropertyService.getByDataPropertyId(examineBuilding.getPropertyId());
                if (dataProperty != null) {
                    vo.setPropertyName(dataProperty.getName());
                }
            }
            if (examineBuilding.getPropertyType() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getDataDicById(examineBuilding.getPropertyType());
                if (baseDataDic != null) {
                    vo.setPropertyTypeName(baseDataDic.getName());
                }
            }

        }
        return vo;
    }

    private String getValue(String key, Integer v) {
        StringBuilder builder = new StringBuilder(1024);
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(key);
        if (baseDataDic.size() >= 1) {
            if (v != null) {
                for (BaseDataDic base : baseDataDic) {
                    if (base.getId().intValue() == v.intValue()) {
                        builder.append(base.getName());
                    }
                }
            }
        }
        return builder.toString();
    }

    public void saveExamineBuilding(List<ExamineBuilding> examineBuildings) {
        Integer id = 0;
        try {
            if (!ObjectUtils.isEmpty(examineBuildings)) {
                for (ExamineBuilding oo : examineBuildings) {
                    if (oo != null) {
                        StringBuilder builder = new StringBuilder();
                        if (oo.getId() == null || oo.getId().intValue() == 0) {
                            //新增
                            oo.setCreator(commonService.thisUserAccount());
                            id = examineBuildingDao.addBuilding(oo);
                            oo.setId(id);
                            oo.setJsonContent(JSON.toJSONString(getExamineBuildingVo(oo)));
                            examineBuildingDao.updateBuilding(oo);
                        } else {
                            //真正的更新
                            examineBuildingDao.updateBuilding(oo);
                            oo.setJsonContent(JSON.toJSONString(getExamineBuildingVo(oo)));
                            examineBuildingDao.updateBuilding(oo);
                            id = oo.getId();
                        }
                        try {
                            if (!StringUtils.isEmpty(oo.getIdentifier())){
                                updateSysAttachmentDto(builder.append(ExamineFileUpLoadFieldEnum.buildingFloorPlan.getName()).append(oo.getIdentifier()).toString(), id);
                                builder.setLength(0);
                                updateSysAttachmentDto(builder.append(ExamineFileUpLoadFieldEnum.buildingFigureOutside.getName()).append(oo.getIdentifier()).toString(), id);
                                builder.setLength(0);
                                updateSysAttachmentDto(builder.append(ExamineFileUpLoadFieldEnum.buildingFloorAppearanceFigure.getName()).append(oo.getIdentifier()).toString(), id);
                            }
                        } catch (Exception e1) {
                            try {
                                throw e1;
                            } catch (Exception e11) {

                            }
                        }
                    }
                }
            }
        } catch (Exception e1) {
            logger.error(String.format("实体解析失败! ==> %s", e1.getMessage()));//不需要抛出异常
        }
    }

    private void updateSysAttachmentDto(String fileName, Integer id) {
        List<SysAttachmentDto> sysAttachmentDtoList = null;
        try {
            sysAttachmentDtoList = baseAttachmentService.getByField_tableId(0, fileName, FormatUtils.entityNameConvertToTableName(ExamineBuilding.class));
            if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                    sysAttachmentDto.setTableId(id);
                    baseAttachmentService.updateAttachment(sysAttachmentDto);
                }
            }
        } catch (Exception e1) {
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineBuilding(Integer id) {
        return examineBuildingDao.deleteBuilding(id);
    }

    public List<DataBuilder> getDataBuilderList() {
        return dataBuilderService.getDataBuilderList(null);
    }

    public List<DataProperty> getDataPropertyList() {
        return dataPropertyService.getDataPropertyList(null);
    }

    public List<DataDeveloper> getDataDeveloperList() {
        return dataDeveloperService.getDataDeveloperList(null);
    }

}
