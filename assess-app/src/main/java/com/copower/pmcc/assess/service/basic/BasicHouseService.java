package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basic.dao.BasicHouseDao;
import com.copower.pmcc.assess.dal.basic.entity.*;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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
import org.springframework.beans.BeanUtils;
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
public class BasicHouseService {
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseRoomService basicHouseRoomService;
    @Autowired
    private BasicUnitHuxingService basicUnitHuxingService;
    @Autowired
    private BasicHouseTradingLeaseService basicHouseTradingLeaseService;
    @Autowired
    private BasicHouseTradingSellService basicHouseTradingSellService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicHouseDao basicHouseDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void init(Integer oldId, Integer newId) throws Exception {
        List<BasicHouseTradingSell> basicHouseTradingSellList = null;
        List<BasicHouseTradingLease> basicHouseTradingLeaseList = null;
        List<BasicHouseRoom> basicHouseRoomList = null;
        BasicHouseTradingSell querySell = new BasicHouseTradingSell();
        BasicHouseTradingLease queryLease = new BasicHouseTradingLease();
        BasicHouseRoom queryRoom = new BasicHouseRoom();

        queryLease.setHouseId(oldId);
        querySell.setHouseId(oldId);
        queryRoom.setHouseId(oldId);

        basicHouseTradingSellList = basicHouseTradingSellService.basicHouseTradingSells(querySell);
        basicHouseTradingLeaseList = basicHouseTradingLeaseService.basicHouseTradingLeaseList(queryLease);
        basicHouseRoomList = basicHouseRoomService.basicHouseRoomList(queryRoom);
        if (newId == null) {
            if (!ObjectUtils.isEmpty(basicHouseTradingSellList)) {
                basicHouseTradingSellList.forEach(oo -> {
                    try {
                        basicHouseTradingSellService.deleteBasicHouseTradingSell(oo.getId());
                    } catch (Exception e1) {

                    }
                });
            }
            if (!ObjectUtils.isEmpty(basicHouseTradingLeaseList)) {
                basicHouseTradingLeaseList.forEach(oo -> {
                    try {
                        basicHouseTradingLeaseService.deleteBasicHouseTradingLease(oo.getId());
                    } catch (Exception e1) {

                    }
                });
            }
            if (!ObjectUtils.isEmpty(basicHouseRoomList)) {
                basicHouseRoomList.forEach(oo -> {
                    try {
                        basicHouseRoomService.deleteBasicHouseRoom(oo.getId());
                    } catch (Exception e1) {

                    }
                });
            }
        }

        if (newId != null) {
            if (!ObjectUtils.isEmpty(basicHouseTradingSellList)) {
                basicHouseTradingSellList.forEach(oo -> {
                    oo.setHouseId(newId);
                    try {
                        basicHouseTradingSellService.saveAndUpdateBasicHouseTradingSell(oo);
                    } catch (Exception e1) {

                    }
                });
            }
            if (!ObjectUtils.isEmpty(basicHouseTradingLeaseList)) {
                basicHouseTradingLeaseList.forEach(oo -> {
                    oo.setHouseId(newId);
                    try {
                        basicHouseTradingLeaseService.saveAndUpdateBasicHouseTradingLease(oo);
                    } catch (Exception e1) {

                    }
                });
            }
            if (!ObjectUtils.isEmpty(basicHouseRoomList)) {
                basicHouseRoomList.forEach(oo -> {
                    oo.setHouseId(newId);
                    try {
                        basicHouseRoomService.saveAndUpdateBasicHouseRoom(oo);
                    } catch (Exception e1) {

                    }
                });
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
    public BasicHouse getBasicHouseById(Integer id) throws Exception {
        return basicHouseDao.getBasicHouseById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouse
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouse(BasicHouse basicHouse) throws Exception {
        if (basicHouse.getId() == null || basicHouse.getId().intValue() == 0) {
            basicHouse.setCreator(commonService.thisUserAccount());
            return basicHouseDao.saveBasicHouse(basicHouse);
        } else {
            basicHouseDao.updateBasicHouse(basicHouse);
            return null;
        }
    }

    public Integer upgradeVersion(BasicHouse basicHouse) throws Exception {
        if (basicHouse.getId() == null || basicHouse.getId().intValue() == 0) {
            basicHouse.setCreator(commonService.thisUserAccount());
            basicHouse.setVersion(0);
            Integer id = basicHouseDao.saveBasicHouse(basicHouse);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicHouse.class), id);
            this.init(0, id);
            basicHouse.setId(id);
            return id;
        } else {
            BasicHouse oo = getBasicHouseById(basicHouse.getId());
            if (oo.getVersion() == null) {
                oo.setVersion(0);
            }
            basicHouse.setVersion(oo.getVersion() + 1);
            basicHouseDao.updateBasicHouse(basicHouse);
            return basicHouse.getId();
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicHouse(Integer id) throws Exception {
        return basicHouseDao.deleteBasicHouse(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicHouse
     * @return
     * @throws Exception
     */
    public List<BasicHouse> basicHouseList(BasicHouse basicHouse) throws Exception {
        return basicHouseDao.basicHouseList(basicHouse);
    }


    public BootstrapTableVo getBootstrapTableVo(BasicHouse basicHouse) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouse> basicHouseList = basicHouseDao.basicHouseList(basicHouse);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicHouseList) ? new ArrayList<BasicHouse>(10) : basicHouseList);
        return vo;
    }

    public BasicHouseVo getBasicHouseVo(BasicHouse basicHouse) {
        BasicHouseVo vo = new BasicHouseVo();
        BaseDataDic dataDic = null;
        BeanUtils.copyProperties(basicHouse, vo);
        if (basicHouse.getUseEnvironment() != null) {
            dataDic = baseDataDicService.getDataDicById(basicHouse.getUseEnvironment());
            vo.setUseEnvironmentName(dataDic.getName());
            dataDic = null;
        }
        if (basicHouse.getCertUse() != null) {
            dataDic = baseDataDicService.getDataDicById(basicHouse.getCertUse());
            vo.setCertUseName(dataDic.getName());
            dataDic = null;
        }
        if (basicHouse.getPracticalUse() != null) {
            dataDic = baseDataDicService.getDataDicById(basicHouse.getPracticalUse());
            vo.setPracticalUseName(dataDic.getName());
            dataDic = null;
        }
        if (basicHouse.getHuxingId() != null) {
            try {
                BasicUnitHuxing basicUnitHuxing = basicUnitHuxingService.getBasicUnitHuxingById(basicHouse.getHuxingId());
                if (basicUnitHuxing != null) {
                    vo.setHuxingName(basicUnitHuxing.getHouseCategory());
                }
            } catch (Exception e1) {

            }
        }
        return vo;
    }

}
