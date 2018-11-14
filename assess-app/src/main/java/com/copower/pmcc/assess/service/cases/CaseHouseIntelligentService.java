package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseHouseIntelligentDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseIntelligent;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseIntelligentVo;
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
 * @Date: 2018/9/18 10:11
 * @Description:
 */
@Service
public class CaseHouseIntelligentService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseHouseIntelligentDao caseHouseIntelligentDao;
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
    public CaseHouseIntelligent getCaseHouseIntelligentById(Integer id) {
        return caseHouseIntelligentDao.getHouseIntelligentById(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseHouseIntelligent
     * @return
     */
    public List<CaseHouseIntelligent> getCaseHouseIntelligentList(CaseHouseIntelligent caseHouseIntelligent) {
        return caseHouseIntelligentDao.getHouseIntelligentList(caseHouseIntelligent);
    }

    public BootstrapTableVo getCaseHouseIntelligentLists(CaseHouseIntelligent caseHouseIntelligent) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseHouseIntelligentVo> vos = Lists.newArrayList();
        getCaseHouseIntelligentList(caseHouseIntelligent).stream().forEach(oo -> vos.add(getCaseHouseIntelligentVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseHouseIntelligentVo>() : vos);
        return vo;
    }

    public CaseHouseIntelligentVo getCaseHouseIntelligentVo(CaseHouseIntelligent caseHouseIntelligent) {
        CaseHouseIntelligentVo vo = new CaseHouseIntelligentVo();
        BeanUtils.copyProperties(caseHouseIntelligent, vo);
        vo.setIntelligentSystemName(baseDataDicService.getNameById(caseHouseIntelligent.getIntelligentSystem()));
        vo.setMonitoringSystemName(baseDataDicService.getNameById(caseHouseIntelligent.getMonitoringSystem()));
        vo.setInternalCommunicationName(baseDataDicService.getNameById(caseHouseIntelligent.getInternalCommunication()));
        vo.setLampsLanternsName(baseDataDicService.getNameById(caseHouseIntelligent.getLampsLanterns()));
        vo.setSwitchCircuitName(baseDataDicService.getNameById(caseHouseIntelligent.getSwitchCircuit()));
        vo.setWireErectionName(baseDataDicService.getNameById(caseHouseIntelligent.getWireErection()));
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
     * @param caseHouseIntelligent
     * @return
     */
    public boolean addCaseHouseIntelligent(CaseHouseIntelligent caseHouseIntelligent) {
        caseHouseIntelligent.setCreator(commonService.thisUserAccount());
        return caseHouseIntelligentDao.addHouseIntelligent(caseHouseIntelligent);
    }

    /**
     * 编辑
     *
     * @param caseHouseIntelligent
     * @return
     */
    public boolean updateCaseHouseIntelligent(CaseHouseIntelligent caseHouseIntelligent) {
        return caseHouseIntelligentDao.updateHouseIntelligent(caseHouseIntelligent);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseHouseIntelligent(Integer id) {
        return caseHouseIntelligentDao.deleteHouseIntelligent(id);
    }

    public void upgradeVersion(CaseHouseIntelligent oo) throws Exception {
        if (oo.getId() == null || oo.getId().intValue() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            oo.setVersion(0);
            this.addCaseHouseIntelligent(oo);
        }
        if (oo.getId().intValue() >= 1) {
            CaseHouseIntelligent po = this.getCaseHouseIntelligentById(oo.getId());
            if (po.getVersion() == null){
                po.setVersion(0);
            }
            int version = po.getVersion() +1;
            BeanUtils.copyProperties(oo,po);
            po.setVersion(version);
            po.setCreator(commonService.thisUserAccount());
            po.setId(null);
            this.addCaseHouseIntelligent(po);
        }
    }

}
