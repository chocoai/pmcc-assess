package com.copower.pmcc.assess.dal.basis.dao.project.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseRoomDecorate;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseRoomDecorateExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineHouseRoomDecorateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
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
public class ExamineHouseRoomDecorateDao {
    @Autowired
    private ExamineHouseRoomDecorateMapper examineHouseRoomDecorateMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineHouseRoomDecorate getHouseRoomDecorateById(Integer id) {
        return examineHouseRoomDecorateMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineHouseRoomDecorate
     * @return
     */
    public List<ExamineHouseRoomDecorate> getHouseRoomDecorateList(ExamineHouseRoomDecorate examineHouseRoomDecorate) {
        ExamineHouseRoomDecorateExample example = new ExamineHouseRoomDecorateExample();
        MybatisUtils.convertObj2Example(examineHouseRoomDecorate, example);
        return examineHouseRoomDecorateMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineHouseRoomDecorate
     * @return
     */
    public int addHouseRoomDecorate(ExamineHouseRoomDecorate examineHouseRoomDecorate) {
        examineHouseRoomDecorateMapper.insertSelective(examineHouseRoomDecorate);
        return examineHouseRoomDecorate.getId();
    }

    /**
     * 编辑
     * @param examineHouseRoomDecorate
     * @return
     */
    public boolean updateHouseRoomDecorate(ExamineHouseRoomDecorate examineHouseRoomDecorate) {
        return examineHouseRoomDecorateMapper.updateByPrimaryKeySelective(examineHouseRoomDecorate) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteHouseRoomDecorate(Integer id){
        return examineHouseRoomDecorateMapper.deleteByPrimaryKey(id) > 0;
    }

}