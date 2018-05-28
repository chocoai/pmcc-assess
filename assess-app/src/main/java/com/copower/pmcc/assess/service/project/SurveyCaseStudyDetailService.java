package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.constant.AssessTableNameConstant;
import com.copower.pmcc.assess.dal.dao.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.SurveyCaseStudyDetailDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.project.SurveyCaseStudyDetailDto;
import com.copower.pmcc.assess.dto.output.project.SurveyCaseStudyDetailVo;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyCaseStudyDetailDao surveyCaseStudyDetailDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseAttachmentDao baseAttachmentDao;
    @Autowired
    private SurveyCommonService surveyCommonService;


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

    public List<SurveyCaseStudyDetail> getDetailList(Integer planDetailsId) {
        List<SurveyCaseStudyDetail> surveyCaseStudyDetails = surveyCaseStudyDetailDao.getSurveyCaseStudyDetail(planDetailsId);
        return surveyCaseStudyDetails;
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
            //changeName(surveyCaseStudyDetailVo, p);

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

    public boolean save(SurveyCaseStudyDetailDto detailDto) throws BusinessException {
        if (detailDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (detailDto.getId() != 0 && detailDto.getId() > 0) {
            surveyCommonService.saveDynamicForm(detailDto.getDynamicFormId(),detailDto.getDynamicFormData(), detailDto.getDynamicTableName(),detailDto.getDynamicTableId());
            return surveyCaseStudyDetailDao.update(detailDto);
        } else {
            Integer dynamicTableId = surveyCommonService.saveDynamicForm(detailDto.getDynamicFormId(),detailDto.getDynamicFormData(), detailDto.getDynamicTableName(),detailDto.getDynamicTableId());
            detailDto.setDynamicTableId(dynamicTableId);
            detailDto.setCreator(serviceComponent.getThisUser());
            boolean flag = surveyCaseStudyDetailDao.save(detailDto);

            //下载定位图
            surveyCommonService.downLoadLocationImage(AssessTableNameConstant.SURVEY_CASE_STUDY_DETAIL,detailDto.getId(),detailDto.getCaseLocaltion());

            //更新附件表id
            BaseAttachment queryParam = new BaseAttachment();
            queryParam.setTableId(0);
            queryParam.setTableName(AssessTableNameConstant.SURVEY_CASE_STUDY_DETAIL);
            queryParam.setCreater(serviceComponent.getThisUser());

            BaseAttachment example = new BaseAttachment();
            example.setTableId(detailDto.getId());
            baseAttachmentDao.updateAttachementByExample(queryParam, example);
            return flag;
        }
    }



    public boolean delete(Integer id) {
        return surveyCaseStudyDetailDao.delete(id);
    }

}
