package com.copower.pmcc.assess.service.project.generate;

import com.alibaba.fastjson.JSON;
import com.aspose.words.*;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldCompareEnum;
import com.copower.pmcc.assess.common.enums.MethodCompareFieldEnum;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportField;
import com.copower.pmcc.assess.dal.basis.entity.DataSetUseField;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompare;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareItem;
import com.copower.pmcc.assess.dto.input.method.MarketCompareItemDto;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseReportFieldService;
import com.copower.pmcc.assess.service.data.DataSetUseFieldService;
import com.copower.pmcc.assess.service.method.MdMarketCompareService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.SpringContextUtils;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by kings on 2019-1-31.
 */
public class GenerateMdCompareService {

    private Integer mcId;
    private List<DataSetUseField> setUseFieldList;
    private MdMarketCompare marketCompare;
    private MdMarketCompareItem evaluationItem;
    private List<MdMarketCompareItem> caseItemList;


    private BaseReportFieldService baseReportFieldService;
    private BaseAttachmentService baseAttachmentService;
    private CommonService commonService;
    private MdMarketCompareService mdMarketCompareService;
    private DataSetUseFieldService dataSetUseFieldService;

    private GenerateMdCompareService() {
    }

    public GenerateMdCompareService(Integer mcId) throws Exception {
        this.mcId = mcId;
        this.commonService = SpringContextUtils.getBean(CommonService.class);
        this.baseReportFieldService = SpringContextUtils.getBean(BaseReportFieldService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.mdMarketCompareService = SpringContextUtils.getBean(MdMarketCompareService.class);
        this.dataSetUseFieldService = SpringContextUtils.getBean(DataSetUseFieldService.class);
        getEvaluationItemList();
    }

    /**
     * 获取设定用途字段
     *
     * @return
     */
    public List<DataSetUseField> getSetUseFieldList() {
        if (CollectionUtils.isNotEmpty(setUseFieldList)) return setUseFieldList;
        List<DataSetUseField> fields = mdMarketCompareService.getSetUseFieldList();
        this.setUseFieldList = fields;
        return fields;
    }

    /**
     * 获取比较法主表
     *
     * @return
     */
    public MdMarketCompare getMarketCompare() {
        if (marketCompare != null) return marketCompare;
        MdMarketCompare mdMarketCompare = mdMarketCompareService.getMdMarketCompare(this.mcId);
        this.marketCompare = mdMarketCompare;
        return mdMarketCompare;
    }

    /**
     * 获取估价对象信息
     *
     * @return
     */
    public MdMarketCompareItem getEvaluationItemList() throws Exception {
        if (evaluationItem != null)
            return this.evaluationItem;
        MdMarketCompareItem marketCompareItem = mdMarketCompareService.getEvaluationListByMcId(getMarketCompare().getId());
        this.evaluationItem = marketCompareItem;
        return marketCompareItem;
    }

    /**
     * 获取案例信息
     *
     * @return
     */
    public List<MdMarketCompareItem> getCaseItemList() {
        if (CollectionUtils.isNotEmpty(caseItemList))
            return this.caseItemList;
        List<MdMarketCompareItem> itemList = mdMarketCompareService.getCaseListByMcId(getMarketCompare().getId());
        this.caseItemList = itemList;
        return itemList;
    }

    //生成文件
    public String generateCompareFile() throws Exception {
        // 1.先找到模板文件
        // 2.下载模板文件到本地
        // 3.找出模板文件中的书签或者文本key
        // 4.依次寻找并生成替换数据
        // 5.将数据替换到模板中并返回替换好的文件路径
        BaseReportField baseReportField = baseReportFieldService.getCacheReportFieldByFieldName(AssessReportFieldConstant.COMPARE_TEMPLATE);
        List<SysAttachmentDto> dtoList = baseAttachmentService.getByField_tableId(baseReportField.getId(), null, FormatUtils.entityNameConvertToTableName(BaseReportField.class));
        if (CollectionUtils.isEmpty(dtoList))
            throw new BusinessException("模板文件未找到");
        String localPath = baseAttachmentService.downloadFtpFileToLocal(dtoList.get(0).getId());
        Document document = new Document(localPath);
        Map<String, String> textMap = AsposeUtils.getRegexExtendList(document);

        if (textMap != null && textMap.size() > 0) {//文本替换
            for (Map.Entry<String, String> entry : textMap.entrySet()) {
                textMap.put(entry.getKey(), this.getValueByKey(entry.getValue()));
            }
            AsposeUtils.replaceTextToFile(localPath, textMap);
        }
        BookmarkCollection bookmarks = AsposeUtils.getBookmarks(document);
        if (bookmarks != null) {//书签替换
            Map<String, String> bookmarkMap = Maps.newHashMap();
            for (Bookmark bookmark : bookmarks) {
                bookmarkMap.put(bookmark.getName(), this.getValueByKey(bookmark.getName()));
            }
            AsposeUtils.replaceBookmark(localPath, bookmarkMap, true);
        }
        return localPath;
    }

    /**
     * 根据字段key值获取被替换的值
     *
     * @param key
     * @return
     */
    public String getValueByKey(String key) throws Exception {
        if (StringUtils.isBlank(key)) return null;
        BaseReportFieldCompareEnum fieldCompareEnum = BaseReportFieldCompareEnum.getEnumByName(key);
        MdMarketCompareItem evaluationItemList = getEvaluationItemList();
        List<MarketCompareItemDto> marketCompareItemDtos = JSON.parseArray(evaluationItemList.getJsonContent(), MarketCompareItemDto.class);
        String localPath = "";
        if (fieldCompareEnum != null) {
            String title = fieldCompareEnum.getName();
            switch (fieldCompareEnum) {
                case COMPARABLE_BASIS:
                    localPath = getTable(marketCompareItemDtos, title, "comparative.basis", "trading.status");
                    break;
                case LOCATION_CONDITION:
                    localPath = getTable(marketCompareItemDtos, title, "location.condition", "");
                    break;
                case RIGHTS_INTERESTS:
                    localPath = getTable(marketCompareItemDtos, title, "equity.condition", "");
                    break;
                case ENTITY_CONDITION:
                    localPath = getTable(marketCompareItemDtos, title, "entity.condition", "");
                    break;
            }
        }
        //dataSetUseFieldService.getCacheSetUseFieldList()


        //return "D:\\IdeaProjects\\pmcc-assess\\assess-app\\target\\pmcc-assess\\temporary\\20190131\\1.docx";
        return localPath;
    }

    //生成表格
    public String getTable(List<MarketCompareItemDto> list, String title, String fieldName, String fieldMore) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        String localPath = String.format("%s\\" + title + "%s%s", baseAttachmentService.createTempDirPath(UUID.randomUUID().toString()), UUID.randomUUID().toString(), ".doc");
        builder.writeln(title);
        List<DataSetUseField> cacheSetUseFieldList = dataSetUseFieldService.getCacheSetUseFieldList(fieldName);

        for (MarketCompareItemDto item : list) {
            for (DataSetUseField useField : cacheSetUseFieldList) {
                if (useField.getFieldName().equals(item.getName())) {
                    builder.insertCell();
                    builder.writeln(MethodCompareFieldEnum.getNameByKey(item.getName()));
                    builder.insertCell();
                    builder.writeln(item.getValue());
                    builder.endRow();
                }
            }
        }
        //交易情况
        if (StringUtils.isNotBlank(fieldMore)) {
            builder.writeln("交易情况");
            List<DataSetUseField> tradingFieldList = dataSetUseFieldService.getCacheSetUseFieldList(fieldMore);
            for (MarketCompareItemDto item : list) {
                for (DataSetUseField useField : tradingFieldList) {
                    if (useField.getFieldName().equals(item.getName())) {
                        builder.insertCell();
                        builder.writeln(MethodCompareFieldEnum.getNameByKey(item.getName()));
                        builder.insertCell();
                        builder.writeln(item.getValue());
                        builder.endRow();
                    }
                }
            }
        }
        //设置表格边框的宽度
        builder.getCellFormat().getBorders().getLeft().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getRight().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getTop().setLineWidth(1.0);
        builder.getCellFormat().getBorders().getBottom().setLineWidth(1.0);
        //设置具体宽度
        builder.getCellFormat().setWidth(100);
        //水平居中
        builder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
        //上下居中
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        builder.endTable();


        doc.save(localPath);
        return localPath;
    }
}
