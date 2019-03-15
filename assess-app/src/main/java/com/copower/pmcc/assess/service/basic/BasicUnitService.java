package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.BasicApplyPartInModeEnum;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.input.SynchronousDataDto;
import com.copower.pmcc.assess.dto.output.cases.CaseUnitHuxingVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.assist.DdlMySqlAssist;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.cases.CaseUnitHuxingService;
import com.copower.pmcc.assess.service.cases.CaseUnitService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicUnitService {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BasicUnitDao basicUnitDao;
    @Autowired
    private CaseUnitHuxingService caseUnitHuxingService;
    @Autowired
    private CaseUnitService caseUnitService;
    @Autowired
    private DdlMySqlAssist ddlMySqlAssist;
    @Autowired
    private BasicEstateService basicEstateService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicUnit getBasicUnitById(Integer id) throws Exception {
        return basicUnitDao.getBasicUnitById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicUnit
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicUnit(BasicUnit basicUnit) throws Exception {
        if (basicUnit.getId() == null || basicUnit.getId().intValue() == 0) {
            basicUnit.setCreator(commonService.thisUserAccount());
            Integer id = basicUnitDao.addBasicUnit(basicUnit);
            return id;
        } else {
            basicUnitDao.updateBasicUnit(basicUnit);
            return null;
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicUnit(Integer id) throws Exception {
        return basicUnitDao.deleteBasicUnit(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicUnit
     * @return
     * @throws Exception
     */
    public List<BasicUnit> basicUnitList(BasicUnit basicUnit) throws Exception {
        if (basicUnit == null) {
            return null;
        }
        return basicUnitDao.basicUnitList(basicUnit);
    }


    public BootstrapTableVo getBootstrapTableVo(BasicUnit basicUnit) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicUnit> basicUnitList = basicUnitDao.basicUnitList(basicUnit);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicUnitList) ? new ArrayList<BasicUnit>(10) : basicUnitList);
        return vo;
    }


    /**
     * 清理无效数据
     *
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void clearInvalidData(Integer applyId) throws Exception {
        BasicUnit where = new BasicUnit();
        where.setApplyId(applyId);
        if (applyId.equals(0))
            where.setCreator(commonService.thisUserAccount());
        List<BasicUnit> unitList = basicUnitDao.basicUnitList(where);
        if (CollectionUtils.isEmpty(unitList)) return;
        BasicUnit unit = unitList.get(0);

        StringBuilder sqlBulder = new StringBuilder();
        String baseSql = "delete from %s where unit_id=%s;";
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class), unit.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class), unit.getId()));
        sqlBulder.append(String.format(baseSql, FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class), unit.getId()));

        sqlBulder.append(String.format("delete from %s where id=%s;", FormatUtils.entityNameConvertToTableName(BasicUnit.class), unit.getId()));
        ddlMySqlAssist.customTableDdl(sqlBulder.toString());
    }


    /**
     * 获取数据
     *
     * @param applyId
     * @return
     * @throws Exception
     */
    public BasicUnit getBasicUnitByApplyId(Integer applyId) throws Exception {
        BasicUnit where = new BasicUnit();
        where.setApplyId(applyId);
        if (applyId == null || applyId == 0) {
            where.setCreator(commonService.thisUserAccount());
        }
        List<BasicUnit> basicUnits = basicUnitDao.basicUnitList(where);
        if (CollectionUtils.isEmpty(basicUnits)) return null;
        return basicUnits.get(0);
    }

    /**
     * 添加楼盘及土地基本信息
     *
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicUnit addUnit(String unitNumber) throws Exception {
        this.clearInvalidData(0);
        BasicUnit basicUnit = new BasicUnit();
        basicUnit.setUnitNumber(unitNumber);
        basicUnit.setApplyId(0);
        basicUnit.setCreator(commonService.thisUserAccount());
        basicUnitDao.addBasicUnit(basicUnit);
        return basicUnit;
    }


    /**
     * 将 CaseUnit 下的子类 转移到 BasicUnit下的子类中去 (用做过程数据)
     *
     * @param caseUnitId
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public BasicUnit appWriteUnit(Integer caseUnitId, String unitPartInMode, Integer applyId) throws Exception {
        if (caseUnitId == null) {
            throw new BusinessException("null point");
        }
        CaseUnit caseUnit = caseUnitService.getCaseUnitById(caseUnitId);
        if (caseUnit == null) {
            throw new BusinessException("null point");
        }
        applyId = applyId == null ? 0 : applyId;
        this.clearInvalidData(applyId);
        BasicUnit basicUnit = new BasicUnit();
        BeanUtils.copyProperties(caseUnit, basicUnit);
        basicUnit.setApplyId(applyId);
        basicUnit.setCreator(commonService.thisUserAccount());
        basicUnit.setGmtCreated(null);
        basicUnit.setGmtModified(null);
        if (StringUtils.equals(unitPartInMode, BasicApplyPartInModeEnum.REFERENCE.getKey())) {
            basicUnit.setUnitNumber(null);
        }
        basicUnitDao.addBasicUnit(basicUnit);

        //附件拷贝
        SysAttachmentDto example = new SysAttachmentDto();
        example.setTableId(caseUnit.getId());
        example.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnit.class));
        SysAttachmentDto attachmentDto = new SysAttachmentDto();
        attachmentDto.setTableId(basicUnit.getId());
        attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        baseAttachmentService.copyFtpAttachments(example,attachmentDto);

        try {
            List<CaseUnitHuxingVo> caseUnitHuxingList = null;
            CaseUnitHuxing query = new CaseUnitHuxing();
            query.setUnitId(caseUnitId);
            caseUnitHuxingList = caseUnitHuxingService.getCaseUnitHuxingList(query);
            if (!ObjectUtils.isEmpty(caseUnitHuxingList)) {
                for (CaseUnitHuxingVo caseUnitHuxing : caseUnitHuxingList) {
                    BasicUnitHuxing basicUnitHuxing = new BasicUnitHuxing();
                    BeanUtils.copyProperties(caseUnitHuxing, basicUnitHuxing);
                    basicUnitHuxing.setUnitId(basicUnit.getId());
                    basicUnitHuxing.setId(null);
                    basicUnitHuxing.setGmtCreated(null);
                    basicUnitHuxing.setGmtModified(null);
                    Integer id = basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(basicUnitHuxing);

                    //附件拷贝
                    example = new SysAttachmentDto();
                    example.setTableId(caseUnitHuxing.getId());
                    example.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnitHuxing.class));
                    attachmentDto = new SysAttachmentDto();
                    attachmentDto.setTableId(id);
                    attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                    baseAttachmentService.copyFtpAttachments(example,attachmentDto);
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
        }

        StringBuilder sqlBuilder = new StringBuilder();
        SynchronousDataDto synchronousDataDto = new SynchronousDataDto();
        HashMap<String, String> map = Maps.newHashMap();
        map.put("unit_id", String.valueOf(basicUnit.getId()));
        map.put("creator", commonService.thisUserAccount());
        synchronousDataDto.setFieldDefaultValue(map);
        synchronousDataDto.setWhereSql("unit_id=" + caseUnit.getId());
        synchronousDataDto.setSourceDataBase(BaseConstant.DATABASE_PMCC_ASSESS_CASE);
        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseUnitDecorate.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//楼栋内装sql

        synchronousDataDto.setSourceTable(FormatUtils.entityNameConvertToTableName(CaseUnitElevator.class));
        synchronousDataDto.setTargeTable(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
        sqlBuilder.append(publicService.getSynchronousSql(synchronousDataDto));//配备电梯sql

        sqlBuilder.append(basicEstateService.copyTaggingFromCase(EstateTaggingTypeEnum.UNIT, caseUnit.getId(), applyId));
        ddlMySqlAssist.customTableDdl(sqlBuilder.toString());//执行sql

        return basicUnit;
    }

}
