package com.copower.pmcc.assess.controller.project.declare;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCert;
import com.copower.pmcc.assess.dto.input.project.declare.AutomatedWarrants;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.declare.DeclarePublicService;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyHouseCertService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:42
 * @Description:房产证控制器
 */
@RequestMapping(value = "/declareRealtyHouseCert")
@Controller
public class DeclareRealtyHouseCertController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    @Autowired
    private BaseService baseService;

    @ResponseBody
    @RequestMapping(value = "/getDeclareRealtyHouseCertById", method = {RequestMethod.GET}, name = "获取房产证维护")
    public HttpResult getById(Integer id) {
        DeclareRealtyHouseCert declareRealtyHouseCert = null;
        try {
            if (id != null) {
                declareRealtyHouseCert = declareRealtyHouseCertService.getDeclareRealtyHouseCertById(id);
            }
        } catch (Exception e1) {
            baseService.writeExceptionInfo(e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(declareRealtyHouseCertService.getDeclareRealtyHouseCertVo(declareRealtyHouseCert));
    }

    @ResponseBody
    @RequestMapping(value = "/getDeclareRealtyHouseCertList", method = {RequestMethod.GET}, name = "获取房产证维护列表")
    public BootstrapTableVo getExamineEstateNetworkList(Integer planDetailsId, String province, String city, String district, Integer pid, String enable) {
        DeclareRealtyHouseCert declareRealtyHouseCert = new DeclareRealtyHouseCert();
        BootstrapTableVo vo = null;
        try {
            if (planDetailsId != null) {
                declareRealtyHouseCert.setPlanDetailsId(planDetailsId);
            }
            if (!StringUtils.isEmpty(province)) {
                declareRealtyHouseCert.setProvince(province);
            }
            if (!StringUtils.isEmpty(city)) {
                declareRealtyHouseCert.setCity(city);
            }
            if (!StringUtils.isEmpty(district)) {
                declareRealtyHouseCert.setDistrict(district);
            }
            if (pid != null) {
                declareRealtyHouseCert.setPid(pid);
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(enable)) {
                declareRealtyHouseCert.setEnable(enable);
            }
            vo = declareRealtyHouseCertService.getDeclareRealtyHouseCertListVos(declareRealtyHouseCert);
        } catch (Exception e1) {
            baseService.writeExceptionInfo(e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDeclareRealtyHouseCertById", method = {RequestMethod.POST}, name = "删除房产证维护")
    public HttpResult delete(String ids) {
        try {
            if (!StringUtils.isEmpty(ids)) {
                List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
                declareRealtyHouseCertService.deleteDeclareRealtyHouseCertById(integers);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            baseService.writeExceptionInfo(e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDeclareRealtyHouseCert", method = {RequestMethod.POST}, name = "更新房产证维护")
    public HttpResult saveAndUpdate(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            DeclareRealtyHouseCert declareRealtyHouseCert = JSON.parseObject(formData, DeclareRealtyHouseCert.class);
            Integer id = declareRealtyHouseCertService.saveAndUpdateDeclareRealtyHouseCert(declareRealtyHouseCert, updateNull);
            return HttpResult.newCorrectResult(id);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listDeclareRealtyHouseCert", method = {RequestMethod.GET}, name = "房产证维护 list")
    public HttpResult list(Integer pid, String province, String city, String district, Integer planDetailsId, String declareType, String enable) {
        try {
            DeclareRealtyHouseCert declareRealtyHouseCert = new DeclareRealtyHouseCert();
            if (!StringUtils.isEmpty(province)) {
                declareRealtyHouseCert.setProvince(province);
            }
            if (!StringUtils.isEmpty(city)) {
                declareRealtyHouseCert.setCity(city);
            }
            if (!StringUtils.isEmpty(district)) {
                declareRealtyHouseCert.setDistrict(district);
            }
            if (planDetailsId != null) {
                declareRealtyHouseCert.setPlanDetailsId(planDetailsId);
            }
            if (pid != null) {
                declareRealtyHouseCert.setPid(pid);
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(declareType)) {
                declareRealtyHouseCert.setDeclareType(declareType);
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(enable)) {
                declareRealtyHouseCert.setEnable(enable);
            }
            return HttpResult.newCorrectResult(declareRealtyHouseCertService.lists(declareRealtyHouseCert));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/attachmentAutomatedWarrants", name = "申报图片自动关联", method = RequestMethod.POST)
    public HttpResult attachmentAutomatedWarrants(AutomatedWarrants automatedWarrants) {
        try {
            declareRealtyHouseCertService.attachmentAutomatedWarrants(automatedWarrants);
            return HttpResult.newCorrectResult(500, "success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(200, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/importData", name = "导入房产证数据", method = RequestMethod.POST)
    public HttpResult importData(HttpServletRequest request, DeclareRealtyHouseCert declareRealtyHouseCert) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String str = declareRealtyHouseCertService.importData(declareRealtyHouseCert, multipartFile);
            return HttpResult.newCorrectResult(str);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

    @ResponseBody
    @RequestMapping(value = "/importDataLand", name = "导入土地证并且关联房产证", method = RequestMethod.POST)
    public HttpResult importDataLand(HttpServletRequest request, Integer planDetailsId) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String str = declareRealtyHouseCertService.importLandAndHouse(multipartFile, planDetailsId);
            return HttpResult.newCorrectResult(str);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


}
