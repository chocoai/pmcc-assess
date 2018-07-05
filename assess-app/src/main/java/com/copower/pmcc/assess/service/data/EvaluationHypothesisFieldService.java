package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationHypothesisFieldDao;
import com.copower.pmcc.assess.dto.input.data.EvaluationHypothesisFieldDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationHypothesisFieldVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 3.1.2.12	评估假设 字段
 * Created by 13426 on 2018/4/28.
 */
@Service
public class EvaluationHypothesisFieldService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;

    @Autowired
    private EvaluationHypothesisFieldDao evaluationHypothesisFieldDao;

    @Transactional
    public boolean add(EvaluationHypothesisFieldDto evaluationHypothesisFieldDto){
        if (evaluationHypothesisFieldDto.getCreator()==null)evaluationHypothesisFieldDto.setCreator(commonService.thisUserAccount());
        if (evaluationHypothesisFieldDto.getGmtCreated()==null)evaluationHypothesisFieldDto.setGmtCreated(new Date());
        return evaluationHypothesisFieldDao.add(evaluationHypothesisFieldDto);
    }

    @Transactional
    public boolean remove(Integer id){
        return evaluationHypothesisFieldDao.remove(id);
    }

    @Transactional
    public boolean update(EvaluationHypothesisFieldDto evaluationHypothesisFieldDto){
        if (evaluationHypothesisFieldDto.getCreator()==null)evaluationHypothesisFieldDto.setCreator(commonService.thisUserAccount());
        if (evaluationHypothesisFieldDto.getGmtCreated()==null)evaluationHypothesisFieldDto.setGmtCreated(new Date());
        return evaluationHypothesisFieldDao.update(evaluationHypothesisFieldDto);
    }

    @Transactional(readOnly = true)
    public EvaluationHypothesisFieldVo get(Integer id){
        return change(evaluationHypothesisFieldDao.get(id));
    }

    public BootstrapTableVo listBoot(Integer hypothesisId){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationHypothesisFieldVo> vos = list(hypothesisId);
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationHypothesisFieldVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    @Transactional(readOnly = true)
    public List<EvaluationHypothesisFieldVo> list(Integer hypothesisId){
        List<EvaluationHypothesisFieldVo> vos = new ArrayList<>();
        evaluationHypothesisFieldDao.list(hypothesisId).parallelStream().forEach(evaluationHypothesisFieldDto -> vos.add(change(evaluationHypothesisFieldDto)));
        return vos;
    }

    @Transactional(readOnly = true)
    public List<List<EvaluationHypothesisFieldVo>> listN(String id){
        List<List<EvaluationHypothesisFieldVo>> vos = new ArrayList<>();
        String[] ids = id.split(",");
        for (String s:ids){
            if (!StringUtils.isEmpty(s)){
                List<EvaluationHypothesisFieldVo> vo = new ArrayList<>();
                evaluationHypothesisFieldDao.list(Integer.parseInt(s)).parallelStream().forEach(evaluationHypothesisFieldDto -> vo.add(change(evaluationHypothesisFieldDto)));
                vos.add(vo);
            }
        }
        return vos;
    }

    public EvaluationHypothesisFieldVo change(EvaluationHypothesisFieldDto evaluationHypothesisFieldDto){
        EvaluationHypothesisFieldVo vo = new EvaluationHypothesisFieldVo();
        BeanUtils.copyProperties(evaluationHypothesisFieldDto,vo);
        return vo;
    }
}
