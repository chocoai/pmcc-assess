package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummary;
import com.copower.pmcc.assess.dto.input.basic.BasicFormClassifyParamDto;
import com.copower.pmcc.assess.dto.output.VersionDataVo;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseCaseSummaryVo;
import com.copower.pmcc.assess.proxy.face.BasicEntityAbstract;
import com.copower.pmcc.assess.service.basic.*;
import com.copower.pmcc.assess.service.project.survey.SurveyCommonService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kings on 2018-7-6.
 */
@Controller
@RequestMapping(value = "/basic", name = "案例 基础")
public class BasicController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BasicHouseCaseSummaryService basicHouseCaseSummaryService;
    @Autowired
    private BasicEstateService basicEstateService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private SurveyCommonService surveyCommonService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private PublicBasicService publicBasicService;

    @RequestMapping(value = "/areaCaseMap", name = "案例地图", method = {RequestMethod.GET})
    public ModelAndView areaEstateCaseMap() {
        String view = "/case/areaCaseMap";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        try {
            modelAndView.addObject("mapList", JSON.toJSONString(basicEstateTaggingService.mapDtoList(null, BasicFormClassifyEnum.ESTATE.getKey())));
        } catch (Exception e1) {
            logger.error("区域楼盘案例获取经度和纬度出错!", e1);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/estateCaseMap", name = "区域楼盘案例-", method = {RequestMethod.GET})
    public ModelAndView estateCaseMap(Integer estateId) {
        String view = "/case/estateCaseMap";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        try {
            if (estateId != null) {
                modelAndView.addObject("caseEstate", basicEstateService.getBasicEstateById(estateId));
            }
        } catch (Exception e1) {
            logger.error("区域楼盘案例获取经度和纬度出错!", e1);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/estateSearch", name = "楼盘案例查询", method = {RequestMethod.GET})
    public ModelAndView estateSearch() {
        String view = "/case/estateSearch";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @RequestMapping(value = "/houseSearch", name = "房屋案例查询", method = {RequestMethod.GET})
    public ModelAndView houseSearch() {
        String view = "/case/houseSearch";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableCaseBaseHouseVo", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getBootstrapTableVo(BigDecimal areaStart, BigDecimal areaEnd, String tradingTimeStart, String tradingTimeEnd, BasicHouseCaseSummary basicHouseCaseSummary) {
        BootstrapTableVo vo = null;
        try {
            Date start = null;
            Date end = null;
            if (StringUtils.isNotBlank(tradingTimeStart)) {
                start = DateUtils.parse(tradingTimeStart);
            }
            if (StringUtils.isNotBlank(tradingTimeEnd)) {
                end = DateUtils.parse(tradingTimeEnd);
            }
            vo = basicHouseCaseSummaryService.getBootstrapTableVo(areaStart, areaEnd, start, end, basicHouseCaseSummary);
        } catch (Exception e1) {
            logger.error("", e1);
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/getDataById", method = {RequestMethod.GET}, name = "通过id获取信息")
    public HttpResult getDataById(Integer id) {
        try {
            if (id != null) {
                BasicHouseCaseSummaryVo vo = basicHouseCaseSummaryService.getBasicHouseCaseSummaryVo(basicHouseCaseSummaryService.getBasicHouseCaseSummaryById(id));
                return HttpResult.newCorrectResult(vo);
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/checkCaseDetail", name = "查看详情", method = RequestMethod.GET)
    public ModelAndView checkDetail(Integer estateId) {
        String view = "/case/caseDetail";
        ModelAndView modelAndView = processControllerComponent.baseModelAndView(view);
        try {
            BasicApplyBatch caseData = basicApplyBatchService.getBasicApplyBatchById(estateId);
            modelAndView.addObject("applyBatch", caseData);
            modelAndView.addObject("formClassifyList", basicApplyBatchService.getFormClassifyList());
            modelAndView.addObject("examineFormTypeList", surveyCommonService.getExamineFormTypeList());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/geBasicFormClassifyParamDto", method = {RequestMethod.GET}, name = "通过参数信息")
    public HttpResult geBasicFormClassifyParamDto(Integer detailId) {
        try {
            BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailService.getCacheBasicApplyBatchDetailById(detailId);
            if (basicApplyBatchDetail != null) {
                BasicApplyBatch applyBatch = basicApplyBatchService.getBasicApplyBatchById(basicApplyBatchDetail.getApplyBatchId());
                BasicFormClassifyParamDto basicFormClassifyParamDto = new BasicFormClassifyParamDto();
                basicFormClassifyParamDto.setFormClassify(applyBatch.getClassify());
                basicFormClassifyParamDto.setFormType(applyBatch.getType());
                if (StringUtils.isNotEmpty(basicApplyBatchDetail.getType())) {
                    basicFormClassifyParamDto.setTbType(basicApplyBatchDetail.getType());
                } else {
                    BasicFormClassifyEnum anEnum = BasicFormClassifyEnum.getEnumByTableName(basicApplyBatchDetail.getTableName());
                    basicFormClassifyParamDto.setTbType(anEnum.getKey());
                }
                basicFormClassifyParamDto.setTbId(basicApplyBatchDetail.getTableId());
                basicFormClassifyParamDto.setApplyBatchId(applyBatch.getId());
                return HttpResult.newCorrectResult(basicFormClassifyParamDto);
            }
        } catch (Exception e) {
            return HttpResult.newErrorResult(String.format("异常! %s", e.getMessage()));
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getVersionList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getVersionList(Integer applyBatchDetailId) {
        BootstrapTableVo vo = null;
        try {
            vo = new BootstrapTableVo();
            RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
            Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
            BasicApplyBatchDetail batchDetail = basicApplyBatchDetailService.getDataById(applyBatchDetailId);
            BasicEntityAbstract entityAbstract = publicBasicService.getServiceBeanByTableName(batchDetail.getTableName());
            List<Object> objects = entityAbstract.getBasicEntityListByBatchDetailId(applyBatchDetailId);
            List<VersionDataVo> vos = Lists.newArrayList();
            if (!CollectionUtils.isEmpty(objects)) {
                for (Object o : objects) {
                    VersionDataVo dataVo = new VersionDataVo();
                    dataVo.setId((Integer) entityAbstract.getProperty(o, "id"));
                    dataVo.setVersion(String.format("%s%s","版本", (Integer)entityAbstract.getProperty(o, "version")));
                    if (entityAbstract.getProperty(o, "fullName") != null) {
                        dataVo.setFullName((String) entityAbstract.getProperty(o, "fullName"));
                    } else {
                        dataVo.setFullName((String) entityAbstract.getProperty(o, "name"));
                    }
                    vos.add(dataVo);
                }
            }
            vo.setTotal(page.getTotal());
            vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<VersionDataVo>() : vos);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s", e1.getMessage()), e1);
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/quoteHouseCase", method = {RequestMethod.POST}, name = "引用数据")
    public HttpResult quoteHouseCase(Integer id, Integer projectId, Integer planDetailsId) {
        try {
            BasicApplyBatch basicApplyBatch = basicHouseCaseSummaryService.referenceDataById(id, projectId, planDetailsId);
            return HttpResult.newCorrectResult(basicApplyBatch);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult("引用数据异常!");
        }
    }
}
