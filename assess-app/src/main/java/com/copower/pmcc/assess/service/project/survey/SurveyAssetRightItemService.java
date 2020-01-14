package com.copower.pmcc.assess.service.project.survey;

import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.constant.AssessDataDicKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetRightItemDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetRightItem;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetRightItemVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by zch on 2019-12-16.
 * 他项权利详情表
 */
@Service
public class SurveyAssetRightItemService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private SurveyAssetRightItemDao surveyAssetRightItemDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseService baseService;

    public boolean updateSurveyAssetRightItem(SurveyAssetRightItem oo, boolean updateNull) {
        return surveyAssetRightItemDao.updateSurveyAssetRightItem(oo, updateNull);
    }

    public boolean saveSurveyAssetRightItem(SurveyAssetRightItem oo) {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        return surveyAssetRightItemDao.saveSurveyAssetRightItem(oo);
    }

    public void saveAndUpdateSurveyAssetRightItem(SurveyAssetRightItem oo, boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            updateSurveyAssetRightItem(oo, updateNull);
        } else {
            saveSurveyAssetRightItem(oo);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetRightItem.class), oo.getId());
        }
    }

    private void removeFileByTableId(Integer tableId) {
        if (tableId == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetRightItem.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteSurveyAssetRightItemById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                surveyAssetRightItemDao.deleteSurveyAssetRightItemById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                surveyAssetRightItemDao.deleteSurveyAssetRightItemByIds(ids);
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(SurveyAssetRightItem oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetRightItem> declareApplyExtensionList = getSurveyAssetRightItemListByExample(oo);
        List<SurveyAssetRightItemVo> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(declareApplyExtensionList)) {
            Iterator<SurveyAssetRightItem> itemIterator = declareApplyExtensionList.iterator();
            while (itemIterator.hasNext()) {
                voList.add(getSurveyAssetRightItemVo(itemIterator.next()));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    public SurveyAssetRightItemVo getSurveyAssetRightItemVo(SurveyAssetRightItem oo) {
        SurveyAssetRightItemVo vo = new SurveyAssetRightItemVo();
        if (oo == null) {
            return null;
        }
        BeanUtils.copyProperties(oo, vo);
        vo.setCategoryName(baseDataDicService.getNameById(oo.getCategory()));
        vo.setTypeName(baseDataDicService.getNameById(oo.getType()));
        return vo;
    }


    public List<SurveyAssetRightItem> getSurveyAssetRightItemByIds(List<Integer> ids) {
        return surveyAssetRightItemDao.getSurveyAssetRightItemByIds(ids);
    }

    public SurveyAssetRightItem getSurveyAssetRightItemById(Integer id) {
        return surveyAssetRightItemDao.getSurveyAssetRightItemById(id);
    }

    public List<SurveyAssetRightItem> getSurveyAssetRightItemListByExample(SurveyAssetRightItem oo) {
        return surveyAssetRightItemDao.getSurveyAssetRightItemListByExample(oo);
    }

    public List<SurveyAssetRightItem> getSurveyAssetRightItemListByGroupId(Integer groupId) {
        SurveyAssetRightItem select = new SurveyAssetRightItem();
        select.setGroupId(groupId);
        return getSurveyAssetRightItemListByExample(select);
    }

    /**
     * 导入数据
     *
     * @return
     */
    public String importData(SurveyAssetRightItem input, MultipartFile multipartFile) throws BusinessException, IOException {
        if (input == null) {
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        }
        StringBuilder builder = new StringBuilder(10);
        Workbook workbook = null;
        Row row = null;
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e, "他项权利详情表");
        }
        Sheet sheet = workbook.getSheetAt(0);//只取第一个sheet
        int startRowNumber = 2;//读取数据的起始行
        int successCount = 0; //导入成功数据条数
        int rowLength = PoiUtils.getSheetRowsCount(sheet); //总行数
        rowLength = rowLength - startRowNumber;
        if (rowLength == 0) {
            throw new BusinessException("没有需要导入的数据");
        }
        row = sheet.getRow(0);//工作表的第一行
        Map<String, Integer> map = PoiUtils.getKeyIndexMap(row);
        if (map.isEmpty()) throw new BusinessException("未设置key值表头");
        List<BaseDataDic> typeList = baseDataDicService.getCacheDataDicList(AssessDataDicKeyConstant.HOUSE_INVENTORY_RIGHT_CATEGORY);
        for (int i = startRowNumber; i < rowLength + startRowNumber; i++) {
            SurveyAssetRightItem rightItem = null;
            //标识符
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                rightItem = new SurveyAssetRightItem();
                BeanUtils.copyProperties(input, rightItem);
                rightItem.setId(null);
                String value = PoiUtils.getCellValue(row.getCell(map.get("category")));
                if (StringUtils.isNotBlank(value)) {
                    BaseDataDic typeDic = baseDataDicService.getDataDicByName(typeList, value);
                    if (typeDic == null) {
                        builder.append(String.format("\n第%s行异常：类别与系统配置的名称不一致", i));
                    } else {
                        rightItem.setCategory(typeDic.getId());
                    }
                } else {
                    builder.append(String.join("", "第", String.valueOf(i), "行", "第", String.valueOf(i), "列", "类型没有填写正确"));
                }
                value = PoiUtils.getCellValue(row.getCell(map.get("remark")));
                if (StringUtils.isNotBlank(value)) {
                    rightItem.setRemark(value);
                }
                value = PoiUtils.getCellValue(row.getCell(map.get("number")));
                if (StringUtils.isNotBlank(value)) {
                    rightItem.setNumber(value);
                }
                value = PoiUtils.getCellValue(row.getCell(map.get("obligor")));
                if (StringUtils.isNotBlank(value)) {
                    rightItem.setObligor(value);
                }
                value = PoiUtils.getCellValue(row.getCell(map.get("registerAmount")));
                if (StringUtils.isNotBlank(value)) {
                    rightItem.setRegisterAmount(value);
                }
                value = PoiUtils.getCellValue(row.getCell(map.get("registerArea")));
                if (StringUtils.isNotBlank(value)) {
                    rightItem.setRegisterArea(value);
                }
                value = PoiUtils.getCellValue(row.getCell(map.get("registerDate")));
                if (StringUtils.isNotBlank(value)) {
                    rightItem.setRegisterDate(DateUtils.convertDate(value));
                }
                value = PoiUtils.getCellValue(row.getCell(map.get("endDate")));
                if (StringUtils.isNotBlank(value)) {
                    rightItem.setEndDate(DateUtils.convertDate(value));
                }
                value = PoiUtils.getCellValue(row.getCell(map.get("beginDate")));
                if (StringUtils.isNotBlank(value)) {
                    rightItem.setBeginDate(DateUtils.convertDate(value));
                }
                value = PoiUtils.getCellValue(row.getCell(map.get("obligee")));
                if (StringUtils.isNotBlank(value)) {
                    rightItem.setObligee(value);
                }
                value = PoiUtils.getCellValue(row.getCell(map.get("actualAmount")));
                if (StringUtils.isNotBlank(value)) {
                    rightItem.setActualAmount(value);
                }
                value = PoiUtils.getCellValue(row.getCell(map.get("rightRank")));
                if (StringUtils.isNotBlank(value)) {
                    rightItem.setRightRank(value);
                }
                saveSurveyAssetRightItem(rightItem);
                successCount++;
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i + 1, e.getMessage()));
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }

}
