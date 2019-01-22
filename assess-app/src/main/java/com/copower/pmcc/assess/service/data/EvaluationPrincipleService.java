package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationPrincipleDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationPrinciple;
import com.copower.pmcc.assess.dto.output.data.DataEvaluationPrincipleVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
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
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationPrincipleDao evaluationPrincipleDao;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;

    /**
     * 保存数据
     *
     * @param formData
     */
    public void saveAndUpdate(String formData) {
        DataEvaluationPrinciple evaluationPrinciple = JSON.parseObject(formData, DataEvaluationPrinciple.class);
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
    public DataEvaluationPrinciple getPrinciple(Integer id) {
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
        List<DataEvaluationPrinciple> hypothesisList = evaluationPrincipleDao.getPrincipleList(name);
        List<DataEvaluationPrincipleVo> vos = LangUtils.transform(hypothesisList, p -> getPrincipleVo(p));
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<DataEvaluationPrincipleVo>() : vos);
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
    public List<DataEvaluationPrinciple> getPrincipleList(Integer type, Integer category, Integer method, Integer purpose) {
        String typeStr = new String();
        String categoryStr = new String();
        String methodStr = new String();
        String purposeStr = new String();
        if (type != null && type > 0) {
            typeStr = String.format(",%s,", type);
        }
        if (category != null && category > 0) {
            categoryStr = String.format(",%s,", category);
        }
        if (method != null && method > 0) {
            methodStr = String.format(",%s,", method);
        }
        if (purpose != null && purpose > 0) {
            purposeStr = String.format(",%s,", purpose);
        }
        return evaluationPrincipleDao.getPrincipleList(typeStr, categoryStr, methodStr, purposeStr);
    }


    public DataEvaluationPrincipleVo getPrincipleVo(DataEvaluationPrinciple evaluationPrinciple) {
        if (evaluationPrinciple == null) return null;
        List<BaseDataDic> methodDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_EVALUATION_METHOD);
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        DataEvaluationPrincipleVo vo = new DataEvaluationPrincipleVo();
        BeanUtils.copyProperties(evaluationPrinciple, vo);
        if (StringUtils.isNotBlank(evaluationPrinciple.getMethod())) {
            vo.setMethodStr(baseDataDicService.getDataDicName(methodDicList, evaluationPrinciple.getMethod()));
        }
        if (StringUtils.isNotBlank(evaluationPrinciple.getEntrustmentPurpose())) {
            vo.setEntrustmentPurposeStr(baseDataDicService.getDataDicName(purposeDicList, evaluationPrinciple.getEntrustmentPurpose()));
        }
        vo.setTypeName(baseProjectClassifyService.getTypeAndCategoryName(evaluationPrinciple.getType(), evaluationPrinciple.getCategory()));
        return vo;
    }

}
