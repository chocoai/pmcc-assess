package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseUnitDecorateDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitDecorate;
import com.copower.pmcc.assess.dto.output.cases.CaseUnitDecorateVo;
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
 * @Date: 2018/9/18 16:28
 * @Description:
 */
@Service
public class CaseUnitDecorateService {
    @Autowired
    private CaseUnitDecorateDao caseUnitDecorateDao;
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
    public CaseUnitDecorate getCaseUnitDecorateById(Integer id) {
        return caseUnitDecorateDao.getUnitDecorateById(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseUnitDecorate
     * @return
     */
    public List<CaseUnitDecorate> getCaseUnitDecorateList(CaseUnitDecorate caseUnitDecorate) {
        return caseUnitDecorateDao.getUnitDecorateList(caseUnitDecorate);
    }

    public BootstrapTableVo getCaseUnitDecorateLists(CaseUnitDecorate caseUnitDecorate) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseUnitDecorateVo> vos = Lists.newArrayList();
        getCaseUnitDecorateList(caseUnitDecorate).stream().forEach(oo -> vos.add(getCaseUnitDecorateVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseUnitDecorateVo>() : vos);
        return vo;
    }

    public CaseUnitDecorateVo getCaseUnitDecorateVo(CaseUnitDecorate caseUnitDecorate) {
        CaseUnitDecorateVo vo = new CaseUnitDecorateVo();
        BeanUtils.copyProperties(caseUnitDecorate, vo);
        vo.setConstructionTechnologyName(baseDataDicService.getNameById(caseUnitDecorate.getConstructionTechnology()));
        vo.setMaterialPriceName(baseDataDicService.getNameById(caseUnitDecorate.getMaterialPriceRange()));
        vo.setDecoratingMaterialName(baseDataDicService.getNameById(caseUnitDecorate.getDecoratingMaterial()));
        return vo;
    }

    /**
     * 新增
     *
     * @param caseUnitDecorate
     * @return
     */
    public boolean addCaseUnitDecorate(CaseUnitDecorate caseUnitDecorate) {
        return caseUnitDecorateDao.addUnitDecorate(caseUnitDecorate);
    }

    /**
     * 编辑
     *
     * @param caseUnitDecorate
     * @return
     */
    public boolean updateCaseUnitDecorate(CaseUnitDecorate caseUnitDecorate) {
        return caseUnitDecorateDao.updateUnitDecorate(caseUnitDecorate);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseUnitDecorate(Integer id) {
        return caseUnitDecorateDao.deleteUnitDecorate(id);
    }

    /**
     * 根据查询条件判断是否有数据
     * @param unitId
     * @return
     */
    public boolean hasUnitDecorateData(Integer unitId){
        return caseUnitDecorateDao.countByUnitId(unitId)>0;
    }

}
