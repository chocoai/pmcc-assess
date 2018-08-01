package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationPrincipleDao;
import com.copower.pmcc.assess.dto.input.data.EvaluationPrincipleDto;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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
    private EvaluationPrincipleDao evaluationPrincipleDao;

    @Transactional
    public boolean add(EvaluationPrincipleDto evaluationPrincipleDto) {
            evaluationPrincipleDto.setCreator(commonService.thisUserAccount());
        return evaluationPrincipleDao.add(evaluationPrincipleDto);
    }

    @Transactional
    public void saveAndUpdate(EvaluationPrincipleDto evaluationPrincipleDto, String field) {
        if (!ObjectUtils.isEmpty(evaluationPrincipleDto.getId())) {//update
            evaluationPrincipleDto.setCreator(evaluationPrincipleDao.get(evaluationPrincipleDto.getId()).getCreator());
            evaluationPrincipleDao.update(evaluationPrincipleDto);
            if (!StringUtils.isEmpty(field)) {// 字段
                // 因为是修改所以可能所有的数据数据库中都已经有相关信息了  有可能增加一些字段,有可能删去一些字段

            }

        } else {// add
            evaluationPrincipleDto.setCreator(commonService.thisUserAccount());
            Integer id = null;
            try {
                id = evaluationPrincipleDao.save(evaluationPrincipleDto);
            } catch (Exception e) {
                try {
                    logger.error("异常啦!" + e.getMessage());
                    throw e;
                } catch (Exception e1) {

                }
            }
        }
    }


    @Transactional
    public boolean remove(Integer id) {
        return evaluationPrincipleDao.remove(id);
    }

    @Transactional
    public boolean update(EvaluationPrincipleDto evaluationPrincipleDto) {
        return evaluationPrincipleDao.update(evaluationPrincipleDto);
    }

    @Transactional(readOnly = true)
    public EvaluationPrincipleDto get(Integer id) {
        return evaluationPrincipleDao.get(id);
    }


    public BootstrapTableVo list(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationPrincipleVo> vos = new ArrayList<>();
        boolean flag = StringUtils.isEmpty(name);
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationPrincipleVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }


}
