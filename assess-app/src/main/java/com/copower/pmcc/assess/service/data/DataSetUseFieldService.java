package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataSetUseFieldDao;
import com.copower.pmcc.assess.dal.basis.entity.DataSetUseField;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
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
public class DataSetUseFieldService {
    @Autowired
    private DataSetUseFieldDao dataSetUseFieldDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    //region 获取设定用途字段列表

    /**
     * 获取设定用途字段列表
     *
     * @return
     */
    public BootstrapTableVo getSetUseFieldList(String name) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataSetUseField> list = dataSetUseFieldDao.getListObject(name, 0);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<DataSetUseField>() : list);
        return bootstrapTableVo;
    }

    /**
     * 获取设定用途字段列表
     *
     * @return
     */
    public BootstrapTableVo getSetUseFieldListByPid(Integer pid) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataSetUseField> list = dataSetUseFieldDao.getListByPid(pid, requestBaseParam.getSearch());
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<DataSetUseField>() : list);
        return bootstrapTableVo;
    }
    //endregion

    //region 保存设定用途字段中的数据

    /**
     * 保存设定用途字段中的数据
     *
     * @param sysSetUseField
     */
    public void saveSetUseField(DataSetUseField sysSetUseField) throws BusinessException {
        if (dataSetUseFieldDao.isExist(sysSetUseField.getFieldName(), sysSetUseField.getId()))
            throw new BusinessException(HttpReturnEnum.EXIST.getName());
        if (sysSetUseField.getId() != null && sysSetUseField.getId() > 0) {
            sysSetUseField.setLevel(getLevel(sysSetUseField.getPid()));
            if (!dataSetUseFieldDao.updateObject(sysSetUseField)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }

        } else {
            sysSetUseField.setBisDelete(false);
            sysSetUseField.setCreator(processControllerComponent.getThisUser());
            sysSetUseField.setLevel(getLevel(sysSetUseField.getPid()));
            if (!dataSetUseFieldDao.addObject(sysSetUseField)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        }
        processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_SET_USE, "");
    }
    //endregion

    //region 删除数据

    /**
     * 删除数据
     *
     * @param id
     */
    public void delSetUseField(Integer id) throws BusinessException {
        DataSetUseField sysSetUseField = dataSetUseFieldDao.getSingleObject(id);
        if (sysSetUseField != null) {
            sysSetUseField.setBisDelete(true);
            if (!dataSetUseFieldDao.updateObject(sysSetUseField))
                throw new BusinessException(HttpReturnEnum.DELETEFAIL.getName());
            processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_SET_USE, "");
        }
    }
    //endregion

    //region 获取缓存中的设定用途字段数据

    /**
     * 获取缓存中的设定用途字段数据
     *
     * @return
     */
    public List<DataSetUseField> getCacheSetUseFieldList(String fieldName) {

        try {

            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_SET_USE_FIELD, fieldName);
            List<DataSetUseField> dataDics = LangUtils.listCache(costsKeyPrefix, fieldName, DataSetUseField.class, input -> dataSetUseFieldDao.getEnableList(input));
            return dataDics;
        } catch (Exception e) {
            return dataSetUseFieldDao.getEnableList(fieldName);
        }

    }

    public DataSetUseField getCacheSetUseFieldByFieldName(String fieldName) {
        String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_SET_USE_FIELD_ITEM, fieldName);

        try {
            DataSetUseField sysSetUseField = LangUtils.singleCache(costsKeyPrefix, fieldName, DataSetUseField.class, o -> dataSetUseFieldDao.getSingleObject(o));
            return sysSetUseField;
        } catch (Exception e) {
            return dataSetUseFieldDao.getSingleObject(fieldName);
        }

    }

    //region 获取缓存中的设定用途字段数据

    /**
     * 获取缓存中的设定用途字段数据
     *
     * @return
     */
    public List<DataSetUseField> getCacheSetUseFieldListByPid(Integer pid) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_SET_USE_PID, String.valueOf(pid));

        try {
            List<DataSetUseField> sysSetUseFields = LangUtils.listCache(rdsKey, pid, DataSetUseField.class, input -> dataSetUseFieldDao.getEnableListByPid(input));
            return sysSetUseFields;
        } catch (Exception e) {
            return dataSetUseFieldDao.getEnableListByPid(pid);
        }
    }

    /**
     * 获取设定用途字段数据
     *
     * @return
     */
    public DataSetUseField getSetUseFieldByType(Integer type) {
        return dataSetUseFieldDao.getSetUseFieldByType(type);
    }

    /**
     * 获取缓存中的设定用途字段数据
     *
     * @return
     */
    public DataSetUseField getCacheSetUseFieldById(Integer id) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_SET_USE_ID, String.valueOf(id));

        try {
            DataSetUseField sysSetUseField = LangUtils.singleCache(rdsKey, id, DataSetUseField.class, o -> dataSetUseFieldDao.getSingleObject(o));
            return sysSetUseField;
        } catch (Exception e) {
            return dataSetUseFieldDao.getSingleObject(id);
        }

    }

    public DataSetUseField getSetUseFieldById(Integer id) {
        return dataSetUseFieldDao.getSingleObject(id);
    }

    /**
     * 获取层次
     *
     * @param pid
     */
    public String getLevel(Integer pid) {
        if (pid == null) return "";
        DataSetUseField useField = getCacheSetUseFieldById(pid);
        if (useField == null) return "";
        return String.format("%s-%s", getLevel(useField.getPid()), useField.getId()).replaceAll("^-|-$","");
    }

    /**
     * 获取字典的数据层次
     *
     * @param id
     * @return
     */
    public KeyValueDto getSetUseFieldLevel(Integer id) {
        KeyValueDto keyValueDto = new KeyValueDto();
        DataSetUseField sysSetUseField = getCacheSetUseFieldById(id);
        DataSetUseField subDataSetUseField = getCacheSetUseFieldById(sysSetUseField.getPid());
        if (subDataSetUseField != null && subDataSetUseField.getId() != null) {
            getSetUseFieldLevelRecursion(keyValueDto, subDataSetUseField.getId());
        }
        keyValueDto.setKey(String.valueOf(sysSetUseField.getId()));
        keyValueDto.setValue(sysSetUseField.getName());
        return keyValueDto;
    }

    private void getSetUseFieldLevelRecursion(KeyValueDto keyValueDto, Integer id) {
        DataSetUseField sysSetUseField = getCacheSetUseFieldById(id);
        if (sysSetUseField != null && sysSetUseField.getId() != null) {
            KeyValueDto kv = new KeyValueDto();
            DataSetUseField subDataSetUseField = getCacheSetUseFieldById(sysSetUseField.getPid());
            if (subDataSetUseField != null && subDataSetUseField.getId() != null) {
                getSetUseFieldLevelRecursion(kv, subDataSetUseField.getId());
            }
            kv.setKey(String.valueOf(sysSetUseField.getId()));
            kv.setValue(sysSetUseField.getName());
            keyValueDto.setKeyValueDto(kv);

        }
    }

}
