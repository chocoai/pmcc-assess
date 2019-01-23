package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileCustom;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileCustomExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeReportFileCustomMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SchemeReportFileCustomDao {
    @Autowired
    private SchemeReportFileCustomMapper schemeReportFileCustomMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public SchemeReportFileCustom getReportFileCustomById(Integer id) {
        return schemeReportFileCustomMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param schemeReportFileCustom
     * @return
     */
    public SchemeReportFileCustom getSchemeReportFileCustom(SchemeReportFileCustom schemeReportFileCustom) {
        SchemeReportFileCustomExample example = new SchemeReportFileCustomExample();
        MybatisUtils.convertObj2Example(schemeReportFileCustom, example);
        List<SchemeReportFileCustom> schemeReportFileCustoms = schemeReportFileCustomMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(schemeReportFileCustoms)) return schemeReportFileCustoms.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineReportFileCustom
     * @return
     */
    public List<SchemeReportFileCustom> getReportFileCustomList(SchemeReportFileCustom examineReportFileCustom) {
        SchemeReportFileCustomExample example = new SchemeReportFileCustomExample();
        MybatisUtils.convertObj2Example(examineReportFileCustom, example);
        return schemeReportFileCustomMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineReportFileCustom
     * @return
     */
    public boolean addReportFileCustom(SchemeReportFileCustom examineReportFileCustom) {
        return schemeReportFileCustomMapper.insertSelective(examineReportFileCustom) > 0;
    }

    /**
     * 编辑
     *
     * @param examineReportFileCustom
     * @return
     */
    public boolean updateReportFileCustom(SchemeReportFileCustom examineReportFileCustom) {
        return schemeReportFileCustomMapper.updateByPrimaryKeySelective(examineReportFileCustom) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteReportFileCustom(Integer id) {
        return schemeReportFileCustomMapper.deleteByPrimaryKey(id) > 0;
    }


}
