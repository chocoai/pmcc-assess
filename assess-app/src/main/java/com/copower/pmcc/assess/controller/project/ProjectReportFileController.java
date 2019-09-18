package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileItem;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeReportFileDto;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.SchemeReportFileService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
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
 * Created by kings on 2019-3-7.
 */
@Controller
@RequestMapping("/projectReportFile")
public class ProjectReportFileController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SchemeReportFileService schemeReportFileService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private DeclareRecordService declareRecordService;


    @RequestMapping(value = "/index", name = "委托书及证明文件")
    public ModelAndView index(Integer projectId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/project/projectReportFileIndex");
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId)));
        modelAndView.addObject("ownershipCertFileList", schemeReportFileService.getOwnershipCertFileList(projectId));
        modelAndView.addObject("inventoryAddressFileList", schemeReportFileService.getInventoryAddressFileList(projectId));
        modelAndView.addObject("reimbursementFileList", schemeReportFileService.getReimbursementFileList(projectId));
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordByProjectId(projectId);
        modelAndView.addObject("declareRecordList", declareRecordList);
        modelAndView.addObject("projectId", projectId);
        //生成位置图
        schemeReportFileService.makeJudgeObjectPosition(declareRecordList);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/saveReprotFile", name = "保存委托书及证明文件", method = RequestMethod.POST)
    public HttpResult saveReprotFile(String formData) {
        try {
            SchemeReportFileDto customDto = JSON.parseObject(formData, SchemeReportFileDto.class);
            List<SchemeReportFileItem> reportFileItemList = customDto.getReportFileItemList();
            if (CollectionUtils.isNotEmpty(reportFileItemList)) {
                for (SchemeReportFileItem custom : reportFileItemList) {
                    schemeReportFileService.updateReportFileItem(custom);
                }
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return HttpResult.newErrorResult("取得计划编制信息异常");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getOwnershipCertFileAll", name = "获取权属证明复印件图片", method = RequestMethod.POST)
    public HttpResult getOwnershipCertFileAll(Integer declareRecordId) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.getOwnershipCertFileAll(declareRecordId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/removeOwnershipCertFile", name = "移除权属证明复印件图片 ", method = RequestMethod.POST)
    public HttpResult removeOwnershipCertFile(Integer id) {
        try {
            schemeReportFileService.removeOwnershipCertFile(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
