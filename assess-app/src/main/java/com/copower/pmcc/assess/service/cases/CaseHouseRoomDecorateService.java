package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseHouseRoomDecorateDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseRoomDecorate;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseRoomDecorateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 10:15
 * @Description:
 */
@Service
public class CaseHouseRoomDecorateService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseHouseRoomDecorateDao caseHouseRoomDecorateDao;
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
    public CaseHouseRoomDecorate getCaseHouseRoomDecorateById(Integer id) {
        return caseHouseRoomDecorateDao.getHouseRoomDecorateById(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseHouseRoomDecorate
     * @return
     */
    public List<CaseHouseRoomDecorate> getCaseHouseRoomDecorateList(CaseHouseRoomDecorate caseHouseRoomDecorate) {
        return caseHouseRoomDecorateDao.getHouseRoomDecorateList(caseHouseRoomDecorate);
    }

    public BootstrapTableVo getCaseHouseRoomDecorateLists(CaseHouseRoomDecorate caseHouseRoomDecorate) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseHouseRoomDecorateVo> vos = Lists.newArrayList();
        getCaseHouseRoomDecorateList(caseHouseRoomDecorate).stream().forEach(oo -> vos.add(getCaseHouseRoomDecorateVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseHouseRoomDecorateVo>() : vos);
        return vo;
    }

    public CaseHouseRoomDecorateVo getCaseHouseRoomDecorateVo(CaseHouseRoomDecorate caseHouseRoomDecorate) {
        CaseHouseRoomDecorateVo vo = new CaseHouseRoomDecorateVo();
        BeanUtils.copyProperties(caseHouseRoomDecorate, vo);
        if (caseHouseRoomDecorate.getPart() != null) {
            vo.setPartName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATION_PART, caseHouseRoomDecorate.getPart()));
        }
        if (caseHouseRoomDecorate.getMaterial() != null) {
            vo.setMaterialName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATING_MATERIAL, caseHouseRoomDecorate.getMaterial()));
        }
        if (StringUtils.isNumeric(caseHouseRoomDecorate.getMaterialPrice())){
            vo.setMaterialPriceName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_MATERIAL_PRICE,Integer.parseInt(caseHouseRoomDecorate.getMaterialPrice())));
        }
        if (StringUtils.isNumeric(caseHouseRoomDecorate.getConstructionTechnology())) {
            vo.setConstructionTechnologyName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_CONSTRUCTION_TECHNOLOGY,Integer.parseInt(caseHouseRoomDecorate.getConstructionTechnology())));
        }
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
     * @param caseHouseRoomDecorate
     * @return
     */
    public boolean addCaseHouseRoomDecorate(CaseHouseRoomDecorate caseHouseRoomDecorate) {
        caseHouseRoomDecorate.setCreator(commonService.thisUserAccount());
        return caseHouseRoomDecorateDao.addHouseRoomDecorate(caseHouseRoomDecorate);
    }

    public void upgradeVersion(CaseHouseRoomDecorate caseHouseRoomDecorate)throws Exception{
        if (caseHouseRoomDecorate.getId()==null || caseHouseRoomDecorate.getId().intValue()==0){
            caseHouseRoomDecorate.setCreator(commonService.thisUserAccount());
            caseHouseRoomDecorate.setVersion(0);
            caseHouseRoomDecorateDao.addHouseRoomDecorate(caseHouseRoomDecorate);
        }else {
            CaseHouseRoomDecorate oo = getCaseHouseRoomDecorateById(caseHouseRoomDecorate.getId());
            if (oo != null){
                if (oo.getVersion()==null){
                    oo.setVersion(0);
                }
                int version = oo.getVersion() +1;
                BeanUtils.copyProperties(caseHouseRoomDecorate,oo);
                oo.setVersion(version);
                oo.setId(null);
                oo.setCreator(commonService.thisUserAccount());
                caseHouseRoomDecorateDao.addHouseRoomDecorate(oo);
            }
        }
    }

    /**
     * 编辑
     *
     * @param caseHouseRoomDecorate
     * @return
     */
    public boolean updateCaseHouseRoomDecorate(CaseHouseRoomDecorate caseHouseRoomDecorate) {
        return caseHouseRoomDecorateDao.updateHouseRoomDecorate(caseHouseRoomDecorate);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseHouseRoomDecorate(Integer id) {
        return caseHouseRoomDecorateDao.deleteHouseRoomDecorate(id);
    }

}
