package com.copower.pmcc.assess.service.basic;

import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basic.dao.BasicUnitHuxingDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicUnit;
import com.copower.pmcc.assess.dal.basic.entity.BasicUnitHuxing;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitHuxing;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitHuxingVo;
import com.copower.pmcc.assess.dto.output.cases.CaseUnitHuxingVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.cases.CaseUnitHuxingService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/5 16:13
 * @Description:户型
 */
@Service
public class BasicUnitHuxingService {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicUnitHuxingDao basicUnitHuxingDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private CaseUnitHuxingService caseUnitHuxingService;
    @Autowired
    private BasicUnitService basicUnitService;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicUnitHuxing getBasicUnitHuxingById(Integer id) throws Exception {
        return basicUnitHuxingDao.getBasicUnitHuxingById(id);
    }

    public String getHouseCategoryNameById(Integer id) {
        try {
            BasicUnitHuxing huxing = basicUnitHuxingDao.getBasicUnitHuxingById(id);
            if (huxing == null || StringUtils.isBlank(huxing.getHouseCategory())) return null;
            StringBuilder stringBuilder = new StringBuilder();
            JSONObject jsonObject = JSONObject.parseObject(huxing.getHouseCategory());
            if (StringUtils.isNotBlank(jsonObject.getString("house")))
                stringBuilder.append(String.format("%s室", jsonObject.getString("house")));
            if (StringUtils.isNotBlank(jsonObject.getString("saloon")))
                stringBuilder.append(String.format("%s厅", jsonObject.getString("saloon")));
            if (StringUtils.isNotBlank(jsonObject.getString("kitchen")))
                stringBuilder.append(String.format("%s厨", jsonObject.getString("kitchen")));
            if (StringUtils.isNotBlank(jsonObject.getString("toilet")))
                stringBuilder.append(String.format("%s卫", jsonObject.getString("toilet")));
            if (StringUtils.isNotBlank(jsonObject.getString("garden")))
                stringBuilder.append(String.format("%s花园", jsonObject.getString("garden")));
            if (StringUtils.isNotBlank(jsonObject.getString("balcony")))
                stringBuilder.append(String.format("%s阳台", jsonObject.getString("balcony")));
            return stringBuilder.toString();
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * 新增或者修改
     *
     * @param basicUnitHuxing
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicUnitHuxing(BasicUnitHuxing basicUnitHuxing) throws Exception {
        if (basicUnitHuxing.getId() == null || basicUnitHuxing.getId().intValue() == 0) {
            basicUnitHuxing.setCreator(commonService.thisUserAccount());
            Integer id = basicUnitHuxingDao.saveBasicUnitHuxing(basicUnitHuxing);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class), id);
            return id;
        } else {
            BasicUnitHuxing oo = basicUnitHuxingDao.getBasicUnitHuxingById(basicUnitHuxing.getId());
            basicUnitHuxingDao.updateBasicUnitHuxing(basicUnitHuxing);
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
    public boolean deleteBasicUnitHuxing(Integer id) throws Exception {
        return basicUnitHuxingDao.deleteBasicUnitHuxing(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicUnitHuxing
     * @return
     * @throws Exception
     */
    public List<BasicUnitHuxing> basicUnitHuxingList(BasicUnitHuxing basicUnitHuxing) throws Exception {
        return basicUnitHuxingDao.basicUnitHuxingList(basicUnitHuxing);
    }

    public void removeBasicUnitHuxing(BasicUnitHuxing basicUnitHuxing) throws Exception {
        basicUnitHuxingDao.removeBasicUnitHuxing(basicUnitHuxing);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicUnitHuxing basicUnitHuxing) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingDao.basicUnitHuxingList(basicUnitHuxing);
        List<BasicUnitHuxingVo> vos = Lists.newArrayList();
        basicUnitHuxingList.forEach(oo -> vos.add(getBasicUnitHuxingVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicUnitHuxingVo>(10) : vos);
        return vo;
    }

    public BasicUnitHuxingVo getBasicUnitHuxingVo(BasicUnitHuxing basicUnitHuxing) {
        if (basicUnitHuxing == null) {
            return null;
        }
        BasicUnitHuxingVo vo = new BasicUnitHuxingVo();
        BeanUtils.copyProperties(basicUnitHuxing, vo);
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(basicUnitHuxing.getId(), null, FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                if (sysAttachmentDto != null) {
                    builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto));
                    builder.append(" ");
                }
            }
            vo.setFileViewName(builder.toString());
        }
        vo.setOrientationName(baseDataDicService.getNameById(NumberUtils.isNumber(basicUnitHuxing.getOrientation()) ? Integer.parseInt(basicUnitHuxing.getOrientation()) : null));
        return vo;
    }

    /**
     * 获取选择户型数据列表
     *
     * @param basicApplyId
     * @return
     */
    public BootstrapTableVo getSelectHuxingList(Integer basicApplyId, Integer caseUnitId) throws Exception {
        BasicUnit basicUnit = basicUnitService.getBasicUnitByApplyId(basicApplyId);
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicUnitHuxingVo> list = Lists.newArrayList();
        if (basicUnit != null) {
            BasicUnitHuxing basicUnitHuxing = new BasicUnitHuxing();
            basicUnitHuxing.setUnitId(basicUnit.getId());
            List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingDao.basicUnitHuxingList(basicUnitHuxing);
            if(!CollectionUtils.isEmpty(basicUnitHuxingList)){
                for (BasicUnitHuxing unitHuxing : basicUnitHuxingList) {
                    BasicUnitHuxingVo unitHuxingVo = getBasicUnitHuxingVo(unitHuxing);
                    unitHuxingVo.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
                    list.add(unitHuxingVo);
                }
            }

        } else if (caseUnitId != null) {
            CaseUnitHuxing caseUnitHuxing = new CaseUnitHuxing();
            caseUnitHuxing.setUnitId(caseUnitId);
            List<CaseUnitHuxingVo> huxingList = caseUnitHuxingService.getCaseUnitHuxingList(caseUnitHuxing);
            if(!CollectionUtils.isEmpty(huxingList)){
                for (CaseUnitHuxingVo huxing : huxingList) {
                    BasicUnitHuxingVo basicUnitHuxingVo=new BasicUnitHuxingVo();
                    BeanUtils.copyProperties(huxing,basicUnitHuxingVo);
                    basicUnitHuxingVo.setTableName(FormatUtils.entityNameConvertToTableName(CaseUnitHuxing.class));
                    list.add(basicUnitHuxingVo);
                }
            }
        }
        vo.setRows(ObjectUtils.isEmpty(list) ? Lists.newArrayList() : list);
        vo.setTotal(page.getTotal());
        return vo;
    }
}
