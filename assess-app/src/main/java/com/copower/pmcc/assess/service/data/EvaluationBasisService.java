package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationBasisDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationBasis;
import com.copower.pmcc.assess.dto.output.data.EvaluationBasisVo;
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
 * 评估依据
 * Created by 13426 on 2018/4/28.
 */
@Service
public class EvaluationBasisService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataCommonService dataCommonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationBasisDao evaluationBasisDao;

    /**
     * 保存数据
     *
     * @param evaluationBasis
     */
    public void saveAndUpdate(EvaluationBasis evaluationBasis) {
        if (evaluationBasis.getId() != null && evaluationBasis.getId() > 0) {
            evaluationBasisDao.updateBasis(evaluationBasis);
        } else {
            evaluationBasis.setCreator(commonService.thisUserAccount());
            evaluationBasisDao.addBasis(evaluationBasis);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean removeBasis(Integer id) {
        return evaluationBasisDao.removeBasis(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public EvaluationBasis getBasis(Integer id) {
        return evaluationBasisDao.getBasis(id);
    }


    /**
     * 获取数据列表
     *
     * @param name
     * @return
     */
    public BootstrapTableVo getBasisList(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationBasis> hypothesisList = evaluationBasisDao.getBasisList(name);
        List<EvaluationBasisVo> vos = LangUtils.transform(hypothesisList, p -> getBasisVo(p));
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationBasisVo>() : vos);
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
    public List<EvaluationBasis> getBasisList(Integer method, Integer purpose) {
        String methodStr = new String();
        String purposeStr = new String();
        if (method != null && method > 0) {
            methodStr = String.format(",%s,", method);
        }
        if (purpose != null && purpose > 0) {
            purposeStr = String.format(",%s,", purpose);
        }
        return evaluationBasisDao.getBasisList(methodStr, purposeStr);
    }


    public EvaluationBasisVo getBasisVo(EvaluationBasis evaluationBasis) {
        if (evaluationBasis == null) return null;
        List<BaseDataDic> methodDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE);
        EvaluationBasisVo evaluationBasisVo = new EvaluationBasisVo();
        BeanUtils.copyProperties(evaluationBasis, evaluationBasisVo);
        if (org.apache.commons.lang3.StringUtils.isNotBlank(evaluationBasis.getMethod())) {
            evaluationBasisVo.setMethodStr(dataCommonService.getDataDicName(methodDicList, evaluationBasis.getMethod()));
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(evaluationBasis.getEntrustmentPurpose())) {
            evaluationBasisVo.setEntrustmentPurposeStr(dataCommonService.getDataDicName(purposeDicList, evaluationBasis.getEntrustmentPurpose()));
        }
        return evaluationBasisVo;
    }



}
