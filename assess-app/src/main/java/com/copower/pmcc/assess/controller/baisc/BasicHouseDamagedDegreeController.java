package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseDamagedDegreeDetail;
import com.copower.pmcc.assess.service.basic.BasicHouseDamagedDegreeService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by kings on 2018-12-25.
 */
@Controller
@RequestMapping("/basicHouseDamagedDegree")
public class BasicHouseDamagedDegreeController extends BaseController {
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;


    @ResponseBody
    @RequestMapping(value = "/getDamagedDegreeList", name = "获取完损度数据", method = {RequestMethod.GET})
    public HttpResult getDamagedDegreeList(Integer houseId) {
        try {
            return HttpResult.newCorrectResult(basicHouseDamagedDegreeService.getDamagedDegreeVoList(houseId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getDamagedDegreeDetailList", method = {RequestMethod.GET})
    public BootstrapTableVo getDamagedDegreeDetailList(Integer damagedDegreeId) {
        return basicHouseDamagedDegreeService.getDamagedDegreeDetailList(damagedDegreeId);
    }


    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDamagedDegreeDetail", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateDamagedDegreeDetail(BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail) {
        try {
            basicHouseDamagedDegreeService.saveAndUpdateDamagedDegreeDetail(basicHouseDamagedDegreeDetail,true);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDamagedDegreeDetail", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteDamagedDegreeDetail(Integer id) {
        try {
            basicHouseDamagedDegreeService.deleteDamagedDegreeDetail(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
