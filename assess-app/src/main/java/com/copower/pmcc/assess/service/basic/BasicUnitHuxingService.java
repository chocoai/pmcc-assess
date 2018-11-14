package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicUnitHuxingDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicUnitHuxing;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitHuxingVo;
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
import org.apache.commons.lang3.math.NumberUtils;
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
 * @Date: 2018/11/5 16:13
 * @Description:户型
 */
@Service
public class BasicUnitHuxingService {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicUnitHuxingDao basicUnitHuxingDao;
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
    public BasicUnitHuxing getBasicUnitHuxingById(Integer id) throws Exception {
        return basicUnitHuxingDao.getBasicUnitHuxingById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicUnitHuxing
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicUnitHuxing(BasicUnitHuxing basicUnitHuxing) throws Exception {
        if (basicUnitHuxing.getId() == null || basicUnitHuxing.getId().intValue() == 0) {
            basicUnitHuxing.setCreator(commonService.thisUserAccount());
            Integer id = basicUnitHuxingDao.saveBasicUnitHuxing(basicUnitHuxing);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class), id);
            return id;
        } else {
            BasicUnitHuxing oo = basicUnitHuxingDao.getBasicUnitHuxingById(basicUnitHuxing.getId());
            basicUnitHuxingDao.updateBasicUnitHuxing(basicUnitHuxing);
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
    public boolean deleteBasicUnitHuxing(Integer id) throws Exception {
        return basicUnitHuxingDao.deleteBasicUnitHuxing(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicUnitHuxing
     * @return
     * @throws Exception
     */
    public List<BasicUnitHuxing> basicUnitHuxingList(BasicUnitHuxing basicUnitHuxing) throws Exception {
        return basicUnitHuxingDao.basicUnitHuxingList(basicUnitHuxing);
    }

    public void removeBasicUnitHuxing(BasicUnitHuxing basicUnitHuxing) throws Exception {
        basicUnitHuxingDao.removeBasicUnitHuxing(basicUnitHuxing);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicUnitHuxing basicUnitHuxing) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicUnitHuxing> basicUnitHuxingList = basicUnitHuxingDao.basicUnitHuxingList(basicUnitHuxing);
        List<BasicUnitHuxingVo> vos = Lists.newArrayList();
        basicUnitHuxingList.forEach(oo -> vos.add(getBasicUnitHuxingVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicUnitHuxingVo>(10) : vos);
        return vo;
    }

    public BasicUnitHuxingVo getBasicUnitHuxingVo(BasicUnitHuxing basicUnitHuxing) {
        if (basicUnitHuxing == null) {
            return null;
        }
        BasicUnitHuxingVo vo = new BasicUnitHuxingVo();
        BeanUtils.copyProperties(basicUnitHuxing, vo);
        BaseDataDic dataDic = null;
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(basicUnitHuxing.getId(), null, FormatUtils.entityNameConvertToTableName(BasicUnitHuxing.class));
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
            vo.setFileViewName(builder.toString());
        }
        vo.setHouseLayoutName(baseDataDicService.getNameById(basicUnitHuxing.getHouseLayout()));
        vo.setOrientationName(baseDataDicService.getNameById(NumberUtils.isNumber(basicUnitHuxing.getOrientation())?Integer.parseInt(basicUnitHuxing.getOrientation()):null));
        return vo;
    }
}
