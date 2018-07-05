package com.copower.pmcc.assess.controller;

import com.copower.pmcc.assess.dal.basis.dao.funi.FuniHousesDao;
import com.copower.pmcc.assess.dal.basis.dao.funi.FuniHousesMatingDao;
import com.copower.pmcc.assess.dal.basis.dao.funi.FuniHousesPropertyDao;
import com.copower.pmcc.assess.dal.basis.dao.funi.FuniHousesTypeDao;
import com.copower.pmcc.assess.dal.basis.entity.FuniHouses;
import com.copower.pmcc.assess.dal.basis.entity.FuniHousesMating;
import com.copower.pmcc.assess.dal.basis.entity.FuniHousesProperty;
import com.copower.pmcc.assess.dal.basis.entity.FuniHousesType;
import com.copower.pmcc.assess.service.FuniWebService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
    private FuniHousesPropertyDao funiHousesPropertyDao;
    @Autowired
    private FuniHousesTypeDao funiHousesTypeDao;
    @Autowired
    private FuniWebService funiWebService;
    @Autowired
    private FuniHousesMatingDao funiHousesMatingDao;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView homeMain() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/funiIndex");
        return modelAndView;
    }

    @RequestMapping(value = "/funiDetails", method = RequestMethod.GET)
    public ModelAndView funiDetails(Integer lpbh) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/funiDetails");

        FuniHouses funiHouses = funiHousesDao.getFuniHouses(lpbh);
        modelAndView.addObject("funiHouses", funiHouses);
        List<FuniHousesProperty> funiHousesPropertyList = funiHousesPropertyDao.getFuniHousesPropertyList(lpbh);
        modelAndView.addObject("funiHousesPropertyList", funiHousesPropertyList);
        FuniHousesMating funiHousesMating = funiHousesMatingDao.getFuniHousesMatingByLpbh(lpbh);
        modelAndView.addObject("funiHousesMating", funiHousesMating);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/getHousesList", name = "取得楼盘信息", method = RequestMethod.GET)
    public BootstrapTableVo getHousesList() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        String search = requestBaseParam.getSearch();
        if (StringUtils.isNotBlank(search)) {
            search = String.format("%%%s%%", search);
        }
        FuniHouses funiHouses = new FuniHouses();
        List<FuniHouses> funiHousesList = funiHousesDao.getFuniHousesList(funiHouses, search);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(funiHousesList) ? new ArrayList<FuniHouses>() : funiHousesList);
        return bootstrapTableVo;
    }

    @ResponseBody
    @RequestMapping(value = "/updateHouses", name = "更新楼盘信息", method = RequestMethod.POST)
    public HttpResult updateHouses(Integer page) {
        funiWebService.getFuniHousesList(1);
        FuniHouses funiHouses = new FuniHouses();
        funiHouses.setBisEdit(false);
        List<FuniHouses> funiHousesList = funiHousesDao.getFuniHousesList(funiHouses, "");
        for (FuniHouses item : funiHousesList) {
            item.setBisEdit(true);
            funiHousesDao.editFuniHouses(item);
            if (item.getFuniweb().contains("{COMMUNITY_ID}")) {
                continue;
            }
            if (item.getId() >= 1) {
                funiWebService.getFuniHousesDetails(item.getFuniweb(), item.getId());
                funiWebService.getFuniHousesType(item.getFuniweb(), item.getId());
            }
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getHousesProperty", name = "取得楼盘物业详情", method = RequestMethod.GET)
    public HttpResult getHousesProperty(Integer lpbh) {
        List<FuniHousesProperty> funiHousesPropertyList = funiHousesPropertyDao.getFuniHousesPropertyList(lpbh);
        return HttpResult.newCorrectResult(funiHousesPropertyList);
    }

    @ResponseBody
    @RequestMapping(value = "/getHousesType", name = "取得楼盘户型详情", method = RequestMethod.GET)
    public HttpResult getHousesType(Integer lpbh) {
        List<FuniHousesType> funiHousesTypeList = funiHousesTypeDao.getFuniHousesTypeList(lpbh);
        return HttpResult.newCorrectResult(funiHousesTypeList);
    }

    @ResponseBody
    @RequestMapping(value = "/updateHousesData", name = "更新楼盘信息", method = RequestMethod.POST)
    public HttpResult updateHousesData(Integer id, String xxType, String keys, String values) {
        try {
            funiWebService.updateHousesData(id, xxType, keys, values);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/newHouse", name = "保存新增案例", method = RequestMethod.POST)
    public HttpResult newHouse(FuniHouses funiHouses) {
        try {
            funiHouses.setLptp("/pmcc-basis/assets/lpt.jpg");
            funiHousesDao.addFuniHouses(funiHouses);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
    @ResponseBody
    @RequestMapping(value = "/newHxxx", name = "保存新增户型", method = RequestMethod.POST)
    public HttpResult newHxxx(FuniHousesType funiHousesType) {
        try {
            funiHousesType.setHxt("/pmcc-basis/assets/hxt.jpg");
            funiHousesTypeDao.addFuniHousesType(funiHousesType);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
    @ResponseBody
    @RequestMapping(value = "/newWyxx", name = "保存新增物业", method = RequestMethod.POST)
    public HttpResult newWyxx(FuniHousesProperty funiHousesProperty) {
        try {
            funiHousesPropertyDao.addFuniHousesProperty(funiHousesProperty);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
