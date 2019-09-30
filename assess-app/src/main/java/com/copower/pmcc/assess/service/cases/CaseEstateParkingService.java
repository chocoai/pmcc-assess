package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.common.enums.basic.ExamineFileUpLoadFieldEnum;
import com.copower.pmcc.assess.dal.cases.dao.CaseEstateParkingDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateParking;
import com.copower.pmcc.assess.dto.output.cases.CaseEstateParkingVo;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/14 16:48
 * @Description:
 */
@Service
public class CaseEstateParkingService {

    @Autowired
    private CaseEstateParkingDao caseEstateParkingDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据信息
     *
     * @param id
     * @return
     */
    public CaseEstateParking getEstateParkingById(Integer id) {
        return caseEstateParkingDao.getEstateParkingById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineEstateParking
     * @return
     */
    public List<CaseEstateParking> getEstateParkingList(CaseEstateParking examineEstateParking) {
        return caseEstateParkingDao.getEstateParkingList(examineEstateParking);
    }

    public BootstrapTableVo getExamineEstateNetworkList(CaseEstateParking examineEstateNetwork) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseEstateParkingVo> vos = Lists.newArrayList();
        getEstateParkingList(examineEstateNetwork).stream().forEach(oo -> vos.add(getCaseEstateParkingVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<CaseEstateParkingVo>() : vos);
        return vo;
    }

    public CaseEstateParkingVo getCaseEstateParkingVo(CaseEstateParking examineEstateParking) {
        CaseEstateParkingVo vo = new CaseEstateParkingVo();
        BeanUtils.copyProperties(examineEstateParking, vo);
        vo.setLocationName(baseDataDicService.getNameById(examineEstateParking.getLocation()));
        vo.setParkingTypeName(baseDataDicService.getNameById(examineEstateParking.getParkingType()));
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(examineEstateParking.getId(), ExamineFileUpLoadFieldEnum.houseEstateParking.getName(), FormatUtils.entityNameConvertToTableName(CaseEstateParking.class));
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

    /**
     * 新增
     *
     * @param examineEstateParking
     * @return
     */
    public boolean addEstateParking(CaseEstateParking examineEstateParking) {
        try {
            int id = caseEstateParkingDao.addEstateParking(examineEstateParking);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(CaseEstateParking.class), id);
        } catch (Exception e1) {
            logger.error(String.format("异常! %s", e1.getMessage()), e1);
            return false;
        }
        return true;
    }

    /**
     * 编辑
     *
     * @param examineEstateParking
     * @return
     */
    public boolean updateEstateParking(CaseEstateParking examineEstateParking) {
        return caseEstateParkingDao.updateEstateParking(examineEstateParking);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteEstateParking(Integer id) {
        return caseEstateParkingDao.deleteEstateParking(id);
    }

    /**
     * 根据查询条件判断是否有数据
     * @param esteteId
     * @return
     */
    public boolean hasEstateParkingData(Integer esteteId){
        return caseEstateParkingDao.countByEstateId(esteteId)>0;
    }
}
