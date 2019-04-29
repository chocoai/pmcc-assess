package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.cases.entity.*;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/4/28 16:45
 * @description:自动添加字段 ()
 */
public class BasicReflexDemo implements Serializable {

    /**
     * 生成数据库sql
     * @param basicReflexClassList
     */
    private void print(List<BasicReflexClass> basicReflexClassList) {
        //目的是生成类似于alter table tb_case_house_trading add scope_include varchar(255); 这样的sql语句串
        final String prefix = "tb_case";
        if (CollectionUtils.isEmpty(basicReflexClassList)) {
            return;
        }
        final StringBuilder stringBuilder = new StringBuilder(8);
        List<BasicReflexEnum> values = Arrays.asList(BasicReflexEnum.values());
        List<String> filters = Arrays.asList("apply_id");
        List<String> stringList = Lists.newArrayList();
        List<String> clearList = Lists.newArrayList();
        for (BasicReflexClass basicReflexClass : basicReflexClassList) {
            Map<String, Class> basicReflexClassTypeString = basicReflexClass.getTypeString();
            if (basicReflexClassTypeString.isEmpty()) {
                continue;
            }
            if (!StringUtils.startsWith(basicReflexClass.getTableName(), prefix)) {
                continue;
            }
            for (Map.Entry<String, Class> entry : basicReflexClassTypeString.entrySet()) {
                if (!values.stream().anyMatch(basicReflexEnum -> Objects.equal(entry.getValue(), basicReflexEnum.getJavaType()))) {
                    continue;
                }
                if (filters.stream().anyMatch(s -> Objects.equal(FormatUtils.camelToUnderline(entry.getKey()),s))){
                    clearList.add(String.format("%s-%s-%s",basicReflexClass.getSourceClass(),entry.getKey(),entry.getValue().getSimpleName()));
                    continue;
                }
                BasicReflexEnum reflexEnum = values.stream().filter(basicReflexEnum -> Objects.equal(entry.getValue(), basicReflexEnum.getJavaType())).findFirst().get();

                stringBuilder.append("alter table").append(" ").append(basicReflexClass.getTableName()).append(" ").append("add").append(" ").append(FormatUtils.camelToUnderline(entry.getKey())).append(" ").append(reflexEnum.getJdbcType()).append(";").append(" ");
            }
            stringList.add(stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.toString().length());
        }
        if (CollectionUtils.isNotEmpty(stringList)) {
            stringList.stream().forEach(s -> System.out.println(s));
        }
        if (CollectionUtils.isNotEmpty(clearList)){
            System.out.println("未匹配的字段");
            clearList.stream().forEach(s -> System.out.println(s));
        }
    }

    @Test
    public void estateTest()throws Exception{
        LinkedHashMap<Class, Class> classClassMap = Maps.newLinkedHashMap();
        List<BasicReflexClass> basicReflexClassList = Lists.newArrayList();
        classClassMap.put(BasicEstate.class, CaseEstate.class);
        classClassMap.put(BasicEstateLandState.class, CaseEstateLandState.class);
        classClassMap.put(BasicEstateTagging.class, CaseEstateTagging.class);
        classClassMap.put(BasicEstateParking.class, CaseEstateParking.class);
        classClassMap.put(BasicEstateNetwork.class, CaseEstateNetwork.class);
        classClassMap.put(BasicEstateSupply.class, CaseEstateSupply.class);
        classClassMap.put(BasicMatchingTraffic.class, CaseMatchingTraffic.class);
        classClassMap.put(BasicMatchingMedical.class, CaseMatchingMedical.class);
        classClassMap.put(BasicMatchingMaterial.class, CaseMatchingMaterial.class);
        classClassMap.put(BasicMatchingLeisurePlace.class, CaseMatchingLeisurePlace.class);
        classClassMap.put(BasicMatchingFinance.class, CaseMatchingFinance.class);
        classClassMap.put(BasicMatchingEnvironment.class, CaseMatchingEnvironment.class);
        classClassMap.put(BasicMatchingEducation.class, CaseMatchingEducation.class);

        if (!classClassMap.isEmpty()) {
            classClassMap.entrySet().stream().forEach(classClassEntry -> put(basicReflexClassList, getBasicReflexClass(classClassEntry.getKey(), classClassEntry.getValue())));
        }
        if (CollectionUtils.isNotEmpty(basicReflexClassList)) {
            System.out.println("初始类数量:"+classClassMap.size());
            print(basicReflexClassList);
        }
    }

    @Test
    public void unitTest()throws Exception{
        LinkedHashMap<Class, Class> classClassMap = Maps.newLinkedHashMap();
        List<BasicReflexClass> basicReflexClassList = Lists.newArrayList();

        classClassMap.put(BasicUnit.class, CaseUnit.class);
        classClassMap.put(BasicUnitHuxing.class, CaseUnitHuxing.class);
        classClassMap.put(BasicUnitDecorate.class, CaseUnitDecorate.class);
        classClassMap.put(BasicUnitElevator.class, CaseUnitElevator.class);

        if (!classClassMap.isEmpty()) {
            classClassMap.entrySet().stream().forEach(classClassEntry -> put(basicReflexClassList, getBasicReflexClass(classClassEntry.getKey(), classClassEntry.getValue())));
        }
        if (CollectionUtils.isNotEmpty(basicReflexClassList)) {
            System.out.println("初始类数量:"+classClassMap.size());
            print(basicReflexClassList);
        }
    }

