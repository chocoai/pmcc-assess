package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.basic.BasicTenementTypeEnum;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 物业类型 可以转换出的各种数据
 */
@Component
public class BasicTenementTypeService {
    public String toColumns(BasicTenementTypeEnum... tenementTypeEnums) {
        for (BasicTenementTypeEnum typeEnum : tenementTypeEnums) {
            switch (typeEnum) {
                case OFFICE:
                case RESIDENTIAL: {
                    return "commonColumn.houseRoomResidence()";
                }
                case SHOP:
                case MARKET:
                case PARKING_SPACE: {
                    return "commonColumn.houseRoomStore()";
                }
                case HOTEL:
                case REPAST: {
                    return "commonColumn.houseRoomHotel()";
                }
                case PRODUCE: {
                    return "commonColumn.houseRoomProduction()";
                }
                case WARE_HOUSE: {
                    return "commonColumn.houseRoomStorage()";
                }
                default: {
                    break;
                }
            }
        }
        return null;
    }

    /**
     * 户型  专有部分的 下拉框
     *
     * @param tenementTypeEnums
     * @return
     */
    public String toOptions(BasicTenementTypeEnum... tenementTypeEnums) {
        StringBuilder stringBuilder = new StringBuilder();
        for (BasicTenementTypeEnum typeEnum : tenementTypeEnums) {
            switch (typeEnum) {
                case RESIDENTIAL: {
                    stringBuilder.append("<option value=\"卧室\" data-desc=\"室\">卧室</option>");
                    stringBuilder.append("<option value=\"客厅\" data-desc=\"厅\">客厅</option>");
                    stringBuilder.append("<option value=\"中餐厅\">中餐厅</option>");
                    stringBuilder.append("<option value=\"西餐厅\">西餐厅</option>");
                    stringBuilder.append("<option value=\"茶室\">茶室</option>");
                    stringBuilder.append("<option value=\"影视室\">影视室</option>");
                    return stringBuilder.toString();
                }
                case SHOP:
                case MARKET:
                case PARKING_SPACE:
                    stringBuilder.append("<option value=\"商间\">商间</option>");
                    stringBuilder.append("<option value=\"商区\">商区</option>");
                    return stringBuilder.toString();
                case REPAST:
                case HOTEL:
                    stringBuilder.append("<option value=\"住宿\" data-child=\"标间(普通),标间(商务),标间(高级),单间(普通),单间(商务),单间(高级),套房(普通),套房(商务),套房(高级)\">住宿</option>");
                    stringBuilder.append("<option value=\"商业\" data-child=\"会议室,会议厅,商务厅,影视厅\">商业</option>");
                    stringBuilder.append("<option value=\"餐饮\" data-child=\"包间(普通),包间(标准),包间(豪华),餐饮大厅,共用餐区\">餐饮</option>");
                    return stringBuilder.toString();
                case OFFICE:
                    stringBuilder.append("<option value=\"会议室\">会议室</option>");
                    stringBuilder.append("<option value=\"会客室\">会客室</option>");
                    stringBuilder.append("<option value=\"休息室\">休息室</option>");
                    stringBuilder.append("<option value=\"办公室\">办公室</option>");
                    stringBuilder.append("<option value=\"办公区\">办公区</option>");
                    stringBuilder.append("<option value=\"档案室\">档案室</option>");
                    stringBuilder.append("<option value=\"影视室\">影视室</option>");
                    return stringBuilder.toString();
                case PRODUCE: {
                    stringBuilder.append("<option value=\"生产车间\">生产车间</option>");
                    stringBuilder.append("<option value=\"维修车间\">维修车间</option>");
                    stringBuilder.append("<option value=\"成品车间\">成品车间</option>");
                    stringBuilder.append("<option value=\"热力车间\">热力车间</option>");
                    stringBuilder.append("<option value=\"中转车间\">中转车间</option>");
                    return stringBuilder.toString();
                }
                case WARE_HOUSE: {
                    stringBuilder.append("<option value=\"储库\">储库</option>");
                    stringBuilder.append("<option value=\"储仓\">储仓</option>");
                    return stringBuilder.toString();
                }
                default: {
                    break;
                }
            }
        }
        return stringBuilder.toString();
    }
    public KeyValueDto[] priceExportColumns(BasicTenementTypeEnum... tenementTypeEnums) {
        List<KeyValueDto> keyValueDtoList = new ArrayList<>();
        keyValueDtoList.add(new KeyValueDto("houseShape", "房间形状"));
        keyValueDtoList.add(new KeyValueDto("shapeRemark", "形状说明"));
        keyValueDtoList.add(new KeyValueDto("specialFactors", "特殊因素"));
        for (BasicTenementTypeEnum typeEnum : tenementTypeEnums) {
            switch (typeEnum) {
                case PARKING_SPACE:
                case MARKET:
                case SHOP: {
                    keyValueDtoList.add(new KeyValueDto("opening", "开间"));
                    keyValueDtoList.add(new KeyValueDto("depth", "进深"));
                    keyValueDtoList.add(new KeyValueDto("adjacentPosition", "相邻位置"));
                    keyValueDtoList.add(new KeyValueDto("distance", "距离(m)"));
                    keyValueDtoList.add(new KeyValueDto("orientation", "方位"));
                    return keyValueDtoList.toArray(new KeyValueDto[keyValueDtoList.size()]);
                }
                case OFFICE:
                case RESIDENTIAL: {
                    keyValueDtoList.add(new KeyValueDto("aeration", "通风"));
                    keyValueDtoList.add(new KeyValueDto("lighting", "采光"));
                    keyValueDtoList.add(new KeyValueDto("sunshine", "日照"));
                    keyValueDtoList.add(new KeyValueDto("soundInsulation", "隔音"));
                    keyValueDtoList.add(new KeyValueDto("length", "长度"));
                    keyValueDtoList.add(new KeyValueDto("width", "宽度"));
                    return keyValueDtoList.toArray(new KeyValueDto[keyValueDtoList.size()]);
                }
                case REPAST:
                case HOTEL: {
                    keyValueDtoList.add(new KeyValueDto("aeration", "通风"));
                    keyValueDtoList.add(new KeyValueDto("lighting", "采光"));
                    keyValueDtoList.add(new KeyValueDto("length", "长度"));
                    keyValueDtoList.add(new KeyValueDto("width", "宽度"));
                    return keyValueDtoList.toArray(new KeyValueDto[keyValueDtoList.size()]);
                }
                case PRODUCE: {
                    keyValueDtoList.add(new KeyValueDto("spanLength", "跨长"));
                    keyValueDtoList.add(new KeyValueDto("spanNum", "跨数"));
                    keyValueDtoList.add(new KeyValueDto("aeration", "通风"));
                    keyValueDtoList.add(new KeyValueDto("lighting", "采光"));
                    keyValueDtoList.add(new KeyValueDto("maxSpan", "最大跨距"));
                    keyValueDtoList.add(new KeyValueDto("minSpan", "最小跨距"));
                    keyValueDtoList.add(new KeyValueDto("standardSpan", "标准跨距"));
                    return keyValueDtoList.toArray(new KeyValueDto[keyValueDtoList.size()]);
                }
                case WARE_HOUSE: {
                    keyValueDtoList.add(new KeyValueDto("standardMeasure", "计量标准"));
                    keyValueDtoList.add(new KeyValueDto("storageRequest", "仓储要求"));
                    return keyValueDtoList.toArray(new KeyValueDto[keyValueDtoList.size()]);
                }
                default: {
                    break;
                }
            }
        }
        return keyValueDtoList.toArray(new KeyValueDto[keyValueDtoList.size()]);
    }

