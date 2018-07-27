package com.copower.pmcc.assess.service.project.examine;

import com.copower.pmcc.assess.common.enums.ExamineFileUpLoadFieldEnum;
import com.copower.pmcc.assess.constant.AssessExamineTaskConstant;
import com.copower.pmcc.assess.dal.basis.dao.examine.ExamineUnitHuxingDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.ExamineUnitHuxing;
import com.copower.pmcc.assess.dto.output.project.survey.ExamineUnitHuxingVo;
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
 * @Date: 2018/7/24 16:21
 * @Description:户型
 */
@Service
public class ExamineUnitHuxingService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExamineUnitHuxingDao examineUnitHuxingDao;
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
    public ExamineUnitHuxing getExamineUnitHuxingById(Integer id) {
        return examineUnitHuxingDao.getUnitHuxingById(id);
    }

    /**
     * 获取数据列表
     *
     * @param examineUnitHuxing
     * @return
     */
    public List<ExamineUnitHuxing> getExamineUnitHuxingList(ExamineUnitHuxing examineUnitHuxing) {
        return examineUnitHuxingDao.getUnitHuxingList(examineUnitHuxing);
    }

    public BootstrapTableVo getExamineUnitHuxingLists(ExamineUnitHuxing examineUnitHuxing) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<ExamineUnitHuxingVo> vos = Lists.newArrayList();
        getExamineUnitHuxingList(examineUnitHuxing).stream().forEach(oo -> vos.add(getExamineUnitHuxingVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<ExamineUnitHuxingVo>() : vos);
        return vo;
    }

    public ExamineUnitHuxingVo getExamineUnitHuxingVo(ExamineUnitHuxing examineUnitHuxing) {
        ExamineUnitHuxingVo vo = new ExamineUnitHuxingVo();
        BeanUtils.copyProperties(examineUnitHuxing, vo);
        if (examineUnitHuxing.getHouseLayout() != null) {
            vo.setHouseLayoutName(getValue(AssessExamineTaskConstant.EXAMINE_UNIT_HOUSE_LAYOUT, examineUnitHuxing.getHouseLayout()));
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(examineUnitHuxing.getId(), ExamineFileUpLoadFieldEnum.houseLatestFamilyPlanV.getName(),FormatUtils.entityNameConvertToTableName(ExamineUnitHuxing.class));
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
     * @param examineUnitHuxing
     * @return
     */
    public boolean addExamineUnitHuxing(ExamineUnitHuxing examineUnitHuxing) {
        examineUnitHuxing.setCreator(commonService.thisUserAccount());
        //以后需要删除掉
        if (ObjectUtils.isEmpty(examineUnitHuxing.getDeclareId())) {
            examineUnitHuxing.setDeclareId(0);
        }
        if (ObjectUtils.isEmpty(examineUnitHuxing.getExamineType())) {
            examineUnitHuxing.setExamineType(0);
        }
        try {
            int id = examineUnitHuxingDao.saveReturnID(examineUnitHuxing);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(ExamineUnitHuxing.class),id);
        } catch (Exception e1) {
            logger.error(String.format("异常! %s",e1.getMessage()),e1);
            return false;
        }
        return true;
    }

    /**
     * 编辑
     *
     * @param examineUnitHuxing
     * @return
     */
    public boolean updateExamineUnitHuxing(ExamineUnitHuxing examineUnitHuxing) {
        return examineUnitHuxingDao.updateUnitHuxing(examineUnitHuxing);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public boolean deleteExamineUnitHuxing(Integer id) {
        return examineUnitHuxingDao.deleteUnitHuxing(id);
    }

}
