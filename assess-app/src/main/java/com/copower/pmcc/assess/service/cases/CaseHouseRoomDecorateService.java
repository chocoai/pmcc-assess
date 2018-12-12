package com.copower.pmcc.assess.service.cases;

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
        vo.setMaterialName(baseDataDicService.getNameById(caseHouseRoomDecorate.getMaterial()));
        vo.setConstructionTechnologyName(baseDataDicService.getNameById(caseHouseRoomDecorate.getConstructionTechnology()));
        vo.setMaterialPriceName(baseDataDicService.getNameById(caseHouseRoomDecorate.getMaterialPrice()));
        return vo;
    }

    /**
     * 新增
     *
     * @param caseHouseRoomDecorate
     * @return
     */
    public boolean addCaseHouseRoomDecorate(CaseHouseRoomDecorate caseHouseRoomDecorate) {
        return caseHouseRoomDecorateDao.addHouseRoomDecorate(caseHouseRoomDecorate);
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
