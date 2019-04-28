package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouse;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseTrading;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouse;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseTrading;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.google.common.base.Objects;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: zch
 * @date: 2019/4/28 16:45
 * @description:
 */
public class BasicReflexDemo {

    @Test
    public void houseTest() throws Exception {
        LinkedHashMap<Class, Class> classClassMap = Maps.newLinkedHashMap();
        LinkedHashMap<Class, List<String>> classListMap = Maps.newLinkedHashMap();
        List<BasicReflexClass> basicReflexClassList = Lists.newArrayList();

        classClassMap.put(BasicHouse.class, CaseHouse.class);
        classClassMap.put(BasicHouseTrading.class, CaseHouseTrading.class);

        if (!classClassMap.isEmpty()) {
            classClassMap.entrySet().stream().forEach(classClassEntry -> put(basicReflexClassList, getBasicReflexClass(classClassEntry.getKey(), classClassEntry.getValue())));
        }
        if (CollectionUtils.isNotEmpty(basicReflexClassList)) {
            basicReflexClassList.stream().forEach(basicReflexClass -> {
                System.out.println(basicReflexClass);
            });
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
