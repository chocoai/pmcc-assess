package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.enums.ExamineMatchingLeisurePlaceTypeEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseMatchingLeisurePlaceDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseMatchingLeisurePlace;
import com.copower.pmcc.assess.dto.output.cases.CaseMatchingLeisurePlaceVo;
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
 * @Date: 2018/9/17 15:35
 * @Description:
 */
@Service
public class CaseMatchingLeisurePlaceService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseMatchingLeisurePlaceDao caseMatchingLeisurePlaceDao;

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
    public CaseMatchingLeisurePlace getCaseMatchingLeisurePlaceById(Integer id) {
        return caseMatchingLeisurePlaceDao.getMatchingMedicalById(id);
    }

    public void upgradeVersion(CaseMatchingLeisurePlace po) throws Exception {
        if (po.getId() == null || po.getId().intValue() == 0) {
            po.setCreator(commonService.thisUserAccount());
            po.setVersion(0);
            this.addCaseMatchingLeisurePlace(po);
        } else {
            CaseMatchingLeisurePlace oo = getCaseMatchingLeisurePlaceById(po.getId());
            if (oo.getVersion() == null) {
                oo.setVersion(0);
            }
            int version = oo.getVersion() + 1;
            BeanUtils.copyProperties(po, oo);
            oo.setVersion(version);
            oo.setId(null);
            oo.setGmtCreated(null);
            oo.setGmtCreated(null);
            oo.setCreator(commonService.thisUserAccount());
            this.addCaseMatchingLeisurePlace(oo);
        }
    }

    /**
     * 获取数据列表
     *
     * @param caseMatchingLeisurePlace
     * @return
     */
    public List<CaseMatchingLeisurePlace> getCaseMatchingLeisurePlaceList(CaseMatchingLeisurePlace caseMatchingLeisurePlace) {
        return caseMatchingLeisurePlaceDao.getMatchingMedicalList(caseMatchingLeisurePlace);
    }

    public BootstrapTableVo getCaseMatchingLeisurePlaceLists(CaseMatchingLeisurePlace caseMatchingLeisurePlace) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseMatchingLeisurePlaceVo> vos = Lists.newArrayList();
        getCaseMatchingLeisurePlaceList(caseMatchingLeisurePlace).stream().forEach(oo -> vos.add(getCaseMatchingLeisurePlaceVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseMatchingLeisurePlaceVo>() : vos);
        return vo;
    }

    public CaseMatchingLeisurePlaceVo getCaseMatchingLeisurePlaceVo(CaseMatchingLeisurePlace caseMatchingLeisurePlace) {
        CaseMatchingLeisurePlaceVo vo = new CaseMatchingLeisurePlaceVo();
        BeanUtils.copyProperties(caseMatchingLeisurePlace, vo);
        vo.setCategoryName(baseDataDicService.getNameById(caseMatchingLeisurePlace.getCategory()));
        vo.setDistanceName(baseDataDicService.getNameById(caseMatchingLeisurePlace.getDistance()));
        vo.setGradeName(baseDataDicService.getNameById(caseMatchingLeisurePlace.getGrade()));
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
     * @param caseMatchingLeisurePlace
     * @return
     */
    public boolean addCaseMatchingLeisurePlace(CaseMatchingLeisurePlace caseMatchingLeisurePlace) {
        caseMatchingLeisurePlace.setCreator(commonService.thisUserAccount());
        return caseMatchingLeisurePlaceDao.addMatchingMedical(caseMatchingLeisurePlace);
    }

    /**
     * 编辑
     *
     * @param caseMatchingLeisurePlace
     * @return
     */
    public boolean updateCaseMatchingLeisurePlace(CaseMatchingLeisurePlace caseMatchingLeisurePlace) {
        return caseMatchingLeisurePlaceDao.updateMatchingMedical(caseMatchingLeisurePlace);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseMatchingLeisurePlace(Integer id) {
        return caseMatchingLeisurePlaceDao.deleteMatchingMedical(id);
    }

    public List<BaseDataDic> caseMatchingLeisurePlace_category(ExamineMatchingLeisurePlaceTypeEnum typeEnum) {
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

    public List<BaseDataDic> caseMatchingLeisurePlace_grade(ExamineMatchingLeisurePlaceTypeEnum typeEnum) {
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

    /**
     * 根据查询条件判断是否有数据
     *
     * @param esteteId
     * @return
     */
    public boolean hasMatchingLeisurePlaceData(Integer esteteId, String type) {
        return caseMatchingLeisurePlaceDao.countByEstateId(esteteId, type) > 0;
    }
}
