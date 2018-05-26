package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.EvaluationThinkingDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.EvaluationMethod;
import com.copower.pmcc.assess.dal.entity.EvaluationThinking;
import com.copower.pmcc.assess.dto.input.data.EvaluationMethodDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationThinkingVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 评估技术思路
 * Created by 13426 on 2018/4/26.
 */
@Service(value = "evaluationThinkingService")
public class EvaluationThinkingService {

    @Resource
    private EvaluationThinkingDao evaluationThinkingDao;
    @Autowired
    private EvaluationPrincipleService principleService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private BaseDataDicService baseDataDicService;

    @Transactional
    public boolean add(EvaluationThinkingDto evaluationThinkingDto) {
        if (evaluationThinkingDto.getCreator() == null)
            evaluationThinkingDto.setCreator(commonService.thisUserAccount());
        if (evaluationThinkingDto.getGmtCreated() == null) evaluationThinkingDto.setGmtCreated(new Date());
        return evaluationThinkingDao.add(evaluationThinkingDto);
    }

    @Transactional
    public boolean update(EvaluationThinkingDto evaluationThinkingDto) {
        if (evaluationThinkingDto.getCreator() == null)
            evaluationThinkingDto.setCreator(commonService.thisUserAccount());
        if (evaluationThinkingDto.getGmtCreated() == null) evaluationThinkingDto.setGmtCreated(new Date());
        return evaluationThinkingDao.update(evaluationThinkingDto);
    }

    @Transactional
    public boolean remove(Integer id) {
        return evaluationThinkingDao.remove(id);
    }

    @Transactional(readOnly = true)
    public EvaluationThinkingDto get(Integer id) {
        return evaluationThinkingDao.get(id);
    }

    public List<EvaluationThinking> getListByMethod(Integer method){
        return evaluationThinkingDao.getListByMethod(method);
    }

    @Transactional(readOnly = true)
    public List<EvaluationThinking> list(String name) {
        return evaluationThinkingDao.list(name);
    }

    public BootstrapTableVo listVo(String method) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationThinkingVo> voList = vosChange(list(method));
        vo.setRows(CollectionUtils.isEmpty(voList) ? new ArrayList<EvaluationThinkingVo>() : voList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    private List<EvaluationThinkingVo> vosChange(List<EvaluationThinking> evaluationThinkings) {
        List<EvaluationThinkingVo> evaluationThinkingVoList = new ArrayList<>();
        evaluationThinkings.forEach(evaluationThinking -> {
            evaluationThinkingVoList.add(change(evaluationThinking));
        });
        return evaluationThinkingVoList;
    }

    private EvaluationThinkingVo change(EvaluationThinking evaluationThinking) {
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        EvaluationThinkingVo evaluationThinkingVo = new EvaluationThinkingVo();
        BeanUtils.copyProperties(evaluationThinking, evaluationThinkingVo);
        if (StringUtils.isNotBlank(evaluationThinking.getMethod())) {
            String s = StringUtils.replacePattern(evaluationThinking.getMethod(), "^,+|,+$", "");
            List<Integer> integerList = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(s));
            String methodString=new String();
            for (Integer integer : integerList) {
                for (BaseDataDic baseDataDic : baseDataDics) {
                    if(integer.equals(baseDataDic.getId()))
                        methodString+=baseDataDic.getName()+",";
                }
            }
            evaluationThinkingVo.setMethodStr(StringUtils.replacePattern(methodString, "^,+|,+$", ""));
        }
        return evaluationThinkingVo;
    }

    /**
     * 将所有模板以评估方法作为分类
     * @return
     */
    public Map<Integer,List<EvaluationThinking>> getEvaluationThinkingMap(){
        Map<Integer,List<EvaluationThinking>> map= Maps.newConcurrentMap();
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        if(CollectionUtils.isNotEmpty(baseDataDics)){
            for (BaseDataDic baseDataDic : baseDataDics) {
                List<EvaluationThinking> thinkingList = getListByMethod(baseDataDic.getId());
                if(CollectionUtils.isNotEmpty(thinkingList)){
                    map.put(baseDataDic.getId(),thinkingList);
                }
            }
        }
        return map;
    }

}
