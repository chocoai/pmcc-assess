package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataDeveloperDao;
import com.copower.pmcc.assess.dal.basis.entity.DataDeveloper;
import com.copower.pmcc.assess.dto.output.data.DataDeveloperVo;
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
 * @Date: 2018/7/18 18:36
 * @Description:开发商
 */
@Service
public class DataDeveloperService {
    @Autowired
    private DataDeveloperDao dataDeveloperDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CrmRpcBaseDataDicService crmRpcBaseDataDicService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public boolean addDataDeveloper(DataDeveloper dataDeveloper) {
        dataDeveloper.setCreator(commonService.thisUserAccount());
        return dataDeveloperDao.addDataDeveloper(dataDeveloper);
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public int addDataDeveloperReturnId(DataDeveloper dataDeveloper) {
        dataDeveloper.setCreator(commonService.thisUserAccount());
        return dataDeveloperDao.addDataDeveloperReturnId(dataDeveloper);
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public List<DataDeveloper> getDataDeveloperList(String name) {
        return dataDeveloperDao.getDataDeveloperList(name);
    }

    public List<DataDeveloper> dataDeveloperList(DataDeveloper oo) {
        return dataDeveloperDao.dataDeveloperList(oo);
    }

    public BootstrapTableVo getListVos(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataDeveloper> list = getDataDeveloperList(name);
        List<DataDeveloperVo> vos = LangUtils.transform(list, o -> getDataDeveloperVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<DataDeveloper>() : vos);
        return vo;
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public boolean deleteDataDeveloper(Integer id) {
        return dataDeveloperDao.deleteDataDeveloper(id);
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public DataDeveloper getByDataDeveloperId(Integer id) {
        return dataDeveloperDao.getByDataDeveloperId(id);
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 18:25
     */
    public boolean updateDataDeveloper(DataDeveloper dataDeveloper) {
        return dataDeveloperDao.updateDataDeveloper(dataDeveloper);
    }


    public DataDeveloperVo getDataDeveloperVo(DataDeveloper dataDeveloper) {
        DataDeveloperVo dataDeveloperVo = new DataDeveloperVo();
        BeanUtils.copyProperties(dataDeveloper, dataDeveloperVo);
        if (dataDeveloper.getCompanyNature() != null) {
            try {
                dataDeveloperVo.setCompanyNatureName(crmRpcBaseDataDicService.getBaseDataDic(dataDeveloper.getCompanyNature()).getName());
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
            }
        }
        if (dataDeveloper.getSocialPrestige() != null) {
            dataDeveloperVo.setSocialPrestigeName(baseDataDicService.getNameById(dataDeveloper.getSocialPrestige()));
        }
        return dataDeveloperVo;
    }
}
