package com.copower.pmcc.assess.service.project.survey;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.survey.SurveyAssetInventoryEnum;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.project.survey.SurveyAssetCommonDataDto;
import com.copower.pmcc.assess.dto.output.basic.SurveyAssetInventoryVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.basic.BasicApplyService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.api.enums.SysProjectEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by zly on 2018/5/9.
 */

@Service
public class SurveyAssetInventoryService extends BaseService {
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyAssetInventoryDao surveyAssetInventoryDao;
    @Autowired
    private SurveyAssetInventoryContentService surveyAssetInventoryContentService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private SurveyAssetInfoGroupService surveyAssetInfoGroupService;
    @Autowired
    private SurveyAssetInfoItemService surveyAssetInfoItemService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private SurveyAssetInfoService surveyAssetInfoService;

    /**
     * 保存资产清查数据
     *
     * @param surveyAssetCommonDataDto
     * @throws BusinessException
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveSurveyAssetInventory(SurveyAssetCommonDataDto surveyAssetCommonDataDto) throws BusinessException {
        if (surveyAssetCommonDataDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        SurveyAssetInventory surveyAssetInventory = surveyAssetCommonDataDto.getSurveyAssetInventory();
        if (surveyAssetInventory == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveyAssetInventory.getId() != null && surveyAssetInventory.getId() > 0) {
            surveyAssetInventoryDao.updateSurveyAssetInventory(surveyAssetInventory);
        } else {
            surveyAssetInventory.setCreator(commonService.thisUserAccount());
            surveyAssetInventoryDao.addSurveyAssetInventory(surveyAssetInventory);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInventory.class), surveyAssetInventory.getId());
        }
        List<SurveyAssetInventoryContent> assetInventoryContentList = surveyAssetCommonDataDto.getAssetInventoryContentList();
        if (CollectionUtils.isNotEmpty(assetInventoryContentList)) {
            for (SurveyAssetInventoryContent surveyAssetInventoryContent : assetInventoryContentList) {
                surveyAssetInventoryContent.setMasterId(surveyAssetInventory.getId());
                surveyAssetInventoryContentService.saveAssetInventoryContent(surveyAssetInventoryContent);
            }
        }
        if (surveyAssetCommonDataDto.getAssetInfoItemId() != null) {
            SurveyAssetInfoItem assetInfoItem = surveyAssetInfoItemService.getSurveyAssetInfoItemById(surveyAssetCommonDataDto.getAssetInfoItemId());
            assetInfoItem.setBisFinishUniformity(true);
            assetInfoItem.setBisFinishDamage(true);
            assetInfoItem.setStatus(ProjectStatusEnum.FINISH.getKey());
            surveyAssetInfoItemService.updateSurveyAssetInfoItem(assetInfoItem, false);
        }
    }

    /**
     * 初始化
     *
     * @param assetInfoItemId
     * @return
     */
    public SurveyAssetInventory initSurveyAssetInventory(Integer assetInfoItemId) throws BusinessException {
        if (assetInfoItemId == null) return null;
        SurveyAssetInventory targetInventory = new SurveyAssetInventory();
        SurveyAssetInfoItem assetInfoItem = surveyAssetInfoItemService.getSurveyAssetInfoItemById(assetInfoItemId);
        if (assetInfoItem == null) return null;
        SurveyAssetInfo surveyAssetInfo = surveyAssetInfoService.getSurveyAssetInfoById(assetInfoItem.getAssetInfoId());
        if (surveyAssetInfo == null) return null;
        targetInventory.setProjectId(surveyAssetInfo.getProjectId());
        targetInventory.setPlanDetailId(surveyAssetInfo.getPlanDetailId());
        targetInventory.setDeclareRecordId(assetInfoItem.getDeclareId());
        targetInventory.setCreator(commonService.thisUserAccount());
        addSurveyAssetInventory(targetInventory);
        surveyAssetInventoryContentService.initContentByInventoryId(assetInfoItem, targetInventory.getId());

        assetInfoItem.setInventoryId(targetInventory.getId());
        surveyAssetInfoItemService.updateSurveyAssetInfoItem(assetInfoItem, false);
        return targetInventory;
    }

    public void addSurveyAssetInventory(SurveyAssetInventory surveyAssetInventory) {
        if (surveyAssetInventory == null) return;
        surveyAssetInventoryDao.addSurveyAssetInventory(surveyAssetInventory);
    }

    public void updateSurveyAssetInventory(SurveyAssetInventory surveyAssetInventory) {
        if (surveyAssetInventory == null) return;
        surveyAssetInventoryDao.updateSurveyAssetInventory(surveyAssetInventory);
    }

    public SurveyAssetCommonDataDto format(String val) {
        SurveyAssetCommonDataDto dto = null;
        if (StringUtils.isNotBlank(val)) {
            dto = JSON.parseObject(val, SurveyAssetCommonDataDto.class);
        }
        return dto;
    }

