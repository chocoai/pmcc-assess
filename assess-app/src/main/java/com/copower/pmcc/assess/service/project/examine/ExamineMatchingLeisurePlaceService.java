package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineMatchingLeisurePlaceTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineMatchingLeisurePlaceDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineMatchingLeisurePlace;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineMatchingLeisurePlaceVo;
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
 * @Date: 2018/7/20 18:09
 * @Description:休闲场所-包含-购物-娱乐-餐饮
 */
@Service
public class ExamineMatchingLeisurePlaceService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineMatchingLeisurePlaceDao examineMatchingLeisurePlaceDao;
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
    public ExamineMatchingLeisurePlace getExamineMatchingLeisurePlaceById(Integer id) {
        return examineMatchingLeisurePlaceDao.getMatchingFinanceById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineMatchingLeisurePlace
     * @return
     */
    public List<ExamineMatchingLeisurePlace> getExamineMatchingLeisurePlaceList(ExamineMatchingLeisurePlace examineMatchingLeisurePlace) {
        return examineMatchingLeisurePlaceDao.getMatchingFinanceList(examineMatchingLeisurePlace);
    }

    public BootstrapTableVo getExamineMatchingLeisurePlaceLists(ExamineMatchingLeisurePlace examineMatchingLeisurePlace) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineMatchingLeisurePlaceVo> vos = Lists.newArrayList();
        getExamineMatchingLeisurePlaceList(examineMatchingLeisurePlace).stream().forEach(oo -> vos.add(getExamineMatchingLeisurePlaceVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineMatchingLeisurePlaceVo>() : vos);
        return vo;
    }

    public ExamineMatchingLeisurePlaceVo getExamineMatchingLeisurePlaceVo(ExamineMatchingLeisurePlace examineMatchingLeisurePlace) {
        ExamineMatchingLeisurePlaceVo vo = new ExamineMatchingLeisurePlaceVo();
        BeanUtils.copyProperties(examineMatchingLeisurePlace, vo);
        String key = examineMatchingLeisurePlace.getType();
        if (!StringUtils.isEmpty(key)) {
            if (key.equals(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey())) {
                if (examineMatchingLeisurePlace.getGrade() != null) {
                    vo.setGradeName(getValue(AssessExamineTaskConstant.ESTATE_SHOP_GRADE, examineMatchingLeisurePlace.getGrade()));
                }
                if (examineMatchingLeisurePlace.getCategory() != null) {
                    vo.setCategoryName(getValue(AssessExamineTaskConstant.ESTATE_SHOP_CATEGORY, examineMatchingLeisurePlace.getCategory()));
                }
            }
            if (key.equals(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getKey())) {
                if (examineMatchingLeisurePlace.getCategory() != null) {
                    vo.setCategoryName(getValue(AssessExamineTaskConstant.ESTATE_ENTERTAINMENT_CATEGORY, examineMatchingLeisurePlace.getCategory()));
                }
            }
            if (key.equals(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT.getKey())) {
                if (examineMatchingLeisurePlace.getCategory() != null) {
                    vo.setCategoryName(getValue(AssessExamineTaskConstant.ESTATE_DINING_CATEGORY, examineMatchingLeisurePlace.getCategory()));
                }
                if (examineMatchingLeisurePlace.getGrade() != null) {
                    vo.setGradeName(getValue(AssessExamineTaskConstant.ESTATE_DINING_GRADE, examineMatchingLeisurePlace.getGrade()));
                }
            }
        }
        if (examineMatchingLeisurePlace.getDistance() != null) {
            vo.setDistanceName(getValue(AssessExamineTaskConstant.ESTATE_SHOP_OR_ENTERTAINMENT_OR_DINING_DISTANCE, examineMatchingLeisurePlace.getDistance()));
        }
        return vo;
    }

    private String getValue(String key, Integer v) {
        StringBuilder builder = new StringBuilder(100);
        List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(key);
        if (!ObjectUtils.isEmpty(baseDataDic)) {
            if (baseDataDic.size() >= 1) {
                if (v != null) {
                    for (BaseDataDic base : baseDataDic) {
                        if (base.getId().intValue() == v.intValue())
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
     * @param examineMatchingLeisurePlace
     * @return
     */
    public boolean addExamineMatchingLeisurePlace(ExamineMatchingLeisurePlace examineMatchingLeisurePlace) {
        examineMatchingLeisurePlace.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineMatchingLeisurePlace.getDeclareId())) {
            examineMatchingLeisurePlace.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineMatchingLeisurePlace.getExamineType())) {
            examineMatchingLeisurePlace.setExamineType(0);
        }
        return examineMatchingLeisurePlaceDao.addMatchingFinance(examineMatchingLeisurePlace);
    }

    /**
     * 编辑
     *
     * @param examineMatchingLeisurePlace
     * @return
     */
    public boolean updateExamineMatchingLeisurePlace(ExamineMatchingLeisurePlace examineMatchingLeisurePlace) {
        return examineMatchingLeisurePlaceDao.updateMatchingFinance(examineMatchingLeisurePlace);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineMatchingLeisurePlace(Integer id) {
        return examineMatchingLeisurePlaceDao.deleteMatchingFinance(id);
    }

    public List<BaseDataDic> examineMatchingLeisurePlace_category(ExamineMatchingLeisurePlaceTypeEnum typeEnum) {
        String key = typeEnum.getKey();
        if (!StringUtils.isEmpty(key)) {
            List<BaseDataDic> baseDataDic = null;
            if (key.equals(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey())) {
                baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SHOP_CATEGORY);
            }
            if (key.equals(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getKey())) {
                baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_ENTERTAINMENT_CATEGORY);
            }
            if (key.equals(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT.getKey())) {
                baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_DINING_CATEGORY);
            }
            if (!ObjectUtils.isEmpty(baseDataDic)) {
                return baseDataDic;
            }
        }
        return null;
    }

    public List<BaseDataDic> examineMatchingLeisurePlace_grade(ExamineMatchingLeisurePlaceTypeEnum typeEnum) {
        String key = typeEnum.getKey();
        if (!StringUtils.isEmpty(key)) {
            List<BaseDataDic> baseDataDic = null;
            if (key.equals(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGMARKET.getKey())) {
                baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_SHOP_GRADE);
            }
            if (key.equals(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRECREATION.getKey())) {

            }
            if (key.equals(ExamineMatchingLeisurePlaceTypeEnum.MATCHINGRESTAURANT.getKey())) {
                baseDataDic = baseDataDicService.getCacheDataDicList(AssessExamineTaskConstant.ESTATE_DINING_GRADE);
            }
            if (!ObjectUtils.isEmpty(baseDataDic)) {
                return baseDataDic;
            }
        }
        return null;
    }
}
