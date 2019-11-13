package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.entity.DataAllocationCorrectionCoefficientVolumeRatioDetail;
import com.copower.pmcc.assess.dto.output.data.DataAllocationCorrectionCoefficientVolumeRatioDetailVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.data.DataAllocationCorrectionCoefficientVolumeRatioDetailService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 * @author: zch
 * @date: 2019/5/6 14:42
 * @description:容积率修正系数配置 详情(从表)
 */
@RequestMapping(value = "/dataAllocationCorrectionCoefficientVolumeRatioDetail")
@RestController
public class DataAllocationCorrectionCoefficientVolumeRatioDetailController {

    @Autowired
    private DataAllocationCorrectionCoefficientVolumeRatioDetailService volumeRatioDetailService;
    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET}, name = "房价指数 列表")
    public BootstrapTableVo getBootstrapTableVo(DataAllocationCorrectionCoefficientVolumeRatioDetail oo) {
        BootstrapTableVo vo = volumeRatioDetailService.getBootstrapTableVo(oo);
        return vo;
    }

    @GetMapping(value = "/get/{id}", name = "restful get")
    public HttpResult get(@PathVariable Integer id) {
        DataAllocationCorrectionCoefficientVolumeRatioDetailVo coefficientVolumeRatioDetailVo = null;
        try {
            coefficientVolumeRatioDetailVo = volumeRatioDetailService.getDataAllocationCorrectionCoefficientVolumeRatioDetailVo(volumeRatioDetailService.getDataAllocationCorrectionCoefficientVolumeRatioDetailById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
        return HttpResult.newCorrectResult(coefficientVolumeRatioDetailVo);
    }


    @PostMapping(value = "/save")
    public HttpResult save(DataAllocationCorrectionCoefficientVolumeRatioDetail coefficientVolumeRatioDetail) {
        try {
            volumeRatioDetailService.saveDataAllocationCorrectionCoefficientVolumeRatioDetail(coefficientVolumeRatioDetail);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @DeleteMapping(value = "/delete/{id}", name = "restful delete")
    public HttpResult delete(@PathVariable Integer id) {
        try {
            if (id != null && id != 0) {
                DataAllocationCorrectionCoefficientVolumeRatioDetail coefficientVolumeRatioDetail = new DataAllocationCorrectionCoefficientVolumeRatioDetail();
                coefficientVolumeRatioDetail.setId(id);
                volumeRatioDetailService.deleteDataAllocationCorrectionCoefficientVolumeRatioDetail(id);
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/importDataAllocationCorrectionCoefficientVolumeRatio", name = "导入 (excel)", method = RequestMethod.POST)
    public HttpResult importDataAllocationCorrectionCoefficientVolumeRatio(DataAllocationCorrectionCoefficientVolumeRatioDetail oo,HttpServletRequest request){
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String resultString = volumeRatioDetailService.importDataAllocationCorrectionCoefficientVolumeRatio(multipartFile,oo) ;
            return HttpResult.newCorrectResult(200,resultString);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    /**
     * 第一次遇到这种bug记录一下 ,原因是使用restful 的时候我直接把实体作为一个参数,当有小数点的时候com.alibaba.fastjson.JSON解析错误 解析出来的字符串没有结束符号
     * @param formData
     * @throws Exception
     */
    private void saveDetail(String formData) throws Exception {
        DataAllocationCorrectionCoefficientVolumeRatioDetail volumeRatioDetail = null;
        try {
            volumeRatioDetail = JSON.parseObject(formData, DataAllocationCorrectionCoefficientVolumeRatioDetail.class);
        } catch (Exception e) {
            //解决bug unclosed string : 
            formData = String.format("%s%s",formData,"\"}");
            volumeRatioDetail = JSON.parseObject(formData, DataAllocationCorrectionCoefficientVolumeRatioDetail.class);
        }
        volumeRatioDetailService.saveDataAllocationCorrectionCoefficientVolumeRatioDetail(volumeRatioDetail);
    }

}
