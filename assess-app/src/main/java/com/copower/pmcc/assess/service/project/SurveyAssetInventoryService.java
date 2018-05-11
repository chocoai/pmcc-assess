package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.dao.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.dao.SurveyAssetOtherTemplateDao;
import com.copower.pmcc.assess.dal.dao.SurveyAssetTemplateDao;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.entity.SurveyAssetInventory;
import com.copower.pmcc.assess.dal.entity.SurveyAssetTemplate;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetInventoryDto;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetOtherTemplateDto;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetCommonDataDto;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetTemplateDto;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;


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
    @Autowired
    private SurveyAssetTemplateDao surveyAssetTemplateDao;
    @Autowired
    private SurveyAssetOtherTemplateService surveyAssetOtherTemplateService;

    public boolean save(ProjectPlanDetails projectPlanDetails,String processInsId,SurveyAssetCommonDataDto surveyAssetCommonDataDto) throws BusinessException {
        Integer projectId = projectPlanDetails.getProjectId();
        Integer planId = projectPlanDetails.getPlanId();

        if(surveyAssetCommonDataDto != null){
            SurveyAssetInventoryDto surveyAssetInventoryDto = surveyAssetCommonDataDto.getSurveyAssetInventoryDto();
            surveyAssetInventoryDto.setProjectId(projectId);
            surveyAssetInventoryDto.setPlanDetailId(planId);
            surveyAssetInventoryDto.setProcessInsId(processInsId);
            surveyAssetInventoryDto.setCreator(serviceComponent.getThisUser());
            int pid = surveyAssetInventoryDao.save(surveyAssetInventoryDto);

            SurveyAssetOtherTemplateDto surveyAssetOtherTemplateDto = surveyAssetCommonDataDto.getSurveyAssetOtherTemplateDto();
            surveyAssetOtherTemplateDto.setProjectId(projectId);
            surveyAssetOtherTemplateDto.setPlanDetailId(planId);
            surveyAssetOtherTemplateDto.setCreator(serviceComponent.getThisUser());
            surveyAssetOtherTemplateDto.setPid(pid);
            surveyAssetOtherTemplateDao.save(surveyAssetOtherTemplateDto);

            List<SurveyAssetTemplate> surveyAssetTemplates = surveyAssetTemplateDao.getSurveyAssetTemplate(serviceComponent.getThisUser(), 0);
            for(SurveyAssetTemplate surveyAssetTemplate :surveyAssetTemplates){
                surveyAssetTemplate.setProjectId(projectId);
                surveyAssetTemplate.setPlanDetailId(planId);
                surveyAssetTemplate.setPid(pid);
                surveyAssetTemplateDao.save(surveyAssetTemplate);
            }
            return true;

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

    public ModelAndView getSurveyAssetInventoryByProcessInsId(ModelAndView modelAndView,String processInsId){
        SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryDao.getSurveyAssetInventoryByProcessInsId(processInsId);
        if(surveyAssetInventory != null){
            modelAndView.addObject("surveyAssetInventory",surveyAssetInventory);
            Integer pid = surveyAssetInventory.getId();
            surveyAssetOtherTemplateService.getSurveyAssetOtherTemplateByPid(modelAndView,pid);
            return modelAndView;
        }
        return modelAndView;
    }

}
