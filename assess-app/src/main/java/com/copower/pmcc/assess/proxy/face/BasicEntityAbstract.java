package com.copower.pmcc.assess.proxy.face;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dto.input.basic.BasicFormClassifyParamDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by wangpc on 2020/2/18.
 */
public abstract class BasicEntityAbstract {

    /**
     * 获取属性值
     *
     * @param obj
     * @param propertyName
     * @return
     */
    public Object getProperty(Object obj, String propertyName) {
        try {
            if (obj == null || StringUtils.isBlank(propertyName)) return null;
            Class clazz = obj.getClass();//获取字节码对象
            Field f = clazz.getDeclaredField(propertyName);
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 为属性设置通用方法
     *
     * @param obj
     * @param propertyName
     * @param value
     */
    public void setProperty(Object obj, String propertyName, Object value) {
        try {
            if (obj == null || StringUtils.isBlank(propertyName)) return;
            Class clazz = obj.getClass();//获取字节码对象
            Field f = clazz.getDeclaredField(propertyName);
            if (f == null) return;
            f.setAccessible(true);
            f.set(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract Integer saveAndUpdate(Object o, Boolean updateNull);//新增或保存

    public abstract Integer saveAndUpdateByFormData(String formData, Integer planDetailsId) throws Exception;//新增或保存(表单数据)

    public abstract Object getBasicEntityById(Integer id);//获取实体by id

    public abstract void clearInvalidData(Integer id) throws Exception;

    public abstract void clearInvalidChildData(Integer id) throws Exception;

    public abstract Object copyBasicEntity(Integer sourceId, Integer targetId, Boolean containChild) throws Exception;//数据拷贝

    public abstract Object copyBasicEntityIgnore(Integer sourceId, Integer targetId, Boolean containChild, List<String> ignoreList) throws Exception;//数据拷贝

    public abstract List<BasicFormClassifyEnum> getLowerFormClassifyList();//获取下级表单模板

    public abstract ModelAndView getEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception;

    public abstract ModelAndView getPhoneEditModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception;

    public abstract ModelAndView getDetailModelAndView(BasicFormClassifyParamDto basicFormClassifyParamDto) throws Exception;
}
