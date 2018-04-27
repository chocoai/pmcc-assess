package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.EvaluationThinkingFieldDao;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingFieldDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationThinkingFieldDaoVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13426 on 2018/4/26.
 */
@Service
public class EvaluationThinkingFieldService {

    @Autowired
    private EvaluationThinkingFieldDao evaluationThinkingFieldDao;

    public boolean add(EvaluationThinkingFieldDto evaluationThinkingFieldDto){
        return evaluationThinkingFieldDao.add(evaluationThinkingFieldDto);
    }

    public boolean remove(Integer id){
        return evaluationThinkingFieldDao.remove(id);
    }

    public boolean removeMethod(Integer method){
        return evaluationThinkingFieldDao.removeMethod(method);
    }

    public boolean update(EvaluationThinkingFieldDto evaluationThinkingFieldDto){
        return  evaluationThinkingFieldDao.update(evaluationThinkingFieldDto);
    }

    public EvaluationThinkingFieldDaoVo get(Integer id){
        return change(evaluationThinkingFieldDao.get(id));
    }

    public BootstrapTableVo listVos(Integer thinkId){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        vo.setRows(CollectionUtils.isEmpty(list(thinkId)) ? new ArrayList<EvaluationThinkingFieldDaoVo>():list(thinkId));
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<EvaluationThinkingFieldDaoVo> list(Integer thinkId){
        List<EvaluationThinkingFieldDaoVo> evaluationThinkingFieldDaoVos = new ArrayList<>();
        evaluationThinkingFieldDao.list(thinkId).forEach(evaluationThinkingFieldDto -> {
            evaluationThinkingFieldDaoVos.add(change(evaluationThinkingFieldDto));
        });
        return evaluationThinkingFieldDaoVos;
    }

    public EvaluationThinkingFieldDaoVo change(EvaluationThinkingFieldDto evaluationThinkingFieldDto){
        EvaluationThinkingFieldDaoVo vo = new EvaluationThinkingFieldDaoVo();
        BeanUtils.copyProperties(evaluationThinkingFieldDto, vo);
        return  vo;
    }
}
