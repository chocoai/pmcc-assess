package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.DateHelp;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDao;
import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndex;
import com.copower.pmcc.assess.dto.input.data.DataHousePriceIndexDto;
import com.copower.pmcc.assess.dto.output.data.DataHousePriceIndexVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
public class DataHousePriceIndexService {

    @Autowired
    private DataHousePriceIndexDao dataHousePriceIndexDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;

    public Integer saveAndUpdateDataHousePriceIndex(DataHousePriceIndexDto dataHousePriceIndexDto){
        DataHousePriceIndex dataHousePriceIndex = new DataHousePriceIndex();
        BeanUtils.copyProperties(dataHousePriceIndexDto,dataHousePriceIndex);
        if (dataHousePriceIndex.getId()==null){
            dataHousePriceIndex.setCreator(commonService.thisUserAccount());
            return dataHousePriceIndexDao.addDataHousePriceIndex(dataHousePriceIndex);
        }else {
            dataHousePriceIndexDao.updateDataHousePriceIndex(dataHousePriceIndex);
            return null;
        }
    }

    public DataHousePriceIndex getDataHousePriceIndexById(Integer id){
        return dataHousePriceIndexDao.getDataHousePriceIndexById(id);
    }

    public BootstrapTableVo getDataHousePriceIndexListVos(Date startTime, Date endTime){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataHousePriceIndexVo> vos = Lists.newArrayList();
        List<DataHousePriceIndex> dataHousePriceIndexList = dataHousePriceIndexDao.listEndStart(startTime,endTime);
        if (!ObjectUtils.isEmpty(dataHousePriceIndexList)){
            for (DataHousePriceIndex dataHousePriceIndex:dataHousePriceIndexList){
                vos.add(getDataHousePriceIndexVo(dataHousePriceIndex));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }
    public  List<DataHousePriceIndexVo> landLevels(DataHousePriceIndex dataHousePriceIndex){
        List<DataHousePriceIndex> dataHousePriceIndexs = dataHousePriceIndexDao.getDataHousePriceIndexList(dataHousePriceIndex);
        List<DataHousePriceIndexVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataHousePriceIndexs)){
            for (DataHousePriceIndex landLevel:dataHousePriceIndexs){
                vos.add(getDataHousePriceIndexVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDataHousePriceIndex(DataHousePriceIndex dataHousePriceIndex){
        dataHousePriceIndexDao.removeDataHousePriceIndex(dataHousePriceIndex);
    }

    public DataHousePriceIndexVo getDataHousePriceIndexVo(DataHousePriceIndex dataHousePriceIndex){
        DataHousePriceIndexVo vo = new DataHousePriceIndexVo();
        BeanUtils.copyProperties(dataHousePriceIndex,vo);
        if (dataHousePriceIndex.getYearMonthCalendar() != null){
            vo.setYearMonthCalendarName(DateHelp.getDateHelp().printDate(dataHousePriceIndex.getYearMonthCalendar()));
        }
        if (StringUtils.isNotBlank(dataHousePriceIndex.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(dataHousePriceIndex.getProvince()));//省
        }
        if (StringUtils.isNotBlank(dataHousePriceIndex.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(dataHousePriceIndex.getCity()));//市或者县
        }
        if (StringUtils.isNotBlank(dataHousePriceIndex.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(dataHousePriceIndex.getDistrict()));//县
        }
        return vo;
    }
}
