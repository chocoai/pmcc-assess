package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareEconomicIndicatorsContentDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareEconomicIndicatorsContent;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zch on 2019/6/25.
 */
@Service
public class DeclareEconomicIndicatorsContentService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private DeclareEconomicIndicatorsContentDao dao;


    public boolean updateDeclareEconomicIndicatorsContent(DeclareEconomicIndicatorsContent oo) {
        return dao.updateDeclareEconomicIndicatorsContent(oo);
    }

    public void removeDeclareEconomicIndicatorsContentIds(List<Integer> ids){
        dao.removeDeclareEconomicIndicatorsContentIds(ids);
    }

    public void removeDeclareEconomicIndicatorsByContent(Integer id){
        DeclareEconomicIndicatorsContent select = new DeclareEconomicIndicatorsContent();
        select.setIndicatorsHeadId(id);
        List<DeclareEconomicIndicatorsContent> contentList = getDeclareEconomicIndicatorsContentList(select) ;
        if (CollectionUtils.isNotEmpty(contentList)){
            List<Integer> ids = contentList.stream().map(oo -> oo.getId()).collect(Collectors.toList());
            removeDeclareEconomicIndicatorsContentIds(ids);
        }
    }


    public boolean addDeclareEconomicIndicatorsContent(DeclareEconomicIndicatorsContent oo) {
        if (oo != null){
            if (StringUtils.isEmpty(oo.getCreator())){
                oo.setCreator(commonService.thisUserAccount());
            }
        }
        return dao.addDeclareEconomicIndicatorsContent(oo);
    }

    public boolean deleteDeclareEconomicIndicatorsContent(Integer id) {
        return dao.deleteDeclareEconomicIndicatorsContent(id);
    }

    public DeclareEconomicIndicatorsContent getByDeclareEconomicIndicatorsContentId(Integer id) {
        return dao.getByDeclareEconomicIndicatorsContentId(id);
    }


    public List<DeclareEconomicIndicatorsContent> getDeclareEconomicIndicatorsContentList(DeclareEconomicIndicatorsContent oo) {
        return dao.getDeclareEconomicIndicatorsContentList(oo);
    }
}
