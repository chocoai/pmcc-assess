package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataImgTwoDimensionalDao;
import com.copower.pmcc.assess.dal.basis.entity.DataImgTwoDimensional;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/11/28 13:50
 * @Description:
 */
@Service
public class DataImgTwoDimensionalService {

    @Autowired
    private DataImgTwoDimensionalDao dataImgTwoDimensionalDao;
    @Autowired
    private CommonService commonService;

    public Integer saveAndUpdateDataImgTwoDimensional(DataImgTwoDimensional dataImgTwoDimensional) throws Exception {
        if (dataImgTwoDimensional == null) {
            throw new Exception("null point");
        }
        if (dataImgTwoDimensional.getId() == null) {
            dataImgTwoDimensional.setCreator(commonService.thisUserAccount());
            return dataImgTwoDimensionalDao.addDataImgTwoDimensional(dataImgTwoDimensional);
        } else {
            dataImgTwoDimensionalDao.updateDataImgTwoDimensional(dataImgTwoDimensional);
            return null;
        }
    }

    public DataImgTwoDimensional getDataImgTwoDimensionalById(Integer id) {
        return dataImgTwoDimensionalDao.getDataImgTwoDimensionalById(id);
    }

    public BootstrapTableVo getBootstrapTableVo(DataImgTwoDimensional dataImgTwoDimensional) throws Exception {
        if (dataImgTwoDimensional == null) {
            throw new Exception("null point");
        }
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataImgTwoDimensional> vos = dataImgTwoDimensionalDao.getDataImgTwoDimensionalList(dataImgTwoDimensional);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DataImgTwoDimensional> dataImgTwoDimensionalList(DataImgTwoDimensional dataImgTwoDimensional) throws Exception {
        if (dataImgTwoDimensional == null) {
            throw new Exception("null point");
        }
        return dataImgTwoDimensionalDao.getDataImgTwoDimensionalList(dataImgTwoDimensional);
    }

    public void removeDataImgTwoDimensional(DataImgTwoDimensional dataImgTwoDimensional) throws Exception {
        if (dataImgTwoDimensional == null) {
            throw new Exception("null point");
        }
        dataImgTwoDimensionalDao.removeDataImgTwoDimensional(dataImgTwoDimensional);
    }

}
