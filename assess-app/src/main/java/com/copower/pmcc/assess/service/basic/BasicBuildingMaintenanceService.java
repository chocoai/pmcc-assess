package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicBuildingMaintenanceDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingMaintenance;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingMaintenanceVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/30 11:42
 * @Description:围护结构
 */
@Service
public class BasicBuildingMaintenanceService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicBuildingMaintenanceDao basicBuildingMaintenanceDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicBuildingMaintenance getBasicBuildingMaintenanceById(Integer id) throws Exception {
        return basicBuildingMaintenanceDao.getBasicBuildingMaintenanceById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicBuildingMaintenance
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicBuildingMaintenance(BasicBuildingMaintenance basicBuildingMaintenance, boolean updateNull) throws Exception {
        if (basicBuildingMaintenance.getId() == null || basicBuildingMaintenance.getId().intValue() == 0) {
            basicBuildingMaintenance.setCreator(commonService.thisUserAccount());
            Integer id = basicBuildingMaintenanceDao.saveBasicBuildingMaintenance(basicBuildingMaintenance);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicBuildingMaintenance.class), id);
            return  id ;
        } else {
            if(updateNull){
                BasicBuildingMaintenance buildingMaintenance = basicBuildingMaintenanceDao.getBasicBuildingMaintenanceById(basicBuildingMaintenance.getId());
                if(basicBuildingMaintenance!=null){
                    basicBuildingMaintenance.setCreator(buildingMaintenance.getCreator());
                    basicBuildingMaintenance.setGmtCreated(buildingMaintenance.getGmtCreated());
                }
            }
            basicBuildingMaintenanceDao.updateBasicBuildingMaintenance(basicBuildingMaintenance,updateNull);
            return null;
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicBuildingMaintenance(Integer id) throws Exception {
        return basicBuildingMaintenanceDao.deleteBasicBuildingMaintenance(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicBuildingMaintenance
     * @return
     * @throws Exception
     */
    public List<BasicBuildingMaintenance> basicBuildingMaintenanceList(BasicBuildingMaintenance basicBuildingMaintenance) throws Exception {
        return basicBuildingMaintenanceDao.basicBuildingMaintenanceList(basicBuildingMaintenance);
    }

    public void removeBasicBuildingMaintenance(BasicBuildingMaintenance basicBuildingMaintenance)throws Exception{
        basicBuildingMaintenanceDao.removeBasicBuildingMaintenance(basicBuildingMaintenance);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicBuildingMaintenance basicBuildingMaintenance) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicBuildingMaintenance> basicBuildingMaintenanceList = basicBuildingMaintenanceDao.basicBuildingMaintenanceList(basicBuildingMaintenance);
        List<BasicBuildingMaintenanceVo> vos = Lists.newArrayList();
        basicBuildingMaintenanceList.forEach(oo -> vos.add(getBasicBuildingMaintenanceVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicBuildingMaintenanceVo>(10) : vos);
        return vo;
    }

    public BasicBuildingMaintenanceVo getBasicBuildingMaintenanceVo(BasicBuildingMaintenance basicBuildingMaintenance){
        if (basicBuildingMaintenance==null){
            return null;
        }
        BasicBuildingMaintenanceVo vo = new BasicBuildingMaintenanceVo();
        BeanUtils.copyProperties(basicBuildingMaintenance,vo);
        vo.setCategoryName(baseDataDicService.getNameById(basicBuildingMaintenance.getCategory()));
        vo.setTypeName(baseDataDicService.getNameById(basicBuildingMaintenance.getType()));
        vo.setMaterialQualityName(baseDataDicService.getNameById(basicBuildingMaintenance.getMaterialQuality()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicBuildingMaintenance.getCreator()));
        return vo;
    }
    
}
