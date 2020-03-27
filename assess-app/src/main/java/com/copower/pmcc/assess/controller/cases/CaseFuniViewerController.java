package com.copower.pmcc.assess.controller.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseFuniHousesDao;
import com.copower.pmcc.assess.dal.cases.dao.CaseFuniHousesMatingDao;
import com.copower.pmcc.assess.dal.cases.dao.CaseFuniHousesPropertyDao;
import com.copower.pmcc.assess.dal.cases.dao.CaseFuniHousesTypeDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHouses;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesMating;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesProperty;
import com.copower.pmcc.assess.dal.cases.entity.CaseFuniHousesType;
import com.copower.pmcc.assess.service.cases.CaseFuniWebService;
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
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/6/12
 * @time: 17:30
 */
@Controller
@RequestMapping(value = "/caseFuniViewer")
public class CaseFuniViewerController {
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private CaseFuniHousesDao caseFuniHousesDao;
    @Autowired
    private CaseFuniHousesPropertyDao caseFuniHousesPropertyDao;
    @Autowired
    private CaseFuniHousesTypeDao caseFuniHousesTypeDao;
    @Autowired
    private CaseFuniWebService caseFuniWebService;
    @Autowired
    private CaseFuniHousesMatingDao caseFuniHousesMatingDao;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView homeMain() {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/caseFuniIndex");
        return modelAndView;
    }

    @RequestMapping(value = "/caseFuniDetails", method = RequestMethod.GET)
    public ModelAndView caseFuniDetails(Integer lpbh) {
        ModelAndView modelAndView = processControllerComponent.baseModelAndView("/base/caseFuniDetails");

        CaseFuniHouses caseFuniHouses = caseFuniHousesDao.getCaseFuniHouses(lpbh);
        modelAndView.addObject("caseFuniHouses", caseFuniHouses);
        List<CaseFuniHousesProperty> caseFuniHousesPropertyList = caseFuniHousesPropertyDao.getCaseFuniHousesPropertyList(lpbh);
        modelAndView.addObject("caseFuniHousesPropertyList", caseFuniHousesPropertyList);
        CaseFuniHousesMating caseFuniHousesMating = caseFuniHousesMatingDao.getCaseFuniHousesMatingByLpbh(lpbh);
        modelAndView.addObject("caseFuniHousesMating", caseFuniHousesMating);
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
        CaseFuniHouses caseFuniHouses = new CaseFuniHouses();
        List<CaseFuniHouses> caseFuniHousesList = caseFuniHousesDao.getCaseFuniHousesList(caseFuniHouses, search);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(caseFuniHousesList) ? new ArrayList<CaseFuniHouses>() : caseFuniHousesList);
        return bootstrapTableVo;
    }

    @ResponseBody
    @RequestMapping(value = "/updateHouses", name = "更新楼盘信息", method = RequestMethod.POST)
    public HttpResult updateHouses(Integer page) {
        caseFuniWebService.getCaseFuniHousesList(1);
        CaseFuniHouses caseFuniHouses = new CaseFuniHouses();
        caseFuniHouses.setBisEdit(false);
        List<CaseFuniHouses> caseFuniHousesList = caseFuniHousesDao.getCaseFuniHousesList(caseFuniHouses, "");
        for (CaseFuniHouses item : caseFuniHousesList) {
            item.setBisEdit(true);
            caseFuniHousesDao.editCaseFuniHouses(item);
            if (item.getFuniweb().contains("{COMMUNITY_ID}")) {
                continue;
            }
            if (item.getId() >= 1) {
                caseFuniWebService.getCaseFuniHousesDetails(item.getFuniweb(), item.getId());
                caseFuniWebService.getCaseFuniHousesType(item.getFuniweb(), item.getId());
            }
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getHousesProperty", name = "取得楼盘物业详情", method = RequestMethod.GET)
    public HttpResult getHousesProperty(Integer lpbh) {
        List<CaseFuniHousesProperty> caseFuniHousesPropertyList = caseFuniHousesPropertyDao.getCaseFuniHousesPropertyList(lpbh);
        return HttpResult.newCorrectResult(caseFuniHousesPropertyList);
    }

    @ResponseBody
    @RequestMapping(value = "/getHousesType", name = "取得楼盘户型详情", method = RequestMethod.GET)
    public HttpResult getHousesType(Integer lpbh) {
        List<CaseFuniHousesType> caseFuniHousesTypeList = caseFuniHousesTypeDao.getCaseFuniHousesTypeList(lpbh);
        return HttpResult.newCorrectResult(caseFuniHousesTypeList);
    }

    @ResponseBody
    @RequestMapping(value = "/updateHousesData", name = "更新楼盘信息", method = RequestMethod.POST)
    public HttpResult updateHousesData(Integer id, String xxType, String keys, String values) {
        try {
            caseFuniWebService.updateHousesData(id, xxType, keys, values);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/newHouse", name = "保存新增案例", method = RequestMethod.POST)
    public HttpResult newHouse(CaseFuniHouses caseFuniHouses) {
        try {
            caseFuniHouses.setLptp("/pmcc-basis/assets/lpt.jpg");
            caseFuniHousesDao.addCaseFuniHouses(caseFuniHouses);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/newHxxx", name = "保存新增户型", method = RequestMethod.POST)
    public HttpResult newHxxx(CaseFuniHousesType caseFuniHousesType) {
        try {
            caseFuniHousesType.setHxt("/pmcc-basis/assets/hxt.jpg");
            caseFuniHousesTypeDao.addCaseFuniHousesType(caseFuniHousesType);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/newWyxx", name = "保存新增物业", method = RequestMethod.POST)
    public HttpResult newWyxx(CaseFuniHousesProperty caseFuniHousesProperty) {
        try {
            caseFuniHousesPropertyDao.addCaseFuniHousesProperty(caseFuniHousesProperty);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }
}
