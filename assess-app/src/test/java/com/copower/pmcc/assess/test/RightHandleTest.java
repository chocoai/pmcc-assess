package com.copower.pmcc.assess.test;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetRightDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetRightDeclareDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetRightGroupDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetRightItemDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zch on 2019-12-19.
 * 他项权力解决老数据办法
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContextTest.xml"})
public class RightHandleTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SurveyAssetRightGroupDao surveyAssetRightGroupDao;
    @Autowired
    private SurveyAssetRightItemDao surveyAssetRightItemDao;
    @Autowired
    private SurveyAssetRightDeclareDao surveyAssetRightDeclareDao;
    @Autowired
    private DeclareRecordDao declareRecordDao;
    @Autowired
    private SurveyAssetRightDao surveyAssetRightDao;

    public static String creator = "admin";

    @org.junit.Test
    public void run() {
        List<SurveyAssetRight> surveyAssetRightList = getSurveyAssetRightList();
        if (CollectionUtils.isEmpty(surveyAssetRightList)) {
            return;
        }
        Iterator<SurveyAssetRight> iterator = surveyAssetRightList.iterator();
        while (iterator.hasNext()) {
            SurveyAssetRight assetRight = iterator.next();
            Integer masterId = writeSurveyAssetRight(assetRight);
            if (masterId == null) {
                continue;
            }
            List<SurveyAssetRightGroupVo> surveyAssetRightGroupVoList = getSurveyAssetRightGroupListByExample(assetRight);
            if (CollectionUtils.isEmpty(surveyAssetRightGroupVoList)) {
                continue;
            }
            Iterator<SurveyAssetRightGroupVo> surveyAssetRightGroupVoIterator = surveyAssetRightGroupVoList.iterator();
            while (surveyAssetRightGroupVoIterator.hasNext()) {
                SurveyAssetRightGroupVo groupVo = surveyAssetRightGroupVoIterator.next();
                Integer groupId = writeSurveyAssetRightGroupVo(groupVo, masterId);
                if (groupId == null) {
                    continue;
                }
                List<SurveyAssetRightItemVo> rightItemVoList = getRightItemList(groupVo);
                if (CollectionUtils.isEmpty(rightItemVoList)) {
                    continue;
                }
                Iterator<SurveyAssetRightItemVo> itemVoIterator = rightItemVoList.iterator();
                while (itemVoIterator.hasNext()) {
                    SurveyAssetRightItemVo itemVo = itemVoIterator.next();
                    writeSurveyAssetRightItemVo(itemVo, groupId);
                }
            }
        }
    }

    private void writeSurveyAssetRightItemVo(SurveyAssetRightItemVo itemVo, Integer groupId) {
        if (itemVo == null) {
            return;
        }
        if (groupId == null) {
            return;
        }
        SurveyAssetRightItemVo target = new SurveyAssetRightItemVo();
        org.springframework.beans.BeanUtils.copyProperties(itemVo, target);
        target.setId(null);
        target.setGroupId(groupId);
        if (StringUtils.isEmpty(target.getCreator())) {
            target.setCreator(creator);
        }
        surveyAssetRightItemDao.saveSurveyAssetRightItem(target);
    }

    private Integer writeSurveyAssetRightGroupVo(SurveyAssetRightGroupVo groupVo, Integer masterId) {
        if (groupVo == null) {
            return null;
        }
        if (masterId == null) {
            return null;
        }
        SurveyAssetRightGroupVo target = new SurveyAssetRightGroupVo();
        org.springframework.beans.BeanUtils.copyProperties(groupVo, target);
        target.setId(null);
        target.setMasterId(masterId);
        if (StringUtils.isEmpty(target.getCreator())) {
            target.setCreator(creator);
        }
        surveyAssetRightGroupDao.saveSurveyAssetRightGroup(target);
        if (StringUtils.isNotEmpty(groupVo.getRecordIds())) {
            List<Integer> integerList = FormatUtils.transformString2Integer(groupVo.getRecordIds());
            if (CollectionUtils.isNotEmpty(integerList)) {
                List<DeclareRecord> declareRecordList = declareRecordDao.getDeclareRecordListByIds(integerList);
                if (CollectionUtils.isNotEmpty(declareRecordList)) {
                    Iterator<DeclareRecord> declareRecordIterator = declareRecordList.iterator();
                    while (declareRecordIterator.hasNext()) {
                        DeclareRecord declareRecord = declareRecordIterator.next();
                        SurveyAssetRightDeclare declare = new SurveyAssetRightDeclare();
                        declare.setDeclareId(declareRecord.getId());
                        declare.setDeclareName(declareRecord.getName());
                        if (NumberUtils.isNumber(declareRecord.getBuildingNumber())) {
                            declare.setBuildingNumber(Integer.parseInt(declareRecord.getBuildingNumber()));
                        }
                        declare.setOwnership(declareRecord.getOwnership());
                        declare.setSeat(declareRecord.getSeat());
                        declare.setUnitNumber(declareRecord.getUnit());
                        declare.setGroupId(target.getId());
                        declare.setPlanDetailsId(target.getPlanDetailsId());
                        if (StringUtils.isEmpty(declare.getCreator())) {
                            declare.setCreator(creator);
                        }
                        surveyAssetRightDeclareDao.saveSurveyAssetRightDeclare(declare);
                    }
                }
            }
        }
        return target.getId();
    }

    private Integer writeSurveyAssetRight(SurveyAssetRight assetRight) {
        if (assetRight == null) {
            return null;
        }
        SurveyAssetRight target = new SurveyAssetRight();
        org.springframework.beans.BeanUtils.copyProperties(assetRight, target);
        target.setId(null);
        if (StringUtils.isEmpty(target.getCreator())) {
            target.setCreator(creator);
        }
        surveyAssetRightDao.saveSurveyAssetRight(target);
        return target.getId();
    }

    private List<SurveyAssetRightItemVo> getRightItemList(SurveyAssetRightGroupVo surveyAssetRightGroupVo) {
        String sql = "select * from tb_survey_asset_inventory_right where inventory_right_record_id = " + surveyAssetRightGroupVo.getId().toString();
        List<SurveyAssetRightItemVo> surveyAssetRightItemVoList = queryForList(sql, SurveyAssetRightItemVo.class);
        return surveyAssetRightItemVoList;
    }

    private List<SurveyAssetRight> getSurveyAssetRightList() {
        String centerSql = "select * from tb_survey_asset_inventory_right_record_center";
        List<SurveyAssetRight> surveyAssetRightList = queryForList(centerSql, SurveyAssetRight.class);

        return surveyAssetRightList;
    }

    private List<SurveyAssetRightGroupVo> getSurveyAssetRightGroupListByExample(SurveyAssetRight surveyAssetRight) {
        List<SurveyAssetRightGroupVo> surveyAssetRightGroupVoArrayList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("select * from tb_survey_asset_inventory_right_record where inventory_right_record_center_id = ");
        stringBuilder.append(surveyAssetRight.getId().toString());
        List<SurveyAssetRightGroupVo> surveyAssetRightList = queryForList(stringBuilder.toString(), SurveyAssetRightGroupVo.class);
        if (CollectionUtils.isNotEmpty(surveyAssetRightList)) {
            surveyAssetRightGroupVoArrayList.addAll(surveyAssetRightList);
        }
        return surveyAssetRightGroupVoArrayList;
    }

    public static class SurveyAssetRightItemVo extends SurveyAssetRightItem {
        private Integer inventoryRightRecordId;

        public Integer getInventoryRightRecordId() {
            return inventoryRightRecordId;
        }

        public void setInventoryRightRecordId(Integer inventoryRightRecordId) {
            this.inventoryRightRecordId = inventoryRightRecordId;
            setGroupId(inventoryRightRecordId);
        }
    }

    public static class SurveyAssetRightGroupVo extends SurveyAssetRightGroup {
        private Integer inventoryRightRecordCenterId;
        private String recordIds;

        public SurveyAssetRightGroupVo() {
        }

        public Integer getInventoryRightRecordCenterId() {
            return inventoryRightRecordCenterId;
        }

        public void setInventoryRightRecordCenterId(Integer inventoryRightRecordCenterId) {
            this.inventoryRightRecordCenterId = inventoryRightRecordCenterId;
        }

        public String getRecordIds() {
            return recordIds;
        }

        public void setRecordIds(String recordIds) {
            this.recordIds = recordIds;
        }
    }

    public <T> List<T> queryForList(String sql, Class<T> clazz, Object... params) {
        final List<T> result = new ArrayList<>();
        jdbcTemplate.query(sql, params, rs -> {
            try {
                // 字段名称
                List<String> columnNames = new ArrayList<>();
                ResultSetMetaData meta = rs.getMetaData();
                int num = meta.getColumnCount();
                for (int i = 0; i < num; i++) {
                    columnNames.add(meta.getColumnLabel(i + 1));
                }
                // 设置值
                do {
                    T obj = clazz.getConstructor().newInstance();
                    for (int i = 0; i < num; i++) {
                        // 获取值
                        Object value = rs.getObject(i + 1);
                        // table.column形式的字段去掉前缀table.
                        String columnName = resolveColumn(columnNames.get(i));
                        // 下划线转驼峰
                        String property = null;
                        try {
                            property = CamelCaseUtils.toCamelCase(columnName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (StringUtils.isNotEmpty(property)) {
                            if (value != null) {
                                // 复制值到属性，这是spring的工具类
                                BeanUtils.copyProperty(obj, property, value);
                            }
                        }
                    }
                    result.add(obj);
                } while (rs.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        if (CollectionUtils.isEmpty(result)) {
            return Collections.emptyList();
        }
        return result;
    }

    private String resolveColumn(String column) {
        final int notExistIndex = -1;
        int index = column.indexOf(".");
        if (index == notExistIndex) {
            return column;
        }
        return column.substring(index + 1);
    }

    public static class CamelCaseUtils {
        private static final char SEPARATOR = '_';

        private CamelCaseUtils() {
        }

        public static String toCamelCase(String input) {
            if (input == null) {
                return null;
            }
            input = input.toLowerCase();
            int length = input.length();

            StringBuilder sb = new StringBuilder(length);
            boolean upperCase = false;
            for (int i = 0; i < length; i++) {
                char c = input.charAt(i);
                if (c == SEPARATOR) {
                    upperCase = true;
                } else if (upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    sb.append(c);
                }
            }

            return sb.toString();
        }
    }

}
