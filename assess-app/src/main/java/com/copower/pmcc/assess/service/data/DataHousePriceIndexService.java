package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataHousePriceIndexDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndex;
import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndexDetail;
import com.copower.pmcc.assess.dto.output.data.DataHousePriceIndexVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 房价指数
 */
@Service
public class DataHousePriceIndexService {
    @Autowired
    private DataHousePriceIndexDetailService housePriceIndexDetailService;
    @Autowired
    private DataHousePriceIndexDao dataHousePriceIndexDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public List<DataHousePriceIndexDetail> getDataHousePriceIndexDetailList(Integer housePriceId) {
        DataHousePriceIndexDetail query = new DataHousePriceIndexDetail();
        query.setHousePriceId(housePriceId);
        return housePriceIndexDetailService.getDataHousePriceIndexDetailList(query);
    }

    public boolean saveAndUpdateDataHousePriceIndex(DataHousePriceIndex dataHousePriceIndex) {
        if (dataHousePriceIndex.getId() == null || dataHousePriceIndex.getId() == 0) {
            dataHousePriceIndex.setCreator(commonService.thisUserAccount());
            return dataHousePriceIndexDao.saveDataHousePriceIndex(dataHousePriceIndex);
        } else {
            return dataHousePriceIndexDao.editDataHousePriceIndex(dataHousePriceIndex);
        }
    }

    public DataHousePriceIndex getDataHousePriceIndexById(Integer id) {
        return dataHousePriceIndexDao.getDataHousePriceIndexById(id);
    }

