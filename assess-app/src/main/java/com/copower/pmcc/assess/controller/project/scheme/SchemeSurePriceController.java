package com.copower.pmcc.assess.controller.project.scheme;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeSurePriceApplyDto;
import com.copower.pmcc.assess.dto.input.project.survey.ExamineHousePriceDto;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.scheme.SchemeSurePriceFactorService;
import com.copower.pmcc.assess.service.project.scheme.SchemeSurePriceService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kings on 2018-10-15.
 */
@RestController
@RequestMapping("/schemeSurePrice")
public class SchemeSurePriceController {
    @Autowired
    private SchemeSurePriceService schemeSurePriceService;
    @Autowired
    private SchemeSurePriceFactorService schemeSurePriceFactorService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private BaseService baseService;
    private final String errorInfo = "确定单价异常";

    @GetMapping(value = "/getSchemeSurePriceItemList", name = "获取确定单价明细数据信息")
    public HttpResult getSchemeSurePriceItemList(Integer judgeObjectId, boolean isUpdatePrice) {
        try {
            List<SchemeSurePriceItem> schemeSurePriceList = schemeSurePriceService.getSchemeSurePriceItemList(judgeObjectId, isUpdatePrice);
            return HttpResult.newCorrectResult(200, schemeSurePriceList);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult(500, "获取数据异常");
        }
    }


    @GetMapping(value = "/getAdjustObjectListByPid", name = "获取待调整价格的估价对象")
    public HttpResult getAdjustObjectListByPid(Integer judgeObjectId) {
        try {
            List<SchemeJudgeObjectVo> vos = schemeJudgeObjectService.getVoListByPid(judgeObjectId);
            return HttpResult.newCorrectResult(200, vos);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult(500, "获取数据异常");
        }
    }


    @GetMapping(value = "/getSurePriceFactors", name = "获取调整单价系数")
    public HttpResult getSurePriceFactors(Integer judgeObjectId) {
        try {
            List<SchemeSurePriceFactor> certAdjustmentFactors = schemeSurePriceFactorService.getSurePriceFactors(judgeObjectId);
            return HttpResult.newCorrectResult(certAdjustmentFactors);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult("获取数据异常");
        }
    }


    @PostMapping(value = "/saveSurePriceFactor", name = "保存调整系数及价格")
    public HttpResult saveSurePriceFactor(Integer judgeObjectId, BigDecimal price, String formData) {
        try {
            List<SchemeSurePriceFactor> factorList = JSON.parseArray(formData, SchemeSurePriceFactor.class);
            return HttpResult.newCorrectResult(schemeSurePriceFactorService.saveSurePriceFactor(judgeObjectId, price, factorList));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult("获取数据异常");
        }
    }

    @PostMapping(value = "/copySurePriceFactor", name = "复制调整系数")
    public HttpResult copySurePriceFactor(Integer beCopyJudgeObjectId, Integer judgeObjectId) {
        try {
            schemeSurePriceFactorService.copySurePriceFactor(beCopyJudgeObjectId, Lists.newArrayList(judgeObjectId));
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult("复制调整系数异常");
        }
    }

    @PostMapping(value = "/copySurePriceFactorBatch", name = "批量复制调整系数")
    public HttpResult copySurePriceFactorBatch(Integer beCopyJudgeObjectId, String judgeObjectIds) {
        try {
            List<Integer> integers = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(judgeObjectIds));
            schemeSurePriceFactorService.copySurePriceFactor(beCopyJudgeObjectId, integers);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult("批量复制调整系数异常");
        }
    }

    @PostMapping(value = "/updateCalculationSchemeSurePrice", name = "更新计算的估价对象单价")
    public HttpResult updateCalculationSchemeSurePrice(String fomData, Integer planDetailsId) {
        try {
            SchemeSurePriceApplyDto schemeSurePriceApplyDto = JSONObject.parseObject(fomData, SchemeSurePriceApplyDto.class);
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
            schemeSurePriceService.submitSurePrice(schemeSurePriceApplyDto, projectPlanDetails, null);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult("更新计算的估价对象单价异常");
        }
    }

    @RequestMapping(value = "/generateAndExport", name = "生成并导出模板")
    public void generateAndExport(HttpServletResponse response, Integer pid, String factorColumns) throws Exception {
        try {
            List<ExamineHousePriceDto> dtoList = JSON.parseArray(factorColumns, ExamineHousePriceDto.class);
            schemeSurePriceService.generateAndExport(response, pid,dtoList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/importData", name = "上传", method = RequestMethod.POST)
    public HttpResult importData(HttpServletRequest request, Integer pid) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String str = schemeSurePriceService.importData(multipartFile, pid);
            return HttpResult.newCorrectResult(str);
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

    @GetMapping(value = "/getBasicHouse", name = "获取待调整价格的估价对象")
    public HttpResult getBasicHouse(Integer judgeObjectId) {
        try {
            BasicHouse house = schemeSurePriceService.getBasicHouse(judgeObjectId);
            return HttpResult.newCorrectResult(200, house);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult(500, "获取数据异常");
        }
    }

    @RequestMapping(value = "/generateHuxingPrice", name = "生成并导出单价调查模板")
    public void generateHuxingPrice(HttpServletResponse response, String columns, Integer houseId,Integer judgeObjectId) throws Exception {
        try {
            List<ExamineHousePriceDto> dtoList = JSON.parseArray(columns, ExamineHousePriceDto.class);
            schemeSurePriceService.generateHuxingPrice(response, dtoList, houseId,judgeObjectId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/importHuxingPrice", name = "导入单价记录", method = RequestMethod.POST)
    public HttpResult importHuxingPrice(HttpServletRequest request, Integer houseId,Integer projectId) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String str = schemeSurePriceService.importDataPrice(multipartFile, houseId,projectId);
            return HttpResult.newCorrectResult(str);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

    @GetMapping(value = "/getTenementTypeData", name = "获取标准schemeJudgeObject")
    public HttpResult getTenementTypeData(Integer pid) {
        try {
            SchemeJudgeObject parent = schemeJudgeObjectService.getSchemeJudgeObject(pid);
            List<SchemeJudgeObjectVo> vos = schemeJudgeObjectService.getVoListByPid(pid);
            List<SchemeJudgeObjectVo> filter = LangUtils.filter(vos, o -> o.getId().equals( parent.getStandardJudgeId()));
            if(CollectionUtils.isNotEmpty(filter)){
                return HttpResult.newCorrectResult(200, schemeJudgeObjectService.getSchemeJudgeObjectVo(filter.get(0)));
            }
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, errorInfo);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

}
