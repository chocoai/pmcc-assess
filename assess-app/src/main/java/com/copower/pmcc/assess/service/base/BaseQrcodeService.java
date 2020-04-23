package com.copower.pmcc.assess.service.base;

import com.copower.pmcc.assess.common.enums.basic.BasicFormClassifyEnum;
import com.copower.pmcc.assess.dal.basis.dao.base.BaseQrcodeDao;
import com.copower.pmcc.assess.dal.basis.entity.BaseQrcode;
import com.copower.pmcc.assess.dal.basis.entity.BasicApplyBatchDetail;
import com.copower.pmcc.assess.service.PublicService;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.api.provider.ErpRpcToolsService;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/10/30 11:52
 * @Description:二维码记录表
 */
@Service
public class BaseQrcodeService {

    @Autowired
    private BaseAttachmentService baseAttachmentService;
    @Autowired
    private BaseQrcodeDao baseQrcodeDao;
    @Autowired
    private BaseDataDicService baseDataDicService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;
    @Autowired
    private ErpRpcToolsService erpRpcToolsService;

    /**
     * 获取数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BaseQrcode getBaseQrcodeById(Integer id) throws Exception {
        return baseQrcodeDao.getBaseQrcodeById(id);
    }

    public void createQrCode(BasicApplyBatchDetail basicApplyBatchDetail)throws Exception {
        BaseQrcode baseQrcode = new BaseQrcode();
        baseQrcode.setTableId(basicApplyBatchDetail.getTableId());
        baseQrcode.setTableName(basicApplyBatchDetail.getTableName());
        baseQrcode.setType(basicApplyBatchDetail.getType());
        BasicFormClassifyEnum enumByKey = BasicFormClassifyEnum.getEnumByKey(basicApplyBatchDetail.getType());
        //必须和 com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService.saveAndUpdateComplete() 这的 switch 选择一致
        StringBuilder stringBuilder = new StringBuilder(8);
        stringBuilder.append("http://192.168.2.206/pmcc-assess/baseQrCode/informationPhoneEdit").append("?1=1");
        List<String> params = new ArrayList<>();
        params.add(String.join("=","a","1")) ;
        stringBuilder.append(StringUtils.join(params,"&")) ;
        switch (enumByKey) {
            case ESTATE:
            case ESTATE_LAND:
            case ESTATE_LAND_INCLUD:

                break;
            case BUILDING:
            case BUILDING_MONOLAYER:
            case BUILDING_BASE:
            case BUILDING_DIFFERENCE:
            case BUILDING_LAND_INCLUD:

                break;
            case UNIT:
            case UNIT_RESIDENCE:
            case UNIT_LAND_INCLUD:

                break;
            case HOUSE:
            case HOUSE_LAND_INCLUD:

                break;
        }
        String generateQRCode = erpRpcToolsService.generateQRCode(stringBuilder.toString());
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
    public boolean deleteBaseQrcode(Integer id) throws Exception {
        SysAttachmentDto sysAttachmentDto = new SysAttachmentDto();
        sysAttachmentDto.setTableId(id);
        sysAttachmentDto.setTableName(FormatUtils.entityNameConvertToTableName(BaseQrcode.class));
        boolean flag = baseQrcodeDao.deleteBaseQrcode(id);
        baseAttachmentService.deleteAttachmentByDto(sysAttachmentDto);
        return flag;
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
