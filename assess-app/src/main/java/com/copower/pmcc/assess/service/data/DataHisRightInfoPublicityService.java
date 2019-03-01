package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataHisRightInfoPublicityDao;
import com.copower.pmcc.assess.dal.basis.entity.DataHisRightInfoPublicity;
import com.copower.pmcc.assess.dto.output.ZtreeVo;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
        DataHisRightInfoPublicity infoPublicity = new DataHisRightInfoPublicity();
        infoPublicity.setCity(dataHisRightInfoPublicity.getCity());
        infoPublicity.setProvince(dataHisRightInfoPublicity.getProvince());
        List<DataHisRightInfoPublicity> listObject = new ArrayList<>();
        if (StringUtils.isNotBlank(dataHisRightInfoPublicity.getDistrict())) {
            infoPublicity.setDistrict(dataHisRightInfoPublicity.getDistrict());
            listObject = dataHisRightInfoPublicityDao.getListObject(infoPublicity);
        }
        List<DataHisRightInfoPublicity> districtObject = dataHisRightInfoPublicityDao.getCityContent(dataHisRightInfoPublicity.getCity());
        if (CollectionUtils.isNotEmpty(listObject)) {
            infoPublicity = listObject.get(0);
            infoPublicity.setContent(dataHisRightInfoPublicity.getContent());
            return dataHisRightInfoPublicityDao.updateObject(infoPublicity);
        } else if (StringUtils.isEmpty(dataHisRightInfoPublicity.getDistrict()) && CollectionUtils.isNotEmpty(districtObject)) {
            infoPublicity = districtObject.get(0);
            infoPublicity.setContent(dataHisRightInfoPublicity.getContent());
            return dataHisRightInfoPublicityDao.updateObject(infoPublicity);
        } else {
            dataHisRightInfoPublicity.setCreator(processControllerComponent.getThisUser());
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
     *通过省市区id获取他权信息
     *
     * @param province 省id
     * @param city  市id
     * @param district 区id
     *
     */
    public DataHisRightInfoPublicity getDataHisRightInfoPublicity(String province, String city, String district) {
        //district不必判断(example:北京市)
        if (StringUtils.isEmpty(province) || StringUtils.isEmpty(city)){
            return null;
        }
        DataHisRightInfoPublicity infoPublicity = new DataHisRightInfoPublicity();
        infoPublicity.setProvince(province);
        infoPublicity.setCity(city);
        if (StringUtils.isNotBlank(district)){
            infoPublicity.setDistrict(district);
        }
        List<DataHisRightInfoPublicity> districtObject = dataHisRightInfoPublicityDao.getListObject(infoPublicity);
        if (CollectionUtils.isNotEmpty(districtObject)) {
            return districtObject.get(0);
        }
        return null;
    }


}
