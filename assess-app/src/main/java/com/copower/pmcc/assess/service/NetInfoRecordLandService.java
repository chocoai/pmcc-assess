package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordLandDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordLandVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcAttachmentService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
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
public class NetInfoRecordLandService {
    @Autowired
    private NetInfoRecordLandDao netInfoRecordLandDao;
    @Autowired
    private NetInfoRecordDao netInfoRecordDao;
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

    public NetInfoRecordLand saveAndUpdateNetInfoRecordLand(NetInfoRecordLand netInfoRecordLand) {
        if (netInfoRecordLand.getId() == null) {
            netInfoRecordLand.setCreator(commonService.thisUserAccount());
            netInfoRecordLandDao.addNetInfoRecordLand(netInfoRecordLand);
            //更新附件id
            SysAttachmentDto queryParam = new SysAttachmentDto();
            queryParam.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordLand.class));
            queryParam.setTableId(0);
            queryParam.setCreater(commonService.thisUserAccount());
            queryParam.setAppKey(applicationConstant.getAppKey());
            SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
            sysAttachmentDto.setTableId(netInfoRecordLand.getId());
            erpRpcAttachmentService.updateAttachmentByParam(queryParam, sysAttachmentDto);
            return netInfoRecordLand;
        } else {
            NetInfoRecordLand recordLand = getNetInfoRecordLandById(netInfoRecordLand.getId());
            netInfoRecordLand.setStatus(recordLand.getStatus());
            netInfoRecordLand.setCreator(commonService.thisUserAccount());
            netInfoRecordLandDao.updateNetInfoRecordLand(netInfoRecordLand, true);
            return netInfoRecordLand;
        }
    }

    public NetInfoRecordLand getNetInfoRecordLandById(Integer id) {
        return netInfoRecordLandDao.getNetInfoRecordLandById(id);
    }

    public BootstrapTableVo getNetInfoRecordLandListVos(Integer status, String province, String city, String district, String street, String name) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetInfoRecordLand> netInfoRecordLands = netInfoRecordLandDao.getNetInfoRecordLandList(status, province, city, district, street, name);
        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordLand.class));
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(netInfoRecordLands, o -> o.getId()), where);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(netInfoRecordLands == null ? new ArrayList() : LangUtils.transform(netInfoRecordLands, o -> getNetInfoRecordLandVo(o, attachmentDtos)));
        return bootstrapTableVo;
    }

    public BootstrapTableVo getLandListByMasterId(Integer masterId) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        NetInfoRecordLand netInfoRecordLand = new NetInfoRecordLand();
        netInfoRecordLand.setMasterId(masterId);
        List<NetInfoRecordLand> netInfoRecordLands = netInfoRecordLandDao.getNetInfoRecordLandList(netInfoRecordLand);
        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordLand.class));
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(netInfoRecordLands, o -> o.getId()), where);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(netInfoRecordLands == null ? new ArrayList() : LangUtils.transform(netInfoRecordLands, o -> getNetInfoRecordLandVo(o, attachmentDtos)));
        return bootstrapTableVo;
    }


    public List<NetInfoRecordLandVo> getVos(NetInfoRecordLand netInfoRecordLand) {
        List<NetInfoRecordLand> netInfoRecordLands = netInfoRecordLandDao.getNetInfoRecordLandList(netInfoRecordLand);
        SysAttachmentDto where = new SysAttachmentDto();
        where.setTableName(FormatUtils.entityNameConvertToTableName(NetInfoRecordLand.class));
        List<SysAttachmentDto> attachmentDtos = baseAttachmentService.getAttachmentList(LangUtils.transform(netInfoRecordLands, o -> o.getId()), where);

        List<NetInfoRecordLandVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(netInfoRecordLands)) {
            for (NetInfoRecordLand landLevel : netInfoRecordLands) {
                vos.add(getNetInfoRecordLandVo(landLevel, attachmentDtos));
            }
        }
        return vos;
    }

    public void removeNetInfoRecordLand(Integer id) {
        netInfoRecordLandDao.deleteInfo(id);
    }

    public NetInfoRecordLandVo getNetInfoRecordLandVo(NetInfoRecordLand netInfoRecordLand, List<SysAttachmentDto> attachmentDtos) {
        NetInfoRecordLandVo vo = new NetInfoRecordLandVo();
        BaseDataDic baseDataDic = null;
        BeanUtils.copyProperties(netInfoRecordLand, vo);
        if (netInfoRecordLand.getDealType() != null) {
            baseDataDic = baseDataDicService.getDataDicById(netInfoRecordLand.getDealType());
            if (baseDataDic != null) {
                vo.setDealTypeName(baseDataDic.getName());
            }
        }
        if (StringUtils.isNotEmpty(netInfoRecordLand.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(netInfoRecordLand.getProvince()));
        }
        if (StringUtils.isNotEmpty(netInfoRecordLand.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(netInfoRecordLand.getCity()));
        }
        if (StringUtils.isNotEmpty(netInfoRecordLand.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(netInfoRecordLand.getDistrict()));
        }
        if (!CollectionUtils.isEmpty(attachmentDtos)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (SysAttachmentDto attachmentDto : attachmentDtos) {
                if (attachmentDto.getTableId().equals(netInfoRecordLand.getId())) {
                    stringBuilder.append(baseAttachmentService.getEditHtml(attachmentDto, false));
                }
            }
            vo.setFileViewName(stringBuilder.toString());
        }
        NetInfoRecord record = netInfoRecordDao.getInfoById(netInfoRecordLand.getMasterId());
        vo.setSourceSiteUrl(record.getSourceSiteUrl());
        return vo;
    }

    //取最新数据
    public NetInfoRecordLand getSingleByMasterId(Integer masterId) {
        NetInfoRecordLand netInfoRecordLand = new NetInfoRecordLand();
        netInfoRecordLand.setMasterId(masterId);
        List<NetInfoRecordLand> netInfoRecordLands = netInfoRecordLandDao.getNetInfoRecordLandList(netInfoRecordLand);
        if (CollectionUtils.isNotEmpty(netInfoRecordLands)) {
            return netInfoRecordLands.get(netInfoRecordLands.size() - 1);
        }
        return null;
    }
}
