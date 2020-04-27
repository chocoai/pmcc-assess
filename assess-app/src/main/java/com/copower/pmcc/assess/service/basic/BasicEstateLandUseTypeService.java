package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicEstateLandUseTypeDao;
import com.copower.pmcc.assess.dal.basis.entity.BasicEstateLandUseType;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BasicEstateLandUseTypeService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BasicEstateLandUseTypeDao basicEstateLandUseTypeDao;
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
    public BasicEstateLandUseType getBasicEstateLandUseTypeById(Integer id) throws Exception {
        return basicEstateLandUseTypeDao.getBasicEstateLandUseTypeById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicEstateLandUseType
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicEstateLandUseType(BasicEstateLandUseType basicEstateLandUseType, boolean updateNull) throws Exception {
        if (basicEstateLandUseType.getId() == null || basicEstateLandUseType.getId().intValue() == 0) {
            basicEstateLandUseType.setCreator(commonService.thisUserAccount());
            Integer id = basicEstateLandUseTypeDao.saveBasicEstateLandUseType(basicEstateLandUseType);
            return id;
        } else {
            if (updateNull) {
                BasicEstateLandUseType estateLandUseType = basicEstateLandUseTypeDao.getBasicEstateLandUseTypeById(basicEstateLandUseType.getId());
                if (estateLandUseType != null) {
                    basicEstateLandUseType.setBisDelete(estateLandUseType.getBisDelete());
                    basicEstateLandUseType.setCreator(estateLandUseType.getCreator());
                    basicEstateLandUseType.setGmtCreated(estateLandUseType.getGmtCreated());
                    basicEstateLandUseType.setGmtModified(DateUtils.now());
                }
            }
            basicEstateLandUseTypeDao.updateBasicEstateLandUseType(basicEstateLandUseType, updateNull);
            return basicEstateLandUseType.getId();
        }
    }


    public BasicEstateLandUseType getDataByType(String landUseType, Integer estateId) {
        BasicEstateLandUseType basicEstateLandUseType = new BasicEstateLandUseType();
        basicEstateLandUseType.setLandUseType(landUseType);
        basicEstateLandUseType.setEstateId(estateId);
        basicEstateLandUseType.setBisDelete(false);
        List<BasicEstateLandUseType> basicEstateLandUseTypes = basicEstateLandUseTypeDao.basicEstateLandUseTypeList(basicEstateLandUseType);
        if (CollectionUtils.isNotEmpty(basicEstateLandUseTypes)) {
            return basicEstateLandUseTypes.get(0);
        }
        return null;
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBasicEstateLandUseType(Integer id) throws Exception {
        return basicEstateLandUseTypeDao.deleteBasicEstateLandUseType(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicEstateLandUseType
     * @return
     * @throws Exception
     */
    public List<BasicEstateLandUseType> basicEstateLandUseTypeList(BasicEstateLandUseType basicEstateLandUseType) {
        return basicEstateLandUseTypeDao.basicEstateLandUseTypeList(basicEstateLandUseType);
    }


    public BootstrapTableVo getBootstrapTableVo(BasicEstateLandUseType basicEstateLandUseType) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstateLandUseType> basicEstateLandUseTypeList = basicEstateLandUseTypeDao.basicEstateLandUseTypeList(basicEstateLandUseType);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicEstateLandUseTypeList) ? new ArrayList<BasicEstateLandUseType>(10) : basicEstateLandUseTypeList);
        return vo;
    }


}
