package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicEstateParkingDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateParking;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateParkingVo;
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
 * @Date: 2018/11/6 10:49
 * @Description:车位信息
 */
@Service
public class BasicEstateParkingService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateParkingDao basicEstateParkingDao;
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
    public BasicEstateParking getBasicEstateParkingById(Integer id) throws Exception {
        return basicEstateParkingDao.getBasicEstateParkingById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicEstateParking
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicEstateParking(BasicEstateParking basicEstateParking) throws Exception {
        if (basicEstateParking.getId() == null || basicEstateParking.getId().intValue() == 0) {
            basicEstateParking.setCreator(commonService.thisUserAccount());
            Integer id = basicEstateParkingDao.saveBasicEstateParking(basicEstateParking);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstateParking.class), id);
            return id;
        } else {
            BasicEstateParking oo = basicEstateParkingDao.getBasicEstateParkingById(basicEstateParking.getId());
            basicEstateParkingDao.updateBasicEstateParking(basicEstateParking);
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
    public boolean deleteBasicEstateParking(Integer id) throws Exception {
        return basicEstateParkingDao.deleteBasicEstateParking(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicEstateParking
     * @return
     * @throws Exception
     */
    public List<BasicEstateParking> basicEstateParkingList(BasicEstateParking basicEstateParking) throws Exception {
        return basicEstateParkingDao.basicEstateParkingList(basicEstateParking);
    }

    public void removeBasicEstateParking(BasicEstateParking basicEstateParking) throws Exception {
        basicEstateParkingDao.removeBasicEstateParking(basicEstateParking);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicEstateParking basicEstateParking) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstateParking> basicEstateParkingList = basicEstateParkingDao.basicEstateParkingList(basicEstateParking);
        List<BasicEstateParkingVo> vos = Lists.newArrayList();
        basicEstateParkingList.forEach(oo -> vos.add(getBasicEstateParkingVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicEstateParkingVo>(10) : vos);
        return vo;
    }

    public BasicEstateParkingVo getBasicEstateParkingVo(BasicEstateParking basicEstateParking) {
        if (basicEstateParking == null) {
            return null;
        }
        BasicEstateParkingVo vo = new BasicEstateParkingVo();
        BeanUtils.copyProperties(basicEstateParking, vo);
        if (basicEstateParking.getParkingType() != null && basicEstateParking.getParkingType() > 0) {
            vo.setParkingTypeName(baseDataDicService.getNameById(basicEstateParking.getParkingType()));
        }
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(basicEstateParking.getId(), null, FormatUtils.entityNameConvertToTableName(BasicEstateParking.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                if (sysAttachmentDto != null) {
                    builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto));
                    builder.append(" ");
                }
            }
            vo.setFileViewName(builder.toString());
        }
        return vo;
    }

}
