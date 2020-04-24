package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyFormNameEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.input.basic.BasicFormClassifyParamDto;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseDamagedDegreeVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseTradingVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseVo;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitHuxingVo;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.assist.ResidueRatioService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataDamagedDegreeService;
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
import java.util.Map;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicHouseLandIncludService extends BasicEntityAbstract {
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicHouseTradingService basicHouseTradingService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private PublicBasicService publicBasicService;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public Integer saveAndUpdate(Object o, Boolean updateNull) {
        return basicHouseService.saveAndUpdate(o,updateNull);
    }

    @Override
    public Integer saveAndUpdateByFormData(String formData, Integer planDetailsId) throws Exception {
        return basicHouseService.saveAndUpdateByFormData(formData,planDetailsId);
    }

    @Override
    public Object getBasicEntityById(Integer id) {
        return basicHouseService.getBasicEntityById(id);
    }

    @Override
    public void clearInvalidData(Integer id) throws Exception {
        basicHouseService.clearInvalidData(id);
    }

    @Override
    public void clearInvalidChildData(Integer id) throws Exception {
        basicHouseService.clearInvalidChildData(id);
    }

    @Override
    public Object copyBasicEntity(Integer sourceId, Integer targetId, Boolean containChild) throws Exception {
        return  basicHouseService.copyBasicEntity(sourceId,targetId,containChild);
    }

    @Override
    public Object copyBasicEntityIgnore(Integer sourceId, Integer targetId, Boolean containChild, List<String> ignoreList) throws Exception {
        return  basicHouseService.copyBasicEntityIgnore(sourceId,targetId,containChild,ignoreList);
    }

    @Override
    public List<BasicFormClassifyEnum> getLowerFormClassifyList() {
        return null;
    }

    @Override
    public ModelAndView getEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/landIncludRealEstate/house");
        modelAndView.addObject("basicHouse", basicHouseService.getBasicHouseById(basicFormClassifyParamDto.getTbId()));
        modelAndView.addObject("basicHouseTrading", basicHouseTradingService.getTradingByHouseId(basicFormClassifyParamDto.getTbId()));
        modelAndView.addObject("basicHouseHuxing", basicUnitHuxingService.getHuxingByHouseId(basicFormClassifyParamDto.getTbId()));
        Integer applyBatchId = basicFormClassifyParamDto.getApplyBatchId();
        Integer tbId = basicFormClassifyParamDto.getTbId();
        BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(applyBatchId, FormatUtils.entityNameConvertToTableName(BasicHouse.class), tbId);
        if (basicApplyBatchDetail != null) {//获取引用id
            basicApplyBatchDetail = basicApplyBatchDetailService.getDataById(basicApplyBatchDetail.getPid());
            if (basicApplyBatchDetail != null) {
                BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByTableName(basicApplyBatchDetail.getTableName());
                Object entity = entityAbstract.getBasicEntityById(basicApplyBatchDetail.getTableId());
                if (entity != null) {
                    modelAndView.addObject("quoteId", entityAbstract.getProperty(entity, "quoteId"));
                }
            }
        }
        return modelAndView;
    }

    @Override
    public ModelAndView getDetailModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/landIncludRealEstate/detail/house");
        modelAndView.addObject("basicHouse",basicHouseService.getBasicHouseVo(basicHouseService.getBasicHouseById(basicFormClassifyParamDto.getTbId())));
        modelAndView.addObject("basicHouseTrading", basicHouseTradingService.getBasicHouseTradingVo(basicHouseTradingService.getTradingByHouseId(basicFormClassifyParamDto.getTbId())));
        modelAndView.addObject("basicHouseHuxing", basicUnitHuxingService.getBasicUnitHuxingVo(basicUnitHuxingService.getHuxingByHouseId(basicFormClassifyParamDto.getTbId())));
        return modelAndView;
    }

    @Override
    public ModelAndView getPhoneEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/landIncludRealEstate/photo/house");
        modelAndView.addObject("basicHouse", basicHouseService.getBasicHouseById(basicFormClassifyParamDto.getTbId()));
        modelAndView.addObject("basicHouseTrading", basicHouseTradingService.getTradingByHouseId(basicFormClassifyParamDto.getTbId()));
        modelAndView.addObject("basicHouseHuxing", basicUnitHuxingService.getHuxingByHouseId(basicFormClassifyParamDto.getTbId()));
        return modelAndView;
    }
}
