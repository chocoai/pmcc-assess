package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMain;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingMainExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseBuildingMainMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/26 17:01
 * @Description:
 */
@Repository
public class CaseBuildingMainDao {

    @Autowired
    private CaseBuildingMainMapper caseBuildingMainMapper;


    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public CaseBuildingMain getBuildingMainById(Integer id) {
        return caseBuildingMainMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseBuildingMain
     * @return
     */
    public List<CaseBuildingMain> getBuildingMainList(CaseBuildingMain caseBuildingMain) {
        CaseBuildingMainExample example = new CaseBuildingMainExample();
        MybatisUtils.convertObj2Example(caseBuildingMain, example);
        return caseBuildingMainMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param caseBuildingMain
     * @return
     */
    public Integer addBuildingMain(CaseBuildingMain caseBuildingMain) {
        caseBuildingMainMapper.insertSelective(caseBuildingMain);
        return caseBuildingMain.getId();
    }

    /**
     * 编辑
     *
     * @param caseBuildingMain
     * @return
     */
    public boolean updateBuildingMain(CaseBuildingMain caseBuildingMain) {
        return caseBuildingMainMapper.updateByPrimaryKeySelective(caseBuildingMain) > 0;
    }

    public int updateEstateId(Integer oldEstateId, Integer newEstateId) {
        CaseBuildingMainExample example = new CaseBuildingMainExample();
        example.createCriteria().andEstateIdEqualTo(oldEstateId);

        CaseBuildingMain caseBuildingMain = new CaseBuildingMain();
        caseBuildingMain.setEstateId(newEstateId);
        return caseBuildingMainMapper.updateByExampleSelective(caseBuildingMain, example);
    }

    public List<CaseBuildingMain> autoCompleteCaseBuildingMain(String identifier, Integer estateId) {
        CaseBuildingMainExample example = new CaseBuildingMainExample();
        CaseBuildingMainExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (!StringUtils.isEmpty(identifier)) {
            criteria.andBuildingNumberLike(String.format("%s%s%s", "%", identifier, "%"));
        }
        if (estateId != null) {
            criteria.andEstateIdEqualTo(estateId);
        }
        example.setOrderByClause("building_number");
        return caseBuildingMainMapper.selectByExample(example);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteBuildingMain(Integer id) {
        return caseBuildingMainMapper.deleteByPrimaryKey(id) > 0;
    }
}
