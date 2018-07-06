package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateNetwork;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateNetworkExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineEstateNetworkMapper;
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
public class ExamineEstateNetworkDao {
    @Autowired
    private ExamineEstateNetworkMapper examineEstateNetworkMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineEstateNetwork getEstateNetworkById(Integer id) {
        return examineEstateNetworkMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineEstateNetwork
     * @return
     */
    public List<ExamineEstateNetwork> getEstateNetworkList(ExamineEstateNetwork examineEstateNetwork) {
        ExamineEstateNetworkExample example = new ExamineEstateNetworkExample();
        MybatisUtils.convertObj2Example(examineEstateNetwork, example);
        return examineEstateNetworkMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineEstateNetwork
     * @return
     */
    public boolean addEstateNetwork(ExamineEstateNetwork examineEstateNetwork) {
        return examineEstateNetworkMapper.insertSelective(examineEstateNetwork) > 0;
    }

    /**
     * 编辑
     * @param examineEstateNetwork
     * @return
     */
    public boolean updateEstateNetwork(ExamineEstateNetwork examineEstateNetwork) {
        return examineEstateNetworkMapper.updateByPrimaryKeySelective(examineEstateNetwork) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstateNetwork(Integer id){
        return examineEstateNetworkMapper.deleteByPrimaryKey(id) > 0;
    }

}