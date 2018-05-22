package com.copower.pmcc.assess.service;

import com.copower.pmcc.assess.dal.dao.SchemeAreaGroupDao;
import com.copower.pmcc.assess.dal.entity.DeclareRecord;
import com.copower.pmcc.assess.dal.entity.SchemeAreaGroup;
import com.copower.pmcc.assess.dto.input.project.SchemeAreaGroupDto;
import com.copower.pmcc.assess.dto.output.project.SchemeAreaGroupVo;
import com.copower.pmcc.assess.service.project.DeclareRecordService;
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


    /**
     * 由于add () 失效不能返回id 因此....
     * @param dto
     * @return
     */
    public int getID(SchemeAreaGroupDto dto){
        return dao.getID(dto);
    }

    @Transactional
    public int add(SchemeAreaGroupDto dto) {
        return dao.add(dto);
    }

    /**
     * 根据分组 获取数据信息
     *
     * @param groupId
     * @return
     */
    public List<SchemeAreaGroupVo> schemeAreaGroupVoList(String groupId) {
        List<SchemeAreaGroupVo> schemeAreaGroupVos = new ArrayList<>();
        List<SchemeAreaGroupDto> dtos = dao.areaGroupDtoList(groupId);
        dtos.parallelStream().forEach(s -> schemeAreaGroupVos.add(change(s)));
        return schemeAreaGroupVos;
    }

    /**
     * 初始化
     *
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

    @Transactional(readOnly = true)
    public SchemeAreaGroup get(Integer id) {
        return dao.get(id);
    }

    public SchemeAreaGroupVo change(SchemeAreaGroupDto dto) {
        SchemeAreaGroupVo vo = new SchemeAreaGroupVo();
        DeclareRecord declareRecord = declareRecordService.getByID(dto.getRecordId());
        vo.setFloorArea(declareRecord.getFloorArea());
        vo.setRecordName(declareRecord.getName());
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

}
