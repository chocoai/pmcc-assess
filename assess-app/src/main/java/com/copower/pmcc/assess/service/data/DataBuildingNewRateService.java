package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.enums.DataBuildingNewRateEnum;
import com.copower.pmcc.assess.dal.dao.DataBuildingNewRateDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.DataBuildingNewRate;
import com.copower.pmcc.assess.dto.output.DataBuildingNewRateVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service(value = "dataBuildingNewRateService")
public class DataBuildingNewRateService {

    @Autowired
    private DataBuildingNewRateDao dataBuildingNewRateDao;

    public BootstrapTableVo getDataBuildingNewRateVo(){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataBuildingNewRate> dataBuildingNewRateList = change(getDataBuildingNewRateList());
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(dataBuildingNewRateList) ? new ArrayList<DataBuildingNewRate>() : dataBuildingNewRateList);
        return vo;
    }

    public BootstrapTableVo getDataBuildingNewRateVo(String buildingStructure){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataBuildingNewRate> dataBuildingNewRateList = change(getDataBuildingNewRateList(buildingStructure));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(dataBuildingNewRateList) ? new ArrayList<DataBuildingNewRate>() : dataBuildingNewRateList);
        return vo;
    }



    public List<DataBuildingNewRate> change(List<DataBuildingNewRate> dataBuildingNewRates){
        for (DataBuildingNewRate d:dataBuildingNewRates){
            d.setUseChange(change(d.getBuildingUse()));
        }
        return dataBuildingNewRates;
    }

    @Deprecated
    private List<DataBuildingNewRateVo> changeV(List<DataBuildingNewRate> dataBuildingNewRates){
        List<DataBuildingNewRateVo> dataBuildingNewRateVos = new ArrayList<DataBuildingNewRateVo>();
        DataBuildingNewRateVo dataBuildingNewRateVo = null;
        for (DataBuildingNewRate buildingNewRate:dataBuildingNewRates){
            dataBuildingNewRateVo = new DataBuildingNewRateVo();
            dataBuildingNewRateVo.setId(buildingNewRate.getId());
            dataBuildingNewRateVo.setUseStr(change(buildingNewRate.getBuildingUse()));
            dataBuildingNewRateVo.setBuildingStructure(buildingNewRate.getBuildingStructure());
            dataBuildingNewRateVo.setDurableLife(buildingNewRate.getDurableLife());
            dataBuildingNewRateVo.setResidualValue(buildingNewRate.getResidualValue());
            dataBuildingNewRateVo.setCreator(buildingNewRate.getCreator());
            dataBuildingNewRateVo.setGmtCreated(buildingNewRate.getGmtCreated());
            dataBuildingNewRateVo.setGmtModified(buildingNewRate.getGmtModified());
        }
        return dataBuildingNewRateVos;
    }

    public BootstrapTableVo change(DataBuildingNewRate dataBuildingNewRate){
        BootstrapTableVo vo = new BootstrapTableVo();
        vo.setTotal(1l);
        vo.setRows(dataBuildingNewRate);
        return vo;
    }

    public String change(Integer val){
        String v = null;
        if (val==DataBuildingNewRateEnum.ONE.getValue()){
            v = DataBuildingNewRateEnum.ProductionRoom.getDescribe();
        }else if (val==DataBuildingNewRateEnum.TWO.getValue()){
            v = DataBuildingNewRateEnum.CorrodedProductionRoom.getDescribe();
        }else if (val==DataBuildingNewRateEnum.Three.getValue()){
            v = DataBuildingNewRateEnum.NonProductionRoom.getDescribe();
        }
        return v;
    }



    @Transactional
    public boolean addDataBuildingNewRate(DataBuildingNewRate dataBuildingNewRate)throws BusinessException {
        boolean flag = false;
        if (dataBuildingNewRate.getGmtCreated()==null)dataBuildingNewRate.setGmtCreated(new Date(System.currentTimeMillis()));
        flag = dataBuildingNewRateDao.addDataBuildingNewRate(dataBuildingNewRate);
        return flag;
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class,RuntimeException.class})
    public boolean editDataBuildingNewRate(DataBuildingNewRate dataBuildingNewRate)throws BusinessException {
        boolean flag = false;
        flag = dataBuildingNewRateDao.editDataBuildingNewRate(dataBuildingNewRate);
        return flag;
    }

    @Transactional(readOnly = true)
    public DataBuildingNewRate getByiDdataBuildingNewRate(Integer id) {
        DataBuildingNewRate dataBuildingNewRate = null;
        try {
            dataBuildingNewRate = dataBuildingNewRateDao.getByiDdataBuildingNewRate(id);
        }catch (Exception e){
            try {
                throw new BusinessException(""+e.getMessage());
            }catch (BusinessException e1){
                e.printStackTrace();
            }
        }
        return dataBuildingNewRate;
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class,RuntimeException.class})
    public boolean deleteDataBuildingNewRate(Integer id)throws BusinessException {
        boolean flag = false;
        flag = dataBuildingNewRateDao.deleteDataBuildingNewRate(id);
        return  flag;
    }

    @Transactional(readOnly = true)
    public List<DataBuildingNewRate> getDataBuildingNewRateList(String buildingStructure){
        List<DataBuildingNewRate> dataBuildingNewRateList = null;
        Map map = new HashMap();
        if ((buildingStructure!=null))map.put("buildingStructure",buildingStructure);
        try {
             dataBuildingNewRateList=dataBuildingNewRateDao.getDataBuildingNewRateList(map);
        }catch (Exception e){
            try {
                throw new BusinessException(""+e.getMessage());
            }catch (BusinessException e1){
                e.printStackTrace();
            }
        }
        return dataBuildingNewRateList;
    }

    @Transactional(readOnly = true)
    public List<DataBuildingNewRate> getDataBuildingNewRateList(){
        List<DataBuildingNewRate> dataBuildingNewRateList = null;
        try {
            dataBuildingNewRateList = dataBuildingNewRateDao.getDataBuildingNewRateList(null);
        }catch (Exception e){
            try {
                throw new BusinessException(""+e.getMessage());
            }catch (BusinessException e1){
                e.printStackTrace();
            }
        }

        return  dataBuildingNewRateList;
    }
}
