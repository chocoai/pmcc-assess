package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.MyEntry;
import com.copower.pmcc.assess.common.enums.DeclareTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRealtyCheckListDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyCheckList;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyHouseCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyLandCert;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRealtyRealEstateCert;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.*;
import com.google.common.collect.Multimap;
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
import java.util.*;

/**
 * Created by zch on 2020-3-12.
 * 不动产清单
 */
@Service
public class DeclareRealtyCheckListService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private DeclareRealtyCheckListDao declareRealtyCheckListDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private DeclarePublicService declarePublicService;
    @Autowired
    private BaseService baseService;

    public Integer getCount(Integer autoInitNumber, Integer marsterId, Integer planDetailsId) {
        return declareRealtyCheckListDao.getCount(autoInitNumber, marsterId, planDetailsId);
    }

    /**
     * excel 辅助导入
     *
     * @param classArrayListMultimap
     * @param target
     * @param builder
     * @param row
     * @return
     */
    private boolean importExcelHelp(Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap, DeclareRealtyCheckList target, StringBuilder builder, Row row) {
        //必填项
        List<String> requiredList = new ArrayList<>(Arrays.asList("autoInitNumber", "streetNumber"));
        boolean check = declarePublicService.excelImportHelp(classArrayListMultimap, target, builder, row, requiredList);
        if (!check) {
            return false;
        }
        //从这开始excel 导入结束,下面是一些约束情况
        TreeSet<Map.Entry<Integer, MyEntry<Integer, Class<?>>>> treeSet = declarePublicService.getCountByPlanDetailsIdAndAutoInitNumberListTool(target.getPlanDetailsId(), target.getAutoInitNumber(), DeclareTypeEnum.MasterData);
        if (CollectionUtils.isEmpty(treeSet)) {
            String errorInfo = String.join("", " 编号", target.getAutoInitNumber().toString(), "没有找到匹配的数据 ");
            declarePublicService.excelImportWriteErrorInfo(row.getRowNum(), errorInfo, builder);
            return false;
        }
        //匹配到了以后需要判断这个编号是否已经被使用过了的情况
        List<MyEntry<Integer, Class<?>>> myEntryArrayList = new ArrayList<>(1);
        if (CollectionUtils.isNotEmpty(treeSet)) {
            Iterator<Map.Entry<Integer, MyEntry<Integer, Class<?>>>> iterator = treeSet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<Integer, MyEntry<Integer, Class<?>>> myEntryEntry = iterator.next();
                Integer marsterId = myEntryEntry.getValue().getKey();
                Integer count = getCount(target.getAutoInitNumber(), marsterId, target.getPlanDetailsId());
                if (count > 0) {
                    continue;
                }
                myEntryArrayList.add(myEntryEntry.getValue());
            }
        }
        //编号被使用
        if (CollectionUtils.isEmpty(myEntryArrayList)) {
            String errorInfo = String.join("", " 编号", target.getAutoInitNumber().toString(), "已经匹配过了 ， 请修改excel中的编号 ");
            declarePublicService.excelImportWriteErrorInfo(row.getRowNum(), errorInfo, builder);
            return false;
        }
        //正常匹配的情况
        if (myEntryArrayList.size() == 1) {
            MyEntry<Integer, Class<?>> myEntry = myEntryArrayList.get(0);
            String simpleName = myEntry.getValue().getSimpleName();
//            builder.append("第").append(row.getRowNum()).append("行").append("编号").append(target.getAutoInitNumber()).append("匹配的") ;
//            if (com.google.common.base.Objects.equal(simpleName, DeclareRealtyHouseCert.class.getSimpleName())) {
//                builder.append(DeclareTypeEnum.HOUSE.getKey()) ;
//            }
//            if (com.google.common.base.Objects.equal(simpleName, DeclareRealtyLandCert.class.getSimpleName())) {
//                builder.append(DeclareTypeEnum.LAND.getKey()) ;
//            }
//            if (com.google.common.base.Objects.equal(simpleName, DeclareRealtyRealEstateCert.class.getSimpleName())) {
//                builder.append(DeclareTypeEnum.RealEstate.getKey()) ;
//            }
//            builder.append("\n \r") ;
            target.setMarsterId(myEntry.getKey());
        } else {
            //这里是有多个匹配的情况  ,这种也是不允许的,这种情况一般不会发生  ,因为在房产证，土地证以及不动产 导入excel的时候已经做过判断,如果还出现了这种情况,那么可能是网络原因或者其它未知原因影响了
            String errorInfo = String.join("", " 编号", target.getAutoInitNumber().toString(), "找到多个可以配过的主表数据 ， 请咨询管理员 ");
            declarePublicService.excelImportWriteErrorInfo(row.getRowNum(), errorInfo, builder);
            return false;
        }
        return true;
    }

    public String importData(DeclareRealtyCheckList oo, MultipartFile multipartFile) throws Exception {
        Workbook workbook = null;
        Row row = null;
        StringBuilder stringBuilder = new StringBuilder();
        //1.保存文件
        String filePath = baseAttachmentService.saveUploadFile(multipartFile);
        //2.读取文件
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
        }

        //只取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        //工作表的第一行
        row = sheet.getRow(0);
        //读取数据的起始行
        int startRowNumber = 2;
        //导入成功数据条数
        int successCount = 0;
        //总行数
        int rowLength = sheet.getPhysicalNumberOfRows() != 0 ? sheet.getPhysicalNumberOfRows() : sheet.getLastRowNum();
        rowLength = rowLength - startRowNumber;
        if (rowLength == 0) {
            stringBuilder.append("没有数据!");
            return stringBuilder.toString();
        }
        Multimap<String, Map.Entry<Class<?>, Integer>> classArrayListMultimap = declarePublicService.getMultimapByClass(DeclareRealtyCheckList.class, row);
        for (int i = startRowNumber; i < rowLength + startRowNumber; i++) {
            DeclareRealtyCheckList realtyCheckList = null;
            try {
                row = sheet.getRow(i);
                if (row == null) {
                    declarePublicService.excelImportWriteErrorInfo(i, "没有数据", stringBuilder);
                    continue;
                }
                realtyCheckList = new DeclareRealtyCheckList();
                BeanUtils.copyProperties(oo, realtyCheckList);
                realtyCheckList.setId(null);
                //excel 处理
                if (!importExcelHelp(classArrayListMultimap, realtyCheckList, stringBuilder, row)) {
                    continue;
                }
                saveDeclareRealtyCheckList(realtyCheckList);
                successCount++;
            } catch (Exception e) {
                declarePublicService.excelImportWriteErrorInfo(i, e.getMessage(), stringBuilder);
            }
        }
        return String.format("数据总条数%s，成功%s，失败%s。%s", rowLength, successCount, rowLength - successCount, stringBuilder.toString());
    }

    public boolean updateDeclareRealtyCheckList(DeclareRealtyCheckList oo, boolean updateNull) {
        return declareRealtyCheckListDao.updateDeclareRealtyCheckList(oo, updateNull);
    }

    public boolean saveDeclareRealtyCheckList(DeclareRealtyCheckList oo) {
        if (oo == null) {
            return false;
        }
        if (StringUtils.isEmpty(oo.getCreator())) {
            oo.setCreator(commonService.thisUserAccount());
        }
        boolean checkList = declareRealtyCheckListDao.saveDeclareRealtyCheckList(oo);
        baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyCheckList.class), oo.getId());
        return checkList;
    }

    public void saveAndUpdateDeclareRealtyCheckList(DeclareRealtyCheckList oo, boolean updateNull) {
        if (oo == null) {
            return;
        }
        if (oo.getId() != null && oo.getId() != 0) {
            updateDeclareRealtyCheckList(oo, updateNull);
        } else {
            saveDeclareRealtyCheckList(oo);
        }
    }

    private void removeFileByTableId(Integer tableId) {
        if (tableId == null) {
            return;
        }
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(tableId);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(DeclareRealtyCheckList.class));
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(sysAttachmentDto);
        if (CollectionUtils.isEmpty(sysAttachmentDtoList)) {
            return;
        }
        sysAttachmentDtoList.forEach(sysAttachmentDto1 -> baseAttachmentService.deleteAttachment(sysAttachmentDto1.getId()));
    }

    public void deleteDeclareRealtyCheckListById(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> ids = FormatUtils.transformString2Integer(id);
        if (CollectionUtils.isNotEmpty(ids)) {
            if (ids.size() == 1) {
                removeFileByTableId(ids.get(0));
                declareRealtyCheckListDao.deleteDeclareRealtyCheckListById(ids.get(0));
            } else {
                ids.forEach(integer -> removeFileByTableId(integer));
                declareRealtyCheckListDao.deleteDeclareRealtyCheckListByIds(ids);
            }
        }
    }

    public BootstrapTableVo<DeclareRealtyCheckList> getBootstrapTableVo(DeclareRealtyCheckList oo) {
        BootstrapTableVo<DeclareRealtyCheckList> vo = new BootstrapTableVo<DeclareRealtyCheckList>();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRealtyCheckList> declareRealtyCheckLists = getDeclareRealtyCheckListListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isNotEmpty(declareRealtyCheckLists) ? declareRealtyCheckLists : new ArrayList(0));
        return vo;
    }


    public List<DeclareRealtyCheckList> getDeclareRealtyCheckListByIds(List<Integer> ids) {
        return declareRealtyCheckListDao.getDeclareRealtyCheckListByIds(ids);
    }

    public DeclareRealtyCheckList getDeclareRealtyCheckListById(Integer id) {
        return declareRealtyCheckListDao.getDeclareRealtyCheckListById(id);
    }

    public List<DeclareRealtyCheckList> getDeclareRealtyCheckListListByExample(DeclareRealtyCheckList oo) {
        return declareRealtyCheckListDao.getDeclareRealtyCheckListListByExample(oo);
    }

}
