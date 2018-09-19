package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineBuildingMaintenanceDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingMaintenance;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineBuildingMaintenanceVo;
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
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/8/2 14:07
 * @Description:维护结构
 */
@Service
public class ExamineBuildingMaintenanceService {


    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineBuildingMaintenanceDao examineBuildingMaintenanceDao;
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
    public ExamineBuildingMaintenance getExamineBuildingMaintenanceById(Integer id) {
        return examineBuildingMaintenanceDao.getBuildingMaintenanceById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineBuildingMaintenance
     * @return
     */
    public List<ExamineBuildingMaintenance> getExamineBuildingMaintenanceList(ExamineBuildingMaintenance examineBuildingMaintenance) {
        return examineBuildingMaintenanceDao.getBuildingMaintenanceList(examineBuildingMaintenance);
    }

    public BootstrapTableVo getExamineBuildingMaintenanceLists(ExamineBuildingMaintenance examineBuildingMaintenance) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineBuildingMaintenanceVo> vos = Lists.newArrayList();
        getExamineBuildingMaintenanceList(examineBuildingMaintenance).stream().forEach(oo -> vos.add(getExamineBuildingMaintenanceVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineBuildingMaintenanceVo>() : vos);
        return vo;
    }

    public ExamineBuildingMaintenanceVo getExamineBuildingMaintenanceVo(ExamineBuildingMaintenance examineBuildingMaintenance) {
        ExamineBuildingMaintenanceVo vo = new ExamineBuildingMaintenanceVo();
        BeanUtils.copyProperties(examineBuildingMaintenance, vo);
        if (examineBuildingMaintenance.getCategory() != null) {
            vo.setCategoryName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_MAINTENANCE_CATEGORY, examineBuildingMaintenance.getCategory()));
        }
        if (examineBuildingMaintenance.getMaterialQuality() != null) {
            vo.setMaterialQualityName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_MATERIALQUALITY, examineBuildingMaintenance.getMaterialQuality()));
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
     * @param examineBuildingMaintenance
     * @return
     */
    public boolean addExamineBuildingMaintenance(ExamineBuildingMaintenance examineBuildingMaintenance) {
        examineBuildingMaintenance.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineBuildingMaintenance.getDeclareId())) {
            examineBuildingMaintenance.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineBuildingMaintenance.getExamineType())) {
            examineBuildingMaintenance.setExamineType(0);
        }
        if (examineBuildingMaintenance.getBuildingId() == null) {
            examineBuildingMaintenance.setBuildingId(0);
        }
        return examineBuildingMaintenanceDao.addBuildingMaintenance(examineBuildingMaintenance);
    }

    /**
     * 编辑
     *
     * @param examineBuildingMaintenance
     * @return
     */
    public boolean updateExamineBuildingMaintenance(ExamineBuildingMaintenance examineBuildingMaintenance) {
        return examineBuildingMaintenanceDao.updateBuildingMaintenance(examineBuildingMaintenance);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineBuildingMaintenance(Integer id) {
        return examineBuildingMaintenanceDao.deleteBuildingMaintenance(id);
    }

    public boolean removeExamineBuildingMaintenance(ExamineBuildingMaintenance examineBuildingMaintenance){
        try {
            examineBuildingMaintenanceDao.removeExamineBuildingMaintenance(examineBuildingMaintenance);
            return  true;
        } catch (Exception e1) {
            return  false;
        }
    }

}
