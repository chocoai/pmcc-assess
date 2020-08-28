package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateStreetInfoDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateStreetInfo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
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
 * @Date: 2018/10/30 11:52
 * @Description:楼盘 街道号
 */
@Service
public class BasicEstateStreetInfoService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateStreetInfoDao basicEstateStreetInfoDao;
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
    public BasicEstateStreetInfo getBasicEstateStreetInfoById(Integer id) throws Exception {
        return basicEstateStreetInfoDao.getBasicEstateStreetInfoById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicEstateStreetInfo
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicEstateStreetInfo(BasicEstateStreetInfo basicEstateStreetInfo, boolean updateNull) throws Exception {
        if (basicEstateStreetInfo.getId() == null || basicEstateStreetInfo.getId().intValue() == 0) {
            basicEstateStreetInfo.setCreator(commonService.thisUserAccount());
            Integer id = basicEstateStreetInfoDao.saveBasicEstateStreetInfo(basicEstateStreetInfo);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicEstateStreetInfo.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicEstateStreetInfo estateStreetInfo = basicEstateStreetInfoDao.getBasicEstateStreetInfoById(basicEstateStreetInfo.getId());
                if (estateStreetInfo != null) {
                    basicEstateStreetInfo.setBisDelete(estateStreetInfo.getBisDelete());
                    basicEstateStreetInfo.setCreator(estateStreetInfo.getCreator());
                    basicEstateStreetInfo.setGmtCreated(estateStreetInfo.getGmtCreated());
                    basicEstateStreetInfo.setGmtModified(DateUtils.now());
                }
            }
            basicEstateStreetInfoDao.updateBasicEstateStreetInfo(basicEstateStreetInfo, updateNull);
            return basicEstateStreetInfo.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicEstateStreetInfo(Integer id) throws Exception {
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(id);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicEstateStreetInfo.class));
        boolean flag = basicEstateStreetInfoDao.deleteBasicEstateStreetInfo(id);
        baseAttachmentService.deleteAttachmentByDto(sysAttachmentDto);
        return flag;
    }

    /**
     * 获取数据列表
     *
     * @param basicEstateStreetInfo
     * @return
     * @throws Exception
     */
    public List<BasicEstateStreetInfo> basicEstateStreetInfoList(BasicEstateStreetInfo basicEstateStreetInfo) {
        return basicEstateStreetInfoDao.basicEstateStreetInfoList(basicEstateStreetInfo);
    }

    public List<BasicEstateStreetInfo> getStreetInfoListByEstateId(Integer estateId) {
        if (estateId == null) return null;
        BasicEstateStreetInfo where = new BasicEstateStreetInfo();
        where.setEstateId(estateId);
        return basicEstateStreetInfoDao.basicEstateStreetInfoList(where);
    }


    public BootstrapTableVo getBootstrapTableVo(BasicEstateStreetInfo basicEstateStreetInfo) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstateStreetInfo> basicEstateStreetInfoList = basicEstateStreetInfoDao.basicEstateStreetInfoList(basicEstateStreetInfo);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicEstateStreetInfoList) ? new ArrayList<BasicEstateStreetInfo>(10) : basicEstateStreetInfoList);
        return vo;
    }


}
