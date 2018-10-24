package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicHouseTradingDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseTrading;
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
public class BasicHouseTradingService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseTradingDao basicHouseTradingDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     * @param id
     * @return
     * @throws Exception
     */
    public BasicHouseTrading getBasicHouseTradingById(Integer id)throws Exception{
        return basicHouseTradingDao.getBasicHouseTradingById(id);
    }

    /**
     * 新增或者修改
     * @param basicHouseTrading
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouseTrading(BasicHouseTrading basicHouseTrading)throws Exception{
        if (basicHouseTrading.getId()== null || basicHouseTrading.getId().intValue()==0){
            basicHouseTrading.setCreator(commonService.thisUserAccount());
            if (basicHouseTrading.getVersion() == null){
                basicHouseTrading.setVersion(0);
            }
            return basicHouseTradingDao.saveBasicHouseTrading(basicHouseTrading);
        }else {
            basicHouseTradingDao.updateBasicHouseTrading(basicHouseTrading);
            if (basicHouseTrading.getVersion() != null){
                basicHouseTrading.setVersion(basicHouseTrading.getVersion()+1);
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
    public boolean deleteBasicHouseTrading(Integer id)throws Exception{
        return basicHouseTradingDao.deleteBasicHouseTrading(id);
    }

    /**
     * 获取数据列表
     * @param basicHouseTrading
     * @return
     * @throws Exception
     */
    public List<BasicHouseTrading> basicHouseTradingList(BasicHouseTrading basicHouseTrading)throws Exception{
        return basicHouseTradingDao.basicHouseTradingList(basicHouseTrading);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouseTrading basicHouseTrading)throws Exception{
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseTrading> basicHouseTradingList = basicHouseTradingDao.basicHouseTradingList(basicHouseTrading);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicHouseTradingList)?new ArrayList<BasicHouseTrading>(10):basicHouseTradingList);
        return vo;
    }

}
