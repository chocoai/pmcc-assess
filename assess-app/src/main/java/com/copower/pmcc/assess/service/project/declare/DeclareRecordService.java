package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeAreaGroupDto;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeJudgeObjectDto;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeAreaGroupVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.api.dto.SysAreaDto;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Autowired
    private ErpAreaService erpAreaService;

    /**
     * 初始化
     *
     * @param projectID
     */
    public void schemeareagroupauxiliary(Integer projectID) {
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
                                        objectDto.setProjectId(projectID);
                                        objectDto.setDeclareRecordId(d.getId());
                                        objectDto.setCreator(commonService.thisUserAccount());
                                        objectDto.setAreaGroupId(0);
                                        objectDto.setFloorArea(d.getFloorArea());
                                        objectDto.setName(d.getName());
                                        objectDto.setBisSplit(false);
                                        if (objectDto != null) {
                                            schemeJudgeObjectService.add(objectDto);
                                        }
                                    }
                                }
                            }
                            if (d1s.size() >= 1) {
                                SchemeAreaGroupDto groupDto = new SchemeAreaGroupDto();
                                groupDto.setProjectId(projectID);
                                groupDto.setCreator(commonService.thisUserAccount());
                                groupDto.setCity(areaId);
                                groupDto.setProvince(id + "");
                                groupDto.setDistrict(districtID);
                                groupDto.setCreator(commonService.thisUserAccount());
                                groupDto.setProvinceCityDistrictStr(erpAreaService.getAreaFullName(id+"",areaId,districtID));
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
                                objectDto.setProjectId(projectID);
                                objectDto.setDeclareRecordId(d.getId());
                                objectDto.setCreator(commonService.thisUserAccount());
                                objectDto.setAreaGroupId(0);
                                objectDto.setFloorArea(d.getFloorArea());
                                objectDto.setName(d.getName());
                                objectDto.setBisSplit(false);
                                if (objectDto != null) schemeJudgeObjectService.add(objectDto);
                            }
                        }
                    }
                    if (d2s.size() >= 1) {
                        SchemeAreaGroupDto groupDto = new SchemeAreaGroupDto();
                        groupDto.setProjectId(projectID);
                        groupDto.setCreator(commonService.thisUserAccount());
                        groupDto.setCity(areaId);
                        groupDto.setProvince(id + "");
                        groupDto.setCreator(commonService.thisUserAccount());
                        groupDto.setProvinceCityDistrictStr(erpAreaService.getAreaFullName(id+"",areaId,null));
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
                areaKey += declareRecord.getDistrict();
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
                schemeAreaGroup.setDistrict(areaIds[2]);
            if (areaIds.length > 1)
                schemeAreaGroup.setCity(areaIds[1]);
            if (areaIds.length > 0)
                schemeAreaGroup.setProvince(areaIds[0]);
            schemeAreaGroups.add(schemeAreaGroup);
        });
        return schemeAreaGroups;
    }

    /**
     * 获取申报信息的区域分组
     *
     * @param projectId
     * @return
     */
    public List<SchemeAreaGroupVo> getSchemeGroup(Integer projectId) {
        List<SchemeAreaGroupVo> voList = schemeAreaGroupService.schemeAreaGroupVoList(projectId);
        if (CollectionUtils.isNotEmpty(voList))
            return voList;
        List<DeclareRecord> declareRecords = dao.getDeclareRecordByProjectId(projectId);
        List<SchemeAreaGroup> areaGroups = groupDeclareRecord(declareRecords);
        if (CollectionUtils.isNotEmpty(areaGroups)) {
            List<DeclareRecord> removeList = Lists.newArrayList();

            for (SchemeAreaGroup areaGroup : areaGroups) {
                String areaFullName = erpAreaService.getAreaFullName(areaGroup.getProvince(), areaGroup.getCity(), areaGroup.getDistrict());
                areaGroup.setProvinceCityDistrictStr(areaFullName);
                areaGroup.setProjectId(projectId);
                areaGroup.setCreator(commonService.thisUserAccount());
                schemeAreaGroupService.add(areaGroup);
                int i=1;
                //初始化估价对象
                for (DeclareRecord declareRecord : declareRecords) {
                    boolean isSameProvince = StringUtils.equals(declareRecord.getProvince(), areaGroup.getProvince());
                    boolean isSameCity = StringUtils.equals(declareRecord.getCity(), areaGroup.getCity());
                    boolean isSameDistrict = StringUtils.equals(declareRecord.getDistrict(), areaGroup.getDistrict());
                    if (isSameProvince && isSameCity && isSameDistrict) {
                        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
                        schemeJudgeObject.setProjectId(projectId);
                        schemeJudgeObject.setDeclareRecordId(declareRecord.getId());
                        schemeJudgeObject.setNumber(i++);
                        schemeJudgeObject.setCreator(commonService.thisUserAccount());
                        schemeJudgeObject.setAreaGroupId(areaGroup.getId());
                        schemeJudgeObject.setFloorArea(declareRecord.getFloorArea());
                        schemeJudgeObject.setName(declareRecord.getName());
                        schemeJudgeObject.setOwnership(declareRecord.getOwnership());
                        schemeJudgeObject.setBisSplit(false);
                        schemeJudgeObject.setCreator(commonService.thisUserAccount());
                        schemeJudgeObjectService.add(schemeJudgeObject);

                        //反写申报数据的区域id
                        declareRecord.setAreaGroupId(areaGroup.getId());
                        dao.updateDeclareRecord(declareRecord);

                        removeList.add(declareRecord);//已被添加到区域的数据下次循环移除掉
                    }
                }
            }
        }
        voList = schemeAreaGroupService.schemeAreaGroupVoList(projectId);
        return voList;
    }



    public List<DeclareRecord> getDeclareRecordByProjectId(Integer projectId) {
        List<DeclareRecord> declareRecords = dao.getDeclareRecordByProjectId(projectId);
        return declareRecords;
    }

    public DeclareRecord getDeclareRecordById(Integer id) {
        return dao.getDeclareRecordById(id);
    }

}
