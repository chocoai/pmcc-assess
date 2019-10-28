package com.copower.pmcc.assess.service.tool;

import com.copower.pmcc.assess.dal.basis.dao.data.ToolMapHandleDao;
import com.copower.pmcc.assess.dal.basis.entity.ToolMapHandle;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zch on 2019-10-8.
 * 地图插件 service
 */
@Service
public class ToolMapHandleService {
    @Autowired
    private ToolMapHandleDao toolMapHandleDao;
    @Autowired
    private CommonService commonService;

    public boolean saveToolMapHandle(ToolMapHandle ToolMapHandle)  {
        if (ToolMapHandle == null) {
            return false;
        }
        if (ToolMapHandle.getId() != null && ToolMapHandle.getId() != 0) {
            return toolMapHandleDao.updateToolMapHandle(ToolMapHandle);
        } else {
            ToolMapHandle.setCreator(commonService.thisUserAccount());
            toolMapHandleDao.addToolMapHandle(ToolMapHandle);
            return true;
        }
    }

    public BootstrapTableVo getBootstrapTableVo(ToolMapHandle oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ToolMapHandle> childrenList = getToolMapHandleListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(childrenList);
        return vo;
    }


    public ToolMapHandle getToolMapHandleById(Integer id) {
        return toolMapHandleDao.getToolMapHandleById(id);
    }

    public boolean deleteToolMapHandleById(Integer id) {
        return toolMapHandleDao.deleteToolMapHandleById(id);
    }

    public void deleteToolMapHandle(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        toolMapHandleDao.deleteToolMapHandle(FormatUtils.transformString2Integer(id));
    }

    public void removeToolMapHandle(ToolMapHandle oo){
        toolMapHandleDao.removeToolMapHandle(oo);
    }

    public List<ToolMapHandle> getToolMapHandleListByExample(ToolMapHandle oo) {
        return toolMapHandleDao.getToolMapHandleListByExample(oo);
    }

}
