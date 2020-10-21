package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataReportGeneralFactorsDao;
import com.copower.pmcc.assess.dal.basis.entity.DataReportGeneralFactors;
import com.copower.pmcc.assess.dto.output.data.DataReportGeneralFactorsVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 报告一般因素
 */
@Service
public class DataReportGeneralFactorsService {
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataReportGeneralFactorsDao dataReportGeneralFactorsDao;
    @Autowired
    private BaseDataDicService baseDataDicService;


    public void saveAndUpdateDataReportGeneralFactors(DataReportGeneralFactors obj) {
        if (obj.getId() == null || obj.getId() == 0){
            if (StringUtils.isBlank(obj.getCreator())) {
                obj.setCreator(commonService.thisUserAccount());
            }
            saveDataReportGeneralFactors(obj) ;
        }else {
            editDataReportGeneralFactors(obj) ;
        }
    }


    public boolean saveDataReportGeneralFactors(DataReportGeneralFactors obj) {
        return dataReportGeneralFactorsDao.saveDataReportGeneralFactors(obj);
    }

    public boolean editDataReportGeneralFactors(DataReportGeneralFactors obj) {
        return dataReportGeneralFactorsDao.editDataReportGeneralFactors(obj);
    }

    public boolean updateByPrimaryKey(DataReportGeneralFactors obj) {
        return dataReportGeneralFactorsDao.updateByPrimaryKey(obj);
    }

    public boolean deleteByIds(List<Integer> ids) {
        return dataReportGeneralFactorsDao.deleteByIds(ids);
    }

    public boolean deleteById(Integer id) {
        return deleteByIds(Arrays.asList(id));
    }

    public DataReportGeneralFactors getDataReportGeneralFactorsById(Integer id){
        return dataReportGeneralFactorsDao.getDataReportGeneralFactorsById(id);
    }

    public List<DataReportGeneralFactors> getDataReportGeneralFactorsList(DataReportGeneralFactors obj) {
        return dataReportGeneralFactorsDao.getDataReportGeneralFactorsList(obj) ;
    }

    public List<DataReportGeneralFactors> getDataReportGeneralFactorsList(String type,String province, String city, String district, String name, String fieldName, Boolean bisEnable, Integer pid){
        return dataReportGeneralFactorsDao.getDataReportGeneralFactorsList(type,province, city, district, name, fieldName, bisEnable, pid) ;
    }

    public BootstrapTableVo getBootstrapTableVo(String type,String province, String city, String district, String name, String fieldName, Boolean bisEnable, Integer pid){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataReportGeneralFactors> list = getDataReportGeneralFactorsList( type,province, city, district, name, fieldName, bisEnable, pid) ;
        vo.setTotal(page.getTotal());
        vo.setRows(LangUtils.transform(list ,oo -> getDataReportGeneralFactorsVo(oo)));
        return vo;
    }

    public BootstrapTableVo getDataReportGeneralFactorsByPid(Integer pid) {
        return getBootstrapTableVo(null,null,null,null,null,null,null,pid);
    }

    /**
     * 获取字典的数据层次
     *
     * @param id
     * @return
     */
    public KeyValueDto getDataAssetsAppraisalDicLevel(Integer id) {
        KeyValueDto keyValueDto = new KeyValueDto();
        DataReportGeneralFactors factors = getDataReportGeneralFactorsById(id);
        DataReportGeneralFactors reportGeneralFactors = getDataReportGeneralFactorsById(factors.getPid());
        if (reportGeneralFactors != null && reportGeneralFactors.getId() != null) {
            getReportFieldLevelRecursion(keyValueDto, reportGeneralFactors.getId());
        }
        keyValueDto.setKey(String.valueOf(factors.getId()));
        keyValueDto.setValue(factors.getName());
        return keyValueDto;
    }

    private void getReportFieldLevelRecursion(KeyValueDto keyValueDto, Integer id) {
        DataReportGeneralFactors generalFactors = getDataReportGeneralFactorsById(id);
        if (generalFactors != null && generalFactors.getId() != null) {
            KeyValueDto kv = new KeyValueDto();
            DataReportGeneralFactors generalFactorsById = getDataReportGeneralFactorsById(generalFactors.getPid());
            if (generalFactorsById != null && generalFactorsById.getId() != null) {
                getReportFieldLevelRecursion(kv, generalFactorsById.getId());
            }
            kv.setKey(String.valueOf(generalFactors.getId()));
            kv.setValue(generalFactors.getName());
            keyValueDto.setKeyValueDto(kv);

        }
    }

    private DataReportGeneralFactorsVo getDataReportGeneralFactorsVo(DataReportGeneralFactors generalFactors) {
        if (generalFactors == null) return null;
        DataReportGeneralFactorsVo vo = new DataReportGeneralFactorsVo();
        BeanUtils.copyProperties(generalFactors, vo);
        if (org.apache.commons.lang.StringUtils.isNotBlank(generalFactors.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(generalFactors.getProvince()));//省
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(generalFactors.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(generalFactors.getCity()));//市或者县
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(generalFactors.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(generalFactors.getDistrict()));//县
        }
        vo.setTypeName(baseDataDicService.getNameById(generalFactors.getType()));
        return vo;
    }

}
