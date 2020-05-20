package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicMatchingEnvironmentDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingEnvironment;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingEnvironmentVo;
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
import org.apache.commons.lang3.math.NumberUtils;
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
 * @Date: 2018/11/6 11:01
 * @Description:环境因素
 */
@Service
public class BasicMatchingEnvironmentService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicMatchingEnvironmentDao basicMatchingEnvironmentDao;
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
    public BasicMatchingEnvironment getBasicMatchingEnvironmentById(Integer id) throws Exception {
        return basicMatchingEnvironmentDao.getBasicMatchingEnvironmentById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicMatchingEnvironment
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicMatchingEnvironment(BasicMatchingEnvironment basicMatchingEnvironment, boolean updateNull) throws Exception {
        if (basicMatchingEnvironment.getId() == null || basicMatchingEnvironment.getId().intValue() == 0) {
            basicMatchingEnvironment.setCreator(commonService.thisUserAccount());
            Integer id = basicMatchingEnvironmentDao.addBasicMatchingEnvironment(basicMatchingEnvironment);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicMatchingEnvironment.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicMatchingEnvironment matchingEnvironment = basicMatchingEnvironmentDao.getBasicMatchingEnvironmentById(basicMatchingEnvironment.getId());
                if (matchingEnvironment != null) {
                    basicMatchingEnvironment.setBisDelete(matchingEnvironment.getBisDelete());
                    basicMatchingEnvironment.setCreator(matchingEnvironment.getCreator());
                    basicMatchingEnvironment.setGmtCreated(matchingEnvironment.getGmtCreated());
                    basicMatchingEnvironment.setGmtModified(DateUtils.now());
                }
            }
            basicMatchingEnvironmentDao.updateBasicMatchingEnvironment(basicMatchingEnvironment, updateNull);
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
    public boolean deleteBasicMatchingEnvironment(Integer id) throws Exception {
        return basicMatchingEnvironmentDao.deleteBasicMatchingEnvironment(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicMatchingEnvironment
     * @return
     * @throws Exception
     */
    public List<BasicMatchingEnvironment> basicMatchingEnvironmentList(BasicMatchingEnvironment basicMatchingEnvironment) throws Exception {
        return basicMatchingEnvironmentDao.basicMatchingEnvironmentList(basicMatchingEnvironment);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicMatchingEnvironment basicMatchingEnvironment) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicMatchingEnvironment> basicMatchingEnvironmentList = basicMatchingEnvironmentDao.basicMatchingEnvironmentList(basicMatchingEnvironment);
        List<BasicMatchingEnvironmentVo> vos = Lists.newArrayList();
        basicMatchingEnvironmentList.forEach(oo -> vos.add(getBasicMatchingEnvironmentVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicMatchingEnvironmentVo>(10) : vos);
        return vo;
    }

    public List<BasicMatchingEnvironmentVo> getBasicMatchingEnvironmentVos(Integer estateId) {
        if (estateId == null) return null;
        BasicMatchingEnvironment where = new BasicMatchingEnvironment();
        where.setEstateId(estateId);
        return LangUtils.transform(basicMatchingEnvironmentDao.basicMatchingEnvironmentList(where), o -> getBasicMatchingEnvironmentVo(o));
    }

    public BasicMatchingEnvironmentVo getBasicMatchingEnvironmentVo(BasicMatchingEnvironment basicMatchingEnvironment) {
        if (basicMatchingEnvironment == null) {
            return null;
        }
        BasicMatchingEnvironmentVo vo = new BasicMatchingEnvironmentVo();
        BeanUtils.copyProperties(basicMatchingEnvironment, vo);
        vo.setTypeName(baseDataDicService.getNameById(NumberUtils.isNumber(basicMatchingEnvironment.getType()) ? Integer.parseInt(basicMatchingEnvironment.getType()) : null));
        vo.setCategoryName(baseDataDicService.getNameById(basicMatchingEnvironment.getCategory()));
        vo.setInfluenceDegreeName(baseDataDicService.getNameById(basicMatchingEnvironment.getInfluenceDegree()));
        vo.setHumanImpactName(baseDataDicService.getNameById(basicMatchingEnvironment.getHumanImpact()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicMatchingEnvironment.getCreator()));
        return vo;
    }

}
