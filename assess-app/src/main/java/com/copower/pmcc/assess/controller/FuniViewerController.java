package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.dal.dao.funi.FuniHousesDao;
import com.copower.pmcc.assess.dal.entity.BaseProcess;
import com.copower.pmcc.assess.dal.entity.FuniHouses;
import com.copower.pmcc.assess.dto.output.BaseProcessVo;
import com.copower.pmcc.assess.service.FuniWebService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/12
 * @time: 17:30
 */
@Controller
@RequestMapping(value = "/funiViewer")
public class FuniViewerController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private FuniHousesDao funiHousesDao;
    @Autowired
    private FuniWebService funiWebService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView homeMain() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/funiIndex");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getHousesList", name = "取得楼盘信息", method = RequestMethod.GET)
    public BootstrapTableVo getHousesList() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        FuniHouses funiHouses = new FuniHouses();
        List<FuniHouses> funiHousesList = funiHousesDao.getFuniHousesList(funiHouses);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(funiHousesList) ? new ArrayList<FuniHouses>() : funiHousesList);
        return bootstrapTableVo;
    }

    @ResponseBody
    @RequestMapping(value = "/updateHouses", name = "更新楼盘信息", method = RequestMethod.POST)
    public HttpResult updateHouses(Integer page) {
      // funiWebService.getFuniHousesList(1);
        FuniHouses funiHouses = new FuniHouses();
        List<FuniHouses> funiHousesList = funiHousesDao.getFuniHousesList(funiHouses);
        for (FuniHouses item : funiHousesList) {
            funiWebService.getFuniHousesDetails(item.getFuniweb(), item.getId());
            funiWebService.getFuniHousesType(item.getFuniweb(), item.getId());
        }
        return HttpResult.newCorrectResult();
    }

}
