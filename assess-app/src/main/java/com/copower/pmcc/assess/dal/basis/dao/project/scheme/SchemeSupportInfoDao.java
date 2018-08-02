package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeSupportInfo;
import com.copower.pmcc.assess.dal.basis.entity.SchemeSupportInfoExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeSupportInfoMapper;
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
public class SchemeSupportInfoDao {
    @Autowired
    private SchemeSupportInfoMapper schemeSupportInfoMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public SchemeSupportInfo getSupportInfoById(Integer id) {
        return schemeSupportInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineSupportInfo
     * @return
     */
    public List<SchemeSupportInfo> getSupportInfoList(SchemeSupportInfo examineSupportInfo) {
        SchemeSupportInfoExample example = new SchemeSupportInfoExample();
        MybatisUtils.convertObj2Example(examineSupportInfo, example);
        return schemeSupportInfoMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineSupportInfo
     * @return
     */
    public boolean addSupportInfo(SchemeSupportInfo examineSupportInfo) {
        return schemeSupportInfoMapper.insertSelective(examineSupportInfo) > 0;
    }

    /**
     * 编辑
     * @param examineSupportInfo
     * @return
     */
    public boolean updateSupportInfo(SchemeSupportInfo examineSupportInfo) {
        return schemeSupportInfoMapper.updateByPrimaryKeySelective(examineSupportInfo) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteSupportInfo(Integer id){
        return schemeSupportInfoMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取数据条数
     * @param planDetailsId
     * @return
     */
    public int getCountByPlanDetailsId(Integer planDetailsId){
        SchemeSupportInfoExample example = new SchemeSupportInfoExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        return schemeSupportInfoMapper.countByExample(example);
    }

}