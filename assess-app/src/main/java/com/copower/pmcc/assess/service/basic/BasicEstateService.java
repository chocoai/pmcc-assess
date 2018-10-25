package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basic.dao.BasicEstateDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstate;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Ordering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicEstateService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicEstateDao basicEstateDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicEstate getBasicEstateById(Integer id) throws Exception {
        return basicEstateDao.getBasicEstateById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicEstate
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicEstate(BasicEstate basicEstate) throws Exception {
        if (basicEstate.getId() == null || basicEstate.getId().intValue() == 0) {
            basicEstate.setCreator(commonService.thisUserAccount());
            if (basicEstate.getVersion() == null) {
                basicEstate.setVersion(0);
            }
            return basicEstateDao.saveBasicEstate(basicEstate);
        } else {
            BasicEstate oo = basicEstateDao.getBasicEstateById(basicEstate.getId());
            basicEstate.setVersion(oo.getVersion() + 1);
            basicEstateDao.updateBasicEstate(basicEstate);
            return null;
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicEstate(Integer id) throws Exception {
        return basicEstateDao.deleteBasicEstate(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicEstate
     * @return
     * @throws Exception
     */
    public List<BasicEstate> basicEstateList(BasicEstate basicEstate) throws Exception {
        return basicEstateDao.basicEstateList(basicEstate);
    }

    public List<BasicEstate> autoComplete(BasicEstate basicEstate) throws Exception {
        List<BasicEstate> basicEstates = basicEstateDao.autoComplete(basicEstate);
        Ordering<BasicEstate> ordering = Ordering.from(new Comparator<BasicEstate>() {
            @Override
            public int compare(BasicEstate o1, BasicEstate o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).reverse();
        Collections.sort(basicEstates, ordering);
        return basicEstateDao.autoComplete(basicEstate);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicEstate basicEstate) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstate> basicEstateList = basicEstateDao.basicEstateList(basicEstate);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicEstateList) ? new ArrayList<BasicEstate>(10) : basicEstateList);
        return vo;
    }

}
