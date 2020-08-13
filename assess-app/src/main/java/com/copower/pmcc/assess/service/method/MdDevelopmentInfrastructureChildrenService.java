package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.common.BeanCopyHelp;
import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentInfrastructureChildrenDao;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentInfrastructureChildren;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zch on 2019/7/31.
 */
@Service
public class MdDevelopmentInfrastructureChildrenService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private MdDevelopmentInfrastructureChildrenDao mdDevelopmentInfrastructureChildrenDao;

    /**
     * 基础设施配套费
     * @param targetPlanDetails
     * @param copyPlanDetails
     * @param copyId
     * @param targetId
     */
    public void  copyData(ProjectPlanDetails targetPlanDetails, ProjectPlanDetails copyPlanDetails,Integer copyId,Integer targetId){
        if (targetPlanDetails == null || copyPlanDetails == null){
            return;
        }
        if (copyId == null || targetId == null){
            return;
        }
        clear(targetId) ;
        MdDevelopmentInfrastructureChildren copySelect = new MdDevelopmentInfrastructureChildren();
        copySelect.setPid(copyId);
        copySelect.setPlanDetailsId(copyPlanDetails.getId());
        List<MdDevelopmentInfrastructureChildren> copyList = getMdDevelopmentInfrastructureChildrenListByExample(copySelect) ;
        if (CollectionUtils.isEmpty(copyList)){
            return;
        }
        List<MdDevelopmentInfrastructureChildren> list = new ArrayList<>(copyList.size()) ;
        for (MdDevelopmentInfrastructureChildren children:copyList){
            MdDevelopmentInfrastructureChildren target = new MdDevelopmentInfrastructureChildren();
            children.setId(null);
            children.setPid(null);
            children.setPlanDetailsId(null);
            children.setCreator(null);
            BeanCopyHelp.copyPropertiesIgnoreNull(children, target);
            target.setPid(targetId);
            target.setPlanDetailsId(targetPlanDetails.getId());
            target.setCreator(commonService.thisUserAccount());
            target.setGmtCreated(DateUtils.now());
            target.setGmtModified(DateUtils.now());
            list.add(target) ;
        }
        if (CollectionUtils.isNotEmpty(list)){
            mdDevelopmentInfrastructureChildrenDao.batchInset(list);
        }
    }

    public boolean saveMdDevelopmentInfrastructureChildren(MdDevelopmentInfrastructureChildren mdDevelopmentInfrastructureChildren){
        if (mdDevelopmentInfrastructureChildren == null){
            return false;
        }
        if (mdDevelopmentInfrastructureChildren.getId() != null && mdDevelopmentInfrastructureChildren.getId() != 0){
            return mdDevelopmentInfrastructureChildrenDao.updateMdDevelopmentInfrastructureChildren(mdDevelopmentInfrastructureChildren);
        }else {
            mdDevelopmentInfrastructureChildren.setCreator(commonService.thisUserAccount());
            return mdDevelopmentInfrastructureChildrenDao.addMdDevelopmentInfrastructureChildren(mdDevelopmentInfrastructureChildren) ;
        }
    }


    public void clear(Integer pid){
        MdDevelopmentInfrastructureChildren oo = new MdDevelopmentInfrastructureChildren();
        oo.setPid(pid == null?Integer.valueOf(0):pid);
        oo.setCreator(commonService.thisUserAccount());
        List<MdDevelopmentInfrastructureChildren> childrenList = getMdDevelopmentInfrastructureChildrenListByExample(oo);
        if (CollectionUtils.isNotEmpty(childrenList)){
            for (MdDevelopmentInfrastructureChildren po:childrenList){
                deleteMdDevelopmentInfrastructureChildrenById(po.getId()) ;
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(MdDevelopmentInfrastructureChildren oo){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<MdDevelopmentInfrastructureChildren> childrenList = getMdDevelopmentInfrastructureChildrenListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(childrenList);
        return vo;
    }

    public MdDevelopmentInfrastructureChildren getMdDevelopmentInfrastructureChildrenById(Integer id){
        return mdDevelopmentInfrastructureChildrenDao.getMdDevelopmentInfrastructureChildrenById(id) ;
    }

    public boolean deleteMdDevelopmentInfrastructureChildrenById(Integer id){
        return mdDevelopmentInfrastructureChildrenDao.deleteMdDevelopmentInfrastructureChildrenById(id) ;
    }

    public List<MdDevelopmentInfrastructureChildren> getMdDevelopmentInfrastructureChildrenListByExample(MdDevelopmentInfrastructureChildren oo){
        return mdDevelopmentInfrastructureChildrenDao.getMdDevelopmentInfrastructureChildrenListByExample(oo);
    }

}
