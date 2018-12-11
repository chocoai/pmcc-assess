package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private ErpAreaService erpAreaService;
    @Autowired
    private DataLandLevelDao dataLandLevelDao;

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
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLandLevelDetail> dataLandLevelDetails = dataLandLevelDetailDao.getDataLandLevelDetailList(landLevelId);
        vo.setTotal(page.getTotal());
        vo.setRows(dataLandLevelDetails);
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
        DataLandLevel dataLandLevelWhere = new DataLandLevel();
        dataLandLevelWhere.setProvince(province);
        dataLandLevelWhere.setCity(city);
        dataLandLevelWhere.setDistrict(district);
        List<DataLandLevel> levelList = dataLandLevelDao.getDataLandLevelList(dataLandLevelWhere);
        DataLandLevel dataLandLevel = null;
        if (CollectionUtils.isEmpty(levelList) && StringUtils.isBlank(district)) return null;
        if (CollectionUtils.isNotEmpty(levelList) && StringUtils.isBlank(district)) {
            for (DataLandLevel landLevel : levelList) {
                if (StringUtils.isBlank(landLevel.getDistrict()))
                    dataLandLevel = landLevel;
            }
        }
        if (CollectionUtils.isEmpty(levelList) && StringUtils.isNotBlank(district)) {
            dataLandLevelWhere.setDistrict(null);
            levelList = dataLandLevelDao.getDataLandLevelList(dataLandLevelWhere);
            if (CollectionUtils.isEmpty(levelList)) {
                return null;
            }
            dataLandLevel = levelList.get(0);
        }
        if (dataLandLevel == null) return null;

        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLandLevelDetail> dataLandLevelDetails = dataLandLevelDetailDao.getDataLandLevelDetailList(dataLandLevel.getId());
        vo.setTotal(page.getTotal());
        vo.setRows(dataLandLevelDetails);
        return vo;
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
     *
     * @param landLevelId
     * @return
     */
    public int getCountByLandLevelId(Integer landLevelId){
        return dataLandLevelDetailDao.getCountByLandLevelId(landLevelId);
    }
}
