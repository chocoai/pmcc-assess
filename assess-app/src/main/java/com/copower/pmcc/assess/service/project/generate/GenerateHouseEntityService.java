package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeJudgeObjectDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingOutfitVo;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitDecorateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 房屋实体信息
 */
@Service
public class GenerateHouseEntityService {
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private SchemeJudgeObjectDao schemeJudgeObjectDao;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicBuildingOutfitService basicBuildingOutfitService;
    @Autowired
    private BasicUnitDecorateService basicUnitDecorateService;
    @Autowired
    private BasicBuildingFunctionService basicBuildingFunctionService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;

    //物业这块无需处理

    /**
     * 获取楼盘名称
     *
     * @param basicEstate
     * @return
     */
    public String getEstateName(BasicEstate basicEstate) {
        try {
            if (basicEstate == null) return "";
            return basicEstate.getName();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取建造年份
     *
     * @param judgeObjectIds
     * @return
     */
    public String getBuildingYear(List<Integer> judgeObjectIds) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            Date beCompletedTime = basicBuilding.getBeCompletedTime();
            int year = DateUtils.getYear(beCompletedTime);
            map.put(schemeJudgeObject.getId(), String.format("%s年", year));
        }
        return generateCommonMethod.judgeSummaryDesc(map, "分别为");
    }

