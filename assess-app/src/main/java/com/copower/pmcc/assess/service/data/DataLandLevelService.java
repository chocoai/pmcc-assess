package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.DataLandLevelDao;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevel;
import com.copower.pmcc.assess.dto.output.data.DataLandLevelVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/4 18:24
 * @Description:土地级别维护
 */
@Service
public class DataLandLevelService {
    @Autowired
    private DataLandLevelDao dataLandLevelDao;
    @Autowired
    private CommonService commonService;

    public Integer saveAndUpdateDataLandLevel(DataLandLevel dataLandLevel){
        if (dataLandLevel.getId()==null){
            dataLandLevel.setCreator(commonService.thisUserAccount());
            return dataLandLevelDao.addDataLandLevel(dataLandLevel);
        }else {
            dataLandLevelDao.updateDataLandLevel(dataLandLevel);
            return null;
        }
    }

    public DataLandLevel getDataLandLevelById(Integer id){
        return dataLandLevelDao.getDataLandLevelById(id);
    }

    public BootstrapTableVo getDataLandLevelListVos(DataLandLevel dataLandLevel){
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataLandLevel> dataLandLevels = dataLandLevelDao.getDataLandLevelList(dataLandLevel);
        List<DataLandLevelVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataLandLevels)){
            for (DataLandLevel landLevel:dataLandLevels){
                vos.add(getDataLandLevelVo(landLevel));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public void removeDataLandLevel(DataLandLevel dataLandLevel){
        try {
            dataLandLevelDao.removeDataLandLevel(dataLandLevel);
        } catch (Exception e1) {
            try {
                throw  new Exception();
            } catch (Exception e11) {

            }
        }
    }

    public DataLandLevelVo getDataLandLevelVo(DataLandLevel dataLandLevel){
        DataLandLevelVo vo = new DataLandLevelVo();
        BeanUtils.copyProperties(dataLandLevel,vo);
        return vo;
    }
}
