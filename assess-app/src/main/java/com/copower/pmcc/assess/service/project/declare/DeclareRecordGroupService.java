package com.copower.pmcc.assess.service.project.declare;

import com.copower.pmcc.assess.dal.basis.dao.project.declare.DeclareRecordGroupDao;
import com.copower.pmcc.assess.dal.basis.entity.DeclareRecordGroup;
import com.copower.pmcc.assess.dto.output.project.declare.DeclareRecordGroupVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zch on 2019-10-14.
 * 资产申报 分组
 */
@Service
public class DeclareRecordGroupService {

    @Autowired
    private DeclareRecordGroupDao declareRecordGroupDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;

    public boolean saveDeclareRecordGroup(DeclareRecordGroup DeclareRecordGroup)  {
        if (DeclareRecordGroup == null) {
            return false;
        }
        if (DeclareRecordGroup.getId() != null && DeclareRecordGroup.getId() != 0) {
            return declareRecordGroupDao.updateDeclareRecordGroup(DeclareRecordGroup);
        } else {
            DeclareRecordGroup.setCreator(commonService.thisUserAccount());
            declareRecordGroupDao.addDeclareRecordGroup(DeclareRecordGroup);
            return true;
        }
    }

    public BootstrapTableVo getBootstrapTableVo(DeclareRecordGroup oo) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DeclareRecordGroup> childrenList = getDeclareRecordGroupListByExample(oo);
        vo.setTotal(page.getTotal());
        vo.setRows(childrenList);
        return vo;
    }


    public DeclareRecordGroup getDeclareRecordGroupById(Integer id) {
        return declareRecordGroupDao.getDeclareRecordGroupById(id);
    }

    public boolean deleteDeclareRecordGroupById(Integer id) {
        return declareRecordGroupDao.deleteDeclareRecordGroupById(id);
    }

    public void deleteDeclareRecordGroup(String id) {
        if (StringUtils.isEmpty(id)) {
            return;
        }
        declareRecordGroupDao.deleteDeclareRecordGroup(FormatUtils.transformString2Integer(id));
    }

    public List<DeclareRecordGroup> getDeclareRecordGroupListByExample(DeclareRecordGroup oo) {
        return declareRecordGroupDao.getDeclareRecordGroupListByExample(oo);
    }

    public DeclareRecordGroupVo getDeclareRecordGroupVo(DeclareRecordGroup group){
        if (group == null){
            return null;
        }
        DeclareRecordGroupVo vo = new DeclareRecordGroupVo();
        BeanUtils.copyProperties(group,vo);
        if (org.apache.commons.lang.StringUtils.isNotBlank(group.getProvince())) {
            if (NumberUtils.isNumber(group.getProvince())) {
                //省
                vo.setProvinceName(erpAreaService.getSysAreaName(group.getProvince()));
            } else {
                //省
                vo.setProvinceName(group.getProvince());
            }
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(group.getCity())) {
            if (NumberUtils.isNumber(group.getCity())) {
                //市或者县
                vo.setCityName(erpAreaService.getSysAreaName(group.getCity()));
            } else {
                vo.setCityName(group.getCity());
            }
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(group.getDistrict())) {
            if (NumberUtils.isNumber(group.getDistrict())) {
                //县
                vo.setDistrictName(erpAreaService.getSysAreaName(group.getDistrict()));
            } else {
                vo.setDistrictName(group.getDistrict());
            }
        }
        return vo;
    }


}
