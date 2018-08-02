package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineFileUpLoadTwoFieldEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineHouseCorollaryEquipmentDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineHouseCorollaryEquipment;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineHouseCorollaryEquipmentVo;
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
 * @Date: 2018/8/2 14:41
 * @Description:配套设备设施
 */
@Service
public class ExamineHouseCorollaryEquipmentService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineHouseCorollaryEquipmentDao examineHouseCorollaryEquipmentDao;
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
    public ExamineHouseCorollaryEquipment getExamineHouseCorollaryEquipmentById(Integer id) {
        return examineHouseCorollaryEquipmentDao.getHouseById(id);
    }

    /**
     * 获取数据列表
     *corollaryequipment
     * @param examineHouseCorollaryEquipment
     * @return
     */
    public List<ExamineHouseCorollaryEquipment> getExamineHouseCorollaryEquipmentList(ExamineHouseCorollaryEquipment examineHouseCorollaryEquipment) {
        return examineHouseCorollaryEquipmentDao.getHouseList(examineHouseCorollaryEquipment);
    }

    public BootstrapTableVo getExamineHouseCorollaryEquipmentLists(ExamineHouseCorollaryEquipment examineHouseCorollaryEquipment) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineHouseCorollaryEquipmentVo> vos = Lists.newArrayList();
        getExamineHouseCorollaryEquipmentList(examineHouseCorollaryEquipment).stream().forEach(oo -> vos.add(getExamineHouseCorollaryEquipmentVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineHouseCorollaryEquipmentVo>() : vos);
        return vo;
    }

    public ExamineHouseCorollaryEquipmentVo getExamineHouseCorollaryEquipmentVo(ExamineHouseCorollaryEquipment examineHouseCorollaryEquipment) {
        ExamineHouseCorollaryEquipmentVo vo = new ExamineHouseCorollaryEquipmentVo();
        BeanUtils.copyProperties(examineHouseCorollaryEquipment, vo);
        if (examineHouseCorollaryEquipment.getCategory() != null) {
            vo.setCategoryName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_COROLLARY_EQUIPMENT_CATEGORY, examineHouseCorollaryEquipment.getCategory()));
        }
        if (examineHouseCorollaryEquipment.getType() != null) {
            vo.setTypeName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_COROLLARY_EQUIPMENT_TYPE, examineHouseCorollaryEquipment.getType()));
        }
        if (examineHouseCorollaryEquipment.getPrice() != null) {
            vo.setPriceName(getValue(AssessExamineTaskConstant.EXAMINE_HOUSE_COROLLARY_EQUIPMENT_PRICE, examineHouseCorollaryEquipment.getPrice()));
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(examineHouseCorollaryEquipment.getId(), ExamineFileUpLoadTwoFieldEnum.positionDiagramFileID.getName(),FormatUtils.entityNameConvertToTableName(ExamineHouseCorollaryEquipment.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)){
            if (sysAttachmentDtos.size() >= 1){
                for (SysAttachmentDto sysAttachmentDto:sysAttachmentDtos){
                    if (sysAttachmentDto != null){
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
        if (!StringUtils.isEmpty(key)){
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
     * @param examineHouseCorollaryEquipment
     * @return
     */
    public boolean addExamineHouseCorollaryEquipment(ExamineHouseCorollaryEquipment examineHouseCorollaryEquipment) {
        examineHouseCorollaryEquipment.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineHouseCorollaryEquipment.getDeclareId())) {
            examineHouseCorollaryEquipment.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineHouseCorollaryEquipment.getExamineType())) {
            examineHouseCorollaryEquipment.setExamineType(0);
        }
        int id = 0;
        try {
            id = examineHouseCorollaryEquipmentDao.addHouse(examineHouseCorollaryEquipment);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(ExamineHouseCorollaryEquipment.class),id);
            return  true;
        } catch (Exception e1) {
            logger.error("error:%s",e1.getMessage());
            return false;
        }
    }

    /**
     * 编辑
     *
     * @param examineHouseCorollaryEquipment
     * @return
     */
    public boolean updateExamineHouseCorollaryEquipment(ExamineHouseCorollaryEquipment examineHouseCorollaryEquipment) {
        return examineHouseCorollaryEquipmentDao.updateHouse(examineHouseCorollaryEquipment);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineHouseCorollaryEquipment(Integer id) {
        return examineHouseCorollaryEquipmentDao.deleteHouse(id);
    }
}
