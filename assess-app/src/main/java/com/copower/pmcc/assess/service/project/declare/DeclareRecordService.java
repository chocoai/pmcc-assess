package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.SchemeAreaGroupService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Created by 13426 on 2018/5/15.
 */
@Service
public class DeclareRecordService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private DeclareRecordDao declareRecordDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private ErpAreaService erpAreaService;

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
            schemeAreaGroup.setDistrict("");
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
    public List<SchemeAreaGroup> getSchemeGroup(Integer projectId) {
        List<SchemeAreaGroup> voList = schemeAreaGroupService.schemeAreaGroupVoList(projectId);
        if (CollectionUtils.isNotEmpty(voList))
            return voList;
        List<DeclareRecord> declareRecords = declareRecordDao.getDeclareRecordByProjectId(projectId);
        List<SchemeAreaGroup> areaGroups = groupDeclareRecord(declareRecords);
        if (CollectionUtils.isNotEmpty(areaGroups)) {
            List<DeclareRecord> removeList = Lists.newArrayList();
            for (SchemeAreaGroup areaGroup : areaGroups) {
                String areaFullName = erpAreaService.getAreaFullName(areaGroup.getProvince(), areaGroup.getCity(), areaGroup.getDistrict());
                areaGroup.setAreaName(areaFullName);
                areaGroup.setProjectId(projectId);
                areaGroup.setPid(0);
                areaGroup.setCreator(commonService.thisUserAccount());
                schemeAreaGroupService.add(areaGroup);
                int i = 1;
                //初始化估价对象
                for (DeclareRecord declareRecord : declareRecords) {
                    boolean isSameProvince = StringUtils.equals(declareRecord.getProvince(), areaGroup.getProvince());
                    boolean isSameCity = StringUtils.equals(declareRecord.getCity(), areaGroup.getCity());
                    boolean isSameDistrict = StringUtils.equals(declareRecord.getDistrict(), areaGroup.getDistrict());
                    if (isSameProvince && isSameCity && isSameDistrict) {
                        SchemeJudgeObject schemeJudgeObject = new SchemeJudgeObject();
                        schemeJudgeObject.setProjectId(projectId);
                        schemeJudgeObject.setDeclareRecordId(declareRecord.getId());
                        schemeJudgeObject.setNumber(String.valueOf(i));
                        schemeJudgeObject.setCreator(commonService.thisUserAccount());
                        schemeJudgeObject.setAreaGroupId(areaGroup.getId());
                        schemeJudgeObject.setFloorArea(declareRecord.getFloorArea());
                        schemeJudgeObject.setName(declareRecord.getName());
                        schemeJudgeObject.setOwnership(declareRecord.getOwnership());
                        schemeJudgeObject.setSeat(declareRecord.getCity());
                        schemeJudgeObject.setCertUse(declareRecord.getCertUse());
                        schemeJudgeObject.setPracticalUse(declareRecord.getPracticalUse());
                        schemeJudgeObject.setSourceId(0);
                        schemeJudgeObject.setPid(0);
                        schemeJudgeObject.setBisSplit(false);
                        schemeJudgeObject.setSorting(i);
                        schemeJudgeObject.setCreator(commonService.thisUserAccount());
                        schemeJudgeObjectService.addSchemeJudgeObject(schemeJudgeObject);
                        //反写申报数据的区域id
                        declareRecord.setAreaGroupId(areaGroup.getId());
                        declareRecordDao.updateDeclareRecord(declareRecord);

                        removeList.add(declareRecord);//已被添加到区域的数据下次循环移除掉
                        i++;
                    }
                }
            }
        }
        voList = schemeAreaGroupService.schemeAreaGroupVoList(projectId);
        return voList;
    }


    public List<DeclareRecord> getDeclareRecordByProjectId(Integer projectId) {
        List<DeclareRecord> declareRecords = declareRecordDao.getDeclareRecordByProjectId(projectId);
        return declareRecords;
    }

    public DeclareRecord getDeclareRecordById(Integer id) {
        return declareRecordDao.getDeclareRecordById(id);
    }

}
