package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineEstateSupplyDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineEstateSupply;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineEstateSupplyVo;
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
 * @Date: 2018/7/20 09:05
 * @Description:
 */
@Service
public class ExamineEstateSupplyService {
    
    @Autowired
    private ExamineEstateSupplyDao examineEstateSupplyDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 获取数据信息
     * @param id
     * @return
     */
    public ExamineEstateSupply getExamineEstateSupplyById(Integer id) {
        return examineEstateSupplyDao.getExamineEstateSupplyById(id);
    }

    /**
     * 获取数据列表
     * @param examineEstateSupply
     * @return
     */
    public List<ExamineEstateSupply> getExamineEstateSupplyList(ExamineEstateSupply examineEstateSupply) {
        return examineEstateSupplyDao.getExamineEstateSupplyList(examineEstateSupply);
    }

    public BootstrapTableVo getExamineEstateNetworkList(ExamineEstateSupply oo){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineEstateSupplyVo> vos = Lists.newArrayList();
        getExamineEstateSupplyList(oo).stream().forEach(obj -> vos.add(getExamineEstateSupplyVo(obj)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineEstateSupplyVo>() : vos);
        return vo;
    }

    private ExamineEstateSupplyVo getExamineEstateSupplyVo(ExamineEstateSupply oo){
        ExamineEstateSupplyVo vo = new ExamineEstateSupplyVo();
        BeanUtils.copyProperties(oo,vo);
        if (!StringUtils.isEmpty(oo.getGrade())){
            if (org.apache.commons.lang.StringUtils.isNumeric(oo.getGrade())){
                List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SUPPLIER_GRADE);
                if (baseDataDic.size() >= 1){
                    for (BaseDataDic base:baseDataDic){
                        if (base.getId().equals(Integer.parseInt(oo.getGrade()))){
                            vo.setGradeName(base.getName());
                        }
                    }
                }
            }
        }
        if (!StringUtils.isEmpty(oo.getLineGrade())){
            if (org.apache.commons.lang.StringUtils.isNumeric(oo.getLineGrade())){
                List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_LINE_WATER_SUPPLY_PIPE_GRADE);
                if (baseDataDic.size() >= 1){
                    for (BaseDataDic base:baseDataDic){
                        if (base.getId().equals(Integer.parseInt(oo.getLineGrade()))){
                            vo.setLineGradeName(base.getName());
                        }
                    }
                }
            }
        }
        return vo;
    }


    /**
     * 新增
     * @param examineEstateSupply
     * @return
     */
    public boolean addExamineEstateSupply(ExamineEstateSupply examineEstateSupply) {
        examineEstateSupply.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineEstateSupply.getDeclareId())){
            examineEstateSupply.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineEstateSupply.getExamineType())){
            examineEstateSupply.setExamineType(0);
        }
        return examineEstateSupplyDao.addExamineEstateSupply(examineEstateSupply);
    }

    /**
     * 编辑
     * @param examineEstateSupply
     * @return
     */
    public boolean updateExamineEstateSupply(ExamineEstateSupply examineEstateSupply) {
        return examineEstateSupplyDao.updateExamineEstateSupply(examineEstateSupply);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public boolean deleteExamineEstateSupply(Integer id){
        return examineEstateSupplyDao.deleteExamineEstateSupply(id);
    }

}
