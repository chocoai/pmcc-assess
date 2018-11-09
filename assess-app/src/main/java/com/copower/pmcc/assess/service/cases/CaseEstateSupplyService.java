package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseEstateSupplyDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateSupply;
import com.copower.pmcc.assess.dto.output.cases.CaseEstateSupplyVo;
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
 * @Date: 2018/9/14 16:52
 * @Description:
 */
@Service
public class CaseEstateSupplyService {
    @Autowired
    private CaseEstateSupplyDao caseEstateSupplyDao;

    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseEstateSupply getCaseEstateSupplyById(Integer id) {
        return caseEstateSupplyDao.getEstateNetworkById(id);
    }

    public void upgradeVersion(CaseEstateSupply po)throws Exception{
        if (po.getId()==null || po.getId().intValue() == 0){
            po.setCreator(commonService.thisUserAccount());
            po.setVersion(0);
            caseEstateSupplyDao.addEstateNetwork(po);
        }else {
            CaseEstateSupply oo = getCaseEstateSupplyById(po.getId());
            if (oo.getVersion() == null){
                oo.setVersion(0);
            }
            int version = oo.getVersion() + 1;
            BeanCopyHelp.copyPropertiesIgnoreNull(po, oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            oo.setCreator(commonService.thisUserAccount());
            caseEstateSupplyDao.addEstateNetwork(oo);
        }
    }

    /**
     * 获取数据列表
     * @param examineEstateSupply
     * @return
     */
    public List<CaseEstateSupply> getCaseEstateSupplyList(CaseEstateSupply examineEstateSupply) {
        return caseEstateSupplyDao.getEstateNetworkList(examineEstateSupply);
    }

    public BootstrapTableVo getExamineEstateNetworkList(CaseEstateSupply oo){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseEstateSupplyVo> vos = Lists.newArrayList();
        getCaseEstateSupplyList(oo).stream().forEach(obj -> vos.add(getCaseEstateSupplyVo(obj)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseEstateSupplyVo>() : vos);
        return vo;
    }

    private CaseEstateSupplyVo getCaseEstateSupplyVo(CaseEstateSupply oo){
        CaseEstateSupplyVo vo = new CaseEstateSupplyVo();
        BeanUtils.copyProperties(oo,vo);
        if (!StringUtils.isEmpty(oo.getGrade())){
            if (org.apache.commons.lang.StringUtils.isNumeric(oo.getGrade())){
                List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SUPPLIER_GRADE);
                if (baseDataDic.size() >= 1){
                    for (BaseDataDic base:baseDataDic){
                        if (base.getId().equals(Integer.parseInt(oo.getGrade()))){
                            vo.setGradeName(base.getName());
                        }
                    }
                }
            }
        }
        if (!StringUtils.isEmpty(oo.getLineGrade())){
            if (org.apache.commons.lang.StringUtils.isNumeric(oo.getLineGrade())){
                List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_LINE_WATER_SUPPLY_PIPE_GRADE);
                if (baseDataDic.size() >= 1){
                    for (BaseDataDic base:baseDataDic){
                        if (base.getId().equals(Integer.parseInt(oo.getLineGrade()))){
                            vo.setLineGradeName(base.getName());
                        }
                    }
                }
            }
        }
        return vo;
    }


    /**
     * 新增
     * @param examineEstateSupply
     * @return
     */
    public boolean addCaseEstateSupply(CaseEstateSupply examineEstateSupply) {
        examineEstateSupply.setCreator(commonService.thisUserAccount());
        return caseEstateSupplyDao.addEstateNetwork(examineEstateSupply);
    }

    /**
     * 编辑
     * @param examineEstateSupply
     * @return
     */
    public boolean updateCaseEstateSupply(CaseEstateSupply examineEstateSupply) {
        return caseEstateSupplyDao.updateEstateNetwork(examineEstateSupply);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteCaseEstateSupply(Integer id){
        return caseEstateSupplyDao.deleteEstateNetwork(id);
    }
}