    /**
     * 获取工程质量
     *
     * @param judgeObjectIds
     * @return
     */
    public String getConstructionQuality(List<Integer> judgeObjectIds) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            Integer constructionQuality = basicBuilding.getConstructionQuality();
            if (constructionQuality != null && constructionQuality > 0) {
                map.put(schemeJudgeObject.getId(), baseDataDicService.getNameById(constructionQuality));
            }
        }
        return generateCommonMethod.judgeSummaryDesc(map, "分别为");
    }

    /**
     * 获取建筑结构
     *
     * @param judgeObjectIds
     * @return
     */
    public String getBuildingStructure(List<Integer> judgeObjectIds) {
        return "";
    }

    /**
     * 获取建筑规模
     *
     * @param judgeObjectIds
     * @return
     */
    public String getBuildingScale(List<Integer> judgeObjectIds) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            StringBuilder builder = new StringBuilder();
            if (basicBuilding.getBuildingArea() != null)
                builder.append(String.format("建筑面积%s平方米，", basicBuilding.getBuildingArea()));
            if (basicBuilding.getInJacketArea() != null)
                builder.append(String.format("套内面积%s平方米，", basicBuilding.getInJacketArea()));
            if (basicBuilding.getUseArea() != null)
                builder.append(String.format("使用面积%s平方米，", basicBuilding.getUseArea()));
            if (basicBuilding.getBuildingHeight() != null)
                builder.append(String.format("建筑高度%s米，", basicBuilding.getBuildingHeight()));
            if (basicBuilding.getFirstFloor() != null)
                builder.append(String.format("首层%s至", basicBuilding.getFirstFloor()));
            if (basicBuilding.getMaxFloor() != null)
                builder.append(String.format("最高层%s是", basicBuilding.getMaxFloor()));
            if (basicBuilding.getPropertyType() != null)
                builder.append(String.format("%s，", baseDataDicService.getNameById(basicBuilding.getPropertyType())));
            if (basicBuilding.getFloorHeight() != null)
                builder.append(String.format("层高%s米", basicBuilding.getFloorHeight()));
            map.put(schemeJudgeObject.getId(), builder.toString());
        }
        return generateCommonMethod.judgeEachDesc(map, "", ";");
    }

    /**
     * 获取层高
     *
     * @param judgeObjectIds
     * @return
     */
    public String getFloorHeight(List<Integer> judgeObjectIds) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<Integer, String> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            BigDecimal floorHeight = basicBuilding.getFloorHeight();
            if (floorHeight != null) {
                map.put(schemeJudgeObject.getId(), String.format("%s米", floorHeight));
            }
        }
        return generateCommonMethod.judgeSummaryDesc(map, "分别为");
    }

    /**
     * 获取空间布局
     *
     * @param judgeObjectIds
     * @return
     */
    public String getSpatialDistribution(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<Integer, String> map = Maps.newHashMap();
        BaseDataDic production = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_UNIT_HUXING_TYPE_PRODUCTION);
        BaseDataDic office = baseDataDicService.getCacheDataDicByFieldName(AssessExamineTaskConstant.EXAMINE_UNIT_HUXING_TYPE_OFFICE);
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
            if (basicHouse != null && basicHouse.getHuxingId() != null && basicHouse.getHuxingId() > 0) {
                BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
                BasicUnitHuxing basicUnitHuxing = basicUnitHuxingService.getBasicUnitHuxingById(basicHouse.getHuxingId());
                if (basicUnitHuxing == null) continue;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(String.format("梯户比%s,", basicUnit.getElevatorHouseholdRatio()));
                if (basicUnitHuxing.getType().equals(production.getId())) {//办公商业取开间进深
                    stringBuilder.append(String.format("开间%s米,", basicUnitHuxing.getBay()));
                    stringBuilder.append(String.format("进深%s米,", basicUnitHuxing.getDeep()));
                } else if (basicUnitHuxing.getType().equals(office.getId())) {//工业仓储取跨长跨宽
                    stringBuilder.append(String.format("跨长%s米,", basicUnitHuxing.getSpanLength()));
                    stringBuilder.append(String.format("跨宽%s米,", basicUnitHuxing.getSpanWidth()));
                    stringBuilder.append(String.format("跨数%s米,", basicUnitHuxing.getSpanNumber()));
                } else {
                    stringBuilder.append(String.format("%s,", basicUnitHuxing.getName()));
                }
                if (StringUtils.isNotBlank(basicUnitHuxing.getDescription())) {
                    stringBuilder.append(basicUnitHuxing.getDescription());
                }
            }
        }
        return generateCommonMethod.trim(generateCommonMethod.judgeEachDesc(map, "", ";"));
    }

    /**
     * 获取装饰装修
     *
     * @param judgeObjectIds
     * @return
     */
    public String getDecoration(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<String, List<Integer>> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
            String key = String.format("%s&%s", basicBuilding.getBuildingNumber(), basicUnit.getUnitNumber());
            if (map.containsKey(key)) {
                List<Integer> list = map.get(key);
                list.add(schemeJudgeObject.getId());
            } else {
                map.put(key, Lists.newArrayList(schemeJudgeObject.getId()));
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append(BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME);
            SchemeJudgeObject judgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(entry.getValue().get(0));
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApply.getId());
            List<BasicBuildingOutfitVo> outfitVos = basicBuildingOutfitService.getBasicBuildingOutfitVos(basicBuilding.getId());
            StringBuilder outfitBuilder = new StringBuilder();
            if (CollectionUtils.isNotEmpty(outfitVos)) {
                outfitVos.forEach(o -> {
                    outfitBuilder.append(o.getDecorationPart())
                            .append(o.getMaterialGradeName()).append(o.getDecoratingMaterialName()).append(",");
                });
            }
            stringBuilder.append(String.format("楼栋外装%s;", outfitBuilder));
            List<BasicUnitDecorateVo> unitDecorates = basicUnitDecorateService.getBasicUnitDecorateList(basicUnit.getId());
            StringBuilder unitDecorateBuilder = new StringBuilder();
            if (CollectionUtils.isNotEmpty(unitDecorates)) {
                unitDecorates.forEach(o -> {
                    unitDecorateBuilder.append(o.getDecorationPartName())
                            .append(o.getMaterialGradeName()).append(o.getDecoratingMaterialName()).append(",");
                });
            }
            stringBuilder.append(String.format("楼栋内装%s;", outfitBuilder));
        }
        return generateCommonMethod.trim(stringBuilder.toString());
    }

    /**
     * 获取外观
     *
     * @param judgeObjectIds
     * @return
     */
    public String getAppearance(List<Integer> judgeObjectIds) throws Exception {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<String, List<Integer>> map = groupByBuilding(judgeObjectList);
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            stringBuilder.append(generateCommonMethod.convertNumber(entry.getValue())).append(BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME);
            SchemeJudgeObject judgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(entry.getValue().get(0));
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            stringBuilder.append(String.format("外观风格%s,", baseDataDicService.getNameById(basicBuilding.getAppearanceStyle())));
            stringBuilder.append(String.format("外观%s;", baseDataDicService.getNameById(basicBuilding.getAppearanceNewAndOld())));
        }
        return generateCommonMethod.trim(stringBuilder.toString());
    }

    /**
     * 获取设备设施
     *
     * @param judgeObjectIds
     * @return
     */
    public String getEquipmentFacilitie(List<Integer> judgeObjectIds) {//

        return null;
    }

    /**
     * 估价对象按楼栋分组
     *
     * @param judgeObjectList
     * @return
     */
    private Map<String, List<Integer>> groupByBuilding(List<SchemeJudgeObject> judgeObjectList) {
        Map<String, List<Integer>> map = Maps.newHashMap();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(schemeJudgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            String key = basicBuilding.getBuildingNumber();
            if (map.containsKey(key)) {
                List<Integer> list = map.get(key);
                list.add(schemeJudgeObject.getId());
            } else {
                map.put(key, Lists.newArrayList(schemeJudgeObject.getId()));
            }
        }
        return map;
    }

    /**
     * 获取建筑功能
     *
     * @param judgeObjectIds
     * @return
     */
    public String getBuildingFunction(List<Integer> judgeObjectIds) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectDao.getListByIds(judgeObjectIds);
        Map<String, List<Integer>> map = groupByBuilding(judgeObjectList);
        StringBuilder stringBuilder = new StringBuilder();
        //楼栋中的建筑功能按楼栋描述
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            SchemeJudgeObject judgeObject = schemeJudgeObjectDao.getSchemeJudgeObject(entry.getValue().get(0));
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            BasicBuilding basicBuilding = basicBuildingService.getBasicBuildingByApplyId(basicApply.getId());
            List<BasicBuildingFunction> functionList = basicBuildingFunctionService.getBasicBuildingFunctionList(basicBuilding.getId());
            if (CollectionUtils.isNotEmpty(functionList)) {
                stringBuilder.append(basicBuilding.getBuildingName());
                for (int i = 0; i < functionList.size(); i++) {
                    BasicBuildingFunction function = functionList.get(i);
                    stringBuilder.append(baseDataDicService.getNameById(function.getDecorationPart()))
                            .append(baseDataDicService.getNameById(function.getType()));
                    stringBuilder.append(i == (functionList.size() - 1) ? "；" : "，");
                }
            }
        }
        //房间中采光等信息按估价对象分别描述
        for (SchemeJudgeObject judgeObject : judgeObjectList) {
            stringBuilder.append(String.format("%s%s", judgeObject.getNumber(), BaseConstant.ASSESS_JUDGE_OBJECT_CN_NAME));
            BasicApply basicApply = surveyCommonService.getSceneExploreBasicApply(judgeObject.getDeclareRecordId());
            BasicHouse basicHouse = basicHouseService.getHouseByApplyId(basicApply.getId());
            List<BasicHouseRoom> roomList = basicHouseRoomService.getBasicHouseRoomList(basicHouse.getId());

            for (BasicHouseRoom room : roomList) {
                stringBuilder.append(baseDataDicService.getNameById(room.getRoomType()));
                Map<String, String> stringMap = Maps.newHashMap();
                stringMap.put("通风",room.getAeration());
                stringMap.put("日照",room.getSunshine());
                stringMap.put("采光",room.getLighting());
                stringMap.put("隔音",room.getSoundInsulation());
                stringBuilder.append(generateCommonMethod.stringSummaryDesc(stringMap,"","，"));
            }
        }
        return generateCommonMethod.trim(stringBuilder.toString());
    }

}
