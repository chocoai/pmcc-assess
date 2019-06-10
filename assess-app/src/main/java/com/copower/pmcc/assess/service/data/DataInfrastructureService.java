package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataInfrastructureDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.input.data.InfrastructureDto;
import com.copower.pmcc.assess.dto.output.data.InfrastructureVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础设施及公共配套设施维护
 *
 * @author liuwei
 */
@Service(value = "dataInfrastructureService")
public class DataInfrastructureService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DataInfrastructureDao dataInfrastructureDao;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CommonService commonService;

    public List<InfrastructureVo> infrastructureList(DataInfrastructure infrastructure) {
        List<DataInfrastructure> infrastructureList = dataInfrastructureDao.getDataInfrastructureListA(infrastructure);
        List<InfrastructureVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(infrastructureList)) {
            for (DataInfrastructure oo : infrastructureList) {
                vos.add(getInfrastructureVo(oo));
            }
        }
        return vos;
    }

    public BootstrapTableVo getInfrastructure(DataInfrastructure infrastructure) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<InfrastructureVo> vos = infrastructureList(infrastructure);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<InfrastructureVo>() : vos);
        return vo;
    }

    public InfrastructureVo get(Integer id) {
        DataInfrastructure infrastructure = dataInfrastructureDao.get(id);
        return getInfrastructureVo(infrastructure);
    }


    public void saveAndUpdateInfrastructure(InfrastructureDto infrastructureDto) throws Exception {
        DataInfrastructure infrastructure = new DataInfrastructure();
        infrastructureDto.setCreator(commonService.thisUserAccount());
        BeanUtils.copyProperties(infrastructureDto, infrastructure);
        if (infrastructure.getId() == null || infrastructure.getId().intValue() == 0) {
            int id = dataInfrastructureDao.addDataInfrastructure(infrastructure);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DataInfrastructure.class), id);
            baseAttachmentService.updateTableIdByTableName("tb_data_infrastructure", id);
        } else {
            dataInfrastructureDao.update(infrastructure);
        }
    }

    /**
     * 更新基础设施费合计
     * @param id
     * @param costTotal
     */
    public void updateCostTotal(Integer id, BigDecimal costTotal){
        DataInfrastructure infrastructure = dataInfrastructureDao.get(id);
        infrastructure.setCostTotal(costTotal);
        dataInfrastructureDao.update(infrastructure);
    }

    /**
     * 更新配套设施费合计
     * @param id
     * @param matchingCostTotal
     */
    public void updateMatchingCostTotal(Integer id, BigDecimal matchingCostTotal){
        DataInfrastructure infrastructure = dataInfrastructureDao.get(id);
        infrastructure.setMatchingCostTotal(matchingCostTotal);
        dataInfrastructureDao.update(infrastructure);
    }

    /**
     * 更新开发税费合计
     * @param id
     * @param devTaxTotal
     */
    public void updateDevTaxTotal(Integer id, BigDecimal devTaxTotal){
        DataInfrastructure infrastructure = dataInfrastructureDao.get(id);
        infrastructure.setDevTaxTotal(devTaxTotal);
        dataInfrastructureDao.update(infrastructure);
    }


    public void deleteInfrastructure(Integer id) throws Exception {
        dataInfrastructureDao.deleteDataInfrastructure(id);
    }

    public InfrastructureVo getInfrastructureVo(DataInfrastructure infrastructure) {
        InfrastructureVo vo = new InfrastructureVo();
        BeanUtils.copyProperties(infrastructure, vo);
        //省
        if (StringUtils.isNotBlank(infrastructure.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(infrastructure.getProvince()));
        }
        //市
        if (StringUtils.isNotBlank(infrastructure.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(infrastructure.getCity()));
        }
        //县
        if (StringUtils.isNotBlank(infrastructure.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(infrastructure.getDistrict()));
        }
        if (!ObjectUtils.isEmpty(infrastructure.getEndDate())) {
            vo.setEndDateName(DateUtils.format(infrastructure.getEndDate(),DateUtils.DATE_CHINESE_PATTERN));
        }
        if (!ObjectUtils.isEmpty(infrastructure.getStartDate())) {
            vo.setStartDateName(DateUtils.format(infrastructure.getStartDate(),DateUtils.DATE_CHINESE_PATTERN));
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(infrastructure.getId(), null, "tb_data_infrastructure");
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            if (sysAttachmentDtos.size() >= 1) {
                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                    if (sysAttachmentDto != null) {
                        builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto));
                        builder.append(" ");
                    }
                }
            }
            vo.setFileViewName(builder.toString());
        }
        return vo;
    }
}
