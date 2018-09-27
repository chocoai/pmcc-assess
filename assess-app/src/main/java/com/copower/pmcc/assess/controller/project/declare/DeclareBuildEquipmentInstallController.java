package com.copower.pmcc.assess.controller.project.declare;

import com.copower.pmcc.assess.service.project.declare.DeclareBuildEquipmentInstallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Auther: zch
 * @Date: 2018/9/27 12:05
 * @Description:在建工程（设备安装）
 */
@Controller
public class DeclareBuildEquipmentInstallController {
    @Autowired
    private DeclareBuildEquipmentInstallService declareBuildEquipmentInstallService;
}
