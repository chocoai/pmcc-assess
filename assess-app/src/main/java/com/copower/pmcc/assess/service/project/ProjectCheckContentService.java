package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class ProjectCheckContentService {

    @Autowired
    BaseDataDicService baseDataDicService;
    /*
        获取数据字典
     */
    public ModelAndView getBaseDataDicList(ModelAndView modelAndView){
        List<BaseDataDic> baseDataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.CHECK_CONTENT);
        modelAndView.addObject("checkContentList",baseDataDicList);
        return modelAndView;
    }
}
