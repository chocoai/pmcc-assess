package com.copower.pmcc.assess.service.ureport;

import com.bstek.ureport.build.BeanPageDataSet;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by kings on 2019-8-2.
 */
@Service("myProjectFinanceService")
public class MyProjectFinancetService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UReportService uReportService;
    @Autowired
    private CommonService commonService;

    /**
     * 项目开票收款报表
     *
     * @param dsname
     * @param datasetName
     * @param maps
     * @return
     */
    public BeanPageDataSet getMyProjectFinanceVoList(String dsname, String datasetName, Map<String, Object> maps) throws Exception {
       return uReportService.getProjectFinanceDataSet(maps,commonService.thisUserAccount());
    }
}
