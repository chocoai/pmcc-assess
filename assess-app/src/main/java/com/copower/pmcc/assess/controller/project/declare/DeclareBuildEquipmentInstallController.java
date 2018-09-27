package com.copower.pmcc.assess.controller.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEquipmentInstall;
import com.copower.pmcc.assess.dto.input.project.declare.DeclareBuildEquipmentInstallDto;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEquipmentInstallService;
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
 * @Date: 2018/9/27 12:05
 * @Description:在建工程（设备安装）
 */
@RequestMapping(value = "/declareBuildEquipmentInstall")
@Controller
public class DeclareBuildEquipmentInstallController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareBuildEquipmentInstallService declareBuildEquipmentInstallService;

    @ResponseBody
    @RequestMapping(value = "/getDeclareBuildEquipmentInstallById", method = {RequestMethod.GET}, name = "获取设备安装维护")
    public HttpResult getById(Integer id) {
        DeclareBuildEquipmentInstall declareBuildEquipmentInstall = null;
        try {
            if (id != null) {
                declareBuildEquipmentInstall = declareBuildEquipmentInstallService.getDeclareBuildEquipmentInstallById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(declareBuildEquipmentInstallService.getDeclareBuildEquipmentInstallVo(declareBuildEquipmentInstall));
    }

    @ResponseBody
    @RequestMapping(value = "/getDeclareBuildEquipmentInstallList", method = {RequestMethod.GET}, name = "获取设备安装维护列表")
    public BootstrapTableVo getExamineEstateNetworkList(Integer planDetailsId, String province, String city, String district, Integer pid) {
        DeclareBuildEquipmentInstall declareBuildEquipmentInstall = new DeclareBuildEquipmentInstall();
        BootstrapTableVo vo = null;
        try {
            if (planDetailsId != null) {
                declareBuildEquipmentInstall.setPlanDetailsId(planDetailsId);
            }
            if (!StringUtils.isEmpty(province)) {
                declareBuildEquipmentInstall.setProvince(province);
            }
            if (!StringUtils.isEmpty(city)) {
                declareBuildEquipmentInstall.setCity(city);
            }
            if (!StringUtils.isEmpty(district)) {
                declareBuildEquipmentInstall.setDistrict(district);
            }
            if (pid != null) {
                declareBuildEquipmentInstall.setPid(pid);
            }
//            declareBuildEquipmentInstall.setEnable("yes");
            vo = declareBuildEquipmentInstallService.getDeclareBuildEquipmentInstallListVos(declareBuildEquipmentInstall);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDeclareBuildEquipmentInstallById", method = {RequestMethod.POST}, name = "删除设备安装维护")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                DeclareBuildEquipmentInstall declareBuildEquipmentInstall = new DeclareBuildEquipmentInstall();
                declareBuildEquipmentInstall.setId(id);
                declareBuildEquipmentInstallService.removeDeclareBuildEquipmentInstall(declareBuildEquipmentInstall);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDeclareBuildEquipmentInstall", method = {RequestMethod.POST}, name = "更新设备安装维护")
    public HttpResult saveAndUpdate(DeclareBuildEquipmentInstallDto declareBuildEquipmentInstallDto) {
        DeclareBuildEquipmentInstall declareBuildEquipmentInstall = new DeclareBuildEquipmentInstall();
        if (declareBuildEquipmentInstallDto != null) {
            BeanUtils.copyProperties(declareBuildEquipmentInstallDto, declareBuildEquipmentInstall);
        }
        try {
            declareBuildEquipmentInstallService.saveAndUpdateDeclareBuildEquipmentInstall(declareBuildEquipmentInstall);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listDeclareBuildEquipmentInstall", method = {RequestMethod.GET}, name = "设备安装维护 list")
    public HttpResult list(Integer pid, String province, String city, String district, Integer planDetailsId) {
        try {
            DeclareBuildEquipmentInstall declareBuildEquipmentInstall = new DeclareBuildEquipmentInstall();
            if (!StringUtils.isEmpty(province)) {
                declareBuildEquipmentInstall.setProvince(province);
            }
            if (!StringUtils.isEmpty(city)) {
                declareBuildEquipmentInstall.setCity(city);
            }
            if (!StringUtils.isEmpty(district)) {
                declareBuildEquipmentInstall.setDistrict(district);
            }
            if (planDetailsId != null) {
                declareBuildEquipmentInstall.setPlanDetailsId(planDetailsId);
            }
            if (pid != null) {
                declareBuildEquipmentInstall.setPid(pid);
            }
            return HttpResult.newCorrectResult(declareBuildEquipmentInstallService.declareBuildEquipmentInstallVos(declareBuildEquipmentInstall));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }
}
