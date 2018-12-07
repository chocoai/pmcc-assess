package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.basis.entity.DataBlock;
import com.copower.pmcc.assess.dal.basis.entity.DataDeveloper;
import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.dao.CaseEstateDao;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.cases.CaseEstateVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.data.DataDeveloperService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
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

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 14:41
 * @Description:
 */
@Service
public class CaseEstateService {
    @Autowired
    private CaseEstateDao caseEstateDao;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private CaseEstateParkingService caseEstateParkingService;
    @Autowired
    private CaseEstateSupplyService caseEstateSupplyService;
    @Autowired
    private CaseEstateNetworkService caseEstateNetworkService;
    @Autowired
    private CaseMatchingTrafficService caseMatchingTrafficService;
    @Autowired
    private CaseMatchingMedicalService caseMatchingMedicalService;
    @Autowired
    private CaseMatchingLeisurePlaceService caseMatchingLeisurePlaceService;
    @Autowired
    private CaseMatchingMaterialService caseMatchingMaterialService;
    @Autowired
    private CaseMatchingFinanceService caseMatchingFinanceService;
    @Autowired
    private CaseMatchingEnvironmentService caseMatchingEnvironmentService;
    @Autowired
    private CaseMatchingEducationService caseMatchingEducationService;
    @Autowired
    private CaseEstateLandStateService caseEstateLandStateService;
    @Autowired
    private CaseBuildingMainService caseBuildingMainService;
    @Autowired
    private DataDeveloperService dataDeveloperService;
    @Autowired
    private DataBlockService dataBlockService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public BootstrapTableVo getCaseEstateVos(CaseEstate caseEstate) {
        List<CaseEstateVo> vos = Lists.newArrayList();
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseEstate> caseEstateList = caseEstateDao.getCaseEstateList(caseEstate.getName(), caseEstate.getProvince(), caseEstate.getCity(), caseEstate.getDistrict());
        if (!ObjectUtils.isEmpty(caseEstateList)) {
            for (CaseEstate oo : caseEstateList) {
                vos.add(getCaseEstateVo(oo));
            }
        }
        vo.setRows(vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<CaseEstate> getCaseEstateList(CaseEstate caseEstate) {

        return caseEstateDao.getEstateList(caseEstate);
    }

    public CaseEstate getCaseEstateById(Integer id) {
        return caseEstateDao.getEstateById(id);
    }

    public Integer getVersion(Integer id) {
        if (id == null) return 0;
        CaseEstate caseEstate = caseEstateDao.getEstateById(id);
        if (caseEstate == null) return 0;
        return caseEstate.getVersion();
    }


    public Integer saveAndUpdateCaseEstate(CaseEstate caseEstate) throws Exception {
        if (caseEstate.getId() == null || caseEstate.getId().intValue() == 0) {
            int id = caseEstateDao.addEstate(caseEstate);
            return id;
        } else {
            caseEstateDao.updateEstate(caseEstate);
            return null;
        }
    }

    public boolean deleteCaseEstate(Integer id) {
        return caseEstateDao.deleteEstate(id);
    }

    public List<CustomCaseEntity> autoCompleteCaseEstate(String name,String province,String city) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CustomCaseEntity> caseEstateList = caseEstateDao.getLatestVersionEstateList(name,province,city,null);
        return caseEstateList;
    }

    public CaseEstateVo getCaseEstateVo(CaseEstate caseEstate) {
        CaseEstateVo vo = new CaseEstateVo();
        //获取格式化对象
        BeanUtils.copyProperties(caseEstate, vo);
        if (org.apache.commons.lang.StringUtils.isNotBlank(caseEstate.getProvince())) {
            //省
            vo.setProvinceName(erpAreaService.getSysAreaName(caseEstate.getProvince()));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(caseEstate.getCity())) {
            //市或者县
            vo.setCityName(erpAreaService.getSysAreaName(caseEstate.getCity()));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(caseEstate.getDistrict())) {
            //县
            vo.setDistrictName(erpAreaService.getSysAreaName(caseEstate.getDistrict()));
        }
        if (caseEstate.getPosition() != null) {
            vo.setPositionName(baseDataDicService.getNameById(caseEstate.getPosition()));
        }
        if (caseEstate.getDeveloperId() != null) {
            DataDeveloper dataDeveloper = dataDeveloperService.getByDataDeveloperId(caseEstate.getDeveloperId());
            if (dataDeveloper != null) {
                vo.setDeveloperName(dataDeveloper.getName());
            }
        }
        if (caseEstate.getBlockId() != null) {
            DataBlock dataBlock = dataBlockService.getDataBlockById(caseEstate.getBlockId());
            if (dataBlock != null) {
                vo.setBlockName(dataBlock.getName());
            }
        }
        if (caseEstate.getSupplyGas() != null) {
            vo.setSupplyGasName(baseDataDicService.getNameById(caseEstate.getSupplyGas()));
        }
        if (caseEstate.getSupplyPower() != null) {
            vo.setSupplyPowerName(baseDataDicService.getNameById(caseEstate.getSupplyPower()));
        }
        if (caseEstate.getSupplyWater() != null) {
            vo.setSupplyWaterName(baseDataDicService.getNameById(caseEstate.getSupplyWater()));
        }
        if (caseEstate.getSupplyHeating() != null) {
            vo.setSupplyHeatingName(baseDataDicService.getNameById(caseEstate.getSupplyHeating()));
        }
        return vo;
    }

    /**
     * 是否有楼盘信息
     * @param name
     * @param province
     * @param city
     * @return
     */
    public Boolean hasEstateByName(String name,String province,String city){
        return caseEstateDao.getEstateCountByName(name, province, city) > 0;
    }
}
