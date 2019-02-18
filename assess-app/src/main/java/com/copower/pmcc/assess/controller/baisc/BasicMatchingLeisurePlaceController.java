package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingLeisurePlace;
import com.copower.pmcc.assess.service.basic.BasicMatchingLeisurePlaceService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:50
 * @Description:
 */
@RequestMapping(value = "/basicMatchingLeisurePlace")
@Controller
public class BasicMatchingLeisurePlaceController {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicMatchingLeisurePlaceService basicMatchingLeisurePlaceService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicMatchingLeisurePlaceById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicMatchingLeisurePlaceById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicMatchingLeisurePlaceService.getBasicMatchingLeisurePlaceById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicMatchingLeisurePlace", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicMatchingLeisurePlace(BasicMatchingLeisurePlace basicMatchingLeisurePlace) {
        try {
            return HttpResult.newCorrectResult(200, basicMatchingLeisurePlaceService.saveAndUpdateBasicMatchingLeisurePlace(basicMatchingLeisurePlace));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicMatchingLeisurePlace", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicMatchingLeisurePlace(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicMatchingLeisurePlaceService.deleteBasicMatchingLeisurePlace(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/removeIds", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult removeIds(String ids) {
        try {
            basicMatchingLeisurePlaceService.removeIds(ids);
            return HttpResult.newCorrectResult(200, ids);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicMatchingLeisurePlace basicMatchingLeisurePlace, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval) {
        try {
            if (basicMatchingLeisurePlace != null) {
                if (!approval) {
                    basicMatchingLeisurePlace.setCreator(commonService.thisUserAccount());
                }
            }
            return basicMatchingLeisurePlaceService.getBootstrapTableVo(basicMatchingLeisurePlace);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicMatchingLeisurePlaceList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicMatchingLeisurePlaceList(BasicMatchingLeisurePlace basicMatchingLeisurePlace) {
        try {
            return HttpResult.newCorrectResult(200, basicMatchingLeisurePlaceService.basicMatchingLeisurePlaceList(basicMatchingLeisurePlace));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

}
