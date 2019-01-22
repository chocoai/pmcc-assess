package com.copower.pmcc.assess.dal.basis.dao.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFile;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileExample;
import com.copower.pmcc.assess.dal.basis.mapper.SchemeReportFileMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SchemeReportFileDao {
    @Autowired
    private SchemeReportFileMapper schemeReportFileMapper;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public SchemeReportFile getReportFileById(Integer id) {
        return schemeReportFileMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取数据
     *
     * @param schemeReportFile
     * @return
     */
    public SchemeReportFile getSchemeReportFile(SchemeReportFile schemeReportFile) {
        SchemeReportFileExample example = new SchemeReportFileExample();
        MybatisUtils.convertObj2Example(schemeReportFile, example);
        List<SchemeReportFile> schemeReportFiles = schemeReportFileMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(schemeReportFiles)) return schemeReportFiles.get(0);
        return null;
    }

    /**
     * 获取数据列表
     *
     * @param examineReportFile
     * @return
     */
    public List<SchemeReportFile> getReportFileList(SchemeReportFile examineReportFile) {
        SchemeReportFileExample example = new SchemeReportFileExample();
        MybatisUtils.convertObj2Example(examineReportFile, example);
        return schemeReportFileMapper.selectByExample(example);
    }

    /**
     * 新增
     *
     * @param examineReportFile
     * @return
     */
    public boolean addReportFile(SchemeReportFile examineReportFile) {
        return schemeReportFileMapper.insertSelective(examineReportFile) > 0;
    }

    /**
     * 编辑
     *
     * @param examineReportFile
     * @return
     */
    public boolean updateReportFile(SchemeReportFile examineReportFile) {
        return schemeReportFileMapper.updateByPrimaryKeySelective(examineReportFile) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteReportFile(Integer id) {
        return schemeReportFileMapper.deleteByPrimaryKey(id) > 0;
    }


}
