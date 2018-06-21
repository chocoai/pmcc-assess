package com.copower.pmcc.assess.dal.dao.base;

import com.copower.pmcc.assess.dal.entity.BaseAttachment;
import com.copower.pmcc.assess.dal.entity.BaseAttachmentExample;
import com.copower.pmcc.assess.dal.mapper.BaseAttachmentMapper;
import com.copower.pmcc.erp.common.utils.MybatisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 描述:附件处理类
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/8/10
 * @time: 16:40
 */
@Repository
public class BaseAttachmentDao {
    @Autowired
    private BaseAttachmentMapper sysAttachmentMapper;

    /**
     * 根据条件查询相应的附件列表
     *
     * @param sysAttachment
     * @return
     */
    public List<BaseAttachment> getAttachmentList(BaseAttachment sysAttachment) {
        BaseAttachmentExample example = new BaseAttachmentExample();
        attachmentWhere(sysAttachment, example);
        List<BaseAttachment> sysAttachments = sysAttachmentMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(sysAttachments)) {
            return sysAttachments;
        }
        return null;
    }

    public boolean updateAttachment(List<Integer> integers, BaseAttachment sysAttachment) {
        BaseAttachmentExample example = new BaseAttachmentExample();
        example.createCriteria().andIdIn(integers);
        int i = sysAttachmentMapper.updateByExampleSelective(sysAttachment, example);
        if (i > 0) {
            return true;
        }
        return false;
    }

    public List<BaseAttachment> getAttachmentList(List<Integer> tableIds, BaseAttachment sysAttachment) {
        BaseAttachmentExample example = new BaseAttachmentExample();
        BaseAttachmentExample.Criteria criteria = example.createCriteria();
        MybatisUtils.convertObj2Criteria(sysAttachment,criteria);
        criteria.andTableIdIn(tableIds);
        List<BaseAttachment> sysAttachments = sysAttachmentMapper.selectByExample(example);
        return sysAttachments;
    }

    /**
     * 根据条件查询相应的附件列表
     *
     * @param processInsId
     * @param search
     * @return
     */
    public List<BaseAttachment> getAttachmentList(String processInsId, String search) {
        if (StringUtils.isBlank(processInsId)) return null;
        BaseAttachmentExample example = new BaseAttachmentExample();
        example.createCriteria().andFileNameLike(search).andProcessInsIdEqualTo(processInsId);
        List<BaseAttachment> sysAttachments = sysAttachmentMapper.selectByExample(example);
        return sysAttachments;
    }

    public BaseAttachment getAttachmentById(Integer id) {
        BaseAttachmentExample example = new BaseAttachmentExample();
        example.createCriteria().andIdEqualTo(id);
        List<BaseAttachment> sysAttachments = sysAttachmentMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(sysAttachments)) {
            return sysAttachments.get(0);
        }
        return null;
    }

    public List<BaseAttachment> getAttachmentById(List<Integer> ids) {
        BaseAttachmentExample example = new BaseAttachmentExample();
        example.createCriteria().andIdIn(ids);
        List<BaseAttachment> sysAttachments = sysAttachmentMapper.selectByExample(example);
        return sysAttachments;
    }

    /**
     * 取审批日志的附件记录
     *
     * @param integers
     * @return
     */
    public List<BaseAttachment> getAttachmentListByTableName(String tableName,List<Integer> integers) {
        BaseAttachmentExample example = new BaseAttachmentExample();
        example.createCriteria().andTableNameEqualTo(tableName).andTableIdIn(integers);
        List<BaseAttachment> sysAttachments = sysAttachmentMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(sysAttachments)) {
            return sysAttachments;
        }
        return null;

    }


    /**
     * @param table_id
     * @param fields_name
     * @param tableName
     * @return
     */
    public List<BaseAttachment> getByField_tableId(int table_id,String fields_name,String tableName){
        BaseAttachmentExample example = new BaseAttachmentExample();//
        BaseAttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        if (!ObjectUtils.isEmpty(table_id)){
            criteria.andTableIdEqualTo(table_id);
        }
        if (!org.springframework.util.StringUtils.isEmpty(fields_name)){
            criteria.andFieldsNameEqualTo(fields_name);
        }
        if (!org.springframework.util.StringUtils.isEmpty(tableName)){
            criteria.andTableNameEqualTo(tableName);
        }
        List<BaseAttachment> baseAttachments = sysAttachmentMapper.selectByExample(example);
        return baseAttachments;
    }

    /**
     * 添加附件表信息到数据库，并返回数据中对应的id
     *
     * @param sysAttachment
     * @return
     */
    public Integer addAttachment(BaseAttachment sysAttachment) {
        int i = sysAttachmentMapper.insertSelective(sysAttachment);
        if (i == 1) {
            return sysAttachment.getId();
        }
        return -1;
    }


    public boolean updateAttachment(BaseAttachment sysAttachment) {
        return sysAttachmentMapper.updateByPrimaryKey(sysAttachment) > 0;
    }

    public Boolean updateAttachementByExample(BaseAttachment sysAttachmentWhere, BaseAttachment sysAttachmentNew) {
        BaseAttachmentExample example = new BaseAttachmentExample();
        attachmentWhere(sysAttachmentWhere, example);
        int i = sysAttachmentMapper.updateByExampleSelective(sysAttachmentNew, example);
        if (i >= 0) {
            return true;
        }
        return false;
    }


    public Boolean deleteAttachmentById(Integer id) {
        int i = sysAttachmentMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            return true;
        }
        return false;
    }

    public boolean deleteAttachment(String tableName, Integer tableId,String creator) {
        if (StringUtils.isBlank(tableName) || tableId == null) return false;
        BaseAttachmentExample example = new BaseAttachmentExample();
        BaseAttachmentExample.Criteria criteria = example.createCriteria();
        criteria.andTableNameEqualTo(tableName).andTableIdEqualTo(tableId).andCreaterEqualTo(creator);
        return sysAttachmentMapper.deleteByExample(example) > 0;
    }

    private void attachmentWhere(BaseAttachment sysAttachment, BaseAttachmentExample example) {
      MybatisUtils.convertObj2Example(sysAttachment, example);
    }


    public List<BaseAttachment> getApprovalLogList(String processInsId,List<String> strings)
    {
        if(CollectionUtils.isEmpty(strings)) return null;
        BaseAttachmentExample example=new BaseAttachmentExample();
        example.createCriteria().andTableNameEqualTo("tb_box_approval_log").andProcessInsIdEqualTo(processInsId).andProcessTaskIdIn(strings);
        return sysAttachmentMapper.selectByExample(example);
    }

}
