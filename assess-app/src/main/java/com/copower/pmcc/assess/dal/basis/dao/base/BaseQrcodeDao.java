package com.copower.pmcc.assess.dal.basis.dao.base;

import com.copower.pmcc.assess.dal.basis.entity.BaseQrcode;
import com.copower.pmcc.assess.dal.basis.entity.BaseQrcodeExample;
import com.copower.pmcc.assess.dal.basis.mapper.BaseQrcodeMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:46
 * @Description:
 */
@Repository
public class BaseQrcodeDao {
    @Autowired
    private BaseQrcodeMapper baseQrcodeMapper;

    public BaseQrcode getBaseQrcodeById(Integer id) {
        return baseQrcodeMapper.selectByPrimaryKey(id);
    }

    public Integer saveBaseQrcode(BaseQrcode baseQrcode) {
        baseQrcodeMapper.insertSelective(baseQrcode);
        return baseQrcode.getId();
    }

    public boolean updateBaseQrcode(BaseQrcode baseQrcode, boolean updateNull) {
        return updateNull ? baseQrcodeMapper.updateByPrimaryKey(baseQrcode) == 1 : baseQrcodeMapper.updateByPrimaryKeySelective(baseQrcode) == 1;
    }

    public boolean deleteBaseQrcode(Integer id) {
        return baseQrcodeMapper.deleteByPrimaryKey(id) == 1;
    }

    public List<BaseQrcode> baseQrcodeList(BaseQrcode baseQrcode) {
        BaseQrcodeExample example = new BaseQrcodeExample();
        BaseQrcodeExample.Criteria criteria = example.createCriteria();
        MybatisUtils.convertObj2Criteria(baseQrcode, criteria);
        return baseQrcodeMapper.selectByExample(example);
    }
}
