package com.copower.pmcc.assess.service.data;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataValueDefinitionDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataValueDefinition;
import com.copower.pmcc.assess.dto.output.data.DataValueDefinitionVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class DataValueDefinitionService {
    @Autowired
    private DataValueDefinitionDao dataValueDefinitionDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public DataValueDefinitionVo getByDataValueDefinitionId(Integer id) {
        DataValueDefinition object = dataValueDefinitionDao.getSingleObject(id);
        return getDataValueDefinitionVo(object);
    }

    /**
     * 获取列表
     *
     * @return
     */
    public BootstrapTableVo getListVos() {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ArrayList<DataValueDefinitionVo> vos = new ArrayList<>();
        List<DataValueDefinition> dataValueDefinitionList = dataValueDefinitionDao.getListObject(new DataValueDefinition());
        if (CollectionUtils.isNotEmpty(dataValueDefinitionList)) {
            for (DataValueDefinition item : dataValueDefinitionList) {
                vos.add(getDataValueDefinitionVo(item));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataValueDefinitionVo>() : vos);
        return vo;
    }


    public DataValueDefinitionVo getDataValueDefinitionVo(DataValueDefinition dataValueDefinition) {
        if (dataValueDefinition == null) return null;
        DataValueDefinitionVo dataValueDefinitionVo = new DataValueDefinitionVo();
        BeanUtils.copyProperties(dataValueDefinition, dataValueDefinitionVo);
        List<BaseDataDic> purposeDicList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.DATA_ENTRUSTMENT_PURPOSE);
        List<BaseDataDic> valueTypeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.VALUE_TYPE);
        List<BaseDataDic> propertyScopes = baseDataDicService.getCacheDataDicList("examine.house.scope.property");
        if (StringUtils.isNotBlank(dataValueDefinition.getEntrustmentPurpose())) {
            dataValueDefinitionVo.setEntrustmentPurposeName(baseDataDicService.getDataDicName(purposeDicList, dataValueDefinition.getEntrustmentPurpose()));
        }
        if (StringUtils.isNotBlank(dataValueDefinition.getValueType())) {
            dataValueDefinitionVo.setValueTypeName(baseDataDicService.getDataDicName(valueTypeList, dataValueDefinition.getValueType()));
        }
        if (StringUtils.isNotBlank(dataValueDefinition.getPropertyScope())) {
            dataValueDefinitionVo.setPropertyScopeName(baseDataDicService.getDataDicName(propertyScopes, dataValueDefinition.getPropertyScope()));
        }
        return dataValueDefinitionVo;
    }

    /**
     * 保存
     *
     * @param dataValueDefinition
     * @throws BusinessException
     */
    public boolean addDataValueDefinitionReturnId(DataValueDefinition dataValueDefinition) {
        dataValueDefinition.setCreator(processControllerComponent.getThisUser());
        return dataValueDefinitionDao.addObject(dataValueDefinition);
    }

    public boolean updateDataValueDefinition(DataValueDefinition dataValueDefinition) {
        return dataValueDefinitionDao.updateObject(dataValueDefinition);
    }

    /**
     * 删除
     *
     * @param id
     * @throws BusinessException
     */
    public boolean deleteDataValueDefinition(Integer id) throws BusinessException {
        return dataValueDefinitionDao.deleteObject(id);
    }

    public void saveData(DataValueDefinition dataValueDefinition) throws Exception {
        //生成word
        String localPath = String.format("%s\\价值定义%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        Document document = new Document();
        DocumentBuilder builder = new DocumentBuilder(document);
        String html = dataValueDefinition.getTemplate();//这是html文本
        builder.insertHtml(html);
        document.save(localPath);
        if (dataValueDefinition.getId() == null || dataValueDefinition.getId().equals(0)) {
            dataValueDefinitionDao.addObject(dataValueDefinition);
        } else {
            dataValueDefinitionDao.updateObject(dataValueDefinition);
        }

    }

    public DataValueDefinition getValueDefinition(String entrustPurpose, String valueType){

        List<DataValueDefinition> definition = dataValueDefinitionDao.getValueDefinition(entrustPurpose, valueType);
        if(CollectionUtils.isNotEmpty(definition)){
            return  definition.get(0);
        }
        return null;
    }
}
