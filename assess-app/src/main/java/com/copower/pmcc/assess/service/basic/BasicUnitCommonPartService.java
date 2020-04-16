package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitCommonPartDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitCommonPart;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitCommonPartVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
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
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:21
 * @Description:室内公共装修
 */
@Service
public class BasicUnitCommonPartService {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicUnitCommonPartDao basicUnitCommonPartDao;
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
    public BasicUnitCommonPart getBasicUnitCommonPartById(Integer id) throws Exception {
        return basicUnitCommonPartDao.getBasicUnitCommonPartById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicUnitCommonPart
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicUnitCommonPart(BasicUnitCommonPart basicUnitCommonPart, boolean updateNull) throws Exception {
        if (basicUnitCommonPart.getId() == null || basicUnitCommonPart.getId().intValue() == 0) {
            basicUnitCommonPart.setCreator(commonService.thisUserAccount());
            Integer id = basicUnitCommonPartDao.addBasicUnitCommonPart(basicUnitCommonPart);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicUnitCommonPart.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicUnitCommonPart unitCommonPart = basicUnitCommonPartDao.getBasicUnitCommonPartById(basicUnitCommonPart.getId());
                if (unitCommonPart != null) {
                    basicUnitCommonPart.setBisDelete(unitCommonPart.getBisDelete());
                    basicUnitCommonPart.setCreator(unitCommonPart.getCreator());
                    basicUnitCommonPart.setGmtCreated(unitCommonPart.getGmtCreated());
                    basicUnitCommonPart.setGmtModified(DateUtils.now());
                }
            }
            basicUnitCommonPartDao.updateBasicUnitCommonPart(basicUnitCommonPart, updateNull);
            return basicUnitCommonPart.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicUnitCommonPart(Integer id) throws Exception {
        return basicUnitCommonPartDao.deleteBasicUnitCommonPart(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicUnitCommonPart
     * @return
     * @throws Exception
     */
    public List<BasicUnitCommonPart> basicUnitCommonPartList(BasicUnitCommonPart basicUnitCommonPart) throws Exception {
        return basicUnitCommonPartDao.basicUnitCommonPartList(basicUnitCommonPart);
    }

    public List<BasicUnitCommonPartVo> getBasicUnitCommonPartList(Integer unitId) {
        if (unitId == null) return null;
        BasicUnitCommonPart where = new BasicUnitCommonPart();
        where.setUnitId(unitId);
        return LangUtils.transform(basicUnitCommonPartDao.basicUnitCommonPartList(where), o -> getBasicUnitCommonPartVo(o));
    }

    public BootstrapTableVo getBootstrapTableVo(BasicUnitCommonPart basicUnitCommonPart) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicUnitCommonPart> basicUnitCommonPartList = basicUnitCommonPartDao.basicUnitCommonPartList(basicUnitCommonPart);
        List<BasicUnitCommonPartVo> vos = Lists.newArrayList();
        basicUnitCommonPartList.forEach(oo -> vos.add(getBasicUnitCommonPartVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicUnitCommonPartVo>(10) : vos);
        return vo;
    }

    public BasicUnitCommonPartVo getBasicUnitCommonPartVo(BasicUnitCommonPart basicUnitCommonPart) {
        if (basicUnitCommonPart == null) {
            return null;
        }
        BasicUnitCommonPartVo vo = new BasicUnitCommonPartVo();
        BeanUtils.copyProperties(basicUnitCommonPart, vo);
        vo.setUnitMonadName(baseDataDicService.getNameById(basicUnitCommonPart.getUnitMonad()));
        vo.setUnitQuantityName(baseDataDicService.getNameById(basicUnitCommonPart.getUnitQuantity()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicUnitCommonPart.getCreator()));
        List<String> stringList = new ArrayList<>();
        if (org.apache.commons.lang3.StringUtils.isNotBlank(basicUnitCommonPart.getUnitLocation())) {
            JSONArray jsonArray = null;
            try {
                jsonArray = JSONArray.parseArray(basicUnitCommonPart.getUnitLocation());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
            if (jsonArray != null) {
                Iterator<Object> iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    Object next = iterator.next();
                    JSONObject jsonObject = (JSONObject) next;
                    if (jsonObject == null) {
                        continue;
                    }
                    String name = jsonObject.getString("name");
                    String description = jsonObject.getString("description");
                    String unitLocation = jsonObject.getString("unitLocation");
                    if (org.apache.commons.lang3.StringUtils.isBlank(name)) {
                        continue;
                    }
                    if (org.apache.commons.lang3.StringUtils.isBlank(unitLocation)) {
                        continue;
                    }
                    stringList.add(String.join("/", name, unitLocation, org.apache.commons.lang3.StringUtils.isNotBlank(description) ? description : "无描述"));
                }
            }
        }
        if (CollectionUtils.isNotEmpty(stringList)) {
            vo.setUnitLocation(org.apache.commons.lang3.StringUtils.join(stringList, " , "));
        }
        return vo;
    }
}
