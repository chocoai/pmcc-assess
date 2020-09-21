package com.copower.pmcc.assess.dal.basis.dao.method;

import com.copower.pmcc.assess.dal.basis.entity.MdBaseLandPrice;
import com.copower.pmcc.assess.dal.basis.entity.MdBaseLandPriceExample;
import com.copower.pmcc.assess.dal.basis.mapper.MdBaseLandPriceMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong @ copowercpa.com)
 * @data: 2018/6/12
 * @time: 15:04
 */
@Repository
public class MdBaseLandPriceDao {
    @Autowired
    private MdBaseLandPriceMapper mdBaseLandPriceMapper;

    public MdBaseLandPrice getMdBaseLandPrice(Integer id) {
        return mdBaseLandPriceMapper.selectByPrimaryKey(id);
    }


    public List<MdBaseLandPrice> getObjectList(MdBaseLandPrice mdBaseLandPrice) {
        MdBaseLandPriceExample example = new MdBaseLandPriceExample();
        MybatisUtils.convertObj2Example(mdBaseLandPrice, example);
        return mdBaseLandPriceMapper.selectByExample(example);
    }

    public boolean addMdBaseLandPrice(MdBaseLandPrice mdBaseLandPrice) {
        int i = mdBaseLandPriceMapper.insert(mdBaseLandPrice);
        return i > 0;
    }

    public boolean editMdBaseLandPrice(MdBaseLandPrice mdBaseLandPrice) {
        int i = mdBaseLandPriceMapper.updateByPrimaryKeySelective(mdBaseLandPrice);
        return i > 0;
    }

    public boolean deleteMdBaseLandPrice(Integer id) {
        int i = mdBaseLandPriceMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

    public MdBaseLandPrice getMdBaseLandPrice(MdBaseLandPrice mdBaseLandPrice) {
        MdBaseLandPriceExample example = new MdBaseLandPriceExample();
        MybatisUtils.convertObj2Example(mdBaseLandPrice, example);
        List<MdBaseLandPrice> mdBaseLandPrices = mdBaseLandPriceMapper.selectByExampleWithBLOBs(example);
        if (CollectionUtils.isNotEmpty(mdBaseLandPrices)) return mdBaseLandPrices.get(0);
        return null;
    }

}
