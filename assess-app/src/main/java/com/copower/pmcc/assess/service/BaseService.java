package com.copower.pmcc.assess.service;

import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by kings on 2018-11-16.
 */
@Service
public class BaseService {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommonService commonService;

    /**
     * 自己拼接的异常打印数据
     * @param e
     */
    public void writeExceptionInfo(Exception e){
        writeExceptionInfo(e,null);
    }

    public void writeExceptionInfo(Exception e,String errorName){
        writeExceptionInfo(log,e,errorName) ;
    }

    /**
     * 编写一个日志书写模式
     * @param logger
     * @param e
     * @param errorName
     */
    public void writeExceptionInfo(Logger logger,Exception e,String errorName){
        StringBuilder stringBuilder = new StringBuilder(8);
        final String filling = "\r " ;
        stringBuilder.append("{").append(StringUtils.repeat(filling,1)) ;
        stringBuilder.append("时间:").append(DateUtils.format(new Date(), DateUtils.DATETIME_PATTERN)).append(StringUtils.repeat(filling,1));
        try {
            stringBuilder.append("操作人,").append(commonService.thisUserAccount()).append(StringUtils.repeat(filling,1)) ;
        } catch (Exception e1) {
            //可能不能获取当前登陆人
        }
        stringBuilder.append(StringUtils.isNotBlank(errorName)?errorName:"").append("异常").append(",").append("异常具体原因").append(StringUtils.repeat(filling,1));
        //默认jdk刚好第一个是调用方法  获取的是堆栈信息
        if (e.getStackTrace().length != 0){
            StackTraceElement stackTraceElement = e.getStackTrace()[0];
            if (stackTraceElement != null) {
                stringBuilder.append("[").append(StringUtils.repeat(filling,1));
                stringBuilder.append("异常class:").append(stackTraceElement.getClassName()).append(StringUtils.repeat(filling,1));
                stringBuilder.append("方法名称:").append(stackTraceElement.getMethodName()).append(StringUtils.repeat(filling,1));
                stringBuilder.append("字段名称:").append(stackTraceElement.getFileName()).append(StringUtils.repeat(filling,1));
                stringBuilder.append("行号:").append(stackTraceElement.getLineNumber()).append(StringUtils.repeat(filling,1));
                stringBuilder.append("异常:").append(e.getMessage()).append(StringUtils.repeat(filling,1));
                stringBuilder.append("]");
            }
        }
        stringBuilder.append("}").append(StringUtils.repeat(filling,1)) ;
        logger.debug(stringBuilder.toString());
        logger.error(stringBuilder.toString(), e);
    }
}
