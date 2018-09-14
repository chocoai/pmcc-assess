package com.copower.pmcc.assess.dal.basis.dao.examine;

import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingSurface;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingSurfaceExample;
import com.copower.pmcc.assess.dal.basis.mapper.ExamineBuildingSurfaceMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: wangpc
 * @data: 2018/07/06
 * @time: 14:36
 */
@Repository
public class ExamineBuildingSurfaceDao {
    @Autowired
    private ExamineBuildingSurfaceMapper examineBuildingSurfaceMapper;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineBuildingSurface getBuildingSurfaceById(Integer id) {
        return examineBuildingSurfaceMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据列表
     * @param examineBuildingSurface
     * @return
     */
    public List<ExamineBuildingSurface> getBuildingSurfaceList(ExamineBuildingSurface examineBuildingSurface) {
        ExamineBuildingSurfaceExample example = new ExamineBuildingSurfaceExample();
        MybatisUtils.convertObj2Example(examineBuildingSurface, example);
        return examineBuildingSurfaceMapper.selectByExample(example);
    }

    /**
     * 新增
     * @param examineBuildingSurface
     * @return
     */
    public boolean addBuildingSurface(ExamineBuildingSurface examineBuildingSurface) {
        return examineBuildingSurfaceMapper.insertSelective(examineBuildingSurface) > 0;
    }

    /**
     * 编辑
     * @param examineBuildingSurface
     * @return
     */
    public boolean updateBuildingSurface(ExamineBuildingSurface examineBuildingSurface) {
        return examineBuildingSurfaceMapper.updateByPrimaryKeySelective(examineBuildingSurface) > 0;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteBuildingSurface(Integer id){
        return examineBuildingSurfaceMapper.deleteByPrimaryKey(id) > 0;
    }

    public void removeExamineBuildingSurface(ExamineBuildingSurface examineBuildingSurface){
        ExamineBuildingSurfaceExample example = new ExamineBuildingSurfaceExample();
        MybatisUtils.convertObj2Example(examineBuildingSurface, example);
        examineBuildingSurfaceMapper.deleteByExample(example);
    }

}