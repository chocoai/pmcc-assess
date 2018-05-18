package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.NetDownloadUtils;
import com.copower.pmcc.assess.dal.dao.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.SurveyCaseStudyDetailDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.FormConfigureDetailDto;
import com.copower.pmcc.assess.dto.input.project.SurveyCaseStudyDetailDto;
import com.copower.pmcc.assess.dto.output.data.DataNumberRuleVo;
import com.copower.pmcc.assess.dto.output.project.SurveyCaseStudyDetailVo;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.base.FormConfigureService;
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

import java.io.File;
import java.io.FileInputStream;
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
    private FormConfigureService formConfigureService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseAttachmentDao baseAttachmentDao;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;

    private static String mapAPiUrl = "http://restapi.amap.com/v3/staticmap";
    private static String mapWebServiceKey = "0d3f1144352d7e2b683e37dd3757156a";


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

    public boolean save(SurveyCaseStudyDetailDto surveyCaseStudyDetailDto) throws BusinessException {
        if (surveyCaseStudyDetailDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveyCaseStudyDetailDto.getId() != 0 && surveyCaseStudyDetailDto.getId() > 0) {
            Integer dynamicTableId = saveDynamicForm(surveyCaseStudyDetailDto);
            surveyCaseStudyDetailDto.setDynamicTableId(dynamicTableId);
            return surveyCaseStudyDetailDao.update(surveyCaseStudyDetailDto);
        } else {
            Integer dynamicTableId = saveDynamicForm(surveyCaseStudyDetailDto);
            surveyCaseStudyDetailDto.setDynamicTableId(dynamicTableId);
            surveyCaseStudyDetailDto.setCreator(serviceComponent.getThisUser());
            boolean flag = surveyCaseStudyDetailDao.save(surveyCaseStudyDetailDto);

            //下载定位图
            String localDir = baseAttachmentService.createBasePath(baseAttachmentService.getTempUploadPath(), DateUtils.formatNowToYMD(), DateUtils.formatNowToYMDHMS());
            String imageName = baseAttachmentService.createNoRepeatFileName("jpg");
            String url = String.format("%s?location=%s&zoom=17&size=900*600&markers=mid,,A:%s&key=%s",
                    mapAPiUrl, surveyCaseStudyDetailDto.getCaseLocaltion(), surveyCaseStudyDetailDto.getCaseLocaltion(), mapWebServiceKey);
            try {
                NetDownloadUtils.download(url, imageName, localDir);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            //再将图片上传到FTP
            String ftpFileName = baseAttachmentService.createNoRepeatFileName("jpg");
            String ftpDirName = baseAttachmentService.createFTPBasePath(FormatUtils.underlineToCamel("tb_survey_case_study_detail", false),
                    DateUtils.formatNowToYMD(), "caseLocaltion");
            try {
                ftpUtilsExtense.uploadFilesToFTP(ftpDirName, new FileInputStream(localDir + File.separator + imageName), ftpFileName);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            //数据库添加定位图片记录
            BaseAttachment baseAttachment = new BaseAttachment();
            baseAttachment.setTableId(surveyCaseStudyDetailDto.getId());
            baseAttachment.setTableName("tb_survey_case_study_detail");
            baseAttachment.setFieldsName("case_localtion");
            baseAttachment.setFtpFilesName(ftpFileName);
            baseAttachment.setFileExtension("jpg");
            baseAttachment.setFilePath(ftpDirName);
            baseAttachment.setFileName("定位图.jpg");
            baseAttachment.setFileSize(FileUtils.getSize(new File(localDir + File.separator + imageName).length()));
            baseAttachment.setCreater(serviceComponent.getThisUser());
            baseAttachment.setModifier(serviceComponent.getThisUser());
            baseAttachmentDao.addAttachment(baseAttachment);

            //更新附件表id
            BaseAttachment queryParam = new BaseAttachment();
            queryParam.setTableId(0);
            queryParam.setTableName("tb_survey_case_study_detail");

            BaseAttachment example = new BaseAttachment();
            example.setTableId(surveyCaseStudyDetailDto.getId());
            baseAttachmentDao.updateAttachementByExample(queryParam, example);

            return flag;
        }
    }

    private Integer saveDynamicForm(SurveyCaseStudyDetailDto surveyCaseStudyDetailDto) throws BusinessException {
        FormConfigureDetailDto formConfigureDetailDto = new FormConfigureDetailDto();
        formConfigureDetailDto.setFormData(surveyCaseStudyDetailDto.getDynamicFormData());
        formConfigureDetailDto.setFormModuleId(surveyCaseStudyDetailDto.getDynamicFormId());
        formConfigureDetailDto.setTableId(surveyCaseStudyDetailDto.getDynamicTableId());
        formConfigureDetailDto.setTableName(surveyCaseStudyDetailDto.getDynamicTableName());
        return formConfigureService.saveSimpleData(formConfigureDetailDto);
    }

    public boolean delete(Integer id) {
        return surveyCaseStudyDetailDao.delete(id);
    }

}
