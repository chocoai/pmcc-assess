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
import com.copower.pmcc.assess.dto.output.data.DataNumberRuleVo;
import com.copower.pmcc.assess.dto.output.project.SurveyAssetTemplateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
//            SurveyAssetOtherTemplateDto surveyAssetOtherTemplateDto = surveyAssetCommonDataDto.getSurveyAssetOtherTemplateDto();
            List<SurveyAssetTemplateDto> surveyAssetTemplateDtos = surveyAssetCommonDataDto.getSurveyAssetTemplateDtos();
            SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryDao.getSurveyAssetInventoryByProcessInsId(processInsId);
            if (surveyAssetInventory != null) {
//                surveyAssetInventoryDto.setId(surveyAssetInventory.getId());
                surveyAssetInventoryDao.update(surveyAssetInventoryDto);

                for(SurveyAssetTemplate surveyAssetTemplate : surveyAssetTemplateDtos){
                    surveyAssetTemplateDao.update(surveyAssetTemplate);
                }
            } else {
                surveyAssetInventoryDto.setProjectId(projectId);
                surveyAssetInventoryDto.setPlanDetailId(planId);
                surveyAssetInventoryDto.setProcessInsId(processInsId);
                surveyAssetInventoryDto.setCreator(processControllerComponent.getThisUser());
                int pid = surveyAssetInventoryDao.save(surveyAssetInventoryDto);

                List<SurveyAssetOtherTemplate> surveyAssetOtherTemplates = surveyAssetOtherTemplateDao.getSurveyAssetOtherTemplateByPid(0);
                for(SurveyAssetOtherTemplate surveyAssetOtherTemplate:surveyAssetOtherTemplates){
                    surveyAssetOtherTemplate.setPid(pid);
                    surveyAssetOtherTemplate.setCreator(processControllerComponent.getThisUser());
                    surveyAssetOtherTemplateDao.update(surveyAssetOtherTemplate);
                }

                for(SurveyAssetTemplate surveyAssetTemplate : surveyAssetTemplateDtos){
                    surveyAssetTemplate.setPid(pid);
                    surveyAssetTemplate.setCreator(processControllerComponent.getThisUser());
                    surveyAssetTemplateDao.update(surveyAssetTemplate);
                }
//                List<SurveyAssetTemplate> surveyAssetTemplates = surveyAssetTemplateDao.getSurveyAssetTemplate(0);
//                for (SurveyAssetTemplate surveyAssetTemplate : surveyAssetTemplates) {
//                    surveyAssetTemplate.setPid(pid);
//                    surveyAssetTemplate.setCreator(processControllerComponent.getThisUser());
//                    surveyAssetTemplateDao.update(surveyAssetTemplate);
//                }
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
            List<SurveyAssetTemplate> surveyAssetTemplates = surveyAssetTemplateDao.getSurveyAssetTemplate(surveyAssetInventory.getId());
            List<SurveyAssetTemplateVo> surveyAssetTemplateVos = surveyAssetTemplateService.getVoList(surveyAssetTemplates);

            modelAndView.addObject("surveyAssetInventory", surveyAssetInventory);
            modelAndView.addObject("surveyAssetTemplateVos",surveyAssetTemplateVos);
//            Integer pid = surveyAssetInventory.getId();
//            surveyAssetOtherTemplateService.getSurveyAssetOtherTemplateByPid(modelAndView, pid);
//            surveyAssetTemplateService.getSurveyAssetTemplateByPid(modelAndView, pid);
            return modelAndView;
        }
        return modelAndView;
    }

}
