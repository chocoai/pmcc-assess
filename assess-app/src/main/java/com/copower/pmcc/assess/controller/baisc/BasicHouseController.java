package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicAlternativeCaseDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicAlternativeCase;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouse;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.basic.PublicBasicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:56
 * @Description:房屋
 */
@RequestMapping(value = "/basicHouse")
@Controller
public class BasicHouseController {
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicAlternativeCaseDao basicAlternativeCaseDao;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseById(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.getBasicHouseById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouse", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicHouse(BasicHouse basicHouse) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.saveAndUpdateBasicHouse(basicHouse, true));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouse", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicHouse(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.deleteBasicHouse(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

//    @ResponseBody
//    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
//    public BootstrapTableVo getBootstrapTableVo(BasicHouse basicHouse) {
//        try {
//            return basicHouseService.getBootstrapTableVo(basicHouse);
//        } catch (Exception e) {
//            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
//            return null;
//        }
//    }

    @ResponseBody
    @RequestMapping(value = "/basicHouseList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicHouseList(BasicHouse basicHouse) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.basicHouseList(basicHouse));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseByApplyId", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseByApplyId(Integer applyId) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.getBasicHouseByApplyId(applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getDataFromProject", name = "项目中引用数据", method = {RequestMethod.GET})
    public HttpResult getDataFromProject(Integer applyId) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.getBasicHouseFromProject(applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/quoteHouseData", name = "项目中引用数据批量", method = {RequestMethod.GET})
    public HttpResult quoteHouseData(Integer id, Integer tableId) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.quoteHouseData(id, tableId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/quoteFromAlternative", name = "引用项目中的数据批量时", method = {RequestMethod.GET})
    public HttpResult quoteFromAlternative(Integer id, Integer tableId) {
        try {
            BasicAlternativeCase alternativeCase = basicAlternativeCaseDao.getBasicAlternativeCaseById(id);
            BasicApplyBatchDetail batchDetail = basicApplyBatchDetailDao.getInfoById(alternativeCase.getBusinessId());
            return HttpResult.newCorrectResult(basicHouseService.quoteHouseData(batchDetail.getTableId(), tableId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addHouseAndTrading", name = "添加房屋及交易信息", method = {RequestMethod.POST})
    public HttpResult addHouseAndTrading(String houseNumber, Integer applyId) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.addHouseAndTrading(houseNumber, applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult("添加房屋及交易信息异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/appWriteHouse", name = "过程数据", method = {RequestMethod.POST})
    public HttpResult appWriteHouse(Integer caseHouseId, String housePartInMode, Integer applyId) {
        try {
            return HttpResult.newCorrectResult(200, basicHouseService.appWriteHouse(caseHouseId, housePartInMode, applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/copyHuxingPlan", name = "拷贝户型图", method = {RequestMethod.POST})
    public HttpResult copyHuxingPlan(Integer sourceTableId, String sourceTableName, Integer targetTableId, String fieldsName) {
        try {
            basicHouseService.copyHuxingPlan(sourceTableId, sourceTableName, targetTableId, fieldsName);
            return HttpResult.newCorrectResult(null);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult("拷贝户型图异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseMapById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseMapById(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicHouseService.getBasicHouseMapById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseList", method = {RequestMethod.GET}, name = "获取案例 房屋列表")
    public BootstrapTableVo getBasicHouseList(Integer unitId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        try {
            if (unitId != null) {
                vo = basicHouseService.getBootstrapTableVo(unitId);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/getQuoteUnitId", name = "获取引用的单元id", method = RequestMethod.GET)
    public HttpResult getAndEditDetail(Integer id) {
        try {
            BasicApplyBatchDetail houseDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_house", id);
            Integer quoteId = 0;
            if (houseDetail != null && houseDetail.getPid() != null) {
                quoteId = basicApplyBatchDetailService.getDataById(houseDetail.getPid()).getQuoteId();
            }
            return HttpResult.newCorrectResult(200, quoteId);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }
}
