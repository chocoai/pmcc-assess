package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataAllocationCorrectionCoefficientVolumeRatioDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatioDetail;
import com.copower.pmcc.assess.dto.output.data.DataAllocationCorrectionCoefficientVolumeRatioDetailVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zch
 * @date: 2019/4/29 18:18
 * @description:容积率修正系数配置 详情(从表)
 */
@Service
public class DataAllocationCorrectionCoefficientVolumeRatioDetailService {

    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioDetailDao dataLandDetailAchievementDao;
    @Autowired
    private CommonService commonService;

    public boolean saveDataAllocationCorrectionCoefficientVolumeRatioDetail(DataAllocationCorrectionCoefficientVolumeRatioDetail oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            return dataLandDetailAchievementDao.saveDataAllocationCorrectionCoefficientVolumeRatioDetail(oo);
        } else {
            return dataLandDetailAchievementDao.editDataAllocationCorrectionCoefficientVolumeRatioDetail(oo);
        }
    }

    public boolean deleteDataAllocationCorrectionCoefficientVolumeRatioDetail(Integer id){
        return dataLandDetailAchievementDao.deleteDataAllocationCorrectionCoefficientVolumeRatioDetail(id);
    }

    public DataAllocationCorrectionCoefficientVolumeRatioDetail getDataAllocationCorrectionCoefficientVolumeRatioDetailById(Integer id){
        return dataLandDetailAchievementDao.getDataAllocationCorrectionCoefficientVolumeRatioDetailById(id);
    }

    public List<DataAllocationCorrectionCoefficientVolumeRatioDetail> getDataAllocationCorrectionCoefficientVolumeRatioDetailList(DataAllocationCorrectionCoefficientVolumeRatioDetail oo){
        return dataLandDetailAchievementDao.getDataAllocationCorrectionCoefficientVolumeRatioDetailList(oo);
    }

    public BootstrapTableVo getBootstrapTableVo(DataAllocationCorrectionCoefficientVolumeRatioDetail oo){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataAllocationCorrectionCoefficientVolumeRatioDetail> list = getDataAllocationCorrectionCoefficientVolumeRatioDetailList(oo);
        List<DataAllocationCorrectionCoefficientVolumeRatioDetailVo> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)){
            list.stream().forEach(po -> voList.add(getDataAllocationCorrectionCoefficientVolumeRatioDetailVo(po)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    public DataAllocationCorrectionCoefficientVolumeRatioDetailVo getDataAllocationCorrectionCoefficientVolumeRatioDetailVo(DataAllocationCorrectionCoefficientVolumeRatioDetail oo){
        if (oo==null){
            return null;
        }
        DataAllocationCorrectionCoefficientVolumeRatioDetailVo vo = new DataAllocationCorrectionCoefficientVolumeRatioDetailVo();
        org.springframework.beans.BeanUtils.copyProperties(oo,vo);
        return vo;
    }

}
