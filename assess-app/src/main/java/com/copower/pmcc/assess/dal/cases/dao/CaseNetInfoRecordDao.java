package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseNetInfoRecord;
import com.copower.pmcc.assess.dal.cases.entity.CaseNetInfoRecordExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseNetInfoRecordMapper;
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
public class CaseNetInfoRecordDao {
    @Autowired
    private CaseNetInfoRecordMapper caseNetInfoRecordMapper;

    public void batchUpdateNetInfoRecord(CaseNetInfoRecord obj, List<Integer> ids){
        CaseNetInfoRecordExample example = new CaseNetInfoRecordExample();
        CaseNetInfoRecordExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids) ;
        caseNetInfoRecordMapper.updateByExampleSelective(obj,example) ;
    }

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public CaseNetInfoRecord getInfoById(Integer id) {
        return caseNetInfoRecordMapper.selectByPrimaryKey(id);
    }


    /**
     * 获取数据
     *
     * @param caseNetInfoRecord
     * @return
     */
    public CaseNetInfoRecord getCaseNetInfoRecord(CaseNetInfoRecord caseNetInfoRecord) {
        CaseNetInfoRecordExample example = new CaseNetInfoRecordExample();
        MybatisUtils.convertObj2Example(caseNetInfoRecord, example);
        List<CaseNetInfoRecord> caseNetInfoRecords = caseNetInfoRecordMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(caseNetInfoRecords)) return caseNetInfoRecords.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineInfo
     * @return
     */
    public List<CaseNetInfoRecord> getInfoList(CaseNetInfoRecord examineInfo) {
        CaseNetInfoRecordExample example = new CaseNetInfoRecordExample();
        MybatisUtils.convertObj2Example(examineInfo, example);
        return caseNetInfoRecordMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param caseNetInfoRecord
     * @return
     */
    public boolean addInfo(CaseNetInfoRecord caseNetInfoRecord) {
        //验证重复 重复数据自动设置为删除状态
        CaseNetInfoRecordExample example = new CaseNetInfoRecordExample();
        CaseNetInfoRecordExample.Criteria criteria = example.createCriteria();
        criteria.andProvinceEqualTo(caseNetInfoRecord.getProvince())
                .andTitleEqualTo(caseNetInfoRecord.getTitle()).andSourceSiteUrlEqualTo(caseNetInfoRecord.getSourceSiteUrl());
        if (caseNetInfoRecordMapper.countByExample(example) <= 0) {
            return caseNetInfoRecordMapper.insertSelective(caseNetInfoRecord) > 0;
        }
        return true;
    }

    /**
     * 编辑
     *
     * @param examineInfo
     * @return
     */
    public boolean updateInfo(CaseNetInfoRecord examineInfo) {
        return caseNetInfoRecordMapper.updateByPrimaryKeySelective(examineInfo) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteInfo(Integer id) {
        return caseNetInfoRecordMapper.deleteByPrimaryKey(id) > 0;
    }


}
