package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstate;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateExample;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstate;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineEstateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: wangpc
 * @data: 2018/07/06
 * @time: 14:36
 */
@Repository
public class ExamineEstateDao {
    @Autowired
    private ExamineEstateMapper examineEstateMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineEstate getEstateById(Integer id) {
        return examineEstateMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据信息
     * @param declareId
     * @return
     */
    public ExamineEstate getEstateByDeclareId(Integer declareId) {
        ExamineEstateExample example = new ExamineEstateExample();
        example.createCriteria().andDeclareIdEqualTo(declareId);
        List<ExamineEstate> blockList = examineEstateMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(blockList)) return blockList.get(0);
        return null;
    }

    /**
     * 获取数据列表
     * @param examineEstate
     * @return
     */
    public List<ExamineEstate> getEstateList(ExamineEstate examineEstate) {
        ExamineEstateExample example = new ExamineEstateExample();
        MybatisUtils.convertObj2Example(examineEstate, example);
        return examineEstateMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineEstate
     * @return
     */
    public boolean addEstate(ExamineEstate examineEstate) {
        return examineEstateMapper.insertSelective(examineEstate) > 0;
    }

    /**
     * 编辑
     * @param examineEstate
     * @return
     */
    public boolean updateEstate(ExamineEstate examineEstate) {
        return examineEstateMapper.updateByPrimaryKeySelective(examineEstate) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstate(Integer id){
        return examineEstateMapper.deleteByPrimaryKey(id) > 0;
    }

}