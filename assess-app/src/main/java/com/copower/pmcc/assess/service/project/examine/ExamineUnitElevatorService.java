package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineUnitElevatorDao;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitElevator;
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
 * @Date: 2018/7/24 17:36
 * @Description:配备电梯
 */

@Service
public class ExamineUnitElevatorService {
    @Autowired
    private ExamineUnitElevatorDao examineUnitElevatorDao;
    @Autowired
    private CommonService commonService;

    /**
     * 功能描述:
     *
     * @param: examineUnitElevator
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:31
     */
    @Transactional
    public boolean saveExamineUnitElevator(ExamineUnitElevator examineUnitElevator) {
        examineUnitElevator.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineUnitElevator.getDeclareId())){
            examineUnitElevator.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineUnitElevator.getExamineType())){
            examineUnitElevator.setExamineType(0);
        }
        return examineUnitElevatorDao.addUnitElevator(examineUnitElevator);
    }

    /**
     *
     * 功能描述:
     *
     * @param: examineUnitElevator
     * @return: BootstrapTableVo
     * @auther: zch
     * @date: 2018/7/18 15:02
     */
    public BootstrapTableVo getExamineUnitElevatorList(ExamineUnitElevator examineUnitElevator){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineUnitElevator> vos = getEstateNetworkLists(examineUnitElevator);
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineUnitElevator>() : vos);
        return vo;
    }

    /**
     * 功能描述:
     *
     * @param: examineUnitElevator
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:33
     */
    @Transactional
    public boolean updateEstateNetwork(ExamineUnitElevator examineUnitElevator) {
        return examineUnitElevatorDao.updateUnitElevator(examineUnitElevator);
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
        return examineUnitElevatorDao.deleteUnitElevator(id);
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:36
     */
    public ExamineUnitElevator getEstateNetworkById(Integer id) {
        return examineUnitElevatorDao.getUnitElevatorById(id);
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: zch
     * @date: 2018/7/18 14:36
     */
    public List<ExamineUnitElevator> getEstateNetworkLists(ExamineUnitElevator examineUnitElevator) {
        return examineUnitElevatorDao.getUnitElevatorList(examineUnitElevator);
    }
}
