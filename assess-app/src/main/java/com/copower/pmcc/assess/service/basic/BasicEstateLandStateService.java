package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateLandStateDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandState;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.data.DataLandLevelService;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther: zch
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicEstateLandStateService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicEstateLandStateDao basicEstateLandStateDao;
    @Autowired
    private DataLandLevelService dataLandLevelService;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicEstateLandState getBasicEstateLandStateById(Integer id) throws Exception {
        return basicEstateLandStateDao.getBasicEstateLandStateById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicEstateLandState
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicEstateLandState(BasicEstateLandState basicEstateLandState) throws Exception {
        if (basicEstateLandState.getId() == null || basicEstateLandState.getId().intValue() == 0) {
            return basicEstateLandStateDao.saveBasicEstateLandState(basicEstateLandState);
        } else {
            basicEstateLandStateDao.updateBasicEstateLandState(basicEstateLandState);
            return null;
        }
    }

    public Integer upgradeVersion(BasicEstateLandState basicEstateLandState) throws Exception {
        if (basicEstateLandState.getId() == null || basicEstateLandState.getId().intValue() == 0) {
            basicEstateLandState.setCreator(commonService.thisUserAccount());
            Integer id = basicEstateLandStateDao.saveBasicEstateLandState(basicEstateLandState);
            return id;
        } else {
            basicEstateLandStateDao.updateBasicEstateLandState(basicEstateLandState);
            return null;
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicEstateLandState(Integer id) throws Exception {
        return basicEstateLandStateDao.deleteBasicEstateLandState(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicEstateLandState
     * @return
     * @throws Exception
     */
    public List<BasicEstateLandState> basicEstateLandStateList(BasicEstateLandState basicEstateLandState) throws Exception {
        return basicEstateLandStateDao.basicEstateLandStateList(basicEstateLandState);
    }

    public BasicEstateLandState getLandStateByEstateId(Integer estateId) {
        try {
            BasicEstateLandState basicEstateLandState = new BasicEstateLandState();
            basicEstateLandState.setEstateId(estateId);
            List<BasicEstateLandState> estateLandStates = basicEstateLandStateDao.basicEstateLandStateList(basicEstateLandState);
            if (!CollectionUtils.isEmpty(estateLandStates)) return estateLandStates.get(0);
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    public BootstrapTableVo getBootstrapTableVo(BasicEstateLandState basicEstateLandState) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstateLandState> basicEstateLandStateList = basicEstateLandStateDao.basicEstateLandStateList(basicEstateLandState);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicEstateLandStateList) ? new ArrayList<BasicEstateLandState>(10) : basicEstateLandStateList);
        return vo;
    }

    public BasicEstateLandStateVo getBasicEstateLandStateVo(BasicEstateLandState basicEstateLandState) {
        if (basicEstateLandState == null) {
            return null;
        }
        BasicEstateLandStateVo vo = new BasicEstateLandStateVo();
        BeanUtils.copyProperties(basicEstateLandState, vo);
        if (basicEstateLandState.getLandUseType() != null) {
            vo.setLandUseTypeName(baseDataDicService.getNameById(basicEstateLandState.getLandUseType()));
        }
        if (basicEstateLandState.getLandUseCategory() != null) {
            vo.setLandUseCategoryName(baseDataDicService.getNameById(basicEstateLandState.getLandUseCategory()));
        }
        if (basicEstateLandState.getLandLevel() != null) {
            vo.setLandLevelName(dataLandLevelDetailService.getCacheNameById(basicEstateLandState.getLandLevel()));
        }
        if (basicEstateLandState.getShapeState() != null) {
            vo.setShapeStateName(baseDataDicService.getNameById(basicEstateLandState.getShapeState()));
        }
        if (basicEstateLandState.getPlaneness() != null) {
            vo.setPlanenessName(baseDataDicService.getNameById(basicEstateLandState.getPlaneness()));
        }
        if (basicEstateLandState.getDevelopmentDegree() != null) {
            vo.setDevelopmentDegreeName(baseDataDicService.getNameById(basicEstateLandState.getDevelopmentDegree()));
        }
        if (basicEstateLandState.getTopographicTerrain() != null) {
            vo.setTopographicTerrainName(baseDataDicService.getNameById(basicEstateLandState.getTopographicTerrain()));
        }
        vo.setInfrastructureCompletenessName(baseDataDicService.getNameById(basicEstateLandState.getInfrastructureCompleteness()));
        vo.setPhName(baseDataDicService.getNameById(basicEstateLandState.getPh()));
        vo.setHoldOnName(baseDataDicService.getNameById(basicEstateLandState.getHoldOn()));
        vo.setFertilityName(baseDataDicService.getNameById(basicEstateLandState.getFertility()));
        vo.setContaminatedName(baseDataDicService.getNameById(basicEstateLandState.getContaminated()));
        vo.setBearingCapacityName(baseDataDicService.getNameById(basicEstateLandState.getBearingCapacity()));
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(basicEstateLandState.getDevelopmentDegreeContent())){
            List<String> stringList = Lists.newArrayList(basicEstateLandState.getDevelopmentDegreeContent().split(",")).stream().map(s -> baseDataDicService.getNameById(s)).collect(Collectors.toList());
            vo.setDevelopmentDegreeContentName(org.apache.commons.lang3.StringUtils.join(stringList,"、"));
        }
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(basicEstateLandState.getLandLevelGrade())){
            vo.setLandLevelGradeName(org.apache.commons.lang3.StringUtils.join(
                    Arrays.asList(basicEstateLandState.getLandLevelGrade().split(",")).stream().map(s -> baseDataDicService.getNameById(s) ).collect(Collectors.toList())
                    ,","));
        }
        return vo;
    }

}
