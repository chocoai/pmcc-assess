package com.copower.pmcc.assess.service.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.copower.pmcc.assess.dal.basis.dao.data.DataBlockDao;
import com.copower.pmcc.assess.dal.basis.dao.data.ToolResidueRatioDao;
import com.copower.pmcc.assess.dal.basis.entity.DataBlock;
import com.copower.pmcc.assess.dal.basis.entity.DataDamagedDegree;
import com.copower.pmcc.assess.dal.basis.entity.ToolResidueRatio;
import com.copower.pmcc.assess.dto.output.basic.BasicHouseDamagedDegreeVo;
import com.copower.pmcc.assess.dto.output.data.DataBlockVo;
import com.copower.pmcc.assess.service.ErpAreaService;
import com.copower.pmcc.assess.service.basic.BasicHouseDamagedDegreeService;
import com.copower.pmcc.erp.api.dto.model.BootstrapTableVo;
import com.copower.pmcc.erp.common.CommonService;
import com.copower.pmcc.erp.common.support.mvc.request.RequestBaseParam;
import com.copower.pmcc.erp.common.support.mvc.request.RequestContext;
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
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.HashMap;
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
    private BasicHouseDamagedDegreeService basicHouseDamagedDegreeService;
    @Autowired
    private DataDamagedDegreeService dataDamagedDegreeService;
    @Autowired
    private ToolResidueRatioDao toolResidueRatioDao;


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

    public void saveResidueRatio(String formData) throws Exception {
        JSONObject jsonObject = JSON.parseObject(formData);
        Integer houseId = Integer.valueOf(jsonObject.getString("houseId"));
        //更改数据表分值
        List<BasicHouseDamagedDegreeVo> list = basicHouseDamagedDegreeService.getDamagedDegreeVoList(houseId);
        for (BasicHouseDamagedDegreeVo item : list) {
            String scoreId = "scores" + item.getCategory();
            String reallyScore = jsonObject.getString(scoreId);
            BigDecimal score = new BigDecimal(reallyScore);
            item.setScore(score);
            basicHouseDamagedDegreeService.saveAndUpdateDamagedDegree(item);
        }

        BigDecimal scoreTotal = new BigDecimal("0");
        //结构部分"structural.part"
        BigDecimal scoreStructural = getScoreTotal(houseId, "structural.part");
        //装修部分"decoration.part"
        BigDecimal scoreDecoration = getScoreTotal(houseId, "decoration.part");
        //设备部分"equipment.part"
        BigDecimal scoreEquipment = getScoreTotal(houseId, "equipment.part");
        //其他"other"
        BigDecimal scoreOther = getScoreTotal(houseId, "other");
        scoreTotal = scoreTotal.add(scoreStructural).add(scoreDecoration).add(scoreEquipment).add(scoreOther);
        //保存
        ToolResidueRatio toolResidueRatio = new ToolResidueRatio();
        HashMap<String, String> parameterMap = new HashMap<>();
        parameterMap.put("houseId",jsonObject.getString("houseId"));
        parameterMap.put("usedYear",jsonObject.getString("usedYear"));
        parameterMap.put("usableYear",jsonObject.getString("usableYear"));
        parameterMap.put("ageRate",jsonObject.getString("ageRate"));
        parameterMap.put("observeRate",jsonObject.getString("observeRate"));
        String parameterValue = JSONObject.toJSON(parameterMap).toString();
        toolResidueRatio.setParameterValue(parameterValue);
        toolResidueRatio.setType(Integer.valueOf(jsonObject.getString("method")));
        toolResidueRatio.setResultValue(scoreTotal+"%");
        toolResidueRatio.setCreator(commonService.thisUserAccount());
        toolResidueRatioDao.addToolResidueRatio(toolResidueRatio);
    }

    public BigDecimal getScoreTotal(Integer houseId, String type) {
        DataDamagedDegree degree = dataDamagedDegreeService.getCacheDamagedDegreeByFieldName(type);
        BigDecimal weight = degree.getWeight();
        List<BasicHouseDamagedDegreeVo> structuralList = basicHouseDamagedDegreeService.getDamagedDegreeVoList(houseId, degree.getId());
        BigDecimal scoreTotal = new BigDecimal("0");
        for (BasicHouseDamagedDegreeVo item : structuralList) {
            scoreTotal = scoreTotal.add(item.getScore());
        }
        return scoreTotal.multiply(weight);
    }
}
