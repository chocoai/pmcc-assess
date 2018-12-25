package com.copower.pmcc.assess.service.project.initiate;

import com.copower.pmcc.assess.common.enums.InitiateContactsEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.initiate.InitiateContactsDao;
import com.copower.pmcc.assess.dal.basis.entity.InitiateContacts;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.CrmCustomerService;
import com.copower.pmcc.crm.api.dto.CrmCustomerLinkmanDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Ordering;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
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
    private InitiateUnitInformationService unitInformationService;

    public InitiateContacts get(Integer id) {
        return dao.get(id);
    }

    public void copyContacts(String str, InitiateContacts initiateContacts) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(str)) {
            List<Integer> ids = new ArrayList<>(10);
            for (String id : str.split(",")) {
                if (NumberUtils.isNumber(id)) {
                    ids.add(Integer.parseInt(id));
                }
            }
            List<InitiateContacts> initiateContactsList = dao.getByIds(ids);
            if (!ObjectUtils.isEmpty(initiateContactsList)) {
                for (InitiateContacts contacts:initiateContactsList){
                    contacts.setcType(initiateContacts.getcType());
                    contacts.setcPid(initiateContacts.getcPid());
                    contacts.setCustomerId(initiateContacts.getCustomerId());
                    contacts.setCrmId(initiateContacts.getCrmId());
                    contacts.setCreator(commonService.thisUserAccount());
                    contacts.setId(null);
                    dao.save(contacts);
                }
            }
        }
    }

    public boolean saveUpdateInitiateContacts(InitiateContacts initiateContacts) {
        if (initiateContacts == null) {
            return false;
        }
        if (initiateContacts.getId() == null || initiateContacts.getId().intValue() == 0) {
            initiateContacts.setCreator(commonService.thisUserAccount());
            Integer id = dao.save(initiateContacts);
            initiateContacts.setId(id);
            return true;
        } else {
            return dao.update(initiateContacts);
        }
    }

    public BootstrapTableVo getBootstrapTableVo(InitiateContacts initiateContacts) {
        if (initiateContacts == null) {
            return null;
        }
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<InitiateContacts> contactsList = dao.initiateContactsList(initiateContacts);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(contactsList) ? new ArrayList<InitiateContacts>(1) : contactsList);
        return vo;
    }

    public List<InitiateContacts> initiateContactsList(InitiateContacts initiateContacts) {
        return dao.initiateContactsList(initiateContacts);
    }

    public void clear(InitiateContacts initiateContacts) {
        dao.clear(initiateContacts);
    }

    /**
     * 从crm中输入数据
     *
     * @param customerId
     * @param cType
     * @return
     */
    public void writeContacts(Integer customerId, Integer cType, Integer pid) {
        if (customerId != null) {
            List<CrmCustomerLinkmanDto> linkmanDtos = crmCustomerService.getCustomerLinkmanList(customerId);
            try {
                Ordering<CrmCustomerLinkmanDto> firstOrdering = Ordering.from(new Comparator<CrmCustomerLinkmanDto>() {
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
                    InitiateContacts contactsDto = new InitiateContacts();
                    if (contactsDto != null) {
                        contactsDto.setcPid(pid);
                        contactsDto.setcDept(crmCustomerLinkmanDto.getDepartment());
                        contactsDto.setCrmId(String.valueOf(crmCustomerLinkmanDto.getId()));
                        contactsDto.setcName(crmCustomerLinkmanDto.getName());
                        contactsDto.setcEmail(crmCustomerLinkmanDto.getEmail());
                        contactsDto.setcPhone(crmCustomerLinkmanDto.getPhoneNumber());
                        contactsDto.setcType(cType);
                        contactsDto.setCreator(commonService.thisUserAccount());
                        contactsDto.setCustomerId(String.valueOf(customerId));
                        //为了使得页面刷新不至于再次写入数据,因此需要校验 已经写入的数据不再写入了
                        List<InitiateContacts> contactsList = dao.getList(pid, cType, null, customerId, crmCustomerLinkmanDto.getId());
                        if (contactsList.size() == 0) {
                            dao.save(contactsDto);
                        }
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
            //只有报告使用单位才能回写
            if (cType.equals(InitiateContactsEnum.UNIT_INFORMATION.getId())) {
                //CRM中暂时没有提供方法
                InitiateUnitInformationVo unitInformationVo = unitInformationService.getDataByProjectId(projectID);
                if (unitInformationVo != null) {
                    InitiateContacts query = new InitiateContacts();
                    query.setcType(cType);
                    query.setcPid(unitInformationVo.getId());
                    List<InitiateContacts> contactsVos = initiateContactsList(query);
                    for (InitiateContacts contacts : contactsVos) {
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
                                //需要更新的crm
                                if (!StringUtils.isEmpty(tempString)) {
                                    Integer crmID = Integer.parseInt(tempString);
                                    crmCustomerService.updateCrmCustomer(crmCustomer);
                                } else {
                                    //需要添加进去的crm
                                    crmCustomerService.saveCrmCustomer(crmCustomer);
                                }
                            } catch (Exception e) {
                                try {
                                    logger.error(String.format("exception: ======> ", e.getMessage()), e);
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


    /**
     * 更新主表的id值
     *
     * @param pid
     * @param flag
     */
    public void update(int pid, int flag) {
        InitiateContacts query = new InitiateContacts();
        query.setcPid(0);
        query.setcType(flag);
        query.setCreator(commonService.thisUserAccount());
        List<InitiateContacts> contactsList = initiateContactsList(query);
        if (!ObjectUtils.isEmpty(contactsList)) {
            for (InitiateContacts contact : contactsList) {
                contact.setcPid(pid);
                dao.update(contact);
            }
        }
    }


    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    public boolean update(InitiateContacts dto) {
        return dao.update(dto);
    }


    public boolean remove(Integer pid, Integer type) {
        return dao.remove(pid, type);
    }

}
