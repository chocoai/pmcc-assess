package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.dao.base.BaseQrcodeDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseQrcode;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatch;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.service.BaseService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.IpUtils;
import com.copower.pmcc.erp.constant.ApplicationConstant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/30 11:52
 * @Description:二维码记录表
 */
@Service
public class BaseQrcodeService extends BaseService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseQrcodeDao baseQrcodeDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private ApplicationConstant applicationConstant;

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BaseQrcode getBaseQrcodeById(Integer id) {
        return baseQrcodeDao.getBaseQrcodeById(id);
    }

    public BaseQrcode toImgQRCodePath(Integer tableId, String type) {
        BasicFormClassifyEnum estateTaggingTypeEnum = BasicFormClassifyEnum.getEnumByKey(type);
        List<String> stringList = FormatUtils.transformString2List(type, BasicFormClassifyEnum.transform(false));
        List<BaseQrcode> baseQrcodeList = getBaseQrcodeList(tableId, StringUtils.join(stringList, BasicFormClassifyEnum.transform(true)), estateTaggingTypeEnum.getTableName());
        if (CollectionUtils.isEmpty(baseQrcodeList)) {
            return null;
        }
        return getBaseQrcodeById(baseQrcodeList.get(0).getId());
    }

    /**
     * 生成查勘节点
     * @param basicApplyBatchDetail
     * @throws Exception
     */
    public void createQrCode(BasicApplyBatchDetail basicApplyBatchDetail) throws Exception {
        try {
            List<String> stringList = FormatUtils.transformString2List(basicApplyBatchDetail.getType(), BasicFormClassifyEnum.transform(false));
            BaseQrcode baseQrcode = new BaseQrcode();
            baseQrcode.setTableId(basicApplyBatchDetail.getTableId());
            baseQrcode.setTableName(basicApplyBatchDetail.getTableName());
            baseQrcode.setType(StringUtils.join(stringList, BasicFormClassifyEnum.transform(true)));
            List<BaseQrcode> baseQrcodeList = getBaseQrcodeList(baseQrcode.getTableId(), baseQrcode.getType(), baseQrcode.getTableName());
            if (CollectionUtils.isNotEmpty(baseQrcodeList)) {
                return;
            }
            StringBuilder stringBuilder = new StringBuilder(8);
            //这里在正式环境需要改变
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attr.getRequest();
            stringBuilder.append(IpUtils.serverPath(request)).append("/");
            stringBuilder.append(applicationConstant.getAppKey()).append("/basicApplyBatch/informationPhoneEdit").append("?");
            List<String> params = new ArrayList<>();
            params.add(String.join("=", "tbType", baseQrcode.getType()));
            params.add(String.join("=", "tbId", baseQrcode.getTableId().toString()));
            params.add(String.join("=", "tableName", baseQrcode.getTableName()));
            if (basicApplyBatchDetail.getApplyBatchId() != null) {
                BasicApplyBatch basicApplyBatch = basicApplyBatchService.getBasicApplyBatchById(basicApplyBatchDetail.getApplyBatchId());
                params.add(String.join("=", "applyBatchId", basicApplyBatch.getId().toString()));
                params.add(String.join("=", "formClassify", basicApplyBatch.getClassify().toString()));
                params.add(String.join("=", "formType", basicApplyBatch.getType().toString()));
                params.add(String.join("=", "planDetailsId", basicApplyBatch.getPlanDetailsId().toString()));
            }
            stringBuilder.append(StringUtils.join(params, "&"));
            String generateQRCode = erpRpcToolsService.generateQRCode(stringBuilder.toString());
            baseQrcode.setQrcode(generateQRCode);
            baseQrcode.setCode(stringBuilder.toString());
            saveAndUpdateBaseQrcode(baseQrcode, false);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * 生成查勘树
     * @param basicApplyBatch
     * @throws Exception
     */
    public void createQrCode(BasicApplyBatch basicApplyBatch) throws Exception {
        if (basicApplyBatch == null || basicApplyBatch.getId() == null){
            return;
        }
        try {
            BaseQrcode baseQrcode = new BaseQrcode();
            baseQrcode.setTableId(basicApplyBatch.getId());
            baseQrcode.setTableName(FormatUtils.entityNameConvertToTableName(BasicApplyBatch.class));
            List<BaseQrcode> baseQrcodeList = getBaseQrcodeList(basicApplyBatch.getId(), null, baseQrcode.getTableName());
            if (CollectionUtils.isNotEmpty(baseQrcodeList)) {
                return;
            }
            StringBuilder stringBuilder = new StringBuilder(8);
            //这里在正式环境需要改变
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attr.getRequest();
            stringBuilder.append(IpUtils.serverPath(request)).append("/");
            stringBuilder.append(applicationConstant.getAppKey()).append("/basicApplyBatch/informationPhoneTree").append("?");
            List<String> params = new ArrayList<>();
            params.add(String.join("=", "applyBatchId", basicApplyBatch.getId().toString()));
            stringBuilder.append(StringUtils.join(params, "&"));
            String generateQRCode = erpRpcToolsService.generateQRCode(stringBuilder.toString());
            baseQrcode.setQrcode(generateQRCode);
            baseQrcode.setCode(stringBuilder.toString());
            saveAndUpdateBaseQrcode(baseQrcode, false);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }


    /**
     * 新增或者修改
     *
     * @param baseQrcode
     * @return
     * @throws Exception
     */
    public Integer saveAndUpdateBaseQrcode(BaseQrcode baseQrcode, boolean updateNull) throws Exception {
        if (baseQrcode.getId() == null || baseQrcode.getId() == 0) {
            baseQrcode.setCreator(commonService.thisUserAccount());
            Integer id = baseQrcodeDao.saveBaseQrcode(baseQrcode);
            baseAttachmentService.updateTableIdByTableName(FormatUtils.entityNameConvertToTableName(BaseQrcode.class), id);
            return id;
        } else {
            if (updateNull) {
                BaseQrcode estateStreetInfo = baseQrcodeDao.getBaseQrcodeById(baseQrcode.getId());
                if (estateStreetInfo != null) {
                    baseQrcode.setCreator(estateStreetInfo.getCreator());
                    baseQrcode.setGmtCreated(estateStreetInfo.getGmtCreated());
                    baseQrcode.setGmtModified(DateUtils.now());
                }
            }
            baseQrcodeDao.updateBaseQrcode(baseQrcode, updateNull);
            return baseQrcode.getId();
        }
    }


    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public boolean deleteBaseQrcode(Integer id) {
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(id);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BaseQrcode.class));
        boolean flag = baseQrcodeDao.deleteBaseQrcode(id);
        baseAttachmentService.deleteAttachmentByDto(sysAttachmentDto);
        return flag;
    }

    public List<BaseQrcode> getBaseQrcodeList(Integer tableId, String type, String tableName) {
        return baseQrcodeDao.getBaseQrcodeList(tableId, type, tableName);
    }

    /**
     * 获取数据列表
     *
     * @param baseQrcode
     * @return
     * @throws Exception
     */
    public List<BaseQrcode> baseQrcodeList(BaseQrcode baseQrcode) {
        return baseQrcodeDao.baseQrcodeList(baseQrcode);
    }


    public BootstrapTableVo getBootstrapTableVo(BaseQrcode baseQrcode) throws Exception {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<BaseQrcode> baseQrcodeList = baseQrcodeDao.baseQrcodeList(baseQrcode);
        vo.setTotal(page.getTotal());
        vo.setRows(ObjectUtils.isEmpty(baseQrcodeList) ? new ArrayList<BaseQrcode>(10) : baseQrcodeList);
        return vo;
    }


}
