package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.StageWeightProportionDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.StageWeightProportion;
import com.copower.pmcc.assess.dto.output.data.StageWeightProportionVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "stageWeightProportionService")
public class StageWeightProportionService {

    @Autowired
    private StageWeightProportionDao stageWeightProportionDao;

    @Autowired
    private BaseDataDicService baseDataDicService;

    public BootstrapTableVo getList(Integer entrustmentPurpose,Integer stage) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<StageWeightProportion> list = stageWeightProportionDao.getList(entrustmentPurpose,stage);
        List<StageWeightProportionVo> stageWeightProportionVos = getVoList(list);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(stageWeightProportionVos) ? new ArrayList<StageWeightProportionVo>() : stageWeightProportionVos);
        return vo;
    }

    private List<StageWeightProportionVo> getVoList(List<StageWeightProportion> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            StageWeightProportionVo stageWeightProportionVo = new StageWeightProportionVo();
            BeanUtils.copyProperties(p, stageWeightProportionVo);
            if (p.getEntrustPurpose() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getEntrustPurpose());
                if (baseDataDic != null)
                    stageWeightProportionVo.setEntrustPurposeName(baseDataDic.getName());
            }
            if(p.getStage() != null){
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getStage());
                if(baseDataDic != null){
                    stageWeightProportionVo.setStageName(baseDataDic.getName());
                }
            }

            return stageWeightProportionVo;
        });
    }

    public boolean save(StageWeightProportion stageWeightProportion) throws BusinessException {
        if (stageWeightProportion == null) {
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        }
        if (stageWeightProportion.getId() != null && stageWeightProportion.getId() > 0) {
            return stageWeightProportionDao.update(stageWeightProportion);
        } else {
            return stageWeightProportionDao.save(stageWeightProportion);
        }

    }

    public boolean delete(Integer id) throws BusinessException {
        if (id == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        return stageWeightProportionDao.delete(id);
    }
}
