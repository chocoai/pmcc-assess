package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataAssetsAppraisalDicDao;
import com.copower.pmcc.assess.dal.basis.entity.DataAssetsAppraisalDic;
import com.copower.pmcc.assess.dto.output.data.DataAssetsAppraisalDicVo;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
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
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zch on 2019-9-18.
 * 资产评估 文件和字段配置
 */
@Service
public class DataAssetsAppraisalDicService {

    @Autowired
    private DataAssetsAppraisalDicDao dataAssetsAppraisalDicDao;
    @Autowired
    private BaseService baseService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;

    public boolean saveDataAssetsAppraisalDic(DataAssetsAppraisalDic dataAssetsAppraisalDic) throws Exception {
        if (dataAssetsAppraisalDic == null) {
            return false;
        }
        if (dataAssetsAppraisalDic.getId() != null && dataAssetsAppraisalDic.getId() != 0) {
            return dataAssetsAppraisalDicDao.updateDataAssetsAppraisalDic(dataAssetsAppraisalDic);
        } else {
            DataAssetsAppraisalDic select = new DataAssetsAppraisalDic();
            if (StringUtils.isNotBlank(dataAssetsAppraisalDic.getFieldName())) {
                select.setFieldName(dataAssetsAppraisalDic.getFieldName());
                List<DataAssetsAppraisalDic> dataAssetsAppraisalDics = getDataAssetsAppraisalDicListByExample(select);
                if (CollectionUtils.isNotEmpty(dataAssetsAppraisalDics)) {
                    throw new Exception("已经存在重复的字段,请检查字段");
                }
            }
            dataAssetsAppraisalDic.setCreator(commonService.thisUserAccount());
            dataAssetsAppraisalDicDao.addDataAssetsAppraisalDic(dataAssetsAppraisalDic);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(DataAssetsAppraisalDic.class), dataAssetsAppraisalDic.getId());
            return true;
        }
    }

    public BootstrapTableVo getBootstrapTableVo(DataAssetsAppraisalDic oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataAssetsAppraisalDic> childrenList = getDataAssetsAppraisalDicListByExample(oo);
        List<DataAssetsAppraisalDicVo> voList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(childrenList)){
            childrenList.forEach(target -> voList.add(getDataAssetsAppraisalDicVo(target)));
        }
        vo.setTotal(page.getTotal());
        vo.setRows(voList);
        return vo;
    }

    public DataAssetsAppraisalDic getDataAssetsAppraisalDicById(Integer id) {
        return dataAssetsAppraisalDicDao.getDataAssetsAppraisalDicById(id);
    }

    public boolean deleteDataAssetsAppraisalDicById(Integer id) {
        return dataAssetsAppraisalDicDao.deleteDataAssetsAppraisalDicById(id);
    }

    public void deleteDataAssetsAppraisalDic(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        dataAssetsAppraisalDicDao.deleteDataAssetsAppraisalDic(FormatUtils.transformString2Integer(id));
    }

    public List<DataAssetsAppraisalDic> getDataAssetsAppraisalDicListByExample(DataAssetsAppraisalDic oo) {
        return dataAssetsAppraisalDicDao.getDataAssetsAppraisalDicListByExample(oo);
    }

    public List<DataAssetsAppraisalDic> getDataAssetsAppraisalDicListByTypeAndPlanDetailsId(Integer planDetailsId, String type) {
        return dataAssetsAppraisalDicDao.getDataAssetsAppraisalDicListByTypeAndPlanDetailsId(planDetailsId, type);
    }

    public List<DataAssetsAppraisalDic> getDataAssetsAppraisalDicListByType(String type) {
        return dataAssetsAppraisalDicDao.getDataAssetsAppraisalDicListByType(type);
    }

    public DataAssetsAppraisalDicVo getDataAssetsAppraisalDicVo(DataAssetsAppraisalDic target){
        if (target == null){
            return null;
        }
        DataAssetsAppraisalDicVo vo = new DataAssetsAppraisalDicVo();
        BeanUtils.copyProperties(target,vo);
        List<SysAttachmentDto> sysAttachmentDtoList = baseAttachmentService.getByField_tableId(target.getId(), null, FormatUtils.entityNameConvertToTableName(DataAssetsAppraisalDic.class));
        if (CollectionUtils.isNotEmpty(sysAttachmentDtoList)){
            StringBuilder builder = new StringBuilder();
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtoList) {
                builder.append(baseAttachmentService.getEditHtml(sysAttachmentDto,true)).append(" ");
            }
            vo.setFileViewName(builder.toString());
        }
        return vo;
    }

}
