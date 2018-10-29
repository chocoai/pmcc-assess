package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMain;
import com.copower.pmcc.assess.service.cases.CaseBuildingMainService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/28 14:29
 * @Description:
 */
@RequestMapping(value = "/caseBuildingMain")
@Controller
public class CaseBuildingMainController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseBuildingMainService caseBuildingMainService;

    @ResponseBody
    @RequestMapping(value = "/getcaseBuildingMainById", method = {RequestMethod.GET}, name = "获取案例 楼栋--")
    public HttpResult getCaseBuildingById(Integer id) {
        CaseBuildingMain caseBuildingMain = null;
        try {
            if (id != null) {
                caseBuildingMain = caseBuildingMainService.getCaseBuildingMainById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseBuildingMain);
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdatecaseBuildingMain", method = {RequestMethod.POST}, name = "更新案例 楼栋--")
    public HttpResult saveAndUpdateCaseBuilding(CaseBuildingMain caseBuildingMain) {
        try {
            caseBuildingMainService.saveAndUpdate(caseBuildingMain);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/autoCompleteCaseBuilding", method = {RequestMethod.GET}, name = "楼栋-- 信息自动补全")
    public HttpResult autoCompleteCaseEstate(String identifier, Integer estateId, Integer maxRows) {
        List<KeyValueDto> keyValueDtos = Lists.newArrayList();
        try {
            List<CaseBuildingMain> buildingMains = caseBuildingMainService.autoCompleteCaseBuildingMain(identifier, estateId, maxRows);
            for (CaseBuildingMain caseBuilding : buildingMains) {
                KeyValueDto keyValueDto = new KeyValueDto();
                keyValueDto.setKey(String.valueOf(caseBuilding.getId()));
                keyValueDto.setValue(caseBuilding.getIdentifier());
                keyValueDtos.add(keyValueDto);
            }
            return HttpResult.newCorrectResult(keyValueDtos);
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }
}
