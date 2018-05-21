package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.NetDownloadUtils;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.dao.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.SurveyLocaleExploreDetailDao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.DataPriceTimepointDescription;
import com.copower.pmcc.assess.dal.entity.SurveyLocaleExploreDetail;
import com.copower.pmcc.assess.dto.input.FormConfigureDetailDto;
import com.copower.pmcc.assess.dto.input.project.SurveyLocaleExploreDetailDto;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FileUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.FtpUtilsExtense;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zly on 2018/5/16.
 */
@Service
public class SurveyLocaleExploreDetailService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SurveyLocaleExploreDetailDao surveyLocaleExploreDetailDao;
    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private BaseAttachmentDao baseAttachmentDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private FtpUtilsExtense ftpUtilsExtense;
    @Autowired
    private FormConfigureService formConfigureService;

    public SurveyLocaleExploreDetail getSingelDetail(Integer id) {
        return surveyLocaleExploreDetailDao.getSingelDetail(id);
    }

    public BootstrapTableVo getList(Integer planDetailsId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyLocaleExploreDetail> surveyLocaleExploreDetailList = surveyLocaleExploreDetailDao.getSurveyLocaleExploreDetail(planDetailsId);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(surveyLocaleExploreDetailList) ? new ArrayList<DataPriceTimepointDescription>() : surveyLocaleExploreDetailList);
        return vo;
    }

    public List<SurveyLocaleExploreDetail> getDetailList(Integer planDetailsId) {
        List<SurveyLocaleExploreDetail> surveyLocaleExploreDetailList = surveyLocaleExploreDetailDao.getSurveyLocaleExploreDetail(planDetailsId);
        return surveyLocaleExploreDetailList;
    }

    @Transactional
    public boolean save(SurveyLocaleExploreDetailDto surveyLocaleExploreDetail) throws BusinessException {
        if (surveyLocaleExploreDetail == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveyLocaleExploreDetail.getId() != null && surveyLocaleExploreDetail.getId() > 0) {
            saveDynamicForm(surveyLocaleExploreDetail);
            return surveyLocaleExploreDetailDao.update(surveyLocaleExploreDetail);
        } else {
            Integer dynamicTableId = saveDynamicForm(surveyLocaleExploreDetail);
            surveyLocaleExploreDetail.setDynamicTableId(dynamicTableId);
            surveyLocaleExploreDetail.setCreator(serviceComponent.getThisUser());
            boolean flag = surveyLocaleExploreDetailDao.save(surveyLocaleExploreDetail);
            //下载定位图
            String localDir = baseAttachmentService.createBasePath(baseAttachmentService.getTempUploadPath(), DateUtils.formatNowToYMD(), DateUtils.formatNowToYMDHMS());
            String imageName = baseAttachmentService.createNoRepeatFileName("jpg");
            String url = String.format("%s?location=%s&zoom=17&size=900*600&markers=mid,,A:%s&key=%s",
                    BaseConstant.MPA_API_URL, surveyLocaleExploreDetail.getSurveyLocaltion(), surveyLocaleExploreDetail.getSurveyLocaltion(), BaseConstant.MAP_WEB_SERVICE_KEY);
            try {
                NetDownloadUtils.download(url, imageName, localDir);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            //再将图片上传到FTP
            String ftpFileName = baseAttachmentService.createNoRepeatFileName("jpg");
            String ftpDirName = baseAttachmentService.createFTPBasePath(FormatUtils.underlineToCamel("tb_survey_locale_explore_detail", false),
                    DateUtils.formatNowToYMD(), "surveyLocaltion");
            try {
                ftpUtilsExtense.uploadFilesToFTP(ftpDirName, new FileInputStream(localDir + File.separator + imageName), ftpFileName);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            //数据库添加定位图片记录
            BaseAttachment baseAttachment = new BaseAttachment();
            baseAttachment.setTableId(surveyLocaleExploreDetail.getId());
            baseAttachment.setTableName("tb_survey_locale_explore_detail");
            baseAttachment.setFieldsName("survey_localtion");
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
            queryParam.setTableName("tb_survey_locale_explore_detail");
            queryParam.setCreater(serviceComponent.getThisUser());

            BaseAttachment example = new BaseAttachment();
            example.setTableId(surveyLocaleExploreDetail.getId());
            baseAttachmentDao.updateAttachementByExample(queryParam, example);
            return flag;

        }
    }

    public Integer saveDynamicForm(SurveyLocaleExploreDetailDto surveyLocaleExploreDetail) throws BusinessException {
        if (surveyLocaleExploreDetail.getDynamicFormId() == null) return 0;
        FormConfigureDetailDto formConfigureDetailDto = new FormConfigureDetailDto();
        formConfigureDetailDto.setFormData(surveyLocaleExploreDetail.getDynamicFormData());
        formConfigureDetailDto.setFormModuleId(surveyLocaleExploreDetail.getDynamicFormId());
        formConfigureDetailDto.setTableId(surveyLocaleExploreDetail.getDynamicTableId());
        formConfigureDetailDto.setTableName(surveyLocaleExploreDetail.getDynamicTableName());
        return formConfigureService.saveSimpleData(formConfigureDetailDto);
    }

    public boolean delete(Integer id) throws BusinessException {
        if (id == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        ;
        return surveyLocaleExploreDetailDao.delete(id);
    }

}
