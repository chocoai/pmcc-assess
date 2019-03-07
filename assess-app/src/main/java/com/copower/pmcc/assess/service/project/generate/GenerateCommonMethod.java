package com.copower.pmcc.assess.service.project.generate;

import com.copower.pmcc.assess.dal.basis.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/3/7 16:32
 * @description:提取一些方法(报告生成)
 */
@Component
public class GenerateCommonMethod {

    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;

    public String getSchemeJudgeObjectShowName(SchemeJudgeObject schemeJudgeObject){
        StringBuilder stringBuilder = new StringBuilder(24);
        if (schemeJudgeObject == null) {
            return "";
        }
        if (StringUtils.isNotBlank(schemeJudgeObject.getNumber())) {
            String[] strings = schemeJudgeObject.getNumber().split(",");
            //显示委估对象最多3个
            final int max = 3;
            if (strings.length > 3) {
                //合并委估对象大于了3个以上的情况
                for (int i = 0; i < max; i++) {
                    stringBuilder.append(strings[i]);
                    if (i != max - 1) {
                        stringBuilder.append(",");
                    }
                }
            } else {
                stringBuilder.append(schemeJudgeObject.getNumber());
                //拆分情况
                if (schemeJudgeObject.getSplitNumber() != null) {
                    stringBuilder.append("-").append(schemeJudgeObject.getSplitNumber());
                }
            }
            stringBuilder.append("号");
            if (strings.length > 3) stringBuilder.append("等");
            stringBuilder.append("委估对象");
        }
        if (StringUtils.isEmpty(stringBuilder.toString())) {
            if (StringUtils.isNotBlank(schemeJudgeObject.getName())) {
                stringBuilder.append(schemeJudgeObject.getName());
            } else {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 获取合并的估价对象
     * @param schemeJudgeObjectsA
     * @return
     */
    public List<SchemeJudgeObject> getByRootAndChildSchemeJudgeObjectList(List<SchemeJudgeObject> schemeJudgeObjectsA){
        List<SchemeJudgeObject> schemeJudgeObjectList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(schemeJudgeObjectsA)) {
            for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectsA) {
                if (schemeJudgeObject.getBisMerge()) {
                    List<SchemeJudgeObject> schemeJudgeObjects = schemeJudgeObjectService.getChildrenJudgeObject(schemeJudgeObject.getId());
                    if (CollectionUtils.isNotEmpty(schemeJudgeObjects)) {
                        schemeJudgeObjectList.addAll(schemeJudgeObjects);
                    }
                } else {
                    schemeJudgeObjectList.add(schemeJudgeObject);
                }
            }
        }
        schemeJudgeObjectList = schemeJudgeObjectList.stream().filter(schemeJudgeObject -> schemeJudgeObject.getDeclareRecordId() != null).collect(Collectors.toList());
        return schemeJudgeObjectList;
    }


    /**
     * 获取重复的集合
     *
     * @param schemeJudgeObjectList
     * @return
     */
    public List<SchemeJudgeObject> unionSchemeJudgeObject(List<SchemeJudgeObject> schemeJudgeObjectList) {
        if (CollectionUtils.isEmpty(schemeJudgeObjectList)) {
            return new ArrayList<SchemeJudgeObject>(0);
        }
        StringBuilder builder = new StringBuilder(16);
        Map<String, SchemeJudgeObject> schemeJudgeObjectMap = Maps.newHashMap();
        //让重复的变成差集
        for (SchemeJudgeObject schemeJudgeObject : schemeJudgeObjectList) {
            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
            if (declareRecord != null) {
                builder.append(declareRecord.getStreetNumber()).append("-").append(declareRecord.getFloor()).append("-");
                builder.append(declareRecord.getUnit()).append("-").append(declareRecord.getBuildingNumber()).append("-");
                builder.append(declareRecord.getAttachedNumber());
                schemeJudgeObjectMap.put(builder.toString(), schemeJudgeObject);
                builder.delete(0, builder.toString().length());
            }
        }
        if (!schemeJudgeObjectMap.isEmpty()) {
            List<SchemeJudgeObject> judgeObjectList = Lists.newArrayList();
            for (Map.Entry<String, SchemeJudgeObject> objectEntry : schemeJudgeObjectMap.entrySet()) {
                judgeObjectList.add(objectEntry.getValue());
            }
            if (CollectionUtils.isNotEmpty(judgeObjectList) && schemeJudgeObjectList.size() > judgeObjectList.size()) {
                //得到差集
                Collection<SchemeJudgeObject> collection = CollectionUtils.subtract(schemeJudgeObjectList, judgeObjectList);
                if (CollectionUtils.isNotEmpty(collection)) {
                    //重要:得到差集对象  ==> 获取带有特征的估价对象
                    SchemeJudgeObject schemeJudgeObject = collection.stream().findFirst().get();
                    DeclareRecord target = declareRecordService.getDeclareRecordById(schemeJudgeObject.getDeclareRecordId());
                    if (target != null) {
                        List<SchemeJudgeObject> schemeJudgeObjects = Lists.newArrayList();
                        for (SchemeJudgeObject judgeObject : schemeJudgeObjectList) {
                            DeclareRecord declareRecord = declareRecordService.getDeclareRecordById(judgeObject.getDeclareRecordId());
                            if (declareRecord != null) {
                                boolean streetNumber = com.google.common.base.Objects.equal(target.getStreetNumber(), declareRecord.getStreetNumber());
                                boolean floor = com.google.common.base.Objects.equal(target.getFloor(), declareRecord.getFloor());
                                boolean unit = com.google.common.base.Objects.equal(target.getUnit(), declareRecord.getUnit());
                                boolean buildingNumber = com.google.common.base.Objects.equal(target.getBuildingNumber(), declareRecord.getBuildingNumber());
                                boolean attachedNumber = Objects.equal(target.getAttachedNumber(), declareRecord.getAttachedNumber());
                                if (streetNumber && floor && unit && buildingNumber && attachedNumber) {
                                    schemeJudgeObjects.add(judgeObject);
                                }
                            }
                        }
                        if (CollectionUtils.isNotEmpty(schemeJudgeObjects)) {
                            return schemeJudgeObjects;
                        }
                    }
                }
            }
        }
        return new ArrayList<SchemeJudgeObject>(0);
    }

    /**
     * 数字转换
     *
     * @param roomNumber
     * @return
     */
    public String convertNumber(List<Integer> roomNumber) {
        if (CollectionUtils.isNotEmpty(roomNumber)) {
            Collections.sort(roomNumber);
            Integer[] ints = new Integer[roomNumber.size()];
            for (int i = 0; i < roomNumber.size(); i++) {
                ints[i] = roomNumber.get(i);
            }
            String text = this.convert(ints, 0);
            text = text.substring(0, text.length() - 1);
            return text;
        }
        return " ";
    }

    /**
     * 获取连续的数字组合
     *
     * @param ints
     * @param index
     * @return
     */
    public String convert(Integer[] ints, int index) {
        int end = index;
        //结束条件，遍历完数组
        if (ints.length == index) {
            return "";
        } else {
            for (int i = index; i < ints.length; i++) {
                if (i < ints.length - 1) {
                    if (ints[i] + 1 == ints[i + 1]) {
                        end = i;
                    } else {
                        if (i > index)
                            end = end + 1;
                        break;
                    }
                } else {
                    if (end == ints.length - 2) {
                        end = ints.length - 1;
                        break;
                    }
                }
            }
            //相等说明不连续
            if (index == end)
                return ints[index] + "," + convert(ints, end + 1);
                //连续
            else
                return ints[index] + "-" + ints[end] + "," + convert(ints, end + 1);

        }
    }

}
