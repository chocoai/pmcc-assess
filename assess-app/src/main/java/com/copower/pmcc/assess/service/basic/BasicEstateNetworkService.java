package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateNetworkDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateNetwork;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateNetworkVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:43
 * @Description:通信网络
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
    @Autowired
    private PublicService publicService;
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
    public Integer saveAndUpdateBasicEstateNetwork(BasicEstateNetwork basicEstateNetwork, boolean updateNull) throws Exception {
        if (basicEstateNetwork.getId() == null || basicEstateNetwork.getId().intValue() == 0) {
            basicEstateNetwork.setCreator(commonService.thisUserAccount());
            Integer id = basicEstateNetworkDao.saveBasicEstateNetwork(basicEstateNetwork);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstateNetwork.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicEstateNetwork estateNetwork = basicEstateNetworkDao.getBasicEstateNetworkById(basicEstateNetwork.getId());
                if (estateNetwork != null) {
                    basicEstateNetwork.setCreator(estateNetwork.getCreator());
                    basicEstateNetwork.setGmtCreated(estateNetwork.getGmtCreated());
                }
            }
            basicEstateNetworkDao.updateBasicEstateNetwork(basicEstateNetwork, updateNull);
            return basicEstateNetwork.getId();
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

    public BootstrapTableVo getBootstrapTableVo(BasicEstateNetwork basicEstateNetwork) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstateNetwork> basicEstateNetworkList = basicEstateNetworkDao.basicEstateNetworkList(basicEstateNetwork);
        List<BasicEstateNetworkVo> vos = LangUtils.transform(basicEstateNetworkList, o -> getBasicEstateNetworkVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? Lists.newArrayList() : vos);
        return vo;
    }

    public List<BasicEstateNetwork> getBasicEstateNetworkList(Integer estateId) {
        BasicEstateNetwork where = new BasicEstateNetwork();
        where.setEstateId(estateId);
        List<BasicEstateNetwork> basicEstateNetworkList = basicEstateNetworkDao.basicEstateNetworkList(where);
        return basicEstateNetworkList;
    }


    public BasicEstateNetworkVo getBasicEstateNetworkVo(BasicEstateNetwork basicEstateNetwork) {
        BasicEstateNetworkVo basicEstateNetworkVo = new BasicEstateNetworkVo();
        BeanUtils.copyProperties(basicEstateNetwork, basicEstateNetworkVo);
        basicEstateNetworkVo.setSupplierName(baseDataDicService.getNameById(basicEstateNetwork.getSupplier()));
        basicEstateNetworkVo.setServiceContentName(baseDataDicService.getNameById(basicEstateNetwork.getServiceContent()));
        basicEstateNetworkVo.setCreatorName(publicService.getUserNameByAccount(basicEstateNetwork.getCreator()));
        return basicEstateNetworkVo;
    }
}
