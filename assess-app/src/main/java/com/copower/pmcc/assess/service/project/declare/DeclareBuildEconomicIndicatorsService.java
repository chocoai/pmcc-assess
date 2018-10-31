package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareBuildEconomicIndicatorsDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildEconomicIndicators;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeclareBuildEconomicIndicatorsService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareBuildEconomicIndicatorsDao declareBuildEconomicIndicatorsDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public Integer saveEconomicIndicators(DeclareBuildEconomicIndicators declareBuildEconomicIndicators) {
        if (declareBuildEconomicIndicators.getId() == null || declareBuildEconomicIndicators.getId().intValue() == 0) {
            declareBuildEconomicIndicators.setCreator(commonService.thisUserAccount());
            Integer id = declareBuildEconomicIndicatorsDao.addDeclareBuildEconomicIndicators(declareBuildEconomicIndicators);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareBuildEconomicIndicators.class), id);
            return id;
        } else {
            declareBuildEconomicIndicatorsDao.updateDeclareBuildEconomicIndicators(declareBuildEconomicIndicators);
            return null;
        }
    }

    public BootstrapTableVo getEconomicIndicatorsListVos(DeclareBuildEconomicIndicators declareBuildEconomicIndicators) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareBuildEconomicIndicators> vos = declareBuildEconomicIndicatorsList(declareBuildEconomicIndicators);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    /**
     * 根据主表id获取数据
     *
     * @param pid
     * @return
     */
    public List<DeclareBuildEconomicIndicators> getEconomicIndicatorsByPid(Integer pid) {
        DeclareBuildEconomicIndicators where = new DeclareBuildEconomicIndicators();
        where.setPid(pid);
        List<DeclareBuildEconomicIndicators> indicatorsList = declareBuildEconomicIndicatorsDao.getDeclareBuildEconomicIndicatorsList(where);
        if (CollectionUtils.isEmpty(indicatorsList)) {
            //初始化数据 获取数据字典配置的数据
            List<BaseDataDic> dataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.PROJECT_DECLARE_ECONOMIC_INDICATORS);
            if (CollectionUtils.isNotEmpty(dataDicList)) {
                for (BaseDataDic baseDataDic : dataDicList) {
                    DeclareBuildEconomicIndicators economicIndicators = new DeclareBuildEconomicIndicators();
                    economicIndicators.setPid(pid);
                    economicIndicators.setBaseDataDicId(baseDataDic.getId());
                    economicIndicators.setName(baseDataDic.getName());
                    declareBuildEconomicIndicatorsDao.addDeclareBuildEconomicIndicators(economicIndicators);
                }
                indicatorsList = declareBuildEconomicIndicatorsDao.getDeclareBuildEconomicIndicatorsList(where);
            }
        }
        return indicatorsList;
    }

    public List<DeclareBuildEconomicIndicators> declareBuildEconomicIndicatorsList(DeclareBuildEconomicIndicators oo) {
        return declareBuildEconomicIndicatorsDao.getDeclareBuildEconomicIndicatorsList(oo);
    }

    public DeclareBuildEconomicIndicators getDeclareBuildEconomicIndicatorsById(Integer id) {
        return declareBuildEconomicIndicatorsDao.getDeclareBuildEconomicIndicatorsById(id);
    }

    public void removeDeclareBuildEconomicIndicators(DeclareBuildEconomicIndicators declareBuildEconomicIndicators) {
        declareBuildEconomicIndicatorsDao.removeDeclareBuildEconomicIndicators(declareBuildEconomicIndicators);
    }
}
