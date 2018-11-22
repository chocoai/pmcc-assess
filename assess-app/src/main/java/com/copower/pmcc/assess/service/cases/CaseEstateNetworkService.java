package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseEstateNetworkDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateNetwork;
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
 * @Date: 2018/9/14 16:47
 * @Description:通信网络
 */
@Service
public class CaseEstateNetworkService {
    @Autowired
    private CaseEstateNetworkDao caseEstateNetworkDao;
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 功能描述:
     *
     * @param: examineEstateNetwork
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:31
     */
    public boolean saveCaseEstateNetwork(CaseEstateNetwork examineEstateNetwork) {
        examineEstateNetwork.setCreator(commonService.thisUserAccount());
        return caseEstateNetworkDao.addEstateNetwork(examineEstateNetwork);
    }

    /**
     *
     * 功能描述:
     *
     * @param: examineEstateNetwork
     * @return: BootstrapTableVo
     * @auther: zch
     * @date: 2018/7/18 15:02
     */
    public BootstrapTableVo getCaseEstateNetworkList(CaseEstateNetwork examineEstateNetwork){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseEstateNetwork> vos = getEstateNetworkLists(examineEstateNetwork);
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseEstateNetwork>() : vos);
        return vo;
    }

    /**
     * 功能描述:
     *
     * @param: examineEstateNetwork
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:33
     */
    public boolean updateEstateNetwork(CaseEstateNetwork examineEstateNetwork) {
        return caseEstateNetworkDao.updateEstateNetwork(examineEstateNetwork);
    }

    /**
     * 功能描述:
     *
     * @param: id
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:34
     */
    public boolean deleteEstateNetwork(Integer id) {
        return caseEstateNetworkDao.deleteEstateNetwork(id);
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:36
     */
    public CaseEstateNetwork getEstateNetworkById(Integer id) {
        return caseEstateNetworkDao.getEstateNetworkById(id);
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:36
     */
    public List<CaseEstateNetwork> getEstateNetworkLists(CaseEstateNetwork examineEstateNetwork) {
        return caseEstateNetworkDao.getEstateNetworkList(examineEstateNetwork);
    }

    /**
     * 根据查询条件判断是否有数据
     * @param esteteId
     * @return
     */
    public boolean hasEstateNetworkData(Integer esteteId){
        return caseEstateNetworkDao.countByEstateId(esteteId)>0;
    }
}
