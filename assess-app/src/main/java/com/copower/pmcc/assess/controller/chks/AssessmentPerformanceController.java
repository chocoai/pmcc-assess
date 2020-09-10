package com.copower.pmcc.assess.controller.chks;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.chks.AssessmentPerformanceService;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDetailDto;
import com.copower.pmcc.chks.api.dto.AssessmentPerformanceDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zch on 2019-12-16.
 * 获取考核数据
 */
@RestController
@RequestMapping(value = "/assessmentPerformance")
public class AssessmentPerformanceController {
    @Autowired
    private BaseService baseService;
    @Autowired
    private AssessmentPerformanceService assessmentPerformanceService;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private CommonService commonService;


    @GetMapping(value = "/getPerformanceList", name = "获取考核数据列表")
    public BootstrapTableVo getPerformanceList(AssessmentPerformanceDto where, Integer boxId, String reActivityName, String flog) {
        return assessmentPerformanceService.getPerformanceList(where, boxId, reActivityName, flog);
    }

    @GetMapping(value = "/getPerformanceDetailsByPerformanceId", name = "获取考核明细数据")
    public HttpResult getPerformanceDetailsByPerformanceId(Integer performanceId) {
        try {
            return HttpResult.newCorrectResult(assessmentPerformanceService.getPerformanceDetailsByPerformanceId(performanceId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "获取考核明细数据");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @GetMapping(value = "/getSpotPerformanceDetailList", name = "获取抽查模板数据")
    public HttpResult getSpotPerformanceDetailList(Integer performanceId) {
        try {
            return HttpResult.newCorrectResult(assessmentPerformanceService.getSpotPerformanceDetailList(performanceId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "获取抽查模板数据");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @GetMapping(value = "/getAdjustRecordListByPerformanceId", name = "获取调整记录列表")
    public BootstrapTableVo getAdjustRecordListByPerformanceId(Integer performanceId) {
        return assessmentPerformanceService.getAdjustRecordListByPerformanceId(performanceId);
    }

    @GetMapping(value = "/getSpotRecordListByPerformanceId", name = "获取抽查记录列表")
    public BootstrapTableVo getSpotRecordListByPerformanceId(Integer performanceId) {
        return assessmentPerformanceService.getSpotRecordListByPerformanceId(performanceId);
    }

    @GetMapping(value = "/getPerformanceListBySpotBatchId", name = "获取抽查记录列表by批次Id")
    public BootstrapTableVo getPerformanceListBySpotBatchId(Integer spotBatchId) {
        return assessmentPerformanceService.getPerformanceListBySpotBatchId(spotBatchId);
    }

    @GetMapping(value = "/getPerformanceById", name = "根据id获取考核数据")
    public HttpResult getPerformanceById(Integer id) {
        try {
            return HttpResult.newCorrectResult(assessmentPerformanceService.getPerformanceById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "根据id获取考核数据");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @GetMapping(value = "/getActivityIdByUserAccountList", name = "获取节点下的审批人")
    public HttpResult getActivityIdByUserAccountList(Integer activityId) {
        try {
            return HttpResult.newCorrectResult(bpmRpcBoxService.getRoleUserByActivityId(activityId));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "获取数据出错");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/saveAssessmentServer", name = "保存考核信息")
    public HttpResult saveAssessmentServer(String fomData, String chksScore, Boolean isSpot) {
        try {
            AssessmentPerformanceDto performanceDto = JSONObject.parseObject(fomData, AssessmentPerformanceDto.class);
            List<AssessmentPerformanceDetailDto> detailDtoList = JSONObject.parseArray(chksScore, AssessmentPerformanceDetailDto.class);
            if (isSpot == Boolean.TRUE) {
                assessmentPerformanceService.saveSpotPerformance(performanceDto,detailDtoList);
                return HttpResult.newCorrectResult();
            } else {
                 assessmentPerformanceService.saveAssessmentServer(performanceDto, detailDtoList);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "保存考核信息");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/pasteAll", name = "粘贴数据")
    public HttpResult pasteAll(Integer copyId, String ids) {
        try {
            return HttpResult.newCorrectResult(assessmentPerformanceService.pasteAll(copyId, ids));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "粘贴数据 出错");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/batchSetFinish", name = "批量设置完成质量考核")
    public HttpResult batchSetFinish(String ids) {
        try {
            List<Integer> list = FormatUtils.transformString2Integer(ids);
            assessmentPerformanceService.batchSetFinish(list);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "批量设置完成质量考核出错");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @PostMapping(value = "/batchSetIneffective", name = "批量设置考核数据为无效")
    public HttpResult batchSetIneffective(String ids) {
        try {
            List<Integer> list = FormatUtils.transformString2Integer(ids);
            assessmentPerformanceService.batchSetIneffective(list);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "批量设置考核数据为无效出错");
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}

