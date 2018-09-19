package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineHouseRoomDecorateDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseRoomDecorate;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineHouseRoomDecorateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
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
 * @Date: 2018/7/25 10:26
 * @Description:房间装修
 */
@Service
public class ExamineHouseRoomDecorateService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineHouseRoomDecorateDao examineHouseRoomDecorateDao;
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
    public ExamineHouseRoomDecorate getExamineHouseRoomDecorateById(Integer id) {
        return examineHouseRoomDecorateDao.getHouseRoomDecorateById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineHouseRoomDecorate
     * @return
     */
    public List<ExamineHouseRoomDecorate> getExamineHouseRoomDecorateList(ExamineHouseRoomDecorate examineHouseRoomDecorate) {
        return examineHouseRoomDecorateDao.getHouseRoomDecorateList(examineHouseRoomDecorate);
    }

    public BootstrapTableVo getExamineHouseRoomDecorateLists(ExamineHouseRoomDecorate examineHouseRoomDecorate) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineHouseRoomDecorateVo> vos = Lists.newArrayList();
        getExamineHouseRoomDecorateList(examineHouseRoomDecorate).stream().forEach(oo -> vos.add(getExamineHouseRoomDecorateVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineHouseRoomDecorateVo>() : vos);
        return vo;
    }

    public ExamineHouseRoomDecorateVo getExamineHouseRoomDecorateVo(ExamineHouseRoomDecorate examineHouseRoomDecorate) {
        ExamineHouseRoomDecorateVo vo = new ExamineHouseRoomDecorateVo();
        BeanUtils.copyProperties(examineHouseRoomDecorate, vo);
        if (examineHouseRoomDecorate.getPart() != null) {
            vo.setPartName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATION_PART, examineHouseRoomDecorate.getPart()));
        }
        if (examineHouseRoomDecorate.getMaterial() != null) {
            vo.setMaterialName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_DECORATING_MATERIAL, examineHouseRoomDecorate.getMaterial()));
        }
        if (StringUtils.isNumeric(examineHouseRoomDecorate.getMaterialPrice())){
            vo.setMaterialPriceName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_MATERIAL_PRICE,Integer.parseInt(examineHouseRoomDecorate.getMaterialPrice())));
        }
        if (StringUtils.isNumeric(examineHouseRoomDecorate.getConstructionTechnology())) {
            vo.setConstructionTechnologyName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_CONSTRUCTION_TECHNOLOGY,Integer.parseInt(examineHouseRoomDecorate.getConstructionTechnology())));
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
     * @param examineHouseRoomDecorate
     * @return
     */
    public boolean addExamineHouseRoomDecorate(ExamineHouseRoomDecorate examineHouseRoomDecorate) {
        examineHouseRoomDecorate.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineHouseRoomDecorate.getDeclareId())) {
            examineHouseRoomDecorate.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineHouseRoomDecorate.getExamineType())) {
            examineHouseRoomDecorate.setExamineType(0);
        }
        return examineHouseRoomDecorateDao.addHouseRoomDecorate(examineHouseRoomDecorate);
    }

    /**
     * 编辑
     *
     * @param examineHouseRoomDecorate
     * @return
     */
    public boolean updateExamineHouseRoomDecorate(ExamineHouseRoomDecorate examineHouseRoomDecorate) {
        return examineHouseRoomDecorateDao.updateHouseRoomDecorate(examineHouseRoomDecorate);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineHouseRoomDecorate(Integer id) {
        return examineHouseRoomDecorateDao.deleteHouseRoomDecorate(id);
    }
}
