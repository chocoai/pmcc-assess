package com.copower.pmcc.assess.controller.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingConstructionPermit;
import com.copower.pmcc.assess.dto.input.project.declare.DeclareBuildingConstructionPermitDto;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildingConstructionPermitService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/9/28 09:13
 * @Description:建筑工程施工许可证
 */
@RequestMapping(value = "/declareBuildingConstructionPermit")
@Controller
public class DeclareBuildingConstructionPermitController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareBuildingConstructionPermitService declareBuildingConstructionPermitService;

    @ResponseBody
    @RequestMapping(value = "/getDeclareBuildingConstructionPermitById", method = {RequestMethod.GET}, name = "获取建筑工程施工许可证维护")
    public HttpResult getById(Integer id) {
        DeclareBuildingConstructionPermit declareBuildingConstructionPermit = null;
        try {
            if (id != null) {
                declareBuildingConstructionPermit = declareBuildingConstructionPermitService.getDeclareBuildingConstructionPermitById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(declareBuildingConstructionPermit);
    }

    @ResponseBody
    @RequestMapping(value = "/getDeclareBuildingConstructionPermitList", method = {RequestMethod.GET}, name = "获取建筑工程施工许可证维护列表")
    public BootstrapTableVo getExamineEstateNetworkList(Integer planDetailsId) {
        DeclareBuildingConstructionPermit declareBuildingConstructionPermit = new DeclareBuildingConstructionPermit();
        BootstrapTableVo vo = null;
        try {
            if (planDetailsId != null) {
                declareBuildingConstructionPermit.setPlanDetailsId(planDetailsId);
            }
            vo = declareBuildingConstructionPermitService.getDeclareBuildingConstructionPermitListVos(declareBuildingConstructionPermit);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDeclareBuildingConstructionPermitById", method = {RequestMethod.POST}, name = "删除建筑工程施工许可证维护")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                DeclareBuildingConstructionPermit declareBuildingConstructionPermit = new DeclareBuildingConstructionPermit();
                declareBuildingConstructionPermit.setId(id);
                declareBuildingConstructionPermitService.removeDeclareBuildingConstructionPermit(declareBuildingConstructionPermit);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDeclareBuildingConstructionPermit", method = {RequestMethod.POST}, name = "更新建筑工程施工许可证维护")
    public HttpResult saveAndUpdate(DeclareBuildingConstructionPermitDto declareBuildingConstructionPermitDto) {
        DeclareBuildingConstructionPermit declareBuildingConstructionPermit = new DeclareBuildingConstructionPermit();
        try {
            BeanUtils.copyProperties(declareBuildingConstructionPermitDto, declareBuildingConstructionPermit);
            Integer id = declareBuildingConstructionPermitService.saveAndUpdateDeclareBuildingConstructionPermit(declareBuildingConstructionPermit);
            return HttpResult.newCorrectResult(id);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listDeclareBuildingConstructionPermit", method = {RequestMethod.GET}, name = "建筑工程施工许可证维护 list")
    public HttpResult list(Integer planDetailsId) {
        try {
            DeclareBuildingConstructionPermit declareBuildingConstructionPermit = new DeclareBuildingConstructionPermit();
            if (planDetailsId != null) {
                declareBuildingConstructionPermit.setPlanDetailsId(planDetailsId);
            }
            return HttpResult.newCorrectResult(declareBuildingConstructionPermitService.declareBuildingConstructionPermitList(declareBuildingConstructionPermit));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }
}
