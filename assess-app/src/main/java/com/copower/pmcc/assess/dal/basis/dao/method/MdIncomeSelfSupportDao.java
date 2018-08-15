package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeSelfSupport;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeSelfSupportExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdIncomeSelfSupportMapper;
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
public class MdIncomeSelfSupportDao {
    @Autowired
    private MdIncomeSelfSupportMapper mdIncomeSelfSupportMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdIncomeSelfSupport getSelfSupportById(Integer id) {
        return mdIncomeSelfSupportMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param mdIncomeSelfSupport
     * @return
     */
    public List<MdIncomeSelfSupport> getSelfSupportList(MdIncomeSelfSupport mdIncomeSelfSupport) {
        MdIncomeSelfSupportExample example = new MdIncomeSelfSupportExample();
        MybatisUtils.convertObj2Example(mdIncomeSelfSupport, example);
        return mdIncomeSelfSupportMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param mdIncomeSelfSupport
     * @return
     */
    public int addSelfSupport(MdIncomeSelfSupport mdIncomeSelfSupport) {
        mdIncomeSelfSupportMapper.insertSelective(mdIncomeSelfSupport);
        return mdIncomeSelfSupport.getId();
    }

    /**
     * 编辑
     * @param mdIncomeSelfSupport
     * @return
     */
    public boolean updateSelfSupport(MdIncomeSelfSupport mdIncomeSelfSupport) {
        return mdIncomeSelfSupportMapper.updateByPrimaryKeySelective(mdIncomeSelfSupport) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteSelfSupport(Integer id){
        return mdIncomeSelfSupportMapper.deleteByPrimaryKey(id) > 0;
    }

}