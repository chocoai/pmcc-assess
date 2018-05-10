package com.copower.pmcc.assess.dto.input;

import com.copower.pmcc.assess.dal.entity.DeclareInfo;
import org.aspectj.weaver.patterns.Declare;

/**
 * Created by kings on 2018-5-10.
 */
public class DeclareInfoDto extends DeclareInfo {
    private FormConfigureDto formConfigureDto;

    public FormConfigureDto getFormConfigureDto() {
        return formConfigureDto;
    }

    public void setFormConfigureDto(FormConfigureDto formConfigureDto) {
        this.formConfigureDto = formConfigureDto;
    }
}
