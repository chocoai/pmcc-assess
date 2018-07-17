package com.copower.pmcc.assess.service.project.initiate;

import com.copower.pmcc.assess.common.enums.InitiateContactsEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.initiate.InitiateContactsDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.InitiateContacts;
import com.copower.pmcc.assess.dto.input.project.initiate.InitiateContactsDto;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateContactsVo;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.CrmCustomerService;
import com.copower.pmcc.crm.api.dto.CrmCustomerLinkmanDto;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Ordering;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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
    @Autowired
    private ProjectInfoDao projectInfoDao;
    @Autowired
    private InitiateUnitInformationService unitInformationService;

    /**
     * 从crm中输入数据
     *
     * @param customerId
     * @param cType
     * @return
     */
    @Transactional
    public void writeContacts(Integer customerId, Integer cType, Integer pid) {
        List<CrmCustomerLinkmanDto> writeCustomer = new ArrayList<>();
        if (customerId != null) {
            List<CrmCustomerLinkmanDto> linkmanDtos = crmCustomerService.getCustomerLinkmanList(customerId);
            try {
                Ordering<CrmCustomerLinkmanDto> firstOrdering  = Ordering.from(new Comparator<CrmCustomerLinkmanDto>() {
                    @Override
                    public int compare(CrmCustomerLinkmanDto o1, CrmCustomerLinkmanDto o2) {
                        return o1.getId().compareTo(o2.getId());
                    }
                }).reverse();//排序 并且反转
                //排序之后 取5个写入本地
                Collections.sort(linkmanDtos, firstOrdering);
                int temp = 5;
                for (int i = 0; i < temp; i++) {
                    CrmCustomerLinkmanDto crmCustomerLinkmanDto = linkmanDtos.get(i);
                    InitiateContactsDto contactsDto = new InitiateContactsDto();
                    if (contactsDto != null) {
                        contactsDto.setcPid(pid);
                        contactsDto.setcDept(crmCustomerLinkmanDto.getDepartment());
                        contactsDto.setCrmId(String.valueOf(crmCustomerLinkmanDto.getId()));
                        contactsDto.setcName(crmCustomerLinkmanDto.getName());
                        contactsDto.setcEmail(crmCustomerLinkmanDto.getEmail());
                        contactsDto.setcPhone(crmCustomerLinkmanDto.getPhoneNumber());
                        contactsDto.setcType(cType);
                        contactsDto.setCreator(commonService.thisUserAccount());
                        dao.save(contactsDto);
                    }

                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        } else {
        }
    }

    /**
     * 回写到CRM中
     *
     * @param projectID
     * @param cType
     */
    public void writeCrmCustomerDto(Integer projectID, Integer cType) {
        if (!ObjectUtils.isEmpty(cType)) {
            if (cType.equals(InitiateContactsEnum.UNIT_INFORMATION.getId())) {//只有报告使用单位才能回写
                //CRM中暂时没有提供方法
                InitiateUnitInformationVo unitInformationVo = unitInformationService.getDataByProjectId(projectID);
                if (unitInformationVo != null) {
                    List<InitiateContactsVo> contactsVos = getVoList(unitInformationVo.getId(), cType);
                    for (InitiateContactsVo contacts : contactsVos) {
                        String tempString = contacts.getCrmId();
                        String uUseUnit = unitInformationVo.getuUseUnit();
                        if (!StringUtils.isEmpty(uUseUnit)) {
                            //标记一下这里容易出现问题 ==>  customerID
                            try {
                                Integer customerID = Integer.parseInt(uUseUnit);
                                CrmCustomerLinkmanDto crmCustomer = new CrmCustomerLinkmanDto();
                                crmCustomer.setPhoneNumber(contacts.getcPhone());
                                crmCustomer.setEmail(contacts.getcEmail());
                                crmCustomer.setName(contacts.getcName());
                                crmCustomer.setDepartment(contacts.getcDept());
                                crmCustomer.setCustomerId(customerID);
                                crmCustomer.setCustomerManager(null);
                                crmCustomer.setOtherContact(null);
                                if (!StringUtils.isEmpty(tempString)) {//需要更新的crm
                                    Integer crmID = Integer.parseInt(tempString);
                                    crmCustomerService.updateCrmCustomer(crmCustomer);
                                } else {//需要添加进去的crm
                                    crmCustomerService.saveCrmCustomer(crmCustomer);
                                }
                            } catch (Exception e) {
                                try {
                                    logger.error("exception: ======> " + e.getMessage());
                                    throw e;
                                } catch (Exception e1) {

                                }
                            }

                        }

                    }
                }
            }
        }
    }

    @Transactional
    public boolean add(InitiateContactsDto dto) {
        if ((dto.getCreator() == null)) dto.setCreator(commonService.thisUserAccount());
        if (dto.getGmtCreated() == null) dto.setGmtCreated(new Date());
        if (dto.getcPid() == null || dto.getcPid() == 0) {
            dto.setcPid(InitiateContactsDto.C_PID);
        }
        return dao.add(dto);
    }

    /*更新主表的id值*/
    public void update(int pid, int flag) {
        dao.update(pid, flag, commonService.thisUserAccount());
    }

    public InitiateContactsVo get(Integer id) {
        InitiateContacts initiateContacts = dao.get(id);
        InitiateContactsVo vo = new InitiateContactsVo();
        BeanUtils.copyProperties(initiateContacts,vo);
        return vo;
    }

    public InitiateContactsDto getById(Integer id) {
        InitiateContacts initiateContacts = dao.get(id);
        InitiateContactsDto vo = new InitiateContactsDto();
        BeanUtils.copyProperties(initiateContacts,vo);
        return vo;
    }

    @Transactional
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    public boolean update(InitiateContactsDto dto) {

        return dao.update(dto);
    }

    public List<InitiateContactsVo> getVoList(Integer cPid, Integer cType) {
        List<InitiateContactsVo> vos = new ArrayList<>();
        dao.getList(cPid, cType, null).parallelStream().forEach(oo -> vos.add(change(oo)));
        return vos;
    }

    private InitiateContactsDto change(InitiateContactsVo vo) {
        InitiateContactsDto dto = new InitiateContactsDto();
        BeanUtils.copyProperties(vo, dto);
        return dto;
    }

    private InitiateContactsVo change(InitiateContacts initiateContacts) {
        InitiateContactsVo vo = new InitiateContactsVo();
        BeanUtils.copyProperties(initiateContacts, vo);
        return vo;
    }

    public Map<String, String> getTypeMap() {
        Map<String, String> map = new HashMap<>();
        map.put("" + InitiateContactsEnum.CONSIGNOR.getId(), InitiateContactsEnum.CONSIGNOR.getName());
        map.put("" + InitiateContactsEnum.POSSESSOR.getId(), InitiateContactsEnum.POSSESSOR.getName());
        map.put("" + InitiateContactsEnum.UNIT_INFORMATION.getId(), InitiateContactsEnum.UNIT_INFORMATION.getName());
        return map;
    }
}
