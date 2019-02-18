package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.BasicApplyPartInModeEnum;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnit;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitDecorate;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitElevator;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitHuxing;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.assess.dto.output.cases.CaseUnitHuxingVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.cases.*;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
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
    private BasicUnitElevatorService basicUnitElevatorService;
    @Autowired
    private BasicUnitDecorateService basicUnitDecorateService;
    @Autowired
    private BasicUnitDao basicUnitDao;
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private CaseUnitHuxingService caseUnitHuxingService;
    @Autowired
    private CaseUnitService caseUnitService;
    @Autowired
    private CaseUnitElevatorService caseUnitElevatorService;
    @Autowired
    private CaseUnitDecorateService caseUnitDecorateService;
    @Autowired
    private BasicEstateTaggingService basicEstateTaggingService;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
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
        BasicUnitHuxing queryBasicUnitHuxing = new BasicUnitHuxing();
        BasicUnitElevator queryBasicUnitElevator = new BasicUnitElevator();
        BasicUnitDecorate queryBasicUnitDecorate = new BasicUnitDecorate();

        queryBasicUnitHuxing.setUnitId(unit.getId());
        queryBasicUnitElevator.setUnitId(unit.getId());
        queryBasicUnitDecorate.setUnitId(unit.getId());

        queryBasicUnitHuxing.setCreator(commonService.thisUserAccount());
        queryBasicUnitElevator.setCreator(commonService.thisUserAccount());
        queryBasicUnitDecorate.setCreator(commonService.thisUserAccount());

        List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingService.basicUnitHuxingList(queryBasicUnitHuxing);
        List<BasicUnitElevator> basicUnitElevatorList = basicUnitElevatorService.basicUnitElevatorList(queryBasicUnitElevator);
        List<BasicUnitDecorate> basicUnitDecorateList = basicUnitDecorateService.basicUnitDecorateList(queryBasicUnitDecorate);

        if (!ObjectUtils.isEmpty(basicUnitHuxingList)) {
            for (BasicUnitHuxing oo : basicUnitHuxingList) {
                basicUnitHuxingService.deleteBasicUnitHuxing(oo.getId());
                //删除户型相关附件
                SysAttachmentDto query = new SysAttachmentDto();
                query.setTableId(oo.getId());
                query.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
                if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                    for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                        baseAttachmentService.deleteAttachment(sysAttachmentDto.getId());
                    }
                }
            }
        }
        if (!ObjectUtils.isEmpty(basicUnitElevatorList)) {
            for (BasicUnitElevator oo : basicUnitElevatorList) {
                basicUnitElevatorService.deleteBasicUnitElevator(oo.getId());
            }
        }
        if (!ObjectUtils.isEmpty(basicUnitDecorateList)) {
            for (BasicUnitDecorate oo : basicUnitDecorateList) {
                basicUnitDecorateService.deleteBasicUnitDecorate(oo.getId());
            }
        }

        basicUnitDao.deleteBasicUnit(unit.getId());
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
        this.clearInvalidData(0);
        if (caseUnitId == null) {
            throw new Exception("null point");
        }
        if (applyId != null) {
            this.clearInvalidData(applyId);
        }
        CaseUnit caseUnit = caseUnitService.getCaseUnitById(caseUnitId);
        if (caseUnit == null) {
            return null;
        }
        BasicUnit basicUnit = new BasicUnit();
        BeanUtils.copyProperties(caseUnit, basicUnit);
        basicUnit.setApplyId(applyId == null ? 0 : applyId);
        basicUnit.setCreator(commonService.thisUserAccount());
        basicUnit.setGmtCreated(null);
        basicUnit.setGmtModified(null);
        if (StringUtils.equals(unitPartInMode, BasicApplyPartInModeEnum.REFERENCE.getKey())) {
            basicUnit.setUnitNumber(null);
        }
        basicUnitDao.addBasicUnit(basicUnit);

        if (StringUtils.equals(unitPartInMode, BasicApplyPartInModeEnum.UPGRADE.getKey())) {

        }
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    CaseEstateTagging caseEstateTagging = new CaseEstateTagging();
                    caseEstateTagging.setDataId(caseUnitId);
                    caseEstateTagging.setType(EstateTaggingTypeEnum.UNIT.getKey());
                    List<CaseEstateTagging> caseEstateTaggings = caseEstateTaggingService.getCaseEstateTaggingList(caseEstateTagging);
                    basicEstateService.copyTaggingFromCase(caseEstateTaggings, applyId);
                } catch (Exception e1) {
                    logger.info("", e1);
                }
            }
        });


        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CaseUnitElevator> caseUnitHuxingList = null;
                    CaseUnitElevator query = new CaseUnitElevator();
                    query.setUnitId(caseUnitId);
                    caseUnitHuxingList = caseUnitElevatorService.getUnitElevatorList(query);
                    if (!ObjectUtils.isEmpty(caseUnitHuxingList)) {
                        for (CaseUnitElevator caseUnitElevator : caseUnitHuxingList) {
                            BasicUnitElevator basicUnitElevator = new BasicUnitElevator();
                            BeanUtils.copyProperties(caseUnitElevator, basicUnitElevator);
                            basicUnitElevator.setUnitId(basicUnit.getId());
                            basicUnitElevator.setId(null);
                            basicUnitElevator.setGmtCreated(null);
                            basicUnitElevator.setGmtModified(null);
                            basicUnitElevatorService.saveAndUpdateBasicUnitElevator(basicUnitElevator);
                        }
                    }
                } catch (Exception e1) {
                    logger.info("", e1);
                }
            }
        });

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CaseUnitDecorate> caseUnitHuxingList = null;
                    CaseUnitDecorate query = new CaseUnitDecorate();
                    query.setUnitId(caseUnitId);
                    caseUnitHuxingList = caseUnitDecorateService.getCaseUnitDecorateList(query);
                    if (!ObjectUtils.isEmpty(caseUnitHuxingList)) {
                        for (CaseUnitDecorate caseUnitDecorate : caseUnitHuxingList) {
                            BasicUnitDecorate basicUnitDecorate = new BasicUnitDecorate();
                            BeanUtils.copyProperties(caseUnitDecorate, basicUnitDecorate);
                            basicUnitDecorate.setUnitId(basicUnit.getId());
                            basicUnitDecorate.setId(null);
                            basicUnitDecorate.setGmtCreated(null);
                            basicUnitDecorate.setGmtModified(null);
                            basicUnitDecorateService.saveAndUpdateBasicUnitDecorate(basicUnitDecorate);
                        }
                    }
                } catch (Exception e1) {
                    logger.info("", e1);
                }
            }
        });

        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
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

                            List<SysAttachmentDto> sysAttachmentDtoList = null;
                            SysAttachmentDto queryAttachment = new SysAttachmentDto();
                            queryAttachment.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnitHuxing.class));
                            queryAttachment.setTableId(caseUnitHuxing.getId());
                            sysAttachmentDtoList = baseAttachmentService.getAttachmentList(queryAttachment);
                            if (!ObjectUtils.isEmpty(sysAttachmentDtoList)) {
                                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                                    SysAttachmentDto attachmentDto = new SysAttachmentDto();
                                    attachmentDto.setTableId(id);
                                    attachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                                    baseAttachmentService.copyFtpAttachment(sysAttachmentDto.getId(), attachmentDto);
                                }
                            }
                        }
                    }
                } catch (Exception e1) {
                    logger.info("", e1);
                }
            }
        });
        return basicUnit;
    }

}
