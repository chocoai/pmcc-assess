package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicUnitElevatorDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicUnitElevator;
import com.copower.pmcc.assess.dto.output.basic.BasicUnitElevatorVo;
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
 * @Date: 2018/11/5 16:17
 * @Description:配备电梯
 */
@Service
public class BasicUnitElevatorService {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicUnitElevatorDao basicUnitElevatorDao;
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
    public BasicUnitElevator getBasicUnitElevatorById(Integer id) throws Exception {
        return basicUnitElevatorDao.getBasicUnitElevatorById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicUnitElevator
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicUnitElevator(BasicUnitElevator basicUnitElevator, boolean updateNull) throws Exception {
        if (basicUnitElevator.getId() == null || basicUnitElevator.getId().intValue() == 0) {
            basicUnitElevator.setCreator(commonService.thisUserAccount());
            Integer id = basicUnitElevatorDao.addBasicUnitElevator(basicUnitElevator);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class), id);
            return  id ;
        } else {
            if(updateNull){
                BasicUnitElevator unitElevator = basicUnitElevatorDao.getBasicUnitElevatorById(basicUnitElevator.getId());
                if(unitElevator!=null){
                    basicUnitElevator.setBisDelete(unitElevator.getBisDelete());
                    basicUnitElevator.setCreator(unitElevator.getCreator());
                    basicUnitElevator.setGmtCreated(unitElevator.getGmtCreated());
                    basicUnitElevator.setGmtModified(DateUtils.now());
                }
            }
            basicUnitElevatorDao.updateBasicUnitElevator(basicUnitElevator,updateNull);
            return basicUnitElevator.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicUnitElevator(Integer id) throws Exception {
        return basicUnitElevatorDao.deleteBasicUnitElevator(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicUnitElevator
     * @return
     * @throws Exception
     */
    public List<BasicUnitElevator> basicUnitElevatorList(BasicUnitElevator basicUnitElevator) throws Exception {
        return basicUnitElevatorDao.basicUnitElevatorList(basicUnitElevator);
    }

    public List<BasicUnitElevator> getBasicUnitElevatorList(Integer unitId){
        BasicUnitElevator where=new BasicUnitElevator();
        where.setUnitId(unitId);
        return basicUnitElevatorDao.basicUnitElevatorList(where);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicUnitElevator basicUnitElevator) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicUnitElevator> basicUnitElevatorList = basicUnitElevatorDao.basicUnitElevatorList(basicUnitElevator);
        List<BasicUnitElevatorVo> vos = Lists.newArrayList();
        basicUnitElevatorList.forEach(oo -> vos.add(getBasicUnitElevatorVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicUnitElevatorVo>(10) : vos);
        return vo;
    }

    public BasicUnitElevatorVo getBasicUnitElevatorVo(BasicUnitElevator basicUnitElevator){
        if (basicUnitElevator==null){
            return null;
        }
        BasicUnitElevatorVo vo = new BasicUnitElevatorVo();
        BeanUtils.copyProperties(basicUnitElevator,vo);
        vo.setTypeName(baseDataDicService.getNameById(basicUnitElevator.getType()));
        vo.setMaintenanceName(baseDataDicService.getNameById(basicUnitElevator.getMaintenance()));
        vo.setCreatorName(publicService.getUserNameByAccount(basicUnitElevator.getCreator()));
        List<SysAttachmentDto> sysAttachmentDtos = baseAttachmentService.getByField_tableId(basicUnitElevator.getId(), null, FormatUtils.entityNameConvertToTableName(BasicUnitElevator.class));
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
