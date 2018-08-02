package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingSurface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineBuildingSurfaceService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/2 15:45
 * @Description:层面结构
 */
@RequestMapping(value = "/examineBuildingSurface")
@Controller
public class ExamineBuildingSurfaceController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineBuildingSurfaceService examineBuildingSurfaceService;
    @Autowired
    private BaseDataDicService baseDataDicService;


    @ResponseBody
    @RequestMapping(value = "/getExamineBuildingSurfaceById",method = {RequestMethod.GET},name = "获取层面结构")
    public HttpResult getById(Integer id) {
        ExamineBuildingSurface examineBuildingSurface = null;
        try {
            if (id!=null){
                examineBuildingSurface = examineBuildingSurfaceService.getExamineBuildingSurfaceById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineBuildingSurface);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineBuildingSurfaceList",method = {RequestMethod.GET},name = "层面结构列表")
    public BootstrapTableVo getExamineBuildingSurfaceList(Integer examineType, Integer declareId,Integer buildingId) {
        BootstrapTableVo vo = null;
        try {
            ExamineBuildingSurface examineBuildingSurface = new ExamineBuildingSurface();
            if (!ObjectUtils.isEmpty(examineType)){
                examineBuildingSurface.setExamineType(examineType);
            }
            if (declareId!=null && declareId.equals(0)){
                examineBuildingSurface.setDeclareId(declareId);
            }
            if (buildingId!=null && buildingId.equals(0)){
                examineBuildingSurface.setBuildingId(buildingId);
            }
            vo = examineBuildingSurfaceService.getExamineBuildingSurfaceLists(examineBuildingSurface);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineBuildingSurfaceById",method = {RequestMethod.POST},name = "删除层面结构")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineBuildingSurfaceService.deleteExamineBuildingSurface(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineBuildingSurface",method = {RequestMethod.POST},name = "更新层面结构")
    public HttpResult save(ExamineBuildingSurface examineBuildingSurface){
        try {
            if (examineBuildingSurface.getId()==null || examineBuildingSurface.getId().equals(0)){
                examineBuildingSurfaceService.addExamineBuildingSurface(examineBuildingSurface);
            }else {
                examineBuildingSurfaceService.updateExamineBuildingSurface(examineBuildingSurface);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/examine_building_structure",method = {RequestMethod.GET},name = "层面结构")
    public HttpResult environment_type() {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.EXAMINE_BUILDING_STRUCTURE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }


    
}
