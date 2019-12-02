package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseWaterDrainDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseWaterDrain;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseWaterDrainVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
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
 * @Date: 2018/12/11 11:38
 * @Description:
 */
@Service
public class BasicHouseWaterDrainService {
    @Autowired
    private BasicHouseWaterDrainDao basicHouseWaterDrainDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private BaseAttachmentService baseAttachmentService;
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
    public BasicHouseWaterDrain getBasicHouseWaterDrainById(Integer id) throws Exception {
        return basicHouseWaterDrainDao.getBasicHouseWaterDrainById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseWaterDrain
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouseWaterDrain(BasicHouseWaterDrain basicHouseWaterDrain, boolean updateNull) throws Exception {
        if (basicHouseWaterDrain.getId() == null || basicHouseWaterDrain.getId().intValue() == 0) {
            basicHouseWaterDrain.setCreator(commonService.thisUserAccount());
            Integer id = basicHouseWaterDrainDao.addBasicHouseWaterDrain(basicHouseWaterDrain);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicHouseWaterDrain.class), id);
            return  id ;
        } else {
            if(updateNull){
                BasicHouseWaterDrain houseWaterDrain = basicHouseWaterDrainDao.getBasicHouseWaterDrainById(basicHouseWaterDrain.getId());
                if(houseWaterDrain!=null){
                    basicHouseWaterDrain.setCreator(houseWaterDrain.getCreator());
                    basicHouseWaterDrain.setGmtCreated(houseWaterDrain.getGmtCreated());
                }
            }
            basicHouseWaterDrainDao.updateBasicHouseWaterDrain(basicHouseWaterDrain,updateNull);
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
    public boolean deleteBasicHouseWaterDrain(Integer id) throws Exception {
        return basicHouseWaterDrainDao.deleteBasicHouseWaterDrain(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicHouseWaterDrain
     * @return
     * @throws Exception
     */
    public List<BasicHouseWaterDrain> basicHouseWaterDrainList(BasicHouseWaterDrain basicHouseWaterDrain) throws Exception {
        return basicHouseWaterDrainDao.basicHouseWaterDrainList(basicHouseWaterDrain);
    }

    public List<BasicHouseWaterDrain> getBasicHouseWaterDrainList(Integer houseId)  {
        BasicHouseWaterDrain where=new BasicHouseWaterDrain();
        where.setHouseId(houseId);
        return basicHouseWaterDrainDao.basicHouseWaterDrainList(where);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouseWaterDrain basicHouseWaterDrain) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseWaterDrain> basicHouseWaterDrainList = basicHouseWaterDrainDao.basicHouseWaterDrainList(basicHouseWaterDrain);
        List<BasicHouseWaterDrainVo> vos = Lists.newArrayList();
        basicHouseWaterDrainList.forEach(oo -> vos.add(getBasicHouseWaterDrainVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicHouseWaterDrainVo>(10) : vos);
        return vo;
    }

    public BasicHouseWaterDrainVo getBasicHouseWaterDrainVo(BasicHouseWaterDrain basicHouseWaterDrain){
        if (basicHouseWaterDrain==null){
            return null;
        }
        BasicHouseWaterDrainVo vo = new BasicHouseWaterDrainVo();
        BeanUtils.copyProperties(basicHouseWaterDrain,vo);
        vo.setTypeName(baseDataDicService.getNameById(basicHouseWaterDrain.getType()));
        vo.setOrganizationName(baseDataDicService.getNameById(basicHouseWaterDrain.getOrganization()));
        vo.setDrainSystemName(baseDataDicService.getNameById(basicHouseWaterDrain.getDrainSystem()));
        vo.setProcessingModeName(baseDataDicService.getNameById(basicHouseWaterDrain.getProcessingMode()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicHouseWaterDrain.getCreator()));
        return vo;
    }

    /**
     * 根据查询条件判断是否有数据
     *
     * @param houseId
     * @return
     */
    public boolean hasHouseWaterDrainData(Integer houseId) {
        return basicHouseWaterDrainDao.countByHouseId(houseId) > 0;
    }


    public List<BasicHouseWaterDrainVo> getBasicHouseWaterDrainVoList(Integer houseId) throws Exception{
        return LangUtils.transform(getBasicHouseWaterDrainList(houseId), o -> getBasicHouseWaterDrainVo(o));
    }
}
