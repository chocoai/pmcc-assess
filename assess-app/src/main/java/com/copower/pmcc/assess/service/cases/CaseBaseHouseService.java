package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomCaseMapper;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCaseSummary;
import com.copower.pmcc.assess.dal.cases.dao.CaseBaseHouseDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseBaseHouse;
import com.copower.pmcc.assess.dto.output.cases.CaseBaseHouseVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by kings on 2018-12-3.
 */
@Service
public class CaseBaseHouseService {
    @Autowired
    private CaseBaseHouseDao caseBaseHouseDao;
    @Autowired
    private CustomCaseMapper customCaseMapper;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ErpAreaService erpAreaService;

    public CaseBaseHouse getBaseHouseById(Integer id) {
        return caseBaseHouseDao.getBaseHouseById(id);
    }

    public BootstrapTableVo getBootstrapTableVo(BigDecimal areaStart, BigDecimal areaEnd, Date tradingTimeStart, Date tradingTimeEnd, BasicHouseCaseSummary basicHouseCaseSummary) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseBaseHouse> list = null;//customCaseMapper.findCaseBaseHouseList(areaStart, areaEnd, tradingTimeStart, tradingTimeEnd, basicHouseCaseSummary);
        List<CaseBaseHouseVo> vos = LangUtils.transform(list, o -> getCaseBaseHouseVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public CaseBaseHouseVo getCaseBaseHouseVo(CaseBaseHouse caseBaseHouse) {
        if (caseBaseHouse == null) return null;
        CaseBaseHouseVo caseBaseHouseVo = new CaseBaseHouseVo();
        BeanUtils.copyProperties(caseBaseHouse, caseBaseHouseVo);
        if (org.apache.commons.lang.StringUtils.isNotEmpty(caseBaseHouse.getProvince())) {
            caseBaseHouseVo.setProvinceName(erpAreaService.getSysAreaName(caseBaseHouse.getProvince()));
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(caseBaseHouse.getCity())) {
            caseBaseHouseVo.setCityName(erpAreaService.getSysAreaName(caseBaseHouse.getCity()));
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(caseBaseHouse.getDistrict())) {
            caseBaseHouseVo.setDistrictName(erpAreaService.getSysAreaName(caseBaseHouse.getDistrict()));
        }
        if (caseBaseHouse.getPracticalUse() != null)
            caseBaseHouseVo.setPracticalUseName(baseDataDicService.getNameById(caseBaseHouse.getPracticalUse()));
        if (caseBaseHouse.getTradingType() != null)
            caseBaseHouseVo.setTradingTypeName(baseDataDicService.getNameById(caseBaseHouse.getTradingType()));
        if (caseBaseHouse.getDealType() != null)
            caseBaseHouseVo.setDealTypeName(baseDataDicService.getNameById(caseBaseHouse.getDealType()));
        if (StringUtils.isNotEmpty(caseBaseHouse.getApprover()))
            caseBaseHouseVo.setApproverName(publicService.getUserNameByAccount(caseBaseHouse.getApprover()));
        if (StringUtils.isNotEmpty(caseBaseHouse.getCreator()))
            caseBaseHouseVo.setCreatorName(publicService.getUserNameByAccount(caseBaseHouse.getCreator()));
        return caseBaseHouseVo;
    }

    /**
     * 新增数据
     *
     * @param caseBaseHouse
     */
    public void addBaseHouse(CaseBaseHouse caseBaseHouse) {
        caseBaseHouseDao.addBaseHouse(caseBaseHouse);
    }

    /**
     * 更新数据
     *
     * @param caseBaseHouse
     */
    public void updateBaseHouse(CaseBaseHouse caseBaseHouse) {
        caseBaseHouseDao.updateBaseHouse(caseBaseHouse);
    }

    /**
     * 获取数据列表
     *
     * @param caseBaseHouse
     * @return
     */
    public List<CaseBaseHouse> getBaseHouseList(CaseBaseHouse caseBaseHouse) {
        return caseBaseHouseDao.getBaseHouseList(caseBaseHouse);
    }

    /**
     * 删除数据by caseHouseId
     *
     * @param id
     */
    public void deleteBaseHouseById(Integer id) {
        caseBaseHouseDao.deleteBaseHouse(id);
    }

    /**
     * 验证
     */
    public boolean checkFullName(String fullName) {
        List<CaseBaseHouse> baseHouseList = getBaseHouseList(new CaseBaseHouse());
        if (!CollectionUtils.isEmpty(baseHouseList)) {
            for (CaseBaseHouse item : baseHouseList) {
                if (StringUtils.equals(fullName, item.getFullName())) {
                    return false;
                }
            }
        }
        return true;

    }

}
