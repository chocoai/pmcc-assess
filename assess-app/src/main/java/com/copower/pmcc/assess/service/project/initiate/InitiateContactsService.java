package com.copower.pmcc.assess.service.project.initiate;

import com.copower.pmcc.assess.common.enums.InitiateContactsEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.initiate.InitiateContactsDao;
import com.copower.pmcc.assess.dal.basis.entity.InitiateContacts;
import com.copower.pmcc.assess.dto.output.project.initiate.InitiateUnitInformationVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.CrmCustomerService;
import com.copower.pmcc.crm.api.dto.CrmCustomerLinkmanDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import com.google.common.collect.Ordering;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 联系人
 * Created by 13426 on 2018/5/4.
 */
@Service
public class InitiateContactsService {
    @Autowired
    private BaseService baseService;
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
        if (org.apache.commons.lang3.StringUtils.isEmpty(str)) {
            return;
        }
        List<Integer> ids = new ArrayList<>(10);
        for (String id : str.split(",")) {
            if (NumberUtils.isNumber(id)) {
                ids.add(Integer.parseInt(id));
            }
        }
        List<InitiateContacts> initiateContactsList = dao.getByIds(ids);
        if (CollectionUtils.isEmpty(initiateContactsList)) {
            return;
        }
        for (InitiateContacts contacts : initiateContactsList) {
            contacts.setcType(initiateContacts.getcType());
            contacts.setcPid(initiateContacts.getcPid());
            contacts.setCustomerId(initiateContacts.getCustomerId());
            contacts.setCrmId(initiateContacts.getCrmId());
            contacts.setCreator(commonService.thisUserAccount());
            contacts.setId(null);
            dao.save(contacts);
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

    public List<InitiateContacts> getList(Integer cPid, Integer cType) {
        return dao.getList(cPid, cType, null, null, null);
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
        if (customerId == null) {
            return;
        }
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
            baseService.writeExceptionInfo(e);
        }
    }

    /**
     * 回写到CRM中
     *
     * @param projectID
     * @param cType
     */
    public void writeCrmCustomerDto(Integer projectID, Integer cType) {
        if (cType == null) {
            return;
        }
        if (projectID == null) {
            return;
        }
        if (!Objects.equal(InitiateContactsEnum.UNIT_INFORMATION.getId(), cType)) {
            return;
        }
        InitiateUnitInformationVo unitInformationVo = unitInformationService.getDataByProjectId(projectID);
        if (unitInformationVo == null) {
            return;
        }
        if (StringUtils.isEmpty(unitInformationVo.getuUseUnit())) {
            return;
        }
        InitiateContacts query = new InitiateContacts();
        query.setcType(cType);
        query.setcPid(unitInformationVo.getId());
        List<InitiateContacts> contactsVos = initiateContactsList(query);
        if (CollectionUtils.isEmpty(contactsVos)) {
            return;
        }
        for (InitiateContacts contacts : contactsVos) {
            CrmCustomerLinkmanDto crmCustomer = new CrmCustomerLinkmanDto();
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(contacts.getcPhone())) {
                crmCustomer.setPhoneNumber(contacts.getcPhone());
            }
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(contacts.getcEmail())) {
                crmCustomer.setEmail(contacts.getcEmail());
            }
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(contacts.getcName())) {
                crmCustomer.setName(contacts.getcName());
            }
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(contacts.getcDept())) {
                crmCustomer.setDepartment(contacts.getcDept());
            }
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(contacts.getCreator())) {

            }
            crmCustomer.setCustomerId(Integer.parseInt(unitInformationVo.getuUseUnit()));
            crmCustomer.setCustomerManager(null);
            crmCustomer.setOtherContact(null);
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(contacts.getCrmId())) {
                crmCustomer.setId(Integer.parseInt(contacts.getCrmId()));
            }
            if (crmCustomer.getId() == null || crmCustomer.getId() == 0) {
                //需要添加进去的crm
                crmCustomerService.saveCrmCustomer(crmCustomer);
            } else {
                //需要更新的crm
                crmCustomerService.updateCrmCustomer(crmCustomer);
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
        if (CollectionUtils.isEmpty(contactsList)) {
            return;
        }
        for (InitiateContacts contact : contactsList) {
            contact.setcPid(pid);
            dao.update(contact);
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
