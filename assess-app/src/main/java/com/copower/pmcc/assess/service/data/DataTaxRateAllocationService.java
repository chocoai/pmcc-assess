package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataTaxRateAllocationDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataTaxRateAllocation;
import com.copower.pmcc.assess.dto.output.data.DataTaxRateAllocationVo;
import com.copower.pmcc.assess.service.ErpAreaService;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:53
 * @Description:税率配置
 */
@Service
public class DataTaxRateAllocationService {
    @Autowired
    private DataTaxRateAllocationDao dataTaxRateAllocationDao;

    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public Integer saveAndUpdateDataTaxRateAllocation(DataTaxRateAllocation dataTaxRateAllocation) {
        if (dataTaxRateAllocation.getId() == null) {
            dataTaxRateAllocation.setCreator(commonService.thisUserAccount());
            return dataTaxRateAllocationDao.addDataTaxRateAllocation(dataTaxRateAllocation);
        } else {
            dataTaxRateAllocationDao.updateDataTaxRateAllocation(dataTaxRateAllocation);
            return null;
        }
    }

    public DataTaxRateAllocation getDataTaxRateAllocationById(Integer id) {
        return dataTaxRateAllocationDao.getDataTaxRateAllocationById(id);
    }

    public BootstrapTableVo getDataTaxRateAllocationListVos(DataTaxRateAllocation dataTaxRateAllocation) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataTaxRateAllocationVo> vos = landLevels(dataTaxRateAllocation);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DataTaxRateAllocationVo> landLevels(DataTaxRateAllocation dataTaxRateAllocation) {
        List<DataTaxRateAllocation> dataTaxRateAllocations = dataTaxRateAllocationDao.getDataTaxRateAllocationList(dataTaxRateAllocation);
        List<DataTaxRateAllocationVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataTaxRateAllocations)) {
            for (DataTaxRateAllocation landLevel : dataTaxRateAllocations) {
                vos.add(getDataTaxRateAllocationVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDataTaxRateAllocation(DataTaxRateAllocation dataTaxRateAllocation) {
        dataTaxRateAllocationDao.removeDataTaxRateAllocation(dataTaxRateAllocation);
    }

    /**
     * 根据类型key获取数据
     *
     * @param key
     * @param province
     * @param city
     * @param district
     * @return
     */
    public DataTaxRateAllocation getTaxRateByKey(String key, String province, String city, String district) {
        BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicByFieldName(key);
        if (baseDataDic == null) return null;
        DataTaxRateAllocation where = new DataTaxRateAllocation();
        where.setType(baseDataDic.getId());
        if (StringUtils.isBlank(province) && StringUtils.isBlank(city) && StringUtils.isBlank(district)) {
            where.setBisNationalUnity(true);
        } else {
            where.setBisNationalUnity(false);
            where.setProvince(province);
            where.setCity(city);
            //当区县配置数据才获取否则取上级区域
            if (StringUtils.isNotBlank(district) && dataTaxRateAllocationDao.hasDataTaxRate(baseDataDic.getId(), province, city, district)) {
                where.setDistrict(district);
            }
        }
        List<DataTaxRateAllocation> rateAllocations = dataTaxRateAllocationDao.getDataTaxRateAllocationList(where);
        if (CollectionUtils.isEmpty(rateAllocations)) return null;
        return rateAllocations.get(0);
    }

    public DataTaxRateAllocation getTaxRateByKey(String key) {
        return getTaxRateByKey(key, null, null, null);
    }

    public DataTaxRateAllocationVo getDataTaxRateAllocationVo(DataTaxRateAllocation dataTaxRateAllocation) {
        DataTaxRateAllocationVo vo = new DataTaxRateAllocationVo();
        BaseDataDic baseDataDic = null;
        BeanUtils.copyProperties(dataTaxRateAllocation, vo);
        if (StringUtils.isNotBlank(dataTaxRateAllocation.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(dataTaxRateAllocation.getProvince()));//省
        }
        if (StringUtils.isNotBlank(dataTaxRateAllocation.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(dataTaxRateAllocation.getCity()));//市或者县
        }
        if (StringUtils.isNotBlank(dataTaxRateAllocation.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(dataTaxRateAllocation.getDistrict()));//县
        }

        if (dataTaxRateAllocation.getType() != null && dataTaxRateAllocation.getType() > 0) {
            baseDataDic = baseDataDicService.getDataDicById(dataTaxRateAllocation.getType());
            if (baseDataDic != null) {
                vo.setTypeName(baseDataDic.getName());
            }
        }
        return vo;
    }
}
