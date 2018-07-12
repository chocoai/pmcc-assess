package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataExamineTaskDao;
import com.copower.pmcc.assess.dal.basis.entity.DataExamineTask;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
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
public class DataExamineTaskService {
    @Autowired
    private DataExamineTaskDao dataExamineTaskDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    //region 获取调查任务列表

    /**
     * 获取调查任务列表
     *
     * @return
     */
    public BootstrapTableVo getDataExamineTaskList(String fieldName, String name) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataExamineTask> list = dataExamineTaskDao.getListObject(name, fieldName, 0);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<DataExamineTask>() : list);
        return bootstrapTableVo;
    }
    

    /**
     * 获取调查任务列表
     *
     * @return
     */
    public BootstrapTableVo getDataExamineTaskListByPid(Integer pid) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataExamineTask> list = dataExamineTaskDao.getListByPid(pid, requestBaseParam.getSearch());
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<DataExamineTask>() : list);
        return bootstrapTableVo;
    }
    //endregion
    

    //region 保存调查任务中的数据

    /**
     * 保存调查任务中的数据
     *
     * @param sysDataExamineTask
     */
    public void saveDataExamineTask(DataExamineTask sysDataExamineTask) throws BusinessException {
        if (sysDataExamineTask == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (sysDataExamineTask.getId() != null && sysDataExamineTask.getId() > 0) {
            sysDataExamineTask.setBisEnable(sysDataExamineTask.getBisEnable() == null ? false : sysDataExamineTask.getBisEnable());
            if (!dataExamineTaskDao.updateObject(sysDataExamineTask)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        } else {
            sysDataExamineTask.setBisEnable(sysDataExamineTask.getBisEnable() == null ? false : sysDataExamineTask.getBisEnable());
            sysDataExamineTask.setBisDelete(false);
            sysDataExamineTask.setCreator(processControllerComponent.getThisUser());
            if (!dataExamineTaskDao.addObject(sysDataExamineTask)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        }
        processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_EXAMINE_TASK, "");
    }
    //endregion

    //region 删除数据

    /**
     * 删除数据
     *
     * @param id
     */
    public void delDataExamineTask(Integer id) throws BusinessException {
        DataExamineTask sysDataExamineTask = dataExamineTaskDao.getSingleObject(id);
        if (sysDataExamineTask != null) {
            sysDataExamineTask.setBisDelete(true);
            if (!dataExamineTaskDao.updateObject(sysDataExamineTask))
                throw new BusinessException(HttpReturnEnum.DELETEFAIL.getName());
            processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_EXAMINE_TASK, "");
        }
    }
    //endregion

    //region 获取缓存中的调查任务数据

    /**
     * 获取缓存中的调查任务数据
     *
     * @return
     */
    public List<DataExamineTask> getCacheDataExamineTaskListByKey(String fieldName) {
        try {

            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_EXAMINE_TASK_FIELD, fieldName);
            List<DataExamineTask> dataDics = LangUtils.listCache(costsKeyPrefix, fieldName, DataExamineTask.class, input -> dataExamineTaskDao.getEnableList(input));
            return dataDics;
        } catch (Exception e) {
            return dataExamineTaskDao.getEnableList(fieldName);
        }
    }

    public List<DataExamineTask> getCacheDataExamineTaskListByKey(String fieldName, String filterKey) {
        List<DataExamineTask> baseDataExamineTaskList = getCacheDataExamineTaskListByKey(fieldName);
        return filterDataExamineTaskList(baseDataExamineTaskList, filterKey);
    }

    public DataExamineTask getCacheDataExamineTaskByFieldName(String fieldName) {
        String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_EXAMINE_TASK_FIELD, fieldName);
        try {
            DataExamineTask sysDataExamineTask = LangUtils.singleCache(costsKeyPrefix, fieldName, DataExamineTask.class, o -> dataExamineTaskDao.getSingleObject(o));
            return sysDataExamineTask;
        } catch (Exception e) {
            return dataExamineTaskDao.getSingleObject(fieldName);
        }
    }

    /**
     * 集合过滤by filterKey
     *
     * @param list
     * @param filterKey
     * @return
     */
    private List<DataExamineTask> filterDataExamineTaskList(List<DataExamineTask> list, String filterKey) {
        if (StringUtils.isBlank(filterKey)) return list;
        List<String> stringList = FormatUtils.transformString2List(filterKey);
        return LangUtils.filter(list, p -> {
            return !stringList.contains(p.getFieldName());
        });
    }

    //region 获取缓存中的调查任务数据

    /**
     * 获取缓存中的调查任务数据
     *
     * @return
     */
    public List<DataExamineTask> getCacheDataExamineTaskListByPid(Integer pid) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_EXAMINE_TASK_PID, String.valueOf(pid));

        try {
            List<DataExamineTask> sysDataExamineTasks = LangUtils.listCache(rdsKey, pid, DataExamineTask.class, input -> dataExamineTaskDao.getEnableListByPid(input));
            return sysDataExamineTasks;
        } catch (Exception e) {
            return dataExamineTaskDao.getEnableListByPid(pid);
        }
    }

    public List<DataExamineTask> getCacheDataExamineTaskListByPid(Integer pid, String filterKey) {
        List<DataExamineTask> baseDataExamineTaskList = getCacheDataExamineTaskListByPid(pid);
        return filterDataExamineTaskList(baseDataExamineTaskList, filterKey);
    }

    /**
     * 获取缓存中的调查任务数据
     *
     * @return
     */
    public DataExamineTask getCacheDataExamineTaskById(Integer id) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_EXAMINE_TASK_ID, String.valueOf(id));
        try {
            DataExamineTask sysDataExamineTask = LangUtils.singleCache(rdsKey, id, DataExamineTask.class, o -> dataExamineTaskDao.getSingleObject(o));
            return sysDataExamineTask;
        } catch (Exception e) {
            return dataExamineTaskDao.getSingleObject(id);
        }
    }

    /**
     * 获取单个分类
     *
     * @param id
     * @return
     */
    public DataExamineTask getDataExamineTaskById(Integer id) {
        return dataExamineTaskDao.getSingleObject(id);
    }


    /**
     * 获取调查任务的数据层次
     *
     * @param id
     * @return
     */
    public KeyValueDto getDataExamineTaskLevel(Integer id) {
        KeyValueDto keyValueDto = new KeyValueDto();
        DataExamineTask sysDataExamineTask = getCacheDataExamineTaskById(id);
        DataExamineTask subDataExamineTask = getCacheDataExamineTaskById(sysDataExamineTask.getPid());
        if (subDataExamineTask != null && subDataExamineTask.getId() != null) {
            getDataExamineTaskLevelRecursion(keyValueDto, subDataExamineTask.getId());
        }
        keyValueDto.setKey(String.valueOf(sysDataExamineTask.getId()));
        keyValueDto.setValue(sysDataExamineTask.getName());
        return keyValueDto;
    }

    private void getDataExamineTaskLevelRecursion(KeyValueDto keyValueDto, Integer id) {
        DataExamineTask sysDataExamineTask = getCacheDataExamineTaskById(id);
        if (sysDataExamineTask != null && sysDataExamineTask.getId() != null) {
            KeyValueDto kv = new KeyValueDto();
            DataExamineTask subDataExamineTask = getCacheDataExamineTaskById(sysDataExamineTask.getPid());
            if (subDataExamineTask != null && subDataExamineTask.getId() != null) {
                getDataExamineTaskLevelRecursion(kv, subDataExamineTask.getId());
            }
            kv.setKey(String.valueOf(sysDataExamineTask.getId()));
            kv.setValue(sysDataExamineTask.getName());
            keyValueDto.setKeyValueDto(kv);
        }
    }


    /**
     * 查询数据信息
     *
     * @param name
     * @return
     */
    public List<ZtreeDto> queryDataExamineTaskTree(String name, Integer pid, String key, String filterKey) {
        //1.如果pid不为空 则查询出数据 并过滤
        //2.pid为空 key 不为空 则以key查询数据再过滤
        //3.如果pid 与key都为空 则先以名称查询 再过滤
        List<DataExamineTask> baseDataExamineTaskList = null;
        baseDataExamineTaskList = dataExamineTaskDao.getListObject(name, null, null);
        if (CollectionUtils.isEmpty(baseDataExamineTaskList))
            return Lists.newArrayList();
        if (StringUtils.isNotBlank(filterKey)) {
            baseDataExamineTaskList = filterDataExamineTaskList(baseDataExamineTaskList, filterKey);
        }

        List<DataExamineTask> resultList = Lists.newArrayList();
        if (pid != null) {
            for (DataExamineTask baseDataExamineTask : baseDataExamineTaskList) {
                if (nodeInPidDataList(baseDataExamineTask, pid)) {
                    resultList.add(baseDataExamineTask);
                }
            }
        }
        if (StringUtils.isNotBlank(key)) {
            for (DataExamineTask baseDataExamineTask : baseDataExamineTaskList) {
                if (nodeInKeyDataList(baseDataExamineTask, key)) {
                    resultList.add(baseDataExamineTask);
                }
            }
        }
        return LangUtils.transform(resultList, p -> {
            return getZtreeDto(p);
        });
    }

    /**
     * 验证节点是否存在初始pid 数据中
     *
     * @param baseDataExamineTask
     * @param pid
     */
    private boolean nodeInPidDataList(DataExamineTask baseDataExamineTask, Integer pid) {
        boolean result = false;
        if (baseDataExamineTask.getPid().equals(pid)) {
            return true;
        } else {
            DataExamineTask projectClassify = getCacheDataExamineTaskById(baseDataExamineTask.getPid());
            if (projectClassify != null)
                result = nodeInPidDataList(projectClassify, pid);
        }
        return result;
    }

    /**
     * 验证节点是否存在初始key 数据中
     *
     * @param baseDataExamineTask
     * @param key
     */
    private boolean nodeInKeyDataList(DataExamineTask baseDataExamineTask, String key) {
        boolean result = false;
        if (StringUtils.equals(key, baseDataExamineTask.getFieldName())) {
            return true;
        } else {
            DataExamineTask projectClassify = getCacheDataExamineTaskById(baseDataExamineTask.getPid());
            if (projectClassify != null)
                result = nodeInKeyDataList(projectClassify, key);
        }
        return result;
    }

    /**
     * 获取树节点by pid
     *
     * @param pid
     * @param filterKey
     * @return
     */
    public List<ZtreeDto> getDataExamineTaskTreeByPid(Integer pid, String filterKey) {
        List<DataExamineTask> baseDataExamineTaskList = getCacheDataExamineTaskListByPid(pid, filterKey);
        if (CollectionUtils.isEmpty(baseDataExamineTaskList)) return null;
        return LangUtils.transform(baseDataExamineTaskList, p -> {
            return getZtreeDto(p);
        });
    }

    /**
     * 获取树节点by key
     *
     * @param key
     * @param filterKey
     * @return
     */
    public List<ZtreeDto> getDataExamineTaskTreeByKey(String key, String filterKey) {
        List<DataExamineTask> baseDataExamineTaskList = getCacheDataExamineTaskListByKey(key, filterKey);
        if (CollectionUtils.isEmpty(baseDataExamineTaskList)) return null;
        return LangUtils.transform(baseDataExamineTaskList, p -> {
            return getZtreeDto(p);
        });
    }


    /**
     * 实体转ztree对象
     *
     * @param baseDataExamineTask
     * @return
     */
    private ZtreeDto getZtreeDto(DataExamineTask baseDataExamineTask) {
        ZtreeDto ztreeDto = new ZtreeDto();
        ztreeDto.setId(baseDataExamineTask.getId());
        ztreeDto.setPid(baseDataExamineTask.getPid());
        ztreeDto.setName(baseDataExamineTask.getName());
        ztreeDto.setKey(baseDataExamineTask.getFieldName());
        List<DataExamineTask> dataDics = getCacheDataExamineTaskListByPid(baseDataExamineTask.getId());
        ztreeDto.setIsParent(CollectionUtils.isNotEmpty(dataDics));
        return ztreeDto;
    }


    public DataExamineTask getDataById(Integer id) {
        DataExamineTask baseDataExamineTask = dataExamineTaskDao.getSingleObject(id);
        return baseDataExamineTask;
    }
}
