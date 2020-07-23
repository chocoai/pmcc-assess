package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataAreaAssessmentBonusDao;
import com.copower.pmcc.assess.dal.basis.entity.DataAreaAssessmentBonus;
import com.copower.pmcc.assess.dto.output.data.DataAreaAssessmentBonusVo;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 10:01
 * @Description:考核加分配置
 */
@Service
public class DataAreaAssessmentBonusService {
    @Autowired
    private DataAreaAssessmentBonusDao dataAreaAssessmentBonusDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void saveAndUpdateDataAreaAssessmentBonus(DataAreaAssessmentBonus dataAreaAssessmentBonus) {
        if (dataAreaAssessmentBonus.getId() == null || dataAreaAssessmentBonus.getId() <= 0) {
            dataAreaAssessmentBonus.setCreator(commonService.thisUserAccount());
            dataAreaAssessmentBonusDao.addDataAreaAssessmentBonus(dataAreaAssessmentBonus);
        } else {
            dataAreaAssessmentBonusDao.updateDataAreaAssessmentBonus(dataAreaAssessmentBonus);
        }
    }

    public DataAreaAssessmentBonus getDataAreaAssessmentBonusById(Integer id) {
        return dataAreaAssessmentBonusDao.getDataAreaAssessmentBonusById(id);
    }

    public BootstrapTableVo getDataAreaAssessmentBonusListVos(String province, String city, String district) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataAreaAssessmentBonusVo> vos = dataAreaAssessmentBonusVos(province, city, district);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DataAreaAssessmentBonusVo> dataAreaAssessmentBonusVos(DataAreaAssessmentBonus dataAreaAssessmentBonus) {
        List<DataAreaAssessmentBonus> dataAreaAssessmentBonuss = dataAreaAssessmentBonusDao.getDataAreaAssessmentBonusList(dataAreaAssessmentBonus);
        List<DataAreaAssessmentBonusVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataAreaAssessmentBonuss)) {
            for (DataAreaAssessmentBonus landLevel : dataAreaAssessmentBonuss) {
                vos.add(getDataAreaAssessmentBonusVo(landLevel));
            }
        }
        return vos;
    }

    public List<DataAreaAssessmentBonusVo> dataAreaAssessmentBonusVos(String province, String city, String district) {
        List<DataAreaAssessmentBonus> dataAreaAssessmentBonuss = dataAreaAssessmentBonusDao.getDataAreaAssessmentBonusList(province, city, district);
        List<DataAreaAssessmentBonusVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataAreaAssessmentBonuss)) {
            for (DataAreaAssessmentBonus landLevel : dataAreaAssessmentBonuss) {
                vos.add(getDataAreaAssessmentBonusVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDataAreaAssessmentBonus(DataAreaAssessmentBonus dataAreaAssessmentBonus) {
        dataAreaAssessmentBonusDao.removeDataAreaAssessmentBonus(dataAreaAssessmentBonus);
    }

    public DataAreaAssessmentBonusVo getDataAreaAssessmentBonusVo(DataAreaAssessmentBonus dataAreaAssessmentBonus) {
        DataAreaAssessmentBonusVo vo = new DataAreaAssessmentBonusVo();
        BeanUtils.copyProperties(dataAreaAssessmentBonus, vo);
        if (StringUtils.isNotBlank(dataAreaAssessmentBonus.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(dataAreaAssessmentBonus.getProvince()));
        }
        if (StringUtils.isNotBlank(dataAreaAssessmentBonus.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(dataAreaAssessmentBonus.getCity()));
        }
        if (StringUtils.isNotBlank(dataAreaAssessmentBonus.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(dataAreaAssessmentBonus.getDistrict()));
        }
        return vo;
    }

    /**
     * 根据区域获取配置信息
     * 当区县未找到对应配置则需到市级寻找
     *
     * @param province
     * @param city
     * @param distric
     * @return
     */
    public DataAreaAssessmentBonus getDataAreaAssessmentBonusByArea(String province, String city, String distric) {
        if (StringUtils.isBlank(province) || StringUtils.isBlank(city)) return null;
        DataAreaAssessmentBonus dataAreaAssessmentBonus = new DataAreaAssessmentBonus();
        dataAreaAssessmentBonus.setProvince(province);
        dataAreaAssessmentBonus.setCity(city);
        if (StringUtils.isNotBlank(distric)) {
            dataAreaAssessmentBonus.setDistrict(distric);
            List<DataAreaAssessmentBonus> list = dataAreaAssessmentBonusDao.getDataAreaAssessmentBonusList(dataAreaAssessmentBonus);
            if (CollectionUtils.isEmpty(list))
                return getAssessmentBonusByArea(province, city);
            else
                return list.get(0);
        } else {
            return getAssessmentBonusByArea(province, city);
        }
    }

    public DataAreaAssessmentBonus getAssessmentBonusByArea(String province, String city) {
        if (StringUtils.isBlank(province) || StringUtils.isBlank(city)) return null;
        DataAreaAssessmentBonus dataAreaAssessmentBonus = new DataAreaAssessmentBonus();
        dataAreaAssessmentBonus.setProvince(province);
        dataAreaAssessmentBonus.setCity(city);
        List<DataAreaAssessmentBonus> list = dataAreaAssessmentBonusDao.getDataAreaAssessmentBonusList(dataAreaAssessmentBonus);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0);
    }
}
