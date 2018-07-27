package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetOtherTemplateDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetTemplateDao;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyAssetCommonDataDto;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyAssetInventoryDto;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyAssetTemplateDto;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetTemplateVo;
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
    private SurveyAssetTemplateService surveyAssetTemplateService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    /**
     * 保存资产清查数据
     * @param projectPlanDetails
     * @param processInsId
     * @param surveyAssetCommonDataDto
     * @throws BusinessException
     */
    public void save(ProjectPlanDetails projectPlanDetails, String processInsId, SurveyAssetCommonDataDto surveyAssetCommonDataDto) throws BusinessException {
        Integer projectId = projectPlanDetails.getProjectId();
        Integer planDetailsId = projectPlanDetails.getId();
        if (surveyAssetCommonDataDto != null) {
            SurveyAssetInventoryDto surveyAssetInventoryDto = surveyAssetCommonDataDto.getSurveyAssetInventoryDto();
            List<SurveyAssetTemplateDto> surveyAssetTemplateDtos = surveyAssetCommonDataDto.getSurveyAssetTemplateDtos();
            SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryDao.getDataByProcessInsId(processInsId);
            if (surveyAssetInventory != null) {
                surveyAssetInventoryDao.update(surveyAssetInventoryDto);

                for(SurveyAssetTemplate surveyAssetTemplate : surveyAssetTemplateDtos){
                    surveyAssetTemplateDao.update(surveyAssetTemplate);
                }
            } else {
                surveyAssetInventoryDto.setProjectId(projectId);
                surveyAssetInventoryDto.setPlanDetailId(planDetailsId);
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

    public SurveyAssetInventory getDataByProcessInsId(String processInsId){
        return surveyAssetInventoryDao.getDataByProcessInsId(processInsId);
    }

    public SurveyAssetInventory getDataByPlanDetailsId(Integer planDetailsId){
        return surveyAssetInventoryDao.getDataByPlanDetailsId(planDetailsId);
    }

    public ModelAndView getSurveyAssetInventoryByProcessInsId(ModelAndView modelAndView, String processInsId) {
        SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryDao.getDataByProcessInsId(processInsId);
        if (surveyAssetInventory != null) {
            List<BaseDataDic> otherRightTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.OTHER_RIGHT_TYPE);
            List<SurveyAssetTemplate> surveyAssetTemplates = surveyAssetTemplateDao.getSurveyAssetTemplate(surveyAssetInventory.getId());
            List<SurveyAssetTemplateVo> surveyAssetTemplateVos = surveyAssetTemplateService.getVoList(surveyAssetTemplates);

            modelAndView.addObject("surveyAssetInventory", surveyAssetInventory);
            modelAndView.addObject("surveyAssetTemplateVos",surveyAssetTemplateVos);
            modelAndView.addObject("otherRightTypeList",otherRightTypeList);
            return modelAndView;
        }
        return modelAndView;
    }

}
