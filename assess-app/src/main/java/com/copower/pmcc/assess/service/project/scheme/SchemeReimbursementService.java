package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReimbursementDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReimbursementItemDao;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursement;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReimbursementItem;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeReimbursementDto;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeReimbursementItemVo;
import com.copower.pmcc.bpm.api.enums.ProcessStatusEnum;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class SchemeReimbursementService {
    @Autowired
    private SchemeReimbursementDao schemeReimbursementDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeReimbursementItemDao schemeReimbursementItemDao;

    public List<SchemeReimbursement> getObjectList(SchemeReimbursement schemeReimbursement){
        return schemeReimbursementDao.getObjectList(schemeReimbursement);
    }

    public SchemeReimbursement getDataByPlanDetailsId(Integer planDetailsId) {
        SchemeReimbursement where = new SchemeReimbursement();
        where.setPlanDetailsId(planDetailsId);
        return schemeReimbursementDao.getSchemeReimbursement(where);
    }

    public SchemeReimbursement getDataByProcessInsId(String processInsId) {
        SchemeReimbursement where = new SchemeReimbursement();
        where.setProcessInsId(processInsId);
        return schemeReimbursementDao.getSchemeReimbursement(where);
    }

    public void applyCommit(String formData, String processInsId) {
        SchemeReimbursementDto schemeReimbursementDto = JSON.parseObject(formData, SchemeReimbursementDto.class);
        SchemeReimbursement schemeReimbursement = this.getSingleObject(schemeReimbursementDto.getId());
        schemeReimbursement.setProcessInsId(processInsId);
        schemeReimbursementDao.editSchemeReimbursement(schemeReimbursement);

        //修改子表
        List<SchemeReimbursementItem> list = schemeReimbursementDto.getItemList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (SchemeReimbursementItem schemeReimbursementItem : list) {
                if (schemeReimbursementItem.getId() != null) {
                    schemeReimbursementItemDao.updateObject(schemeReimbursementItem);
                }
            }
        }
    }


    public void saveSchemeReimbursement(SchemeReimbursement schemeReimbursement) {
        if (schemeReimbursement.getId() != null && schemeReimbursement.getId().intValue() > 0) {
            schemeReimbursementDao.editSchemeReimbursement(schemeReimbursement);
        } else {
            schemeReimbursement.setCreator(processControllerComponent.getThisUser());
            schemeReimbursementDao.addSchemeReimbursement(schemeReimbursement);
        }
    }

    public SchemeReimbursement getSingleObject(Integer id) {
        return schemeReimbursementDao.getSchemeReimbursement(id);
    }


    /**
     * 获取法定有限受偿款完整描述（单位万元）
     *
     * @param judgeObjectId
     * @return
     */
    public String getFullDescription(Integer judgeObjectId) {
        SchemeReimbursementItem schemeReimbursementItem = new SchemeReimbursementItem();
        schemeReimbursementItem.setJudgeObjectId(judgeObjectId);
        SchemeReimbursementItem object = schemeReimbursementItemDao.getSingleObject(schemeReimbursementItem);
        StringBuilder builder = new StringBuilder();
        if (builder != null) {
            BigDecimal decimal = new BigDecimal("10000");
            builder.append(String.format("假定未设立法定优先受偿权总价%s万元,", (object.getNotSetUpTotalPrice().divide(decimal))));
            builder.append(String.format("已抵押担保的债权数额总价%s万元,", (object.getMortgagedTotalPrice().divide(decimal))));
            builder.append(String.format("拖欠的建设工程价款总价%s万元,", (object.getOwedTotalPrice().divide(decimal))));
            builder.append(String.format("其它法定优先受偿款总价%s万元,", (object.getOtherTotalPrice().divide(decimal))));
            builder.append(String.format("估价师知悉的法定优先受偿款总价%s万元,", (object.getKnowTotalPrice().divide(decimal))));
            builder.append(String.format("抵押价值总价%s万元;", (object.getMortgageTotalPrice().divide(decimal))));
            return builder.toString();
        }
        return null;
    }


    public SchemeReimbursement applyInit(ProjectPlanDetails projectPlanDetails) {
        //初始化添加
        SchemeReimbursement master = new SchemeReimbursement();
        master.setProjectId(projectPlanDetails.getProjectId());
        master.setPlanDetailsId(projectPlanDetails.getId());
        master.setStatus(ProcessStatusEnum.RUN.getValue());
        this.saveSchemeReimbursement(master);

        return master;
    }

    //获取明细
    public List<SchemeReimbursementItemVo> getItemByMasterId(Integer masterId) {
        SchemeReimbursementItem schemeReimbursementItem = new SchemeReimbursementItem();
        schemeReimbursementItem.setMasterId(masterId);
        return findQueryBySchemeReimbursementItem(schemeReimbursementItem);
    }

    public void saveAndUpdateSchemeReimbursementItem(SchemeReimbursementItem schemeReimbursementItem){
        if (schemeReimbursementItem == null){
            return;
        }
        if (schemeReimbursementItem.getId() == null || schemeReimbursementItem.getId().intValue() == 0){
            schemeReimbursementItem.setCreator(processControllerComponent.getThisUser());
            schemeReimbursementItemDao.addObject(schemeReimbursementItem);
        }else {
            schemeReimbursementItemDao.updateObject(schemeReimbursementItem);
        }
    }

    public List<SchemeReimbursementItemVo> findQueryBySchemeReimbursementItem(SchemeReimbursementItem schemeReimbursementItem) {
        List<SchemeReimbursementItemVo> vos = Lists.newArrayList();
        try {
            List<SchemeReimbursementItem> list = schemeReimbursementItemDao.getListObject2(schemeReimbursementItem);
            if (CollectionUtils.isNotEmpty(list)) {
                for (SchemeReimbursementItem item : list) {
                    vos.add(getSchemeReimbursementItemVo(item));
                }
            }
        } catch (Exception e) {
            String error = e.getMessage();
        }
        return vos;
    }


    public SchemeReimbursementItemVo getSchemeReimbursementItemVo(SchemeReimbursementItem schemeReimbursementItem) {
        SchemeReimbursementItemVo vo = new SchemeReimbursementItemVo();
        BeanUtils.copyProperties(schemeReimbursementItem, vo);
        vo.setJudgeObjectName(schemeJudgeObjectService.getSchemeJudgeObject(schemeReimbursementItem.getJudgeObjectId()).getName());
        return vo;
    }
}
