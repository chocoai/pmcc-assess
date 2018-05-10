package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.DeclareInfoDao;
import com.copower.pmcc.assess.dal.dao.FormConfigureDao;
import com.copower.pmcc.assess.dal.entity.DeclareInfo;
import com.copower.pmcc.assess.dto.input.DeclareInfoDto;
import com.copower.pmcc.assess.dto.input.FormConfigureDetailDto;
import com.copower.pmcc.assess.dto.input.FormConfigureDto;
import com.copower.pmcc.assess.service.ServiceComponent;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.aspectj.weaver.patterns.Declare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
    /**
     * 保存申报数据
     *
     * @param declareInfoDto
     */
    public void saveDeclareInfo(DeclareInfoDto declareInfoDto) throws BusinessException {
        FormConfigureDto formConfigureDto = declareInfoDto.getFormConfigureDto();

        if(declareInfoDto.getId()!=null&&declareInfoDto.getId()>0){
            //暂不处理
        }else{
            Integer integer = formConfigureService.saveData(formConfigureDto);
            DeclareInfo declareInfo = new DeclareInfo();
            declareInfo.setProjectId(declareInfoDto.getProjectId());
            declareInfo.setProcessInsId(declareInfoDto.getProcessInsId());
            declareInfo.setPlanDetailId(declareInfoDto.getPlanDetailId());
            declareInfo.setCreator(serviceComponent.getThisUser());
            declareInfoDao.editDeclareInfo(declareInfo);
        }

        //申报信息写入到 固定的申报记录表中 tb_declare_record
        //循环申报数据将申报的 位置信息写入 并且拼接出权证号的名称

        List<FormConfigureDetailDto> multipleFormList = formConfigureDto.getMultipleFormList();
        if(CollectionUtils.isNotEmpty(multipleFormList)){
            for (FormConfigureDetailDto formConfigureDetailDto : multipleFormList) {
                List<Map<String, Object>> maps = formConfigureDao.getObjectList(String.format(""));

            }
        }
    }
}
