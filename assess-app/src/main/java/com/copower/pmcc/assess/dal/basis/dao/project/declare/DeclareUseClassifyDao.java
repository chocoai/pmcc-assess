package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareUseClassify;
import com.copower.pmcc.assess.dal.basis.entity.DeclareUseClassifyExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareUseClassifyMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/8/31
 * @time: 16:00
 */
@Repository
public class DeclareUseClassifyDao {
    @Autowired
    private DeclareUseClassifyMapper declareUseClassifyMapper;

    public DeclareUseClassify getDeclareUseClassify(Integer projectId,Integer planDetailsId,Integer projectClassifyId){
        DeclareUseClassifyExample example = new DeclareUseClassifyExample();
        DeclareUseClassifyExample.Criteria criteria = example.createCriteria();
        criteria.andProjectIdEqualTo(projectId).andPlanDetailsIdEqualTo(planDetailsId).andProjectClassifyIdEqualTo(projectClassifyId);
        List<DeclareUseClassify> declareUseClassifyList = declareUseClassifyMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(declareUseClassifyList))
            return declareUseClassifyList.get(0);
        return null;
    }

    public List<DeclareUseClassify> getDeclareUseClassifyList(Integer projectId,Integer planDetailsId) {
        DeclareUseClassifyExample example = new DeclareUseClassifyExample();
        DeclareUseClassifyExample.Criteria criteria = example.createCriteria();
        if (projectId != null) {
            criteria.andProjectIdEqualTo(projectId);
        }
        if (projectId != null) {
            criteria.andPlanDetailsIdEqualTo(planDetailsId);
        }
        example.setOrderByClause(" id desc");
        List<DeclareUseClassify> DeclareUseClassifys = declareUseClassifyMapper.selectByExample(example);
        return DeclareUseClassifys;
    }


    public Boolean addDeclareUseClassify(DeclareUseClassify DeclareUseClassify) {
        int i = declareUseClassifyMapper.insertSelective(DeclareUseClassify);
        return i == 1;
    }

    public Boolean updateDeclareUseClassify(DeclareUseClassify DeclareUseClassify) {
        int i = declareUseClassifyMapper.updateByPrimaryKeySelective(DeclareUseClassify);
        return i >= 0;
    }

    public Boolean deleteDeclareUseClassify(Integer id){
        return declareUseClassifyMapper.deleteByPrimaryKey(id) > 0;
    }

}
