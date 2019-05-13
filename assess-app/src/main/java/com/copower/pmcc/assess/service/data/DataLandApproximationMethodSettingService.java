package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataLandApproximationMethodSettingDao;
import com.copower.pmcc.assess.dal.basis.entity.DataLandApproximationMethodSetting;
import com.copower.pmcc.assess.dto.output.data.DataLandApproximationMethodSettingVo;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zch
 * @date: 2019/4/29 18:18
 * @description:土地逼近法补偿配置
 */
@Service
public class DataLandApproximationMethodSettingService {

    @Autowired
    private DataLandApproximationMethodSettingDao dataLandDetailAchievementDao;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public boolean saveDataLandApproximationMethodSetting(DataLandApproximationMethodSetting oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            return dataLandDetailAchievementDao.saveDataLandApproximationMethodSetting(oo);
        } else {
            return dataLandDetailAchievementDao.editDataLandApproximationMethodSetting(oo);
        }
    }

    public boolean deleteDataLandApproximationMethodSetting(Integer id) {
        return dataLandDetailAchievementDao.deleteDataLandApproximationMethodSetting(id);
    }

    public DataLandApproximationMethodSetting getDataLandApproximationMethodSettingById(Integer id) {
        return dataLandDetailAchievementDao.getDataLandApproximationMethodSettingById(id);
    }

    public List<DataLandApproximationMethodSetting> getDataLandApproximationMethodSettingList(DataLandApproximationMethodSetting oo) {
        return dataLandDetailAchievementDao.getDataLandApproximationMethodSettingList(oo);
    }

    public BootstrapTableVo getBootstrapTableVo(DataLandApproximationMethodSetting oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLandApproximationMethodSetting> list = getDataLandApproximationMethodSettingList(oo);
        List<DataLandApproximationMethodSettingVo> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(po -> voList.add(getDataLandApproximationMethodSettingVo(po)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    public DataLandApproximationMethodSettingVo getDataLandApproximationMethodSettingVo(DataLandApproximationMethodSetting oo) {
        if (oo == null) {
            return null;
        }
        DataLandApproximationMethodSettingVo vo = new DataLandApproximationMethodSettingVo();
        org.springframework.beans.BeanUtils.copyProperties(oo, vo);
        vo.setAreaName(erpAreaService.getAreaFullName(oo.getProvince(), oo.getCity(), oo.getDistrict()));
        vo.setCategoryName(baseDataDicService.getNameById(oo.getCategory()));
        if (oo.getAmountMoney() != null)
            vo.setBhouPrice(oo.getAmountMoney().multiply(new BigDecimal("666.67")).multiply(new BigDecimal("0.91")).setScale(2,BigDecimal.ROUND_HALF_UP));
            vo.setSquareMoney(oo.getAmountMoney().multiply(new BigDecimal("0.91")).setScale(2,BigDecimal.ROUND_HALF_UP));
        return vo;
    }


}
