package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareField;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareFieldExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdMarketCompareFieldMapper;
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
public class MdMarketCompareFieldDao {
    @Autowired
    private MdMarketCompareFieldMapper mdMarketCompareFieldMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public MdMarketCompareField getMarketCompareFieldById(Integer id) {
        return mdMarketCompareFieldMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineMarketCompareField
     * @return
     */
    public List<MdMarketCompareField> getMarketCompareFieldList(MdMarketCompareField examineMarketCompareField) {
        MdMarketCompareFieldExample example = new MdMarketCompareFieldExample();
        MybatisUtils.convertObj2Example(examineMarketCompareField, example);
        return mdMarketCompareFieldMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineMarketCompareField
     * @return
     */
    public boolean addMarketCompareField(MdMarketCompareField examineMarketCompareField) {
        return mdMarketCompareFieldMapper.insertSelective(examineMarketCompareField) > 0;
    }

    /**
     * 编辑
     * @param examineMarketCompareField
     * @return
     */
    public boolean updateMarketCompareField(MdMarketCompareField examineMarketCompareField) {
        return mdMarketCompareFieldMapper.updateByPrimaryKeySelective(examineMarketCompareField) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMarketCompareField(Integer id){
        return mdMarketCompareFieldMapper.deleteByPrimaryKey(id) > 0;
    }

}