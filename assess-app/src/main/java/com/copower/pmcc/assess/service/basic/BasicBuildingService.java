package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicBuildingDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuilding;
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
public class BasicBuildingService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicBuildingDao basicBuildingDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicBuilding getBasicBuildingById(Integer id) throws Exception {
        return basicBuildingDao.getBasicBuildingById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicBuilding
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicBuilding(BasicBuilding basicBuilding) throws Exception {
        if (basicBuilding.getId() == null || basicBuilding.getId().intValue() == 0) {
            basicBuilding.setCreator(commonService.thisUserAccount());
            if (basicBuilding.getVersion() == null) {
                basicBuilding.setVersion(0);
            }
            return basicBuildingDao.saveBasicBuilding(basicBuilding);
        } else {
            BasicBuilding oo = basicBuildingDao.getBasicBuildingById(basicBuilding.getId());
            basicBuilding.setVersion(oo.getVersion() + 1);
            basicBuildingDao.updateBasicBuilding(basicBuilding);
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
    public boolean deleteBasicBuilding(Integer id) throws Exception {
        return basicBuildingDao.deleteBasicBuilding(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicBuilding
     * @return
     * @throws Exception
     */
    public List<BasicBuilding> basicBuildingList(BasicBuilding basicBuilding) throws Exception {
        return basicBuildingDao.basicBuildingList(basicBuilding);
    }

    public List<BasicBuilding> autoComplete(BasicBuilding basicBuilding) throws Exception {
        List<BasicBuilding> basicBuildingList = basicBuildingDao.autoComplete(basicBuilding);
        Ordering<BasicBuilding> ordering = Ordering.from(new Comparator<BasicBuilding>() {
            @Override
            public int compare(BasicBuilding o1, BasicBuilding o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).reverse();
        Collections.sort(basicBuildingList, ordering);
        return basicBuildingDao.autoComplete(basicBuilding);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicBuilding basicBuilding) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicBuilding> basicBuildingList = basicBuildingDao.basicBuildingList(basicBuilding);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicBuildingList) ? new ArrayList<BasicBuilding>(10) : basicBuildingList);
        return vo;
    }

}
