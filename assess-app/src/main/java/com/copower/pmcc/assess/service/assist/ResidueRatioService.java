package com.copower.pmcc.assess.service.assist;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.common.enums.data.DataDamagedDegreeEnum;
import com.copower.pmcc.assess.dal.basis.dao.data.ToolResidueRatioDao;
import com.copower.pmcc.assess.dal.basis.dao.data.ToolResidueRatioObserveDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseDamagedDegree;
import com.copower.pmcc.assess.dal.basis.entity.DataDamagedDegree;
import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatio;
import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatioObserve;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseDamagedDegreeVo;
import com.copower.pmcc.assess.service.basic.BasicHouseDamagedDegreeService;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.data.DataDamagedDegreeService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: huhao
 * @Date: 2018/9/11 10:01
 * @Description:基础版块维护
 */
@Service
public class ResidueRatioService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private DataDamagedDegreeService dataDamagedDegreeService;
    @Autowired
    private ToolResidueRatioDao toolResidueRatioDao;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private ToolResidueRatioObserveDao toolResidueRatioObserveDao;


   public void copyDataToolResidueRatio(Integer copyId,ToolResidueRatio target){
       if (copyId == null){
           return;
       }
       ToolResidueRatio toolResidueRatio = getToolResidueRatio(copyId) ;
       toolResidueRatio.setId(null);
       BeanCopyHelp.copyPropertiesIgnoreNull(toolResidueRatio, target);
       target.setCreator(commonService.thisUserAccount());
       toolResidueRatioDao.addToolResidueRatio(target) ;
       ToolResidueRatioObserve queryToolResidueRatioObserve = new ToolResidueRatioObserve() ;
       queryToolResidueRatioObserve.setMasterId(copyId);
       List<ToolResidueRatioObserve> toolResidueRatioObserveList = toolResidueRatioObserveDao.getToolResidueRatioObserve(queryToolResidueRatioObserve);
       if (CollectionUtils.isNotEmpty(toolResidueRatioObserveList)){
           for (ToolResidueRatioObserve obj:toolResidueRatioObserveList){
               obj.setId(null);
               obj.setMasterId(target.getId());
           }
           toolResidueRatioObserveDao.batchInset(toolResidueRatioObserveList);
       }
   }

    public ToolResidueRatio saveResidueRatio(String formData) throws Exception {
        ToolResidueRatio toolResidueRatio = JSON.parseObject(formData, ToolResidueRatio.class);
        JSONObject jsonObject = JSON.parseObject(formData);
        //Integer houseId = toolResidueRatio.getHouseId();
        //保存
        HashMap<String, String> parameterMap = new HashMap<>();
        parameterMap.put("residueRatioStructuralScore", jsonObject.getString("residueRatioStructuralScore"));
        parameterMap.put("residueRatioDecorationScore", jsonObject.getString("residueRatioDecorationScore"));
        parameterMap.put("residueRatioEquipmentScore", jsonObject.getString("residueRatioEquipmentScore"));
        parameterMap.put("residueRatioOtherScore", jsonObject.getString("residueRatioOtherScore"));
        String parameterValue = JSONObject.toJSON(parameterMap).toString();
        toolResidueRatio.setParameterValue(parameterValue);

        if (toolResidueRatio.getId() != null && toolResidueRatio.getId() != 0) {
            toolResidueRatioDao.editToolResidueRatio(toolResidueRatio);
        } else {
            toolResidueRatio.setCreator(commonService.thisUserAccount());
            toolResidueRatioDao.addToolResidueRatio(toolResidueRatio);
        }
        List<ToolResidueRatioObserve> observeList = getToolResidueRatioObserveList(toolResidueRatio.getId());
        if (toolResidueRatio.getType() != 0) {
            for (ToolResidueRatioObserve item : observeList) {
                String scoreId = "scores" + item.getCategory();
                String reallyScore = jsonObject.getString(scoreId);
                BigDecimal score = new BigDecimal(reallyScore);
                item.setScore(score);

                String entityConditionNameStr = "entityConditionName" + item.getCategory();
                String entityConditionName = jsonObject.getString(entityConditionNameStr);
                item.setEntityCondition(entityConditionName);

                String entityConditionContentStr = "entityConditionContent" + item.getCategory();
                String entityConditionContent = jsonObject.getString(entityConditionContentStr);
                item.setEntityConditionContent(entityConditionContent);
                toolResidueRatioObserveDao.modifyToolResidueRatioObserve(item);
            }

        }

        return toolResidueRatio;
    }

    public ToolResidueRatio getToolResidueRatio(Integer id) {
        if (id == null) {
            return null;
        }
        return toolResidueRatioDao.getToolResidueRatio(id);
    }

    public HashMap<String, String> getObserveDate(Integer residueRatioId, Integer houseId) {
        HashMap<String, String> observeDateMap = new HashMap<>();
        if (residueRatioId != null) {
            ToolResidueRatio toolResidueRatio = toolResidueRatioDao.getToolResidueRatio(residueRatioId);
            //成新法表中存在数据
            if (toolResidueRatio != null) {
                String parameterValue = toolResidueRatio.getParameterValue();
                Map map = JSON.parseObject(parameterValue, Map.class);
                if (map != null) {
                    observeDateMap.putAll(map);
                }
                if (toolResidueRatio.getType() != null && toolResidueRatio.getType() != 0) {
                    List<ToolResidueRatioObserve> toolResidueRatioObserveList = getToolResidueRatioObserveList(toolResidueRatio.getId());
                    if (CollectionUtils.isNotEmpty(toolResidueRatioObserveList)) {
                        for (ToolResidueRatioObserve item : toolResidueRatioObserveList) {
                            String scoreId = "scores" + item.getCategory();
                            String score = item.getScore().toString();

                            String entityConditionNameId = "entityConditionName" + item.getCategory();
                            String entityConditionName = item.getEntityCondition();

                            String entityConditionContentId = "entityConditionContent" + item.getCategory();
                            String entityConditionContent = item.getEntityConditionContent();
                            observeDateMap.put(scoreId, score);
                            observeDateMap.put(entityConditionNameId, entityConditionName);
                            observeDateMap.put(entityConditionContentId, entityConditionContent);
                        }
                    }
                }
            }
        }
        return observeDateMap;
    }


    public ToolResidueRatio initAgeLimit(Integer residueRatioId) {
        ToolResidueRatio alreadyObj = new ToolResidueRatio();
        if (residueRatioId != null) {
            alreadyObj = toolResidueRatioDao.getToolResidueRatio(residueRatioId);
            //成新法表中存在数据
            if (alreadyObj != null) {
                return alreadyObj;
            }
        }
        return alreadyObj;
    }

    public BigDecimal getTypeScore(Integer houseId, String type) {
        DataDamagedDegree degree = dataDamagedDegreeService.getCacheDamagedDegreeByFieldName(type);
        BigDecimal weight = degree.getWeight();
        List<BasicHouseDamagedDegreeVo> structuralList = basicHouseDamagedDegreeService.getDamagedDegreeVoList(houseId, degree.getId());
        BigDecimal scoreTotal = new BigDecimal("0");
        for (BasicHouseDamagedDegreeVo item : structuralList) {
            if (item.getScore() != null) {
                scoreTotal = scoreTotal.add(item.getScore());
            }
        }
        return scoreTotal.multiply(weight);
    }


    //观察法成新率
    public String getObservationalRatio(Integer houseId) {
        BigDecimal structuralScore = this.getTypeScore(houseId, "structural.part");
        BigDecimal decorationScore = this.getTypeScore(houseId, "decoration.part");
        BigDecimal equipmentScore = this.getTypeScore(houseId, "equipment.part");
        BigDecimal otherScore = this.getTypeScore(houseId, "other");
        BigDecimal total = structuralScore.add(decorationScore).add(equipmentScore).add(otherScore);
        String level = "50%";
        if (total.compareTo(new BigDecimal("85")) == 1 && total.compareTo(new BigDecimal("100")) < 1) {
            level = "90%";
        }
        if (total.compareTo(new BigDecimal("65")) == 1 && total.compareTo(new BigDecimal("85")) < 1) {
            level = "75%";
        }
        if (total.compareTo(new BigDecimal("50")) == 1 && total.compareTo(new BigDecimal("65")) < 1) {
            level = "60%";
        }
        return level;
    }

    //观察法完损明细
    public ToolResidueRatioObserve getToolResidueRatioObserve(Integer masterId, Integer category) {
        ToolResidueRatioObserve toolResidueRatioObserve = new ToolResidueRatioObserve();
        toolResidueRatioObserve.setMasterId(masterId);
        toolResidueRatioObserve.setCategory(category);
        List<ToolResidueRatioObserve> list = toolResidueRatioObserveDao.getToolResidueRatioObserve(toolResidueRatioObserve);
        if (CollectionUtils.isNotEmpty(list)) return list.get(0);
        return null;
    }

    public List<ToolResidueRatioObserve> getToolResidueRatioObserveList(Integer masterId) {
        ToolResidueRatioObserve toolResidueRatioObserve = new ToolResidueRatioObserve();
        toolResidueRatioObserve.setMasterId(masterId);
        List<ToolResidueRatioObserve> list = toolResidueRatioObserveDao.getToolResidueRatioObserve(toolResidueRatioObserve);
        if (CollectionUtils.isNotEmpty(list)) return list;
        return null;
    }

    public List<BasicHouseDamagedDegreeVo> getDamagedDegreeList(Integer masterId, Integer type) {
        if (masterId == null || masterId <= 0) return null;
        ToolResidueRatioObserve item = new ToolResidueRatioObserve();
        item.setMasterId(masterId);
        item.setType(type);
        List<ToolResidueRatioObserve> list = toolResidueRatioObserveDao.getToolResidueRatioObserve(item);
        List<BasicHouseDamagedDegreeVo> transform = LangUtils.transform(list, o -> getBasicHouseDamagedDegreeVo(o));
        return transform;
    }

    public BasicHouseDamagedDegreeVo getBasicHouseDamagedDegreeVo(ToolResidueRatioObserve toolResidueRatioObserve) {
        DataDamagedDegree degree = dataDamagedDegreeService.getDamagedDegreeById(toolResidueRatioObserve.getType());

        BasicHouseDamagedDegreeVo basicHouseDamagedDegreeVo = new BasicHouseDamagedDegreeVo();
        BeanUtils.copyProperties(toolResidueRatioObserve, basicHouseDamagedDegreeVo);
        basicHouseDamagedDegreeVo.setTypeName(dataDamagedDegreeService.getNameById(toolResidueRatioObserve.getType()));
        DataDamagedDegree dataDamagedDegree = dataDamagedDegreeService.getCacheDamagedDegreeById(toolResidueRatioObserve.getCategory());
        if (dataDamagedDegree != null) {
            basicHouseDamagedDegreeVo.setCategoryName(dataDamagedDegree.getName());
            basicHouseDamagedDegreeVo.setStandardScore(dataDamagedDegree.getStandardScore());
            basicHouseDamagedDegreeVo.setEntityConditionName(toolResidueRatioObserve.getEntityCondition());
            basicHouseDamagedDegreeVo.setEntityConditionContent(toolResidueRatioObserve.getEntityConditionContent());
            basicHouseDamagedDegreeVo.setWeight(degree.getWeight());
            basicHouseDamagedDegreeVo.setIntact(dataDamagedDegree.getIntact());
            basicHouseDamagedDegreeVo.setBasicallyIntact(dataDamagedDegree.getBasicallyIntact());
            basicHouseDamagedDegreeVo.setGeneralDamage(dataDamagedDegree.getGeneralDamage());
            basicHouseDamagedDegreeVo.setSeriousDamage(dataDamagedDegree.getSeriousDamage());
            basicHouseDamagedDegreeVo.setHasChildren(!org.springframework.util.CollectionUtils.isEmpty(dataDamagedDegreeService.getCacheDamagedDegreeListByPid(dataDamagedDegree.getId())));
        }
        return basicHouseDamagedDegreeVo;
    }

    public ToolResidueRatio initMasterData(Integer residueRatioId, Integer houseId) throws Exception {
        if (residueRatioId == null||residueRatioId==0) {
            ToolResidueRatio toolResidueRatio = new ToolResidueRatio();
            toolResidueRatio.setCreator(commonService.thisUserAccount());
            toolResidueRatioDao.addToolResidueRatio(toolResidueRatio);
            List<DataDamagedDegree> degreeList = dataDamagedDegreeService.getCacheDamagedDegreeListByPid(0);
            List<ToolResidueRatioObserve> observeList = getToolResidueRatioObserveList(toolResidueRatio.getId());
            if (CollectionUtils.isEmpty(observeList)) {
                for (DataDamagedDegree degree : degreeList) {
                    List<DataDamagedDegree> damagedDegreeList = dataDamagedDegreeService.getCacheDamagedDegreeListByPid(degree.getId());
                    if (CollectionUtils.isNotEmpty(damagedDegreeList)) {
                        for (DataDamagedDegree damagedDegree : damagedDegreeList) {
                            ToolResidueRatioObserve observeItem = new ToolResidueRatioObserve();
                            observeItem.setStandardScore(damagedDegree.getStandardScore());
                            observeItem.setType(degree.getId());
                            observeItem.setCategory(damagedDegree.getId());
                            observeItem.setMasterId(toolResidueRatio.getId());
                            observeItem.setCreator(commonService.thisUserAccount());
                            if (houseId != null) {
                                BasicHouseDamagedDegree houseDamagedDegree = basicHouseDamagedDegreeService.getDamagedDegreeByHouseIdAndCategory(houseId, damagedDegree.getId());
                                if (houseDamagedDegree!=null&&StringUtils.isNotEmpty(houseDamagedDegree.getEntityCondition())) {
                                    observeItem.setEntityCondition(DataDamagedDegreeEnum.getEnumByKey(houseDamagedDegree.getEntityCondition()).getValue());
                                } else {
                                    observeItem.setEntityCondition("");
                                }
                                if (houseDamagedDegree!=null&&StringUtils.isNotEmpty(houseDamagedDegree.getEntityConditionContent())) {
                                    observeItem.setEntityConditionContent(houseDamagedDegree.getEntityConditionContent());
                                } else {
                                    observeItem.setEntityConditionContent("");
                                }
                                if (houseDamagedDegree!=null&&houseDamagedDegree.getScore() != null) {
                                    observeItem.setScore(houseDamagedDegree.getScore());
                                } else {
                                    observeItem.setScore(new BigDecimal("0"));
                                }

                            }
                            toolResidueRatioObserveDao.addToolResidueRatioObserve(observeItem);
                        }
                    }
                }
            }
            return toolResidueRatio;
        } else {
            return toolResidueRatioDao.getToolResidueRatio(residueRatioId);
        }

    }
}
