package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicEstateNetworkDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateNetwork;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
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
 * @Date: 2018/11/6 10:43
 * @Description:
 */
@Service
public class BasicEstateNetworkService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateNetworkDao basicEstateNetworkDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicEstateNetwork getBasicEstateNetworkById(Integer id) throws Exception {
        return basicEstateNetworkDao.getBasicEstateNetworkById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicEstateNetwork
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicEstateNetwork(BasicEstateNetwork basicEstateNetwork) throws Exception {
        if (basicEstateNetwork.getId() == null || basicEstateNetwork.getId().intValue() == 0) {
            basicEstateNetwork.setCreator(commonService.thisUserAccount());
            Integer id = basicEstateNetworkDao.saveBasicEstateNetwork(basicEstateNetwork);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class), id);
            return  id ;
        } else {
            BasicEstateNetwork oo = basicEstateNetworkDao.getBasicEstateNetworkById(basicEstateNetwork.getId());
            basicEstateNetworkDao.updateBasicEstateNetwork(basicEstateNetwork);
            return null;
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicEstateNetwork(Integer id) throws Exception {
        return basicEstateNetworkDao.deleteBasicEstateNetwork(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicEstateNetwork
     * @return
     * @throws Exception
     */
    public List<BasicEstateNetwork> basicEstateNetworkList(BasicEstateNetwork basicEstateNetwork) throws Exception {
        return basicEstateNetworkDao.basicEstateNetworkList(basicEstateNetwork);
    }

    public void removeBasicEstateNetwork(BasicEstateNetwork basicEstateNetwork)throws Exception{
        basicEstateNetworkDao.removeBasicEstateNetwork(basicEstateNetwork);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicEstateNetwork basicEstateNetwork) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstateNetwork> basicEstateNetworkList = basicEstateNetworkDao.basicEstateNetworkList(basicEstateNetwork);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicEstateNetworkList) ? new ArrayList<BasicEstateNetwork>(10) : basicEstateNetworkList);
        return vo;
    }


}
