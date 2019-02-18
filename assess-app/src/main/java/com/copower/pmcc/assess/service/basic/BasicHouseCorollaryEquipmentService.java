package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicHouseCorollaryEquipmentDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicHouseCorollaryEquipment;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseCorollaryEquipmentVo;
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
 * @Date: 2018/11/6 11:34
 * @Description:配套设备设施
 */
@Service
public class BasicHouseCorollaryEquipmentService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicHouseCorollaryEquipmentDao basicHouseCorollaryEquipmentDao;
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
    public BasicHouseCorollaryEquipment getBasicHouseCorollaryEquipmentById(Integer id) throws Exception {
        return basicHouseCorollaryEquipmentDao.getBasicHouseCorollaryEquipmentById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicHouseCorollaryEquipment
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicHouseCorollaryEquipment(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment) throws Exception {
        if (basicHouseCorollaryEquipment.getId() == null || basicHouseCorollaryEquipment.getId().intValue() == 0) {
            basicHouseCorollaryEquipment.setCreator(commonService.thisUserAccount());
            Integer id = basicHouseCorollaryEquipmentDao.saveBasicHouseCorollaryEquipment(basicHouseCorollaryEquipment);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class), id);
            return  id ;
        } else {
            BasicHouseCorollaryEquipment oo = basicHouseCorollaryEquipmentDao.getBasicHouseCorollaryEquipmentById(basicHouseCorollaryEquipment.getId());
            basicHouseCorollaryEquipmentDao.updateBasicHouseCorollaryEquipment(basicHouseCorollaryEquipment);
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
    public boolean deleteBasicHouseCorollaryEquipment(Integer id) throws Exception {
        if (id==null){
            return false;
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(id, null, FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)){
            sysAttachmentDtos.forEach(oo -> baseAttachmentService.deleteAttachment(oo.getId()));
        }
        return basicHouseCorollaryEquipmentDao.deleteBasicHouseCorollaryEquipment(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicHouseCorollaryEquipment
     * @return
     * @throws Exception
     */
    public List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment) throws Exception {
        return basicHouseCorollaryEquipmentDao.basicHouseCorollaryEquipmentList(basicHouseCorollaryEquipment);
    }

    public List<BasicHouseCorollaryEquipment> getBasicHouseCorollaryEquipmentList(Integer houseId){
        BasicHouseCorollaryEquipment where=new BasicHouseCorollaryEquipment();
        where.setHouseId(houseId);
        return basicHouseCorollaryEquipmentDao.basicHouseCorollaryEquipmentList(where);
    }

    public void removeBasicHouseCorollaryEquipment(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment)throws Exception{
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(basicHouseCorollaryEquipment.getId(), null, FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)){
            sysAttachmentDtos.forEach(oo -> baseAttachmentService.deleteAttachment(oo.getId()));
        }
        basicHouseCorollaryEquipmentDao.removeBasicHouseCorollaryEquipment(basicHouseCorollaryEquipment);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicHouseCorollaryEquipment> basicHouseCorollaryEquipmentList = basicHouseCorollaryEquipmentDao.basicHouseCorollaryEquipmentList(basicHouseCorollaryEquipment);
        List<BasicHouseCorollaryEquipmentVo> vos = Lists.newArrayList();
        basicHouseCorollaryEquipmentList.forEach(oo -> vos.add(getBasicHouseCorollaryEquipmentVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicHouseCorollaryEquipmentVo>(10) : vos);
        return vo;
    }

    public BasicHouseCorollaryEquipmentVo getBasicHouseCorollaryEquipmentVo(BasicHouseCorollaryEquipment basicHouseCorollaryEquipment){
        if (basicHouseCorollaryEquipment==null){
            return null;
        }
        BasicHouseCorollaryEquipmentVo vo = new BasicHouseCorollaryEquipmentVo();
        BeanUtils.copyProperties(basicHouseCorollaryEquipment,vo);
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(basicHouseCorollaryEquipment.getId(), null, FormatUtils.entityNameConvertToTableName(BasicHouseCorollaryEquipment.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto)).append(" ");
            }
            vo.setFileViewName(builder.toString());
        }
        vo.setCategoryName(baseDataDicService.getNameById(basicHouseCorollaryEquipment.getCategory()));
        vo.setTypeName(baseDataDicService.getNameById(basicHouseCorollaryEquipment.getType()));
        return vo;
    }
    
}
