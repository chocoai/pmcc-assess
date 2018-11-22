package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareBuildEconomicIndicatorsCenterDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEconomicIndicators;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEconomicIndicatorsCenter;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/22 10:25
 * @Description:在建工程 规划经济指标 中间表
 */
@Service
public class DeclareBuildEconomicIndicatorsCenterService {
    @Autowired
    private DeclareBuildEconomicIndicatorsService declareBuildEconomicIndicatorsService;
    @Autowired
    private DeclareBuildEconomicIndicatorsCenterDao declareBuildEconomicIndicatorsCenterDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateDeclareBuildEconomicIndicatorsCenter(DeclareBuildEconomicIndicatorsCenter declareBuildEconomicIndicatorsCenter) {
        if (declareBuildEconomicIndicatorsCenter.getId() == null || declareBuildEconomicIndicatorsCenter.getId().intValue() == 0) {
            declareBuildEconomicIndicatorsCenter.setCreator(commonService.thisUserAccount());
            Integer id = declareBuildEconomicIndicatorsCenterDao.addDeclareBuildEconomicIndicatorsCenter(declareBuildEconomicIndicatorsCenter);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareBuildEconomicIndicatorsCenter.class), id);
            return id;
        } else {
            declareBuildEconomicIndicatorsCenterDao.updateDeclareBuildEconomicIndicatorsCenter(declareBuildEconomicIndicatorsCenter);
            return null;
        }
    }

    public BootstrapTableVo getDeclareBuildEconomicIndicatorsCenterListVos(DeclareBuildEconomicIndicatorsCenter declareBuildEconomicIndicatorsCenter) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareBuildEconomicIndicatorsCenter> vos = declareBuildEconomicIndicatorsCenterList(declareBuildEconomicIndicatorsCenter);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareBuildEconomicIndicatorsCenter> declareBuildEconomicIndicatorsCenterList(DeclareBuildEconomicIndicatorsCenter oo) {
        return declareBuildEconomicIndicatorsCenterDao.getDeclareBuildEconomicIndicatorsCenterList(oo);
    }

    public DeclareBuildEconomicIndicatorsCenter getDeclareBuildEconomicIndicatorsCenterById(Integer id) {
        return declareBuildEconomicIndicatorsCenterDao.getDeclareBuildEconomicIndicatorsCenterById(id);
    }

    public void removeDeclareBuildEconomicIndicatorsCenter(DeclareBuildEconomicIndicatorsCenter declareBuildEconomicIndicatorsCenter) {
        DeclareBuildEconomicIndicators query = new DeclareBuildEconomicIndicators();
        query.setPid(declareBuildEconomicIndicatorsCenter.getId());
        List<DeclareBuildEconomicIndicators> declareBuildEconomicIndicatorsList = declareBuildEconomicIndicatorsService.declareBuildEconomicIndicatorsList(query);
        if (!ObjectUtils.isEmpty(declareBuildEconomicIndicatorsList)){
            for (DeclareBuildEconomicIndicators oo:declareBuildEconomicIndicatorsList){
                declareBuildEconomicIndicatorsService.removeDeclareBuildEconomicIndicators(oo);
            }
        }
        declareBuildEconomicIndicatorsCenterDao.removeDeclareBuildEconomicIndicatorsCenter(declareBuildEconomicIndicatorsCenter);
    }
    
}
