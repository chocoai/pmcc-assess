package com.copower.pmcc.assess.dto.input.project;

import java.util.List;

/**
 * Created by zly on 2018/5/31.
 */
public class MethodMarketCompareCommonDto {

    private List<MethodMarketCompareFactorDto> methodMarketCompareFactorDtos;
    private List<MethodMarketCompareIndexDto> methodMarketCompareIndexDtos;
    private List<MethodMarketCompareCalculationDto> methodMarketCompareCalculationDtos;
    private List<MethodMarketCompareResultDto> methodMarketCompareResultDtos;

    public List<MethodMarketCompareFactorDto> getMethodMarketCompareFactorDtos() {
        return methodMarketCompareFactorDtos;
    }

    public void setMethodMarketCompareFactorDtos(List<MethodMarketCompareFactorDto> methodMarketCompareFactorDtos) {
        this.methodMarketCompareFactorDtos = methodMarketCompareFactorDtos;
    }

    public List<MethodMarketCompareIndexDto> getMethodMarketCompareIndexDtos() {
        return methodMarketCompareIndexDtos;
    }

    public void setMethodMarketCompareIndexDtos(List<MethodMarketCompareIndexDto> methodMarketCompareIndexDtos) {
        this.methodMarketCompareIndexDtos = methodMarketCompareIndexDtos;
    }

    public List<MethodMarketCompareCalculationDto> getMethodMarketCompareCalculationDtos() {
        return methodMarketCompareCalculationDtos;
    }

    public void setMethodMarketCompareCalculationDtos(List<MethodMarketCompareCalculationDto> methodMarketCompareCalculationDtos) {
        this.methodMarketCompareCalculationDtos = methodMarketCompareCalculationDtos;
    }

    public List<MethodMarketCompareResultDto> getMethodMarketCompareResultDtos() {
        return methodMarketCompareResultDtos;
    }

    public void setMethodMarketCompareResultDtos(List<MethodMarketCompareResultDto> methodMarketCompareResultDtos) {
        this.methodMarketCompareResultDtos = methodMarketCompareResultDtos;
    }
}
