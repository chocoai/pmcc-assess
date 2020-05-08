package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitStairsDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitStairs;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitStairsVo;
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
 * @Description:单元 单元楼梯信息
 */
@Service
public class BasicUnitStairsService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicUnitStairsDao basicUnitStairsDao;
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
    public BasicUnitStairs getBasicUnitStairsById(Integer id) throws Exception {
        return basicUnitStairsDao.getBasicUnitStairsById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicUnitStairs
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicUnitStairs(BasicUnitStairs basicUnitStairs, boolean updateNull) throws Exception {
        if (basicUnitStairs.getId() == null || basicUnitStairs.getId().intValue() == 0) {
            basicUnitStairs.setCreator(commonService.thisUserAccount());
            Integer id = basicUnitStairsDao.saveBasicUnitStairs(basicUnitStairs);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicUnitStairs.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicUnitStairs estateStreetInfo = basicUnitStairsDao.getBasicUnitStairsById(basicUnitStairs.getId());
                if (estateStreetInfo != null) {
                    basicUnitStairs.setBisDelete(estateStreetInfo.getBisDelete());
                    basicUnitStairs.setCreator(estateStreetInfo.getCreator());
                    basicUnitStairs.setGmtCreated(estateStreetInfo.getGmtCreated());
                    basicUnitStairs.setGmtModified(DateUtils.now());
                }
            }
            basicUnitStairsDao.updateBasicUnitStairs(basicUnitStairs, updateNull);
            return basicUnitStairs.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicUnitStairs(Integer id) throws Exception {
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(id);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BasicUnitStairs.class));
        boolean flag = basicUnitStairsDao.deleteBasicUnitStairs(id);
        baseAttachmentService.deleteAttachmentByDto(sysAttachmentDto);
        return flag;
    }

    /**
     * 获取数据列表
     *
     * @param basicUnitStairs
     * @return
     * @throws Exception
     */
    public List<BasicUnitStairs> basicUnitStairsList(BasicUnitStairs basicUnitStairs) {
        return basicUnitStairsDao.basicUnitStairsList(basicUnitStairs);
    }


    public BootstrapTableVo getBootstrapTableVo(BasicUnitStairs basicUnitStairs) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicUnitStairs> basicUnitStairsList = basicUnitStairsDao.basicUnitStairsList(basicUnitStairs);
        List<BasicUnitStairsVo> vos = Lists.newArrayList();
        basicUnitStairsList.forEach(oo -> vos.add(getBasicUnitStairsVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicUnitStairsVo>(10) : vos);
        return vo;
    }

    public BasicUnitStairsVo getBasicUnitStairsVo(BasicUnitStairs basicUnitStairs) {
        if (basicUnitStairs == null) {
            return null;
        }
        BasicUnitStairsVo vo = new BasicUnitStairsVo();
        BeanUtils.copyProperties(basicUnitStairs, vo);
        vo.setCreatorName(publicService.getUserNameByAccount(basicUnitStairs.getCreator()));
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(basicUnitStairs.getId(), null, FormatUtils.entityNameConvertToTableName(BasicUnitStairs.class));
        StringBuilder builder = new StringBuilder();
        if (!ObjectUtils.isEmpty(sysAttachmentDtos)) {
            for (SysAttachmentDto sysAttachmentDto : sysAttachmentDtos) {
                builder.append(baseAttachmentService.getViewHtml(sysAttachmentDto)).append(" ");
            }
            vo.setFileViewName(builder.toString());
        }
        return vo;
    }
}
