package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdIncomeDateSection;
import com.copower.pmcc.assess.dal.basis.entity.MdIncomeDateSectionExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdIncomeDateSectionMapper;
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
public class MdIncomeDateSectionDao {
    @Autowired
    private MdIncomeDateSectionMapper mdIncomeDateSectionMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public MdIncomeDateSection getDateSectionById(Integer id) {
        return mdIncomeDateSectionMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param mdIncomeDateSection
     * @return
     */
    public MdIncomeDateSection getDateSection(MdIncomeDateSection mdIncomeDateSection) {
        MdIncomeDateSectionExample example = new MdIncomeDateSectionExample();
        MybatisUtils.convertObj2Example(mdIncomeDateSection, example);
        List<MdIncomeDateSection> mdIncomeDateSections = mdIncomeDateSectionMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(mdIncomeDateSections)) return mdIncomeDateSections.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param mdIncomeDateSection
     * @return
     */
    public List<MdIncomeDateSection> getDateSectionList(MdIncomeDateSection mdIncomeDateSection) {
        MdIncomeDateSectionExample example = new MdIncomeDateSectionExample();
        MybatisUtils.convertObj2Example(mdIncomeDateSection, example);
        return mdIncomeDateSectionMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param mdIncomeDateSection
     * @return
     */
    public int addDateSection(MdIncomeDateSection mdIncomeDateSection) {
        mdIncomeDateSectionMapper.insertSelective(mdIncomeDateSection);
        return mdIncomeDateSection.getId();
    }

    /**
     * 更新
     *
     * @param mdIncomeDateSection
     * @return
     */
    public boolean updateDateSection(MdIncomeDateSection mdIncomeDateSection) {
        return mdIncomeDateSectionMapper.updateByPrimaryKeySelective(mdIncomeDateSection) > 0;
    }

    public boolean updateDateSection(MdIncomeDateSection where,MdIncomeDateSection mdIncomeDateSection) {
        MdIncomeDateSectionExample example = new MdIncomeDateSectionExample();
        MybatisUtils.convertObj2Example(where, example);
        return mdIncomeDateSectionMapper.updateByExampleSelective(mdIncomeDateSection,example) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteDateSection(Integer id) {
        return mdIncomeDateSectionMapper.deleteByPrimaryKey(id) > 0;
    }

}