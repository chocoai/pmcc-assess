package com.copower.pmcc.assess.controller.project.survey;

import com.alibaba.dubbo.rpc.RpcInvocation;
import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.controller.ControllerComponent;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.entity.SurveyCaseStudyDetail;
import com.copower.pmcc.assess.dto.output.project.SurveyCaseStudyDetailVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.DeclareRecordService;
import com.copower.pmcc.assess.service.project.SurveyCaseStudyDetailService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.activiti.engine.form.FormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zly on 2018/5/17.
 */
@Controller
@RequestMapping(value = "/caseStudy")
public class SurveyCaseStudyDetailController {

    @Autowired
    private ControllerComponent controllerComponent;
    @Autowired
    private SurveyCaseStudyDetailService surveyCaseStudyDetailService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/index", name = "案例调查管理页面", method = RequestMethod.GET)
    public ModelAndView index(Integer id, Integer planDetailsId, Integer projectId) {
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/task/case/caseStudyManage");
        List<BaseDataDic> caseType = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.CASE_TYPE);
        List<BaseDataDic> caseInfoSource = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.CASE_INFO_SOURCE);
        SurveyCaseStudyDetail surveyCaseStudyDetail = null;
        if (id != null && id > 0) {
            surveyCaseStudyDetail = surveyCaseStudyDetailService.getSingelDetail(id);
        } else {
            surveyCaseStudyDetail = new SurveyCaseStudyDetail();
            surveyCaseStudyDetail.setId(0);
        }
        List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordByProjectId(projectId);
        modelAndView.addObject("declareRecords", declareRecords);
        modelAndView.addObject("caseType", caseType);
        modelAndView.addObject("caseInfoSource", caseInfoSource);
        modelAndView.addObject("surveyCaseStudyDetail", surveyCaseStudyDetail);
        modelAndView.addObject("planDetailsId",planDetailsId);
        return modelAndView;
    }

    @RequestMapping(value="/detailsIndex", name="详情页面",method = RequestMethod.GET)
    public ModelAndView detailIndex(Integer id){
        ModelAndView modelAndView = controllerComponent.baseModelAndView("/task/case/caseStudyDetails");
        SurveyCaseStudyDetail surveyCaseStudyDetail = null;
        if (id != null && id > 0) {
            surveyCaseStudyDetail = surveyCaseStudyDetailService.getSingelDetail(id);
        }
        List<SurveyCaseStudyDetailVo> surveyCaseStudyDetailVo = surveyCaseStudyDetailService.getName(id);
        SurveyCaseStudyDetailVo surveyCaseStudyDetailName = surveyCaseStudyDetailVo.get(0);

        modelAndView.addObject("surveyCaseStudyDetail", surveyCaseStudyDetail);
        modelAndView.addObject("surveyCaseStudyDetailName",surveyCaseStudyDetailName);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/list", name = "取得案例调查列表", method = RequestMethod.GET)
    public BootstrapTableVo getList(Integer planDetailsId) {
        BootstrapTableVo bootstrapTableVo = surveyCaseStudyDetailService.getList(planDetailsId);
        return bootstrapTableVo;
    }

    @ResponseBody
    @RequestMapping(value = "/save", name = "保存或修改数据", method = RequestMethod.POST)
    public HttpResult save(String formData) {
        try {
            SurveyCaseStudyDetail surveyCaseStudyDetail = JSON.parseObject(formData, SurveyCaseStudyDetail.class);
            surveyCaseStudyDetailService.save(surveyCaseStudyDetail);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/delete", name = "删除数据", method = RequestMethod.POST)
    public HttpResult delete(Integer id) {
        try {
            surveyCaseStudyDetailService.delete(id);

        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
