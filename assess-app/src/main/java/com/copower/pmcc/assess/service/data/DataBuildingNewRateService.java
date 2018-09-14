package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataBuildingNewRateDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataBuildingNewRate;
import com.copower.pmcc.assess.dto.output.data.DataBuildingNewRateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
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


/**
 *
 * 功能描述: 建筑成新率
 *
 * @param:
 * @return:
 * @auther: zch
 * @date: 2018/8/14 15:53
 */
@Service(value = "dataBuildingNewRateService")
public class DataBuildingNewRateService {

    @Autowired
    private DataBuildingNewRateDao dataBuildingNewRateDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    public List<DataBuildingNewRateVo> dataBuildingNewRateList(){
        List<DataBuildingNewRate> dataBuildingNewRateList = dataBuildingNewRateDao.getDataBuildingNewRateList(new DataBuildingNewRate());
        List<DataBuildingNewRateVo> dataBuildingNewRateVoList = LangUtils.transform(dataBuildingNewRateList, p -> {
            return getDataBuildingNewRateVo(p);
        });
        return dataBuildingNewRateVoList;
    }


    public BootstrapTableVo getDataBuildingNewRateList(DataBuildingNewRate dataBuildingNewRate) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataBuildingNewRate> dataBuildingNewRateList = dataBuildingNewRateDao.getDataBuildingNewRateList(dataBuildingNewRate);
        List<DataBuildingNewRateVo> dataBuildingNewRateVoList = LangUtils.transform(dataBuildingNewRateList, p -> {
            return getDataBuildingNewRateVo(p);
        });
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(dataBuildingNewRateVoList) ? new ArrayList<DataBuildingNewRateVo>() : dataBuildingNewRateVoList);
        return vo;
    }

    @Transactional
    public boolean addDataBuildingNewRate(DataBuildingNewRate dataBuildingNewRate)  {
        dataBuildingNewRate.setCreator(commonService.thisUserAccount());
        return dataBuildingNewRateDao.addDataBuildingNewRate(dataBuildingNewRate);
    }

    public boolean editDataBuildingNewRate(DataBuildingNewRate dataBuildingNewRate)  {
        return dataBuildingNewRateDao.editDataBuildingNewRate(dataBuildingNewRate);
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

    public boolean deleteDataBuildingNewRate(Integer id) {
        return dataBuildingNewRateDao.deleteDataBuildingNewRate(id);
    }
}
