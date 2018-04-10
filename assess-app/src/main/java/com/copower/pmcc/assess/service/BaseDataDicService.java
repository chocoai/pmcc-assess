package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.dao.BaseDataDicDao;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.copower.pmcc.assess.constant.CrmCacheConstant;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpc on 2017/8/16.
 */
@Service
public class BaseDataDicService {
    @Autowired
    private BaseDataDicDao cmsBaseDataDicDao;
    @Autowired
    private ServiceComponent serviceComponent;

    //region 获取数据字典列表

    /**
     * 获取数据字典列表
     *
     * @return
     */
    public BootstrapTableVo getDataDicList(String fieldName, String name) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseDataDic> list = cmsBaseDataDicDao.getListObject(fieldName, name);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<BaseDataDic>() : list);
        return bootstrapTableVo;
    }

    /**
     * 获取数据字典列表
     *
     * @return
     */
    public BootstrapTableVo getDataDicListByPid(Integer pid) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseDataDic> list = cmsBaseDataDicDao.getListByPid(pid, requestBaseParam.getSearch());
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<BaseDataDic>() : list);
        return bootstrapTableVo;
    }
    //endregion

    //region 保存数据字典中的数据

    /**
     * 保存数据字典中的数据
     *
     * @param sysDataDic
     */
    public void saveDataDic(BaseDataDic sysDataDic) throws BusinessException {
        BaseDataDic sysDataDicTemp = null;

        if (cmsBaseDataDicDao.isExist(sysDataDic.getFieldName(), sysDataDic.getId()))
            throw new BusinessException(HttpReturnEnum.EXIST.getName());
        if (sysDataDic.getId() != null && sysDataDic.getId() > 0) {
            sysDataDicTemp = cmsBaseDataDicDao.getSingleObject(sysDataDic.getId());
            if (sysDataDicTemp != null)//如果没有找到相应信息，则表示没有相应的数据，不进行更新处
            {
                sysDataDicTemp.setName(sysDataDic.getName());
                sysDataDicTemp.setFieldName(sysDataDic.getFieldName());
                sysDataDicTemp.setGroupKey(sysDataDic.getGroupKey());
                sysDataDicTemp.setBisEnable(sysDataDic.getBisEnable() == null ? false : sysDataDic.getBisEnable());
                sysDataDicTemp.setSorting(sysDataDic.getSorting());
                sysDataDicTemp.setRemark(sysDataDic.getRemark());
                if (!cmsBaseDataDicDao.updateObject(sysDataDicTemp)) {
                    throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
                }
            } else {
                throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
            }

        } else {
            sysDataDicTemp = new BaseDataDic();
            sysDataDicTemp.setName(sysDataDic.getName());
            sysDataDicTemp.setFieldName(sysDataDic.getFieldName());
            sysDataDicTemp.setGroupKey(sysDataDic.getGroupKey());
            sysDataDicTemp.setSorting(sysDataDic.getSorting());
            sysDataDicTemp.setRemark(sysDataDic.getRemark());
            sysDataDicTemp.setBisEnable(sysDataDic.getBisEnable() == null ? false : sysDataDic.getBisEnable());
            sysDataDicTemp.setBisDelete(false);
            if (sysDataDic.getPid() != null) {
                sysDataDicTemp.setPid(sysDataDic.getPid());

                BaseDataDic cacheDataDicById = getCacheDataDicById(sysDataDic.getPid());
                sysDataDicTemp.setGroupKey(cacheDataDicById.getGroupKey());
            }
            sysDataDicTemp.setCreator(serviceComponent.getThisUser());
            if (!cmsBaseDataDicDao.addObject(sysDataDicTemp)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        }
        serviceComponent.RemoveRedisKeyValues(CrmCacheConstant.PMCC_CHKS_DATA_DIC, "");
    }
    //endregion

    //region 删除数据

    /**
     * 删除数据
     *
     * @param id
     */
    public void delDataDic(Integer id) throws BusinessException {
        BaseDataDic sysDataDic = cmsBaseDataDicDao.getSingleObject(id);
        if (sysDataDic != null) {
            sysDataDic.setBisDelete(true);
            if (!cmsBaseDataDicDao.updateObject(sysDataDic))
                throw new BusinessException(HttpReturnEnum.DELETEFAIL.getName());
            serviceComponent.RemoveRedisKeyValues(CrmCacheConstant.PMCC_CHKS_DATA_DIC, "");
        }
    }
    //endregion

    //region 获取缓存中的数据字典数据

    /**
     * 获取缓存中的数据字典数据
     *
     * @return
     */
    public List<BaseDataDic> getCacheDataDicList(String fieldName) {

        try {

            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(CrmCacheConstant.PMCC_CHKS_DATA_DIC_FIELD, fieldName);
            List<BaseDataDic> dataDics = LangUtils.listCache(costsKeyPrefix, fieldName, BaseDataDic.class, input -> cmsBaseDataDicDao.getEnableList(input));
            return dataDics;
        } catch (Exception e) {
            return cmsBaseDataDicDao.getEnableList(fieldName);
        }

    }

    public BaseDataDic getCacheDataDicByFieldName(String fieldName) {
        String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(CrmCacheConstant.PMCC_CHKS_DATA_DIC_FIELD_ITEM, fieldName);

        try {
            BaseDataDic sysDataDic = LangUtils.singleCache(costsKeyPrefix, fieldName, BaseDataDic.class, o -> cmsBaseDataDicDao.getSingleObject(o));
            return sysDataDic;
        } catch (Exception e) {
            return cmsBaseDataDicDao.getSingleObject(fieldName);
        }

    }

    //region 获取缓存中的数据字典数据

    /**
     * 获取缓存中的数据字典数据
     *
     * @return
     */
    public List<BaseDataDic> getCacheDataDicListByPid(Integer pid) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(CrmCacheConstant.PMCC_CHKS_DATA_DIC_PID, String.valueOf(pid));

        try {
            List<BaseDataDic> sysDataDics = LangUtils.listCache(rdsKey, pid, BaseDataDic.class, input -> cmsBaseDataDicDao.getEnableListByPid(input));
            return sysDataDics;
        } catch (Exception e) {
            return cmsBaseDataDicDao.getEnableListByPid(pid);
        }
    }

    /**
     * 获取缓存中的数据字典数据
     *
     * @return
     */
    public BaseDataDic getCacheDataDicById(Integer id) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(CrmCacheConstant.PMCC_CHKS_DATA_DIC_ID, String.valueOf(id));

        try {
            BaseDataDic sysDataDic = LangUtils.singleCache(rdsKey, id, BaseDataDic.class, o -> cmsBaseDataDicDao.getSingleObject(o));
            return sysDataDic;
        } catch (Exception e) {
            return cmsBaseDataDicDao.getSingleObject(id);
        }

    }

    /**
     * 获取字典的数据层次
     *
     * @param id
     * @return
     */
    public KeyValueDto getDataDicLevel(Integer id) {
        KeyValueDto keyValueDto = new KeyValueDto();
        BaseDataDic sysDataDic = getCacheDataDicById(id);
        BaseDataDic subBaseDataDic = getCacheDataDicById(sysDataDic.getPid());
        if (subBaseDataDic != null && subBaseDataDic.getId() != null) {
            getDataDicLevelRecursion(keyValueDto, subBaseDataDic.getId());
        }
        keyValueDto.setKey(String.valueOf(sysDataDic.getId()));
        keyValueDto.setValue(sysDataDic.getName());
        return keyValueDto;
    }

    private void getDataDicLevelRecursion(KeyValueDto keyValueDto, Integer id) {
        BaseDataDic sysDataDic = getCacheDataDicById(id);
        if (sysDataDic != null && sysDataDic.getId() != null) {
            KeyValueDto kv = new KeyValueDto();
            BaseDataDic subBaseDataDic = getCacheDataDicById(sysDataDic.getPid());
            if (subBaseDataDic != null && subBaseDataDic.getId() != null) {
                getDataDicLevelRecursion(kv, subBaseDataDic.getId());
            }
            kv.setKey(String.valueOf(sysDataDic.getId()));
            kv.setValue(sysDataDic.getName());
            keyValueDto.setKeyValueDto(kv);

        }
    }

}
