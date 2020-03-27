package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseIntelligentDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseIntelligent;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseIntelligentVo;
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
 * @Date: 2018/11/6 11:25
 * @Description:电力通讯网络
 */
@Service
public class BasicHouseIntelligentService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseIntelligentDao basicHouseIntelligentDao;
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
    public BasicHouseIntelligent getBasicHouseIntelligentById(Integer id) throws Exception {
        return basicHouseIntelligentDao.getBasicHouseIntelligentById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseIntelligent
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouseIntelligent(BasicHouseIntelligent basicHouseIntelligent, boolean updateNull) throws Exception {
        if (basicHouseIntelligent.getId() == null || basicHouseIntelligent.getId().intValue() == 0) {
            basicHouseIntelligent.setCreator(commonService.thisUserAccount());
            Integer id = basicHouseIntelligentDao.addBasicHouseIntelligent(basicHouseIntelligent);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicHouseIntelligent.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicHouseIntelligent houseIntelligent = basicHouseIntelligentDao.getBasicHouseIntelligentById(basicHouseIntelligent.getId());
                if (houseIntelligent != null) {
                    basicHouseIntelligent.setBisDelete(houseIntelligent.getBisDelete());
                    basicHouseIntelligent.setCreator(houseIntelligent.getCreator());
                    basicHouseIntelligent.setGmtCreated(houseIntelligent.getGmtCreated());
                    basicHouseIntelligent.setGmtModified(DateUtils.now());
                }
            }
            basicHouseIntelligentDao.updateBasicHouseIntelligent(basicHouseIntelligent, updateNull);
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
    public boolean deleteBasicHouseIntelligent(Integer id) throws Exception {
        return basicHouseIntelligentDao.deleteBasicHouseIntelligent(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicHouseIntelligent
     * @return
     * @throws Exception
     */
    public List<BasicHouseIntelligent> basicHouseIntelligentList(BasicHouseIntelligent basicHouseIntelligent) throws Exception {
        return basicHouseIntelligentDao.basicHouseIntelligentList(basicHouseIntelligent);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouseIntelligent basicHouseIntelligent) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseIntelligent> basicHouseIntelligentList = basicHouseIntelligentDao.basicHouseIntelligentList(basicHouseIntelligent);
        List<BasicHouseIntelligentVo> vos = Lists.newArrayList();
        basicHouseIntelligentList.forEach(oo -> vos.add(getBasicHouseIntelligentVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicHouseIntelligentVo>(10) : vos);
        return vo;
    }

    public List<BasicHouseIntelligentVo> getBasicHouseIntelligentVos(Integer houseId) {
        BasicHouseIntelligent where = new BasicHouseIntelligent();
        where.setHouseId(houseId);
        return LangUtils.transform(basicHouseIntelligentDao.basicHouseIntelligentList(where), o -> getBasicHouseIntelligentVo(o));
    }

    public BasicHouseIntelligentVo getBasicHouseIntelligentVo(BasicHouseIntelligent basicHouseIntelligent) {
        if (basicHouseIntelligent == null) {
            return null;
        }
        BasicHouseIntelligentVo vo = new BasicHouseIntelligentVo();
        BeanUtils.copyProperties(basicHouseIntelligent, vo);
        vo.setSwitchCircuitName(baseDataDicService.getNameById(basicHouseIntelligent.getSwitchCircuit()));
        vo.setLayingMethodName(baseDataDicService.getNameById(basicHouseIntelligent.getLayingMethod()));
        vo.setGradeName(baseDataDicService.getNameById(basicHouseIntelligent.getGrade()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicHouseIntelligent.getCreator()));
        return vo;
    }

    /**
     * 根据查询条件判断是否有数据
     *
     * @param houseId
     * @return
     */
    public boolean hasHouseIntelligentData(Integer houseId) {
        return basicHouseIntelligentDao.countByHouseId(houseId) > 0;
    }

}
