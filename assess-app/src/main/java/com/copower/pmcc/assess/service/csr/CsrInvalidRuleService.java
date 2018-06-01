package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.csr.CsrInvalidRuleDao;
import com.copower.pmcc.assess.dal.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.entity.CsrInvalidRule;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 2018-5-31.
 */
@Service
public class CsrInvalidRuleService {
    @Autowired
    private CsrInvalidRuleDao csrInvalidRuleDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataCsrFieldRelationService dataCsrFieldRelationService;
    @Autowired
    private CsrBorrowerDao csrBorrowerDao;

    /**
     * 获取过滤规则数据列表
     *
     * @param csrProjectId
     * @return
     */
    public BootstrapTableVo getInvalidRuleList(Integer csrProjectId) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        CsrInvalidRule queryParam = new CsrInvalidRule();
        if (csrProjectId != null && csrProjectId > 0) {
            queryParam.setCsrProjectId(csrProjectId);
        } else {
            queryParam.setCsrProjectId(0);
            queryParam.setCreator(commonService.thisUserAccount());
        }
        List<CsrInvalidRule> list = csrInvalidRuleDao.getCsrInvalidRuleList(queryParam);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(list) ? new ArrayList<CsrInvalidRule>() : list);
        return bootstrapTableVo;
    }

    /**
     * 获取过滤规则数据列表
     *
     * @param csrProjectId
     * @return
     */
    public List<CsrInvalidRule> getRuleList(Integer csrProjectId) {
        CsrInvalidRule queryParam = new CsrInvalidRule();
        queryParam.setCsrProjectId(csrProjectId);
        List<CsrInvalidRule> list = csrInvalidRuleDao.getCsrInvalidRuleList(queryParam);
        return list;
    }

    /**
     * 保存过滤规则
     *
     * @param csrInvalidRule
     */
    public void saveInvalidRule(CsrInvalidRule csrInvalidRule) {
        if (csrInvalidRule.getId() != null && csrInvalidRule.getId() > 0) {
            csrInvalidRuleDao.updateCsrInvalidRule(csrInvalidRule);
        } else {
            csrInvalidRule.setCreator(commonService.thisUserAccount());
            csrInvalidRuleDao.addCsrInvalidRule(csrInvalidRule);
        }
    }

    /**
     * 删除过滤规则
     *
     * @param id
     */
    public void deleteInvalidRule(Integer id) {
        csrInvalidRuleDao.deleteCsrInvalidRule(id);
    }

    /**
     * 更新CsrProjectId
     *
     * @param csrProjectId
     */
    public void updateCsrProjectId(Integer csrProjectId) {
        CsrInvalidRule queryParam = new CsrInvalidRule();
        queryParam.setCsrProjectId(0);
        queryParam.setCreator(commonService.thisUserAccount());
        List<CsrInvalidRule> list = csrInvalidRuleDao.getCsrInvalidRuleList(queryParam);
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(p -> {
                p.setCsrProjectId(csrProjectId);
                csrInvalidRuleDao.updateCsrInvalidRule(p);
            });
        }
    }

    /**
     * 是否过滤
     *
     * @param ruleList
     * @param columnName
     * @param columnValue
     * @return
     */
    private boolean isFilter(List<CsrInvalidRule> ruleList, String columnName, String columnValue) {
        for (CsrInvalidRule csrInvalidRule : ruleList) {
            if (StringUtils.equals(csrInvalidRule.getColumnName(), columnName) && StringUtils.equals(csrInvalidRule.getColumnValue(), columnValue)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 数据导入
     *
     * @param path
     * @param csrProjectInfo
     * @throws IOException
     */
    public void importData(String path, CsrProjectInfo csrProjectInfo) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
        int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();//总列数
        int startRowNumber = csrProjectInfo.getStartRowNumber();//读取业务数据的起始行序号
        List<DataCsrFieldRelation> fieldRelations = dataCsrFieldRelationService.getAllList();
        List<CsrInvalidRule> ruleList = getRuleList(csrProjectInfo.getId());//过滤规则数据
        HashMap<Integer, String> invalidRuleIndexMap = Maps.newHashMap();//需要参与过滤的列
        HashMap<Integer, CsrImportColumnDto> hashMap = Maps.newHashMap();
        HSSFRow row = null;
        HSSFCell cell = null;
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            //第一行特殊处理 需要处理数据行才操作 其它行丢弃
            //没有在基础配置的字段中不做处理
            if (rowNum == 0) {
                //确定单元格对应的字段 将每一列与配置的字段对应
                row = sheet.getRow(rowNum);
                for (int i = 0; i < coloumNum; i++) {
                    cell = row.getCell(i);
                    String value = cell.getStringCellValue();
                    if (StringUtils.isBlank(value)) continue;
                    //关联到配置
                    CsrImportColumnDto csrImportColumnDto = new CsrImportColumnDto();
                    csrImportColumnDto.setColumnIndex(i);
                    csrImportColumnDto.setColumnName(value);
                    DataCsrFieldRelation fieldRelation = dataCsrFieldRelationService.getFieldRelationFromList(fieldRelations, value);
                    if (fieldRelation != null) {
                        csrImportColumnDto.setTableName(fieldRelation.getTableName());
                        csrImportColumnDto.setFieldName(fieldRelation.getFieldName());
                    }
                    hashMap.put(i, csrImportColumnDto);

                    //记录需要过滤行的 序号
                    for (CsrInvalidRule csrInvalidRule : ruleList) {
                        if (StringUtils.equals(csrInvalidRule.getColumnName(), value)) {
                            invalidRuleIndexMap.put(i, value);
                        }
                    }
                }

            } else if (startRowNumber > rowNum) {
                //读取数据 过滤行 过滤数据
                if (!invalidRuleIndexMap.isEmpty()) {
                    boolean isFilter = false;
                    for (Map.Entry<Integer, String> integerStringEntry : invalidRuleIndexMap.entrySet()) {
                        isFilter = isFilter(ruleList, integerStringEntry.getValue(), row.getCell(integerStringEntry.getKey()).getStringCellValue());
                        if (isFilter) continue;
                    }
                    if (isFilter) continue;
                }

                //取到核心字段验证 确定数据的保存条数
                //先取到该行需要保存的数据
                if (!hashMap.isEmpty()) {
                    String secondLevelBranch = "";//二级分行
                    String idNumber = "";//客户证件号
                    String contractNumber = "";//合同编号

                    CsrBorrower csrBorrower = null;//借款人
                    CsrBorrowerMortgage csrBorrowerMortgage = null;//抵押物
                    CsrContract csrContract = null;//合同
                    CsrGuarantor csrGuarantor = null;//保证人
                    CsrLitigation csrLitigation = null;//诉讼保全
                    CsrPrincipalInterest csrPrincipalInterest = null;//本金利息
                    //region 处理数据到核心字段 实体类中
                    for (Map.Entry<Integer, CsrImportColumnDto> entry : hashMap.entrySet()) {
                        CsrImportColumnDto columnDto = entry.getValue();
                        if (StringUtils.isNotBlank(columnDto.getTableName()) && StringUtils.isNotBlank(columnDto.getFieldName())) {
                            //找出 二级分行 客户证件号 合同编号 并将数据包含到对应的实体对象中
                            cell = row.getCell(entry.getKey());
                            if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_BORROWER)) {
                                if (StringUtils.equals(columnDto.getFieldName(), AssessFieldNameConstant.CSR_BORROWER_SECOND_LEVEL_BRANCH)) {
                                    secondLevelBranch = cell.getStringCellValue();
                                }
                                if (StringUtils.equals(columnDto.getFieldName(), AssessFieldNameConstant.CSR_BORROWER_ID_NUMBER)) {
                                    idNumber = cell.getStringCellValue();
                                }
                                if (csrBorrower == null)
                                    csrBorrower = new CsrBorrower();
                                try {
                                    ReflectUtils.setProperty(csrBorrower, FormatUtils.underlineToCamel(columnDto.getFieldName(), false), cell.getStringCellValue());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_BORROWER_MORTGAGE)) {
                                if (StringUtils.equals(columnDto.getFieldName(), AssessFieldNameConstant.CSR_BORROWER_MORTGAGE_CONTRACT_NUMBER)) {
                                    contractNumber = cell.getStringCellValue();
                                }
                                if (csrBorrowerMortgage == null)
                                    csrBorrowerMortgage = new CsrBorrowerMortgage();
                                try {
                                    ReflectUtils.setProperty(csrBorrowerMortgage, FormatUtils.underlineToCamel(columnDto.getFieldName(), false), cell.getStringCellValue());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_CONTRACT)) {
                                if (csrContract == null)
                                    csrContract = new CsrContract();
                                try {
                                    ReflectUtils.setProperty(csrContract, FormatUtils.underlineToCamel(columnDto.getFieldName(), false), cell.getStringCellValue());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_GUARANTOR)) {
                                if (csrGuarantor == null)
                                    csrGuarantor = new CsrGuarantor();
                                try {
                                    ReflectUtils.setProperty(csrGuarantor, FormatUtils.underlineToCamel(columnDto.getFieldName(), false), cell.getStringCellValue());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_LITIGATION)) {
                                if (csrLitigation == null)
                                    csrLitigation = new CsrLitigation();
                                try {
                                    ReflectUtils.setProperty(csrLitigation, FormatUtils.underlineToCamel(columnDto.getFieldName(), false), cell.getStringCellValue());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (StringUtils.equals(columnDto.getTableName(), AssessTableNameConstant.CSR_PRINCIPAL_INTEREST)) {
                                if (csrPrincipalInterest == null)
                                    csrPrincipalInterest = new CsrPrincipalInterest();
                                try {
                                    ReflectUtils.setProperty(csrPrincipalInterest, FormatUtils.underlineToCamel(columnDto.getFieldName(), false), cell.getStringCellValue());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    //endregion

                    //确定客户数据是否保存
                    CsrBorrower borrower = useHistoryBorrower(csrProjectInfo.getId(), secondLevelBranch, idNumber, contractNumber);
                    if (borrower == null) {//保存客户信息
                        if (csrBorrower != null) {
                            csrBorrower.setCsrProjectId(csrProjectInfo.getId());
                            csrBorrower.setBisImport(true);
                            csrBorrower.setCreator(commonService.thisUserAccount());
                            csrBorrowerDao.addCsrBorrower(csrBorrower);
                        }
                    } else {
                        csrBorrower = borrower;
                    }
                    if (csrBorrowerMortgage != null) {
                        csrBorrowerMortgage.setBisImport(true);
                        csrBorrowerMortgage.setCreator(commonService.thisUserAccount());
                        //csrBorrowerDao.addCsrBorrower(csrBorrowerMortgage);
                    }

                }

            }

            System.out.print(row.getCell(15));
        }
        is.close();
    }

    /**
     * 确定是否使用已有的借款人信息
     *
     * @param csrProjectId
     * @param secondLevelBranch
     * @param idNumber
     * @param contractNumber
     * @return
     */
    public CsrBorrower useHistoryBorrower(Integer csrProjectId, String secondLevelBranch, String idNumber, String contractNumber) {
        CsrBorrower queryParam = new CsrBorrower();
        queryParam.setCsrProjectId(csrProjectId);
        queryParam.setSecondLevelBranch(secondLevelBranch);
        queryParam.setIdNumber(idNumber);
        List<CsrBorrower> csrBorrowerList = csrBorrowerDao.getCsrBorrowerList(queryParam);
        if(CollectionUtils.isNotEmpty(csrBorrowerList)) {
            return csrBorrowerList.get(0);
        }
        return null;
    }
}
