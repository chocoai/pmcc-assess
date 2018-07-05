package com.copower.pmcc.assess.service.base;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.constant.AssessCacheConstant;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.base.BaseFormDao;
import com.copower.pmcc.assess.dal.basis.dao.base.FormConfigureDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseForm;
import com.copower.pmcc.assess.dal.basis.entity.BaseFormModule;
import com.copower.pmcc.assess.dal.basis.entity.BaseFormModuleField;
import com.copower.pmcc.assess.dto.input.FormConfigureDetailDto;
import com.copower.pmcc.assess.dto.input.FormConfigureDto;
import com.copower.pmcc.assess.dto.output.FormConfigureFieldVo;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.DynamicFormField;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.CustomTableTypeEnum;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FileUtils;
import com.copower.pmcc.erp.common.utils.GenerateDynamicFormUtil;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BaseFormDao hrBaseFormDao;
    @Autowired
    private FormConfigureDao formConfigureDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private ErpRpcUserService erpRpcUserService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    /**
     * 获取表单信息列表
     *
     * @return
     */
    public BootstrapTableVo getFormList() {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseForm> baseFormModule = hrBaseFormDao.getBaseForm(requestBaseParam.getSearch());
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(baseFormModule) ? new ArrayList<BaseForm>() : baseFormModule);
        return bootstrapTableVo;
    }

    /**
     * 获取表单信息列表
     *
     * @return
     */
    public BootstrapTableVo getFormModuleList(Integer formId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseFormModule> baseFormModule = hrBaseFormDao.getBaseFormModuleList(formId);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(baseFormModule) ? new ArrayList<BaseFormModule>() : baseFormModule);
        return bootstrapTableVo;
    }

    public List<BaseFormModule> getFormModules(Integer formId) {
        List<BaseFormModule> baseFormModule = hrBaseFormDao.getBaseFormModuleList(formId);
        return baseFormModule;
    }

    public BaseFormModule getBaseFormModuleById(Integer id) {
        return hrBaseFormDao.getBaseFormModule(id);
    }


    /**
     * 获取字段信息列表
     *
     * @return
     */
    public BootstrapTableVo getFormModuleFieldList(Integer formModuleId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseFormModuleField> baseFormModule = hrBaseFormDao.getBaseFormModuleFields(formModuleId);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(baseFormModule) ? new ArrayList<BaseFormModuleField>() : baseFormModule);
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
     * 保存表单数据
     *
     * @param baseFormDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveForm(BaseForm baseFormDto) throws BusinessException {
        BaseForm baseForm = null;
        if (baseFormDto.getId() != null && baseFormDto.getId() > 0) {
            baseForm = hrBaseFormDao.getBaseForm(baseFormDto.getId());
            if (baseForm != null)//如果没有找到相应信息，则表示没有相应的数据，不进行更新处
            {
                try {
                    BeanUtils.copyProperties(baseFormDto, baseForm);
                } catch (Exception e) {
                    throw new BusinessException(HttpReturnEnum.COPYFAIL.getName(), e);
                }
                if (!hrBaseFormDao.updateBaseForm(baseForm)) {
                    throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
                }
            } else {
                throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
            }
        } else {
            baseForm = new BaseForm();
            baseForm.setBisEnable(true);
            try {
                BeanUtils.copyProperties(baseFormDto, baseForm);
            } catch (Exception e) {
                throw new BusinessException(HttpReturnEnum.COPYFAIL.getName(), e);
            }
            if (!hrBaseFormDao.addBaseForm(baseForm)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        }
    }


    /**
     * 删除表单信息
     *
     * @param id
     */
    public void deleteForm(Integer id) throws BusinessException {
        hrBaseFormDao.deleteBaseForm(id);
    }

    /**
     * 保存表单模块数据
     *
     * @param baseFormModuleDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveFormModule(BaseFormModule baseFormModuleDto) throws BusinessException {
        BaseFormModule baseFormModule = null;
        if (baseFormModuleDto.getId() != null && baseFormModuleDto.getId() > 0) {
            baseFormModule = hrBaseFormDao.getBaseFormModule(baseFormModuleDto.getId());
            if (baseFormModule != null)//如果没有找到相应信息，则表示没有相应的数据，不进行更新处
            {
                try {
                    BeanUtils.copyProperties(baseFormModuleDto, baseFormModule);
                } catch (Exception e) {
                    throw new BusinessException(HttpReturnEnum.COPYFAIL.getName(), e);
                }
                if (!hrBaseFormDao.updateBaseFormModule(baseFormModule)) {
                    throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
                }
            } else {
                throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
            }
        } else {
            baseFormModule = new BaseFormModule();
            try {
                BeanUtils.copyProperties(baseFormModuleDto, baseFormModule);
            } catch (Exception e) {
                throw new BusinessException(HttpReturnEnum.COPYFAIL.getName(), e);
            }
            baseFormModule.setBisEnable(true);
            if (!hrBaseFormDao.addBaseFormModule(baseFormModule)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        }
    }


    /**
     * 删除表单模块信息
     *
     * @param id
     */
    public void deleteFormModule(Integer id) throws BusinessException {
        hrBaseFormDao.deleteBaseFormModule(id);
    }

    /**
     * 保存字段数据
     *
     * @param hrBaseFormModuleFieldDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveFormModuleField(BaseFormModuleField hrBaseFormModuleFieldDto) throws BusinessException {
        BaseFormModuleField hrBaseFormModuleField = null;
        if (hrBaseFormModuleFieldDto.getId() != null && hrBaseFormModuleFieldDto.getId() > 0) {
            hrBaseFormModuleField = hrBaseFormDao.getBaseFormModuleField(hrBaseFormModuleFieldDto.getId());
            if (hrBaseFormModuleField != null)//如果没有找到相应信息，则表示没有相应的数据，不进行更新处
            {
                try {
                    BeanUtils.copyProperties(hrBaseFormModuleFieldDto, hrBaseFormModuleField);
                } catch (Exception e) {
                    throw new BusinessException(HttpReturnEnum.COPYFAIL.getName(), e);
                }
                if (!hrBaseFormDao.updateBaseFormModuleField(hrBaseFormModuleField)) {
                    throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
                }
            } else {
                throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
            }
        } else {
            hrBaseFormModuleField = new BaseFormModuleField();
            try {
                BeanUtils.copyProperties(hrBaseFormModuleFieldDto, hrBaseFormModuleField);
            } catch (Exception e) {
                throw new BusinessException(HttpReturnEnum.COPYFAIL.getName(), e);
            }
            if (!hrBaseFormDao.addBaseFormModuleField(hrBaseFormModuleField)) {
                throw new BusinessException(HttpReturnEnum.SAVEFAIL.getName());
            }
        }
    }


    /**
     * 删除字段信息
     *
     * @param id
     */
    public void deleteFormModuleField(Integer id) throws BusinessException {
        BaseFormModuleField formListField = hrBaseFormDao.getBaseFormModuleField(id);
        if (formListField == null) {
            throw new BusinessException(HttpReturnEnum.NOTFIND.getName());
        }
        formListField.setBisDelete(true);
        hrBaseFormDao.updateBaseFormModuleField(formListField);
    }

    /**
     * 获取表单html
     *
     * @param formModuleId
     * @param readOnly
     * @return
     */
    public String getDynamicFormHtml(Integer formModuleId, Boolean readOnly, String jsonValue) {
        BaseFormModule hrbaseFormModule = hrBaseFormDao.getBaseFormModule(formModuleId);
        if (hrbaseFormModule != null) {
            BaseFormModuleField queryParam = new BaseFormModuleField();
            queryParam.setFormModuleId(hrbaseFormModule.getId());
            queryParam.setBisEnable(true);
            queryParam.setBisDelete(false);
            queryParam.setBisShow(true);
            List<BaseFormModuleField> hrBaseFormModuleFields = hrBaseFormDao.getBaseFormModuleFields(queryParam);
            String s = getFormHtmlString(hrbaseFormModule.getTableName(), readOnly, jsonValue, hrBaseFormModuleFields);
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
        String cacheKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_DYNAMIC_FORM_DATASOURCE, sql);
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
        String cacheKey = CacheConstant.getCostsKeyPrefix(AssessCacheConstant.PMCC_ASSESS_DYNAMIC_FORM_TEXT, sql);
        try {
            String text = LangUtils.singleCache(cacheKey, sql, String.class, p -> {
                return formConfigureDao.getText(sql);
            });
            return text;
        } catch (Exception e) {
            return formConfigureDao.getText(sql);
        }
    }

    private String getFormHtmlString(String tableName, Boolean readOnly, String jsonValue, List<BaseFormModuleField> hrBaseFormModuleFields) {
        if (CollectionUtils.isNotEmpty(hrBaseFormModuleFields)) {
            jsonValue = jsonContentOut(hrBaseFormModuleFields, jsonValue);
            Map<String, Object> map = jsonStringToMap(jsonValue);
            map = map == null ? Maps.newHashMap() : map;
            List<DynamicFormField> transform = Lists.newArrayList();

            //为自定义控件提供的相关值
            Map<String, Object> customMap = Maps.newHashMap();
            customMap.put("curr_tableName", tableName);
            customMap.put("curr_tableId", map.containsKey("id") ? map.get("id").toString() : "0");
            customMap.put("curr_userAccount", processControllerComponent.getThisUser());
            SysUserDto sysUser = erpRpcUserService.getSysUser(processControllerComponent.getThisUser());
            sysUser = sysUser == null ? new SysUserDto() : sysUser;
            customMap.put("curr_userAccountName", sysUser.getUserName());
            for (BaseFormModuleField o : hrBaseFormModuleFields) {
                DynamicFormField dynamicFormField = new DynamicFormField();
                dynamicFormField.setFormField(Boolean.TRUE == o.getBisJson() ? o.getJsonName() : o.getName());
                dynamicFormField.setFormFieldId(String.format("%s-%s", tableName, dynamicFormField.getFormField()));
                dynamicFormField.setLabelName(o.getDisplayName());
                dynamicFormField.setFieldType(o.getFieldType().toString());
                dynamicFormField.setBisRequired(o.getBisRequired());
                dynamicFormField.setValueLength(o.getFieldLength());
                dynamicFormField.setDisplaySort(o.getSorting());
                dynamicFormField.setDefaultValue(o.getDefaultValue());
                dynamicFormField.setBisAloneLine(o.getBisAloneLine());
                customMap.put(dynamicFormField.getFormField(), dynamicFormField.getDefaultValue());

                customMap.put("curr_fieldName", dynamicFormField.getFormField());
                customMap.put("curr_fieldId", dynamicFormField.getFormFieldId());
                customMap.put("curr_labelName", dynamicFormField.getLabelName());
                customMap.put("curr_value", map.containsKey(dynamicFormField.getFormField()) ? map.get(dynamicFormField.getFormField()).toString() : "");
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
                    String text = new String();
                    viewSql = getRegexSql(map, "#\\{(.*?)\\}", viewSql);
                    if (StringUtils.isNotBlank(viewSql)) {
                        try {
                            text = Boolean.TRUE == o.getBisCacheDataView() ? getCacheText(viewSql) : formConfigureDao.getText(viewSql);
                        } catch (Exception e) {
                            logger.error(e.getMessage());
                        }
                    }

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
            GenerateDynamicFormUtil dynamic = new GenerateDynamicFormUtil();
            String s = dynamic.buildDynamicForm(transform, readOnly, mapToJsonString(map));
            return s;
        }
        return null;
    }

    /**
     * 根据key获取字段数据
     *
     * @param formModuleId
     * @return
     */
    public List<BaseFormModuleField> getListFields(Integer formModuleId) {
        List<BaseFormModuleField> hrBaseFormModuleFields = hrBaseFormDao.getBaseFormModuleFields(formModuleId);
        return hrBaseFormModuleFields;
    }

    /**
     * 根据id获取列表显示字段数据
     *
     * @param formModuleId
     * @return
     */
    public List<FormConfigureFieldVo> getListShowFields(Integer formModuleId) {
        List<FormConfigureFieldVo> fieldVos = Lists.newArrayList();
        List<BaseFormModuleField> listFields = getListFields(formModuleId);
        if (CollectionUtils.isNotEmpty(listFields)) {
            for (BaseFormModuleField listField : listFields) {
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
     * @param hrBaseFormModuleFields
     * @return
     */
    public String jsonContentIn(List<BaseFormModuleField> hrBaseFormModuleFields, String jsonString) {
        if (CollectionUtils.isEmpty(hrBaseFormModuleFields))
            return jsonString;
        if (StringUtils.isBlank(jsonString))
            return jsonString;
        HashMap<String, List<String>> map = new HashMap<>();
        fieldToMap(hrBaseFormModuleFields, map);
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
     * @param hrBaseFormModuleFields
     * @return
     */
    public String jsonContentOut(List<BaseFormModuleField> hrBaseFormModuleFields, String jsonString) {
        if (CollectionUtils.isEmpty(hrBaseFormModuleFields))
            return jsonString;
        if (StringUtils.isBlank(jsonString))
            return jsonString;
        HashMap<String, List<String>> map = new HashMap<>();
        fieldToMap(hrBaseFormModuleFields, map);

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

    private void fieldToMap(List<BaseFormModuleField> hrBaseFormModuleFields, HashMap<String, List<String>> map) {
        for (BaseFormModuleField hrBaseFormModuleField : hrBaseFormModuleFields) {
            if (hrBaseFormModuleField.getBisJson()) {
                List<String> list = null;
                if (map.containsKey(hrBaseFormModuleField.getName())) {
                    list = map.get(hrBaseFormModuleField.getName());
                    list.add(hrBaseFormModuleField.getJsonName());
                } else {
                    list = Lists.newArrayList();
                    list.add(hrBaseFormModuleField.getJsonName());
                    map.put(hrBaseFormModuleField.getName(), list);
                }
            }
        }
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
     * 分页查询数据
     *
     * @param sql
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public List<Map<String, Object>> getObjectList(String sql, Integer pageIndex, Integer pageSize) {
        return formConfigureDao.getObjectList(sql, pageIndex, pageSize);
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
        map.put("creator", processControllerComponent.getThisUser());
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
    public Integer updateObject(String tableName, Integer id, List<BaseFormModuleField> formListFields, JSONObject jsonObject) throws BusinessException {
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
                for (BaseFormModuleField formListField : formListFields) {
                    if (formListField.getBisJson()) {
                        if (formListField.getJsonName().equals(stringObjectEntry.getKey())) {
                            if (stringObjectEntry.getValue() == null) {
                                stringBuilder.append(String.format("%s=JSON_SET(%s,'$.%s',%s),", formListField.getName(), formListField.getName(), formListField.getJsonName(), stringObjectEntry.getValue()));
                            } else {
                                stringBuilder.append(String.format("%s=JSON_SET(%s,'$.%s','%s'),", formListField.getName(), formListField.getName(), formListField.getJsonName(), stringObjectEntry.getValue()));
                            }
                        }
                    } else {
                        if (formListField.getName().equals(stringObjectEntry.getKey())) {
                            if (stringObjectEntry.getValue() == null) {
                                stringBuilder.append(String.format("%s=%s,", formListField.getName(), stringObjectEntry.getValue()));
                            } else {
                                stringBuilder.append(String.format("%s='%s',", formListField.getName(), stringObjectEntry.getValue()));
                            }
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
        List<BaseFormModuleField> formListFields = hrBaseFormDao.getBaseFormModuleFields(formListId);
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
            List<BaseFormModuleField> formListFields = hrBaseFormDao.getBaseFormModuleFields(formListId);
            for (Map<String, Object> p : mapList) {
                Map<String, Object> map = jsonStringToMap(jsonContentOut(formListFields, mapToJsonString(p)));
                Map<String, Object> tempMap = Maps.newHashMap();

                for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
                    for (BaseFormModuleField item : formListFields) {
                        String fieldName = Boolean.TRUE == item.getBisJson() ? item.getJsonName() : item.getName();
                        if (stringObjectEntry.getKey().equals(fieldName)) {
                            CustomTableTypeEnum customTableTypeEnum = CustomTableTypeEnum.getCustomTypeByColumnsPrefix(item.getFieldType());
                            if (customTableTypeEnum != null) {
                                switch (customTableTypeEnum) {
                                    case DATE:
                                        map.put(fieldName, DateUtils.formatDate(DateUtils.convertDate(map.get(fieldName).toString()), "yyyy-MM-dd"));
                                        break;
                                    case DATETIME:
                                        map.put(fieldName, DateUtils.formatDateToYMDHMS(DateUtils.convertDate(map.get(fieldName).toString())));
                                        break;
                                }
                            }

                            if (StringUtils.isNotBlank(item.getDataViewSql())) {
                                String viewSql = item.getDataViewSql();
                                viewSql = getRegexSql(map, "#\\{(.*?)\\}", viewSql);
                                String text = new String();
                                if (StringUtils.isNotBlank(viewSql)) {
                                    try {
                                        text = Boolean.TRUE == item.getBisCacheDataView() ? getCacheText(viewSql) : formConfigureDao.getText(viewSql);
                                    } catch (Exception e) {
                                        logger.error(e.getMessage());
                                    }
                                }
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

    private String getRegexSql(Map<String, Object> map, String regex, String source) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            String group = matcher.group(0);
            String group1 = matcher.group(1);
            String s = new String();
            if (map != null && map.containsKey(group1))
                s = map.get(group1).toString();
            if (StringUtils.isBlank(s)) return "";//被替换的参数值一但为空 则直接返回空
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
        List<FormConfigureDetailDto> singelFormModule = formConfigureDto.getSingelFormList();
        List<FormConfigureDetailDto> resultDataList = Lists.newArrayList();
        Map<String, List<Integer>> resultTableField = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(singelFormModule)) {
            HashSet<String> hashSet = Sets.newHashSet();
            for (FormConfigureDetailDto formConfigureDetailDto : singelFormModule) {
                hashSet.add(formConfigureDetailDto.getTableName());
            }
            for (String s : hashSet) {
                FormConfigureDetailDto detailDto = new FormConfigureDetailDto();
                for (FormConfigureDetailDto formConfigureDetailDto : singelFormModule) {
                    if (StringUtils.equals(s, formConfigureDetailDto.getTableName())) {
                        detailDto.setTableName(formConfigureDetailDto.getTableName());
                        detailDto.setTableId(formConfigureDetailDto.getTableId());
                        detailDto.setFormModuleId(formConfigureDetailDto.getFormModuleId());
                        if (StringUtils.isNotBlank(detailDto.getFormData())) {
                            JSONObject jsonObject = JSONObject.parseObject(formConfigureDetailDto.getFormData());
                            JSONObject jo = JSONObject.parseObject(detailDto.getFormData());
                            jo.putAll(jsonObjectToMap(jsonObject));
                            detailDto.setFormData(jo.toJSONString());

                            List<Integer> integers = resultTableField.get(formConfigureDetailDto.getTableName());
                            if (CollectionUtils.isEmpty(integers))
                                integers = Lists.newArrayList(formConfigureDetailDto.getFormModuleId());
                            else
                                integers.add(formConfigureDetailDto.getFormModuleId());
                            resultTableField.put(formConfigureDetailDto.getTableName(), integers);
                        } else {
                            detailDto.setFormData(formConfigureDetailDto.getFormData());
                            resultTableField.put(formConfigureDetailDto.getTableName(), Lists.newArrayList(formConfigureDetailDto.getFormModuleId()));
                        }
                    }
                }
                resultDataList.add(detailDto);
            }

            //2.剥离json字段
            for (FormConfigureDetailDto detailDto : singelFormModule) {
                for (FormConfigureDetailDto formConfigureDetailDto : resultDataList) {
                    if (detailDto.getTableName().equals(formConfigureDetailDto.getTableName())) {
                        List<BaseFormModuleField> formListFields = hrBaseFormDao.getBaseFormModuleFields(detailDto.getFormModuleId());
                        formConfigureDetailDto.setFormData(jsonContentIn(formListFields, formConfigureDetailDto.getFormData()));
                    }
                }
            }
        } else {//只有从表的情况
            FormConfigureDetailDto detailDto = new FormConfigureDetailDto();
            detailDto.setTableId(primaryId);
            detailDto.setTableName(formConfigureDto.getPrimaryTableName());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", primaryId);
            detailDto.setFormData(jsonObject.toJSONString());
            resultDataList.add(detailDto);
        }


        if (primaryId != null && primaryId > 0) {//编辑
            for (FormConfigureDetailDto formConfigureDetailDto : resultDataList) {
                if (formConfigureDetailDto.getTableId() != null && formConfigureDetailDto.getTableId() > 0) {
                    if (!resultTableField.isEmpty()) {
                        List<BaseFormModuleField> formListFields = hrBaseFormDao.getBaseFormModuleFields(resultTableField.get(formConfigureDetailDto.getTableName()));
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
            List<FormConfigureDetailDto> multipleFormModule = formConfigureDto.getMultipleFormList();
            if (CollectionUtils.isNotEmpty(multipleFormModule)) {
                for (FormConfigureDetailDto detailDto : multipleFormModule) {
                    BaseFormModule hrBaseFormModule = hrBaseFormDao.getBaseFormModule(detailDto.getFormModuleId());
                    String sql = String.format("update %s set %s=%s where %s=0 and creator='%s'", detailDto.getTableName(),
                            hrBaseFormModule.getForeignKeyName(), primaryId, hrBaseFormModule.getForeignKeyName(), processControllerComponent.getThisUser());
                    formConfigureDao.updateObject(sql);
                }
            }
        }
        return primaryId;
    }

    private void addTableInfo(Integer primaryId, FormConfigureDetailDto formConfigureDetailDto) throws BusinessException {
        BaseFormModule hrBaseFormModule = hrBaseFormDao.getBaseFormModule(formConfigureDetailDto.getFormModuleId());
        Map<String, Object> map = jsonStringToMap(formConfigureDetailDto.getFormData());
        map.put(hrBaseFormModule.getForeignKeyName(), primaryId);
        Integer tableId = addObject(formConfigureDetailDto.getTableName(), map);
        //更新附件tableId
        updateAttachmentTableId(formConfigureDetailDto.getTableName(), tableId);
    }

    /**
     * 保存单表的动态数据
     *
     * @param formConfigureDetailDto
     * @return
     */
    public Integer saveSimpleData(FormConfigureDetailDto formConfigureDetailDto) throws BusinessException {
        Integer tableId = formConfigureDetailDto.getTableId();
        Integer primaryId = 0;
        if (tableId != null && tableId > 0) {
            List<BaseFormModuleField> formListFields = hrBaseFormDao.getBaseFormModuleFields(formConfigureDetailDto.getFormModuleId());
            String jsonContentOut = jsonContentOut(formListFields, formConfigureDetailDto.getFormData());
            JSONObject jsonObject = JSONObject.parseObject(jsonContentOut);
            updateObject(formConfigureDetailDto.getTableName(), formConfigureDetailDto.getTableId(), formListFields, jsonObject);
        } else {
            Map<String, Object> map = jsonStringToMap(formConfigureDetailDto.getFormData());
            primaryId = addObject(formConfigureDetailDto.getTableName(), map);
        }
        return primaryId;
    }

    /**
     * 更新附件tableId
     *
     * @param tableName
     * @param tableId
     */
    private void updateAttachmentTableId(String tableName, Integer tableId) {
        SysAttachmentDto hrSysAttachmentDto = new SysAttachmentDto();
        hrSysAttachmentDto.setTableId(0);
        hrSysAttachmentDto.setTableName(tableName);
        hrSysAttachmentDto.setCreater(processControllerComponent.getThisUser());

        SysAttachmentDto attachment = new SysAttachmentDto();
        attachment.setTableId(tableId);
        baseAttachmentService.updateAttachementByExample(hrSysAttachmentDto, attachment);
    }

    /**
     * 获取流程配置的所有单表json数据
     *
     * @param formId
     * @param tableId
     * @return
     */
    public Map<String, KeyValueDto> getAllTableJsonString(Integer formId, Integer tableId) {
        Map<String, KeyValueDto> map = Maps.newHashMap();
        BaseForm baseForm = hrBaseFormDao.getBaseForm(formId);
        if (baseForm == null)
            return map;
        List<BaseFormModule> hrBaseFormModules = hrBaseFormDao.getBaseFormModuleList(formId);
        Map<String, Object> objectMap = formConfigureDao.getObjectSingle(baseForm.getTableName(), tableId);//
        KeyValueDto keyValueDto = null;
        if (objectMap != null) {
            keyValueDto = new KeyValueDto();
            keyValueDto.setKey(String.valueOf(tableId));
            keyValueDto.setValue(mapToJsonString(objectMap));
            map.put(baseForm.getTableName(), keyValueDto);
        }
        if (CollectionUtils.isNotEmpty(hrBaseFormModules)) {
            for (BaseFormModule hrBaseFormModule : hrBaseFormModules) {
                boolean isConfigure = hrBaseFormModule.getBisConfigure() == null ? false : hrBaseFormModule.getBisConfigure();
                boolean notMultiple = !(hrBaseFormModule.getBisMultiple() == null ? false : hrBaseFormModule.getBisMultiple());
                boolean notPrimaryTable = !baseForm.getTableName().equals(hrBaseFormModule.getTableName());
                if (isConfigure && notMultiple && notPrimaryTable) {
                    String sql = String.format("select * from %s where %s=?", hrBaseFormModule.getTableName(), hrBaseFormModule.getForeignKeyName());
                    objectMap = formConfigureDao.getObjectSingle(sql, new Object[]{tableId});//
                    if (objectMap != null) {
                        keyValueDto = new KeyValueDto();
                        keyValueDto.setKey(objectMap.get("id").toString());
                        keyValueDto.setValue(mapToJsonString(objectMap));
                        map.put(hrBaseFormModule.getTableName(), keyValueDto);
                    }
                }
            }
        }
        return map;
    }

    /**
     * 获取module的json字符串
     *
     * @param moduleId
     * @param tableId
     * @return
     */
    public String getModuleJsonString(Integer moduleId, Integer tableId) {
        BaseFormModule baseFormModule = hrBaseFormDao.getBaseFormModule(moduleId);
        if (baseFormModule != null) {
            Map<String, Object> objectMap = formConfigureDao.getObjectSingle(baseFormModule.getTableName(), tableId);//
            return mapToJsonString(objectMap);
        }
        return null;
    }

}
