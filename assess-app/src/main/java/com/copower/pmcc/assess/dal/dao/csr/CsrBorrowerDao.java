package com.copower.pmcc.assess.dal.dao.csr;

import com.copower.pmcc.assess.dal.entity.CsrBorrower;
import com.copower.pmcc.assess.dal.entity.CsrBorrowerExample;
import com.copower.pmcc.assess.dal.entity.CsrBorrowerMortgageExample;
import com.copower.pmcc.assess.dal.mapper.CsrBorrowerMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/31
 * @time: 11:07
 */
@Repository
public class CsrBorrowerDao {

    @Autowired
    private CsrBorrowerMapper csrBorrowerMapper;

    public boolean addCsrBorrower(CsrBorrower csrBorrower){
        return csrBorrowerMapper.insertSelective(csrBorrower)>0;
    }

    public List<CsrBorrower> getCsrBorrowerList(CsrBorrower csrBorrower) {
        CsrBorrowerExample example = new CsrBorrowerExample();
        MybatisUtils.convertObj2Example(csrBorrower, example);
        return csrBorrowerMapper.selectByExample(example);
    }

    public boolean update(CsrBorrower csrBorrower){
        return csrBorrowerMapper.updateByPrimaryKey(csrBorrower)==1;
    }

    public CsrBorrower getCsrBorrowerByID(Integer id){
        return csrBorrowerMapper.selectByPrimaryKey(id);
    }

    public List<CsrBorrower> borrowerLists(String secondLevelBranch,String firstLevelBranch,Integer csrProjectInfoID){
        CsrBorrowerExample example = new CsrBorrowerExample();
        if (StringUtils.isEmpty(firstLevelBranch) && !StringUtils.isEmpty(secondLevelBranch)){
            example.createCriteria().andIdIsNotNull().andGroupIdIsNull().andSecondLevelBranchLike("%"+secondLevelBranch+"%").andCsrProjectIdEqualTo(csrProjectInfoID);
        }else if (!StringUtils.isEmpty(firstLevelBranch) && StringUtils.isEmpty(secondLevelBranch)){
            example.createCriteria().andIdIsNotNull().andGroupIdIsNull().andFirstLevelBranchLike("%"+firstLevelBranch+"%").andCsrProjectIdEqualTo(csrProjectInfoID);
        }else if (!StringUtils.isEmpty(firstLevelBranch) && !StringUtils.isEmpty(secondLevelBranch)){
            example.createCriteria().andIdIsNotNull().andGroupIdIsNull().andFirstLevelBranchLike("%"+firstLevelBranch+"%").andSecondLevelBranchLike("%"+secondLevelBranch+"%").andCsrProjectIdEqualTo(csrProjectInfoID);
        }else if (StringUtils.isEmpty(firstLevelBranch) && StringUtils.isEmpty(secondLevelBranch)){
            example.createCriteria().andIdIsNotNull().andGroupIdIsNull().andCsrProjectIdEqualTo(csrProjectInfoID);
        }
        return csrBorrowerMapper.selectByExample(example);
    }

    public List<CsrBorrower> getCsrBorrowerList(Integer projectId){
        CsrBorrowerExample example = new CsrBorrowerExample();
        example.createCriteria().andIdIsNotNull().andProjectIdEqualTo(projectId);
        return csrBorrowerMapper.selectByExample(example);
    }

    public List<CsrBorrower> getCsrBorrowerListByCsrProjectID(Integer csrProjectID){
        CsrBorrowerExample example = new CsrBorrowerExample();
        example.createCriteria().andIdIsNotNull().andCsrProjectIdEqualTo(csrProjectID);
        return csrBorrowerMapper.selectByExample(example);
    }

    /**
     * 批量删除
     *
     * @param csrProjectId
     * @return
     */
    public boolean deleteByCsrProjectId(Integer csrProjectId) {
        CsrBorrowerExample example = new CsrBorrowerExample();
        example.createCriteria().andCsrProjectIdEqualTo(csrProjectId);
        return csrBorrowerMapper.deleteByExample(example) > 0;
    }
}
