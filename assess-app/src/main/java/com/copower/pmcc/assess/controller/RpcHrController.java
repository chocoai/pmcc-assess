package com.copower.pmcc.assess.controller;

import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.hr.api.dto.HrBaseDataDicDto;
import com.copower.pmcc.hr.api.enums.HrBaseDataDicEnum;
import com.copower.pmcc.hr.api.provider.HrRpcDataDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/1/23
 * @time: 10:16
 */
@Controller
@RequestMapping(value = "/RpcHrService", name = "Hr接口类")
public class RpcHrController {
    @Autowired
    private HrRpcDataDicService hrRpcDataDicService;

    @RequestMapping(value = "/getProfessionalType", name = "资质证书类型", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult getProfessionalType() {
        List<HrBaseDataDicDto> hrBaseDataDicDtos = hrRpcDataDicService.getCacheDataDicListByKey(HrBaseDataDicEnum.HR_PROFESSIONAL_TYPE.getValue());
        return HttpResult.newCorrectResult(hrBaseDataDicDtos);
    }

    @RequestMapping(value = "/getEmployeeCertType", name = "获取员工的证件证书证明类型", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult getEmployeeCertType() {
        List<HrBaseDataDicDto> hrBaseDataDicDtos = hrRpcDataDicService.getCacheDataDicListByKey(HrBaseDataDicEnum.HR_EMPLOYEE_CERT_TYPE.getValue());
        return HttpResult.newCorrectResult(hrBaseDataDicDtos);
    }

    @RequestMapping(value = "/getCacheDataDicByPid", name = "获取员工的证件证书证明类型", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult getCacheDataDicByPid(Integer pid) {
        List<HrBaseDataDicDto> hrBaseDataDicDtos = hrRpcDataDicService.getCacheDataDicByPid(pid);
        return HttpResult.newCorrectResult(hrBaseDataDicDtos);
    }
}
