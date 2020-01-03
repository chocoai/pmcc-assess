package com.copower.pmcc.assess.controller.project.declare;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DeclareLandUsePermit;
import com.copower.pmcc.assess.dal.basis.entity.DeclareLandUsePermit;
import com.copower.pmcc.assess.dto.input.project.declare.DeclareLandUsePermitDto;
import com.copower.pmcc.assess.service.project.declare.DeclareLandUsePermitService;
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
 * @Date: 2018/9/28 09:16
 * @Description:建设用地规划许可证
 */
@RequestMapping(value = "/declareLandUsePermit")
@Controller
public class DeclareLandUsePermitController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareLandUsePermitService declareLandUsePermitService;

    @ResponseBody
    @RequestMapping(value = "/getDeclareLandUsePermitById", method = {RequestMethod.GET}, name = "获取建设用地规划许可证")
    public HttpResult getById(Integer id) {
        DeclareLandUsePermit declareLandUsePermit = null;
        try {
            if (id != null) {
                declareLandUsePermit = declareLandUsePermitService.getDeclareLandUsePermitById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(declareLandUsePermit);
    }

    @ResponseBody
    @RequestMapping(value = "/getDeclareLandUsePermitList", method = {RequestMethod.GET}, name = "获取建设用地规划许可证列表")
    public BootstrapTableVo getExamineEstateNetworkList(Integer planDetailsId) {
        DeclareLandUsePermit declareLandUsePermit = new DeclareLandUsePermit();
        BootstrapTableVo vo = null;
        try {
            if (planDetailsId != null) {
                declareLandUsePermit.setPlanDetailsId(planDetailsId);
            }
            vo = declareLandUsePermitService.getDeclareLandUsePermitListVos(declareLandUsePermit);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDeclareLandUsePermitById", method = {RequestMethod.POST}, name = "删除建设用地规划许可证")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                DeclareLandUsePermit declareLandUsePermit = new DeclareLandUsePermit();
                declareLandUsePermit.setId(id);
                declareLandUsePermitService.removeDeclareLandUsePermit(declareLandUsePermit);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @Deprecated
    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDeclareLandUsePermit", method = {RequestMethod.POST}, name = "更新建设用地规划许可证")
    public HttpResult saveAndUpdate(DeclareLandUsePermitDto declareLandUsePermitDto) {
        DeclareLandUsePermit declareLandUsePermit = new DeclareLandUsePermit();
        try {
            BeanUtils.copyProperties(declareLandUsePermitDto, declareLandUsePermit);
            Integer id = declareLandUsePermitService.saveAndUpdateDeclareLandUsePermit(declareLandUsePermit);
            return HttpResult.newCorrectResult(id);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveDeclareLandUsePermit", method = {RequestMethod.POST}, name = "更新建设用地规划许可证")
    public HttpResult saveDeclareLandUsePermit(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            DeclareLandUsePermit declareLandUsePermit = JSONObject.parseObject(formData, DeclareLandUsePermit.class);
            declareLandUsePermitService.saveAndUpdateDeclareLandUsePermit(declareLandUsePermit, updateNull);
            return HttpResult.newCorrectResult(declareLandUsePermit);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listDeclareLandUsePermit", method = {RequestMethod.GET}, name = "建设用地规划许可证 list")
    public HttpResult list(Integer planDetailsId) {
        try {
            DeclareLandUsePermit declareLandUsePermit = new DeclareLandUsePermit();
            if (planDetailsId != null) {
                declareLandUsePermit.setPlanDetailsId(planDetailsId);
            }
            return HttpResult.newCorrectResult(declareLandUsePermitService.declareLandUsePermitList(declareLandUsePermit));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }
}
