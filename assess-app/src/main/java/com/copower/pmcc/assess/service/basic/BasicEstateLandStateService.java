package com.copower.pmcc.assess.service.basic;

import com.copower.pmcc.assess.dal.basic.dao.BasicEstateLandStateDao;
import com.copower.pmcc.assess.dal.basic.entity.BasicEstateLandState;
import com.copower.pmcc.assess.dal.basis.entity.BaseDataDic;
import com.copower.pmcc.assess.dal.basis.entity.DataLandLevelDetail;
import com.copower.pmcc.assess.dto.output.basic.BasicEstateLandStateVo;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.assess.service.data.DataLandLevelDetailService;
import com.copower.pmcc.assess.service.data.DataLandLevelService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
 * @Date: 2018/10/24 15:29
 * @Description:案例基础数据
 */
@Service
public class BasicEstateLandStateService {

    @Autowired
    private CommonService commonService;
    @Autowired
    private BasicEstateLandStateDao basicEstateLandStateDao;
    @Autowired
    private DataLandLevelService dataLandLevelService;
    @Autowired
    private DataLandLevelDetailService dataLandLevelDetailService;
    @Autowired
    private BaseDataDicService baseDataDicService;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BasicEstateLandState getBasicEstateLandStateById(Integer id) throws Exception {
        return basicEstateLandStateDao.getBasicEstateLandStateById(id);
    }

    /**
     * 新增或者修改
     *
     * @param basicEstateLandState
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBasicEstateLandState(BasicEstateLandState basicEstateLandState) throws Exception {
        if (basicEstateLandState.getId() == null || basicEstateLandState.getId().intValue() == 0) {
            return basicEstateLandStateDao.saveBasicEstateLandState(basicEstateLandState);
        } else {
            basicEstateLandStateDao.updateBasicEstateLandState(basicEstateLandState);
            return null;
        }
    }

    public Integer upgradeVersion(BasicEstateLandState basicEstateLandState) throws Exception {
        if (basicEstateLandState.getId() == null || basicEstateLandState.getId().intValue() == 0) {
            basicEstateLandState.setCreator(commonService.thisUserAccount());
            if (basicEstateLandState.getVersion() == null) {
                basicEstateLandState.setVersion(0);
            }
            Integer id = basicEstateLandStateDao.saveBasicEstateLandState(basicEstateLandState);
            return id;
        } else {
            BasicEstateLandState oo = basicEstateLandStateDao.getBasicEstateLandStateById(basicEstateLandState.getId());
            if (oo.getVersion() == null) {
                oo.setVersion(0);
            }
            basicEstateLandState.setVersion(oo.getVersion() + 1);
            basicEstateLandStateDao.updateBasicEstateLandState(basicEstateLandState);
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
    public boolean deleteBasicEstateLandState(Integer id) throws Exception {
        return basicEstateLandStateDao.deleteBasicEstateLandState(id);
    }

    /**
     * 获取数据列表
     *
     * @param basicEstateLandState
     * @return
     * @throws Exception
     */
    public List<BasicEstateLandState> basicEstateLandStateList(BasicEstateLandState basicEstateLandState) throws Exception {
        return basicEstateLandStateDao.basicEstateLandStateList(basicEstateLandState);
    }

    public BootstrapTableVo getBootstrapTableVo(BasicEstateLandState basicEstateLandState) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BasicEstateLandState> basicEstateLandStateList = basicEstateLandStateDao.basicEstateLandStateList(basicEstateLandState);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(basicEstateLandStateList) ? new ArrayList<BasicEstateLandState>(10) : basicEstateLandStateList);
        return vo;
    }

    public BasicEstateLandStateVo getBasicEstateLandStateVo(BasicEstateLandState basicEstateLandState) {
        if (basicEstateLandState == null) {
            return null;
        }
        BasicEstateLandStateVo vo = new BasicEstateLandStateVo();
        BaseDataDic dataDic = null;
        BeanUtils.copyProperties(basicEstateLandState, vo);
        if (basicEstateLandState.getLandUseType() != null) {
            dataDic = baseDataDicService.getDataDicById(basicEstateLandState.getLandUseType());
            if (dataDic != null) {
                vo.setLandUseTypeName(dataDic.getName());
                dataDic = null;
            }
        }
        if (basicEstateLandState.getLandUseCategory() != null) {
            dataDic = baseDataDicService.getDataDicById(basicEstateLandState.getLandUseCategory());
            if (dataDic != null) {
                vo.setLandUseCategoryName(dataDic.getName());
                dataDic = null;
            }
        }
        if (basicEstateLandState.getLandLevel() != null) {
            DataLandLevelDetail dataLandLevelDetail = dataLandLevelDetailService.getDataLandLevelDetailById(basicEstateLandState.getLandLevel());
            if (dataLandLevelDetail != null) {

            }
        }
        return vo;
    }

}
