package com.copower.pmcc.assess.service;

import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-4-18.
 */
@Service
public class ErpAreaService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;

    /**
     * 功能描述: 验证区域名称是否存在
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/9/26 11:42
     */
    public boolean checkArea(String provinceName, String cityName, String districtName, StringBuilder builder, Map<String,String> map) throws Exception {
        if (org.springframework.util.StringUtils.isEmpty(provinceName)) {
            builder.append("省名称不能为null");
            throw new Exception("异常");
        }
        if (org.springframework.util.StringUtils.isEmpty(cityName)) {
            builder.append("市名称不能为null");
            throw new Exception("异常");
        }
        List<SysAreaDto> sysAreaDtos = erpRpcToolsService.getSysAreaDtoList("0");
        if (!ObjectUtils.isEmpty(sysAreaDtos)) {
            for (SysAreaDto sysAreaDto : sysAreaDtos) {
                if (Objects.equal(provinceName, sysAreaDto.getName())) {//省匹配
                    String areaId = sysAreaDto.getAreaId();//获取省级别的区域id
                    SysAreaDto province = erpRpcToolsService.getSysAreaDto(areaId);//省级别
                    map.put("province",province.getAreaId());
                    List<SysAreaDto> citys = erpRpcToolsService.getSysAreaDtoList(province.getAreaId());
                    if (!ObjectUtils.isEmpty(citys)) {
                        for (SysAreaDto city : citys) {
                            if (Objects.equal(cityName, city.getName())) {//市 匹配
                                map.put("city",city.getAreaId());
                                if (org.springframework.util.StringUtils.isEmpty(districtName)) {//县级可以为null
                                    return true;
                                }
                                if (!org.springframework.util.StringUtils.isEmpty(districtName)) {//市区名称对应,县级传入了值则进行下一步判断
                                    List<SysAreaDto> sysAreaDtoList = erpRpcToolsService.getSysAreaDtoList(city.getAreaId());
                                    if (!ObjectUtils.isEmpty(sysAreaDtoList)) {//县
                                        for (SysAreaDto district : sysAreaDtoList) {
                                            if (Objects.equal(districtName, district.getName())) {//只需要匹配到一个即可
                                                map.put("district",district.getAreaId());
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        logger.error("数据库异常");
                        throw new Exception("数据库异常");//省下面一定会有市或者区 (既然已经匹配到省说明区域下面必须有市)
                    }

                }
            }
        }
        builder.append("某个区域名称填写错误") ;
        return false;
    }

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

    /**
     * 获取单个区域数据信息
     *
     * @param areaId
     * @return
     */
    public SysAreaDto getSysAreaDto(String areaId) {
        return erpRpcToolsService.getSysAreaDto(areaId);
    }

    /**
     * 获取区域名称
     *
     * @param areaId
     * @return
     */
    public String getSysAreaName(String areaId) {
        SysAreaDto sysAreaDto = getSysAreaDto(areaId);
        if (sysAreaDto == null) return "";
        return sysAreaDto.getName();
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
        if (StringUtils.isNotBlank(province)) {
            sysAreaDto = getSysAreaDto(province);
            if (sysAreaDto != null)
                areaName += sysAreaDto.getName();
        }
        if (StringUtils.isNotBlank(city)) {
            sysAreaDto = getSysAreaDto(city);
            if (sysAreaDto != null)
                areaName += sysAreaDto.getName();
        }
        if (StringUtils.isNotBlank(district)) {
            sysAreaDto = getSysAreaDto(district);
            if (sysAreaDto != null)
                areaName += sysAreaDto.getName();
        }
        return areaName;
    }
}
