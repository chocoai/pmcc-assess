package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouse;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.service.basic.BasicBuildingService;
import com.copower.pmcc.assess.service.basic.BasicEstateService;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.basic.BasicUnitService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:56
 * @Description:楼栋
 */
@RequestMapping(value = "/basicApplyHistory")
@Controller
public class BasicApplyHistoryController {
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseService basicHouseService;
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @ResponseBody
    @RequestMapping(value = "/getDataByRelevanceId", name = "获取数据列表", method = {RequestMethod.GET})
    public BootstrapTableVo getDataByRelevanceId(String tableName, Integer relevanceId) {
        BootstrapTableVo vo = null;
        try {
            if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicEstate.class))) {
                BasicEstate basicEstate = new BasicEstate();
                basicEstate.setRelevanceId(relevanceId);
                vo = basicEstateService.getBootstrapTableVo(basicEstate);
            }
            if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicBuilding.class))) {
                BasicBuilding building = new BasicBuilding();
                building.setRelevanceId(relevanceId);
                vo = basicBuildingService.getBootstrapTableVo(building);
            }
            if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicUnit.class))) {
                BasicUnit unit = new BasicUnit();
                unit.setRelevanceId(relevanceId);
                vo = basicUnitService.getBootstrapTableVo(unit);
            }
            if (Objects.equal(tableName, FormatUtils.entityNameConvertToTableName(BasicHouse.class))) {
                BasicHouse basicHouse = new BasicHouse();
                basicHouse.setRelevanceId(relevanceId);
                vo = basicHouseService.getBootstrapTableVo(basicHouse);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return null;
        }
        return vo;
    }


}
