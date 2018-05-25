package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.EvaluationBasisFieldDao;
import com.copower.pmcc.assess.dto.input.data.EvaluationBasisFieldDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationBasisFieldVo;
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
 * 3.1.2.3	评估依据 字段
 * Created by 13426 on 2018/4/28.
 */
@Service
public class EvaluationBasisFieldService {

    @Autowired
    private EvaluationBasisFieldDao dao;

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;

    @Transactional
    public boolean add(EvaluationBasisFieldDto dto){
        if (dto.getCreator()==null)dto.setCreator(commonService.thisUserAccount());
        if (dto.getGmtCreated()==null)dto.setGmtCreated(new Date());
        return dao.add(dto);
    }

    @Transactional
    public boolean remove(Integer id){
        return dao.remove(id);
    }

    @Transactional
    public boolean update(EvaluationBasisFieldDto dto){
        if (dto.getCreator()==null)dto.setCreator(commonService.thisUserAccount());
        if (dto.getGmtCreated()==null)dto.setGmtCreated(new Date());
        return dao.update(dto);
    }

    public EvaluationBasisFieldDto get(Integer id){
        return dao.get(id);
    }

    public BootstrapTableVo listBoot(Integer basisId){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationBasisFieldVo> vos = list(basisId);
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationBasisFieldVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<EvaluationBasisFieldVo> list(Integer basisId){
        List<EvaluationBasisFieldVo> vos = new ArrayList<>();
        dao.list(basisId).parallelStream().forEach(e -> vos.add(change(e)));
        return vos;
    }

    public List<List<EvaluationBasisFieldVo>> listN(String id){
        List<List<EvaluationBasisFieldVo>> vos = new ArrayList<>();
        String[] ids = id.split(",");
        for (String s:ids){
            if (!StringUtils.isEmpty(s)){
                vos.add(list(Integer.parseInt(s)));
            }
        }
        return vos;
    }

    public EvaluationBasisFieldVo change(EvaluationBasisFieldDto dto){
        EvaluationBasisFieldVo vo = new EvaluationBasisFieldVo();
        BeanUtils.copyProperties(dto,vo);
        return vo;
    }
}
