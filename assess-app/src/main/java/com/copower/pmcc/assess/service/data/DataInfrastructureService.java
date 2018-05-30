package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.BaseAttachmentDao;
import com.copower.pmcc.assess.dal.dao.DataInfrastructureDao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.Infrastructure;
import com.copower.pmcc.assess.dto.input.data.InfrastructureDto;
import com.copower.pmcc.assess.dto.output.data.InfrastructureVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private BaseAttachmentDao baseAttachmentDao;
    @Autowired
    private CommonService commonService;

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public BootstrapTableVo getInfrastructure(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<Infrastructure> infrastructureList = dataInfrastructureDao.getInfrastructureList(name);
        List<InfrastructureVo> vos = new ArrayList<>();
        for (Infrastructure infrastructure : infrastructureList) {
            vos.add(change(infrastructure));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<InfrastructureVo>() : vos);
        return vo;
    }

    public InfrastructureVo get(Integer id){
        Infrastructure infrastructure = dataInfrastructureDao.get(id);
        return change(infrastructure);
    }

    @Transactional
    public boolean addInfrastructure(InfrastructureDto infrastructureDto) throws BusinessException {
        Infrastructure infrastructure = new Infrastructure();
        infrastructureDto.setCreator(processControllerComponent.getThisUser());
        BeanUtils.copyProperties(infrastructureDto, infrastructure);
        int id = dataInfrastructureDao.addInfrastructure(infrastructure);
        update_BaseAttachment(id,InfrastructureVo.fileName);
        return id >0;
    }
    //修改附件中的table id 以及存附件的主表的附件id
    public boolean update_BaseAttachment(int pid, String fields_name){
        int TEMP = 0;
        List<BaseAttachment> baseAttachments = baseAttachmentDao.getByField_tableId(TEMP, fields_name);
        //一般都只有一个
        if (baseAttachments.size()>0){
            BaseAttachment baseAttachment = baseAttachments.get(0);
            // 更新 存附件的主表
            baseAttachment.setTableId(pid);
            baseAttachmentDao.updateAttachment(baseAttachment);
            Infrastructure infrastructure = dataInfrastructureDao.get(pid);
            infrastructure.setFileName(baseAttachment.getFileName());
            dataInfrastructureDao.update(infrastructure);
            return true;
        }else {
            return false;
        }
    }

    @Transactional
    public boolean editInfrastructure(InfrastructureDto dto) throws BusinessException {
        Infrastructure infrastructure = new Infrastructure();
        dto.setCreator(processControllerComponent.getThisUser());
        BeanUtils.copyProperties(dto, infrastructure);
        boolean flag = update_BaseAttachment(infrastructure.getId(),InfrastructureVo.fileName);
       try {
           InfrastructureVo vo = get(dto.getId());
           infrastructure.setFileName(vo.getFileName());
           infrastructure.setCreator(vo.getCreator());
           dataInfrastructureDao.update(infrastructure);
           flag = true;
       }catch (Exception e){
           flag = false;
           logger.error("----------------->错误!");
       }
        return flag;
    }

    public boolean deleteInfrastructure(Integer id) {
        boolean flag = false;
        flag = dataInfrastructureDao.deleteInfrastructure(id);
        return flag;
    }

    public InfrastructureVo change(Infrastructure infrastructure) {
        InfrastructureVo vo = new InfrastructureVo();
        BeanUtils.copyProperties(infrastructure, vo);
        vo.setProvinceName(getProvinceName(Integer.parseInt(infrastructure.getProvince())));
        vo.setCityName(getSysArea(Integer.parseInt(infrastructure.getCity())));
        vo.setDistrictName(getSysArea(Integer.parseInt(infrastructure.getDistrict())));
        return vo;
    }

    /*省名称*/
    public String getProvinceName(int pid) {
        List<SysAreaDto> provinceLists = erpAreaService.getProvinceList();
        String s = provinceAndArea(pid, provinceLists);
        return s;
    }

    public String provinceAndArea(int id, List<SysAreaDto> sysAreaDtos) {
        String v = "";
        inner:
        for (SysAreaDto s : sysAreaDtos) {
            if (id == s.getId()) {
                v = s.getName();
                break inner;
            }
        }
        return v;
    }

    /*城市名称*/
    public String getSysArea(Integer id) {
        return erpAreaService.getSysAreaDto(String.valueOf(id)).getName();
    }
}
