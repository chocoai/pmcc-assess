package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingSurface;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseBuildingSurfaceService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 16:06
 * @Description:
 */
@RequestMapping(value = "/caseBuildingSurface")
@Controller
public class CaseBuildingSurfaceController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseBuildingSurfaceService caseBuildingSurfaceService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingSurfaceById",method = {RequestMethod.GET},name = "获取屋面结构")
    public HttpResult getById(Integer id) {
        CaseBuildingSurface caseBuildingSurface = null;
        try {
            if (id!=null){
                caseBuildingSurface = caseBuildingSurfaceService.getCaseBuildingSurfaceById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseBuildingSurface);
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingSurfaceList",method = {RequestMethod.GET},name = "屋面结构列表")
    public BootstrapTableVo getCaseBuildingSurfaceList( Integer buildingId, String buildNumber) {
        BootstrapTableVo vo = null;
        try {
            CaseBuildingSurface caseBuildingSurface = new CaseBuildingSurface();
            if (buildingId!=null ){
                caseBuildingSurface.setBuildingId(buildingId);
            }
            if (!StringUtils.isEmpty(buildNumber)){
                caseBuildingSurface.setBuildNumber(buildNumber);
            }
            vo = caseBuildingSurfaceService.getCaseBuildingSurfaceLists(caseBuildingSurface);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseBuildingSurfaceById",method = {RequestMethod.POST},name = "删除屋面结构")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(caseBuildingSurfaceService.deleteCaseBuildingSurface(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseBuildingSurface",method = {RequestMethod.POST},name = "更新屋面结构")
    public HttpResult save(CaseBuildingSurface caseBuildingSurface){
        try {
            if (caseBuildingSurface.getId()==null || caseBuildingSurface.getId().equals(0)){
                caseBuildingSurfaceService.addCaseBuildingSurface(caseBuildingSurface);
            }else {
                caseBuildingSurfaceService.updateCaseBuildingSurface(caseBuildingSurface);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


}
