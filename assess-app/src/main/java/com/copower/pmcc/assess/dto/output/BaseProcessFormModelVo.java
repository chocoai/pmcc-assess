package com.copower.pmcc.assess.dto.output;

import com.copower.pmcc.assess.dal.entity.BaseFormList;
import com.copower.pmcc.bpm.api.dto.model.BoxReActivityDto;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/2/23
 * @time: 17:50
 */
public class BaseProcessFormModelVo {
    private List<BoxReActivityDto> boxReActivityDtoList;

    private List<BaseFormList> hrBaseFormLists;

    public List<BoxReActivityDto> getBoxReActivityDtoList() {
        return boxReActivityDtoList;
    }

    public void setBoxReActivityDtoList(List<BoxReActivityDto> boxReActivityDtoList) {
        this.boxReActivityDtoList = boxReActivityDtoList;
    }

    public List<BaseFormList> getBaseFormLists() {
        return hrBaseFormLists;
    }

    public void setBaseFormLists(List<BaseFormList> hrBaseFormLists) {
        this.hrBaseFormLists = hrBaseFormLists;
    }
}
