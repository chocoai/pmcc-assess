package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataSetUseFieldDao;
import com.copower.pmcc.assess.dal.basis.entity.DataSetUseField;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.output.TreeViewVo;
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
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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

    //region 获取数据字典列表

    /**
     * 获取数据字典列表
     *
     * @return
     */
    public BootstrapTableVo getSetUseFieldList(String fieldName, String name) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataSetUseField> list = dataSetUseFieldDao.getListObject(fieldName, name,null);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<DataSetUseField>() : list);
        return bootstrapTableVo;
    }

    /**
     * 获取数据字典列表
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

    //region 保存数据字典中的数据

    /**
     * 保存数据字典中的数据
     *
     * @param sysSetUseField
     */
    public void saveSetUseField(DataSetUseField sysSetUseField) throws BusinessException {
        DataSetUseField sysSetUseFieldTemp = null;

        if (dataSetUseFieldDao.isExist(sysSetUseField.getFieldName(), sysSetUseField.getId()))
            throw new BusinessException(HttpReturnEnum.EXIST.getName());
        if (sysSetUseField.getId() != null && sysSetUseField.getId() > 0) {
            sysSetUseFieldTemp = dataSetUseFieldDao.getSingleObject(sysSetUseField.getId());
            if (sysSetUseFieldTemp != null)//如果没有找到相应信息，则表示没有相应的数据，不进行更新处
            {
                BeanUtils.copyProperties(sysSetUseField, sysSetUseFieldTemp);
                if (!dataSetUseFieldDao.updateObject(sysSetUseFieldTemp)) {
                    throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
                }
            } else {
                throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
            }

        } else {
            sysSetUseFieldTemp = new DataSetUseField();
            BeanUtils.copyProperties(sysSetUseField, sysSetUseFieldTemp);
            sysSetUseFieldTemp.setBisDelete(false);
            sysSetUseFieldTemp.setCreator(processControllerComponent.getThisUser());
            if (!dataSetUseFieldDao.addObject(sysSetUseFieldTemp)) {
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

    //region 获取缓存中的数据字典数据

    /**
     * 获取缓存中的数据字典数据
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

    //region 获取缓存中的数据字典数据

    /**
     * 获取缓存中的数据字典数据
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
     * 获取缓存中的数据字典数据
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

    public TreeViewVo getSetUseFieldTree(String fieldName) {

        DataSetUseField hrDataSetUseField = getCacheSetUseFieldByFieldName(fieldName);
        TreeViewVo treeViewVo = new TreeViewVo();
        if (hrDataSetUseField != null) {

            treeViewVo.setId(hrDataSetUseField.getId());
            treeViewVo.setText(hrDataSetUseField.getName());
            treeViewVo.setpId(0);
            treeViewVo.setpName("");
            treeViewVo.setNodes(getTreeView(hrDataSetUseField.getId()));
        }
        return treeViewVo;
    }

    private List<TreeViewVo> getTreeView(Integer pid) {
        TreeViewVo treeViewVo;
        List<DataSetUseField> hrDataSetUseFields = getCacheSetUseFieldListByPid(pid);
        List<TreeViewVo> treeViewVos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(hrDataSetUseFields)) {

            for (DataSetUseField item : hrDataSetUseFields) {
                treeViewVo = new TreeViewVo();
                treeViewVo.setId(item.getId());
                treeViewVo.setText(item.getName());
                treeViewVo.setpId(item.getPid());
                List<TreeViewVo> treeView = getTreeView(item.getId());
                if (treeView.size() > 0) {
                    treeViewVo.setNodes(treeView);
                }
                treeViewVos.add(treeViewVo);
            }
        }
        return treeViewVos;
    }


    /**
     * 获取数据信息
     *
     * @param pid
     * @return
     */
    public List<ZtreeDto> getBaseDicTree(Integer pid) {
        if (pid == null)
            return Lists.newArrayList();
        List<DataSetUseField> baseSetUseFieldList = getCacheSetUseFieldListByPid(pid);
        if (CollectionUtils.isEmpty(baseSetUseFieldList))
            return Lists.newArrayList();
        return LangUtils.transform(baseSetUseFieldList, p -> {
            return getZtreeDto(p);
        });
    }

    /**
     * 查询数据信息
     *
     * @param name
     * @return
     */
    public List<ZtreeDto> queryBaseDicTree(String name) {
        if (StringUtils.isBlank(name))
            return Lists.newArrayList();
        List<DataSetUseField> dataDicList = dataSetUseFieldDao.getListObject(null, name,null);
        if (CollectionUtils.isEmpty(dataDicList))
            return Lists.newArrayList();
        return LangUtils.transform(dataDicList, p -> {
            return getZtreeDto(p);
        });
    }

    public List<ZtreeDto> getBaseDicByKey(String key) {
        DataSetUseField baseSetUseField = getCacheSetUseFieldByFieldName(key);
        if (baseSetUseField == null) return null;
        return Lists.newArrayList(getZtreeDto(baseSetUseField));
    }

    private ZtreeDto getZtreeDto(DataSetUseField baseSetUseField) {
        ZtreeDto ztreeDto = new ZtreeDto();
        ztreeDto.setId(baseSetUseField.getId());
        ztreeDto.setPid(baseSetUseField.getPid());
        ztreeDto.setName(baseSetUseField.getName());
        List<DataSetUseField> dataDics = getCacheSetUseFieldListByPid(baseSetUseField.getId());
        ztreeDto.setIsParent(CollectionUtils.isNotEmpty(dataDics));
        return ztreeDto;
    }

    /**
     * 根据id获取显示的名称
     * @param id
     * @return
     */
    public String getNameById(Integer id){
        DataSetUseField baseSetUseField = dataSetUseFieldDao.getSingleObject(id);
        if(baseSetUseField==null) return "";
        return baseSetUseField.getName();
    }

    /**
     * 从现有集合中根据名称找出对应数据
     * @param list
     * @param name
     * @return
     */
    public DataSetUseField getSetUseFieldByName(List<DataSetUseField> list,String name){
        if(CollectionUtils.isEmpty(list)) return null;
        if(StringUtils.isBlank(name)) return null;
        for (DataSetUseField baseSetUseField : list) {
            if(StringUtils.equals(baseSetUseField.getName().trim(),name.trim()))
                return baseSetUseField;
        }
        return null;
    }

}
