package com.copower.pmcc.assess.service;

import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.assess.dal.dao.ChksApprovalInfoDao;
import com.copower.pmcc.assess.dal.entity.ChksApprovalBusiness;
import com.copower.pmcc.assess.dal.dao.ChksApprovalBusinessDao;
import com.copower.pmcc.assess.dal.entity.ChksApprovalInfo;
import com.copower.pmcc.assess.dto.output.ChksApprovalBusinessVo;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/28
 * @time: 17:48
 */
@Service
public class ChksApprovalBusinessService {
    @Autowired
    private ChksApprovalBusinessDao chksApprovalBusinessDao;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private ChksApprovalInfoDao chksApprovalInfoDao;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;

    public Integer addChksApprovalBusiness(String appKey, String processInsId, Integer boxId) {
        ChksApprovalBusiness chksApprovalBusiness = new ChksApprovalBusiness();
        chksApprovalBusiness.setAppkey(appKey);
        chksApprovalBusiness.setBusinessProcessInsId(processInsId);
        chksApprovalBusiness.setBusinessBoxId(boxId);

        BoxReDto boxReDto = bpmRpcBoxService.getBoxReInfoByBoxId(boxId);
        chksApprovalBusiness.setBusinessDetailsUrl(boxReDto.getProcessDisplayUrl());
        chksApprovalBusinessDao.addChksApprovalBusiness(chksApprovalBusiness);
        return chksApprovalBusiness.getId();
    }

    public void updateChksApprovalBusinessEnable(Integer id) {

        ChksApprovalBusiness chksApprovalBusiness = chksApprovalBusinessDao.getChksApprovalBusinessById(id);

        ChksApprovalInfo chksApprovalInfo = new ChksApprovalInfo();
        chksApprovalInfo.setProcessInsId(chksApprovalBusiness.getBusinessProcessInsId());
        List<ChksApprovalInfo> chksApprovalInfoList = chksApprovalInfoDao.getChksApprovalInfoList(chksApprovalInfo);
        if (chksApprovalInfoList.size() > 1) {
            chksApprovalBusiness.setFirstChks(chksApprovalInfoList.get(1).getPersonString());
        }

        chksApprovalBusiness.setBisEnable(true);
        chksApprovalBusinessDao.updateChksApprovalBusiness(chksApprovalBusiness);
    }

    public void updateChksApprovalBusiness(ChksApprovalBusiness chksApprovalBusiness) {
        chksApprovalBusinessDao.updateChksApprovalBusiness(chksApprovalBusiness);
    }

    public List<ChksApprovalBusinessVo> getChksApprovalBusinessList(Boolean bisCheck,String search) {
        ChksApprovalBusiness chksApprovalBusiness = new ChksApprovalBusiness();
        if (bisCheck != null) {
            chksApprovalBusiness.setBisCheck(bisCheck);
        }
        if (!erpRpcToolsService.userIsAdmin(serviceComponent.getThisUser())) {
            chksApprovalBusiness.setFirstChks(serviceComponent.getThisUser());
        }
        List<ChksApprovalBusiness> chksApprovalBusinessList = chksApprovalBusinessDao.getChksApprovalBusinessList(chksApprovalBusiness,search);
        List<ChksApprovalBusinessVo> transform = LangUtils.transform(chksApprovalBusinessList, o -> getChksApprovalBusinessVo(o));
        return transform;
    }

    public ChksApprovalBusinessVo getChksApprovalBusinessVo(ChksApprovalBusiness chksApprovalBusiness) {
        ChksApprovalBusinessVo chksApprovalBusinessVo = new ChksApprovalBusinessVo();
        if (chksApprovalBusiness == null) {
            return chksApprovalBusinessVo;
        }
        BeanUtils.copyProperties(chksApprovalBusiness, chksApprovalBusinessVo);
        chksApprovalBusinessVo.setBusinessUrl(String.format("/%s%s?processInsId=%s", chksApprovalBusiness.getAppkey(), chksApprovalBusiness.getBusinessDetailsUrl(), chksApprovalBusiness
                .getBusinessProcessInsId()));
        return chksApprovalBusinessVo;
    }

    public ChksApprovalBusinessVo getChksByBusinessProcessInsId(String processInsId) {
        ChksApprovalBusiness chksApprovalBusiness = new ChksApprovalBusiness();
        chksApprovalBusiness.setBusinessProcessInsId(processInsId);
        List<ChksApprovalBusiness> chksApprovalBusinessList = chksApprovalBusinessDao.getChksApprovalBusinessList(chksApprovalBusiness,"");
        if (CollectionUtils.isNotEmpty(chksApprovalBusinessList)) {
            return getChksApprovalBusinessVo(chksApprovalBusinessList.get(0));
        }
        return null;
    }

    public ChksApprovalBusinessVo getChksByProcessInsId(String processInsId) {
        ChksApprovalBusiness chksApprovalBusiness = new ChksApprovalBusiness();
        chksApprovalBusiness.setProcessInsId(processInsId);
        List<ChksApprovalBusiness> chksApprovalBusinessList = chksApprovalBusinessDao.getChksApprovalBusinessList(chksApprovalBusiness,"");
        if (CollectionUtils.isNotEmpty(chksApprovalBusinessList)) {
            return getChksApprovalBusinessVo(chksApprovalBusinessList.get(0));
        }
        return null;
    }

    public ChksApprovalBusiness getChksApprovalBusinessById(Integer id) {
        return chksApprovalBusinessDao.getChksApprovalBusinessById(id);
    }
}
