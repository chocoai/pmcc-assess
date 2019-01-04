package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.BasicApplyFormNameEnum;
import com.copower.pmcc.assess.common.enums.ExamineHouseEquipmentTypeEnum;
import com.copower.pmcc.assess.dal.basic.dao.BasicHouseDamagedDegreeDetailDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouse;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseDamagedDegree;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseDamagedDegreeDetail;
import com.copower.pmcc.assess.dal.basis.entity.DataDamagedDegree;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseDamagedDegreeDetailVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseDamagedDegreeVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseWaterDrainVo;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.data.DataDamagedDegreeService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 18:16
 * @Description:
 */
@RequestMapping(value = "/basicHousePrint")
@Controller
public class BasicHousePrintController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BasicHouseService basicHouseService;
    @Autowired
    private DataDamagedDegreeService dataDamagedDegreeService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicHouseFaceStreetService basicHouseFaceStreetService;
    @Autowired
    private BasicHouseIntelligentService basicHouseIntelligentService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private BasicHouseWaterService basicHouseWaterService;
    @Autowired
    private BasicHouseEquipmentService basicHouseEquipmentService;
    @Autowired
    private BasicHouseWaterDrainService basicHouseWaterDrainService;
    @Autowired
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private BasicHouseDamagedDegreeDetailDao basicHouseDamagedDegreeDetailDao;


    @RequestMapping(value = "/printedPage", name = "转到详情页面 ", method = RequestMethod.GET)
    public ModelAndView detailView(Integer id) throws Exception {
        String view = "/basic/printedPage/houseDetail";
        BasicHouse basicHouse = null;
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        basicHouse = basicHouseService.getBasicHouseById(id);
        if (id == null) {
            return modelAndView;
        }
        modelAndView.addObject("caseHouse", basicHouseService.getBasicHouseVo(basicHouse));
        modelAndView.addObject("hasHouseFaceStreetData", basicHouseFaceStreetService.hasHouseFaceStreetData(id));
        modelAndView.addObject("hasHouseIntelligentData", basicHouseIntelligentService.hasHouseIntelligentData(id));
        modelAndView.addObject("hasHouseRoomData", basicHouseRoomService.hasHouseRoomData(id));
        modelAndView.addObject("hasHouseWaterData", basicHouseWaterService.hasHouseWaterData(id));
        modelAndView.addObject("hasHouseWaterDrainData", basicHouseWaterDrainService.hasHouseWaterDrainData(id));
        modelAndView.addObject("hasHouseEquipmentAirConditioner", basicHouseEquipmentService.hasHouseEquipmentData(id, ExamineHouseEquipmentTypeEnum.houseAirConditioner.getKey()));
        modelAndView.addObject("hasHouseEquipmentHeating", basicHouseEquipmentService.hasHouseEquipmentData(id, ExamineHouseEquipmentTypeEnum.houseHeating.getKey()));
        modelAndView.addObject("hasHouseEquipmentNewWind", basicHouseEquipmentService.hasHouseEquipmentData(id, ExamineHouseEquipmentTypeEnum.houseNewWind.getKey()));
        modelAndView.addObject("hasStructuralPortion", basicHouseDamagedDegreeService.hasHouseDamagedDegreeData(id, "structural.part"));
        modelAndView.addObject("hasFitmentPortion", basicHouseDamagedDegreeService.hasHouseDamagedDegreeData(id, "decoration.part"));
        modelAndView.addObject("hasEquipmentPortion", basicHouseDamagedDegreeService.hasHouseDamagedDegreeData(id, "equipment.part"));
        modelAndView.addObject("hasOtherPortion", basicHouseDamagedDegreeService.hasHouseDamagedDegreeData(id, "other"));

        modelAndView.addObject("hasStructuralElementDetail", basicHouseDamagedDegreeService.hasOppositeDetail(id, 1));
        modelAndView.addObject("hasNonbearingWallDetail", basicHouseDamagedDegreeService.hasOppositeDetail(id, 2));
        modelAndView.addObject("hasRoofDetail", basicHouseDamagedDegreeService.hasOppositeDetail(id, 3));
        modelAndView.addObject("hasFlooringDetail", basicHouseDamagedDegreeService.hasOppositeDetail(id, 4));
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/getBasicHouseFaceStreetByHouseId", name = "结果临街信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getBasicHouseFaceStreetByHouseId(Integer houseId) throws Exception {
        return basicHouseFaceStreetService.getBootstrapTableVo(houseId);
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseIntelligentByHouseId", name = "电力通讯网络信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getBasicHouseIntelligentByHouseId(Integer houseId) throws Exception {
        return basicHouseIntelligentService.getBootstrapTableVo(houseId);
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseRoomByHouseId", name = "房间信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getBasicHouseRoomByHouseId(Integer houseId) throws Exception {
        return basicHouseRoomService.getBootstrapTableVo(houseId);
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseWaterByHouseId", name = "供水情况信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getBasicHouseWaterByHouseId(Integer houseId) throws Exception {
        return basicHouseWaterService.getBootstrapTableVo(houseId);
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseEquipmentByHouseId", name = "空调情况信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getBasicHouseEquipmentByHouseId(Integer houseId, String type) throws Exception {
        return basicHouseEquipmentService.getBootstrapTableVo(houseId, type);
    }

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseWaterDrainByHouseId", name = "排水情况信息列表", method = RequestMethod.GET)
    public BootstrapTableVo getBasicHouseWaterDrainByHouseId(Integer houseId) throws Exception {
        return basicHouseWaterDrainService.getBootstrapTableVo(houseId);

    }

    @ResponseBody
    @RequestMapping(value = "/saveData", method = {RequestMethod.POST}, name = "保存数据")
    public HttpResult saveData(String formData) {
        try {
            JSONObject jsonObject = JSON.parseObject(formData);
            String jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_HOUSE.getVar());
            BasicHouse basicHouse = JSONObject.parseObject(jsonContent, BasicHouse.class);
            if (basicHouse != null) {
                basicHouseService.saveAndUpdateBasicHouse(basicHouse);
                jsonContent = jsonObject.getString(BasicApplyFormNameEnum.BASIC_DAMAGED_DEGREE.getVar());
                List<BasicHouseDamagedDegree> damagedDegreeList = JSONObject.parseArray(jsonContent, BasicHouseDamagedDegree.class);
                if (!org.springframework.util.CollectionUtils.isEmpty(damagedDegreeList)) {
                    for (BasicHouseDamagedDegree degree : damagedDegreeList) {
                        basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(degree);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("保存异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();

    }

    @ResponseBody
    @RequestMapping(value = "/getHouseDamagedDegreeList", name = "房屋完损度部分", method = RequestMethod.GET)
    public BootstrapTableVo getHouseDamagedDegreeList(Integer houseId, String type) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        requestBaseParam.setLimit(100);
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        DataDamagedDegree degree = dataDamagedDegreeService.getCacheDamagedDegreeByFieldName(type);
        List<BasicHouseDamagedDegreeVo> list = basicHouseDamagedDegreeService.getDamagedDegreeVoList(houseId, degree.getId());
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(list) ? new ArrayList<BasicHouseWaterDrainVo>(10) : list);
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/getHouseDamagedDegreeDetailList", name = "结构明细部分", method = RequestMethod.GET)
    public BootstrapTableVo getHouseDamagedDegreeDetailList(Integer houseId, Integer index) throws Exception {
        List<BasicHouseDamagedDegree> damagedDegreeList = basicHouseDamagedDegreeService.getDamagedDegreeList(houseId);
        Integer id = damagedDegreeList.get(index).getId();
        return getHouseDamagedDegreeDetailList(id);
    }

    public BootstrapTableVo getHouseDamagedDegreeDetailList(Integer damagedDegreeId) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        requestBaseParam.setLimit(100);
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        BasicHouseDamagedDegreeDetail basicHouseDamagedDegreeDetail = new BasicHouseDamagedDegreeDetail();
        basicHouseDamagedDegreeDetail.setDamagedDegreeId(damagedDegreeId);
        List<BasicHouseDamagedDegreeDetail> degreeDetailList = basicHouseDamagedDegreeDetailDao.getDamagedDegreeDetailList(basicHouseDamagedDegreeDetail);
        List<BasicHouseDamagedDegreeDetailVo> vos = LangUtils.transform(degreeDetailList, o -> basicHouseDamagedDegreeService.getBasicHouseDamagedDegreeDetailVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? Lists.newArrayList() : vos);
        return vo;
    }
}
