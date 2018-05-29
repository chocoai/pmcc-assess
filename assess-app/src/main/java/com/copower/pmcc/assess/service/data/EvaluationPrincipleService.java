package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.enums.FieldEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.EvaluationPrincipleDao;
import com.copower.pmcc.assess.dal.dao.EvaluationPrincipleFieldDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.input.data.EvaluationMethodFieldDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationPrincipleDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationPrincipleFieldDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationPrincipleVo;
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
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
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
    private EvaluationPrincipleFieldService principleFieldService;

    @Autowired
    private BaseDataDicService baseDataDicService;

    @Autowired
    private EvaluationPrincipleFieldDao fieldDao;

    @Autowired
    private EvaluationPrincipleDao evaluationPrincipleDao;

    @Transactional
    public boolean add(EvaluationPrincipleDto evaluationPrincipleDto) {
        if (evaluationPrincipleDto.getCreator() == null)
            evaluationPrincipleDto.setCreator(commonService.thisUserAccount());
        if (evaluationPrincipleDto.getGmtCreated() == null) evaluationPrincipleDto.setGmtCreated(new Date());
        return evaluationPrincipleDao.add(evaluationPrincipleDto);
    }

    @Transactional
    public void saveAndUpdate(EvaluationPrincipleDto evaluationPrincipleDto, String field) {
        if (!ObjectUtils.isEmpty(evaluationPrincipleDto.getId())) {//update
            evaluationPrincipleDto.setCreator(evaluationPrincipleDao.get(evaluationPrincipleDto.getId()).getCreator());
            evaluationPrincipleDao.update(evaluationPrincipleDto);
            if (!StringUtils.isEmpty(field)) {// 字段
                // 因为是修改所以可能所有的数据数据库中都已经有相关信息了  有可能增加一些字段,有可能删去一些字段
                String[] fields = field.split(",");
                for (String f : fields) {
                    if (!StringUtils.isEmpty(f)) fieldDao.delete(f, evaluationPrincipleDto.getId());
                }
                for (String f : fields) {
                    if (!StringUtils.isEmpty(f)) {
                        EvaluationPrincipleFieldDto fieldDto = new EvaluationPrincipleFieldDto();
                        fieldDto.setName(f);
                        fieldDto.setCreator(commonService.thisUserAccount());
                        fieldDto.setPrincipleId(evaluationPrincipleDto.getId());
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
            evaluationPrincipleDto.setCreator(commonService.thisUserAccount());
            Integer id = null;
            try {
                id = evaluationPrincipleDao.save(evaluationPrincipleDto);
            } catch (Exception e) {
                try {
                    logger.error("异常啦!"+e.getMessage());
                    throw e;
                }catch (Exception e1){

                }
            }
            if (!StringUtils.isEmpty(field)) {
                String[] fields = field.split(",");
                for (String f : fields) {//字段
                    if (!StringUtils.isEmpty(f)) {
                        EvaluationPrincipleFieldDto fieldDto = new EvaluationPrincipleFieldDto();
                        fieldDto.setName(f);
                        fieldDto.setCreator(commonService.thisUserAccount());
                        fieldDto.setPrincipleId(id);
                        try {
                            if (id!=null) fieldDao.add(fieldDto);//会自动判断是否存在已经添加过的字段
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
    public boolean remove(Integer id) {
        return evaluationPrincipleDao.remove(id);
    }

    @Transactional
    public boolean update(EvaluationPrincipleDto evaluationPrincipleDto) {
        if (evaluationPrincipleDto.getCreator() == null)
            evaluationPrincipleDto.setCreator(commonService.thisUserAccount());
        if (evaluationPrincipleDto.getGmtCreated() == null) evaluationPrincipleDto.setGmtCreated(new Date());
        return evaluationPrincipleDao.update(evaluationPrincipleDto);
    }

    @Transactional(readOnly = true)
    public EvaluationPrincipleDto get(Integer id) {
        return evaluationPrincipleDao.get(id);
    }

    @Transactional(readOnly = true)
    public List<EvaluationPrincipleDto> listN(String name) {
        return evaluationPrincipleDao.list(name);
    }

    public List<EvaluationPrincipleVo> listNs(String name) {
        List<EvaluationPrincipleVo> vos = new ArrayList<>();
        List<EvaluationPrincipleDto> dtos = listN(name);
        for (EvaluationPrincipleDto dto : dtos) {
            vos.add(change(dto));
        }
        return vos;
    }


    public BootstrapTableVo list(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationPrincipleVo> vos = new ArrayList<>();
//        boolean flag = (name == null) || (name == "");
        boolean flag = StringUtils.isEmpty(name);
        listN(flag ? null : name).forEach(evaluationPrincipleDto -> vos.add(change(evaluationPrincipleDto)));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationPrincipleVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    private EvaluationPrincipleVo change(EvaluationPrincipleDto evaluationPrincipleDto) {
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        List<BaseDataDic> baseDataDicsA = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE);
        EvaluationPrincipleVo vo = new EvaluationPrincipleVo();
        List<EvaluationPrincipleFieldDto> fieldVos;
        fieldVos = principleFieldService.listN(evaluationPrincipleDto.getId());
        vo.setFieldVos(fieldVos);
        vo.setSize(fieldVos.size());
        BeanUtils.copyProperties(evaluationPrincipleDto, vo);
        try {
            if (!StringUtils.isEmpty(vo.getMethod()) && vo.getMethod() != null) {
                StringBuilder builder = new StringBuilder(1024);
                String[] methods = vo.getMethod().split(",");
                for (int i = 0; i < methods.length; i++) {
                    if (i < 3) {//只显示3条
                        int id = Integer.parseInt(methods[i]);
                        if (i == methods.length - 1) {
                            builder.append(changeMethodC(id));
                        } else {
                            builder.append(changeMethodC(id) + ",");
                        }
                    }
                }
                vo.setMethodStr(builder.toString());
            }
            if (!StringUtils.isEmpty(vo.getEntrustmentPurpose())) {
                StringBuilder builder = new StringBuilder(1024);
                String[] entrustmentPurposeS = vo.getEntrustmentPurpose().split(",");
                for (int i = 0; i < entrustmentPurposeS.length; i++) {
                    if (i < 3) {//只显示3条
                        int id = Integer.parseInt(entrustmentPurposeS[i]);
                        if (i == entrustmentPurposeS.length - 1) {
                            builder.append(changeEntrustmentPurpose(id));
                        } else {
                            builder.append(changeEntrustmentPurpose(id) + ",");
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

    /**
     * 特别处理 方法 int
     *
     * @param methodStr
     * @return
     */
    public Integer changeMethod(String methodStr) {
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

    /**
     * 特别处理 方法
     *
     * @param id
     * @return
     */
    public String changeMethodC(Integer id) {
        List<BaseDataDic> baseDataDicsA = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        String v = "";
        inner:
        for (BaseDataDic b : baseDataDicsA) {
            if (b.getId() == id) {
                v = b.getName();
                break inner;
            }
        }
        return v;
    }

    /**
     * 特别处理 委托
     *
     * @param id
     * @return
     */
    public String changeEntrustmentPurpose(Integer id) {
        List<BaseDataDic> baseDataDicsA = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.ENTRUSTMENT_PURPOSE);
        String v = "";
        inner:
        for (BaseDataDic b : baseDataDicsA) {
            if (b.getId() == id) {
                v = b.getName();
                break inner;
            }
        }
        return v;
    }
}
