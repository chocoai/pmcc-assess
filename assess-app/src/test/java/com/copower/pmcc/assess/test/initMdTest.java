package com.copower.pmcc.assess.test;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.enums.MarketCompareObjectTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.method.MdMarketCompareDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdMarketCompareFieldDao;
import com.copower.pmcc.assess.dal.basis.dao.method.MdMarketCompareItemDao;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompare;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareField;
import com.copower.pmcc.assess.dal.basis.entity.MdMarketCompareItem;
import com.copower.pmcc.assess.dto.input.method.MarketCompareItemDto;
import com.google.common.collect.Lists;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-7-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContextTest.xml"})
public class initMdTest {
    @Autowired
    private MdMarketCompareDao mdMarketCompareDao;
    @Autowired
    private MdMarketCompareItemDao mdMarketCompareItemDao;
    @Autowired
    private MdMarketCompareFieldDao mdMarketCompareFieldDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @org.junit.Test
    public void synchronize() {
        String sql = getSynchronizeSql("tb_examine_block", 9371, 9372);
        System.out.print(sql);
    }

    private String getSynchronizeSql(String tableName, Integer oldPlanDetailsId, Integer newPlanDetailsId) {
        String sql = String.format("select column_name from information_schema.columns where table_name='%s' and table_schema='pmcc_assess'", tableName);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        //1.去除id 评出sql前半截  拼出sql后半截
        for (Map<String, Object> map : mapList) {
            if (map.get("column_name").equals("id")) {
                mapList.remove(map);break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map<String, Object> map : mapList) {
            stringBuilder.append(map.get("column_name")).append(",");
        }
        String columnString = stringBuilder.toString();
        columnString = columnString.replaceAll(",$", "");

        String resultString = MessageFormat.format("INSERT into {0}({1}) SELECT %s FROM {0} where plan_details_id={2}", tableName, columnString, String.valueOf(oldPlanDetailsId));
        resultString = String.format(resultString, columnString.replace("plan_details_id", String.valueOf(newPlanDetailsId)));
        return resultString;
    }


    //初始化测试所需要的数据
    @org.junit.Test
    public void init() {
        MdMarketCompare mdMarketCompare = new MdMarketCompare();
        mdMarketCompare.setName("1号委估对象");
        mdMarketCompare.setCreator("admin");
        mdMarketCompareDao.addMarketCompare(mdMarketCompare);

        for (MdMarketCompareField mdMarketCompareField : getFieldList()) {
            mdMarketCompareField.setMcId(mdMarketCompare.getId());
            mdMarketCompareFieldDao.addMarketCompareField(mdMarketCompareField);
        }

        MdMarketCompareItem evaluationObject = getEvaluationObject();
        evaluationObject.setMcId(mdMarketCompare.getId());
        mdMarketCompareItemDao.addMarketCompareItem(evaluationObject);

        for (MdMarketCompareItem mdMarketCompareItem : getCaseList()) {
            mdMarketCompareItem.setMcId(mdMarketCompare.getId());
            mdMarketCompareItemDao.addMarketCompareItem(mdMarketCompareItem);
        }
    }

    //获取所有关联字段信息
    private List<MdMarketCompareField> getFieldList() {
        List<MdMarketCompareField> list = Lists.newArrayList();
        MdMarketCompareField mdMarketCompareField = new MdMarketCompareField();
        mdMarketCompareField.setName("楼盘名称");
        mdMarketCompareField.setValue("楼盘名称");
        mdMarketCompareField.setBisPrimaryKey(true);
        mdMarketCompareField.setBisOnlyView(true);
        list.add(mdMarketCompareField);

        mdMarketCompareField = new MdMarketCompareField();
        mdMarketCompareField.setName("交易情况");
        mdMarketCompareField.setValue("交易情况");
        list.add(mdMarketCompareField);

        mdMarketCompareField = new MdMarketCompareField();
        mdMarketCompareField.setName("付款方式");
        mdMarketCompareField.setValue("付款方式");
        list.add(mdMarketCompareField);

        mdMarketCompareField = new MdMarketCompareField();
        mdMarketCompareField.setName("房地产坐落及方位");
        mdMarketCompareField.setValue("房地产坐落及方位");
        list.add(mdMarketCompareField);

        mdMarketCompareField = new MdMarketCompareField();
        mdMarketCompareField.setName("交通条件");
        mdMarketCompareField.setValue("交通条件");
        list.add(mdMarketCompareField);
        return list;
    }

    //获取估价对象信息
    private MdMarketCompareItem getEvaluationObject() {
        MdMarketCompareItem mdMarketCompareItem = new MdMarketCompareItem();

        List<MarketCompareItemDto> list = Lists.newArrayList();
        MarketCompareItemDto marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("楼盘名称");
        marketCompareItemDto.setValue("香瑞福");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("交易情况");
        marketCompareItemDto.setValue("正常");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("付款方式");
        marketCompareItemDto.setValue("一次性付款");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("房地产坐落及方位");
        marketCompareItemDto.setValue("东大街西北角");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("交通条件");
        marketCompareItemDto.setValue("良");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        mdMarketCompareItem.setType(MarketCompareObjectTypeEnum.EVALUATION.getId());
        mdMarketCompareItem.setName("香瑞福");
        mdMarketCompareItem.setJsonContent(JSON.toJSONString(list));
        mdMarketCompareItem.setCreator("admin");
        return mdMarketCompareItem;
    }

    //获取案例信息
    private List<MdMarketCompareItem> getCaseList() {
        List<MdMarketCompareItem> itemList = Lists.newArrayList();

        //案例1
        MdMarketCompareItem mdMarketCompareItem = new MdMarketCompareItem();
        List<MarketCompareItemDto> list = Lists.newArrayList();
        MarketCompareItemDto marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("楼盘名称");
        marketCompareItemDto.setValue("彩叠园");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("交易情况");
        marketCompareItemDto.setValue("正常");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("付款方式");
        marketCompareItemDto.setValue("分期付款");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("房地产坐落及方位");
        marketCompareItemDto.setValue("正南街偏东");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("交通条件");
        marketCompareItemDto.setValue("优");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        mdMarketCompareItem.setType(MarketCompareObjectTypeEnum.CASE.getId());
        mdMarketCompareItem.setName("彩叠园");
        mdMarketCompareItem.setJsonContent(JSON.toJSONString(list));
        mdMarketCompareItem.setCreator("admin");
        itemList.add(mdMarketCompareItem);

        //案例2
        mdMarketCompareItem = new MdMarketCompareItem();
        list = Lists.newArrayList();
        marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("楼盘名称");
        marketCompareItemDto.setValue("合能琥珀");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("交易情况");
        marketCompareItemDto.setValue("正常");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("付款方式");
        marketCompareItemDto.setValue("一次性付款");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("房地产坐落及方位");
        marketCompareItemDto.setValue("东坡路南门");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        marketCompareItemDto = new MarketCompareItemDto();
        marketCompareItemDto.setName("交通条件");
        marketCompareItemDto.setValue("差");
        marketCompareItemDto.setScore(100);
        marketCompareItemDto.setRatio(new BigDecimal("1"));
        list.add(marketCompareItemDto);

        mdMarketCompareItem.setType(MarketCompareObjectTypeEnum.CASE.getId());
        mdMarketCompareItem.setName("合能琥珀");
        mdMarketCompareItem.setJsonContent(JSON.toJSONString(list));
        mdMarketCompareItem.setCreator("admin");

        itemList.add(mdMarketCompareItem);
        return itemList;
    }

    @org.junit.Test
    public void initTask() {

    }
}
