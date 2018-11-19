package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseMatchingMedicalDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMedical;
import com.copower.pmcc.assess.dto.output.cases.CaseMatchingMedicalVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/17 15:39
 * @Description:
 */
@Service
public class CaseMatchingMedicalService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseMatchingMedicalDao caseMatchingMedicalDao;

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
    public CaseMatchingMedical getCaseMatchingMedicalById(Integer id) {
        return caseMatchingMedicalDao.getMatchingMedicalById(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseMatchingMedical
     * @return
     */
    public List<CaseMatchingMedical> getCaseMatchingMedicalList(CaseMatchingMedical caseMatchingMedical) {
        return caseMatchingMedicalDao.getMatchingMedicalList(caseMatchingMedical);
    }

    public BootstrapTableVo getCaseMatchingMedicalLists(CaseMatchingMedical caseMatchingMedical) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseMatchingMedicalVo> vos = Lists.newArrayList();
        getCaseMatchingMedicalList(caseMatchingMedical).stream().forEach(oo -> vos.add(getCaseMatchingMedicalVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseMatchingMedicalVo>() : vos);
        return vo;
    }

    public CaseMatchingMedicalVo getCaseMatchingMedicalVo(CaseMatchingMedical caseMatchingMedical) {
        CaseMatchingMedicalVo vo = new CaseMatchingMedicalVo();
        BeanUtils.copyProperties(caseMatchingMedical, vo);
        vo.setOrganizationLevelName(baseDataDicService.getNameById(NumberUtils.isNumber(caseMatchingMedical.getOrganizationLevel()) ? Integer.parseInt(caseMatchingMedical.getOrganizationLevel()) : null));
        vo.setDistanceName(baseDataDicService.getNameById(caseMatchingMedical.getDistance()));
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
     * @param caseMatchingMedical
     * @return
     */
    public boolean addCaseMatchingMedical(CaseMatchingMedical caseMatchingMedical) {
        caseMatchingMedical.setCreator(commonService.thisUserAccount());
        return caseMatchingMedicalDao.addMatchingMedical(caseMatchingMedical);
    }

    /**
     * 编辑
     *
     * @param caseMatchingMedical
     * @return
     */
    public boolean updateCaseMatchingMedical(CaseMatchingMedical caseMatchingMedical) {
        return caseMatchingMedicalDao.updateMatchingMedical(caseMatchingMedical);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseMatchingMedical(Integer id) {
        return caseMatchingMedicalDao.deleteMatchingMedical(id);
    }

    public void upgradeVersion(CaseMatchingMedical po)throws Exception{
        if (po.getId()==null || po.getId().intValue() == 0){
            po.setCreator(commonService.thisUserAccount());
            po.setVersion(0);
            this.addCaseMatchingMedical(po);
        }else {
            CaseMatchingMedical oo = getCaseMatchingMedicalById(po.getId());
            if (oo.getVersion() == null){
                oo.setVersion(0);
            }
            int version = oo.getVersion() + 1;
            BeanUtils.copyProperties(po,oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            oo.setCreator(commonService.thisUserAccount());
            this.addCaseMatchingMedical(oo);
        }
    }

    /**
     * 根据查询条件判断是否有数据
     * @param esteteId
     * @return
     */
    public boolean hasMatchingMedicalData(Integer esteteId){
        return caseMatchingMedicalDao.countByEstateId(esteteId)>0;
    }
    
}
