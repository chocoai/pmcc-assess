package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataLocaleSurveyPicture;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataLocaleSurveyPictureService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 10:00
 * @Description:现场查勘图片配置
 */
@RequestMapping(value = "/dataLocaleSurveyPicture")
@Controller
public class DataLocaleSurveyPictureController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataLocaleSurveyPictureService dataLocaleSurveyPictureService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataLocaleSurveyPictureView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<BaseDataDic> pictureTemplates = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_LOCALE_SURVEY_PICTURE_TEMPLATE);
        modelAndView.addObject("pictureTemplates", pictureTemplates);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataLocaleSurveyPictureById", method = {RequestMethod.GET}, name = "获取现场查勘图片配置")
    public HttpResult getById(Integer id) {
        DataLocaleSurveyPicture dataLocaleSurveyPicture = null;
        try {
            if (id != null) {
                dataLocaleSurveyPicture = dataLocaleSurveyPictureService.getDataLocaleSurveyPictureById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataLocaleSurveyPicture);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataLocaleSurveyPictureList", method = {RequestMethod.GET}, name = "获取现场查勘图片配置列表")
    public BootstrapTableVo getExamineEstateNetworkList(Integer type) {
        BootstrapTableVo vo = null;
        DataLocaleSurveyPicture dataLocaleSurveyPicture = new DataLocaleSurveyPicture();
        if (type != null) {
            dataLocaleSurveyPicture.setType(type);
        }
        try {
            vo = dataLocaleSurveyPictureService.getDataLocaleSurveyPictureListVos(dataLocaleSurveyPicture);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataLocaleSurveyPictureById", method = {RequestMethod.POST}, name = "删除现场查勘图片配置")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                DataLocaleSurveyPicture dataLocaleSurveyPicture = new DataLocaleSurveyPicture();
                dataLocaleSurveyPicture.setId(id);
                dataLocaleSurveyPictureService.removeDataLocaleSurveyPicture(dataLocaleSurveyPicture);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataLocaleSurveyPicture", method = {RequestMethod.POST}, name = "更新现场查勘图片配置")
    public HttpResult saveAndUpdate(DataLocaleSurveyPicture dataLocaleSurveyPicture) {
        try {
            dataLocaleSurveyPictureService.saveAndUpdateDataLocaleSurveyPicture(dataLocaleSurveyPicture);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

}
