package com.copower.pmcc.assess.service.data;

import com.aspose.words.ControlChar;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.StreamUtils;
import com.copower.pmcc.assess.dal.basis.dao.data.DataReportGeneralFactorsDao;
import com.copower.pmcc.assess.dal.basis.entity.DataReportGeneralFactors;
import com.copower.pmcc.assess.dto.output.data.DataReportGeneralFactorsVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.generate.GenerateCommonMethod;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (obj.getId() == null || obj.getId() == 0) {
            if (StringUtils.isBlank(obj.getCreator())) {
                obj.setCreator(commonService.thisUserAccount());
            }
            saveDataReportGeneralFactors(obj);
        } else {
            editDataReportGeneralFactors(obj);
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

    public DataReportGeneralFactors getDataReportGeneralFactorsById(Integer id) {
        return dataReportGeneralFactorsDao.getDataReportGeneralFactorsById(id);
    }

    public List<DataReportGeneralFactors> getDataReportGeneralFactorsList(DataReportGeneralFactors obj) {
        return dataReportGeneralFactorsDao.getDataReportGeneralFactorsList(obj);
    }

    public List<DataReportGeneralFactors> getDataReportGeneralFactorsList(String type, String province, String city, String district, String name, String fieldName, Boolean bisEnable, Integer pid) {
        return dataReportGeneralFactorsDao.getDataReportGeneralFactorsList(type, province, city, district, name, fieldName, bisEnable, pid);
    }

    public BootstrapTableVo getBootstrapTableVo(String type, String province, String city, String district, String name, String fieldName, Boolean bisEnable, Integer pid) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataReportGeneralFactors> list = getDataReportGeneralFactorsList(type, province, city, district, name, fieldName, bisEnable, pid);
        vo.setTotal(page.getTotal());
        vo.setRows(LangUtils.transform(list, oo -> getDataReportGeneralFactorsVo(oo)));
        return vo;
    }

    public BootstrapTableVo getDataReportGeneralFactorsByPid(Integer pid) {
        return getBootstrapTableVo(null, null, null, null, null, null, null, pid);
    }

    public List<DataReportGeneralFactors> getDataReportGeneralFactorsListByPid(Integer pid) {
        return getDataReportGeneralFactorsList(null, null, null, null, null, null, null, pid);
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

    public Map<String, String> getFactorsMap(String province, String city, String district, String name) throws Exception {
        String[] chineseNumbers = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二", "十三", "十四"};
        Map<String, String> map = new HashMap<>();
        List<KeyValueDto> keyValueDtoList = Arrays.asList(new KeyValueDto("text-indent", "2em"),
                new KeyValueDto("font-family", "仿宋_GB2312"),
                new KeyValueDto("font-size", "14pt"),
                new KeyValueDto("line-height", "100%"));
        final int repeat = 2;
        //目前只处理4个层级
        List<DataReportGeneralFactors> dataReportGeneralFactorsList = getDataReportGeneralFactorsList(null, province, city, district, name, null, true, null);
        if (CollectionUtils.isEmpty(dataReportGeneralFactorsList)){
            dataReportGeneralFactorsList = getDataReportGeneralFactorsList(null, province, city, null, name, null, true, null);
        }
        if (CollectionUtils.isEmpty(dataReportGeneralFactorsList)){
            dataReportGeneralFactorsList = getDataReportGeneralFactorsList(null, province, null, null, name, null, true, null);
        }
        if (CollectionUtils.isEmpty(dataReportGeneralFactorsList)){
            dataReportGeneralFactorsList = getDataReportGeneralFactorsList(null, province, null, null, null, null, true, null);
        }
        if (CollectionUtils.isNotEmpty(dataReportGeneralFactorsList)) {
            for (DataReportGeneralFactors factors : dataReportGeneralFactorsList) {
                List<DataReportGeneralFactors> reportGeneralFactorsList = getDataReportGeneralFactorsListByPid(factors.getId());
                if (CollectionUtils.isEmpty(reportGeneralFactorsList)) {
                    continue;
                }
                StringBuilder stringBuilder = new StringBuilder();
                //1
                for (DataReportGeneralFactors generalFactors : reportGeneralFactorsList) {
                    Integer sorting = generalFactors.getSorting() == null ? 0 : generalFactors.getSorting();
                    stringBuilder.append(AsposeUtils.getWarpCssHtml(getFactorsHandleText(generalFactors, chineseNumbers[sorting]), keyValueDtoList));
                    //插入换行符
                    stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, repeat));
                    List<DataReportGeneralFactors> generalFactorsList = getDataReportGeneralFactorsListByPid(generalFactors.getId());
                    if (CollectionUtils.isEmpty(generalFactorsList)) {
                        continue;
                    }
                    //2
                    for (DataReportGeneralFactors reportGeneralFactors : generalFactorsList) {
                        String title = String.valueOf(reportGeneralFactors.getSorting() == null ? 0 : reportGeneralFactors.getSorting());
                        stringBuilder.append(AsposeUtils.getWarpCssHtml(getFactorsHandleText(reportGeneralFactors, title), keyValueDtoList));
                        //插入换行符
                        stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, repeat));

                        List<DataReportGeneralFactors> factorsList = getDataReportGeneralFactorsListByPid(reportGeneralFactors.getId());
                        if (CollectionUtils.isEmpty(factorsList)) {
                            continue;
                        }
                        //3
                        for (DataReportGeneralFactors dataReportGeneralFactors : factorsList) {
                            String titleA = String.valueOf(dataReportGeneralFactors.getSorting() == null ? 0 : dataReportGeneralFactors.getSorting());
                            titleA = String.format("(%s)", titleA);
                            stringBuilder.append(AsposeUtils.getWarpCssHtml(getFactorsHandleText(dataReportGeneralFactors, titleA), keyValueDtoList));
                            //插入换行符
                            stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, repeat));
                            List<DataReportGeneralFactors> list = getDataReportGeneralFactorsListByPid(dataReportGeneralFactors.getId());
                            if (CollectionUtils.isEmpty(list)) {
                                continue;
                            }
                            //4
                            for (DataReportGeneralFactors obj : list) {
                                Integer index = obj.getSorting() == null ? 0 : obj.getSorting();
                                stringBuilder.append(AsposeUtils.getWarpCssHtml(getFactorsHandleText(obj, GenerateCommonMethod.parseToCircleNumber(index)), keyValueDtoList));
                                //插入换行符
                                stringBuilder.append(StringUtils.repeat(ControlChar.LINE_BREAK, repeat));
                            }
                        }
                    }
                }
                map.put(factors.getName(), stringBuilder.toString());
                stringBuilder.delete(0, stringBuilder.toString().length());
            }
        }
        return map;
    }

    private String getFactorsHandleText(DataReportGeneralFactors generalFactors, String title) {
        String value = null;
        if (StringUtils.isBlank(generalFactors.getTemplate())) {
            value = String.format("%s %s ", title, generalFactors.getName());
        } else {
            value = String.format("%s %s : %s", title, generalFactors.getName(), StreamUtils.StripHTML(generalFactors.getTemplate()));
        }
        return value;
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
