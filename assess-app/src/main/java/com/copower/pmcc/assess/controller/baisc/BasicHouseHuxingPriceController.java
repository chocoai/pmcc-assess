package com.copower.pmcc.assess.controller.baisc;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseHuxingPrice;
import com.copower.pmcc.assess.dal.basis.entity.ProjectPlanDetails;
import com.copower.pmcc.assess.dto.input.project.survey.ExamineHousePriceDto;
import com.copower.pmcc.assess.service.basic.BasicHouseHuxingPriceService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:57
 * @Description:
 */
@RequestMapping(value = "/basicHouseHuxingPrice")
@Controller
public class BasicHouseHuxingPriceController {
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private BasicHouseHuxingPriceService basicHouseHuxingPriceService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getBasicHouseHuxingPriceById", name = "获取数据", method = {RequestMethod.GET})
    public HttpResult getBasicHouseHuxingPriceById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicHouseHuxingPriceService.getBasicHouseHuxingPriceById(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateBasicHouseHuxingPrice", name = "新增或者修改", method = {RequestMethod.POST})
    public HttpResult saveAndUpdateBasicHouseHuxingPrice(BasicHouseHuxingPrice basicHouseHuxingPrice) {
        try {
            return HttpResult.newCorrectResult(200, basicHouseHuxingPriceService.saveAndUpdateBasicHouseHuxingPrice(basicHouseHuxingPrice, true));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBasicHouseHuxingPrice", name = "删除数据", method = {RequestMethod.POST})
    public HttpResult deleteBasicHouseHuxingPrice(Integer id) {
        try {
            return HttpResult.newCorrectResult(200, basicHouseHuxingPriceService.deleteBasicHouseHuxingPrice(id));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getBootstrapTableVo", method = {RequestMethod.GET})
    public BootstrapTableVo getBootstrapTableVo(BasicHouseHuxingPrice basicHouseHuxingPrice, @RequestParam(required = true, name = "approval", defaultValue = "false") Boolean approval) {
        try {
            return basicHouseHuxingPriceService.getBootstrapTableVo(basicHouseHuxingPrice);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getListByQuery", method = {RequestMethod.GET})
    public BootstrapTableVo getListByQuery(String judgeIds, String houseNumber) {
        try {
            return basicHouseHuxingPriceService.getListByQuery(judgeIds, houseNumber);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/loadListByIds", method = {RequestMethod.GET})
    public BootstrapTableVo loadListByIds(String huxingPriceIds, String houseNumber) {
        try {
            List<Integer> idsList = FormatUtils.transformString2Integer(huxingPriceIds);
            return basicHouseHuxingPriceService.loadListByIds(idsList, houseNumber);
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/basicHouseHuxingPriceList", name = "获取数据列表", method = {RequestMethod.GET})
    public HttpResult basicHouseHuxingPriceList(BasicHouseHuxingPrice basicHouseHuxingPrice) {
        try {
            return HttpResult.newCorrectResult(200, basicHouseHuxingPriceService.basicHouseHuxingPriceList(basicHouseHuxingPrice));
        } catch (Exception e) {
            logger.error(String.format("Server-side exception:%s", e.getMessage()), e);
            return HttpResult.newErrorResult(500, e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/importData", name = "导入", method = RequestMethod.POST)
    public HttpResult importData(HttpServletRequest request, Integer houseId, Integer planDetailsId) {
        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> fileNames = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(fileNames.next());
            if (multipartFile.isEmpty()) {
                return HttpResult.newErrorResult("上传的文件不能为空");
            }
            ProjectPlanDetails planDetails = projectPlanDetailsService.getProjectPlanDetailsById(planDetailsId);
            String str = basicHouseHuxingPriceService.importData(multipartFile, houseId, planDetails.getProjectId());
            return HttpResult.newCorrectResult(str);
        } catch (Exception e) {
            return HttpResult.newErrorResult(e.getMessage());
        }

    }

    @RequestMapping(value = "/generateAndExport", name = "生成并导出模板")
    public void generateAndExport(HttpServletResponse response, String columns, String source) throws Exception {
        try {
            List<ExamineHousePriceDto> dtoList = JSON.parseArray(columns, ExamineHousePriceDto.class);
            basicHouseHuxingPriceService.generateAndExport(response, dtoList, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