    @Test
    public void buildTest()throws Exception{
        LinkedHashMap<Class, Class> classClassMap = Maps.newLinkedHashMap();
        List<BasicReflexClass> basicReflexClassList = Lists.newArrayList();
        classClassMap.put(BasicBuilding.class, CaseBuilding.class);
        classClassMap.put(BasicBuildingOutfit.class, CaseBuildingOutfit.class);
        classClassMap.put(BasicBuildingMaintenance.class, CaseBuildingMaintenance.class);
        classClassMap.put(BasicBuildingSurface.class, CaseBuildingSurface.class);
        classClassMap.put(BasicBuildingFunction.class, CaseBuildingFunction.class);
        if (!classClassMap.isEmpty()) {
            classClassMap.entrySet().stream().forEach(classClassEntry -> put(basicReflexClassList, getBasicReflexClass(classClassEntry.getKey(), classClassEntry.getValue())));
        }
        if (CollectionUtils.isNotEmpty(basicReflexClassList)) {
            System.out.println("初始类数量:"+classClassMap.size());
            print(basicReflexClassList);
        }
    }

    @Test
    public void houseTest() throws Exception {
        LinkedHashMap<Class, Class> classClassMap = Maps.newLinkedHashMap();
        List<BasicReflexClass> basicReflexClassList = Lists.newArrayList();

        classClassMap.put(BasicHouse.class, CaseHouse.class);
        classClassMap.put(BasicHouseTrading.class, CaseHouseTrading.class);
        classClassMap.put(BasicHouseTradingSell.class, CaseHouseTradingSell.class);
        classClassMap.put(BasicHouseTradingLease.class, CaseHouseTradingLease.class);
        classClassMap.put(BasicHouseWater.class, CaseHouseWater.class);
        classClassMap.put(BasicHouseWaterDrain.class, CaseHouseWaterDrain.class);
        classClassMap.put(BasicHouseIntelligent.class, CaseHouseIntelligent.class);
        classClassMap.put(BasicHouseFaceStreet.class, CaseHouseFaceStreet.class);
        classClassMap.put(BasicHouseEquipment.class, CaseHouseEquipment.class);
        classClassMap.put(BasicHouseCorollaryEquipment.class, CaseHouseCorollaryEquipment.class);
        classClassMap.put(BasicHouseRoomDecorate.class, CaseHouseRoomDecorate.class);
        classClassMap.put(BasicHouseRoom.class, CaseHouseRoom.class);
        classClassMap.put(BasicHouseDamagedDegree.class, CaseHouseDamagedDegree.class);
        classClassMap.put(BasicHouseDamagedDegreeDetail.class, CaseHouseDamagedDegreeDetail.class);

        if (!classClassMap.isEmpty()) {
            classClassMap.entrySet().stream().forEach(classClassEntry -> put(basicReflexClassList, getBasicReflexClass(classClassEntry.getKey(), classClassEntry.getValue())));
        }
        if (CollectionUtils.isNotEmpty(basicReflexClassList)) {
            System.out.println("初始类数量:"+classClassMap.size());
            print(basicReflexClassList);
        }
    }


    private BasicReflexClass getBasicReflexClass(Class a, Class b) {
        BasicReflexClass basicReflexClass = null;
        if (a == null || b == null) {
            return basicReflexClass;
        }
        List<Field> fieldAs = Arrays.asList(a.getDeclaredFields());
        List<Field> fieldBs = Arrays.asList(b.getDeclaredFields());
        final Collection<Field> fieldCollection = Lists.newArrayList();
        if (fieldAs.size() == fieldBs.size()) {
            return basicReflexClass;
        }
        if (fieldAs.size() > fieldBs.size()) {
            basicReflexClass = new BasicReflexClass(FormatUtils.entityNameConvertToTableName(b), b);
            List<String> stringList = Lists.newArrayList(CollectionUtils.subtract(fieldAs.stream().map(field -> field.getName()).collect(Collectors.toList()), fieldBs.stream().map(field -> field.getName()).collect(Collectors.toList())));
            stringList.stream().forEach(s -> {
                if (fieldAs.stream().anyMatch(field1 -> Objects.equal(field1.getName(), s))) {
                    fieldCollection.add(fieldAs.stream().filter(field1 -> Objects.equal(field1.getName(), s)).findFirst().get());
                }
            });
        }
        if (fieldAs.size() < fieldBs.size()) {
            basicReflexClass = new BasicReflexClass(FormatUtils.entityNameConvertToTableName(a), a);
            List<String> stringList = Lists.newArrayList(CollectionUtils.subtract(fieldBs.stream().map(field -> field.getName()).collect(Collectors.toList()), fieldAs.stream().map(field -> field.getName()).collect(Collectors.toList())));
            stringList.stream().forEach(s -> {
                if (fieldBs.stream().anyMatch(field1 -> Objects.equal(field1.getName(), s))) {
                    fieldCollection.add(fieldBs.stream().filter(field1 -> Objects.equal(field1.getName(), s)).findFirst().get());
                }
            });
        }
        if (CollectionUtils.isNotEmpty(fieldCollection)) {
            Map<String, Class> typeString = Maps.newHashMap();
            fieldCollection.stream().forEach(field -> {
                typeString.put(field.getName(), field.getType());
            });
            basicReflexClass.setTypeString(typeString);
        }
        return basicReflexClass;
    }

    private void put(List<BasicReflexClass> basicReflexClassList, BasicReflexClass basicReflexClass) {
        if (basicReflexClass != null) {
            if (basicReflexClassList != null) {
                basicReflexClassList.add(basicReflexClass);
            }
        }
    }


}
