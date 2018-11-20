package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basic.dao.BasicUnitDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicUnit;
import com.copower.pmcc.assess.dal.basic.entity.BasicUnitDecorate;
import com.copower.pmcc.assess.dal.basic.entity.BasicUnitElevator;
import com.copower.pmcc.assess.dal.basic.entity.BasicUnitHuxing;
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
import com.google.common.collect.Ordering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void initUpdateSon(Integer oldId, Integer newId, BasicUnit basicUnit) throws Exception {
        BasicUnitHuxing queryBasicUnitHuxing = new BasicUnitHuxing();
        BasicUnitElevator queryBasicUnitElevator = new BasicUnitElevator();
        BasicUnitDecorate queryBasicUnitDecorate = new BasicUnitDecorate();
        SysAttachmentDto query = new SysAttachmentDto();
        queryBasicUnitHuxing.setUnitId(oldId);
        queryBasicUnitElevator.setUnitId(oldId);
        queryBasicUnitDecorate.setUnitId(oldId);
        query.setTableId(oldId);
        queryBasicUnitHuxing.setCreator(commonService.thisUserAccount());
        queryBasicUnitElevator.setCreator(commonService.thisUserAccount());
        queryBasicUnitDecorate.setCreator(commonService.thisUserAccount());
        query.setCreater(commonService.thisUserAccount());
        query.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnit.class));
        List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingService.basicUnitHuxingList(queryBasicUnitHuxing);
        List<BasicUnitElevator> basicUnitElevatorList = basicUnitElevatorService.basicUnitElevatorList(queryBasicUnitElevator);
        List<BasicUnitDecorate> basicUnitDecorateList = basicUnitDecorateService.basicUnitDecorateList(queryBasicUnitDecorate);
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getAttachmentList(query);
        if (newId == null) {
            if (!ObjectUtils.isEmpty(basicUnitHuxingList)) {
                for (BasicUnitHuxing oo : basicUnitHuxingList) {
                    basicUnitHuxingService.deleteBasicUnitHuxing(oo.getId());
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
            if (!ObjectUtils.isEmpty(sysAttachmentDtoList)){
                for (SysAttachmentDto sysAttachmentDto:sysAttachmentDtoList){
                    baseAttachmentService.deleteAttachment(sysAttachmentDto.getId());
                }
            }
        }
        if (newId != null) {
            if (!ObjectUtils.isEmpty(basicUnitHuxingList)) {
                for (BasicUnitHuxing oo : basicUnitHuxingList) {
                    oo.setUnitId(newId);
                    oo.setTemporary(basicUnit.getTemporary());
                    basicUnitHuxingService.saveAndUpdateBasicUnitHuxing(oo);
                }
            }
            if (!ObjectUtils.isEmpty(basicUnitElevatorList)) {
                for (BasicUnitElevator oo : basicUnitElevatorList) {
                    oo.setUnitId(newId);
                    oo.setTemporary(basicUnit.getTemporary());
                    basicUnitElevatorService.saveAndUpdateBasicUnitElevator(oo);
                }
            }
            if (!ObjectUtils.isEmpty(basicUnitDecorateList)) {
                for (BasicUnitDecorate oo : basicUnitDecorateList) {
                    oo.setUnitId(newId);
                    oo.setTemporary(basicUnit.getTemporary());
                    basicUnitDecorateService.saveAndUpdateBasicUnitDecorate(oo);
                }
            }
        }
    }

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
            Integer id = basicUnitDao.saveBasicUnit(basicUnit);
            return id;
        } else {
            basicUnitDao.updateBasicUnit(basicUnit);
            return null;
        }
    }

    public Integer upgradeVersion(BasicUnit basicUnit) throws Exception {
        if (basicUnit.getId() == null || basicUnit.getId().intValue() == 0) {
            basicUnit.setCreator(commonService.thisUserAccount());
            basicUnit.setVersion(0);
            Integer id = basicUnitDao.saveBasicUnit(basicUnit);
            this.initUpdateSon(0, id, basicUnit);
            basicUnit.setId(id);
            return id;
        } else {
            BasicUnit oo = this.getBasicUnitById(basicUnit.getId());
            if (oo.getVersion() == null) {
                oo.setVersion(0);
            }
            basicUnit.setVersion(oo.getVersion() + 1);
            basicUnitDao.updateBasicUnit(basicUnit);
            return basicUnit.getId();
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

}
