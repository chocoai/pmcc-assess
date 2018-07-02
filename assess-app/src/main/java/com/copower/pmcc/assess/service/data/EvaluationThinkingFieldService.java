package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.enums.EvaluationThinkingFieldVoEnum;
import com.copower.pmcc.assess.dal.dao.data.EvaluationThinkingFieldDao;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingFieldDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationThinkingFieldDaoVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 评估技术思路 字段
 * Created by 13426 on 2018/4/26.
 */
@Service
public class EvaluationThinkingFieldService {

    @Autowired
    private EvaluationThinkingFieldDao evaluationThinkingFieldDao;

    @Autowired
    private CommonService commonService;

    public boolean add(EvaluationThinkingFieldDto evaluationThinkingFieldDto) {
        if (evaluationThinkingFieldDto.getCreator() == null)
            evaluationThinkingFieldDto.setCreator(commonService.thisUserAccount());
        return evaluationThinkingFieldDao.add(evaluationThinkingFieldDto);
    }

    public boolean remove(Integer id) {
        return evaluationThinkingFieldDao.remove(id);
    }

    public boolean removeMethod(Integer method) {
        return evaluationThinkingFieldDao.removeMethod(method);
    }

    public boolean update(EvaluationThinkingFieldDto evaluationThinkingFieldDto) {
        if (evaluationThinkingFieldDto.getCreator() == null)
            evaluationThinkingFieldDto.setCreator(commonService.thisUserAccount());
        return evaluationThinkingFieldDao.update(evaluationThinkingFieldDto);
    }

    public EvaluationThinkingFieldDaoVo get(Integer id) {
        return change(evaluationThinkingFieldDao.get(id));
    }

    public BootstrapTableVo listVos(Integer thinkId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationThinkingFieldDaoVo> list = list(thinkId);
        vo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<EvaluationThinkingFieldDaoVo>() : list);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<EvaluationThinkingFieldDaoVo> list(Integer thinkId) {
        List<EvaluationThinkingFieldDaoVo> evaluationThinkingFieldDaoVos = new ArrayList<>();
        evaluationThinkingFieldDao.list(thinkId).forEach(evaluationThinkingFieldDto -> {
            evaluationThinkingFieldDaoVos.add(change(evaluationThinkingFieldDto));
        });
        return evaluationThinkingFieldDaoVos;
    }

    public EvaluationThinkingFieldDaoVo change(EvaluationThinkingFieldDto evaluationThinkingFieldDto) {
        EvaluationThinkingFieldDaoVo vo = new EvaluationThinkingFieldDaoVo();
        BeanUtils.copyProperties(evaluationThinkingFieldDto, vo);

        if (vo.getType() == EvaluationThinkingFieldVoEnum.APPLICABLE.getId()) {
            vo.setTypeStr(EvaluationThinkingFieldVoEnum.APPLICABLE.getName());
        } else if (vo.getType() == EvaluationThinkingFieldVoEnum.NOT_APPLICABLE.getId()){
            vo.setTypeStr(EvaluationThinkingFieldVoEnum.NOT_APPLICABLE.getName());
        }
        return vo;
    }
}
