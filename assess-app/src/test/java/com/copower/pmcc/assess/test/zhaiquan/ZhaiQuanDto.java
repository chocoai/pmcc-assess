package com.copower.pmcc.assess.test.zhaiquan;

import com.copower.pmcc.erp.api.dto.KeyValueDto;

import java.util.List;

/**
 * Created by kings on 2019-4-12.
 */
public class ZhaiQuanDto {
    private String mutiDywFlag;
    private String ejfh;
    private String khmc;
    private String number;
    private String role;
    private String version;
    private KeyValueDto customerInfo;
    private List<ZhaiQuanDywDto> dywInfo;

    public String getMutiDywFlag() {
        return mutiDywFlag;
    }

    public void setMutiDywFlag(String mutiDywFlag) {
        this.mutiDywFlag = mutiDywFlag;
    }

    public String getEjfh() {
        return ejfh;
    }

    public void setEjfh(String ejfh) {
        this.ejfh = ejfh;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }

    public String getNumber() {
        return number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public KeyValueDto getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(KeyValueDto customerInfo) {
        this.customerInfo = customerInfo;
    }

    public List<ZhaiQuanDywDto> getDywInfo() {
        return dywInfo;
    }

    public void setDywInfo(List<ZhaiQuanDywDto> dywInfo) {
        this.dywInfo = dywInfo;
    }
}
