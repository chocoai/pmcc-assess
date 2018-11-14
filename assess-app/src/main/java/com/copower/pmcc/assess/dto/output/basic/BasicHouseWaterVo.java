package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseWater;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:20
 * @Description:
 */
public class BasicHouseWaterVo extends BasicHouseWater {
    private String supplyErectionMethodName;

    private String intakePointNumberName;

    private String pretreatedWaterName;


    private String purificationEquipmentPriceName;


    private String waterIntakeEquipmentName;

    private String waterIntakeEquipmentPriceName;

    private String drainageErectionMethodName;

    private String drainageCircuitName;

    private String drainageCircuitCountName;

    public String getSupplyErectionMethodName() {
        return supplyErectionMethodName;
    }

    public void setSupplyErectionMethodName(String supplyErectionMethodName) {
        this.supplyErectionMethodName = supplyErectionMethodName;
    }

    public String getIntakePointNumberName() {
        return intakePointNumberName;
    }

    public void setIntakePointNumberName(String intakePointNumberName) {
        this.intakePointNumberName = intakePointNumberName;
    }

    public String getPretreatedWaterName() {
        return pretreatedWaterName;
    }

    public void setPretreatedWaterName(String pretreatedWaterName) {
        this.pretreatedWaterName = pretreatedWaterName;
    }

    public String getPurificationEquipmentPriceName() {
        return purificationEquipmentPriceName;
    }

    public void setPurificationEquipmentPriceName(String purificationEquipmentPriceName) {
        this.purificationEquipmentPriceName = purificationEquipmentPriceName;
    }

    public String getWaterIntakeEquipmentName() {
        return waterIntakeEquipmentName;
    }

    public void setWaterIntakeEquipmentName(String waterIntakeEquipmentName) {
        this.waterIntakeEquipmentName = waterIntakeEquipmentName;
    }

    public String getWaterIntakeEquipmentPriceName() {
        return waterIntakeEquipmentPriceName;
    }

    public void setWaterIntakeEquipmentPriceName(String waterIntakeEquipmentPriceName) {
        this.waterIntakeEquipmentPriceName = waterIntakeEquipmentPriceName;
    }

    public String getDrainageErectionMethodName() {
        return drainageErectionMethodName;
    }

    public void setDrainageErectionMethodName(String drainageErectionMethodName) {
        this.drainageErectionMethodName = drainageErectionMethodName;
    }

    public String getDrainageCircuitName() {
        return drainageCircuitName;
    }

    public void setDrainageCircuitName(String drainageCircuitName) {
        this.drainageCircuitName = drainageCircuitName;
    }

    public String getDrainageCircuitCountName() {
        return drainageCircuitCountName;
    }

    public void setDrainageCircuitCountName(String drainageCircuitCountName) {
        this.drainageCircuitCountName = drainageCircuitCountName;
    }
}
