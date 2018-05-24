package com.copower.pmcc.assess.service;

import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kings on 2018-4-18.
 */
@Service
public class ErpAreaService {
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;

    /**
     * 获取省数据
     *
     * @return
     */
    public List<SysAreaDto> getProvinceList() {
        return erpRpcToolsService.getSysAreaDtoList("0");
    }

    /**
     * 获取区域数据by pid
     *
     * @param pid
     * @return
     */
    public List<SysAreaDto> getAreaList(String pid) {
        return erpRpcToolsService.getSysAreaDtoList(pid);
    }

    public SysAreaDto getSysAreaDto(String id) {
        return erpRpcToolsService.getSysAreaDto(id);
    }

    /**
     * 获取区域全名称
     *
     * @param province
     * @param city
     * @param district
     * @return
     */
    public String getAreaFullName(String province, String city, String district) {
        String areaName = new String();
        SysAreaDto sysAreaDto = null;
        if (province != null) {
            sysAreaDto = getSysAreaDto(province);
            if (sysAreaDto != null)
                areaName += sysAreaDto.getName();
        }
        if (city != null) {
            sysAreaDto = getSysAreaDto(city);
            if (sysAreaDto != null)
                areaName += sysAreaDto.getName();
        }
        if (district != null) {
            sysAreaDto = getSysAreaDto(district);
            if (sysAreaDto != null)
                areaName += sysAreaDto.getName();
        }
        return areaName;
    }
}
