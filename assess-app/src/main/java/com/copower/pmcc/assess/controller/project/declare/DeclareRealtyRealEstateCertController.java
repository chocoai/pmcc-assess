package com.copower.pmcc.assess.controller.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyRealEstateCert;
import com.copower.pmcc.assess.dto.input.project.declare.DeclareRealtyRealEstateCertDto;
import com.copower.pmcc.assess.service.project.declare.DeclareRealtyRealEstateCertService;
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

/**
 * @Auther: zch
 * @Date: 2018/9/19 10:50
 * @Description:不动产控制器
 */
@RequestMapping(value = "/declareRealtyRealEstateCert")
@Controller
public class DeclareRealtyRealEstateCertController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;

    @ResponseBody
    @RequestMapping(value = "/getDeclareRealtyRealEstateCertById", method = {RequestMethod.GET}, name = "获取不动产维护")
    public HttpResult getById(Integer id) {
        DeclareRealtyRealEstateCert declareRealtyRealEstateCert = null;
        try {
            if (id != null) {
                declareRealtyRealEstateCert = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertVo(declareRealtyRealEstateCert));
    }

    @ResponseBody
    @RequestMapping(value = "/getDeclareRealtyRealEstateCertList", method = {RequestMethod.GET}, name = "获取不动产维护列表")
    public BootstrapTableVo getExamineEstateNetworkList(Integer pid, Integer planDetailsId, String province, String city, String district) {
        DeclareRealtyRealEstateCert declareRealtyRealEstateCert = new DeclareRealtyRealEstateCert();
        BootstrapTableVo vo = null;
        try {
            if (planDetailsId != null) {
                declareRealtyRealEstateCert.setPlanDetailsId(planDetailsId);
            }
            if (!StringUtils.isEmpty(province)) {
                declareRealtyRealEstateCert.setProvince(province);
            }
            if (!StringUtils.isEmpty(city)) {
                declareRealtyRealEstateCert.setCity(city);
            }
            if (!StringUtils.isEmpty(district)) {
                declareRealtyRealEstateCert.setDistrict(district);
            }
            if (pid != null) {
                declareRealtyRealEstateCert.setPid(pid);
            }
            vo = declareRealtyRealEstateCertService.getDeclareRealtyRealEstateCertListVos(declareRealtyRealEstateCert);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDeclareRealtyRealEstateCertById", method = {RequestMethod.POST}, name = "删除不动产维护")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                DeclareRealtyRealEstateCert declareRealtyRealEstateCert = new DeclareRealtyRealEstateCert();
                declareRealtyRealEstateCert.setId(id);
                declareRealtyRealEstateCertService.removeDeclareRealtyRealEstateCert(declareRealtyRealEstateCert);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDeclareRealtyRealEstateCert", method = {RequestMethod.POST}, name = "更新不动产维护")
    public HttpResult saveAndUpdate(DeclareRealtyRealEstateCertDto declareRealtyRealEstateCertDto) {
        DeclareRealtyRealEstateCert declareRealtyRealEstateCert = new DeclareRealtyRealEstateCert();
        if (declareRealtyRealEstateCertDto != null){
            BeanUtils.copyProperties(declareRealtyRealEstateCertDto,declareRealtyRealEstateCert);
        }
        try {
            declareRealtyRealEstateCertService.saveAndUpdateDeclareRealtyRealEstateCert(declareRealtyRealEstateCert);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listDeclareRealtyRealEstateCert", method = {RequestMethod.GET}, name = "不动产维护 list")
    public HttpResult list(Integer pid, String province, String city, String district, Integer planDetailsId) {
        try {
            DeclareRealtyRealEstateCert declareRealtyRealEstateCert = new DeclareRealtyRealEstateCert();
            if (!StringUtils.isEmpty(province)) {
                declareRealtyRealEstateCert.setProvince(province);
            }
            if (!StringUtils.isEmpty(city)) {
                declareRealtyRealEstateCert.setCity(city);
            }
            if (!StringUtils.isEmpty(district)) {
                declareRealtyRealEstateCert.setDistrict(district);
            }
            if (planDetailsId != null) {
                declareRealtyRealEstateCert.setPlanDetailsId(planDetailsId);
            }
            if (pid != null) {
                declareRealtyRealEstateCert.setPid(pid);
            }
            return HttpResult.newCorrectResult(declareRealtyRealEstateCertService.landLevels(declareRealtyRealEstateCert));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }
}
