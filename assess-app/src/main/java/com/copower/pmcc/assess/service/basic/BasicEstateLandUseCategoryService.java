package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateLandUseCategoryDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandUseCategory;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandUseCategoryVo;
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
public class BasicEstateLandUseCategoryService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateLandUseCategoryDao basicEstateLandUseCategoryDao;
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
    public BasicEstateLandUseCategory getBasicEstateLandUseCategoryById(Integer id) throws Exception {
        return basicEstateLandUseCategoryDao.getBasicEstateLandUseCategoryById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicEstateLandUseCategory
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicEstateLandUseCategory(BasicEstateLandUseCategory basicEstateLandUseCategory, boolean updateNull) throws Exception {
        if (basicEstateLandUseCategory.getId() == null || basicEstateLandUseCategory.getId().intValue() == 0) {
            basicEstateLandUseCategory.setCreator(commonService.thisUserAccount());
            Integer id = basicEstateLandUseCategoryDao.saveBasicEstateLandUseCategory(basicEstateLandUseCategory);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstateLandUseCategory.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicEstateLandUseCategory estateLandUseCategory = basicEstateLandUseCategoryDao.getBasicEstateLandUseCategoryById(basicEstateLandUseCategory.getId());
                if (estateLandUseCategory != null) {
                    basicEstateLandUseCategory.setBisDelete(estateLandUseCategory.getBisDelete());
                    basicEstateLandUseCategory.setCreator(estateLandUseCategory.getCreator());
                    basicEstateLandUseCategory.setGmtCreated(estateLandUseCategory.getGmtCreated());
                    basicEstateLandUseCategory.setGmtModified(DateUtils.now());
                }
            }
            basicEstateLandUseCategoryDao.updateBasicEstateLandUseCategory(basicEstateLandUseCategory, updateNull);
            return basicEstateLandUseCategory.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicEstateLandUseCategory(Integer id) throws Exception {
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(id);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateLandUseCategory.class));
        boolean flag = basicEstateLandUseCategoryDao.deleteBasicEstateLandUseCategory(id);
        baseAttachmentService.deleteAttachmentByDto(sysAttachmentDto);
        return flag;
    }

    /**
     * 获取数据列表
     *
     * @param basicEstateLandUseCategory
     * @return
     * @throws Exception
     */
    public List<BasicEstateLandUseCategoryVo> basicEstateLandUseCategoryList(BasicEstateLandUseCategory basicEstateLandUseCategory) {
        List<BasicEstateLandUseCategory> villageList = basicEstateLandUseCategoryDao.basicEstateLandUseCategoryList(basicEstateLandUseCategory);
        List<BasicEstateLandUseCategoryVo> vos = Lists.newArrayList();
        villageList.forEach(oo -> vos.add(getBasicEstateLandUseCategoryVo(oo)));
        return vos;
    }


    public BootstrapTableVo getBootstrapTableVo(BasicEstateLandUseCategory basicEstateLandUseCategory) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstateLandUseCategory> basicEstateLandUseCategoryList = basicEstateLandUseCategoryDao.basicEstateLandUseCategoryList(basicEstateLandUseCategory);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicEstateLandUseCategoryList) ? new ArrayList<BasicEstateLandUseCategory>(10) : basicEstateLandUseCategoryList);
        return vo;
    }

    public BasicEstateLandUseCategoryVo getBasicEstateLandUseCategoryVo(BasicEstateLandUseCategory basicEstateLandUseCategory) {
        if (basicEstateLandUseCategory == null) {
            return null;
        }
        BasicEstateLandUseCategoryVo vo = new BasicEstateLandUseCategoryVo();
        BeanUtils.copyProperties(basicEstateLandUseCategory, vo);
        //vo.setDistrictName(erpAreaService.getSysAreaName(basicEstateLandUseCategory.getDistrict()));
        return vo;
    }
}
