package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.enums.data.DataDamagedDegreeEnum;
import com.copower.pmcc.assess.dal.basis.entity.DataDamagedDegree;
import com.copower.pmcc.assess.dal.cases.dao.CaseHouseDamagedDegreeDao;
import com.copower.pmcc.assess.dal.cases.dao.CaseHouseDamagedDegreeDetailDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseDamagedDegree;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseDamagedDegreeDetail;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseDamagedDegreeDetailVo;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseDamagedDegreeVo;
import com.copower.pmcc.assess.service.data.DataDamagedDegreeService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/2 09:59
 * @Description:房间
 */
@Service
public class CaseHouseDamagedDegreeService {
    @Autowired
    private CaseHouseDamagedDegreeDao caseHouseDamagedDegreeDao;
    @Autowired
    private CaseHouseDamagedDegreeDetailDao caseHouseDamagedDegreeDetailDao;
    @Autowired
    private DataDamagedDegreeService dataDamagedDegreeService;

    public List<CaseHouseDamagedDegree> getDamagedDegreeListByHouseId(Integer houseId) {
        CaseHouseDamagedDegree caseHouseDamagedDegree = new CaseHouseDamagedDegree();
        caseHouseDamagedDegree.setHouseId(houseId);
        return caseHouseDamagedDegreeDao.getDamagedDegreeList(caseHouseDamagedDegree);
    }

    public List<CaseHouseDamagedDegree> getDamagedDegreeVoList(Integer houseId) {
        List<CaseHouseDamagedDegree> damagedDegreeList = getDamagedDegreeListByHouseId(houseId);
        return LangUtils.transform(damagedDegreeList, o -> getCaseHouseDamagedDegreeVo(o));
    }

    public void addCaseHouseDamagedDegree(CaseHouseDamagedDegree caseHouseDamagedDegree) {
        caseHouseDamagedDegreeDao.addCaseHouseDamagedDegree(caseHouseDamagedDegree);
    }

    private CaseHouseDamagedDegreeVo getCaseHouseDamagedDegreeVo(CaseHouseDamagedDegree caseHouseDamagedDegree) {
        CaseHouseDamagedDegreeVo caseHouseDamagedDegreeVo = new CaseHouseDamagedDegreeVo();
        BeanUtils.copyProperties(caseHouseDamagedDegree, caseHouseDamagedDegreeVo);
        caseHouseDamagedDegreeVo.setTypeName(dataDamagedDegreeService.getNameById(caseHouseDamagedDegree.getType()));
        DataDamagedDegree dataDamagedDegree = dataDamagedDegreeService.getCacheDamagedDegreeById(caseHouseDamagedDegree.getCategory());
        if (dataDamagedDegree != null) {
            caseHouseDamagedDegreeVo.setCategoryName(dataDamagedDegree.getName());
            caseHouseDamagedDegreeVo.setStandardScore(dataDamagedDegree.getStandardScore());
            caseHouseDamagedDegreeVo.setHasChildren(!CollectionUtils.isEmpty(dataDamagedDegreeService.getCacheDamagedDegreeListByPid(dataDamagedDegree.getId())));
        }
        if (!StringUtils.isEmpty(caseHouseDamagedDegree.getEntityCondition()))
            caseHouseDamagedDegreeVo.setEntityConditionName(DataDamagedDegreeEnum.getEnumByKey(caseHouseDamagedDegree.getEntityCondition()).getValue());
        return caseHouseDamagedDegreeVo;
    }

    public List<CaseHouseDamagedDegreeDetail> getDamagedDegreeDetails(Integer damagedDegreeId) {
        CaseHouseDamagedDegreeDetail caseHouseDamagedDegreeDetail = new CaseHouseDamagedDegreeDetail();
        caseHouseDamagedDegreeDetail.setDamagedDegreeId(damagedDegreeId);
        return caseHouseDamagedDegreeDetailDao.getDamagedDegreeDetailList(caseHouseDamagedDegreeDetail);
    }

    public void addCaseHouseDamagedDegreeDetail(CaseHouseDamagedDegreeDetail caseHouseDamagedDegreeDetail) {
        caseHouseDamagedDegreeDetailDao.addCaseHouseDamagedDegreeDetail(caseHouseDamagedDegreeDetail);
    }

    private CaseHouseDamagedDegreeDetailVo getCaseHouseDamagedDegreeDetailVo(CaseHouseDamagedDegreeDetail caseHouseDamagedDegreeDetail) {
        CaseHouseDamagedDegreeDetailVo caseHouseDamagedDegreeDetailVo = new CaseHouseDamagedDegreeDetailVo();
        BeanUtils.copyProperties(caseHouseDamagedDegreeDetail, caseHouseDamagedDegreeDetailVo);
        DataDamagedDegree dataDamagedDegree = dataDamagedDegreeService.getCacheDamagedDegreeById(caseHouseDamagedDegreeDetail.getType());
        if (dataDamagedDegree != null) {
            caseHouseDamagedDegreeDetailVo.setTypeName(dataDamagedDegree.getName());
            caseHouseDamagedDegreeDetailVo.setStandardScore(dataDamagedDegree.getStandardScore());
        }
        if (!StringUtils.isEmpty(caseHouseDamagedDegreeDetail.getEntityCondition()))
            caseHouseDamagedDegreeDetailVo.setEntityConditionName(DataDamagedDegreeEnum.getEnumByKey(caseHouseDamagedDegreeDetail.getEntityCondition()).getValue());
        return caseHouseDamagedDegreeDetailVo;
    }

    public BootstrapTableVo getDamagedDegreeDetailList(Integer damagedDegreeId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        CaseHouseDamagedDegreeDetail caseHouseDamagedDegreeDetail = new CaseHouseDamagedDegreeDetail();
        caseHouseDamagedDegreeDetail.setDamagedDegreeId(damagedDegreeId);
        List<CaseHouseDamagedDegreeDetail> degreeDetailList = caseHouseDamagedDegreeDetailDao.getDamagedDegreeDetailList(caseHouseDamagedDegreeDetail);
        List<CaseHouseDamagedDegreeDetailVo> vos = LangUtils.transform(degreeDetailList, o -> getCaseHouseDamagedDegreeDetailVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? Lists.newArrayList() : vos);
        return vo;
    }

    /**
     * 根据查询条件判断是否有数据
     *
     * @param houseId
     * @return
     */
    public boolean hasHouseDamagedDegreeData(Integer houseId) {
        return caseHouseDamagedDegreeDao.countByHouseId(houseId) > 0;
    }
}
