package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 13426 on 2018/5/15.
 */
@Service
public class DeclareRecordService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeclareRecordDao declareRecordDao;


    public Integer saveAndUpdateDeclareRecord(DeclareRecord declareRecord) throws BusinessException {
        if (declareRecord == null) {
            throw new BusinessException("null point");
        }
        if (declareRecord.getId() == null || declareRecord.getId().intValue() == 0) {
            return declareRecordDao.saveReturnId(declareRecord);
        } else {
            declareRecordDao.updateDeclareRecord(declareRecord);
            return declareRecord.getId();
        }
    }


    public List<DeclareRecord> getDeclareRecordByProjectId(Integer projectId) {
        List<DeclareRecord> declareRecords = declareRecordDao.getDeclareRecordListByProjectId(projectId);
        return declareRecords;
    }

    public List<DeclareRecord> getDeclareRecordList(Integer projectId,Boolean bisGenerate) {
        List<DeclareRecord> declareRecords = declareRecordDao.getDeclareRecordList(projectId,bisGenerate);
        return declareRecords;
    }

    public BootstrapTableVo getDeclareRecordList(Integer projectId,String name,String seat,Boolean bisPartIn) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRecord> declareRecordList = declareRecordDao.getDeclareRecordList(projectId, name, seat, bisPartIn);
        vo.setTotal(page.getTotal());
        vo.setRows(declareRecordList);
        return vo;
    }

    /**
     * 添加或移除申报记录
     * @param ids
     * @param bisPartIn
     */
    @Transactional(rollbackFor = Exception.class)
    public void addOrRemoveDeclareRecord(String ids,Boolean bisPartIn){
        if(StringUtils.isNotBlank(ids)){
            List<Integer> idList= FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
            if(CollectionUtils.isNotEmpty(idList)){
                List<DeclareRecord> recordList = declareRecordDao.getDeclareRecordListByIds(idList);
                for (DeclareRecord declareRecord : recordList) {
                    declareRecord.setBisPartIn(bisPartIn);
                    declareRecordDao.updateDeclareRecord(declareRecord);
                }
            }
        }
    }

    public DeclareRecord getDeclareRecordById(Integer id) {
        return declareRecordDao.getDeclareRecordById(id);
    }

    public List<DeclareRecord> getDeclareRecordListByIds(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) return null;
        return declareRecordDao.getDeclareRecordListByIds(ids);
    }

}
