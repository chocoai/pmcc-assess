package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseHouseCorollaryEquipmentDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseHouseCorollaryEquipment;
import com.copower.pmcc.assess.dto.output.cases.CaseHouseCorollaryEquipmentVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 14:55
 * @Description:
 */
@Service
public class CaseHouseCorollaryEquipmentService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseHouseCorollaryEquipmentDao caseHouseCorollaryEquipmentDao;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public CaseHouseCorollaryEquipment getCaseHouseCorollaryEquipmentById(Integer id) {
        return caseHouseCorollaryEquipmentDao.getEstateLandStateById(id);
    }

    /**
     * 获取数据列表
     * corollaryequipment
     *
     * @param caseHouseCorollaryEquipment
     * @return
     */
    public List<CaseHouseCorollaryEquipment> getCaseHouseCorollaryEquipmentList(CaseHouseCorollaryEquipment caseHouseCorollaryEquipment) {
        return caseHouseCorollaryEquipmentDao.getEstateLandStateList(caseHouseCorollaryEquipment);
    }

    public BootstrapTableVo getCaseHouseCorollaryEquipmentLists(CaseHouseCorollaryEquipment caseHouseCorollaryEquipment) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseHouseCorollaryEquipmentVo> vos = Lists.newArrayList();
        getCaseHouseCorollaryEquipmentList(caseHouseCorollaryEquipment).stream().forEach(oo -> vos.add(getCaseHouseCorollaryEquipmentVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseHouseCorollaryEquipmentVo>() : vos);
        return vo;
    }


    public CaseHouseCorollaryEquipmentVo getCaseHouseCorollaryEquipmentVo(CaseHouseCorollaryEquipment caseHouseCorollaryEquipment) {
        CaseHouseCorollaryEquipmentVo vo = new CaseHouseCorollaryEquipmentVo();
        BeanUtils.copyProperties(caseHouseCorollaryEquipment, vo);
        vo.setCategoryName(baseDataDicService.getNameById(caseHouseCorollaryEquipment.getCategory()));
        vo.setPriceName(baseDataDicService.getNameById(caseHouseCorollaryEquipment.getPrice()));
        vo.setTypeName(baseDataDicService.getNameById(caseHouseCorollaryEquipment.getType()));
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(caseHouseCorollaryEquipment.getId(), null, FormatUtils.entityNameConvertToTableName(CaseHouseCorollaryEquipment.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            if (sysAttachmentDtos.size() >= 1) {
                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                    if (sysAttachmentDto != null) {
                        builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto));
                        builder.append(" ");
                    }
                }
            }
            vo.setFileName(builder.toString());
        }
        return vo;
    }

    private String getValue(String key, Integer v) {
        StringBuilder builder = new StringBuilder(1024);
        if (!StringUtils.isEmpty(key)) {
            List<BaseDataDic> baseDataDic = baseDataDicService.getCacheDataDicList(key);
            if (baseDataDic.size() >= 1) {
                if (v != null) {
                    for (BaseDataDic base : baseDataDic) {
                        if (base.getId().intValue() == v.intValue()) {
                            builder.append(base.getName());
                        }
                    }
                }
            }
        }
        return builder.toString();
    }

    /**
     * 新增
     *
     * @param caseHouseCorollaryEquipment
     * @return
     */
    public boolean addCaseHouseCorollaryEquipment(CaseHouseCorollaryEquipment caseHouseCorollaryEquipment) {
        int id = 0;
        try {
            id = caseHouseCorollaryEquipmentDao.addEstateLandState(caseHouseCorollaryEquipment);
            return true;
        } catch (Exception e1) {
            logger.error("error:%s", e1.getMessage());
            return false;
        }
    }

    /**
     * 编辑
     *
     * @param caseHouseCorollaryEquipment
     * @return
     */
    public boolean updateCaseHouseCorollaryEquipment(CaseHouseCorollaryEquipment caseHouseCorollaryEquipment) {
        return caseHouseCorollaryEquipmentDao.updateEstateLandState(caseHouseCorollaryEquipment);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseHouseCorollaryEquipment(Integer id) {
        return caseHouseCorollaryEquipmentDao.deleteEstateLandState(id);
    }

    /**
     * 根据查询条件判断是否有数据
     * @param houseId
     * @return
     */
    public boolean hasHouseCorollaryEquipmentData(Integer houseId){
        return caseHouseCorollaryEquipmentDao.countByHouseId(houseId)>0;
    }
}
