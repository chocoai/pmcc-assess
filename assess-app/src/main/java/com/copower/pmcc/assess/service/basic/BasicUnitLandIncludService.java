package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dto.input.basic.BasicFormClassifyParamDto;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicUnitLandIncludService extends BasicEntityAbstract {
    @Autowired
    private BasicUnitService basicUnitService;

    @Override
    public Integer saveAndUpdate(Object o, Boolean updateNull) {
        return basicUnitService.saveAndUpdate(o,updateNull);
    }

    @Override
    public Integer saveAndUpdateByFormData(String formData, Integer planDetailsId) throws Exception {
        return basicUnitService.saveAndUpdateByFormData(formData,planDetailsId);
    }

    @Override
    public Object getBasicEntityById(Integer id) {
        return basicUnitService.getBasicEntityById(id);
    }

    @Override
    public void clearInvalidData(Integer id) throws Exception {
        basicUnitService.clearInvalidData(id);
    }

    @Override
    public void clearInvalidChildData(Integer id) throws Exception {
        basicUnitService.clearInvalidChildData(id);
    }

    @Override
    public Object copyBasicEntity(Integer sourceId, Integer targetId, Boolean containChild) throws Exception {
        return basicUnitService.copyBasicEntity(sourceId,targetId,containChild);
    }

    @Override
    public Object copyBasicEntityIgnore(Integer sourceId, Integer targetId, Boolean containChild, List<String> ignoreList) throws Exception {
        return basicUnitService.copyBasicEntityIgnore(sourceId,targetId,containChild,ignoreList);
    }

    @Override
    public List<BasicFormClassifyEnum> getLowerFormClassifyList() {
        List<BasicFormClassifyEnum> list = Lists.newArrayList();
        list.add(BasicFormClassifyEnum.HOUSE_LAND_INCLUD);
        return list;
    }

    @Override
    public ModelAndView getEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView editModelAndView = basicUnitService.getEditModelAndView(basicFormClassifyParamDto);
        editModelAndView.setViewName("/project/stageSurvey/landIncludRealEstate/unit");
        return editModelAndView;
    }

    @Override
    public ModelAndView getDetailModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView detailModelAndView = basicUnitService.getDetailModelAndView(basicFormClassifyParamDto);
        detailModelAndView.setViewName("/project/stageSurvey/landIncludRealEstate/detail/unit");
        return detailModelAndView;
    }

    @Override
    public ModelAndView getPhoneEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView editModelAndView = basicUnitService.getEditModelAndView(basicFormClassifyParamDto);
        editModelAndView.setViewName("/project/stageSurvey/landIncludRealEstate/photo/unit");
        return editModelAndView;
    }
}
