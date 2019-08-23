package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.dao.data.DataDispatchRegisterDao;
import com.copower.pmcc.assess.dal.basis.entity.DataDispatchRegister;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 10:01
 * @Description:基础版块维护
 */
@Service
public class DataDispatchRegisterService {
    @Autowired
    private DataDispatchRegisterDao dataDispatchRegisterDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateDataDispatchRegister(DataDispatchRegister dataDispatchRegister) {
        if (dataDispatchRegister.getId() == null || dataDispatchRegister.getId().intValue() == 0) {
            dataDispatchRegister.setCreator(commonService.thisUserAccount());
            try {
                return dataDispatchRegisterDao.addDataDispatchRegister(dataDispatchRegister);
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
                return null;
            }
        } else {
            dataDispatchRegisterDao.updateDataDispatchRegister(dataDispatchRegister);
            return null;
        }
    }

    public DataDispatchRegister getDataDispatchRegisterById(Integer id) {
        if (id == null) {
            logger.error("null point");
            return null;
        }
        return dataDispatchRegisterDao.getDataDispatchRegisterById(id);
    }

    public BootstrapTableVo getDataDispatchRegisterListVos() {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataDispatchRegister> dataDispatchRegisterList = dataDispatchRegisterDao.getDataDispatchRegisterList(new DataDispatchRegister());
        vo.setTotal(page.getTotal());
        vo.setRows(dataDispatchRegisterList);
        return vo;
    }


    public void removeDataDispatchRegister(DataDispatchRegister dataDispatchRegister) {
        dataDispatchRegisterDao.removeDataDispatchRegister(dataDispatchRegister);
    }

    /**
     * 功能描述: 导入
     *
     * @param:
     * @return:
     * @auther:
     * @date: 2018/9/25 17:58
     */
    public String importData(MultipartFile multipartFile) throws Exception {
        Workbook workbook = null;
        Row row = null;
        StringBuilder builder = new StringBuilder();
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        //只取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //工作表的第一行
        row = sheet.getRow(0);
        //读取数据的起始行
        int startRowNumber = 1;
        //导入成功数据条数
        int successCount = 0;
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        rowLength = rowLength - startRowNumber;
        if (rowLength == 0) {
            builder.append("没有数据!");
            return builder.toString();
        }
        for (int i = startRowNumber; i < startRowNumber + rowLength; i++) {
            DataDispatchRegister dataDispatchRegister = null;
            try {
                dataDispatchRegister = new DataDispatchRegister();
                row = sheet.getRow(i);
                if (row == null) {
                    builder.append(String.format("\n第%s行异常：%s", i, "没有数据"));
                    continue;
                }
                if (!importDataDispatchRegister(dataDispatchRegister, builder, row, i)) {
                    continue;
                }
                dataDispatchRegister.setCreator(commonService.thisUserAccount());
            } catch (Exception e) {
                builder.append(String.format("\n第%s行异常：%s", i, e.getMessage()));
                continue;
            }
            this.saveAndUpdateDataDispatchRegister(dataDispatchRegister);
            successCount++;
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, builder.toString());
    }


    public boolean importDataDispatchRegister(DataDispatchRegister dataDispatchRegister, StringBuilder builder, Row row, int i) throws Exception {
        //登记日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(0)))) {
            dataDispatchRegister.setDispatchDate(PoiUtils.getCellValue(row.getCell(0)));
        }
        //发文号
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(1)))) {
            dataDispatchRegister.setDispatchNumber(PoiUtils.getCellValue(row.getCell(1)));
        }
        //业务类型
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(2)))) {
            dataDispatchRegister.setBusinessType(PoiUtils.getCellValue(row.getCell(2)));
        }
        //委托目的
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(3)))) {
            dataDispatchRegister.setEntrustPurpose(PoiUtils.getCellValue(row.getCell(3)));
        }
        //项目名称
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(4)))) {
            dataDispatchRegister.setProjectName(PoiUtils.getCellValue(row.getCell(4)));
        }
        //客户公司
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(5)))) {
            dataDispatchRegister.setClientCompany(PoiUtils.getCellValue(row.getCell(5)));
        }
        //委托单位
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(6)))) {
            dataDispatchRegister.setEntrustUnit(PoiUtils.getCellValue(row.getCell(6)));
        }
        //评估面积
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(7)))) {
            dataDispatchRegister.setAssessArea(PoiUtils.getCellValue(row.getCell(7)));
        }

        //评估额(元)
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(8)))) {
            dataDispatchRegister.setAssessAmount(PoiUtils.getCellValue(row.getCell(8)));
        }
        //外送份数
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(9)))) {
            dataDispatchRegister.setSendNumber(PoiUtils.getCellValue(row.getCell(9)));
        }
        //留存份数
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(10)))) {
            dataDispatchRegister.setRemainNumber(PoiUtils.getCellValue(row.getCell(10)));
        }
        //经办人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(11)))) {
            dataDispatchRegister.setOperator(PoiUtils.getCellValue(row.getCell(11)));
        }
        //审批人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(12)))) {
            dataDispatchRegister.setApprover(PoiUtils.getCellValue(row.getCell(12)));
        }
        //交存人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(13)))) {
            dataDispatchRegister.setDepositPerson(PoiUtils.getCellValue(row.getCell(13)));
        }
        //编存人
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(14)))) {
            dataDispatchRegister.setRedactPerson(PoiUtils.getCellValue(row.getCell(14)));
        }
        //归档日期
        if (org.apache.commons.lang3.StringUtils.isNotBlank(PoiUtils.getCellValue(row.getCell(15)))) {
            dataDispatchRegister.setPigeonholeDate(PoiUtils.getCellValue(row.getCell(15)));
        }

        return true;
    }
}
