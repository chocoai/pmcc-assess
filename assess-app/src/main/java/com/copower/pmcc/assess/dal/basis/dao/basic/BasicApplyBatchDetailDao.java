package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetailExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicApplyBatchDetailMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Repository
public class BasicApplyBatchDetailDao {
    @Autowired
    private BasicApplyBatchDetailMapper basicApplyBatchDetailMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public BasicApplyBatchDetail getInfoById(Integer id) {
        return basicApplyBatchDetailMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param basicApplyBatchDetail
     * @return
     */
    public BasicApplyBatchDetail getBasicApplyBatchDetail(BasicApplyBatchDetail basicApplyBatchDetail) {
        BasicApplyBatchDetailExample example = new BasicApplyBatchDetailExample();
        BasicApplyBatchDetailExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicApplyBatchDetail, criteria);
        List<BasicApplyBatchDetail> basicApplyBatchDetails = basicApplyBatchDetailMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(basicApplyBatchDetails)) return basicApplyBatchDetails.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param basicApplyBatchDetail
     * @return
     */
    public List<BasicApplyBatchDetail> getInfoList(BasicApplyBatchDetail basicApplyBatchDetail) {
        BasicApplyBatchDetailExample example = new BasicApplyBatchDetailExample();
        BasicApplyBatchDetailExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        MybatisUtils.convertObj2Criteria(basicApplyBatchDetail, criteria);
        return basicApplyBatchDetailMapper.selectByExample(example);
    }

    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailListByTypes(List<String> types, Integer applyBatchId,Integer pid) {
        BasicApplyBatchDetailExample example = new BasicApplyBatchDetailExample();
        BasicApplyBatchDetailExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        if (CollectionUtils.isNotEmpty(types)) {
            criteria.andTypeIn(types);
        }
        if (applyBatchId != null){
            criteria.andApplyBatchIdEqualTo(applyBatchId);
        }
        if (pid != null){
            criteria.andPidEqualTo(pid);
        }
        return basicApplyBatchDetailMapper.selectByExample(example);
    }

    public List<BasicApplyBatchDetail> getBasicApplyBatchDetailList(List<Integer> basicApplyBatchIds, String type) {
        BasicApplyBatchDetailExample example = new BasicApplyBatchDetailExample();
        BasicApplyBatchDetailExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        if (CollectionUtils.isNotEmpty(basicApplyBatchIds))
            criteria.andApplyBatchIdIn(basicApplyBatchIds);
        if (StringUtils.isNotBlank(type))
            criteria.andTypeEqualTo(type);
        return basicApplyBatchDetailMapper.selectByExample(example);
    }

    public List<BasicApplyBatchDetail> getBatchDetailListByIds(List<Integer> ids) {
        BasicApplyBatchDetailExample example = new BasicApplyBatchDetailExample();
        BasicApplyBatchDetailExample.Criteria criteria = example.createCriteria().andBisDeleteEqualTo(false);
        if (CollectionUtils.isNotEmpty(ids))
            criteria.andIdIn(ids);
        return basicApplyBatchDetailMapper.selectByExample(example);
    }


    /**
     * 案例升级数据
     *
     * @param applyBatchId
     * @return
     */
    public List<BasicApplyBatchDetail> getUpgradeAddDetail(Integer applyBatchId) {
        BasicApplyBatchDetailExample example = new BasicApplyBatchDetailExample();
        BasicApplyBatchDetailExample.Criteria criteria = example.createCriteria();
        criteria.andUpgradeTableIdIsNotNull();
        criteria.andApplyBatchIdEqualTo(applyBatchId);
        return basicApplyBatchDetailMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineInfo
     * @return
     */
    public boolean addInfo(BasicApplyBatchDetail examineInfo) {
        return basicApplyBatchDetailMapper.insertSelective(examineInfo) > 0;
    }

    /**
     * 编辑
     *
     * @param examineInfo
     * @return
     */
    public boolean updateInfo(BasicApplyBatchDetail examineInfo) {
        return basicApplyBatchDetailMapper.updateByPrimaryKeySelective(examineInfo) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteInfo(Integer id) {
        BasicApplyBatchDetail basicApplyBatchDetail = getInfoById(id);
        if (basicApplyBatchDetail == null) return false;
        basicApplyBatchDetail.setBisDelete(true);
        return basicApplyBatchDetailMapper.updateByPrimaryKeySelective(basicApplyBatchDetail) > 0;
    }

    public BasicApplyBatchDetail getBasicApplyBatchDetailList(Integer basicApplyBatchId, String tableName, Integer tableId,String name) {
        BasicApplyBatchDetailExample example = new BasicApplyBatchDetailExample();
        BasicApplyBatchDetailExample.Criteria criteria = example.createCriteria();
        if(basicApplyBatchId!=null){
            criteria.andApplyBatchIdEqualTo(basicApplyBatchId);
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(String.format("%s%s%s", "%", name, "%"));
        }
        if (StringUtils.isNotBlank(tableName)) {
            criteria.andTableNameEqualTo(tableName);
        }
        if (tableId!=null) {
            criteria.andTableIdEqualTo(tableId);
        }
        example.setOrderByClause("id desc");

        List<BasicApplyBatchDetail> basicApplyBatchDetails = basicApplyBatchDetailMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(basicApplyBatchDetails)) return basicApplyBatchDetails.get(0);
        return null;
    }
}
