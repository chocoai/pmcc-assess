package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataAllocationCorrectionCoefficientVolumeRatioDao;
import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatio;
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
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean deleteDataAllocationCorrectionCoefficientVolumeRatio(Integer id){
        return dataLandDetailAchievementDao.deleteDataAllocationCorrectionCoefficientVolumeRatio(id);
    }

    public DataAllocationCorrectionCoefficientVolumeRatio getDataAllocationCorrectionCoefficientVolumeRatioById(Integer id){
        return dataLandDetailAchievementDao.getDataAllocationCorrectionCoefficientVolumeRatioById(id);
    }

    public List<DataAllocationCorrectionCoefficientVolumeRatio> getDataAllocationCorrectionCoefficientVolumeRatioList(DataAllocationCorrectionCoefficientVolumeRatio oo){
        return dataLandDetailAchievementDao.getDataAllocationCorrectionCoefficientVolumeRatioList(oo);
    }

    public BootstrapTableVo getBootstrapTableVo(DataAllocationCorrectionCoefficientVolumeRatio oo){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataAllocationCorrectionCoefficientVolumeRatio> list = getDataAllocationCorrectionCoefficientVolumeRatioList(oo);
        List<DataAllocationCorrectionCoefficientVolumeRatioVo> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)){
            list.stream().forEach(po -> voList.add(getDataAllocationCorrectionCoefficientVolumeRatioVo(po)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    public DataAllocationCorrectionCoefficientVolumeRatioVo getDataAllocationCorrectionCoefficientVolumeRatioVo(DataAllocationCorrectionCoefficientVolumeRatio oo){
        if (oo==null){
            return null;
        }
        DataAllocationCorrectionCoefficientVolumeRatioVo vo = new DataAllocationCorrectionCoefficientVolumeRatioVo();
        org.springframework.beans.BeanUtils.copyProperties(oo,vo);
        vo.setAreaName(erpAreaService.getAreaFullName(oo.getProvince(),oo.getCity(),oo.getDistrict()));
        vo.setPurposeName(baseDataDicService.getNameById(oo.getPurpose()));
        return vo;
    }
}
