package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.common.DeclareRecordItems;
import com.copower.pmcc.assess.dal.entity.DataBestUseDescription;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dto.output.project.DeclareRecordVo;
import com.copower.pmcc.assess.service.data.DataBestUseDescriptionService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 评估工作方案
 * Created by 13426 on 2018/5/15.
 */
@Service
public class SchemeAssistService {

    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private DataBestUseDescriptionService dataBestUseDescriptionService;

    public List<DataBestUseDescription> dataBestUseDescriptionList(){
        return dataBestUseDescriptionService.dataBestUseDescriptionList();
    }

    public DeclareRecordItems items(){
        return declareRecordService.items();
    }

    public Collection<DeclareRecord> list(){
        Collection<DeclareRecord> declareRecords = new ArrayList<>();
        declareRecords.addAll(declareRecordService.queryProvinceCityAll());
        declareRecords.addAll(declareRecordService.queryProvinceCityDistrictAll());
        return declareRecords;
    }
    public BootstrapTableVo vos(){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRecordVo> vos = new ArrayList<>();
        list().parallelStream().forEach(declareRecord -> vos.add(change(declareRecord)));
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DeclareRecordVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public DeclareRecordVo change(DeclareRecord declareRecord){
        DeclareRecordVo vo = new DeclareRecordVo();
        BeanUtils.copyProperties(declareRecord,vo);
        return vo;
    }

}
