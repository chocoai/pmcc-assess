package com.copower.pmcc.assess.proxy.face;

import com.copower.pmcc.assess.dto.output.BaseBussinessVo;
import com.copower.pmcc.bpm.api.dto.model.ApprovalModelDto;
import com.copower.pmcc.bpm.api.exception.BpmException;
import com.copower.pmcc.erp.common.exception.BusinessException;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述:人力资源类公共处理方法接口，包括页面和提交方法
 *
 * @author: Calvin(qiudong@copowercpa.com)
 * @data: 2017/11/17
 * @time: 14:51
 * @ProjectTaskAnnotation(desc = "xxx")
 * @Component 如果不标注此注解，页面配置不能查找到您自定义服务类
 */
public interface BaseInterface {

    /**
     * 申请页面
     *
     * @return
     */
    ModelAndView applyView(Integer boxId, Integer phaseId, Integer ruId);

    /**
     * 审批页面
     *
     * @param processInsId       流程实例编号
     * @param taskId             任务编号
     * @param boxId              模型编号
     * @return
     */
    ModelAndView approvalView(String processInsId, Integer phaseId, String taskId, Integer boxId, String agentUserAccount);

    /**
     * 返回修改页面
     *
     * @param processInsId       流程实例编号
     * @param taskId             任务编号
     * @param boxId              模型编号
     * @return
     */
    ModelAndView editView(String processInsId, Integer phaseId, String taskId, Integer boxId, String agentUserAccount);

    /**
     * 详情页面
     *
     * @param processInsId       流程实例编号
     * @return
     */

    ModelAndView detailsView(String processInsId, Integer boxId);

    /**
     * 保存草稿     *
     * @param formData      表单数据
     */
    BaseBussinessVo saveDraft(String formData) throws BusinessException, BusinessException, BusinessException;

    /**
     * 申请结果提交     *
     * @param formData      表单数据
     */
    BaseBussinessVo applyCommit(String formData, String processInsId) throws BusinessException, BpmException;

    /**
     * 审批提交
     *
     * @param approvalModelDto 流程审批所属的变更包
     * @param formData         审批页面填写的数据
     */
    void approvalCommit(ApprovalModelDto approvalModelDto, String formData) throws BusinessException;

    /**
     * 返回修改提交
     *
     * @param approvalModelDto 流程审批所属的变更包
     * @param formData         审批页面填写的数据
     */
    void editCommit(ApprovalModelDto approvalModelDto, String formData) throws BusinessException;
    /**
     * 关闭流程提交
     *
     * @param approvalModelDto 流程审批所属的变更包
     * @param formData         审批页面填写的数据
     */
    void closeCommit(ApprovalModelDto approvalModelDto, String formData) throws BusinessException;

}
