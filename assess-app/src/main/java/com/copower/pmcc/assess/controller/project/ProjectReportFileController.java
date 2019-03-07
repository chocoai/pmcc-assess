package com.copower.pmcc.assess.controller.project;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileItem;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeReportFileDto;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.SchemeReportFileService;
import com.copower.pmcc.assess.service.project.scheme.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
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
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SchemeReportFileService schemeReportFileService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;

    @RequestMapping(value = "/index", name = "委托书及证明文件")
    public ModelAndView index(Integer projectId,Integer areaId) {
        ModelAndView modelAndView = new ModelAndView("/project/projectReportFileIndex");
        modelAndView.addObject("projectInfo", projectInfoService.getSimpleProjectInfoVo(projectInfoService.getProjectInfoById(projectId)));
        modelAndView.addObject("areaId", areaId);
        modelAndView.addObject("areaGroup", schemeAreaGroupService.get(areaId));
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectApplicableListByAreaGroupId(areaId);//该区域下的所有委估对象
        modelAndView.addObject("judgeObjectList", judgeObjectList);
        modelAndView.addObject("judgeObjectFullList", schemeJudgeObjectService.getJudgeObjectFullListByAreaId(areaId));
        modelAndView.addObject("ownershipCertFileList", schemeReportFileService.getOwnershipCertFileList(areaId));
        modelAndView.addObject("inventoryAddressFileList", schemeReportFileService.getInventoryAddressFileList(areaId));
        modelAndView.addObject("reimbursementFileList", schemeReportFileService.getReimbursementFileList(areaId));
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
            logger.error(e.getMessage(),e);
            return HttpResult.newErrorResult("取得计划编制信息异常");
        }
    }
}
