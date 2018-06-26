package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class ProjectCheckContentService {

    @Autowired
    private BaseDataDicService baseDataDicService;

    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ProjectPlanDetailsDao projectPlanDetailsDao;
    /*
        获取数据字典
     */
    public ModelAndView getBaseDataDicList(ModelAndView modelAndView, ProjectPlanDetails projectPlanDetails){
        List<BaseDataDic> baseDataDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.CHECK_CONTENT);
        SysUserDto thisUserInfo = processControllerComponent.getThisUserInfo();
        Integer id = projectPlanDetails.getPid();
        ProjectPlanDetails parentProject = projectPlanDetailsDao.getProjectPlanDetailsItemById(id);
        modelAndView.addObject("checkContentList",baseDataDicList); //数据字典
        modelAndView.addObject("thisUserInfo",thisUserInfo);    //当前操作用户信息
        modelAndView.addObject("parentProject",parentProject);  //识别的项目名称
        return modelAndView;
    }
}
