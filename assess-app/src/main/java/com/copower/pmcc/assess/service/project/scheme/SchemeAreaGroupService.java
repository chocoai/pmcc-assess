package com.copower.pmcc.assess.service.project.scheme;

import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeAreaGroupDao;
import com.copower.pmcc.assess.dal.basis.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
    private PublicService publicService;
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
    public List<SchemeAreaGroup> schemeAreaGroupVoList(Integer projectId) {
        List<SchemeAreaGroup> schemeAreaGroupList = schemeAreaGroupDao.getSchemeAreaGroupByProjectId(projectId);
        return schemeAreaGroupList;
    }

    /**
     * 区域分组合并
     *
     * @param projectId
     * @param areaGroupIds
     */
    @Transactional
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

        SchemeAreaGroup schemeAreaGroup = new SchemeAreaGroup();
        schemeAreaGroup.setPid(0);
        schemeAreaGroup.setProjectId(projectId);
        schemeAreaGroup.setProvince(province);
        schemeAreaGroup.setCity(city);
        schemeAreaGroup.setAreaName(erpAreaService.getAreaFullName(province, city, null));
        schemeAreaGroup.setValueTimePoint(schemeAreaGroupList.get(0).getValueTimePoint());
        schemeAreaGroup.setTimePointExplain(schemeAreaGroupList.get(0).getTimePointExplain());
        schemeAreaGroup.setBisEnable(true);
        schemeAreaGroup.setBisMerge(true);
        schemeAreaGroup.setCreator(commonService.thisUserAccount());
        schemeAreaGroupDao.add(schemeAreaGroup);

        for (SchemeAreaGroup areaGroup : schemeAreaGroupList) {
            areaGroup.setPid(schemeAreaGroup.getId());
            areaGroup.setBisEnable(false);
            schemeAreaGroupDao.update(areaGroup);
            schemeJudgeObjectService.updateSchemeJudgeObject(areaGroup.getId(), schemeAreaGroup.getId());
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
        if(CollectionUtils.isNotEmpty(schemeAreaGroupList)){
            for (SchemeAreaGroup areaGroup : schemeAreaGroupList) {
                areaGroup.setPid(0);
                areaGroup.setBisEnable(true);
                schemeAreaGroupDao.update(areaGroup);
                schemeJudgeObjectService.areaGroupReduction(areaGroup.getId());
            }
        }
        schemeAreaGroupDao.remove(id);
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

}
