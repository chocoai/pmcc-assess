package com.copower.pmcc.assess.controller.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReportFileCustomDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeFunction;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileCustom;
import com.copower.pmcc.assess.dal.basis.entity.SchemeReportFileItem;
import com.copower.pmcc.assess.service.project.SchemeReportFileService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeFunctionService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by kings on 2018-10-25.
 */
@Controller
@RequestMapping("/scheme")
public class SchemeController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SchemeJudgeFunctionService schemeJudgeFunctionService;
    @Autowired
    private SchemeReportFileService schemeReportFileService;
    @Autowired
    private SchemeReportFileCustomDao schemeReportFileCustomDao;

    @ResponseBody
    @RequestMapping(value = "/changeFunctionContent", name = "评估方法原因及思路更新 ", method = RequestMethod.POST)
    public HttpResult changeFunctionContent(String formData) {
        try {
            List<SchemeJudgeFunction> judgeFunctionList = JSON.parseArray(formData, SchemeJudgeFunction.class);
            schemeJudgeFunctionService.changeFunctionContent(judgeFunctionList);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
        return HttpResult.newCorrectResult();
    }

    @ResponseBody
    @RequestMapping(value = "/getLiveSituationAll", name = "获取查勘中所有的实况图片 ", method = RequestMethod.POST)
    public HttpResult getLiveSituationAll(Integer schemeJudgeObjectId) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.getLiveSituationAll(schemeJudgeObjectId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getLiveSituationByCertifyPart", name = "根据类型获取委估对象下实况图片 ", method = RequestMethod.POST)
    public HttpResult getLiveSituationByCertifyPart(Integer certifyPart,Integer schemeJudgeObjectId) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.getLiveSituationByCertifyPart(certifyPart,schemeJudgeObjectId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/correspondingSitePic", name = "获取对应位置查勘图片 ", method = RequestMethod.POST)
    public HttpResult getLiveSituationAll(Integer schemeJudgeObjectId,Integer certifyPartCategory) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.correspondingSitePic(schemeJudgeObjectId, certifyPartCategory));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getListBySchemeJudgeObjectId", name = "获取实况图片", method = RequestMethod.POST)
    public HttpResult getListBySchemeJudgeObjectId(Integer schemeJudgeObjectId) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.getListBySchemeJudgeObjectId(schemeJudgeObjectId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getSchemeReportFileItemById", name = "获取一个实况图片 ", method = RequestMethod.GET)
    public HttpResult getSchemeReportFileItemById(Integer id) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.getSchemeReportFileItemById(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/reportFileEditName", name = "选择的图片修改名称 ", method = RequestMethod.POST)
    public HttpResult reportFileEditName(Integer id, String newName, Integer newSorting) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.reportFileEditName(id, newName, newSorting));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveToReportFileItem", name = "添加实况图片 ", method = RequestMethod.POST)
    public HttpResult saveToReportFileItem(SchemeReportFileItem schemeReportFileItem) {
        try {
            schemeReportFileService.saveToReportFileItem(schemeReportFileItem);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/selectLiveSituation", name = "选择实况图片 ", method = RequestMethod.POST)
    public HttpResult selectLiveSituation(Integer attachmentId, Integer schemeJudgeObjectId, String fileName) {
        try {
            schemeReportFileService.selectLiveSituation(attachmentId, schemeJudgeObjectId, fileName);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/selectCorrespondingSitePic", name = "选择指定位置查勘图片 ", method = RequestMethod.POST)
    public HttpResult selectCorrespondingSitePic(Integer attachmentId, Integer reportFileItemId) {
        try {
            schemeReportFileService.selectCorrespondingSitePic(attachmentId, reportFileItemId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/removeLiveSituation", name = "移除实况图片 ", method = RequestMethod.POST)
    public HttpResult removeLiveSituation(Integer id) {
        try {
            schemeReportFileService.removeLiveSituation(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getReportFileCustomList", name = "获取自定义块 ", method = RequestMethod.POST)
    public HttpResult getReportFileCustomList(Integer schemeJudgeObjectId) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.getReportFileCustomList(schemeJudgeObjectId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addReportFileCustom", name = "新增自定义块 ", method = RequestMethod.POST)
    public HttpResult addReportFileCustom(SchemeReportFileCustom schemeReportFileCustom) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.addReportFileCustom(schemeReportFileCustom));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteReportFileCustom", name = "删除自定义块 ", method = RequestMethod.POST)
    public HttpResult deleteReportFileCustom(Integer id) {
        try {
            schemeReportFileService.deleteReportFileCustom(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/affirmPictureTemplate", name = "确认模板 ", method = RequestMethod.POST)
    public HttpResult affirmPictureTemplate(Integer masterId, Integer schemeJudgeObjectId) {
        try {
            schemeReportFileService.affirmPictureTemplate(masterId, schemeJudgeObjectId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/saveToTemplate", name = "确认模板 ", method = RequestMethod.POST)
    public HttpResult saveToTemplate(String name, Integer schemeJudgeObjectId) {
        try {
            schemeReportFileService.saveToTemplate(name, schemeJudgeObjectId);
            return HttpResult.newCorrectResult();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }


    @ResponseBody
    @RequestMapping(value = "/generateLiveSituation", name = "预览实况照片报告 ", method = RequestMethod.POST)
    public HttpResult generateLiveSituation(Integer schemeJudgeObjectId) {
        try {
            return HttpResult.newCorrectResult(schemeReportFileService.generateLiveSituation(schemeJudgeObjectId));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return HttpResult.newErrorResult(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/reloadSchemeJudgeObjectList", method = {RequestMethod.GET}, name = "获取项目完整并没合并估价对象")
    public BootstrapTableVo reloadSchemeJudgeObjectList(Integer projectId, String name, String certName,String seat) {
        return schemeReportFileService.reloadSchemeJudgeObjectListByQuery(projectId, name, certName, seat);
    }
}
