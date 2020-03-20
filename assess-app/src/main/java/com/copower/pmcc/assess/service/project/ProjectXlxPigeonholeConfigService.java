package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.basis.dao.project.ProjectXlxPigeonholeConfigDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectXlxPigeonholeConfig;
import com.copower.pmcc.assess.dto.output.project.ProjectXlxPigeonholeConfigVo;
import com.copower.pmcc.assess.service.base.BaseProjectClassifyService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 评估技术思路
 * Created by 13426 on 2018/4/26.
 */
@Service
public class ProjectXlxPigeonholeConfigService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectXlxPigeonholeConfigDao projectXlxPigeonholeConfigDao;
    @Autowired
    private BaseProjectClassifyService baseProjectClassifyService;


    /**
     * 保存数据
     *
     * @param projectXlxPigeonholeConfig
     */
    public void saveAndUpdate(ProjectXlxPigeonholeConfig projectXlxPigeonholeConfig) {
        if (projectXlxPigeonholeConfig.getId() != null && projectXlxPigeonholeConfig.getId() > 0) {
            projectXlxPigeonholeConfigDao.updateProjectXlxPigeonholeConfig(projectXlxPigeonholeConfig);
        } else {
            projectXlxPigeonholeConfig.setCreator(commonService.thisUserAccount());
            projectXlxPigeonholeConfigDao.addProjectXlxPigeonholeConfig(projectXlxPigeonholeConfig);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public boolean removeProjectXlxPigeonholeConfig(Integer id) {
        return projectXlxPigeonholeConfigDao.removeProjectXlxPigeonholeConfig(id);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     */
    public ProjectXlxPigeonholeConfig getProjectXlxPigeonholeConfig(Integer id) {
        return projectXlxPigeonholeConfigDao.getProjectXlxPigeonholeConfig(id);
    }


    /**
     * 获取数据列表
     *
     * @return
     */
    public BootstrapTableVo getVoList(String queryName,Integer queryType) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ProjectXlxPigeonholeConfig> hypothesisList = projectXlxPigeonholeConfigDao.getProjectXlxPigeonholeConfigList(queryName,queryType);
        List<ProjectXlxPigeonholeConfigVo> vos = LangUtils.transform(hypothesisList, p -> getProjectXlxPigeonholeConfigVo(p));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<ProjectXlxPigeonholeConfigVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public ProjectXlxPigeonholeConfigVo getProjectXlxPigeonholeConfigVo(ProjectXlxPigeonholeConfig projectXlxPigeonholeConfig) {
        if (projectXlxPigeonholeConfig == null) return null;
        ProjectXlxPigeonholeConfigVo vo = new ProjectXlxPigeonholeConfigVo();
        vo.setTypeName(baseProjectClassifyService.getNameById(projectXlxPigeonholeConfig.getProjectType()));
        BeanUtils.copyProperties(projectXlxPigeonholeConfig, vo);
        return vo;
    }

}
