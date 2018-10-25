package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basic.entity.BasicApply;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstate;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zch
 * @Date: 2018/10/25 10:31
 * @Description:
 */
@Service
public class PublicBasicService {
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicApplyService basicApplyService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void saveBasic(String formData, Boolean bisNextUser) throws Exception {
        if (StringUtils.isEmpty(formData)) {
            return;
        }
        BasicApply basicApply = new BasicApply();
        basicApplyService.saveBasicApply(basicApply);

        JSONObject jsonObject = JSON.parseObject(formData);
        String jsonContent = null;

        jsonContent = jsonObject.getString("basicEstate");
        if (StringUtils.isNotBlank(jsonContent)) {
            BasicEstate basicEstate = JSONObject.parseObject(jsonContent, BasicEstate.class);
            basicEstate.setApplyId(basicApply.getId());
            basicEstateService.saveAndUpdateBasicEstate(basicEstate);
        }

        ProcessUserDto processUserDto = basicApplyService.sumTask(basicApply.getId(), FormatUtils.entityNameConvertToTableName(BasicApply.class));
        basicApply.setProcessInsId(processUserDto.getProcessInsId());
        basicApply.setStatus(ProjectStatusEnum.RUNING.getKey());
        basicApplyService.updateBasicApply(basicApply);
    }
}
