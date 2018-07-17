package com.copower.pmcc.assess.service.project.survey;


import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetTemplateDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetTemplate;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyAssetTemplateDto;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetTemplateVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
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
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
@Service
public class SurveyAssetTemplateService {

    @Autowired
    private SurveyAssetTemplateDao surveyAssetTemplateDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public BootstrapTableVo getList(Integer pid) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetTemplate> surveyAssetTemplatesList = surveyAssetTemplateDao.getSurveyAssetTemplate(pid);
        List<SurveyAssetTemplateVo> surveyAssetTemplateVos = getVoList(surveyAssetTemplatesList);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(surveyAssetTemplateVos) ? new ArrayList<SurveyAssetTemplate>() : surveyAssetTemplateVos);
        return vo;
    }
    public List<SurveyAssetTemplateVo> getVoList(List<SurveyAssetTemplate> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            SurveyAssetTemplateVo surveyAssetTemplateVo = new SurveyAssetTemplateVo();
            BeanUtils.copyProperties(p, surveyAssetTemplateVo);
            if (p.getInventoryContent() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(Integer.valueOf(p.getInventoryContent()));
                if (baseDataDic != null)
                    surveyAssetTemplateVo.setInventoryContentName(baseDataDic.getName());
            }
            return surveyAssetTemplateVo;
        });
    }

    public boolean save(SurveyAssetTemplateDto surveyAssetTemplateDto,Integer pid) throws BusinessException {
        if(surveyAssetTemplateDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(surveyAssetTemplateDto.getId() != null && surveyAssetTemplateDto.getId() > 0){
            return surveyAssetTemplateDao.update(surveyAssetTemplateDto);
        }else{
            SysAttachmentDto sysAttachment = new SysAttachmentDto();
            sysAttachment.setTableId(0);
            sysAttachment.setFieldsName(SurveyAssetTemplateDto.CREDENTIALACCESSORY);
            List<SysAttachmentDto> baseAttachments = baseAttachmentService.getAttachmentList(sysAttachment);
            SysAttachmentDto baseAttachment = new SysAttachmentDto();
            if(baseAttachments != null){
                 baseAttachment = baseAttachments.get(0);
                Integer credentialAccessory = baseAttachment.getId();
                surveyAssetTemplateDto.setCredentialAccessory("" + credentialAccessory);
            }

            surveyAssetTemplateDto.setPid(pid);
            surveyAssetTemplateDto.setCreator(processControllerComponent.getThisUser());
            surveyAssetTemplateDao.save(surveyAssetTemplateDto);

            int tableId = surveyAssetTemplateDto.getId();
            baseAttachment.setTableId(tableId);
            baseAttachmentService.updateAttachment(baseAttachment);
            return true;
        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if(id ==null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());;
        return surveyAssetTemplateDao.delete(id);
    }

    public ModelAndView getSurveyAssetTemplateByPid(ModelAndView modelAndView, Integer pid) {
        List<SurveyAssetTemplate> surveyAssetTemplates = surveyAssetTemplateDao.getSurveyAssetTemplateByPid(pid);
        if (surveyAssetTemplates != null){
            modelAndView.addObject("surveyAssetTemplates",surveyAssetTemplates);
            return modelAndView;
        }
        return modelAndView;
    }
}
