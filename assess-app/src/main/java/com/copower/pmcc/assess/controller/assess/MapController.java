package com.copower.pmcc.assess.controller.assess;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateTaggingVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.basic.BasicEstateTaggingService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.google.common.base.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BaseService baseService;

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

    @RequestMapping(value = "/landTagging", name = "土地标注画区块", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView landTagging(String readonly, Integer applyId) {
        ModelAndView modelAndView = new ModelAndView("base/landTaggingView");
        List<BasicEstateTaggingVo> taggingVoList = basicEstateTaggingService.getEstateTaggingList(applyId, BasicFormClassifyEnum.ESTATE.getKey());
        if (CollectionUtils.isNotEmpty(taggingVoList)) {
            if (StringUtils.isNotEmpty(taggingVoList.stream().findFirst().get().getPathArray())) {
                modelAndView.addObject("pathArray", taggingVoList.stream().findFirst().get().getPathArray());
            }
        }
        if (StringUtils.isBlank(readonly) || Objects.equal(readonly, "undefined")) {
            modelAndView.addObject("readonly", false);
        } else {
            modelAndView.addObject("readonly", readonly);
        }
        return modelAndView;
    }

    @GetMapping(value = "/houseTagging", name = "房屋标注")
    public ModelAndView houseTagging(Integer attachmentId, String lng, String lat, String deg, Boolean readonly) {
        ModelAndView modelAndView = new ModelAndView("base/houseTaggingView");
        if (attachmentId != null) {
            try {
                String huxingImg = baseAttachmentService.getViewImageUrl(attachmentId);
                if (StringUtils.isNotEmpty(huxingImg)) {
                    modelAndView.addObject("huxingImg", huxingImg);
                    modelAndView.addObject("attachmentId", attachmentId);
                }
            } catch (Exception e) {
                baseService.writeExceptionInfo(e, "房屋标注 附件可能被删除了");
            }
        }
        modelAndView.addObject("lng", StringUtils.isEmpty(lng) ? "" : lng);
        modelAndView.addObject("lat", StringUtils.isEmpty(lat) ? "" : lat);
        modelAndView.addObject("deg", StringUtils.isEmpty(deg) ? "" : deg);
        modelAndView.addObject("readonly", readonly == null ? true : readonly);
        return modelAndView;
    }

    @RequestMapping(value = "/mapMarkerEstate", name = "楼盘地图标注")
    public ModelAndView mapMarkerEstate(String estateName, String click, String lng, String lat) {
        ModelAndView modelAndView = new ModelAndView("base/mapMarkerEstate");
        modelAndView.addObject("estateName", estateName);
        modelAndView.addObject("click", click);
        modelAndView.addObject("center", JSONObject.toJSONString(new KeyValueDto(lng, lat)));
        return modelAndView;
    }

    @RequestMapping(value = "/drawPolygonMap", name = "地图多边形标注")
    public ModelAndView drawPolygonMap(@RequestParam(defaultValue = "false") boolean apply, @RequestParam(defaultValue = "false") boolean detail, String formData) {
        ModelAndView modelAndView = new ModelAndView("base/drawPolygon");
        if (apply) {
            modelAndView.addObject("apply", "apply");
        }
        if (detail) {
            modelAndView.addObject("detail", "detail");
        }
        if (StringUtils.isNotBlank(formData)){
            modelAndView.addObject("formData", "formData");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/mapMarkerEstateByTableId", name = "楼盘地图标注")
    public ModelAndView mapMarkerEstateByTableId(Integer tableId, String tableName, String click) {
        ModelAndView modelAndView = new ModelAndView("base/mapMarkerEstate");
        BasicApplyBatch applyBatch = null;
        if ("tb_basic_estate".equals(tableName)) {
            applyBatch = basicApplyBatchService.getBasicApplyBatchByEstateId(tableId);
        } else {
            BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailService.getBasicApplyBatchDetail(tableName, tableId);
            applyBatch = basicApplyBatchService.getBasicApplyBatchById(basicApplyBatchDetail.getApplyBatchId());
        }
        modelAndView.addObject("estateName", applyBatch.getEstateName());
        modelAndView.addObject("click", click);
        return modelAndView;
    }
}
