package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateStreetInfo;
import com.copower.pmcc.assess.service.basic.BasicEstateStreetInfoService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:39
 * @Description:
 */
@RequestMapping(value = "/basicEstateStreetInfo")
@Controller
public class BasicEstateStreetInfoController {
    @Autowired
    private BasicApplyBatchDao basicApplyBatchDao;
    @Autowired
    private BasicEstateStreetInfoService basicEstateStreetInfoService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicEstateStreetInfoById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicEstateStreetInfoById(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicEstateStreetInfoService.getBasicEstateStreetInfoById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicEstateStreetInfo", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicEstateStreetInfo(BasicEstateStreetInfo basicEstateStreetInfo,@RequestParam(defaultValue = "false") boolean updateNull){
        try {
            return HttpResult.newCorrectResult(200,basicEstateStreetInfoService.saveAndUpdateBasicEstateStreetInfo(basicEstateStreetInfo,updateNull));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicEstateStreetInfo", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicEstateStreetInfo(Integer id){
        try {
            return HttpResult.newCorrectResult(200,basicEstateStreetInfoService.deleteBasicEstateStreetInfo(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicEstateStreetInfo basicEstateStreetInfo){
        try {
            return basicEstateStreetInfoService.getBootstrapTableVo(basicEstateStreetInfo);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicEstateStreetInfoList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicEstateStreetInfoList(BasicEstateStreetInfo basicEstateStreetInfo){
        try {
            return HttpResult.newCorrectResult(200,basicEstateStreetInfoService.basicEstateStreetInfoList(basicEstateStreetInfo));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s",e.getMessage()),e);
            return HttpResult.newErrorResult(500,e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/autoCompleteCaseEstate", method = {RequestMethod.GET}, name = "楼盘 信息自动补全")
    public HttpResult autoCompleteCaseEstate(String name, String province, String city) {
        try {
            List<BasicApplyBatch> applyBatchList = basicApplyBatchDao.getListByRemark(province, city, name);
            return HttpResult.newCorrectResult(applyBatchList);
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }
}
