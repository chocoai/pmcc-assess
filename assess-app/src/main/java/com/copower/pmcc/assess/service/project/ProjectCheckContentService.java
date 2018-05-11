package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class ProjectCheckContentService {

    @Autowired
    private BaseDataDicService baseDataDicService;

    @Autowired
    private ServiceComponent serviceComponent;
    /*
        获取数据字典
     */
    public ModelAndView getBaseDataDicList(ModelAndView modelAndView, ProjectPlanDetails projectPlanDetails){
        List<BaseDataDic> baseDataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.CHECK_CONTENT);
        String surveyUser = serviceComponent.getThisUser(); //取当前用户名
        String userName = serviceComponent.getThisUserInfo().getUserName(); //取当前用户姓名
        modelAndView.addObject("checkContentList",baseDataDicList);
        modelAndView.addObject("surveyUser",surveyUser);
        modelAndView.addObject("userName",userName);

        return modelAndView;
    }
}
