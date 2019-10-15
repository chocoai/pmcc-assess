package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.common.enums.meau.DeclareRecordGroupEnum;
import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordGroupDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordGroup;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRecordGroupVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zch on 2019-10-14.
 * 资产申报 分组
 */
@Service
public class DeclareRecordGroupService {

    @Autowired
    private DeclareRecordGroupDao declareRecordGroupDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private TaskExecutor taskExecutor;

    public void switchAutoGroup(DeclareRecordGroup recordGroup, Integer key) throws Exception {
        DeclareRecord select = new DeclareRecord();
        select.setProjectId(recordGroup.getProjectId());
        List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordList(select);
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            Iterator<DeclareRecord> iterator = declareRecordList.iterator();
            while (iterator.hasNext()) {
                DeclareRecord declareRecord = iterator.next();
                if (declareRecord.getGroupId() != null && declareRecord.getGroupId() != 0) {
                    iterator.remove();
                }
            }
        }
        if (CollectionUtils.isEmpty(declareRecordList)) {
            throw new Exception("申报数据已经分组分组完毕，此次分组无效!");
        }
        //区域分组
        if (Objects.equal(DeclareRecordGroupEnum.Area.getId(), key)) {
            Map<String, LinkedList<Integer>> listHashMap = new HashMap<>(1);
            List<String> testList = Lists.newArrayList();
            declareRecordList.forEach(declareRecord -> {
                LinkedList<Integer> integerLinkedList = Lists.newLinkedList();
                if (NumberUtils.isNumber(declareRecord.getProvince())) {
                    testList.add(erpAreaService.getSysAreaName(declareRecord.getProvince()));
                    integerLinkedList.add(Integer.parseInt(declareRecord.getProvince()));
                }
                if (NumberUtils.isNumber(declareRecord.getCity())) {
                    testList.add(erpAreaService.getSysAreaName(declareRecord.getCity()));
                    integerLinkedList.add(Integer.parseInt(declareRecord.getCity()));
                }
                if (NumberUtils.isNumber(declareRecord.getDistrict())) {
                    testList.add(erpAreaService.getSysAreaName(declareRecord.getDistrict()));
                    integerLinkedList.add(Integer.parseInt(declareRecord.getDistrict()));
                }
                listHashMap.put(StringUtils.join(testList, ""), integerLinkedList);
                testList.clear();
            });
            if (listHashMap.isEmpty()) {
                return;
            }
            List<DeclareRecordGroup> recordGroupList = Lists.newArrayList();
            listHashMap.forEach((s, integers) -> {
                DeclareRecordGroup declareRecordGroup = new DeclareRecordGroup();
                declareRecordGroup.setName(String.join("", DeclareRecordGroupEnum.Area.getRemark(), s));
                declareRecordGroup.setPlanId(recordGroup.getPlanId());
                declareRecordGroup.setProjectId(recordGroup.getProjectId());
                int size = integers.size();
                for (int i = 0; i < size; i++) {
                    if (i == 0) {
                        declareRecordGroup.setProvince(integers.get(i).toString());
                    }
                    if (i == 1) {
                        declareRecordGroup.setCity(integers.get(i).toString());
                    }
                    if (i == 2) {
                        declareRecordGroup.setDistrict(integers.get(i).toString());
                    }
                }
                saveDeclareRecordGroup(declareRecordGroup);
                recordGroupList.add(declareRecordGroup);
            });
            //记录添加 组信息
            for (DeclareRecord declareRecord : declareRecordList) {
                recordGroupList.forEach(groupObj -> {
                    boolean province = Objects.equal(groupObj.getProvince(), declareRecord.getProvince());
                    boolean city = Objects.equal(groupObj.getCity(), declareRecord.getCity());
                    boolean district = Objects.equal(groupObj.getDistrict(), declareRecord.getDistrict());
                    if (province && city && district) {
                        declareRecord.setGroupId(groupObj.getId());
                        declareRecordService.saveAndUpdateDeclareRecord(declareRecord) ;
                        return;
                    }
                    if (province && city) {
                        declareRecord.setGroupId(groupObj.getId());
                        declareRecordService.saveAndUpdateDeclareRecord(declareRecord) ;
                        return;
                    }
                    if (province) {
                        declareRecord.setGroupId(groupObj.getId());
                        declareRecordService.saveAndUpdateDeclareRecord(declareRecord) ;
                        return;
                    }
                });
            }

        }
        //楼栋号分组
        if (Objects.equal(DeclareRecordGroupEnum.BuildingNumber.getId(), key)){
            Map<String, LinkedList<Integer>> listHashMap = new HashMap<>(1);
            declareRecordList.forEach(declareRecord -> {
                if (StringUtils.isEmpty(declareRecord.getBuildingNumber())){
                    return;
                }
                LinkedList<Integer> integerLinkedList = Lists.newLinkedList();
                if (NumberUtils.isNumber(declareRecord.getProvince())) {
                    integerLinkedList.add(Integer.parseInt(declareRecord.getProvince()));
                }
                if (NumberUtils.isNumber(declareRecord.getCity())) {
                    integerLinkedList.add(Integer.parseInt(declareRecord.getCity()));
                }
                if (NumberUtils.isNumber(declareRecord.getDistrict())) {
                    integerLinkedList.add(Integer.parseInt(declareRecord.getDistrict()));
                }
                listHashMap.put(declareRecord.getBuildingNumber(), integerLinkedList);
            });
            if (listHashMap.isEmpty()) {
                return;
            }
            List<DeclareRecordGroup> recordGroupList = Lists.newArrayList();
            listHashMap.forEach((s, integers) -> {
                DeclareRecordGroup declareRecordGroup = new DeclareRecordGroup();
                declareRecordGroup.setName(s);
                declareRecordGroup.setPlanId(recordGroup.getPlanId());
                declareRecordGroup.setProjectId(recordGroup.getProjectId());
                for (int i = 0; i < integers.size(); i++) {
                    if (i == 0) {
                        declareRecordGroup.setProvince(integers.get(i).toString());
                    }
                    if (i == 1) {
                        declareRecordGroup.setCity(integers.get(i).toString());
                    }
                    if (i == 2) {
                        declareRecordGroup.setDistrict(integers.get(i).toString());
                    }
                }
                saveDeclareRecordGroup(declareRecordGroup);
                recordGroupList.add(declareRecordGroup);
            });
            //记录添加 组信息
            for (DeclareRecord declareRecord : declareRecordList) {
                recordGroupList.forEach(groupObj -> {
                    if (!Objects.equal(groupObj.getName(),declareRecord.getBuildingNumber())){
                        return;
                    }
                    declareRecord.setGroupId(groupObj.getId());
                    declareRecordService.saveAndUpdateDeclareRecord(declareRecord) ;
                    groupObj.setName(DeclareRecordGroupEnum.BuildingNumber.getRemark());
                    saveDeclareRecordGroup(groupObj) ;
                });
            }

        }
    }

    public boolean saveDeclareRecordGroup(DeclareRecordGroup DeclareRecordGroup) {
        if (DeclareRecordGroup == null) {
            return false;
        }
        if (DeclareRecordGroup.getId() != null && DeclareRecordGroup.getId() != 0) {
            return declareRecordGroupDao.updateDeclareRecordGroup(DeclareRecordGroup);
        } else {
            DeclareRecordGroup.setCreator(commonService.thisUserAccount());
            declareRecordGroupDao.addDeclareRecordGroup(DeclareRecordGroup);
            return true;
        }
    }

    public BootstrapTableVo getBootstrapTableVo(DeclareRecordGroup oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRecordGroup> childrenList = getDeclareRecordGroupListByExample(oo);
        List<DeclareRecordGroupVo> voList = Lists.newArrayList();
        childrenList.forEach(obj -> voList.add(getDeclareRecordGroupVo(obj)));
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }


    public DeclareRecordGroup getDeclareRecordGroupById(Integer id) {
        return declareRecordGroupDao.getDeclareRecordGroupById(id);
    }

    public boolean deleteDeclareRecordGroupById(Integer id) {
        return declareRecordGroupDao.deleteDeclareRecordGroupById(id);
    }

    public void deleteDeclareRecordGroup(String id) throws Exception {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        List<Integer> integerList = FormatUtils.transformString2Integer(id);
        int i = 0;
        if (CollectionUtils.isNotEmpty(integerList)) {
            for (Integer integer : integerList) {
                DeclareRecord select = new DeclareRecord();
                select.setGroupId(integer);
                List<DeclareRecord> declareRecordList = declareRecordService.getDeclareRecordList(select);
                if (CollectionUtils.isNotEmpty(declareRecordList)) {
                    i++;
                }
            }
        }
        if (i == 0) {
            declareRecordGroupDao.deleteDeclareRecordGroup(integerList);
        }
        if (i != 0) {
            throw new Exception("请移除组里面关联的申报数据!");
        }
    }

    public List<DeclareRecordGroup> getDeclareRecordGroupListByExample(DeclareRecordGroup oo) {
        return declareRecordGroupDao.getDeclareRecordGroupListByExample(oo);
    }

    public DeclareRecordGroupVo getDeclareRecordGroupVo(DeclareRecordGroup group) {
        if (group == null) {
            return null;
        }
        DeclareRecordGroupVo vo = new DeclareRecordGroupVo();
        BeanUtils.copyProperties(group, vo);
        if (org.apache.commons.lang.StringUtils.isNotBlank(group.getProvince())) {
            if (NumberUtils.isNumber(group.getProvince())) {
                //省
                vo.setProvinceName(erpAreaService.getSysAreaName(group.getProvince()));
            } else {
                //省
                vo.setProvinceName(group.getProvince());
            }
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(group.getCity())) {
            if (NumberUtils.isNumber(group.getCity())) {
                //市或者县
                vo.setCityName(erpAreaService.getSysAreaName(group.getCity()));
            } else {
                vo.setCityName(group.getCity());
            }
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(group.getDistrict())) {
            if (NumberUtils.isNumber(group.getDistrict())) {
                //县
                vo.setDistrictName(erpAreaService.getSysAreaName(group.getDistrict()));
            } else {
                vo.setDistrictName(group.getDistrict());
            }
        }
        return vo;
    }


}
