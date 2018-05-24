package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.constant.BaseConstant;
import com.copower.pmcc.assess.dal.dao.DeclareInfoDao;
import com.copower.pmcc.assess.dal.dao.DeclareRecordDao;
import com.copower.pmcc.assess.dal.dao.FormConfigureDao;
import com.copower.pmcc.assess.dal.entity.DeclareInfo;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dto.input.DeclareInfoDto;
import com.copower.pmcc.assess.dto.input.FormConfigureDetailDto;
import com.copower.pmcc.assess.dto.input.FormConfigureDto;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.patterns.Declare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by kings on 2018-5-10.
 */
@Service
public class DeclareInfoService {
    @Autowired
    private DeclareInfoDao declareInfoDao;
    @Autowired
    private FormConfigureService formConfigureService;
    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private FormConfigureDao formConfigureDao;
    @Autowired
    private DeclareRecordDao declareRecordDao;

    /**
     * 获取申报信息by processInsId
     * @param processInsId
     * @return
     */
    public DeclareInfo getDeclareInfoByProcessInsId(String processInsId){
        if(StringUtils.isBlank(processInsId)) return null;
        return declareInfoDao.getDeclareInfoByProcessInsId(processInsId);
    }

    /**
     * 保存申报数据
     *
     * @param declareInfoDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveDeclareInfo(DeclareInfoDto declareInfoDto) throws BusinessException {
        FormConfigureDto formConfigureDto = declareInfoDto.getFormConfigureDto();
        Integer mainId = 0;
        if (declareInfoDto.getId() != null && declareInfoDto.getId() > 0) {
            //暂不处理
        } else {
            mainId = formConfigureService.saveData(formConfigureDto);
            DeclareInfo declareInfo = new DeclareInfo();
            declareInfo.setId(mainId);
            declareInfo.setProjectId(declareInfoDto.getProjectId());
            declareInfo.setProcessInsId(declareInfoDto.getProcessInsId());
            declareInfo.setPlanDetailId(declareInfoDto.getPlanDetailId());
            declareInfo.setDynamicTableName(declareInfoDto.getFormConfigureDto().getMultipleFormList().get(0).getTableName());
            declareInfo.setCreator(serviceComponent.getThisUser());
            declareInfoDao.editDeclareInfo(declareInfo);
        }
    }
}
