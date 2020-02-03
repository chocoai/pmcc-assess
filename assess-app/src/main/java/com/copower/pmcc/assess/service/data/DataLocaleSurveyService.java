package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataLocaleSurveyDao;
import com.copower.pmcc.assess.dal.basis.entity.DataLocaleSurvey;
import com.copower.pmcc.assess.dto.output.data.DataLocaleSurveyVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/7 09:53
 * @Description:税率配置
 */
@Service
public class DataLocaleSurveyService {
    @Autowired
    private DataLocaleSurveyDao dataLocaleSurveyDao;
    @Autowired
    private CommonService commonService;

    @Autowired
    private ProcessControllerComponent processControllerComponent;

    public Integer saveAndUpdateDataLocaleSurvey(DataLocaleSurvey dataLocaleSurvey) {
        if (dataLocaleSurvey.getType() == null)
            dataLocaleSurvey.setType(1);
        if (dataLocaleSurvey.getId() == null) {
            dataLocaleSurvey.setCreator(commonService.thisUserAccount());
            return dataLocaleSurveyDao.addDataLocaleSurvey(dataLocaleSurvey);
        } else {
            dataLocaleSurveyDao.updateDataLocaleSurvey(dataLocaleSurvey);
            return null;
        }
    }

    public DataLocaleSurvey getDataLocaleSurveyById(Integer id) {
        return dataLocaleSurveyDao.getDataLocaleSurveyById(id);
    }

    public BootstrapTableVo getDataLocaleSurveyListVos(Integer type, String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLocaleSurveyVo> vos = getDataLocaleSurveyVos(type, name,null);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public BootstrapTableVo getDataLocaleSurveyListBySurvey(Integer type, String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        String account = processControllerComponent.getThisUser();
        List<DataLocaleSurveyVo> vos = null;
        if(type==1){
            vos = getDataLocaleSurveyVos(type, name,account);
        }else {
            vos = getDataLocaleSurveyVos(type, name, null);
        }
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DataLocaleSurveyVo> getDataLocaleSurveyVos(Integer type, String name,String account) {
        List<DataLocaleSurvey> dataLocaleSurveys = dataLocaleSurveyDao.getDataLocaleSurveyList(type, name,account);
        List<DataLocaleSurveyVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataLocaleSurveys)) {
            for (DataLocaleSurvey landLevel : dataLocaleSurveys) {
                vos.add(getDataLocaleSurveyVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDataLocaleSurvey(DataLocaleSurvey dataLocaleSurvey) {
        dataLocaleSurveyDao.removeDataLocaleSurvey(dataLocaleSurvey);
    }


    public DataLocaleSurveyVo getDataLocaleSurveyVo(DataLocaleSurvey dataLocaleSurvey) {
        DataLocaleSurveyVo vo = new DataLocaleSurveyVo();
        BeanUtils.copyProperties(dataLocaleSurvey, vo);
        if (0 == dataLocaleSurvey.getType()) {
            vo.setTypeName("系统");
        } else {
            vo.setTypeName("个人");
        }

        return vo;
    }
}
