package com.copower.pmcc.assess.dto.output.basic;

import com.copower.pmcc.assess.dal.basic.entity.BasicHouseRoom;

/**
 * @Auther: zch
 * @Date: 2018/11/2 10:03
 * @Description:
 */
public class BasicHouseRoomVo extends BasicHouseRoom {
    private String roomTypeName;

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }
}
