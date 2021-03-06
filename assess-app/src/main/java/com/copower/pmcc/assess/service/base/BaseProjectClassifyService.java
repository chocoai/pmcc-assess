package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.base.BaseProjectClassifyDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.output.base.BaseProjectClassifyVo;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysProjectTypeDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcProjectService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
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
public class BaseProjectClassifyService {
    @Autowired
    private BaseProjectClassifyDao baseProjectClassifyDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ErpRpcProjectService erpRpcProjectService;
    @Autowired
    private ApplicationConstant applicationConstant;

    //region 获取项目分类列表

    /**
     * 获取项目分类列表
     *
     * @return
     */
    public BootstrapTableVo getProjectClassifyList(String fieldName, String name) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseProjectClassify> list = baseProjectClassifyDao.getListObject(name, fieldName, 0);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<BaseProjectClassify>() : list);
        return bootstrapTableVo;
    }

    /**
     * 获取项目分类列表
     *
     * @return
     */
    public BootstrapTableVo getProjectClassifyListByPid(Integer pid) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseProjectClassify> list = baseProjectClassifyDao.getListByPid(pid, requestBaseParam.getSearch());
        List<BaseProjectClassifyVo> voList = LangUtils.transform(list, p -> {
            return getBaseProjectClassifyVo(p);
        });
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(voList) ? new ArrayList<BaseProjectClassifyVo>() : voList);
        return bootstrapTableVo;
    }
    //endregion

    public BaseProjectClassifyVo getBaseProjectClassifyVo(BaseProjectClassify baseProjectClassify) {
        BaseProjectClassifyVo baseProjectClassifyVo = new BaseProjectClassifyVo();
        BeanUtils.copyProperties(baseProjectClassify, baseProjectClassifyVo);
        return baseProjectClassifyVo;
    }

    //region 保存项目分类中的数据

    /**
     * 保存项目分类中的数据
     *
     * @param sysProjectClassify
     */
    public void saveProjectClassify(BaseProjectClassify sysProjectClassify) throws BusinessException {
        if (sysProjectClassify == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (sysProjectClassify.getId() != null && sysProjectClassify.getId() > 0) {
            sysProjectClassify.setBisEnable(sysProjectClassify.getBisEnable() == null ? false : sysProjectClassify.getBisEnable());
            if (!baseProjectClassifyDao.updateObject(sysProjectClassify)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        } else {
            sysProjectClassify.setBisEnable(sysProjectClassify.getBisEnable() == null ? false : sysProjectClassify.getBisEnable());
            sysProjectClassify.setBisDelete(false);
            sysProjectClassify.setCreator(processControllerComponent.getThisUser());
            if (!baseProjectClassifyDao.addObject(sysProjectClassify)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
            //写入erp
            List<Integer> firstLevelIds = LangUtils.transform(getCacheEnableListByPid(0), o -> o.getId());
            SysProjectTypeDto sysProjectTypeDto = new SysProjectTypeDto();
            sysProjectTypeDto.setAppKey(applicationConstant.getAppKey());
            sysProjectTypeDto.setCode(String.valueOf(sysProjectClassify.getId()));
            if (sysProjectClassify.getPid().equals(0))
                sysProjectTypeDto.setDataGrade(1);
            else if(CollectionUtils.isNotEmpty(firstLevelIds)&&firstLevelIds.contains(sysProjectClassify.getPid()))
                sysProjectTypeDto.setDataGrade(2);
            else
                sysProjectTypeDto.setDataGrade(3);
            sysProjectTypeDto.setName(sysProjectClassify.getName());
            erpRpcProjectService.saveSysProjectTypeDto(sysProjectTypeDto);
        }
        processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_PROJECT_CLASSIFY, "");
    }
    //endregion

    //region 删除数据

    /**
     * 删除数据
     *
     * @param id
     */
    public void delProjectClassify(Integer id) throws BusinessException {
        BaseProjectClassify sysProjectClassify = baseProjectClassifyDao.getSingleObject(id);
        if (sysProjectClassify != null) {
            sysProjectClassify.setBisDelete(true);
            if (!baseProjectClassifyDao.updateObject(sysProjectClassify))
                throw new BusinessException(HttpReturnEnum.DELETEFAIL.getName());
            processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_PROJECT_CLASSIFY, "");
        }
    }
    //endregion

    //region 获取缓存中的项目分类数据

    /**
     * 获取缓存中的项目分类数据
     *
     * @return
     */
    public List<BaseProjectClassify> getCacheProjectClassifyListByKey(String fieldName) {
        try {

            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_PROJECT_CLASSIFY_FIELD, fieldName);
            List<BaseProjectClassify> dataDics = LangUtils.listCache(costsKeyPrefix, fieldName, BaseProjectClassify.class, input -> baseProjectClassifyDao.getEnableList(input));
            return dataDics;
        } catch (Exception e) {
            return baseProjectClassifyDao.getEnableList(fieldName);
        }
    }

    public List<BaseProjectClassify> getCacheProjectClassifyListByKey(String fieldName, String filterKey) {
        List<BaseProjectClassify> baseProjectClassifyList = getCacheProjectClassifyListByKey(fieldName);
        return filterBaseProjectClassifyList(baseProjectClassifyList, filterKey);
    }

    public BaseProjectClassify getCacheProjectClassifyByFieldName(String fieldName) {
        String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_PROJECT_CLASSIFY_FIELD, fieldName);
        try {
            BaseProjectClassify sysProjectClassify = LangUtils.singleCache(costsKeyPrefix, fieldName, BaseProjectClassify.class, o -> baseProjectClassifyDao.getSingleObject(o));
            return sysProjectClassify;
        } catch (Exception e) {
            return baseProjectClassifyDao.getSingleObject(fieldName);
        }
    }

    /**
     * 集合过滤by filterKey
     *
     * @param list
     * @param filterKey
     * @return
     */
    private List<BaseProjectClassify> filterBaseProjectClassifyList(List<BaseProjectClassify> list, String filterKey) {
        if (StringUtils.isBlank(filterKey)) return list;
        List<String> stringList = FormatUtils.transformString2List(filterKey);
        return LangUtils.filter(list, p -> {
            return !stringList.contains(p.getFieldName());
        });
    }

    //region 获取缓存中的项目分类数据

    /**
     * 获取缓存中的项目分类数据
     *
     * @return
     */
    public List<BaseProjectClassify> getCacheProjectClassifyListByPid(Integer pid) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_PROJECT_CLASSIFY_PID, String.valueOf(pid));
        try {
            List<BaseProjectClassify> sysProjectClassifys = LangUtils.listCache(rdsKey, pid, BaseProjectClassify.class, input -> baseProjectClassifyDao.getEnableListByPid(input));
            return sysProjectClassifys;
        } catch (Exception e) {
            return baseProjectClassifyDao.getListByPid(pid);
        }
    }

    /**
     * 获取缓存中的项目分类数据
     *
     * @return
     */
    public List<BaseProjectClassify> getCacheEnableListByPid(Integer pid) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_PROJECT_CLASSIFY_PID_ENABLE, String.valueOf(pid));
        try {
            List<BaseProjectClassify> sysProjectClassifys = LangUtils.listCache(rdsKey, pid, BaseProjectClassify.class, input -> baseProjectClassifyDao.getEnableListByPid(input));
            return sysProjectClassifys;
        } catch (Exception e) {
            return baseProjectClassifyDao.getEnableListByPid(pid);
        }
    }

    public List<BaseProjectClassify> getCacheProjectClassifyListByPid(Integer pid, String filterKey) {
        List<BaseProjectClassify> baseProjectClassifyList = getCacheProjectClassifyListByPid(pid);
        return filterBaseProjectClassifyList(baseProjectClassifyList, filterKey);
    }

    public List<BaseProjectClassify> getProjectClassifyListByPids(List<Integer> pids) {
        return baseProjectClassifyDao.getEnableListByPids(pids);
    }

    /**
     * 获取缓存中的项目分类数据
     *
     * @return
     */
    public BaseProjectClassify getCacheProjectClassifyById(Integer id) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_PROJECT_CLASSIFY_ID, String.valueOf(id));
        try {
            BaseProjectClassify sysProjectClassify = LangUtils.singleCache(rdsKey, id, BaseProjectClassify.class, o -> baseProjectClassifyDao.getSingleObject(o));
            return sysProjectClassify;
        } catch (Exception e) {
            return baseProjectClassifyDao.getSingleObject(id);
        }
    }

    /**
     * 根据id获取名称
     *
     * @param id
     * @return
     */
    public String getNameById(Integer id) {
        if (id == null) return "";
        BaseProjectClassify projectClassify = this.getCacheProjectClassifyById(id);
        if (projectClassify == null) return "";
        return projectClassify.getName();
    }

    /**
     * 获取单个分类
     *
     * @param id
     * @return
     */
    public BaseProjectClassify getProjectClassifyById(Integer id) {
        return baseProjectClassifyDao.getSingleObject(id);
    }

    /**
     * 获取立项项目类型 类别 范围
     *
     * @return
     */
    public List<KeyValueDto> getProjectInitClassify() {
        List<KeyValueDto> keyValueDtoList = Lists.newArrayList();
        List<BaseProjectClassify> classList = getCacheEnableListByPid(0);//获取到类型数据
        if (CollectionUtils.isNotEmpty(classList)) {
            KeyValueDto keyValueDto = null;
            for (BaseProjectClassify baseProjectClassify : classList) {
                keyValueDto = new KeyValueDto();
                keyValueDto.setKey(String.valueOf(baseProjectClassify.getId()));
                keyValueDto.setValue(baseProjectClassify.getName());
                //找对应的类别
                List<BaseProjectClassify> typeList = getCacheEnableListByPid(baseProjectClassify.getId());
                if (CollectionUtils.isNotEmpty(typeList)) {
                    List<KeyValueDto> projectClassifyDtoList = Lists.newArrayList();
                    for (BaseProjectClassify projectClassify : typeList) {
                        KeyValueDto projectClassifyDto = new KeyValueDto();
                        projectClassifyDto.setKey(String.valueOf(projectClassify.getId()));
                        projectClassifyDto.setValue(projectClassify.getName());
                        projectClassifyDto.setExplain(projectClassify.getApplyUrl());
                        //找出对应的范围
                        List<BaseProjectClassify> categoryList = getCacheEnableListByPid(projectClassify.getId());
                        if (CollectionUtils.isNotEmpty(categoryList)) {
                            List<KeyValueDto> classifyDtoList = Lists.newArrayList();
                            for (BaseProjectClassify classify : categoryList) {
                                KeyValueDto classifyDto = new KeyValueDto();
                                classifyDto.setKey(String.valueOf(classify.getId()));
                                classifyDto.setValue(classify.getName());
                                classifyDto.setExplain(StringUtils.isBlank(classify.getApplyUrl()) ? projectClassify.getApplyUrl() : classify.getApplyUrl());
                                classifyDtoList.add(classifyDto);
                            }
                            projectClassifyDto.setKeyValueDtos(classifyDtoList);
                        }
                        projectClassifyDtoList.add(projectClassifyDto);
                    }
                    keyValueDto.setKeyValueDtos(projectClassifyDtoList);
                }
                keyValueDtoList.add(keyValueDto);
            }
        }
        return keyValueDtoList;
    }


    /**
     * 获取项目分类的数据层次
     *
     * @param id
     * @return
     */
    public KeyValueDto getProjectClassifyLevel(Integer id) {
        KeyValueDto keyValueDto = new KeyValueDto();
        BaseProjectClassify sysProjectClassify = getCacheProjectClassifyById(id);
        BaseProjectClassify subBaseProjectClassify = getCacheProjectClassifyById(sysProjectClassify.getPid());
        if (subBaseProjectClassify != null && subBaseProjectClassify.getId() != null) {
            getProjectClassifyLevelRecursion(keyValueDto, subBaseProjectClassify.getId());
        }
        keyValueDto.setKey(String.valueOf(sysProjectClassify.getId()));
        keyValueDto.setValue(sysProjectClassify.getName());
        return keyValueDto;
    }

    private void getProjectClassifyLevelRecursion(KeyValueDto keyValueDto, Integer id) {
        BaseProjectClassify sysProjectClassify = getCacheProjectClassifyById(id);
        if (sysProjectClassify != null && sysProjectClassify.getId() != null) {
            KeyValueDto kv = new KeyValueDto();
            BaseProjectClassify subBaseProjectClassify = getCacheProjectClassifyById(sysProjectClassify.getPid());
            if (subBaseProjectClassify != null && subBaseProjectClassify.getId() != null) {
                getProjectClassifyLevelRecursion(kv, subBaseProjectClassify.getId());
            }
            kv.setKey(String.valueOf(sysProjectClassify.getId()));
            kv.setValue(sysProjectClassify.getName());
            keyValueDto.setKeyValueDto(kv);
        }
    }


    /**
     * 查询数据信息
     *
     * @param name
     * @return
     */
    public List<ZtreeDto> queryProjectClassifyTree(String name, Integer pid, String key, String filterKey) {
        //1.如果pid不为空 则查询出数据 并过滤
        //2.pid为空 key 不为空 则以key查询数据再过滤
        //3.如果pid 与key都为空 则先以名称查询 再过滤
        List<BaseProjectClassify> baseProjectClassifyList = null;
        baseProjectClassifyList = baseProjectClassifyDao.getListObject(name, null, null);
        if (CollectionUtils.isEmpty(baseProjectClassifyList))
            return Lists.newArrayList();
        if (StringUtils.isNotBlank(filterKey)) {
            baseProjectClassifyList = filterBaseProjectClassifyList(baseProjectClassifyList, filterKey);
        }

        List<BaseProjectClassify> resultList = Lists.newArrayList();
        if (pid != null) {
            for (BaseProjectClassify baseProjectClassify : baseProjectClassifyList) {
                if (nodeInPidDataList(baseProjectClassify, pid)) {
                    resultList.add(baseProjectClassify);
                }
            }
        }
        if (StringUtils.isNotBlank(key)) {
            for (BaseProjectClassify baseProjectClassify : baseProjectClassifyList) {
                if (nodeInKeyDataList(baseProjectClassify, key)) {
                    resultList.add(baseProjectClassify);
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
     * @param baseProjectClassify
     * @param pid
     */
    private boolean nodeInPidDataList(BaseProjectClassify baseProjectClassify, Integer pid) {
        boolean result = false;
        if (baseProjectClassify.getPid().equals(pid)) {
            return true;
        } else {
            BaseProjectClassify projectClassify = getCacheProjectClassifyById(baseProjectClassify.getPid());
            if (projectClassify != null)
                result = nodeInPidDataList(projectClassify, pid);
        }
        return result;
    }

    /**
     * 验证节点是否存在初始key 数据中
     *
     * @param baseProjectClassify
     * @param key
     */
    private boolean nodeInKeyDataList(BaseProjectClassify baseProjectClassify, String key) {
        boolean result = false;
        if (StringUtils.equals(key, baseProjectClassify.getFieldName())) {
            return true;
        } else {
            BaseProjectClassify projectClassify = getCacheProjectClassifyById(baseProjectClassify.getPid());
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
    public List<ZtreeDto> getProjectClassifyTreeByPid(Integer pid, String filterKey) {
        List<BaseProjectClassify> baseProjectClassifyList = getCacheProjectClassifyListByPid(pid, filterKey);
        if (CollectionUtils.isEmpty(baseProjectClassifyList)) return null;
        return LangUtils.transform(baseProjectClassifyList, p -> {
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
    public List<ZtreeDto> getProjectClassifyTreeByKey(String key, String filterKey) {
        List<BaseProjectClassify> baseProjectClassifyList = getCacheProjectClassifyListByKey(key, filterKey);
        if (CollectionUtils.isEmpty(baseProjectClassifyList)) return null;
        return LangUtils.transform(baseProjectClassifyList, p -> {
            return getZtreeDto(p);
        });
    }


    /**
     * 实体转ztree对象
     *
     * @param baseProjectClassify
     * @return
     */
    private ZtreeDto getZtreeDto(BaseProjectClassify baseProjectClassify) {
        ZtreeDto ztreeDto = new ZtreeDto();
        ztreeDto.setId(baseProjectClassify.getId());
        ztreeDto.setPid(baseProjectClassify.getPid());
        ztreeDto.setName(baseProjectClassify.getName());
        ztreeDto.setKey(baseProjectClassify.getFieldName());
        List<BaseProjectClassify> dataDics = getCacheProjectClassifyListByPid(baseProjectClassify.getId());
        ztreeDto.setIsParent(CollectionUtils.isNotEmpty(dataDics));
        return ztreeDto;
    }


    public BaseProjectClassify getDataById(Integer id) {
        BaseProjectClassify baseProjectClassify = baseProjectClassifyDao.getSingleObject(id);
        return baseProjectClassify;
    }

    /**
     * 获取数据引用id
     *
     * @param id
     * @return
     */
    public Integer getReferenceId(Integer id) {
        BaseProjectClassify projectClassify = this.getCacheProjectClassifyById(id);
        if (projectClassify == null) return id;
        if (StringUtils.isBlank(projectClassify.getUseSameFieldName())) return id;
        projectClassify = this.getCacheProjectClassifyByFieldName(projectClassify.getUseSameFieldName());
        return projectClassify == null ? id : projectClassify.getId();
    }

    /**
     * 获取类型类别的名称
     *
     * @param type
     * @param category
     * @return
     */
    public String getTypeAndCategoryName(String type, String category) {
        if (StringUtils.isBlank(type) || StringUtils.isBlank(category)) return "";
        List<Integer> typeList = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(StringUtils.strip(type, ",")));
        List<Integer> categoryList = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(StringUtils.strip(category, ",")));
        if (CollectionUtils.isEmpty(typeList)) return "";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < typeList.size(); i++) {
            stringBuilder.append(this.getNameById(typeList.get(i))).append("/")
                    .append(this.getNameById(categoryList.get(i))).append(",");
        }

        return StringUtils.stripEnd(stringBuilder.toString(), ",");
    }
}
