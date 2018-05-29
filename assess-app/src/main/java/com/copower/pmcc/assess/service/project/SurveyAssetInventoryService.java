package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.dao.SurveyAssetOtherTemplateDao;
import com.copower.pmcc.assess.dal.dao.SurveyAssetTemplateDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetCommonDataDto;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetInventoryDto;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetOtherTemplateDto;
import com.copower.pmcc.assess.dto.input.project.SurveyAssetTemplateDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * Created by zly on 2018/5/9.
 */

@Service
public class SurveyAssetInventoryService {

    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SurveyAssetInventoryDao surveyAssetInventoryDao;
    @Autowired
    private SurveyAssetOtherTemplateDao surveyAssetOtherTemplateDao;
    @Autowired
    private SurveyAssetTemplateDao surveyAssetTemplateDao;
    @Autowired
    private SurveyAssetOtherTemplateService surveyAssetOtherTemplateService;
    @Autowired
    private SurveyAssetTemplateService surveyAssetTemplateService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public void save(ProjectPlanDetails projectPlanDetails, String processInsId, SurveyAssetCommonDataDto surveyAssetCommonDataDto) throws BusinessException {
        Integer projectId = projectPlanDetails.getProjectId();
        Integer planId = projectPlanDetails.getPlanId();
        List<BaseDataDic> baseDataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.CHECK_CONTENT);
        if (surveyAssetCommonDataDto != null) {
            SurveyAssetInventoryDto surveyAssetInventoryDto = surveyAssetCommonDataDto.getSurveyAssetInventoryDto();
            SurveyAssetOtherTemplateDto surveyAssetOtherTemplateDto = surveyAssetCommonDataDto.getSurveyAssetOtherTemplateDto();
            List<SurveyAssetTemplateDto> surveyAssetTemplateDtos = surveyAssetCommonDataDto.getSurveyAssetTemplateDtos();
            SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryDao.getSurveyAssetInventoryByProcessInsId(processInsId);
            if (surveyAssetInventory != null) {
                surveyAssetInventoryDto.setId(surveyAssetInventory.getId());
                surveyAssetInventoryDao.update(surveyAssetInventoryDto);
                Integer pid = surveyAssetInventory.getId();
                SurveyAssetOtherTemplate surveyAssetOtherTemplate = surveyAssetOtherTemplateDao.getSurveyAssetOtherTemplateByPid(pid);
                if (surveyAssetOtherTemplate != null) {
                    surveyAssetOtherTemplateDto.setId(surveyAssetOtherTemplate.getId());
                    surveyAssetOtherTemplateDao.update(surveyAssetOtherTemplateDto);
                }
                if (surveyAssetTemplateDtos.size() > 0) {
                    int j = surveyAssetTemplateDao.deleteByPid(pid);
                    int i=0;
                    if(j == 1){
                        for(SurveyAssetTemplate surveyAssetTemplate : surveyAssetTemplateDtos){
                            surveyAssetTemplate.setProjectId(projectId);
                            surveyAssetTemplate.setPlanDetailId(planId);
                            surveyAssetTemplate.setPid(pid);
                            surveyAssetTemplate.setInventoryContent(baseDataDicList.get(i).getName());
                            i++;
                            surveyAssetTemplateDao.save(surveyAssetTemplate);
                        }
                    }
                }
            } else {
                surveyAssetInventoryDto.setProjectId(projectId);
                surveyAssetInventoryDto.setPlanDetailId(planId);
                surveyAssetInventoryDto.setProcessInsId(processInsId);
                surveyAssetInventoryDto.setCreator(processControllerComponent.getThisUser());
                int pid = surveyAssetInventoryDao.save(surveyAssetInventoryDto);

                surveyAssetOtherTemplateDto.setProjectId(projectId);
                surveyAssetOtherTemplateDto.setPlanDetailId(planId);
                surveyAssetOtherTemplateDto.setCreator(processControllerComponent.getThisUser());
                surveyAssetOtherTemplateDto.setPid(pid);
                surveyAssetOtherTemplateDao.save(surveyAssetOtherTemplateDto);

//                List<SurveyAssetTemplate> surveyAssetTemplates = surveyAssetTemplateDao.getSurveyAssetTemplate(0);
                int i = 0;
                for (SurveyAssetTemplate surveyAssetTemplate : surveyAssetTemplateDtos) {
                    surveyAssetTemplate.setProjectId(projectId);
                    surveyAssetTemplate.setPlanDetailId(planId);
                    surveyAssetTemplate.setPid(pid);
                    surveyAssetTemplate.setInventoryContent(baseDataDicList.get(i).getName());
                    i++;
                    surveyAssetTemplateDao.save(surveyAssetTemplate);
                }
            }
        }
    }

    public SurveyAssetCommonDataDto format(String val) {
        SurveyAssetCommonDataDto dto = null;
        if (StringUtils.isNotBlank(val)) {
            dto = JSON.parseObject(val, SurveyAssetCommonDataDto.class);
        }
        return dto;
    }

    public ModelAndView getSurveyAssetInventoryByProcessInsId(ModelAndView modelAndView, String processInsId) {
        SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryDao.getSurveyAssetInventoryByProcessInsId(processInsId);
        if (surveyAssetInventory != null) {
            modelAndView.addObject("surveyAssetInventory", surveyAssetInventory);
            Integer pid = surveyAssetInventory.getId();
            surveyAssetOtherTemplateService.getSurveyAssetOtherTemplateByPid(modelAndView, pid);
            surveyAssetTemplateService.getSurveyAssetTemplateByPid(modelAndView, pid);
            return modelAndView;
        }
        return modelAndView;
    }

}
