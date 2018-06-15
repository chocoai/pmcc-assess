package com.copower.pmcc.assess.controller.csr.task;

import com.copower.pmcc.assess.dal.entity.CsrBorrower;
import com.copower.pmcc.assess.dal.entity.CsrBorrowerEntering;
import com.copower.pmcc.assess.dto.output.project.csr.CsrBorrowerEnteringVo;
import com.copower.pmcc.assess.dto.output.project.csr.CsrContractVo;
import com.copower.pmcc.assess.service.csr.CsrBorrowerService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2018/6/1
 * @time: 17:08
 */
@Controller
@RequestMapping(value = "/csrBorrower", name = "借款人")
public class CsrBorrowerController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CsrBorrowerService csrBorrowerService;

    @ResponseBody
    @RequestMapping(value = "/saveLoanBorrower", name = "保存录入借款人", method = RequestMethod.POST)
    public HttpResult saveLoanBorrower(CsrBorrowerEntering csrBorrower, Integer detailsId, String taskRemarks, String actualHours) {
        try {
            csrBorrower = csrBorrowerService.saveCsrBorrower(csrBorrower,detailsId,taskRemarks,actualHours);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult(csrBorrower.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/loadLoanBorrower", name = "读取录入借款人", method = RequestMethod.GET)
    public HttpResult loadLoanBorrower(Integer borrowerId,Integer detailsId) {
        try {
            CsrBorrowerEnteringVo csrBorrowerEnteringVo = csrBorrowerService.loadLoanBorrower(borrowerId, detailsId);
            return HttpResult.newCorrectResult(csrBorrowerEnteringVo);
        } catch (BusinessException e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/listBorrowers", name = "显示借款人列表", method = RequestMethod.GET)
    public BootstrapTableVo list(String secondLevelBranch, String firstLevelBranch, Integer csrProjectInfoID) {
        BootstrapTableVo vo = null;
        vo = csrBorrowerService.borrowerListVos(secondLevelBranch,firstLevelBranch,csrProjectInfoID);
        return vo;
    }

    @RequestMapping(value = "/downloadBorrowers",name = "多 报表 下载",method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseEntity<byte[]> downloadBorrowers(String borrowerIds, HttpServletRequest request, HttpServletResponse response){
        try {
            ResponseEntity<byte[]> responseEntity = csrBorrowerService.downloadBorrower(borrowerIds,request,response);
            if (responseEntity!=null) {
                return responseEntity;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            logger.error("下载异常");
        }
        return null;
    }

    @RequestMapping(value = "/exportFormBorrowers",name = "报表 导出",method = {RequestMethod.GET})
    public ResponseEntity<byte[]> exportFormBorrowers(HttpServletRequest request, HttpServletResponse response,Integer csrProjectInfoID){
        try {
            ResponseEntity<byte[]> responseEntity = null;
            responseEntity = csrBorrowerService.exportFormBorrowers(request,response,csrProjectInfoID);
            if (responseEntity!=null) {
                return responseEntity;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            logger.error("下载异常");
        }
        return null;
    }

}
