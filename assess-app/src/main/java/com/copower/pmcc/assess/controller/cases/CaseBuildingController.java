package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnit;
import com.copower.pmcc.assess.dto.input.cases.CaseBuildingDto;
import com.copower.pmcc.assess.service.cases.CaseBuildingService;
import com.copower.pmcc.assess.service.cases.CaseUnitService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 18:02
 * @Description:
 */
@RequestMapping(value = "/caseBuilding")
@Controller
public class CaseBuildingController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CaseUnitService caseUnitService;
    @Autowired
    private CaseBuildingService caseBuildingService;

    @RequestMapping(value = "/appView", name = "转到新增页面 ", method = RequestMethod.GET)
    public ModelAndView appView(Integer estateId) {
        String view = "/case/caseBuild/apply/caseBuildingView";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        modelAndView.addObject("estateId", estateId);
        return modelAndView;
    }

    @RequestMapping(value = "/editView", name = "转到编辑页面 ", method = RequestMethod.GET)
    public ModelAndView editView(Integer id,@RequestParam(defaultValue = "false") boolean copy) {
        String view = "/case/caseBuild/apply/caseBuildingView";
        CaseBuilding caseBuilding = null;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        caseBuilding = caseBuildingService.getCaseBuildingById(id) ;
        if (copy){
            //复制数据 需要把id设为null
            caseBuilding.setId(null);
            //处理附件,所有附件则把附件复制后保存后的id传入页面显示
            //附件暂且不处理
        }
        modelAndView.addObject("caseBuilding",caseBuilding);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingById", method = {RequestMethod.GET}, name = "获取案例 楼栋")
    public HttpResult getCaseBuildingById(Integer id) {
        CaseBuilding caseBuilding = null;
        try {
            if (id != null) {
                caseBuilding = caseBuildingService.getCaseBuildingById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return HttpResult.newCorrectResult(caseBuilding);
    }

    @ResponseBody
    @RequestMapping(value = "/listCaseBuilding", method = {RequestMethod.GET}, name = "获取案例 楼栋")
    public HttpResult list(Integer caseBuildingMainId,Integer estateId) {
        CaseBuilding caseBuilding = new CaseBuilding();
        try {
            if (caseBuildingMainId != null) {
                caseBuilding.setCaseBuildingMainId(caseBuildingMainId);
            }
            if (estateId != null){
                caseBuilding.setEstateId(estateId);
            }
            List<CaseBuilding> caseBuildingList = caseBuildingService.getCaseBuildingList(caseBuilding);
            return HttpResult.newCorrectResult(caseBuildingList);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getCaseBuildingList", method = {RequestMethod.GET}, name = "获取案例 楼栋列表")
    public BootstrapTableVo getCaseBuildingList(Integer estateId) {
        CaseBuilding caseBuilding = new CaseBuilding();
        BootstrapTableVo vo = new BootstrapTableVo();
        try {
            if (estateId != null) {
                caseBuilding.setEstateId(estateId);
                vo = caseBuildingService.getCaseBuildingListVos(caseBuilding);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCaseBuildingById", method = {RequestMethod.POST}, name = "删除案例 楼栋")
    public HttpResult deleteCaseBuildingById(Integer id) {
        List<CaseUnit> caseUnitList = null;
        CaseUnit caseUnit = new CaseUnit();
        try {
            if (id != null) {
                caseUnit.setBuildingId(id);
                caseUnitList = caseUnitService.getCaseUnitList(caseUnit);
                if (caseUnitList.size() >= 1){
                    return HttpResult.newErrorResult("请删除此楼栋下的单元之后在删除此楼栋! remove fail");
                }
                Integer estateId = null;
                estateId = caseBuildingService.getCaseBuildingById(id).getEstateId();
                caseBuildingService.deleteCaseBuilding(id);
                return HttpResult.newCorrectResult(estateId);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateCaseBuilding", method = {RequestMethod.POST}, name = "更新案例 楼栋")
    public HttpResult saveAndUpdateCaseBuilding(CaseBuildingDto caseBuildingDto) {
        CaseBuilding caseBuilding = new CaseBuilding();
        BeanUtils.copyProperties(caseBuildingDto,caseBuilding);
        try {
            caseBuildingService.saveAndUpdateCaseBuilding(caseBuilding);
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/initAndUpdateSon", method = {RequestMethod.POST}, name = "初始化子类")
    public HttpResult initAndUpdateSon() {
        try {
            caseBuildingService.initAndUpdateSon(0,null);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            return HttpResult.newErrorResult("异常");
        }
    }


}
