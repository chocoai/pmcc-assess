package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.common.enums.EstateTaggingTypeEnum;
import com.copower.pmcc.assess.dal.basic.dao.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateTagging;
import com.copower.pmcc.erp.common.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

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
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void clearInvalidData(Integer appId)throws Exception{
        BasicEstateTagging query = new BasicEstateTagging();
        query.setApplyId(appId);
        basicEstateTaggingDao.removeBasicEstateTagging(query);
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicEstateTagging getBasicEstateTaggingById(Integer id) throws Exception {
        return basicEstateTaggingDao.getBasicEstateTaggingById(id);
    }

    @Transactional(value = "transactionManagerBasic", rollbackFor = Exception.class)
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
    public List<BasicEstateTagging> basicEstateTaggingList(BasicEstateTagging basicEstateTagging) throws Exception {
        return basicEstateTaggingDao.basicEstateTaggingList(basicEstateTagging);
    }

    public List<BasicEstateTagging> getEstateTaggingList(Integer applyId, String type) throws Exception {
        BasicEstateTagging basicEstateTagging = new BasicEstateTagging();
        if (applyId == null || applyId == 0)
            basicEstateTagging.setCreator(commonService.thisUserAccount());
        basicEstateTagging.setApplyId(applyId);
        basicEstateTagging.setType(type);
        return basicEstateTaggingDao.basicEstateTaggingList(basicEstateTagging);
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
}
