package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.data.SysFeedbackDao;
import com.copower.pmcc.assess.dal.basis.entity.SysFeedback;
import com.copower.pmcc.assess.dto.output.data.SysFeedbackVo;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.assess.service.base.BaseDataDicService;
import com.copower.pmcc.bpm.core.process.ProcessControllerComponent;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SysFeedbackService {
    @Autowired
    private SysFeedbackDao sysFeedbackDao;
    @Autowired
    private ProcessControllerComponent processControllerComponent;
    @Autowired
    private PublicService publicService;
    @Autowired
    private BaseDataDicService baseDataDicService;

    public SysFeedbackVo getBySysFeedbackId(Integer id) {
        SysFeedback object = sysFeedbackDao.getSingleObject(id);
        return getSysFeedbackVo(object);
    }

    /**
     * 获取列表
     *
     * @return
     */
    public BootstrapTableVo getListVos(Integer systemType, String questionTitle, String feedbackPerson, Integer status, String creator) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        ArrayList<SysFeedbackVo> vos = new ArrayList<>();
        List<SysFeedback> sysFeedbackList = sysFeedbackDao.getSysFeedbackList(systemType, questionTitle, feedbackPerson, status, creator);
        if (CollectionUtils.isNotEmpty(sysFeedbackList)) {
            for (SysFeedback item : sysFeedbackList) {
                vos.add(getSysFeedbackVo(item));
            }
        }
        vo.setTotal(page.getTotal());
        vo.setRows(CollectionUtils.isEmpty(vos) ? new ArrayList<SysFeedbackVo>() : vos);
        return vo;
    }


    public SysFeedbackVo getSysFeedbackVo(SysFeedback sysFeedback) {
        if (sysFeedback == null) return null;
        SysFeedbackVo sysFeedbackVo = new SysFeedbackVo();
        BeanUtils.copyProperties(sysFeedback, sysFeedbackVo);
        if (sysFeedback.getSystemType() != null) {
            sysFeedbackVo.setSystemTypeName(baseDataDicService.getNameById(sysFeedback.getSystemType()));
        }
        if (sysFeedback.getUrgencyLevel() != null) {
            sysFeedbackVo.setUrgencyLevelName(baseDataDicService.getNameById(sysFeedback.getUrgencyLevel()));

        }
        if (sysFeedback.getStatus() == 0) {
            sysFeedbackVo.setStatusName("新添加");
        }
        if (sysFeedback.getStatus() == 1) {
            sysFeedbackVo.setStatusName("待处理");
        }
        if (sysFeedback.getStatus() == 2) {
            sysFeedbackVo.setStatusName("已处理");
        }
        if (StringUtils.isNotEmpty(sysFeedback.getCreator())) {
            sysFeedbackVo.setFeedbackPersonName(publicService.getUserNameByAccount(sysFeedback.getFeedbackPerson()));
        }
        return sysFeedbackVo;
    }

    /**
     * 保存
     *
     * @param sysFeedback
     * @throws BusinessException
     */
    public boolean addSysFeedbackReturnId(SysFeedback sysFeedback) {
        sysFeedback.setCreator(processControllerComponent.getThisUser());
        return sysFeedbackDao.addObject(sysFeedback);
    }

    public boolean updateSysFeedback(SysFeedback sysFeedback) {
        return sysFeedbackDao.updateObject(sysFeedback);
    }

    /**
     * 删除
     *
     * @param id
     * @throws BusinessException
     */
    public boolean deleteSysFeedback(Integer id) throws BusinessException {
        return sysFeedbackDao.deleteObject(id);
    }

}
