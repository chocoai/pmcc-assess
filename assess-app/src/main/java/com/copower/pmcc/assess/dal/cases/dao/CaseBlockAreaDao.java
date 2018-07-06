package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseBlockArea;
import com.copower.pmcc.assess.dal.cases.entity.CaseBlockAreaExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseBlockAreaMapper;
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
public class CaseBlockAreaDao {
    @Autowired
    private CaseBlockAreaMapper caseBlockAreaMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseBlockArea getBlockAreaById(Integer id) {
        return caseBlockAreaMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseBlockArea
     * @return
     */
    public List<CaseBlockArea> getBlockAreaList(CaseBlockArea caseBlockArea) {
        CaseBlockAreaExample example = new CaseBlockAreaExample();
        MybatisUtils.convertObj2Example(caseBlockArea, example);
        return caseBlockAreaMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseBlockArea
     * @return
     */
    public boolean addBlockArea(CaseBlockArea caseBlockArea) {
        return caseBlockAreaMapper.insertSelective(caseBlockArea) > 0;
    }

    /**
     * 编辑
     * @param caseBlockArea
     * @return
     */
    public boolean updateBlockArea(CaseBlockArea caseBlockArea) {
        return caseBlockAreaMapper.updateByPrimaryKeySelective(caseBlockArea) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBlockArea(Integer id){
        return caseBlockAreaMapper.deleteByPrimaryKey(id) > 0;
    }

}