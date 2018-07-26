package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineHouseIntelligentDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseIntelligent;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineHouseIntelligentVo;
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
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/7/25 18:10
 * @Description:电力通讯网络
 */
@Service
public class ExamineHouseIntelligentService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineHouseIntelligentDao examineHouseIntelligentDao;
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
    public ExamineHouseIntelligent getExamineHouseIntelligentById(Integer id) {
        return examineHouseIntelligentDao.getHouseIntelligentById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineHouseIntelligent
     * @return
     */
    public List<ExamineHouseIntelligent> getExamineHouseIntelligentList(ExamineHouseIntelligent examineHouseIntelligent) {
        return examineHouseIntelligentDao.getHouseIntelligentList(examineHouseIntelligent);
    }

    public BootstrapTableVo getExamineHouseIntelligentLists(ExamineHouseIntelligent examineHouseIntelligent) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineHouseIntelligentVo> vos = Lists.newArrayList();
        getExamineHouseIntelligentList(examineHouseIntelligent).stream().forEach(oo -> vos.add(getExamineHouseIntelligentVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineHouseIntelligentVo>() : vos);
        return vo;
    }

    public ExamineHouseIntelligentVo getExamineHouseIntelligentVo(ExamineHouseIntelligent examineHouseIntelligent) {
        ExamineHouseIntelligentVo vo = new ExamineHouseIntelligentVo();
        BeanUtils.copyProperties(examineHouseIntelligent, vo);
        if (examineHouseIntelligent.getWireErection() != null){
            vo.setWireErectionName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_WIRE_ERECTION_METHOD,examineHouseIntelligent.getWireErection()));
        }
        if (examineHouseIntelligent.getSwitchCircuit() != null){
            vo.setSwitchCircuitName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_SWITCH_CIRCUIT,examineHouseIntelligent.getSwitchCircuit()));
        }
        if (examineHouseIntelligent.getLampsLanterns() != null){
            vo.setLampsLanternsName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_LAMPS_LANTERNS,examineHouseIntelligent.getLampsLanterns()));
        }
        if (examineHouseIntelligent.getInternalCommunication() != null){
            vo.setInternalCommunicationName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_INTERNAL_COMMUNICATION,examineHouseIntelligent.getInternalCommunication()));
        }
        if (examineHouseIntelligent.getMonitoringSystem() != null){
            vo.setMonitoringSystemName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_MONITORING_SYSTEM,examineHouseIntelligent.getMonitoringSystem()));
        }
        if (examineHouseIntelligent.getIntelligentSystem() != null){
            vo.setIntelligentSystemName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_INTELLIGENT_SYSTEM,examineHouseIntelligent.getIntelligentSystem()));
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
     * @param examineHouseIntelligent
     * @return
     */
    public boolean addExamineHouseIntelligent(ExamineHouseIntelligent examineHouseIntelligent) {
        examineHouseIntelligent.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineHouseIntelligent.getDeclareId())) {
            examineHouseIntelligent.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineHouseIntelligent.getExamineType())) {
            examineHouseIntelligent.setExamineType(0);
        }
        return examineHouseIntelligentDao.addHouseIntelligent(examineHouseIntelligent);
    }

    /**
     * 编辑
     *
     * @param examineHouseIntelligent
     * @return
     */
    public boolean updateExamineHouseIntelligent(ExamineHouseIntelligent examineHouseIntelligent) {
        return examineHouseIntelligentDao.updateHouseIntelligent(examineHouseIntelligent);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineHouseIntelligent(Integer id) {
        return examineHouseIntelligentDao.deleteHouseIntelligent(id);
    }


}
