package com.copower.pmcc.assess.dal.cases.dao;

import com.copower.pmcc.assess.dal.cases.custom.entity.CustomCaseEntity;
import com.copower.pmcc.assess.dal.cases.custom.mapper.CustomCaseMapper;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstate;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateExample;
import com.copower.pmcc.assess.dal.cases.mapper.CaseEstateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 描述:
 *
 * @author: wangpc
 * @data: 2018/07/06
 * @time: 14:36
 */
@Repository
public class CaseEstateDao {
    @Autowired
    private CaseEstateMapper caseEstateMapper;
    @Autowired
    private CustomCaseMapper customCaseEstateMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public CaseEstate getEstateById(Integer id) {
        return caseEstateMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param caseEstate
     * @return
     */
    public List<CaseEstate> getEstateList(CaseEstate caseEstate) {
        CaseEstateExample example = new CaseEstateExample();
        MybatisUtils.convertObj2Example(caseEstate, example);
        return caseEstateMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param caseEstate
     * @return
     */
    public Integer addEstate(CaseEstate caseEstate) {
        caseEstateMapper.insertSelective(caseEstate);
        return caseEstate.getId();
    }

    /**
     * 编辑
     * @param caseEstate
     * @return
     */
    public boolean updateEstate(CaseEstate caseEstate) {
        return caseEstateMapper.updateByPrimaryKeySelective(caseEstate) > 0;
    }

    public List<CaseEstate> getCaseEstateList(String name, String province, String city, String district){
        CaseEstateExample example = new CaseEstateExample();
        CaseEstateExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (!StringUtils.isEmpty(name)){
            criteria.andNameLike(String.format("%s%s%s","%",name,"%"));
        }
        if (!StringUtils.isEmpty(province)){
            criteria.andProvinceEqualTo(province);
        }
        if (!StringUtils.isEmpty(city)){
            criteria.andCityEqualTo(city);
        }
        if (!StringUtils.isEmpty(district)){
            criteria.andDistrictEqualTo(district);
        }
        example.setOrderByClause("id desc");
        return caseEstateMapper.selectByExample(example);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteEstate(Integer id){
        return caseEstateMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 获取版本最新的楼盘信息
     * @param name
     * @param province
     * @param city
     * @param district
     * @return
     */
    public List<CustomCaseEntity> getLatestVersionEstateList(String name, String province, String city, String district){
        return customCaseEstateMapper.getCaseEstateList(name, province, city, district);
    }

    public List<CustomCaseEntity> getLatestVersionEstateList(String name){
        return getLatestVersionEstateList(name,null,null,null);
    }

}