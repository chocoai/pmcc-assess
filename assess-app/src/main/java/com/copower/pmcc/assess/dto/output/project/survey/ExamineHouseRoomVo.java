package com.copower.pmcc.assess.dto.output.project.survey;

import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseRoom;

/**
 * @Auther: zch
 * @Date: 2018/7/24 18:25
 * @Description:
 */
public class ExamineHouseRoomVo extends ExamineHouseRoom {
    private String roomTypeName;

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }
}
