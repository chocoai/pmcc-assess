package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.cases.dao.CaseUnitElevatorDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitElevator;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 16:30
 * @Description:
 */
@Service
public class CaseUnitElevatorService {
    @Autowired
    private CaseUnitElevatorDao caseUnitElevatorDao;
    @Autowired
    private CommonService commonService;
    /**
     * 功能描述:
     *
     * @param: caseUnitElevator
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:31
     */
    public boolean saveCaseUnitElevator(CaseUnitElevator caseUnitElevator) {
        caseUnitElevator.setCreator(commonService.thisUserAccount());
        return caseUnitElevatorDao.addUnitElevator(caseUnitElevator);
    }

    /**
     *
     * 功能描述:
     *
     * @param: caseUnitElevator
     * @return: BootstrapTableVo
     * @auther: zch
     * @date: 2018/7/18 15:02
     */
    public BootstrapTableVo getCaseUnitElevatorList(CaseUnitElevator caseUnitElevator){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseUnitElevator> vos = getEstateNetworkLists(caseUnitElevator);
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseUnitElevator>() : vos);
        return vo;
    }

    /**
     * 功能描述:
     *
     * @param: caseUnitElevator
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:33
     */
    public boolean updateEstateNetwork(CaseUnitElevator caseUnitElevator) {
        return caseUnitElevatorDao.updateUnitElevator(caseUnitElevator);
    }

    /**
     * 功能描述:
     *
     * @param: id
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:34
     */
    @Transactional
    public boolean deleteEstateNetwork(Integer id) {
        return caseUnitElevatorDao.deleteUnitElevator(id);
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:36
     */
    public CaseUnitElevator getEstateNetworkById(Integer id) {
        return caseUnitElevatorDao.getUnitElevatorById(id);
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:36
     */
    public List<CaseUnitElevator> getEstateNetworkLists(CaseUnitElevator caseUnitElevator) {
        return caseUnitElevatorDao.getUnitElevatorList(caseUnitElevator);
    }

    public void upgradeVersion(CaseUnitElevator po)throws Exception{
        if (po.getId()==null || po.getId().intValue() == 0){
            po.setCreator(commonService.thisUserAccount());
            po.setVersion(0);
            this.saveCaseUnitElevator(po);
        }else {
            CaseUnitElevator oo = getEstateNetworkById(po.getId());
            if (oo.getVersion() == null){
                oo.setVersion(0);
            }
            int version = oo.getVersion() + 1;
            BeanCopyHelp.copyPropertiesIgnoreNull(po, oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            oo.setCreator(commonService.thisUserAccount());
            this.saveCaseUnitElevator(oo);
        }
    }
}
