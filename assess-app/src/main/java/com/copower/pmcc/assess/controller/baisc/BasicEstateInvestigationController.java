package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicEstateInvestigation;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.basic.BasicEstateInvestigationService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;


@RequestMapping(value = "/basicEstateInvestigation")
@Controller
public class BasicEstateInvestigationController {
    @Autowired
    private BasicEstateInvestigationService basicEstateInvestigationService;
    @Autowired
    private BaseService baseService;


    @ResponseBody
    @RequestMapping(value = "/getBasicEstateInvestigationById", name = "获取数据", method = {RequestMethod.POST})
    public HttpResult getBasicEstateInvestigationById(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicEstateInvestigationService.getBasicEstateInvestigationById(id));
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicEstateInvestigation", name = "新增或者修改", method = RequestMethod.POST)
    public HttpResult saveAndUpdateBasicEstateInvestigation(BasicEstateInvestigation basicUnitHuxing) {
        try {
            return HttpResult.newCorrectResult(basicEstateInvestigationService.saveAndUpdateBasicEstateInvestigation(basicUnitHuxing, true));
        } catch (Exception e) {
            return HttpResult.newErrorResult("新增或者修改失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicEstateInvestigation", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicEstateInvestigation(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicEstateInvestigationService.deleteBasicEstateInvestigation(id));
        } catch (Exception e) {
            return HttpResult.newErrorResult("删除失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getEstateInvestigationList", name = "获取选择户型数据列表")
    public BootstrapTableVo getEstateInvestigationList(Integer unitHuxingId) {
        BasicEstateInvestigation basicEstateInvestigation = new BasicEstateInvestigation();
        basicEstateInvestigation.setHuxingId(unitHuxingId);
        return basicEstateInvestigationService.getBasicEstateInvestigationListVos(basicEstateInvestigation);

    }

    @ResponseBody
    @RequestMapping(value = "/getEstateInvestigationListByEstateId", name = "获取选择户型数据列表")
    public BootstrapTableVo getEstateInvestigationListByEstateId(Integer estateId) {
        BasicEstateInvestigation basicEstateInvestigation = new BasicEstateInvestigation();
        basicEstateInvestigation.setEstateId(estateId);
        return basicEstateInvestigationService.getBasicEstateInvestigationListVos(basicEstateInvestigation);

    }


    @ResponseBody
    @RequestMapping(value = "/importData", name = "导入", method = RequestMethod.POST)
    public HttpResult importData(HttpServletRequest request, Integer huxingId, Integer estateId) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String str = basicEstateInvestigationService.importData(multipartFile, huxingId, estateId);
            return HttpResult.newCorrectResult(str);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }

    }
}
