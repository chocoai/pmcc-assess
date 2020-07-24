package com.copower.pmcc.assess.service.project;


import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectReviewScoreDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScore;
import com.copower.pmcc.assess.dal.basis.entity.ProjectReviewScoreItem;
import com.copower.pmcc.assess.dto.output.project.ProjectReviewScoreItemVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectReviewScoreService {
    @Autowired
    private ProjectReviewScoreDao projectReviewScoreDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;

    public ProjectReviewScore getReviewScoreById(Integer id) {
        return projectReviewScoreDao.getProjectReviewScoreById(id);
    }

    public ProjectReviewScore getReviewScoreByProcessInsId(String processInsId) {
        if (StringUtils.isBlank(processInsId)) return null;
        ProjectReviewScore where = new ProjectReviewScore();
        where.setProcessInsId(processInsId);
        List<ProjectReviewScore> projectReviewScoreList = projectReviewScoreDao.getProjectReviewScoreList(where);
        if (CollectionUtils.isEmpty(projectReviewScoreList)) return null;
        return projectReviewScoreList.get(0);
    }

    public void saveReviewScore(ProjectReviewScore reviewScore) {
        if (reviewScore.getId() != null || reviewScore.getId() > 0) {
            projectReviewScoreDao.modifyProjectReviewScore(reviewScore);
        } else {
            reviewScore.setCreator(commonService.thisUserAccount());
            projectReviewScoreDao.addProjectReviewScore(reviewScore);
        }
    }

    //----------------------------------------------------------------------------------------------------

    public void saveReviewScoreItem(ProjectReviewScoreItem reviewScoreItem) {
        if (reviewScoreItem.getId() != null || reviewScoreItem.getId() > 0) {
            projectReviewScoreDao.modifyProjectReviewScoreItem(reviewScoreItem);
        } else {
            //先将上一条数据置为无效状态
            ProjectReviewScoreItem scoreItem = getEnableReviewScoreItemsByMasterId(reviewScoreItem.getMasterId());
            if (scoreItem != null) {
                scoreItem.setBisEnable(false);
                projectReviewScoreDao.modifyProjectReviewScoreItem(scoreItem);
            }
            reviewScoreItem.setCreator(commonService.thisUserAccount());
            projectReviewScoreDao.addProjectReviewScoreItem(reviewScoreItem);
        }
    }

    public ProjectReviewScoreItem getEnableReviewScoreItemsByMasterId(Integer masterId) {
        if (masterId == null) return null;
        ProjectReviewScoreItem where = new ProjectReviewScoreItem();
        where.setBisEnable(true);
        where.setMasterId(masterId);
        List<ProjectReviewScoreItem> list = projectReviewScoreDao.getProjectReviewScoreItemList(where);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0);
    }

    public List<ProjectReviewScoreItem> getHistoryReviewScoreItemsByMasterId(Integer masterId) {
        if (masterId == null) return null;
        ProjectReviewScoreItem where = new ProjectReviewScoreItem();
        where.setBisEnable(false);
        where.setMasterId(masterId);
        List<ProjectReviewScoreItem> list = projectReviewScoreDao.getProjectReviewScoreItemList(where);
        if (CollectionUtils.isEmpty(list)) return null;
        return list;
    }


    public ProjectReviewScoreItemVo getReviewScoreItemVo(ProjectReviewScoreItem reviewScoreItem) {
        if (reviewScoreItem == null) return null;
        ProjectReviewScoreItemVo vo = new ProjectReviewScoreItemVo();
        BeanUtils.copyProperties(reviewScoreItem, vo);
        vo.setCreatorName(publicService.getUserNameByAccount(reviewScoreItem.getCreator()));
        return vo;
    }
}
