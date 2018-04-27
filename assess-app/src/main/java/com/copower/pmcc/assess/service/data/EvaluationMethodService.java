package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.URLDecoderHelp;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.EvaluationMethodDao;
import com.copower.pmcc.assess.dal.dao.EvaluationMethodFieldDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.EvaluationMethod;
import com.copower.pmcc.assess.dal.entity.EvaluationMethodField;
import com.copower.pmcc.assess.dto.input.data.EvaluationMethodDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationMethodFieldDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationMethodVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 13426 on 2018/4/24.
 */
@Service(value = "evaluationMethodService")
public class EvaluationMethodService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;

    @Autowired
    private BaseDataDicService baseDataDicService;

    @Autowired
    private EvaluationMethodFieldDao evaluationMethodFieldDao;

    @Autowired
    private EvaluationMethodDao methodDao;

    @Deprecated
    @Transactional
    public boolean add(EvaluationMethodDto evaluationMethodDto) {
        if (evaluationMethodDto.getCreator() == null) evaluationMethodDto.setCreator(commonService.thisUserAccount());
        return methodDao.addEvaluationMethod(evaluationMethodDto);
    }

    @Transactional
    public boolean add(EvaluationMethodFieldDto evaluationMethodFieldDto) {
        if (evaluationMethodFieldDto.getCreator()==null)evaluationMethodFieldDto.setCreator(commonService.thisUserAccount());
        return evaluationMethodFieldDao.add(evaluationMethodFieldDto);
    }

    @Transactional
    public boolean update(EvaluationMethodDto evaluationMethodDto) {
        return methodDao.updateEvaluationMethod(evaluationMethodDto);
    }

    @Transactional
    public boolean update(EvaluationMethodFieldDto evaluationMethodFieldDto) {
        return evaluationMethodFieldDao.update(evaluationMethodFieldDto);
    }

    @Transactional(readOnly = true)
    public EvaluationMethodDto get(Integer id) {
        return methodDao.getEvaluationMethod(id);
    }

    public EvaluationMethodFieldDto getField(Integer id){
        return evaluationMethodFieldDao.get(id);
    }

    @Transactional
    public boolean remove(Integer id) {//一起删除
        evaluationMethodFieldDao.removeMethod(id);
        return methodDao.removeEvaluationMethod(id);
    }

    @Transactional
    public boolean removeFild(Integer id){//单独字段删除
        return  evaluationMethodFieldDao.remove(id);
    }

    public BootstrapTableVo getVosField(Integer methodId){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        vo.setTotal(page.getTotal());
        List<EvaluationMethodField> evaluationMethodFields = evaluationMethodFieldDao.list(methodId);
        vo.setRows(CollectionUtils.isEmpty(evaluationMethodFields)?new ArrayList<EvaluationMethodField>():evaluationMethodFields);
        return vo;
    }

    public BootstrapTableVo getVos(Integer method) {
        List<EvaluationMethodVo> vos = null;
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        vo.setTotal(page.getTotal());
        if (method != null) {
            vos = list(method);
            vo.setRows(CollectionUtils.isEmpty(vos)?new ArrayList<EvaluationMethodVo>():vos);
        } else {
            vos = list(null);
            vo.setRows(CollectionUtils.isEmpty(vos)?new ArrayList<EvaluationMethodVo>():vos);
        }
        return vo;
    }

    @Transactional(readOnly = true)
    public List<EvaluationMethodVo> list(Integer method) {
        List<EvaluationMethodVo> evaluationMethodVos = null;
        List<EvaluationMethodDto> evaluationMethodDtos = null;
        if (method != null) {
            Map<String, Object> map = new HashedMap();
            map.put(EvaluationMethodDto.METHOD_FIELD, method);
            evaluationMethodDtos = methodDao.list(map);
            evaluationMethodVos = change(evaluationMethodDtos);
        } else {
            evaluationMethodDtos = methodDao.list(null);
            evaluationMethodVos = change(evaluationMethodDtos);
        }
        return evaluationMethodVos;
    }


    private List<EvaluationMethodVo> change(List<EvaluationMethodDto> evaluationMethodDtos) {
        List<EvaluationMethodVo> evaluationMethodVos = new ArrayList<>();
        evaluationMethodDtos.forEach(evaluationMethodDto -> evaluationMethodVos.add(change(evaluationMethodDto)));
        return evaluationMethodVos;
    }

    private EvaluationMethodVo change(EvaluationMethodDto evaluationMethodDto) {
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        EvaluationMethodVo vo = new EvaluationMethodVo();
        BeanUtils.copyProperties(evaluationMethodDto, vo);
        try {
            if (vo.getMethod() != 0 && vo.getMethod() != null) {
                vo.setMethodStr(baseDataDics.get(vo.getMethod()).getName());
            }
        }catch (Exception e){
            throw  e;
        }
        return vo;
    }

    public Integer change(String methodStr) {
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
