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
     * @return
     */
    public List<SysAreaDto> getProvinceList(){
        return erpRpcToolsService.getSysAreaDtoList("0");
    }

    /**
     * 获取区域数据by pid
     * @param pid
     * @return
     */
    public List<SysAreaDto> getAreaList(String pid){
        return erpRpcToolsService.getSysAreaDtoList(pid);
    }

    public SysAreaDto getSysAreaDto(Integer id){
        return  erpRpcToolsService.getSysAreaDto(""+id);
    }
}
