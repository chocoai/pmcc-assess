package com.copower.pmcc.assess.controller.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightRecord;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightRecordService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: zch
 * @date: 2019/3/18 17:26
 * @description:
 */

@RequestMapping(value = "/surveyAssetInventoryRightRecord", name = "他项权力申报(...)")
@RestController
public class SurveyAssetInventoryRightRecordController {

    @Autowired
    private SurveyAssetInventoryRightRecordService surveyAssetInventoryRightRecordService;

    @RequestMapping(value = "/save", name = "新增和修改他项权利", method = RequestMethod.POST)
    public HttpResult save(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord) {
        try {
            if (surveyAssetInventoryRightRecord != null) {
                if (surveyAssetInventoryRightRecord.getId() != null && surveyAssetInventoryRightRecord.getId().intValue() != 0) {
                    surveyAssetInventoryRightRecordService.updateSurveyAssetInventoryRightRecord(surveyAssetInventoryRightRecord);
                } else {
                    surveyAssetInventoryRightRecordService.addSurveyAssetInventoryRightRecord(surveyAssetInventoryRightRecord);
                }
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(surveyAssetInventoryRightRecord);
    }

}
