package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineEstateNetworkDao;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateNetwork;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 通信网络
 * @Auther: zch
 * @Date: 2018/7/18 14:13
 * @Description:
 */

@Service
public class ExamineEstateNetworkService {
    @Autowired
    private CommonService commonService;

    @Autowired
    private ExamineEstateNetworkDao examineEstateNetworkDao;

    /**
     * 功能描述:
     *
     * @param: examineEstateNetwork
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:31
     */
    @Transactional
    public boolean saveExamineEstateNetwork(ExamineEstateNetwork examineEstateNetwork) {
        examineEstateNetwork.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineEstateNetwork.getDeclareId())){
            examineEstateNetwork.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineEstateNetwork.getExamineType())){
            examineEstateNetwork.setExamineType(0);
        }
        return examineEstateNetworkDao.addEstateNetwork(examineEstateNetwork);
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
    public BootstrapTableVo getExamineEstateNetworkList(ExamineEstateNetwork examineEstateNetwork){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineEstateNetwork> vos = getEstateNetworkLists(examineEstateNetwork);
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineEstateNetwork>() : vos);
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
    @Transactional
    public boolean updateEstateNetwork(ExamineEstateNetwork examineEstateNetwork) {
        return examineEstateNetworkDao.updateEstateNetwork(examineEstateNetwork);
    }

    /**
     * 功能描述:
     *
     * @param: id
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:34
     */
    @Transactional
    public boolean deleteEstateNetwork(Integer id) {
        return examineEstateNetworkDao.deleteEstateNetwork(id);
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:36
     */
    public ExamineEstateNetwork getEstateNetworkById(Integer id) {
        return examineEstateNetworkDao.getEstateNetworkById(id);
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:36
     */
    public List<ExamineEstateNetwork> getEstateNetworkLists(ExamineEstateNetwork examineEstateNetwork) {
        return examineEstateNetworkDao.getEstateNetworkList(examineEstateNetwork);
    }
}
