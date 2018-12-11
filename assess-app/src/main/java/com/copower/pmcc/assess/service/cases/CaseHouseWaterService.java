package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseHouseWaterDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseWater;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseWaterVo;
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
 * @Date: 2018/9/18 10:16
 * @Description:
 */
@Service
public class CaseHouseWaterService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseHouseWaterDao caseHouseWaterDao;
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
    public CaseHouseWater getCaseHouseWaterById(Integer id) {
        return caseHouseWaterDao.getHouseWaterById(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseHouseWater
     * @return
     */
    public List<CaseHouseWater> getCaseHouseWaterList(CaseHouseWater caseHouseWater) {
        return caseHouseWaterDao.getHouseWaterList(caseHouseWater);
    }

    public BootstrapTableVo getCaseHouseWaterLists(CaseHouseWater caseHouseWater) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseHouseWaterVo> vos = Lists.newArrayList();
        getCaseHouseWaterList(caseHouseWater).stream().forEach(oo -> vos.add(getCaseHouseWaterVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseHouseWaterVo>() : vos);
        return vo;
    }

    public CaseHouseWaterVo getCaseHouseWaterVo(CaseHouseWater caseHouseWater) {
        CaseHouseWaterVo vo = new CaseHouseWaterVo();
        BeanUtils.copyProperties(caseHouseWater, vo);
        vo.setPretreatedWaterName(baseDataDicService.getNameById(caseHouseWater.getPretreatedWater()));
        vo.setPurificationEquipmentPriceName(baseDataDicService.getNameById(caseHouseWater.getPurificationEquipmentPrice()));
        vo.setBoosterEquipmentName(baseDataDicService.getNameById(caseHouseWater.getBoosterEquipment()));
        vo.setPipingLayoutName(baseDataDicService.getNameById(caseHouseWater.getPipingLayout()));
        vo.setPipeMaterialName(baseDataDicService.getNameById(caseHouseWater.getPipeMaterial()));
        vo.setSupplyModeName(baseDataDicService.getNameById(caseHouseWater.getSupplyMode()));
        vo.setPurificationEquipmentPriceName(baseDataDicService.getNameById(caseHouseWater.getPurificationEquipmentPrice()));
        vo.setFireWaterSupplyName(baseDataDicService.getNameById(caseHouseWater.getFireWaterSupply()));
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
     * @param caseHouseWater
     * @return
     */
    public boolean addCaseHouseWater(CaseHouseWater caseHouseWater) {
        return caseHouseWaterDao.addHouseWater(caseHouseWater);
    }

    /**
     * 编辑
     *
     * @param caseHouseWater
     * @return
     */
    public boolean updateCaseHouseWater(CaseHouseWater caseHouseWater) {
        return caseHouseWaterDao.updateHouseWater(caseHouseWater);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseHouseWater(Integer id) {
        return caseHouseWaterDao.deleteHouseWater(id);
    }

    /**
     * 根据查询条件判断是否有数据
     *
     * @param houseId
     * @return
     */
    public boolean hasHouseWaterData(Integer houseId) {
        return caseHouseWaterDao.countByHouseId(houseId) > 0;
    }
}
