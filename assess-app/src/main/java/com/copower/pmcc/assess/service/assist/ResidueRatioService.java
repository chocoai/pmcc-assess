package com.copower.pmcc.assess.service.assist;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.dao.data.ToolResidueRatioDao;
import com.copower.pmcc.assess.dal.basis.entity.DataDamagedDegree;
import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatio;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseDamagedDegreeVo;
import com.copower.pmcc.assess.service.basic.BasicHouseDamagedDegreeService;
import com.copower.pmcc.assess.service.data.DataDamagedDegreeService;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ToolResidueRatio saveResidueRatio(String formData) throws Exception {
        ToolResidueRatio toolResidueRatio = JSON.parseObject(formData, ToolResidueRatio.class);
        JSONObject jsonObject = JSON.parseObject(formData);
        Integer houseId = toolResidueRatio.getHouseId();
        //更改数据表分值
        if (houseId != null) {
            List<BasicHouseDamagedDegreeVo> list = basicHouseDamagedDegreeService.getDamagedDegreeVoList(houseId);
            if (toolResidueRatio.getType() != 0) {
                if (CollectionUtils.isNotEmpty(list)) {
                    for (BasicHouseDamagedDegreeVo item : list) {
                        String scoreId = "scores" + item.getCategory();
                        String reallyScore = jsonObject.getString(scoreId);
                        BigDecimal score = new BigDecimal(reallyScore);
                        item.setScore(score);
                        basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(item);
                    }
                }
            }
        }
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
        return toolResidueRatio;
    }

    public HashMap<String, String> getObserveDate(Integer residueRatioId) {
        HashMap<String, String> observeDateMap = new HashMap<>();
        if (residueRatioId != null) {
            ToolResidueRatio toolResidueRatio = toolResidueRatioDao.getToolResidueRatio(residueRatioId);
            //成新法表中存在数据
            if (toolResidueRatio != null) {
                String parameterValue = toolResidueRatio.getParameterValue();
                Map map = JSON.parseObject(parameterValue, Map.class);
                observeDateMap.putAll(map);
                if (toolResidueRatio.getType() != 0) {
                    List<BasicHouseDamagedDegreeVo> list = basicHouseDamagedDegreeService.getDamagedDegreeVoList(toolResidueRatio.getHouseId());
                    if (CollectionUtils.isNotEmpty(list)) {
                        for (BasicHouseDamagedDegreeVo item : list) {
                            String scoreId = "scores" + item.getCategory();
                            String score = item.getScore().toString();
                            observeDateMap.put(scoreId, score);
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
        String level = "%50";
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

}
