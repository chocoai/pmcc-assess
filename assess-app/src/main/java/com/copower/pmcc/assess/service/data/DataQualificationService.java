package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.ad.api.enums.AdPersonalEnum;
import com.copower.pmcc.assess.dal.basis.dao.data.DataQualificationDao;
import com.copower.pmcc.assess.dal.basis.entity.DataQualification;
import com.copower.pmcc.assess.dto.output.data.DataQualificationVo;
import com.copower.pmcc.assess.service.PublicService;
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
public class DataQualificationService {
    @Autowired
    private DataQualificationDao dataQualificationDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private PublicService publicService;

    public DataQualificationVo getByDataQualificationId(Integer id) {
        DataQualification object = dataQualificationDao.getSingleObject(id);
        return getDataQualificationVo(object);
    }

    /**
     * 获取列表
     *
     * @return
     */
    public BootstrapTableVo getListVos(String type) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ArrayList<DataQualificationVo> vos = new ArrayList<>();
        List<DataQualification> dataQualificationList = dataQualificationDao.getDataQualificationList(type);
        if(CollectionUtils.isNotEmpty(dataQualificationList)){
            for (DataQualification item:dataQualificationList) {
                vos.add(getDataQualificationVo(item));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(org.apache.commons.collections.CollectionUtils.isEmpty(vos) ? new ArrayList<DataQualificationVo>() : vos);
        return vo;
    }

    public List<DataQualification> getDataQualificationList(String type){
        return dataQualificationDao.getDataQualificationList(type);
    }


    public DataQualificationVo getDataQualificationVo(DataQualification dataQualification) {
        if (dataQualification == null) return null;
        DataQualificationVo dataQualificationVo = new DataQualificationVo();
        BeanUtils.copyProperties(dataQualification, dataQualificationVo);
        dataQualificationVo.setQualificationTypeName(AdPersonalEnum.create(dataQualification.getQualificationType()).getName());
        String[] strings = dataQualification.getUserAccount().split(",");
        StringBuilder builder = new StringBuilder();
        for (String user:strings) {
            builder.append(publicService.getUserNameByAccount(user));
            builder.append(",");
        }
        dataQualificationVo.setUserAccountName(builder.deleteCharAt(builder.length()-1).toString());
        return dataQualificationVo;
    }
    /**
     * 保存
     *
     * @param dataQualification
     * @throws BusinessException
     */
    public boolean addDataQualificationReturnId(DataQualification dataQualification) {
        dataQualification.setCreator(processControllerComponent.getThisUser());
        return dataQualificationDao.addObject(dataQualification);
    }

    public boolean updateDataQualification(DataQualification dataQualification) {
        return dataQualificationDao.updateObject(dataQualification);
    }

    /**
     * 删除
     *
     * @param id
     * @throws BusinessException
     */
    public boolean deleteDataQualification(Integer id) throws BusinessException {
        return dataQualificationDao.deleteObject(id);
    }

}
