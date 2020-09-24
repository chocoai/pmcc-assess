package com.copower.pmcc.assess.service.project;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.ArithmeticUtils;
import com.copower.pmcc.assess.dal.basis.dao.project.ProjectLandAchievementGroupDao;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailAchievement;
import com.copower.pmcc.assess.dal.basis.entity.ProjectLandAchievementGroupWithBLOBs;
import com.copower.pmcc.assess.dto.output.KeyValueVo;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelDetailAchievementVo;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailAchievementService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 地价修正因素 等级组
 */
@Service
public class ProjectLandAchievementGroupService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private ProjectLandAchievementGroupDao projectLandAchievementGroupDao;
    @Autowired
    private DataLandLevelDetailAchievementService dataLandLevelDetailAchievementService;

    public List<List<ProjectLandAchievementGroupWithBLOBs>> getInitProjectLandAchievementGroupData(Integer projectId, Integer dataTableId, String dataTableName) {
        List<ProjectLandAchievementGroupWithBLOBs> groupWithBLOBsList = projectLandAchievementGroupDao.getProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId(projectId, dataTableId, dataTableName);
        return handleGrouping(groupWithBLOBsList);
    }

    public BigDecimal countProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId(Integer projectId, Integer dataTableId, String dataTableName) {
        BigDecimal bigDecimal = new BigDecimal(0);
        List<ProjectLandAchievementGroupWithBLOBs> list = getProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId(projectId, dataTableId, dataTableName);
        if (CollectionUtils.isNotEmpty(list)) {
            List<String> stringList = new ArrayList<>();
            for (ProjectLandAchievementGroupWithBLOBs obj : list) {
                if (StringUtils.isBlank(obj.getSelectValue())) {
                    continue;
                }
                if (!NumberUtils.isNumber(obj.getSelectValue())) {
                    continue;
                }
                stringList.add(obj.getSelectValue());
            }
            if (CollectionUtils.isNotEmpty(stringList)) {
                String[] toArray = stringList.toArray(new String[stringList.size()]);
                String add = ArithmeticUtils.add(toArray);
                bigDecimal = ArithmeticUtils.createBigDecimal(ArithmeticUtils.round(ArithmeticUtils.createBigDecimal(add), 5));
            }
        }
        return bigDecimal;
    }

    /**
     * 过滤形成  方法可用数据
     *
     * @param levelDetailId
     * @param projectId
     * @param dataTableId
     * @param dataTableName
     * @return
     */
    public List<List<ProjectLandAchievementGroupWithBLOBs>> initProjectLandAchievementGroup(Integer levelDetailId, Integer projectId, Integer dataTableId, String dataTableName) {
        List<DataLandLevelDetailAchievement> dataLandLevelDetailAchievementVoList = null;
        List<List<DataLandLevelDetailAchievementVo>> listList = null;
        List<ProjectLandAchievementGroupWithBLOBs> groupWithBLOBsList = null;
        groupWithBLOBsList = projectLandAchievementGroupDao.getProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId(projectId, dataTableId, dataTableName);
        if (CollectionUtils.isNotEmpty(groupWithBLOBsList)) {
            return handleGrouping(groupWithBLOBsList);
        }
        if (levelDetailId != null) {
            dataLandLevelDetailAchievementVoList = dataLandLevelDetailAchievementService.getAchievementsByLandLevelDetailId(levelDetailId);
        }
        if (CollectionUtils.isNotEmpty(dataLandLevelDetailAchievementVoList)) {
            listList = dataLandLevelDetailAchievementService.landFirstLevelFilter(dataLandLevelDetailAchievementVoList.stream().map(po -> dataLandLevelDetailAchievementService.getDataLandLevelDetailAchievementVo(po)).collect(Collectors.toList()));
        }
        if (CollectionUtils.isNotEmpty(listList)) {
            groupWithBLOBsList = initProjectLandAchievementGroup(projectId, dataTableId, dataTableName, listList);
        }
        return handleGrouping(groupWithBLOBsList);
    }


    /**
     * 土地因素 过滤形成 可以使用的数据
     * @param levelDetailId
     * @param projectId
     * @param dataTableId
     * @param dataTableName
     * @param targetTableId
     * @param targetTableName
     * @return
     */
    public List<List<ProjectLandAchievementGroupWithBLOBs>> initProjectLandAchievement(Integer levelDetailId, Integer projectId, Integer dataTableId, String dataTableName, Integer targetTableId, String targetTableName) {
        List<DataLandLevelDetailAchievement> dataLandLevelDetailAchievementVoList = null;
        List<List<DataLandLevelDetailAchievementVo>> listList = null;
        List<ProjectLandAchievementGroupWithBLOBs> groupWithBLOBsList = null;
        groupWithBLOBsList = projectLandAchievementGroupDao.getProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId(projectId, dataTableId, dataTableName);
        if (CollectionUtils.isEmpty(groupWithBLOBsList)) {
            if (levelDetailId != null) {
                dataLandLevelDetailAchievementVoList = dataLandLevelDetailAchievementService.getAchievementsByLandLevelDetailId(levelDetailId);
            }
            if (CollectionUtils.isNotEmpty(dataLandLevelDetailAchievementVoList)) {
                listList = dataLandLevelDetailAchievementService.landFirstLevelFilter(dataLandLevelDetailAchievementVoList.stream().map(po -> dataLandLevelDetailAchievementService.getDataLandLevelDetailAchievementVo(po)).collect(Collectors.toList()));
            }
            if (CollectionUtils.isNotEmpty(listList)) {
                groupWithBLOBsList = initProjectLandAchievementGroup(projectId, dataTableId, dataTableName, listList);
            }
        }
        //将 查勘和土地因素的数据转移至 测算方法中使用
        if (targetTableId != null && StringUtils.isNotBlank(targetTableName) && CollectionUtils.isNotEmpty(groupWithBLOBsList)) {
            List<ProjectLandAchievementGroupWithBLOBs> groupWithBLOBs = new ArrayList<>(groupWithBLOBsList.size());
            for (ProjectLandAchievementGroupWithBLOBs obj:groupWithBLOBsList){
                obj.setDataTableName(targetTableName);
                obj.setDataTableId(targetTableId);
                obj.setId(null);
                groupWithBLOBs.add(obj) ;
            }
            batchInsert(groupWithBLOBs);
            groupWithBLOBsList = groupWithBLOBs ;
        }
        return handleGrouping(groupWithBLOBsList);
    }

    private List<List<ProjectLandAchievementGroupWithBLOBs>> handleGrouping(List<ProjectLandAchievementGroupWithBLOBs> groupWithBLOBsList) {
        Map<String, List<ProjectLandAchievementGroupWithBLOBs>> map = new HashMap<>(2);
        List<List<ProjectLandAchievementGroupWithBLOBs>> lists = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(groupWithBLOBsList)) {
            for (ProjectLandAchievementGroupWithBLOBs group : groupWithBLOBsList) {
                if (StringUtils.isBlank(group.getType())) {
                    continue;
                }
                if (map.containsKey(group.getType())) {
                    map.get(group.getType()).add(group);
                } else {
                    map.put(group.getType(), Lists.newArrayList(group));
                }
            }
        }
        if (!map.isEmpty()) {
            Iterator<Map.Entry<String, List<ProjectLandAchievementGroupWithBLOBs>>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<ProjectLandAchievementGroupWithBLOBs>> entry = iterator.next();
                lists.add(entry.getValue());
            }
        }
        return lists;
    }

    private List<ProjectLandAchievementGroupWithBLOBs> initProjectLandAchievementGroup(Integer projectId, Integer dataTableId, String dataTableName, List<List<DataLandLevelDetailAchievementVo>> lists) {
        List<ProjectLandAchievementGroupWithBLOBs> groupWithBLOBsList = new ArrayList<>();
        if (CollectionUtils.isEmpty(lists)) {
            return groupWithBLOBsList;
        }
        groupWithBLOBsList = new ArrayList<>(lists.size());
        ProjectLandAchievementGroupWithBLOBs target = null;
        String userAccount = commonService.thisUserAccount();
        Date now = DateUtils.now();
        Iterator<List<DataLandLevelDetailAchievementVo>> iterator = lists.iterator();
        while (iterator.hasNext()) {
            List<DataLandLevelDetailAchievementVo> detailAchievementVoList = iterator.next();
            target = new ProjectLandAchievementGroupWithBLOBs();
            target.setAchievementIds(StringUtils.join(LangUtils.transform(detailAchievementVoList, obj -> obj.getId()), ","));
            target.setType(detailAchievementVoList.get(0).getTypeName());
            target.setCategory(detailAchievementVoList.get(0).getCategoryName());
            target.setClassification(detailAchievementVoList.get(0).getClassification());
            List<KeyValueVo> keyValueVoArrayList = new ArrayList<>(detailAchievementVoList.size());
            List<String> stringList = new ArrayList<>(detailAchievementVoList.size());
            for (DataLandLevelDetailAchievementVo achievementVo : detailAchievementVoList) {
                if (StringUtils.isBlank(achievementVo.getGradeName())) {
                    continue;
                }
                stringList.add(String.join("", "等级:", achievementVo.getGradeName(), "，说明:", achievementVo.getReamark()));
                if (achievementVo.getAchievement() == null) {
                    continue;
                }
                keyValueVoArrayList.add(new KeyValueVo(achievementVo.getGradeName(), ArithmeticUtils.getPercentileSystem(achievementVo.getAchievement(), 2), achievementVo.getAchievement(), achievementVo.getId()));
            }
            if (CollectionUtils.isNotEmpty(stringList)) {
                target.setRemark(StringUtils.join(stringList, "\r"));
            }
            if (CollectionUtils.isNotEmpty(keyValueVoArrayList)) {
                String jsonString = JSONObject.toJSONString(keyValueVoArrayList);
                target.setKeyValue(jsonString);
            }
            target.setCreator(userAccount);
            target.setGmtCreated(now);
            target.setGmtModified(now);
            target.setProjectId(projectId);
            target.setDataTableId(dataTableId);
            target.setDataTableName(dataTableName);
            groupWithBLOBsList.add(target);
        }
        if (CollectionUtils.isNotEmpty(groupWithBLOBsList)) {
            batchInsert(groupWithBLOBsList);
        }
        return groupWithBLOBsList;
    }


    public void insertSelective(ProjectLandAchievementGroupWithBLOBs obj) {
        if (StringUtils.isBlank(obj.getCreator())) {
            obj.setCreator(commonService.thisUserAccount());
        }
        projectLandAchievementGroupDao.insertSelective(obj);
    }

    public void updateByPrimaryKeyWithBLOBs(ProjectLandAchievementGroupWithBLOBs obj) {
        projectLandAchievementGroupDao.updateByPrimaryKeyWithBLOBs(obj);
    }

    public void updateByPrimaryKeySelective(ProjectLandAchievementGroupWithBLOBs obj) {
        projectLandAchievementGroupDao.updateByPrimaryKeySelective(obj);
    }

    public void batchInsert(List<ProjectLandAchievementGroupWithBLOBs> list) {
        projectLandAchievementGroupDao.batchInsert(list);
    }

    public ProjectLandAchievementGroupWithBLOBs getProjectLandAchievementGroupWithBLOBsById(Integer id) {
        return projectLandAchievementGroupDao.getProjectLandAchievementGroupWithBLOBsById(id);
    }

    public void deleteProjectLandAchievementGroupByProjectId(Integer projectId) {
        projectLandAchievementGroupDao.deleteProjectLandAchievementGroupByProjectId(projectId);
    }

    public void deleteProjectLandAchievementGroupByDataTableIdAndDataTableName(Integer dataTableId, String dataTableName) {
        projectLandAchievementGroupDao.deleteProjectLandAchievementGroupByDataTableIdAndDataTableName(dataTableId, dataTableName);
    }

    public List<ProjectLandAchievementGroupWithBLOBs> getProjectLandAchievementGroupByDataTableIdAndDataTableName(Integer dataTableId, String dataTableName) {
        return projectLandAchievementGroupDao.getProjectLandAchievementGroupByDataTableIdAndDataTableName(dataTableId, dataTableName);
    }

    public List<ProjectLandAchievementGroupWithBLOBs> getProjectLandAchievementGroupByProjectId(Integer projectId) {
        return projectLandAchievementGroupDao.getProjectLandAchievementGroupByProjectId(projectId);
    }

    public List<ProjectLandAchievementGroupWithBLOBs> getProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId(Integer projectId, Integer dataTableId, String dataTableName) {
        return projectLandAchievementGroupDao.getProjectLandAchievementGroupByDataTableIdAndDataTableNameAndProjectId(projectId, dataTableId, dataTableName);
    }

    public void deleteProjectLandAchievementGroupByIds(List<Integer> ids) {
        projectLandAchievementGroupDao.deleteProjectLandAchievementGroupByIds(ids);
    }

}
