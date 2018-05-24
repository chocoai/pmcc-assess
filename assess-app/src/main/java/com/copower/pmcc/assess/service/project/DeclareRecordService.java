package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.DeclareRecordItems;
import com.copower.pmcc.assess.common.DeclareRecordList;
import com.copower.pmcc.assess.dal.dao.DeclareRecordDao;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dal.entity.SchemeAreaGroupAuxiliary;
import com.copower.pmcc.assess.dto.input.project.SchemeAreaGroupDto;
import com.copower.pmcc.assess.dto.input.project.SchemeJudgeObjectDto;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.SchemeAreaGroupService;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Created by 13426 on 2018/5/15.
 */
@Service
public class DeclareRecordService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DeclareRecordDao dao;
    @Autowired
    private ErpAreaService areaService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ProjectInfoService projectInfoService;

    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;


    /*相同省 市 ====>注意必须重写equals*/
    @Transactional(readOnly = true)
    public List<DeclareRecord> queryProvinceCityAll() {
        List<DeclareRecord> declareRecords = dao.queryProvinceCityAll();
        List<DeclareRecord> declareRecords2 = dao.queryProvinceCityDistrictAll();
        declareRecords.removeAll(declareRecords2);
        return declareRecords;
    }

    /*相同省 市 县*/
    @Transactional(readOnly = true)
    public List<DeclareRecord> queryProvinceCityDistrictAll() {
        List<DeclareRecord> declareRecords = dao.queryProvinceCityDistrictAll();
        return declareRecords;
    }

    public List<DeclareRecord> queryNotSelect() {
        List<DeclareRecord> declareRecords = dao.queryDeclareRecords();
        List<DeclareRecord> declareRecords2 = dao.queryProvinceAll();
        declareRecords.removeAll(declareRecords2);
        int size = declareRecords.size();
        if (declareRecords.size() == size) {//说明没有删除掉,那么暴力删除
            declareRecords2.parallelStream().forEach(declareRecord -> {
                declareRecords.remove(declareRecord);//remove
            });
        }

        List<DeclareRecord> declareRecordk = new ArrayList<>();
        if (declareRecords.size() == size) {//继续删除
            for (DeclareRecord record : declareRecords) {
                for (DeclareRecord r : declareRecords2) {
                    if (!record.equals(r)) {
                        declareRecordk.add(record);
                    }
                }
            }
            return declareRecordk;
        }
        return declareRecords;
    }

    /**
     * 初始化
     *
     * @param projectID
     */
    public void schemeareagroupauxiliary(String projectID) {
        /**
         * 思路:所有非省会城市必然有一个中心城市即省会，有可能没有下级城市但是也可能有
         * 需求市 把相同城市下的房产信息放在一个列表中,然后在添加到一个列表中
         * 可能会有散乱的 把全部列表减去查询出来的就是散乱的
         */
        List<DeclareRecord> declareRecords = dao.queryAll(projectID);
        List<SysAreaDto> sysAreaDtos = areaService.getProvinceList();
        for (int i = 0; i < sysAreaDtos.size(); i++) {//省
            SysAreaDto sysAreaDto = sysAreaDtos.get(i);
            Integer id = sysAreaDto.getId();//省标识符
            List<SysAreaDto> citys = areaService.getAreaList(id + "");
            for (int j = 0; j < citys.size(); j++) {//市
                SysAreaDto sysAreaDto1 = citys.get(j);
                String parentId = sysAreaDto1.getParentId();//父标识符 也就是省会标识符
                String areaId = sysAreaDto1.getAreaId();//市标识符
                //县级或者县级市
                List<SysAreaDto> districts = areaService.getAreaList(areaId);
                //size==0那么可能是直辖市
                if (districts.size() != 0) {
                    //非直辖市判断 并且添加到列表中
                    for (int k = 0; k < districts.size(); k++) {
                        try {
                            List<DeclareRecord> d1s = new ArrayList<>();
                            SysAreaDto sysAreaDto2 = districts.get(k);
                            String districtID = sysAreaDto2.getId() + "";//县或者县级市标识符
                            SchemeJudgeObjectDto objectDto = null;
                            String groupID = UUID.randomUUID().toString();
                            for (DeclareRecord d : declareRecords) {
                                if (!ObjectUtils.isEmpty(d) && !StringUtils.isEmpty(d.getProvince()) && !StringUtils.isEmpty(d.getDistrict()) && !StringUtils.isEmpty(d.getCity())) {
                                    if (d.getProvince().equals(parentId) && d.getCity().equals(areaId) && d.getDistrict().equals(districtID)) {
                                        objectDto = new SchemeJudgeObjectDto();
                                        d1s.add(d);
                                        objectDto.setProjectId(Integer.parseInt(projectID));
                                        objectDto.setDeclareRecordId(d.getId());
                                        objectDto.setCreator(commonService.thisUserAccount());
                                        objectDto.setGroupId(groupID);
                                        objectDto.setFloorArea(d.getFloorArea());
                                        objectDto.setName(d.getName());
                                        objectDto.setFlag("0");
                                        if (objectDto != null) {
                                            schemeJudgeObjectService.add(objectDto);
                                        }
                                    }
                                }
                            }
                            if (d1s.size() >= 1) {
                                SchemeAreaGroupDto groupDto = new SchemeAreaGroupDto();
                                groupDto.setGroupId(groupID);
                                groupDto.setProjectId(Integer.parseInt(projectID));
                                groupDto.setGroupId(groupID);
                                groupDto.setCreator(commonService.thisUserAccount());
                                groupDto.setCity(areaId);
                                groupDto.setProvince(id + "");
                                groupDto.setDistrict(districtID);
                                groupDto.setCreator(commonService.thisUserAccount());
                                StringBuilder builder = new StringBuilder(1024);
                                if (id != null) {
                                    builder.append(projectInfoService.getProvinceName(id));
                                }
                                if (!StringUtils.isEmpty(areaId)) {
                                    builder.append(projectInfoService.getSysArea(Integer.parseInt(areaId)));
                                }
                                if (!StringUtils.isEmpty(districtID)) {
                                    builder.append(projectInfoService.getSysArea(Integer.parseInt(districtID)));
                                }
                                groupDto.setProvinceCityDistrictStr(builder.toString());
                                schemeAreaGroupService.add(groupDto);
                            }
                        } catch (Exception e) {
                            logger.error(e.getMessage());
                            logger.error("有可能个别数据有异常会抛出数组越界! 如非法数据");
                        }
                    }
                } else {
                    //直辖市判断 并且初始化
                    SchemeJudgeObjectDto objectDto = null;
                    String groupID = UUID.randomUUID().toString();
                    List<DeclareRecord> d2s = new ArrayList<>();
                    for (DeclareRecord d : declareRecords) {
                        objectDto = new SchemeJudgeObjectDto();
                        String getProvince = d.getProvince();
                        String getCity = d.getCity();
                        if (!ObjectUtils.isEmpty(d) && !StringUtils.isEmpty(getProvince) && !StringUtils.isEmpty(getCity)) {
                            if (getProvince.equals(parentId) && getCity.equals(areaId)) {
                                d2s.add(d);
                                objectDto.setProjectId(Integer.parseInt(projectID));
                                objectDto.setDeclareRecordId(d.getId());
                                objectDto.setCreator(commonService.thisUserAccount());
                                objectDto.setGroupId(groupID);
                                objectDto.setFloorArea(d.getFloorArea());
                                objectDto.setName(d.getName());
                                objectDto.setFlag("0");
                                if (objectDto != null) schemeJudgeObjectService.add(objectDto);
                            }
                        }
                    }
                    if (d2s.size() >= 1) {
                        SchemeAreaGroupDto groupDto = new SchemeAreaGroupDto();
                        groupDto.setGroupId(groupID);
                        groupDto.setProjectId(Integer.parseInt(projectID));
                        groupDto.setGroupId(groupID);
                        groupDto.setCreator(commonService.thisUserAccount());
                        groupDto.setCity(areaId);
                        groupDto.setProvince(id + "");
                        groupDto.setCreator(commonService.thisUserAccount());
                        StringBuilder builder = new StringBuilder(1024);
                        if (id != null) {
                            builder.append(projectInfoService.getProvinceName(id));
                        }
                        if (!StringUtils.isEmpty(areaId)) {
                            builder.append(projectInfoService.getSysArea(Integer.parseInt(areaId)));
                        }
                        groupDto.setProvinceCityDistrictStr(builder.toString());
                        schemeAreaGroupService.add(groupDto);
                    }
                }
            }
        }
    }

    /**
     * 申报记录信息按区域分组
     *
     * @param declareRecords
     * @return
     */
    public List<SchemeAreaGroup> groupDeclareRecord(List<DeclareRecord> declareRecords) {
        if (CollectionUtils.isEmpty(declareRecords)) return null;
        //1.查看区域信息
        HashSet<String> hashSet = Sets.newHashSet();
        String areaKey = "";
        for (DeclareRecord declareRecord : declareRecords) {
            areaKey = "";
            if (StringUtils.isNotBlank(declareRecord.getProvince())) {
                areaKey += declareRecord.getProvince() + "_";
            }
            if (StringUtils.isNotBlank(declareRecord.getCity())) {
                areaKey += declareRecord.getCity() + "_";
            }
            if (StringUtils.isNotBlank(declareRecord.getDistrict())) {
                areaKey += declareRecord.getDistrict() + "_";
            }
            if (!hashSet.contains(areaKey)) {
                hashSet.add(areaKey);
            }
        }
        if (hashSet.isEmpty()) return null;
        List<SchemeAreaGroup> schemeAreaGroups = Lists.newArrayList();
        hashSet.forEach(p -> {
            SchemeAreaGroup schemeAreaGroup = new SchemeAreaGroup();
            String[] areaIds = p.split("_");
            if (areaIds.length > 2)
                schemeAreaGroup.setProvince(areaIds[2]);
            if (areaIds.length > 1)
                schemeAreaGroup.setCity(areaIds[1]);
            if (areaIds.length > 0)
                schemeAreaGroup.setDistrict(areaIds[0]);
            schemeAreaGroups.add(schemeAreaGroup);
        });
        return schemeAreaGroups;
    }

    public String aa(){
        return "";
    }


    public List<DeclareRecord> getDeclareRecordByProjectId(Integer projectId) {
        List<DeclareRecord> declareRecords = dao.getDeclareRecordByProjectId(projectId);
        return declareRecords;
    }

    public List<DeclareRecord> getDeclareRecordById(Integer id) {
        return dao.getDeclareRecordById(id);
    }

    public DeclareRecord getByID(Integer id) {
        return dao.getByID(id);
    }
}
