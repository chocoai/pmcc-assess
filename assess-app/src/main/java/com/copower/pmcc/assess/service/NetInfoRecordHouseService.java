package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordHouseDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordHouse;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordHouseVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcAttachmentService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:53
 */
@Service
public class NetInfoRecordHouseService {
    @Autowired
    private NetInfoRecordHouseDao netInfoRecordHouseDao;

    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ErpRpcAttachmentService erpRpcAttachmentService;
    @Autowired
    private ApplicationConstant applicationConstant;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private NetInfoRecordDao netInfoRecordDao;

    public NetInfoRecordHouse saveAndUpdateNetInfoRecordHouse(NetInfoRecordHouse netInfoRecordHouse) {
        if (netInfoRecordHouse.getId() == null) {
            netInfoRecordHouse.setCreator(commonService.thisUserAccount());
            netInfoRecordHouseDao.addNetInfoRecordHouse(netInfoRecordHouse);
            //更新附件id
            SysAttachmentDto queryParam = new SysAttachmentDto();
            queryParam.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordHouse.class));
            queryParam.setTableId(0);
            queryParam.setCreater(commonService.thisUserAccount());
            queryParam.setAppKey(applicationConstant.getAppKey());
            SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
            sysAttachmentDto.setTableId(netInfoRecordHouse.getId());
            erpRpcAttachmentService.updateAttachmentByParam(queryParam, sysAttachmentDto);
            return netInfoRecordHouse;
        } else {
            NetInfoRecordHouse recordHouse = getNetInfoRecordHouseById(netInfoRecordHouse.getId());
            netInfoRecordHouse.setStatus(recordHouse.getStatus());
            netInfoRecordHouse.setCreator(recordHouse.getCreator());
            netInfoRecordHouse.setGmtCreated(recordHouse.getGmtCreated());
            netInfoRecordHouse.setGmtModified(DateUtils.now());
            netInfoRecordHouse.setVersion(recordHouse.getVersion());
            netInfoRecordHouse.setBisNewest(recordHouse.getBisNewest());
            netInfoRecordHouse.setBeUpgradeId(recordHouse.getBeUpgradeId());
            netInfoRecordHouseDao.updateNetInfoRecordHouse(netInfoRecordHouse, true);
            return netInfoRecordHouse;
        }
    }

    public NetInfoRecordHouse getNetInfoRecordHouseById(Integer id) {
        return netInfoRecordHouseDao.getNetInfoRecordHouseById(id);
    }

    public BootstrapTableVo getNetInfoRecordHouseListVos(Integer status, String province, String city, String district, String street, String name) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetInfoRecordHouse> netInfoRecordHouses = netInfoRecordHouseDao.getNetInfoRecordHouseList(status, province, city, district, street, name);
        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordHouse.class));
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(netInfoRecordHouses, o -> o.getId()), where);

        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(netInfoRecordHouses == null ? new ArrayList() : LangUtils.transform(netInfoRecordHouses, o -> getNetInfoRecordHouseVo(o, attachmentDtos)));
        return bootstrapTableVo;
    }

    public BootstrapTableVo getHouseListByMasterId(Integer masterId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        NetInfoRecordHouse netInfoRecordHouse = new NetInfoRecordHouse();
        netInfoRecordHouse.setMasterId(masterId);
        List<NetInfoRecordHouse> netInfoRecordHouses = netInfoRecordHouseDao.getNetInfoRecordHouseList(netInfoRecordHouse);
        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordHouse.class));
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(netInfoRecordHouses, o -> o.getId()), where);

        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(netInfoRecordHouses == null ? new ArrayList() : LangUtils.transform(netInfoRecordHouses, o -> getNetInfoRecordHouseVo(o, attachmentDtos)));
        return bootstrapTableVo;
    }

    public List<NetInfoRecordHouseVo> getVos(NetInfoRecordHouse netInfoRecordHouse) {
        List<NetInfoRecordHouse> netInfoRecordHouses = netInfoRecordHouseDao.getNetInfoRecordHouseList(netInfoRecordHouse);
        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordHouse.class));
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(netInfoRecordHouses, o -> o.getId()), where);

        List<NetInfoRecordHouseVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(netInfoRecordHouses)) {
            for (NetInfoRecordHouse houseLevel : netInfoRecordHouses) {
                vos.add(getNetInfoRecordHouseVo(houseLevel, attachmentDtos));
            }
        }
        return vos;
    }

    public void removeNetInfoRecordHouse(Integer id) {
        netInfoRecordHouseDao.deleteInfo(id);
    }

    public NetInfoRecordHouseVo getNetInfoRecordHouseVo(NetInfoRecordHouse netInfoRecordHouse, List<SysAttachmentDto> attachmentDtos) {
        NetInfoRecordHouseVo vo = new NetInfoRecordHouseVo();
        BaseDataDic baseDataDic = null;
        BeanUtils.copyProperties(netInfoRecordHouse, vo);
        if (StringUtils.isNotEmpty(netInfoRecordHouse.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(netInfoRecordHouse.getProvince()));
        }
        if (StringUtils.isNotEmpty(netInfoRecordHouse.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(netInfoRecordHouse.getCity()));
        }
        if (StringUtils.isNotEmpty(netInfoRecordHouse.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(netInfoRecordHouse.getDistrict()));
        }
        if (netInfoRecordHouse.getDealType() != null) {
            baseDataDic = baseDataDicService.getDataDicById(netInfoRecordHouse.getDealType());
            if (baseDataDic != null) {
                vo.setDealTypeName(baseDataDic.getName());
            }
        }
        if (netInfoRecordHouse.getTradingType() != null) {
            baseDataDic = baseDataDicService.getDataDicById(netInfoRecordHouse.getTradingType());
            if (baseDataDic != null) {
                vo.setTradingTypeName(baseDataDic.getName());
            }
        }
        if (!CollectionUtils.isEmpty(attachmentDtos)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (SysAttachmentDto attachmentDto : attachmentDtos) {
                if (attachmentDto.getTableId().equals(netInfoRecordHouse.getId())) {
                    stringBuilder.append(baseAttachmentService.getViewHtml(attachmentDto));
                }
            }
            vo.setFileViewName(stringBuilder.toString());
        }
        NetInfoRecord record = netInfoRecordDao.getInfoById(netInfoRecordHouse.getMasterId());
        if (record != null) {
            vo.setSourceSiteUrl(record.getSourceSiteUrl());
        }
        return vo;
    }

    //取最新数据
    public NetInfoRecordHouse getSingleByMasterId(Integer masterId) {
        NetInfoRecordHouse netInfoRecordHouse = new NetInfoRecordHouse();
        netInfoRecordHouse.setMasterId(masterId);
        List<NetInfoRecordHouse> netInfoRecordHouses = netInfoRecordHouseDao.getNetInfoRecordHouseList(netInfoRecordHouse);
        if (CollectionUtils.isNotEmpty(netInfoRecordHouses)) {
            return netInfoRecordHouses.get(netInfoRecordHouses.size() - 1);
        }
        return null;
    }
}
