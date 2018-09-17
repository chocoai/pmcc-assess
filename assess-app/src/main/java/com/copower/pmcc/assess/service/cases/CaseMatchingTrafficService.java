package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseMatchingTrafficDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingTraffic;
import com.copower.pmcc.assess.dto.output.cases.CaseMatchingTrafficVo;
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
 * @Date: 2018/9/17 15:40
 * @Description:
 */
@Service
public class CaseMatchingTrafficService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseMatchingTrafficDao caseMatchingTrafficDao;

    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseMatchingTraffic getMatchingTrafficById(Integer id) {
        return caseMatchingTrafficDao.getMatchingTrafficById(id);
    }

    /**
     * 获取数据列表
     * @param caseMatchingTraffic
     * @return
     */
    public List<CaseMatchingTraffic> getMatchingTrafficList(CaseMatchingTraffic caseMatchingTraffic) {
        return caseMatchingTrafficDao.getMatchingTrafficList(caseMatchingTraffic);
    }

    public BootstrapTableVo getCaseMatchingTrafficList(CaseMatchingTraffic caseMatchingTraffic){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseMatchingTrafficVo> vos = Lists.newArrayList();
        getMatchingTrafficList(caseMatchingTraffic).stream().forEach(oo -> vos.add(getCaseMatchingTrafficVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseMatchingTrafficVo>() : vos);
        return vo;
    }

    public CaseMatchingTrafficVo getCaseMatchingTrafficVo(CaseMatchingTraffic caseMatchingTraffic){
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_DISTANCE);
        CaseMatchingTrafficVo vo = new CaseMatchingTrafficVo();
        if (caseMatchingTraffic.getDistance()!=null){
            for (BaseDataDic base:baseDataDic){
                if (base.getId().equals(caseMatchingTraffic.getDistance())){
                    vo.setDistanceName(base.getName());
                }
            }
        }
        BeanUtils.copyProperties(caseMatchingTraffic,vo);
        return vo;
    }

    /**
     * 新增
     * @param caseMatchingTraffic
     * @return
     */
    public boolean addMatchingTraffic(CaseMatchingTraffic caseMatchingTraffic) {
        caseMatchingTraffic.setCreator(commonService.thisUserAccount());
        return caseMatchingTrafficDao.addMatchingTraffic(caseMatchingTraffic);
    }

    /**
     * 编辑
     * @param caseMatchingTraffic
     * @return
     */
    public boolean updateMatchingTraffic(CaseMatchingTraffic caseMatchingTraffic) {
        return caseMatchingTrafficDao.updateMatchingTraffic(caseMatchingTraffic);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingTraffic(Integer id){
        return caseMatchingTrafficDao.deleteMatchingTraffic(id);
    }

}
