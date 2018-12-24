package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataDamagedDegreeDao;
import com.copower.pmcc.assess.dal.basis.entity.DataDamagedDegree;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.output.TreeViewVo;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpc on 2017/8/16.
 */
@Service
public class DataDamagedDegreeService {
    @Autowired
    private DataDamagedDegreeDao cmsDataDamagedDegreeDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    //region 获取数据字典列表

    /**
     * 获取数据字典列表
     *
     * @return
     */
    public BootstrapTableVo getDamagedDegreeList(String fieldName, String name) {
        RequestBaseParam requestDataParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestDataParam.getOffset(), requestDataParam.getLimit());
        List<DataDamagedDegree> list = cmsDataDamagedDegreeDao.getListObject(fieldName, name);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<DataDamagedDegree>() : list);
        return bootstrapTableVo;
    }

    /**
     * 获取数据字典列表
     *
     * @return
     */
    public BootstrapTableVo getDamagedDegreeListByPid(Integer pid) {
        RequestBaseParam requestDataParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestDataParam.getOffset(), requestDataParam.getLimit());
        List<DataDamagedDegree> list = cmsDataDamagedDegreeDao.getListByPid(pid, requestDataParam.getSearch());
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<DataDamagedDegree>() : list);
        return bootstrapTableVo;
    }
    //endregion

    //region 保存数据字典中的数据

    /**
     * 保存数据字典中的数据
     *
     * @param sysDamagedDegree
     */
    public void saveDamagedDegree(DataDamagedDegree sysDamagedDegree) throws BusinessException {
        if (cmsDataDamagedDegreeDao.isExist(sysDamagedDegree.getFieldName(), sysDamagedDegree.getId()))
            throw new BusinessException(HttpReturnEnum.EXIST.getName());
        if (sysDamagedDegree.getId() != null && sysDamagedDegree.getId() > 0) {
            if (!cmsDataDamagedDegreeDao.updateObject(sysDamagedDegree)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        } else {
            sysDamagedDegree.setBisDelete(false);
            sysDamagedDegree.setCreator(commonService.thisUserAccount());
            if (!cmsDataDamagedDegreeDao.addObject(sysDamagedDegree)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        }
        processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_DAMAGED_DEGREE, "");
    }
    //endregion

    //region 删除数据

    /**
     * 删除数据
     *
     * @param id
     */
    public void delDamagedDegree(Integer id) throws BusinessException {
        DataDamagedDegree sysDamagedDegree = cmsDataDamagedDegreeDao.getSingleObject(id);
        if (sysDamagedDegree != null) {
            sysDamagedDegree.setBisDelete(true);
            if (!cmsDataDamagedDegreeDao.updateObject(sysDamagedDegree))
                throw new BusinessException(HttpReturnEnum.DELETEFAIL.getName());
            processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_DAMAGED_DEGREE, "");
        }
    }
    //endregion

    //region 获取缓存中的数据字典数据

    /**
     * 获取缓存中的数据字典数据
     *
     * @return
     */
    public List<DataDamagedDegree> getCacheDamagedDegreeList(String fieldName) {

        try {

            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_DAMAGED_DEGREE_FIELD, fieldName);
            List<DataDamagedDegree> DamagedDegrees = LangUtils.listCache(costsKeyPrefix, fieldName, DataDamagedDegree.class, input -> cmsDataDamagedDegreeDao.getEnableList(input));
            return DamagedDegrees;
        } catch (Exception e) {
            return cmsDataDamagedDegreeDao.getEnableList(fieldName);
        }

    }


    public DataDamagedDegree getCacheDamagedDegreeByFieldName(String fieldName) {
        String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_DAMAGED_DEGREE_FIELD_ITEM, fieldName);

        try {
            DataDamagedDegree sysDamagedDegree = LangUtils.singleCache(costsKeyPrefix, fieldName, DataDamagedDegree.class, o -> cmsDataDamagedDegreeDao.getSingleObject(o));
            return sysDamagedDegree;
        } catch (Exception e) {
            return cmsDataDamagedDegreeDao.getSingleObject(fieldName);
        }

    }

    //region 获取缓存中的数据字典数据

    /**
     * 获取缓存中的数据字典数据
     *
     * @return
     */
    public List<DataDamagedDegree> getCacheDamagedDegreeListByPid(Integer pid) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_DAMAGED_DEGREE_PID, String.valueOf(pid));

        try {
            List<DataDamagedDegree> sysDamagedDegrees = LangUtils.listCache(rdsKey, pid, DataDamagedDegree.class, input -> cmsDataDamagedDegreeDao.getEnableListByPid(input));
            return sysDamagedDegrees;
        } catch (Exception e) {
            return cmsDataDamagedDegreeDao.getEnableListByPid(pid);
        }
    }

    /**
     * 获取缓存中的数据字典数据
     *
     * @return
     */
    public DataDamagedDegree getCacheDamagedDegreeById(Integer id) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_DAMAGED_DEGREE_ID, String.valueOf(id));

        try {
            DataDamagedDegree sysDamagedDegree = LangUtils.singleCache(rdsKey, id, DataDamagedDegree.class, o -> cmsDataDamagedDegreeDao.getSingleObject(o));
            return sysDamagedDegree;
        } catch (Exception e) {
            return cmsDataDamagedDegreeDao.getSingleObject(id);
        }

    }

    public DataDamagedDegree getDamagedDegreeById(Integer id) {
        return cmsDataDamagedDegreeDao.getSingleObject(id);
    }

    /**
     * 获取字典的数据层次
     *
     * @param id
     * @return
     */
    public KeyValueDto getDamagedDegreeLevel(Integer id) {
        KeyValueDto keyValueDto = new KeyValueDto();
        DataDamagedDegree sysDamagedDegree = getCacheDamagedDegreeById(id);
        DataDamagedDegree subDataDamagedDegree = getCacheDamagedDegreeById(sysDamagedDegree.getPid());
        if (subDataDamagedDegree != null && subDataDamagedDegree.getId() != null) {
            getDamagedDegreeLevelRecursion(keyValueDto, subDataDamagedDegree.getId());
        }
        keyValueDto.setKey(String.valueOf(sysDamagedDegree.getId()));
        keyValueDto.setValue(sysDamagedDegree.getName());
        return keyValueDto;
    }

    private void getDamagedDegreeLevelRecursion(KeyValueDto keyValueDto, Integer id) {
        DataDamagedDegree sysDamagedDegree = getCacheDamagedDegreeById(id);
        if (sysDamagedDegree != null && sysDamagedDegree.getId() != null) {
            KeyValueDto kv = new KeyValueDto();
            DataDamagedDegree subDataDamagedDegree = getCacheDamagedDegreeById(sysDamagedDegree.getPid());
            if (subDataDamagedDegree != null && subDataDamagedDegree.getId() != null) {
                getDamagedDegreeLevelRecursion(kv, subDataDamagedDegree.getId());
            }
            kv.setKey(String.valueOf(sysDamagedDegree.getId()));
            kv.setValue(sysDamagedDegree.getName());
            keyValueDto.setKeyValueDto(kv);

        }
    }

    /**
     * 根据key获取树形结构
     *
     * @param fieldName
     * @return
     */
    public TreeViewVo getTreeViewByKey(String fieldName) {
        DataDamagedDegree DataDamagedDegree = getCacheDamagedDegreeByFieldName(fieldName);
        TreeViewVo treeViewVo = new TreeViewVo();
        if (DataDamagedDegree != null) {
            treeViewVo.setId(DataDamagedDegree.getId());
            treeViewVo.setText(DataDamagedDegree.getName());
            treeViewVo.setpId(0);
            treeViewVo.setpName("");
            treeViewVo.setLevel("1");
            treeViewVo.setNodes(getTreeView(DataDamagedDegree.getId(), treeViewVo.getLevel()));
        }
        return treeViewVo;
    }

    private List<TreeViewVo> getTreeView(Integer pid, String parentLevel) {
        TreeViewVo treeViewVo;
        List<DataDamagedDegree> DataDamagedDegrees = getCacheDamagedDegreeListByPid(pid);
        List<TreeViewVo> treeViewVos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(DataDamagedDegrees)) {
            for (DataDamagedDegree item : DataDamagedDegrees) {
                treeViewVo = new TreeViewVo();
                treeViewVo.setId(item.getId());
                treeViewVo.setText(item.getName());
                treeViewVo.setpId(item.getPid());
                treeViewVo.setLevel(parentLevel);
                List<TreeViewVo> treeView = getTreeView(item.getId(), String.format("%s-%s", parentLevel, item.getId()));
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
    public List<ZtreeDto> getDamagedDegreeTree(Integer pid) {
        if (pid == null)
            return Lists.newArrayList();
        List<DataDamagedDegree> DataDamagedDegreeList = getCacheDamagedDegreeListByPid(pid);
        if (CollectionUtils.isEmpty(DataDamagedDegreeList))
            return Lists.newArrayList();
        return LangUtils.transform(DataDamagedDegreeList, p -> {
            return getZtreeDto(p);
        });
    }

    /**
     * 查询数据信息
     *
     * @param name
     * @return
     */
    public List<ZtreeDto> queryDamagedDegreeTree(String name) {
        if (StringUtils.isBlank(name))
            return Lists.newArrayList();
        List<DataDamagedDegree> DamagedDegreeList = cmsDataDamagedDegreeDao.getListObject(null, name);
        if (CollectionUtils.isEmpty(DamagedDegreeList))
            return Lists.newArrayList();
        return LangUtils.transform(DamagedDegreeList, p -> {
            return getZtreeDto(p);
        });
    }

    public List<ZtreeDto> getDamagedDegreeByKey(String key) {
        DataDamagedDegree DataDamagedDegree = getCacheDamagedDegreeByFieldName(key);
        if (DataDamagedDegree == null) return null;
        return Lists.newArrayList(getZtreeDto(DataDamagedDegree));
    }

    private ZtreeDto getZtreeDto(DataDamagedDegree DataDamagedDegree) {
        ZtreeDto ztreeDto = new ZtreeDto();
        ztreeDto.setId(DataDamagedDegree.getId());
        ztreeDto.setPid(DataDamagedDegree.getPid());
        ztreeDto.setName(DataDamagedDegree.getName());
        List<DataDamagedDegree> DamagedDegrees = getCacheDamagedDegreeListByPid(DataDamagedDegree.getId());
        ztreeDto.setIsParent(CollectionUtils.isNotEmpty(DamagedDegrees));
        return ztreeDto;
    }

    /**
     * 根据id获取显示的名称
     *
     * @param id
     * @return
     */
    public String getNameById(Integer id) {
        if (id == null) return "";
        DataDamagedDegree DataDamagedDegree = cmsDataDamagedDegreeDao.getSingleObject(id);
        if (DataDamagedDegree == null) return "";
        return DataDamagedDegree.getName();
    }

    /**
     * 从现有集合中根据名称找出对应数据
     *
     * @param list
     * @param name
     * @return
     */
    public DataDamagedDegree getDamagedDegreeByName(List<DataDamagedDegree> list, String name) {
        if (CollectionUtils.isEmpty(list)) return null;
        if (StringUtils.isBlank(name)) return null;
        for (DataDamagedDegree DataDamagedDegree : list) {
            if (StringUtils.equals(DataDamagedDegree.getName().trim(), name.trim()))
                return DataDamagedDegree;
        }
        return null;
    }

}
