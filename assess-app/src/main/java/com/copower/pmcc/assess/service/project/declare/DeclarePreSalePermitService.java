package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclarePreSalePermitDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclarePreSalePermit;
import com.copower.pmcc.assess.service.ErpAreaService;
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
 * @Date: 2018/9/27 18:11
 * @Description:商品房预售许可证
 */
@Service
public class DeclarePreSalePermitService {
    @Autowired
    private DeclarePreSalePermitDao declarePreSalePermitDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateDeclarePreSalePermit(DeclarePreSalePermit declarePreSalePermit) {
        return saveAndUpdateDeclarePreSalePermit(declarePreSalePermit, false);
    }


    public Integer saveAndUpdateDeclarePreSalePermit(DeclarePreSalePermit declarePreSalePermit, boolean updateNull) {
        if (declarePreSalePermit.getId() == null || declarePreSalePermit.getId().intValue() == 0) {
            declarePreSalePermit.setCreator(commonService.thisUserAccount());
            Integer id = declarePreSalePermitDao.addDeclarePreSalePermit(declarePreSalePermit);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclarePreSalePermit.class), id);
            return id;
        } else {
            declarePreSalePermitDao.updateDeclarePreSalePermit(declarePreSalePermit, updateNull);
            return null;
        }
    }

    public BootstrapTableVo getDeclarePreSalePermitListVos(DeclarePreSalePermit declarePreSalePermit) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclarePreSalePermit> vos = declarePreSalePermitList(declarePreSalePermit);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclarePreSalePermit> declarePreSalePermitList(DeclarePreSalePermit oo) {
        return declarePreSalePermitDao.getDeclarePreSalePermitList(oo);
    }

    public DeclarePreSalePermit getDeclarePreSalePermitById(Integer id) {
        return declarePreSalePermitDao.getDeclarePreSalePermitById(id);
    }

    public void removeDeclarePreSalePermit(DeclarePreSalePermit declarePreSalePermit) {
        declarePreSalePermitDao.removeDeclarePreSalePermit(declarePreSalePermit);
    }

    public boolean deleteDeclarePreSalePermitById(Integer id) {
        return declarePreSalePermitDao.deleteDeclarePreSalePermitById(id);
    }

    public void deleteDeclarePreSalePermitByIds(String id) {
        FormatUtils.transformString2Integer(id).forEach(integer -> deleteDeclarePreSalePermitById(integer));
    }


    public List<DeclarePreSalePermit> getDeclarePreSalePermitByMasterId(Integer masterId) {
        return declarePreSalePermitDao.getDeclarePreSalePermitByMasterId(masterId);
    }

    public List<DeclarePreSalePermit> getDataIds(List<Integer> ids){
        return declarePreSalePermitDao.getDataIds(ids);
    }

}
