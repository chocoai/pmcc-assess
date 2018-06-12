package com.copower.pmcc.assess.service.project;

import com.copower.pmcc.assess.dal.dao.InitiateConsignorDao;
import com.copower.pmcc.assess.dto.input.project.InitiateConsignorDto;
import com.copower.pmcc.assess.dto.output.project.InitiateConsignorVo;
import com.copower.pmcc.erp.common.CommonService;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 委托人信息
 * Created by 13426 on 2018/5/4.
 */
@Service
public class InitiateConsignorService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private InitiateConsignorDao dao;

    public int add(InitiateConsignorDto dto) {

        //对 委托人进行单独处理
        if (dto.getCsType() == InitiateConsignorDto.CSTYPEa) {
            dto.setCsUnitProperties(null);
            dto.setCsScopeOperation(null);
            dto.setCsSociologyCode(null);
            dto.setCsLegalRepresentative(null);
            dto.setCsEntrustmentUnit(null);
        }
        if (dto.getCreator() == null) dto.setCreator(commonService.thisUserAccount());
        return dao.add(dto);
    }

    public InitiateConsignorVo get(Integer id) {
        return change(dao.get(id));
    }

    public InitiateConsignorDto getById(Integer id) {
        return dao.get(id);
    }

    @Transactional
    public boolean remove(Integer id) {
        return dao.remove(id);
    }

    @Transactional
    public boolean update(InitiateConsignorDto dto) {
        if (dto.getCreator() == null) dto.setCreator(commonService.thisUserAccount());
        return dao.update(dto);
    }


    private List<InitiateConsignorVo> getVoList() {
        List<InitiateConsignorVo> vos = new ArrayList<>();
        dao.getList().parallelStream().forEach(oo -> vos.add(change(oo)));
        return vos;
    }

    private InitiateConsignorDto change(InitiateConsignorVo vo) {
        InitiateConsignorDto dto = new InitiateConsignorDto();
        BeanUtils.copyProperties(vo, dto);
        return dto;
    }

    private InitiateConsignorVo change(InitiateConsignorDto dto) {
        InitiateConsignorVo vo = new InitiateConsignorVo();
        BeanUtils.copyProperties(dto, vo);
        return vo;
    }

    /**
     * 第一次填写后留下的委托人 数据信息
     * @return
     */
    public InitiateConsignorDto oneFirstConsignor() {
        List<InitiateConsignorDto> dtos = dao.getList();
        Collections.sort(dtos,new Comparator<Object>(){
            @Override
            public int compare(Object o1, Object o2) {
                InitiateConsignorDto consignorA = (InitiateConsignorDto)o1;
                InitiateConsignorDto consignorB = (InitiateConsignorDto)o2;
                if (!ObjectUtils.isEmpty(consignorA.getGmtCreated()) &&  !ObjectUtils.isEmpty(consignorB.getGmtCreated())){
                    return consignorA.getGmtCreated().compareTo(consignorB.getGmtCreated());
                }else if (consignorA.getGmtCreated() == null){
                    return consignorA.getCsName().compareTo(consignorB.getCsName());
                }
                return 0;
            }
        });
        InitiateConsignorDto consignorDto = dtos.get(dtos.size()-1);
        return consignorDto;
    }

}
