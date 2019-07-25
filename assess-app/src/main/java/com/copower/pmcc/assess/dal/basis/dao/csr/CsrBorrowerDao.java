package com.copower.pmcc.assess.dal.basis.dao.csr;

import com.copower.pmcc.assess.dal.basis.entity.CsrBorrower;
import com.copower.pmcc.assess.dal.basis.entity.CsrBorrowerExample;
import com.copower.pmcc.assess.dal.basis.mapper.CsrBorrowerMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        return csrBorrowerMapper.updateByPrimaryKeySelective(csrBorrower)==1;
    }

    public CsrBorrower getCsrBorrowerByID(Integer id){
        return csrBorrowerMapper.selectByPrimaryKey(id);
    }

    public CsrBorrower getCsrBorrowerByID(String borrowerId){
        CsrBorrowerExample example = new CsrBorrowerExample();
        List<CsrBorrower> csrBorrowerList = csrBorrowerMapper.selectByExample(example);
        if(CollectionUtils.isNotEmpty(csrBorrowerList))
            return csrBorrowerList.get(0);
        return null;
    }

    public List<CsrBorrower> borrowerListsA(String secondLevelBranch, String firstLevelBranch, Integer csrProjectInfoID,Integer csrProjectInfoGroupID){
        List<CsrBorrower> csrBorrowers = null;
        CsrBorrowerExample example = new CsrBorrowerExample();
        CsrBorrowerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if(csrProjectInfoID!=null){
            criteria.andCsrProjectIdEqualTo(csrProjectInfoID);
        }
        if (csrProjectInfoGroupID!=null){
            criteria.andGroupIdEqualTo(csrProjectInfoGroupID);
        }else {
            criteria.andGroupIdIsNull();
        }
        if(StringUtils.isNotBlank(firstLevelBranch)){
            criteria.andFirstLevelBranchLike("%"+firstLevelBranch+"%");
        }
        if(StringUtils.isNotBlank(secondLevelBranch)){
            criteria.andSecondLevelBranchLike("%"+secondLevelBranch+"%");
        }
        csrBorrowers = csrBorrowerMapper.selectByExample(example);
        return csrBorrowers;
    }

    /**
     * 请不要和上面的合并 不能加 GroupId 查询
     * @param secondLevelBranch
     * @param firstLevelBranch
     * @param csrProjectInfoID
     * @return
     */
    public List<CsrBorrower> borrowerListsB(String secondLevelBranch, String firstLevelBranch, Integer csrProjectInfoID){
        List<CsrBorrower> csrBorrowers = null;
        CsrBorrowerExample example = new CsrBorrowerExample();
        CsrBorrowerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if(csrProjectInfoID!=null){
            criteria.andCsrProjectIdEqualTo(csrProjectInfoID);
        }
        if(StringUtils.isNotBlank(firstLevelBranch)){
            criteria.andFirstLevelBranchLike("%"+firstLevelBranch+"%");
        }
        if(StringUtils.isNotBlank(secondLevelBranch)){
            criteria.andSecondLevelBranchLike("%"+secondLevelBranch+"%");
        }
        csrBorrowers = csrBorrowerMapper.selectByExample(example);
        return csrBorrowers;
    }

    /**
     * 取消分派
     * @param csrProjectInfoID
     * @param ids
     */
    public void cancel(Integer csrProjectInfoID,List<Integer> ids,Integer csrProjectInfoGroupID){
        CsrBorrowerExample example = new CsrBorrowerExample();
        CsrBorrowerExample.Criteria criteria = example.createCriteria();
        criteria.andCsrProjectIdEqualTo(csrProjectInfoID);
        criteria.andIdIn(ids);
        criteria.andGroupIdEqualTo(csrProjectInfoGroupID);
        List<CsrBorrower> csrBorrowers = csrBorrowerMapper.selectByExample(example);
        for (CsrBorrower csrBorrower:csrBorrowers){
            csrBorrower.setGroupId(null);
            csrBorrowerMapper.updateByPrimaryKeySelective(csrBorrower);
        }
    }

    public List<CsrBorrower> getCsrBorrowerList(Integer projectId){
        CsrBorrowerExample example = new CsrBorrowerExample();
        example.createCriteria().andIdIsNotNull().andProjectIdEqualTo(projectId);
        return csrBorrowerMapper.selectByExample(example);
    }

    public List<CsrBorrower> getCsrBorrowerListByCsrProjectID(Integer csrProjectID,Integer groupID){
        CsrBorrowerExample example = new CsrBorrowerExample();
        CsrBorrowerExample.Criteria criterion = example.createCriteria();
        criterion.andIdIsNotNull();
        if (csrProjectID!=null){
            criterion.andCsrProjectIdEqualTo(csrProjectID);
        }
        if (groupID!=null){
            criterion.andGroupIdEqualTo(groupID);
        }
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
