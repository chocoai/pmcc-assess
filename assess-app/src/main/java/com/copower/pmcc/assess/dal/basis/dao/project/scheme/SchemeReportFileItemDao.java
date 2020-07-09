package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileItem;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileItemExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeReportFileItemMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SchemeReportFileItemDao {
    @Autowired
    private SchemeReportFileItemMapper schemeReportFileItemMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public SchemeReportFileItem getReportFileItemById(Integer id) {
        return schemeReportFileItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param schemeReportFileItem
     * @return
     */
    public SchemeReportFileItem getSchemeReportFileItem(SchemeReportFileItem schemeReportFileItem) {
        SchemeReportFileItemExample example = new SchemeReportFileItemExample();
        MybatisUtils.convertObj2Example(schemeReportFileItem, example);
        List<SchemeReportFileItem> schemeReportFileItems = schemeReportFileItemMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(schemeReportFileItems)) return schemeReportFileItems.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineReportFileItem
     * @return
     */
    public List<SchemeReportFileItem> getReportFileItemList(SchemeReportFileItem examineReportFileItem) {
        SchemeReportFileItemExample example = new SchemeReportFileItemExample();
        MybatisUtils.convertObj2Example(examineReportFileItem, example);
        example.setOrderByClause("sorting");
        return schemeReportFileItemMapper.selectByExample(example);
    }

    public List<SchemeReportFileItem> getReportFileItemListWhereJudgeIdNull() {
        SchemeReportFileItemExample example = new SchemeReportFileItemExample();
        SchemeReportFileItemExample.Criteria criteria = example.createCriteria();
        criteria.andSchemeJudgeObjectIdIsNull().andDeclareRecordIdIsNotNull();
        example.setOrderByClause("sorting");
        return schemeReportFileItemMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineReportFileItem
     * @return
     */
    public boolean addReportFileItem(SchemeReportFileItem examineReportFileItem) {
        return schemeReportFileItemMapper.insertSelective(examineReportFileItem) > 0;
    }

    /**
     * 编辑
     *
     * @param examineReportFileItem
     * @return
     */
    public boolean updateReportFileItem(SchemeReportFileItem examineReportFileItem) {
        return schemeReportFileItemMapper.updateByPrimaryKeySelective(examineReportFileItem) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteReportFileItem(Integer id) {
        return schemeReportFileItemMapper.deleteByPrimaryKey(id) > 0;
    }

    public boolean hasReportFileItem(Integer judgeObjectId, String type, Integer attachmentId) {
        SchemeReportFileItemExample example = new SchemeReportFileItemExample();
        SchemeReportFileItemExample.Criteria criteria = example.createCriteria();
        criteria.andSchemeJudgeObjectIdEqualTo(judgeObjectId)
                .andTypeEqualTo(type).andAttachmentIdEqualTo(attachmentId);
        return schemeReportFileItemMapper.countByExample(example) > 0;
    }
}
