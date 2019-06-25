package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareEconomicIndicatorsHeadDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareEconomicIndicatorsContent;
import com.copower.pmcc.assess.dal.basis.entity.DeclareEconomicIndicatorsHead;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zch on 2019/6/25.
 * 经济指标
 */
@Service
public class DeclareEconomicIndicatorsHeadService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private DeclareEconomicIndicatorsHeadDao dao;
    @Autowired
    private DeclareEconomicIndicatorsContentService declareEconomicIndicatorsContentService;


    public boolean updateDeclareEconomicIndicatorsHead(DeclareEconomicIndicatorsHead oo) {
        return dao.updateDeclareEconomicIndicatorsHead(oo);
    }


    public boolean addDeclareEconomicIndicatorsHead(DeclareEconomicIndicatorsHead oo) {
        if (oo != null){
            if (StringUtils.isEmpty(oo.getCreator())){
                oo.setCreator(commonService.thisUserAccount());
            }
        }
        return dao.addDeclareEconomicIndicatorsHead(oo);
    }

    public void removeDeclareEconomicIndicatorsHeadById(Integer id){
        DeclareEconomicIndicatorsContent select = new DeclareEconomicIndicatorsContent();
        select.setIndicatorsHeadId(id);
        List<DeclareEconomicIndicatorsContent> contentList = declareEconomicIndicatorsContentService.getDeclareEconomicIndicatorsContentList(select) ;
        dao.deleteDeclareEconomicIndicatorsHead(id);
        if (CollectionUtils.isNotEmpty(contentList)){
            List<Integer> ids = contentList.stream().map(oo -> oo.getId()).collect(Collectors.toList());
            declareEconomicIndicatorsContentService.removeDeclareEconomicIndicatorsContentIds(ids);
        }
        deleteDeclareEconomicIndicatorsHead(id) ;
    }

    public boolean deleteDeclareEconomicIndicatorsHead(Integer id) {
        return dao.deleteDeclareEconomicIndicatorsHead(id);
    }

    public DeclareEconomicIndicatorsHead getByDeclareEconomicIndicatorsHeadId(Integer id) {
        return dao.getByDeclareEconomicIndicatorsHeadId(id);
    }


    public List<DeclareEconomicIndicatorsHead> getDeclareEconomicIndicatorsHeadList(DeclareEconomicIndicatorsHead oo) {
        return dao.getDeclareEconomicIndicatorsHeadList(oo);
    }
    
}
