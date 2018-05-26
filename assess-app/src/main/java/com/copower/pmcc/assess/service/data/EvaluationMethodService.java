package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.enums.EvaluationThinkingFieldVoEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.EvaluationMethodDao;
import com.copower.pmcc.assess.dal.dao.EvaluationMethodFieldDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.EvaluationMethod;
import com.copower.pmcc.assess.dal.entity.EvaluationMethodField;
import com.copower.pmcc.assess.dto.input.data.EvaluationMethodDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationMethodFieldDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationMethodFieldVo;
import com.copower.pmcc.assess.dto.output.data.EvaluationMethodVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 评估技术方法
 * Created by 13426 on 2018/4/24.
 */
@Service
public class EvaluationMethodService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;

    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private EvaluationPrincipleService principleService;

    @Resource
    private EvaluationMethodFieldDao evaluationMethodFieldDao;

    @Resource
    private EvaluationMethodDao methodDao;

    @Transactional
    public boolean add(EvaluationMethodDto evaluationMethodDto) {
        if (evaluationMethodDto.getGmtCreated() == null) evaluationMethodDto.setGmtCreated(new Date());
        if (evaluationMethodDto.getCreator() == null) evaluationMethodDto.setCreator(commonService.thisUserAccount());
        return methodDao.addEvaluationMethod(evaluationMethodDto);
    }

    @Transactional
    public boolean add(EvaluationMethodFieldDto evaluationMethodFieldDto) {
        if (evaluationMethodFieldDto.getGmtCreated() == null) evaluationMethodFieldDto.setGmtCreated(new Date());
        if (evaluationMethodFieldDto.getCreator() == null)
            evaluationMethodFieldDto.setCreator(commonService.thisUserAccount());
        return evaluationMethodFieldDao.add(evaluationMethodFieldDto);
    }

    @Transactional
    public boolean update(EvaluationMethodDto evaluationMethodDto) {
        if (evaluationMethodDto.getCreator() == null) evaluationMethodDto.setCreator(commonService.thisUserAccount());
        if (evaluationMethodDto.getGmtCreated() == null) evaluationMethodDto.setGmtCreated(new Date());
        return methodDao.updateEvaluationMethod(evaluationMethodDto);
    }

    @Transactional
    public boolean update(EvaluationMethodFieldDto evaluationMethodFieldDto) {
        if (evaluationMethodFieldDto.getCreator() == null)
            evaluationMethodFieldDto.setCreator(commonService.thisUserAccount());
        if (evaluationMethodFieldDto.getGmtCreated() == null) evaluationMethodFieldDto.setGmtCreated(new Date());
        return evaluationMethodFieldDao.update(evaluationMethodFieldDto);
    }

    @Transactional(readOnly = true)
    public EvaluationMethodDto get(Integer id) {
        return methodDao.getEvaluationMethod(id);
    }

    public EvaluationMethodFieldDto getField(Integer id) {
        return evaluationMethodFieldDao.get(id);
    }

    @Transactional
    public boolean remove(Integer id) {//一起删除
        evaluationMethodFieldDao.removeMethod(id);
        return methodDao.removeEvaluationMethod(id);
    }

    @Transactional
    public boolean removeFild(Integer id) {//单独字段删除
        return evaluationMethodFieldDao.remove(id);
    }

    public BootstrapTableVo getVosField(Integer methodId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationMethodField> evaluationMethodFields = evaluationMethodFieldDao.list(methodId);
        List<EvaluationMethodFieldVo> list = new ArrayList<>();
        evaluationMethodFields.forEach(evaluationMethodField -> list.add(change(evaluationMethodField)));
        vo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<EvaluationMethodFieldVo>() : list);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public BootstrapTableVo getVos(Integer method) {
        List<EvaluationMethodVo> evaluationMethodVos = null;
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        if (method != null) {
            evaluationMethodVos = list(method);// 121 比较法
            vo.setTotal(page.getTotal());
            vo.setRows(CollectionUtils.isEmpty(evaluationMethodVos) ? new ArrayList<EvaluationMethodVo>() : evaluationMethodVos);
        } else {
            evaluationMethodVos = list(null);
            vo.setTotal(page.getTotal());
            vo.setRows(CollectionUtils.isEmpty(evaluationMethodVos) ? new ArrayList<EvaluationMethodVo>() : evaluationMethodVos);
        }
        return vo;
    }

    @Transactional(readOnly = true)
    public List<EvaluationMethodVo> list(Integer method) {
        List<EvaluationMethodVo> evaluationMethodVos = null;
        List<EvaluationMethodDto> evaluationMethodDtos = null;
        if (method != null) {
            Map<String, Integer> map = new HashedMap();
            map.put(EvaluationMethodDto.METHOD_FIELD, method);
            evaluationMethodDtos = methodDao.list(map);
            evaluationMethodVos = change(evaluationMethodDtos);
        } else {
            evaluationMethodDtos = methodDao.list(null);
            evaluationMethodVos = change(evaluationMethodDtos);
        }
        return evaluationMethodVos;
    }

    /**
     * 将所有模板以评估方法作为分类
     * @return
     */
    public Map<Integer,List<EvaluationMethod>> getEvaluationMethodMap(){
        Map<Integer,List<EvaluationMethod>> map= Maps.newConcurrentMap();
        List<EvaluationMethodDto> evaluationMethodDtoList = methodDao.list(null);
        for (EvaluationMethodDto evaluationMethodDto : evaluationMethodDtoList) {
            Integer method = evaluationMethodDto.getMethod();
            if(map.containsKey(method)){
                List<EvaluationMethod> evaluationMethods = map.get(method);
                evaluationMethods.add(evaluationMethodDto);
            }else{
                List<EvaluationMethod> evaluationMethods= Lists.newArrayList();
                evaluationMethods.add(evaluationMethodDto);
                map.put(method,evaluationMethods);
            }
        }
        return map;
    }

    public List<EvaluationMethod> getEvaluationMethodList(Integer method) {
        EvaluationMethod evaluationMethod = new EvaluationMethod();
        evaluationMethod.setMethod(method);
        return methodDao.getEvaluationMethodList(evaluationMethod);
    }

    private EvaluationMethodFieldVo change(EvaluationMethodField evaluationMethodField) {
        EvaluationMethodFieldVo vo = new EvaluationMethodFieldVo();
        BeanUtils.copyProperties(evaluationMethodField, vo);
        if (vo.getType() == EvaluationThinkingFieldVoEnum.ONE.getNum()) {
            vo.setTypeStr(EvaluationThinkingFieldVoEnum.STR1.getVar());
        } else if (vo.getType() == EvaluationThinkingFieldVoEnum.ZERO.getNum()) {
            vo.setTypeStr(EvaluationThinkingFieldVoEnum.STR2.getVar());
        }
        return vo;
    }

    public List<EvaluationMethod> getEvaluationMethodByMethod(Integer method) {

        return null;
    }


    private List<EvaluationMethodVo> change(List<EvaluationMethodDto> evaluationMethodDtos) {
        List<EvaluationMethodVo> evaluationMethodVos = new ArrayList<>();
        evaluationMethodDtos.forEach(evaluationMethodDto -> evaluationMethodVos.add(change(evaluationMethodDto)));
        return evaluationMethodVos;
    }

    private EvaluationMethodVo change(EvaluationMethodDto evaluationMethodDto) {
        EvaluationMethodVo vo = new EvaluationMethodVo();
        BeanUtils.copyProperties(evaluationMethodDto, vo);
        if (evaluationMethodDto.getMethod() != null) {
            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(evaluationMethodDto.getMethod());
            if (baseDataDic != null) {
                vo.setMethodStr(baseDataDic.getName());
            }
        }
        return vo;
    }

    public Integer changeMethod(String methodStr) {
        Integer key = null;
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        inner:
        for (BaseDataDic b : baseDataDics) {
            for (int i = 0; i < baseDataDics.size(); i++) {
                String v = baseDataDics.get(i).getName();
                if (methodStr.equals(v)) {
                    key = b.getId();
                    break inner;
                }
            }
        }
        return key;
    }
}
