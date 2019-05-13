package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataAllocationCorrectionCoefficientVolumeRatioDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataAllocationCorrectionCoefficientVolumeRatioDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatio;
import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatioDetail;
import com.copower.pmcc.assess.dto.output.data.DataAllocationCorrectionCoefficientVolumeRatioVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: zch
 * @date: 2019/4/29 18:17
 * @description:容积率修正系数配置
 */
@Service
public class DataAllocationCorrectionCoefficientVolumeRatioService {
    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioDao dataLandDetailAchievementDao;
    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioDetailDao dataAllocationCorrectionCoefficientVolumeRatioDetailDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioDetailService detailService;

    public boolean saveDataAllocationCorrectionCoefficientVolumeRatio(DataAllocationCorrectionCoefficientVolumeRatio oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            return dataLandDetailAchievementDao.saveDataAllocationCorrectionCoefficientVolumeRatio(oo);
        } else {
            return dataLandDetailAchievementDao.editDataAllocationCorrectionCoefficientVolumeRatio(oo);
        }
    }

    public boolean deleteDataAllocationCorrectionCoefficientVolumeRatio(Integer id) {
        return dataLandDetailAchievementDao.deleteDataAllocationCorrectionCoefficientVolumeRatio(id);
    }

    public DataAllocationCorrectionCoefficientVolumeRatio getDataAllocationCorrectionCoefficientVolumeRatioById(Integer id) {
        return dataLandDetailAchievementDao.getDataAllocationCorrectionCoefficientVolumeRatioById(id);
    }

    public List<DataAllocationCorrectionCoefficientVolumeRatio> getDataAllocationCorrectionCoefficientVolumeRatioList(DataAllocationCorrectionCoefficientVolumeRatio oo) {
        return dataLandDetailAchievementDao.getDataAllocationCorrectionCoefficientVolumeRatioList(oo);
    }

    public BootstrapTableVo getBootstrapTableVo(DataAllocationCorrectionCoefficientVolumeRatio oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataAllocationCorrectionCoefficientVolumeRatio> list = getDataAllocationCorrectionCoefficientVolumeRatioList(oo);
        List<DataAllocationCorrectionCoefficientVolumeRatioVo> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(po -> voList.add(getDataAllocationCorrectionCoefficientVolumeRatioVo(po)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    public DataAllocationCorrectionCoefficientVolumeRatioVo getDataAllocationCorrectionCoefficientVolumeRatioVo(DataAllocationCorrectionCoefficientVolumeRatio oo) {
        if (oo == null) {
            return null;
        }
        DataAllocationCorrectionCoefficientVolumeRatioVo vo = new DataAllocationCorrectionCoefficientVolumeRatioVo();
        org.springframework.beans.BeanUtils.copyProperties(oo, vo);
        vo.setAreaName(erpAreaService.getAreaFullName(oo.getProvince(), oo.getCity(), oo.getDistrict()));
        vo.setPurposeName(baseDataDicService.getNameById(oo.getPurpose()));
        return vo;
    }

    /**
     * 根据容积率获取容积率修正系数
     *
     * @param province
     * @param city
     * @param district
     * @param volumetricRate 容积率
     * @return
     */
    public BigDecimal getAmendByVolumetricRate(String province, String city, String district, String volumetricRate) {
        //根据容积率找到配置中对应的容积率修正
        List<DataAllocationCorrectionCoefficientVolumeRatio> coefficientVolumeRatioList;
        coefficientVolumeRatioList = dataLandDetailAchievementDao.getDataAllocationCorrectionCoefficientVolumeRatioList(province, city, district);
        if (CollectionUtils.isEmpty(coefficientVolumeRatioList)) {
            coefficientVolumeRatioList = dataLandDetailAchievementDao.getDataAllocationCorrectionCoefficientVolumeRatioList(province, city, null);
        }
        if (CollectionUtils.isNotEmpty(coefficientVolumeRatioList)) {
            Integer masterId = coefficientVolumeRatioList.get(0).getId();
            DataAllocationCorrectionCoefficientVolumeRatioDetail coefficientVolumeRatioDetail = new DataAllocationCorrectionCoefficientVolumeRatioDetail();
            coefficientVolumeRatioDetail.setAllocationVolumeRatioId(masterId);
            List<DataAllocationCorrectionCoefficientVolumeRatioDetail> detailList = dataAllocationCorrectionCoefficientVolumeRatioDetailDao.getDataAllocationCorrectionCoefficientVolumeRatioDetailList(coefficientVolumeRatioDetail);
            for (DataAllocationCorrectionCoefficientVolumeRatioDetail detailItem : detailList) {
                //直接匹配
                if (detailItem.getPlotRatio().compareTo(new BigDecimal(volumetricRate)) == 0) {
                    return detailItem.getCorrectionFactor();
                }
            }
            //在区间中
            return getAmend(detailList, volumetricRate);
        }
        return null;
    }

    public BigDecimal getAmend(List<DataAllocationCorrectionCoefficientVolumeRatioDetail> detailList, String volumetricRate) {
        if (detailList.size() >= 2) {
            //排序
            Ordering<DataAllocationCorrectionCoefficientVolumeRatioDetail> ordering = Ordering.from((o1, o2) -> {
                return (o1.getPlotRatio().compareTo(o2.getPlotRatio()));
            });
            detailList.sort(ordering);
            //最小值
            DataAllocationCorrectionCoefficientVolumeRatioDetail minValue = detailList.get(0);
            //最大值
            DataAllocationCorrectionCoefficientVolumeRatioDetail maxValue = detailList.get(detailList.size() - 1);
            //如果在区间
            if (minValue.getPlotRatio().compareTo(new BigDecimal(volumetricRate)) == -1 && new BigDecimal(volumetricRate).compareTo(maxValue.getPlotRatio()) == -1) {
                BigDecimal cardinalNumber = maxValue.getCorrectionFactor().subtract(minValue.getCorrectionFactor()).divide(maxValue.getPlotRatio().subtract(minValue.getPlotRatio()),2,BigDecimal.ROUND_HALF_UP);
                BigDecimal amend = minValue.getCorrectionFactor().add(cardinalNumber.multiply(new BigDecimal(volumetricRate).subtract(minValue.getPlotRatio())));
                return amend;
            }
        }
        return null;
    }
}
