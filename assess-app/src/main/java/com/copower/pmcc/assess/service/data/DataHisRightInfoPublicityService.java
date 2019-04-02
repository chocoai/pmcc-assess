package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataHisRightInfoPublicityDao;
import com.copower.pmcc.assess.dal.basis.entity.DataHisRightInfoPublicity;
import com.copower.pmcc.assess.dto.output.ZtreeVo;
import com.copower.pmcc.assess.dto.output.data.DataHisRightInfoPublicityVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DataHisRightInfoPublicityService {
    @Autowired
    private DataHisRightInfoPublicityDao dataHisRightInfoPublicityDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private ErpAreaService erpAreaService;


    public DataHisRightInfoPublicity getByDataHisRightInfoPublicityId(Integer id) {
        DataHisRightInfoPublicity object = dataHisRightInfoPublicityDao.getSingleObject(id);
        return object;
    }


    /**
     * 保存
     *
     * @param dataHisRightInfoPublicity
     * @throws BusinessException
     */
    public boolean saveObject(DataHisRightInfoPublicity dataHisRightInfoPublicity) {
        if (dataHisRightInfoPublicity.getId() != null && dataHisRightInfoPublicity.getId() > 0) {
            return dataHisRightInfoPublicityDao.updateObject(dataHisRightInfoPublicity);
        } else {
            return dataHisRightInfoPublicityDao.addObject(dataHisRightInfoPublicity);
        }
    }


    /**
     * 删除
     *
     * @param id
     * @throws BusinessException
     */
    public boolean deleteDataHisRightInfoPublicity(Integer id) throws BusinessException {
        return dataHisRightInfoPublicityDao.deleteObject(id);
    }

    /**
     * 获取数据列表
     */
    public BootstrapTableVo getDataHisRightInfoPublicityList() {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataHisRightInfoPublicity> list = dataHisRightInfoPublicityDao.getListObject(new DataHisRightInfoPublicity());
        List<DataHisRightInfoPublicityVo> vos = LangUtils.transform(list, p -> getHisRightInfoPublicityVo(p));
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<DataHisRightInfoPublicity>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public DataHisRightInfoPublicityVo getHisRightInfoPublicityVo(DataHisRightInfoPublicity reportAnalysis) {
        if (reportAnalysis == null) return null;
        DataHisRightInfoPublicityVo vo = new DataHisRightInfoPublicityVo();
        BeanUtils.copyProperties(reportAnalysis, vo);
        if (org.apache.commons.lang.StringUtils.isNotBlank(reportAnalysis.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(reportAnalysis.getProvince()));//省
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(reportAnalysis.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(reportAnalysis.getCity()));//市或者县
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(reportAnalysis.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(reportAnalysis.getDistrict()));//县
        }

        return vo;
    }


    //获取省份
    public List<ZtreeVo> getDataHisRightInfoPublicityTree(Integer pid) {
        ZtreeVo vo;
        List<SysAreaDto> sysAreaDtoList = erpRpcToolsService.getSysAreaDtoList(String.valueOf(pid));
        List<ZtreeVo> ztreeVoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(sysAreaDtoList)) {
            for (SysAreaDto item : sysAreaDtoList) {
                vo = new ZtreeVo();
                vo.setId(Integer.valueOf(item.getAreaId()));
                vo.setParent(false);
                if (CollectionUtils.isNotEmpty(erpRpcToolsService.getSysAreaDtoList(item.getAreaId()))) {
                    vo.setParent(true);
                }
                vo.setName(item.getName());
                vo.setPid(Integer.valueOf(item.getParentId()));

                ztreeVoList.add(vo);
            }
        }
        return ztreeVoList;
    }

    public DataHisRightInfoPublicity getContent(Integer areaId) {
        //是否与区匹配
        DataHisRightInfoPublicity district = new DataHisRightInfoPublicity();
        district.setDistrict(String.valueOf(areaId));
        List<DataHisRightInfoPublicity> districtObject = dataHisRightInfoPublicityDao.getListObject(district);
        if (CollectionUtils.isNotEmpty(districtObject)) {
            return districtObject.get(0);
        }
        //是否与市匹配
        List<DataHisRightInfoPublicity> cityObject = dataHisRightInfoPublicityDao.getCityContent(String.valueOf(areaId));
        if (CollectionUtils.isNotEmpty(cityObject)) {
            return cityObject.get(0);
        }
        return null;
    }


    /**
     * 通过省市区id获取他权信息
     *
     * @param province 省id
     * @param city     市id
     * @param district 区id
     */
    public DataHisRightInfoPublicity getDataHisRightInfoPublicity(String province, String city, String district) {
        //district不必判断(example:北京市)
        if (StringUtils.isEmpty(province) || StringUtils.isEmpty(city)) {
            return null;
        }
        DataHisRightInfoPublicity infoPublicity = new DataHisRightInfoPublicity();
        infoPublicity.setProvince(province);
        infoPublicity.setCity(city);
        if (StringUtils.isNotBlank(district)) {
            infoPublicity.setDistrict(district);
        }
        List<DataHisRightInfoPublicity> districtObject = dataHisRightInfoPublicityDao.getListObject(infoPublicity);
        if (CollectionUtils.isNotEmpty(districtObject)) {
            return districtObject.get(0);
        }
        return null;
    }


}
