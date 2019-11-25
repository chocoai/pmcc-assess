package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordHouseDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordHouseVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.LangUtils;
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

    public BootstrapTableVo getNetInfoRecordHouseListVos(NetInfoRecordHouse netInfoRecordHouse) {
        List<NetInfoRecordHouse> netInfoRecordHouses = netInfoRecordHouseDao.getNetInfoRecordHouseList(netInfoRecordHouse);
        List<Integer> masterIds = LangUtils.transform(netInfoRecordHouses, o -> o.getMasterId());
        return netInfoAssignTaskService.getNetInfoRecordApprovalVos(masterIds);
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
