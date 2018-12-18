package com.copower.pmcc.assess.controller.assess;

import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateTagging;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateTagging;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.basic.BasicEstateTaggingService;
import com.copower.pmcc.assess.service.basic.BasicUnitService;
import com.copower.pmcc.assess.service.cases.CaseHouseService;
import com.copower.pmcc.assess.service.cases.CaseUnitService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by kings on 2018-5-17.
 */
@Controller
@RequestMapping(value = "/map", name = "高德地图相关处理")
public class MapController {
    @Autowired
    private BasicUnitService basicUnitService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private CaseUnitService caseUnitService;
    @Autowired
    private CaseHouseService caseHouseService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/positionPicker", name = "当前位置定位")
    public ModelAndView positionPicker() {
        ModelAndView modelAndView = new ModelAndView("/base/mapPositionPicker");
        return modelAndView;
    }

    @RequestMapping(value = "/estateTagging", name = "楼盘标注")
    public ModelAndView estateTagging() {
        ModelAndView modelAndView = new ModelAndView("base/estateTaggingView");
        return modelAndView;
    }

    @GetMapping(value = "/houseTagging", name = "房屋标注")
    public ModelAndView houseTagging(@RequestParam(defaultValue = "4506") String sysAttachmentId, Integer unitId, String click, @RequestParam(name = "case_", defaultValue = "false") Boolean case_) {
        ModelAndView modelAndView = new ModelAndView("base/houseTaggingView");
        if (NumberUtils.isNumber(sysAttachmentId)) {
            String huxingImg = baseAttachmentService.getViewImageUrl(Integer.parseInt(sysAttachmentId));
            modelAndView.addObject("huxingImg", huxingImg);
        }
        BasicEstateTagging tagging = null;
        try {
            //案例单元id tb_case_unit
            if (case_) {
                if (unitId != null) {
                    CaseEstateTagging caseEstateTagging = caseUnitService.getCaseEstateTaggingByUnitId(unitId);
                    if (caseEstateTagging != null) {
                        tagging = new BasicEstateTagging();
                        BeanUtils.copyProperties(caseEstateTagging, tagging);
                    }
                }
            }
            //非案例单元id tb_basic_unit
            if (!case_) {
                tagging = basicUnitService.getBasicEstateTagging(unitId);
            }
        } catch (Exception e1) {
            logger.error("", e1);
        }

        if (tagging == null) {
            tagging = new BasicEstateTagging();
            tagging.setLng("104.084335");
            tagging.setLat("30.590403");
            tagging.setDeg(0);
        } else {
            if (tagging.getDeg() == null) {
                tagging.setDeg(0);
            }
        }
        if (NumberUtils.isNumber(sysAttachmentId)) {
            tagging.setAttachmentId(Integer.parseInt(sysAttachmentId));
        }
        modelAndView.addObject("click", click);
        modelAndView.addObject("tagging", tagging);
        return modelAndView;
    }

    @GetMapping(value = "/houseTaggingMore", name = "房屋标注(重复打开页面的时候 以及只读标注信息)")
    public ModelAndView houseTaggingMore(BasicEstateTagging tagging, String click, @RequestParam(name = "readonly", defaultValue = "false") Boolean readonly, @RequestParam(name = "case_", defaultValue = "false") Boolean case_, Integer houseId) {
        ModelAndView modelAndView = null;
        if (!readonly) {
            //标记位置和方位
            modelAndView = new ModelAndView("base/houseTaggingView");
            modelAndView.addObject("click", click);
            modelAndView.addObject("tagging", tagging);
            if (tagging.getAttachmentId() == null) {
                //临时数据
                tagging.setAttachmentId(4506);
            }
            try {
                String huxingImg = baseAttachmentService.getViewImageUrl(tagging.getAttachmentId());
                modelAndView.addObject("huxingImg", huxingImg);
            } catch (Exception e1) {
                logger.error("附件不存在", e1);
            }
        } else {
            modelAndView = new ModelAndView("base/houseTaggingReadonlyView");
            //仅仅显示标记而已
            if (case_) {
                if (houseId != null) {
                    try {
                        CaseEstateTagging caseEstateTagging = caseHouseService.getCaseEstateTaggingByUnitId(houseId);
                        if (caseEstateTagging != null) {
                            tagging = new BasicEstateTagging();
                            BeanUtils.copyProperties(caseEstateTagging, tagging);
                            String huxingImg = baseAttachmentService.getViewImageUrl(tagging.getAttachmentId());
                            if (StringUtils.isNotBlank(huxingImg)) {
                                modelAndView.addObject("huxingImg", huxingImg);
                            }
                            modelAndView.addObject("tagging", tagging);
                        } else {
                            tagging = new BasicEstateTagging();
                            tagging.setLng("104.084335");
                            tagging.setLat("30.590403");
                            tagging.setDeg(0);
                            modelAndView.addObject("tagging", tagging);
                        }
                    } catch (Exception e1) {
                        logger.error("未获取到数据!", e1);
                    }
                }
            } else {
                try {
                    List<BasicEstateTagging> basicEstateTaggings = basicEstateTaggingService.getEstateTaggingList(tagging.getApplyId(), EstateTaggingTypeEnum.HOUSE.getKey());
                    if (!ObjectUtils.isEmpty(basicEstateTaggings)) {
                        BasicEstateTagging basicEstateTagging = basicEstateTaggings.get(0);
                        String huxingImg = baseAttachmentService.getViewImageUrl(basicEstateTagging.getAttachmentId());
                        if (StringUtils.isNotBlank(huxingImg)) {
                            modelAndView.addObject("huxingImg", huxingImg);
                        }
                        modelAndView.addObject("tagging", basicEstateTagging);
                    }
                } catch (Exception e1) {
                    logger.error("未获取到数据!", e1);
                }
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = "/mapMarkerEstate", name = "楼盘地图标注")
    public ModelAndView mapMarkerEstate(String estateName, String click) {
        ModelAndView modelAndView = new ModelAndView("base/mapMarkerEstate");
        modelAndView.addObject("estateName", estateName);
        modelAndView.addObject("click", click);
        return modelAndView;
    }
}
