package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicAlternativeCaseDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDetailDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicAlternativeCase;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dto.input.BasicAlternativeCaseDto;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:53
 */
@Service
public class BasicAlternativeCaseService {
    @Autowired
    private BasicAlternativeCaseDao basicAlternativeCaseDao;

    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BasicApplyBatchDetailDao basicApplyBatchDetailDao;
    @Autowired
    private BasicApplyBatchDao basicApplyBatchDao;

    public Integer saveAndUpdateBasicAlternativeCase(BasicAlternativeCase basicAlternativeCase) throws BusinessException, Exception {
        if (basicAlternativeCase.getId() == null) {
            List<BasicAlternativeCase> alternativeCases = basicAlternativeCaseDao.getBasicAlternativeCaseList(basicAlternativeCase);
            if (CollectionUtils.isNotEmpty(alternativeCases)) {
                throw new BusinessException("已添加，请重新选择");
            }

            BasicApplyBatchDetail basicApplyBatchDetail = basicApplyBatchDetailDao.getInfoById(basicAlternativeCase.getBusinessId());
            if (StringUtils.equals(basicAlternativeCase.getBusinessKey(), BasicFormClassifyEnum.ESTATE.getKey())) {
                basicAlternativeCase.setName(basicApplyBatchDetail.getDisplayName());
            }
            if (StringUtils.equals(basicAlternativeCase.getBusinessKey(), BasicFormClassifyEnum.BUILDING.getKey())) {
                BasicApplyBatchDetail estate = basicApplyBatchDetailDao.getInfoById(basicApplyBatchDetail.getPid());
                basicAlternativeCase.setName(String.format("%s%s", estate.getDisplayName(), basicApplyBatchDetail.getDisplayName()));
            }
            if (StringUtils.equals(basicAlternativeCase.getBusinessKey(), BasicFormClassifyEnum.UNIT.getKey())) {
                BasicApplyBatchDetail building = basicApplyBatchDetailDao.getInfoById(basicApplyBatchDetail.getPid());
                BasicApplyBatchDetail estate = basicApplyBatchDetailDao.getInfoById(building.getPid());
                basicAlternativeCase.setName(String.format("%s%s%s", estate.getDisplayName(), building.getDisplayName(), basicApplyBatchDetail.getDisplayName()));

            }
            if (StringUtils.equals(basicAlternativeCase.getBusinessKey(), BasicFormClassifyEnum.HOUSE.getKey())) {
                BasicApplyBatchDetail unit = basicApplyBatchDetailDao.getInfoById(basicApplyBatchDetail.getPid());
                BasicApplyBatchDetail building = basicApplyBatchDetailDao.getInfoById(unit.getPid());
                BasicApplyBatchDetail estate = basicApplyBatchDetailDao.getInfoById(building.getPid());
                basicAlternativeCase.setName(String.format("%s%s%s%s", estate.getDisplayName(), building.getDisplayName(), unit.getDisplayName(), basicApplyBatchDetail.getDisplayName()));
            }
            basicAlternativeCase.setCreator(commonService.thisUserAccount());
            return basicAlternativeCaseDao.addBasicAlternativeCase(basicAlternativeCase);
        } else {
            basicAlternativeCaseDao.updateBasicAlternativeCase(basicAlternativeCase);
            return null;
        }
    }

    public BasicAlternativeCase getBasicAlternativeCaseById(Integer id) {
        return basicAlternativeCaseDao.getBasicAlternativeCaseById(id);
    }

    public BootstrapTableVo getBasicAlternativeCaseList(String name, String tbType) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicAlternativeCase> alternativeCases = basicAlternativeCaseDao.getBasicAlternativeCaseList(name, tbType);
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(alternativeCases) ? new ArrayList<BasicAlternativeCase>() : alternativeCases);
        return vo;
    }

    public BasicAlternativeCaseDto getBasicAlternativeCaseDto(Integer id) {
        BasicAlternativeCase basicAlternativeCase = this.getBasicAlternativeCaseById(id);
        BasicApplyBatchDetail applyBatchDetail = basicApplyBatchDetailDao.getInfoById(basicAlternativeCase.getBusinessId());
        BasicApplyBatch basicApplyBatch = basicApplyBatchDao.getBasicApplyBatchById(applyBatchDetail.getApplyBatchId());
        BasicAlternativeCaseDto basicAlternativeCaseDto = new BasicAlternativeCaseDto();
        basicAlternativeCaseDto.setApplyBatchId(basicApplyBatch.getId());
        basicAlternativeCaseDto.setFormClassify(basicApplyBatch.getClassify());
        basicAlternativeCaseDto.setFormType(basicApplyBatch.getType());
        basicAlternativeCaseDto.setTableId(applyBatchDetail.getTableId());
        basicAlternativeCaseDto.setTableName(applyBatchDetail.getTableName());
        basicAlternativeCaseDto.setPlanDetailsId(basicApplyBatch.getPlanDetailsId());
        return basicAlternativeCaseDto;
    }

    public boolean deleteDataById(Integer id) {
        return basicAlternativeCaseDao.deleteInfo(id);
    }
}
