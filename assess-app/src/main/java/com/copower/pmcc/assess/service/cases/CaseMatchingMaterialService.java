package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseMatchingMaterialDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingMaterial;
import com.copower.pmcc.assess.dto.output.cases.CaseMatchingMaterialVo;
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
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/17 15:37
 * @Description:
 */
@Service
public class CaseMatchingMaterialService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseMatchingMaterialDao caseMatchingMaterialDao;

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
    public CaseMatchingMaterial getCaseMatchingMaterialById(Integer id) {
        return caseMatchingMaterialDao.getMatchingMaterialById(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseMatchingMaterial
     * @return
     */
    public List<CaseMatchingMaterial> getCaseMatchingMaterialList(CaseMatchingMaterial caseMatchingMaterial) {
        return caseMatchingMaterialDao.getMatchingMaterialList(caseMatchingMaterial);
    }

    public BootstrapTableVo getCaseMatchingMaterialLists(CaseMatchingMaterial caseMatchingMaterial) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseMatchingMaterialVo> vos = Lists.newArrayList();
        getCaseMatchingMaterialList(caseMatchingMaterial).stream().forEach(oo -> vos.add(getCaseMatchingMaterialVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseMatchingMaterialVo>() : vos);
        return vo;
    }

    public CaseMatchingMaterialVo getCaseMatchingMaterialVo(CaseMatchingMaterial caseMatchingMaterial) {
        CaseMatchingMaterialVo vo = new CaseMatchingMaterialVo();
        BeanUtils.copyProperties(caseMatchingMaterial, vo);
        vo.setCategoryName(baseDataDicService.getNameById(caseMatchingMaterial.getCategory()));
        vo.setScaleName(baseDataDicService.getNameById(caseMatchingMaterial.getScale()));
        vo.setDistanceName(baseDataDicService.getNameById(caseMatchingMaterial.getDistance()));
        return vo;
    }

    private String getValue(String key, Integer v) {
        StringBuilder builder = new StringBuilder(1024);
        if (!StringUtils.isEmpty(key)){
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
        }
        return builder.toString();
    }

    /**
     * 新增
     *
     * @param caseMatchingMaterial
     * @return
     */
    public boolean addCaseMatchingMaterial(CaseMatchingMaterial caseMatchingMaterial) {
        return caseMatchingMaterialDao.addMatchingMaterial(caseMatchingMaterial);
    }

    /**
     * 编辑
     *
     * @param caseMatchingMaterial
     * @return
     */
    public boolean updateCaseMatchingMaterial(CaseMatchingMaterial caseMatchingMaterial) {
        return caseMatchingMaterialDao.updateMatchingMaterial(caseMatchingMaterial);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseMatchingMaterial(Integer id) {
        return caseMatchingMaterialDao.deleteMatchingMaterial(id);
    }

    /**
     * 根据查询条件判断是否有数据
     * @param esteteId
     * @return
     */
    public boolean hasMatchingMaterialData(Integer esteteId){
        return caseMatchingMaterialDao.countByEstateId(esteteId)>0;
    }
}
