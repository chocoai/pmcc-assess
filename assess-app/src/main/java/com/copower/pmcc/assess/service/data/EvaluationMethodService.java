package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationMethodDao;
import com.copower.pmcc.assess.dal.basis.entity.DataEvaluationMethod;
import com.copower.pmcc.assess.dal.basis.entity.ProjectInfo;
import com.copower.pmcc.assess.dto.output.data.DataEvaluationMethodVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
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
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationMethodDao evaluationMethodDao;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;
    @Autowired
    private ProjectInfoService projectInfoService;

    /**
     * 保存数据
     *
     * @param evaluationMethod
     */
    public void saveAndUpdate(DataEvaluationMethod evaluationMethod) {
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
    public DataEvaluationMethod getMethod(Integer id) {
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
        List<DataEvaluationMethod> hypothesisList = evaluationMethodDao.getMethodList(name);
        List<DataEvaluationMethodVo> vos = LangUtils.transform(hypothesisList, p -> getMethodVo(p));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataEvaluationMethodVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<DataEvaluationMethod> getMethodListByMethod(Integer method) {
        return evaluationMethodDao.getMethodListByMethod(method);
    }

    public List<DataEvaluationMethod> getMethodListByMethod(Integer method, Integer projectId) {
        ProjectInfo projectInfo = projectInfoService.getProjectInfoById(projectId);
        String projectType=String.format(",%s,",projectInfo.getProjectTypeId());
        String projectCategory=String.format(",%s,",projectInfo.getProjectCategoryId());
        List<DataEvaluationMethod> list = evaluationMethodDao.getMethodListByMethod(method, projectType, projectCategory);
        return list;
    }

    public List<DataEvaluationMethod> getMethodAllList() {
        return evaluationMethodDao.getMethodAllList();
    }


    public DataEvaluationMethodVo getMethodVo(DataEvaluationMethod method) {
        if (method == null) return null;
        DataEvaluationMethodVo vo = new DataEvaluationMethodVo();
        BeanUtils.copyProperties(method, vo);
        vo.setMethodStr(baseDataDicService.getNameById(method.getMethod()));
        vo.setTypeName(baseProjectClassifyService.getTypeAndCategoryName(method.getType(), method.getCategory()));
        return vo;
    }

    /**
     * 将所有模板以评估方法作为分类
     *
     * @return
     */
    public Map<Integer, List<DataEvaluationMethod>> getEvaluationMethodMap() {
        Map<Integer, List<DataEvaluationMethod>> map = Maps.newConcurrentMap();
        List<DataEvaluationMethod> evaluationMethodDtoList = evaluationMethodDao.getMethodAllList();
        for (DataEvaluationMethod evaluationMethod : evaluationMethodDtoList) {
            Integer method = evaluationMethod.getMethod();
            if (map.containsKey(method)) {
                List<DataEvaluationMethod> evaluationMethods = map.get(method);
                evaluationMethods.add(evaluationMethod);
            } else {
                List<DataEvaluationMethod> evaluationMethods = Lists.newArrayList();
                evaluationMethods.add(evaluationMethod);
                map.put(method, evaluationMethods);
            }
        }
        return map;
    }
}
