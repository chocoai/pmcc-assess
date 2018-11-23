package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.cases.dao.CaseUnitHuxingDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseUnitHuxing;
import com.copower.pmcc.assess.dto.output.cases.CaseUnitHuxingVo;
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

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/18 16:32
 * @Description:
 */
@Service
public class CaseUnitHuxingService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CaseUnitHuxingDao caseUnitHuxingDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public CaseUnitHuxing getCaseUnitHuxingById(Integer id) {
        return caseUnitHuxingDao.getUnitHuxingById(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseUnitHuxing
     * @return
     */
    public List<CaseUnitHuxingVo> getCaseUnitHuxingList(CaseUnitHuxing caseUnitHuxing) {
        List<CaseUnitHuxing> caseUnitHuxingList = caseUnitHuxingDao.getUnitHuxingList(caseUnitHuxing);
        List<CaseUnitHuxingVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(caseUnitHuxingList)) {
            caseUnitHuxingList.forEach(oo -> vos.add(getCaseUnitHuxingVo(oo)));
        }
        return vos;
    }

    public BootstrapTableVo getCaseUnitHuxingLists(CaseUnitHuxing caseUnitHuxing) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        vo.setTotal(page.getTotal());
        vo.setRows(getCaseUnitHuxingList(caseUnitHuxing));
        return vo;
    }

    public CaseUnitHuxingVo getCaseUnitHuxingVo(CaseUnitHuxing caseUnitHuxing) {
        if (caseUnitHuxing==null){
            return null;
        }
        CaseUnitHuxingVo vo = new CaseUnitHuxingVo();
        BeanUtils.copyProperties(caseUnitHuxing, vo);
        vo.setHouseLayoutName(baseDataDicService.getNameById(caseUnitHuxing.getHouseLayout()));
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(caseUnitHuxing.getId(), null, FormatUtils.entityNameConvertToTableName(CaseUnitHuxing.class));
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
            vo.setFileViewName(builder.toString());
        }
        return vo;
    }

    private String getValue(String key, Integer v) {
        StringBuilder builder = new StringBuilder(1024);
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
        return builder.toString();
    }

    /**
     * 新增
     *
     * @param caseUnitHuxing
     * @return
     */
    public boolean addCaseUnitHuxing(CaseUnitHuxing caseUnitHuxing) {
        try {
            int id = caseUnitHuxingDao.addUnitHuxing(caseUnitHuxing);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseUnitHuxing.class), id);
        } catch (Exception e1) {
            logger.error(String.format("异常! %s", e1.getMessage()), e1);
            return false;
        }
        return true;
    }

    /**
     * 编辑
     *
     * @param caseUnitHuxing
     * @return
     */
    public boolean updateCaseUnitHuxing(CaseUnitHuxing caseUnitHuxing) {
        return caseUnitHuxingDao.updateUnitHuxing(caseUnitHuxing);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteCaseUnitHuxing(Integer id) {
        return caseUnitHuxingDao.deleteUnitHuxing(id);
    }


    /**
     * 根据查询条件判断是否有数据
     * @param unitId
     * @return
     */
    public boolean hasUnitHuxingData(Integer unitId){
        return caseUnitHuxingDao.countByUnitId(unitId)>0;
    }
}
