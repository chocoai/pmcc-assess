package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dto.input.basic.BasicFormClassifyParamDto;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class BasicEstateLandService extends BasicEntityAbstract {
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BasicEstateLandStateService basicEstateLandStateService;
    @Override
    public Integer saveAndUpdate(Object o, Boolean updateNull) {
        return basicEstateService.saveAndUpdate(o,updateNull);
    }

    @Override
    public Integer saveAndUpdateByFormData(String formData, Integer planDetailsId) throws Exception {
        return basicEstateService.saveAndUpdateByFormData(formData,planDetailsId);
    }

    @Override
    public Object getBasicEntityById(Integer id) {
        return basicEstateService.getBasicEntityById(id);
    }

    @Override
    public void clearInvalidData(Integer id) throws Exception {
        basicEstateService.clearInvalidData(id);
    }

    @Override
    public void clearInvalidChildData(Integer id) throws Exception {
        basicEstateService.clearInvalidChildData(id);
    }

    @Override
    public Object copyBasicEntity(Integer sourceId, Integer targetId, Boolean containChild) throws Exception {
        return basicEstateService.copyBasicEntity(sourceId,targetId,containChild);
    }

    @Override
    public Object copyBasicEntityIgnore(Integer sourceId, Integer targetId, Boolean containChild, List<String> ignoreList) throws Exception {
        return basicEstateService.copyBasicEntityIgnore(sourceId,targetId,containChild,ignoreList);
    }

    @Override
    public List<BasicFormClassifyEnum> getLowerFormClassifyList() {
        return null;
    }

    @Override
    public ModelAndView getEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/land/estate");
        modelAndView.addObject("basicEstate", basicEstateService.getBasicEstateById(basicFormClassifyParamDto.getTbId()));
        modelAndView.addObject("basicEstateLandState", basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateService.getLandStateByEstateId(basicFormClassifyParamDto.getTbId())));
        List<CrmBaseDataDicDto> unitPropertiesList = projectInfoService.getUnitPropertiesList();
        modelAndView.addObject("unitPropertiesList", unitPropertiesList);
        return modelAndView;
    }

    @Override
    public ModelAndView getDetailModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/land/detail/estate");
        modelAndView.addObject("basicEstate", basicEstateService.getBasicEstateVo(basicEstateService.getBasicEstateById(basicFormClassifyParamDto.getTbId())));
        modelAndView.addObject("basicEstateLandState", basicEstateLandStateService.getBasicEstateLandStateVo(basicEstateLandStateService.getLandStateByEstateId(basicFormClassifyParamDto.getTbId())));
        return modelAndView;
    }

    @Override
    public ModelAndView getPhoneEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        return null;
    }
}
