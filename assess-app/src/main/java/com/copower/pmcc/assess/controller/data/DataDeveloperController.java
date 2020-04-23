package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataDeveloper;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataDeveloperService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.crm.api.dto.CrmBaseDataDicDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/18 18:56
 * @Description:开发商
 */
@Controller
@RequestMapping(value = "/dataDeveloper")
public class DataDeveloperController {

    @Autowired
    private DataDeveloperService dataDeveloperService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @RequestMapping(value = "/view", name = "转到index页面 ",method = {RequestMethod.GET})
    public ModelAndView index() {
//        String view = "/data/dataDeveloperView" ;
        String view = "/base/drawPolygon" ;
//        String view = "/base/drawPolygonDetail" ;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        List<CrmBaseDataDicDto> unitPropertiesList = projectInfoService.getUnitPropertiesList();
        modelAndView.addObject("unitPropertiesList", unitPropertiesList);
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_COMPANY_REPUTATION);
        modelAndView.addObject("reputations", baseDataDic);
        //;

        modelAndView.addObject("formData", "[{\"path\":[[116.40259,39.921129],[116.403571,39.92094],[116.403711,39.92117],[116.403679,39.921293]],\"extData\":{\"title\":[{\"id\":64,\"pid\":\"56\",\"lng\":\"116.403486\",\"lat\":\"39.921162\",\"name\":\"rjadjad\"}]}},{\"path\":[[116.404408,39.921026],[116.404639,39.920347],[116.405728,39.921104]],\"extData\":{\"title\":[{\"id\":67,\"pid\":\"61\",\"lng\":\"116.404837\",\"lat\":\"39.920845\",\"name\":\"sdjsdjdjs\"}]}}]");

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataDeveloperById",method = {RequestMethod.GET},name = "获取开发商")
    public HttpResult getById(Integer id) {
        DataDeveloper dataDeveloper = null;
        try {
            if (id!=null){
                dataDeveloper = dataDeveloperService.getByDataDeveloperId(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataDeveloper);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataDeveloperList",method = {RequestMethod.GET},name = "获取开发商列表")
    public BootstrapTableVo getExamineEstateNetworkList(String name) {
        BootstrapTableVo vo = null;
        try {
            vo = dataDeveloperService.getListVos(name);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataDeveloperById",method = {RequestMethod.POST},name = "删除开发商")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(dataDeveloperService.deleteDataDeveloper(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataDeveloper",method = {RequestMethod.POST},name = "更新开发商")
    public HttpResult saveAndUpdate(DataDeveloper dataDeveloper){
        try {
            if (dataDeveloper.getId()==null || dataDeveloper.getId().equals(0)){
                dataDeveloperService.addDataDeveloperReturnId(dataDeveloper);
            }else {
                dataDeveloperService.updateDataDeveloper(dataDeveloper);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }
}
