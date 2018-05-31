package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.dao.DataCsrFieldRelationDao;
import com.copower.pmcc.assess.dal.entity.DataCsrFieldRelation;
import com.copower.pmcc.assess.dto.output.data.DataCsrFieldRelationVo;
import com.copower.pmcc.assess.service.base.FormConfigureService;
import com.copower.pmcc.erp.api.dto.KeyValueDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 债权独立模块 字段关联关系
 */
@Service
public class DataCsrFieldRelationService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataCsrFieldRelationDao csrFieldRelationDao;

    @Autowired
    private CommonService commonService;

    @Autowired
    private FormConfigureService configureService;

    @Transactional
    public boolean add(DataCsrFieldRelation csrFieldRelation){
        try {
            if (ObjectUtils.isEmpty(csrFieldRelation)){
                throw  new Exception();
            }else {
                csrFieldRelation.setCreator(commonService.thisUserAccount());
                return csrFieldRelationDao.add(csrFieldRelation);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean remove(Integer id){
        try {
            if (ObjectUtils.isEmpty(id)){
                throw  new Exception();
            }else {
                return csrFieldRelationDao.remove(id);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<DataCsrFieldRelationVo> dataCsrFieldRelationList(String anotherName){
        List<DataCsrFieldRelation> fieldRelations = csrFieldRelationDao.dataCsrFieldRelationList(anotherName);
        List<DataCsrFieldRelationVo> vos = new ArrayList<>();
        for (DataCsrFieldRelation csrFieldRelation:fieldRelations){
            vos.add(change(csrFieldRelation));
        }
        return vos;
    }

    public BootstrapTableVo listVos(String anotherName){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataCsrFieldRelationVo> vos = dataCsrFieldRelationList(anotherName);
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataCsrFieldRelationVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    @Transactional
    public boolean update(DataCsrFieldRelation csrFieldRelation){
        try {
            if (ObjectUtils.isEmpty(csrFieldRelation)){
                throw  new Exception();
            }else {
                csrFieldRelation.setCreator(csrFieldRelationDao.get(csrFieldRelation.getId()).getCreator());
                return csrFieldRelationDao.update(csrFieldRelation);
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }

    @Transactional(readOnly = true)
    public DataCsrFieldRelationVo get(Integer id){
        try {
            if (ObjectUtils.isEmpty(id)){
                throw  new Exception();
            }else {
                return change(csrFieldRelationDao.get(id));
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<KeyValueDto> getTableList() {
        return configureService.getTableList();
    }

    public List<KeyValueDto> getFieldList(String tableName){
        return configureService.getFieldList(tableName);
    }

    public DataCsrFieldRelationVo change(DataCsrFieldRelation csrFieldRelation){
        DataCsrFieldRelationVo vo = new DataCsrFieldRelationVo();
        BeanUtils.copyProperties(csrFieldRelation,vo);
        return vo;
    }
}
