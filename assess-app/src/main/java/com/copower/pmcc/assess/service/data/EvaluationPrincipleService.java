package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.EvaluationPrincipleDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.input.data.EvaluationPrincipleDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationPrincipleVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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

import java.util.ArrayList;
import java.util.Date;
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

    @Transactional
    public boolean add(EvaluationPrincipleDto evaluationPrincipleDto) {
        if (evaluationPrincipleDto.getCreator()==null)evaluationPrincipleDto.setCreator(commonService.thisUserAccount());
        if (evaluationPrincipleDto.getGmtCreated()==null)evaluationPrincipleDto.setGmtCreated(new Date());
        return evaluationPrincipleDao.add(evaluationPrincipleDto);
    }

    @Transactional
    public boolean remove(Integer id) {
        return evaluationPrincipleDao.remove(id);
    }

    @Transactional
    public boolean update(EvaluationPrincipleDto evaluationPrincipleDto) {
        if (evaluationPrincipleDto.getCreator()==null)evaluationPrincipleDto.setCreator(commonService.thisUserAccount());
        if (evaluationPrincipleDto.getGmtCreated()==null)evaluationPrincipleDto.setGmtCreated(new Date());
        return evaluationPrincipleDao.update(evaluationPrincipleDto);
    }

    @Transactional(readOnly = true)
    public EvaluationPrincipleDto get(Integer id) {
        return evaluationPrincipleDao.get(id);
    }

    @Transactional(readOnly = true)
    private List<EvaluationPrincipleDto> listN(String method) {
        Integer key = null;
        if (method!=null)  key = changeMethod(method);
        return evaluationPrincipleDao.list(key);
    }

    public BootstrapTableVo list(String method) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationPrincipleVo> vos = new ArrayList<>();
        boolean flag = (method == null) || (method == "");
        listN(flag ? null : method).forEach(evaluationPrincipleDto -> vos.add(change(evaluationPrincipleDto)));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationPrincipleVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    private EvaluationPrincipleVo change(EvaluationPrincipleDto evaluationPrincipleDto) {
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        EvaluationPrincipleVo vo = new EvaluationPrincipleVo();
        BeanUtils.copyProperties(evaluationPrincipleDto, vo);
        try {
            if (vo.getMethod() != "" && vo.getMethod() != null) {
                vo.setMethodStr(baseDataDics.get(Integer.parseInt(vo.getMethod())).getName());
            }
        }catch (Exception e){
            throw  e;
        }
        return vo;
    }

    private Integer changeMethod(String methodStr) {
        Integer key = null;
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        inner:
        for (BaseDataDic b : baseDataDics) {
            for (int i = 0; i < baseDataDics.size() - 1; i++) {
                String v = baseDataDics.get(i).getName();
                if (methodStr.equals(v)) {
                    key = i;
                    break inner;
                }
            }
        }
        return key;
    }
}
