package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicHouseDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouse;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicHouseService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseDao basicHouseDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     * @param id
     * @return
     * @throws Exception
     */
    public BasicHouse getBasicHouseById(Integer id)throws Exception{
        return basicHouseDao.getBasicHouseById(id);
    }

    /**
     * 新增或者修改
     * @param basicHouse
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouse(BasicHouse basicHouse)throws Exception{
        if (basicHouse.getId()== null || basicHouse.getId().intValue()==0){
            basicHouse.setCreator(commonService.thisUserAccount());
            if (basicHouse.getVersion() == null){
                basicHouse.setVersion(0);
            }
            return basicHouseDao.saveBasicHouse(basicHouse);
        }else {
            basicHouseDao.updateBasicHouse(basicHouse);
            if (basicHouse.getVersion() != null){
                basicHouse.setVersion(basicHouse.getVersion()+1);
            }
            return null;
        }
    }

    /**
     * 删除数据
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicHouse(Integer id)throws Exception{
        return basicHouseDao.deleteBasicHouse(id);
    }

    /**
     * 获取数据列表
     * @param basicHouse
     * @return
     * @throws Exception
     */
    public List<BasicHouse> basicHouseList(BasicHouse basicHouse)throws Exception{
        return basicHouseDao.basicHouseList(basicHouse);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouse basicHouse)throws Exception{
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouse> basicHouseList = basicHouseDao.basicHouseList(basicHouse);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicHouseList)?new ArrayList<BasicHouse>(10):basicHouseList);
        return vo;
    }

}
