package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingLeisurePlace;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingLeisurePlaceExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineMatchingLeisurePlaceMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/20 18:06
 * @Description:
 */
@Repository
public class ExamineMatchingLeisurePlaceDao {
    @Autowired
    private ExamineMatchingLeisurePlaceMapper examineMatchingLeisurePlaceMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineMatchingLeisurePlace getMatchingFinanceById(Integer id) {
        return examineMatchingLeisurePlaceMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMatchingLeisurePlace
     * @return
     */
    public List<ExamineMatchingLeisurePlace> getMatchingFinanceList(ExamineMatchingLeisurePlace examineMatchingLeisurePlace) {
        ExamineMatchingLeisurePlaceExample example = new ExamineMatchingLeisurePlaceExample();
        MybatisUtils.convertObj2Example(examineMatchingLeisurePlace, example);
        return examineMatchingLeisurePlaceMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMatchingLeisurePlace
     * @return
     */
    public boolean addMatchingFinance(ExamineMatchingLeisurePlace examineMatchingLeisurePlace) {
        return examineMatchingLeisurePlaceMapper.insertSelective(examineMatchingLeisurePlace) > 0;
    }

    /**
     * 编辑
     * @param examineMatchingLeisurePlace
     * @return
     */
    public boolean updateMatchingFinance(ExamineMatchingLeisurePlace examineMatchingLeisurePlace) {
        return examineMatchingLeisurePlaceMapper.updateByPrimaryKeySelective(examineMatchingLeisurePlace) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingFinance(Integer id){
        return examineMatchingLeisurePlaceMapper.deleteByPrimaryKey(id) > 0;
    }

}
