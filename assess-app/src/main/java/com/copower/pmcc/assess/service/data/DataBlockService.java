package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.constant.AssessPhaseKeyConstant;
import com.copower.pmcc.assess.dal.basis.dao.data.DataBlockDao;
import com.copower.pmcc.assess.dal.basis.dao.net.NetInfoAssignTaskDao;
import com.copower.pmcc.assess.dal.basis.dao.project.scheme.SchemeReportFileItemDao;
import com.copower.pmcc.assess.dal.basis.dao.project.survey.SurveyAssetInventoryContentDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dal.basis.mapper.ProjectInfoMapper;
import com.copower.pmcc.assess.dto.output.basic.SurveyAssetInventoryVo;
import com.copower.pmcc.assess.dto.output.data.DataBlockVo;
import com.copower.pmcc.assess.service.*;
import com.copower.pmcc.assess.service.base.BaseAttachmentService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.event.project.ProjectSpotCheckEvent;
import com.copower.pmcc.assess.service.project.ProjectPhaseService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.declare.DeclareRecordService;
import com.copower.pmcc.assess.service.project.generate.GenerateReportGroupService;
import com.copower.pmcc.assess.service.project.generate.GenerateReportInfoService;
import com.copower.pmcc.assess.service.project.generate.GenerateReportItemService;
import com.copower.pmcc.assess.service.project.scheme.SchemeJudgeObjectService;
import com.copower.pmcc.assess.service.project.survey.*;
import com.copower.pmcc.bpm.api.enums.TaskHandleStateEnum;
import com.copower.pmcc.erp.api.dto.SysAttachmentDto;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.DateUtils;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.copower.pmcc.erp.common.utils.LangUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Auther: zch
 * @Date: 2018/9/11 10:01
 * @Description:基础版块维护
 */
@Service
public class DataBlockService {
    @Autowired
    private DataBlockDao dataBlockDao;
    @Autowired
    private CommonService commonService;
    @Autowired
    private ErpAreaService erpAreaService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private ProjectInfoMapper projectInfoMapper;
    @Autowired
    private ProjectPhaseService projectPhaseService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateDataBlock(DataBlock dataBlock) {
        if (dataBlock.getId() == null || dataBlock.getId().intValue() == 0) {
            dataBlock.setCreator(commonService.thisUserAccount());
            return dataBlockDao.addDataBlock(dataBlock);
        } else {
            dataBlockDao.updateDataBlock(dataBlock);
            return dataBlock.getId();
        }
    }

    public DataBlock getDataBlockById(Integer id) {
        if (id == null) {
            logger.error("null point");
            return null;
        }
        return dataBlockDao.getDataBlockById(id);
    }

    public BootstrapTableVo getDataBlockListVos(String province, String city, String district, String name) {
        BootstrapTableVo vo = new BootstrapTableVo();
        RequestBaseParam requestBaseParam = RequestContext.getRequestBaseParam();
        Page<PageInfo> page = PageHelper.startPage(requestBaseParam.getOffset(), requestBaseParam.getLimit());
        List<DataBlockVo> vos = dataBlockVos(province, city, district, name);
        vo.setTotal(page.getTotal());
        vo.setRows(vos);
        return vo;
    }

    public List<DataBlockVo> dataBlockVos(DataBlock dataBlock) {
        List<DataBlock> dataBlocks = dataBlockDao.getDataBlockList(dataBlock);
        List<DataBlockVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataBlocks)) {
            for (DataBlock landLevel : dataBlocks) {
                vos.add(getDataBlockVo(landLevel));
            }
        }
        return vos;
    }

    public List<DataBlockVo> dataBlockVos(String province, String city, String district, String name) {
        List<DataBlock> dataBlocks = dataBlockDao.getDataBlockList(province, city, district, name);
        List<DataBlockVo> vos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(dataBlocks)) {
            for (DataBlock landLevel : dataBlocks) {
                vos.add(getDataBlockVo(landLevel));
            }
        }
        return vos;
    }

    public void removeDataBlock(DataBlock dataBlock) {
        dataBlockDao.removeDataBlock(dataBlock);
    }

    public DataBlockVo getDataBlockVo(DataBlock dataBlock) {
        DataBlockVo vo = new DataBlockVo();
        BeanUtils.copyProperties(dataBlock, vo);
        if (StringUtils.isNotBlank(dataBlock.getProvince())) {
            vo.setProvinceName(erpAreaService.getSysAreaName(dataBlock.getProvince()));
        }
        if (StringUtils.isNotBlank(dataBlock.getCity())) {
            vo.setCityName(erpAreaService.getSysAreaName(dataBlock.getCity()));
        }
        if (StringUtils.isNotBlank(dataBlock.getDistrict())) {
            vo.setDistrictName(erpAreaService.getSysAreaName(dataBlock.getDistrict()));
        }
        return vo;
    }

    /**
     * 根据区域获取版块信息
     *
     * @param province
     * @param city
     * @param distric
     * @return
     */
    public List<DataBlock> getDataBlockListByArea(String province, String city, String distric) {
        if (StringUtils.isBlank(province) || StringUtils.isBlank(city))
            return null;
        DataBlock dataBlock = new DataBlock();
        dataBlock.setProvince(province);
        dataBlock.setCity(city);
        if (StringUtils.isNotBlank(distric))
            dataBlock.setDistrict(distric);
        return dataBlockDao.getDataBlockList(dataBlock);
    }

    /**
     * 版块是否已存在
     *
     * @param province
     * @param city
     * @param distric
     * @param blockName
     * @return
     */
    public Boolean isExistBlock(String province, String city, String distric, String blockName) {
        DataBlock dataBlock = new DataBlock();
        dataBlock.setProvince(province);
        dataBlock.setCity(city);
        if (StringUtils.isNotBlank(distric))
            dataBlock.setDistrict(distric);
        dataBlock.setName(blockName);
        List<DataBlock> blockList = dataBlockDao.getDataBlockList(dataBlock);
        return blockList.size() > 0;
    }

    public void updateOldData(String processInsId) throws Exception {
        //1.inventory_content的数据直接关联到info_item表上
    }
}
