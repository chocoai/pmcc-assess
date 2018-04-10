package com.copower.pmcc.assess.service;

import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.assess.dal.entity.ChksApprovalInfo;
import com.copower.pmcc.assess.dal.dao.ChksApprovalInfoDao;
import com.copower.pmcc.assess.dto.output.ChksApprovalInfoVo;
import com.copower.pmcc.erp.api.dto.SysUserDto;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/3/21
 * @time: 17:05
 */
@Service
public class ChksApprovalService {

    @Autowired
    private ChksApprovalInfoDao chksApprovalInfoDao;
    @Autowired
    private BpmRpcBoxService bpmRpcBoxService;
    @Autowired
    private ErpRpcUserService erpRpcUserService;

    private ChksApprovalInfoVo getChksApprovalVoInfo(Integer boxId, Integer activityId, String processInsId) {
        ChksApprovalInfo chksApprovalInfo = new ChksApprovalInfo();
        chksApprovalInfo.setBoxId(boxId);
        chksApprovalInfo.setBoxActivityId(activityId);
        chksApprovalInfo.setProcessInsId(processInsId);
        List<ChksApprovalInfo> chksApprovalInfoList = chksApprovalInfoDao.getChksApprovalInfoList(chksApprovalInfo);
        if (CollectionUtils.isNotEmpty(chksApprovalInfoList)) {
            chksApprovalInfo = chksApprovalInfoList.get(0);
            return getChksApprovalInfoVo(chksApprovalInfo);

        } else {
            return null;
        }
    }

    public ChksApprovalInfo getChksApprovalInfoById(Integer id) {
        return chksApprovalInfoDao.getChksApprovalInfoById(id);
    }

    public ChksApprovalInfoVo getChksApprovalInfoItem(Integer boxId, Integer activityId, String processInsId) {
        return getChksApprovalVoInfo(boxId, activityId, processInsId);
    }

    public void updateChksApprovalInfo(ChksApprovalInfo chksApprovalInfo) {
        chksApprovalInfoDao.updateChksApprovalInfo(chksApprovalInfo);
    }

    public void updateChksApprovalInfo(Integer id, String userAccount) {
        chksApprovalInfoDao.updateChksApprovalInfo(id, userAccount);
    }

    public void addChksApprovalInfo(Integer boxId, Integer activityId, String processInsId, String appKey, String userAccount) {
        ChksApprovalInfo chksApprovalInfo = new ChksApprovalInfo();
        chksApprovalInfo.setBoxId(boxId);
        chksApprovalInfo.setBoxActivityId(activityId);
        chksApprovalInfo.setProcessInsId(processInsId);
        chksApprovalInfo.setAppKey(appKey);
        chksApprovalInfo.setPersonString(userAccount);
        chksApprovalInfo.setBisChks(false);
        chksApprovalInfoDao.addChksApprovalInfo(chksApprovalInfo);
    }

    public List<ChksApprovalInfo> getChksApprovalInfoList(String processInsId) {
        ChksApprovalInfo chksApprovalInfo = new ChksApprovalInfo();
        chksApprovalInfo.setProcessInsId(processInsId);
        return chksApprovalInfoDao.getChksApprovalInfoList(chksApprovalInfo);
    }

    public List<ChksApprovalInfoVo> getChksApprovalInfoVoList(String processInsId) {
        List<ChksApprovalInfo> chksApprovalInfoList = getChksApprovalInfoList(processInsId);
        List<ChksApprovalInfoVo> transform = LangUtils.transform(chksApprovalInfoList, o -> getChksApprovalInfoVo(o));
        return transform;
    }

    public ChksApprovalInfoVo getChksApprovalInfoVo(ChksApprovalInfo chksApprovalInfo) {
        ChksApprovalInfoVo chksApprovalInfoVo = new ChksApprovalInfoVo();
        if (chksApprovalInfo == null) {
            return chksApprovalInfoVo;
        }

        BeanUtils.copyProperties(chksApprovalInfo, chksApprovalInfoVo);
        if (chksApprovalInfo.getBoxActivityId() != null) {
            BoxReActivityDto boxReActivityDto = bpmRpcBoxService.getBoxreActivityInfoById(chksApprovalInfo.getBoxActivityId());
            if (boxReActivityDto != null) {
                chksApprovalInfoVo.setActivityName(boxReActivityDto.getCnName());
            }
        }
        if (StringUtils.isNotBlank(chksApprovalInfo.getPersonString())) {
            List<SysUserDto> sysUserList = erpRpcUserService.getSysUserList(FormatUtils.transformString2List(chksApprovalInfo.getPersonString()));
            chksApprovalInfoVo.setSysUserDtos(sysUserList);
            if (CollectionUtils.isNotEmpty(sysUserList)) {
                List<String> transform = LangUtils.transform(sysUserList, o -> o.getUserName());
                chksApprovalInfoVo.setUserName(FormatUtils.transformListString(transform));
            }
        }
        return chksApprovalInfoVo;
    }

}
