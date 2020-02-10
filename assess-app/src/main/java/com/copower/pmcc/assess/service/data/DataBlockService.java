package com.copower.pmcc.assess.service.data;

import com.copower.pmcc.assess.dal.basis.dao.basic.BasicApplyBatchDao;
import com.copower.pmcc.assess.dal.basis.dao.data.DataBlockDao;
import com.copower.pmcc.assess.dal.basis.entity.*;
import com.copower.pmcc.assess.dto.output.data.DataBlockVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchDetailService;
import com.copower.pmcc.assess.service.basic.BasicApplyBatchService;
import com.copower.pmcc.assess.service.project.ProjectInfoService;
import com.copower.pmcc.assess.service.project.ProjectPlanDetailsService;
import com.copower.pmcc.assess.service.project.ProjectPlanService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.exception.BusinessException;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
import com.copower.pmcc.erp.common.utils.FormatUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private ProjectInfoService projectInfoService;
    @Autowired
    private BasicApplyBatchDao basicApplyBatchDao;
    @Autowired
    private BasicApplyBatchService basicApplyBatchService;
    @Autowired
    private BasicApplyBatchDetailService basicApplyBatchDetailService;
    @Autowired
    private ProjectPlanService projectPlanService;
    @Autowired
    private ProjectPlanDetailsService projectPlanDetailsService;


    private final Logger logger = LoggerFactory.getLogger(getClass());

    public Integer saveAndUpdateDataBlock(DataBlock dataBlock) {
        if (dataBlock.getId() == null || dataBlock.getId().intValue() == 0) {
            dataBlock.setCreator(commonService.thisUserAccount());
            try {
                return dataBlockDao.addDataBlock(dataBlock);
            } catch (Exception e1) {
                logger.error(e1.getMessage(), e1);
                return null;
            }
        } else {
            dataBlockDao.updateDataBlock(dataBlock);
            return null;
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

    //将案例库的
    public void caseAllToBasic(){

    }

    public void updateOldData() throws BusinessException {
        //处理老数据
        //1.为每个项目，生成报告的阶段添加一个事项 （生成报告） 主要为了查看以前生成的报告
        //2.将applyBatchDetail中添加 楼盘的主信息，并将楼栋的pid设置为新添加的楼盘主信息
        List<ProjectInfo> projectInfoList = projectInfoService.getProjectInfoList(new ProjectInfo());
        if (CollectionUtils.isNotEmpty(projectInfoList)) {
            for (ProjectInfo projectInfo : projectInfoList) {
                List<ProjectPlan> projectPlanList = projectPlanService.getProjectPlanList(projectInfo.getId());
                if (CollectionUtils.isNotEmpty(projectPlanList)) {
                    ProjectPlan projectPlan = projectPlanList.get(projectPlanList.size() - 1);
                    ProjectPlanDetails projectPlanDetails = new ProjectPlanDetails();
                    projectPlanDetails.setPid(0);
                    projectPlanDetails.setProjectPhaseName("出具报告");
                    projectPlanDetails.setProjectId(projectInfo.getId());
                    projectPlanDetails.setPlanId(projectPlan.getId());
                    projectPlanDetails.setProjectWorkStageId(15);
                    projectPlanDetails.setProjectPhaseId(153);
                    projectPlanDetails.setExecuteUserAccount(projectPlan.getCreator());
                    projectPlanDetails.setBisEnable(true);
                    projectPlanDetails.setSorting(0);
                    projectPlanDetails.setFirstPid(0);
                    projectPlanDetails.setBisStart(true);
                    projectPlanDetails.setProcessInsId("0");
                    projectPlanDetails.setStatus("finish");
                    projectPlanDetails.setBisNew(true);
                    projectPlanDetails.setBisLastLayer(true);
                    projectPlanDetails.setBisRestart(false);
                    projectPlanDetails.setCreator(projectPlan.getCreator());
                    projectPlanDetailsService.saveProjectPlanDetails(projectPlanDetails);
                }
            }
        }
    }
}
