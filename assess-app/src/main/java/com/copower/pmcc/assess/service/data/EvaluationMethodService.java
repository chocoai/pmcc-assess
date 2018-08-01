package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationMethodDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationMethod;
import com.copower.pmcc.assess.dto.output.data.EvaluationMethodVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
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
 * 评估技术方法
 * Created by 13426 on 2018/4/24.
 */
@Service
public class EvaluationMethodService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataCommonService dataCommonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationMethodDao evaluationMethodDao;

    /**
     * 保存数据
     *
     * @param evaluationMethod
     */
    public void saveAndUpdate(EvaluationMethod evaluationMethod) {
        if (evaluationMethod.getId() != null && evaluationMethod.getId() > 0) {
            evaluationMethodDao.updateMethod(evaluationMethod);
        } else {
            evaluationMethod.setCreator(commonService.thisUserAccount());
            evaluationMethodDao.addMethod(evaluationMethod);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean removeMethod(Integer id) {
        return evaluationMethodDao.removeMethod(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public EvaluationMethod getMethod(Integer id) {
        return evaluationMethodDao.getMethod(id);
    }


    /**
     * 获取数据列表
     *
     * @param name
     * @return
     */
    public BootstrapTableVo getMethodList(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationMethod> hypothesisList = evaluationMethodDao.getMethodList(name);
        List<EvaluationMethodVo> vos = LangUtils.transform(hypothesisList, p -> getMethodVo(p));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationMethodVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<EvaluationMethod> getMethodListByMethod(Integer method) {
        return evaluationMethodDao.getMethodListByMethod(method);
    }


    public EvaluationMethodVo getMethodVo(EvaluationMethod evaluationMethod) {
        if (evaluationMethod == null) return null;
        List<BaseDataDic> methodDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        EvaluationMethodVo evaluationMethodVo = new EvaluationMethodVo();
        BeanUtils.copyProperties(evaluationMethod, evaluationMethodVo);
        if (evaluationMethod.getMethod() != null && evaluationMethod.getMethod() > 0) {
            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(evaluationMethod.getMethod());
            if (baseDataDic != null)
                evaluationMethodVo.setMethodStr(baseDataDic.getName());
        }

        return evaluationMethodVo;
    }

    /**
     * 将所有模板以评估方法作为分类
     *
     * @return
     */
    public Map<Integer, List<EvaluationMethod>> getEvaluationMethodMap() {
        Map<Integer, List<EvaluationMethod>> map = Maps.newConcurrentMap();
        List<EvaluationMethod> evaluationMethodDtoList = evaluationMethodDao.getMethodAllList();
        for (EvaluationMethod evaluationMethod : evaluationMethodDtoList) {
            Integer method = evaluationMethod.getMethod();
            if (map.containsKey(method)) {
                List<EvaluationMethod> evaluationMethods = map.get(method);
                evaluationMethods.add(evaluationMethod);
            } else {
                List<EvaluationMethod> evaluationMethods = Lists.newArrayList();
                evaluationMethods.add(evaluationMethod);
                map.put(method, evaluationMethods);
            }
        }
        return map;
    }
}
