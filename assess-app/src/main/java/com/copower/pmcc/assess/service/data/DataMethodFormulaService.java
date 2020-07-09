package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataMethodFormulaDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataMethodFormula;
import com.copower.pmcc.assess.dto.output.data.DataMethodFormulaVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DataMethodFormulaService {
    @Autowired
    private DataMethodFormulaDao dataMethodFormulaDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public DataMethodFormulaVo getByDataMethodFormulaId(Integer id) {
        DataMethodFormula object = dataMethodFormulaDao.getSingleObject(id);
        return getDataMethodFormulaVo(object);
    }

    /**
     * 获取列表
     *
     * @return
     */
    public BootstrapTableVo getListVos(Integer type) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ArrayList<DataMethodFormulaVo> vos = new ArrayList<>();
        List<DataMethodFormula> dataMethodFormulaList = dataMethodFormulaDao.getDataMethodFormulaList(type);
        if (CollectionUtils.isNotEmpty(dataMethodFormulaList)) {
            for (DataMethodFormula item : dataMethodFormulaList) {
                vos.add(getDataMethodFormulaVo(item));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<DataMethodFormulaVo>() : vos);
        return vo;
    }

    public List<DataMethodFormula> getDataMethodFormulaList(Integer type) {
        return dataMethodFormulaDao.getDataMethodFormulaList(type);
    }

    public DataMethodFormula getDataMethodFormulaByType(Integer methodType) {
        List<DataMethodFormula> list = dataMethodFormulaDao.getDataMethodFormulaList(methodType);
        if (CollectionUtils.isEmpty(list)) return null;
        return list.get(0);
    }

    public DataMethodFormula getMethodFormulaByMethodKey(String methodKey) {
        DataMethodFormula dataMethodFormula = new DataMethodFormula();
        dataMethodFormula.setMethodKey(methodKey);
        DataMethodFormula singleObject = dataMethodFormulaDao.getSingleObject(dataMethodFormula);
        if (singleObject != null) {
            return singleObject;
        }
        return dataMethodFormula;
    }


    public DataMethodFormulaVo getDataMethodFormulaVo(DataMethodFormula dataMethodFormula) {
        if (dataMethodFormula == null) return null;
        DataMethodFormulaVo dataMethodFormulaVo = new DataMethodFormulaVo();
        BeanUtils.copyProperties(dataMethodFormula, dataMethodFormulaVo);
        BaseDataDic cacheDataDicById = baseDataDicService.getCacheDataDicById(dataMethodFormula.getMethod());
        dataMethodFormulaVo.setMethodType(cacheDataDicById.getName());
        return dataMethodFormulaVo;
    }

    /**
     * 保存
     *
     * @param dataMethodFormula
     * @throws BusinessException
     */
    public boolean addDataMethodFormulaReturnId(DataMethodFormula dataMethodFormula) {
        dataMethodFormula.setCreator(processControllerComponent.getThisUser());
        return dataMethodFormulaDao.addObject(dataMethodFormula);
    }

    public boolean updateDataMethodFormula(DataMethodFormula dataMethodFormula) {
        return dataMethodFormulaDao.updateObject(dataMethodFormula);
    }

    /**
     * 删除
     *
     * @param id
     * @throws BusinessException
     */
    public boolean deleteDataMethodFormula(Integer id) throws BusinessException {
        return dataMethodFormulaDao.deleteObject(id);
    }

}
