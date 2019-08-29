package com.copower.pmcc.assess.controller.project.scheme;

import com.copower.pmcc.assess.controller.BaseController;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeSurePriceRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSurePriceRecord;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: huhao
 * @Date: 2018/9/11 10:08
 */
@RequestMapping(value = "/schemeSurePriceRecord")
@Controller
public class SchemeSurePriceRecordController extends BaseController {
    @Autowired
    private SchemeSurePriceRecordDao schemeSurePriceRecordDao;


    @ResponseBody
    @RequestMapping(value = "/getSchemeSurePriceRecordList", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getSchemeSurePriceRecordList(Integer planDetailsId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        SchemeSurePriceRecord schemeSurePriceRecord = new SchemeSurePriceRecord();
        schemeSurePriceRecord.setPlanDetailsId(planDetailsId);
        List<SchemeSurePriceRecord> schemeSurePriceRecordList = schemeSurePriceRecordDao.getSchemeSurePriceRecordList(schemeSurePriceRecord);
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(schemeSurePriceRecordList) ? new ArrayList<SchemeSurePriceRecord>() : schemeSurePriceRecordList);
        return vo;
    }


}
