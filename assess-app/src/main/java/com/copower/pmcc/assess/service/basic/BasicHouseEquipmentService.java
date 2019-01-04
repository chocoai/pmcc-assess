package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicHouseEquipmentDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicHouseEquipment;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseEquipmentVo;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
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
 * @Date: 2018/11/6 11:31
 * @Description:设备 包含（空调、新风、供暖）
 */
@Service
public class BasicHouseEquipmentService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseEquipmentDao basicHouseEquipmentDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicHouseEquipment getBasicHouseEquipmentById(Integer id) throws Exception {
        return basicHouseEquipmentDao.getBasicHouseEquipmentById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseEquipment
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouseEquipment(BasicHouseEquipment basicHouseEquipment) throws Exception {
        if (basicHouseEquipment.getId() == null || basicHouseEquipment.getId().intValue() == 0) {
            basicHouseEquipment.setCreator(commonService.thisUserAccount());
            Integer id = basicHouseEquipmentDao.saveBasicHouseEquipment(basicHouseEquipment);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class), id);
            return id;
        } else {
            BasicHouseEquipment oo = basicHouseEquipmentDao.getBasicHouseEquipmentById(basicHouseEquipment.getId());
            basicHouseEquipmentDao.updateBasicHouseEquipment(basicHouseEquipment);
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
    public boolean deleteBasicHouseEquipment(Integer id) throws Exception {
        return basicHouseEquipmentDao.deleteBasicHouseEquipment(id);
    }

    public boolean deleteBasicHouseEquipment(BasicHouseEquipment basicHouseEquipment) throws Exception {
        return basicHouseEquipmentDao.deleteBasicHouseEquipment(basicHouseEquipment);
    }

    /**
     * 获取数据列表
     *
     * @param basicHouseEquipment
     * @return
     * @throws Exception
     */
    public List<BasicHouseEquipment> basicHouseEquipmentList(BasicHouseEquipment basicHouseEquipment) throws Exception {
        return basicHouseEquipmentDao.basicHouseEquipmentList(basicHouseEquipment);
    }

    public List<BasicHouseEquipment> getBasicHouseEquipmentList(Integer houseId){
        BasicHouseEquipment where=new BasicHouseEquipment();
        where.setHouseId(houseId);
        return basicHouseEquipmentDao.basicHouseEquipmentList(where);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouseEquipment basicHouseEquipment) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseEquipment> basicHouseEquipmentList = basicHouseEquipmentDao.basicHouseEquipmentList(basicHouseEquipment);
        List<BasicHouseEquipmentVo> vos = Lists.newArrayList();
        basicHouseEquipmentList.forEach(oo -> vos.add(getBasicHouseEquipmentVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicHouseEquipmentVo>(10) : vos);
        return vo;
    }

    public BasicHouseEquipmentVo getBasicHouseEquipmentVo(BasicHouseEquipment basicHouseEquipment) {
        if (basicHouseEquipment == null) {
            return null;
        }
        BasicHouseEquipmentVo vo = new BasicHouseEquipmentVo();
        BeanUtils.copyProperties(basicHouseEquipment, vo);
        vo.setCategoryName(baseDataDicService.getNameById(basicHouseEquipment.getCategory()));
        vo.setEquipmentPriceName(baseDataDicService.getNameById(basicHouseEquipment.getEquipmentPrice()));
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(basicHouseEquipment.getId(), null, FormatUtils.entityNameConvertToTableName(BasicHouseEquipment.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            if (sysAttachmentDtos.size() >= 1) {
                for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                    if (sysAttachmentDto != null) {
                        builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto));
                        builder.append(" ");
                    }
                }
            }
            vo.setFileName(builder.toString());
        }
        return vo;
    }

    /**
     * 根据查询条件判断是否有数据
     *
     * @param houseId
     * @return
     */
    public boolean hasHouseEquipmentData(Integer houseId, String type) {
        return basicHouseEquipmentDao.countByHouseId(houseId, type) > 0;
    }

}
