package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateVillageDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateVillage;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateVillageVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
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
 * @Date: 2018/10/30 11:52
 * @Description:楼盘 街道号
 */
@Service
public class BasicEstateVillageService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateVillageDao basicEstateVillageDao;
    @Autowired
    private ErpAreaService erpAreaService;
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
    public BasicEstateVillage getBasicEstateVillageById(Integer id) throws Exception {
        return basicEstateVillageDao.getBasicEstateVillageById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicEstateVillage
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicEstateVillage(BasicEstateVillage basicEstateVillage, boolean updateNull) throws Exception {
        if (basicEstateVillage.getId() == null || basicEstateVillage.getId().intValue() == 0) {
            basicEstateVillage.setCreator(commonService.thisUserAccount());
            Integer id = basicEstateVillageDao.saveBasicEstateVillage(basicEstateVillage);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstateVillage.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicEstateVillage estateVillage = basicEstateVillageDao.getBasicEstateVillageById(basicEstateVillage.getId());
                if (estateVillage != null) {
                    basicEstateVillage.setBisDelete(estateVillage.getBisDelete());
                    basicEstateVillage.setCreator(estateVillage.getCreator());
                    basicEstateVillage.setGmtCreated(estateVillage.getGmtCreated());
                    basicEstateVillage.setGmtModified(DateUtils.now());
                }
            }
            basicEstateVillageDao.updateBasicEstateVillage(basicEstateVillage, updateNull);
            return basicEstateVillage.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicEstateVillage(Integer id) throws Exception {
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(id);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateVillage.class));
        boolean flag = basicEstateVillageDao.deleteBasicEstateVillage(id);
        baseAttachmentService.deleteAttachmentByDto(sysAttachmentDto);
        return flag;
    }

    /**
     * 获取数据列表
     *
     * @param basicEstateVillage
     * @return
     * @throws Exception
     */
    public List<BasicEstateVillageVo> basicEstateVillageList(BasicEstateVillage basicEstateVillage) {
        List<BasicEstateVillage> villageList = basicEstateVillageDao.basicEstateVillageList(basicEstateVillage);
        List<BasicEstateVillageVo> vos = Lists.newArrayList();
        villageList.forEach(oo -> vos.add(getBasicEstateVillageVo(oo)));
        return vos;
    }


    public BootstrapTableVo getBootstrapTableVo(BasicEstateVillage basicEstateVillage) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstateVillage> basicEstateVillageList = basicEstateVillageDao.basicEstateVillageList(basicEstateVillage);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicEstateVillageList) ? new ArrayList<BasicEstateVillage>(10) : basicEstateVillageList);
        return vo;
    }

    public BasicEstateVillageVo getBasicEstateVillageVo(BasicEstateVillage basicEstateVillage) {
        if (basicEstateVillage == null) {
            return null;
        }
        BasicEstateVillageVo vo = new BasicEstateVillageVo();
        BeanUtils.copyProperties(basicEstateVillage, vo);
        vo.setDistrictName(erpAreaService.getSysAreaName(basicEstateVillage.getDistrict()));
        return vo;
    }
}
