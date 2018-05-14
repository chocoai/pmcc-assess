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
            declareInfo.setCreator(serviceComponent.getThisUser());
            declareInfoDao.editDeclareInfo(declareInfo);
        }

        //申报信息写入到 固定的申报记录表中 tb_declare_record
        //循环申报数据将申报的 位置信息写入 并且拼接出权证号的名称

        //先清空原有数据
        List<DeclareRecord> declareRecordList = declareRecordDao.getDeclareRecordByProjectId(declareInfoDto.getProjectId());
        if (CollectionUtils.isNotEmpty(declareRecordList)) {
            declareRecordList.forEach(p -> declareRecordDao.deleteDeclareRecord(p.getId()));
        }

        List<FormConfigureDetailDto> multipleFormList = formConfigureDto.getMultipleFormList();
        if (CollectionUtils.isNotEmpty(multipleFormList)) {
            String sql = "select * from %s where main_id=%s";
            for (FormConfigureDetailDto formConfigureDetailDto : multipleFormList) {
                List<Map<String, Object>> mapList = formConfigureDao.getObjectList(String.format(sql, formConfigureDetailDto.getTableName(), mainId));
                if (CollectionUtils.isNotEmpty(mapList)) {
                    for (Map<String, Object> map : mapList) {
                        //记录申报数据
                        DeclareRecord declareRecord = new DeclareRecord();
                        declareRecord.setProjectId(declareInfoDto.getProjectId());
                        //申报数据特定字段记录
                        if (map.containsKey(BaseConstant.PMCC_ASSESS_DECLARE_RECORD_NAME)) {
                            declareRecord.setName(String.valueOf(map.get(BaseConstant.PMCC_ASSESS_DECLARE_RECORD_NAME)));
                        }
                        if (map.containsKey(BaseConstant.PMCC_ASSESS_DECLARE_RECORD_PROVINCE)) {
                            declareRecord.setProvince(String.valueOf(map.get(BaseConstant.PMCC_ASSESS_DECLARE_RECORD_PROVINCE)));
                        }
                        if (map.containsKey(BaseConstant.PMCC_ASSESS_DECLARE_RECORD_CITY)) {
                            declareRecord.setCity(String.valueOf(map.get(BaseConstant.PMCC_ASSESS_DECLARE_RECORD_CITY)));
                        }
                        if (map.containsKey(BaseConstant.PMCC_ASSESS_DECLARE_RECORD_DISTRICT)) {
                            declareRecord.setDistrict(String.valueOf(map.get(BaseConstant.PMCC_ASSESS_DECLARE_RECORD_DISTRICT)));
                        }
                        if (map.containsKey(BaseConstant.PMCC_ASSESS_DECLARE_RECORD_FLOOR_AREA)) {
                            declareRecord.setFloorArea(new BigDecimal(String.valueOf(map.get(BaseConstant.PMCC_ASSESS_DECLARE_RECORD_FLOOR_AREA))));
                        }
                        declareRecord.setCreator(serviceComponent.getThisUser());
                        declareRecordDao.addDeclareRecord(declareRecord);
                    }
                }
            }
        }
    }
}
