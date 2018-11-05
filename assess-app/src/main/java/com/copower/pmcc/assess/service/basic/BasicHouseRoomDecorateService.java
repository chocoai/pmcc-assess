package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicHouseRoomDecorateDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseRoomDecorate;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseRoomDecorateVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
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
 * @Date: 2018/11/2 10:12
 * @Description:
 */
@Service
public class BasicHouseRoomDecorateService {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseRoomDecorateDao basicHouseRoomDecorateDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
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
    public BasicHouseRoomDecorate getBasicHouseRoomDecorateById(Integer id) throws Exception {
        return basicHouseRoomDecorateDao.getBasicHouseRoomDecorateById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseRoomDecorate
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouseRoomDecorate(BasicHouseRoomDecorate basicHouseRoomDecorate) throws Exception {
        if (basicHouseRoomDecorate.getId() == null || basicHouseRoomDecorate.getId().intValue() == 0) {
            basicHouseRoomDecorate.setCreator(commonService.thisUserAccount());
            Integer id = basicHouseRoomDecorateDao.saveBasicHouseRoomDecorate(basicHouseRoomDecorate);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicHouseRoomDecorate.class), id);
            return id;
        } else {
            BasicHouseRoomDecorate oo = basicHouseRoomDecorateDao.getBasicHouseRoomDecorateById(basicHouseRoomDecorate.getId());
            basicHouseRoomDecorateDao.updateBasicHouseRoomDecorate(basicHouseRoomDecorate);
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
    public boolean deleteBasicHouseRoomDecorate(Integer id) throws Exception {
        return basicHouseRoomDecorateDao.deleteBasicHouseRoomDecorate(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicHouseRoomDecorate
     * @return
     * @throws Exception
     */
    public List<BasicHouseRoomDecorate> basicHouseRoomDecorateList(BasicHouseRoomDecorate basicHouseRoomDecorate) throws Exception {
        return basicHouseRoomDecorateDao.basicHouseRoomDecorateList(basicHouseRoomDecorate);
    }


    public BootstrapTableVo getBootstrapTableVo(BasicHouseRoomDecorate basicHouseRoomDecorate) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseRoomDecorate> basicHouseRoomDecorateList = basicHouseRoomDecorateDao.basicHouseRoomDecorateList(basicHouseRoomDecorate);
        List<BasicHouseRoomDecorateVo> vos = Lists.newArrayList();
        basicHouseRoomDecorateList.forEach(oo -> vos.add(getBasicHouseRoomDecorateVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicHouseRoomDecorateVo>(10) : vos);
        return vo;
    }

    public BasicHouseRoomDecorateVo getBasicHouseRoomDecorateVo(BasicHouseRoomDecorate basicHouseRoomDecorate) {
        if (basicHouseRoomDecorate == null) {
            return null;
        }
        BasicHouseRoomDecorateVo vo = new BasicHouseRoomDecorateVo();
        BeanUtils.copyProperties(basicHouseRoomDecorate, vo);
        BaseDataDic dataDic = null;
        if (StringUtils.isNotEmpty(basicHouseRoomDecorate.getConstructionTechnology())) {
            if (NumberUtils.isNumber(basicHouseRoomDecorate.getConstructionTechnology())) {
                dataDic = baseDataDicService.getDataDicById(Integer.parseInt(basicHouseRoomDecorate.getConstructionTechnology()));
                if (dataDic != null) {
                    vo.setConstructionTechnologyName(dataDic.getName());
                    dataDic = null;
                }
            }
        }
        if (StringUtils.isNotEmpty(basicHouseRoomDecorate.getMaterialPrice())) {
            if (NumberUtils.isNumber(basicHouseRoomDecorate.getMaterialPrice())) {
                dataDic = baseDataDicService.getDataDicById(Integer.parseInt(basicHouseRoomDecorate.getMaterialPrice()));
                if (dataDic != null) {
                    vo.setMaterialPriceName(dataDic.getName());
                    dataDic = null;
                }
            }
        }
        if (basicHouseRoomDecorate.getPart() != null){
            dataDic = baseDataDicService.getDataDicById(basicHouseRoomDecorate.getPart());
            if (dataDic != null){
                vo.setPartName(dataDic.getName());
                dataDic = null;
            }
        }
        if (basicHouseRoomDecorate.getMaterial() != null){
            dataDic = baseDataDicService.getDataDicById(basicHouseRoomDecorate.getMaterial());
            if (dataDic != null){
                vo.setMaterialName(dataDic.getName());
                dataDic = null;
            }
        }
        return vo;
    }
}
