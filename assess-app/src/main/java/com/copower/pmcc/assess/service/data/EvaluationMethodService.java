package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.EvaluationMethodDao;
import com.copower.pmcc.assess.dal.dao.EvaluationMethodFieldDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        if(evaluationMethodDto.getGmtCreated()==null)evaluationMethodDto.setGmtCreated(new Date());
        if (evaluationMethodDto.getCreator() == null) evaluationMethodDto.setCreator(commonService.thisUserAccount());
        return methodDao.addEvaluationMethod(evaluationMethodDto);
    }

    @Transactional
    public boolean add(EvaluationMethodFieldDto evaluationMethodFieldDto) {
        if(evaluationMethodFieldDto.getGmtCreated()==null)evaluationMethodFieldDto.setGmtCreated(new Date());
        if (evaluationMethodFieldDto.getCreator()==null)evaluationMethodFieldDto.setCreator(commonService.thisUserAccount());
        return evaluationMethodFieldDao.add(evaluationMethodFieldDto);
    }

    @Transactional
    public boolean update(EvaluationMethodDto evaluationMethodDto) {
        if (evaluationMethodDto.getCreator() == null) evaluationMethodDto.setCreator(commonService.thisUserAccount());
        if(evaluationMethodDto.getGmtCreated()==null)evaluationMethodDto.setGmtCreated(new Date());
        return methodDao.updateEvaluationMethod(evaluationMethodDto);
    }

    @Transactional
    public boolean update(EvaluationMethodFieldDto evaluationMethodFieldDto) {
        if (evaluationMethodFieldDto.getCreator()==null)evaluationMethodFieldDto.setCreator(commonService.thisUserAccount());
        if(evaluationMethodFieldDto.getGmtCreated()==null)evaluationMethodFieldDto.setGmtCreated(new Date());
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
        List<EvaluationMethodField> evaluationMethodFields = evaluationMethodFieldDao.list(methodId);
        vo.setRows(CollectionUtils.isEmpty(evaluationMethodFields)?new ArrayList<EvaluationMethodField>():evaluationMethodFields);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public BootstrapTableVo getVos(Integer method) {
        List<EvaluationMethodVo> evaluationMethodVos = null;
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        if (method != null) {
            evaluationMethodVos = list(method);
            vo.setTotal(page.getTotal());
            vo.setRows(CollectionUtils.isEmpty(evaluationMethodVos)?new ArrayList<EvaluationMethodVo>():evaluationMethodVos);
        } else {
            evaluationMethodVos = list(null);
            vo.setTotal(page.getTotal());
            vo.setRows(CollectionUtils.isEmpty(evaluationMethodVos)?new ArrayList<EvaluationMethodVo>():evaluationMethodVos);
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
