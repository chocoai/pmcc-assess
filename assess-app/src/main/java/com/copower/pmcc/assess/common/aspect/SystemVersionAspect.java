package com.copower.pmcc.assess.common.aspect;

import com.copower.pmcc.assess.common.enums.BaseParameterEnum;
import com.copower.pmcc.assess.service.base.BaseParameterService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wangpc on 2019-9-11.
 */
@Aspect
@Component
@Order(0)
public class SystemVersionAspect {
    private static final Logger logger = LoggerFactory.getLogger(SystemVersionAspect.class);
    @Autowired
    private BaseParameterService baseParameterService;

    /**
     * 添加系统版本号
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.copower.pmcc.*.controller..*.* (..) )")
    public Object sysAssessVersion(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed = joinPoint.proceed();
        if (proceed instanceof ModelAndView) {
            ModelAndView modelAndView = (ModelAndView) proceed;
            modelAndView.addObject("assessVersion", baseParameterService.getParameterValues(BaseParameterEnum.SYS_ASSESS_VERSION.getParameterKey()));
            return modelAndView;
        }
        return proceed;
    }
}
