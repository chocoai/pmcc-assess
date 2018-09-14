package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuilding;
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
 * @Date: 2018/9/11 14:40
 * @Description:案例 楼栋信息
 */
@Service
public class CaseBuildingService {
    @Autowired
    private CaseBuildingDao caseBuildingDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public BootstrapTableVo getCaseBuildingListVos(CaseBuilding caseBuilding) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseBuilding> caseBuildings = getCaseBuildingList(caseBuilding);
        vo.setRows(caseBuildings);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<CaseBuilding> getCaseBuildingList(CaseBuilding caseBuilding) {
        if (caseBuilding == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseBuildingDao.getBuildingList(caseBuilding);
    }

    public CaseBuilding getCaseBuildingById(Integer id) {
        if (id == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseBuildingDao.getBuildingById(id);
    }

    public Integer saveAndUpdateCaseBuilding(CaseBuilding caseBuilding) {
        Integer id = null;
        if (caseBuilding == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        if (caseBuilding.getId() == null || caseBuilding.getId().intValue() == 0) {
            caseBuilding.setCreator(commonService.thisUserAccount());
            caseBuilding.setVersion(0);
            id = caseBuildingDao.addBuilding(caseBuilding);
            //更新附件
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseBuilding.class), id);
            return id;
        } else {
            //更新版本
            CaseBuilding oo = caseBuildingDao.getBuildingById(caseBuilding.getId());
            if (oo != null) {
                if (oo.getVersion() == null) {
                    oo.setVersion(0);
                }
            }
            caseBuilding.setVersion(oo.getVersion() + 1);
            caseBuildingDao.updateBuilding(caseBuilding);
            return null;
        }
    }

    public boolean deleteCaseBuilding(Integer id) {
        if (id == null) {
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseBuildingDao.deleteBuilding(id);
    }
}
