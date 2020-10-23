package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicBuildingFunctionDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicBuildingFunction;
import com.copower.pmcc.assess.dto.output.basic.BasicBuildingFunctionVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
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
 * @Date: 2018/10/30 11:29
 * @Description:楼栋建筑功能
 */
@Service
public class BasicBuildingFunctionService {
    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicBuildingFunctionDao basicBuildingFunctionDao;
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
    public BasicBuildingFunction getBasicBuildingFunctionById(Integer id) throws Exception {
        return basicBuildingFunctionDao.getBasicBuildingFunctionById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicBuildingFunction
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicBuildingFunction(BasicBuildingFunction basicBuildingFunction, boolean updateNull) throws Exception {
        if (basicBuildingFunction.getId() == null || basicBuildingFunction.getId().intValue() == 0) {
            basicBuildingFunction.setCreator(commonService.thisUserAccount());
            Integer id = basicBuildingFunctionDao.saveBasicBuildingFunction(basicBuildingFunction);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BasicBuildingFunction.class), id);
            return id;
        } else {
            if (updateNull) {
                BasicBuildingFunction buildingFunction = basicBuildingFunctionDao.getBasicBuildingFunctionById(basicBuildingFunction.getId());
                if (buildingFunction != null) {
                    basicBuildingFunction.setBisDelete(buildingFunction.getBisDelete());
                    basicBuildingFunction.setCreator(buildingFunction.getCreator());
                    basicBuildingFunction.setGmtCreated(buildingFunction.getGmtCreated());
                    basicBuildingFunction.setGmtModified(DateUtils.now());
                }
            }
            basicBuildingFunctionDao.updateBasicBuildingFunction(basicBuildingFunction, updateNull);
            return basicBuildingFunction.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicBuildingFunction(Integer id) throws Exception {
        return basicBuildingFunctionDao.deleteBasicBuildingFunction(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicBuildingFunction
     * @return
     * @throws Exception
     */
    public List<BasicBuildingFunction> basicBuildingFunctionList(BasicBuildingFunction basicBuildingFunction) throws Exception {
        return basicBuildingFunctionDao.basicBuildingFunctionList(basicBuildingFunction);
    }

    public void removeBasicBuildingFunction(BasicBuildingFunction basicBuildingFunction) throws Exception {
        basicBuildingFunctionDao.removeBasicBuildingFunction(basicBuildingFunction);
    }

    public List<BasicBuildingFunction> getBasicBuildingFunctionList(Integer buildingId) {
        if (buildingId == null) return null;
        BasicBuildingFunction where = new BasicBuildingFunction();
        where.setBuildingId(buildingId);
        return basicBuildingFunctionDao.basicBuildingFunctionList(where);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicBuildingFunction basicBuildingFunction) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicBuildingFunction> basicBuildingFunctionList = basicBuildingFunctionDao.basicBuildingFunctionList(basicBuildingFunction);
        List<BasicBuildingFunctionVo> vos = Lists.newArrayList();
        basicBuildingFunctionList.forEach(oo -> vos.add(getBasicBuildingFunctionVo(oo)));
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(vos) ? new ArrayList<BasicBuildingFunctionVo>(10) : vos);
        return vo;
    }

    public BasicBuildingFunctionVo getBasicBuildingFunctionVo(BasicBuildingFunction basicBuildingFunction) {
        if (basicBuildingFunction == null) {
            return null;
        }
        BasicBuildingFunctionVo vo = new BasicBuildingFunctionVo();
        BeanUtils.copyProperties(basicBuildingFunction, vo);
        if (basicBuildingFunction.getType() != null) {
            vo.setTypeName(baseDataDicService.getNameById(basicBuildingFunction.getType()));
        }
        if (basicBuildingFunction.getDecorationPart() != null) {
            vo.setDecorationPartName(baseDataDicService.getNameById(basicBuildingFunction.getDecorationPart()));
        }
        vo.setCreatorName(publicService.getUserNameByAccount(basicBuildingFunction.getCreator()));
        return vo;
    }
}
