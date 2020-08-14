package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareBuildingPermitDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareBuildingPermit;
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
 * @Date: 2018/9/27 18:10
 * @Description:建设工程规划许可证
 */
@Service
public class DeclareBuildingPermitService {
    @Autowired
    private DeclareBuildingPermitDao declareBuildingPermitDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateDeclareBuildingPermit(DeclareBuildingPermit declareBuildingPermit) {
       return saveAndUpdateDeclareBuildingPermit(declareBuildingPermit,false) ;
    }

    public Integer saveAndUpdateDeclareBuildingPermit(DeclareBuildingPermit declareBuildingPermit, boolean updateNull) {
        if (declareBuildingPermit.getId() == null || declareBuildingPermit.getId().intValue() == 0) {
            declareBuildingPermit.setCreator(commonService.thisUserAccount());
            Integer id = declareBuildingPermitDao.addDeclareBuildingPermit(declareBuildingPermit);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareBuildingPermit.class), id);
            return id;
        } else {
            declareBuildingPermitDao.updateDeclareBuildingPermit(declareBuildingPermit,updateNull);
            return null;
        }
    }

    public BootstrapTableVo getDeclareBuildingPermitListVos(DeclareBuildingPermit declareBuildingPermit) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareBuildingPermit> vos = declareBuildingPermitList(declareBuildingPermit);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareBuildingPermit> declareBuildingPermitList(DeclareBuildingPermit oo) {
        return declareBuildingPermitDao.getDeclareBuildingPermitList(oo);
    }

    public DeclareBuildingPermit getDeclareBuildingPermitById(Integer id) {
        return declareBuildingPermitDao.getDeclareBuildingPermitById(id);
    }

    public void removeDeclareBuildingPermit(DeclareBuildingPermit declareBuildingPermit) {
        declareBuildingPermitDao.removeDeclareBuildingPermit(declareBuildingPermit);
    }

    public boolean deleteDeclareBuildingPermitById(Integer id){
        return declareBuildingPermitDao.deleteDeclareBuildingPermitById(id) ;
    }

    public void deleteDeclareBuildingPermitByIds(String id){
        if (StringUtils.isBlank(id)){
            return;
        }
        List<Integer> integerList = FormatUtils.transformString2Integer(id);
        deleteDeclareBuildingPermitById(integerList) ;
    }

    public boolean deleteDeclareBuildingPermitById(List<Integer> ids){
        return declareBuildingPermitDao.deleteDeclareBuildingPermitById(ids) ;
    }

    public List<DeclareBuildingPermit> getDataIds(List<Integer> ids){
        return declareBuildingPermitDao.getDataIds(ids) ;
    }


    public List<DeclareBuildingPermit> getDeclareBuildingPermitByMasterId(Integer masterId){
        return declareBuildingPermitDao.getDeclareBuildingPermitByMasterId(masterId) ;
    }
    
}
