package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseNetInfoRecordContent;
import com.copower.pmcc.assess.dal.cases.entity.CaseNetInfoRecordContentExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseNetInfoRecordContentMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Repository
public class CaseNetInfoRecordContentDao {
    @Autowired
    private CaseNetInfoRecordContentMapper caseNetInfoRecordContentMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public CaseNetInfoRecordContent getInfoById(Integer id) {
        return caseNetInfoRecordContentMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param caseNetInfoRecordContent
     * @return
     */
    public CaseNetInfoRecordContent getCaseNetInfoRecordContent(CaseNetInfoRecordContent caseNetInfoRecordContent) {
        CaseNetInfoRecordContentExample example = new CaseNetInfoRecordContentExample();
        MybatisUtils.convertObj2Example(caseNetInfoRecordContent, example);
        List<CaseNetInfoRecordContent> caseNetInfoRecordContents = caseNetInfoRecordContentMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(caseNetInfoRecordContents)) return caseNetInfoRecordContents.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineInfo
     * @return
     */
    public List<CaseNetInfoRecordContent> getInfoList(CaseNetInfoRecordContent examineInfo) {
        CaseNetInfoRecordContentExample example = new CaseNetInfoRecordContentExample();
        MybatisUtils.convertObj2Example(examineInfo, example);
        return caseNetInfoRecordContentMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineInfo
     * @return
     */
    public boolean addInfo(CaseNetInfoRecordContent examineInfo) {
        return caseNetInfoRecordContentMapper.insertSelective(examineInfo) > 0;
    }

    /**
     * 编辑
     *
     * @param examineInfo
     * @return
     */
    public boolean updateInfo(CaseNetInfoRecordContent examineInfo) {
        return caseNetInfoRecordContentMapper.updateByPrimaryKeySelective(examineInfo) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteInfo(Integer id) {
        return caseNetInfoRecordContentMapper.deleteByPrimaryKey(id) > 0;
    }


}
