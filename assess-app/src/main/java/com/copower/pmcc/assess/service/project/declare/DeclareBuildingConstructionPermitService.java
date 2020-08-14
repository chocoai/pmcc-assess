package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareBuildingConstructionPermitDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingConstructionPermit;
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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/27 18:09
 * @Description:建筑工程施工许可证
 */
@Service
public class DeclareBuildingConstructionPermitService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareBuildingConstructionPermitDao declareBuildingConstructionPermitDao;

    public Integer saveAndUpdateDeclareBuildingConstructionPermit(DeclareBuildingConstructionPermit declareBuildingConstructionPermit) {
       return saveAndUpdateDeclareBuildingConstructionPermit(declareBuildingConstructionPermit,false) ;
    }

    public Integer saveAndUpdateDeclareBuildingConstructionPermit(DeclareBuildingConstructionPermit declareBuildingConstructionPermit, boolean updateNull) {
        if (declareBuildingConstructionPermit.getId() == null || declareBuildingConstructionPermit.getId().intValue() == 0) {
            declareBuildingConstructionPermit.setCreator(commonService.thisUserAccount());
            Integer id = declareBuildingConstructionPermitDao.addDeclareBuildingConstructionPermit(declareBuildingConstructionPermit);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareBuildingConstructionPermit.class), id);
            return id;
        } else {
            declareBuildingConstructionPermitDao.updateDeclareBuildingConstructionPermit(declareBuildingConstructionPermit,updateNull);
            return null;
        }
    }

    public BootstrapTableVo getDeclareBuildingConstructionPermitListVos(DeclareBuildingConstructionPermit declareBuildingConstructionPermit) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareBuildingConstructionPermit> vos = declareBuildingConstructionPermitList(declareBuildingConstructionPermit);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareBuildingConstructionPermit> declareBuildingConstructionPermitList(DeclareBuildingConstructionPermit oo) {
        return declareBuildingConstructionPermitDao.getDeclareBuildingConstructionPermitList(oo);
    }

    public DeclareBuildingConstructionPermit getDeclareBuildingConstructionPermitById(Integer id) {
        return declareBuildingConstructionPermitDao.getDeclareBuildingConstructionPermitById(id);
    }

    public void removeDeclareBuildingConstructionPermit(DeclareBuildingConstructionPermit declareBuildingConstructionPermit) {
        declareBuildingConstructionPermitDao.removeDeclareBuildingConstructionPermit(declareBuildingConstructionPermit);
    }

    public List<DeclareBuildingConstructionPermit> getDeclareBuildingConstructionPermitByMasterId(Integer masterId){
        return declareBuildingConstructionPermitDao.getDeclareBuildingConstructionPermitByMasterId(masterId) ;
    }

    public boolean deleteDeclareBuildingConstructionPermitById(Integer id){
        return declareBuildingConstructionPermitDao.deleteDeclareBuildingConstructionPermitById(id) ;
    }

    public boolean deleteDeclareBuildingConstructionPermitById(List<Integer> ids){
        return declareBuildingConstructionPermitDao.deleteDeclareBuildingConstructionPermitById(ids) ;
    }

    public List<DeclareBuildingConstructionPermit> getDataIds(List<Integer> ids){
        return declareBuildingConstructionPermitDao.getDataIds(ids) ;
    }

    public void deleteDeclareBuildingConstructionPermitByIds(String id){
        if (StringUtils.isBlank(id)){
            return;
        }
        List<Integer> integerList = FormatUtils.transformString2Integer(id);
        deleteDeclareBuildingConstructionPermitById(integerList) ;
    }

}
