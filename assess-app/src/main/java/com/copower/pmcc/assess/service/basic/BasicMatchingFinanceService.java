package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicMatchingFinanceDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicMatchingFinance;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.output.basic.BasicMatchingFinanceVo;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 11:05
 * @Description:金融服务
 */
@Service
public class BasicMatchingFinanceService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicMatchingFinanceDao basicMatchingFinanceDao;
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
    public BasicMatchingFinance getBasicMatchingFinanceById(Integer id) throws Exception {
        return basicMatchingFinanceDao.getBasicMatchingFinanceById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicMatchingFinance
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicMatchingFinance(BasicMatchingFinance basicMatchingFinance, boolean updateNull) throws Exception {
        if (basicMatchingFinance.getId() == null || basicMatchingFinance.getId().intValue() == 0) {
            basicMatchingFinance.setCreator(commonService.thisUserAccount());
            Integer id = basicMatchingFinanceDao.addBasicMatchingFinance(basicMatchingFinance);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicMatchingFinance.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicMatchingFinance matchingFinance = basicMatchingFinanceDao.getBasicMatchingFinanceById(basicMatchingFinance.getId());
                if (matchingFinance != null) {
                    basicMatchingFinance.setBisDelete(matchingFinance.getBisDelete());
                    basicMatchingFinance.setCreator(matchingFinance.getCreator());
                    basicMatchingFinance.setGmtCreated(matchingFinance.getGmtCreated());
                    basicMatchingFinance.setGmtModified(DateUtils.now());
                }
            }
            basicMatchingFinanceDao.updateBasicMatchingFinance(basicMatchingFinance, updateNull);
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
    public boolean deleteBasicMatchingFinance(Integer id) throws Exception {
        return basicMatchingFinanceDao.deleteBasicMatchingFinance(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicMatchingFinance
     * @return
     * @throws Exception
     */
    public List<BasicMatchingFinance> basicMatchingFinanceList(BasicMatchingFinance basicMatchingFinance) throws Exception {
        return basicMatchingFinanceDao.basicMatchingFinanceList(basicMatchingFinance);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicMatchingFinance basicMatchingFinance) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicMatchingFinance> basicMatchingFinanceList = basicMatchingFinanceDao.basicMatchingFinanceList(basicMatchingFinance);
        List<BasicMatchingFinanceVo> vos = Lists.newArrayList();
        basicMatchingFinanceList.forEach(oo -> vos.add(getBasicMatchingFinanceVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicMatchingFinanceVo>(10) : vos);
        return vo;
    }

    public List<BasicMatchingFinanceVo> getBasicMatchingFinanceList(Integer estateId) {
        BasicMatchingFinance where = new BasicMatchingFinance();
        where.setEstateId(estateId);
        List<BasicMatchingFinance> basicMatchingFinanceList = basicMatchingFinanceDao.basicMatchingFinanceList(where);
        if (CollectionUtils.isEmpty(basicMatchingFinanceList)) return null;
        return LangUtils.transform(basicMatchingFinanceList, o -> getBasicMatchingFinanceVo(o));
    }

    public BasicMatchingFinanceVo getBasicMatchingFinanceVo(BasicMatchingFinance basicMatchingFinance) {
        if (basicMatchingFinance == null) {
            return null;
        }
        BasicMatchingFinanceVo vo = new BasicMatchingFinanceVo();
        BeanUtils.copyProperties(basicMatchingFinance, vo);
        BaseDataDic dataDic = null;
        vo.setNatureName(baseDataDicService.getNameById(basicMatchingFinance.getCategory()));
        vo.setServiceContentName(baseDataDicService.getNameById(NumberUtils.isNumber(basicMatchingFinance.getServiceContent()) ? Integer.parseInt(basicMatchingFinance.getServiceContent()) : null));
        vo.setCategoryName(baseDataDicService.getNameById(basicMatchingFinance.getNature()));
        vo.setDistanceName(baseDataDicService.getNameById(basicMatchingFinance.getDistance()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicMatchingFinance.getCreator()));
        return vo;
    }

    public void removeIds(String id) {
        if (StringUtils.isNotBlank(id)) {
            List<Integer> ids = new ArrayList<>(10);
            for (String str : id.split(",")) {
                if (NumberUtils.isNumber(str)) {
                    ids.add(Integer.parseInt(str));
                }
            }
            basicMatchingFinanceDao.removeIds(ids);
        }
    }

}