    public SurveyAssetInventory getDataByProcessInsId(String processInsId) {
        return surveyAssetInventoryDao.getDataByProcessInsId(processInsId);
    }

    public SurveyAssetInventoryVo getDataByPlanDetailsId(Integer planDetailsId) {
        SurveyAssetInventory dataByPlanDetailsId = surveyAssetInventoryDao.getDataByPlanDetailsId(planDetailsId);
        return getSurveyAssetInventoryVo(dataByPlanDetailsId);
    }

    public SurveyAssetInventory getDataByDeclareId(Integer declareId) {
        return surveyAssetInventoryDao.getDataByDeclareId(declareId);
    }

    public List<SurveyAssetInventory> getDataByDeclareIds(List<Integer> declareIds) {
        return surveyAssetInventoryDao.getDataByDeclareIds(declareIds);
    }

    /**
     * 反写申报记录数据的证载用途与实际用途
     *
     * @param surveyAssetInventory
     */
    @Transactional(rollbackFor = Exception.class)
    public void writeBackDeclareRecord(SurveyAssetInventory surveyAssetInventory) throws BusinessException {
        if (surveyAssetInventory != null) {
            List<SurveyAssetInventoryContent> contentList = surveyAssetInventoryContentService.getContentListByPlanDetailsId(surveyAssetInventory.getPlanDetailId());
            if (CollectionUtils.isNotEmpty(contentList)) {
                DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(surveyAssetInventory.getDeclareRecordId());
                BaseDataDic areaDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_AREA);
                BaseDataDic addressDic = baseDataDicService.getCacheDataDicByFieldName(AssessDataDicKeyConstant.INVENTORY_CONTENT_DEFAULT_ACTUAL_ADDRESS);

                for (SurveyAssetInventoryContent content : contentList) {
                    //反写实际面积
                    if (content.getInventoryContent().equals(areaDic.getId()) && StringUtils.isNotBlank(content.getActual()) && NumberUtils.isNumber(content.getActual())) {
                        declareRecord.setPracticalArea(new BigDecimal(content.getActual()));
                    }
                    //反写实际地址
                    if (content.getInventoryContent().equals(addressDic.getId()) && StringUtils.isNotBlank(content.getActual())) {
                        declareRecord.setSeat(content.getActual());
                    }
                }
                declareRecordService.saveAndUpdateDeclareRecord(declareRecord);
            }
        }
    }


    public SurveyAssetInventoryVo getSurveyAssetInventoryVo(SurveyAssetInventory surveyAssetInventory) {
        if (surveyAssetInventory == null) {
            return null;
        }
        SurveyAssetInventoryVo vo = new SurveyAssetInventoryVo();
        BeanUtils.copyProperties(surveyAssetInventory, vo);
        if (surveyAssetInventory.getApplication() != null) {
            vo.setApplicationName(baseDataDicService.getNameById(surveyAssetInventory.getApplication()));
        }
        if (StringUtils.isNotBlank(surveyAssetInventory.getCertificate())) {
            vo.setCertificateName(baseDataDicService.getNameById(surveyAssetInventory.getCertificate()));
        }
        vo.setFindOriginalName(baseDataDicService.getNameById(surveyAssetInventory.getFindOriginal()));
        vo.setFindMethodName(baseDataDicService.getNameById(surveyAssetInventory.getFindMethod()));
        if (StringUtils.isNotBlank(surveyAssetInventory.getInfluenceFactor())) {
            List<Integer> string2Integer = FormatUtils.transformString2Integer(surveyAssetInventory.getInfluenceFactor());
            List<String> stringList = new ArrayList<>();
            for (Integer id : string2Integer) {
                stringList.add(baseDataDicService.getNameById(id));
            }
            vo.setInfluenceFactorName(StringUtils.join(stringList, ","));
        }
        if (StringUtils.isNotBlank(surveyAssetInventory.getAffected())) {
            List<Integer> string2Integer = FormatUtils.transformString2Integer(surveyAssetInventory.getAffected());
            List<String> stringList = new ArrayList<>();
            for (Integer id : string2Integer) {
                stringList.add(baseDataDicService.getNameById(id));
            }
            vo.setAffectedName(StringUtils.join(stringList, ","));
        }
        if (StringUtils.isNotBlank(surveyAssetInventory.getInfluenceFactorRemarkText())) {
            List<String> stringList = FormatUtils.transformString2List(surveyAssetInventory.getInfluenceFactorRemarkText());
            if (CollectionUtils.isNotEmpty(stringList)) {
                for (String s : stringList) {
                    List<String> strings = FormatUtils.transformString2List(s, ":");
                    if (strings.size() == 1) {
                        continue;
                    }
                    String number = getNumber(strings.get(0));
                    if (NumberUtils.isNumber(number)) {
                        vo.getInfluenceFactorRemarkList().add(new KeyValueDto(baseDataDicService.getNameById(number), strings.get(1)));
                    }
                }
            }
        }
        return vo;
    }

    /**
     * 提取数字
     *
     * @param text
     * @return
     */
    private String getNumber(String text) {
        if (StringUtils.isEmpty(text)) {
            return "0";
        }
        if (NumberUtils.isNumber(text)) {
            return text;
        }
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        String s = m.replaceAll("").trim();
        return StringUtils.isNotBlank(s) ? s : "0";
    }


    public SurveyAssetInventory getSurveyAssetInventoryById(Integer id) {
        return surveyAssetInventoryDao.getSurveyAssetInventoryById(id);
    }

    /**
     * 粘贴  拷贝的数据
     *
     * @param inventoryId
     * @param type
     * @param masterId
     * @throws Exception
     */
    public void parseSurveyAssetInventory(Integer inventoryId, String type, String masterId) throws Exception {
        SurveyAssetInventory surveyAssetInventory = surveyAssetInventoryDao.getSurveyAssetInventoryById(inventoryId);
        List<SurveyAssetInventoryContent> surveyAssetInventoryContents = surveyAssetInventoryContentService.getSurveyAssetInventoryContentListByMasterId(inventoryId);
        Iterator<SurveyAssetInventoryContent> iterator = surveyAssetInventoryContents.iterator();
        List<Integer> integerList = FormatUtils.transformString2Integer(masterId);
        if (CollectionUtils.isEmpty(integerList)) {
            throw new Exception("无拷贝数据");
        }
        //粘贴主数据
        copySurveyAssetInventory(surveyAssetInventory);

        while (iterator.hasNext()) {
            SurveyAssetInventoryContent inventoryContent = iterator.next();
            //粘贴从数据
            copySurveyAssetInventoryContent(inventoryContent, surveyAssetInventory.getId());

        }
        if (type.equals(SurveyAssetInventoryEnum.group.getKey())) {
            for (Integer id : integerList) {
                SurveyAssetInfoGroup infoGroup = surveyAssetInfoGroupService.getSurveyAssetInfoGroupById(id);
                if (Objects.equal(infoGroup.getInventoryId(), inventoryId)) {
                    throw new Exception("无法粘贴自己");
                }
                infoGroup.setInventoryId(surveyAssetInventory.getId());
                surveyAssetInfoGroupService.updateSurveyAssetInfoGroup(infoGroup, true);
            }
        }
        if (type.equals(SurveyAssetInventoryEnum.unit.getKey())) {
            for (Integer id : integerList) {
                SurveyAssetInfoItem surveyAssetInfoItem = surveyAssetInfoItemService.getSurveyAssetInfoItemById(id);
                if (Objects.equal(surveyAssetInfoItem.getInventoryId(), inventoryId)) {
                    throw new Exception("无法粘贴自己");
                }
                surveyAssetInfoItem.setInventoryId(surveyAssetInventory.getId());
                surveyAssetInfoItem.setStatus(SysProjectEnum.FINISH.getValue());
                surveyAssetInfoItemService.saveAndUpdateSurveyAssetInfoItem(surveyAssetInfoItem, true);
            }
        }
    }

    private void copySurveyAssetInventory(SurveyAssetInventory surveyAssetInventory) throws Exception {
        Integer inventoryId = surveyAssetInventory.getId();
        surveyAssetInventory.setId(null);
        surveyAssetInventoryDao.addSurveyAssetInventory(surveyAssetInventory);

        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(inventoryId);
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInventory.class));

        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInventory.class));
        sysAttachmentDto.setTableId(surveyAssetInventory.getId());

        baseAttachmentService.copyFtpAttachments(attachmentDto, sysAttachmentDto);
    }

    private void copySurveyAssetInventoryContent(SurveyAssetInventoryContent inventoryContent, Integer inventoryId) throws Exception {
        Integer id = inventoryContent.getId();
        inventoryContent.setId(null);
        inventoryContent.setMasterId(inventoryId);
        surveyAssetInventoryContentService.saveAssetInventoryContent(inventoryContent);

        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(id);
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInventoryContent.class));

        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInventoryContent.class));
        sysAttachmentDto.setTableId(inventoryContent.getId());

        baseAttachmentService.copyFtpAttachments(attachmentDto, sysAttachmentDto);

    }

    /**
     * 该权证是否可清查
     *
     * @param assetInfoItemId
     * @return
     */
    public Boolean canInventory(Integer assetInfoItemId) {
        SurveyAssetInfoItem assetInfoItem = surveyAssetInfoItemService.getSurveyAssetInfoItemById(assetInfoItemId);
        List<BasicApply> list = basicApplyService.getListByDeclareRecordId(assetInfoItem.getDeclareId());
        return CollectionUtils.isNotEmpty(list);
    }

}
