package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicAlternativeCaseDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicAlternativeCase;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicUnitService;
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
 * @Description:
 */
@RequestMapping(value = "/basicUnit")
@Controller
public class BasicUnitController {
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private PublicBasicService publicBasicService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicAlternativeCaseDao basicAlternativeCaseDao;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicUnitById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicUnitById(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.getBasicUnitById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicUnit", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicUnit(BasicUnit basicUnit) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.saveAndUpdateBasicUnit(basicUnit, true));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicUnit", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicUnit(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.deleteBasicUnit(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

//    @ResponseBody
//    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
//    public BootstrapTableVo getBootstrapTableVo(BasicUnit basicUnit) {
//        try {
//            return basicUnitService.getBootstrapTableVo(basicUnit);
//        } catch (Exception e) {
//            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
//            return null;
//        }
//    }

    @ResponseBody
    @RequestMapping(value = "/basicUnitList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicUnitList(BasicUnit basicUnit) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.basicUnitList(basicUnit));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicUnitByApplyId", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicUnitByApplyId(Integer applyId) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.getBasicUnitByApplyId(applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getDataFromProject", name = "项目中引用数据", method = {RequestMethod.GET})
    public HttpResult getDataFromProject(Integer applyId) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.getBasicUnitByFromProject(applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/quoteUnitData", name = "项目中引用数据批量", method = {RequestMethod.GET})
    public HttpResult quoteUnitData(Integer id, Integer tableId) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.quoteUnitData(id, tableId));
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
            return HttpResult.newCorrectResult(basicUnitService.quoteUnitData(batchDetail.getTableId(), tableId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addUnit", name = "添加单元信息", method = {RequestMethod.POST})
    public HttpResult addUnit(String unitNumber) {
        try {
            return HttpResult.newCorrectResult(basicUnitService.addUnit(unitNumber));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult("添加楼盘及土地基本信息异常");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/appWriteUnit", name = "过程数据转移", method = {RequestMethod.POST})
    public HttpResult appWriteUnit(Integer caseUnitId, String unitPartInMode, Integer applyId) {
        try {
            return HttpResult.newCorrectResult(200, basicUnitService.appWriteUnit(caseUnitId, unitPartInMode, applyId));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicUnitList", method = {RequestMethod.GET}, name = "获取案例 单元列表")
    public BootstrapTableVo getBasicUnitList(Integer buildingId) {
        BasicUnit basicUnit = new BasicUnit();
        BootstrapTableVo vo = new BootstrapTableVo();
        try {
            if (buildingId != null) {
                vo = basicUnitService.getBootstrapTableVo(buildingId);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/getQuoteBuildingId", name = "获取引用的楼栋id", method = RequestMethod.GET)
    public HttpResult getAndEditDetail(Integer id) {
        try {
            BasicApplyBatchDetail unitDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_unit", id);
            Integer quoteId = 0;
            if (unitDetail != null && unitDetail.getPid()!= null &&unitDetail.getUpgradeTableId()==null) {
                quoteId = basicApplyBatchDetailService.getDataById(unitDetail.getPid()).getQuoteId();
            }
            return HttpResult.newCorrectResult(200, quoteId);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

}
