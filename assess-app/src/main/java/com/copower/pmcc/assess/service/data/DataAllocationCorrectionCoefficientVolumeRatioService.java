package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataAllocationCorrectionCoefficientVolumeRatioDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDetailVolumeDao;
import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatio;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailVolume;
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
import com.google.common.collect.Ordering;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    private DataLandLevelDetailVolumeDao dataLandLevelDetailVolumeDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private DataLandLevelDetailVolumeService detailService;

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
        if (StringUtils.isBlank(oo.getProvince()))
            oo.setProvince(null);
        if (StringUtils.isBlank(oo.getCity()))
            oo.setCity(null);
        if (StringUtils.isBlank(oo.getDistrict()))
            oo.setDistrict(null);
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
     * @param landLevelDetailId 土地明细id
     * @param volumetricRate 容积率
     * @return
     */
    public BigDecimal getAmendByVolumetricRate(Integer landLevelDetailId, String volumetricRate) {
        if (StringUtils.isBlank(volumetricRate)) return null;
        DataLandLevelDetailVolume coefficientVolumeRatioDetail = new DataLandLevelDetailVolume();
        coefficientVolumeRatioDetail.setLevelDetailId(landLevelDetailId);
        List<DataLandLevelDetailVolume> detailList = dataLandLevelDetailVolumeDao.getDataLandLevelDetailVolumeList(coefficientVolumeRatioDetail);
        for (DataLandLevelDetailVolume detailItem : detailList) {
            //直接匹配
            if(detailItem.getPlotRatio()==null) continue;
            if (detailItem.getPlotRatio().compareTo(new BigDecimal(volumetricRate)) == 0) {
                return detailItem.getCorrectionFactor();
            }
        }
        //不能直接匹配
        return getAmend(detailList, volumetricRate);
    }

    public BigDecimal getAmend(List<DataLandLevelDetailVolume> detailList, String volumetricRate) {
        if (detailList.size() >= 2) {
            //排序
            Ordering<DataLandLevelDetailVolume> ordering = Ordering.from((o1, o2) -> {
                return (o1.getPlotRatio().compareTo(o2.getPlotRatio()));
            });
            detailList.sort(ordering);
            //如果在两个值间
            for (int i = 0; i < detailList.size() - 1; i++) {
                if (detailList.get(i).getPlotRatio().compareTo(new BigDecimal(volumetricRate)) == -1 &&
                        new BigDecimal(volumetricRate).compareTo(detailList.get(i + 1).getPlotRatio()) == -1) {
                    BigDecimal cardinalNumber = detailList.get(i + 1).getCorrectionFactor().subtract(detailList.get(i).getCorrectionFactor()).divide(detailList.get(i + 1).getPlotRatio().subtract(detailList.get(i).getPlotRatio()), 2, BigDecimal.ROUND_HALF_UP);
                    BigDecimal amend = detailList.get(i).getCorrectionFactor().add(cardinalNumber.multiply(new BigDecimal(volumetricRate).subtract(detailList.get(i).getPlotRatio())));
                    return amend;
                }
            }
            //最小值
            DataLandLevelDetailVolume minValue = detailList.get(0);
            //最大值
            DataLandLevelDetailVolume maxValue = detailList.get(detailList.size() - 1);
            //小于最小值
            if (minValue.getPlotRatio().compareTo(new BigDecimal(volumetricRate)) == 1) {
                DataLandLevelDetailVolume tempValue = detailList.get(1);
                BigDecimal cardinalNumber = tempValue.getCorrectionFactor().subtract(minValue.getCorrectionFactor()).divide(tempValue.getPlotRatio().subtract(minValue.getPlotRatio()), 2, BigDecimal.ROUND_HALF_UP);
                BigDecimal amend = minValue.getCorrectionFactor().subtract(cardinalNumber.multiply(minValue.getPlotRatio().subtract(new BigDecimal(volumetricRate))));
                return amend;
            }
            //大于最大值
            if (new BigDecimal(volumetricRate).compareTo(maxValue.getPlotRatio()) == 1) {
                DataLandLevelDetailVolume tempValue = detailList.get(detailList.size() - 2);
                BigDecimal cardinalNumber = maxValue.getCorrectionFactor().subtract(tempValue.getCorrectionFactor()).divide(maxValue.getPlotRatio().subtract(tempValue.getPlotRatio()), 2, BigDecimal.ROUND_HALF_UP);
                BigDecimal amend = maxValue.getCorrectionFactor().add(cardinalNumber.multiply(new BigDecimal(volumetricRate).subtract(maxValue.getPlotRatio())));
                return amend;
            }
        }
        return null;
    }
}
