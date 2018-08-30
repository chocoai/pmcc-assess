package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineFileUpLoadFieldEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineBuildingDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
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
import com.google.common.collect.Ordering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<ExamineBuilding> getByDeclareIdAndExamineType(Integer declareId,Integer planDetailsId, Integer examineType) {
        return examineBuildingDao.getByDeclareIdAndExamineType(declareId,planDetailsId, examineType);
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

    public ExamineBuilding getOneAndTwoData(Integer examineType, Integer declareId, Integer number) {
        ExamineBuilding oo = new ExamineBuilding();
        oo.setExamineType(examineType);
        oo.setDeclareId(declareId);
        List<ExamineBuilding> examineBuildings = getExamineBuildingList(oo);
        if (!ObjectUtils.isEmpty(examineBuildings)) {
            Ordering<ExamineBuilding> firstOrdering = Ordering.from(new Comparator<ExamineBuilding>() {
                @Override
                public int compare(ExamineBuilding o1, ExamineBuilding o2) {
                    return o1.getGmtCreated().compareTo(o2.getGmtCreated());
                }
//            }).reverse();//排序 并且反转
            });//排序 并且反转
            Collections.sort(examineBuildings, firstOrdering);
            ExamineBuilding examineBuilding = null;
            if (number.equals(1)) {
                examineBuilding = examineBuildings.get(0);
            }
            if (examineBuildings.size() >= 2){
                if (number.equals(2)) {
                    examineBuilding = examineBuildings.get(1);
                }
            }
            return examineBuilding;
        }
        return null;
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
        if (examineBuilding != null){
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
            if (examineBuilding.getPropertyType() != null){
                BaseDataDic baseDataDic = baseDataDicService.getDataDicById(examineBuilding.getPropertyType());
                if (baseDataDic != null){
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

    /**
     * 新增
     *
     * @param examineBuilding
     * @return
     */
    @Transactional
    public boolean addExamineBuilding(ExamineBuilding examineBuilding) {
        examineBuilding.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineBuilding.getDeclareId())) {
            examineBuilding.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineBuilding.getExamineType())) {
            examineBuilding.setExamineType(0);
        }
        ExamineBuildingOutfit examineBuildingOutfit = new ExamineBuildingOutfit();
        examineBuildingOutfit.setDeclareId(examineBuilding.getDeclareId());
        examineBuildingOutfit.setExamineType(examineBuilding.getExamineType());
        examineBuildingOutfit.setBuildingId(0);
        List<ExamineBuildingOutfit> examineBuildingOutfitList = examineBuildingOutfitService.getExamineBuildingOutfitList(examineBuildingOutfit);
        ExamineBuildingMaintenance examineBuildingMaintenance = new ExamineBuildingMaintenance();
        examineBuildingMaintenance.setBuildingId(0);
        List<ExamineBuildingMaintenance> examineBuildingMaintenances = examineBuildingMaintenanceService.getExamineBuildingMaintenanceList(examineBuildingMaintenance);
        ExamineBuildingSurface examineBuildingSurface = new ExamineBuildingSurface();
        examineBuildingSurface.setBuildingId(0);
        List<ExamineBuildingSurface> examineBuildingSurfaceList = examineBuildingSurfaceService.getExamineBuildingSurfaceList(examineBuildingSurface);
        try {
            int id = examineBuildingDao.addBuilding(examineBuilding);
            if (!ObjectUtils.isEmpty(examineBuildingOutfitList)) {
                for (ExamineBuildingOutfit oo : examineBuildingOutfitList) {
                    oo.setBuildingId(id);
                    examineBuildingOutfitService.updateExamineBuildingOutfit(oo);
                }
            }
            if (!ObjectUtils.isEmpty(examineBuildingMaintenances)){
                for (ExamineBuildingMaintenance maintenance:examineBuildingMaintenances){
                    maintenance.setBuildingId(id);
                    examineBuildingMaintenanceService.updateExamineBuildingMaintenance(maintenance);
                }
            }
            if (!ObjectUtils.isEmpty(examineBuildingSurfaceList)){
                for (ExamineBuildingSurface buildingSurface:examineBuildingSurfaceList){
                    buildingSurface.setBuildingId(id);
                    examineBuildingSurfaceService.updateExamineBuildingSurface(buildingSurface);
                }
            }
            updateSysAttachmentDto(ExamineFileUpLoadFieldEnum.buildingFloorPlan.getName(),id);
            updateSysAttachmentDto(ExamineFileUpLoadFieldEnum.buildingFigureOutside.getName(),id);
            updateSysAttachmentDto(ExamineFileUpLoadFieldEnum.buildingFloorAppearanceFigure.getName(),id);
            return true;
        } catch (Exception e1) {
            logger.error(String.format("%s%s", "异常! ===>", e1.getMessage()), e1);
            return false;
        }
    }

    private void updateSysAttachmentDto(String fileName,Integer id){
        List<SysAttachmentDto> sysAttachmentDtoList = null;
        sysAttachmentDtoList = baseAttachmentService.getByField_tableId(0, fileName, FormatUtils.entityNameConvertToTableName(ExamineBuilding.class));
        if (!ObjectUtils.isEmpty(sysAttachmentDtoList)){
            for (SysAttachmentDto sysAttachmentDto:sysAttachmentDtoList){
                sysAttachmentDto.setTableId(id);
                baseAttachmentService.updateAttachment(sysAttachmentDto);
            }
        }
    }

    /**
     * 编辑
     *
     * @param examineBuilding
     * @return
     */
    public boolean updateExamineBuilding(ExamineBuilding examineBuilding) {
        ExamineBuildingOutfit examineBuildingOutfit = new ExamineBuildingOutfit();
        examineBuildingOutfit.setDeclareId(examineBuilding.getDeclareId());
        examineBuildingOutfit.setExamineType(examineBuilding.getExamineType());
        examineBuildingOutfit.setBuildingId(0);
        List<ExamineBuildingOutfit> examineBuildingOutfitList = examineBuildingOutfitService.getExamineBuildingOutfitList(examineBuildingOutfit);
        ExamineBuildingMaintenance examineBuildingMaintenance = new ExamineBuildingMaintenance();
        examineBuildingMaintenance.setBuildingId(0);
        List<ExamineBuildingMaintenance> examineBuildingMaintenances = examineBuildingMaintenanceService.getExamineBuildingMaintenanceList(examineBuildingMaintenance);
        ExamineBuildingSurface examineBuildingSurface = new ExamineBuildingSurface();
        examineBuildingSurface.setBuildingId(0);
        List<ExamineBuildingSurface> examineBuildingSurfaceList = examineBuildingSurfaceService.getExamineBuildingSurfaceList(examineBuildingSurface);
        if (!ObjectUtils.isEmpty(examineBuildingOutfitList)) {
            for (ExamineBuildingOutfit oo : examineBuildingOutfitList) {
                oo.setBuildingId(examineBuilding.getId());
                examineBuildingOutfitService.updateExamineBuildingOutfit(oo);
            }
        }
        if (!ObjectUtils.isEmpty(examineBuildingMaintenances)){
            for (ExamineBuildingMaintenance maintenance:examineBuildingMaintenances){
                maintenance.setBuildingId(examineBuilding.getId());
                examineBuildingMaintenanceService.updateExamineBuildingMaintenance(maintenance);
            }
        }
        if (!ObjectUtils.isEmpty(examineBuildingSurfaceList)){
            for (ExamineBuildingSurface buildingSurface:examineBuildingSurfaceList){
                buildingSurface.setBuildingId(examineBuilding.getId());
                examineBuildingSurfaceService.updateExamineBuildingSurface(buildingSurface);
            }
        }
        updateSysAttachmentDto(ExamineFileUpLoadFieldEnum.buildingFloorPlan.getName(),examineBuilding.getId());
        updateSysAttachmentDto(ExamineFileUpLoadFieldEnum.buildingFigureOutside.getName(),examineBuilding.getId());
        updateSysAttachmentDto(ExamineFileUpLoadFieldEnum.buildingFloorAppearanceFigure.getName(),examineBuilding.getId());
        return examineBuildingDao.updateBuilding(examineBuilding);
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
