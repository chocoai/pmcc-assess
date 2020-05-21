package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetailVolume;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelDetailVolumeVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailVolumeService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

/**
 * @author: zch
 * @date: 2019/5/6 14:42
 * @description:容积率修正系数配置 详情(从表)
 */
@RequestMapping(value = "/dataLandLevelDetailVolume")
@RestController
public class DataLandLevelDetailVolumeController {

    @Autowired
    private DataLandLevelDetailVolumeService volumeRatioDetailService;
    @Autowired
    private BaseService baseService;

    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET}, name = "房价指数 列表")
    public BootstrapTableVo getBootstrapTableVo(DataLandLevelDetailVolume oo) {
        BootstrapTableVo vo = volumeRatioDetailService.getBootstrapTableVo(oo);
        return vo;
    }

    @GetMapping(value = "/get/{id}", name = "restful get")
    public HttpResult get(@PathVariable Integer id) {
        DataLandLevelDetailVolumeVo coefficientVolumeRatioDetailVo = null;
        try {
            coefficientVolumeRatioDetailVo = volumeRatioDetailService.getDataLandLevelDetailVolumeVo(volumeRatioDetailService.getDataLandLevelDetailVolumeById(id));
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
        return HttpResult.newCorrectResult(coefficientVolumeRatioDetailVo);
    }


    @PostMapping(value = "/save")
    public HttpResult save(DataLandLevelDetailVolume coefficientVolumeRatioDetail) {
        try {
            volumeRatioDetailService.saveDataLandLevelDetailVolume(coefficientVolumeRatioDetail);
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @DeleteMapping(value = "/delete/{id}", name = "restful delete")
    public HttpResult delete(@PathVariable String id) {
        try {
            List<Integer> integerList = FormatUtils.transformString2Integer(id);
            for (Integer integer:integerList){
                volumeRatioDetailService.deleteDataLandLevelDetailVolume(integer);
            }
        } catch (Exception e) {
            baseService.writeExceptionInfo(e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @RequestMapping(value = "/importDataAllocationCorrectionCoefficientVolumeRatio", name = "导入 (excel)", method = RequestMethod.POST)
    public HttpResult importDataAllocationCorrectionCoefficientVolumeRatio(DataLandLevelDetailVolume oo,HttpServletRequest request){
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
        DataLandLevelDetailVolume volumeRatioDetail = null;
        try {
            volumeRatioDetail = JSON.parseObject(formData, DataLandLevelDetailVolume.class);
        } catch (Exception e) {
            //解决bug unclosed string : 
            formData = String.format("%s%s",formData,"\"}");
            volumeRatioDetail = JSON.parseObject(formData, DataLandLevelDetailVolume.class);
        }
        volumeRatioDetailService.saveDataLandLevelDetailVolume(volumeRatioDetail);
    }

}
