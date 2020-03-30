package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dto.input.basic.BasicFormClassifyParamDto;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class BasicBuildingMonolayerService extends BasicEntityAbstract {
    @Autowired
    private BasicBuildingService basicBuildingService;

    @Override
    public Integer saveAndUpdate(Object o, Boolean updateNull) {
        return null;
    }

    @Override
    public Integer saveAndUpdateByFormData(String formData, Integer planDetailsId) throws Exception {
        return null;
    }

    @Override
    public Object getBasicEntityById(Integer id) {
        return null;
    }

    @Override
    public void clearInvalidData(Integer id) throws Exception {

    }

    @Override
    public void clearInvalidChildData(Integer id) throws Exception {

    }

    @Override
    public Object copyBasicEntity(Integer sourceId, Integer targetId, Boolean containChild) throws Exception {
        return null;
    }

    @Override
    public Object copyBasicEntityIgnore(Integer sourceId, Integer targetId, Boolean containChild, List<String> ignoreList) throws Exception {
        return null;
    }

    @Override
    public List<BasicFormClassifyEnum> getLowerFormClassifyList() {
        return null;
    }

    @Override
    public ModelAndView getEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        return null;
    }

    @Override
    public ModelAndView getDetailModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        return null;
    }
}
