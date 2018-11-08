package com.copower.pmcc.assess.controller.project.declare;

import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCert;
import com.copower.pmcc.assess.dto.input.project.declare.DeclareRealtyLandCertDto;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyLandCertService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:35
 * @Description:土地证控制器
 */
@RequestMapping(value = "/declareRealtyLandCert")
@Controller
public class DeclareRealtyLandCertController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService;

    @ResponseBody
    @RequestMapping(value = "/getDeclareRealtyLandCertById", method = {RequestMethod.GET}, name = "获取土地证维护")
    public HttpResult getById(Integer id) {
        DeclareRealtyLandCert declareRealtyLandCert = null;
        try {
            if (id != null) {
                declareRealtyLandCert = declareRealtyLandCertService.getDeclareRealtyLandCertById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(declareRealtyLandCert);
    }

    @ResponseBody
    @RequestMapping(value = "/getDeclareRealtyLandCertList", method = {RequestMethod.GET}, name = "获取土地证维护列表")
    public BootstrapTableVo getExamineEstateNetworkList(Integer pid, Integer planDetailsId, String province, String city, String district) {
        DeclareRealtyLandCert declareRealtyLandCert = new DeclareRealtyLandCert();
        BootstrapTableVo vo = null;
        try {
            if (planDetailsId != null) {
                declareRealtyLandCert.setPlanDetailsId(planDetailsId);
            }
            if (!StringUtils.isEmpty(province)) {
                declareRealtyLandCert.setProvince(province);
            }
            if (!StringUtils.isEmpty(city)) {
                declareRealtyLandCert.setCity(city);
            }
            if (!StringUtils.isEmpty(district)) {
                declareRealtyLandCert.setDistrict(district);
            }
            if (pid != null) {
                declareRealtyLandCert.setPid(pid);
            }
            declareRealtyLandCert.setEnable(DeclareTypeEnum.Enable.getKey());
            vo = declareRealtyLandCertService.getDeclareRealtyLandCertListVos(declareRealtyLandCert);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDeclareRealtyLandCertById", method = {RequestMethod.POST}, name = "删除土地证维护")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                DeclareRealtyLandCert declareRealtyLandCert = new DeclareRealtyLandCert();
                declareRealtyLandCert.setId(id);
                declareRealtyLandCertService.removeDeclareRealtyLandCert(declareRealtyLandCert);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDeclareRealtyLandCert", method = {RequestMethod.POST}, name = "更新土地证维护")
    public HttpResult saveAndUpdate(DeclareRealtyLandCertDto declareRealtyLandCertDto) {
        DeclareRealtyLandCert declareRealtyLandCert = new DeclareRealtyLandCert();
        if (declareRealtyLandCertDto != null){
            BeanUtils.copyProperties(declareRealtyLandCertDto,declareRealtyLandCert);
        }
        try {
            Integer id = declareRealtyLandCertService.saveAndUpdateDeclareRealtyLandCert(declareRealtyLandCert);
            return HttpResult.newCorrectResult(id);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listDeclareRealtyLandCert", method = {RequestMethod.GET}, name = "土地证维护 list")
    public HttpResult list(Integer pid, String province, String city, String district, Integer planDetailsId,String declareType,String enable) {
        try {
            DeclareRealtyLandCert declareRealtyLandCert = new DeclareRealtyLandCert();
            if (!StringUtils.isEmpty(province)) {
                declareRealtyLandCert.setProvince(province);
            }
            if (!StringUtils.isEmpty(city)) {
                declareRealtyLandCert.setCity(city);
            }
            if (!StringUtils.isEmpty(district)) {
                declareRealtyLandCert.setDistrict(district);
            }
            if (planDetailsId != null) {
                declareRealtyLandCert.setPlanDetailsId(planDetailsId);
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(declareType)){
                declareRealtyLandCert.setDeclareType(declareType);
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(enable)){
                declareRealtyLandCert.setEnable(enable);
            }
            if (pid != null) {
                declareRealtyLandCert.setPid(pid);
            }
            return HttpResult.newCorrectResult(declareRealtyLandCertService.lists(declareRealtyLandCert));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/importData", name = "导入土地证数据", method = RequestMethod.POST)
    public HttpResult importData(HttpServletRequest request, DeclareRealtyLandCert declareRealtyLandCert) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String str = declareRealtyLandCertService.importData(declareRealtyLandCert,multipartFile);
            return HttpResult.newCorrectResult(str);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/importDataHouse", name = "导入房产证并且关联土地证", method = RequestMethod.POST)
    public HttpResult importDataLand(HttpServletRequest request) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String str = declareRealtyLandCertService.importLandAndHouse(multipartFile);
            return HttpResult.newCorrectResult(str);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

}
