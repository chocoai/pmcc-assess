package com.copower.pmcc.assess.dto.input.project.initiate;


import com.copower.pmcc.assess.dal.entity.InitiateContacts;

import java.io.Serializable;

/**
 * Created by 13426 on 2018/5/4.
 */
public class InitiateContactsDto extends InitiateContacts implements Comparable<InitiateContactsDto> ,Serializable{
    public static Integer C_PID = 0;

    @Override
    public int compareTo(InitiateContactsDto o) {
        return this.getGmtCreated().compareTo(o.getGmtCreated());
    }
}
