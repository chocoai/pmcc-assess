package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseWater;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:20
 * @Description:
 */
public class BasicHouseWaterVo extends BasicHouseWater {


    private String pretreatedWaterName;

    private String pipeMaterialName;

    private String pipingLayoutName;

    private String supplyModeName;

    private String boosterEquipmentName;

    private String purificationEquipmentPriceName;

    private String fireWaterSupplyName;


    public String getPretreatedWaterName() {
        return pretreatedWaterName;
    }

    public void setPretreatedWaterName(String pretreatedWaterName) {
        this.pretreatedWaterName = pretreatedWaterName;
    }

    public String getPipeMaterialName() {
        return pipeMaterialName;
    }

    public void setPipeMaterialName(String pipeMaterialName) {
        this.pipeMaterialName = pipeMaterialName;
    }

    public String getPipingLayoutName() {
        return pipingLayoutName;
    }

    public void setPipingLayoutName(String pipingLayoutName) {
        this.pipingLayoutName = pipingLayoutName;
    }

    public String getSupplyModeName() {
        return supplyModeName;
    }

    public void setSupplyModeName(String supplyModeName) {
        this.supplyModeName = supplyModeName;
    }

    public String getBoosterEquipmentName() {
        return boosterEquipmentName;
    }

    public void setBoosterEquipmentName(String boosterEquipmentName) {
        this.boosterEquipmentName = boosterEquipmentName;
    }

    public String getPurificationEquipmentPriceName() {
        return purificationEquipmentPriceName;
    }

    public void setPurificationEquipmentPriceName(String purificationEquipmentPriceName) {
        this.purificationEquipmentPriceName = purificationEquipmentPriceName;
    }

    public String getFireWaterSupplyName() {
        return fireWaterSupplyName;
    }

    public void setFireWaterSupplyName(String fireWaterSupplyName) {
        this.fireWaterSupplyName = fireWaterSupplyName;
    }
}
