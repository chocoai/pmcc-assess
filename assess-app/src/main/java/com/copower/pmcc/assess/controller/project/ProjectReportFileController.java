package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileItem;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeReportFileDto;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.SchemeReportFileService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.collections.CollectionUtils;
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
    @Autowired
    private SchemeReportFileService schemeReportFileService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseService baseService;


    @RequestMapping(value = "/index", name = "委托书及证明文件")
    public ModelAndView index(Integer projectId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("/project/projectReportFileIndex");
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId)));
        modelAndView.addObject("projectId", projectId);
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
            baseService.writeExceptionInfo(e,"取得计划编制信息异常");
            return HttpResult.newErrorResult("取得计划编制信息异常");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getOwnershipCertFileAll", name = "获取权属证明复印件图片", method = RequestMethod.POST)
    public HttpResult getOwnershipCertFileAll(Integer declareRecordId) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.getOwnershipCertFileAll(declareRecordId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,"获取权属证明复印件图片");
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
            baseService.writeExceptionInfo(e,"移除权属证明复印件图片");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getLandCertId", name = "获取关联土地证id", method = RequestMethod.POST)
    public HttpResult getLandCertId(Integer declareRecordId) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.getLandCertId(declareRecordId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,"获取关联土地证id");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getLandFileAll", name = "获取权属证明复印件图片", method = RequestMethod.POST)
    public HttpResult getLandFileAll(Integer tableId) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.getLandFileAll(tableId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,"获取权属证明复印件图片");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getAddressFileListByDeclareRecordId", name = "获取地址不一致附件", method = RequestMethod.POST)
    public HttpResult getAddressFileListByDeclareRecordId(Integer declareRecordId) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.getAddressFileListByDeclareRecordId(declareRecordId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,"获取地址不一致附件");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/makeJudgeObjectPosition", name = "生成位置图", method = RequestMethod.POST)
    public HttpResult makeJudgeObjectPosition(Integer schemeJudgeObjectId) {
        try {
            schemeReportFileService.makeJudgeObjectPosition(schemeJudgeObjectId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e,"生成位置图");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


}
