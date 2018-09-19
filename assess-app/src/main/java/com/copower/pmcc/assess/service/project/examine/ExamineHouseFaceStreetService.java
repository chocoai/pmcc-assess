package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineHouseFaceStreetDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseFaceStreet;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineHouseFaceStreetVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/25 14:48
 * @Description:临街（路）状况
 */
@Service
public class ExamineHouseFaceStreetService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineHouseFaceStreetDao examineHouseFaceStreetDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public ExamineHouseFaceStreet getExamineHouseFaceStreetById(Integer id) {
        return examineHouseFaceStreetDao.getHouseFaceStreetById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineHouseFaceStreet
     * @return
     */
    public List<ExamineHouseFaceStreet> getExamineHouseFaceStreetList(ExamineHouseFaceStreet examineHouseFaceStreet) {
        return examineHouseFaceStreetDao.getHouseFaceStreetList(examineHouseFaceStreet);
    }

    public BootstrapTableVo getExamineHouseFaceStreetLists(ExamineHouseFaceStreet examineHouseFaceStreet) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineHouseFaceStreetVo> vos = Lists.newArrayList();
        getExamineHouseFaceStreetList(examineHouseFaceStreet).stream().forEach(oo -> vos.add(getExamineHouseFaceStreetVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineHouseFaceStreetVo>() : vos);
        return vo;
    }

    public ExamineHouseFaceStreetVo getExamineHouseFaceStreetVo(ExamineHouseFaceStreet examineHouseFaceStreet) {
        ExamineHouseFaceStreetVo vo = new ExamineHouseFaceStreetVo();
        BeanUtils.copyProperties(examineHouseFaceStreet, vo);
        if (examineHouseFaceStreet.getStreetLevel() != null) {
            vo.setStreetLevelName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_STREET_LEVEL, examineHouseFaceStreet.getStreetLevel()));
        }
        if (examineHouseFaceStreet.getTrafficFlow() != null) {
            vo.setTrafficFlowName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_TRAFFIC_FLOW, examineHouseFaceStreet.getTrafficFlow()));
        }
        if (examineHouseFaceStreet.getVisitorsFlowrate() != null) {
            vo.setVisitorsFlowrateName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_VISITORS_FLOWRATE, examineHouseFaceStreet.getVisitorsFlowrate()));
        }
        return vo;
    }

    private String getValue(String key, Integer v) {
        StringBuilder builder = new StringBuilder(1024);
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(key);
        if (baseDataDic.size() >= 1) {
            if (v != null) {
                for (BaseDataDic base : baseDataDic) {
                    if (base.getId().intValue() == v.intValue()) {
                        builder.append(base.getName());
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * 新增
     *
     * @param examineHouseFaceStreet
     * @return
     */
    public boolean addExamineHouseFaceStreet(ExamineHouseFaceStreet examineHouseFaceStreet) {
        examineHouseFaceStreet.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineHouseFaceStreet.getDeclareId())) {
            examineHouseFaceStreet.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineHouseFaceStreet.getExamineType())) {
            examineHouseFaceStreet.setExamineType(0);
        }
        return examineHouseFaceStreetDao.addHouseFaceStreet(examineHouseFaceStreet);
    }

    /**
     * 编辑
     *
     * @param examineHouseFaceStreet
     * @return
     */
    public boolean updateExamineHouseFaceStreet(ExamineHouseFaceStreet examineHouseFaceStreet) {
        return examineHouseFaceStreetDao.updateHouseFaceStreet(examineHouseFaceStreet);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineHouseFaceStreet(Integer id) {
        return examineHouseFaceStreetDao.deleteHouseFaceStreet(id);
    }
}
