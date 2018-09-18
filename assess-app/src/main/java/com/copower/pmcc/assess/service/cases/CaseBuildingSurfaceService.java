package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseBuildingSurfaceDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseBuildingSurface;
import com.copower.pmcc.assess.dto.output.cases.CaseBuildingSurfaceVo;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 12:06
 * @Description:
 */
@Service
public class CaseBuildingSurfaceService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseBuildingSurfaceDao caseBuildingSurfaceDao;
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
    public CaseBuildingSurface getCaseBuildingSurfaceById(Integer id) {
        return caseBuildingSurfaceDao.getBuildingSurfaceById(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseBuildingSurface
     * @return
     */
    public List<CaseBuildingSurface> getCaseBuildingSurfaceList(CaseBuildingSurface caseBuildingSurface) {
        return caseBuildingSurfaceDao.getBuildingSurfaceList(caseBuildingSurface);
    }

    public BootstrapTableVo getCaseBuildingSurfaceLists(CaseBuildingSurface caseBuildingSurface) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseBuildingSurfaceVo> vos = Lists.newArrayList();
        getCaseBuildingSurfaceList(caseBuildingSurface).stream().forEach(oo -> vos.add(getCaseBuildingSurfaceVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseBuildingSurfaceVo>() : vos);
        return vo;
    }

    public CaseBuildingSurfaceVo getCaseBuildingSurfaceVo(CaseBuildingSurface caseBuildingSurface) {
        CaseBuildingSurfaceVo vo = new CaseBuildingSurfaceVo();
        BeanUtils.copyProperties(caseBuildingSurface, vo);
        if (caseBuildingSurface.getStructure() != null) {
            vo.setStructureName(getValue(AssessExamineTaskConstant.EXAMINE_BUILDING_STRUCTURE, caseBuildingSurface.getStructure()));
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
     * @param caseBuildingSurface
     * @return
     */
    public boolean addCaseBuildingSurface(CaseBuildingSurface caseBuildingSurface) {
        caseBuildingSurface.setCreator(commonService.thisUserAccount());

        if (caseBuildingSurface.getBuildingId() == null) {
            caseBuildingSurface.setBuildingId(0);
        }
        return caseBuildingSurfaceDao.addBuildingSurface(caseBuildingSurface);
    }

    /**
     * 编辑
     *
     * @param caseBuildingSurface
     * @return
     */
    public boolean updateCaseBuildingSurface(CaseBuildingSurface caseBuildingSurface) {
        return caseBuildingSurfaceDao.updateBuildingSurface(caseBuildingSurface);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseBuildingSurface(Integer id) {
        return caseBuildingSurfaceDao.deleteBuildingSurface(id);
    }


}
