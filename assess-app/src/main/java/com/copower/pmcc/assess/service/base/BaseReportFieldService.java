package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.dal.basis.dao.base.BaseReportFieldDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportField;
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
 * @Auther: zch
 * @Date: 2019/1/14 14:12
 * @Description:
 */
@Service
public class BaseReportFieldService {

    @Autowired
    private BaseReportFieldDao baseReportFieldDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseAttachmentService baseAttachmentService;


    /**
     * 获取缓存中的数据字典数据
     *
     * @return
     */
    public List<BaseReportField> getCacheReportFieldList(String fieldName) {

        try {

            String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_REPORT_FIELD_FIELD, fieldName);
            List<BaseReportField> dataDics = LangUtils.listCache(costsKeyPrefix, fieldName, BaseReportField.class, input -> baseReportFieldDao.getEnableList(input));
            return dataDics;
        } catch (Exception e) {
            return baseReportFieldDao.getEnableList(fieldName);
        }

    }


    public BaseReportField getCacheReportFieldByFieldName(String fieldName) {
        String costsKeyPrefix = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_REPORT_FIELD_FIELD_ITEM, fieldName);

        try {
            BaseReportField sysReportField = LangUtils.singleCache(costsKeyPrefix, fieldName, BaseReportField.class, o -> baseReportFieldDao.getSingleObject(o));
            return sysReportField;
        } catch (Exception e) {
            return baseReportFieldDao.getSingleObject(fieldName);
        }

    }

    //region 获取缓存中的数据字典数据

    /**
     * 获取缓存中的数据字典数据
     *
     * @return
     */
    public List<BaseReportField> getCacheReportFieldListByPid(Integer pid) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_REPORT_FIELD_PID, String.valueOf(pid));

        try {
            List<BaseReportField> sysReportFields = LangUtils.listCache(rdsKey, pid, BaseReportField.class, input -> baseReportFieldDao.getEnableListByPid(input));
            return sysReportFields;
        } catch (Exception e) {
            return baseReportFieldDao.getEnableListByPid(pid);
        }
    }

    /**
     * 获取缓存中的数据字典数据
     *
     * @return
     */
    public BaseReportField getCacheReportFieldById(Integer id) {
        String rdsKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_REPORT_FIELD_ID, String.valueOf(id));

        try {
            BaseReportField sysReportField = LangUtils.singleCache(rdsKey, id, BaseReportField.class, o -> baseReportFieldDao.getSingleObject(o));
            return sysReportField;
        } catch (Exception e) {
            return baseReportFieldDao.getSingleObject(id);
        }

    }
    
    
    

    public List<BaseReportField> query(BaseReportField baseReportField){
        return baseReportFieldDao.query(baseReportField);
    }

    //region 获取数据字典列表

    /**
     * 获取数据字典列表
     *
     * @return
     */
    public BootstrapTableVo getReportFieldListVo(String fieldName, String name) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseReportField> list = baseReportFieldDao.getListObject(fieldName, name);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<BaseReportField>() : list);
        return bootstrapTableVo;
    }

    /**
     * 获取数据字典列表
     *
     * @return
     */
    public BootstrapTableVo getReportFieldListByPidVo(Integer pid) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseReportField> list = baseReportFieldDao.getListByPid(pid, requestBaseParam.getSearch());
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<BaseReportField>() : list);
        return bootstrapTableVo;
    }
    //endregion

    //region 保存数据字典中的数据

    /**
     * 保存数据字典中的数据
     *
     * @param baseReportField
     */
    public void saveReportField(BaseReportField baseReportField) throws BusinessException {
        BaseReportField baseReportFieldTemp = null;

        if (baseReportFieldDao.isExist(baseReportField.getFieldName(), baseReportField.getId())) {
            throw new BusinessException(HttpReturnEnum.EXIST.getName());
        }
        if (baseReportField.getId() != null && baseReportField.getId() > 0) {
            baseReportFieldTemp = baseReportFieldDao.getSingleObject(baseReportField.getId());
            //如果没有找到相应信息，则表示没有相应的数据，不进行更新处
            if (baseReportFieldTemp != null) {
                BeanUtils.copyProperties(baseReportField, baseReportFieldTemp);
                baseReportFieldTemp.setBisEnable(baseReportField.getBisEnable() == null ? false : baseReportField.getBisEnable());
                if (!baseReportFieldDao.updateObject(baseReportFieldTemp)) {
                    throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
                }
            } else {
                throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
            }

        } else {
            baseReportFieldTemp = new BaseReportField();
            BeanUtils.copyProperties(baseReportField, baseReportFieldTemp);
            baseReportFieldTemp.setBisEnable(baseReportField.getBisEnable() == null ? false : baseReportField.getBisEnable());
            baseReportFieldTemp.setBisDelete(false);
            baseReportFieldTemp.setCreator(processControllerComponent.getThisUser());
            if (!baseReportFieldDao.addObject(baseReportFieldTemp)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BaseReportField.class),baseReportFieldTemp.getId());
        }
    }
    //endregion

    //region 删除数据

    /**
     * 删除数据
     *
     * @param id
     */
    public void delReportField(Integer id) throws BusinessException {
        BaseReportField baseReportField = baseReportFieldDao.getSingleObject(id);
        if (baseReportField != null) {
            baseReportField.setBisDelete(true);
            if (!baseReportFieldDao.updateObject(baseReportField)) {
                throw new BusinessException(HttpReturnEnum.DELETEFAIL.getName());
            }
        }
    }
    //endregion

    public List<BaseReportField> getReportFieldListByPid(Integer pid) {
        return baseReportFieldDao.getEnableListByPid(pid);
    }

    public List<BaseReportField> getReportFieldList(String fieldName) {
        return baseReportFieldDao.getEnableList(fieldName);
    }

    public List<BaseReportField> getListObject(String fieldName, String name){
        return baseReportFieldDao.getListObject(fieldName, name);
    }


    public BaseReportField getReportFieldById(Integer id) {
        return baseReportFieldDao.getSingleObject(id);
    }

    /**
     * 获取字典的数据层次
     *
     * @param id
     * @return
     */
    public KeyValueDto getReportFieldLevel(Integer id) {
        KeyValueDto keyValueDto = new KeyValueDto();
        BaseReportField baseReportField = getReportFieldById(id);
        BaseReportField subBaseReportField = getReportFieldById(baseReportField.getPid());
        if (subBaseReportField != null && subBaseReportField.getId() != null) {
            getReportFieldLevelRecursion(keyValueDto, subBaseReportField.getId());
        }
        keyValueDto.setKey(String.valueOf(baseReportField.getId()));
        keyValueDto.setValue(baseReportField.getName());
        return keyValueDto;
    }

    private void getReportFieldLevelRecursion(KeyValueDto keyValueDto, Integer id) {
        BaseReportField baseReportField = getReportFieldById(id);
        if (baseReportField != null && baseReportField.getId() != null) {
            KeyValueDto kv = new KeyValueDto();
            BaseReportField subBaseReportField = getReportFieldById(baseReportField.getPid());
            if (subBaseReportField != null && subBaseReportField.getId() != null) {
                getReportFieldLevelRecursion(kv, subBaseReportField.getId());
            }
            kv.setKey(String.valueOf(baseReportField.getId()));
            kv.setValue(baseReportField.getName());
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
        List<BaseReportField> baseReportFields = getReportFieldList(fieldName);
        BaseReportField baseReportField = baseReportFields.get(0);
        TreeViewVo treeViewVo = new TreeViewVo();
        if (baseReportField != null) {
            treeViewVo.setId(baseReportField.getId());
            treeViewVo.setText(baseReportField.getName());
            treeViewVo.setpId(0);
            treeViewVo.setpName("");
            treeViewVo.setLevel("1");
            treeViewVo.setNodes(getTreeView(baseReportField.getId(), treeViewVo.getLevel()));
        }
        return treeViewVo;
    }

    private List<TreeViewVo> getTreeView(Integer pid, String parentLevel) {
        TreeViewVo treeViewVo;
        List<BaseReportField> baseReportFields = getReportFieldListByPid(pid);
        List<TreeViewVo> treeViewVos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(baseReportFields)) {
            for (BaseReportField item : baseReportFields) {
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
        if (pid == null) {
            return Lists.newArrayList();
        }
        List<BaseReportField> baseReportFieldList = getReportFieldListByPid(pid);
        if (CollectionUtils.isEmpty(baseReportFieldList)) {
            return Lists.newArrayList();
        }
        return LangUtils.transform(baseReportFieldList, p -> {
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
        if (StringUtils.isBlank(name)) {
            return Lists.newArrayList();
        }
        List<BaseReportField> dataDicList = baseReportFieldDao.getListObject(null, name);
        if (CollectionUtils.isEmpty(dataDicList)) {
            return Lists.newArrayList();
        }
        return LangUtils.transform(dataDicList, p -> {
            return getZtreeDto(p);
        });
    }

    public List<ZtreeDto> getBaseDicByKey(String key) {
        List<BaseReportField> baseReportFields = getReportFieldList(key);
        BaseReportField baseReportField = baseReportFields.get(0);
        if (baseReportField == null) {
            return null;
        }
        return Lists.newArrayList(getZtreeDto(baseReportField));
    }

    private ZtreeDto getZtreeDto(BaseReportField baseReportField) {
        ZtreeDto ztreeDto = new ZtreeDto();
        ztreeDto.setId(baseReportField.getId());
        ztreeDto.setPid(baseReportField.getPid());
        ztreeDto.setName(baseReportField.getName());
        List<BaseReportField> dataDics = getReportFieldListByPid(baseReportField.getId());
        ztreeDto.setIsParent(CollectionUtils.isNotEmpty(dataDics));
        return ztreeDto;
    }

    /**
     * 根据id获取显示的名称
     *
     * @param id
     * @return
     */
    public String getNameById(Integer id) {
        if (id == null) {
            return "";
        }
        BaseReportField baseReportField = baseReportFieldDao.getSingleObject(id);
        if (baseReportField == null) {
            return "";
        }
        return baseReportField.getName();
    }


}
