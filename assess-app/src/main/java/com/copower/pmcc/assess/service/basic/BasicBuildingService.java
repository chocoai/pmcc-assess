package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.BasicBuildingFieldEnum;
import com.copower.pmcc.assess.dal.basic.dao.BasicBuildingDao;
import com.copower.pmcc.assess.dal.basic.entity.*;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataBuilder;
import com.copower.pmcc.assess.dal.basis.entity.DataProperty;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 初始化或者更新
     *
     * @param id
     * @param buildingNumber
     * @throws Exception
     */
    public void init(Integer id, String buildingNumber,BasicBuilding basicBuilding) throws Exception {
        if (id != null) {
            if (StringUtils.isEmpty(buildingNumber)) {
                throw new Exception("不符合条件");
            }
        }
        if (StringUtils.isNotEmpty(buildingNumber)) {
            if (id == null) {
                throw new Exception("不符合条件");
            }
        }
        List<BasicBuildingFunction> basicBuildingFunctionList = null;
        List<BasicBuildingMaintenance> basicBuildingMaintenanceList = null;
        List<BasicBuildingOutfit> basicBuildingOutfitList = null;
        List<BasicBuildingSurface> basicBuildingSurfaceList = null;
        BasicBuildingSurface querySurface = new BasicBuildingSurface();
        BasicBuildingFunction queryFunction = new BasicBuildingFunction();
        BasicBuildingOutfit queryOutfit = new BasicBuildingOutfit();
        BasicBuildingMaintenance queryMaintenance = new BasicBuildingMaintenance();
        querySurface.setBuildingId(0);
        queryFunction.setBuildingId(0);
        queryOutfit.setBuildingId(0);
        queryMaintenance.setBuildingId(0);
        querySurface.setCreator(commonService.thisUserAccount());
        queryFunction.setCreator(commonService.thisUserAccount());
        queryOutfit.setCreator(commonService.thisUserAccount());
        queryMaintenance.setCreator(commonService.thisUserAccount());
        if (id == null) {
            SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
            sysAttachmentDto.setTableId(0);
            sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
            List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getAttachmentList(sysAttachmentDto);
            if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
                for (SysAttachmentDto oo : sysAttachmentDtos) {
                    baseAttachmentService.deleteAttachment(oo.getId());
                }
            }
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
        }

        if (id != null) {
            querySurface.setBuildingNumber(buildingNumber);
            queryFunction.setBuildingNumber(buildingNumber);
            queryOutfit.setBuildingNumber(buildingNumber);
            queryMaintenance.setBuildingNumber(buildingNumber);
            basicBuildingSurfaceList = basicBuildingSurfaceService.basicBuildingSurfaceList(querySurface);
            basicBuildingFunctionList = basicBuildingFunctionService.basicBuildingFunctionList(queryFunction);
            basicBuildingOutfitList = basicBuildingOutfitService.basicBuildingOutfitList(queryOutfit);
            basicBuildingMaintenanceList = basicBuildingMaintenanceService.basicBuildingMaintenanceList(queryMaintenance);
            for (BasicBuildingSurface oo : basicBuildingSurfaceList) {
                oo.setBuildingId(id);
                oo.setTemporary(basicBuilding.getTemporary());
                basicBuildingSurfaceService.saveAndUpdateBasicBuildingSurface(oo);
            }
            for (BasicBuildingFunction oo : basicBuildingFunctionList) {
                oo.setBuildingId(id);
                oo.setTemporary(basicBuilding.getTemporary());
                basicBuildingFunctionService.saveAndUpdateBasicBuildingFunction(oo);
            }
            for (BasicBuildingOutfit oo : basicBuildingOutfitList) {
                oo.setBuildingId(id);
                oo.setTemporary(basicBuilding.getTemporary());
                basicBuildingOutfitService.saveAndUpdateBasicBuildingOutfit(oo);
            }
            for (BasicBuildingMaintenance oo : basicBuildingMaintenanceList) {
                oo.setBuildingId(id);
                oo.setTemporary(basicBuilding.getTemporary());
                basicBuildingMaintenanceService.saveAndUpdateBasicBuildingMaintenance(oo);
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
    public BasicBuilding getBasicBuildingById(Integer id) throws Exception {
        return basicBuildingDao.getBasicBuildingById(id);
    }

    public boolean update(BasicBuilding basicBuilding) throws Exception {
        return basicBuildingDao.updateBasicBuilding(basicBuilding);
    }

    public Integer upgradeVersion(BasicBuilding basicBuilding) throws Exception {
        if (basicBuilding.getId() == null || basicBuilding.getId().intValue() == 0) {
            basicBuilding.setCreator(commonService.thisUserAccount());
            if (basicBuilding.getVersion() == null) {
                basicBuilding.setVersion(0);
            }
            Integer id = basicBuildingDao.saveBasicBuilding(basicBuilding);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class), id);
            if (basicBuilding.getPart() != null) {
                this.init(id, String.valueOf(basicBuilding.getPart()),basicBuilding);
            }
            return id;
        } else {
            BasicBuilding oo = basicBuildingDao.getBasicBuildingById(basicBuilding.getId());
            if (oo.getVersion() == null) {
                oo.setVersion(0);
            }
            basicBuilding.setVersion(oo.getVersion() + 1);
            basicBuildingDao.updateBasicBuilding(basicBuilding);
            return null;
        }
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
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class), id);
            this.init(id, BasicBuildingFieldEnum.BuildFour.getKey(),basicBuilding);
            this.init(id, BasicBuildingFieldEnum.BuildThree.getKey(),basicBuilding);
            this.init(id, BasicBuildingFieldEnum.BuildTwo.getKey(),basicBuilding);
            this.init(id, BasicBuildingFieldEnum.BuildOne.getKey(),basicBuilding);
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
        BaseDataDic dataDic = null;
        if (basicBuilding.getPropertyType() != null) {
            dataDic = baseDataDicService.getDataDicById(basicBuilding.getPropertyType());
            if (dataDic != null) {
                vo.setPropertyTypeName(dataDic.getName());
                dataDic = null;
            }
        }
        if (basicBuilding.getBuildingStructure() != null) {
            dataDic = baseDataDicService.getDataDicById(basicBuilding.getBuildingStructure());
            if (dataDic != null) {
                vo.setBuildingStructureName(dataDic.getName());
                dataDic = null;
            }
        }
        if (basicBuilding.getBuildingStructureLower() != null) {
            dataDic = baseDataDicService.getDataDicById(basicBuilding.getBuildingStructureLower());
            if (dataDic != null) {
                vo.setBuildingStructureLowerName(dataDic.getName());
                dataDic = null;
            }
        }
        if (basicBuilding.getBuildingCategory() != null) {
            dataDic = baseDataDicService.getDataDicById(basicBuilding.getBuildingCategory());
            if (dataDic != null) {
                vo.setBuildingCategoryName(dataDic.getName());
                dataDic = null;
            }
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
        if (basicBuilding.getBeCompletedTime() != null){
            vo.setBeCompletedTimeName(DateUtils.format(basicBuilding.getBeCompletedTime()));
        }
        return vo;
    }


}
