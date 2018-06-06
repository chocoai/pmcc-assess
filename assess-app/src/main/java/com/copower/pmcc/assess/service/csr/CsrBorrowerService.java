package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.constant.AssessFieldNameConstant;
import com.copower.pmcc.assess.dal.dao.csr.CsrBorrowerDao;
import com.copower.pmcc.assess.dal.dao.csr.CsrBorrowerEnteringDao;
import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.CsrBorrower;
import com.copower.pmcc.assess.dal.entity.CsrBorrowerEntering;
import com.copower.pmcc.assess.dal.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerEnteringVo;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.plan.service.ProjectPlanFinancialClaimService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 借款人
 */
@Service
public class CsrBorrowerService {

    @Autowired
    private CsrBorrowerDao csrBorrowerDao;
    @Autowired
    private CsrBorrowerEnteringDao csrBorrowerEnteringDao;
    @Autowired
    private ProjectPlanFinancialClaimService projectPlanFinancialClaimService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    public BootstrapTableVo borrowerLists(String secondLevelBranch,String firstLevelBranch,Integer csrProjectInfoID,Integer csrProjectInfoGroupID) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CsrBorrower> vos = csrBorrowerDao.borrowerListsA(secondLevelBranch,firstLevelBranch,csrProjectInfoID,csrProjectInfoGroupID);
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CsrBorrower>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    /**
     * 查询分派是否完成
     * @param csrProjectInfoID
     * @return
     */
    public Integer checkCsrBorrower(Integer csrProjectInfoID){
        return csrBorrowerDao.borrowerListsA(null,null,csrProjectInfoID,null).size();
    }

    /**
     * 取消分派
     * @param csrProjectInfoID
     * @param id
     */
    public void cancel(Integer csrProjectInfoID,String id,Integer csrProjectInfoGroupID){
        String[] ids = id.split(",");
        List<Integer> integerList = new ArrayList<>();
        for (String s:ids){
            if (!StringUtils.isEmpty(s)){
                integerList.add(Integer.valueOf(s));
            }
        }
        csrBorrowerDao.cancel(csrProjectInfoID,integerList,csrProjectInfoGroupID);
    }

    public boolean update(CsrBorrower csrBorrower) {
        return csrBorrowerDao.update(csrBorrower);
    }

    public List<CsrBorrowerVo> getCsrBorrowerByProjectId(Integer projectId) {
        CsrBorrower csrBorrower = new CsrBorrower();
        csrBorrower.setProjectId(projectId);
        List<CsrBorrower> csrBorrowerList = csrBorrowerDao.getCsrBorrowerList(projectId);
        return getCsrBorrowerVos(csrBorrowerList);
    }

    public List<CsrBorrowerVo> getCsrBorrowerVos(List<CsrBorrower> csrBorrowerList){
        if(CollectionUtils.isEmpty(csrBorrowerList)) return null;
        List<Integer> tableIds = LangUtils.transform(csrBorrowerList, p -> p.getId());
        BaseAttachment queryParam=new BaseAttachment();
        queryParam.setTableName(FormatUtils.entityNameConvertToTableName(CsrBorrower.class));
        queryParam.setFieldsName(AssessFieldNameConstant.CSR_BORROWER_REPORT);
        List<BaseAttachment> attachmentList = baseAttachmentService.getAttachmentList(tableIds, queryParam);
        return LangUtils.transform(csrBorrowerList, p -> {
            CsrBorrowerVo csrBorrowerVo=new CsrBorrowerVo();
            BeanUtils.copyProperties(p,csrBorrowerVo);
            if(CollectionUtils.isNotEmpty(attachmentList)){
                for (BaseAttachment baseAttachment : attachmentList) {
                    if(baseAttachment.getTableId().equals(p.getId())){
                        csrBorrowerVo.setAttachmentHtml(baseAttachmentService.getViewHtml(baseAttachment));
                    }
                }
            }
            return csrBorrowerVo;
        });
    }

    public List<CsrBorrower> getCsrBorrowerListByCsrProjectID(Integer csrProjectID) {
        return csrBorrowerDao.getCsrBorrowerListByCsrProjectID(csrProjectID);
    }

    public CsrBorrowerEntering saveCsrBorrower(CsrBorrowerEntering csrBorrowerEntering, Integer detailsId, String taskRemarks, String actualHours) throws BusinessException {
        try {
            if (csrBorrowerEntering.getId() == null || csrBorrowerEntering.getId() <= 0) {
                csrBorrowerEnteringDao.addCsrBorrowerEntering(csrBorrowerEntering);
            } else {
                csrBorrowerEnteringDao.update(csrBorrowerEntering);
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        projectPlanFinancialClaimService.updatePlanTaskActualHours(detailsId, taskRemarks, actualHours);
        return csrBorrowerEntering;
    }

    public CsrBorrowerEnteringVo loadLoanBorrower(Integer borrowerId, Integer detailsId) throws BusinessException {

        CsrBorrowerEntering csrBorrowerEntering = csrBorrowerEnteringDao.getCsrBorrowerEnteringByBorrowerId(borrowerId);
        if (csrBorrowerEntering == null) {
            CsrBorrower csrBorrower = csrBorrowerDao.getCsrBorrowerByID(borrowerId);
            if (csrBorrower != null) {
                csrBorrowerEntering = new CsrBorrowerEntering();
                BeanUtils.copyProperties(csrBorrower, csrBorrowerEntering);
            }
            csrBorrowerEntering.setId(0);
            csrBorrowerEntering.setBorrowerId(borrowerId);
            try {
                csrBorrowerEnteringDao.addCsrBorrowerEntering(csrBorrowerEntering);
            } catch (Exception e) {
                throw new BusinessException(e.getMessage());
            }
        }
        CsrBorrowerEnteringVo csrBorrowerEnteringVo = new CsrBorrowerEnteringVo();
        if (csrBorrowerEntering != null) {
            BeanUtils.copyProperties(csrBorrowerEntering, csrBorrowerEnteringVo);
            ProjectPlanDetails projectPlanDetails = projectPlanDetailsService.getProjectPlanDetailsById(detailsId);
            if (projectPlanDetails != null) {
                if (projectPlanDetails.getActualHours() != null) {
                    csrBorrowerEnteringVo.setActualHours(projectPlanDetails.getActualHours().toString());
                }
                csrBorrowerEnteringVo.setTaskRemarks(projectPlanDetails.getTaskRemarks());
            }
        }
        return csrBorrowerEnteringVo;
    }

}
