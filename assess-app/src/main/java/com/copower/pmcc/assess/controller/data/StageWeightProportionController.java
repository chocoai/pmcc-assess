package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dal.basis.entity.DataStageWeightProportion;
import com.copower.pmcc.assess.dal.basis.entity.ProjectWorkStage;
import com.copower.pmcc.assess.dto.input.data.StageWeightProportionDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.data.StageWeightProportionService;
import com.copower.pmcc.assess.service.project.ProjectWorkStageService;
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
    @Autowired
    private ProjectWorkStageService projectWorkStageService;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    @RequestMapping(value="/Index",name="阶段权重占比视图")
    public ModelAndView index(){
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/data/stageWeightProportion");
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE);

        BaseProjectClassify baseProjectClassify = baseProjectClassifyService.getDefaultType();
        List<ProjectWorkStage> projectWorkStages = projectWorkStageService.queryWorkStageByClassIdAndTypeId(baseProjectClassify.getId(), true);
        modelAndView.addObject("entrustmentPurposeList",baseDataDics);
        modelAndView.addObject("stageList",projectWorkStages);
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
    public HttpResult save(StageWeightProportionDto stageWeightProportionDto){
        try {
            stageWeightProportionService.save(stageWeightProportionDto);
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


    @RequestMapping(value="/edit", name="点编辑请求的数据",method = RequestMethod.POST)
    @ResponseBody
    public List<DataStageWeightProportion> edit(DataStageWeightProportion stageWeightProportion){
        List<DataStageWeightProportion> stageWeightProportions = stageWeightProportionService.edit(stageWeightProportion);
        return stageWeightProportions;
    }

}
