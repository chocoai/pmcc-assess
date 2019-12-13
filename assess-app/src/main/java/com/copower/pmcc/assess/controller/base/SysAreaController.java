package com.copower.pmcc.assess.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.SysArea;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.SysAreaService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zch on 2019-12-13.
 * 地区 区域
 */
@RequestMapping(value = "/sysArea")
@RestController
public class SysAreaController {
    private static final String STRING = "地区 区域";
    @Autowired
    private BaseService baseService;
    @Autowired
    private SysAreaService sysAreaService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    /**
     * 页面视图
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/sysAreaView");
        return modelAndView;
    }

    @PostMapping(value = "/saveAndUpdateSysAreaAll", name = "save")
    public HttpResult saveAndUpdateSysAreaAll(String fomData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            List<SysArea> declareApplyExtensionList = JSONObject.parseArray(fomData, SysArea.class);
            if (CollectionUtils.isNotEmpty(declareApplyExtensionList)) {
                Iterator<SysArea> iterator = declareApplyExtensionList.iterator();
                while (iterator.hasNext()) {
                    sysAreaService.saveAndUpdateSysArea(iterator.next(), updateNull);
                }
            }
            return HttpResult.newCorrectResult(200, declareApplyExtensionList);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/saveAndUpdateSysArea", name = "save")
    public HttpResult saveAndUpdateSysArea(String fomData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            SysArea sysArea = JSONObject.parseObject(fomData, SysArea.class);
            sysAreaService.saveAndUpdateSysArea(sysArea, updateNull);
            return HttpResult.newCorrectResult(200, sysArea);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/deleteSysAreaById", name = "delete")
    public HttpResult deleteSysAreaById(String id) {
        try {
            sysAreaService.deleteSysAreaById(id);
            return HttpResult.newCorrectResult(200, "success");
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/deleteSysAreaByIdEnable", name = "delete 这个方法并不是真的删除，只是把数据设置为不启用")
    public HttpResult deleteSysAreaByIdEnable(String id) {
        try {
            // BusinessException 在这属于正常异常
            try {
                sysAreaService.deleteSysAreaByIdEnable(id);
                return HttpResult.newCorrectResult(200, "ok");
            } catch (BusinessException e) {
                return HttpResult.newCorrectResult(200, String.join("","error:",e.getMessage()));
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(500, e);
        }
    }


    @GetMapping(value = "/getSysAreaById", name = "get")
    public HttpResult getSysAreaById(String id) {
        try {
            List<Integer> ids = FormatUtils.transformString2Integer(id);
            if (ids.size() == 1) {
                return HttpResult.newCorrectResult(200, sysAreaService.getSysAreaById(ids.get(0)));
            } else {
                return HttpResult.newCorrectResult(200, sysAreaService.getSysAreaByIds(ids));
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getSysAreaListSelect", name = "get list")
    public HttpResult getSysAreaListByExample(SysArea oo) {
        try {
            if (oo == null) {
                oo = new SysArea();
            }
            return HttpResult.newCorrectResult(200, sysAreaService.getSysAreaListSelect(oo));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, String.join("", STRING, e.getMessage()));
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/getBootstrapTableVo", name = "get Pagination list")
    public BootstrapTableVo getBootstrapTableVo(SysArea oo) {
        return sysAreaService.getBootstrapTableVo(oo);
    }
}
