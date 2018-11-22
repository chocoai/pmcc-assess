package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicBuildingMainDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuilding;
import com.copower.pmcc.assess.dal.basic.entity.BasicBuildingMain;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/26 16:46
 * @Description:
 */
@Service
public class BasicBuildingMainService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicBuildingService basicBuildingService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicBuildingMainDao basicBuildingMainDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void batchBasicBuilding(Integer id)throws Exception{
        BasicBuilding building = new BasicBuilding();
        building.setBasicBuildingMainId(0);
        List<BasicBuilding> basicBuildingList = basicBuildingService.basicBuildingList(building);
        if (!ObjectUtils.isEmpty(basicBuildingList)){
            for (BasicBuilding basicBuilding:basicBuildingList){
                basicBuilding.setBasicBuildingMainId(id);
                basicBuildingService.update(basicBuilding);
            }
        }
    }

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicBuildingMain getBasicBuildingMainById(Integer id) throws Exception {
        return basicBuildingMainDao.getBasicBuildingMainById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicBuildingMain
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicBuildingMain(BasicBuildingMain basicBuildingMain) throws Exception {
        if (basicBuildingMain.getId() == null || basicBuildingMain.getId().intValue() == 0) {
            basicBuildingMain.setCreator(commonService.thisUserAccount());
            Integer id = basicBuildingMainDao.saveBasicBuildingMain(basicBuildingMain);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicBuildingMain.class), id);
            return  id ;
        } else {
            basicBuildingMainDao.updateBasicBuildingMain(basicBuildingMain);
            return null;
        }
    }

    public Integer upgradeVersion(BasicBuildingMain basicBuildingMain) throws Exception {
        if (basicBuildingMain.getId() == null || basicBuildingMain.getId().intValue() == 0) {
            basicBuildingMain.setCreator(commonService.thisUserAccount());
            Integer id = basicBuildingMainDao.saveBasicBuildingMain(basicBuildingMain);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicBuildingMain.class), id);
            basicBuildingMain.setId(id);
            return  id ;
        } else {
            return basicBuildingMain.getId();
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicBuildingMain(Integer id) throws Exception {
        return basicBuildingMainDao.deleteBasicBuildingMain(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicBuildingMain
     * @return
     * @throws Exception
     */
    public List<BasicBuildingMain> basicBuildingMainList(BasicBuildingMain basicBuildingMain) throws Exception {
        return basicBuildingMainDao.basicBuildingMainList(basicBuildingMain);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicBuildingMain basicBuildingMain) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicBuildingMain> basicBuildingMainList = basicBuildingMainDao.basicBuildingMainList(basicBuildingMain);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicBuildingMainList) ? new ArrayList<BasicBuildingMain>(10) : basicBuildingMainList);
        return vo;
    }

    public List<BasicBuildingMain> autoComplete(String identifier,Integer estateId)throws Exception{
        BasicBuildingMain main = new BasicBuildingMain();
        main.setBuildingNumber(identifier);
        main.setEstateId(estateId);
        return basicBuildingMainDao.autoComplete(main);
    }

}
