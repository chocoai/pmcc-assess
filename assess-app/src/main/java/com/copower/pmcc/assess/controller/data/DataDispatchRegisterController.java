package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.entity.DataDispatchRegister;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.data.DataDispatchRegisterService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 * @Auther: zch
 * @Date: 2018/9/11 10:08
 * @Description:发文登记表
 */
@RequestMapping(value = "/dataDispatchRegister")
@Controller
public class DataDispatchRegisterController extends BaseController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataDispatchRegisterService dataDispatchRegisterService;
    @Autowired
    private BaseService baseService;
    @Autowired
    private CommonService commonService;


    @RequestMapping(value = "/view", name = "转到index页面 ", method = {RequestMethod.GET})
    public ModelAndView index() {
        String view = "/data/dataDispatchRegisterView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);

        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataDispatchRegisterById", method = {RequestMethod.GET}, name = "获取发文登记表")
    public HttpResult getDataDispatchRegisterById(Integer id) {
        DataDispatchRegister dataDispatchRegister = null;
        try {
            if (id != null) {
                dataDispatchRegister = dataDispatchRegisterService.getDataDispatchRegisterById(id);
            }
        } catch (Exception e1) {
            log.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(dataDispatchRegister);
    }

    @ResponseBody
    @RequestMapping(value = "/getDataDispatchRegisterList", method = {RequestMethod.GET}, name = "获取发文登记表列表")
    public BootstrapTableVo getDataDispatchRegisterList() {
        DataDispatchRegister dataDispatchRegister = new DataDispatchRegister();
        BootstrapTableVo vo = null;
        try {
            vo = dataDispatchRegisterService.getDataDispatchRegisterListVos();
        } catch (Exception e1) {
            log.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDataDispatchRegisterById", method = {RequestMethod.POST}, name = "删除发文登记表")
    public HttpResult deleteDataDispatchRegisterById(Integer id) {
        try {
            if (id != null) {
                DataDispatchRegister dataDispatchRegister = new DataDispatchRegister();
                dataDispatchRegister.setId(id);
                dataDispatchRegisterService.removeDataDispatchRegister(dataDispatchRegister);
                return HttpResult.newCorrectResult();
            }
        } catch (Exception e1) {
            log.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateDataDispatchRegister", method = {RequestMethod.POST}, name = "更新发文登记表")
    public HttpResult saveAndUpdateDataDispatchRegister(DataDispatchRegister dataDispatchRegister) {
        try {
            dataDispatchRegister.setCreator(commonService.thisUser().getUserName());
            dataDispatchRegisterService.saveAndUpdateDataDispatchRegister(dataDispatchRegister);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            log.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/importData", name = "导入", method = RequestMethod.POST)
    public HttpResult importDataLand(HttpServletRequest request) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String str = dataDispatchRegisterService.importData(multipartFile);
            return HttpResult.newCorrectResult(str);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

}
