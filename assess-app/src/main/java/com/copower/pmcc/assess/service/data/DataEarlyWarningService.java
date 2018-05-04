package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.DataEarlyWarningDao;
import com.copower.pmcc.assess.dal.entity.EarlyWarning;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "dataEarlyWarningServiceService")
public class DataEarlyWarningService {

    @Autowired
    private ServiceComponent serviceComponent;

    @Autowired
    private DataEarlyWarningDao dataEarlyWarningDao;

    /**
     * 修改一条预警设置信息
     * @param earlyWarning 预警设置entity
     * @return true or false
     */
    public boolean editEarlyWarning(EarlyWarning earlyWarning) throws Exception{
        boolean flag = false;
        earlyWarning.setCreator(serviceComponent.getThisUser());
        earlyWarning.setGmtModified(new Date());
        dataEarlyWarningDao.editEarlyWarning(earlyWarning);
        return flag;
    }

    /**
     * 新增一条预警设置信息
     * @param earlyWarning 预警设置entity
     * @return true or false
     */
    public boolean addEarlyWarning(EarlyWarning earlyWarning) throws Exception{
        boolean flag = false;
        earlyWarning.setCreator(serviceComponent.getThisUser());
        earlyWarning.setGmtCreated(new Date());
        flag = dataEarlyWarningDao.addEarlyWarning(earlyWarning);
        return flag;
    }

    /**
     * 获取预警设置信息列表
     * @param keyWord 查询关键字
     * @return 分页列表信息
     */
    public BootstrapTableVo getEarlyWarningForList(String keyWord) {
        //Bootstrap表格对象
        BootstrapTableVo vo = new BootstrapTableVo();
        //分页参数对象
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(),requestBaseParam.getLimit());
        //查询数据
        List<EarlyWarning> earlyWarningList = new ArrayList<EarlyWarning>();
        earlyWarningList = dataEarlyWarningDao.getEarlyWarningList(keyWord);
        vo.setTotal(page.getTotal());
        vo.setRows(earlyWarningList);
        return vo;
    }

    /**
     * 删除一条预警设置信息
     * @param id id
     * @return true or false
     */
    public boolean deleteEarlyWarning(Integer id) {
        boolean flag = false;
        dataEarlyWarningDao.deleteEarlyWarning(id);
        return flag;
    }
}
