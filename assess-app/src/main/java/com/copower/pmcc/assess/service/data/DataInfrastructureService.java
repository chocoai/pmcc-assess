package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.DateHelp;
import com.copower.pmcc.assess.dal.basis.dao.data.DataInfrastructureDao;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureCost;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureDevTax;
import com.copower.pmcc.assess.dal.basis.entity.DataInfrastructureMatchingCost;
import com.copower.pmcc.assess.dal.basis.entity.Infrastructure;
import com.copower.pmcc.assess.dto.input.data.InfrastructureDto;
import com.copower.pmcc.assess.dto.output.data.InfrastructureVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
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
    private DataInfrastructureMatchingCostService dataInfrastructureMatchingCostService;
    @Autowired
    private DataInfrastructureCostService dataDataInfrastructureCostService;
    @Autowired
    private DataInfrastructureDevTaxService dataInfrastructureDevTaxService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public List<InfrastructureVo> infrastructureList(Infrastructure infrastructure) {
        List<Infrastructure> infrastructureList = dataInfrastructureDao.getInfrastructureListA(infrastructure);
        List<InfrastructureVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(infrastructureList)) {
            for (Infrastructure oo : infrastructureList) {
                vos.add(getInfrastructureVo(oo));
            }
        }
        return vos;
    }

    public BootstrapTableVo getInfrastructure(Infrastructure infrastructure) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<InfrastructureVo> vos = infrastructureList(infrastructure);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<InfrastructureVo>() : vos);
        return vo;
    }

    public InfrastructureVo get(Integer id) {
        Infrastructure infrastructure = dataInfrastructureDao.get(id);
        return getInfrastructureVo(infrastructure);
    }


    public void saveAndUpdateInfrastructure(InfrastructureDto infrastructureDto) throws Exception {
        Infrastructure infrastructure = new Infrastructure();
        infrastructureDto.setCreator(processControllerComponent.getThisUser());
        BeanUtils.copyProperties(infrastructureDto, infrastructure);
        if (infrastructure.getId() == null || infrastructure.getId().intValue() == 0) {
            int id = dataInfrastructureDao.addInfrastructure(infrastructure);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(Infrastructure.class), id);
            baseAttachmentService.updateTableIdByTableName("tb_data_infrastructure", id);
        } else {
            dataInfrastructureDao.update(infrastructure);
        }
    }


    public void deleteInfrastructure(Integer id) throws Exception {
        dataInfrastructureDao.deleteInfrastructure(id);
    }

    public InfrastructureVo getInfrastructureVo(Infrastructure infrastructure) {
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
            vo.setEndDateName(DateHelp.getDateHelp().printDate(infrastructure.getEndDate()));
        }
        if (!ObjectUtils.isEmpty(infrastructure.getStartDate())) {
            vo.setStartDateName(DateHelp.getDateHelp().printDate(infrastructure.getStartDate()));
        }
        DataInfrastructureCost dataInfrastructureCost = new DataInfrastructureCost();
        DataInfrastructureMatchingCost dataInfrastructureMatchingCost = new DataInfrastructureMatchingCost();
        DataInfrastructureDevTax dataInfrastructureDevTax = new DataInfrastructureDevTax();
        dataInfrastructureCost.setPid(infrastructure.getId());
        dataInfrastructureMatchingCost.setPid(infrastructure.getId());
        dataInfrastructureDevTax.setPid(infrastructure.getId());
        List<DataInfrastructureCost> dataInfrastructureCosts = dataDataInfrastructureCostService.getDataInfrastructureCostList(dataInfrastructureCost);
        List<DataInfrastructureMatchingCost> dataInfrastructureMatchingCosts = dataInfrastructureMatchingCostService.infrastructureMatchingCosts(dataInfrastructureMatchingCost);
        List<DataInfrastructureDevTax> dataInfrastructureDevTaxes = dataInfrastructureDevTaxService.dataInfrastructureDevTaxes(dataInfrastructureDevTax);
        double temp = 0.0;
        if (!ObjectUtils.isEmpty(dataInfrastructureCosts)) {
            for (DataInfrastructureCost cost : dataInfrastructureCosts) {
                if (cost != null) {
                    if (cost.getNumber() != null) {
                        temp += cost.getNumber().doubleValue();
                    }
                }
            }
        }
        vo.setPriceCost(temp);
        temp = 0;
        if (!ObjectUtils.isEmpty(dataInfrastructureMatchingCosts)) {
            for (DataInfrastructureMatchingCost cost : dataInfrastructureMatchingCosts) {
                if (cost != null) {
                    if (cost.getNumber() != null) {
                        temp += cost.getNumber().doubleValue();
                    }
                }
            }
        }
        vo.setPriceMarch(temp);
        temp = 0;
        double xxx = 0;
        if (!ObjectUtils.isEmpty(dataInfrastructureDevTaxes)){
            for (DataInfrastructureDevTax oo:dataInfrastructureDevTaxes){
                if (oo.getNumber() != null){
                    temp += oo.getNumber().doubleValue();
                }
                if (oo.getTax() != null){
                    xxx += oo.getTax().doubleValue();
                }
            }
        }
        vo.setPriceDev(temp);
        vo.setPriceTax(xxx);
        temp = 0;
        xxx = 0;
        //下面此条语句失效了
//        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(infrastructure.getId(), null, FormatUtils.entityNameConvertToTableName(Infrastructure.class));
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
