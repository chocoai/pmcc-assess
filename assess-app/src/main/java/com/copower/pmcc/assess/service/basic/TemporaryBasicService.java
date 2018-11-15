package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BasicJsonFieldStrEnum;
import com.copower.pmcc.assess.dal.basic.entity.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/15 17:42
 * @Description:
 */
@Service
public class TemporaryBasicService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicBuildingMainService basicBuildingMainService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;

    /**
     * 保存临时数据
     *
     * @param formData
     * @param bisNextUser
     * @throws Exception
     */
    public void saveBasic(String formData) throws Exception {
        if (StringUtils.isEmpty(formData)) {
            return;
        }
        JSONObject jsonObject = JSON.parseObject(formData);
        String jsonContent = null;
        BasicEstate basicEstate = null;
        if (StringUtils.isNotEmpty(jsonObject.getString(BasicJsonFieldStrEnum.INDUSTRY.getVar()))) {
        }
        //楼盘过程数据
        jsonContent = jsonObject.getString(BasicJsonFieldStrEnum.BASICESTATE.getVar());
        if (StringUtils.isNotBlank(jsonContent)) {
            try {
                basicEstate = JSONObject.parseObject(jsonContent, BasicEstate.class);
            } catch (Exception e1) {

            }
            if (basicEstate != null) {
                if (basicEstate.getId() != null) {
                    basicEstate.setCaseEstateId(basicEstate.getId());
                    basicEstate.setId(null);
                    basicEstate.setTemporary(true);
                }
                try {
                    basicEstateService.upgradeVersion(basicEstate);
                    if (basicEstate.getId() != null) {
                        BasicEstateLandState basicEstateLandState = null;
                        if (StringUtils.isNotEmpty(jsonObject.getString(BasicJsonFieldStrEnum.BASICESTATELANDSTATE.getVar()))) {
                            try {
                                basicEstateLandState = JSONObject.parseObject(jsonObject.getString(BasicJsonFieldStrEnum.BASICESTATELANDSTATE.getVar()), BasicEstateLandState.class);
                            } catch (Exception e1) {

                            }
                            if (basicEstateLandState != null) {
                                if (basicEstateLandState.getId() != null) {
                                    basicEstateLandState.setCaseEstateLandStateId(basicEstateLandState.getId());
                                    basicEstateLandState.setId(null);
                                    basicEstateLandState.setTemporary(true);
                                }
                                basicEstateLandState.setEstateId(basicEstate.getId());
                                basicEstateLandStateService.upgradeVersion(basicEstateLandState);
                            }
                        }
                    }
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        }
        //楼栋主过程数据
        jsonContent = null;
        try {
            jsonContent = jsonObject.getString(BasicJsonFieldStrEnum.BASICBUILDINGMAIN.getVar());
        } catch (Exception e1) {

        }
        BasicBuildingMain basicBuildingMain = null;
        if (StringUtils.isNotBlank(jsonContent)) {
            basicBuildingMain = JSONObject.parseObject(jsonContent, BasicBuildingMain.class);
            if (basicBuildingMain != null) {
                if (StringUtils.isNotEmpty(basicBuildingMain.getIdentifier())) {
                }
                if (basicBuildingMain.getId() != null) {
                    basicBuildingMain.setCaseBuildingMain(basicBuildingMain.getId());
                    basicBuildingMain.setId(null);
                    basicBuildingMain.setTemporary(true);
                }
                //当楼栋数据未包含楼盘 那么楼盘必须是新增的情况
                //1
                if (basicEstate == null) {
                    if (basicBuildingMain.getEstateId() != null) {
                        //...............
                    }
                    if (basicBuildingMain.getEstateId() == null) {
                        throw new Exception("未选择楼盘!");
                    }
                }
                //2
                if (basicEstate != null) {
                    if (basicEstate.getId() != null) {
                        basicBuildingMain.setEstateId(basicEstate.getId());
                    }
                    if (basicEstate.getId() == null) {
                        throw new Exception("楼盘数据异常!");
                    }
                }
                try {
                    basicBuildingMainService.upgradeVersion(basicBuildingMain);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
            //楼栋过程数据
            jsonContent = null;
            try {
                jsonContent = jsonObject.getString(BasicJsonFieldStrEnum.BASICBUILDINGS.getVar());
            } catch (Exception e1) {

            }
            List<BasicBuilding> basicBuildingList = null;
            try {
                basicBuildingList = JSONObject.parseArray(jsonContent, BasicBuilding.class);
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
            }
            if (!ObjectUtils.isEmpty(basicBuildingList)) {
                for (BasicBuilding basicBuilding : basicBuildingList) {
                    if (basicBuilding.getId() != null) {
                        basicBuilding.setCaseBuildingId(basicBuilding.getId());
                        basicBuilding.setId(null);
                    }
                    if (basicBuildingMain.getId() != null) {
                        basicBuilding.setBasicBuildingMainId(basicBuildingMain.getId());
                        basicBuilding.setTemporary(true);
                        try {
                            basicBuildingService.upgradeVersion(basicBuilding);
                        } catch (Exception e1) {
                            logger.error(e1.getMessage(), e1);
                        }
                    }
                }
            }
        }
        //单元过程数据
        jsonContent = null;
        try {
            jsonContent = jsonObject.getString(BasicJsonFieldStrEnum.BASICUNIT.getVar());
        } catch (Exception e1) {

        }
        BasicUnit basicUnit = null;
        if (StringUtils.isNotEmpty(jsonContent)) {
            basicUnit = JSONObject.parseObject(jsonContent, BasicUnit.class);
            if (basicUnit != null) {
                if (StringUtils.isNotEmpty(basicUnit.getUnitNumber())) {
                }
                if (basicUnit.getId() != null) {
                    basicUnit.setCaseUnitId(basicUnit.getId());
                    basicUnit.setId(null);
                    basicUnit.setTemporary(true);
                }
                if (basicBuildingMain != null) {
                    if (basicBuildingMain.getId() != null) {
                        basicUnit.setBasicBuildingMainId(basicBuildingMain.getId());
                    }
                    if (basicBuildingMain.getId() == null) {
                        throw new Exception("楼栋主数据异常!");
                    }
                }
                if (basicBuildingMain == null) {
                    if (basicUnit.getBasicBuildingMainId() != null) {
                        //....................
                    }
                    if (basicUnit.getBasicBuildingMainId() == null) {
                        throw new Exception("未选择楼栋主数据!");
                    }
                }
                try {
                    basicUnitService.upgradeVersion(basicUnit);
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        }
        //处理房屋数据
        jsonContent = null;
        try {
            jsonContent = jsonObject.getString(BasicJsonFieldStrEnum.BASICHOUSE.getVar());
        } catch (Exception e1) {

        }
        BasicHouse basicHouse = null;
        if (StringUtils.isNotEmpty(jsonContent)) {
            basicHouse = JSONObject.parseObject(jsonContent, BasicHouse.class);
            if (basicHouse != null) {
                if (basicHouse.getId() != null) {
                    basicHouse.setCaseHouseId(basicHouse.getId());
                    basicHouse.setId(null);
                    basicHouse.setTemporary(true);
                }
                if (StringUtils.isNotEmpty(basicHouse.getHouseNumber())) {
                }
                if (basicUnit == null) {
                    if (basicHouse.getUnitId() != null) {
                        //
                    }
                    if (basicHouse.getUnitId() == null) {
                        throw new Exception("未选择单元");
                    }
                }
                if (basicUnit != null) {
                    if (basicUnit.getId() != null) {
                        basicHouse.setUnitId(basicUnit.getId());
                    }
                    if (basicUnit.getId() == null) {
                        throw new Exception("单元exception");
                    }
                }
                Integer house = basicHouseService.upgradeVersion(basicHouse);
                try {
                    BasicHouseTrading basicTrading = JSONObject.parseObject(jsonObject.getString(BasicJsonFieldStrEnum.BASICTRADING.getVar()), BasicHouseTrading.class);
                    if (basicTrading != null) {
                        if (basicTrading.getId() != null) {
                            basicTrading.setCaseTradingId(basicTrading.getId());
                            basicTrading.setId(null);
                            basicTrading.setTemporary(true);
                        }
                        basicTrading.setHouseId(house);
                        basicHouseTradingService.upgradeVersion(basicTrading);
                    }
                } catch (Exception e1) {
                    logger.error(e1.getMessage(), e1);
                }
            }
        }
    }
}
