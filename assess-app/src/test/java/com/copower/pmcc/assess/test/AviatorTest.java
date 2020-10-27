package com.copower.pmcc.assess.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Red
 * @Description //TODO
 * @Date 2020/10/14 15:06
 **/
public class AviatorTest {

    @Test
    public void test() {
        try {
            List<String> strs = Lists.newArrayList("w", "t", "c", "r");
            List<Map> maps = Lists.newArrayList();


            HashMap<String, Object> master = Maps.newHashMap();
            master.put("xmmc", "高级项目");


            HashMap<String, Object> sub = Maps.newHashMap();
            sub.put("type", 1);
            sub.put("spdw", "测试单位");
            sub.put("yzdw", "业主单位");

            maps.add(sub);
            master.put("dataList", maps);


            master.put("F001", sub);

            Expression expression = AviatorEvaluator.getInstance().compileScript("D:\\av.av");
            //AviatorEvaluator.importFunctions(ScriptTemplateExtensionFunctionUtils.class);


            List<String> variableFullNames = expression.getVariableFullNames();
            System.out.println(variableFullNames);

            String execute = (String) expression.execute(master);

            System.out.println(execute);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
