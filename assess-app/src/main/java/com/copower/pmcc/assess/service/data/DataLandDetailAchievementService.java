package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    /**
     * 过滤 类型
     *
     * @param oo
     * @return
     */
    public Set<List<List<DataLandDetailAchievementVo>>> landLevelFilter(DataLandDetailAchievement oo) {
        List<DataLandDetailAchievement> dataLandDetailAchievementVoList = getDataLandDetailAchievementList(oo);
        List<List<DataLandDetailAchievementVo>> listList = landLevelFilter2(dataLandDetailAchievementVoList.stream().map(po -> getDataLandDetailAchievementVo(po)).collect(Collectors.toList()));
        Set<List<List<DataLandDetailAchievementVo>>> set = landLevelFilter1(listList);
        return set;
    }

    public List<List<DataLandDetailAchievementVo>> landLevelFilter2(List<DataLandDetailAchievementVo> dataLandDetailAchievementVoList) {
        Map<String, List<DataLandDetailAchievementVo>> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(dataLandDetailAchievementVoList)) {
            dataLandDetailAchievementVoList.forEach(oo -> {
                List<DataLandDetailAchievementVo> voList = map.get(oo.getCategory());
                if (CollectionUtils.isEmpty(voList)) {
                    voList = Lists.newArrayList();
                }
                voList.add(oo);
                map.put(oo.getCategory(), voList);
            });
        }
        List<List<DataLandDetailAchievementVo>> listList = Lists.newArrayList();
        if (!map.isEmpty()) {
            map.entrySet().stream().forEachOrdered(stringListEntry -> {
                listList.add(stringListEntry.getValue());
            });
        }
        return listList;
    }

    public Set<List<List<DataLandDetailAchievementVo>>> landLevelFilter1(List<List<DataLandDetailAchievementVo>> listList) {
        Set<List<List<DataLandDetailAchievementVo>>> set = Sets.newHashSet();
        Map<Integer, List<List<DataLandDetailAchievementVo>>> map = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(listList)) {
            listList.forEach(po -> {
                int random = RandomUtils.nextInt(0, po.size() - 1);
                Integer key = po.get(random).getType();
                List<List<DataLandDetailAchievementVo>> lists = map.get(key);
                if (CollectionUtils.isEmpty(lists)) {
                    lists = Lists.newArrayList();
                }
                lists.add(po);
                map.put(key, lists);
            });
        }
        if (!map.isEmpty()) {
            map.entrySet().forEach(oo -> {
                set.add(oo.getValue());
            });
        }
        return set;
    }


    public DataLandDetailAchievementVo getDataLandDetailAchievementVo(DataLandDetailAchievement oo) {
        if (oo == null) {
            return null;
        }
        DataLandDetailAchievementVo vo = new DataLandDetailAchievementVo();
        org.springframework.beans.BeanUtils.copyProperties(oo, vo);
        vo.setTypeName(baseDataDicService.getNameById(oo.getType()));
        if (StringUtils.isNotEmpty(oo.getCategory())) {
            vo.setCategoryName(oo.getCategory());
            if (NumberUtils.isNumber(oo.getCategory())) {
                vo.setCategoryName(baseDataDicService.getNameById(oo.getCategory()));
            }
        }
        vo.setGradeName(baseDataDicService.getNameById(oo.getGrade()));
//        vo.setJsonObj(JSON.toJSONString(vo));
        return vo;
    }

    public DataLandDetailAchievement getDataLandDetailAchievement(Integer levelDetailId, String category, Integer grade, Integer type) {
        List<DataLandDetailAchievement> dataLandDetailAchievement = dataLandDetailAchievementDao.getDataLandDetailAchievement(levelDetailId, category, grade, type);
        if (CollectionUtils.isNotEmpty(dataLandDetailAchievement)) {
            return dataLandDetailAchievement.get(0);
        }
        return null;
    }


}
