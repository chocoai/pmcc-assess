package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.ad.api.dto.AdPersonalQualificationDto;
import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.dal.basis.entity.DataQualification;
import com.copower.pmcc.assess.dto.output.data.DataQualificationVo;
import com.copower.pmcc.assess.service.AdRpcQualificationsAppService;
import com.copower.pmcc.assess.service.data.DataQualificationService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/dataQualification")
public class DataQualificationController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataQualificationService dataQualificationService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private AdRpcQualificationsAppService adRpcQualificationsAppService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataQualificationManage";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        Map<String, String> qualification = new HashMap<>();
        qualification.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCTDGJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCTDGJS.getName());
        qualification.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCZCGJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCZCGJS.getName());
        qualification.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_SFJDR.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_SFJDR.getName());
        qualification.put(AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getValue(), AdPersonalEnum.PERSONAL_QUALIFICATION_ASSESS_ZCFDCGJS.getName());
        modelAndView.addObject("qualificationTypes", qualification);
        return modelAndView;
    }

    @RequestMapping(value = "/getDataQualificationById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getById(Integer id) {
        DataQualificationVo dataQualificationVo = null;
        try {
            if (id != null) {
                dataQualificationVo = dataQualificationService.getByDataQualificationId(id);
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult(dataQualificationVo);
    }

    @RequestMapping(value = "/getDataQualificationList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getExamineEstateNetworkList(String type) {
        BootstrapTableVo vo = null;
        try {
            vo = dataQualificationService.getListVos(type);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return null;
        }
        return vo;
    }

    @RequestMapping(value = "/findDataQualificationList", method = {RequestMethod.GET}, name = "获取列表")
    public HttpResult findDataQualificationList(String type) {
        List<DataQualification> dataQualificationList = null;
        try {
            dataQualificationList = dataQualificationService.getDataQualificationList(type);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        List<DataQualificationVo> dataQualificationVoList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(dataQualificationList)){
            dataQualificationList.stream().forEach(dataQualification -> dataQualificationVoList.add(dataQualificationService.getDataQualificationVo(dataQualification)));
        }
        return HttpResult.newCorrectResult(dataQualificationVoList);
    }

    @RequestMapping(value = "/deleteDataQualificationById", method = {RequestMethod.POST}, name = "删除")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                return HttpResult.newCorrectResult(dataQualificationService.deleteDataQualification(id));
            }
        } catch (Exception e) {
            logger.error(String.format("exception: %s" + e.getMessage()), e);
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return null;
    }

    @RequestMapping(value = "/saveAndUpdateDataQualification", method = {RequestMethod.POST}, name = "保存")
    public HttpResult saveAndUpdate(DataQualification dataQualification) {
        try {
            if (dataQualification.getId() == null || dataQualification.getId().equals(0)) {
                dataQualificationService.addDataQualificationReturnId(dataQualification);
            } else {
                dataQualificationService.updateDataQualification(dataQualification);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @GetMapping(value = "/getAdPersonalIdentityDto",name = "获取资质")
    public HttpResult getAdPersonalIdentityDto(String userAccount, String qualificationType){
        List<AdPersonalQualificationDto> adPersonalQualificationDtoList = null;
        if (StringUtils.isNotBlank(userAccount)){
            try {
                if (StringUtils.isEmpty(qualificationType)){
                    adPersonalQualificationDtoList = adRpcQualificationsAppService.getAdPersonalIdentityDto(userAccount);
                }
                if (StringUtils.isNotBlank(qualificationType)){
                    adPersonalQualificationDtoList = adRpcQualificationsAppService.getAdPersonalQualificationDto(userAccount,qualificationType);
                }
            } catch (Exception e1) {
                logger.error("获取资质异常!",e1);
            }
        }
        return HttpResult.newCorrectResult(CollectionUtils.isNotEmpty(adPersonalQualificationDtoList)?adPersonalQualificationDtoList:new ArrayList<AdPersonalQualificationDto>());
    }

}
