package com.copower.pmcc.assess.service.project.survey;


import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetOtherTemplateDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetOtherTemplate;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyAssetOtherTemplateDto;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetOtherTemplateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
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

/**
 * Created by zly on 2018/5/11.
 */
@Service
public class SurveyAssetOtherTemplateService {

    @Autowired
    private SurveyAssetOtherTemplateDao surveyAssetOtherTemplateDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public ModelAndView getSurveyAssetOtherTemplateByPid(ModelAndView modelAndView, Integer pid){
        List<SurveyAssetOtherTemplate> surveyAssetOtherTemplates = surveyAssetOtherTemplateDao.getSurveyAssetOtherTemplateByPid(pid);
        if (surveyAssetOtherTemplates != null){
            modelAndView.addObject("surveyAssetOtherTemplates",surveyAssetOtherTemplates);
            return modelAndView;
        }
        return modelAndView;
    }

    public BootstrapTableVo getList(Integer pid) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetOtherTemplate> surveyAssetOtherTemplateList = surveyAssetOtherTemplateDao.getSurveyAssetTemplate(pid);
        List<SurveyAssetOtherTemplateVo> surveyAssetOtherTemplateVoList = getVoList(surveyAssetOtherTemplateList);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(surveyAssetOtherTemplateVoList) ? new ArrayList<SurveyAssetOtherTemplate>() : surveyAssetOtherTemplateVoList);
        return vo;
    }

    public List<SurveyAssetOtherTemplateVo> getVoList(List<SurveyAssetOtherTemplate> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            SurveyAssetOtherTemplateVo surveyAssetOtherTemplateVo = new SurveyAssetOtherTemplateVo();
            BeanUtils.copyProperties(p, surveyAssetOtherTemplateVo);
            if (p.getType() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(Integer.valueOf(p.getType()));
                if (baseDataDic != null)
                    surveyAssetOtherTemplateVo.setTypeName(baseDataDic.getName());
            }
            return surveyAssetOtherTemplateVo;
        });
    }

    public boolean save(SurveyAssetOtherTemplateDto surveyAssetOtherTemplateDto, Integer pid) throws BusinessException {
        if(surveyAssetOtherTemplateDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if(surveyAssetOtherTemplateDto.getId() != null && surveyAssetOtherTemplateDto.getId() > 0){
            return surveyAssetOtherTemplateDao.update(surveyAssetOtherTemplateDto);
        }else{
            surveyAssetOtherTemplateDto.setCreator(processControllerComponent.getThisUser());
            return surveyAssetOtherTemplateDao.save(surveyAssetOtherTemplateDto);
        }
    }

    public boolean delete(Integer id) throws BusinessException {
        if(id ==null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());;
        return surveyAssetOtherTemplateDao.delete(id);
    }
}
