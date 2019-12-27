package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordExtend;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRecordVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by 13426 on 2018/5/15.
 */
@Service
public class DeclareRecordService {
    @Autowired
    private DeclareRecordExtendService declareRecordExtendService;
    @Autowired
    private DeclareRecordDao declareRecordDao;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;

    /**
     * 获取申报 扩展表
     *
     * @param declareId
     * @return
     */
    public DeclareRecordExtend getByDeclareRecordExtend(Integer declareId) {
        if (declareId == null) {
            return null;
        }
        DeclareRecordExtend query = new DeclareRecordExtend();
        query.setDeclareId(declareId);
        List<DeclareRecordExtend> declareRecordExtendList = declareRecordExtendService.getDeclareLandUsePermitList(query);
        if (CollectionUtils.isNotEmpty(declareRecordExtendList)) {
            return declareRecordExtendList.stream().findFirst().get();
        }
        return null;
    }

    public List<DeclareRecord> getDeclareRecordListByDataTableId(String dataTableName, Integer dataTableId, Integer projectId) {
        return declareRecordDao.getDeclareRecordListByDataTableId(dataTableName, dataTableId, projectId);
    }


    public Integer saveAndUpdateDeclareRecord(DeclareRecord declareRecord) {
        if (declareRecord == null) {
            return null;
        }
        if (declareRecord.getId() == null || declareRecord.getId() == 0) {
            declareRecord.setCreator(commonService.thisUserAccount());
            declareRecordDao.saveReturnId(declareRecord);
            return declareRecord.getId();
        } else {
            declareRecordDao.updateDeclareRecord(declareRecord);
            return declareRecord.getId();
        }
    }


    public List<DeclareRecord> getDeclareRecordByProjectId(Integer projectId) {
        List<DeclareRecord> declareRecords = declareRecordDao.getDeclareRecordListByProjectId(projectId);
        return declareRecords;
    }

    public List<DeclareRecord> getDeclareRecordList(DeclareRecord declareRecord) {
        List<DeclareRecord> declareRecords = declareRecordDao.getDeclareRecordList(declareRecord);
        return declareRecords;
    }

    /**
     * 分组或者不分组 数据获取
     *
     * @param declareRecord
     * @return
     */
    public BootstrapTableVo getBootstrapTableVoDispatch(DeclareRecord declareRecord) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        Integer groupId = declareRecord.getGroupId();
        declareRecord.setGroupId(null);
        List<DeclareRecord> declareRecordList = getDeclareRecordList(declareRecord);
        //防止数据错误 分组弄到其它项目去了的情况
        if (declareRecord.getProjectId() != null && CollectionUtils.isNotEmpty(declareRecordList)) {
            Iterator<DeclareRecord> it = declareRecordList.iterator();
            while (it.hasNext()) {
                DeclareRecord record = it.next();
                if (!Objects.equal(record.getProjectId(), declareRecord.getProjectId())) {
                    it.remove();
                }
                if (groupId != null) {
                    if (!Objects.equal(groupId, record.getGroupId())) {
                        it.remove();
                    }
                }
                if (groupId == null) {
                    if (record.getGroupId() != null && record.getGroupId() != 0) {
                        it.remove();
                    }
                }
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(declareRecordList);
        return vo;
    }

    /**
     * 去重方法
     *
     * @param keyExtractor
     * @param <T>
     * @return
     */
    private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public List<DeclareRecord> getDeclareRecordByAreaId(Integer areaId) {
        DeclareRecord where = new DeclareRecord();
        where.setAreaGroupId(areaId);
        List<DeclareRecord> declareRecords = declareRecordDao.getDeclareRecordList(where);
        return declareRecords;
    }

    public List<DeclareRecord> getDeclareRecordList(Integer projectId, Boolean bisGenerate) {
        List<DeclareRecord> declareRecords = declareRecordDao.getDeclareRecordList(projectId, bisGenerate);
        return declareRecords;
    }

    public BootstrapTableVo getBootstrapTableVo(DeclareRecord declareRecord){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRecord> declareRecordList = declareRecordDao.getDeclareRecordList(declareRecord);
        List<DeclareRecordVo> vos = LangUtils.transform(declareRecordList, o -> getDeclareRecordVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DeclareRecordVo>() : vos);
        return vo;
    }

    public BootstrapTableVo getDeclareRecordList(Integer projectId, String name, String seat, Boolean bisPartIn, String province, String city, String district) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRecord> declareRecordList = declareRecordDao.getDeclareRecordList(projectId, name, seat, bisPartIn, province, city, district);
        List<DeclareRecordVo> vos = LangUtils.transform(declareRecordList, o -> getDeclareRecordVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DeclareRecordVo>() : vos);
        return vo;
    }

    public DeclareRecordVo getDeclareRecordVo(DeclareRecord declareRecord) {
        if (declareRecord == null) {
            return null;
        }
        DeclareRecordVo vo = new DeclareRecordVo();
        BeanUtils.copyProperties(declareRecord, vo);
        if (org.apache.commons.lang.StringUtils.isNotBlank(declareRecord.getProvince())) {
            if (NumberUtils.isNumber(declareRecord.getProvince())) {
                //省
                vo.setProvinceName(erpAreaService.getSysAreaName(declareRecord.getProvince()));
            } else {
                vo.setProvinceName(declareRecord.getProvince());
            }
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(declareRecord.getCity())) {
            if (NumberUtils.isNumber(declareRecord.getCity())) {
                //市或者县
                vo.setCityName(erpAreaService.getSysAreaName(declareRecord.getCity()));
            } else {
                vo.setCityName(declareRecord.getCity());
            }
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(declareRecord.getDistrict())) {
            if (NumberUtils.isNumber(declareRecord.getDistrict())) {
                //县
                vo.setDistrictName(erpAreaService.getSysAreaName(declareRecord.getDistrict()));
            } else {
                vo.setDistrictName(declareRecord.getDistrict());
            }
        }
        return vo;
    }


    /**
     * 添加或移除申报记录
     *
     * @param ids
     * @param bisPartIn
     */
    @Transactional(rollbackFor = Exception.class)
    public void addOrRemoveDeclareRecord(String ids, Boolean bisPartIn) {
        if (StringUtils.isNotBlank(ids)) {
            List<Integer> idList = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(ids));
            if (CollectionUtils.isNotEmpty(idList)) {
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

    public void reStartDeclareApplyByDeclareRecordId(List<Integer> declareRecordIds, Integer projectId) {
        if (CollectionUtils.isEmpty(declareRecordIds)) {
            return;
        }
        if (projectId == null) {
            return;
        }
        schemeJudgeObjectService.reStartDeclareApplyByDeclareRecordId(declareRecordIds, projectId);
    }

    /**
     * 获取项目可用的最大编号
     *
     * @param projectId
     * @return
     */
    public int getMaxNumber(Integer projectId) {
        return declareRecordDao.getCountByProjectId(projectId) + 1;
    }

}
