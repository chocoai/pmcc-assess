package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyFormNameEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.input.basic.BasicFormClassifyParamDto;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitVo;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicUnitResidenceService extends BasicEntityAbstract {
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
        return basicUnitService.getLowerFormClassifyList();
    }

    @Override
    public ModelAndView getEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView editModelAndView = basicUnitService.getEditModelAndView(basicFormClassifyParamDto);
        editModelAndView.setViewName("/project/stageSurvey/realEstate/unitResidence");
        return editModelAndView;
    }

    @Override
    public ModelAndView getDetailModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView detailModelAndView = basicUnitService.getDetailModelAndView(basicFormClassifyParamDto);
        detailModelAndView.setViewName("/project/stageSurvey/realEstate/detail/unitResidence");
        return detailModelAndView;
    }

    @Override
    public ModelAndView getPhoneEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView editModelAndView = basicUnitService.getEditModelAndView(basicFormClassifyParamDto);
        editModelAndView.setViewName("/project/stageSurvey/realEstate/photo/unitResidence");
        return editModelAndView;
    }
}
