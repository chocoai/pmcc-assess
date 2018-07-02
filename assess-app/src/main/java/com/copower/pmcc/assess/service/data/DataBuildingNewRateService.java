package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.data.DataBuildingNewRateDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.DataBuildingNewRate;
import com.copower.pmcc.assess.dto.output.data.DataBuildingNewRateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
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
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service(value = "dataBuildingNewRateService")
public class DataBuildingNewRateService {

    @Autowired
    private DataBuildingNewRateDao dataBuildingNewRateDao;
    @Autowired
    private BaseDataDicService baseDataDicService;


    public BootstrapTableVo getDataBuildingNewRateList(String buildingStructure) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataBuildingNewRate> dataBuildingNewRateList = dataBuildingNewRateDao.getDataBuildingNewRateList(buildingStructure);
        List<DataBuildingNewRateVo> dataBuildingNewRateVoList = LangUtils.transform(dataBuildingNewRateList, p -> {
            return getDataBuildingNewRateVo(p);
        });
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(dataBuildingNewRateVoList) ? new ArrayList<DataBuildingNewRateVo>() : dataBuildingNewRateVoList);
        return vo;
    }

    @Transactional
    public boolean addDataBuildingNewRate(DataBuildingNewRate dataBuildingNewRate) throws BusinessException {
        boolean flag = false;
        if (dataBuildingNewRate.getGmtCreated() == null)
            dataBuildingNewRate.setGmtCreated(new Date(System.currentTimeMillis()));
        flag = dataBuildingNewRateDao.addDataBuildingNewRate(dataBuildingNewRate);
        return flag;
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class, RuntimeException.class})
    public boolean editDataBuildingNewRate(DataBuildingNewRate dataBuildingNewRate) throws BusinessException {
        boolean flag = false;
        flag = dataBuildingNewRateDao.editDataBuildingNewRate(dataBuildingNewRate);
        return flag;
    }

    @Transactional(readOnly = true)
    public DataBuildingNewRate getByiDdataBuildingNewRate(Integer id) {
        return dataBuildingNewRateDao.getByiDdataBuildingNewRate(id);
    }

    public DataBuildingNewRateVo getDataBuildingNewRateVo(DataBuildingNewRate dataBuildingNewRate) {
        if (dataBuildingNewRate == null)
            return null;
        DataBuildingNewRateVo dataBuildingNewRateVo = new DataBuildingNewRateVo();
        BeanUtils.copyProperties(dataBuildingNewRate, dataBuildingNewRateVo);
        if (dataBuildingNewRate.getBuildingUse() != null && dataBuildingNewRate.getBuildingUse() > 0) {
            BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(dataBuildingNewRate.getBuildingUse());
            if (baseDataDic != null) {
                dataBuildingNewRateVo.setBuildingUseName(baseDataDic.getName());
            }
        }
        return dataBuildingNewRateVo;
    }

    @Transactional(rollbackFor = {Exception.class})
    public boolean deleteDataBuildingNewRate(Integer id) throws BusinessException {
        boolean flag = false;
        flag = dataBuildingNewRateDao.deleteDataBuildingNewRate(id);
        return flag;
    }
}
