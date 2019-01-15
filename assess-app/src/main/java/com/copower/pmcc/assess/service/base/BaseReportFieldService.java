package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.dal.basis.dao.base.BaseReportFieldDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportField;
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

    //region 获取数据字典列表

    /**
     * 获取数据字典列表
     *
     * @return
     */
    public BootstrapTableVo getDataDicListVo(String fieldName, String name) {
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
    public BootstrapTableVo getDataDicListByPidVo(Integer pid) {
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
    public void saveDataDic(BaseReportField baseReportField) throws BusinessException {
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
    public void delDataDic(Integer id) throws BusinessException {
        BaseReportField baseReportField = baseReportFieldDao.getSingleObject(id);
        if (baseReportField != null) {
            baseReportField.setBisDelete(true);
            if (!baseReportFieldDao.updateObject(baseReportField)) {
                throw new BusinessException(HttpReturnEnum.DELETEFAIL.getName());
            }
        }
    }
    //endregion

    public List<BaseReportField> getDataDicListByPid(Integer pid) {
        return baseReportFieldDao.getEnableListByPid(pid);
    }

    public List<BaseReportField> getDataDicList(String fieldName) {
        return baseReportFieldDao.getEnableList(fieldName);
    }

    public List<BaseReportField> getListObject(String fieldName, String name){
        return baseReportFieldDao.getListObject(fieldName, name);
    }

    public BaseReportField getDataDicById(Integer id) {
        return baseReportFieldDao.getSingleObject(id);
    }

    /**
     * 获取字典的数据层次
     *
     * @param id
     * @return
     */
    public KeyValueDto getDataDicLevel(Integer id) {
        KeyValueDto keyValueDto = new KeyValueDto();
        BaseReportField baseReportField = getDataDicById(id);
        BaseReportField subBaseReportField = getDataDicById(baseReportField.getPid());
        if (subBaseReportField != null && subBaseReportField.getId() != null) {
            getDataDicLevelRecursion(keyValueDto, subBaseReportField.getId());
        }
        keyValueDto.setKey(String.valueOf(baseReportField.getId()));
        keyValueDto.setValue(baseReportField.getName());
        return keyValueDto;
    }

    private void getDataDicLevelRecursion(KeyValueDto keyValueDto, Integer id) {
        BaseReportField baseReportField = getDataDicById(id);
        if (baseReportField != null && baseReportField.getId() != null) {
            KeyValueDto kv = new KeyValueDto();
            BaseReportField subBaseReportField = getDataDicById(baseReportField.getPid());
            if (subBaseReportField != null && subBaseReportField.getId() != null) {
                getDataDicLevelRecursion(kv, subBaseReportField.getId());
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
        List<BaseReportField> baseDataDics = getDataDicList(fieldName);
        BaseReportField baseDataDic = baseDataDics.get(0);
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
        List<BaseReportField> baseDataDics = getDataDicListByPid(pid);
        List<TreeViewVo> treeViewVos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(baseDataDics)) {
            for (BaseReportField item : baseDataDics) {
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
        List<BaseReportField> baseDataDicList = getDataDicListByPid(pid);
        if (CollectionUtils.isEmpty(baseDataDicList)) {
            return Lists.newArrayList();
        }
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
        List<BaseReportField> baseDataDics = getDataDicList(key);
        BaseReportField baseDataDic = baseDataDics.get(0);
        if (baseDataDic == null) {
            return null;
        }
        return Lists.newArrayList(getZtreeDto(baseDataDic));
    }

    private ZtreeDto getZtreeDto(BaseReportField baseDataDic) {
        ZtreeDto ztreeDto = new ZtreeDto();
        ztreeDto.setId(baseDataDic.getId());
        ztreeDto.setPid(baseDataDic.getPid());
        ztreeDto.setName(baseDataDic.getName());
        List<BaseReportField> dataDics = getDataDicListByPid(baseDataDic.getId());
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
        BaseReportField baseDataDic = baseReportFieldDao.getSingleObject(id);
        if (baseDataDic == null) {
            return "";
        }
        return baseDataDic.getName();
    }

    /**
     * 从现有集合中根据名称找出对应数据
     *
     * @param list
     * @param name
     * @return
     */
    public BaseReportField getDataDicByName(List<BaseReportField> list, String name) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (StringUtils.isBlank(name)) {
            return null;
        }
        for (BaseReportField baseDataDic : list) {
            if (StringUtils.equals(baseDataDic.getName().trim(), name.trim())) {
                return baseDataDic;
            }
        }
        return null;
    }


}
