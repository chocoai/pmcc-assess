package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataPropertyDao;
import com.copower.pmcc.assess.dal.basis.entity.DataProperty;
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
 * @Date: 2018/7/18 18:40
 * @Description:物业
 */
@Service
public class DataPropertyService {

    @Autowired
    private DataPropertyDao dataPropertyDao;
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
    public boolean addDataProperty(DataProperty dataProperty){
        dataProperty.setCreator(commonService.thisUserAccount());
        if (dataProperty.getDeclareId()==null){
            dataProperty.setDeclareId(0);
        }
        if (dataProperty.getExamineType()==null){
            dataProperty.setExamineType(0);
        }
        return dataPropertyDao.addDataProperty(dataProperty);
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
    public int addDataPropertyReturnId(DataProperty dataProperty){
        dataProperty.setCreator(commonService.thisUserAccount());
        if (dataProperty.getDeclareId()==null){
            dataProperty.setDeclareId(0);
        }
        if (dataProperty.getExamineType()==null){
            dataProperty.setExamineType(0);
        }
        return dataPropertyDao.addDataPropertyReturnId(dataProperty);
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
    public List<DataProperty> getDataPropertyList(String name){
        return dataPropertyDao.getDataPropertyList(name);
    }

    public BootstrapTableVo getListVos(String name){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataProperty> vos = getDataPropertyList(name);
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<DataProperty>() : vos);
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
    public boolean deleteDataProperty(Integer id){
        return dataPropertyDao.deleteDataProperty(id);
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
    public DataProperty getByDataPropertyId(Integer id){
        return dataPropertyDao.getByDataPropertyId(id);
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
    public boolean updateDataProperty(DataProperty dataProperty){
        return dataPropertyDao.updateDataProperty(dataProperty);
    }
}
