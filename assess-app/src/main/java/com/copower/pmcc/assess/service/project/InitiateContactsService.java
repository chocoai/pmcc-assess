package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.enums.InitiateContactsEnum;
import com.copower.pmcc.assess.dal.dao.InitiateContactsDao;
import com.copower.pmcc.assess.dto.input.project.InitiateContactsDto;
import com.copower.pmcc.assess.dto.output.project.InitiateContactsVo;
import com.copower.pmcc.assess.service.CrmCustomerService;
import com.copower.pmcc.crm.api.dto.CrmCustomerLinkmanDto;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 联系人
 * Created by 13426 on 2018/5/4.
 */
@Service
public class InitiateContactsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Lazy
    @Autowired
    private CrmCustomerService crmCustomerService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private InitiateContactsDao dao;


    public List<InitiateContactsVo> listVo(Integer crmId,Integer flag){
        List<InitiateContactsVo> vos = new ArrayList<>();
        if (crmId!=null){
            List<CrmCustomerLinkmanDto> linkmanDtos = crmCustomerService.getCustomerLinkmanList(crmId);
            InitiateContactsVo vo = null;
            try {
                for (CrmCustomerLinkmanDto dto:linkmanDtos){
                    vo = new InitiateContactsVo();
                    vo.setId(dto.getId());
                    vo.setcEmail(dto.getEmail());
                    vo.setcDept(dto.getDepartment());
                    vo.setcName(dto.getName());
                    vo.setcPhone(dto.getPhoneNumber());
                    vos.add(vo);
                }
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }else {
            vos = getVoList(InitiateContactsDto.CPID,flag);
        }
        return vos;
    }

    @Transactional
    public boolean add(InitiateContactsDto dto) {
        if ((dto.getCreator()==null))dto.setCreator(commonService.thisUserAccount());
        if (dto.getGmtCreated()==null)dto.setGmtCreated(new Date());
        if (dto.getcPid()==null || dto.getcPid()==0){
            dto.setcPid(InitiateContactsDto.CPID);
        }
        return dao.add(dto);
    }

    /*更新主表的id值*/
    @Transactional
    public void update(int pid, int flag){
        dao.update(pid,flag);
    }

    public InitiateContactsVo get(Integer id) {
        return change(dao.get(id));
    }

    public InitiateContactsDto getById(Integer id){
        return dao.get(id);
    }

    @Transactional
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    public boolean update(InitiateContactsDto dto) {
        return dao.update(dto);
    }

    public List<InitiateContactsVo> getVoList(Integer cPid,Integer flag) {
        List<InitiateContactsVo> vos = new ArrayList<>();
        if (cPid==null && flag!=null){
            dao.getList(flag).parallelStream().forEach(oo -> vos.add(change(oo)));
        }else if (cPid!=null && flag!=null){
            dao.getList(cPid,flag).parallelStream().forEach(oo -> vos.add(change(oo)));
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

    public Map<String,String> getTypeMap(){
        Map<String,String> map = new HashMap<>();
        map.put(""+ InitiateContactsEnum.ONE.getNum(),InitiateContactsEnum.CONTACTS_ENUM_A.getVal());
        map.put(""+ InitiateContactsEnum.TWO.getNum(),InitiateContactsEnum.CONTACTS_ENUM_B.getVal());
        map.put(""+ InitiateContactsEnum.THREE.getNum(),InitiateContactsEnum.CONTACTS_ENUM_C.getVal());
        return map;
    }
}
