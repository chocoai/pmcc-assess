package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicEstateTaggingDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateTagging;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

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


    /**
     * 新增或者修改
     *
     * @param basicEstateTagging
     * @return
     * @throws Exception
     */
    public void saveBasicEstateTagging(BasicEstateTagging basicEstateTagging) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        if(!StringUtils.isEmpty(basicEstateTagging.getBuildingNumber())){
            stringBuilder.append(basicEstateTagging.getBuildingNumber()).append("栋");
        }
        if(!StringUtils.isEmpty(basicEstateTagging.getUnitNumber())){
            stringBuilder.append(basicEstateTagging.getUnitNumber()).append("单元");
        }
        if(!StringUtils.isEmpty(basicEstateTagging.getRemark())){
            stringBuilder.append(basicEstateTagging.getRemark());
        }
        basicEstateTagging.setContent(stringBuilder.toString());
        basicEstateTagging.setCreator(commonService.thisUserAccount());
        basicEstateTaggingDao.saveBasicEstateTagging(basicEstateTagging);
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

    public void removeBasicEstateTagging(BasicEstateTagging basicEstateTagging) throws Exception {
        basicEstateTaggingDao.removeBasicEstateTagging(basicEstateTagging);
    }

    public BootstrapTableVo getBootstrapTableVo(Integer estateId) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        BasicEstateTagging where = new BasicEstateTagging();
        if (estateId <= 0) {
            where.setEstateId(0);
            where.setCreator(commonService.thisUserAccount());
        } else {
            where.setEstateId(estateId);
        }
        List<BasicEstateTagging> basicEstateTaggingList = basicEstateTaggingDao.basicEstateTaggingList(where);
        vo.setTotal((long) basicEstateTaggingList.size());
        vo.setRows(ObjectUtils.isEmpty(basicEstateTaggingList) ? Lists.newArrayList() : basicEstateTaggingList);
        return vo;
    }

}
