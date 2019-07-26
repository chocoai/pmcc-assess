package com.copower.pmcc.assess.service;

import com.copower.pmcc.bpm.api.dto.SysWorkLogVo;
import com.copower.pmcc.bpm.api.provider.BpmRpcToolsService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkLogService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BpmRpcToolsService bpmRpcToolsService;
    @Autowired
    private ApplicationConstant applicationConstant;

    public List<SysWorkLogVo> getWorkLogList(String dsname, String datasetName, Map<String, Object> maps) {
        String queryProjectName = "";
        String queryTitle = "";
        if (maps.get("queryProjectName") != null) {
            queryProjectName = (String) maps.get("queryProjectName");
        }
        if (maps.get("queryTitle") != null) {
            queryTitle = (String) maps.get("queryTitle");
        }
        BootstrapTableVo workLogList = bpmRpcToolsService.getWorkLogList(applicationConstant.getAppKey(), queryProjectName, queryTitle, 0, 0);
        List<SysWorkLogVo> list = workLogList.getRows();
        for (SysWorkLogVo item: list) {
            item.setContent(delHtmlTags(item.getContent()));
        }
        return list;
    }


    //去掉标签
    public String delHtmlTags(String htmlStr) {
        //定义script的正则表达式，去除js可以防止注入
        String scriptRegex = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        //定义style的正则表达式，去除style样式，防止css代码过多时只截取到css样式代码
        String styleRegex = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        //定义HTML标签的正则表达式，去除标签，只提取文字内容
        String htmlRegex = "<[^>]+>";
        //定义空格,回车,换行符,制表符
        String spaceRegex = "\\s*|\t|\r|\n";

        // 过滤script标签
        htmlStr = htmlStr.replaceAll(scriptRegex, "");
        // 过滤style标签
        htmlStr = htmlStr.replaceAll(styleRegex, "");
        // 过滤html标签
        htmlStr = htmlStr.replaceAll(htmlRegex, "");
        // 过滤空格等
        htmlStr = htmlStr.replaceAll(spaceRegex, "");

        htmlStr = htmlStr.replaceAll("&nbsp;", "");
        return htmlStr.trim(); // 返回文本字符串
    }
}
