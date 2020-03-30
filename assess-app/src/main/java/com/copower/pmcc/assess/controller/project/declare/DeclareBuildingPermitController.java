package com.copower.pmcc.assess.controller.project.declare;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingPermit;
import com.copower.pmcc.assess.dto.input.project.declare.DeclareBuildingPermitDto;
import com.copower.pmcc.assess.service.project.declare.DeclareBuildingPermitService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/9/28 09:12
 * @Description:建设工程规划许可证
 */
@RequestMapping(value = "/declareBuildingPermit")
@Controller
public class DeclareBuildingPermitController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareBuildingPermitService declareBuildingPermitService;

    @ResponseBody
    @RequestMapping(value = "/getDeclareBuildingPermitById", method = {RequestMethod.GET}, name = "获取建设工程规划许可证")
    public HttpResult getById(Integer id) {
        DeclareBuildingPermit declareBuildingPermit = null;
        try {
            if (id != null) {
                declareBuildingPermit = declareBuildingPermitService.getDeclareBuildingPermitById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(declareBuildingPermit);
    }

    @ResponseBody
    @RequestMapping(value = "/getDeclareBuildingPermitList", method = {RequestMethod.GET}, name = "获取建设工程规划许可证列表")
    public BootstrapTableVo getExamineEstateNetworkList(DeclareBuildingPermit declareBuildingPermit) {
        BootstrapTableVo vo = declareBuildingPermitService.getDeclareBuildingPermitListVos(declareBuildingPermit);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDeclareBuildingPermitById", method = {RequestMethod.POST}, name = "删除建设工程规划许可证")
    public HttpResult delete(String id) {
        try {
            declareBuildingPermitService.deleteDeclareBuildingPermitByIds(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @Deprecated
    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDeclareBuildingPermit", method = {RequestMethod.POST}, name = "更新建设工程规划许可证")
    public HttpResult saveAndUpdate(DeclareBuildingPermitDto declareBuildingPermitDto) {
        DeclareBuildingPermit declareBuildingPermit = new DeclareBuildingPermit();
        try {
            BeanUtils.copyProperties(declareBuildingPermitDto, declareBuildingPermit);
            Integer id = declareBuildingPermitService.saveAndUpdateDeclareBuildingPermit(declareBuildingPermit);
            return HttpResult.newCorrectResult(id);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveDeclareBuildingPermit", method = {RequestMethod.POST}, name = "更新建设工程规划许可证")
    public HttpResult saveDeclareBuildingPermit(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            DeclareBuildingPermit declareBuildingPermit = JSONObject.parseObject(formData, DeclareBuildingPermit.class);
            declareBuildingPermitService.saveAndUpdateDeclareBuildingPermit(declareBuildingPermit, updateNull);
            return HttpResult.newCorrectResult(declareBuildingPermit);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listDeclareBuildingPermit", method = {RequestMethod.GET}, name = "建设工程规划许可证 list")
    public HttpResult list(Integer planDetailsId) {
        try {
            DeclareBuildingPermit declareBuildingPermit = new DeclareBuildingPermit();
            if (planDetailsId != null) {
                declareBuildingPermit.setPlanDetailsId(planDetailsId);
            }
            return HttpResult.newCorrectResult(declareBuildingPermitService.declareBuildingPermitList(declareBuildingPermit));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }
}
