package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseHouseWaterDrainDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseWaterDrain;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseWaterDrainVo;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/12/11 11:39
 * @Description:
 */
@Service
public class CaseHouseWaterDrainService {
    @Autowired
    private CaseHouseWaterDrainDao caseHouseWaterDrainDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public Integer saveAndUpdateCaseHouseWaterDrain(CaseHouseWaterDrain caseHouseWaterDrain) {
        if (caseHouseWaterDrain.getId() == null) {
            caseHouseWaterDrain.setCreator(commonService.thisUserAccount());
            return caseHouseWaterDrainDao.addBuildingOutfit(caseHouseWaterDrain);
        } else {
            caseHouseWaterDrainDao.updateBuildingOutfit(caseHouseWaterDrain);
            return null;
        }
    }

    public CaseHouseWaterDrain getCaseHouseWaterDrainById(Integer id) {
        return caseHouseWaterDrainDao.getBuildingOutfitById(id);
    }

    public BootstrapTableVo getCaseHouseWaterDrainListVos(CaseHouseWaterDrain caseHouseWaterDrain) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseHouseWaterDrainVo> vos = getCaseHouseWaterDrainList(caseHouseWaterDrain);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<CaseHouseWaterDrainVo> getCaseHouseWaterDrainList(CaseHouseWaterDrain caseHouseWaterDrain) {
        List<CaseHouseWaterDrain> caseHouseWaterDrains = caseHouseWaterDrainDao.getBuildingOutfitList(caseHouseWaterDrain);
        List<CaseHouseWaterDrainVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(caseHouseWaterDrains)) {
            for (CaseHouseWaterDrain landLevel : caseHouseWaterDrains) {
                vos.add(getCaseHouseWaterDrainVo(landLevel));
            }
        }
        return vos;
    }

    public List<CaseHouseWaterDrain> getCaseHouseWaterDrainListO(CaseHouseWaterDrain caseHouseWaterDrain) {
        List<CaseHouseWaterDrain> caseHouseWaterDrains = caseHouseWaterDrainDao.getBuildingOutfitList(caseHouseWaterDrain);
        return caseHouseWaterDrains;
    }

    public boolean removeCaseHouseWaterDrain(CaseHouseWaterDrain caseHouseWaterDrain) {
        try {
            caseHouseWaterDrainDao.deleteBuildingOutfit(caseHouseWaterDrain.getId());
            return true;
        } catch (Exception e1) {
            return false;
        }
    }

    public CaseHouseWaterDrainVo getCaseHouseWaterDrainVo(CaseHouseWaterDrain caseHouseWaterDrain) {
        CaseHouseWaterDrainVo vo = new CaseHouseWaterDrainVo();
        BeanUtils.copyProperties(caseHouseWaterDrain, vo);
        vo.setTypeName(baseDataDicService.getNameById(caseHouseWaterDrain.getType()));
        vo.setDrainSystemName(baseDataDicService.getNameById(caseHouseWaterDrain.getDrainSystem()));
        vo.setProcessingModeName(baseDataDicService.getNameById(caseHouseWaterDrain.getProcessingMode()));
        return vo;
    }


}
