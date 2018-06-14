package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.dao.base.BaseProjectClassifyDao;
import com.copower.pmcc.assess.dal.entity.BaseFormModule;
import com.copower.pmcc.assess.dal.entity.BaseProjectClassify;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.output.BaseProjectClassifyVo;
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
public class BaseProjectClassifyService {
    @Autowired
    private BaseProjectClassifyDao baseProjectClassifyDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseFormService baseFormService;

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
        List<BaseProjectClassify> list = baseProjectClassifyDao.getListObject(fieldName, name);
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
        if (baseProjectClassify.getFormModuleId() != null) {
            BaseFormModule baseFormModule = baseFormService.getCacheFormModuleById(baseProjectClassify.getFormModuleId());
            if (baseFormModule != null)
                baseProjectClassifyVo.setFormModuleName(baseFormModule.getCnName());
        }
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
    public List<BaseProjectClassify> getCacheProjectClassifyList(String fieldName) {

        try {

            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_PROJECT_CLASSIFY_FIELD, fieldName);
            List<BaseProjectClassify> dataDics = LangUtils.listCache(costsKeyPrefix, fieldName, BaseProjectClassify.class, input -> baseProjectClassifyDao.getEnableList(input));
            return dataDics;
        } catch (Exception e) {
            return baseProjectClassifyDao.getEnableList(fieldName);
        }

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
            return baseProjectClassifyDao.getEnableListByPid(pid);
        }
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
        List<BaseProjectClassify> classList = getCacheProjectClassifyListByPid(0);//获取到类型数据
        if (CollectionUtils.isNotEmpty(classList)) {
            KeyValueDto keyValueDto = null;
            for (BaseProjectClassify baseProjectClassify : classList) {
                keyValueDto = new KeyValueDto();
                keyValueDto.setKey(String.valueOf(baseProjectClassify.getId()));
                keyValueDto.setValue(baseProjectClassify.getName());
                //找对应的类别
                List<BaseProjectClassify> typeList = getCacheProjectClassifyListByPid(baseProjectClassify.getId());
                if (CollectionUtils.isNotEmpty(typeList)) {
                    List<KeyValueDto> projectClassifyDtoList = Lists.newArrayList();
                    for (BaseProjectClassify projectClassify : typeList) {
                        KeyValueDto projectClassifyDto = new KeyValueDto();
                        projectClassifyDto.setKey(String.valueOf(projectClassify.getId()));
                        projectClassifyDto.setValue(projectClassify.getName());
                        projectClassifyDto.setExplain(projectClassify.getApplyUrl());
                        //找出对应的范围
                        List<BaseProjectClassify> categoryList = getCacheProjectClassifyListByPid(projectClassify.getId());
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
     * 获取默认类型
     *
     * @return
     */
    public BaseProjectClassify getDefaultClass() {
        BaseProjectClassify queryParam = new BaseProjectClassify();
        queryParam.setBisDefault(true);
        queryParam.setPid(0);
        List<BaseProjectClassify> classList = baseProjectClassifyDao.getProjectClassifyList(queryParam);
        if (CollectionUtils.isEmpty(classList)) return null;
        return classList.get(0);
    }

    /**
     * 获取默认类别
     *
     * @return
     */
    public BaseProjectClassify getDefaultType() {
        BaseProjectClassify defaultClass = getDefaultClass();
        if (defaultClass == null) return null;
        BaseProjectClassify queryParam = new BaseProjectClassify();
        queryParam.setBisDefault(true);
        queryParam.setPid(defaultClass.getId());
        List<BaseProjectClassify> typeList = baseProjectClassifyDao.getProjectClassifyList(queryParam);
        if (CollectionUtils.isEmpty(typeList)) return null;
        return typeList.get(0);
    }

    /**
     * 获取默认范围
     *
     * @return
     */
    public BaseProjectClassify getDefaultCategory(Integer typeId) {
        List<BaseProjectClassify> categoryList = null;
        BaseProjectClassify queryParam = new BaseProjectClassify();
        queryParam.setBisDefault(true);
        if (typeId != null && typeId > 0) {
            queryParam.setPid(typeId);
            categoryList = baseProjectClassifyDao.getProjectClassifyList(queryParam);
            if (CollectionUtils.isNotEmpty(categoryList))
                return categoryList.get(0);
        }
        BaseProjectClassify defaultType = getDefaultType();
        if (defaultType == null) return null;
        queryParam.setPid(defaultType.getId());
        categoryList = baseProjectClassifyDao.getProjectClassifyList(queryParam);
        if (CollectionUtils.isEmpty(categoryList)) return null;
        return categoryList.get(0);
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

    public TreeViewVo getProjectClassifyTree(String fieldName) {

        BaseProjectClassify hrBaseProjectClassify = getCacheProjectClassifyByFieldName(fieldName);
        TreeViewVo treeViewVo = new TreeViewVo();
        if (hrBaseProjectClassify != null) {
            treeViewVo.setId(hrBaseProjectClassify.getId());
            treeViewVo.setText(hrBaseProjectClassify.getName());
            treeViewVo.setpId(0);
            treeViewVo.setpName("");
            treeViewVo.setNodes(getTreeView(hrBaseProjectClassify.getId()));
        }
        return treeViewVo;
    }

    private List<TreeViewVo> getTreeView(Integer pid) {
        TreeViewVo treeViewVo;
        List<BaseProjectClassify> hrBaseProjectClassifys = getCacheProjectClassifyListByPid(pid);
        List<TreeViewVo> treeViewVos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(hrBaseProjectClassifys)) {

            for (BaseProjectClassify item : hrBaseProjectClassifys) {
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
    public List<ZtreeDto> getProjectClassifyTree(Integer pid) {
        if (pid == null)
            return Lists.newArrayList();
        List<BaseProjectClassify> baseProjectClassifyList = getCacheProjectClassifyListByPid(pid);
        if (CollectionUtils.isEmpty(baseProjectClassifyList))
            return Lists.newArrayList();
        return LangUtils.transform(baseProjectClassifyList, p -> {
            return getZtreeDto(p);
        });
    }

    /**
     * 查询数据信息
     *
     * @param name
     * @return
     */
    public List<ZtreeDto> queryProjectClassifyTree(String name) {
        if (StringUtils.isBlank(name))
            return Lists.newArrayList();
        List<BaseProjectClassify> dataDicList = baseProjectClassifyDao.getListObject(null, name);
        if (CollectionUtils.isEmpty(dataDicList))
            return Lists.newArrayList();
        return LangUtils.transform(dataDicList, p -> {
            return getZtreeDto(p);
        });
    }

    public List<ZtreeDto> getProjectClassifyByKey(String key) {
        BaseProjectClassify baseProjectClassify = getCacheProjectClassifyByFieldName(key);
        if (baseProjectClassify == null) return null;
        return Lists.newArrayList(getZtreeDto(baseProjectClassify));
    }

    private ZtreeDto getZtreeDto(BaseProjectClassify baseProjectClassify) {
        ZtreeDto ztreeDto = new ZtreeDto();
        ztreeDto.setId(baseProjectClassify.getId());
        ztreeDto.setPid(baseProjectClassify.getPid());
        ztreeDto.setName(baseProjectClassify.getName());
        List<BaseProjectClassify> dataDics = getCacheProjectClassifyListByPid(baseProjectClassify.getId());
        ztreeDto.setIsParent(CollectionUtils.isNotEmpty(dataDics));
        return ztreeDto;
    }


}
