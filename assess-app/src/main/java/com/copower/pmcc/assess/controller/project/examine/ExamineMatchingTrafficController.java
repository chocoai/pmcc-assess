package com.copower.pmcc.assess.controller.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingTraffic;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.examine.ExamineMatchingTrafficService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/19 15:08
 * @Description:交通条件
 */
@Controller
@RequestMapping(value = "/examineMatchingTraffic")
public class ExamineMatchingTrafficController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineMatchingTrafficService examineMatchingTrafficService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    @ResponseBody
    @RequestMapping(value = "/getExamineMatchingTrafficById",method = {RequestMethod.GET},name = "获取交通条件")
    public HttpResult getById(Integer id) {
        ExamineMatchingTraffic examineMatchingTraffic = null;
        try {
            if (id!=null){
                examineMatchingTraffic = examineMatchingTrafficService.getMatchingTrafficById(id);
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return HttpResult.newCorrectResult(examineMatchingTraffic);
    }

    @ResponseBody
    @RequestMapping(value = "/getExamineMatchingTrafficList",method = {RequestMethod.GET},name = "交通条件列表")
    public BootstrapTableVo getExamineMatchingTrafficList(String name, Integer examineType,String type,Integer declareId) {
        BootstrapTableVo vo = null;
        try {
            ExamineMatchingTraffic examineMatchingTraffic = new ExamineMatchingTraffic();
            if (!StringUtils.isEmpty(name)){
                examineMatchingTraffic.setName(name);
            }
            if (!ObjectUtils.isEmpty(examineType)){
                examineMatchingTraffic.setExamineType(examineType);
            }
            if (!StringUtils.isEmpty(type)){
                examineMatchingTraffic.setType(type);
            }
            if (declareId!=null){
                examineMatchingTraffic.setDeclareId(declareId);
            }
            vo = examineMatchingTrafficService.getExamineMatchingTrafficList(examineMatchingTraffic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s",e1.getMessage()),e1);
            return null;
        }
        return vo;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteExamineMatchingTrafficById",method = {RequestMethod.POST},name = "删除交通条件")
    public HttpResult delete(Integer id) {
        try {
            if (id!=null){
                return HttpResult.newCorrectResult(examineMatchingTrafficService.deleteMatchingTraffic(id));
            }
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/saveAndUpdateExamineMatchingTraffic",method = {RequestMethod.POST},name = "更新交通条件")
    public HttpResult save(ExamineMatchingTraffic examineMatchingTraffic){
        try {
            if (examineMatchingTraffic.getId()==null || examineMatchingTraffic.getId().equals(0)){
                examineMatchingTrafficService.addMatchingTraffic(examineMatchingTraffic);
            }else {
                examineMatchingTrafficService.updateMatchingTraffic(examineMatchingTraffic);
            }
            return HttpResult.newCorrectResult("保存 success!");
        } catch (Exception e) {
            logger.error(String.format("exception: %s",e.getMessage()),e);
            return HttpResult.newErrorResult("保存异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/estate_distance",method = {RequestMethod.GET},name = "获取距离")
    public HttpResult estate_distance(Integer id) {
        try {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_DISTANCE);
            return HttpResult.newCorrectResult(baseDataDic);
        } catch (Exception e1) {
            logger.error(String.format("exception: %s"+e1.getMessage()),e1);
            return HttpResult.newErrorResult(String.format("异常! %s",e1.getMessage()));
        }
    }
}
