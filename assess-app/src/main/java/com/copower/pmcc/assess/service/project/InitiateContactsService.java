package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.InitiateContactsDao;
import com.copower.pmcc.assess.dto.input.project.InitiateContactsDto;
import com.copower.pmcc.assess.dto.output.project.InitiateContactsVo;
import com.copower.pmcc.assess.service.CrmCustomerService;
import com.copower.pmcc.crm.api.dto.CrmCustomerLinkmanDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 委托人信息
 * Created by 13426 on 2018/5/4.
 */
@Service
public class InitiateContactsService {

    @Lazy
    @Autowired(required = true)
    private CrmCustomerService crmCustomerService;

    @Autowired
    private InitiateContactsDao dao;

    public List<InitiateContactsVo> listVo(Integer crmId){
        List<InitiateContactsVo> vos = new ArrayList<>();
        if (crmId!=null){
            List<CrmCustomerLinkmanDto> linkmanDtos = crmCustomerService.getCustomerLinkmanList(crmId);
            InitiateContactsVo vo = null;
            for (CrmCustomerLinkmanDto dto:linkmanDtos){
                vo = new InitiateContactsVo();
                vo.setId(dto.getId());
                vo.setcEmail(dto.getEmail());
                vo.setcDept(dto.getDepartment());
                vo.setcName(dto.getName());
                vo.setcPhone(dto.getPhoneNumber());
                vos.add(vo);
            }
        }else {
            vos = getVoList(InitiateContactsDto.CPID);
        }
        return vos;
    }

    public boolean add(InitiateContactsDto dto) {
        dto.setcPid(InitiateContactsDto.CPID);
        return dao.add(dto);
    }

    public InitiateContactsVo get(Integer id) {
        return change(dao.get(id));
    }

    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    public boolean update(InitiateContactsDto dto) {
        return dao.update(dto);
    }

    private List<InitiateContactsVo> getVoList(Integer cPid) {
        List<InitiateContactsVo> vos = new ArrayList<>();
        if (cPid==null){
            dao.getList().parallelStream().forEach(oo -> vos.add(change(oo)));
        }else {
            dao.getList(cPid).parallelStream().forEach(oo -> vos.add(change(oo)));
        }
        return vos;
    }

    private InitiateContactsDto change(InitiateContactsVo vo) {
        InitiateContactsDto dto = new InitiateContactsDto();
        BeanUtils.copyProperties(vo, dto);
        return dto;
    }

    private InitiateContactsVo change(InitiateContactsDto dto) {
        InitiateContactsVo vo = new InitiateContactsVo();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }
}
