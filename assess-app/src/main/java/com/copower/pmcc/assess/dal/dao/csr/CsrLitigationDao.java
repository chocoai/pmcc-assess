package com.copower.pmcc.assess.dal.dao.csr;


import com.copower.pmcc.assess.dal.entity.CsrLitigation;
import com.copower.pmcc.assess.dal.entity.CsrLitigationExample;
import com.copower.pmcc.assess.dal.mapper.CsrLitigationMapper;
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
public class CsrLitigationDao {
    @Autowired
    private CsrLitigationMapper csrLitigationMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CsrLitigation getCsrLitigationById(Integer id) {
        return csrLitigationMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param csrLitigation
     * @return
     */
    public List<CsrLitigation> getCsrLitigationList(CsrLitigation csrLitigation) {
        CsrLitigationExample example = new CsrLitigationExample();
        MybatisUtils.convertObj2Example(csrLitigation, example);
        return csrLitigationMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param csrLitigation
     * @return
     */
    public boolean addCsrLitigation(CsrLitigation csrLitigation) {
        return csrLitigationMapper.insertSelective(csrLitigation) > 0;
    }

    /**
     * 编辑
     * @param csrLitigation
     * @return
     */
    public boolean updateCsrLitigation(CsrLitigation csrLitigation) {
        return csrLitigationMapper.updateByPrimaryKeySelective(csrLitigation) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteCsrLitigation(Integer id){
        return csrLitigationMapper.deleteByPrimaryKey(id) > 0;
    }
}
