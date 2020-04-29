package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicMatchingMaterialDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingMaterial;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingMaterialVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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
 * @Date: 2018/11/6 11:11
 * @Description:原料供应及销售条件
 */
@Service
public class BasicMatchingMaterialService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicMatchingMaterialDao basicMatchingMaterialDao;
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
    public BasicMatchingMaterial getBasicMatchingMaterialById(Integer id) throws Exception {
        return basicMatchingMaterialDao.getBasicMatchingMaterialById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicMatchingMaterial
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicMatchingMaterial(BasicMatchingMaterial basicMatchingMaterial, boolean updateNull) throws Exception {
        if (basicMatchingMaterial.getId() == null || basicMatchingMaterial.getId().intValue() == 0) {
            basicMatchingMaterial.setCreator(commonService.thisUserAccount());
            Integer id = basicMatchingMaterialDao.addBasicMatchingMaterial(basicMatchingMaterial);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicMatchingMaterial.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicMatchingMaterial matchingMaterial = basicMatchingMaterialDao.getBasicMatchingMaterialById(basicMatchingMaterial.getId());
                if (matchingMaterial != null) {
                    basicMatchingMaterial.setBisDelete(matchingMaterial.getBisDelete());
                    basicMatchingMaterial.setCreator(matchingMaterial.getCreator());
                    basicMatchingMaterial.setGmtCreated(matchingMaterial.getGmtCreated());
                    basicMatchingMaterial.setGmtModified(DateUtils.now());
                }
            }
            basicMatchingMaterialDao.updateBasicMatchingMaterial(basicMatchingMaterial, updateNull);
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
    public boolean deleteBasicMatchingMaterial(Integer id) throws Exception {
        return basicMatchingMaterialDao.deleteBasicMatchingMaterial(id);
    }

    /**
     * 获取数据 by estateId
     * @param estateId
     * @return
     */
    public List<BasicMatchingMaterial> getListByEstateId(Integer estateId) {
        if (estateId == null) return null;
        BasicMatchingMaterial where = new BasicMatchingMaterial();
        where.setEstateId(estateId);
        return basicMatchingMaterialDao.basicMatchingMaterialList(where);
    }

    /**
     * 获取数据列表
     *
     * @param basicMatchingMaterial
     * @return
     * @throws Exception
     */
    public List<BasicMatchingMaterial> basicMatchingMaterialList(BasicMatchingMaterial basicMatchingMaterial) {
        return basicMatchingMaterialDao.basicMatchingMaterialList(basicMatchingMaterial);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicMatchingMaterial basicMatchingMaterial) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicMatchingMaterial> basicMatchingMaterialList = basicMatchingMaterialDao.basicMatchingMaterialList(basicMatchingMaterial);
        List<BasicMatchingMaterialVo> vos = Lists.newArrayList();
        basicMatchingMaterialList.forEach(oo -> vos.add(getBasicMatchingMaterialVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicMatchingMaterialVo>(10) : vos);
        return vo;
    }

    public BasicMatchingMaterialVo getBasicMatchingMaterialVo(BasicMatchingMaterial basicMatchingMaterial) {
        if (basicMatchingMaterial == null) {
            return null;
        }
        BasicMatchingMaterialVo vo = new BasicMatchingMaterialVo();
        BeanUtils.copyProperties(basicMatchingMaterial, vo);
        vo.setCategoryName(baseDataDicService.getNameById(basicMatchingMaterial.getCategory()));
        vo.setScaleName(baseDataDicService.getNameById(basicMatchingMaterial.getScale()));
        vo.setDistanceName(baseDataDicService.getNameById(basicMatchingMaterial.getDistance()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicMatchingMaterial.getCreator()));
        return vo;
    }

}
