package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataCustomerFieldDao;
import com.copower.pmcc.assess.dal.basis.entity.DataCustomerField;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/6/12
 * @time: 11:16
 */
@Service
public class DataCustomerFieldService {
    @Autowired
    private DataCustomerFieldDao dataCustomerFieldDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    
    //保存信息
    public DataCustomerField saveDataCustomerField(DataCustomerField customerField){
        if (customerField.getId() != null && customerField.getId() > 0) {
            dataCustomerFieldDao.editDataCustomerField(customerField);
        } else {
            customerField.setCreator(processControllerComponent.getThisUser());

            dataCustomerFieldDao.addDataCustomerField(customerField);
        }

        return customerField;
    }


    //删除一条信息
    public void deleteDataCustomerField(Integer id) throws BusinessException{
        dataCustomerFieldDao.deleteDataCustomerField(id);

    }

    /**
     * 获取列表
     *
     * @return
     */
    public BootstrapTableVo getDataCustomerFieldList(String queryName) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataCustomerField> list = dataCustomerFieldDao.getDataCustomerFieldList(queryName);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<DataCustomerField>() : list);
        return bootstrapTableVo;
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public DataCustomerField getCustomerFieldById(Integer id) throws Exception {
        return dataCustomerFieldDao.getDataCustomerField(id);
    }


    public List<DataCustomerField> getCustomerField(DataCustomerField dataCustomerField) throws Exception {
        return dataCustomerFieldDao.getObjectList(dataCustomerField);
    }

}
