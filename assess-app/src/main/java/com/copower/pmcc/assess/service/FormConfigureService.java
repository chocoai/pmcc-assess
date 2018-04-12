package com.copower.pmcc.assess.service;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.constant.CrmCacheConstant;
import com.copower.pmcc.assess.dal.dao.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.BaseFormDao;
import com.copower.pmcc.assess.dal.dao.BaseProcessDao;
import com.copower.pmcc.assess.dal.dao.FormConfigureDao;
import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dto.input.FormConfigureDetailDto;
import com.copower.pmcc.assess.dto.input.FormConfigureDto;
import com.copower.pmcc.assess.dto.output.FormConfigureFieldVo;
import com.copower.pmcc.erp.api.dto.DynamicFormField;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.CustomTableTypeEnum;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FileUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.CacheConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kings on 2018-3-21.
 */
@Service
public class FormConfigureService {
    @Autowired
    private BaseFormDao hrBaseFormDao;
    @Autowired
    private BaseProcessDao hrProcessDao;
    @Autowired
    private FormConfigureDao formConfigureDao;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private BaseProcessService hrBaseProcessService;
    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private BaseAttachmentDao hrBaseAttachmentDao;

    /**
     * 获取表单信息列表
     *
     * @return
     */
    public BootstrapTableVo getFormList() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseForm> baseFormList = hrBaseFormDao.getBaseForm(requestBaseParam.getSearch());
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(baseFormList) ? new ArrayList<BaseForm>() : baseFormList);
        return bootstrapTableVo;
    }

    /**
     * 获取表单信息列表
     *
     * @return
     */
    public BootstrapTableVo getFormModularList(String formName) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseFormList> baseFormList = hrBaseFormDao.getbaseFormList(formName);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(baseFormList) ? new ArrayList<BaseFormList>() : baseFormList);
        return bootstrapTableVo;
    }

    /**
     * 获取字段信息列表
     *
     * @return
     */
    public BootstrapTableVo getFormModularFieldList(Integer formListId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseFormListField> baseFormList = hrBaseFormDao.getBaseFormListFields(formListId);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(baseFormList) ? new ArrayList<BaseFormListField>() : baseFormList);
        return bootstrapTableVo;
    }

    /**
     * 根据数据名称获取所有表数据
     *
     * @return
     */
    public List<KeyValueDto> getTableList() {
        List<KeyValueDto> tableList = formConfigureDao.getDbTableList(BaseConstant.CURRENT_DATABASE_NAME);
        return tableList;
    }

    /**
     * 根据表名称获取字段信息
     *
     * @return
     */
    public List<KeyValueDto> getFieldList(String tableName) {
        List<KeyValueDto> fieldList = formConfigureDao.getTableFieldList(BaseConstant.CURRENT_DATABASE_NAME, tableName);
        return fieldList;
    }

    /**
     * 保存字段数据
     *
     * @param hrBaseFormListFieldDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveFormListField(BaseFormListField hrBaseFormListFieldDto) throws BusinessException {
        BaseFormListField hrBaseFormListField = null;
        if (hrBaseFormListFieldDto.getId() != null && hrBaseFormListFieldDto.getId() > 0) {
            hrBaseFormListField = hrBaseFormDao.getBaseFormListField(hrBaseFormListFieldDto.getId());
            if (hrBaseFormListField != null)//如果没有找到相应信息，则表示没有相应的数据，不进行更新处
            {
                try {
                    BeanUtils.copyProperties(hrBaseFormListFieldDto, hrBaseFormListField);
                } catch (Exception e) {
                    throw new BusinessException(HttpReturnEnum.COPYFAIL.getName(), e);
                }
                if (!hrBaseFormDao.updateBaseFormListField(hrBaseFormListField)) {
                    throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
                }
            } else {
                throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
            }
        } else {
            hrBaseFormListField = new BaseFormListField();
            try {
                BeanUtils.copyProperties(hrBaseFormListFieldDto, hrBaseFormListField);
            } catch (Exception e) {
                throw new BusinessException(HttpReturnEnum.COPYFAIL.getName(), e);
            }
            if (!hrBaseFormDao.addBaseFormListField(hrBaseFormListField)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        }
    }


    /**
     * 删除字段信息
     *
     * @param id
     */
    public void deleteFormListField(Integer id) throws BusinessException {
        BaseFormListField formListField = hrBaseFormDao.getBaseFormListField(id);
        if (formListField == null) {
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
        }
        formListField.setBisDelete(true);
        hrBaseFormDao.updateBaseFormListField(formListField);
    }

    /**
     * 获取表单html
     *
     * @param formName
     * @param formListName
     * @param readOnly
     * @return
     */
    public String getDynamicFormHtml(String formName, String formListName, Boolean readOnly, String jsonValue) {
        List<BaseFormList> hrbaseFormList = hrBaseFormDao.getbaseFormList(formName, formListName);
        if (CollectionUtils.isNotEmpty(hrbaseFormList)) {
            List<BaseFormListField> hrBaseFormListFields = hrBaseFormDao.getBaseFormListFields(hrbaseFormList.get(0).getId());
            String s = getFormHtmlString(hrbaseFormList.get(0).getTableName(), readOnly, jsonValue, hrBaseFormListFields);
        }
        return "";
    }

    public String getDynamicFormHtml(Integer formListId, Boolean readOnly, String jsonValue) {
        BaseFormList hrbaseFormList = hrBaseFormDao.getBaseFormList(formListId);
        if (hrbaseFormList != null) {
            List<BaseFormListField> hrBaseFormListFields = hrBaseFormDao.getBaseFormListFields(hrbaseFormList.getId());
            String s = getFormHtmlString(hrbaseFormList.getTableName(), readOnly, jsonValue, hrBaseFormListFields);
            if (s != null) return s;
        }
        return "";
    }

    /**
     * 缓存中获取数据源
     *
     * @param sql
     * @return
     */
    public List<KeyValueDto> getCacheDdlDataSource(String sql) {
        String cacheKey = CacheConstant.getCostsKeyPrefix(CrmCacheConstant.PMCC_ASSESS_DYNAMIC_FORM_DATASOURCE, sql);
        try {
            List<KeyValueDto> keyValueDtos = LangUtils.listCache(cacheKey, sql, KeyValueDto.class, input -> {
                return formConfigureDao.getDdlDataSource(sql);
            });
            return keyValueDtos;
        } catch (Exception e) {
            return formConfigureDao.getDdlDataSource(sql);
        }
    }

    /**
     * 缓存中获取值
     *
     * @param sql
     * @return
     */
    public String getCacheText(String sql) {
        String cacheKey = CacheConstant.getCostsKeyPrefix(CrmCacheConstant.PMCC_ASSESS_DYNAMIC_FORM_TEXT, sql);
        try {
            String text = LangUtils.singleCache(cacheKey, sql, String.class, p -> {
                return formConfigureDao.getText(sql);
            });
            return text;
        } catch (Exception e) {
            return formConfigureDao.getText(sql);
        }
    }

    private String getFormHtmlString(String tableName, Boolean readOnly, String jsonValue, List<BaseFormListField> hrBaseFormListFields) {
        if (CollectionUtils.isNotEmpty(hrBaseFormListFields)) {
            jsonValue = jsonContentOut(hrBaseFormListFields, jsonValue);
            Map<String, Object> map = jsonStringToMap(jsonValue);
            map = map == null ? Maps.newHashMap() : map;
            List<DynamicFormField> transform = Lists.newArrayList();

            //为自定义控件提供的相关值
            Map<String, Object> customMap = Maps.newHashMap();
            customMap.put("tableName", tableName);
            customMap.put("tableId", map.containsKey("id") ? map.get("id").toString() : "0");
            customMap.put("userAccount", serviceComponent.getThisUser());
            SysUserDto sysUser = erpRpcUserService.getSysUser(serviceComponent.getThisUser());
            sysUser = sysUser == null ? new SysUserDto() : sysUser;
            customMap.put("userAccountName", sysUser.getUserName());
            for (BaseFormListField o : hrBaseFormListFields) {
                DynamicFormField dynamicFormField = new DynamicFormField();
                dynamicFormField.setFormField(Boolean.TRUE == o.getBisJson() ? o.getJsonName() : o.getName());
                dynamicFormField.setFormFieldId(String.format("%s-%s", tableName, dynamicFormField.getFormField()));
                dynamicFormField.setLabelName(o.getDisplayName());
                dynamicFormField.setFieldType(o.getFieldType().toString());
                dynamicFormField.setBisRequired(o.getBisRequired());
                dynamicFormField.setValueLength(o.getFieldLength());
                dynamicFormField.setDisplaySort(o.getSorting());
                dynamicFormField.setDefaultValue(o.getDefaultValue());

                customMap.put("formField", dynamicFormField.getFormField());
                customMap.put("formFieldId", dynamicFormField.getFormFieldId());
                customMap.put("labelName", dynamicFormField.getLabelName());
                customMap.put("value", map.containsKey(dynamicFormField.getFormField()) ? map.get(dynamicFormField.getFormField()).toString() : "");
                if (readOnly) {//数据显示
                    if (StringUtils.isNotBlank(o.getCustomDisplayUrl())) {
                        String realPath = servletContext.getRealPath(o.getCustomDisplayUrl());
                        String fileContent = FileUtils.getFileContent(realPath, "UTF-8");
                        fileContent = getRegexString(customMap, "#\\{(.*?)\\}", fileContent);
                        dynamicFormField.setCustomHtml(fileContent);
                    }
                } else {//新增或编辑
                    //自定义控件数据录入
                    if (StringUtils.isNotBlank(o.getCustomUrl())) {
                        String realPath = servletContext.getRealPath(o.getCustomUrl());
                        String fileContent = FileUtils.getFileContent(realPath, "UTF-8");
                        fileContent = getRegexString(customMap, "#\\{(.*?)\\}", fileContent);
                        dynamicFormField.setCustomHtml(fileContent);
                    }
                    //有数据源先设置数据源
                    if (StringUtils.isNotBlank(o.getDataSourceSql())) {
                        String sql = o.getDataSourceSql();
                        List<KeyValueDto> dataSource = Boolean.TRUE == o.getBisCacheDataSource() ? getCacheDdlDataSource(sql) : formConfigureDao.getDdlDataSource(sql);
                        dynamicFormField.setData(dataSource);
                    }
                }
                //设置数据的初始值或显示值
                if (StringUtils.isNotBlank(jsonValue) && StringUtils.isNotBlank(o.getDataViewSql())) {
                    String viewSql = o.getDataViewSql();
                    //根据参数处理一下sql
                    viewSql = getRegexString(map, "#\\{(.*?)\\}", viewSql);
                    String text = Boolean.TRUE == o.getBisCacheDataView() ? getCacheText(viewSql) : formConfigureDao.getText(viewSql);
                    CustomTableTypeEnum customTableTypeEnum = CustomTableTypeEnum.getCustomTypeByColumnsPrefix(o.getFieldType());
                    if (customTableTypeEnum != null) {
                        switch (customTableTypeEnum) {
                            case RADIOUSER:
                                text = text + "_" + map.get(dynamicFormField.getFormField());
                                break;
                        }
                    }
                    map.put(dynamicFormField.getFormField() + "_name", text);
                    customMap.put("text", text);
                }
                transform.add(dynamicFormField);
            }

            String s = erpRpcToolsService.buildDynamicForm(transform, readOnly, mapToJsonString(map));
            return s;
        }
        return null;
    }


    public String getDynamicFormHtml(String formName, String formListName) {
        return getDynamicFormHtml(formName, formListName, false, "");
    }

    /**
     * 根据key获取字段数据
     *
     * @param formName
     * @param formListName
     * @return
     */
    public List<BaseFormListField> getListFields(String formName, String formListName) {
        List<BaseFormList> hrbaseFormList = hrBaseFormDao.getbaseFormList(formName, formListName);
        if (CollectionUtils.isNotEmpty(hrbaseFormList)) {
            List<BaseFormListField> hrBaseFormListFields = hrBaseFormDao.getBaseFormListFields(hrbaseFormList.get(0).getId());
            return hrBaseFormListFields;
        }
        return null;
    }

    public List<BaseFormListField> getListFields(Integer formListId) {
        List<BaseFormListField> hrBaseFormListFields = hrBaseFormDao.getBaseFormListFields(formListId);
        return hrBaseFormListFields;
    }

    /**
     * 根据key获取列表显示字段数据
     *
     * @param formName
     * @param formListName
     * @return
     */
    public List<KeyValueDto> getListFieldsShow(String formName, String formListName) {
        List<KeyValueDto> keyValueDtoList = Lists.newArrayList();
        List<BaseFormListField> listFields = getListFields(formName, formListName);
        if (CollectionUtils.isNotEmpty(listFields)) {
            keyValueDtoList = LangUtils.transform(listFields, p -> {
                KeyValueDto keyValueDto = new KeyValueDto();
                keyValueDto.setKey(p.getName());
                keyValueDto.setValue(p.getDisplayName());
                return keyValueDto;
            });
        }
        return keyValueDtoList;
    }

    public List<FormConfigureFieldVo> getListFieldsShow(Integer formListId) {
        List<FormConfigureFieldVo> fieldVos = Lists.newArrayList();
        List<BaseFormListField> listFields = getListFields(formListId);
        if (CollectionUtils.isNotEmpty(listFields)) {
            for (BaseFormListField listField : listFields) {
                if (Boolean.TRUE == listField.getBisListShow()) {
                    FormConfigureFieldVo fieldVo = new FormConfigureFieldVo();
                    fieldVo.setName(Boolean.TRUE == listField.getBisJson() ? listField.getJsonName() : listField.getName());
                    fieldVo.setTitle(listField.getDisplayName());
                    if (StringUtils.isNotBlank(listField.getDataViewSql())) {
                        fieldVo.setName(fieldVo.getName() + "_name");
                    }
                    fieldVos.add(fieldVo);
                }
            }
        }
        return fieldVos;
    }


    /**
     * 将json字段的数据提取到json字段中
     *
     * @param jsonString
     * @param hrBaseFormListFields
     * @return
     */
    public String jsonContentIn(List<BaseFormListField> hrBaseFormListFields, String jsonString) {
        if (CollectionUtils.isEmpty(hrBaseFormListFields))
            return jsonString;
        if (StringUtils.isBlank(jsonString))
            return jsonString;
        HashMap<String, List<String>> map = new HashMap<>();
        fieldToMap(hrBaseFormListFields, map);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if (map.size() > 0) {
            for (Map.Entry<String, List<String>> stringListEntry : map.entrySet()) {
                if (CollectionUtils.isNotEmpty(stringListEntry.getValue())) {
                    JSONObject json = new JSONObject();//原有的加新内容的
                    for (String s : stringListEntry.getValue()) {
                        if (jsonObject.containsKey(s)) {
                            JSONObject jo = jsonObject.getJSONObject(stringListEntry.getKey());//取得原有内容
                            json.put(s, jsonObject.getString(s));
                            if (jo != null)
                                json.putAll(jsonObjectToMap(jo));
                            jsonObject.remove(s);
                        }
                    }
                    jsonObject.put(stringListEntry.getKey(), json.toJSONString());
                }
            }
        }
        return jsonObject.toJSONString();
    }

    /**
     * 将json字段的数据提取出来
     *
     * @param jsonString
     * @param hrBaseFormListFields
     * @return
     */
    public String jsonContentOut(List<BaseFormListField> hrBaseFormListFields, String jsonString) {
        if (CollectionUtils.isEmpty(hrBaseFormListFields))
            return jsonString;
        if (StringUtils.isBlank(jsonString))
            return jsonString;
        HashMap<String, List<String>> map = new HashMap<>();
        fieldToMap(hrBaseFormListFields, map);

        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        if (map.size() > 0) {
            for (Map.Entry<String, List<String>> stringListEntry : map.entrySet()) {
                if (jsonObject.containsKey(stringListEntry.getKey())) {
                    String jsonObjectString = jsonObject.getString(stringListEntry.getKey());
                    if (StringUtils.isNotBlank(jsonObjectString)) {
                        JSONObject json = JSONObject.parseObject(jsonObjectString);
                        jsonObject.remove(stringListEntry.getKey());
                        for (Map.Entry<String, Object> entry : json.entrySet()) {
                            jsonObject.put(entry.getKey(), entry.getValue());
                        }
                    }
                }
            }
        }
        return jsonObject.toJSONString();
    }

    private void fieldToMap(List<BaseFormListField> hrBaseFormListFields, HashMap<String, List<String>> map) {
        for (BaseFormListField hrBaseFormListField : hrBaseFormListFields) {
            if (hrBaseFormListField.getBisJson()) {
                List<String> list = null;
                if (map.containsKey(hrBaseFormListField.getName())) {
                    list = map.get(hrBaseFormListField.getName());
                    list.add(hrBaseFormListField.getJsonName());
                } else {
                    list = Lists.newArrayList();
                    list.add(hrBaseFormListField.getJsonName());
                    map.put(hrBaseFormListField.getName(), list);
                }
            }
        }
    }

    /**
     * 获取流程节点下配置的所有字段信息
     *
     * @param process
     * @param activityName
     * @return
     */
    public List<BaseFormListField> getFieldsByProcessInfo(String process, String activityName) {
        List<BaseProcessForm> hrProcessFormList = hrBaseProcessService.getProcessFormList(process, activityName);
        if (CollectionUtils.isEmpty(hrProcessFormList)) return Lists.newArrayList();
        List<BaseFormListField> listFields = Lists.newArrayList();
        for (BaseProcessForm hrBaseProcessForm : hrProcessFormList) {
            List<BaseFormListField> listFieldList = getListFields(hrBaseProcessForm.getProcess(), hrBaseProcessForm.getName());
            if (CollectionUtils.isNotEmpty(listFieldList))
                listFields.addAll(listFieldList);
        }
        return listFields;
    }

    /**
     * 获取单条数据
     *
     * @param tableName
     * @param tableId
     * @return
     */
    public Map<String, Object> getObjectSingle(String tableName, Integer tableId) {
        return formConfigureDao.getObjectSingle(tableName, tableId);
    }

    public Map<String, Object> getObjectSingle(String sql, Object[] args) {
        return formConfigureDao.getObjectSingle(sql, args);
    }

    public Map<String, Object> getObjectSingle(String tableName, String processInsId) {
        return formConfigureDao.getObjectSingle(tableName, processInsId);
    }

    /**
     * 保存数据
     *
     * @param tableName
     * @param map
     * @return
     */
    public Integer addObject(String tableName, Map<String, Object> map) throws BusinessException {
        if (StringUtils.isBlank(tableName))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (map == null || map.size() <= 0)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        Integer tableId = 0;
        map.put("creator", serviceComponent.getThisUser());
        tableId = formConfigureDao.addObject(tableName, map);
        return tableId;
    }

    /**
     * 更新数据
     *
     * @param id
     * @param formListFields
     * @param jsonObject
     */
    public Integer updateObject(String tableName, Integer id, List<BaseFormListField> formListFields, JSONObject jsonObject) throws BusinessException {
        //1.主要是生成更新的sql，如果是正常字段直接生成sql，单如果是json字段内容更新则需单独处理
        //2.循环遍历提交的数据，判断每个字段数据是正常还是json数据
        if (id == null || id <= 0)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (CollectionUtils.isEmpty(formListFields))
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (jsonObject == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        Map<String, Object> objectMap = jsonObjectToMap(jsonObject);
        formConfigureDao.removeInvalidField(tableName, objectMap);
        StringBuilder stringBuilder = new StringBuilder();
        if (objectMap != null && objectMap.size() > 0) {
            for (Map.Entry<String, Object> stringObjectEntry : objectMap.entrySet()) {
                for (BaseFormListField formListField : formListFields) {
                    if (formListField.getBisJson()) {
                        if (formListField.getJsonName().equals(stringObjectEntry.getKey())) {
                            stringBuilder.append(String.format("%s=JSON_SET(%s,'$.%s','%s'),", formListField.getName(), formListField.getName(), formListField.getJsonName(), stringObjectEntry.getValue()));
                        }
                    } else {
                        if (formListField.getName().equals(stringObjectEntry.getKey())) {
                            stringBuilder.append(String.format("%s='%s',", formListField.getName(), stringObjectEntry.getValue()));
                        }
                    }
                }
            }
        }
        String setString = StringUtils.strip(stringBuilder.toString(), ",");
        if (StringUtils.isNotBlank(setString)) {
            String sql = String.format("update %s set %s where id=%s", tableName, setString, id);
            formConfigureDao.updateObject(sql);
        }
        return id;
    }


    /**
     * map转Json字符串
     *
     * @param map
     * @return
     */
    public String mapToJsonString(Map<String, Object> map) {
        if (map == null) return "";
        JSONObject jsonObject = new JSONObject();
        if (map.size() > 0) {
            jsonObject.putAll(map);
        }
        return jsonObject.toJSONString();
    }

    /**
     * Json字符串转Map
     *
     * @param json
     * @return
     */
    public Map<String, Object> jsonStringToMap(String json) {
        if (StringUtils.isBlank(json)) return null;
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObjectToMap(jsonObject);
    }

    /**
     * jsonObject转Map字符串
     *
     * @param jsonObject
     * @return
     */
    public Map<String, Object> jsonObjectToMap(JSONObject jsonObject) {
        Map<String, Object> map = Maps.newHashMap();
        if (jsonObject.entrySet().size() > 0) {
            for (Map.Entry<String, Object> stringObjectEntry : jsonObject.entrySet()) {
                map.put(stringObjectEntry.getKey(), stringObjectEntry.getValue());
            }
        }
        return map;
    }

    /**
     * 保存从表数据
     *
     * @param tableName
     * @param tableId
     * @param formData
     */
    public void saveDetailInfo(String tableName, Integer tableId, Integer formListId, String formData) throws BusinessException {
        List<BaseFormListField> formListFields = hrBaseFormDao.getBaseFormListFields(formListId);
        if (tableId != null && tableId > 0) {
            updateObject(tableName, tableId, formListFields, JSONObject.parseObject(formData));
        } else {
            formData = jsonContentIn(formListFields, formData);
            JSONObject jsonObject = JSONObject.parseObject(formData);
            Map<String, Object> map = jsonObjectToMap(jsonObject);
            tableId = addObject(tableName, map);
            //更新附件tableId
            updateAttachmentTableId(tableName, tableId);
        }
    }

    /**
     * 删除从表数据
     *
     * @param tableName
     * @param tableId
     */
    public void deleteDetailInfo(String tableName, Integer tableId) {
        formConfigureDao.deleteObject(tableName, tableId);
    }

    /**
     * 获取字段信息列表
     *
     * @return
     */
    public BootstrapTableVo getDetailInfoList(String tableName, String foreignKeyName, Integer foreignKeyValue, Integer formListId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        String sql = String.format("select * from %s where %s=%s", tableName, foreignKeyName, foreignKeyValue);
        List<Map<String, Object>> mapList = formConfigureDao.getObjectList(sql, requestBaseParam.getOffset(), requestBaseParam.getLimit());
        //处理json字段的内容
        List<Map<String, Object>> maps = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(mapList)) {
            List<BaseFormListField> formListFields = hrBaseFormDao.getBaseFormListFields(formListId);
            for (Map<String, Object> p : mapList) {
                Map<String, Object> map = jsonStringToMap(jsonContentOut(formListFields, mapToJsonString(p)));
                Map<String, Object> tempMap = Maps.newHashMap();

                for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
                    for (BaseFormListField item : formListFields) {
                        String fieldName = Boolean.TRUE == item.getBisJson() ? item.getJsonName() : item.getName();
                        if (stringObjectEntry.getKey().equals(fieldName)) {
                            CustomTableTypeEnum customTableTypeEnum = CustomTableTypeEnum.getCustomTypeByColumnsPrefix(item.getFieldType());
                            if (customTableTypeEnum != null) {
                                switch (customTableTypeEnum) {
                                    case DATE:
                                        map.put(fieldName, DateUtils.formatDate(DateUtils.convertDate(map.get(fieldName).toString()),"yyyy-MM-dd"));
                                        break;
                                    case DATETIME:
                                        map.put(fieldName, DateUtils.formatDateToYMDHMS(DateUtils.convertDate(map.get(fieldName).toString())));
                                        break;
                                }
                            }

                            if (StringUtils.isNotBlank(item.getDataViewSql())) {
                                String viewSql = item.getDataViewSql();
                                viewSql = getRegexString(map, "#\\{(.*?)\\}", viewSql);
                                String text = Boolean.TRUE == item.getBisCacheDataView() ? getCacheText(viewSql) : formConfigureDao.getText(viewSql);
                                if (customTableTypeEnum != null) {
                                    switch (customTableTypeEnum) {
                                        case RADIOUSER:
                                            text = text + "_" + map.get(fieldName);
                                            break;
                                    }
                                }
                                tempMap.put(fieldName + "_name", text);
                            }
                        }
                    }
                }
                map.putAll(tempMap);
                maps.add(map);
            }
        }
        bootstrapTableVo.setTotal(formConfigureDao.getObjectCount(sql));
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(maps) ? Lists.newArrayList() : maps);
        return bootstrapTableVo;
    }

    private String getRegexString(Map<String, Object> map, String regex, String source) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            String group = matcher.group(0);
            String group1 = matcher.group(1);
            String s = new String();
            if (map != null && map.containsKey(group1))
                s = map.get(group1).toString();
            source = source.replace(group, s);
        }
        return source;
    }

    /**
     * 保存数据
     *
     * @param formConfigureDto
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer saveData(FormConfigureDto formConfigureDto) throws BusinessException {
        //处理数据 先将同表数据融合 再剥离出json字段数据
        // 检查主表id 确定信息为新增或编辑，
        //如果为新增 则先保存主表信息 再更新主表相关附件id 设置关联表的外键 保存关联表数据 更新关联表附件id 更新所有子表的外键id
        Integer primaryId = formConfigureDto.getPrimaryId();
        //1.同表数据融合 记录同表所配置的所有字段
        List<FormConfigureDetailDto> singelFormList = formConfigureDto.getSingelFormList();
        List<FormConfigureDetailDto> resultDataList = Lists.newArrayList();
        Map<String, List<Integer>> resultTableField = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(singelFormList)) {
            HashSet<String> hashSet = Sets.newHashSet();
            for (FormConfigureDetailDto formConfigureDetailDto : singelFormList) {
                hashSet.add(formConfigureDetailDto.getTableName());
            }
            for (String s : hashSet) {
                FormConfigureDetailDto detailDto = new FormConfigureDetailDto();
                for (FormConfigureDetailDto formConfigureDetailDto : singelFormList) {
                    if (StringUtils.equals(s, formConfigureDetailDto.getTableName())) {
                        detailDto.setTableName(formConfigureDetailDto.getTableName());
                        detailDto.setTableId(formConfigureDetailDto.getTableId());
                        detailDto.setFormListId(formConfigureDetailDto.getFormListId());
                        if (StringUtils.isNotBlank(detailDto.getFormData())) {
                            JSONObject jsonObject = JSONObject.parseObject(formConfigureDetailDto.getFormData());
                            JSONObject jo = JSONObject.parseObject(detailDto.getFormData());
                            jo.putAll(jsonObjectToMap(jsonObject));
                            detailDto.setFormData(jo.toJSONString());

                            List<Integer> integers = resultTableField.get(formConfigureDetailDto.getTableName());
                            if (CollectionUtils.isEmpty(integers))
                                integers = Lists.newArrayList(formConfigureDetailDto.getFormListId());
                            else
                                integers.add(formConfigureDetailDto.getFormListId());
                            resultTableField.put(formConfigureDetailDto.getTableName(), integers);
                        } else {
                            detailDto.setFormData(formConfigureDetailDto.getFormData());
                            resultTableField.put(formConfigureDetailDto.getTableName(), Lists.newArrayList(formConfigureDetailDto.getFormListId()));
                        }
                    }
                }
                resultDataList.add(detailDto);
            }

            //2.剥离json字段
            for (FormConfigureDetailDto detailDto : singelFormList) {
                for (FormConfigureDetailDto formConfigureDetailDto : resultDataList) {
                    if (detailDto.getTableName().equals(formConfigureDetailDto.getTableName())) {
                        List<BaseFormListField> formListFields = hrBaseFormDao.getBaseFormListFields(detailDto.getFormListId());
                        formConfigureDetailDto.setFormData(jsonContentIn(formListFields, formConfigureDetailDto.getFormData()));
                    }
                }
            }
        }else{//只有从表的情况
            FormConfigureDetailDto detailDto = new FormConfigureDetailDto();
            detailDto.setTableId(primaryId);
            detailDto.setTableName(formConfigureDto.getPrimaryTableName());
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("phase_id",formConfigureDto.getPhaseId());
            jsonObject.put("id",primaryId);
            detailDto.setFormData(jsonObject.toJSONString());
            resultDataList.add(detailDto);
        }




        if (primaryId != null && primaryId > 0) {//编辑
            for (FormConfigureDetailDto formConfigureDetailDto : resultDataList) {
                if (formConfigureDetailDto.getTableId() != null && formConfigureDetailDto.getTableId() > 0) {
                    if(!resultTableField.isEmpty()){
                        List<BaseFormListField> formListFields = hrBaseFormDao.getBaseFormListFields(resultTableField.get(formConfigureDetailDto.getTableName()));
                        String jsonContentOut = jsonContentOut(formListFields, formConfigureDetailDto.getFormData());
                        JSONObject jsonObject = JSONObject.parseObject(jsonContentOut);
                        updateObject(formConfigureDetailDto.getTableName(), formConfigureDetailDto.getTableId(), formListFields, jsonObject);
                    }
                } else {
                    addTableInfo(primaryId, formConfigureDetailDto);
                }
            }
        } else {//新增
            for (FormConfigureDetailDto formConfigureDetailDto : resultDataList) {
                if (formConfigureDetailDto.getTableName().equals(formConfigureDto.getPrimaryTableName())) {
                    Map<String, Object> map = jsonStringToMap(formConfigureDetailDto.getFormData());
                    if (StringUtils.isNotBlank(formConfigureDto.getProcessInsId()))
                        map.put("process_ins_id", formConfigureDto.getProcessInsId());
                    primaryId = addObject(formConfigureDetailDto.getTableName(), map);
                    //更新附件信息
                    updateAttachmentTableId(formConfigureDetailDto.getTableName(), primaryId);
                    resultDataList.remove(formConfigureDetailDto);
                    break;
                }
            }
            for (FormConfigureDetailDto formConfigureDetailDto : resultDataList) {
                addTableInfo(primaryId, formConfigureDetailDto);
            }
            //更新从表外键id
            List<FormConfigureDetailDto> multipleFormList = formConfigureDto.getMultipleFormList();
            if (CollectionUtils.isNotEmpty(multipleFormList)) {
                for (FormConfigureDetailDto detailDto : multipleFormList) {
                    BaseFormList hrBaseFormList = hrBaseFormDao.getBaseFormList(detailDto.getFormListId());
                    String sql = String.format("update %s set %s=%s where %s=0 and creator='%s'", detailDto.getTableName(),
                            hrBaseFormList.getForeignKeyName(), primaryId, hrBaseFormList.getForeignKeyName(), serviceComponent.getThisUser());
                    formConfigureDao.updateObject(sql);
                }
            }
        }
        return primaryId;
    }

    private void addTableInfo(Integer primaryId, FormConfigureDetailDto formConfigureDetailDto) throws BusinessException {
        BaseFormList hrBaseFormList = hrBaseFormDao.getBaseFormList(formConfigureDetailDto.getFormListId());
        Map<String, Object> map = jsonStringToMap(formConfigureDetailDto.getFormData());
        map.put(hrBaseFormList.getForeignKeyName(), primaryId);
        Integer tableId = addObject(formConfigureDetailDto.getTableName(), map);
        //更新附件tableId
        updateAttachmentTableId(formConfigureDetailDto.getTableName(), tableId);
    }

    /**
     * 更新附件tableId
     *
     * @param tableName
     * @param tableId
     */
    private void updateAttachmentTableId(String tableName, Integer tableId) {
        BaseAttachment hrBaseAttachment = new BaseAttachment();
        hrBaseAttachment.setTableId(0);
        hrBaseAttachment.setTableName(tableName);
        hrBaseAttachment.setCreater(serviceComponent.getThisUser());

        BaseAttachment attachment = new BaseAttachment();
        attachment.setTableId(tableId);
        hrBaseAttachmentDao.updateAttachementByExample(hrBaseAttachment, attachment);
    }

    /**
     * 获取流程配置的所有单表json数据
     *
     * @param phaseId
     * @param tableId
     * @return
     */
    public Map<String, KeyValueDto> getAllTableJsonString(Integer phaseId, Integer tableId) {
        Map<String, KeyValueDto> map = Maps.newHashMap();
        BaseProcess hrBaseProcess = hrProcessDao.getProcessById(phaseId);
        if (hrBaseProcess == null)
            return map;
        BaseProcessForm hrBaseProcessForm = new BaseProcessForm();
        hrBaseProcessForm.setProcess(hrBaseProcess.getBaseForm());
        hrBaseProcessForm.setBoxName(hrBaseProcess.getBoxName());
        List<BaseProcessForm> hrProcessFormList = hrProcessDao.getProcessFormList(hrBaseProcessForm);
        List<String> list = LangUtils.transform(hrProcessFormList, p -> {
            return p.getName();
        });
        List<BaseFormList> hrBaseFormLists = hrBaseFormDao.getbaseFormList(null, list);
        Map<String, Object> objectMap = formConfigureDao.getObjectSingle(hrBaseProcess.getTableName(), tableId);//
        KeyValueDto keyValueDto = null;
        if (objectMap != null) {
            keyValueDto = new KeyValueDto();
            keyValueDto.setKey(String.valueOf(tableId));
            keyValueDto.setValue(mapToJsonString(objectMap));
            map.put(hrBaseProcess.getTableName(), keyValueDto);
        }
        if (CollectionUtils.isNotEmpty(hrBaseFormLists)) {
            for (BaseFormList hrBaseFormList : hrBaseFormLists) {
                boolean isConfigure = hrBaseFormList.getBisConfigure() == null ? false : hrBaseFormList.getBisConfigure();
                boolean notMultiple = !(hrBaseFormList.getBisMultiple() == null ? false : hrBaseFormList.getBisMultiple());
                boolean notPrimaryTable = !hrBaseProcess.getTableName().equals(hrBaseFormList.getTableName());
                if (isConfigure && notMultiple && notPrimaryTable) {
                    String sql = String.format("select * from %s where %s=?", hrBaseFormList.getTableName(), hrBaseFormList.getForeignKeyName());
                    objectMap = formConfigureDao.getObjectSingle(sql, new Object[]{tableId});//
                    if (objectMap != null) {
                        keyValueDto = new KeyValueDto();
                        keyValueDto.setKey(objectMap.get("id").toString());
                        keyValueDto.setValue(mapToJsonString(objectMap));
                        map.put(hrBaseFormList.getTableName(), keyValueDto);
                    }
                }
            }
        }
        return map;
    }
}
