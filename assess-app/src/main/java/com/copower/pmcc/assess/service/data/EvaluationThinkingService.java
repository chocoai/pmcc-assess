package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.EvaluationThinkingDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.EvaluationThinking;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationThinkingVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 13426 on 2018/4/26.
 */
@Service(value = "evaluationThinkingService")
public class EvaluationThinkingService {

    @Resource
    private EvaluationThinkingDao evaluationThinkingDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private BaseDataDicService baseDataDicService;

    @Transactional
    public boolean add(EvaluationThinkingDto evaluationThinkingDto){
        if (evaluationThinkingDto.getCreator()==null)evaluationThinkingDto.setCreator(commonService.thisUserAccount());
        if (evaluationThinkingDto.getGmtCreated()==null)evaluationThinkingDto.setGmtCreated(new Date());
        return evaluationThinkingDao.add(evaluationThinkingDto);
    }

    @Transactional
    public boolean update(EvaluationThinkingDto evaluationThinkingDto){
        if (evaluationThinkingDto.getCreator()==null)evaluationThinkingDto.setCreator(commonService.thisUserAccount());
        if (evaluationThinkingDto.getGmtCreated()==null)evaluationThinkingDto.setGmtCreated(new Date());
        return evaluationThinkingDao.update(evaluationThinkingDto);
    }

    @Transactional
    public boolean remove(Integer id){
        return evaluationThinkingDao.remove(id);
    }

    @Transactional(readOnly = true)
    public EvaluationThinkingDto get(Integer id){
        return evaluationThinkingDao.get(id);
    }

    @Transactional(readOnly = true)
    public List<EvaluationThinking> list(String method){
        try {
            if (method==null || method=="") {
                return evaluationThinkingDao.list(null);
            }else {
                return evaluationThinkingDao.list(method);
            }
        }catch (Exception e){
            try {
                throw e;
            }catch (Exception e1){

            }
        }
        return null;
    }

    public BootstrapTableVo listVo(String method){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationThinkingVo> voList = vosChange(list(method));
        vo.setRows(CollectionUtils.isEmpty(voList) ? new ArrayList<EvaluationThinkingVo>() : voList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    private List<EvaluationThinkingVo> vosChange(List<EvaluationThinking> evaluationThinkings){
        List<EvaluationThinkingVo> evaluationThinkingVoList = new ArrayList<>();
        evaluationThinkings.forEach(evaluationThinking -> {
            evaluationThinkingVoList.add(change(evaluationThinking));
        });
        return evaluationThinkingVoList;
    }

    private EvaluationThinkingVo change(EvaluationThinking evaluationThinking){
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_THINKING);
        EvaluationThinkingVo evaluationThinkingVo = new EvaluationThinkingVo();
        BeanUtils.copyProperties(evaluationThinking,evaluationThinkingVo);
        if (evaluationThinking.getMethod()!=null && evaluationThinking.getMethod()!="" ){
            evaluationThinkingVo.setMethodStr(baseDataDics.get(Integer.parseInt(evaluationThinking.getMethod())).getName());
        }
        return evaluationThinkingVo;
    }

    public String changeMethod(String methodStr){
        Integer key = null;
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_THINKING);
        inner:
        for (BaseDataDic b : baseDataDics) {
            for (int i = 0; i < baseDataDics.size() - 1; i++) {
                String v = baseDataDics.get(i).getName();
                if (methodStr.equals(v)) {
                    key = i;
                    break inner;
                }
            }
        }
        return key+"";
    }


}
