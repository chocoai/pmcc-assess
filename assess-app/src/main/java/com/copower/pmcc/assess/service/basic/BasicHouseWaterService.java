package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseWaterDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseWater;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseWaterVo;
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
 * @Date: 2018/11/6 11:21
 * @Description:供排水情况
 */
@Service
public class BasicHouseWaterService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseWaterDao basicHouseWaterDao;
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
    public BasicHouseWater getBasicHouseWaterById(Integer id) throws Exception {
        return basicHouseWaterDao.getBasicHouseWaterById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseWater
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouseWater(BasicHouseWater basicHouseWater, boolean updateNull) throws Exception {
        if (basicHouseWater.getId() == null || basicHouseWater.getId().intValue() == 0) {
            basicHouseWater.setCreator(commonService.thisUserAccount());
            Integer id = basicHouseWaterDao.addBasicHouseWater(basicHouseWater);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicHouseWater.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicHouseWater houseWater = basicHouseWaterDao.getBasicHouseWaterById(basicHouseWater.getId());
                if (houseWater != null) {
                    basicHouseWater.setBisDelete(houseWater.getBisDelete());
                    basicHouseWater.setCreator(houseWater.getCreator());
                    basicHouseWater.setGmtCreated(houseWater.getGmtCreated());
                    basicHouseWater.setGmtModified(DateUtils.now());
                }
            }
            basicHouseWaterDao.updateBasicHouseWater(basicHouseWater, updateNull);
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
    public boolean deleteBasicHouseWater(Integer id) throws Exception {
        return basicHouseWaterDao.deleteBasicHouseWater(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicHouseWater
     * @return
     * @throws Exception
     */
    public List<BasicHouseWater> basicHouseWaterList(BasicHouseWater basicHouseWater) throws Exception {
        return basicHouseWaterDao.basicHouseWaterList(basicHouseWater);
    }

    public List<BasicHouseWater> getBasicHouseWaterList(Integer houseId)  {
        BasicHouseWater where = new BasicHouseWater();
        where.setHouseId(houseId);
        return basicHouseWaterDao.basicHouseWaterList(where);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouseWater basicHouseWater) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseWater> basicHouseWaterList = basicHouseWaterDao.basicHouseWaterList(basicHouseWater);
        List<BasicHouseWaterVo> vos = Lists.newArrayList();
        basicHouseWaterList.forEach(oo -> vos.add(getBasicHouseWaterVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicHouseWaterVo>(10) : vos);
        return vo;
    }

    public BasicHouseWaterVo getBasicHouseWaterVo(BasicHouseWater basicHouseWater) {
        if (basicHouseWater == null) {
            return null;
        }
        BasicHouseWaterVo vo = new BasicHouseWaterVo();
        BeanUtils.copyProperties(basicHouseWater, vo);
        vo.setPretreatedWaterName(baseDataDicService.getNameById(basicHouseWater.getPretreatedWater()));
        vo.setPurificationEquipmentPriceName(baseDataDicService.getNameById(basicHouseWater.getPurificationEquipmentPrice()));
        vo.setBoosterEquipmentName(baseDataDicService.getNameById(basicHouseWater.getBoosterEquipment()));
        vo.setPipingLayoutName(baseDataDicService.getNameById(basicHouseWater.getPipingLayout()));
        vo.setPipeMaterialName(baseDataDicService.getNameById(basicHouseWater.getPipeMaterial()));
        vo.setGradeName(baseDataDicService.getNameById(basicHouseWater.getGrade()));
        vo.setSupplyModeName(baseDataDicService.getNameById(basicHouseWater.getSupplyMode()));
        vo.setPurificationEquipmentPriceName(baseDataDicService.getNameById(basicHouseWater.getPurificationEquipmentPrice()));
        vo.setFireWaterSupplyName(baseDataDicService.getNameById(basicHouseWater.getFireWaterSupply()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicHouseWater.getCreator()));
        return vo;
    }

    /**
     * 根据查询条件判断是否有数据
     *
     * @param houseId
     * @return
     */
    public boolean hasHouseWaterData(Integer houseId) {
        return basicHouseWaterDao.countByHouseId(houseId) > 0;
    }


    public List<BasicHouseWaterVo> getBasicHouseWaterVoList(Integer houseId) throws Exception {
        return LangUtils.transform(getBasicHouseWaterList(houseId), o -> getBasicHouseWaterVo(o));
    }
}
