package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.EvaluationBasisDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.input.data.EvaluationBasisDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationBasisVo;
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
 * 评估依据
 * Created by 13426 on 2018/4/28.
 */
@Service
public class EvaluationBasisService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;

    @Autowired
    private BaseDataDicService baseDataDicService;

    @Autowired
    private EvaluationPrincipleService principleService;

    @Autowired
    private EvaluationBasisDao evaluationBasisDao;

    @Transactional
    public boolean add(EvaluationBasisDto dto) {
        if (dto.getCreator() == null) dto.setCreator(commonService.thisUserAccount());
        if (dto.getGmtCreated() == null) dto.setGmtCreated(new Date());
        return evaluationBasisDao.add(dto);
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

    @Transactional(readOnly = true)
    public EvaluationBasisVo get(Integer id) {
        return change(evaluationBasisDao.get(id));
    }

    public List<EvaluationBasisDto> listN(String method) {
        Integer key = null;
        if (method != null) key = changeMethod(method);
        return evaluationBasisDao.list(key);
    }

    public BootstrapTableVo list(String method) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationBasisVo> vos = new ArrayList<>();
        boolean flag = (method == null) || (method == "");
        listN(flag ? null : method).parallelStream().forEach(evaluationBasisDto -> vos.add(change(evaluationBasisDto)));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationBasisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public EvaluationBasisVo change(EvaluationBasisDto dto) {
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        EvaluationBasisVo vo = new EvaluationBasisVo();
        BeanUtils.copyProperties(dto, vo);
        try {
            if (vo.getMethod() != "" && vo.getMethod() != null) {
                StringBuilder builder = new StringBuilder(1024);
                String[] methods = vo.getMethod().split(",");
                for (int i = 0; i < methods.length; i++) {
                    if (i < 3) {// 只显示3条
                        int id = Integer.parseInt(methods[i]);
                        if (i == methods.length - 1) {
                            builder.append(principleService.changeMethodC(id));
                        } else {
                            builder.append(principleService.changeMethodC(id)+",");
                        }
                    }
                }
                vo.setMethodStr(builder.toString());
            }
            if (vo.getEntrustmentPurpose() != null && vo.getEntrustmentPurpose() != "") {
                StringBuilder builder = new StringBuilder(1024);
                String[] entrustmentPurposeS = vo.getEntrustmentPurpose().split(",");
                for (int i = 0; i < entrustmentPurposeS.length; i++) {
                    int id = Integer.parseInt(entrustmentPurposeS[i]);
                    if (i < 3) { // 只显示3条
                        if (i == entrustmentPurposeS.length - 1) {
                            builder.append(principleService.changeEntrustmentPurpose(id));
                        } else {
                            builder.append(principleService.changeEntrustmentPurpose(id)+",");
                        }
                    }
                }
                vo.setEntrustmentPurposeStr(builder.toString());
            }
        } catch (Exception e) {
            throw e;
        }
        return vo;
    }

    public EvaluationBasisDto change(EvaluationBasisVo e) {
        EvaluationBasisDto dto = new EvaluationBasisDto();
        BeanUtils.copyProperties(e, dto);
        return dto;
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
