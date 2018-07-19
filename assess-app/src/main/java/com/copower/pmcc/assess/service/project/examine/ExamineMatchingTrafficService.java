package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineMatchingTrafficDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingTraffic;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineMatchingTrafficVo;
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
 * @Date: 2018/7/18 17:31
 * @Description:
 * 交通条件
 */
@Service
public class ExamineMatchingTrafficService {//ExamineMatchingTrafficTypeEnum

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineMatchingTrafficDao examineMatchingTrafficDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineMatchingTraffic getMatchingTrafficById(Integer id) {
        return examineMatchingTrafficDao.getMatchingTrafficById(id);
    }

    /**
     * 获取数据列表
     * @param examineMatchingTraffic
     * @return
     */
    public List<ExamineMatchingTraffic> getMatchingTrafficList(ExamineMatchingTraffic examineMatchingTraffic) {
        return examineMatchingTrafficDao.getMatchingTrafficList(examineMatchingTraffic);
    }

    public BootstrapTableVo getExamineMatchingTrafficList(ExamineMatchingTraffic examineMatchingTraffic){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineMatchingTrafficVo> vos = Lists.newArrayList();
        getMatchingTrafficList(examineMatchingTraffic).stream().forEach(oo -> vos.add(getExamineMatchingTrafficVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineMatchingTrafficVo>() : vos);
        return vo;
    }

    public ExamineMatchingTrafficVo getExamineMatchingTrafficVo(ExamineMatchingTraffic examineMatchingTraffic){
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_DISTANCE);
        ExamineMatchingTrafficVo vo = new ExamineMatchingTrafficVo();
        if (examineMatchingTraffic.getDistance()!=null){
            for (BaseDataDic base:baseDataDic){
                if (base.getId().equals(examineMatchingTraffic.getDistance())){
                    vo.setDistanceName(base.getName());
                }
            }
        }
        BeanUtils.copyProperties(examineMatchingTraffic,vo);
        return vo;
    }

    /**
     * 新增
     * @param examineMatchingTraffic
     * @return
     */
    public boolean addMatchingTraffic(ExamineMatchingTraffic examineMatchingTraffic) {
        examineMatchingTraffic.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineMatchingTraffic.getDeclareId())){
            examineMatchingTraffic.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineMatchingTraffic.getExamineType())){
            examineMatchingTraffic.setExamineType(0);
        }
        return examineMatchingTrafficDao.addMatchingTraffic(examineMatchingTraffic);
    }

    /**
     * 编辑
     * @param examineMatchingTraffic
     * @return
     */
    public boolean updateMatchingTraffic(ExamineMatchingTraffic examineMatchingTraffic) {
        return examineMatchingTrafficDao.updateMatchingTraffic(examineMatchingTraffic);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteMatchingTraffic(Integer id){
        return examineMatchingTrafficDao.deleteMatchingTraffic(id);
    }

}
