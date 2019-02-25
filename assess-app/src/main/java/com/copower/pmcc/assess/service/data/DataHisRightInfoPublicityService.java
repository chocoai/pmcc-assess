package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataHisRightInfoPublicityDao;
import com.copower.pmcc.assess.dal.basis.entity.DataHisRightInfoPublicity;
import com.copower.pmcc.assess.dto.output.ZtreeVo;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
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
        infoPublicity.setDistrict(dataHisRightInfoPublicity.getDistrict());
        List<DataHisRightInfoPublicity> listObject = dataHisRightInfoPublicityDao.getListObject(infoPublicity);
        if(CollectionUtils.isNotEmpty(listObject)){
            infoPublicity = listObject.get(0);
            infoPublicity.setContent(dataHisRightInfoPublicity.getContent());
            return dataHisRightInfoPublicityDao.updateObject(infoPublicity);
        }else {
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
        DataHisRightInfoPublicity city = new DataHisRightInfoPublicity();
        city.setCity(String.valueOf(areaId));
        List<DataHisRightInfoPublicity> cityObject = dataHisRightInfoPublicityDao.getListObject(city);
        if (CollectionUtils.isNotEmpty(cityObject)) {
            return cityObject.get(0);
        }
        DataHisRightInfoPublicity district = new DataHisRightInfoPublicity();
        district.setDistrict(String.valueOf(areaId));
        List<DataHisRightInfoPublicity> districtObject = dataHisRightInfoPublicityDao.getListObject(district);
        if (CollectionUtils.isNotEmpty(districtObject)) {
            return districtObject.get(0);
        }
        return null;
    }

}
