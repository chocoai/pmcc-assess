package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouse;
import com.copower.pmcc.assess.dto.input.basic.BasicFormClassifyParamDto;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BasicHouseLandService extends BasicEntityAbstract {
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
    @Autowired
    private BasicEstateLandCategoryInfoService basicEstateLandCategoryInfoService;

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
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/land/house");
        modelAndView.addObject("basicHouse", basicHouseService.getBasicHouseById(basicFormClassifyParamDto.getTbId()));
        modelAndView.addObject("basicHouseTrading", basicHouseTradingService.getTradingByHouseId(basicFormClassifyParamDto.getTbId()));
        modelAndView.addObject("basicHouseHuxing", basicUnitHuxingService.getHuxingByHouseId(basicFormClassifyParamDto.getTbId()));
        modelAndView.addObject("landCategoryInfo", basicEstateLandCategoryInfoService.getBasicEstateLandCategoryInfoByHouseId(basicFormClassifyParamDto.getTbId()));
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
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/land/detail/house");
        modelAndView.addObject("basicHouse",basicHouseService.getBasicHouseVo(basicHouseService.getBasicHouseById(basicFormClassifyParamDto.getTbId())));
        modelAndView.addObject("basicHouseTrading", basicHouseTradingService.getBasicHouseTradingVo(basicHouseTradingService.getTradingByHouseId(basicFormClassifyParamDto.getTbId())));
        modelAndView.addObject("basicHouseHuxing", basicUnitHuxingService.getBasicUnitHuxingVo(basicUnitHuxingService.getHuxingByHouseId(basicFormClassifyParamDto.getTbId())));
        modelAndView.addObject("landCategoryInfo", basicEstateLandCategoryInfoService.getBasicEstateLandCategoryInfoByHouseId(basicFormClassifyParamDto.getTbId()));
        return modelAndView;
    }

    @Override
    public List<Object> getBasicEntityListByBatchDetailId(Integer applyBatchDetailId) throws Exception {
        return basicHouseService.getBasicEntityListByBatchDetailId(applyBatchDetailId);
    }

    @Override
    public ModelAndView getPhoneEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/project/stageSurvey/land/photo/house");
        modelAndView.addObject("basicHouse", basicHouseService.getBasicHouseById(basicFormClassifyParamDto.getTbId()));
        modelAndView.addObject("basicHouseTrading", basicHouseTradingService.getTradingByHouseId(basicFormClassifyParamDto.getTbId()));
        modelAndView.addObject("basicHouseHuxing", basicUnitHuxingService.getHuxingByHouseId(basicFormClassifyParamDto.getTbId()));
        modelAndView.addObject("landCategoryInfo", basicEstateLandCategoryInfoService.getBasicEstateLandCategoryInfoByHouseId(basicFormClassifyParamDto.getTbId()));
        return modelAndView;
    }

}
