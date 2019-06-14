package com.copower.pmcc.assess.dal.basis.dao.project.declare;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordExtend;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordExtendExample;
import com.copower.pmcc.assess.dal.basis.mapper.DeclareRecordExtendMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: zch
 * @date: 2019/6/14 16:42
 * @description:
 */
@Repository
public class DeclareRecordExtendDao {

    @Autowired
    private DeclareRecordExtendMapper mapper;

    public List<DeclareRecordExtend> getDeclareRecordListByIds(List<Integer> ids) {
        DeclareRecordExtendExample example = new DeclareRecordExtendExample();
        example.createCriteria().andIdIn(ids);
        return mapper.selectByExample(example);
    }

    public boolean updateDeclareRecord( DeclareRecordExtend declareRecord) {
        return mapper.updateByPrimaryKeySelective(declareRecord) > 0;
    }

    public boolean addDeclareRecord( DeclareRecordExtend declareRecord) {
        return mapper.insertSelective(declareRecord) > 0;
    }

    public boolean deleteDeclareRecord(Integer id) {
        return mapper.deleteByPrimaryKey(id) > 0;
    }

    public  DeclareRecordExtend getDeclareRecordById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<DeclareRecordExtend> getDeclareLandUsePermitList(DeclareRecordExtend oo){
        DeclareRecordExtendExample example = new DeclareRecordExtendExample();
        MybatisUtils.convertObj2Example(oo, example);
        return mapper.selectByExample(example);
    }

}
