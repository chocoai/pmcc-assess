package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicBuildingOutfitDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingOutfit;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingOutfitVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
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
 * @Date: 2018/10/30 11:46
 * @Description:楼栋外装情况
 */
@Service
public class BasicBuildingOutfitService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicBuildingOutfitDao basicBuildingOutfitDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicBuildingOutfit getBasicBuildingOutfitById(Integer id) throws Exception {
        return basicBuildingOutfitDao.getBasicBuildingOutfitById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicBuildingOutfit
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicBuildingOutfit(BasicBuildingOutfit basicBuildingOutfit, boolean updateNull) throws Exception {
        if (basicBuildingOutfit.getId() == null || basicBuildingOutfit.getId().intValue() == 0) {
            basicBuildingOutfit.setCreator(commonService.thisUserAccount());
            Integer id = basicBuildingOutfitDao.saveBasicBuildingOutfit(basicBuildingOutfit);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicBuildingOutfit.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicBuildingOutfit buildingOutfit = basicBuildingOutfitDao.getBasicBuildingOutfitById(basicBuildingOutfit.getId());
                if (buildingOutfit != null) {
                    basicBuildingOutfit.setBisDelete(buildingOutfit.getBisDelete());
                    basicBuildingOutfit.setCreator(buildingOutfit.getCreator());
                    basicBuildingOutfit.setGmtCreated(buildingOutfit.getGmtCreated());
                    basicBuildingOutfit.setGmtModified(DateUtils.now());
                }
            }
            basicBuildingOutfitDao.updateBasicBuildingOutfit(basicBuildingOutfit, updateNull);
            return basicBuildingOutfit.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicBuildingOutfit(Integer id) throws Exception {
        return basicBuildingOutfitDao.deleteBasicBuildingOutfit(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicBuildingOutfit
     * @return
     * @throws Exception
     */
    public List<BasicBuildingOutfit> basicBuildingOutfitList(BasicBuildingOutfit basicBuildingOutfit) throws Exception {
        return basicBuildingOutfitDao.basicBuildingOutfitList(basicBuildingOutfit);
    }

    public void removeBasicBuildingOutfit(BasicBuildingOutfit basicBuildingOutfit) throws Exception {
        basicBuildingOutfitDao.removeBasicBuildingOutfit(basicBuildingOutfit);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicBuildingOutfit basicBuildingOutfit) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicBuildingOutfit> basicBuildingOutfitList = basicBuildingOutfitDao.basicBuildingOutfitList(basicBuildingOutfit);
        List<BasicBuildingOutfitVo> vos = Lists.newArrayList();
        basicBuildingOutfitList.forEach(oo -> vos.add(getBasicBuildingOutfitVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicBuildingOutfitVo>(10) : vos);
        return vo;
    }

    public List<BasicBuildingOutfitVo> getBasicBuildingOutfitVos(Integer buildingId) {
        if (buildingId == null) return null;
        BasicBuildingOutfit where = new BasicBuildingOutfit();
        where.setBuildingId(buildingId);
        List<BasicBuildingOutfit> basicBuildingOutfitList = basicBuildingOutfitDao.basicBuildingOutfitList(where);
        return LangUtils.transform(basicBuildingOutfitList, o -> getBasicBuildingOutfitVo(o));
    }

    public BasicBuildingOutfitVo getBasicBuildingOutfitVo(BasicBuildingOutfit basicBuildingOutfit) {
        if (basicBuildingOutfit == null) {
            return null;
        }
        BasicBuildingOutfitVo vo = new BasicBuildingOutfitVo();
        BeanUtils.copyProperties(basicBuildingOutfit, vo);
        vo.setDecoratingMaterialName(baseDataDicService.getNameById(basicBuildingOutfit.getDecoratingMaterial()));
        vo.setMaterialPriceName(baseDataDicService.getNameById(basicBuildingOutfit.getMaterialPrice()));
        vo.setConstructionTechnologyName(baseDataDicService.getNameById(basicBuildingOutfit.getConstructionTechnology()));
        vo.setMaterialGradeName(baseDataDicService.getNameById(basicBuildingOutfit.getMaterialGrade()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicBuildingOutfit.getCreator()));
        return vo;
    }

}
