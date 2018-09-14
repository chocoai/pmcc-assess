package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseUnitDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnit;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 14:39
 * @Description:案例 单元信息
 */
@Service
public class CaseUnitService {
    @Autowired
    private CaseUnitDao caseUnitDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public BootstrapTableVo getCaseUnitListVos(CaseUnit caseUnit) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseUnit> caseUnits = getCaseUnitList(caseUnit);
        vo.setRows(caseUnits);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<CaseUnit> getCaseUnitList(CaseUnit caseUnit) {
        if (caseUnit == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseUnitDao.getUnitList(caseUnit);
    }

    public CaseUnit getCaseUnitById(Integer id) {
        if (id == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseUnitDao.getUnitById(id);
    }

    public Integer saveAndUpdateCaseUnit(CaseUnit caseUnit) {
        Integer id = null;
        if (caseUnit == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        if (caseUnit.getId() == null || caseUnit.getId().intValue() == 0) {
            caseUnit.setCreator(commonService.thisUserAccount());
            caseUnit.setVersion(0);
            id = caseUnitDao.addUnit(caseUnit);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseUnit.class), id);
            return  id;
        } else {
            CaseUnit oo = caseUnitDao.getUnitById(caseUnit.getId());
            if (oo != null) {
                if (oo.getVersion() == null) {
                    oo.setVersion(0);
                }
            }
            caseUnit.setVersion(oo.getVersion() + 1);
            caseUnitDao.updateUnit(caseUnit);
            return null;
        }
    }

    public boolean deleteCaseUnit(Integer id) {
        if (id == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseUnitDao.deleteUnit(id);
    }
}
