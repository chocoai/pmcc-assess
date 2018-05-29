package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.StageWeightProportion;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.StageWeightProportionService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping(value = "/stageWeightProportion")
@Controller
public class StageWeightProportionController {

    @Autowired
    private StageWeightProportionService stageWeightProportionService;

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value="/Index",name="阶段权重占比视图")
    public ModelAndView index(){
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/stageWeightProportion");
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE);
        List<BaseDataDic> baseDataDics1 = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_WORK_STAGE);
        modelAndView.addObject("entrustmentPurposeList",baseDataDics);
        modelAndView.addObject("stageList",baseDataDics1);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value="/list",name="取得阶段权重占比",method = RequestMethod.GET)
    public BootstrapTableVo list(Integer entrustmentPurpose){
        BootstrapTableVo vo = stageWeightProportionService.getList(entrustmentPurpose);
        return vo;
    }
    @ResponseBody
    @RequestMapping(value="/save",name="新增或修改阶段权重占比",method = RequestMethod.POST)
    public HttpResult save(StageWeightProportion stageWeightProportion){
        try {
            if (stageWeightProportionService.save(stageWeightProportion) == false){
                return HttpResult.newErrorResult("该选项已存在");
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value="delete",name="删除阶段权重占比",method = RequestMethod.POST)
    public HttpResult delete(Integer entrustPurpose){
        try {
            stageWeightProportionService.delete(entrustPurpose);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }


    @RequestMapping(value="/edit", name="点编辑请求的数据")
    public ModelAndView edit(StageWeightProportion stageWeightProportion,ModelAndView modelAndView){
        List<StageWeightProportion> stageWeightProportions = stageWeightProportionService.edit(stageWeightProportion);
        modelAndView.addObject("stageWeightProportions",stageWeightProportions);
        modelAndView.setViewName("showEdit");
        return modelAndView;
    }

}
