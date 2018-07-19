package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataBuilderDao;
import com.copower.pmcc.assess.dal.basis.entity.DataBuilder;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/18 18:38
 * @Description:建造商
 */
@Service
public class DataBuilderService {

    @Autowired
    private DataBuilderDao dataBuilderDao;
    @Autowired
    private CommonService commonService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public boolean addDataBuilder(DataBuilder dataBuilder){
        dataBuilder.setCreator(commonService.thisUserAccount());
        return dataBuilderDao.addDataBuilder(dataBuilder);
    }

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public int addDataBuilderReturnId(DataBuilder dataBuilder){
        dataBuilder.setCreator(commonService.thisUserAccount());
        return dataBuilderDao.addDataBuilderReturnId(dataBuilder);
    }

    public BootstrapTableVo getListVos(String name){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataBuilder> vos = getDataBuilderList(name);
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<DataBuilder>() : vos);
        return vo;
    }

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public List<DataBuilder> getDataBuilderList(String name){
        return dataBuilderDao.getDataBuilderList(name);
    }

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public boolean deleteDataBuilder(Integer id){
        return  dataBuilderDao.deleteDataBuilder(id);
    }

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public DataBuilder getByDataBuilderId(Integer id){
        return dataBuilderDao.getByDataBuilderId(id);
    }

    /**
     *
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public boolean updateDataBuilder(DataBuilder dataBuilder){
        return dataBuilderDao.updateDataBuilder(dataBuilder);
    }
}
