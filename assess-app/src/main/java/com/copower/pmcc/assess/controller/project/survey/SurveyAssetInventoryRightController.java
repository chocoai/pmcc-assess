package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRight;
import com.copower.pmcc.assess.service.project.survey.SurveyAssetInventoryRightService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 * Created by zly on 2018/6/11.
 */
@Controller
@RequestMapping(value = "/surveyAssetInventoryRight")
public class SurveyAssetInventoryRightController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyAssetInventoryRightService surveyAssetInventoryRightService;

    @ResponseBody
    @RequestMapping(value = "/getListByPlanDetailsId", name = "取得他项权利列表", method = RequestMethod.GET)
    public BootstrapTableVo list(SurveyAssetInventoryRight select) {
        BootstrapTableVo vo = surveyAssetInventoryRightService.getBootstrapTableVo(select);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/getListByProjectId", name = "取得他项权利列表", method = RequestMethod.GET)
    public BootstrapTableVo get(SurveyAssetInventoryRight select) {
        BootstrapTableVo vo = surveyAssetInventoryRightService.getBootstrapTableVo(select);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/get", name = "获取他项权利", method = RequestMethod.GET)
    public HttpResult get(Integer id) {
        try {
            return HttpResult.newCorrectResult(surveyAssetInventoryRightService.getSurveyAssetInventoryRightById(id));
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/save", name = "新增和修改他项权利", method = RequestMethod.POST)
    public HttpResult save(String formData) {
        try {
            SurveyAssetInventoryRight surveyAssetInventoryRight = JSON.parseObject(formData, SurveyAssetInventoryRight.class);
            surveyAssetInventoryRightService.save(surveyAssetInventoryRight);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除他项权利", method = RequestMethod.POST)
    public HttpResult delete(String id) {
        try {
            String[] ids = id.split(",");
            for (String s : ids) {
                surveyAssetInventoryRightService.delete(Integer.parseInt(s));
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/importData", name = "导入他项权利", method = RequestMethod.POST)
    public HttpResult importData(SurveyAssetInventoryRight right, HttpServletRequest request) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty())
                return HttpResult.newErrorResult("上传的文件不能为空");
            String s = surveyAssetInventoryRightService.importData(right, multipartFile);
            return HttpResult.newCorrectResult(s);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        } catch (Exception e) {
            logger.error(String.format("导入他项权利-%s", e.getMessage()), e);
            return HttpResult.newErrorResult("导入他项权利异常");
        }

    }

}
