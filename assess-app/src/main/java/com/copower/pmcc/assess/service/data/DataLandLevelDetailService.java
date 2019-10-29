package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.RomanNumeral;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelDetailVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: zch
 * @Date: 2018/9/4 18:24
 * @Description:土地级别维护
 */
@Service
public class DataLandLevelDetailService {
    @Autowired
    private DataLandLevelDetailDao dataLandLevelDetailDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataLandLevelDao dataLandLevelDao;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public void saveAndUpdateDataLandLevelDetail(DataLandLevelDetail dataLandLevelDetail) {
        if (dataLandLevelDetail.getId() == null || dataLandLevelDetail.getId() == 0) {
            dataLandLevelDetail.setCreator(commonService.thisUserAccount());
            dataLandLevelDetailDao.addDataLandLevelDetail(dataLandLevelDetail);
        } else {
            dataLandLevelDetailDao.updateDataLandLevelDetail(dataLandLevelDetail);
        }
    }

    public DataLandLevelDetail getDataLandLevelDetailById(Integer id) {
        return dataLandLevelDetailDao.getDataLandLevelDetailById(id);
    }

    /**
     * 获取列表数据
     *
     * @param landLevelId
     * @return
     */
    public BootstrapTableVo getDataLandLevelDetailList(Integer landLevelId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        DataLandLevelDetail oo = new DataLandLevelDetail();
        oo.setLandLevelId(landLevelId);
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLandLevelDetail> dataLandLevelDetailList = dataLandLevelDetailSorted(getDataLandLevelDetailList(oo));
        List<DataLandLevelDetailVo> vos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(dataLandLevelDetailList)) {
            dataLandLevelDetailList.forEach(obj -> vos.add(getDataLandLevelDetailVo(obj)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    /**
     * 根据区域获取土地级别
     *
     * @param province
     * @param city
     * @param district
     * @return
     */
    public BootstrapTableVo getDataLandLevelDetailListByArea(String province, String city, String district) {
        BootstrapTableVo vo = new BootstrapTableVo();
        DataLandLevel dataLandLevelWhere = new DataLandLevel();
        if (StringUtils.isNotEmpty(province)) {
            dataLandLevelWhere.setProvince(province);
        }
        if (StringUtils.isNotEmpty(city)) {
            dataLandLevelWhere.setCity(city);
        }
        if (StringUtils.isNotEmpty(district)) {
            dataLandLevelWhere.setDistrict(district);
        }
        List<DataLandLevel> levelList = dataLandLevelDao.getDataLandLevelList(dataLandLevelWhere);
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLandLevelDetail> dataLandLevelDetailList = Lists.newArrayList();
        List<DataLandLevelDetailVo> vos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(levelList)) {
            dataLandLevelDetailList = getByMasterIdInfo(levelList.stream().map(oo -> oo.getId()).collect(Collectors.toList())) ;
        }
        if (CollectionUtils.isNotEmpty(dataLandLevelDetailList)) {
            dataLandLevelDetailList = dataLandLevelDetailSorted(dataLandLevelDetailList);
            dataLandLevelDetailList.forEach(obj -> vos.add(getDataLandLevelDetailVo(obj)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    /**
     * 注意这是pid 列表
     * @param integerList
     * @return
     */
    public List<DataLandLevelDetail> getByMasterIdInfo(List<Integer> integerList){
        return dataLandLevelDetailDao.getByMasterIdInfo(integerList) ;
    }

    public List<DataLandLevelDetail> getDataLandLevelDetailList(DataLandLevelDetail oo) {
        return dataLandLevelDetailSorted(dataLandLevelDetailDao.getDataLandLevelDetailList(oo));
    }

    /**
     * 分类并且排序
     *
     * @param dataLandLevelDetails
     * @return
     */
    public List<DataLandLevelDetail> dataLandLevelDetailSorted(List<DataLandLevelDetail> dataLandLevelDetails) {
        LinkedHashMap<String, List<DataLandLevelDetail>> listLinkedHashMap = Maps.newLinkedHashMap();
        if (CollectionUtils.isNotEmpty(dataLandLevelDetails)) {
            dataLandLevelDetails.forEach(oo -> {
                List<DataLandLevelDetail> details = listLinkedHashMap.get(oo.getClassify());
                if (CollectionUtils.isEmpty(details)) {
                    details = Lists.newArrayList();
                }
                details.add(oo);
                listLinkedHashMap.put(oo.getClassify(), details);
            });
        }
        List<DataLandLevelDetail> dataLandLevelDetailList = Lists.newArrayList();
        if (!listLinkedHashMap.isEmpty()) {
            listLinkedHashMap.entrySet().stream().forEachOrdered(stringListEntry -> {
                if (CollectionUtils.isNotEmpty(stringListEntry.getValue())) {
                    List<DataLandLevelDetail> landLevelDetailList = stringListEntry.getValue().stream().sorted((o1, o2) -> {
                        int i = 0;
                        final String remove = "级";
                        if (StringUtils.isNotEmpty(o1.getType())) {
                            i++;
                        }
                        if (StringUtils.isNotEmpty(o2.getType())) {
                            i++;
                        }
                        if (i == 2) {
                            String value1 = StringUtils.trim(StringUtils.remove(o1.getType(), remove));
                            String value2 = StringUtils.trim(StringUtils.remove(o2.getType(), remove));
                            int a = RomanNumeral.intValue(value1);
                            int b = RomanNumeral.intValue(value2);
                            return Integer.compare(a, b);
                        }
                        return 0;
                    }).collect(Collectors.toList());
                    dataLandLevelDetailList.addAll(landLevelDetailList);
                }
            });
        }
        return dataLandLevelDetailList;
    }

    /**
     * 根据id获取显示的名称
     *
     * @param id
     * @return
     */
    public String getCacheNameById(Integer id) {
        DataLandLevelDetail dataLandLevelDetail = null;
        try {
            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_LAND_LEVEL_ID, String.valueOf(id));
            dataLandLevelDetail = LangUtils.singleCache(costsKeyPrefix, id, DataLandLevelDetail.class, (o) -> {
                return dataLandLevelDetailDao.getDataLandLevelDetailById(o);
            });
        } catch (Exception e) {
            dataLandLevelDetail = dataLandLevelDetailDao.getDataLandLevelDetailById(id);
        }
        if (dataLandLevelDetail == null) return null;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(dataLandLevelDetail.getClassify());
        if (StringUtils.isNotBlank(dataLandLevelDetail.getType()))
            stringBuffer.append("/").append(dataLandLevelDetail.getType());
        if (StringUtils.isNotBlank(dataLandLevelDetail.getCategory()))
            stringBuffer.append("/").append(dataLandLevelDetail.getCategory());
        return stringBuffer.toString();
    }

    /**
     * 刪除土地級別
     *
     * @param id
     */
    public void removeDataLandLevelDetail(Integer id) {
        DataLandLevelDetail levelDetail = dataLandLevelDetailDao.getDataLandLevelDetailById(id);
        if (levelDetail != null) {
            levelDetail.setBisDelete(true);
            dataLandLevelDetailDao.updateDataLandLevelDetail(levelDetail);
        }
    }

    /**
     * @param landLevelId
     * @return
     */
    public int getCountByLandLevelId(Integer landLevelId) {
        return dataLandLevelDetailDao.getCountByLandLevelId(landLevelId);
    }

    public List<DataLandLevelDetail> getDataLandLevelDetailListByPid(Integer landLevelId) {
        DataLandLevelDetail oo = new DataLandLevelDetail();
        oo.setLandLevelId(landLevelId);
        return dataLandLevelDetailSorted(getDataLandLevelDetailList(oo));
    }

    //根据大类和级别获取数据
    public DataLandLevelDetail getDataByClassifyAndType(String classify, String type, Integer landLevelId) {
        List<DataLandLevelDetail> dataList = dataLandLevelDetailDao.getDataByClassifyAndType(classify, type, landLevelId);
        if (CollectionUtils.isNotEmpty(dataList)) {
            return dataList.get(0);
        }
        return null;
    }

    public DataLandLevelDetailVo getDataLandLevelDetailVo(DataLandLevelDetail oo) {
        DataLandLevelDetailVo vo = new DataLandLevelDetailVo();
        if (oo == null) {
            return vo;
        }
        BeanUtils.copyProperties(oo, vo);
        if (NumberUtils.isNumber(oo.getClassify())) {
            vo.setClassifyName(baseDataDicService.getNameById(oo.getClassify()));
        } else {
            vo.setClassifyName(oo.getClassify());
        }
        if (NumberUtils.isNumber(oo.getType())) {
            vo.setTypeName(baseDataDicService.getNameById(oo.getType()));
        } else {
            vo.setTypeName(oo.getType());
        }
        return vo;
    }
}
