package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseHouseFaceStreetDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseFaceStreet;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseFaceStreetVo;
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
 * @Date: 2018/9/18 10:09
 * @Description:
 */
@Service
public class CaseHouseFaceStreetService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseHouseFaceStreetDao caseHouseFaceStreetDao;
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
    public CaseHouseFaceStreet getCaseHouseFaceStreetById(Integer id) {
        return caseHouseFaceStreetDao.getHouseFaceStreetById(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseHouseFaceStreet
     * @return
     */
    public List<CaseHouseFaceStreet> getCaseHouseFaceStreetList(CaseHouseFaceStreet caseHouseFaceStreet) {
        return caseHouseFaceStreetDao.getHouseFaceStreetList(caseHouseFaceStreet);
    }

    public BootstrapTableVo getCaseHouseFaceStreetLists(CaseHouseFaceStreet caseHouseFaceStreet) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseHouseFaceStreetVo> vos = Lists.newArrayList();
        getCaseHouseFaceStreetList(caseHouseFaceStreet).stream().forEach(oo -> vos.add(getCaseHouseFaceStreetVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseHouseFaceStreetVo>() : vos);
        return vo;
    }

    public CaseHouseFaceStreetVo getCaseHouseFaceStreetVo(CaseHouseFaceStreet caseHouseFaceStreet) {
        CaseHouseFaceStreetVo vo = new CaseHouseFaceStreetVo();
        BeanUtils.copyProperties(caseHouseFaceStreet, vo);
        vo.setVisitorsFlowrateName(baseDataDicService.getNameById(caseHouseFaceStreet.getVisitorsFlowrate()));
        vo.setTrafficFlowName(baseDataDicService.getNameById(caseHouseFaceStreet.getTrafficFlow()));
        vo.setStreetLevelName(baseDataDicService.getNameById(caseHouseFaceStreet.getStreetLevel()));
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
     * @param caseHouseFaceStreet
     * @return
     */
    public boolean addCaseHouseFaceStreet(CaseHouseFaceStreet caseHouseFaceStreet) {
        caseHouseFaceStreet.setCreator(commonService.thisUserAccount());
        return caseHouseFaceStreetDao.addHouseFaceStreet(caseHouseFaceStreet);
    }

    /**
     * 编辑
     *
     * @param caseHouseFaceStreet
     * @return
     */
    public boolean updateCaseHouseFaceStreet(CaseHouseFaceStreet caseHouseFaceStreet) {
        return caseHouseFaceStreetDao.updateHouseFaceStreet(caseHouseFaceStreet);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseHouseFaceStreet(Integer id) {
        return caseHouseFaceStreetDao.deleteHouseFaceStreet(id);
    }

    public void upgradeVersion(CaseHouseFaceStreet oo) throws Exception {
        if (oo.getId() == null || oo.getId().intValue() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            oo.setVersion(0);
            this.addCaseHouseFaceStreet(oo);
        }
        if (oo.getId().intValue() >= 1) {
            CaseHouseFaceStreet po = this.getCaseHouseFaceStreetById(oo.getId());
            if (po.getVersion() == null){
                po.setVersion(0);
            }
            int version = po.getVersion() +1;
            BeanUtils.copyProperties(oo,po);
            po.setVersion(version);
            po.setCreator(commonService.thisUserAccount());
            po.setId(null);
            this.addCaseHouseFaceStreet(po);

        }
    }
}
