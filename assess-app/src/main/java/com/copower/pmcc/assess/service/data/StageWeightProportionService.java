package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.StageWeightProportionDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.StageWeightProportion;
import com.copower.pmcc.assess.dto.output.data.ProportionTempVo;
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

    public BootstrapTableVo getList(Integer entrustmentPurpose) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<StageWeightProportion> list = stageWeightProportionDao.getList(entrustmentPurpose);
        List<StageWeightProportionVo> stageWeightProportionVos = getVoList(list);
        List<ProportionTempVo> proportionTempVos = getStageProportion(stageWeightProportionVos);    //把相同委托目的添加到一起
        page.setTotal(proportionTempVos.size());    //同步数据
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(proportionTempVos) ? new ArrayList<ProportionTempVo>() : proportionTempVos);
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

    public boolean delete(Integer entrustPurpose) throws BusinessException {
        if (entrustPurpose == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        return stageWeightProportionDao.delete(entrustPurpose);
    }

    //拼接委托目的显示
    private List<ProportionTempVo> getStageProportion(List<StageWeightProportionVo> stageWeightProportionVos){
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE);  //委托目的
        List<ProportionTempVo> proportionTempVos = new ArrayList<ProportionTempVo>();
        for(BaseDataDic baseDataDic : baseDataDics){    //循环委托目的取name
            String name = baseDataDic.getName();    //委托目的具体名字
            List<String> str = new ArrayList<String>();
            ProportionTempVo proportionTempVo = new ProportionTempVo();
                for( StageWeightProportionVo stageWeightProportionVo : stageWeightProportionVos) {
                    if (stageWeightProportionVo.getEntrustPurposeName().equals(name)) {
                        str.add(stageWeightProportionVo.getStageName() + "：" + stageWeightProportionVo.getProportion());    //循环数据合并在一起
                        proportionTempVo.setEntrustPurpose(stageWeightProportionVo.getEntrustPurpose());
                    }
                }
                if(str.size() > 0){
                    String stageProportionName = String.join(",", str); //转字符串
                    proportionTempVo.setEntrustPurposeName(name);
                    proportionTempVo.setStageProportionName(stageProportionName);
                    proportionTempVos.add(proportionTempVo);    //为显示用的临时vo
                }
        }
        return proportionTempVos;
    }
}
