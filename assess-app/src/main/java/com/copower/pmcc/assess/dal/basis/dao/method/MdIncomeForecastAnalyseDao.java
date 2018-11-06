package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastAnalyse;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeForecastAnalyseExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdIncomeForecastAnalyseMapper;
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
public class MdIncomeForecastAnalyseDao {
    @Autowired
    private MdIncomeForecastAnalyseMapper mdIncomeForecastAnalyseMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public MdIncomeForecastAnalyse getForecastAnalyseById(Integer id) {
        return mdIncomeForecastAnalyseMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param mdIncomeForecastAnalyse
     * @return
     */
    public MdIncomeForecastAnalyse getForecastAnalyse(MdIncomeForecastAnalyse mdIncomeForecastAnalyse) {
        MdIncomeForecastAnalyseExample example = new MdIncomeForecastAnalyseExample();
        MybatisUtils.convertObj2Example(mdIncomeForecastAnalyse, example);
        List<MdIncomeForecastAnalyse> mdIncomeForecastAnalyses = mdIncomeForecastAnalyseMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(mdIncomeForecastAnalyses)) return mdIncomeForecastAnalyses.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param mdIncomeForecastAnalyse
     * @return
     */
    public List<MdIncomeForecastAnalyse> getForecastAnalyseList(MdIncomeForecastAnalyse mdIncomeForecastAnalyse) {
        MdIncomeForecastAnalyseExample example = new MdIncomeForecastAnalyseExample();
        MybatisUtils.convertObj2Example(mdIncomeForecastAnalyse, example);
        return mdIncomeForecastAnalyseMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param mdIncomeForecastAnalyse
     * @return
     */
    public int addForecastAnalyse(MdIncomeForecastAnalyse mdIncomeForecastAnalyse) {
        mdIncomeForecastAnalyseMapper.insertSelective(mdIncomeForecastAnalyse);
        return mdIncomeForecastAnalyse.getId();
    }

    /**
     * 编辑
     *
     * @param mdIncomeForecastAnalyse
     * @return
     */
    public boolean updateForecastAnalyse(MdIncomeForecastAnalyse mdIncomeForecastAnalyse) {
        return mdIncomeForecastAnalyseMapper.updateByPrimaryKeySelective(mdIncomeForecastAnalyse) > 0;
    }

    public int getForecastAnalyseCount(MdIncomeForecastAnalyse mdIncomeForecastAnalyse){
        MdIncomeForecastAnalyseExample example = new MdIncomeForecastAnalyseExample();
        MybatisUtils.convertObj2Example(mdIncomeForecastAnalyse, example);
        return mdIncomeForecastAnalyseMapper.countByExample(example);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteForecastAnalyse(Integer id) {
        return mdIncomeForecastAnalyseMapper.deleteByPrimaryKey(id) > 0;
    }


}