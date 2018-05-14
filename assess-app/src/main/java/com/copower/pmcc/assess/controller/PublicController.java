package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kings on 2018-5-10.
 */
public class PublicController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ErpAreaService erpAreaService;

    @ResponseBody
    @RequestMapping(value = "/getAreaList", name = "获取区域信息", method = RequestMethod.POST)
    public Object getAreaList(String pid) {
        try {
            if (StringUtils.isNotBlank(pid)) {
                List<SysAreaDto> sysAreaDtos = erpAreaService.getAreaList(pid);
                return sysAreaDtos;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
