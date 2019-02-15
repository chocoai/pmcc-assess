package com.copower.pmcc.assess.service.project.scheme;

import com.alibaba.fastjson.JSON;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeAreaGroupDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dal.basis.entity.SchemeJudgeObject;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeAreaGroupVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 区域分组
 * Created by 13426 on 2018/5/14.
 */
@Service
public class SchemeAreaGroupService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private DeclareRecordService declareRecordService;
    @Autowired
    private SchemeAreaGroupDao schemeAreaGroupDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private SchemeJudgeObjectService schemeJudgeObjectService;

    public int add(SchemeAreaGroup schemeAreaGroup) {
        return schemeAreaGroupDao.add(schemeAreaGroup);
    }

    /**
     * 根据分组 获取数据信息
     *
     * @param projectId
     * @return
     */
    public List<SchemeAreaGroup> getAreaGroupList(Integer projectId) {
        List<SchemeAreaGroup> schemeAreaGroupList = schemeAreaGroupDao.getSchemeAreaGroupByProjectId(projectId);
        return schemeAreaGroupList;
    }

    /**
     * 区域分组合并
     *
     * @param projectId
     * @param areaGroupIds
     */
    @Transactional(rollbackFor = Exception.class)
    public void areaGroupMerge(Integer projectId, String areaGroupIds) throws BusinessException {
        //验证区域是否能合并
        //创建合并区域 设置参与合并的区域不可用 将委估对象移至新区域下
        List<Integer> ids = FormatUtils.ListStringToListInteger(FormatUtils.transformString2List(areaGroupIds));
        if (CollectionUtils.isEmpty(ids) || ids.size() <= 1)
            throw new BusinessException("参与合并的区域至少为两个");
        List<SchemeAreaGroup> schemeAreaGroupList = schemeAreaGroupDao.getSchemeAreaGroupByIds(ids);
        String province = schemeAreaGroupList.get(0).getProvince();
        String city = schemeAreaGroupList.get(0).getCity();
        for (SchemeAreaGroup schemeAreaGroup : schemeAreaGroupList) {
            if (!StringUtils.equals(schemeAreaGroup.getProvince(), province) || !StringUtils.equals(schemeAreaGroup.getCity(), city))
                throw new BusinessException("区域不一致不能参与合并");
        }

        SchemeAreaGroup newAreaGroup = new SchemeAreaGroup();
        newAreaGroup.setPid(0);
        newAreaGroup.setProjectId(projectId);
        newAreaGroup.setProvince(province);
        newAreaGroup.setCity(city);
        newAreaGroup.setAreaName(erpAreaService.getAreaFullName(province, city, null));
        newAreaGroup.setValueTimePoint(schemeAreaGroupList.get(0).getValueTimePoint());
        newAreaGroup.setTimePointExplain(schemeAreaGroupList.get(0).getTimePointExplain());
        newAreaGroup.setBisEnable(true);
        newAreaGroup.setBisMerge(true);
        newAreaGroup.setCreator(commonService.thisUserAccount());
        schemeAreaGroupDao.add(newAreaGroup);
        int i = 1;//委估对象重新编号
        for (SchemeAreaGroup oldAreaGroup : schemeAreaGroupList) {
            oldAreaGroup.setPid(newAreaGroup.getId());
            oldAreaGroup.setBisEnable(false);
            schemeAreaGroupDao.update(oldAreaGroup);
            //委估对象逐一验证，如果已存在合并或拆分的对象，则不允许区域合并
            List<SchemeJudgeObject> judgeObjects = schemeJudgeObjectService.getJudgeObjectListByAreaGroupId(oldAreaGroup.getId());
            if (CollectionUtils.isNotEmpty(judgeObjects)) {
                for (SchemeJudgeObject judgeObject : judgeObjects) {
                    if (judgeObject.getBisMerge() == Boolean.TRUE || judgeObject.getBisSplit() == Boolean.TRUE)
                        throw new BusinessException("参与合并的区域中已存在合并或拆分对象");
                    judgeObject.setOriginalNumber(judgeObject.getNumber());
                    judgeObject.setNumber(String.valueOf(i));
                    judgeObject.setOriginalAreaGroupId(oldAreaGroup.getId());
                    judgeObject.setAreaGroupId(newAreaGroup.getId());
                    schemeJudgeObjectService.updateSchemeJudgeObject(judgeObject);
                    i++;
                }
            }
        }
    }

    /**
     * 取消合并的区域
     *
     * @param id
     */
    @Transactional
    public void areaGroupMergeCancel(Integer id) throws BusinessException {
        List<SchemeAreaGroup> schemeAreaGroupList = schemeAreaGroupDao.getSchemeAreaGroupByPid(id);
        if (CollectionUtils.isNotEmpty(schemeAreaGroupList)) {
            for (SchemeAreaGroup areaGroup : schemeAreaGroupList) {
                areaGroup.setPid(0);
                areaGroup.setBisEnable(true);
                schemeAreaGroupDao.update(areaGroup);
                schemeJudgeObjectService.areaGroupReduction(areaGroup.getId());
            }
        }
        schemeAreaGroupDao.remove(id);
    }

    /**
     * 保存区域分组
     *
     * @param schemeAreaGroup
     */
    public void saveAreaGroup(SchemeAreaGroup schemeAreaGroup) throws BusinessException {
        if (schemeAreaGroup == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (schemeAreaGroup.getId() != null && schemeAreaGroup.getId() > 0) {
            schemeAreaGroupDao.update(schemeAreaGroup);
        } else {
            schemeAreaGroup.setCreator(commonService.thisUserAccount());
            schemeAreaGroupDao.add(schemeAreaGroup);
        }
    }


    public boolean remove(Integer id) {
        return schemeAreaGroupDao.remove(id);
    }


    public boolean update(SchemeAreaGroup schemeAreaGroup) {
        return schemeAreaGroupDao.update(schemeAreaGroup);
    }


    public SchemeAreaGroup get(Integer id) {
        return schemeAreaGroupDao.get(id);
    }


    public SchemeAreaGroupVo getSchemeAreaGroupVo(SchemeAreaGroup schemeAreaGroup) {
        if (schemeAreaGroup == null) return null;
        SchemeAreaGroupVo schemeAreaGroupVo = new SchemeAreaGroupVo();
        BeanUtils.copyProperties(schemeAreaGroup, schemeAreaGroupVo);
        schemeAreaGroupVo.setEntrustPurposeName(baseDataDicService.getNameById(schemeAreaGroup.getEntrustPurpose()));
        schemeAreaGroupVo.setValueDefinitionName(baseDataDicService.getNameById(schemeAreaGroup.getValueDefinition()));
        if (StringUtils.isNotBlank(schemeAreaGroup.getValueConnotation())) {
            List<String> list = JSON.parseArray(schemeAreaGroup.getValueConnotation(), String.class);
            if (CollectionUtils.isNotEmpty(list)) {
                StringBuilder builder = new StringBuilder();
                list.forEach(o -> builder.append(baseDataDicService.getNameById(o)).append(","));
                schemeAreaGroupVo.setValueConnotationName(StringUtils.strip(builder.toString(), ","));
            }
        }
        return schemeAreaGroupVo;
    }
}
