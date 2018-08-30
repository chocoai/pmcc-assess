package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationHypothesisDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationHypothesis;
import com.copower.pmcc.assess.dto.output.data.DataEvaluationHypothesisVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.1.2.12	评估假设
 * Created by 13426 on 2018/4/28.
 */
@Service
public class EvaluationHypothesisService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataCommonService dataCommonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationHypothesisDao evaluationHypothesisDao;

    /**
     * 保存数据
     *
     * @param evaluationHypothesis
     */
    public void saveAndUpdate(DataEvaluationHypothesis evaluationHypothesis) {
        if (evaluationHypothesis.getId() != null && evaluationHypothesis.getId() > 0) {
            evaluationHypothesisDao.updateHypothesis(evaluationHypothesis);
        } else {
            evaluationHypothesis.setCreator(commonService.thisUserAccount());
            evaluationHypothesisDao.addHypothesis(evaluationHypothesis);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean removeHypothesis(Integer id) {
        return evaluationHypothesisDao.removeHypothesis(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public DataEvaluationHypothesis getHypothesis(Integer id) {
        return evaluationHypothesisDao.getHypothesis(id);
    }


    /**
     * 获取数据列表
     *
     * @param name
     * @return
     */
    public BootstrapTableVo getHypothesisList(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataEvaluationHypothesis> hypothesisList = evaluationHypothesisDao.getHypothesisList(name);
        List<DataEvaluationHypothesisVo> vos = LangUtils.transform(hypothesisList, p -> getHypothesisVo(p));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataEvaluationHypothesisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    /**
     * 根据委估目的及评估方法获取数据列表
     *
     * @param method
     * @param purpose
     * @return
     */
    public List<DataEvaluationHypothesis> getHypothesisList(Integer method, Integer purpose) {
        String methodStr = new String();
        String purposeStr = new String();
        if (method != null && method > 0) {
            methodStr = String.format(",%s,", method);
        }
        if (purpose != null && purpose > 0) {
            purposeStr = String.format(",%s,", purpose);
        }
        return evaluationHypothesisDao.getHypothesisList(methodStr, purposeStr);
    }


    public DataEvaluationHypothesisVo getHypothesisVo(DataEvaluationHypothesis evaluationHypothesis) {
        if (evaluationHypothesis == null) return null;
        List<BaseDataDic> methodDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE);
        DataEvaluationHypothesisVo vo = new DataEvaluationHypothesisVo();
        BeanUtils.copyProperties(evaluationHypothesis, vo);
        if (StringUtils.isNotBlank(evaluationHypothesis.getMethod())) {
            vo.setMethodStr(dataCommonService.getDataDicName(methodDicList, evaluationHypothesis.getMethod()));
        }
        if (StringUtils.isNotBlank(evaluationHypothesis.getEntrustmentPurpose())) {
            vo.setEntrustmentPurposeStr(dataCommonService.getDataDicName(purposeDicList, evaluationHypothesis.getEntrustmentPurpose()));
        }
        BaseDataDic baseDataDic = null;
        if (evaluationHypothesis.getType() != null){
            baseDataDic = baseDataDicService.getDataDicById(evaluationHypothesis.getType());
            if (baseDataDic !=null){
                vo.setTypeName(baseDataDic.getName());
                baseDataDic = null;
            }
        }
        if (evaluationHypothesis.getCategory() != null){
            baseDataDic = baseDataDicService.getDataDicById(evaluationHypothesis.getCategory());
            if (baseDataDic != null){
                vo.setCategoryName(baseDataDic.getName());
            }
        }
        return vo;
    }


}
