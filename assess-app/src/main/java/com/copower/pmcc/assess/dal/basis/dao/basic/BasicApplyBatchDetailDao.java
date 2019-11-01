package com.copower.pmcc.assess.dal.basis.dao.basic;

import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetailExample;
import com.copower.pmcc.assess.dal.basis.mapper.BasicApplyBatchDetailMapper;
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


}
