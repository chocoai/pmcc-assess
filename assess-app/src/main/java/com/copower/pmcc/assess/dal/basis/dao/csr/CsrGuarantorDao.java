package com.copower.pmcc.assess.dal.basis.dao.csr;


import com.copower.pmcc.assess.dal.basis.entity.CsrGuarantor;
import com.copower.pmcc.assess.dal.basis.entity.CsrGuarantorExample;
import com.copower.pmcc.assess.dal.basis.mapper.CsrGuarantorMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/23
 * @time: 14:23
 */
@Repository
public class CsrGuarantorDao {
    @Autowired
    private CsrGuarantorMapper csrGuarantorMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CsrGuarantor getCsrGuarantorById(Integer id) {
        return csrGuarantorMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param csrGuarantor
     * @return
     */
    public List<CsrGuarantor> getCsrGuarantorList(CsrGuarantor csrGuarantor) {
        CsrGuarantorExample example = new CsrGuarantorExample();
        MybatisUtils.convertObj2Example(csrGuarantor, example);
        return csrGuarantorMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param csrGuarantor
     * @return
     */
    public boolean addCsrGuarantor(CsrGuarantor csrGuarantor) {
        return csrGuarantorMapper.insertSelective(csrGuarantor) > 0;
    }

    /**
     * 编辑
     * @param csrGuarantor
     * @return
     */
    public boolean updateCsrGuarantor(CsrGuarantor csrGuarantor) {
        return csrGuarantorMapper.updateByPrimaryKeySelective(csrGuarantor) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteCsrGuarantor(Integer id){
        return csrGuarantorMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 批量删除
     *
     * @param csrProjectId
     * @return
     */
    public boolean deleteByCsrProjectId(Integer csrProjectId) {
        CsrGuarantorExample example = new CsrGuarantorExample();
        example.createCriteria().andCsrProjectIdEqualTo(csrProjectId);
        return csrGuarantorMapper.deleteByExample(example) > 0;
    }
}
