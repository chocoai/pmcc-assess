package com.copower.pmcc.assess.controller.data;

import com.copower.pmcc.assess.dto.input.data.InfrastructureDto;
import com.copower.pmcc.assess.dto.output.data.InfrastructureVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.data.DataInfrastructureService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author liuwei
 */
@RequestMapping(value = "/infrastructure")
@Controller
public class DataInfrastructureController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private DataInfrastructureService dataInfrastructureService;

    @Autowired
    private ErpAreaService erpAreaService;

    @RequestMapping(value = "/Index" , name = "发文单位查看")
    public ModelAndView index(){
       ModelAndView modelAndView =  processControllerComponent.baseModelAndView("/data/dataInfrastructure");
       //获取省
        List<SysAreaDto> provinceList = erpAreaService.getProvinceList();
        modelAndView.addObject("provinceList",provinceList);
        return  modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getAreaList",name = "获取市或者县",method = RequestMethod.POST)
    public Object getAreaList(String pid){
        List<SysAreaDto> areaList = erpAreaService.getAreaList(pid);
        return areaList;
    }

    @ResponseBody
    @RequestMapping(value = "/get",name = "get DataInfrastructure",method = RequestMethod.POST)
    public Object get(Integer id){
        try {
            InfrastructureVo vo = dataInfrastructureService.get(id);
            if (!ObjectUtils.isEmpty(vo))return vo;
        }catch (Exception e){
            logger.error("----------------->报错误啦!");
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getInfrastructure",name = "获取发文单位信息",method = RequestMethod.GET)
    public BootstrapTableVo getInfrastructure(@RequestParam(value = "name") String name){
        return dataInfrastructureService.getInfrastructure(name);
    }

    @ResponseBody
    @RequestMapping(value = "/addInfrastructure",name = "添加发文单位",method = RequestMethod.POST)
    public HttpResult addInfrastructure(InfrastructureDto infrastructure) {
        try {
            if (infrastructure.getId() != null && infrastructure.getId() != 0){
                dataInfrastructureService.editInfrastructure(infrastructure);
            } else {
                dataInfrastructureService.addInfrastructure(infrastructure);
            }
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/deleteInfrastructure",name = "删除发文单位",method = RequestMethod.POST)
    public HttpResult deleteInfrastructure(@RequestParam("id") Integer id){
        dataInfrastructureService.deleteInfrastructure(id);
        return HttpResult.newCorrectResult();
    }

}
