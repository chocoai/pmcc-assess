package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.SurveyCaseStudyDetailDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.output.data.DataNumberRuleVo;
import com.copower.pmcc.assess.dto.output.project.SurveyCaseStudyDetailVo;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zly on 2018/5/17.
 */
@Service
public class SurveyCaseStudyDetailService {

    @Autowired
    private SurveyCaseStudyDetailDao surveyCaseStudyDetailDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private DeclareRecordService declareRecordService;


    public BootstrapTableVo getList(Integer planDetailsId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyCaseStudyDetail> surveyCaseStudyDetailList = surveyCaseStudyDetailDao.getSurveyCaseStudyDetail(planDetailsId);
        List<SurveyCaseStudyDetailVo> surveyCaseStudyDetailVos = getVoList(surveyCaseStudyDetailList);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(surveyCaseStudyDetailVos) ? new ArrayList<SurveyCaseStudyDetail>() : surveyCaseStudyDetailVos);
        return vo;
    }

    public List<SurveyCaseStudyDetailVo> getName(Integer id) {
        List<SurveyCaseStudyDetail> surveyCaseStudyDetailList = surveyCaseStudyDetailDao.getSurveyCaseStudyDetailById(id);
        List<SurveyCaseStudyDetailVo> surveyCaseStudyDetailVos = getVoList(surveyCaseStudyDetailList);
        return surveyCaseStudyDetailVos;
    }


    private List<SurveyCaseStudyDetailVo> getVoList(List<SurveyCaseStudyDetail> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            SurveyCaseStudyDetailVo surveyCaseStudyDetailVo = new SurveyCaseStudyDetailVo();
            BeanUtils.copyProperties(p, surveyCaseStudyDetailVo);
            if (p.getCaseType() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getCaseType());
                if (baseDataDic != null)
                    surveyCaseStudyDetailVo.setCaseTypeName(baseDataDic.getName());
            }
            if (p.getInformationSource() != null) {
                BaseDataDic baseDataDic1 = baseDataDicService.getCacheDataDicById(p.getInformationSource());
                if (baseDataDic1 != null) {
                    surveyCaseStudyDetailVo.setInformationSourceName(baseDataDic1.getName());
                }
            }
            changeName(surveyCaseStudyDetailVo, p);

            return surveyCaseStudyDetailVo;
        });
    }

    private SurveyCaseStudyDetailVo changeName(SurveyCaseStudyDetailVo surveyCaseStudyDetailVo, SurveyCaseStudyDetail p) {
        String strs = p.getCorrelationCard();
        String[] correlationCards = strs.split(",");
        List<String> strings = new ArrayList<>();
        for (String correlationCard : correlationCards) {
            Integer id = Integer.valueOf(correlationCard);
            List<DeclareRecord> declareRecords = declareRecordService.getDeclareRecordById(id);
            if(declareRecords !=null &&declareRecords.size()>0){
                DeclareRecord declareRecord = declareRecords.get(0);
                strings.add(declareRecord.getName());
            }
        }
        String correlationCardName =String.join(",",strings);   //数组转成字符串
        surveyCaseStudyDetailVo.setCorrelationCardName(correlationCardName);
        return surveyCaseStudyDetailVo;
    }

    public SurveyCaseStudyDetail getSingelDetail(Integer id) {
        return surveyCaseStudyDetailDao.getSingelDetail(id);
    }

    public boolean save(SurveyCaseStudyDetail surveyCaseStudyDetail) throws BusinessException {
        if (surveyCaseStudyDetail == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveyCaseStudyDetail.getId() != 0 && surveyCaseStudyDetail.getId() > 0) {
            return surveyCaseStudyDetailDao.update(surveyCaseStudyDetail);
        } else {
            surveyCaseStudyDetail.setCreator(serviceComponent.getThisUser());
            return surveyCaseStudyDetailDao.save(surveyCaseStudyDetail);
        }
    }

    public boolean delete(Integer id) {
        return surveyCaseStudyDetailDao.delete(id);
    }

}
