package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.common.FileUtils;
import com.copower.pmcc.assess.dal.basis.dao.data.SysFeedbackDao;
import com.copower.pmcc.assess.dal.basis.entity.SysFeedback;
import com.copower.pmcc.assess.dto.output.data.SysFeedbackVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcAttachmentService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.support.mvc.response.HttpResult;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.copower.pmcc.erp.http.HttpApiRequest;
import com.copower.pmcc.erp.http.RequestParam;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class SysFeedbackService {
    @Autowired
    private SysFeedbackDao sysFeedbackDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private ErpRpcAttachmentService erpRpcAttachmentService;

    public SysFeedbackVo getBySysFeedbackId(Integer id) {
        SysFeedback object = sysFeedbackDao.getSingleObject(id);
        return getSysFeedbackVo(object);
    }

    /**
     * 获取列表
     *
     * @return
     */
    public BootstrapTableVo getListVos(Integer systemType, String questionTitle, String feedbackPerson, Integer status, String creator) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ArrayList<SysFeedbackVo> vos = new ArrayList<>();
        List<SysFeedback> sysFeedbackList = sysFeedbackDao.getSysFeedbackList(systemType, questionTitle, feedbackPerson, status, creator);
        if (CollectionUtils.isNotEmpty(sysFeedbackList)) {
            for (SysFeedback item : sysFeedbackList) {
                vos.add(getSysFeedbackVo(item));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<SysFeedbackVo>() : vos);
        return vo;
    }


    public SysFeedbackVo getSysFeedbackVo(SysFeedback sysFeedback) {
        if (sysFeedback == null) return null;
        SysFeedbackVo sysFeedbackVo = new SysFeedbackVo();
        BeanUtils.copyProperties(sysFeedback, sysFeedbackVo);
        if (sysFeedback.getSystemType() != null) {
            sysFeedbackVo.setSystemTypeName(baseDataDicService.getNameById(sysFeedback.getSystemType()));
        }
        if (sysFeedback.getUrgencyLevel() != null) {
            sysFeedbackVo.setUrgencyLevelName(baseDataDicService.getNameById(sysFeedback.getUrgencyLevel()));

        }
        if (sysFeedback.getStatus() == 0) {
            sysFeedbackVo.setStatusName("新添加");
        }
        if (sysFeedback.getStatus() == 1) {
            sysFeedbackVo.setStatusName("待处理");
        }
        if (sysFeedback.getStatus() == 2) {
            sysFeedbackVo.setStatusName("已处理");
        }
        if (StringUtils.isNotEmpty(sysFeedback.getCreator())) {
            sysFeedbackVo.setFeedbackPersonName(publicService.getUserNameByAccount(sysFeedback.getFeedbackPerson()));
        }
        sysFeedbackVo.setCompanyName(publicService.getCurrentCompany().getName());
        return sysFeedbackVo;
    }

    /**
     * 保存
     *
     * @param sysFeedback
     * @throws BusinessException
     */
    public boolean saveSysFeedbackReturnId(SysFeedback sysFeedback) throws Exception {
        //找到对应的attachment下载到本地
        //进行base64编译，替换src路径
        String content = sysFeedback.getDeatilDescription();
        String regex = "(?<=(title=\"))[^\"]*?(?=\")";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        List<String> paths = Lists.newArrayList();
        while (m.find()) {
            String result = m.group();
            SysAttachmentDto deleteExample = new SysAttachmentDto();
            deleteExample.setTableName("tb_uedit");
            deleteExample.setFtpFileName(result);
            deleteExample.setAppKey("pmcc-all");
            List<SysAttachmentDto> attachmentList = erpRpcAttachmentService.getAttachmentList(deleteExample);
            if (CollectionUtils.isNotEmpty(attachmentList)) {
                paths.add(baseAttachmentService.downloadFtpFileToLocal(attachmentList.get(0).getId()));
            } else {
                paths.add("");
            }
        }

        String regex2 = "(?<=(src=\"))[^\"]*?(?=\")";
        Pattern p2 = Pattern.compile(regex2);
        Matcher m2 = p2.matcher(content);
        Integer i = 0;
        while (m2.find()) {
            String result = m2.group();
            String encode = FileUtils.base64Encode(paths.get(i));
            content = content.replaceAll(result, String.format("%s%s", "data:img/jpg;base64,", encode));
            i++;
        }

        sysFeedback.setDetailEncode(content);
        if (sysFeedback.getId() == null || sysFeedback.getId().equals(0)) {
            sysFeedback.setCreator(processControllerComponent.getThisUser());
            return sysFeedbackDao.addObject(sysFeedback);
        } else {
            return sysFeedbackDao.updateObject(sysFeedback);
        }

    }


    /**
     * 删除
     *
     * @param id
     * @throws BusinessException
     */
    public boolean deleteSysFeedback(Integer id) throws BusinessException {
        return sysFeedbackDao.deleteObject(id);
    }

    /**
     * 问题提交
     *
     * @param ids
     */
    public void submitQuestion(List<Integer> ids) throws Exception {
        if (CollectionUtils.isNotEmpty(ids)) {
            List<SysFeedbackVo> feedbackList = LangUtils.transform(ids, o -> getSysFeedbackVo(sysFeedbackDao.getSingleObject(o)));
            String jsonString = JSON.toJSONString(feedbackList);
            //提交到pmcc-develop
            RequestParam param = new RequestParam();
            param.setCompanyKey("123");
            param.setAppSecret("123");
            param.setUrl("http://dev.pmcc.com/pmcc-develop/devProjectFeedback/acceptQuestions");
            Map<String, String> map = new HashMap<>();
            map.put("jsonData", jsonString);
            param.setParams(map);
            HttpResult httpResult = HttpApiRequest.doPost(param);
            if (httpResult.getRet()) {
                for (Integer id : ids) {
                    SysFeedbackVo feedbackVo = this.getBySysFeedbackId(id);
                    //状态变为待处理
                    feedbackVo.setStatus(1);
                    sysFeedbackDao.updateObject(feedbackVo);
                }
            }
        }
    }

    /**
     * 问题更新
     */
    public void updateQuestions() throws Exception {
        //获取我所有待处理任务
        SysFeedback sysFeedback = new SysFeedback();
        sysFeedback.setStatus(1);
        sysFeedback.setCreator(processControllerComponent.getThisUser());
        List<SysFeedback> list = sysFeedbackDao.getListObject(sysFeedback);
        List<Integer> ids = LangUtils.transform(list, o -> o.getId());
        String jsonString = JSON.toJSONString(ids);
        //从pmcc-develop中拉取
        RequestParam param = new RequestParam();
        param.setCompanyKey("123");
        param.setAppSecret("123");
        param.setUrl("http://dev.pmcc.com/pmcc-develop/devProjectFeedback/reverseQuestions");
        Map<String, String> map = new HashMap<>();
        map.put("stringIds", jsonString);
        param.setParams(map);
        HttpResult httpResult = HttpApiRequest.doPost(param);
        if (httpResult.getRet()) {
            String resultData = httpResult.getData().toString();
            List<SysFeedback> reverseQuestions = JSON.parseArray(resultData, SysFeedback.class);
            if (CollectionUtils.isNotEmpty(reverseQuestions)) {
                for (SysFeedback temp : reverseQuestions) {
                    SysFeedback feedback = sysFeedbackDao.getSingleObject(temp.getId());
                    feedback.setStatus(2);
                    feedback.setDisposeScheme(temp.getDisposeScheme());
                    sysFeedbackDao.updateObject(feedback);
                }
            }
        }
    }
}
