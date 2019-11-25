package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordHouseDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordHouseVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:53
 */
@Service
public class NetInfoRecordHouseService {
    @Autowired
    private NetInfoRecordHouseDao netInfoRecordHouseDao;

    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private NetInfoAssignTaskService netInfoAssignTaskService;

    public Integer saveAndUpdateNetInfoRecordHouse(NetInfoRecordHouse netInfoRecordHouse) {
        if (netInfoRecordHouse.getId() == null) {
            netInfoRecordHouse.setCreator(commonService.thisUserAccount());
            return netInfoRecordHouseDao.addNetInfoRecordHouse(netInfoRecordHouse);
        } else {
            netInfoRecordHouseDao.updateNetInfoRecordHouse(netInfoRecordHouse);
            return null;
        }
    }

    public NetInfoRecordHouse getNetInfoRecordHouseById(Integer id) {
        return netInfoRecordHouseDao.getNetInfoRecordHouseById(id);
    }

    public BootstrapTableVo getNetInfoRecordHouseListVos(Integer status, String province, String city, String district, String street, String name) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetInfoRecordHouse> netInfoRecordHouses = netInfoRecordHouseDao.getNetInfoRecordHouseList(status, province, city, district, street, name);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(netInfoRecordHouses == null ? new ArrayList() : LangUtils.transform(netInfoRecordHouses, o -> getNetInfoRecordHouseVo(o)));
        return bootstrapTableVo;
    }

    public List<NetInfoRecordHouseVo> getVos(NetInfoRecordHouse netInfoRecordHouse) {
        List<NetInfoRecordHouse> netInfoRecordHouses = netInfoRecordHouseDao.getNetInfoRecordHouseList(netInfoRecordHouse);
        List<NetInfoRecordHouseVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(netInfoRecordHouses)) {
            for (NetInfoRecordHouse houseLevel : netInfoRecordHouses) {
                vos.add(getNetInfoRecordHouseVo(houseLevel));
            }
        }
        return vos;
    }

    public void removeNetInfoRecordHouse(Integer id) {
        netInfoRecordHouseDao.deleteInfo(id);
    }

    public NetInfoRecordHouseVo getNetInfoRecordHouseVo(NetInfoRecordHouse netInfoRecordHouse) {
        NetInfoRecordHouseVo vo = new NetInfoRecordHouseVo();
        BaseDataDic baseDataDic = null;
        BeanUtils.copyProperties(netInfoRecordHouse, vo);
        if (StringUtils.isNotEmpty(netInfoRecordHouse.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(netInfoRecordHouse.getProvince()));
        }
        if (StringUtils.isNotEmpty(netInfoRecordHouse.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(netInfoRecordHouse.getCity()));
        }
        if (StringUtils.isNotEmpty(netInfoRecordHouse.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(netInfoRecordHouse.getDistrict()));
        }
        if (netInfoRecordHouse.getDealType() != null) {
            baseDataDic = baseDataDicService.getDataDicById(netInfoRecordHouse.getDealType());
            if (baseDataDic != null) {
                vo.setDealTypeName(baseDataDic.getName());
            }
        }

        return vo;
    }
}
