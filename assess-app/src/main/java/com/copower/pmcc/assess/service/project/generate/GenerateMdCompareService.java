package com.copower.pmcc.assess.service.project.generate;

import com.aspose.words.Bookmark;
import com.aspose.words.BookmarkCollection;
import com.aspose.words.Document;
import com.copower.pmcc.assess.common.AsposeUtils;
import com.copower.pmcc.assess.common.enums.BaseReportFieldCompareEnum;
import com.copower.pmcc.assess.constant.AssessReportFieldConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseReportField;
import com.copower.pmcc.assess.dal.basis.entity.DataSetUseField;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompare;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareItem;
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

    public GenerateMdCompareService(Integer mcId) {
        this.mcId = mcId;
        this.commonService = SpringContextUtils.getBean(CommonService.class);
        this.baseReportFieldService = SpringContextUtils.getBean(BaseReportFieldService.class);
        this.baseAttachmentService = SpringContextUtils.getBean(BaseAttachmentService.class);
        this.mdMarketCompareService = SpringContextUtils.getBean(MdMarketCompareService.class);
        this.dataSetUseFieldService = SpringContextUtils.getBean(DataSetUseFieldService.class);
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
    public MdMarketCompareItem getEvaluationItemList() {
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
    public String getValueByKey(String key) {
        if (StringUtils.isBlank(key)) return null;
        BaseReportFieldCompareEnum fieldCompareEnum = BaseReportFieldCompareEnum.getEnumByName(key);
        if(fieldCompareEnum!=null){
            switch (fieldCompareEnum) {
                case COMPARABLE_BASIS:

                    break;
                case LOCATION_CONDITION:

                    break;
                case RIGHTS_INTERESTS:

                    break;
            }
        }
        //dataSetUseFieldService.getCacheSetUseFieldList()


        return "D:\\IdeaProjects\\pmcc-assess\\assess-app\\target\\pmcc-assess\\temporary\\20190131\\1.docx";
    }

}
