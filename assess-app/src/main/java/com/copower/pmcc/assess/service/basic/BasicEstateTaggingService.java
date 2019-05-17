package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateTagging;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateTagging;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateTaggingGaoDe;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateTaggingVo;
import com.copower.pmcc.assess.service.cases.CaseEstateTaggingService;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:54
 * @Description:
 */
@Service
public class BasicEstateTaggingService {
    @Autowired
    private BasicEstateTaggingDao basicEstateTaggingDao;
    @Autowired
    private CaseEstateTaggingService caseEstateTaggingService;
    @Autowired
    private BasicApplyService basicApplyService;
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void clearInvalidData(Integer appId) throws Exception {
        BasicEstateTagging query = new BasicEstateTagging();
        query.setApplyId(appId);
        if (appId == null || appId == 0)
            query.setCreator(commonService.thisUserAccount());
        basicEstateTaggingDao.removeBasicEstateTagging(query);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicEstateTagging getBasicEstateTaggingById(Integer id) {
        return basicEstateTaggingDao.getBasicEstateTaggingById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addBasicEstateTagging(BasicEstateTagging basicEstateTagging) throws Exception {
        //先清除标记
        BasicEstateTagging where = new BasicEstateTagging();
        if (basicEstateTagging.getApplyId() == null || basicEstateTagging.getApplyId() == 0)
            where.setCreator(commonService.thisUserAccount());
        where.setApplyId(basicEstateTagging.getApplyId());
        where.setType(basicEstateTagging.getType());
        basicEstateTaggingDao.removeBasicEstateTagging(where);

        basicEstateTagging.setCreator(commonService.thisUserAccount());
        basicEstateTaggingDao.addBasicEstateTagging(basicEstateTagging);
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicEstateTagging(Integer id) throws Exception {
        return basicEstateTaggingDao.deleteBasicEstateTagging(id);
    }

    public boolean updateBasicEstateTagging(BasicEstateTagging basicEstateTagging) throws SQLException {
        return basicEstateTaggingDao.updateBasicEstateTagging(basicEstateTagging);
    }

    /**
     * 获取数据列表
     *
     * @param basicEstateTagging
     * @return
     * @throws Exception
     */
    public List<BasicEstateTagging> getBasicEstateTaggingList(BasicEstateTagging basicEstateTagging) throws Exception {
        return basicEstateTaggingDao.getBasicEstateTaggingList(basicEstateTagging);
    }

    public List<BasicEstateTaggingVo> getEstateTaggingList(Integer applyId, String type) {
        BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
        if (applyId == null || applyId == 0)
            basicEstateTagging.setCreator(commonService.thisUserAccount());
        basicEstateTagging.setApplyId(applyId);
        basicEstateTagging.setType(type);
        return basicEstateTaggingDao.getBasicEstateTaggingList(basicEstateTagging).stream().map(oo -> getBasicEstateTaggingVo(oo)).collect(Collectors.toList());
    }

    public void removeBasicEstateTagging(BasicEstateTagging basicEstateTagging) throws Exception {
        basicEstateTaggingDao.removeBasicEstateTagging(basicEstateTagging);
    }

    public Boolean hasBasicEstateTagging(Integer applyId, EstateTaggingTypeEnum estateTaggingTypeEnum) {
        BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
        basicEstateTagging.setApplyId(applyId);
        basicEstateTagging.setType(estateTaggingTypeEnum.getKey());
        if (applyId == null || applyId == 0)
            basicEstateTagging.setCreator(commonService.thisUserAccount());
        return basicEstateTaggingDao.getEstateTaggingCount(basicEstateTagging) > 0;
    }

    /**
     * 获取单元下的标注
     *
     * @param unitPartInMode
     * @param applyId
     * @param caseUnitId
     * @return
     */
    public BasicEstateTagging getUnitTagging(String unitPartInMode, Integer applyId, Integer caseUnitId) throws Exception {
        BasicEstateTagging tagging = new BasicEstateTagging();
        if (StringUtils.isBlank(unitPartInMode)) {
            CaseEstateTagging caseEstateTagging = caseEstateTaggingService.getCaseEstateTagging(caseUnitId, EstateTaggingTypeEnum.UNIT.getKey());
            if (caseEstateTagging == null) return null;
            BeanUtils.copyProperties(caseEstateTagging, tagging);
        } else {
            BasicEstateTagging where = new BasicEstateTagging();
            where.setApplyId(applyId);
            where.setType(EstateTaggingTypeEnum.UNIT.getKey());
            if (applyId == null || applyId == 0)
                where.setCreator(commonService.thisUserAccount());
            List<BasicEstateTagging> taggingList = basicEstateTaggingDao.getBasicEstateTaggingList(where);
            if (!CollectionUtils.isEmpty(taggingList))
                tagging = taggingList.get(0);
        }
        return tagging;
    }

    /**
     * 删除房屋下标注信息
     *
     * @param applyId
     */
    public void deleteHouseTagging(Integer applyId) throws SQLException {
        BasicEstateTagging where = new BasicEstateTagging();
        where.setApplyId(applyId);
        where.setType(EstateTaggingTypeEnum.HOUSE.getKey());
        if (applyId == null || applyId == 0)
            where.setCreator(commonService.thisUserAccount());
        List<BasicEstateTagging> taggingList = basicEstateTaggingDao.getBasicEstateTaggingList(where);
        if (CollectionUtils.isEmpty(taggingList)) return;
        taggingList.forEach(o -> basicEstateTaggingDao.deleteBasicEstateTagging(o.getId()));
    }

    public BasicEstateTaggingVo getBasicEstateTaggingVo(BasicEstateTagging oo) {
        if (oo == null) {
            return null;
        }
        BasicEstateTaggingVo vo = new BasicEstateTaggingVo();
        BeanUtils.copyProperties(oo, vo);
        if (StringUtils.isNotEmpty(oo.getPathArray())) {
            try {
                JSONObject jsonObject = JSON.parseObject(oo.getPathArray());
                if (jsonObject != null) {

                }
                List<BasicEstateTaggingGaoDe> gaoDeList = JSON.parseArray(oo.getPathArray(), BasicEstateTaggingGaoDe.class);
                if (org.apache.commons.collections.CollectionUtils.isNotEmpty(gaoDeList)) {
                    vo.getGaoDeList().addAll(gaoDeList);
                }
            } catch (Exception e) {
            }
        }
        return vo;
    }
}
