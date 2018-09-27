package com.copower.pmcc.assess.controller.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEngineering;
import com.copower.pmcc.assess.dto.input.project.declare.DeclareBuildEngineeringDto;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringService;
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
 * @Date: 2018/9/27 12:04
 * @Description:在建工程（土建）
 */
@RequestMapping(value = "/declareBuildEngineering")
@Controller
public class DeclareBuildEngineeringController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareBuildEngineeringService declareBuildEngineeringService;

    @ResponseBody
    @RequestMapping(value = "/getDeclareBuildEngineeringById", method = {RequestMethod.GET}, name = "获取土建维护")
    public HttpResult getById(Integer id) {
        DeclareBuildEngineering declareBuildEngineering = null;
        try {
            if (id != null) {
                declareBuildEngineering = declareBuildEngineeringService.getDeclareBuildEngineeringById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(declareBuildEngineeringService.getDeclareBuildEngineeringVo(declareBuildEngineering));
    }

    @ResponseBody
    @RequestMapping(value = "/getDeclareBuildEngineeringList", method = {RequestMethod.GET}, name = "获取土建维护列表")
    public BootstrapTableVo getExamineEstateNetworkList(Integer planDetailsId, String province, String city, String district, Integer pid) {
        DeclareBuildEngineering declareBuildEngineering = new DeclareBuildEngineering();
        BootstrapTableVo vo = null;
        try {
            if (planDetailsId != null) {
                declareBuildEngineering.setPlanDetailsId(planDetailsId);
            }
            if (!StringUtils.isEmpty(province)) {
                declareBuildEngineering.setProvince(province);
            }
            if (!StringUtils.isEmpty(city)) {
                declareBuildEngineering.setCity(city);
            }
            if (!StringUtils.isEmpty(district)) {
                declareBuildEngineering.setDistrict(district);
            }
            if (pid != null) {
                declareBuildEngineering.setPid(pid);
            }
//            declareBuildEngineering.setEnable("yes");
            vo = declareBuildEngineeringService.getDeclareBuildEngineeringListVos(declareBuildEngineering);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDeclareBuildEngineeringById", method = {RequestMethod.POST}, name = "删除土建维护")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                DeclareBuildEngineering declareBuildEngineering = new DeclareBuildEngineering();
                declareBuildEngineering.setId(id);
                declareBuildEngineeringService.removeDeclareBuildEngineering(declareBuildEngineering);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDeclareBuildEngineering", method = {RequestMethod.POST}, name = "更新土建维护")
    public HttpResult saveAndUpdate(DeclareBuildEngineeringDto declareBuildEngineeringDto) {
        DeclareBuildEngineering declareBuildEngineering = new DeclareBuildEngineering();
        if (declareBuildEngineeringDto != null) {
            BeanUtils.copyProperties(declareBuildEngineeringDto, declareBuildEngineering);
        }
        try {
            declareBuildEngineeringService.saveAndUpdateDeclareBuildEngineering(declareBuildEngineering);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listDeclareBuildEngineering", method = {RequestMethod.GET}, name = "土建维护 list")
    public HttpResult list(Integer pid, String province, String city, String district, Integer planDetailsId) {
        try {
            DeclareBuildEngineering declareBuildEngineering = new DeclareBuildEngineering();
            if (!StringUtils.isEmpty(province)) {
                declareBuildEngineering.setProvince(province);
            }
            if (!StringUtils.isEmpty(city)) {
                declareBuildEngineering.setCity(city);
            }
            if (!StringUtils.isEmpty(district)) {
                declareBuildEngineering.setDistrict(district);
            }
            if (planDetailsId != null) {
                declareBuildEngineering.setPlanDetailsId(planDetailsId);
            }
            if (pid != null) {
                declareBuildEngineering.setPid(pid);
            }
            return HttpResult.newCorrectResult(declareBuildEngineeringService.declareBuildEngineeringVoList(declareBuildEngineering));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }
}
