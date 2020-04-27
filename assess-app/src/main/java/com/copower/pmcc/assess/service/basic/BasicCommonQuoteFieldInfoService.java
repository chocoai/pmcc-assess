package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.common.enums.basic.ExamineCommonQuoteFieldEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicCommonQuoteFieldInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.BasicCommonQuoteFieldInfo;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstate;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.Reflections;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/30 11:52
 * @Description:查勘 公共引用字段
 */
@Service
public class BasicCommonQuoteFieldInfoService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicCommonQuoteFieldInfoDao basicCommonQuoteFieldInfoDao;
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 新增或者修改
     *
     * @param basicCommonQuoteFieldInfo
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicCommonQuoteFieldInfo(BasicCommonQuoteFieldInfo basicCommonQuoteFieldInfo, boolean updateNull) {
        if (basicCommonQuoteFieldInfo.getId() == null || basicCommonQuoteFieldInfo.getId().intValue() == 0) {
            basicCommonQuoteFieldInfo.setCreator(commonService.thisUserAccount());
            Integer id = basicCommonQuoteFieldInfoDao.saveBasicCommonQuoteFieldInfo(basicCommonQuoteFieldInfo);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicCommonQuoteFieldInfo.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicCommonQuoteFieldInfo commonQuoteFieldInfo = basicCommonQuoteFieldInfoDao.getBasicCommonQuoteFieldInfoById(basicCommonQuoteFieldInfo.getId());
                if (commonQuoteFieldInfo != null) {
                    basicCommonQuoteFieldInfo.setBisDelete(commonQuoteFieldInfo.getBisDelete());
                    basicCommonQuoteFieldInfo.setCreator(commonQuoteFieldInfo.getCreator());
                    basicCommonQuoteFieldInfo.setGmtCreated(commonQuoteFieldInfo.getGmtCreated());
                    basicCommonQuoteFieldInfo.setGmtModified(DateUtils.now());
                }
            }
            basicCommonQuoteFieldInfoDao.updateBasicCommonQuoteFieldInfo(basicCommonQuoteFieldInfo, updateNull);
            return basicCommonQuoteFieldInfo.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicCommonQuoteFieldInfo(Integer id) throws Exception {
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(id);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicCommonQuoteFieldInfo.class));
        boolean flag = basicCommonQuoteFieldInfoDao.deleteBasicCommonQuoteFieldInfo(id);
        baseAttachmentService.deleteAttachmentByDto(sysAttachmentDto);
        return flag;
    }

    /**
     * 设值
     *
     * @param applyBatchId
     * @param quoteFieldEnum
     * @param value
     */
    public void setValue(Integer applyBatchId, String type, ExamineCommonQuoteFieldEnum quoteFieldEnum, String value) {
        setValue(applyBatchId, type, quoteFieldEnum, value, null, null);
    }

    /**
     * 设值
     *
     * @param applyBatchId
     * @param quoteFieldEnum
     * @param value
     */
    public void setValue(Integer applyBatchId, String type, ExamineCommonQuoteFieldEnum quoteFieldEnum, String value, String tableName, Integer tableId) {
        if (StringUtils.isBlank(value) || "null".equals(value)) return;
        BasicCommonQuoteFieldInfo info = new BasicCommonQuoteFieldInfo();
        info.setApplyBatchId(applyBatchId);
        info.setType(type);
        info.setTableName(tableName);
        info.setTableId(tableId);
        info.setFieldKey(quoteFieldEnum.getKey());
        info.setFieldValue(value);
        saveAndUpdateBasicCommonQuoteFieldInfo(info, false);
    }

    /**
     * 取值
     *
     * @param applyBatchId
     * @param quoteFieldEnum
     * @return
     */
    public String getValue(Integer applyBatchId, ExamineCommonQuoteFieldEnum quoteFieldEnum) {
        return getValue(applyBatchId, quoteFieldEnum, null, null, null);
    }

    /**
     * 取值
     *
     * @param applyBatchId
     * @param quoteFieldEnum
     * @return
     */
    public String getValue(Integer applyBatchId, ExamineCommonQuoteFieldEnum quoteFieldEnum, String type, String tableName, Integer tableId) {
        BasicCommonQuoteFieldInfo where = new BasicCommonQuoteFieldInfo();
        where.setApplyBatchId(applyBatchId);
        where.setFieldKey(quoteFieldEnum.getKey());
        where.setType(type);
        where.setTableName(tableName);
        where.setTableId(tableId);
        List<BasicCommonQuoteFieldInfo> list = basicCommonQuoteFieldInfoDao.basicCommonQuoteFieldInfoList(where);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0).getFieldValue();
    }
}
