package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.*;
import com.copower.pmcc.assess.service.assist.ResidueRatioService;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/25 10:31
 * @Description:
 */
@Service
public class PublicBasicService {
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private ResidueRatioService residueRatioService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseService basicHouseService;


    private final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * @param sourcePlanDetailsId
     * @param targetPlanDetailsId
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicApply copyForExamine(Integer sourcePlanDetailsId, Integer targetPlanDetailsId) throws Exception {
        BasicApply sourceBasicApply = basicApplyService.getBasicApplyByPlanDetailsId(sourcePlanDetailsId);
        if (sourceBasicApply == null)
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());

        basicApplyBatchService.deleteBatchByPlanDetailsId(targetPlanDetailsId);

        BasicApply targetBasicApply = new BasicApply();
        BeanUtils.copyProperties(sourceBasicApply, targetBasicApply);
        targetBasicApply.setPlanDetailsId(targetPlanDetailsId);
        targetBasicApply.setStatus(ProjectStatusEnum.STARTAPPLY.getKey());
        targetBasicApply.setBasicEstateId(0);
        targetBasicApply.setBasicBuildingId(0);
        targetBasicApply.setBasicUnitId(0);
        targetBasicApply.setBasicHouseId(0);
        targetBasicApply.setId(null);
        targetBasicApply.setCreator(commonService.thisUserAccount());
        targetBasicApply.setGmtCreated(new Date());
        targetBasicApply.setGmtModified(new Date());
        basicApplyService.saveBasicApply(targetBasicApply);

        //取数据
        BasicEstate basicEstateSource = getBasicEstateByAppId(sourceBasicApply);
        BasicBuilding basicBuildingSource = getBasicBuildingByAppId(sourceBasicApply);
        BasicUnit basicUnitSource = getBasicUnitByAppId(sourceBasicApply);
        BasicHouse basicHouseSource = getBasicHouseVoByAppId(sourceBasicApply);

        BasicEstate basicEstateNew = basicEstateService.copyBasicEstate(basicEstateSource.getId(),null, true);//处理楼盘
        BasicBuilding basicBuilding = basicBuildingService.copyBasicBuilding(basicBuildingSource.getId(),null, true);//处理楼栋
        BasicUnit basicUnit = basicUnitService.copyBasicUnit(basicUnitSource.getId(),null, true);//处理单元
        BasicHouse basicHouse = basicHouseService.copyBasicHouse(basicHouseSource.getId(),null, true);//处理房屋
        targetBasicApply.setBasicEstateId(basicEstateNew.getId());
        targetBasicApply.setBasicBuildingId(basicBuilding.getId());
        targetBasicApply.setBasicUnitId(basicUnit.getId());
        targetBasicApply.setBasicHouseId(basicHouse.getId());
        basicApplyService.updateBasicApply(targetBasicApply);

        BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchByPlanDetailsId(sourcePlanDetailsId);
        basicApplyBatch.setId(null);
        basicApplyBatch.setPlanDetailsId(targetPlanDetailsId);
        basicApplyBatch.setEstateId(basicEstateNew.getId());
        basicApplyBatch.setEstateName(basicEstateNew.getName());
        basicApplyBatch.setCreator(commonService.thisUserAccount());
        basicApplyBatchService.addBasicApplyBatch(basicApplyBatch);

