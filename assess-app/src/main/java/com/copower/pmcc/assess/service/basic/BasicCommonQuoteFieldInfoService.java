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
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicEstateService basicEstateService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicCommonQuoteFieldInfo getBasicCommonQuoteFieldInfoById(Integer id) throws Exception {
        return basicCommonQuoteFieldInfoDao.getBasicCommonQuoteFieldInfoById(id);
    }

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

    public void addBasicCommonQuoteFieldInfo(BasicCommonQuoteFieldInfo info) {
        BasicCommonQuoteFieldInfo query = new BasicCommonQuoteFieldInfo();
        query.setApplyBatchId(info.getApplyBatchId());
        query.setType(info.getType());
        query.setTableName(info.getTableName());
        query.setTableId(info.getTableId());
        List<BasicCommonQuoteFieldInfo> fieldInfos = basicCommonQuoteFieldInfoList(query);
        if (CollectionUtils.isNotEmpty(fieldInfos)) {
            info.setId(fieldInfos.get(0).getId());
        }
        saveAndUpdateBasicCommonQuoteFieldInfo(info, false);
    }


    /**
     * 楼栋
     *
     * @param applyBatchId
     * @param tableName
     * @param obj
     */
    public void settingBuildingObjValue(Integer applyBatchId, String tableName, Object obj) {
        if (applyBatchId == null) {
            return;
        }
        BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(applyBatchId);
        if (basicApplyBatch == null) {
            return;
        }
        if (basicApplyBatch.getEstateId() == null) {
            return;
        }
        settingObjValue(applyBatchId, tableName, basicApplyBatch.getEstateId(), obj);
    }

    /**
     * 普通引用
     *
     * @param applyBatchId
     * @param tableName    如楼盘表名
     * @param tableId      如楼盘id
     * @param obj          如楼栋对象
     */
    public void settingObjValue(Integer applyBatchId, String tableName, Integer tableId, Object obj) {
        if (applyBatchId == null) {
            return;
        }
        if (obj == null) {
            return;
        }
        BasicCommonQuoteFieldInfo query = new BasicCommonQuoteFieldInfo();
        query.setApplyBatchId(applyBatchId);
        query.setTableName(tableName);
        query.setTableId(tableId);
        List<BasicCommonQuoteFieldInfo> basicCommonQuoteFieldInfos = basicCommonQuoteFieldInfoList(query);
        if (CollectionUtils.isEmpty(basicCommonQuoteFieldInfos)) {
            return;
        }
        Iterator<BasicCommonQuoteFieldInfo> iterator = basicCommonQuoteFieldInfos.iterator();
        while (iterator.hasNext()) {
            BasicCommonQuoteFieldInfo commonQuoteFieldInfo = iterator.next();

            //目前考虑楼盘的公共字段  引用到楼栋中去使用
            if (Objects.equal(commonQuoteFieldInfo.getTableName(), BasicFormClassifyEnum.ESTATE.getTableName())) {
                invokeSetter(obj, ExamineCommonQuoteFieldEnum.COMMON_QUOTE_OPENTIME_ENUM.getKey(), commonQuoteFieldInfo.getOpenTime());
                invokeSetter(obj, ExamineCommonQuoteFieldEnum.COMMON_QUOTE_LANDUSEYEAR_ENUM.getKey(), commonQuoteFieldInfo.getLandUseYear());
            }

            //

        }
    }

    public void settingObjValue(Integer applyBatchId, String tableName, Integer tableId, Object obj, ExamineCommonQuoteFieldEnum examineCommonQuoteFieldEnum) {
        BasicCommonQuoteFieldInfo query = new BasicCommonQuoteFieldInfo();
        query.setApplyBatchId(applyBatchId);
        query.setTableName(tableName);
        query.setTableId(tableId);
        List<BasicCommonQuoteFieldInfo> basicCommonQuoteFieldInfos = basicCommonQuoteFieldInfoList(query);
        if (CollectionUtils.isEmpty(basicCommonQuoteFieldInfos)) {
            return;
        }
        Iterator<BasicCommonQuoteFieldInfo> iterator = basicCommonQuoteFieldInfos.iterator();
        while (iterator.hasNext()) {
            BasicCommonQuoteFieldInfo commonQuoteFieldInfo = iterator.next();

            //目前考虑楼盘的公共字段  引用到楼栋中去使用
            if (Objects.equal(commonQuoteFieldInfo.getTableName(), BasicFormClassifyEnum.ESTATE.getTableName())) {
                invokeSetter(obj, examineCommonQuoteFieldEnum.getKey(), commonQuoteFieldInfo.getOpenTime());
            }

            //

        }
    }


    private void invokeSetter(Object target, String propertyName, Object value) {
        if (value == null || propertyName == null || target == null) {
            return;
        }
        try {
            Reflections.invokeSetter(target, propertyName, value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 获取数据列表
     *
     * @param basicCommonQuoteFieldInfo
     * @return
     * @throws Exception
     */
    public List<BasicCommonQuoteFieldInfo> basicCommonQuoteFieldInfoList(BasicCommonQuoteFieldInfo basicCommonQuoteFieldInfo) {
        return basicCommonQuoteFieldInfoDao.basicCommonQuoteFieldInfoList(basicCommonQuoteFieldInfo);
    }

    public List<BasicCommonQuoteFieldInfo> getCommonQuoteInfoByApplyBatchId(Integer applyBatchId, String type) {
        BasicCommonQuoteFieldInfo query = new BasicCommonQuoteFieldInfo();
        query.setApplyBatchId(applyBatchId);
        if (StringUtils.isNotBlank(type)) {
            query.setType(type);
        }
        return basicCommonQuoteFieldInfoList(query);
    }


    public BootstrapTableVo getBootstrapTableVo(BasicCommonQuoteFieldInfo basicCommonQuoteFieldInfo) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicCommonQuoteFieldInfo> basicCommonQuoteFieldInfoList = basicCommonQuoteFieldInfoDao.basicCommonQuoteFieldInfoList(basicCommonQuoteFieldInfo);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicCommonQuoteFieldInfoList) ? new ArrayList<BasicCommonQuoteFieldInfo>(10) : basicCommonQuoteFieldInfoList);
        return vo;
    }


}
