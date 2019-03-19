package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRightRecord;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightRecordService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @RequestMapping(value = "/saveFormData", name = "新增和修改他项权利", method = RequestMethod.POST)
    public HttpResult saveFormData(String formData){
        try {
            List<SurveyAssetInventoryRightRecord> surveyAssetInventoryRightRecordList = JSON.parseArray(formData,SurveyAssetInventoryRightRecord.class);
            if (CollectionUtils.isNotEmpty(surveyAssetInventoryRightRecordList)){
                for (SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord:surveyAssetInventoryRightRecordList){
                    if (surveyAssetInventoryRightRecord.getId() != null && surveyAssetInventoryRightRecord.getId().intValue() != 0) {
                        surveyAssetInventoryRightRecordService.updateSurveyAssetInventoryRightRecord(surveyAssetInventoryRightRecord);
                    } else {
                        surveyAssetInventoryRightRecordService.addSurveyAssetInventoryRightRecord(surveyAssetInventoryRightRecord);
                    }
                }
            }
            return HttpResult.newCorrectResult(formData);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @GetMapping(value = "/getSurveyAssetInventoryRightRecordById",name = "")
    public HttpResult getSurveyAssetInventoryRightRecordById(Integer id){
        try {
            return HttpResult.newCorrectResult(surveyAssetInventoryRightRecordService.getSurveyAssetInventoryRightRecordById(id));
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/remove",name = "clear")
    public HttpResult remove(SurveyAssetInventoryRightRecord surveyAssetInventoryRightRecord){
        try {
            surveyAssetInventoryRightRecordService.clear(surveyAssetInventoryRightRecord);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(surveyAssetInventoryRightRecord);
    }

}
