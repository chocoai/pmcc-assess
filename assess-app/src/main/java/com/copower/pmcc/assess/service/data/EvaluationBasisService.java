package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationBasisDao;
import com.copower.pmcc.assess.dal.basis.dao.data.EvaluationBasisFieldDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.input.data.EvaluationBasisDto;
import com.copower.pmcc.assess.dto.input.data.EvaluationBasisFieldDto;
import com.copower.pmcc.assess.dto.output.data.EvaluationBasisFieldVo;
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
    private BaseDataDicService baseDataDicService;

    @Autowired
    private EvaluationPrincipleService principleService;

    @Autowired
    private EvaluationBasisFieldService basisFieldService;

    @Autowired
    private EvaluationBasisDao evaluationBasisDao;

    @Autowired
    private EvaluationBasisFieldDao fieldDao;

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
                for (String f : fields) {
                    if (!StringUtils.isEmpty(f)) fieldDao.delete(f, dto.getId());
                }
                for (String f : fields) {
                    if (!StringUtils.isEmpty(f)) {
                        EvaluationBasisFieldDto fieldDto = new EvaluationBasisFieldDto();
                        fieldDto.setName(f);
                        fieldDto.setCreator(commonService.thisUserAccount());
                        fieldDto.setBasisId(dto.getId());
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
                String[] fields = field.split(",");
                for (String f : fields) {//字段
                    if (!StringUtils.isEmpty(f)) {
                        EvaluationBasisFieldDto fieldDto = new EvaluationBasisFieldDto();
                        fieldDto.setName(f);
                        fieldDto.setCreator(commonService.thisUserAccount());
                        fieldDto.setBasisId(id);
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

    public List<EvaluationBasisDto> listN(String name) {
        return evaluationBasisDao.list(name);
    }

    public List<EvaluationBasisVo> listNs(String name){
        List<EvaluationBasisDto> dtos = listN(name);
        List<EvaluationBasisVo> vos = new ArrayList<>();
        for (EvaluationBasisDto dto:dtos){
            vos.add(change(dto));
        }
        return vos;
    }

    public BootstrapTableVo list(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<EvaluationBasisVo> vos = new ArrayList<>();
//        boolean flag = (name == null) || (name == "");
        boolean flag = StringUtils.isEmpty(name);
        listN(flag ? null : name).parallelStream().forEach(evaluationBasisDto -> vos.add(change(evaluationBasisDto)));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<EvaluationBasisVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public EvaluationBasisVo change(EvaluationBasisDto dto) {
        List<BaseDataDic> baseDataDics = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.EVALUATION_METHOD);
        EvaluationBasisVo vo = new EvaluationBasisVo();
        List<EvaluationBasisFieldVo> vos = basisFieldService.list(dto.getId());
        vo.setFieldVos(vos);
        vo.setSize(vos.size());
        BeanUtils.copyProperties(dto, vo);
        try {
            if (!StringUtils.isEmpty(vo.getMethod())) {
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
            if (!StringUtils.isEmpty(vo.getEntrustmentPurpose())) {
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
