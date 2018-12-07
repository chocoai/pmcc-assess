package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingMaintenanceDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMaintenance;
import com.copower.pmcc.assess.dto.output.cases.CaseBuildingMaintenanceVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 11:58
 * @Description:
 */
@Service
public class CaseBuildingMaintenanceService {
    @Autowired
    private CaseBuildingMaintenanceDao caseBuildingMaintenanceDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public CaseBuildingMaintenance getCaseBuildingMaintenanceById(Integer id) {
        return caseBuildingMaintenanceDao.getBuildingMaintenanceById(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseBuildingMaintenance
     * @return
     */
    public List<CaseBuildingMaintenance> getCaseBuildingMaintenanceList(CaseBuildingMaintenance caseBuildingMaintenance) {
        return caseBuildingMaintenanceDao.getBuildingMaintenanceList(caseBuildingMaintenance);
    }

    public BootstrapTableVo getCaseBuildingMaintenanceLists(CaseBuildingMaintenance caseBuildingMaintenance) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseBuildingMaintenanceVo> vos = Lists.newArrayList();
        getCaseBuildingMaintenanceList(caseBuildingMaintenance).stream().forEach(oo -> vos.add(getCaseBuildingMaintenanceVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseBuildingMaintenanceVo>() : vos);
        return vo;
    }

    public CaseBuildingMaintenanceVo getCaseBuildingMaintenanceVo(CaseBuildingMaintenance caseBuildingMaintenance) {
        CaseBuildingMaintenanceVo vo = new CaseBuildingMaintenanceVo();
        BeanUtils.copyProperties(caseBuildingMaintenance, vo);
        if (caseBuildingMaintenance.getType() != null) {
            vo.setCategoryName(baseDataDicService.getNameById(caseBuildingMaintenance.getType()));
        }
        if (caseBuildingMaintenance.getCategory() != null) {
            vo.setCategoryName(baseDataDicService.getNameById(caseBuildingMaintenance.getCategory()));
        }
        if (caseBuildingMaintenance.getMaterialQuality() != null) {
            vo.setMaterialQualityName(baseDataDicService.getNameById(caseBuildingMaintenance.getMaterialQuality()));
        }
        return vo;
    }

    private String getValue(String key, Integer v) {
        StringBuilder builder = new StringBuilder(1024);
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(key);
        if (baseDataDic.size() >= 1) {
            if (v != null) {
                for (BaseDataDic base : baseDataDic) {
                    if (base.getId().intValue() == v.intValue()) {
                        builder.append(base.getName());
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * 新增
     *
     * @param caseBuildingMaintenance
     * @return
     */
    public boolean addCaseBuildingMaintenance(CaseBuildingMaintenance caseBuildingMaintenance) {
        return caseBuildingMaintenanceDao.addBuildingMaintenance(caseBuildingMaintenance);
    }

    /**
     * 编辑
     *
     * @param caseBuildingMaintenance
     * @return
     */
    public boolean updateCaseBuildingMaintenance(CaseBuildingMaintenance caseBuildingMaintenance) {
        return caseBuildingMaintenanceDao.updateBuildingMaintenance(caseBuildingMaintenance);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseBuildingMaintenance(Integer id) {
        return caseBuildingMaintenanceDao.deleteBuildingMaintenance(id);
    }

    public boolean removeCaseBuildingMaintenance(CaseBuildingMaintenance caseBuildingMaintenance) {
        try {
            caseBuildingMaintenanceDao.deleteBuildingMaintenance(caseBuildingMaintenance.getId());
            return true;
        } catch (Exception e1) {
            return false;
        }
    }

    /**
     * 根据查询条件判断是否有数据
     *
     * @param buildingId
     * @return
     */
    public boolean hasBuildingMaintenanceData(Integer buildingId) {
        return caseBuildingMaintenanceDao.countByBuildingId(buildingId) > 0;
    }
}
