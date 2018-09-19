package com.copower.pmcc.assess.service.project.survey;


import com.copower.pmcc.assess.common.PoiUtils;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryRightDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.SurveyAssetInventoryRight;
import com.copower.pmcc.assess.dto.output.project.survey.SurveyAssetInventoryRightVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zly on 2018/5/11.
 */
@Service
public class SurveyAssetInventoryRightService {

    @Autowired
    private SurveyAssetInventoryRightDao surveyAssetInventoryRightDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public BootstrapTableVo getListByPlanDetailsId(Integer planDetailsId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightDao.getListByPlanDetailsId(planDetailsId);
        List<SurveyAssetInventoryRightVo> surveyAssetInventoryRightVoList = getVoList(surveyAssetInventoryRightList);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(surveyAssetInventoryRightVoList) ? new ArrayList<SurveyAssetInventoryRight>() : surveyAssetInventoryRightVoList);
        return vo;
    }

    public BootstrapTableVo getListByProjectId(Integer projectId) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<SurveyAssetInventoryRight> surveyAssetInventoryRightList = surveyAssetInventoryRightDao.getListByProjectId(projectId, requestBaseParam.getSearch());
        List<SurveyAssetInventoryRightVo> surveyAssetInventoryRightVoList = getVoList(surveyAssetInventoryRightList);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(surveyAssetInventoryRightVoList) ? new ArrayList<SurveyAssetInventoryRight>() : surveyAssetInventoryRightVoList);
        return vo;
    }

    public List<SurveyAssetInventoryRightVo> getVoList(List<SurveyAssetInventoryRight> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            SurveyAssetInventoryRightVo surveyAssetInventoryRightVo = new SurveyAssetInventoryRightVo();
            BeanUtils.copyProperties(p, surveyAssetInventoryRightVo);
            if (p.getType() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(Integer.valueOf(p.getType()));
                if (baseDataDic != null)
                    surveyAssetInventoryRightVo.setTypeName(baseDataDic.getName());
            }
            if (p.getCategory() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(Integer.valueOf(p.getCategory()));
                if (baseDataDic != null)
                    surveyAssetInventoryRightVo.setCategoryName(baseDataDic.getName());
            }
            return surveyAssetInventoryRightVo;
        });
    }

    /**
     * 删除数据
     *
     * @param surveyAssetInventoryRight
     * @throws BusinessException
     */
    public void save(SurveyAssetInventoryRight surveyAssetInventoryRight) throws BusinessException {
        if (surveyAssetInventoryRight == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (surveyAssetInventoryRight.getId() != null && surveyAssetInventoryRight.getId() > 0) {
            surveyAssetInventoryRightDao.update(surveyAssetInventoryRight);
        } else {
            surveyAssetInventoryRight.setCreator(commonService.thisUserAccount());
            surveyAssetInventoryRightDao.add(surveyAssetInventoryRight);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(SurveyAssetInventoryRight.class), surveyAssetInventoryRight.getId());
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    public boolean delete(Integer id) throws BusinessException {
        if (id == null) throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        ;
        return surveyAssetInventoryRightDao.delete(id);
    }

    /**
     * 导入数据
     *
     * @return
     */
    public String importData(SurveyAssetInventoryRight right, MultipartFile multipartFile) throws BusinessException, IOException {
        if (right == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook hssfWorkbook = PoiUtils.isExcel2003(filePath) ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
        Sheet sheet = hssfWorkbook.getSheetAt(0);//只取第一个sheet
        int rowCount = sheet.getLastRowNum();//总行数
        int startRowNumber = 1;//读取业务数据的起始行序号
        for (int i = startRowNumber; i < rowCount; i++) {
            try {
                Row row = sheet.getRow(startRowNumber);
                SurveyAssetInventoryRight surveyAssetInventoryRight=new SurveyAssetInventoryRight();
                surveyAssetInventoryRight.setProjectId(right.getProjectId());
                surveyAssetInventoryRight.setPlanDetailsId(right.getPlanDetailsId());
                surveyAssetInventoryRight.setCertName(right.getCertName());
                surveyAssetInventoryRight.setCreator(commonService.thisUserAccount());
                surveyAssetInventoryRight.setType(0);
                surveyAssetInventoryRight.setCategory(0);
                surveyAssetInventoryRight.setNumber(row.getCell(3).getStringCellValue());
                surveyAssetInventoryRight.setRegisterDate(row.getCell(4).getDateCellValue());
                surveyAssetInventoryRight.setObligor(row.getCell(5).getStringCellValue());
                surveyAssetInventoryRight.setObligee(row.getCell(6).getStringCellValue());
                surveyAssetInventoryRight.setRegisterAmount(row.getCell(7).getStringCellValue());
                surveyAssetInventoryRight.setActualAmount(row.getCell(8).getStringCellValue());
                surveyAssetInventoryRight.setRegisterArea(row.getCell(9).getStringCellValue());
                surveyAssetInventoryRight.setRightRank(row.getCell(10).getStringCellValue());
                surveyAssetInventoryRight.setBeginDate(row.getCell(11).getDateCellValue());
                surveyAssetInventoryRight.setEndDate(row.getCell(12).getDateCellValue());
                surveyAssetInventoryRightDao.add(surveyAssetInventoryRight);
            }catch (Exception e){

            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.toString();
    }
}
