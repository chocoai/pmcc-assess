package com.copower.pmcc.assess.controller.project.archives;


import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.ad.api.dto.AdPlaceFileGroupDto;
import com.copower.pmcc.ad.api.dto.AdPlaceFileItemDto;
import com.copower.pmcc.ad.api.dto.AdPlaceFileVolumeNumberDto;
import com.copower.pmcc.assess.service.project.archives.ProjectArchivesDataService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/projectArchives")
@Controller
public class ProjectArchivesController {
    @Autowired
    private ProjectArchivesDataService projectArchivesDataService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/getAdPlaceFileItemDtoListByParam", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getAdPlaceFileItemDtoListByParam(Integer publicProjectId,Integer groupId,String name,Integer fileType,Integer fileCategory,Boolean bisBinding) {
        return projectArchivesDataService.getAdPlaceFileItemDtoListByParam(publicProjectId, groupId, name, fileType, fileCategory, bisBinding);
    }

    @ResponseBody
    @RequestMapping(value = "/getAdPlaceFileGroupDtoListByParam", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getAdPlaceFileGroupDtoListByParam(AdPlaceFileGroupDto dto) {
        return projectArchivesDataService.getAdPlaceFileGroupDtoListByParam(dto);
    }


    @ResponseBody
    @RequestMapping(value = "/getAdPlaceFileVolumeNumberDtoListByParam", method = {RequestMethod.GET}, name = "获取列表")
    public BootstrapTableVo getAdPlaceFileVolumeNumberDtoListByParam(AdPlaceFileVolumeNumberDto dto) {
        if (dto == null){
            dto = new AdPlaceFileVolumeNumberDto() ;
        }
        return projectArchivesDataService.getAdPlaceFileVolumeNumberDtoListByParam(dto);
    }


    @ResponseBody
    @RequestMapping(value = "/saveAdPlaceFileItemDto", method = {RequestMethod.POST}, name = "更新")
    public HttpResult saveAdPlaceFileItemDto(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            AdPlaceFileItemDto dto = JSONObject.parseObject(formData, AdPlaceFileItemDto.class);
            projectArchivesDataService.saveAdPlaceFileItemDto(dto);
            return HttpResult.newCorrectResult(dto);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/saveAdPlaceFileGroupDto", method = {RequestMethod.POST}, name = "更新")
    public HttpResult saveAdPlaceFileGroupDto(String formData, Integer targetId) {
        try {
            AdPlaceFileGroupDto dto = JSONObject.parseObject(formData, AdPlaceFileGroupDto.class);
            projectArchivesDataService.saveAdPlaceFileGroupDto(dto);
            if (targetId != null && targetId != 0) {
                AdPlaceFileItemDto fileItemDto = projectArchivesDataService.getAdPlaceFileItemDtoById(targetId);
                if (fileItemDto != null){
                    fileItemDto.setGroupId(dto.getId());
                    projectArchivesDataService.saveAdPlaceFileItemDto(fileItemDto);
                }
            }
            return HttpResult.newCorrectResult(dto);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/saveAdPlaceFileVolumeNumberDto", method = {RequestMethod.POST}, name = "更新")
    public HttpResult saveAdPlaceFileVolumeNumberDto(String formData, @RequestParam(defaultValue = "false") boolean updateNull) {
        try {
            AdPlaceFileVolumeNumberDto dto = JSONObject.parseObject(formData, AdPlaceFileVolumeNumberDto.class);
            projectArchivesDataService.saveAdPlaceFileVolumeNumberDto(dto);
            return HttpResult.newCorrectResult(dto);
        } catch (Exception e) {
            logger.error(String.format("exception: %s", e.getMessage()), e);
            return HttpResult.newErrorResult("保存异常");
        }
    }


    @ResponseBody
    @RequestMapping(value = "/deleteAdPlaceFileItemDtoByIds", method = {RequestMethod.POST}, name = "删除")
    public HttpResult deleteAdPlaceFileItemDtoByIds(String id) {
        try {
            projectArchivesDataService.deleteAdPlaceFileItemDtoByIds(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAdPlaceFileGroupDtoByIds", method = {RequestMethod.POST}, name = "删除")
    public HttpResult deleteAdPlaceFileGroupDtoByIds(String id) {
        try {
            projectArchivesDataService.deleteAdPlaceFileGroupDtoByIds(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteAdPlaceFileVolumeNumberDtoByIds", method = {RequestMethod.POST}, name = "删除")
    public HttpResult deleteAdPlaceFileVolumeNumberDtoByIds(String id) {
        try {
            projectArchivesDataService.deleteAdPlaceFileVolumeNumberDtoByIds(id);
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getAdPlaceFileGroupDtoById", method = {RequestMethod.GET}, name = "get")
    public HttpResult getAdPlaceFileGroupDtoById(Integer id) {
        try {
            return HttpResult.newCorrectResult(200,projectArchivesDataService.getAdPlaceFileGroupDtoById(id));
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" + e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/symbol", method = {RequestMethod.GET}, name = "get 卷号")
    public HttpResult symbol(Integer ruleId) {
        try {
            return HttpResult.newCorrectResult(200,projectArchivesDataService.getSymbolDto(ruleId));
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" , e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }


    @ResponseBody
    @RequestMapping(value = "/getAdPlaceFileItemDtoValidList", method = {RequestMethod.GET}, name = "判断 卷号")
    public HttpResult getAdPlaceFileItemDtoValidList(Integer projectId) {
        try {
            return HttpResult.newCorrectResult(200,projectArchivesDataService.getAdPlaceFileItemDtoValidList(projectId));
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" , e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }


    @ResponseBody
    @RequestMapping(value = "/autoCreateProjectFileCompleteNow", method = {RequestMethod.POST}, name = "自动生成档案")
    public HttpResult autoCreateProjectFileCompleteNow(Integer projectId) {
        try {
            projectArchivesDataService.autoCreateProjectFileCompleteNow(projectId) ;
            return HttpResult.newCorrectResult();
        } catch (Exception e1) {
            logger.error(String.format("exception: %s" ,e1.getMessage()), e1);
            return HttpResult.newErrorResult(String.format("异常! %s", e1.getMessage()));
        }
    }

}
