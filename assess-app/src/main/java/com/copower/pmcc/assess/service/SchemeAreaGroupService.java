package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.dao.project.scheme.SchemeAreaGroupDao;
import com.copower.pmcc.assess.dal.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dto.input.project.scheme.SchemeAreaGroupDto;
import com.copower.pmcc.assess.dto.output.project.scheme.SchemeAreaGroupVo;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.erp.common.CommonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 区域分组
 * Created by 13426 on 2018/5/14.
 */
@Service
public class SchemeAreaGroupService {
    @Autowired
    private CommonService commonService;

    @Autowired
    private DeclareRecordService declareRecordService;

    @Autowired
    private SchemeAreaGroupDao dao;



    @Transactional
    public int add(SchemeAreaGroup schemeAreaGroup) {
        return dao.add(schemeAreaGroup);
    }

    /**
     * 根据分组 获取数据信息
     *
     * @param projectID
     * @return
     */
    public List<SchemeAreaGroupVo> schemeAreaGroupVoList(Integer projectID) {
        List<SchemeAreaGroupVo> schemeAreaGroupVos = new ArrayList<>();
        List<SchemeAreaGroupDto> dtos = dao.areaGroupDtoList(projectID);
        dtos.parallelStream().forEach(s -> schemeAreaGroupVos.add(change(s)));
        return schemeAreaGroupVos;
    }

    /**
     * @param dto
     * @return
     */
    @Transactional
    public boolean addEspecially(SchemeAreaGroupDto dto) {
        if (dto.getGmtCreated() == null) dto.setGmtCreated(new Date());
        if (!StringUtils.isEmpty(dto.getCreator()))dto.setCreator(commonService.thisUserAccount());
        return dao.addEspecially(dto);
    }

    @Transactional
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    @Transactional
    public boolean update(SchemeAreaGroupDto dto) {
        return dao.update(dto);
    }

    @Transactional
    public boolean update(SchemeAreaGroup oo){
        return dao.update(oo);
    }

    @Transactional(readOnly = true)
    public SchemeAreaGroup get(Integer id) {
        return dao.get(id);
    }



    public SchemeAreaGroupVo change(SchemeAreaGroupDto dto) {
        SchemeAreaGroupVo vo = new SchemeAreaGroupVo();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

}
