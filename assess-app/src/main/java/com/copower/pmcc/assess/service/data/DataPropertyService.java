package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.data.DataPropertyDao;
import com.copower.pmcc.assess.dal.basis.entity.DataProperty;
import com.copower.pmcc.assess.dto.output.data.DataPropertyVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.crm.api.provider.CrmRpcBaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CrmRpcBaseDataDicService crmRpcBaseDataDicService;
    @Autowired
    private DataPropertyServiceItemService dataPropertyServiceItemService;
    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 保存数据
     *
     *
     */
    public void saveAndUpdate(DataProperty dataProperty) {
        if (dataProperty.getId() != null && dataProperty.getId() > 0) {
            dataPropertyDao.updateDataProperty(dataProperty);
        } else {
            dataProperty.setCreator(commonService.thisUserAccount());
            dataPropertyDao.addDataProperty(dataProperty);
            //修改子模板
            dataPropertyServiceItemService.templateItemToSetMasterId(dataProperty.getId());
        }
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
        List<DataProperty> list = getDataPropertyList(name);
        List<DataPropertyVo> vos = LangUtils.transform(list, o -> getDataPropertyVo(o));
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

    public List<DataProperty> dataPropertyList(DataProperty dataProperty){
        return dataPropertyDao.dataPropertyList(dataProperty);
    }

    public DataPropertyVo getDataPropertyVo(DataProperty dataProperty) {
        DataPropertyVo dataPropertyVo = new DataPropertyVo();
        BeanUtils.copyProperties(dataProperty, dataPropertyVo);
        if (dataProperty.getCompanyNature() != null) {
            try {
                //crm 未知错误  暂时这样处理
                dataPropertyVo.setCompanyNatureName(crmRpcBaseDataDicService.getBaseDataDic(dataProperty.getCompanyNature()).getName());
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
        }
        if (dataProperty.getSocialPrestige() != null) {
            dataPropertyVo.setSocialPrestigeName(baseDataDicService.getNameById(dataProperty.getSocialPrestige()));
        }
        return dataPropertyVo;
    }
}