    public BootstrapTableVo getDataHousePriceIndexListVos(String province, String city, String district, Integer type) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataHousePriceIndex> dataHouseIndexList = dataHousePriceIndexDao.getDataPriceIndexList(province, city, district, type, null);
        List<DataHousePriceIndexVo> vos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(dataHouseIndexList)) {
            for (DataHousePriceIndex item : dataHouseIndexList) {
                vos.add(getDataHousePriceIndexVo(item));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataHousePriceIndexVo>() : vos);
        return vo;
    }

    public List<DataHousePriceIndexVo> getDataHousePriceIndexList(DataHousePriceIndex dataHousePriceIndex) {
        List<DataHousePriceIndex> dataHousePriceIndexs = dataHousePriceIndexDao.getDataHousePriceIndexList(dataHousePriceIndex);
        List<DataHousePriceIndexVo> vos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(dataHousePriceIndexs)) {
            for (DataHousePriceIndex landLevel : dataHousePriceIndexs) {
                vos.add(getDataHousePriceIndexVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDataHousePriceIndex(DataHousePriceIndex dataHousePriceIndex) {
        dataHousePriceIndexDao.deleteDataHousePriceIndexList(dataHousePriceIndex);
    }

    public boolean deleteDataHousePriceIndex(Integer id) {
        return dataHousePriceIndexDao.deleteDataHousePriceIndex(id);
    }

    public DataHousePriceIndexVo getDataHousePriceIndexVo(DataHousePriceIndex dataHousePriceIndex) {
        if (dataHousePriceIndex == null) {
            return null;
        }
        DataHousePriceIndexVo vo = new DataHousePriceIndexVo();
        BeanUtils.copyProperties(dataHousePriceIndex, vo);
        if (StringUtils.isNotBlank(dataHousePriceIndex.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(dataHousePriceIndex.getProvince()));
        }
        if (StringUtils.isNotBlank(dataHousePriceIndex.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(dataHousePriceIndex.getCity()));
        }
        vo.setPurposeName(baseDataDicService.getNameById(dataHousePriceIndex.getPurpose()));
        if (StringUtils.isNotBlank(dataHousePriceIndex.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(dataHousePriceIndex.getDistrict()));
        }
        vo.setTypeName(baseDataDicService.getNameById(dataHousePriceIndex.getType()));
        vo.setAreaName(erpAreaService.getAreaFullName(dataHousePriceIndex.getProvince(), dataHousePriceIndex.getCity(), dataHousePriceIndex.getDistrict()));
        return vo;
    }

    /**
     * 获取土地指数数据
     *
     * @param province
     * @param city
     * @param district
     * @param date
     * @return
     */
    public DataHousePriceIndex getLandPriceIndexByArea(String province, String city, String district, Date date) {
        if (StringUtils.isBlank(province) || StringUtils.isBlank(city) || date == null) return null;
        BaseDataDic dataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_INDEX_LAND_TYPE);//土地指数
        return getPriceIndexByType(province, city, district, date, dataDic.getId());
    }

    /**
     * 获取房产指数数据
     *
     * @param province
     * @param city
     * @param district
     * @param date
     * @return
     */
    public DataHousePriceIndex getHousePriceIndexByArea(String province, String city, String district, Date date) {
        if (StringUtils.isBlank(province) || StringUtils.isBlank(city) || date == null) return null;
        BaseDataDic dataDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.DATA_INDEX_HOUSE_TYPE);//房产指数
        return getPriceIndexByType(province, city, district, date, dataDic.getId());
    }

    /**
     * 获取土地指数数据
     *
     * @param province
     * @param city
     * @param district
     * @param date
     * @return
     */
    public DataHousePriceIndex getPriceIndexByType(String province, String city, String district, Date date, Integer type) {
        //1.根据省市区及报告期找出有效的数据，如果没有找则到上一级寻找
        if (StringUtils.isBlank(province) || StringUtils.isBlank(city) || date == null) return null;
        DataHousePriceIndex dataHousePriceIndex = null;
        if (StringUtils.isNotBlank(district)) {
            List<DataHousePriceIndex> priceIndexList = dataHousePriceIndexDao.getDataPriceIndexList(province, city, district, type, date);
            if (CollectionUtils.isNotEmpty(priceIndexList)) {
                dataHousePriceIndex = priceIndexList.get(0);
            }
        }
        if (dataHousePriceIndex == null) {
            List<DataHousePriceIndex> priceIndexList = dataHousePriceIndexDao.getDataPriceIndexList(province, city, null, type, date);
            if (CollectionUtils.isNotEmpty(priceIndexList)) {
                dataHousePriceIndex = priceIndexList.get(0);
            }
        }
        return dataHousePriceIndex;
    }

    /**
     * 获取期日修正系数
     *
     * @param priceIndex
     * @return
     */
    public BigDecimal getCorrectionFactor(DataHousePriceIndex priceIndex, Date valueDate) {
        //期日修正系数=当前系数/基期系数
        if (priceIndex == null || valueDate == null) return null;
        Date currDate = DateUtils.convertDate(valueDate, DateUtils.MONTH_PATTERN);
        List<DataHousePriceIndexDetail> priceIndexDetails = housePriceIndexDetailService.getPriceIndexDetailListByMasterId(priceIndex.getId());
        if (CollectionUtils.isEmpty(priceIndexDetails)) return null;
        List<DataHousePriceIndexDetail> basePriceIndexDetails = LangUtils.filter(priceIndexDetails, o -> Boolean.TRUE.equals(o.getBisBase()));
        if (CollectionUtils.isEmpty(basePriceIndexDetails)) return null;
        for (DataHousePriceIndexDetail priceIndexDetail : priceIndexDetails) {
            Date startDate = DateUtils.convertDate(priceIndexDetail.getStartDate(), DateUtils.MONTH_PATTERN);
            Date endDate = DateUtils.convertDate(priceIndexDetail.getEndDate(), DateUtils.MONTH_PATTERN);
            if (DateUtils.compareDate(currDate, startDate) > -1 && DateUtils.compareDate(endDate, currDate) > -1) {
                return priceIndexDetail.getIndexNumber().divide(basePriceIndexDetails.get(0).getIndexNumber(), 2, RoundingMode.HALF_UP);
            }
        }
        return null;
    }
}
