package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
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
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    @Autowired
    private DeclareRealtyHouseCertService declareRealtyHouseCertService;
    @Autowired
    private DeclareRealtyLandCertService declareRealtyLandCertService;
    @Autowired
    private DeclareRealtyRealEstateCertService declareRealtyRealEstateCertService;


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
        return saveAndUpdateDeclareRecord(declareRecord, false);
    }

    public Integer saveAndUpdateDeclareRecord(DeclareRecord declareRecord, boolean updateNull) {
        if (declareRecord == null) {
            return null;
        }
        if (declareRecord.getId() == null || declareRecord.getId() == 0) {
            declareRecord.setCreator(commonService.thisUserAccount());
            declareRecordDao.saveReturnId(declareRecord);
            return declareRecord.getId();
        } else {
            updateDeclareRecord(declareRecord, updateNull);
            return declareRecord.getId();
        }
    }

    public boolean updateDeclareRecord(DeclareRecord oo, boolean updateNull) {
        return declareRecordDao.updateDeclareRecord(oo, updateNull);
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

    public BootstrapTableVo getBootstrapTableVo(DeclareRecord declareRecord) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRecord> declareRecordList = declareRecordDao.getDeclareRecordList(declareRecord);
        List<DeclareRecordVo> vos = LangUtils.transform(declareRecordList, o -> getDeclareRecordVo(o));
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DeclareRecordVo>() : vos);
        return vo;
    }

    public BootstrapTableVo getDeclareRecordList(Integer projectId, String name, String seat, Boolean bisPartIn, String province, String city, String district, String inventoryStatus) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRecord> declareRecordList = declareRecordDao.getDeclareRecordList(projectId, name, seat, bisPartIn, province, city, district, inventoryStatus);
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


    /**
     * 变更  编号
     *
     * @param declareRecordId
     * @param projectId
     * @param number
     * @return
     */
    public String changeDeclareRecordNumber(Integer declareRecordId, Integer projectId, String number) {
        StringBuilder stringBuilder = new StringBuilder();
        DeclareRecord declareRecord = getDeclareRecordById(declareRecordId);
        SchemeJudgeObject query = new SchemeJudgeObject();
        query.setProjectId(projectId);
        query.setDeclareRecordId(declareRecordId);
        List<SchemeJudgeObject> objectList = schemeJudgeObjectService.getJudgeObjectList(query);
        if (CollectionUtils.isNotEmpty(objectList)) {
            stringBuilder.append("已经生成了估价对象不允许修改");
            return stringBuilder.toString();
        }
        TreeSet<Integer> treeSet = getCountByPlanDetailsIdAndAutoInitNumberList(projectId);
        if (CollectionUtils.isNotEmpty(treeSet) && treeSet.contains(Integer.parseInt(number))) {
            stringBuilder.append("编号重复");
            return stringBuilder.toString();
        }
        changeDeclareRecordNumberHelp(declareRecord, Integer.parseInt(number));
        stringBuilder.append("变更成功");
        return stringBuilder.toString();
    }

    private void changeDeclareRecordNumberHelp(DeclareRecord declareRecord, Integer number) {
        if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyHouseCert.class))) {
            declareRealtyHouseCertService.changeAutoInitNumber(number, declareRecord.getDataTableId());
        }

        if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyLandCert.class))) {
            declareRealtyLandCertService.changeAutoInitNumber(number, declareRecord.getDataTableId());
        }
        if (Objects.equal(declareRecord.getDataTableName(), FormatUtils.entityNameConvertToTableName(DeclareRealtyRealEstateCert.class))) {
            declareRealtyRealEstateCertService.changeAutoInitNumber(number, declareRecord.getDataTableId());
        }

        declareRecord.setNumber(number);
        saveAndUpdateDeclareRecord(declareRecord, true);
    }

    /**
     * 自动变更编号
     *
     * @param projectId
     * @return
     */
    public String autoChangeDeclareRecordNumber(Integer projectId) {
        StringBuilder stringBuilder = new StringBuilder();
        List<DeclareRecord> declareRecordList = getDeclareRecordByProjectId(projectId);
        if (CollectionUtils.isEmpty(declareRecordList)) {
            return "无权证记录";
        }
        TreeSet<Integer> treeSet = Sets.newTreeSet();
        int startIndex = 1;
        List<SchemeJudgeObject> schemeJudgeObjectList = getJudgeObjectListByProjectId(projectId);
        if (CollectionUtils.isNotEmpty(declareRecordList) && CollectionUtils.isNotEmpty(schemeJudgeObjectList)) {
            List<Integer> collect = schemeJudgeObjectList.stream().map(schemeJudgeObject -> schemeJudgeObject.getDeclareRecordId()).collect(Collectors.toList());
            Iterator<DeclareRecord> iterator = declareRecordList.iterator();
            while (iterator.hasNext()) {
                DeclareRecord declareRecord = iterator.next();
                if (CollectionUtils.isNotEmpty(collect)) {
                    if (collect.contains(declareRecord.getId())) {
                        if (declareRecord.getNumber() != null || declareRecord.getNumber() != 0) {
                            treeSet.add(declareRecord.getNumber());
                        }
                        iterator.remove();
                    }
                }
            }
        }
        if (CollectionUtils.isEmpty(declareRecordList)) {
            return "权证已经全部生成了估价对象,无剩余可以变更编号的权证";
        }
        if (CollectionUtils.isNotEmpty(treeSet)) {
            startIndex += treeSet.last().intValue();
        }
        //排下序
        Ordering<DeclareRecord> ordering = Ordering.from(new Comparator<DeclareRecord>() {
            @Override
            public int compare(DeclareRecord o1, DeclareRecord o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        declareRecordList.sort(ordering);
        Iterator<DeclareRecord> iterator = declareRecordList.iterator();
        while (iterator.hasNext()) {
            DeclareRecord declareRecord = iterator.next();
            changeDeclareRecordNumberHelp(declareRecord, startIndex);
            startIndex++;
        }
        stringBuilder.append("变更成功");
        return stringBuilder.toString();
    }


    private TreeSet<Integer> getCountByPlanDetailsIdAndAutoInitNumberList(Integer projectId) {
        TreeSet<Integer> treeSet = Sets.newTreeSet();
        List<DeclareRecord> declareRecordList = getDeclareRecordByProjectId(projectId);
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            Iterator<DeclareRecord> iterator = declareRecordList.iterator();
            while (iterator.hasNext()) {
                DeclareRecord declareRecord = iterator.next();
                if (declareRecord.getNumber() == null || declareRecord.getNumber() == 0) {
                    continue;
                }
                treeSet.add(declareRecord.getNumber());
            }
        }
        return treeSet;
    }

    private List<SchemeJudgeObject> getJudgeObjectListByProjectId(Integer projectId) {
        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
        schemeJudgeObject.setProjectId(projectId);
        return schemeJudgeObjectService.getJudgeObjectList(schemeJudgeObject);
    }

    /**
     * 根据id获取坐落
     *
     * @param id
     * @return
     */
    public String getSeatById(Integer id) {
        if (id == null) return "";
        DeclareRecord declareRecord = getDeclareRecordById(id);
        String cityName = erpAreaService.getSysAreaName(declareRecord.getCity());
        String districtName = erpAreaService.getSysAreaName(declareRecord.getDistrict());
        String seat = declareRecord.getSeat();
        if (!seat.contains(districtName))
            seat = districtName + seat;
        if (!seat.contains(cityName))
            seat = cityName + seat;
        return seat;
    }
}
