package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.common.enums.CaseComparisonEnum;
import com.copower.pmcc.assess.dal.dao.CaseComparisonDao;
import com.copower.pmcc.assess.dto.input.data.CaseComparisonDto;
import com.copower.pmcc.assess.dto.output.data.CaseComparisonVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 3.1.2.15	案例对比配置
 * Created by 13426 on 2018/5/3.
 */
@Service
public class CaseComparisonService {

    @Autowired
    private CaseComparisonDao caseComparisonDao;
    @Autowired
    private CommonService commonService;


    @Transactional
    public boolean add(CaseComparisonDto dto) {
        if (dto.getCreator()==null || dto.getCreator()=="")dto.setCreator(commonService.thisUserAccount());
        if (dto.getGmtCreated()==null)dto.setGmtCreated(new Date());
        return caseComparisonDao.add(dto);
    }

    @Transactional
    public boolean remove(Integer id) {
        return caseComparisonDao.remove(id);
    }

    @Transactional
    public boolean update(CaseComparisonDto dto) {
        if (dto.getCreator()==null || dto.getCreator()=="")dto.setCreator(commonService.thisUserAccount());
        if (dto.getGmtCreated()==null)dto.setGmtCreated(new Date());
        return caseComparisonDao.update(dto);
    }

    @Transactional(readOnly = true)
    public CaseComparisonVo get(Integer id) {
        return change(caseComparisonDao.get(id));
    }

    @Transactional(readOnly = true)
    private List<CaseComparisonVo> list(String name) {
        List<CaseComparisonVo> vos = new ArrayList<>();
        caseComparisonDao.list(name).parallelStream().forEach(c -> vos.add(change(c)));
        return vos;
    }

    public BootstrapTableVo listVos(String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CaseComparisonVo> vos = list(name);
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CaseComparisonVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    private CaseComparisonDto change(CaseComparisonVo vo) {
        CaseComparisonDto dto = new CaseComparisonVo();
        BeanUtils.copyProperties(vo, dto);
        return dto;
    }

    private CaseComparisonVo change(CaseComparisonDto dto) {
        CaseComparisonVo vo = new CaseComparisonVo();
        BeanUtils.copyProperties(dto, vo);
        if (Integer.parseInt(vo.getType())== CaseComparisonEnum.CASE_COMPARISON_ONE_ENUM.getNum()){
            vo.setTypeStr(CaseComparisonEnum.Text.getVar());
        }else if (Integer.parseInt(vo.getType())== CaseComparisonEnum.CASE_COMPARISON_TWO_ENUM.getNum()){
            vo.setTypeStr(CaseComparisonEnum.NO_Text.getVar());
        }
        return vo;
    }

    public Map<Integer,Object> getTypeMap(){
        Map<Integer,Object> map = new HashMap<>();
        map.put(CaseComparisonEnum.CASE_COMPARISON_ONE_ENUM.getNum(),CaseComparisonEnum.Text.getVar());
        map.put(CaseComparisonEnum.CASE_COMPARISON_TWO_ENUM.getNum(),CaseComparisonEnum.NO_Text.getVar());
        return map;
    }
}
