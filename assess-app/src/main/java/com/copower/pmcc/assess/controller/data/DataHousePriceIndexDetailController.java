package com.copower.pmcc.assess.controller.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.DataHousePriceIndexDetail;
import com.copower.pmcc.assess.service.data.DataHousePriceIndexDetailService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;


/**
 * @author: zch
 * @date: 2019/5/6 10:47
 * @description:指数详情
 */
@RequestMapping(value = "/dataHousePriceIndexDetail")
@RestController
public class DataHousePriceIndexDetailController {

    @Autowired
    private DataHousePriceIndexDetailService dataHousePriceIndexDetailService;

    @GetMapping(value = "/getBootstrapTableVo")
    public BootstrapTableVo getBootstrapTableVo(DataHousePriceIndexDetail oo) {
        BootstrapTableVo bootstrapTableVo = dataHousePriceIndexDetailService.getBootstrapTableVo(oo);
        return bootstrapTableVo;
    }

    @DeleteMapping(value = "/delete/{id}", name = "restful delete")
    public HttpResult delete(@PathVariable Integer id) {
        try {
            return HttpResult.newCorrectResult(dataHousePriceIndexDetailService.deleteDataHousePriceIndexDetail(id));
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, e);
        }
    }

    @GetMapping(value = "/get/{id}", name = "restful get")
    public HttpResult get(@PathVariable Integer id) {
        try {
            return HttpResult.newCorrectResult(dataHousePriceIndexDetailService.getDataHousePriceIndexDetailById(id));
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, e);
        }
    }

    @PostMapping(value = "/save", name = "restful post")
    public HttpResult save(String formData) {
        try {
            this.saveDataHousePriceIndexDetail(formData);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            return HttpResult.newErrorResult(500, e);
        }
    }


    private void saveDataHousePriceIndexDetail(String formData) throws Exception {
        DataHousePriceIndexDetail oo = JSON.parseObject(formData, DataHousePriceIndexDetail.class);
        dataHousePriceIndexDetailService.saveDataHousePriceIndexDetail(oo);
    }


    @RequestMapping(value = "/generateTemplate", name = "生成并导出单价调查模板")
    public void generateTemplate(HttpServletResponse response) throws Exception {
        try {
            dataHousePriceIndexDetailService.generateTemplate(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/importData", name = "导入", method = RequestMethod.POST)
    public HttpResult importDataLand(HttpServletRequest request, Integer housePriceId) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            String str = dataHousePriceIndexDetailService.importData(multipartFile, housePriceId);
            return HttpResult.newCorrectResult(str);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }
    }
}
