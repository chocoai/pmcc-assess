package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.dal.basis.custom.mapper.CustomCaseMapper;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoRecordLandDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecord;
import com.copower.pmcc.assess.dal.basis.entity.NetInfoRecordLand;
import com.copower.pmcc.assess.dto.input.StatisticsDto;
import com.copower.pmcc.assess.dto.input.basic.BasicHouseCaseSummaryParamsDto;
import com.copower.pmcc.assess.dto.output.net.NetInfoRecordLandVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcAttachmentService;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private PublicService publicService;
    @Autowired
    private CustomCaseMapper customCaseMapper;
    @Autowired
    private ErpRpcUserService erpRpcUserService;

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
            netInfoRecordLand.setGmtCreated(recordLand.getGmtCreated());
            netInfoRecordLand.setGmtModified(DateUtils.now());
            netInfoRecordLand.setApprover(recordLand.getApprover());
            netInfoRecordLand.setVersion(recordLand.getVersion());
            netInfoRecordLand.setBisNewest(recordLand.getBisNewest());
            netInfoRecordLand.setBeUpgradeId(recordLand.getBeUpgradeId());
            netInfoRecordLandDao.updateNetInfoRecordLand(netInfoRecordLand, true);
            return netInfoRecordLand;
        }
    }

    public NetInfoRecordLand getNetInfoRecordLandById(Integer id) {
        return netInfoRecordLandDao.getNetInfoRecordLandById(id);
    }

    public BootstrapTableVo getNetInfoRecordLandVoList(String creator, String approver, Date startDate, Date endDate) {
        if (endDate != null) {
            endDate = DateUtils.addDay(endDate, 1);
        }
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<NetInfoRecordLand> netInfoRecordLands = netInfoRecordLandDao.getNetInfoRecordLandList(creator, approver, startDate, endDate);
        bootstrapTableVo.setTotal(page.getTotal());
        List<NetInfoRecordLandVo> vos = CollectionUtils.isNotEmpty(netInfoRecordLands) ? LangUtils.transform(netInfoRecordLands, o -> getNetInfoRecordLandVo(o, null)) : Lists.newArrayList();
        bootstrapTableVo.setRows(vos);
        return bootstrapTableVo;
    }

    public BootstrapTableVo getNetInfoRecordLandListVos(Integer status, String province, String city, String district, String street, Integer belongType, String belongCategory, Integer dealType, String negotiatedDateStart, String negotiatedDateEnd) {
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        Date queryNegotiatedDateStart = null;
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(negotiatedDateStart)) {
            queryNegotiatedDateStart = DateUtils.parse(negotiatedDateStart);
        }
        Date queryNegotiatedDateEnd = null;
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(negotiatedDateEnd)) {
            queryNegotiatedDateEnd = DateUtils.parse(negotiatedDateEnd);
        }
        String queryBelongType = "";
        if (belongType != null) {
            BaseDataDic dic = baseDataDicService.getCacheDataDicById(belongType);
            queryBelongType = dic.getName();
        }
        List<NetInfoRecordLand> netInfoRecordLands = netInfoRecordLandDao.getNetInfoRecordLandList(status, province, city, district, street, queryBelongType, belongCategory, dealType, queryNegotiatedDateStart, queryNegotiatedDateEnd);
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

    public BootstrapTableVo findLandReportAuditStatistics(String creator, String approver, Date startDate, Date endDate) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        NetInfoRecordLand land = new NetInfoRecordLand();
        land.setCreator(creator);
        land.setApprover(approver);
        List<StatisticsDto> list = customCaseMapper.findLandReportAuditStatistics(endDate, startDate, land);
        List<StatisticsDto> statisticsDtoList = LangUtils.transform(list, o -> getStatisticsDto(o));
        vo.setTotal(page.getTotal());
        vo.setRows(statisticsDtoList);
        return vo;
    }

    public BootstrapTableVo findLandReportApplyStatistics(String creator, String approver, Date startDate, Date endDate) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        NetInfoRecordLand land = new NetInfoRecordLand();
        land.setCreator(creator);
        land.setApprover(approver);
        List<com.copower.pmcc.assess.dto.input.StatisticsDto> list = customCaseMapper.findLandReportApplyStatistics(endDate,startDate,land);
        List<com.copower.pmcc.assess.dto.input.StatisticsDto> statisticsDtoList = LangUtils.transform(list, o -> getStatisticsDto(o));
        vo.setTotal(page.getTotal());
        vo.setRows(statisticsDtoList);
        return vo;
    }

    private StatisticsDto getStatisticsDto(com.copower.pmcc.assess.dto.input.StatisticsDto statisticsDto) {
        if (StringUtils.isNotBlank(statisticsDto.getName())) {
            SysUserDto sysUser = erpRpcUserService.getSysUser(statisticsDto.getName());
            if (sysUser != null && org.apache.commons.lang3.StringUtils.isNotBlank(sysUser.getUserName())) {
                statisticsDto.setName(sysUser.getUserName());
            }
        }
        return statisticsDto;
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
        if (netInfoRecordLand.getLandRealizationRatios() != null) {
            vo.setRealizationRatiosStr(ArithmeticUtils.getPercentileSystem(netInfoRecordLand.getLandRealizationRatios(), 4, BigDecimal.ROUND_HALF_UP));
        }
        if (netInfoRecordLand.getGreeningRate() != null) {
            vo.setGreeningRateStr(ArithmeticUtils.getPercentileSystem(netInfoRecordLand.getGreeningRate(), 4, BigDecimal.ROUND_HALF_UP));
        }
        if (netInfoRecordLand.getLandArea() != null) {

            if (StringUtils.isNotEmpty(netInfoRecordLand.getLandAreaUnit()) && netInfoRecordLand.getLandAreaUnit().contains("亩")) {
                vo.setLandAreaMu(netInfoRecordLand.getLandArea());
                vo.setLandAreaCentiare(netInfoRecordLand.getLandArea().multiply(new BigDecimal("666.67").setScale(2, BigDecimal.ROUND_HALF_UP)));
            } else {
                vo.setLandAreaCentiare(netInfoRecordLand.getLandArea());
                vo.setLandAreaMu(netInfoRecordLand.getLandArea().divide(new BigDecimal("666.67"), 2, BigDecimal.ROUND_HALF_UP));
            }
        }
        if (!CollectionUtils.isEmpty(attachmentDtos)) {
            StringBuilder stringBuilder = new StringBuilder();
            for (SysAttachmentDto attachmentDto : attachmentDtos) {
                if (attachmentDto.getTableId().equals(netInfoRecordLand.getId())) {
                    stringBuilder.append(baseAttachmentService.getViewHtml(attachmentDto));
                }
            }
            vo.setFileViewName(stringBuilder.toString());
        }
        if (StringUtils.isNotBlank(netInfoRecordLand.getCreator())) {
            vo.setCreatorName(publicService.getUserNameByAccount(netInfoRecordLand.getCreator()));
        }
        if (StringUtils.isNotBlank(netInfoRecordLand.getApprover())) {
            vo.setApproverName(publicService.getUserNameByAccount(netInfoRecordLand.getApprover()));
        }

        NetInfoRecord record = netInfoRecordDao.getInfoById(netInfoRecordLand.getMasterId());
        if (record != null) {
            vo.setSourceSiteUrl(record.getSourceSiteUrl());
        }
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
