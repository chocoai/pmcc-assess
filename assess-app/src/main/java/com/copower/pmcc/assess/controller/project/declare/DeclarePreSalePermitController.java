package com.copower.pmcc.assess.controller.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclarePreSalePermit;
import com.copower.pmcc.assess.dto.input.project.declare.DeclarePreSalePermitDto;
import com.copower.pmcc.assess.service.project.declare.DeclarePreSalePermitService;
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
 * @Date: 2018/9/28 09:14
 * @Description:商品房预售许可证
 */
@RequestMapping(value = "/declarePreSalePermit")
@Controller
public class DeclarePreSalePermitController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclarePreSalePermitService declarePreSalePermitService;


    @ResponseBody
    @RequestMapping(value = "/getDeclarePreSalePermitById", method = {RequestMethod.GET}, name = "获取商品房预售许可证")
    public HttpResult getById(Integer id) {
        DeclarePreSalePermit declarePreSalePermit = null;
        try {
            if (id != null) {
                declarePreSalePermit = declarePreSalePermitService.getDeclarePreSalePermitById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(declarePreSalePermit);
    }

    @ResponseBody
    @RequestMapping(value = "/getDeclarePreSalePermitList", method = {RequestMethod.GET}, name = "获取商品房预售许可证列表")
    public BootstrapTableVo getExamineEstateNetworkList(Integer planDetailsId) {
        DeclarePreSalePermit declarePreSalePermit = new DeclarePreSalePermit();
        BootstrapTableVo vo = null;
        try {
            if (planDetailsId != null) {
                declarePreSalePermit.setPlanDetailsId(planDetailsId);
            }
            vo = declarePreSalePermitService.getDeclarePreSalePermitListVos(declarePreSalePermit);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDeclarePreSalePermitById", method = {RequestMethod.POST}, name = "删除商品房预售许可证")
    public HttpResult delete(Integer id) {
        try {
            if (id != null) {
                DeclarePreSalePermit declarePreSalePermit = new DeclarePreSalePermit();
                declarePreSalePermit.setId(id);
                declarePreSalePermitService.removeDeclarePreSalePermit(declarePreSalePermit);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDeclarePreSalePermit", method = {RequestMethod.POST}, name = "更新商品房预售许可证")
    public HttpResult saveAndUpdate(DeclarePreSalePermitDto declarePreSalePermitDto) {
        DeclarePreSalePermit declarePreSalePermit = new DeclarePreSalePermit();
        try {
            BeanUtils.copyProperties(declarePreSalePermitDto,declarePreSalePermit);
            Integer id = declarePreSalePermitService.saveAndUpdateDeclarePreSalePermit(declarePreSalePermit);
            return HttpResult.newCorrectResult(id);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listDeclarePreSalePermit", method = {RequestMethod.GET}, name = "商品房预售许可证 list")
    public HttpResult list( Integer planDetailsId) {
        try {
            DeclarePreSalePermit declarePreSalePermit = new DeclarePreSalePermit();
            if (planDetailsId != null) {
                declarePreSalePermit.setPlanDetailsId(planDetailsId);
            }
            return HttpResult.newCorrectResult(declarePreSalePermitService.getDeclarePreSalePermitListVos(declarePreSalePermit));
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("异常");
        }
    }
}
