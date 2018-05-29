package com.copower.pmcc.assess.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kings on 2018-5-29.
 */
@Service
public class PublicService {

    /**
     * 获取到说明的视图信息
     * @param title
     * @param message
     * @return
     */
    public ModelAndView getExplainPage(String title, String message) {
        ModelAndView modelAndView = new ModelAndView("/base/explainPage");
        modelAndView.addObject("title",title);
        modelAndView.addObject("message",message);
        return modelAndView;
    }
}
