package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.dao.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.dao.SurveyAssetOtherTemplateDao;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetInventoryDto;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetOtherTemplateDto;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetCommonDataDto;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zly on 2018/5/9.
 */

@Service
public class SurveyAssetInventoryService {

    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private SurveyAssetInventoryDao surveyAssetInventoryDao;
    @Autowired
    private SurveyAssetOtherTemplateDao surveyAssetOtherTemplateDao;

    public boolean save(SurveyAssetCommonDataDto surveyAssetCommonDataDto) throws BusinessException {
        if(surveyAssetCommonDataDto != null){
            SurveyAssetInventoryDto surveyAssetInventoryDto = surveyAssetCommonDataDto.getSurveyAssetInventoryDto();
            surveyAssetInventoryDto.setCreator(serviceComponent.getThisUser());
            int pid = surveyAssetInventoryDao.save(surveyAssetInventoryDto);

            SurveyAssetOtherTemplateDto surveyAssetOtherTemplateDto = surveyAssetCommonDataDto.getSurveyAssetOtherTemplateDto();
            surveyAssetOtherTemplateDto.setPid(pid);
            boolean j = surveyAssetOtherTemplateDao.save(surveyAssetOtherTemplateDto);
            if(j == true){
                return true;
            }
        }
        return false;
    }

    public SurveyAssetCommonDataDto format(String val){
        SurveyAssetCommonDataDto dto = null;
        if (StringUtils.isNotBlank(val)){
            dto = JSON.parseObject(val,SurveyAssetCommonDataDto.class);
        }
        return dto;
    }

}
