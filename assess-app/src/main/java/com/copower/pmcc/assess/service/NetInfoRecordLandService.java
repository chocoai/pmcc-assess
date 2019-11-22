package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordLandDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordLandVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:53
 */
@Service
public class NetInfoRecordLandService {
    @Autowired
    private NetInfoRecordLandDao netInfoRecordLandDao;

    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public Integer saveAndUpdateNetInfoRecordLand(NetInfoRecordLand netInfoRecordLand) {
        if (netInfoRecordLand.getId() == null) {
            netInfoRecordLand.setCreator(commonService.thisUserAccount());
            return netInfoRecordLandDao.addNetInfoRecordLand(netInfoRecordLand);
        } else {
            netInfoRecordLandDao.updateNetInfoRecordLand(netInfoRecordLand);
            return null;
        }
    }

    public NetInfoRecordLand getNetInfoRecordLandById(Integer id) {
        return netInfoRecordLandDao.getNetInfoRecordLandById(id);
    }

    public BootstrapTableVo getNetInfoRecordLandListVos(NetInfoRecordLand netInfoRecordLand) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetInfoRecordLandVo> vos = getVos(netInfoRecordLand);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<NetInfoRecordLandVo> getVos(NetInfoRecordLand netInfoRecordLand) {
        List<NetInfoRecordLand> netInfoRecordLands = netInfoRecordLandDao.getNetInfoRecordLandList(netInfoRecordLand);
        List<NetInfoRecordLandVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(netInfoRecordLands)) {
            for (NetInfoRecordLand landLevel : netInfoRecordLands) {
                vos.add(getNetInfoRecordLandVo(landLevel));
            }
        }
        return vos;
    }

    public void removeNetInfoRecordLand(Integer id) {
        netInfoRecordLandDao.deleteInfo(id);
    }

    public NetInfoRecordLandVo getNetInfoRecordLandVo(NetInfoRecordLand netInfoRecordLand) {
        NetInfoRecordLandVo vo = new NetInfoRecordLandVo();
        BaseDataDic baseDataDic = null;
        BeanUtils.copyProperties(netInfoRecordLand, vo);
        if (netInfoRecordLand.getDealType() != null) {
            baseDataDic = baseDataDicService.getDataDicById(netInfoRecordLand.getDealType());
            if (baseDataDic != null) {
                vo.setDealTypeName(baseDataDic.getName());
            }
        }
        if (StringUtils.isNotEmpty(netInfoRecordLand.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(netInfoRecordLand.getProvince()));
        }
        if (StringUtils.isNotEmpty(netInfoRecordLand.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(netInfoRecordLand.getCity()));
        }
        if (StringUtils.isNotEmpty(netInfoRecordLand.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(netInfoRecordLand.getDistrict()));
        }

        return vo;
    }
}
