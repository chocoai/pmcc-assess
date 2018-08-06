package com.copower.pmcc.assess.dal.basis.dao.project.compile;

import com.copower.pmcc.assess.dal.basis.entity.CompileReportDetail;
import com.copower.pmcc.assess.dal.basis.entity.CompileReportDetailExample;
import com.copower.pmcc.assess.dal.basis.mapper.CompileReportDetailMapper;
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
public class CompileReportDetailDao {
    @Autowired
    private CompileReportDetailMapper compileReportDetailMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CompileReportDetail getReportDetailById(Integer id) {
        return compileReportDetailMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineReportDetail
     * @return
     */
    public List<CompileReportDetail> getReportDetailList(CompileReportDetail examineReportDetail) {
        CompileReportDetailExample example = new CompileReportDetailExample();
        MybatisUtils.convertObj2Example(examineReportDetail, example);
        return compileReportDetailMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineReportDetail
     * @return
     */
    public boolean addReportDetail(CompileReportDetail examineReportDetail) {
        return compileReportDetailMapper.insertSelective(examineReportDetail) > 0;
    }

    /**
     * 编辑
     * @param examineReportDetail
     * @return
     */
    public boolean updateReportDetail(CompileReportDetail examineReportDetail) {
        return compileReportDetailMapper.updateByPrimaryKeySelective(examineReportDetail) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteReportDetail(Integer id){
        return compileReportDetailMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取数据条数
     * @param planDetailsId
     * @return
     */
    public int getCountByPlanDetailsId(Integer planDetailsId){
        CompileReportDetailExample example = new CompileReportDetailExample();
        example.createCriteria().andPlanDetailsIdEqualTo(planDetailsId);
        return compileReportDetailMapper.countByExample(example);
    }

}