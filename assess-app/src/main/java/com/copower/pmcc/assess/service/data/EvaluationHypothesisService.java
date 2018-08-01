package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationHypothesisDao;
import com.copower.pmcc.assess.dto.input.data.EvaluationHypothesisDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationHypothesisVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
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
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationPrincipleService principleService;

    @Autowired
    private EvaluationHypothesisDao evaluationHypothesisDao;

    @Transactional
    public boolean add(EvaluationHypothesisDto evaluationHypothesisDto){
        if (evaluationHypothesisDto.getCreator()==null)evaluationHypothesisDto.setCreator(commonService.thisUserAccount());
        if (evaluationHypothesisDto.getGmtCreated()==null)evaluationHypothesisDto.setGmtCreated(new Date());
        return evaluationHypothesisDao.add(evaluationHypothesisDto);
    }

    @Transactional
    public void saveAndUpdate(EvaluationHypothesisDto evaluationHypothesisDto, String field) {
        if (!ObjectUtils.isEmpty(evaluationHypothesisDto.getId())) {//update
            evaluationHypothesisDto.setCreator(evaluationHypothesisDao.get(evaluationHypothesisDto.getId()).getCreator());
            evaluationHypothesisDao.update(evaluationHypothesisDto);


        } else {// add
            evaluationHypothesisDto.setCreator(commonService.thisUserAccount());
            Integer id = null;
            try {
                id = evaluationHypothesisDao.save(evaluationHypothesisDto);
            } catch (Exception e) {
                try {
                    logger.error("异常啦!"+e.getMessage());
                    throw e;
                }catch (Exception e1){

                }
            }

        }
    }

    @Transactional
    public boolean remove(Integer id){
        return evaluationHypothesisDao.remove(id);
    }

    @Transactional
    public boolean update(EvaluationHypothesisDto evaluationHypothesisDto){
        if (evaluationHypothesisDto.getCreator()==null)evaluationHypothesisDto.setCreator(commonService.thisUserAccount());
        if (evaluationHypothesisDto.getGmtCreated()==null)evaluationHypothesisDto.setGmtCreated(new Date());
        return evaluationHypothesisDao.update(evaluationHypothesisDto);
    }

    @Transactional(readOnly = true)
    public EvaluationHypothesisDto get(Integer id){
        return evaluationHypothesisDao.get(id);
    }

    @Transactional(readOnly = true)
    public List<EvaluationHypothesisDto> listN(String name) {
        return evaluationHypothesisDao.list(name);
    }

    public List<EvaluationHypothesisVo> listNs(String name){
        List<EvaluationHypothesisDto> dtos = listN(name);
        List<EvaluationHypothesisVo> vos = new ArrayList<>();
        for (EvaluationHypothesisDto dto:dtos){

        }
        return vos;
    }

    public BootstrapTableVo list(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationHypothesisVo> vos = new ArrayList<>();
        boolean flag = StringUtils.isEmpty(name);

        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationHypothesisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }





}
