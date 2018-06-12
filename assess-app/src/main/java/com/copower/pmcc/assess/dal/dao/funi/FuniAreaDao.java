package com.copower.pmcc.assess.dal.dao.funi;

import com.copower.pmcc.assess.dal.entity.FuniArea;
import com.copower.pmcc.assess.dal.entity.FuniAreaExample;
import com.copower.pmcc.assess.dal.mapper.FuniAreaMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/12
 * @time: 15:04
 */
@Repository
public class FuniAreaDao {
    @Autowired
    private FuniAreaMapper funiAreaMapper;

    public FuniArea getFuniArea(Integer id) {
        return funiAreaMapper.selectByPrimaryKey(id);
    }

    public List<FuniArea> getFuniAreaList(FuniArea funiArea) {
        FuniAreaExample example = new FuniAreaExample();
        MybatisUtils.convertObj2Example(funiArea, example);
        List<FuniArea> funiAreas = funiAreaMapper.selectByExample(example);
        return funiAreas;
    }

    public boolean addFuniArea(FuniArea funiArea) {
        int i = funiAreaMapper.insert(funiArea);
        return i > 0;
    }

    public boolean editFuniArea(FuniArea funiArea) {
        int i = funiAreaMapper.updateByPrimaryKeySelective(funiArea);
        return i > 0;
    }

    public boolean deleteFuniArea(Integer id) {
        int i = funiAreaMapper.deleteByPrimaryKey(id);
        return i > 0;
    }

}
