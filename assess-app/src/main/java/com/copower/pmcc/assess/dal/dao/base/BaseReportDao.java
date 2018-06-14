package com.copower.pmcc.assess.dal.dao.base;

import com.copower.pmcc.assess.dal.entity.*;
import com.copower.pmcc.assess.dal.mapper.BaseReportColumnsMapper;
import com.copower.pmcc.assess.dal.mapper.BaseReportTableMapper;
import com.copower.pmcc.assess.dal.mapper.BaseReportTemplateFilesMapper;
import com.copower.pmcc.assess.dal.mapper.BaseReportTemplateMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/5/21
 * @time: 17:16
 */
@Repository
public class BaseReportDao {
    @Autowired
    private BaseReportTableMapper baseReportTableMapper;
    @Autowired
    private BaseReportColumnsMapper baseReportColumnsMapper;
    @Autowired
    private BaseReportTemplateMapper baseReportTemplateMapper;
    @Autowired
    private BaseReportTemplateFilesMapper baseReportTemplateFilesMapper;

    public List<BaseReportTable> getBaseReportTableList(List<Integer> tableIdId) {
        BaseReportTableExample example = new BaseReportTableExample();
        BaseReportTableExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        if (CollectionUtils.isNotEmpty(tableIdId)) {
            criteria.andIdIn(tableIdId);
        }
        List<BaseReportTable> baseReportTables = baseReportTableMapper.selectByExample(example);
        return baseReportTables;
    }

    public List<BaseReportTable> getBaseReportTableList(Integer typeId) {
        BaseReportTableExample example = new BaseReportTableExample();
        BaseReportTableExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        if (typeId != null) {
            criteria.andTableTypeEqualTo(typeId);
        }
        List<BaseReportTable> baseReportTables = baseReportTableMapper.selectByExample(example);
        return baseReportTables;
    }

    public BaseReportTable getBaseReportTableById(Integer id) {
        return baseReportTableMapper.selectByPrimaryKey(id);
    }

    public List<BaseReportColumns> getBaseReportColumnsList(Integer tableId) {
        BaseReportColumnsExample example = new BaseReportColumnsExample();
        example.createCriteria().andBisEnableEqualTo(true).andTableIdEqualTo(tableId);
        List<BaseReportColumns> baseReportColumns = baseReportColumnsMapper.selectByExample(example);
        return baseReportColumns;
    }

    public BaseReportColumns getBaseReportColumnsById(Integer id) {
        return baseReportColumnsMapper.selectByPrimaryKey(id);
    }

    public List<BaseReportTemplate> getBaseReportTemplateByExample(BaseReportTemplate baseReportTemplate, String bookMarkName) {
        BaseReportTemplateExample example = new BaseReportTemplateExample();
        BaseReportTemplateExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        if (StringUtils.isNotBlank(bookMarkName)) {
            criteria.andBookmarkNameLike(bookMarkName);
        }
        MybatisUtils.convertObj2Criteria(baseReportTemplate, criteria);
        List<BaseReportTemplate> baseReportTemplates = baseReportTemplateMapper.selectByExample(example);
        return baseReportTemplates;

    }

    public BaseReportTemplate getBaseReportTemplateById(Integer id) {
        return baseReportTemplateMapper.selectByPrimaryKey(id);
    }

    public Boolean deleteBaseReportTemplate(Integer id) {
        BaseReportTemplate baseReportTemplate = baseReportTemplateMapper.selectByPrimaryKey(id);
        baseReportTemplate.setBisEnable(false);
        return baseReportTemplateMapper.updateByPrimaryKeySelective(baseReportTemplate) >= 0;
    }

    public Boolean addBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        return baseReportTemplateMapper.insertSelective(baseReportTemplate) == 1;
    }

    public Boolean updateBaseReportTemplate(BaseReportTemplate baseReportTemplate) {
        return baseReportTemplateMapper.updateByPrimaryKeySelective(baseReportTemplate) >= 0;
    }

    public List<BaseReportTemplateFiles> getBaseReportTemplateFilesByExample(BaseReportTemplateFiles baseReportTemplateFiles, String search) {
        BaseReportTemplateFilesExample example = new BaseReportTemplateFilesExample();
        BaseReportTemplateFilesExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(search)) {
            criteria.andFilesRemarksLike(search);
        }
        MybatisUtils.convertObj2Criteria(baseReportTemplateFiles, criteria);
        List<BaseReportTemplateFiles> baseReportTemplateFiless = baseReportTemplateFilesMapper.selectByExample(example);
        return baseReportTemplateFiless;

    }

    public BaseReportTemplateFiles getBaseReportTemplateFilesById(Integer id) {
        return baseReportTemplateFilesMapper.selectByPrimaryKey(id);
    }

    public Boolean deleteBaseReportTemplateFiles(Integer id) {
        BaseReportTemplateFiles baseReportTemplateFiles = baseReportTemplateFilesMapper.selectByPrimaryKey(id);
        baseReportTemplateFiles.setBisEnable(false);
        return baseReportTemplateFilesMapper.updateByPrimaryKeySelective(baseReportTemplateFiles) >= 0;
    }

    public Boolean deleteAllBaseReportTemplateFiles(Integer id) {

        BaseReportTemplateFilesExample example = new BaseReportTemplateFilesExample();
        BaseReportTemplateFiles where = baseReportTemplateFilesMapper.selectByPrimaryKey(id);
        BaseReportTemplateFilesExample.Criteria criteria = example.createCriteria().andBisEnableEqualTo(true);
        criteria.andCustomerIdEqualTo(where.getCustomerId());
        criteria.andEntrustIdEqualTo(where.getEntrustId());
        criteria.andReportTypeIdEqualTo(where.getReportTypeId());
        criteria.andCsTypeEqualTo(where.getCsType());
        criteria.andClassifyIdEqualTo(where.getClassifyId());

        BaseReportTemplateFiles baseReportTemplateFiles = new BaseReportTemplateFiles();
        baseReportTemplateFiles.setBisEnable(false);
        return baseReportTemplateFilesMapper.updateByExampleSelective(baseReportTemplateFiles, example) > 0;
    }

    public Boolean addBaseReportTemplateFiles(BaseReportTemplateFiles baseReportTemplateFiles) {
        return baseReportTemplateFilesMapper.insertSelective(baseReportTemplateFiles) == 1;
    }

    public Boolean updateBaseReportTemplateFiles(BaseReportTemplateFiles baseReportTemplateFiles) {
        return baseReportTemplateFilesMapper.updateByPrimaryKeySelective(baseReportTemplateFiles) >= 0;
    }

}
