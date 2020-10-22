package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseFaceStreetDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseFaceStreet;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseFaceStreetVo;
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
 * @Date: 2018/11/6 11:28
 * @Description:临街
 */
@Service
public class BasicHouseFaceStreetService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseFaceStreetDao basicHouseFaceStreetDao;
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
    public BasicHouseFaceStreet getBasicHouseFaceStreetById(Integer id) throws Exception {
        return basicHouseFaceStreetDao.getBasicHouseFaceStreetById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseFaceStreet
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouseFaceStreet(BasicHouseFaceStreet basicHouseFaceStreet, boolean updateNull) throws Exception {
        if (basicHouseFaceStreet.getId() == null || basicHouseFaceStreet.getId().intValue() == 0) {
            basicHouseFaceStreet.setCreator(commonService.thisUserAccount());
            Integer id = basicHouseFaceStreetDao.saveBasicHouseFaceStreet(basicHouseFaceStreet);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicHouseFaceStreet.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicHouseFaceStreet houseFaceStreet = basicHouseFaceStreetDao.getBasicHouseFaceStreetById(basicHouseFaceStreet.getId());
                if (houseFaceStreet != null) {
                    basicHouseFaceStreet.setBisDelete(houseFaceStreet.getBisDelete());
                    basicHouseFaceStreet.setCreator(houseFaceStreet.getCreator());
                    basicHouseFaceStreet.setGmtCreated(houseFaceStreet.getGmtCreated());
                    basicHouseFaceStreet.setGmtModified(DateUtils.now());
                }
            }
            basicHouseFaceStreetDao.updateBasicHouseFaceStreet(basicHouseFaceStreet, updateNull);
            return basicHouseFaceStreet.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicHouseFaceStreet(Integer id) throws Exception {
        return basicHouseFaceStreetDao.deleteBasicHouseFaceStreet(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicHouseFaceStreet
     * @return
     * @throws Exception
     */
    public List<BasicHouseFaceStreet> basicHouseFaceStreetList(BasicHouseFaceStreet basicHouseFaceStreet)  {
        return basicHouseFaceStreetDao.basicHouseFaceStreetList(basicHouseFaceStreet);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouseFaceStreet basicHouseFaceStreet) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseFaceStreet> basicHouseFaceStreetList = basicHouseFaceStreetDao.basicHouseFaceStreetList(basicHouseFaceStreet);
        List<BasicHouseFaceStreetVo> vos = Lists.newArrayList();
        basicHouseFaceStreetList.forEach(oo -> vos.add(getBasicHouseFaceStreetVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicHouseFaceStreetVo>(10) : vos);
        return vo;
    }

    public BasicHouseFaceStreetVo getBasicHouseFaceStreetVo(BasicHouseFaceStreet basicHouseFaceStreet) {
        if (basicHouseFaceStreet == null) {
            return null;
        }
        BasicHouseFaceStreetVo vo = new BasicHouseFaceStreetVo();
        BeanUtils.copyProperties(basicHouseFaceStreet, vo);
        vo.setVisitorsFlowrateName(baseDataDicService.getNameById(basicHouseFaceStreet.getVisitorsFlowrate()));
        vo.setTrafficFlowName(baseDataDicService.getNameById(basicHouseFaceStreet.getTrafficFlow()));
        vo.setStreetLevelName(baseDataDicService.getNameById(basicHouseFaceStreet.getStreetLevel()));
        vo.setPositionName(baseDataDicService.getNameById(basicHouseFaceStreet.getPosition()));
        vo.setDistanceName(baseDataDicService.getNameById(basicHouseFaceStreet.getDistance()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicHouseFaceStreet.getCreator()));
        return vo;
    }

    /**
     * 根据查询条件判断是否有数据
     *
     * @param houseId
     * @return
     */
    public boolean hasHouseFaceStreetData(Integer houseId) {
        return basicHouseFaceStreetDao.countByHouseId(houseId) > 0;
    }

    public List<BasicHouseFaceStreet> getBasicHouseFaceStreetList(Integer houseId) {
        BasicHouseFaceStreet basicHouseFaceStreet = new BasicHouseFaceStreet();
        basicHouseFaceStreet.setHouseId(houseId);
        List<BasicHouseFaceStreet> basicHouseFaceStreetList = basicHouseFaceStreetDao.basicHouseFaceStreetList(basicHouseFaceStreet);
        return basicHouseFaceStreetList;
    }

    public List<BasicHouseFaceStreetVo> getBasicHouseFaceStreetVoList(Integer houseId) {
        return LangUtils.transform(getBasicHouseFaceStreetList(houseId), o -> getBasicHouseFaceStreetVo(o));
    }
}
