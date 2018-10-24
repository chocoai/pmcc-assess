package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicEstateDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstate;
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
public class BasicEstateService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicEstateDao basicEstateDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     * @param id
     * @return
     * @throws Exception
     */
    public BasicEstate getBasicEstateById(Integer id)throws Exception{
        return basicEstateDao.getBasicEstateById(id);
    }

    /**
     * 新增或者修改
     * @param basicEstate
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicEstate(BasicEstate basicEstate)throws Exception{
        if (basicEstate.getId()== null || basicEstate.getId().intValue()==0){
            basicEstate.setCreator(commonService.thisUserAccount());
            if (basicEstate.getVersion() == null){
                basicEstate.setVersion(0);
            }
            return basicEstateDao.saveBasicEstate(basicEstate);
        }else {
            basicEstateDao.updateBasicEstate(basicEstate);
            if (basicEstate.getVersion() != null){
                basicEstate.setVersion(basicEstate.getVersion()+1);
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
    public boolean deleteBasicEstate(Integer id)throws Exception{
        return basicEstateDao.deleteBasicEstate(id);
    }

    /**
     * 获取数据列表
     * @param basicEstate
     * @return
     * @throws Exception
     */
    public List<BasicEstate> basicEstateList(BasicEstate basicEstate)throws Exception{
        return basicEstateDao.basicEstateList(basicEstate);
    }

    public List<BasicEstate> autoComplete(BasicEstate basicEstate)throws Exception{
        return basicEstateDao.autoComplete(basicEstate);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicEstate basicEstate)throws Exception{
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstate> basicEstateList = basicEstateDao.basicEstateList(basicEstate);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicEstateList)?new ArrayList<BasicEstate>(10):basicEstateList);
        return vo;
    }

}
