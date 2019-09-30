package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.enums.data.DataInfrastructureEnum;
import com.copower.pmcc.assess.dal.basis.dao.data.DataInfrastructureDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
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
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
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
    @Autowired
    private DataInfrastructureChildrenService dataInfrastructureChildrenService;
    @Autowired
    private DataInfrastructureDao dataInfrastructureDao;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private CommonService commonService;

    public List<InfrastructureVo> infrastructureList(DataInfrastructure infrastructure) {
        List<DataInfrastructure> infrastructureList = getDataInfrastructureList(infrastructure);
        List<InfrastructureVo> vos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(infrastructureList)) {
            for (DataInfrastructure oo : infrastructureList) {
                vos.add(getInfrastructureVo(oo));
            }
        }
        return vos;
    }

    public List<DataInfrastructure> getDataInfrastructureList(DataInfrastructure infrastructure){
        return dataInfrastructureDao.getDataInfrastructureListA(infrastructure) ;
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

    public DataInfrastructure getDataInfrastructure(Integer id){
        return dataInfrastructureDao.getDataInfrastructure(id) ;
    }


    public void saveAndUpdateInfrastructure(DataInfrastructure infrastructure) throws Exception {
        if (infrastructure.getId() == null || infrastructure.getId() == 0) {
            infrastructure.setCreator(commonService.thisUserAccount());
            dataInfrastructureDao.addDataInfrastructure(infrastructure);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DataInfrastructure.class), infrastructure.getId());
        } else {
            dataInfrastructureDao.update(infrastructure);
        }
    }

    /**
     * 测算方法使用
     * @param province
     * @param city
     * @param district
     * @return
     */
    public List<InfrastructureVo>  calculatingMethod(String province,String city,String district){
        List<InfrastructureVo> voList = Lists.newArrayList();
        DataInfrastructure query = new DataInfrastructure();
        if (StringUtils.isNotBlank(province)){
            query.setProvince(province);
        }
        if (StringUtils.isNotBlank(city)){
            query.setCity(city);
        }
        if (StringUtils.isNotBlank(district)){
            query.setDistrict(district);
        }
        List<DataInfrastructure> dataInfrastructures =  getDataInfrastructureList(query) ;
        if (CollectionUtils.isEmpty(dataInfrastructures)){
            query.setDistrict(null);
            dataInfrastructures =  getDataInfrastructureList(query) ;
        }
        if (CollectionUtils.isEmpty(dataInfrastructures)){
            query.setCity(null);
            dataInfrastructures =  getDataInfrastructureList(query) ;
        }
        if (CollectionUtils.isNotEmpty(dataInfrastructures)){
            dataInfrastructures.forEach( oo -> {
                InfrastructureVo vo = getInfrastructureVo(oo) ;
                DataInfrastructureChildren select = new DataInfrastructureChildren();
                select.setPid(oo.getId());
                List<DataInfrastructureChildren> dataInfrastructureChildrenList = dataInfrastructureChildrenService.getDataInfrastructureChildrenList(select) ;
                if (CollectionUtils.isNotEmpty(dataInfrastructureChildrenList)){
                    BigDecimal bigDecimal = new BigDecimal(0) ;
                    for (DataInfrastructureChildren po:dataInfrastructureChildrenList){
                        if (po.getNumber() != null){
                            bigDecimal = bigDecimal.add(po.getNumber()) ;
                        }
                    }
                    if (Objects.equal(DataInfrastructureEnum.CommunalFacilities.getName(),oo.getType())){
                        vo.setCommunalFacilities(bigDecimal);
                    }
                    if (Objects.equal(DataInfrastructureEnum.devTaxTotal.getName(),oo.getType())){
                        vo.setDevTaxTotal(bigDecimal);
                    }
                    if (Objects.equal(DataInfrastructureEnum.InfrastructureSupportingFacilities.getName(),oo.getType())){
                        vo.setInfrastructureSupportingFacilities(bigDecimal);
                    }
                }
                voList.add(vo) ;
            });
        }
        return voList ;
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
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(infrastructure.getId(), null, FormatUtils.entityNameConvertToTableName(DataInfrastructure.class));
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
