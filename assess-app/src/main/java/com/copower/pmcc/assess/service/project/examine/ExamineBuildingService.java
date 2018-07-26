package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineBuildingDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataBuilder;
import com.copower.pmcc.assess.dal.basis.entity.DataProperty;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuilding;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineBuildingVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataBuilderService;
import com.copower.pmcc.assess.service.data.DataPropertyService;
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
 * @Date: 2018/7/23 17:29
 * @Description:楼栋基础信息
 */
@Service
public class ExamineBuildingService {
    @Autowired
    private ExamineBuildingDao examineBuildingDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private DataBuilderService dataBuilderService;
    @Autowired
    private DataPropertyService dataPropertyService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public ExamineBuildingVo getExamineBuildingById(Integer id) {
        ExamineBuildingVo examineBuildingVo = getExamineBuildingVo(examineBuildingDao.getBuildingById(id));
        return examineBuildingVo;
    }

    /**
     * 获取数据列表
     *
     * @param examineBuilding
     * @return
     */
    public List<ExamineBuilding> getExamineBuildingList(ExamineBuilding examineBuilding) {
        return examineBuildingDao.getBuildingList(examineBuilding);
    }

    public BootstrapTableVo getExamineBuildingLists(ExamineBuilding examineBuilding) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineBuildingVo> vos = Lists.newArrayList();
        getExamineBuildingList(examineBuilding).stream().forEach(oo -> vos.add(getExamineBuildingVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineBuildingVo>() : vos);
        return vo;
    }

    public ExamineBuildingVo getExamineBuildingVo(ExamineBuilding examineBuilding) {
        ExamineBuildingVo vo = new ExamineBuildingVo();
        BeanUtils.copyProperties(examineBuilding, vo);
        if (examineBuilding.getBuildingStructure() != null) {
            BaseDataDic baseDataDic = baseDataDicService.getDataDicById(examineBuilding.getBuildingStructure());
            if (baseDataDic != null){
                vo.setBuildingStructureName(baseDataDic.getName());
                vo.setBuildingStructurePid(baseDataDic.getPid());
            }
        }
        if (examineBuilding.getBuildingCategory() != null) {
            vo.setBuildingCategoryName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_PROPERTY_CATEGORY, examineBuilding.getBuildingCategory()));
        }
        if (examineBuilding.getBuilderId() != null) {
            DataBuilder dataBuilder = dataBuilderService.getByDataBuilderId(examineBuilding.getBuilderId());
            if (dataBuilder != null) {
                vo.setBuilderName(dataBuilder.getName());
            }
        }
        if (examineBuilding.getPropertyId() != null) {
            DataProperty dataProperty = dataPropertyService.getByDataPropertyId(examineBuilding.getPropertyId());
            if (dataProperty != null) {
                vo.setPropertyName(dataProperty.getName());
            }
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
     * @param examineBuilding
     * @return
     */
    public boolean addExamineBuilding(ExamineBuilding examineBuilding) {
        examineBuilding.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineBuilding.getDeclareId())) {
            examineBuilding.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineBuilding.getExamineType())) {
            examineBuilding.setExamineType(0);
        }
        return examineBuildingDao.addBuilding(examineBuilding);
    }

    /**
     * 编辑
     *
     * @param examineBuilding
     * @return
     */
    public boolean updateExamineBuilding(ExamineBuilding examineBuilding) {
        return examineBuildingDao.updateBuilding(examineBuilding);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineBuilding(Integer id) {
        return examineBuildingDao.deleteBuilding(id);
    }

    public List<DataBuilder> getDataBuilderList() {
        return dataBuilderService.getDataBuilderList(null);
    }

    public List<DataProperty> getDataPropertyList() {
        return dataPropertyService.getDataPropertyList(null);
    }

}
