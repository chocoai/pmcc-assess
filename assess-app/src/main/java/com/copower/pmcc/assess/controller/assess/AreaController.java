package com.copower.pmcc.assess.controller.assess;

import com.copower.pmcc.assess.service.assess.AreaService;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kings on 2018-4-18.
 */
@Controller
@RequestMapping("/area")
public class AreaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AreaController.class);
    @Autowired
    private AreaService areaService;

    /**
     * 获取字典列表数据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAreaList")
    public HttpResult getAreaList(String pid) {
        try {
            List<SysAreaDto> areaList = areaService.getAreaList(pid);
            return HttpResult.newCorrectResult(areaList);
        } catch (Exception e) {
            LOGGER.error("获取区域数据异常", e);
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

}
