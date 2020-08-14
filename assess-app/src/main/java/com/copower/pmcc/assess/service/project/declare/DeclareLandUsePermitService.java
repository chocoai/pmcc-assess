package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareLandUsePermitDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareLandUsePermit;
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
 * @Date: 2018/9/27 18:11
 * @Description:建设用地规划许可证
 */
@Service
public class DeclareLandUsePermitService {
    @Autowired
    private DeclareLandUsePermitDao declareLandUsePermitDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateDeclareLandUsePermit(DeclareLandUsePermit declareLandUsePermit) {
        return saveAndUpdateDeclareLandUsePermit(declareLandUsePermit, false);
    }

    public Integer saveAndUpdateDeclareLandUsePermit(DeclareLandUsePermit declareLandUsePermit, boolean updateNull) {
        if (declareLandUsePermit.getId() == null || declareLandUsePermit.getId().intValue() == 0) {
            declareLandUsePermit.setCreator(commonService.thisUserAccount());
            Integer id = declareLandUsePermitDao.addDeclareLandUsePermit(declareLandUsePermit);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DeclareLandUsePermit.class), id);
            return id;
        } else {
            declareLandUsePermitDao.updateDeclareLandUsePermit(declareLandUsePermit, updateNull);
            return null;
        }
    }

    public BootstrapTableVo getDeclareLandUsePermitListVos(DeclareLandUsePermit declareLandUsePermit) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareLandUsePermit> vos = declareLandUsePermitList(declareLandUsePermit);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DeclareLandUsePermit> declareLandUsePermitList(DeclareLandUsePermit oo) {
        return declareLandUsePermitDao.getDeclareLandUsePermitList(oo);
    }

    public DeclareLandUsePermit getDeclareLandUsePermitById(Integer id) {
        return declareLandUsePermitDao.getDeclareLandUsePermitById(id);
    }

    public void removeDeclareLandUsePermit(DeclareLandUsePermit declareLandUsePermit) {
        declareLandUsePermitDao.removeDeclareLandUsePermit(declareLandUsePermit);
    }


    public List<DeclareLandUsePermit> getDeclareLandUsePermitByMasterId(Integer masterId) {
        return declareLandUsePermitDao.getDeclareLandUsePermitByMasterId(masterId);
    }

    public boolean deleteDeclareLandUsePermitById(Integer id) {
        return declareLandUsePermitDao.deleteDeclareLandUsePermitById(id);
    }

    public boolean deleteDeclareLandUsePermitById(List<Integer> ids){
        return declareLandUsePermitDao.deleteDeclareLandUsePermitById(ids) ;
    }

    public void deleteDeclareLandUsePermitByIds(String id) {
        if (StringUtils.isBlank(id)){
            return;
        }
       deleteDeclareLandUsePermitById(FormatUtils.transformString2Integer(id)) ;
    }

    public List<DeclareLandUsePermit> getDataIds(List<Integer> ids){
        return declareLandUsePermitDao.getDataIds(ids) ;
    }

}
