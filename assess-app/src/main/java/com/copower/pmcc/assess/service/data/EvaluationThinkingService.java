package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.enums.FieldEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationThinkingDao;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationThinkingFieldDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.EvaluationThinking;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationThinkingFieldDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationThinkingVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 评估技术思路
 * Created by 13426 on 2018/4/26.
 */
@Service(value = "evaluationThinkingService")
public class EvaluationThinkingService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EvaluationThinkingDao evaluationThinkingDao;

    @Autowired
    private EvaluationPrincipleService principleService;

    @Autowired
    private EvaluationThinkingFieldDao fieldDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private BaseDataDicService baseDataDicService;

    @Transactional
    public boolean add(EvaluationThinkingDto evaluationThinkingDto) {
        if (evaluationThinkingDto.getCreator() == null)
            evaluationThinkingDto.setCreator(commonService.thisUserAccount());
        if (evaluationThinkingDto.getGmtCreated() == null) evaluationThinkingDto.setGmtCreated(new Date());
        return evaluationThinkingDao.add(evaluationThinkingDto);
    }

    @Transactional
    public void saveAndUpdate(EvaluationThinkingDto dto, String field, String Nofield) {
        if (!ObjectUtils.isEmpty(dto.getId())) {//update
            dto.setCreator(evaluationThinkingDao.get(dto.getId()).getCreator());
            evaluationThinkingDao.update(dto);
            if (!org.springframework.util.StringUtils.isEmpty(field)) {// 适用
                // 因为是修改所以可能所有的数据数据库中都已经有相关信息了
                String[] fields = field.split(",");
                int type = FieldEnum.APPLICABLE.getNum();//有可能增加一些字段,有可能删去一些字段
                for (String f : fields) {
                    if (!org.springframework.util.StringUtils.isEmpty(f)) fieldDao.delete(type, f, dto.getId());
                }
                for (String f : fields) {
                    if (!org.springframework.util.StringUtils.isEmpty(f)) {
                        EvaluationThinkingFieldDto fieldDto = new EvaluationThinkingFieldDto();
                        fieldDto.setType(type);
                        fieldDto.setName(f);
                        fieldDto.setCreator(commonService.thisUserAccount());
                        fieldDto.setThinkingId(dto.getId());
                        try {
                            fieldDao.add(fieldDto);//会自动判断是否存在已经添加过的字段
                        } catch (Exception e) {
                            try {
                                logger.error("错误打印!" + e.getMessage());
                            } catch (Exception e1) {
                                throw e;
                            }
                        }
                    }
                }
            }
            if (!org.springframework.util.StringUtils.isEmpty(Nofield)) {//不适用
                String[] noFields = Nofield.split(",");
                int type = FieldEnum.NO_APPLICABLE.getNum();
                for (String f : noFields) {
                    if (!org.springframework.util.StringUtils.isEmpty(f))
                        fieldDao.delete(type, f, dto.getId());
                }
                for (String f : noFields) {
                    if (!org.springframework.util.StringUtils.isEmpty(f)) {
                        EvaluationThinkingFieldDto fieldDto = new EvaluationThinkingFieldDto();
                        fieldDto.setType(type);
                        fieldDto.setName(f);
                        fieldDto.setCreator(commonService.thisUserAccount());
                        fieldDto.setThinkingId(dto.getId());
                        try {
                            fieldDao.add(fieldDto);//会自动判断是否存在已经添加过的字段
                        } catch (Exception e) {
                            try {
                                logger.error("错误打印!" + e.getMessage());
                            } catch (Exception e1) {
                                throw e;
                            }
                        }
                    }
                }
            }
        } else {// add
            dto.setCreator(commonService.thisUserAccount());
            int id = evaluationThinkingDao.save(dto);
            if (!org.springframework.util.StringUtils.isEmpty(field)) {
                String[] fields = field.split(",");
                for (String f : fields) {//适用字段
                    if (!org.springframework.util.StringUtils.isEmpty(f)) {
                        EvaluationThinkingFieldDto fieldDto = new EvaluationThinkingFieldDto();
                        fieldDto.setType(FieldEnum.APPLICABLE.getNum());
                        fieldDto.setCreator(commonService.thisUserAccount());
                        fieldDto.setName(f);
                        fieldDto.setThinkingId(id);
                        try {
                            fieldDao.add(fieldDto);//会自动判断是否存在已经添加过的字段
                        } catch (Exception e) {
                            try {
                                logger.error("错误打印!" + e.getMessage());
                            } catch (Exception e1) {
                                throw e;
                            }
                        }
                    }
                }

            }
            if (!org.springframework.util.StringUtils.isEmpty(Nofield)) {//不适用字段
                String[] noFields = Nofield.split(",");
                for (String f : noFields) {
                    if (!org.springframework.util.StringUtils.isEmpty(f)) {
                        EvaluationThinkingFieldDto fieldDto = new EvaluationThinkingFieldDto();
                        fieldDto.setType(FieldEnum.NO_APPLICABLE.getNum());
                        fieldDto.setCreator(commonService.thisUserAccount());
                        fieldDto.setName(f);
                        fieldDto.setThinkingId(id);
                        try {
                            fieldDao.add(fieldDto);//会自动判断是否存在已经添加过的字段
                        } catch (Exception e) {
                            try {
                                logger.error("错误打印!" + e.getMessage());
                            } catch (Exception e1) {
                                throw e;
                            }
                        }
                    }
                }

            }
        }
    }

    @Transactional
    public boolean update(EvaluationThinkingDto evaluationThinkingDto) {
        if (evaluationThinkingDto.getCreator() == null)
            evaluationThinkingDto.setCreator(commonService.thisUserAccount());
        if (evaluationThinkingDto.getGmtCreated() == null) evaluationThinkingDto.setGmtCreated(new Date());
        return evaluationThinkingDao.update(evaluationThinkingDto);
    }

    @Transactional
    public boolean remove(Integer id) {
        return evaluationThinkingDao.remove(id);
    }

    @Transactional(readOnly = true)
    public EvaluationThinkingDto get(Integer id) {
        return evaluationThinkingDao.get(id);
    }

    public List<EvaluationThinking> getListByMethod(Integer method) {
        return evaluationThinkingDao.getListByMethod(method);
    }

    @Transactional(readOnly = true)
    public List<EvaluationThinking> list(String name) {
        return evaluationThinkingDao.list(name);
    }

    public BootstrapTableVo listVo(String method) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationThinkingVo> voList = vosChange(list(method));
        vo.setRows(CollectionUtils.isEmpty(voList) ? new ArrayList<EvaluationThinkingVo>() : voList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    private List<EvaluationThinkingVo> vosChange(List<EvaluationThinking> evaluationThinkings) {
        List<EvaluationThinkingVo> evaluationThinkingVoList = new ArrayList<>();
        evaluationThinkings.forEach(evaluationThinking -> {
            evaluationThinkingVoList.add(change(evaluationThinking));
        });
        return evaluationThinkingVoList;
    }

    private EvaluationThinkingVo change(EvaluationThinking evaluationThinking) {
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        EvaluationThinkingVo evaluationThinkingVo = new EvaluationThinkingVo();
        BeanUtils.copyProperties(evaluationThinking, evaluationThinkingVo);
        if (StringUtils.isNotBlank(evaluationThinking.getMethod())) {
            String s = StringUtils.replacePattern(evaluationThinking.getMethod(), "^,+|,+$", "");
            List<Integer> integerList = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(s));
            String methodString = new String();
            for (Integer integer : integerList) {
                for (BaseDataDic baseDataDic : baseDataDics) {
                    if (integer.equals(baseDataDic.getId()))
                        methodString += baseDataDic.getName() + ",";
                }
            }
            evaluationThinkingVo.setMethodStr(StringUtils.replacePattern(methodString, "^,+|,+$", ""));
        }
        return evaluationThinkingVo;
    }

    /**
     * 将所有模板以评估方法作为分类
     *
     * @return
     */
    public Map<Integer, List<EvaluationThinking>> getEvaluationThinkingMap() {
        Map<Integer, List<EvaluationThinking>> map = Maps.newConcurrentMap();
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        if (CollectionUtils.isNotEmpty(baseDataDics)) {
            for (BaseDataDic baseDataDic : baseDataDics) {
                List<EvaluationThinking> thinkingList = getListByMethod(baseDataDic.getId());
                if (CollectionUtils.isNotEmpty(thinkingList)) {
                    map.put(baseDataDic.getId(), thinkingList);
                }
            }
        }
        return map;
    }

}