        BasicApplyBatchDetail estateBatchDetail = new BasicApplyBatchDetail();
        estateBatchDetail.setPid(0);
        estateBatchDetail.setApplyBatchId(basicApplyBatch.getId());
        estateBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstate.class));
        estateBatchDetail.setTableId(basicEstateNew.getId());
        estateBatchDetail.setName(basicEstateNew.getName());
        estateBatchDetail.setDisplayName(basicEstateNew.getName());
        estateBatchDetail.setBisStandard(false);
        estateBatchDetail.setExecutor(commonService.thisUserAccount());
        basicApplyBatchDetailService.saveBasicApplyBatchDetail(estateBatchDetail);

        BasicApplyBatchDetail buildingBatchDetail = new BasicApplyBatchDetail();
        buildingBatchDetail.setPid(estateBatchDetail.getId());
        buildingBatchDetail.setApplyBatchId(basicApplyBatch.getId());
        buildingBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicBuilding.class));
        buildingBatchDetail.setTableId(basicBuilding.getId());
        buildingBatchDetail.setName(basicBuilding.getBuildingNumber());
        buildingBatchDetail.setDisplayName(basicBuilding.getBuildingName());
        buildingBatchDetail.setBisStandard(false);
        buildingBatchDetail.setExecutor(commonService.thisUserAccount());
        basicApplyBatchDetailService.saveBasicApplyBatchDetail(buildingBatchDetail);

        BasicApplyBatchDetail unitBatchDetail = new BasicApplyBatchDetail();
        unitBatchDetail.setPid(buildingBatchDetail.getId());
        unitBatchDetail.setApplyBatchId(basicApplyBatch.getId());
        unitBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        unitBatchDetail.setTableId(basicUnit.getId());
        unitBatchDetail.setName(basicUnit.getUnitNumber());
        unitBatchDetail.setDisplayName(String.format("%s单元", basicUnit.getUnitNumber()));
        unitBatchDetail.setBisStandard(false);
        unitBatchDetail.setExecutor(commonService.thisUserAccount());
        basicApplyBatchDetailService.saveBasicApplyBatchDetail(unitBatchDetail);

        BasicApplyBatchDetail houseBatchDetail = new BasicApplyBatchDetail();
        houseBatchDetail.setPid(unitBatchDetail.getId());
        houseBatchDetail.setApplyBatchId(basicApplyBatch.getId());
        houseBatchDetail.setTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class));
        houseBatchDetail.setTableId(basicHouse.getId());
        houseBatchDetail.setName(basicHouse.getHouseNumber());
        houseBatchDetail.setDisplayName(basicHouse.getHouseNumber());
        houseBatchDetail.setBisStandard(true);
        houseBatchDetail.setExecutor(commonService.thisUserAccount());
        basicApplyBatchDetailService.saveBasicApplyBatchDetail(houseBatchDetail);
        return targetBasicApply;
    }

    public BasicEstateVo getBasicEstateByAppId(BasicApply basicApply) {
        BasicEstate estate = basicEstateService.getBasicEstateByApplyId(basicApply.getId());
        if (estate == null) return null;
        return basicEstateService.getBasicEstateVo(estate);
    }

    public BasicEstateVo getBasicEstateById(Integer id) throws Exception {
        return basicEstateService.getBasicEstateVo(basicEstateService.getBasicEstateById(id));
    }

    public BasicEstateLandStateVo getEstateLandStateByAppId(BasicApply basicApply) throws Exception {
        BasicEstateVo vo = getBasicEstateByAppId(basicApply);
        if (vo == null) return null;
        BasicEstateLandState basicEstateLandState = basicEstateLandStateService.getLandStateByEstateId(vo.getId());
        if (basicEstateLandState == null) return null;
        return basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandState);
    }

    public BasicEstateLandStateVo getEstateLandStateByEstateId(Integer estateId) throws Exception {
        BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
        basicEstateLandState.setEstateId(estateId);
        List<BasicEstateLandState> basicEstateLandStateList = basicEstateLandStateService.basicEstateLandStateList(basicEstateLandState);
        if (!ObjectUtils.isEmpty(basicEstateLandStateList)) {
            return basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateList.get(0));
        } else {
            return null;
        }
    }

    public BasicBuildingVo getBasicBuildingByAppId(BasicApply basicApply) throws Exception {
        return basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
    }

    public BasicBuildingVo getBasicBuildingById(Integer id) throws Exception {
        return basicBuildingService.getBasicBuildingVo(basicBuildingService.getBasicBuildingById(id));
    }

    public BasicUnit getBasicUnitByAppId(BasicApply basicApply) {
        return basicUnitService.getBasicUnitByApplyId(basicApply.getId());
    }

    public BasicUnit getBasicUnitById(Integer id) throws Exception {
        return basicUnitService.getBasicUnitById(id);
    }

    public BasicHouseTradingVo getBasicHouseTradingByAppId(BasicApply basicApply) throws Exception {
        BasicHouseVo basicHouseVo = getBasicHouseVoByAppId(basicApply);
        if(basicHouseVo==null) return null;
        return getBasicHouseTradingByHouseId(basicHouseVo.getId());
    }

    public BasicHouseTradingVo getBasicHouseTradingByHouseId(Integer houseId) throws Exception {
        return basicHouseTradingService.getBasicHouseTradingVo(basicHouseTradingService.getTradingByHouseId(houseId));
    }

    public BasicHouseVo getBasicHouseVoByAppId(BasicApply basicApply) throws Exception {
        BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
        if (basicHouse == null) return null;
        return basicHouseService.getBasicHouseVo(basicHouse);
    }

    public BasicHouseVo getBasicHouseVoById(Integer id) throws Exception {
        return basicHouseService.getBasicHouseVo(basicHouseService.getBasicHouseById(id));
    }

}
