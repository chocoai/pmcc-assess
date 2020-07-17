package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouse;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitHuxing;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitHuxingVo;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicHouseService;
import com.copower.pmcc.assess.service.basic.BasicUnitHuxingService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:27
 * @Description:
 */
@RequestMapping(value = "/basicUnitHuxing")
@RestController
public class BasicUnitHuxingController {
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/getBasicUnitHuxingById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicUnitHuxingById(Integer huxingId) {
        try {
            BasicUnitHuxingVo basicUnitHuxingVo = basicUnitHuxingService.getBasicUnitHuxingVo(basicUnitHuxingService.getBasicUnitHuxingById(huxingId));
            return HttpResult.newCorrectResult(basicUnitHuxingVo);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/saveBasicUnitHuxing", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveBasicUnitHuxing(String formData) {
        try {
            BasicUnitHuxing basicUnitHuxing = JSON.parseObject(formData, BasicUnitHuxing.class);
            Integer huxingId = basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(basicUnitHuxing, true);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(String.format("保存户型信息异常:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteBasicUnitHuxing", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicUnitHuxing(Integer huxingId) {
        try {
            return HttpResult.newCorrectResult(basicUnitHuxingService.deleteBasicUnitHuxing(huxingId));
        } catch (Exception e) {
            logger.error(String.format("删除数据:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/referenceHuxing", name = "引用户型信息", method = {RequestMethod.POST})
    public HttpResult referenceHuxing(Integer huxingId, Integer houseId) {
        try {
            basicUnitHuxingService.referenceHuxing(huxingId, houseId);
            return HttpResult.newCorrectResult(basicUnitHuxingService.getBasicUnitHuxingById(huxingId));
        } catch (Exception e) {
            logger.error(String.format("引用户型信息:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(Integer applyBatchId, String name) {
        try {
            return basicUnitHuxingService.getBootstrapTableVo(applyBatchId, name);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @GetMapping(value = "/getSelectHuxingList", name = "获取选择户型数据列表")
    public BootstrapTableVo getSelectHuxingList(Integer basicApplyId, Integer caseUnitId) {
        try {
            return basicUnitHuxingService.getSelectHuxingList(basicApplyId, caseUnitId);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @GetMapping(value = "/getSelectHuxingListByUnitId", name = "获取选择户型数据列表")
    public BootstrapTableVo getSelectHuxingListByUnitId(Integer basicUnitId) {
        try {
            return basicUnitHuxingService.getSelectHuxingListByUnitId(basicUnitId);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }


    @GetMapping(value = "/getHuxingByHouseId", name = "获取通过HouseId")
    public HttpResult getHuxingByHouseId(Integer basicHouseId) {
        try {
            BasicHouse basicHouse = basicHouseService.getBasicHouseById(basicHouseId);
            return HttpResult.newCorrectResult(200, basicUnitHuxingService.getBasicUnitHuxingById(basicHouse.getHuxingId()));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getUnitId", name = "批量申请获取单元id")
    public HttpResult getUnitId(Integer tableId) {
        try {
            BasicApplyBatchDetail house = basicApplyBatchDetailService.getBasicApplyBatchDetail("tb_basic_house", tableId);
            BasicUnit basicUnit = basicApplyBatchDetailService.getBasicUnitByBatchDetailId(house.getPid());
            return HttpResult.newCorrectResult(basicUnit.getId());
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }


    @RequestMapping(value = "/basicUnitHuxingList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicUnitHuxingList(BasicUnitHuxing basicUnitHuxing) {
        try {
            return HttpResult.newCorrectResult(200, basicUnitHuxingService.basicUnitHuxingList(basicUnitHuxing));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @GetMapping(value = "/getAttachmentId", name = "获取附件id")
    public HttpResult getAttachmentId(Integer tableId) {
        try {
            Integer attachmentId = basicUnitHuxingService.getAttachmentId(tableId);
            return HttpResult.newCorrectResult(attachmentId);
        } catch (Exception e) {
            logger.error("获取附件id异常", e);
            return HttpResult.newErrorResult("获取附件id异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/importHouse", name = "导入", method = RequestMethod.POST)
    public HttpResult importHouse(HttpServletRequest request, Integer unitId) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String str = basicUnitHuxingService.importHouse(multipartFile, unitId);
            return HttpResult.newCorrectResult(str);
        } catch (Exception e) {
            logger.error("导入数据异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

}
