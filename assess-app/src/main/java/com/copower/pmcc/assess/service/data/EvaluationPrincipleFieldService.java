package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.EvaluationPrincipleFieldDao;
import com.copower.pmcc.assess.dto.input.data.EvaluationPrincipleFieldDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationPrincipleFieldVo;
import com.copower.pmcc.assess.dto.output.data.EvaluationPrincipleVo;
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
 * 3.1.2.11	评估原则 字段
 * Created by 13426 on 2018/4/27.
 */
@Service
public class EvaluationPrincipleFieldService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;

    @Autowired
    private EvaluationPrincipleFieldDao evaluationPrincipleFieldDao;

    @Transactional
    public boolean add(EvaluationPrincipleFieldDto evaluationPrincipleFieldDto){
        if (evaluationPrincipleFieldDto.getCreator()==null)evaluationPrincipleFieldDto.setCreator(commonService.thisUserAccount());
        if (evaluationPrincipleFieldDto.getGmtCreated()==null)evaluationPrincipleFieldDto.setGmtCreated(new Date());
        return evaluationPrincipleFieldDao.add(evaluationPrincipleFieldDto);
    }

    @Transactional
    public boolean update(EvaluationPrincipleFieldDto evaluationPrincipleFieldDto){
        if (evaluationPrincipleFieldDto.getCreator()==null)evaluationPrincipleFieldDto.setCreator(commonService.thisUserAccount());
        if (evaluationPrincipleFieldDto.getGmtCreated()==null)evaluationPrincipleFieldDto.setGmtCreated(new Date());
        return evaluationPrincipleFieldDao.update(evaluationPrincipleFieldDto);
    }

    @Transactional
    public boolean remove(Integer id){
        return evaluationPrincipleFieldDao.remove(id);
    }


    @Transactional(readOnly = true)
    public EvaluationPrincipleFieldDto get(Integer id){
        return evaluationPrincipleFieldDao.get(id);
    }

    @Transactional(readOnly = true)
    public BootstrapTableVo list(Integer principleId){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationPrincipleFieldVo> vos = new ArrayList<>();
        if (principleId==null){
            evaluationPrincipleFieldDao.list().stream().parallel().forEach(evaluationPrincipleFieldDto -> vos.add(change(evaluationPrincipleFieldDto)));
            vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationPrincipleVo>() : vos);
            vo.setTotal(page.getTotal());
        }else {
            evaluationPrincipleFieldDao.list(principleId).stream().parallel().forEach(evaluationPrincipleFieldDto -> vos.add(change(evaluationPrincipleFieldDto)));
            vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationPrincipleVo>() : vos);
            vo.setTotal(page.getTotal());
        }
        return vo;
    }

    public List<EvaluationPrincipleFieldDto> listN(Integer principleId){
        return evaluationPrincipleFieldDao.list(principleId);
    }

    public EvaluationPrincipleFieldVo change(EvaluationPrincipleFieldDto evaluationPrincipleFieldDto){
        EvaluationPrincipleFieldVo evaluationPrincipleFieldVo = new EvaluationPrincipleFieldVo();
        BeanUtils.copyProperties(evaluationPrincipleFieldDto,evaluationPrincipleFieldVo);
        return evaluationPrincipleFieldVo;
    }
}
