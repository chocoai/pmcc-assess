package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeInfo;
import com.copower.pmcc.assess.dal.basis.entity.SchemeInfoExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeInfoMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 方案主表
 * Created by 13426 on 2018/5/24.
 */
@Repository
public class SchemeInfoDao {
    @Autowired
    private SchemeInfoMapper schemeInfoMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public SchemeInfo getInfoById(Integer id) {
        return schemeInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineInfo
     * @return
     */
    public List<SchemeInfo> getInfoList(SchemeInfo examineInfo) {
        SchemeInfoExample example = new SchemeInfoExample();
        MybatisUtils.convertObj2Example(examineInfo, example);
        return schemeInfoMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineInfo
     * @return
     */
    public boolean addInfo(SchemeInfo examineInfo) {
        return schemeInfoMapper.insertSelective(examineInfo) > 0;
    }

    /**
     * 编辑
     * @param examineInfo
     * @return
     */
    public boolean updateInfo(SchemeInfo examineInfo) {
        return schemeInfoMapper.updateByPrimaryKeySelective(examineInfo) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteInfo(Integer id){
        return schemeInfoMapper.deleteByPrimaryKey(id) > 0;
    }

    
}
