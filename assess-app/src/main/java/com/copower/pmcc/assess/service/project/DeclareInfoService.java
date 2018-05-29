package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.DeclareInfoDao;
import com.copower.pmcc.assess.dal.entity.DeclareInfo;
import com.copower.pmcc.assess.dto.input.DeclareInfoDto;
import com.copower.pmcc.assess.dto.input.FormConfigureDto;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private ProcessControllerComponent processControllerComponent;

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
            declareInfo.setCreator(processControllerComponent.getThisUser());
            declareInfoDao.editDeclareInfo(declareInfo);
        }
    }
}
