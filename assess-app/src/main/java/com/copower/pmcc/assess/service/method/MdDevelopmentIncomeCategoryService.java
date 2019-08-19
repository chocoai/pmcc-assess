package com.copower.pmcc.assess.service.method;

import com.copower.pmcc.assess.dal.basis.dao.method.MdDevelopmentIncomeCategoryDao;
import com.copower.pmcc.assess.dal.basis.entity.MdDevelopmentIncomeCategory;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zch on 2019/8/15.
 */
@Service
public class MdDevelopmentIncomeCategoryService {

    @Autowired
    private CommonService commonService;

    @Autowired
    private MdDevelopmentIncomeCategoryDao MdDevelopmentIncomeCategoryDao;

    public boolean saveMdDevelopmentIncomeCategory(MdDevelopmentIncomeCategory MdDevelopmentIncomeCategory){
        if (MdDevelopmentIncomeCategory == null){
            return false;
        }
        if (MdDevelopmentIncomeCategory.getId() != null && MdDevelopmentIncomeCategory.getId() != 0){
            return MdDevelopmentIncomeCategoryDao.updateMdDevelopmentIncomeCategory(MdDevelopmentIncomeCategory);
        }else {
            MdDevelopmentIncomeCategory.setCreator(commonService.thisUserAccount());
            return MdDevelopmentIncomeCategoryDao.addMdDevelopmentIncomeCategory(MdDevelopmentIncomeCategory) ;
        }
    }


    public void clear(){
        MdDevelopmentIncomeCategory oo = new MdDevelopmentIncomeCategory();
        oo.setPid(0);
        oo.setCreator(commonService.thisUserAccount());
        List<MdDevelopmentIncomeCategory> childrenList = getMdDevelopmentIncomeCategoryListByExample(oo);
        if (CollectionUtils.isNotEmpty(childrenList)){
            for (MdDevelopmentIncomeCategory po:childrenList){
                deleteMdDevelopmentIncomeCategoryById(po.getId()) ;
            }
        }
    }

    public BootstrapTableVo getBootstrapTableVo(MdDevelopmentIncomeCategory oo){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<MdDevelopmentIncomeCategory> childrenList = getMdDevelopmentIncomeCategoryListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(childrenList);
        return vo;
    }

    public MdDevelopmentIncomeCategory getMdDevelopmentIncomeCategoryById(Integer id){
        return MdDevelopmentIncomeCategoryDao.getMdDevelopmentIncomeCategoryById(id) ;
    }

    public boolean deleteMdDevelopmentIncomeCategoryById(Integer id){
        return MdDevelopmentIncomeCategoryDao.deleteMdDevelopmentIncomeCategoryById(id) ;
    }

    public List<MdDevelopmentIncomeCategory> getMdDevelopmentIncomeCategoryListByExample(MdDevelopmentIncomeCategory oo){
        return MdDevelopmentIncomeCategoryDao.getMdDevelopmentIncomeCategoryListByExample(oo);
    }
    
}
