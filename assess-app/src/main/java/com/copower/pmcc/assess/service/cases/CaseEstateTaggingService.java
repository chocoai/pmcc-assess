package com.copower.pmcc.assess.service.cases;

import com.copower.pmcc.assess.dal.cases.dao.CaseEstateTaggingDao;
import com.copower.pmcc.assess.dal.cases.entity.CaseEstateTagging;
import com.copower.pmcc.assess.dto.input.MapDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/6 10:54
 * @Description:
 */
@Service
public class CaseEstateTaggingService {
    @Autowired
    private CaseEstateTaggingDao caseEstateTaggingDao;
    @Autowired
    private CommonService commonService;


    public List<MapDto> mapDtoList(Integer pid,String type) throws Exception {
        List<MapDto> mapDtoList = new ArrayList<MapDto>(10);
        CaseEstateTagging query = new CaseEstateTagging();
        query.setPid(pid);
        query.setType(type);
        List<CaseEstateTagging> taggingList = getCaseEstateTaggingList(query);
        if (!ObjectUtils.isEmpty(taggingList)){
            for (CaseEstateTagging tagging:taggingList){
                if (!NumberUtils.isNumber(tagging.getLat()) || !NumberUtils.isNumber(tagging.getLng())){
                    continue;
                }
                MapDto mapDto = new MapDto();
                mapDto.setLat(new BigDecimal(tagging.getLat())).setLon(new BigDecimal(tagging.getLng())).setId(tagging.getId()).setType(tagging.getType());
                if (StringUtils.isNotBlank(tagging.getName())){
                    mapDto.setName(tagging.getName());
                }
                mapDtoList.add(mapDto);
            }
        }
        return mapDtoList;
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public CaseEstateTagging getCaseEstateTaggingById(Integer id) throws Exception {
        return caseEstateTaggingDao.getCaseEstateTaggingById(id);
    }


    /**
     * 新增或者修改
     *
     * @param caseEstateTagging
     * @return
     * @throws Exception
     */
    public void saveCaseEstateTagging(CaseEstateTagging caseEstateTagging) throws Exception {
        caseEstateTaggingDao.saveCaseEstateTagging(caseEstateTagging);
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteCaseEstateTagging(Integer id) throws Exception {
        return caseEstateTaggingDao.deleteCaseEstateTagging(id);
    }

    /**
     * 获取数据列表
     *
     * @param caseEstateTagging
     * @return
     * @throws Exception
     */
    public List<CaseEstateTagging> getCaseEstateTaggingList(CaseEstateTagging caseEstateTagging) throws Exception {
        return caseEstateTaggingDao.caseEstateTaggingList(caseEstateTagging);
    }

    public void removeCaseEstateTagging(CaseEstateTagging caseEstateTagging) throws Exception {
        caseEstateTaggingDao.removeCaseEstateTagging(caseEstateTagging);
    }

    public BootstrapTableVo getEstateTaggingList(Integer estateId) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        CaseEstateTagging where = new CaseEstateTagging();
        where.setEstateId(estateId);
        List<CaseEstateTagging> caseEstateTaggingList = caseEstateTaggingDao.caseEstateTaggingList(where);
        vo.setTotal((long) caseEstateTaggingList.size());
        vo.setRows(ObjectUtils.isEmpty(caseEstateTaggingList) ? Lists.newArrayList() : caseEstateTaggingList);
        return vo;
    }

}
