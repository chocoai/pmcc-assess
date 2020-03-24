package com.copower.pmcc.assess.service.project.xlx;

import com.copower.pmcc.assess.dal.basis.dao.project.xlx.ProjectXlxCommissionRatioDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxCommissionRatio;
import com.copower.pmcc.assess.dto.output.project.ProjectMemberVo;
import com.copower.pmcc.assess.service.project.ProjectMemberService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 评估依据
 * Created by 13426 on 2018/4/28.
 */
@Service
public class ProjectXlxCommissionRatioService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectMemberService projectMemberService;
    @Autowired
    private ProjectXlxCommissionRatioDao commissionRatioDao;
    @Autowired
    private GenerateCommonMethod generateCommonMethod;

    /**
     * 保存数据
     *
     * @param commissionRatio
     */
    public void saveAndUpdate(ProjectXlxCommissionRatio commissionRatio) {
        if (commissionRatio.getId() != null && commissionRatio.getId() > 0) {
            commissionRatioDao.modifyProjectXlxCommissionRatio(commissionRatio);
        } else {
            commissionRatio.setCreator(commonService.thisUserAccount());
            commissionRatioDao.addProjectXlxCommissionRatio(commissionRatio);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteData(Integer id) {
        return commissionRatioDao.deleteProjectXlxCommissionRatio(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public ProjectXlxCommissionRatio getDataById(Integer id) {
        return commissionRatioDao.getDataById(id);
    }


    /**
     * 获取数据列表
     *
     * @param masterId
     * @return
     */
    public BootstrapTableVo getProjectXlxCommissionRatioList(Integer masterId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ProjectXlxCommissionRatio projectXlxCommissionRatio = new ProjectXlxCommissionRatio();
        projectXlxCommissionRatio.setMasterId(masterId);
        List<ProjectXlxCommissionRatio> commissionRatioList = commissionRatioDao.getProjectXlxCommissionRatio(projectXlxCommissionRatio);
        vo.setRows(CollectionUtils.isEmpty(commissionRatioList) ? new ArrayList<ProjectXlxCommissionRatio>() : commissionRatioList);
        vo.setTotal(page.getTotal());
        return vo;
    }


    public void initCommissionRatio(Integer projectId, Integer masterId) {
        ProjectMemberVo projectMemberVo = projectMemberService.getProjectMember(projectId);
        String managerName = projectMemberVo.getUserAccountManagerName();
        String memberName = projectMemberVo.getUserAccountMemberName();
        List<String> all = Lists.newArrayList();
        if (StringUtils.isNotEmpty(managerName)) {
            List<String> managerList = FormatUtils.transformString2List(managerName);
            all.addAll(managerList);
        }
        if (StringUtils.isNotEmpty(memberName)) {
            List<String> memberList = FormatUtils.transformString2List(memberName);
            all.addAll(memberList);
        }
        if (CollectionUtils.isNotEmpty(all)) {
            //去重
            all = generateCommonMethod.removeDuplicate(all);
            for (String name : all) {
                ProjectXlxCommissionRatio commissionRatio = new ProjectXlxCommissionRatio();
                commissionRatio.setName(name);
                commissionRatio.setMasterId(masterId);
                saveAndUpdate(commissionRatio);
            }
        }
    }

    public void deleteByMasterId(Integer masterId){
        ProjectXlxCommissionRatio projectXlxCommissionRatio = new ProjectXlxCommissionRatio();
        projectXlxCommissionRatio.setMasterId(masterId);
        List<ProjectXlxCommissionRatio> commissionRatioList = commissionRatioDao.getProjectXlxCommissionRatio(projectXlxCommissionRatio);
        if(CollectionUtils.isNotEmpty(commissionRatioList)){
            for (ProjectXlxCommissionRatio item: commissionRatioList) {
                commissionRatioDao.deleteProjectXlxCommissionRatio(item.getId());
            }
        }
    }
}
