package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareInfo;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareInfoExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdMarketCompareInfoMapper;
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
public class MdMarketCompareInfoDao {
    @Autowired
    private MdMarketCompareInfoMapper mdMarketCompareInfoMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdMarketCompareInfo getMarketCompareInfoById(Integer id) {
        return mdMarketCompareInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMarketCompareInfo
     * @return
     */
    public List<MdMarketCompareInfo> getMarketCompareInfoList(MdMarketCompareInfo examineMarketCompareInfo) {
        MdMarketCompareInfoExample example = new MdMarketCompareInfoExample();
        MybatisUtils.convertObj2Example(examineMarketCompareInfo, example);
        return mdMarketCompareInfoMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMarketCompareInfo
     * @return
     */
    public boolean addMarketCompareInfo(MdMarketCompareInfo examineMarketCompareInfo) {
        return mdMarketCompareInfoMapper.insertSelective(examineMarketCompareInfo) > 0;
    }

    /**
     * 编辑
     * @param examineMarketCompareInfo
     * @return
     */
    public boolean updateMarketCompareInfo(MdMarketCompareInfo examineMarketCompareInfo) {
        return mdMarketCompareInfoMapper.updateByPrimaryKeySelective(examineMarketCompareInfo) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMarketCompareInfo(Integer id){
        return mdMarketCompareInfoMapper.deleteByPrimaryKey(id) > 0;
    }

}