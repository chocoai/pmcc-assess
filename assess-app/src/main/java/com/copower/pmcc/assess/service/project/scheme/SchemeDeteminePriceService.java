package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeAreaJudgeObjectVo;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeJudgeObjectVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;

/**
 * Created by kings on 2018-10-9.
 */
@Service
public class SchemeDeteminePriceService {
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;
    @Autowired
    private SchemeAreaGroupService schemeAreaGroupService;
    @Autowired
    private ErpAreaService erpAreaService;

    /**
     * 获取区域委估对象信息列表
     *
     * @param projectId
     * @return
     */
    public List<SchemeAreaJudgeObjectVo> getAreaJudgeObjectList(Integer projectId) {
        List<SchemeJudgeObject> judgeObjectList = schemeJudgeObjectService.getJudgeObjectListByProjectId(projectId);
        if (CollectionUtils.isEmpty(judgeObjectList))
            return null;
        HashSet<Integer> hashSet = Sets.newHashSet();
        for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
            hashSet.add(schemeJudgeObject.getAreaGroupId());
        }
        List<SchemeAreaJudgeObjectVo> areaJudgeObjectVoList = Lists.newArrayList();
        for (Integer integer : hashSet) {
            SchemeAreaJudgeObjectVo schemeAreaJudgeObjectVo = new SchemeAreaJudgeObjectVo();
            schemeAreaJudgeObjectVo.setAreaGroupId(integer);
            SchemeAreaGroup areaGroup = schemeAreaGroupService.get(integer);
            schemeAreaJudgeObjectVo.setAreaGroupName(erpAreaService.getAreaFullName(areaGroup.getProvince(), areaGroup.getCity(), areaGroup.getDistrict()));
            List<SchemeJudgeObjectVo> judgeObjectVoList = Lists.newArrayList();
            for (SchemeJudgeObject schemeJudgeObject : judgeObjectList) {
                if (schemeJudgeObject.getAreaGroupId().equals(integer)) {
                    judgeObjectVoList.add(schemeJudgeObjectService.getSchemeJudgeObjectVo(schemeJudgeObject));
                }
            }
            schemeAreaJudgeObjectVo.setJudgeObjectVoList(judgeObjectVoList);
            areaJudgeObjectVoList.add(schemeAreaJudgeObjectVo);
        }
        return areaJudgeObjectVoList;
    }
}
