package com.copower.pmcc.assess.dal.basis.dao.project;

import com.copower.pmcc.assess.dal.basis.entity.ReportProjectDebt;
import com.copower.pmcc.assess.dal.basis.entity.ReportProjectDebtExample;
import com.copower.pmcc.assess.dal.basis.mapper.ReportProjectDebtMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author Red
 * @version 1.0
 * @date: 2018/09/03 10:29
 */

@Repository
public class ReportProjectDebtDao {
    @Autowired
    private ReportProjectDebtMapper reportProjectDebtMapper;


    public List<ReportProjectDebt> getReportProjectDebtList(String queryProjectName,String queryConsignorName,String queryReportUseUnitName,
                                                        String preauditReportNumber,String resultReportNumber,String queryUserAccount,String queryEntrustPurposeName,String queryLoanTypeName,String queryDepartmentName) {
        ReportProjectDebtExample example = new ReportProjectDebtExample();
        ReportProjectDebtExample.Criteria criteria = example.createCriteria();

        if (StringUtils.isNotEmpty(queryProjectName)) {
            criteria.andProjectNameLike(String.format("%s%s%s","%",queryProjectName,"%"));
        }
        if (StringUtils.isNotEmpty(queryConsignorName)) {
            criteria.andConsignorNameLike(String.format("%s%s%s","%",queryConsignorName,"%"));
        }
        if (StringUtils.isNotEmpty(queryReportUseUnitName)) {
            criteria.andReportUseUnitNameLike(String.format("%s%s%s","%",queryReportUseUnitName,"%"));
        }
        if (StringUtils.isNotEmpty(preauditReportNumber)) {
            criteria.andPreauditNumberLike(String.format("%s%s%s","%",preauditReportNumber,"%"));

        }
        if (StringUtils.isNotEmpty(resultReportNumber)) {
            criteria.andResultNumberLike(String.format("%s%s%s","%",resultReportNumber,"%"));
        }
        if (StringUtils.isNotEmpty(queryUserAccount)) {
            criteria.andProjectManagerNameLike(String.format("%s%s%s","%",queryUserAccount,"%"));
        }
        if (StringUtils.isNotEmpty(queryEntrustPurposeName)) {
            criteria.andEntrustPurposeNameLike(String.format("%s%s%s","%",queryEntrustPurposeName,"%"));
        }
        if (StringUtils.isNotEmpty(queryLoanTypeName)) {
            criteria.andLoanTypeNameLike(String.format("%s%s%s","%",queryLoanTypeName,"%"));
        }
        if (StringUtils.isNotEmpty(queryDepartmentName)) {
            criteria.andDepartmentNameLike(String.format("%s%s%s","%",queryDepartmentName,"%"));
        }
        criteria.andBisHasDebtEqualTo(true);
        return reportProjectDebtMapper.selectByExample(example);
    }



    /**
     * @param where
     * @return
     */
    public List<ReportProjectDebt> getReportProjectDebt(ReportProjectDebt where) {
        ReportProjectDebtExample example = new ReportProjectDebtExample();
        MybatisUtils.convertObj2Example(where, example);
        return reportProjectDebtMapper.selectByExample(example);
    }


    /**
     * 新增数据
     *
     * @param data
     * @return
     */
    public boolean addReportProjectDebt(ReportProjectDebt data) {
        return reportProjectDebtMapper.insertSelective(data) == 1;
    }

    /**
     * 更新数据
     *
     * @param data
     * @return
     */
    public boolean modifyReportProjectDebt(ReportProjectDebt data) {
        return reportProjectDebtMapper.updateByPrimaryKeySelective(data) == 1;
    }

    /**
     * 根据条件更新
     *
     * @param data
     * @param where
     * @return
     */
    public int modifyReportProjectDebt(ReportProjectDebt data, ReportProjectDebt where) {
        ReportProjectDebtExample example = new ReportProjectDebtExample();
        MybatisUtils.convertObj2Example(where, example);
        return reportProjectDebtMapper.updateByExampleSelective(data, example);
    }
}
