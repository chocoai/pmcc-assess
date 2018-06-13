package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.enums.InitiateContactsEnum;
import com.copower.pmcc.assess.dal.dao.InitiateContactsDao;
import com.copower.pmcc.assess.dto.input.project.InitiateContactsDto;
import com.copower.pmcc.assess.dto.output.project.InitiateContactsVo;
import com.copower.pmcc.assess.service.CrmCustomerService;
import com.copower.pmcc.crm.api.dto.CrmCustomerDto;
import com.copower.pmcc.crm.api.dto.CrmCustomerLinkmanDto;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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


    /**
     * 从crm中输入数据
     * @param crmId
     * @param cType
     * @return
     */
    @Transactional
    public void writeContacts(Integer crmId,Integer cType,Integer pid){
        List<CrmCustomerLinkmanDto> writeCustomer = new ArrayList<>();
        if (crmId!=null){
            List<CrmCustomerLinkmanDto> linkmanDtos = crmCustomerService.getCustomerLinkmanList(crmId);
            InitiateContactsVo vo = null;
            try {
                List<InitiateContactsDto> dtos = new ArrayList<>();
                for (CrmCustomerLinkmanDto dto:linkmanDtos){
                    InitiateContactsDto contactsDto = new InitiateContactsDto();
                    contactsDto.setcDept(dto.getDepartment());
                    contactsDto.setcEmail(dto.getEmail());
                    contactsDto.setcName(dto.getName());
                    contactsDto.setcPhone(dto.getPhoneNumber());
                    contactsDto.setcPid(pid);
                    contactsDto.setcType(cType);
                    dtos.add(contactsDto);
                }

                //排序之后 取5个写入本地 接着把5个最新的存入CRM中
                Collections.sort(dtos,new Comparator<Object>(){
                    @Override
                    public int compare(Object o1, Object o2) {
                        InitiateContactsDto contactsA = (InitiateContactsDto)o1;
                        InitiateContactsDto contactsB = (InitiateContactsDto)o2;
                        if (contactsA.getGmtCreated()!=null && contactsB.getGmtCreated()!=null){
                            return contactsA.getGmtCreated().compareTo(contactsB.getGmtCreated());
                        }
                        return 0;
                    }
                });//暂时不处理
                int temp = 5;
                for (int i = 0; i < temp; i++) {
                    CrmCustomerLinkmanDto crmCustomerLinkmanDto = new CrmCustomerLinkmanDto();
                    InitiateContactsDto contactsDto = dtos.get(i);
                    if (contactsDto != null){
                        crmCustomerLinkmanDto.setName(contactsDto.getcName());
                        crmCustomerLinkmanDto.setDepartment(contactsDto.getcDept());
                        crmCustomerLinkmanDto.setEmail(contactsDto.getcEmail());
                        crmCustomerLinkmanDto.setPhoneNumber(contactsDto.getcPhone());
                        contactsDto.setCreator(commonService.thisUserAccount());
                        int id = dao.save(contactsDto);
                        writeCustomer.add(crmCustomerLinkmanDto);
                    }

                }
            }catch (Exception e){
                logger.error(e.getMessage());
            }
        }else {
        }
        writeCrmCustomerDto(writeCustomer,cType);
    }

    /**
     * 回写到CRM中
     * @param writeCustomer
     * @param cType
     */
    public void writeCrmCustomerDto(List<CrmCustomerLinkmanDto> writeCustomer,Integer cType){
        if (!ObjectUtils.isEmpty(cType)){
            if (cType.equals(InitiateContactsEnum.THREE.getNum())){//只有报告使用单位才能回写
                //CRM中暂时没有提供方法
            }
        }
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
    public void update(int pid, int flag){
        dao.update(pid,flag,commonService.thisUserAccount());
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

    public List<InitiateContactsVo> getVoList(Integer cPid,Integer cType) {
        List<InitiateContactsVo> vos = new ArrayList<>();
        dao.getList(cPid,cType,commonService.thisUserAccount()).parallelStream().forEach(oo -> vos.add(change(oo)));
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

    /**
     * 联系人 检查数量是否达到要求
     * @param initiateContactsEnum
     * @return
     */
    public boolean checkContacts(InitiateContactsEnum initiateContactsEnum){
        boolean flag = false;
        List<InitiateContactsDto> contactsDtos = dao.getList(InitiateContactsEnum.Zero.getNum(),initiateContactsEnum.getNum(),commonService.thisUserAccount());
        if (contactsDtos.size()>=1){
            flag = true;
        }
        return flag;
    }

    public Map<String,String> getTypeMap(){
        Map<String,String> map = new HashMap<>();
        map.put(""+ InitiateContactsEnum.ONE.getNum(),InitiateContactsEnum.CONTACTS_ENUM_A.getVal());
        map.put(""+ InitiateContactsEnum.TWO.getNum(),InitiateContactsEnum.CONTACTS_ENUM_B.getVal());
        map.put(""+ InitiateContactsEnum.THREE.getNum(),InitiateContactsEnum.CONTACTS_ENUM_C.getVal());
        return map;
    }
}
