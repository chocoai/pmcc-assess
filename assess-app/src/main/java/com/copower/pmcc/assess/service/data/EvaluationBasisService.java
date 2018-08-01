package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationBasisDao;
import com.copower.pmcc.assess.dto.input.data.EvaluationBasisDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationBasisVo;
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
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 评估依据
 * Created by 13426 on 2018/4/28.
 */
@Service
public class EvaluationBasisService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private EvaluationBasisDao evaluationBasisDao;

    @Transactional
    public boolean add(EvaluationBasisDto dto) {
        if (dto.getCreator() == null) dto.setCreator(commonService.thisUserAccount());
        if (dto.getGmtCreated() == null) dto.setGmtCreated(new Date());
        return evaluationBasisDao.add(dto);
    }

    @Transactional
    public void saveAndUpdate(EvaluationBasisDto dto, String field) {
        if (!ObjectUtils.isEmpty(dto.getId())) {//update
            dto.setCreator(evaluationBasisDao.get(dto.getId()).getCreator());
            evaluationBasisDao.update(dto);
            if (!StringUtils.isEmpty(field)) {// 字段
                // 因为是修改所以可能所有的数据数据库中都已经有相关信息了  有可能增加一些字段,有可能删去一些字段
                String[] fields = field.split(",");

            }

        } else {// add
            dto.setCreator(commonService.thisUserAccount());
            Integer id = null;
            try {
                id = evaluationBasisDao.save(dto);
            } catch (Exception e) {
                try {
                    logger.error("异常啦!"+e.getMessage());
                    throw e;
                }catch (Exception e1){

                }
            }
            if (!StringUtils.isEmpty(field)) {


            }
        }
    }


    @Transactional
    public boolean remove(Integer id) {
        return evaluationBasisDao.remove(id);
    }

    @Transactional
    public boolean update(EvaluationBasisDto dto) {
        if (dto.getCreator() == null) dto.setCreator(commonService.thisUserAccount());
        if (dto.getGmtCreated() == null) dto.setGmtCreated(new Date());
        return evaluationBasisDao.update(dto);
    }



    public List<EvaluationBasisDto> listN(String name) {
        return evaluationBasisDao.list(name);
    }

    public List<EvaluationBasisVo> listNs(String name){
        List<EvaluationBasisDto> dtos = listN(name);
        List<EvaluationBasisVo> vos = new ArrayList<>();
        for (EvaluationBasisDto dto:dtos){

        }
        return vos;
    }

    public BootstrapTableVo list(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationBasisVo> vos = new ArrayList<>();
        boolean flag = StringUtils.isEmpty(name);

        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationBasisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }



    public EvaluationBasisDto change(EvaluationBasisVo e) {
        EvaluationBasisDto dto = new EvaluationBasisDto();
        BeanUtils.copyProperties(e, dto);
        return dto;
    }


}
