package com.copower.pmcc.assess.dto.output.cases;

import com.copower.pmcc.assess.dal.cases.entity.CaseHouseRoom;

/**
 * @Auther: zch
 * @Date: 2018/9/18 10:21
 * @Description:
 */
public class CaseHouseRoomVo extends CaseHouseRoom {
    private String roomTypeName;

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }
}
