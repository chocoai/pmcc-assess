package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationPrincipleDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationPrinciple;
import com.copower.pmcc.assess.dto.output.data.EvaluationPrincipleVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 3.1.2.11	评估原则
 * Created by 13426 on 2018/4/27.
 */
@Service
public class EvaluationPrincipleService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataCommonService dataCommonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationPrincipleDao evaluationPrincipleDao;

    /**
     * 保存数据
     *
     * @param evaluationPrinciple
     */
    public void saveAndUpdate(EvaluationPrinciple evaluationPrinciple) {
        if (evaluationPrinciple.getId() != null && evaluationPrinciple.getId() > 0) {
            evaluationPrincipleDao.updatePrinciple(evaluationPrinciple);
        } else {
            evaluationPrinciple.setCreator(commonService.thisUserAccount());
            evaluationPrincipleDao.addPrinciple(evaluationPrinciple);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean removePrinciple(Integer id) {
        return evaluationPrincipleDao.removePrinciple(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public EvaluationPrinciple getPrinciple(Integer id) {
        return evaluationPrincipleDao.getPrinciple(id);
    }


    /**
     * 获取数据列表
     *
     * @param name
     * @return
     */
    public BootstrapTableVo getPrincipleList(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationPrinciple> hypothesisList = evaluationPrincipleDao.getPrincipleList(name);
        List<EvaluationPrincipleVo> vos = LangUtils.transform(hypothesisList, p -> getPrincipleVo(p));
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationPrincipleVo>() : vos);
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
    public List<EvaluationPrinciple> getPrincipleList(Integer method, Integer purpose) {
        String methodStr = new String();
        String purposeStr = new String();
        if (method != null && method > 0) {
            methodStr = String.format(",%s,", method);
        }
        if (purpose != null && purpose > 0) {
            purposeStr = String.format(",%s,", purpose);
        }
        return evaluationPrincipleDao.getPrincipleList(methodStr, purposeStr);
    }


    public EvaluationPrincipleVo getPrincipleVo(EvaluationPrinciple evaluationPrinciple) {
        if (evaluationPrinciple == null) return null;
        List<BaseDataDic> methodDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE);
        EvaluationPrincipleVo evaluationPrincipleVo = new EvaluationPrincipleVo();
        BeanUtils.copyProperties(evaluationPrinciple, evaluationPrincipleVo);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(evaluationPrinciple.getMethod())) {
            evaluationPrincipleVo.setMethodStr(dataCommonService.getDataDicName(methodDicList, evaluationPrinciple.getMethod()));
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(evaluationPrinciple.getEntrustmentPurpose())) {
            evaluationPrincipleVo.setEntrustmentPurposeStr(dataCommonService.getDataDicName(purposeDicList, evaluationPrinciple.getEntrustmentPurpose()));
        }
        return evaluationPrincipleVo;
    }

}
