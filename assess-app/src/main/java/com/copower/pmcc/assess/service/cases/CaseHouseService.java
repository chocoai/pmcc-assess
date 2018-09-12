package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseHouseDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouse;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
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
 * @Date: 2018/9/11 14:38
 * @Description:案例 房屋信息
 */
@Service
public class CaseHouseService {
    @Autowired
    private CaseHouseDao caseHouseDao;
    @Autowired
    private CommonService commonService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public BootstrapTableVo getCaseHouseListVos(CaseHouse caseHouse){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseHouse> caseHouses = getCaseHouseList(caseHouse);
        vo.setRows(caseHouses);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public List<CaseHouse> getCaseHouseList(CaseHouse caseHouse){
        if (caseHouse==null){
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseHouseDao.getHouseList(caseHouse);
    }

    public CaseHouse getCaseHouseById(Integer id){
        if (id==null){
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseHouseDao.getHouseById(id);
    }

    public boolean saveAndUpdateCaseHouse(CaseHouse caseHouse){
        if (caseHouse==null){
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        if (caseHouse.getId()==null || caseHouse.getId().intValue()==0){
            caseHouse.setCreator(commonService.thisUserAccount());
            caseHouse.setVersion(0);
            return caseHouseDao.addHouse(caseHouse);
        }else {
            CaseHouse oo = caseHouseDao.getHouseById(caseHouse.getId());
            if (oo != null){
                if (oo.getVersion()==null){
                    oo.setVersion(0);
                }
            }
            caseHouse.setVersion(oo.getVersion()+1);
            return caseHouseDao.updateHouse(caseHouse);
        }
    }

    public boolean deleteCaseHouse(Integer id){
        if (id==null){
            try {
                logger.error("传入了null");
                throw new Exception("null point");
            } catch (Exception e1) {

            }
        }
        return caseHouseDao.deleteHouse(id);
    }
}
