package com.copower.pmcc.assess.dto.output.method;

import com.copower.pmcc.assess.dal.basis.entity.SchemeInfo;

import java.io.Serializable;

/**
 * Created by zch on 2019-11-11.
 */
public class SchemeInfoVo extends SchemeInfo implements Serializable{
    private String judgeObjectName;

    private String methodName;
    private String certName;
    public String getJudgeObjectName() {
        return judgeObjectName;
    }

    public void setJudgeObjectName(String judgeObjectName) {
        this.judgeObjectName = judgeObjectName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }
}
