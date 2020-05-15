package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicApplyTypeEnum;
import com.copower.pmcc.assess.common.enums.ProjectStatusEnum;
import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyDao;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectPlanDetailsDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.basic.BasicApplyVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import com.copower.pmcc.assess.service.data.DataBlockService;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.bpm.api.dto.ProcessUserDto;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.dto.model.BoxReDto;
import com.copower.pmcc.bpm.api.dto.model.ProcessInfo;
import com.copower.pmcc.bpm.api.provider.BpmRpcBoxService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 2018-10-24.
 */
@Service
public class BasicApplyService {
    @Autowired
    private BasicApplyDao basicApplyDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public BasicApply getByBasicApplyId(Integer id) {
        return basicApplyDao.getBasicApplyById(id);
    }

    public BasicApply getBasicApplyByHouseId(Integer houseId) {
        if (houseId == null || houseId <= 0) return null;
        return basicApplyDao.getBasicApplyByHouseId(houseId);
    }

    public List<BasicApply> getListByDeclareRecordId(Integer declareRecordId) {
        BasicApply where = new BasicApply();
        where.setDeclareRecordId(declareRecordId);
        List<BasicApply> basicApplyList = basicApplyDao.getBasicApplyList(where);
        if (CollectionUtils.isEmpty(basicApplyList)) return null;
        return basicApplyList;
    }

    public BasicApply getBasicApplyByPlanDetailsId(Integer planDetailsId) {
        List<BasicApply> basicApplies = getBasicApplyListByPlanDetailsId(planDetailsId);
        if (!ObjectUtils.isEmpty(basicApplies)) {
            return basicApplies.get(0);
        } else {
            return null;
        }
    }

    public List<BasicApply> getBasicApplyListByPlanDetailsId(Integer planDetailsId) {
        BasicApply basicApply = new BasicApply();
        basicApply.setPlanDetailsId(planDetailsId);
        List<BasicApply> basicApplies = basicApplyDao.getBasicApplyList(basicApply);
        return basicApplies;
    }

    public Integer saveBasicApply(BasicApply basicApply) {
        if (basicApply.getId() == null || basicApply.getId() == 0) {
            basicApply.setCreator(commonService.thisUserAccount());
            basicApplyDao.addBasicApply(basicApply);
        } else {
            basicApplyDao.updateBasicApply(basicApply);
        }
        return basicApply.getId();
    }

    public boolean updateBasicApply(BasicApply basicApply) {
        return basicApplyDao.updateBasicApply(basicApply);
    }

    public void deleteBasicApplyById(Integer id) {
        basicApplyDao.deleteBasicApply(id);
    }

    public List<BasicApply> getBasicApplyListByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) return null;
        return basicApplyDao.getBasicApplyListByIds(ids);
    }

    public List<BasicApply> getBasicApplysByBatchDetailIds(List<Integer> batchDetailIds) {
        List<BasicApply> list = basicApplyDao.getBasicApplysByBatchDetailIds(batchDetailIds);
        return list;
    }

    /**
     * 根据apply表中名称
     * @param batchDetailId
     */
    public void updateNameByBatchDetailId(Integer batchDetailId) {
        List<BasicApplyBatchDetail> houseBatchDetailList = basicApplyBatchDetailService.getHouseBatchDetailList(batchDetailId);
        if (CollectionUtils.isEmpty(houseBatchDetailList)) return;
        for (BasicApplyBatchDetail basicApplyBatchDetail : houseBatchDetailList) {
            BasicApply basicApply = getBasicApplyByHouseId(basicApplyBatchDetail.getTableId());
            if (basicApply == null) continue;
            List<KeyValueDto> keyValueDtos = JSON.parseArray(basicApply.getStructuralInfo(), KeyValueDto.class);
            StringBuilder stringBuilder = new StringBuilder();
            for (KeyValueDto keyValueDto : keyValueDtos) {
                if(StringUtils.isNotBlank(keyValueDto.getExplain())){
                    BasicApplyBatchDetail batchDetail = basicApplyBatchDetailService.getCacheBasicApplyBatchDetailById(Integer.valueOf(keyValueDto.getExplain()));
                    if (batchDetail != null) {
                        stringBuilder.append(batchDetail.getName()).append("/");
                    }
                }
            }
            basicApply.setName(StringUtils.strip(stringBuilder.toString(), "/"));
            updateBasicApply(basicApply);
        }
    }
}
