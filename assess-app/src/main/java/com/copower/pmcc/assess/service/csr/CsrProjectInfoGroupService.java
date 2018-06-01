package com.copower.pmcc.assess.service.csr;

import com.copower.pmcc.assess.dal.dao.csr.CsrBorrowerDao;
import com.copower.pmcc.assess.dal.dao.csr.CsrProjectInfoGroupDao;
import com.copower.pmcc.assess.dal.entity.CsrBorrower;
import com.copower.pmcc.assess.dal.entity.CsrProjectInfoGroup;
import com.copower.pmcc.assess.dto.input.project.csr.CsrProjectInfoGroupSubmitDto;
import com.copower.pmcc.assess.dto.output.project.csr.CsrProjectInfoGroupVo;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcUserService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目组
 */

@Service
public class CsrProjectInfoGroupService {


    @Autowired
    private CsrProjectInfoGroupDao projectInfoGroupDao;

    @Autowired
    private CsrBorrowerDao csrBorrowerDao;

    @Lazy
    @Autowired
    private ErpRpcUserService erpRpcUserService;


    @Autowired
    private CommonService commonService;

    @Transactional
    public boolean saveAndUpdate(CsrProjectInfoGroup csrProjectInfoGroup) {
        csrProjectInfoGroup.setCreator(commonService.thisUserAccount());
        if (ObjectUtils.isEmpty(csrProjectInfoGroup.getId())){
            return projectInfoGroupDao.add(csrProjectInfoGroup);
        }else {
           return projectInfoGroupDao.update(csrProjectInfoGroup);
        }
    }

    @Transactional
    public boolean delete(Integer id){
        return projectInfoGroupDao.removeByID(id);
    }

    public CsrProjectInfoGroupVo getByID(Integer id){
        return change(projectInfoGroupDao.getByID(id));
    }


    @Transactional
    public void submitGroup(CsrProjectInfoGroupSubmitDto submitDto){
        String[] ids = submitDto.getCsrBorrowerIDS().split(",");
        for (String id:ids){
            if (!StringUtils.isEmpty(id)){
                CsrBorrower csrBorrower = csrBorrowerDao.getCsrBorrowerByID(Integer.parseInt(id));
                csrBorrower.setProjectId(submitDto.getCsrProjectId());
                csrBorrower.setGroupId(submitDto.getCsrProjectInfoGroupID());
                csrBorrowerDao.update(csrBorrower);
            }
        }
    }

    @Transactional(readOnly = true)
    public List<CsrProjectInfoGroup> groupList(Integer projectID,String projectName) {
        return projectInfoGroupDao.groupList(projectID,projectName);
    }

    public BootstrapTableVo groupVoList(Integer projectID,String projectName) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<CsrProjectInfoGroup> csrProjectInfoGroups = groupList(projectID,projectName);
        List<CsrProjectInfoGroupVo> vos =  new ArrayList<>();
        for (CsrProjectInfoGroup csrProjectInfoGroup:csrProjectInfoGroups){
            vos.add(change(csrProjectInfoGroup));
        }
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<CsrProjectInfoGroupVo>() : vos);
        vo.setTotal(page.getTotal());
        return vo;
    }

    public CsrProjectInfoGroupVo change(CsrProjectInfoGroup csrProjectInfoGroup){
        CsrProjectInfoGroupVo vo = new CsrProjectInfoGroupVo();
        BeanUtils.copyProperties(csrProjectInfoGroup,vo);
        vo.setProjectManagerName(erpRpcUserService.getSysUser(csrProjectInfoGroup.getProjectManager()).getUserName());
        String[] members = csrProjectInfoGroup.getProjectMember().split(",");
        StringBuilder builder = new StringBuilder(1024);
        for (int i = 0; i < members.length; i++) {
            if (i==members.length-1){
                builder.append(erpRpcUserService.getSysUser(members[i]).getUserName());
            }else {
                builder.append(erpRpcUserService.getSysUser(members[i]).getUserName()+",");
            }
        }
        vo.setProjectMemberName(builder.toString());
        return vo;
    }
}
