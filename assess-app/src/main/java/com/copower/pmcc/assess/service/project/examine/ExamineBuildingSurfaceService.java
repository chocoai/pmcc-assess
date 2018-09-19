package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.project.examine.ExamineBuildingSurfaceDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineBuildingSurface;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineBuildingSurfaceVo;
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
 * @Date: 2018/8/2 14:05
 * @Description:层面结构
 */

@Service
public class ExamineBuildingSurfaceService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineBuildingSurfaceDao examineBuildingSurfaceDao;

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
    public ExamineBuildingSurface getExamineBuildingSurfaceById(Integer id) {
        return examineBuildingSurfaceDao.getBuildingSurfaceById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineBuildingSurface
     * @return
     */
    public List<ExamineBuildingSurface> getExamineBuildingSurfaceList(ExamineBuildingSurface examineBuildingSurface) {
        return examineBuildingSurfaceDao.getBuildingSurfaceList(examineBuildingSurface);
    }

    public BootstrapTableVo getExamineBuildingSurfaceLists(ExamineBuildingSurface examineBuildingSurface) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineBuildingSurfaceVo> vos = Lists.newArrayList();
        getExamineBuildingSurfaceList(examineBuildingSurface).stream().forEach(oo -> vos.add(getExamineBuildingSurfaceVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineBuildingSurfaceVo>() : vos);
        return vo;
    }

    public ExamineBuildingSurfaceVo getExamineBuildingSurfaceVo(ExamineBuildingSurface examineBuildingSurface) {
        ExamineBuildingSurfaceVo vo = new ExamineBuildingSurfaceVo();
        BeanUtils.copyProperties(examineBuildingSurface, vo);
        if (examineBuildingSurface.getStructure() != null) {
            vo.setStructureName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_STRUCTURE, examineBuildingSurface.getStructure()));
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
     * @param examineBuildingSurface
     * @return
     */
    public boolean addExamineBuildingSurface(ExamineBuildingSurface examineBuildingSurface) {
        examineBuildingSurface.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineBuildingSurface.getDeclareId())) {
            examineBuildingSurface.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineBuildingSurface.getExamineType())) {
            examineBuildingSurface.setExamineType(0);
        }
        if (examineBuildingSurface.getBuildingId() == null) {
            examineBuildingSurface.setBuildingId(0);
        }
        return examineBuildingSurfaceDao.addBuildingSurface(examineBuildingSurface);
    }

    /**
     * 编辑
     *
     * @param examineBuildingSurface
     * @return
     */
    public boolean updateExamineBuildingSurface(ExamineBuildingSurface examineBuildingSurface) {
        return examineBuildingSurfaceDao.updateBuildingSurface(examineBuildingSurface);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineBuildingSurface(Integer id) {
        return examineBuildingSurfaceDao.deleteBuildingSurface(id);
    }

    public boolean removeExamineBuildingSurface(ExamineBuildingSurface examineBuildingSurface){
        try {
            examineBuildingSurfaceDao.removeExamineBuildingSurface(examineBuildingSurface);
            return  true;
        } catch (Exception e1) {
            return false;
        }
    }
}
