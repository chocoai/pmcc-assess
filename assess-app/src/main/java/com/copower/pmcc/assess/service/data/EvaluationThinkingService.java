package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.EvaluationThinkingDao;
import com.copower.pmcc.assess.dal.entity.EvaluationThinking;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingDto;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 13426 on 2018/4/26.
 */
@Service(value = "evaluationThinkingService")
public class EvaluationThinkingService {

    @Autowired
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
        return evaluationThinkingDao.list(method);
    }

    public BootstrapTableVo listVo(String method){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationThinking> list = list(method);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<EvaluationThinking>() : list);
        return vo;
    }


}