    /**
     * 调整因素
     *
     * @return
     */
    public List<String> getAdjustFactorList(BasicTenementTypeEnum tenementTypeEnum) {
        List<String> list = Lists.newArrayList("面积", "楼层", "层高", "形状", "特殊因素");
        if (tenementTypeEnum != null) {
            switch (tenementTypeEnum) {
                case PARKING_SPACE:
                case MARKET:
                case SHOP: {
                    list.add("开间");
                    list.add("进深");
                    list.add("相邻位置");
                    list.add("距离");
                    list.add("方位");
                    break;
                }
                case OFFICE:
                case RESIDENTIAL: {
                    list.add("通风");
                    list.add("采光");
                    list.add("日照");
                    list.add("隔音");
                    list.add("长度");
                    list.add("宽度");
                    break;
                }
                case REPAST:
                case HOTEL: {
                    list.add("通风");
                    list.add("采光");
                    list.add("长度");
                    list.add("宽度");
                    break;
                }
                case PRODUCE: {
                    list.add("跨长");
                    list.add("跨数");
                    list.add("通风");
                    list.add("采光");
                    list.add("最大跨距");
                    list.add("最小跨距");
                    list.add("标准跨距");
                    break;
                }
                case WARE_HOUSE: {
                    list.add("计量标准");
                    list.add("仓储要求");
                    break;
                }
                default: {
                    break;
                }
            }
        }
        return list;
    }

    public String toHtmlViewClassNamePrefix(BasicTenementTypeEnum... tenementTypeEnums) {
        //注意下面返回的是页面class选择器的前缀和枚举没关系的
        for (BasicTenementTypeEnum typeEnum : tenementTypeEnums) {
            switch (typeEnum) {
                case RESIDENTIAL:
                case OFFICE: {
                    return "residence";
                }
                case SHOP:
                case MARKET:
                case PARKING_SPACE: {
                    return "store";
                }
                case REPAST:
                case HOTEL: {
                    return "hotel";
                }
                case PRODUCE: {
                    return "production";
                }
                case WARE_HOUSE: {
                    return "storage";
                }
                default: {
                    break;
                }
            }
        }
        return null;
    }
}
