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
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append("{") ;
        stringBuilder.append("时间:").append(DateUtils.format(new Date(), DateUtils.DATETIME_PATTERN)).append("操作人,").append(commonService.thisUserAccount());
        stringBuilder.append(StringUtils.isNotBlank(errorName)?errorName:"").append("异常").append(",").append("异常具体原因");
        //默认jdk刚好第一个是调用方法  获取的是堆栈信息
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        if (stackTraceElement != null) {
            stringBuilder.append("[");
            stringBuilder.append("declaringClass:").append(stackTraceElement.getClassName());
            stringBuilder.append("methodName:").append(stackTraceElement.getMethodName());
            stringBuilder.append("fileName:").append(stackTraceElement.getFileName());
            stringBuilder.append("lineNumber:").append(stackTraceElement.getLineNumber());
            stringBuilder.append("message:").append(e.getMessage());
            stringBuilder.append("]");
        }
        stringBuilder.append("}") ;
        log.debug(stringBuilder.toString());
        log.error(stringBuilder.toString(), e);
    }
}
