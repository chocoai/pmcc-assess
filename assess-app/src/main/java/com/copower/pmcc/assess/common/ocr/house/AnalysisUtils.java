package com.copower.pmcc.assess.common.ocr.house;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.DateHelp;
import com.copower.pmcc.assess.dto.input.ocr.RealtyRealEstateCertOcrDto;
import com.google.common.collect.Maps;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: zch
 * @Date: 2018/10/17 09:15
 * @Description:
 */
public class AnalysisUtils {

    /**
     *
     * 功能描述:
     *
     * @param: startPath 图片地址
     * @return: RealtyRealEstateCertOcrDto
     * @auther: zch
     * @date: 2018/10/17 11:32
     */
    public static RealtyRealEstateCertOcrDto parseRealtyEstateCert(String startPath)throws Exception{
        String formData = StartOcr.start(startPath);
        RealtyRealEstateCertOcrDto realtyRealEstateCertOcrDto = parseEstateCert(formData);
        return realtyRealEstateCertOcrDto;
    }

    /**
     * 功能描述: 不动产解析
     *
     * @return: RealtyRealEstateCertOcrDto
     * @auther: zch
     * @date: 2018/10/17 11:26
     */
    private static RealtyRealEstateCertOcrDto parseEstateCert(String formData) throws Exception {
        String temp = null;
        Map<String, String> keys = setEstateCertKey();
        RealtyRealEstateCertOcrDto certificateOcrDto = new RealtyRealEstateCertOcrDto();
        JSONObject jsonObject = JSON.parseObject(formData);
        JSONObject dataObj = JSON.parseObject(jsonObject.getString("data"));
        if (!StringUtils.isEmpty(jsonObject.getString(keys.get("sid")))) {
            certificateOcrDto.setSid(jsonObject.getString(keys.get("sid")));
        }
        if (jsonObject.getInteger(keys.get("angle")) != null) {
            certificateOcrDto.setAngle(jsonObject.getInteger(keys.get("angle")));
        }
        if (!StringUtils.isEmpty(dataObj.getString(keys.get("A1")))) {
            certificateOcrDto.setNumber(dataObj.getString(keys.get("A1")));
        }
        if (!StringUtils.isEmpty(dataObj.getString(keys.get("A2")))) {
            certificateOcrDto.setOwnership(dataObj.getString(keys.get("A2")));
        }
        if (!StringUtils.isEmpty(dataObj.getString(keys.get("A3")))) {
            certificateOcrDto.setPublicSituation(dataObj.getString(keys.get("A3")));
        }
        if (!StringUtils.isEmpty(dataObj.getString(keys.get("A4")))) {
            certificateOcrDto.setBeLocated(dataObj.getString(keys.get("A4")));
        }
        if (!StringUtils.isEmpty(dataObj.getString(keys.get("A5")))) {
            certificateOcrDto.setRealEstateUnitNumber(dataObj.getString(keys.get("A5")));
        }
        temp = String.format("%s%s", dataObj.getString(keys.get("A6")), dataObj.getString(keys.get("A7")));
        if (!StringUtils.isEmpty(temp)) {
            certificateOcrDto.setUseRightType(temp);
            temp = null;
        }
        temp = String.format("%s%s", dataObj.getString(keys.get("A8")), dataObj.getString(keys.get("A9")));
        if (!StringUtils.isEmpty(temp)) {
            certificateOcrDto.setNature(temp);
            temp = null;
        }
        temp = String.format("%s%s", dataObj.getString(keys.get("A10")), dataObj.getString(keys.get("A11")));
        if (!StringUtils.isEmpty(temp)) {
            certificateOcrDto.setPurpose(temp);
            temp = null;
        }
        if (dataObj.getInteger(keys.get("A12")) != null) {
            certificateOcrDto.setFloorArea(new BigDecimal(dataObj.getInteger(keys.get("A12"))));
        }
        //解析字符中的文字
        temp = dataObj.getString(keys.get("A13"));
        if (!StringUtils.isEmpty(temp)) {
            Matcher matcher = Pattern.compile("[0-9]{4}[年][0-9]{1,2}[月][0-9]{1,2}[日]").matcher(temp);
            while (matcher.find()) {
                if (!StringUtils.isEmpty(matcher.group(0))) {
                    Date terminationDate = DateHelp.getDateHelp().parse(matcher.group(0), "yyyy年MM月dd日");
                    certificateOcrDto.setTerminationDate(terminationDate);
                }
            }
        }
        System.out.println(certificateOcrDto);
        return certificateOcrDto;
    }

    /**
     * 功能描述: 不动产key
     *
     * @auther: zch
     * @date: 2018/10/17 11:26
     */
    private static Map<String, String> setEstateCertKey() {
        Map<String, String> key = Maps.newHashMap();
        key.put("angle", "angle");
        key.put("sid", "sid");
        key.put("A1", "房产证号");
        key.put("A2", "权利人");
        key.put("A3", "共有情况");
        key.put("A4", "坐落");
        key.put("A5", "不动产单元号");
        key.put("A6", "土地权利类型");
        key.put("A7", "房屋权利类型");
        key.put("A8", "土地权利性质/取得方式");
        key.put("A9", "房屋权利性质/取得方式");
        key.put("A10", "土地用途");
        key.put("A11", "房屋用途");
        key.put("A12", "建筑面积");
        key.put("A13", "使用期限");
        return key;
    }

}
