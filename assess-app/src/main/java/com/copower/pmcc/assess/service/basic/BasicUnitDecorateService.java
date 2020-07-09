package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitDecorateDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitDecorate;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitDecorateVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
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
 * @Date: 2018/11/5 16:21
 * @Description:室内公共装修
 */
@Service
public class BasicUnitDecorateService {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicUnitDecorateDao basicUnitDecorateDao;
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
    public BasicUnitDecorate getBasicUnitDecorateById(Integer id) throws Exception {
        return basicUnitDecorateDao.getBasicUnitDecorateById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicUnitDecorate
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicUnitDecorate(BasicUnitDecorate basicUnitDecorate, boolean updateNull) throws Exception {
        if (basicUnitDecorate.getId() == null || basicUnitDecorate.getId().intValue() == 0) {
            basicUnitDecorate.setCreator(commonService.thisUserAccount());
            Integer id = basicUnitDecorateDao.addBasicUnitDecorate(basicUnitDecorate);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicUnitDecorate unitDecorate = basicUnitDecorateDao.getBasicUnitDecorateById(basicUnitDecorate.getId());
                if (unitDecorate != null) {
                    basicUnitDecorate.setBisDelete(unitDecorate.getBisDelete());
                    basicUnitDecorate.setCreator(unitDecorate.getCreator());
                    basicUnitDecorate.setGmtCreated(unitDecorate.getGmtCreated());
                    basicUnitDecorate.setGmtModified(DateUtils.now());
                }
            }
            basicUnitDecorateDao.updateBasicUnitDecorate(basicUnitDecorate, updateNull);
            return basicUnitDecorate.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicUnitDecorate(Integer id) throws Exception {
        return basicUnitDecorateDao.deleteBasicUnitDecorate(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicUnitDecorate
     * @return
     * @throws Exception
     */
    public List<BasicUnitDecorate> basicUnitDecorateList(BasicUnitDecorate basicUnitDecorate) throws Exception {
        return basicUnitDecorateDao.basicUnitDecorateList(basicUnitDecorate);
    }

    public List<BasicUnitDecorateVo> getBasicUnitDecorateList(Integer unitId)  {
        if (unitId == null) return null;
        BasicUnitDecorate where = new BasicUnitDecorate();
        where.setUnitId(unitId);
        return LangUtils.transform(basicUnitDecorateDao.basicUnitDecorateList(where), o -> getBasicUnitDecorateVo(o));
    }

    public BootstrapTableVo getBootstrapTableVo(BasicUnitDecorate basicUnitDecorate) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicUnitDecorate> basicUnitDecorateList = basicUnitDecorateDao.basicUnitDecorateList(basicUnitDecorate);
        List<BasicUnitDecorateVo> vos = Lists.newArrayList();
        basicUnitDecorateList.forEach(oo -> vos.add(getBasicUnitDecorateVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicUnitDecorateVo>(10) : vos);
        return vo;
    }

    public BasicUnitDecorateVo getBasicUnitDecorateVo(BasicUnitDecorate basicUnitDecorate) {
        if (basicUnitDecorate == null) {
            return null;
        }
        BasicUnitDecorateVo vo = new BasicUnitDecorateVo();
        BeanUtils.copyProperties(basicUnitDecorate, vo);
        vo.setDecorationPartName(baseDataDicService.getNameById(basicUnitDecorate.getDecorationPart()));
        vo.setConstructionTechnologyName(baseDataDicService.getNameById(basicUnitDecorate.getConstructionTechnology()));
        vo.setMaterialPriceName(baseDataDicService.getNameById(basicUnitDecorate.getMaterialPriceRange()));
        vo.setDecoratingMaterialName(baseDataDicService.getNameById(basicUnitDecorate.getDecoratingMaterial()));
        vo.setMaterialGradeName(baseDataDicService.getNameById(basicUnitDecorate.getMaterialGrade()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicUnitDecorate.getCreator()));
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(basicUnitDecorate.getId(), null, FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto)).append(" ");
            }
            vo.setFileViewName(builder.toString());
        }
        return vo;
    }
}
