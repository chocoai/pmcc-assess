package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.EvaluationHypothesisDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public BootstrapTableVo list(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationHypothesisVo> vos = new ArrayList<>();
        boolean flag = (name == null) || (name == "");
        listN(flag ? null : name).forEach(evaluationHypothesisDto -> vos.add(change(evaluationHypothesisDto)));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationHypothesisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    private EvaluationHypothesisVo change(EvaluationHypothesisDto evaluationHypothesisDto){
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        EvaluationHypothesisVo vo = new EvaluationHypothesisVo();
        BeanUtils.copyProperties(evaluationHypothesisDto,vo);
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
            if (vo.getEntrustmentPurpose() != null && vo.getEntrustmentPurpose()!=""){
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
