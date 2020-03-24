package com.copower.pmcc.assess.service.project.xlx;

import com.copower.pmcc.assess.dal.basis.dao.project.xlx.ProjectXlxRebateRatioDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxRebateRatio;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
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
public class ProjectXlxRebateRatioService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectXlxRebateRatioDao rebateRatioDao;


    /**
     * 保存数据
     *
     * @param rebateRatio
     */
    public void saveAndUpdate(ProjectXlxRebateRatio rebateRatio) {
        if (rebateRatio.getId() != null && rebateRatio.getId() > 0) {
            rebateRatioDao.modifyProjectXlxRebateRatio(rebateRatio);
        } else {
            rebateRatio.setCreator(commonService.thisUserAccount());
            rebateRatioDao.addProjectXlxRebateRatio(rebateRatio);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean deleteData(Integer id) {
        return rebateRatioDao.deleteProjectXlxRebateRatio(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public ProjectXlxRebateRatio getDataById(Integer id) {
        return rebateRatioDao.getDataById(id);
    }


    /**
     * 获取数据列表
     *
     * @param masterId
     * @return
     */
    public BootstrapTableVo getProjectXlxRebateRatioList(Integer masterId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ProjectXlxRebateRatio projectXlxRebateRatio = new ProjectXlxRebateRatio();
        projectXlxRebateRatio.setMasterId(masterId);
        List<ProjectXlxRebateRatio> rebateRatioList = rebateRatioDao.getProjectXlxRebateRatio(projectXlxRebateRatio);
        vo.setRows(CollectionUtils.isEmpty(rebateRatioList) ? new ArrayList<ProjectXlxRebateRatio>() : rebateRatioList);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public void deleteByMasterId(Integer masterId){
        ProjectXlxRebateRatio projectXlxCommissionRatio = new ProjectXlxRebateRatio();
        projectXlxCommissionRatio.setMasterId(masterId);
        List<ProjectXlxRebateRatio> commissionRatioList = rebateRatioDao.getProjectXlxRebateRatio(projectXlxCommissionRatio);
        if(CollectionUtils.isNotEmpty(commissionRatioList)){
            for (ProjectXlxRebateRatio item: commissionRatioList) {
                rebateRatioDao.deleteProjectXlxRebateRatio(item.getId());
            }
        }
    }
}
