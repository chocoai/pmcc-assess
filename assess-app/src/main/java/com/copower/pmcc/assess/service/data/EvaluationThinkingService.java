package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationThinkingDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationThinking;
import com.copower.pmcc.assess.dto.output.data.DataEvaluationThinkingVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 评估技术思路
 * Created by 13426 on 2018/4/26.
 */
@Service(value = "evaluationThinkingService")
public class EvaluationThinkingService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataCommonService dataCommonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationThinkingDao evaluationThinkingDao;

    /**
     * 保存数据
     *
     * @param evaluationThinking
     */
    public void saveAndUpdate(DataEvaluationThinking evaluationThinking) {
        if (evaluationThinking.getId() != null && evaluationThinking.getId() > 0) {
            evaluationThinkingDao.updateThinking(evaluationThinking);
        } else {
            evaluationThinking.setCreator(commonService.thisUserAccount());
            evaluationThinkingDao.addThinking(evaluationThinking);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean removeThinking(Integer id) {
        return evaluationThinkingDao.removeThinking(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public DataEvaluationThinking getThinking(Integer id) {
        return evaluationThinkingDao.getThinking(id);
    }


    /**
     * 获取数据列表
     *
     * @param name
     * @return
     */
    public BootstrapTableVo getThinkingList(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataEvaluationThinking> hypothesisList = evaluationThinkingDao.getThinkingList(name);
        List<DataEvaluationThinkingVo> vos = LangUtils.transform(hypothesisList, p -> getThinkingVo(p));
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<DataEvaluationThinkingVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    /**
     * 根据委估目的及评估方法获取数据列表
     *
     * @param method
     * @return
     */
    public List<DataEvaluationThinking> getThinkingList(Integer method) {
        String methodStr = new String();
        String purposeStr = new String();
        if (method != null && method > 0) {
            methodStr = String.format(",%s,", method);
        }
        return evaluationThinkingDao.getThinkingList(methodStr);
    }


    public DataEvaluationThinkingVo getThinkingVo(DataEvaluationThinking evaluationThinking) {
        if (evaluationThinking == null) return null;
        List<BaseDataDic> methodDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        DataEvaluationThinkingVo evaluationThinkingVo = new DataEvaluationThinkingVo();
        BeanUtils.copyProperties(evaluationThinking, evaluationThinkingVo);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(evaluationThinking.getMethod())) {
            evaluationThinkingVo.setMethodStr(dataCommonService.getDataDicName(methodDicList, evaluationThinking.getMethod()));
        }
        return evaluationThinkingVo;
    }

    /**
     * 将所有模板以评估方法作为分类
     *
     * @return
     */
    public Map<Integer, List<DataEvaluationThinking>> getEvaluationThinkingMap() {
        Map<Integer, List<DataEvaluationThinking>> map = Maps.newConcurrentMap();
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        if (CollectionUtils.isNotEmpty(baseDataDics)) {
            for (BaseDataDic baseDataDic : baseDataDics) {
                List<DataEvaluationThinking> thinkingList = evaluationThinkingDao.getThinkingListByMethod(String.valueOf(baseDataDic.getId()));
                if (CollectionUtils.isNotEmpty(thinkingList)) {
                    map.put(baseDataDic.getId(), thinkingList);
                }
            }
        }
        return map;
    }
}
