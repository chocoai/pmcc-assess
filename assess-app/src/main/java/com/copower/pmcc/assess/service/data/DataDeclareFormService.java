package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataDeclareFormDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataDeclareForm;
import com.copower.pmcc.assess.dto.output.data.DataDeclareFormVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.enums.HttpReturnEnum;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kings on 2018-4-24.
 */
@Service
public class DataDeclareFormService {
    @Autowired
    private DataDeclareFormDao dataDeclareFormDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private ProcessControllerComponent processControllerComponent;

    /**
     * 获取数据列表
     *
     * @return
     */
    public BootstrapTableVo getList(Integer assessClass, String name) {
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        BootstrapTableVo bootstrapTableVo = new BootstrapTableVo();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataDeclareForm> list = dataDeclareFormDao.getDataDeclareFormList(assessClass, name);
        List<DataDeclareFormVo> voList = getVoList(list);
        bootstrapTableVo.setTotal(page.getTotal());
        bootstrapTableVo.setRows(CollectionUtils.isEmpty(voList) ? new ArrayList<DataDeclareFormVo>() : voList);
        return bootstrapTableVo;
    }

    private List<DataDeclareFormVo> getVoList(List<DataDeclareForm> list) {
        if (CollectionUtils.isEmpty(list)) return null;
        return LangUtils.transform(list, p -> {
            DataDeclareFormVo dataDeclareFormVo = new DataDeclareFormVo();
            BeanUtils.copyProperties(p, dataDeclareFormVo);
            if (p.getAssessClass() != null) {
                BaseDataDic baseDataDic = baseDataDicService.getCacheDataDicById(p.getAssessClass());
                if (baseDataDic != null)
                    dataDeclareFormVo.setAssessClassName(baseDataDic.getName());
            }
            return dataDeclareFormVo;
        });
    }

    /**
     * 保存数据
     *
     * @param dataDeclareFormDto
     */
    public void saveData(DataDeclareForm dataDeclareFormDto) throws BusinessException {
        if (dataDeclareFormDto == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        if (dataDeclareFormDto.getId() != null && dataDeclareFormDto.getId() > 0) {
            dataDeclareFormDao.updateDataDeclareForm(dataDeclareFormDto);
        } else {
            dataDeclareFormDto.setCreator(processControllerComponent.getThisUser());
            dataDeclareFormDao.addDataDeclareForm(dataDeclareFormDto);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     */
    public void deleteData(Integer id) throws BusinessException {
        if (id == null)
            throw new BusinessException(HttpReturnEnum.EMPTYPARAM.getName());
        dataDeclareFormDao.deleteDataDeclareForm(id);
    }
}
