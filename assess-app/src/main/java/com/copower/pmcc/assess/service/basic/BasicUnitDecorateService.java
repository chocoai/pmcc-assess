package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicUnitDecorateDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicUnitDecorate;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitDecorateVo;
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
    public Integer saveAndUpdateBasicUnitDecorate(BasicUnitDecorate basicUnitDecorate) throws Exception {
        if (basicUnitDecorate.getId() == null || basicUnitDecorate.getId().intValue() == 0) {
            basicUnitDecorate.setCreator(commonService.thisUserAccount());
            Integer id = basicUnitDecorateDao.saveBasicUnitDecorate(basicUnitDecorate);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicUnitDecorate.class), id);
            return  id ;
        } else {
            BasicUnitDecorate oo = basicUnitDecorateDao.getBasicUnitDecorateById(basicUnitDecorate.getId());
            basicUnitDecorateDao.updateBasicUnitDecorate(basicUnitDecorate);
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

    public void removeBasicUnitDecorate(BasicUnitDecorate basicUnitDecorate)throws Exception{
        basicUnitDecorateDao.removeBasicUnitDecorate(basicUnitDecorate);
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

    public BasicUnitDecorateVo getBasicUnitDecorateVo(BasicUnitDecorate basicUnitDecorate){
        if (basicUnitDecorate==null){
            return null;
        }
        BasicUnitDecorateVo vo = new BasicUnitDecorateVo();
        BeanUtils.copyProperties(basicUnitDecorate,vo);
        BaseDataDic dataDic = null;
        if (basicUnitDecorate.getConstructionTechnology() != null){
            dataDic = baseDataDicService.getDataDicById(basicUnitDecorate.getConstructionTechnology());
            if (dataDic != null){
                vo.setConstructionTechnologyName(dataDic.getName());
                dataDic = null;
            }
        }
        if (basicUnitDecorate.getDecorationPart() != null){
            dataDic = baseDataDicService.getDataDicById(basicUnitDecorate.getDecorationPart());
            if (dataDic != null){
                vo.setDecorationPartName(dataDic.getName());
                dataDic = null;
            }
        }
        if (basicUnitDecorate.getMaterialPriceRange() != null){
            dataDic = baseDataDicService.getDataDicById(basicUnitDecorate.getMaterialPriceRange());
            if (dataDic != null){
                vo.setMaterialPriceName(dataDic.getName());
                dataDic = null;
            }
        }
        if (basicUnitDecorate.getDecoratingMaterial() != null){
            dataDic = baseDataDicService.getDataDicById(basicUnitDecorate.getDecoratingMaterial());
            if (dataDic != null){
                vo.setDecoratingMaterialName(dataDic.getName());
                dataDic = null;
            }
        }
        return vo;
    }
}
