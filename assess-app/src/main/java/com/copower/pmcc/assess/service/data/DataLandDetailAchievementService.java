package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataLandDetailAchievementDao;
import com.copower.pmcc.assess.dal.basis.entity.DataLandDetailAchievement;
import com.copower.pmcc.assess.dto.output.data.DataLandDetailAchievementVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zch
 * @date: 2019/5/5 10:18
 * @description:土地级别详情从表
 */
@Service
public class DataLandDetailAchievementService {
    @Autowired
    private DataLandDetailAchievementDao dataLandDetailAchievementDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public boolean saveDataLandDetailAchievement(DataLandDetailAchievement oo) {
        if (oo.getId() == null || oo.getId() == 0) {
            oo.setCreator(commonService.thisUserAccount());
            return dataLandDetailAchievementDao.saveDataLandDetailAchievement(oo);
        } else {
            return dataLandDetailAchievementDao.editDataLandDetailAchievement(oo);
        }
    }

    public boolean deleteDataLandDetailAchievement(Integer id) {
        return dataLandDetailAchievementDao.deleteDataLandDetailAchievement(id);
    }

    public DataLandDetailAchievement getDataLandDetailAchievementById(Integer id) {
        return dataLandDetailAchievementDao.getDataLandDetailAchievementById(id);
    }

    public List<DataLandDetailAchievement> getDataLandDetailAchievementList(DataLandDetailAchievement oo) {
        return dataLandDetailAchievementDao.getDataLandDetailAchievementList(oo);
    }

    public BootstrapTableVo getBootstrapTableVo(DataLandDetailAchievement oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLandDetailAchievement> list = getDataLandDetailAchievementList(oo);
        List<DataLandDetailAchievementVo> voList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(po -> voList.add(getDataLandDetailAchievementVo(po)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    public DataLandDetailAchievementVo getDataLandDetailAchievementVo(DataLandDetailAchievement oo) {
        if (oo == null) {
            return null;
        }
        DataLandDetailAchievementVo vo = new DataLandDetailAchievementVo();
        org.springframework.beans.BeanUtils.copyProperties(oo, vo);
        vo.setTypeName(baseDataDicService.getNameById(oo.getType()));
        if (StringUtils.isNotEmpty(oo.getCategory())){
            vo.setCategoryName(oo.getCategory());
            if (NumberUtils.isNumber(oo.getCategory())) {
                vo.setCategoryName(baseDataDicService.getNameById(oo.getCategory()));
            }
        }
        vo.setGradeName(baseDataDicService.getNameById(oo.getGrade()));
        return vo;
    }

}
