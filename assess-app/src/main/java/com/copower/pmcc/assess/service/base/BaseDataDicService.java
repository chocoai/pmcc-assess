package com.copower.pmcc.assess.service.base;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.base.BaseDataDicDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.input.ZtreeDto;
import com.copower.pmcc.assess.dto.output.TreeViewVo;
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
import org.springframework.beans.BeanUtils;
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
    private ProcessControllerComponent processControllerComponent;

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
                BeanUtils.copyProperties(sysDataDic, sysDataDicTemp);
                if (!cmsBaseDataDicDao.updateObject(sysDataDicTemp)) {
                    throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
                }
            } else {
                throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
            }

        } else {
            sysDataDicTemp = new BaseDataDic();
            BeanUtils.copyProperties(sysDataDic, sysDataDicTemp);
            sysDataDicTemp.setBisEnable(sysDataDic.getBisEnable() == null ? false : sysDataDic.getBisEnable());
            sysDataDicTemp.setBisDelete(false);
            sysDataDicTemp.setCreator(processControllerComponent.getThisUser());
            if (!cmsBaseDataDicDao.addObject(sysDataDicTemp)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        }
        processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_DATA_DIC, "");
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
            processControllerComponent.removeRedisKeyValues(AssessCacheConstant.PMCC_ASSESS_DATA_DIC, "");
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

            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_DATA_DIC_FIELD, fieldName);
            List<BaseDataDic> dataDics = LangUtils.listCache(costsKeyPrefix, fieldName, BaseDataDic.class, input -> cmsBaseDataDicDao.getEnableList(input));
            return dataDics;
        } catch (Exception e) {
            return cmsBaseDataDicDao.getEnableList(fieldName);
        }

    }

    public Integer getDataDicIdByName(List<BaseDataDic> dataDicList,String name) {
        if(CollectionUtils.isEmpty(dataDicList)) return 0;
        for (BaseDataDic baseDataDic : dataDicList) {
            if(name.equals(baseDataDic.getName()))
                return baseDataDic.getId();
        }
        return 0;
    }


    public BaseDataDic getCacheDataDicByFieldName(String fieldName) {
        String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_DATA_DIC_FIELD_ITEM, fieldName);

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
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_DATA_DIC_PID, String.valueOf(pid));

        try {
            List<BaseDataDic> sysDataDics = LangUtils.listCache(rdsKey, pid, BaseDataDic.class, input -> cmsBaseDataDicDao.getEnableListByPid(input));
            return sysDataDics;
        } catch (Exception e) {
            return cmsBaseDataDicDao.getEnableListByPid(pid);
        }
    }

    /**
     * 通过pid获取所有最低级数据
     *
     * @return
     */
    public void getBestLowDicListByPid(List<BaseDataDic> list,Integer pid) {
        List<BaseDataDic> dataDicList = cmsBaseDataDicDao.getEnableListByPid(pid);
        if(CollectionUtils.isNotEmpty(dataDicList)){
            for (BaseDataDic item: dataDicList) {
                getBestLowDicListByPid(list,item.getId());
            }
        }else {
            BaseDataDic singleObject = cmsBaseDataDicDao.getSingleObject(pid);
            list.add(singleObject);
        }
    }


    /**
     * 获取缓存中的数据字典数据
     *
     * @return
     */
    public BaseDataDic getCacheDataDicById(Integer id) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_DATA_DIC_ID, String.valueOf(id));

        try {
            BaseDataDic sysDataDic = LangUtils.singleCache(rdsKey, id, BaseDataDic.class, o -> cmsBaseDataDicDao.getSingleObject(o));
            return sysDataDic;
        } catch (Exception e) {
            return cmsBaseDataDicDao.getSingleObject(id);
        }

    }

    public BaseDataDic getDataDicById(Integer id) {
        return cmsBaseDataDicDao.getSingleObject(id);
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

    /**
     * 根据key获取树形结构
     *
     * @param fieldName
     * @return
     */
    public TreeViewVo getTreeViewByKey(String fieldName) {
        BaseDataDic baseDataDic = getCacheDataDicByFieldName(fieldName);
        TreeViewVo treeViewVo = new TreeViewVo();
        if (baseDataDic != null) {
            treeViewVo.setId(baseDataDic.getId());
            treeViewVo.setText(baseDataDic.getName());
            treeViewVo.setpId(0);
            treeViewVo.setpName("");
            treeViewVo.setLevel("1");
            treeViewVo.setNodes(getTreeView(baseDataDic.getId(), treeViewVo.getLevel()));
        }
        return treeViewVo;
    }

    private List<TreeViewVo> getTreeView(Integer pid, String parentLevel) {
        TreeViewVo treeViewVo;
        List<BaseDataDic> baseDataDics = getCacheDataDicListByPid(pid);
        List<TreeViewVo> treeViewVos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(baseDataDics)) {
            for (BaseDataDic item : baseDataDics) {
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
    public List<ZtreeDto> getBaseDicTree(Integer pid) {
        if (pid == null)
            return Lists.newArrayList();
        List<BaseDataDic> baseDataDicList = getCacheDataDicListByPid(pid);
        if (CollectionUtils.isEmpty(baseDataDicList))
            return Lists.newArrayList();
        return LangUtils.transform(baseDataDicList, p -> {
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
        List<BaseDataDic> dataDicList = cmsBaseDataDicDao.getListObject(null, name);
        if (CollectionUtils.isEmpty(dataDicList))
            return Lists.newArrayList();
        return LangUtils.transform(dataDicList, p -> {
            return getZtreeDto(p);
        });
    }

    public List<ZtreeDto> getBaseDicByKey(String key) {
        BaseDataDic baseDataDic = getCacheDataDicByFieldName(key);
        if (baseDataDic == null) return null;
        return Lists.newArrayList(getZtreeDto(baseDataDic));
    }

    private ZtreeDto getZtreeDto(BaseDataDic baseDataDic) {
        ZtreeDto ztreeDto = new ZtreeDto();
        ztreeDto.setId(baseDataDic.getId());
        ztreeDto.setPid(baseDataDic.getPid());
        ztreeDto.setName(baseDataDic.getName());
        List<BaseDataDic> dataDics = getCacheDataDicListByPid(baseDataDic.getId());
        ztreeDto.setIsParent(CollectionUtils.isNotEmpty(dataDics));
        return ztreeDto;
    }

    public String getNameByIds(String ids){
        List<String> stringList = FormatUtils.transformString2List(ids);
        stringList = LangUtils.transform(stringList,obj -> getNameById(obj)) ;
        return StringUtils.join(stringList,",") ;
    }

    /**
     * 根据id获取显示的名称
     *
     * @param id
     * @return
     */
    public String getNameById(Integer id) {
        if (id == null || id <= 0) return "";
        BaseDataDic baseDataDic = this.getCacheDataDicById(id);
        if (baseDataDic == null) return "";
        return baseDataDic.getName();
    }

    public String getNameById(String id) {
        if (StringUtils.isBlank(id) || !StringUtils.isNumeric(id)) return id;
        return getNameById(Integer.valueOf(id));
    }

    /**
     * 从现有集合中根据名称找出对应数据
     *
     * @param list
     * @param name
     * @return
     */
    public BaseDataDic getDataDicByName(List<BaseDataDic> list, String name) {
        if (CollectionUtils.isEmpty(list)) return null;
        if (StringUtils.isBlank(name)) return null;
        for (BaseDataDic baseDataDic : list) {
            if (StringUtils.equals(baseDataDic.getName().trim(), name.trim()))
                return baseDataDic;
        }
        return null;
    }

    /**
     * 获取集合中对应名称
     *
     * @param dataDicList
     * @param idString
     * @return
     */
    public String getDataDicName(List<BaseDataDic> dataDicList, String idString) {
        StringBuilder result = new StringBuilder();
        if (StringUtils.isNotBlank(idString)) {
            String s = StringUtils.strip(idString, ",");
            List<Integer> integerList = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(s));
            for (Integer integer : integerList) {
                for (BaseDataDic baseDataDic : dataDicList) {
                    if (integer.equals(baseDataDic.getId())) {
                        result.append(baseDataDic.getName()).append(",");
                    }
                }
            }
        }
        return StringUtils.strip(result.toString(), ",");
    }

    /**
     * 通过key获取key-value中value值
     *
     * @param key
     * @param data
     * @return
     */
    public String getValueByKey(String key,BaseDataDic data){
        if(StringUtils.isNotBlank(key)&&data!=null){
            List<KeyValueDto> keyValueDtos = JSON.parseArray(data.getKeyValue(), KeyValueDto.class);
            if(CollectionUtils.isNotEmpty(keyValueDtos)) {
                for (KeyValueDto dto : keyValueDtos) {
                    if (key.equals(dto.getKey())) {
                        return dto.getValue();
                    }
                }
            }else{
                BaseDataDic parentData = this.getDataDicById(data.getPid());
                keyValueDtos = JSON.parseArray(parentData.getKeyValue(), KeyValueDto.class);
                if(CollectionUtils.isNotEmpty(keyValueDtos)) {
                    for (KeyValueDto dto : keyValueDtos) {
                        if (key.equals(dto.getKey())) {
                            return dto.getValue();
                        }
                    }
                }
            }
        }
        return null;
    }
}
