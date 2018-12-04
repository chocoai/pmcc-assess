package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataPositionDao;
import com.copower.pmcc.assess.dal.basis.entity.DataPosition;
import com.copower.pmcc.assess.dto.output.data.DataPositionVo;
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
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/12/4 10:34
 * @Description:方位维护
 */
@Service
public class DataPositionService {

    @Autowired
    private DataPositionDao dataPositionDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateDataPosition(DataPosition dataPosition) {
        if (dataPosition.getId() == null || dataPosition.getId().intValue() == 0) {
            dataPosition.setCreator(commonService.thisUserAccount());
            try {
                return dataPositionDao.addDataPosition(dataPosition);
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
                return null;
            }
        } else {
            dataPositionDao.updateDataPosition(dataPosition);
            return null;
        }
    }

    public DataPosition getDataPositionById(Integer id) {
        if (id == null) {
            logger.error("null point");
            return null;
        }
        return dataPositionDao.getDataPositionById(id);
    }

    public BootstrapTableVo getDataPositionListVos(String province, String city, String district, String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataPositionVo> vos = dataPositionVoList(province, city, district,name);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DataPositionVo> dataPositionVos(DataPosition dataPosition) {
        List<DataPosition> dataPositions = dataPositionDao.getDataPositionList(dataPosition);
        List<DataPositionVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataPositions)) {
            for (DataPosition landLevel : dataPositions) {
                vos.add(getDataPositionVo(landLevel));
            }
        }
        return vos;
    }

    public List<DataPositionVo> dataPositionVoList(String province, String city, String district, String name) {
        DataPosition dataPosition = new DataPosition();
        if (StringUtils.isNotBlank(province)) {
            dataPosition.setProvince(province);
        }
        if (StringUtils.isNotBlank(city)) {
            dataPosition.setCity(city);
        }
        if (StringUtils.isNotBlank(district)) {
            dataPosition.setDistrict(district);
        }
        if (StringUtils.isNotBlank(name)) {
            dataPosition.setName(name);
        }
        List<DataPosition> dataPositions = dataPositionDao.getDataPositionList(dataPosition);
        List<DataPositionVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataPositions)) {
            for (DataPosition landLevel : dataPositions) {
                vos.add(getDataPositionVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDataPosition(DataPosition dataPosition) {
        dataPositionDao.removeDataPosition(dataPosition);
    }

    public DataPositionVo getDataPositionVo(DataPosition dataPosition) {
        if (dataPosition == null){
            return null;
        }
        DataPositionVo vo = new DataPositionVo();
        BeanUtils.copyProperties(dataPosition, vo);
        if (StringUtils.isNotBlank(dataPosition.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(dataPosition.getProvince()));
        }
        if (StringUtils.isNotBlank(dataPosition.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(dataPosition.getCity()));
        }
        if (StringUtils.isNotBlank(dataPosition.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(dataPosition.getDistrict()));
        }
        return vo;
    }
    
}
