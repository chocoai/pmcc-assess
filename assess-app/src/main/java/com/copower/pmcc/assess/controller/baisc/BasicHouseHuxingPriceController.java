package com.copower.pmcc.assess.controller.baisc;

import com.copower.pmcc.assess.dal.basis.entity.BasicHouseHuxingPrice;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.service.basic.BasicHouseHuxingPriceService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;


@RequestMapping(value = "/basicHouseHuxingPrice")
@Controller
public class BasicHouseHuxingPriceController {
    @Autowired
    private BasicHouseHuxingPriceService basicHouseHuxingPriceService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;


    @ResponseBody
    @RequestMapping(value = "/getBasicHouseHuxingPriceById", name = "获取数据", method = {RequestMethod.POST})
    public HttpResult getBasicHouseHuxingPriceById(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicHouseHuxingPriceService.getBasicHouseHuxingPriceById(id));
        } catch (Exception e) {
            return HttpResult.newErrorResult("获取失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouseHuxingPrice", name = "新增或者修改", method = RequestMethod.POST)
    public HttpResult saveAndUpdateBasicHouseHuxingPrice(BasicHouseHuxingPrice basicHouseHuxing) {
        try {
            return HttpResult.newCorrectResult(basicHouseHuxingPriceService.saveAndUpdateBasicHouseHuxingPrice(basicHouseHuxing, true));
        } catch (Exception e) {
            return HttpResult.newErrorResult("新增或者修改失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouseHuxingPrice", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicHouseHuxingPrice(Integer id) {
        try {
            return HttpResult.newCorrectResult(basicHouseHuxingPriceService.deleteBasicHouseHuxingPrice(id));
        } catch (Exception e) {
            return HttpResult.newErrorResult("删除失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getHouseHuxingPriceList", name = "获取选择户型数据列表")
    public BootstrapTableVo getHouseHuxingPriceList(Integer unitHuxingId) {
        BasicHouseHuxingPrice basicHouseHuxingPrice = new BasicHouseHuxingPrice();
        basicHouseHuxingPrice.setHuxingId(unitHuxingId);
        return basicHouseHuxingPriceService.getBasicHouseHuxingPriceListVos(basicHouseHuxingPrice);

    }

    @ResponseBody
    @RequestMapping(value = "/importData", name = "导入", method = RequestMethod.POST)
    public HttpResult importData(HttpServletRequest request, Integer huxingId, Integer planDetailsId) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
            String str = basicHouseHuxingPriceService.importData(multipartFile, huxingId, planDetails.getProjectId());
            return HttpResult.newCorrectResult(str);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

    }
}
