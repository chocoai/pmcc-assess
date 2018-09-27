package com.copower.pmcc.assess.controller.project.declare;

import com.copower.pmcc.assess.service.project.declare.DeclareBuildEngineeringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Auther: zch
 * @Date: 2018/9/27 12:04
 * @Description:在建工程（土建）
 */
@Controller
public class DeclareBuildEngineeringController {
    @Autowired
    private DeclareBuildEngineeringService declareBuildEngineeringService;
}
