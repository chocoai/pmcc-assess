package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataBuildingInstallCostDao;
import com.copower.pmcc.assess.dal.basis.entity.DataBuildingInstallCost;
import com.copower.pmcc.assess.dto.output.data.DataBuildingInstallCostVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 10:01
 * @Description:基础版块维护
 */
@Service
public class DataBuildingInstallCostService {
    @Autowired
    private DataBuildingInstallCostDao dataBuildingInstallCostDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateDataBuildingInstallCost(DataBuildingInstallCost dataBuildingInstallCost) {
        if (dataBuildingInstallCost.getId() == null || dataBuildingInstallCost.getId().intValue() == 0) {
            dataBuildingInstallCost.setCreator(commonService.thisUserAccount());
            try {
                return dataBuildingInstallCostDao.addDataBuildingInstallCost(dataBuildingInstallCost);
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
                return null;
            }
        } else {
            dataBuildingInstallCostDao.updateDataBuildingInstallCost(dataBuildingInstallCost);
            return null;
        }
    }

    public DataBuildingInstallCost getDataBuildingInstallCostById(Integer id) {
        if (id == null) {
            logger.error("null point");
            return null;
        }
        return dataBuildingInstallCostDao.getDataBuildingInstallCostById(id);
    }

    public BootstrapTableVo getDataBuildingInstallCostListVos(String province, String city, String district) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataBuildingInstallCostVo> vos = dataBuildingInstallCostVos(province, city, district);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DataBuildingInstallCostVo> dataBuildingInstallCostVos(DataBuildingInstallCost dataBuildingInstallCost) {
        List<DataBuildingInstallCost> dataBuildingInstallCosts = dataBuildingInstallCostDao.getDataBuildingInstallCostList(dataBuildingInstallCost);
        List<DataBuildingInstallCostVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataBuildingInstallCosts)) {
            for (DataBuildingInstallCost landLevel : dataBuildingInstallCosts) {
                vos.add(getDataBuildingInstallCostVo(landLevel));
            }
        }
        return vos;
    }

    public List<DataBuildingInstallCostVo> dataBuildingInstallCostVos(String province, String city, String district) {
        List<DataBuildingInstallCost> dataBuildingInstallCosts = dataBuildingInstallCostDao.getDataBuildingInstallCostList(province, city, district);
        List<DataBuildingInstallCostVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataBuildingInstallCosts)) {
            for (DataBuildingInstallCost landLevel : dataBuildingInstallCosts) {
                vos.add(getDataBuildingInstallCostVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDataBuildingInstallCost(DataBuildingInstallCost dataBuildingInstallCost) {
        dataBuildingInstallCostDao.removeDataBuildingInstallCost(dataBuildingInstallCost);
    }

    public DataBuildingInstallCostVo getDataBuildingInstallCostVo(DataBuildingInstallCost dataBuildingInstallCost) {
        DataBuildingInstallCostVo vo = new DataBuildingInstallCostVo();
        BeanUtils.copyProperties(dataBuildingInstallCost, vo);
        if (StringUtils.isNotBlank(dataBuildingInstallCost.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(dataBuildingInstallCost.getProvince()));
        }
        if (StringUtils.isNotBlank(dataBuildingInstallCost.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(dataBuildingInstallCost.getCity()));
        }
        if (StringUtils.isNotBlank(dataBuildingInstallCost.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(dataBuildingInstallCost.getDistrict()));
        }
        return vo;
    }


}
